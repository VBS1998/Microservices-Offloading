package com.gustavovbs.broker;

import com.gustavovbs.microservicesoffloading.Auction;
import com.gustavovbs.microservicesoffloading.MicroservicesOffloadingController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;

@RestController
@RequestMapping("/broker")
public class BrokerController {

    Broker broker;

    @GetMapping("/")
    public String runMicroservice(HttpServletResponse request){
        //request.get
        try {
            URI url = new URI("http://127.0.0.1:8080");
            broker = new Broker(url);
        }catch (Exception e){
            return e.toString();
        }
        return "Broker is running";
    }

    @PostMapping("/broadcast")
    public String broadcast(Auction auction){
        return broker.broadcast(auction);
    }

    @PostMapping("/add_host")
    public String addHost(URI host) {
        broker.addHost(host);
        return "ok";
    }
}
