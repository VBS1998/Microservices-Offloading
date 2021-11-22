package com.gustavovbs.broker;

import com.gustavovbs.microservicesoffloading.Auction;
import com.gustavovbs.microservicesoffloading.Bid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;

public class Broker {

    ArrayList<URI> hosts;

    URI url;

    public Broker(URI url){
        this.url = url;
        hosts = new ArrayList<>();
    }

    public String broadcast(Auction auction){
        RestTemplate rest = new RestTemplate();
        for (URI host : hosts){
            if(host.compareTo(auction.getHost()) == 0){ //The auction host should not make a bid
                //TODO: Treat timeout, Paralelizar?
                ResponseEntity<Bid> response = rest.postForEntity(host + "/bid", auction, Bid.class);
                auction.bid(response.getBody());
            }
        }

        //After the auction has ended
        URI winner = auction.close().getHost();
        return rest.postForEntity(winner + "/run", auction.getMicroserviceName(), String.class).getBody();
    }

    public void addHost(URI host){
        hosts.add(host);
    }
}
