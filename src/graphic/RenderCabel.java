/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphic;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;
import java.awt.image.BufferedImage;
import java.util.List;

/**
 *
 * @author Алексей
 */
public class RenderCabel implements IRender {
    private Graphics2D g2;

    @Override
    public void setRender(Graphics2D g2) {
        this.g2 = g2;
    }

    @Override
    public BufferedImage getImage(List<Model> models, int height, int width, Camera camera, boolean center) {
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Matrix3 cam = camera.getCam();
        Matrix3 transform;
        for(Model m : models){
            transform = m.getMatrix().multiply(cam);
            for (Triangle t : m.triangles) {
                
                Vertex v1 = transform.transform(t.v1);
 
                Vertex v2 = transform.transform(t.v2);
    
                Vertex v3 = transform.transform(t.v3);

                if(center){   
                    if(v1.z > 0 || v2.z > 0 || v3.z > 0)
                        continue;
                    double d = 1; 
                    double a = (1000 + d) / (1000 - d);
                    double b = -2 * 1000 * d / (1000 - d);
                    d = - width / 2 * Math.tan(Math.toRadians(45));
                    double z = v1.z == 0 ? -0.1 : v1.z;
                    v1.x = d * v1.x / z;
                    v1.y = d * v1.y / z;
                    v1.z = (a*(v1.z)+b)/ z;

                    z = v2.z == 0 ? -0.1 : v2.z;
                    v2.x = d * v2.x / z;
                    v2.y = d * v2.y / z;
                    v2.z = (a*(v2.z)+b)/ z;

                    z = v3.z == 0 ? -0.1 : v3.z;
                    v3.x = d * v3.x / z;
                    v3.y = d * v3.y / z;
                    v3.z = (a*(v3.z)+b)/ z;  

                    d = 1;
                    v1.x += width / 2 + v1.x*d/(v1.z + d);
                    v1.y += height / 2 + v1.y*d/(v1.z + d);
                    v2.x += width / 2 + v2.x*d/(v2.z + d);
                    v2.y += height / 2 + v2.y*d/(v2.z + d);
                    v3.x += width / 2 + v3.x*d/(v3.z + d);
                    v3.y += height / 2 + v3.y*d/(v3.z + d);
                }else{
                    v1.x += width / 2;
                    v1.y += height / 2;
                    v2.x += width / 2;
                    v2.y += height / 2;
                    v3.x += width / 2;
                    v3.y += height /2;
                }
                
                g2.setColor(Color.red);
                Path2D path = new Path2D.Double();
                path.moveTo(v1.x, v1.y);
                path.lineTo(v2.x, v2.y);
                path.lineTo(v3.x, v3.y);
                path.closePath();
                g2.draw(path);
            }  
        }
        return img;
    }
}

