package graphic;

import java.awt.Color;
import java.io.Serializable;

public class Triangle implements Serializable{
    Vertex v1;
    Vertex v2;
    Vertex v3;
    Color color;
    Triangle(Vertex v1, Vertex v2, Vertex v3, Color color) {
        this.v1 = v1;
        this.v2 = v2;
        this.v3 = v3;
        this.color = color;
    }
    
    public Vertex getV1(){
        return v1;
    }
    
    public Vertex getV2(){
        return v2;
    }
    
    public Vertex getV3(){
        return v3;
    }
}
