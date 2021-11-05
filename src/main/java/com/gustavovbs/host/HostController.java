package com.gustavovbs.host;

import com.gustavovbs.microservicesoffloading.Auction;
import com.gustavovbs.microservicesoffloading.Bid;
import com.gustavovbs.microservicesoffloading.MicroservicesOffloadingController;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@RestController
@RequestMapping("/host")
public class HostController extends MicroservicesOffloadingController {

    URI path;
    Host host;

    public HostController(){
        super();
        try {
            host = new Host(new URI("")); //Broker URI
            path = new URI(""); //Self URI
        } catch (Exception e){}
    }

    @GetMapping("/run")
    public String runMicroservice(String microservice){
        //Run given microservice
        return host.run(microservice);
    }

    @PostMapping("/bid")
    public Bid bid(Auction auction){
        //TODO: Use Host class
        return new Bid(path);
    }

    @GetMapping("/broadcast/{microserviceName}")
    public String broadcast(@PathVariable("microserviceName") String microserviceName){

        return host.broadcast(microserviceName);
    }

}