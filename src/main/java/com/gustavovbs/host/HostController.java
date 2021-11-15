package com.gustavovbs.host;

import com.gustavovbs.microservicesoffloading.Auction;
import com.gustavovbs.microservicesoffloading.Bid;
import com.gustavovbs.microservicesoffloading.MicroservicesOffloadingController;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;

@RestController
@RequestMapping("/host")
public class HostController {

    Host host;

    @GetMapping("/")
    public String runHost(HttpServletResponse response){
        try {
            host = new Host(new URI("http://127.0.0.1:8080"), new URI("http://127.0.0.1:8081"));
        } catch (Exception e){
            return e.toString();
        }
        return "Host running.";
    }

    @GetMapping("/run")
    public String runMicroservice(String microservice){
        //Run given microservice
        return host.run(microservice);
    }

    @PostMapping("/bid")
    public Bid bid(Auction auction){
        return host.bid(auction);
    }

    @GetMapping("/broadcast/{microserviceName}")
    public String broadcast(@PathVariable("microserviceName") String microserviceName){

        return host.broadcast(microserviceName);
    }

}