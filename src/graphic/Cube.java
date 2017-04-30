/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphic;

import java.awt.Color;

/**
 *
 * @author Алексей
 */
public class Cube extends Model{
    
    public Cube(double worldX, double worldY, double worldZ, String name) {
        super(worldX, worldY, worldZ, name);
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
    
}
