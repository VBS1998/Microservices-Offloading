package com.gustavovbs.microservicesoffloading;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Auction {

    String microserviceName;

    URI host;

    private Bid winner;

    private HashMap<String, Double> weights;
    private HashMap<String, ArrayList<Double>> min_max;


    public Auction(String microserviceName, URI host){
        this.microserviceName = microserviceName;
        this.host = host;
        this.winner = null;

        try {
            String directory = new File("./").getAbsolutePath();
            HashMap<String, Object> file;
            file = new ObjectMapper().readValue(new File(directory.substring(0, directory.length() - 1) + "src/main/java/com/gustavovbs/microservicesoffloading/weights.json"), HashMap.class);

            weights = (HashMap) file.get("rooter");
            min_max = (HashMap) file.get("min-max");

        } catch (Exception e){
            weights = new HashMap<>();
        }
    }

    public void bid(Bid bid){
        double bidValue = valueForBid(bid);
        if (winner == null || bidValue > valueForBid(winner)){
            if(bidValue >= 0.6){
                winner = bid;
            }
        }
    }

    private double valueForBid(Bid bid){
        double value = 0;
        for(String key : weights.keySet()){
            double min = min_max.get(key).get(0);
            double max = min_max.get(key).get(1);

            value += weights.get(key) * (bid.getStat(key) - min)/(max - min);
        }

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
