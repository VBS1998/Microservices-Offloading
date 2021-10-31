package com.gustavovbs.broker;

import com.gustavovbs.microservicesoffloading.Microservice;
import com.gustavovbs.microservicesoffloading.MicroservicesOffloadingController;

public class Broker extends Microservice {

    @Override
    public String run() {

        return "broker ta rodante";
    }

    @Override
    public void close() {
        super.close();
    }
}
