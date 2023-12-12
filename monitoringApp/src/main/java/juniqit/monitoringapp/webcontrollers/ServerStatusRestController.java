package juniqit.monitoringapp.webcontrollers;


import juniqit.monitoringapp.entities.Server;
import juniqit.monitoringapp.service.ServerService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


import java.util.ArrayList;
import java.util.List;



@RestController
@RequestMapping()
public class ServerStatusRestController {
    private final ServerService serverService;
    public ServerStatusRestController(ServerService serverService) {
        this.serverService = serverService;
    }

    @CrossOrigin(origins = "http://localhost:80", methods = {RequestMethod.GET})
    @GetMapping("/monitoring-rest-api")
    public ResponseEntity<CollectionModel<EntityModel<Server>>> getAllServers() {

        List<EntityModel<Server>> entityModels = serverService.getServersList().
                stream().map(
                        s -> EntityModel.of(s, getServerLinks(s))).toList();
        CollectionModel<EntityModel<Server>> collectionModel =
                CollectionModel.of(entityModels);
        return ResponseEntity.ok(collectionModel);
    }

    private Link[] getServerLinks(Server server) {
        List<Link> ServersLinks = new ArrayList<>();
        Link serversLink = linkTo(methodOn(ServerStatusRestController.class).getAllServers()).withRel("servers-link");
        ServersLinks.add(serversLink);
        return ServersLinks.toArray(new Link[0]);
    }
}
