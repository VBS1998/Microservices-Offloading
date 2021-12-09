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
        return "Info is running";
    }

    @GetMapping("/long_string")
    public String long_string(){
        return info.long_strings();
    }
}
