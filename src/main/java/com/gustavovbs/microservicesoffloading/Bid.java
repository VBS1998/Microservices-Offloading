package com.gustavovbs.microservicesoffloading;

import java.net.URI;
import java.util.HashMap;

public class Bid {

    private URI host;

    private HashMap<String, Float> stats;

    public Bid(){}

    public Bid(URI host){
        this.host = host;

        //TODO: Initialize stats
        stats = new HashMap<>();
        stats.put("cpu", 10.0f);
    }

    public float getStat(String key){
        return (stats.keySet().contains(key)) ? stats.get(key) : 0.0f;
    }

    public URI getHost() {
        return host;
    }
}
