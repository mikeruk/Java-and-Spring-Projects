package juniqit.monitoringapp.service.impl;

import juniqit.monitoringapp.entities.Server;
import juniqit.monitoringapp.service.ServerService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServerServiceImpl implements ServerService {

    public List<Server> serversList = new ArrayList<>();

    public ServerServiceImpl() {
    }

    @Override
    public void addServerToList(Server server) {
        serversList.add(server);
    }

    @Override
    public List<Server> getServersList() {
        return this.serversList;
    }
    @Override
    public void setServersList(List<Server> serversList) {
        this.serversList = serversList;
    }

}
