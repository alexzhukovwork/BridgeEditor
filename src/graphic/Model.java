/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphic;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Алексей
 */
public class Model implements Coordinats, Serializable{
    private String name;
    public List<Triangle> triangles = new ArrayList<>();
    public double worldX, worldY, worldZ;
    public double angleX, angleY, angleZ;
    public double dx, dy, dz;
    
    public Model(double worldX, double worldY, double worldZ, String name){
        this.name = name;
        angleX = angleY = angleZ = 0;
        dx = dy = dz = 1;
        this.worldX = worldX;
        this.worldY = worldY;
        this.worldZ = worldZ;
    }
    
    public String getName(){
        return name;
    }
    
    public Matrix3 getMatrix(){
        double x = Math.toRadians(angleX);
        double y = Math.toRadians(angleY);
        double z = Math.toRadians(angleZ);
        return Matrix3.getRotate(x, y, z).multiply( Matrix3.getZoom(dx, dy, dz) )
                .multiply(Matrix3.getWorld(worldX, worldY, worldZ));
    }

    @Override
    public void setTriangle(List<Triangle> triangles) {
        this.triangles = triangles;
    }
    
    protected List<Triangle> getRotateTriangle(double width, double height, double thick,
        double localX, double localY, double localZ,
        double angleX, double angleY, double angleZ, int number){
        List<Triangle> triangles = new ArrayList<>();
        if(number == 0)
            triangles = createTriangleFirst(width, height, thick);
        else 
            triangles = createTriangleSecond(width, height, thick);
        Matrix3 transform = Matrix3.getRotate(angleX, angleY, angleZ).
                multiply( Matrix3.getWorld(localX, localY, localZ) );
        for(Triangle t : triangles){
            t.v1 = transform.transform(t.v1);
            t.v2 = transform.transform(t.v2);
            t.v3 = transform.transform(t.v3);
        }
        
        return triangles;
    }
  
    
    protected List<Triangle> createRectangleWorld(double width, double height, double thick,
        double localX, double localY, double localZ){
        List<Triangle> triangles = new ArrayList<>();
        triangles = createRectangle(width, height, thick);
        for(Triangle t : triangles){
            t.v1.x += localX;
            t.v1.y += localY;
            t.v1.z += localZ;

            t.v2.x += localX;
            t.v2.y += localY;
            t.v2.z += localZ;

            t.v3.x += localX;
            t.v3.y += localY;
            t.v3.z += localZ;
        }
        return triangles;
    }
    
    protected List<Triangle> createRectangleWorld(double width, double height, double thick,
        double localX, double localY, double localZ, double countZ){
        List<Triangle> triangles = new ArrayList<>();
        triangles = createRectangle(width, height, thick, countZ);
        for(Triangle t : triangles){
            t.v1.x += localX;
            t.v1.y += localY;
            t.v1.z += localZ;

            t.v2.x += localX;
            t.v2.y += localY;
            t.v2.z += localZ;

            t.v3.x += localX;
            t.v3.y += localY;
            t.v3.z += localZ;
        }
        return triangles;
    }
    
    protected List<Triangle> createTriangleFirst(double width, double height, double thick){
        double x = width / 2.0;
        double y = height / 2.0;
        double z = thick / 2.0; 
        double step = 1;
        
        List <Triangle> triangles = new ArrayList<Triangle>();
        for(double i = -y; i < y; i += height)
            for(double j = -x; j < x; j += width)
                for(double k = -z; k < z; k += thick){

                    triangles.add( new Triangle(
                        new Vertex(j + width, i + height, z),
                        new Vertex(j        , i + height , z),
                        new Vertex(j        , i         , z),
                        Color.white) );

                    triangles.add( new Triangle(
                        new Vertex(j + width, i + height, -z),
                        new Vertex(j , i + height , -z),
                        new Vertex(j, i         , -z),
                        Color.white) );
                    System.out.println("ffff");
                }
        return triangles;
    }
    
     protected List<Triangle> createTriangleSecond(double width, double height, double thick){
        double x = width / 2.0;
        double y = height / 2.0;
        double z = thick / 2.0; 
        double step = 1;
        
        List <Triangle> triangles = new ArrayList<Triangle>();
        for(double i = -y; i < y; i += height)
            for(double j = -x; j < x; j += width)
                for(double k = -z; k < z; k += thick){

                    triangles.add( new Triangle(
                        new Vertex(j + width, i + height, z),
                        new Vertex(j        , i + height, z),
                        new Vertex(j + width, i         , z),
                        Color.white) );

                    triangles.add( new Triangle(
                        new Vertex(j + width, i + height, -z),
                        new Vertex(j        , i + height, -z),
                        new Vertex(j + width, i         , -z),
                        Color.white) );
                    System.out.println("ffff");
                }
        return triangles;
    }
    protected List<Triangle> createRectangle(double width, double height, double thick){
      
        double x = width / 2.0;
        double y = height / 2.0;
        double z = thick / 2.0; 
        
        List <Triangle> triangles = new ArrayList<Triangle>();
        for(double i = -y; i < y; i += height)
            for(double j = -x; j < x; j += width)
                for(double k = -z; k < z; k += thick){
                    triangles.add( new Triangle(
                        new Vertex(j + width , -y, k       ),
                        new Vertex(j        , -y, k       ),
                        new Vertex(j        , -y, k + thick),
                        Color.black) );
                    triangles.add( new Triangle(
                        new Vertex(j + width, -y, k + thick),
                        new Vertex(j + width, -y, k       ),
                        new Vertex(j        , -y, k + thick),
                        Color.black) );
                    triangles.add( new Triangle(
                        new Vertex(j + width, y, k       ),
                        new Vertex(j        , y, k       ),
                        new Vertex(j        , y, k + thick),
                        Color.black) );
                    triangles.add( new Triangle(
                        new Vertex(j + width, y, k + thick),
                        new Vertex(j + width, y, k       ),
                        new Vertex(j        , y, k + thick),
                        Color.black) );

                    triangles.add( new Triangle(
                        new Vertex(x, i + height, k       ),
                        new Vertex(x, i         , k       ),
                        new Vertex(x, i         , k + thick),
                        Color.red) );
                    triangles.add( new Triangle(
                        new Vertex(x, i + height, k + thick),
                        new Vertex(x, i + height, k       ),
                        new Vertex(x, i         , k + thick),
                        Color.red) );
                    triangles.add( new Triangle(
                        new Vertex(-x, i + height, k       ),
                        new Vertex(-x, i         , k       ),
                        new Vertex(-x, i         , k + thick),
                        Color.red) );
                    triangles.add( new Triangle(
                        new Vertex(-x, i + height, k + thick),
                        new Vertex(-x, i + height, k       ),
                        new Vertex(-x, i         , k + thick),
                        Color.red) );
                    
                    triangles.add( new Triangle(
                        new Vertex(j        , i + height, z),
                        new Vertex(j        , i         , z),
                        new Vertex(j + width, i         , z),
                        Color.white) );
                    triangles.add( new Triangle(
                        new Vertex(j + width, i + height, z),
                        new Vertex(j        , i + height, z),
                        new Vertex(j + width, i         , z),
                        Color.white) );
                    triangles.add( new Triangle(
                        new Vertex(j        , i + height, -z),
                        new Vertex(j        , i         , -z),
                        new Vertex(j + width, i         , -z),
                        Color.white) );
                    triangles.add( new Triangle(
                        new Vertex(j + width, i + height, -z),
                        new Vertex(j        , i + height, -z),
                        new Vertex(j + width, i         , -z),
                        Color.white) );
                }
        return triangles; 
    }
    
    protected List<Triangle> createRectangle(double width, double height, double thick, double countZ){
      
        double x = width / 2.0;
        double y = height / 2.0;
        double z = thick / 2.0; 
        thick /= countZ;
        List <Triangle> triangles = new ArrayList<Triangle>();
        for(double i = -y; i < y; i += height)
            for(double j = -x; j < x; j += width)
                for(double k = -z; k < z; k += thick){
                    triangles.add( new Triangle(
                        new Vertex(j + width , -y, k       ),
                        new Vertex(j        , -y, k       ),
                        new Vertex(j        , -y, k + thick),
                        Color.black) );
                    triangles.add( new Triangle(
                        new Vertex(j + width, -y, k + thick),
                        new Vertex(j + width, -y, k       ),
                        new Vertex(j        , -y, k + thick),
                        Color.black) );
                    triangles.add( new Triangle(
                        new Vertex(j + width, y, k       ),
                        new Vertex(j        , y, k       ),
                        new Vertex(j        , y, k + thick),
                        Color.black) );
                    triangles.add( new Triangle(
                        new Vertex(j + width, y, k + thick),
                        new Vertex(j + width, y, k       ),
                        new Vertex(j        , y, k + thick),
                        Color.black) );

                    triangles.add( new Triangle(
                        new Vertex(x, i + height, k       ),
                        new Vertex(x, i         , k       ),
                        new Vertex(x, i         , k + thick),
                        Color.red) );
                    triangles.add( new Triangle(
                        new Vertex(x, i + height, k + thick),
                        new Vertex(x, i + height, k       ),
                        new Vertex(x, i         , k + thick),
                        Color.red) );
                    triangles.add( new Triangle(
                        new Vertex(-x, i + height, k       ),
                        new Vertex(-x, i         , k       ),
                        new Vertex(-x, i         , k + thick),
                        Color.red) );
                    triangles.add( new Triangle(
                        new Vertex(-x, i + height, k + thick),
                        new Vertex(-x, i + height, k       ),
                        new Vertex(-x, i         , k + thick),
                        Color.red) );
                    
                    triangles.add( new Triangle(
                        new Vertex(j        , i + height, z),
                        new Vertex(j        , i         , z),
                        new Vertex(j + width, i         , z),
                        Color.white) );
                    triangles.add( new Triangle(
                        new Vertex(j + width, i + height, z),
                        new Vertex(j        , i + height, z),
                        new Vertex(j + width, i         , z),
                        Color.white) );
                    triangles.add( new Triangle(
                        new Vertex(j        , i + height, -z),
                        new Vertex(j        , i         , -z),
                        new Vertex(j + width, i         , -z),
                        Color.white) );
                    triangles.add( new Triangle(
                        new Vertex(j + width, i + height, -z),
                        new Vertex(j        , i + height, -z),
                        new Vertex(j + width, i         , -z),
                        Color.white) );
                }
        return triangles; 
    }
    
     protected List<Triangle> createRectangleT(Vertex v1, Vertex v2, Vertex v3, Vertex v4,
             Vertex v5, Vertex v6, Vertex v7, Vertex v8){
      
        
        List <Triangle> triangles = new ArrayList<Triangle>();
        triangles.add( new Triangle(v1, v3, v4, Color.red) );
        triangles.add( new Triangle(v2, v1, v3, Color.red) );
        triangles.add( new Triangle(v5, v7, v8, Color.red) );
        triangles.add( new Triangle(v6, v5, v7, Color.red) );
        triangles.add( new Triangle(v1, v5, v8, Color.white) );
        triangles.add( new Triangle(v4, v1, v8, Color.white) );
        triangles.add( new Triangle(v2, v6, v7, Color.white) );
        triangles.add( new Triangle(v2, v3, v7, Color.white) );
        triangles.add( new Triangle(v1, v5, v6, Color.black) );
        triangles.add( new Triangle(v2, v1, v6, Color.black) );
        triangles.add( new Triangle(v4, v7, v3, Color.black) );
        triangles.add( new Triangle(v4, v8, v7, Color.black) );            
                    
        return triangles; 
    }
}
