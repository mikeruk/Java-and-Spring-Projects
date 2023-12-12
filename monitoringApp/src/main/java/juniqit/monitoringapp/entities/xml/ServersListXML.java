package juniqit.monitoringapp.entities.xml;



import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "servers")
@XmlAccessorType(XmlAccessType.FIELD)
public class ServersListXML {


    @XmlElement(name = "server")
    List<ServerXML> servers;

    public ServersListXML() {
    }


    public ServersListXML(List<ServerXML> servers) {
        this.servers = servers;
    }

}
