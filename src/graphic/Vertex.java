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
    
    static Vertex multiply(Vertex v1, Vertex v2){
        return new Vertex( v1.y * v2.z - v1.z * v2.y,
                v1.x * v2.z - v1.z * v2.x,
                v1.x * v2.y - v1.y * v2.x);
    }
    
    static double multiplyD(Vertex v1, Vertex v2){
        return v1.x * v2.x + v1.y * v2.y + v1.z * v2.z; 
    }
    
    static Vertex multiply(Vertex v, double number){
        return new Vertex(v.x * number, v.y * number, v.z * number);
    }
    
    static Vertex getVector(Vertex v1, Vertex v2){
        return new Vertex(v2.x - v1.x, v2.y - v1.y, v2.z - v1.z);
    }
    
    static double getLen(Vertex v1){
        return Math.sqrt(v1.x * v1.x + v1.y * v1.y + v1.z * v1.z);
    }
    
    static double multiplyScalar(Vertex view, Vertex n, Vertex v1){
        Vertex cam = getVector(v1, view);
        Vertex normal = getVector(v1, n);
        return multiplyD(cam, normal) /  (getLen(cam) * getLen(normal));
    }
    
    static Vertex normalize(Vertex v1, Vertex v2, Vertex v3){
        Vertex a = new Vertex(v3.x - v1.x, v3.y - v1.y, v3.z - v1.z);
        Vertex b = new Vertex(v2.x - v1.x, v2.y - v2.y, v2.z - v1.z);
        return multiply(a, b);
    }
    
    static Vertex getVectorE(Vertex v1, Vertex v2){
        return new Vertex(v1.x + v2.x, v1.y + v2.y, v1.z + v2.z);
    }
    
    static Vertex getE(Vertex v){
        double len = getLen(v);
        v.x /= len;
        v.y /= len;
        v.z /= len;
        return v;
    }
}
