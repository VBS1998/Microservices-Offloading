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

    @GetMapping("/")
    public String root(){
        return "Bem vindo. Digite o caminho /run/{service_name} para iniciar um serviço.";
    }


    @GetMapping("/run/{service}")
    public String runMicroservice(@PathVariable("service") String service, HttpServletResponse httpResponse)
    {

        try { httpResponse.sendRedirect("/" + service); }
        catch (Exception e) { return e.toString(); }

        return "ok";
    }

}
