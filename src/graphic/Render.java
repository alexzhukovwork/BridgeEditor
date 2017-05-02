/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphic;

import static com.sun.javafx.iio.ImageStorage.ImageType.RGB;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Алексей
 */
public class Render implements Serializable {
     public static Graphics2D g2;
     public static BufferedImage getImage(List<Model> models, int height, int width, Camera camera){
        
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Matrix3 transform = camera.getCam();
        final int SIZE = width * height;
        
        double[] zBuffer = new double[SIZE];
        int[] colors = new int[SIZE];

        for (int q = 0; q < zBuffer.length; q++) {
            zBuffer[q] = Double.NEGATIVE_INFINITY;
        }
        
        double alpha = 0.5 * 2 - 0.5;
        double beta = 0.5 * 2 - 0.5;
        
        for(Model m : models){
            for (Triangle t : m.getMatrix()) {

            /*    Vertex u = Vertex.getVector(t.v1, t.v2);
                Vertex v = Vertex.getVector(t.v1, t.v3);
                Vertex n = Vertex.multipy(u, v);
                Vertex view = Vertex.getVector(t.v1, 
                        new Vertex(camera.x, camera.y, camera.z));
                double te = 0;
                System.out.println("x = " + camera.x + "y = " + camera.y + "z = " + camera.z);
                if( (te = Vertex.multiplyScalar(n, view) ) <= 0.0 ){
                    System.out.println(te);
                    continue;
                }
                
              */
            double d = 200;
            Vertex v1 = transform.transform(t.v1);
             // Vertex v1 = t.v1;
          //  v1.x *=  d / v1.z;
              //v1.y *= d / v1.z;
             /*   double d1 = 600.0 * Math.tan(Math.toRadians(90/ 2)) / 2;
                double d2 = 300.0 * Math.tan(Math.toRadians(90 / 2))/ 2;
                
               System.out.println(camera.width);
              /*  System.out.println(d1);
                v1.x = alpha + alpha * v1.x;//(d1 * v1.x / v1.z);
                v1.y = beta - beta * v1.y;//(d2 * v1.y  * 2/ v1.z);
                */
                Vertex v2 = transform.transform(t.v2);
               // Vertex v2 = t.v2;
              // v2.x *= d / v2.z;
             // v2.y *= d / v2.z;
               /* v2.x = alpha + alpha * v2.x;//(d1 * v2.x / v2.z);
                v2.y = beta - beta * v2.y;//(d2 * v2.y  * 2/ v2.z);
*/
                Vertex v3 = transform.transform(t.v3);
               // Vertex v3 = t.v3;
              // v3.x *= d / v3.z;
             // v3.y *= d / v3.z;
  /*              v3.x = alpha + alpha * v3.x;//(d1 * v3.x / v3.z);
                v3.y = beta - beta * v3.y;//(d2 * v3.y  * 2/ v3.z);
    *//*            g2.setColor(Color.red);
                Path2D path = new Path2D.Double();
                path.moveTo(v1.x, v1.y);
                path.lineTo(v2.x, v2.y);
                path.lineTo(v3.x, v3.y);
                path.closePath();
                g2.draw(path);
*/
                int minX = (int) Math.max(0, Math.ceil(Math.min(v1.x, Math.min(v2.x, v3.x))));
                int maxX = (int) Math.min(img.getWidth() - 1, Math.floor(Math.max(v1.x, Math.max(v2.x, v3.x))));
                int minY = (int) Math.max(0, Math.ceil(Math.min(v1.y, Math.min(v2.y, v3.y))));
                int maxY = (int) Math.min(img.getHeight() - 1, Math.floor(Math.max(v1.y, Math.max(v2.y, v3.y))));

                double triangleArea = (v1.y - v3.y) * (v2.x - v3.x) + (v2.y - v3.y) * (v3.x - v1.x);
                for (int y = minY; y <= maxY; y++) {
                    for (int x = minX; x <= maxX; x++) {
                        double b1 = ((y - v3.y) * (v2.x - v3.x) + (v2.y - v3.y) * (v3.x - x)) / triangleArea;
                        double b2 = ((y - v1.y) * (v3.x - v1.x) + (v3.y - v1.y) * (v1.x - x)) / triangleArea;
                        double b3 = ((y - v2.y) * (v1.x - v2.x) + (v1.y - v2.y) * (v2.x - x)) / triangleArea;
                        if (b1 >= 0 && b1 <= 1 && b2 >= 0 && b2 <= 1 && b3 >= 0 && b3 <= 1) {
                            double depth = b1 * v1.z + b2 * v2.z + b3 * v3.z;
                            int zIndex = y * img.getWidth() + x;
                            if (zBuffer[zIndex] < depth) {
                                zBuffer[zIndex] = depth;
                                colors[zIndex] = t.color.getRGB();
                            }
                        }  
                    }
                }
            }  
        }
        img.setRGB(0, 0, width, height, colors, 0, img.getWidth()); 
        return img;
    }
}
