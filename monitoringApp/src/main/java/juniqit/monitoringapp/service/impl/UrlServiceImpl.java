package juniqit.monitoringapp.service.impl;


import jakarta.annotation.PostConstruct;
import jakarta.xml.bind.JAXBException;
import juniqit.monitoringapp.entities.Server;
import juniqit.monitoringapp.entities.xml.ServerXML;
import juniqit.monitoringapp.entities.xml.ServersListXML;
import juniqit.monitoringapp.service.*;
import org.modelmapper.ModelMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class UrlServiceImpl implements UrlService {

    private final FileService fileService;
    private final ServerService serverService;
    private final LogService logService;
    private final ServerXmlService serverXmlService;

    private final ModelMapper modelMapper;

    List<Server> serversToCheck = new ArrayList<>();

    public List<Server> getServersToCheck() {
        return serversToCheck;
    }

    public void setServersToCheck(List<Server> serversToCheck) {
        this.serversToCheck = serversToCheck;
    }

    private URL url;
    private String resolvedDns= "";
    private String icmpPing= "";
    private String testPort= "";
    private String remoteHttpsPort = "";
    private String remoteSmtpPort = "";
    private boolean ready;


    public UrlServiceImpl(FileService fileService, ServerService serverService, LogService logService, ServerXmlService serverXmlService, ModelMapper modelMapper) {
        this.fileService = fileService;
        this.serverService = serverService;
        this.logService = logService;
        this.serverXmlService = serverXmlService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    @Override
    public void testUrlConnection() throws IOException, JAXBException {
        List<ServerXML> serversXMLlist = new ArrayList<>();

        fileService.parseAllURLs(fileService.readServerURLs())
        .forEach(url -> {
            this.url = url;
            try {
                String hostIpAddress = checkDNSdomainName(url);
                if (this.resolvedDns.equals("success")) {
                    testPingICMP(hostIpAddress);
                    connectToPortHttps(url);
                    connectToPortSmtp(url);
                    Server server = new Server(
                            logService.createTimeStamp().toString(),
                            this.url,
                            this.resolvedDns,
                            this.icmpPing,
                            this.testPort,
                            this.remoteHttpsPort,
                            this.remoteSmtpPort
                    );

                    server.analyse();
                    this.serversToCheck.add(server);

                    //XML single server create:
                    ServerXML xmlServer = new ServerXML(logService.createTimeStamp().toString(),
                            this.url.toString(),
                            this.resolvedDns,
                            this.icmpPing,
                            this.testPort,
                            this.remoteHttpsPort,
                            this.remoteSmtpPort,
                            server.getNote());

                    serversXMLlist.add(xmlServer);
                } else {
                    Server server = new Server();
                    server.setTimeStamp(logService.createTimeStamp().toString());
                    server.setUrl(this.url);
                    server.setResolvedDns(this.resolvedDns);
                    server.analyse();
                    this.serversToCheck.add(server);

                    ServerXML xmlServer = new ServerXML();
                    xmlServer.setTimeStamp(logService.createTimeStamp().toString());
                    xmlServer.setUrl(this.url.toString());
                    xmlServer.setResolvedDns(this.resolvedDns);
                    xmlServer.setNote(server.getNote());
                    serversXMLlist.add(xmlServer);
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });

        ServersListXML serversListXML = new ServersListXML(serversXMLlist);
        serverXmlService.printXML(serversListXML);

        serverService.setServersList(this.serversToCheck);
        this.ready = true;
        this.serversToCheck = new ArrayList<>();

    }

    @Override
    public String checkDNSdomainName(URL url) {
        try {
            InetAddress address = InetAddress.getByName(url.getHost());
            String hostIpAddress = address.getHostAddress();
            this.resolvedDns = "success";
            return hostIpAddress;
        } catch (UnknownHostException ex) {
            this.resolvedDns = "failed";
        }
        return null;
    }

    @Override
    public void testPingICMP(String hostIpAddress) throws IOException {
        //Exception is never thrown in this code:
        if (InetAddress.getByName(hostIpAddress).isReachable(2000)) {
            this.icmpPing = "success";
        } else {
            this.icmpPing = "failed";
        }
    }
    @Override
    public boolean connectToPortHttps(URL url) throws IOException {
        if (testPort(url, 443, 2000)) {
            this.testPort = "reached";
            testPortForActiveService(url, 443, 2000);
        } else{
            this.testPort = "not reached";
        }
        return false;
    }

    @Override
    public boolean connectToPortSmtp(URL url) throws IOException {
        if (this.testPort.equals("reached")) {
            testPortForActiveService(url, 25, 2000);
        }
        return false;
    }


    private boolean testPort(URL url, int port, int timeout) throws IOException {
        Socket socket = new Socket();
        try {
            // Connects this socket to the server with a specified timeout value.
            socket.connect(new InetSocketAddress(url.getHost(), port), timeout);
            //Return true if connection successful
            return true;
        } catch (IOException exception) {
            //Will return false if connection fails
            return false;
        } finally {
            socket.close();
        }

    }


    public boolean testPortForActiveService(URL url, int port, int timeout) throws IOException {
        Socket socket = new Socket();
        try {
            //wait THAT LONG to receive any bytes as input.
            socket.setSoTimeout(timeout);
            // Connects this socket to the server with a specified timeout value.
            socket.connect(new InetSocketAddress(url.getHost(), port), timeout);

            // If the connection is successful, you can write something to the server
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write("Test Socket Connection!".getBytes());
            outputStream.flush();

            // Wait for a response from the server
            InputStream inputStream = socket.getInputStream();
            byte[] buffer = new byte[1024];
            int bytesRead = inputStream.read(buffer);

            if (bytesRead > 0) {
                if (port == 443) {
                    this.remoteHttpsPort = "listening";
                } else if (port == 25) {
                    this.remoteSmtpPort = "listening";
                };
            } else {
                if (port == 443) {
                    this.remoteHttpsPort = "inactive";
                } else if (port == 25) {
                    this.remoteSmtpPort = "inactive";
                }
            }
        } catch (IOException exception) {
            if (port == 443) {
                this.remoteHttpsPort = "off";
            } else if (port == 25) {
                this.remoteSmtpPort = "off";
            }

            //Will return false if connection fails
            return false;
        } finally {
            socket.close();
        }
        return false;
    }



//    @Scheduled(cron = "* * * * * *")
    @Scheduled(fixedRate = 60000)
    void testSchedule() throws IOException, JAXBException {
        if(this.ready = true){
            String timeStamp = logService.createTimeStamp().toString();
            System.out.println("\n"+timeStamp);
            this.testUrlConnection();
        } else {
            System.out.println("Waiting...");
        }
    }



}



