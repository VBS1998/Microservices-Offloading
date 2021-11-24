package com.gustavovbs.host;

import com.gustavovbs.microservicesoffloading.Auction;
import com.gustavovbs.microservicesoffloading.Bid;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

public class Host {

    URI broker;
    URI url;

    public Host(URI url, URI broker){
        this.url = url;
        this.broker = broker;

        new RestTemplate().postForEntity(broker + "/add_host", url, String.class);
    }

    public String run(String microserviceName) {
        URI serviceURL = null;
        try{
            serviceURL = new URI(url.getPath().substring(0, url.getPath().lastIndexOf('/')));
        } catch(Exception e){
            return "Failed at finding URL";
        }

        //System.out.println("\n\n\n\nURLL: " + serviceURL.toString());
        // Runs the microservice and returns its message (xxx is running) together with the url for his host
        return /*new RestTemplate().getForEntity(serviceURL + "/run/" + microserviceName + "/", String.class)*/ serviceURL.toString() + " on host " + url;
    }

    public String broadcast(String microserviceName){
        Auction auction = new Auction(microserviceName, url);
        return new RestTemplate().postForEntity(broker + "/broadcast", auction, String.class).getBody();
    }

    public Bid bid(Auction auction){
        return new Bid(url);
    }
}
