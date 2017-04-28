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
        double step = 1;
        
        List <Triangle> triangles = new ArrayList<Triangle>();
        for(double i = -y; i < y; i += height)
            for(double j = -x; j < x; j += width)
                for(double k = -z; k < z; k += thick){
                    triangles.add( new Triangle(
                        new Vertex(j + width, -y, k       ),
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
}
