package com.gustavovbs.microservicesoffloading;

import java.net.URI;

public class Auction {

    String microserviceName;

    URI host;

    private Bid winner;

    public Auction(String microserviceName, URI host){
        this.microserviceName = microserviceName;
        this.host = host;
    }

    public void bid(Bid bid){
        if (winner == null || bid.value() > winner.value()){
            winner = bid;
        }
    }

    public Bid close(){
        return winner;
    }

    public String getMicroserviceName(){
        return microserviceName;
    }

    public URI getHost(){
        return host;
    }
}
