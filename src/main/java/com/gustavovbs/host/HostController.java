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
            host = new Host(new URI("http://ec2-54-205-105-151.compute-1.amazonaws.com:8080/host"), new URI("http://ec2-54-242-237-17.compute-1.amazonaws.com:8080/broker"));
        } catch (Exception e){
            return e.toString();
        }
        return "Host running.";
    }

    @PostMapping("/run")
    public String runMicroservice(String microservice){
        //Run given microservice
        return host.run(microservice);
    }

    @PostMapping(value = "/bid")
    public Bid bid(@RequestBody Auction auction){
        return host.bid(auction);
    }

    @GetMapping("/broadcast/{microserviceName}")
    public String broadcast(@PathVariable("microserviceName") String microserviceName){

        return host.broadcast(microserviceName);
    }

}