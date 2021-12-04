package com.gustavovbs.matrix;

import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

@RestController
@RequestMapping("/matrix")
public class MatrixController {

    public static class DefBody implements Serializable {
        public int seed, size;
        public DefBody(){};
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
        Double[][] res = matrix.def(body.seed, body.size);
        String ret = "";

        for(int i = 0; i < body.size; i++){
            for(int j = 0; j < body.size; j++){
                ret += res[i][j] + " ";
            }
            ret += "\n";
        }

        return ret;
    }

}
