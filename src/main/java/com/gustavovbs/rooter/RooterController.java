package com.gustavovbs.rooter;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rooter")
public class RooterController {

    private Rooter rooter;

    @GetMapping("/")
    public String runRooter(){
        rooter = new Rooter();
        return "Rooter is running";
    }

    @PostMapping("/sqrt")
    public Double sqrt(@RequestBody Double x){
        return rooter.root(x);
    }

}
