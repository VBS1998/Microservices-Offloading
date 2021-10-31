package com.gustavovbs.microservicesoffloading;

import com.gustavovbs.broker.Broker;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class MicroservicesOffloadingController {

    public MicroservicesOffloadingController(){
        super();
    }

    @GetMapping("/run/{service}")
    public String run_microservice(@PathVariable("service") String service, HttpServletResponse httpResponse)
    {

        try { httpResponse.sendRedirect("/" + service + "/run"); }
        catch (Exception e) { return e.toString(); }

        return "ok";
    }

    @GetMapping("/close")
    public String instance_closing() {

        return "Changed to" + " (nome da instância nova)";
    }

}
