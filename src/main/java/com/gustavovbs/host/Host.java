package com.gustavovbs.host;

import com.gustavovbs.microservicesoffloading.Auction;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

public class Host {

    URI broker;

    public Host(URI broker){
        this.broker = broker;
    }

    public String run(String microserviceName) {

        return microserviceName + "is running on node bla";
    }

    public String broadcast(String microserviceName){
        Auction auction = new Auction(microserviceName);
        return new RestTemplate().postForEntity(broker + "/broadcast", auction, String.class).getBody();
    }
}
