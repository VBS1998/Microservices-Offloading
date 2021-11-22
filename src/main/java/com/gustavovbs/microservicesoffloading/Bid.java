package com.gustavovbs.microservicesoffloading;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.net.URI;

public class Bid {

    @JsonProperty("host")
    private URI host;

    public Bid(URI host){
        this.host = host;
    }

    int value(){
        return 0;
    }

    public URI getHost() {
        return host;
    }

    //    int value(func formula){
//        return formula();
//    }

}
