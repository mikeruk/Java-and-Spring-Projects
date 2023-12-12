package juniqit.monitoringapp.service;

import jakarta.xml.bind.JAXBException;

import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.URL;
import java.net.UnknownHostException;

public interface UrlService {


    void testUrlConnection() throws IOException, JAXBException;

    String checkDNSdomainName(URL url);

    //void checkAllDnsDomainNames(URL url);

    void testPingICMP(String hostIpAddress) throws IOException;

    public boolean connectToPortHttps(URL url) throws IOException;

    public boolean connectToPortSmtp(URL url) throws IOException;




}
