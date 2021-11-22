package com.gustavovbs.microservicesoffloading;

import java.net.URI;

public class Bid {

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
}
