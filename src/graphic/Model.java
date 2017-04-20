/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphic;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Алексей
 */
public class Model implements Coordinats{
    public List<Triangle> triangles = new ArrayList<>();
    public double worldX, worldY, worldZ;
    public double angleX, angleY, angleZ;
    public double dx, dy, dz;
    
    public Model(double worldX, double worldY, double worldZ){
        angleX = angleY = angleZ = 0;
        dx = dy = dz = 1;
        this.worldX = worldX;
        this.worldY = worldY;
        this.worldZ = worldZ;
        createModel();
    }
    
    public void createModel(){
        triangles.add(new Triangle(new Vertex(50, -50, -50),
                              new Vertex(50, -50, 50),
                              new Vertex(50, 50, 50),
                              Color.YELLOW));
        triangles.add(new Triangle(new Vertex(50, 50, 50),
                              new Vertex(50, 50, -50),
                              new Vertex(50, -50, -50),
                              Color.YELLOW));
        triangles.add(new Triangle(new Vertex(50, 50, 50),
                              new Vertex(50, -50, 50),
                              new Vertex(-50, 50, 50),
                              Color.WHITE));
        triangles.add(new Triangle(new Vertex(50, -50, 50),
                              new Vertex(-50, 50, 50),
                              new Vertex(-50, -50, 50),
                              Color.WHITE));
        triangles.add(new Triangle(new Vertex(50, 50, -50),
                              new Vertex(-50, 50, -50),
                              new Vertex(50, -50, -50),
                              Color.RED));
        triangles.add(new Triangle(new Vertex(-50, -50, -50),
                              new Vertex(50, -50, -50),
                              new Vertex(-50, 50, -50),
                              Color.RED));
        triangles.add(new Triangle(new Vertex(-50, -50, 50),
                              new Vertex(-50, -50, -50),
                              new Vertex(-50, 50, 50),
                              Color.GREEN));
        triangles.add(new Triangle(new Vertex(-50, 50, -50),
                              new Vertex(-50, 50, 50),
                              new Vertex(-50, -50, -50),
                              Color.GREEN));
        triangles.add(new Triangle(new Vertex(50, 50, 50),
                              new Vertex(50, 50, -50),
                              new Vertex(-50, 50, 50),
                              Color.BLUE));
        triangles.add(new Triangle(new Vertex(-50, 50, -50),
                              new Vertex(50, 50, -50),
                              new Vertex(-50, 50, 50),
                              Color.BLUE));
        triangles.add(new Triangle(new Vertex(50, -50, 50),
                              new Vertex(50, -50, -50),
                              new Vertex(-50, -50, 50),
                              Color.BLUE));
        triangles.add(new Triangle(new Vertex(-50, -50, -50),
                              new Vertex(50, -50, -50),
                              new Vertex(-50, -50, 50),
                              Color.BLUE));
        triangles.add(new Triangle(new Vertex(100, 100, 100),
                              new Vertex(100, -100, 100),
                              new Vertex(-100, 100, 100),
                              Color.WHITE));
         triangles.add(new Triangle(new Vertex(100, -100, 100),
                              new Vertex(-100, 100, 100),
                              new Vertex(-100, -100, 100),
                              Color.WHITE));
    }
    
    public List<Triangle> getMatrix(){
        double x = Math.toRadians(angleX);
        double y = Math.toRadians(angleY);
        double z = Math.toRadians(angleZ);
        Matrix3 newCoordinats =  Matrix3.getRotate(x, y, z).multiply( Matrix3.getZoom(dx, dy, dz) )
                .multiply(Matrix3.getWorld(worldX, worldY, worldZ));
        List<Triangle> newTriangles = new ArrayList<>();
   
        for(Triangle t : triangles){
            Vertex v1 = newCoordinats.transform(t.v1);
            Vertex v2 = newCoordinats.transform(t.v2);
            Vertex v3 = newCoordinats.transform(t.v3);
            newTriangles.add(new Triangle(v1, v2, v3, t.color));
        }
        
        return newTriangles;
    }

    @Override
    public void setTriangle(List<Triangle> triangles) {
        this.triangles = triangles;
    }
}
