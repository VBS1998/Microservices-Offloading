package com.gustavovbs.broker;

import com.gustavovbs.microservicesoffloading.Auction;
import com.gustavovbs.microservicesoffloading.Bid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.time.Duration;
import java.util.ArrayList;
import java.util.concurrent.*;

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

                //Set timeout for 2 seconds
                final Duration timeout = Duration.ofSeconds(2);
                ExecutorService executor = Executors.newSingleThreadExecutor();

                final Future<String> handler = executor.submit(new Callable() {
                    @Override
                    public String call() throws Exception {

                        // Posts for the host to get a bid
                        ResponseEntity<Bid> response = rest.postForEntity(host + "/bid", auction, Bid.class);
                        auction.bid(response.getBody());
                        return "bid received";
                    }
                });

                try {
                    handler.get(timeout.toMillis(), TimeUnit.MILLISECONDS);
                } catch (Exception e) {
                    handler.cancel(true);
                }

                executor.shutdownNow();

//                ResponseEntity<Bid> response = rest.postForEntity(host + "/bid", auction, Bid.class);
//                auction.bid(response.getBody());
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
