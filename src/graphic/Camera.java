/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Алексей
 */
public class Camera implements Coordinats, Serializable{
    public double x, y, z;
    public double d;
    public double angleX, angleY, angleZ;
    public int width, height;
    List<Triangle> triangles = new ArrayList<>();
    
    public Camera(){
        width = height = 0;
        angleX = angleY = angleZ = 0;
        x = y = z = 0;
        d = 0;
    }
    
    @Override
    public void setTriangle(List<Triangle> triangles) {
        this.triangles = triangles;
    }
    
    public Matrix3 getCam(){
        double rX = Math.toRadians(angleX);
        double rY = Math.toRadians(angleY);
        double rZ = Math.toRadians(angleZ);
        
        return Matrix3.getCam(x, y, z, 
                rX, rY, rZ, d);
    }
    
}
