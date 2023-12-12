package juniqit.monitoringapp.service;

import juniqit.monitoringapp.entities.Server;

import java.util.List;

public interface ServerService {

    void addServerToList(Server server);

    List<Server> getServersList();

    void setServersList(List<Server> serversList);


}
