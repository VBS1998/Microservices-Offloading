package com.gustavovbs.broker;

import com.gustavovbs.microservicesoffloading.Auction;
import com.gustavovbs.microservicesoffloading.Bid;
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
            if(host.compareTo(auction.getHost()) != 0){ //The auction host should not make a bid

//                //Set timeout for 2 seconds
//                final Duration timeout = Duration.ofSeconds(2);
//                ExecutorService executor = Executors.newSingleThreadExecutor();
//
//                final Future<Bid> handler = executor.submit(new Callable() {
//                    @Override
//                    public Bid call() throws Exception {
//                        // Posts for the host to get a bid
//                        return rest.postForEntity(host + "/bid", auction, Bid.class).getBody();
//                    }
//                });
//
//                try {
//                    //Uses the bid in the auction
//                    auction.bid(handler.get(timeout.toMillis(), TimeUnit.MILLISECONDS));
//                } catch (Exception e) {
//                    handler.cancel(true);
//                }

//                executor.shutdownNow();

                auction.bid(rest.postForEntity(host + "/bid", auction, Bid.class).getBody());
            }
        }
        //After the auction has ended
        Bid winnerBid = auction.close();

        if(winnerBid != null) {
            URI winner = winnerBid.getHost();
            return rest.postForEntity(winner + "/run", auction.getMicroserviceName(), String.class).getBody();
        }
        else{
            return "Auction for " + auction.getMicroserviceName() + " failed.";
        }
    }

    public void addHost(URI host){
        hosts.add(host);
    }
}
