/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphic;

import java.io.Serializable;

/**
 *
 * @author Алексей
 */
public class Vertex implements Serializable {
     double x;
    double y;
    double z;
    double w;
    Vertex(double x, double y, double z, double w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }
    
    Vertex(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = 1;
    }
    
    static Vertex multipy(Vertex v1, Vertex v2){
        return new Vertex( v1.y * v2.z - v1.z * v2.y,
                v1.x * v2.z - v1.z * v2.x,
                v1.x * v2.y - v1.y * v2.x);
    }
    
    static Vertex getVector(Vertex v1, Vertex v2){
        return new Vertex(v2.x - v1.x, v2.y - v1.y, v2.z - v1.z);
    }
    
    static double multiplyScalar(Vertex view, Vertex n){
        return n.x * view.x + n.y + view.y + n.z * view.z;
    }
}
