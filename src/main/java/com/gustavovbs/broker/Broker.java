package com.gustavovbs.broker;

import com.gustavovbs.microservicesoffloading.Auction;
import com.gustavovbs.microservicesoffloading.Bid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;

public class Broker {

    ArrayList<URI> hosts;

    public Broker(){
        hosts = new ArrayList<>();
    }

    public String broadcast(Auction auction){
        RestTemplate rest = new RestTemplate();
        for (URI host : hosts){
            //TODO: Check if it is not who started the bid
            ResponseEntity<Bid> response = rest.postForEntity(host + "/bid", auction, Bid.class);
            auction.bid(response.getBody());
        }

        //After the auction has ended
        URI winner = auction.close().getHost();
        return rest.postForEntity(winner + "/run", auction.getMicroserviceName(), String.class).getBody();
    }
}
