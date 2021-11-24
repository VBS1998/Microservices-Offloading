package com.gustavovbs.microservicesoffloading;

import java.io.File;
import java.net.URI;
import java.util.HashMap;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Auction {

    String microserviceName;

    URI host;

    private Bid winner;

    private HashMap<String, Float> weights;

    public Auction(String microserviceName, URI host){
        this.microserviceName = microserviceName;
        this.host = host;

        try {
            weights = new ObjectMapper().readValue(new File("weights.json"), HashMap.class);
        } catch (Exception e){
            weights = new HashMap<>();
            System.out.println(e);
        }
    }

    public void bid(Bid bid){
        if (winner == null || valueForBid(bid) > valueForBid(winner)){
            winner = bid;
        }
    }

    private float valueForBid(Bid bid){
        float value = 0;
        for(String key : weights.keySet()){
            value += weights.get(key) * bid.getStat(key);
        }
        System.out.println("value: " + value);
        return value;
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
