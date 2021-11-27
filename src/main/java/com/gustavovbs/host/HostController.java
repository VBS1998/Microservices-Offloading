package com.gustavovbs.host;

import com.gustavovbs.microservicesoffloading.Auction;
import com.gustavovbs.microservicesoffloading.Bid;
import com.gustavovbs.microservicesoffloading.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@RestController
@RequestMapping("/host")
public class HostController {

    @Autowired
    private Configuration configuration;

    Host host;

    @GetMapping("/")
    public String runHost(){
        try {

            URI ipCheck = new URI("http://checkip.amazonaws.com");
            String ipv4 = new RestTemplate().getForEntity(ipCheck, String.class).getBody();
            ipv4 = ipv4.substring(0, ipv4.length() - 1); // Drops last blank character
            URI hostURL = new URI("http://" + ipv4 + ":" + configuration.getPort() + "/host");

            host = new Host(hostURL, new URI("http://ec2-54-242-237-17.compute-1.amazonaws.com:8080/broker"));
        } catch (Exception e){
            return e.toString();
        }
        return "Host running.";
    }

    @PostMapping("/run")
    public String runMicroservice(@RequestBody String microservice){
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