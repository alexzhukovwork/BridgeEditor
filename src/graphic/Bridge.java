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
public class Bridge extends Model {
    double lowerSupportWidth = 30;
    double lowerSupportHeight = 10;
    double lowerSupportThick = 10;
    double supportThick = 10;
    double supportWidth = 4;
    double supportHeight = 120;
    double bridgeLength = 500;
    double bridgeWidth = 14;
    double bridgeHeight = 2;
    double bridgeLevel = 40;
    double metalHeight = 5;
    double metalWidth = 5;
    double metalThick = 5;
    double firstHoleHeight = 20;
    double secondHoleHeight = 15;
    double thirdHoleHeight = 10;
    double holeBalkHeight = 5;
    double bindingHeight = 5;
    double bindingWidth = 5;
    double fanceDistance = 1;
    double fanceParam = 0.2;
    int countThirdHole = 2;
    int countRope = 0;
    
    
    public Bridge(double worldX, double worldY, double worldZ, String name) {
        super(worldX, worldY, worldZ, name);
    }
    
    public void createModel(){
        triangles.clear();
        triangles.addAll( createRectangleWorld(bridgeWidth, bridgeHeight, bridgeLength + 50 , 0, -(lowerSupportHeight/2 + bridgeLevel), 0, 100) );
        triangles.addAll( createRectangleWorld(lowerSupportWidth, lowerSupportHeight, lowerSupportThick, 0, 0, -bridgeLength / 2) );
        triangles.addAll( createRectangleWorld(lowerSupportWidth, lowerSupportHeight, lowerSupportThick, 0, 0, bridgeLength / 2) );
        triangles.addAll( createRectangleWorld(supportWidth, supportHeight, supportThick, 
                lowerSupportWidth / 5, -(lowerSupportHeight/2 + supportHeight / 2), bridgeLength / 2) );
        triangles.addAll( createRectangleWorld(supportWidth, supportHeight, supportThick, -lowerSupportWidth / 5, -(lowerSupportHeight/2 + supportHeight / 2), bridgeLength / 2) );
        triangles.addAll( createRectangleWorld(supportWidth, supportHeight, supportThick, lowerSupportWidth / 5, -(lowerSupportHeight/2 + supportHeight / 2), -bridgeLength / 2) );
        triangles.addAll( createRectangleWorld(supportWidth, supportHeight, supportThick, -lowerSupportWidth / 5, -(lowerSupportHeight/2 + supportHeight / 2), -bridgeLength / 2) );
        createMetal();
        createHole();
        createInclineSupport();
    
        createBinding();
        createRope();
        createFance();
    }
    
    private void createRope(){
        double y = (supportHeight + bindingHeight + lowerSupportHeight / 2);
        double z = 0;
        
        double p3X = bridgeLength / 2 - supportThick / 2;
        double p3Y = -y;
        
        double p1X = -bridgeLength + supportThick / 2;
        double p1Y = 0;
        
        double p2X =  -bridgeLength / 2 + supportThick / 2; 
        double p2Y = (supportHeight - bridgeLevel + bindingHeight) * 2;
       
        int currentCount = 0;
        double distance = 0;
        if(countRope == 1)
            distance = bridgeLength / 6; 
        else if(countRope > 0)
            distance = (bridgeLength / 2 - bridgeLength / 5) / countRope;   

        double allDistance = -bridgeLength / 2 + distance;
        double t = 0;
        double heightBalk = 0;
        while(t <= 1) {
            z = (1-t) * (1-t) * p1X + 2 * t * (1-t) * p2X + t * t + p3X; 
            y = (1-t) * (1-t) * p1Y + 2 * t * (1-t) * p2Y + t * t + p3Y; 

            
            if (currentCount < countRope && z > allDistance) {
                heightBalk = -(y + bridgeLevel + bridgeHeight + lowerSupportHeight / 2 - 5);

                triangles.addAll( createRectangleWorld(1, heightBalk, 1, -(lowerSupportWidth / 5 + supportWidth / 2) + 1, y + heightBalk / 2 - bridgeHeight / 2, z) );
                triangles.addAll( createRectangleWorld(1, heightBalk, 1, -(lowerSupportWidth / 5 + supportWidth / 2) + 1, y + heightBalk / 2 - bridgeHeight / 2, -z) );
                triangles.addAll( createRectangleWorld(1, heightBalk, 1, (lowerSupportWidth / 5 + supportWidth / 2) - 1, y + heightBalk / 2 - bridgeHeight / 2, z) );
                triangles.addAll( createRectangleWorld(1, heightBalk, 1, (lowerSupportWidth / 5 + supportWidth / 2) - 1, y + heightBalk / 2 - bridgeHeight / 2, -z) );
                allDistance += distance;
                currentCount++;
            }
            triangles.addAll( createRectangleWorld(1, 1, 1, lowerSupportWidth / 5 + supportWidth / 2 - 1, y, z) );
            triangles.addAll( createRectangleWorld(1, 1, 1, -(lowerSupportWidth / 5 +supportWidth / 2) + 1, y, z) );

            t += 0.001;
            
        }
    }
    
    private void createBinding(){
        double cat1 = supportWidth;
        double cat2 = bindingHeight;
        double gep = Math.sqrt(cat1 * cat1 + cat2 * cat2);
        
        triangles.addAll( getRotate( (lowerSupportWidth / 2.5 - supportWidth) / 2 + cat1 / 2,
                -(lowerSupportHeight / 2 + supportHeight + bindingHeight / 2) , 
                bridgeLength / 2,
                0, 0, Math.atan(cat1 / cat2), 0.1, gep, supportThick) );
        triangles.addAll( createRectangleWorld(0.1, bindingHeight, supportThick, 
                (lowerSupportWidth / 2.5 - supportWidth) / 2 + cat1 - 0.05
                , -(lowerSupportHeight / 2 + supportHeight + bindingHeight / 2) 
                , bridgeLength / 2) );
        triangles.addAll( getRotateTriangle(supportWidth, bindingHeight, supportThick,
                (lowerSupportWidth / 2.5 - supportWidth) / 2 + cat1 / 2, 
                -(lowerSupportHeight / 2 + supportHeight + bindingHeight / 2), 
                bridgeLength / 2, 0, 0, 0, 1) );
        
        triangles.addAll( getRotateTriangle(supportWidth, bindingHeight, supportThick,
                - (lowerSupportWidth / 2.5 - supportWidth) / 2  - cat1 / 2, 
                -(lowerSupportHeight / 2 + supportHeight + bindingHeight / 2), 
                bridgeLength / 2, 0, 0, 0, 0) );
        triangles.addAll( createRectangleWorld(0.1, bindingHeight, supportThick, 
                - (lowerSupportWidth / 2.5 - supportWidth) / 2 + 0.05 - cat1
                , -(lowerSupportHeight / 2 + supportHeight + bindingHeight / 2) 
                , bridgeLength / 2) );
        triangles.addAll( getRotate( -(lowerSupportWidth / 2.5 - supportWidth) / 2 - cat1 / 2,
                -(lowerSupportHeight / 2 + supportHeight + bindingHeight / 2) , 
                bridgeLength / 2,
                0, 0, -Math.atan(cat1 / cat2), 0.1, gep, supportThick) );
        
        triangles.addAll( getRotate( (lowerSupportWidth / 2.5 - supportWidth) / 2 + cat1 / 2,
                -(lowerSupportHeight / 2 + supportHeight + bindingHeight / 2) , 
                -bridgeLength / 2,
                0, 0, Math.atan(cat1 / cat2), 0.1, gep, supportThick) );
        triangles.addAll( createRectangleWorld(0.1, bindingHeight, supportThick, 
                (lowerSupportWidth / 2.5 - supportWidth) / 2 + cat1 - 0.05
                , -(lowerSupportHeight / 2 + supportHeight + bindingHeight / 2) 
                , -bridgeLength / 2) );
        triangles.addAll( getRotateTriangle(supportWidth, bindingHeight, supportThick,
                (lowerSupportWidth / 2.5 - supportWidth) / 2 + cat1 / 2, 
                -(lowerSupportHeight / 2 + supportHeight + bindingHeight / 2), 
                -bridgeLength / 2, 0, 0, 0, 1) );
        
        triangles.addAll( getRotateTriangle(supportWidth, bindingHeight, supportThick,
                - (lowerSupportWidth / 2.5 - supportWidth) / 2  - cat1 / 2, 
                -(lowerSupportHeight / 2 + supportHeight + bindingHeight / 2), 
                -bridgeLength / 2, 0, 0, 0, 0) );
        triangles.addAll( createRectangleWorld(0.1, bindingHeight, supportThick, 
                - (lowerSupportWidth / 2.5 - supportWidth) / 2 + 0.05 - cat1
                , -(lowerSupportHeight / 2 + supportHeight + bindingHeight / 2) 
                , -bridgeLength / 2) );
        triangles.addAll( getRotate( -(lowerSupportWidth / 2.5 - supportWidth) / 2 - cat1 / 2,
                -(lowerSupportHeight / 2 + supportHeight + bindingHeight / 2) , 
                -bridgeLength / 2,
                0, 0, -Math.atan(cat1 / cat2), 0.1, gep, supportThick) );
        
     
        
    }
    
    private void createInclineSupport(){
        double cat1 = ( (bridgeLevel - metalHeight/2 - bridgeHeight/2) / 2) - holeBalkHeight - metalHeight/2;
        double cat2 = (lowerSupportWidth / 2.5) - supportWidth;
      
        double rot = Math.sqrt(cat1 * cat1 + cat2 * cat2);
        
        Vertex v1 = new Vertex(cat2 / 2 + 1, -lowerSupportHeight/2, bridgeLength / 2 - supportThick / 2);
        Vertex v2 = new Vertex(cat2 / 2 + 1, -lowerSupportHeight/2, bridgeLength / 2 + supportThick / 2);
        Vertex v3 = new Vertex(cat2 / 2 + 1, -lowerSupportHeight/2 + 2, bridgeLength / 2 + supportThick / 2 );
        Vertex v4 = new Vertex(cat2 / 2 + 1, -lowerSupportHeight/2 + 2, bridgeLength / 2 - supportThick / 2);
        Vertex v5 = new Vertex(-cat2 / 2 - 1, -lowerSupportHeight/2 - cat1 - 4, bridgeLength / 2 - supportThick / 2);
        Vertex v6 = new Vertex(-cat2 / 2 - 1, -lowerSupportHeight/2 - cat1 - 4, bridgeLength / 2 + supportThick / 2);
        Vertex v7 = new Vertex(-cat2 / 2 - 1, -lowerSupportHeight/2 - cat1 - 6, bridgeLength / 2 + supportThick / 2 );
        Vertex v8 = new Vertex(-cat2 / 2 - 1, -lowerSupportHeight/2 - cat1 - 6, bridgeLength / 2 - supportThick / 2);
        triangles.addAll(createRectangleT(v1, v2, v3, v4, v5, v6, v7, v8));
        
        v1 = new Vertex(-cat2 / 2 - 1, -lowerSupportHeight/2, bridgeLength / 2 - supportThick / 2);
        v2 = new Vertex(-cat2 / 2 - 1, -lowerSupportHeight/2, bridgeLength / 2 + supportThick / 2);
        v3 = new Vertex(-cat2 / 2 - 1, -lowerSupportHeight/2 + 2, bridgeLength / 2 + supportThick / 2 );
        v4 = new Vertex(-cat2 / 2 - 1, -lowerSupportHeight/2 + 2, bridgeLength / 2 - supportThick / 2);
        v5 = new Vertex(cat2 / 2 + 1, -lowerSupportHeight/2 - cat1 - 4, bridgeLength / 2 - supportThick / 2);
        v6 = new Vertex(cat2 / 2 + 1, -lowerSupportHeight/2 - cat1 - 4, bridgeLength / 2 + supportThick / 2);
        v7 = new Vertex(cat2 / 2 + 1, -lowerSupportHeight/2 - cat1 - 6, bridgeLength / 2 + supportThick / 2 );
        v8 = new Vertex(cat2 / 2 + 1, -lowerSupportHeight/2 - cat1 - 6, bridgeLength / 2 - supportThick / 2);
        triangles.addAll(createRectangleT(v1, v2, v3, v4, v5, v6, v7, v8));
        
        v1 = new Vertex(-cat2 / 2 - 1, -lowerSupportHeight/2, -bridgeLength / 2 + supportThick / 2);
        v2 = new Vertex(-cat2 / 2 - 1, -lowerSupportHeight/2, -bridgeLength / 2 - supportThick / 2);
        v3 = new Vertex(-cat2 / 2 - 1, -lowerSupportHeight/2 + 2, -bridgeLength / 2 - supportThick / 2 );
        v4 = new Vertex(-cat2 / 2 - 1, -lowerSupportHeight/2 + 2, -bridgeLength / 2 + supportThick / 2);
        v5 = new Vertex(cat2 / 2 + 1, -lowerSupportHeight/2 - cat1 - 4, -bridgeLength / 2 + supportThick / 2);
        v6 = new Vertex(cat2 / 2 + 1, -lowerSupportHeight/2 - cat1 - 4, -bridgeLength / 2 - supportThick / 2);
        v7 = new Vertex(cat2 / 2 + 1, -lowerSupportHeight/2 - cat1 - 6, -bridgeLength / 2 - supportThick / 2 );
        v8 = new Vertex(cat2 / 2 + 1, -lowerSupportHeight/2 - cat1 - 6, -bridgeLength / 2 + supportThick / 2);
        triangles.addAll(createRectangleT(v1, v2, v3, v4, v5, v6, v7, v8));
        
        v1 = new Vertex(cat2 / 2 + 1, -lowerSupportHeight/2, -bridgeLength / 2 + supportThick / 2);
        v2 = new Vertex(cat2 / 2 + 1, -lowerSupportHeight/2, -bridgeLength / 2 - supportThick / 2);
        v3 = new Vertex(cat2 / 2 + 1, -lowerSupportHeight/2 + 2, -bridgeLength / 2 - supportThick / 2 );
        v4 = new Vertex(cat2 / 2 + 1, -lowerSupportHeight/2 + 2, -bridgeLength / 2 + supportThick / 2);
        v5 = new Vertex(-cat2 / 2 - 1, -lowerSupportHeight/2 - cat1 - 4, -bridgeLength / 2 + supportThick / 2);
        v6 = new Vertex(-cat2 / 2 - 1, -lowerSupportHeight/2 - cat1 - 4, -bridgeLength / 2 - supportThick / 2);
        v7 = new Vertex(-cat2 / 2 - 1, -lowerSupportHeight/2 - cat1 - 6, -bridgeLength / 2 - supportThick / 2 );
        v8 = new Vertex(-cat2 / 2 - 1, -lowerSupportHeight/2 - cat1 - 6, -bridgeLength / 2 + supportThick / 2);
        triangles.addAll(createRectangleT(v1, v2, v3, v4, v5, v6, v7, v8));
        //
        v1 = new Vertex(cat2 / 2 + 1, -lowerSupportHeight/2 - cat1 - holeBalkHeight, bridgeLength / 2 - supportThick / 2);
        v2 = new Vertex(cat2 / 2 + 1, -lowerSupportHeight/2 - cat1 - holeBalkHeight, bridgeLength / 2 + supportThick / 2);
        v3 = new Vertex(cat2 / 2 + 1, -lowerSupportHeight/2 + 2 - cat1 - holeBalkHeight, bridgeLength / 2 + supportThick / 2 );
        v4 = new Vertex(cat2 / 2 + 1, -lowerSupportHeight/2 + 2 - cat1 - holeBalkHeight, bridgeLength / 2 - supportThick / 2);
        v5 = new Vertex(-cat2 / 2 - 1, -lowerSupportHeight/2 - cat1 - 4 - cat1 - holeBalkHeight, bridgeLength / 2 - supportThick / 2);
        v6 = new Vertex(-cat2 / 2 - 1, -lowerSupportHeight/2 - cat1 - 4 - cat1 - holeBalkHeight, bridgeLength / 2 + supportThick / 2);
        v7 = new Vertex(-cat2 / 2 - 1, -lowerSupportHeight/2 - cat1 - 6 - cat1 - holeBalkHeight, bridgeLength / 2 + supportThick / 2 );
        v8 = new Vertex(-cat2 / 2 - 1, -lowerSupportHeight/2 - cat1 - 6 - cat1 - holeBalkHeight, bridgeLength / 2 - supportThick / 2);
        triangles.addAll(createRectangleT(v1, v2, v3, v4, v5, v6, v7, v8));
        
        v1 = new Vertex(-cat2 / 2 - 1, -lowerSupportHeight/2 - cat1 - holeBalkHeight, bridgeLength / 2 - supportThick / 2);
        v2 = new Vertex(-cat2 / 2 - 1, -lowerSupportHeight/2 - cat1 - holeBalkHeight, bridgeLength / 2 + supportThick / 2);
        v3 = new Vertex(-cat2 / 2 - 1, -lowerSupportHeight/2 + 2 - cat1 - holeBalkHeight, bridgeLength / 2 + supportThick / 2 );
        v4 = new Vertex(-cat2 / 2 - 1, -lowerSupportHeight/2 + 2 - cat1 - holeBalkHeight, bridgeLength / 2 - supportThick / 2);
        v5 = new Vertex(cat2 / 2 + 1, -lowerSupportHeight/2 - cat1 - 4 - cat1 - holeBalkHeight, bridgeLength / 2 - supportThick / 2);
        v6 = new Vertex(cat2 / 2 + 1, -lowerSupportHeight/2 - cat1 - 4 - cat1 - holeBalkHeight, bridgeLength / 2 + supportThick / 2);
        v7 = new Vertex(cat2 / 2 + 1, -lowerSupportHeight/2 - cat1 - 6 - cat1 - holeBalkHeight, bridgeLength / 2 + supportThick / 2 );
        v8 = new Vertex(cat2 / 2 + 1, -lowerSupportHeight/2 - cat1 - 6 - cat1 - holeBalkHeight, bridgeLength / 2 - supportThick / 2);
        triangles.addAll(createRectangleT(v1, v2, v3, v4, v5, v6, v7, v8));
        
        v1 = new Vertex(-cat2 / 2 - 1, -lowerSupportHeight/2 - cat1 - holeBalkHeight, -bridgeLength / 2 + supportThick / 2);
        v2 = new Vertex(-cat2 / 2 - 1, -lowerSupportHeight/2 - cat1 - holeBalkHeight, -bridgeLength / 2 - supportThick / 2);
        v3 = new Vertex(-cat2 / 2 - 1, -lowerSupportHeight/2 - cat1 - holeBalkHeight + 2, -bridgeLength / 2 - supportThick / 2 );
        v4 = new Vertex(-cat2 / 2 - 1, -lowerSupportHeight/2 - cat1 - holeBalkHeight + 2, -bridgeLength / 2 + supportThick / 2);
        v5 = new Vertex(cat2 / 2 + 1, -lowerSupportHeight/2  - cat1 - holeBalkHeight- cat1 - 4, -bridgeLength / 2 + supportThick / 2);
        v6 = new Vertex(cat2 / 2 + 1, -lowerSupportHeight/2  - cat1 - holeBalkHeight- cat1 - 4, -bridgeLength / 2 - supportThick / 2);
        v7 = new Vertex(cat2 / 2 + 1, -lowerSupportHeight/2 - cat1 - 6 - cat1 - holeBalkHeight, -bridgeLength / 2 - supportThick / 2 );
        v8 = new Vertex(cat2 / 2 + 1, -lowerSupportHeight/2 - cat1 - 6 - cat1 - holeBalkHeight, -bridgeLength / 2 + supportThick / 2);
        triangles.addAll(createRectangleT(v1, v2, v3, v4, v5, v6, v7, v8));
        
        v1 = new Vertex(cat2 / 2 + 1, -lowerSupportHeight/2 - cat1 - holeBalkHeight, -bridgeLength / 2 + supportThick / 2);
        v2 = new Vertex(cat2 / 2 + 1, -lowerSupportHeight/2 - cat1 - holeBalkHeight, -bridgeLength / 2 - supportThick / 2);
        v3 = new Vertex(cat2 / 2 + 1, -lowerSupportHeight/2 + 2 - cat1 - holeBalkHeight, -bridgeLength / 2 - supportThick / 2 );
        v4 = new Vertex(cat2 / 2 + 1, -lowerSupportHeight/2 + 2 - cat1 - holeBalkHeight, -bridgeLength / 2 + supportThick / 2);
        v5 = new Vertex(-cat2 / 2 - 1, -lowerSupportHeight/2 - cat1 - 4 - cat1 - holeBalkHeight, -bridgeLength / 2 + supportThick / 2);
        v6 = new Vertex(-cat2 / 2 - 1, -lowerSupportHeight/2 - cat1 - 4 - cat1 - holeBalkHeight, -bridgeLength / 2 - supportThick / 2);
        v7 = new Vertex(-cat2 / 2 - 1, -lowerSupportHeight/2 - cat1 - 6 - cat1 - holeBalkHeight, -bridgeLength / 2 - supportThick / 2 );
        v8 = new Vertex(-cat2 / 2 - 1, -lowerSupportHeight/2 - cat1 - 6 - cat1 - holeBalkHeight, -bridgeLength / 2 + supportThick / 2);
        triangles.addAll(createRectangleT(v1, v2, v3, v4, v5, v6, v7, v8));
        
        triangles.addAll( getRotate(0, -lowerSupportHeight / 2 - cat1 - metalHeight, bridgeLength/2,
               0, 0, 0, 
                cat2, holeBalkHeight / 2, supportThick) );
        triangles.addAll( getRotate(0, -lowerSupportHeight / 2 - cat1 - metalHeight, -bridgeLength/2,
               0, 0, 0, 
                cat2, holeBalkHeight / 2, supportThick) );
        triangles.addAll( getRotate(0, -lowerSupportHeight / 2 - cat1 - 2 * metalHeight  - cat1, bridgeLength/2,
               0, 0, 0, 
                cat2, holeBalkHeight / 2, supportThick) );
        triangles.addAll( getRotate(0, -lowerSupportHeight / 2 - cat1 -  2 * metalHeight - cat1, -bridgeLength/2,
               0, 0, 0, 
                cat2, holeBalkHeight / 2, supportThick) );
        
//        
//        triangles.addAll( getRotate(-cat2/2, -rot / 2 , -bridgeLength/2,
//                0, 0, angle, 
//                1, rot, supportThick,
//                -cat2/2, -lowerSupportHeight/2, -bridgeLength/2) );
//        triangles.addAll( getRotate(0, -rot / 2 - lowerSupportHeight / 2, - bridgeLength/2,
//                0, 0, -angle, 
//                1, rot, supportThick) );
//        triangles.addAll( getRotate(0, -lowerSupportHeight / 2 - rot - metalHeight / 2, bridgeLength/2,
//               0, 0, 0, 
//                cat2, holeBalkHeight / 2, supportThick) );
//        
//        triangles.addAll( getRotate(0, -(lowerSupportHeight / 2 + holeBalkHeight / 4 +  bridgeLevel / 2 / 1.3 + rot/2), -bridgeLength/2,
//                0, 0, -Math.tan(cat2 / cat1), 
//                1, rot, supportThick) );
//        triangles.addAll( getRotate(0, -(lowerSupportHeight / 2 + holeBalkHeight / 4 +  bridgeLevel / 2 / 1.3 + rot/2), -bridgeLength/2,
//                0, 0, Math.tan(cat2 / cat1), 
//                1, rot, supportThick) );
//        triangles.addAll( getRotate(0, -(lowerSupportHeight / 2 + holeBalkHeight / 4 +  bridgeLevel / 2 / 1.3 + cat1), -bridgeLength/2,
//                0, 0, 0, 
//                cat2, holeBalkHeight / 2, supportThick) );
//        
//        triangles.addAll( getRotate(0, -rot / 2 - lowerSupportHeight / 2, bridgeLength/2,
//                0, 0, -Math.tan(cat2 / cat1), 
//                1, rot, supportThick) );
//        triangles.addAll( getRotate(0, -rot / 2 - lowerSupportHeight / 2, bridgeLength/2,
//                0, 0, Math.tan(cat2 / cat1), 
//                1, rot, supportThick) );
//        triangles.addAll( getRotate(0, -(lowerSupportHeight / 2 + holeBalkHeight / 4 +  bridgeLevel / 2 / 1.3), bridgeLength/2,
//                0, 0, 0, 
//                cat2, holeBalkHeight / 2, supportThick) );
//        
//        triangles.addAll( getRotate(0, -(lowerSupportHeight / 2 + holeBalkHeight / 4 +  bridgeLevel / 2 / 1.3 + rot/2), bridgeLength/2,
//                0, 0, -Math.tan(cat2 / cat1), 
//                1, rot, supportThick) );
//        triangles.addAll( getRotate(0, -(lowerSupportHeight / 2 + holeBalkHeight / 4 +  bridgeLevel / 2 / 1.3 + rot/2), bridgeLength/2,
//                0, 0, Math.tan(cat2 / cat1), 
//                1, rot, supportThick) );
//        triangles.addAll( getRotate(0, -(lowerSupportHeight / 2 + holeBalkHeight / 4 +  bridgeLevel / 2 / 1.3 + cat1), bridgeLength/2,
//                0, 0, 0, 
//                cat2, holeBalkHeight / 2, supportThick) );
    }
    
    private List<Triangle> getRotate(double x, double y, double z, double angleX, double angleY, double angleZ,
            double width, double height, double thick){
        List<Triangle> triangles = new ArrayList<>();
        triangles = createRectangle(width, height, thick);
        Matrix3 transform = Matrix3.getRotate(angleX, angleY, angleZ).multiply( Matrix3.getWorld(x, y, z) );
        for(Triangle t : triangles){
            t.v1 = transform.transform(t.v1);
            t.v2 = transform.transform(t.v2);
            t.v3 = transform.transform(t.v3);
        }
        
        return triangles;
    }
    
    private List<Triangle> getRotate(double x, double y, double z, double angleX, double angleY, double angleZ,
            double width, double height, double thick, double dx, double dy, double dz){
        List<Triangle> triangles = new ArrayList<>();
        triangles = createRectangle(width, height, thick);
        Matrix3 transform = Matrix3.getWorld(dx, dy, dz).multiply( Matrix3.getRotate(angleX, angleY, angleZ) )
                .multiply(Matrix3.getWorld(-dx, -dy, -dz)).multiply( Matrix3.getWorld(x, y, z) );
        for(Triangle t : triangles){
            t.v1 = transform.transform(t.v1);
            t.v2 = transform.transform(t.v2);
            t.v3 = transform.transform(t.v3);
        }
        
        return triangles;
    }
    
    private void createHole(){
        double height = -(lowerSupportHeight / 2 + bridgeLevel + bridgeHeight + firstHoleHeight);
        triangles.addAll( createRectangleWorld(lowerSupportWidth / 2.5, holeBalkHeight, lowerSupportThick,
                0, height, -bridgeLength / 2) );
        triangles.addAll( createRectangleWorld(lowerSupportWidth / 2.5, holeBalkHeight, lowerSupportThick,
                0, height, bridgeLength / 2) );
        
        height -= (holeBalkHeight  + secondHoleHeight);        
        triangles.addAll( createRectangleWorld(lowerSupportWidth / 2.5, holeBalkHeight, lowerSupportThick,
                0, height, -bridgeLength / 2) );
        triangles.addAll( createRectangleWorld(lowerSupportWidth / 2.5, holeBalkHeight, lowerSupportThick,
                0, height, bridgeLength / 2) );
        
        double needHeight = height - holeBalkHeight * (countThirdHole - 1) - countThirdHole * thirdHoleHeight;
        int i = 0;
        
        while(i < countThirdHole &&  needHeight > -supportHeight){
            height -= (holeBalkHeight + thirdHoleHeight);
                    System.out.println(height + " :h");
            triangles.addAll( createRectangleWorld(lowerSupportWidth / 2.5, holeBalkHeight, lowerSupportThick,
                0, height, -bridgeLength / 2) );
            triangles.addAll( createRectangleWorld(lowerSupportWidth / 2.5, holeBalkHeight, lowerSupportThick,
                0, height, bridgeLength / 2) );
            i++;
        }
    }
    
    private void createFance(){
        double x = bridgeWidth / 2;
        double y = -(lowerSupportHeight/2 + bridgeLevel + bridgeHeight / 2) + fanceParam / 2 - 1;
        double step = fanceDistance;
        double heightFance = 1; 
        System.out.println("height: " + heightFance);
        triangles.addAll( createRectangleWorld(fanceParam, fanceParam, bridgeLength, x , y, 0, 100) );
        triangles.addAll( createRectangleWorld(fanceParam, fanceParam, bridgeLength, -x , y, 0, 100) );
        for(double i = -bridgeLength / 2; i < bridgeLength / 2; i += step){
            triangles.addAll( createRectangleWorld(fanceParam, heightFance, fanceParam, x , y + heightFance / 2, i) );
            triangles.addAll( createRectangleWorld(fanceParam, heightFance, fanceParam, -x , y + heightFance / 2, i) );
        }
    }
    
    private void createMetal(){
        double x = 0, y = 0, z = 0;
        int count = (int)(bridgeLength / metalThick) - 1;
        double gep = Math.sqrt( (metalThick - metalThick / 5) * (metalThick - metalThick / 5)
                + (metalHeight - metalHeight / 5) * (metalHeight - metalHeight / 5) );
        for(int i = 0; i < 2; i++){
            for(int j = 0; j < count; j++){
                x = bridgeWidth / 2 - i * (bridgeWidth - metalThick);
                y = -(lowerSupportHeight/2 + bridgeLevel - bridgeHeight / 2);
                z =  -bridgeLength / 2 + supportThick / 2 + metalThick / 2 + j * metalThick;
                triangles.addAll( createRectangleWorld(metalWidth / 5, metalHeight / 5, metalThick, x, y, z) );
                triangles.addAll( createRectangleWorld(metalWidth / 5, metalHeight / 5, metalThick, x - metalThick, y, z) );
                triangles.addAll( createRectangleWorld(metalWidth / 5, metalHeight / 5, metalThick, x, y + metalHeight, z) );
                triangles.addAll( createRectangleWorld(metalWidth / 5, metalHeight / 5, metalThick, x - metalThick, y + metalHeight, z) );

                y = -(lowerSupportHeight/2 + bridgeLevel - bridgeHeight / 2 - metalHeight / 2.5);
                z = -bridgeLength / 2 + supportThick / 2 + j * metalThick;
                triangles.addAll( getRotate(x, y, z + metalThick / 2 + metalThick / 10, 
                        Math.atan( (metalHeight - metalHeight / 5) / (metalThick - metalThick / 5) ), 0, 0, 
                        metalWidth / 5, gep, metalThick / 5) );
                triangles.addAll( getRotate(x, y, z + metalThick / 2 + metalThick / 10, 
                        -Math.atan( (metalHeight - metalHeight / 5) / (metalThick - metalThick / 5) ), 0, 0, 
                        metalWidth / 5, gep, metalThick / 5) );
                triangles.addAll( getRotate(x - metalThick, y, z + metalThick / 2 + metalThick / 10, 
                        Math.atan( (metalHeight - metalHeight / 5) / (metalThick - metalThick / 5) ), 0, 0, 
                        metalWidth / 5, gep, metalThick / 5) );
                triangles.addAll( getRotate(x - metalThick, y, z + metalThick / 2 + metalThick / 10, 
                        -Math.atan( (metalHeight - metalHeight / 5) / (metalThick - metalThick / 5) ), 0, 0, 
                        metalWidth / 5, gep, metalThick / 5) );
              
                
                triangles.addAll( createRectangleWorld(metalWidth / 5, metalHeight, metalThick / 5, 
                        x, y, z) );
                triangles.addAll( createRectangleWorld(metalWidth / 5, metalHeight, metalThick / 5, 
                        x, y, z + metalThick) );
                triangles.addAll( createRectangleWorld(metalWidth / 5, metalHeight, metalThick / 5, 
                        x - metalThick, y, z) );
                triangles.addAll( createRectangleWorld(metalWidth / 5, metalHeight, metalThick / 5, 
                        x - metalThick, y, z + metalThick) );

                x = bridgeWidth / 2 - metalWidth / 2 - i * (bridgeWidth - metalWidth);
                y = -(lowerSupportHeight/2 + bridgeLevel - bridgeHeight / 2);
                z = -bridgeLength / 2 + supportThick / 2 + metalThick / 10 + j * metalThick;
                triangles.addAll( createRectangleWorld(metalWidth, metalHeight / 5, metalThick / 5, 
                        x, y, z) );
                triangles.addAll( createRectangleWorld(metalWidth, metalHeight / 5, metalThick / 5, 
                        x, y, z + metalThick - metalThick / 5) );
                triangles.addAll( createRectangleWorld(metalWidth, metalHeight / 5, metalThick / 5, 
                        x, y + metalHeight, z) );
                triangles.addAll( createRectangleWorld(metalWidth, metalHeight / 5, metalThick / 5, 
                        x, y + metalHeight, z + metalThick - metalThick / 5) );
            } 
        }
    }
}
