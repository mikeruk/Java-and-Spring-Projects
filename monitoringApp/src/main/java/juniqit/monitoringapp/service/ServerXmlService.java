package juniqit.monitoringapp.service;
import jakarta.xml.bind.JAXBException;
import juniqit.monitoringapp.entities.xml.ServersListXML;

public interface ServerXmlService {
    void printXML(ServersListXML serversListXML) throws JAXBException, JAXBException;

}
