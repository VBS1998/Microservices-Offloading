package com.gustavovbs.microservicesoffloading;

public class Auction {

    String microserviceName;

    private Bid winner;

    public Auction(String microserviceName){
        this.microserviceName = microserviceName;
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
}
