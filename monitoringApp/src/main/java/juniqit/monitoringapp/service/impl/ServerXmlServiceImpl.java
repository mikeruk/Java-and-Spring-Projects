package juniqit.monitoringapp.service.impl;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

import juniqit.monitoringapp.entities.xml.ServersListXML;
import juniqit.monitoringapp.service.ServerXmlService;
import org.springframework.stereotype.Service;


@Service
public class ServerXmlServiceImpl implements ServerXmlService {


    @Override
    public void printXML(ServersListXML serversListXML) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(ServersListXML.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(serversListXML, System.out);
    }
}
