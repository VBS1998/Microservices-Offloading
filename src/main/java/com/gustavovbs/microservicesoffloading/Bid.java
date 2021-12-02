package com.gustavovbs.microservicesoffloading;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.net.URI;
import java.util.HashMap;

public class Bid {

    private URI host;

    private HashMap<String, Double> stats;

    public Bid(){}

    public Bid(URI host){
        this.host = host;

        // Initialize stats
        try {
            String directory = new File("./").getAbsolutePath();
            stats = new ObjectMapper().readValue(new File(directory.substring(0, directory.length() - 1) + "src/main/java/com/gustavovbs/microservicesoffloading/stats.json"), HashMap.class);

        } catch (Exception e){
            stats = new HashMap<>();
        }
    }

    public double getStat(String key){
        return (stats.keySet().contains(key)) ? stats.get(key) : 0.0;
    }

    public URI getHost() {
        return host;
    }
}
