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
public class RenderFill implements Serializable, IRender {
    public BufferedImage getImage(List<Model> models, int height, int width, Camera camera){
        
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Matrix3 transform = camera.getCam();
     //  Matrix3 transform = Matrix3.GetPerspectiveMatrix();
       final int SIZE = width * height;
        
        double[] zBuffer = new double[SIZE];
        int[] colors = new int[SIZE];

        for (int q = 0; q < zBuffer.length; q++) {
            zBuffer[q] = Double.NEGATIVE_INFINITY;
        }
        System.out.println(width);
        
        for(Model m : models){
            for (Triangle t : m.getMatrix()) {

                camera.d = Math.sqrt(camera.x*camera.x + camera.y*camera.y + camera.z*camera.z);
                double d = 50;
                
                double a = (100 + d) / (100 - d);
                double b = -2 * 100 * d / (100 - d);
                
                d = -width / 2* Math.tan(Math.toRadians(45));
                
                Vertex v1 = transform.transform(t.v1);
                Vertex v2 = transform.transform(t.v2);
                Vertex v3 = transform.transform(t.v3);
               
            
                
                double z = v1.z == 0 ? 0.1 : v1.z;
                v1.x = d * v1.x / z;
                v1.y = d * v1.y / z;
                v1.z = (a*v1.z+b)/ z;

                z = v2.z == 0 ? 0.1 : v2.z;
                v2.x = d * v2.x / z;
                v2.y = d * v2.y / z;
                v2.z = (a*v2.z+b)/ z;

                z = v3.z == 0 ? 0.1 : v3.z;
                v3.x = d * v3.x / z;
                v3.y = d * v3.y / z;
                v3.z = (a*v3.z+b)/ z;
               
                if (v1.z <= 0){
                   
                        continue;
                }
                if (v2.z <= 0){
                    
                        continue;
                }
                if (v3.z <= 0){ 
                   
                        continue;
                }
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

    @Override
    public void setRender(Graphics2D g2) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
