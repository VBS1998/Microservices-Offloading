package com.gustavovbs.broker;

import com.gustavovbs.microservicesoffloading.Auction;
import com.gustavovbs.microservicesoffloading.MicroservicesOffloadingController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/broker")
public class BrokerController extends MicroservicesOffloadingController {

    Broker broker;

    public BrokerController(){
        super();
        broker = new Broker();
    }

    @GetMapping("/")
    public String runMicroservice(){
        return "Broker is running";
    }

    @PostMapping("/broadcast")
    public String broadcast(Auction auction){
        return broker.broadcast(auction);
    }

}
