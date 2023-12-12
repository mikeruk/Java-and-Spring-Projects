package juniqit.monitoringapp.entities;

import java.net.URL;

public class Server {


    String timeStamp;
    private URL url;
    private String resolvedDns;
    private String icmpPing;
    private String testPort;
    private String remoteHttpsPort;
    private String remoteSmtpPort;

    private String note = "";

    public Server() {
    }

    public Server(String timeStamp, URL url, String resolvedDns, String icmpPing, String testPort, String remoteHttpsPort, String remoteSmtpPort) {
        this.timeStamp = timeStamp;
        this.url = url;
        this.resolvedDns = resolvedDns;
        this.icmpPing = icmpPing;
        this.testPort = testPort;
        this.remoteHttpsPort = remoteHttpsPort;
        this.remoteSmtpPort = remoteSmtpPort;
    }

    public void analyse(){
        if(this.resolvedDns.equals("failed")) {
            this.note += String.format("Could not resolve %s \n", url.getHost());
        } else {
            if(this.icmpPing.equals("failed") && (this.remoteHttpsPort.equals("off")
                                                    || this.remoteSmtpPort.equals("off"))){
                this.note += String.format("Ping ICMP failed, because either blocking the ICMP probes or host is down. \n", url.getHost());


            } else if(this.icmpPing.equals("failed") && (this.remoteHttpsPort.equals("listening")
                                                        || this.remoteHttpsPort.equals("inactive")
            )                                           || this.remoteSmtpPort.equals("listening")
                                                        || this.getRemoteSmtpPort().equals("inactive")){
                this.note += String.format("Host is UP, but either blocking ICMP or there is a network issue. \n", url.getHost());
            }

            if(this.testPort.equals("not reached")){
                this.note += String.format("Some network error prevented from reaching to any port on %s. \n", url.getHost());
            }

            if(this.remoteHttpsPort.equals("listening")){
                this.note += String.format("There is an active service listening on port 443. \n");
            } else if(this.remoteHttpsPort.equals("inactive")){
                this.note += String.format("Port 443 is open but no service is listening on it. \n");
            }else if(this.getRemoteSmtpPort().equals("off")){
                this.note += String.format("Port 443 is closed or host is shut down. \n");
            }else if(this.remoteHttpsPort.isBlank() && this.testPort.equals("reached") && this.remoteSmtpPort.equals("listening")){
                this.note += String.format("Port 443 is closed. \n");
            } else if(this.remoteHttpsPort.isBlank() && this.testPort.equals("reached") && this.remoteSmtpPort.isBlank()){
                this.note += String.format("Either port 443 is closed or the Operating System is shut down. \n");
            }

            if(this.remoteSmtpPort.equals("listening")){
                this.note += String.format("There is an active service listening on port 25. \n");
            }else if(this.getRemoteSmtpPort().equals("inactive")){
                this.note += String.format("Port 25 is open but no service is listening on it. \n");
            } else if(this.getRemoteSmtpPort().equals("off")){
                this.note += String.format("Port 25 is closed or host is shut down. \n");
            }else if(this.remoteSmtpPort.isBlank() && this.testPort.equals("reached") && this.remoteHttpsPort.equals("listening")){
                this.note += String.format("Port 25 is closed. \n");
            } else if(this.remoteSmtpPort.isBlank() && this.testPort.equals("reached") && this.remoteHttpsPort.isBlank()){
                this.note += String.format("Either port 25 is closed or the Operating System is shut down. \n");
            }



        }
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public String getResolvedDns() {
        return resolvedDns;
    }

    public void setResolvedDns(String resolvedDns) {
        this.resolvedDns = resolvedDns;
    }

    public String getIcmpPing() {
        return icmpPing;
    }

    public void setIcmpPing(String icmpPing) {
        this.icmpPing = icmpPing;
    }


    public String getTestPort() {
        return testPort;
    }

    public void setTestPort(String testPort) {
        this.testPort = testPort;
    }

    public String getRemoteHttpsPort() {
        return remoteHttpsPort;
    }

    public void setRemoteHttpsPort(String remoteHttpsPort) {
        this.remoteHttpsPort = remoteHttpsPort;
    }

    public String getRemoteSmtpPort() {
        return remoteSmtpPort;
    }

    public void setRemoteSmtpPort(String remoteSmtpPort) {
        this.remoteSmtpPort = remoteSmtpPort;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }


    @Override
    public String toString() {
        return "Server{" +
                "timeStamp='" + timeStamp + '\'' +
                ", url=" + url +
                ", resolvedDns='" + resolvedDns + '\'' +
                ", icmpPing='" + icmpPing + '\'' +
                ", testPort='" + testPort + '\'' +
                ", remoteHttpsPort='" + remoteHttpsPort + '\'' +
                ", remoteSmtpPort='" + remoteSmtpPort + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}
