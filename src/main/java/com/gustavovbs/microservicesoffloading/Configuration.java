package com.gustavovbs.microservicesoffloading;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("server")
public class Configuration {

    private int port;

    public void setPort(int port) {
        this.port = port;
    }
    public int getPort() {
        return port;
    }
}
