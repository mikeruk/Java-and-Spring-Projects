package juniqit.monitoringapp.entities.xml;


import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "server")
@XmlAccessorType(XmlAccessType.FIELD)
public class ServerXML {


    @XmlAttribute(name = "time-stamp")
    String timeStamp;

    @XmlAttribute(name = "url")
    private String url;

    @XmlAttribute(name = "resolved-dns")
    private String resolvedDns;
    @XmlAttribute(name = "icmp-ping")
    private String icmpPing;

    @XmlAttribute(name = "network-port")
    private String testPort;

    @XmlAttribute(name = "https-port")
    private String remoteHttpsPort;

    @XmlAttribute(name = "smtp-port")
    private String remoteSmtpPort;

    @XmlAttribute(name = "analysis")
    private String note;


    public ServerXML() {
    }

    public ServerXML(String timeStamp, String url, String resolvedDns, String icmpPing, String testPort, String remoteHttpsPort, String remoteSmtpPort, String note) {
        this.timeStamp = timeStamp;
        this.url = url;
        this.resolvedDns = resolvedDns;
        this.icmpPing = icmpPing;
        this.testPort = testPort;
        this.remoteHttpsPort = remoteHttpsPort;
        this.remoteSmtpPort = remoteSmtpPort;
        this.note = note;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
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


}
