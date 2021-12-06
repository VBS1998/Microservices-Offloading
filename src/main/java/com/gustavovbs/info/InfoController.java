package com.gustavovbs.info;

import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

@RestController
@RequestMapping("/info")
public class InfoController {

    public static class DefBody implements Serializable {
        public int value;
        public DefBody(){};
        public int getValue() { return value; }
        public void setValue(int value) { this.value = value; }
    }

    private Info info;

    @GetMapping("/")
    public String runInfo(){
        info = new Info();
        return "Rooter is running";
    }

    @GetMapping("/nums")
    public Double[] nums(){
        return info.nums();
    }
}
