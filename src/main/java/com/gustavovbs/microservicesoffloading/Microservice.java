package com.gustavovbs.microservicesoffloading;

import java.lang.ref.WeakReference;

public abstract class Microservice {

    protected String path = "";

    public String run(){
        return "Run Method must be implemented.";
    }
    public void close(){}
}
