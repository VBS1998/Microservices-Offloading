package com.gustavovbs.matrix;

import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

@RestController
@RequestMapping("/matrix")
public class MatrixController {

    public class DefBody implements Serializable {
        public int seed, size;
        public DefBody(int seed, int size){
            this.seed = seed;
            this.size = size;
        }
        public int getSeed() { return seed; }
        public void setSeed(int seed) { this.seed = seed; }
        public int getSize() { return size; }
        public void setSize(int size) { this.size = size; }
    }

    private Matrix matrix;

    @GetMapping("/")
    public String runRooter(){
        matrix = new Matrix();
        return "Matrix is running";
    }

    @PostMapping("/default")
    public String def(@RequestBody DefBody body){
        return matrix.def(body.seed, body.size).toString();
    }

}
