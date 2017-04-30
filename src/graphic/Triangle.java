/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphic;

import java.awt.Color;
import java.io.Serializable;

/**
 *
 * @author Алексей
 */
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
