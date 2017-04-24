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
    int countThirdHole = 2;
    
    
    public Bridge(double worldX, double worldY, double worldZ) {
        super(worldX, worldY, worldZ);
        createModel();
    }
    
    public void createModel(){
        triangles.addAll( createRectangleWorld(lowerSupportWidth, lowerSupportHeight, lowerSupportThick, 0, 0, -bridgeLength / 2) );
        triangles.addAll( createRectangleWorld(lowerSupportWidth, lowerSupportHeight, lowerSupportThick, 0, 0, bridgeLength / 2) );
        triangles.addAll( createRectangleWorld(supportWidth, supportHeight, supportThick, 
                lowerSupportWidth / 5, -(lowerSupportHeight/2 + supportHeight / 2), bridgeLength / 2) );
        triangles.addAll( createRectangleWorld(supportWidth, supportHeight, supportThick, -lowerSupportWidth / 5, -(lowerSupportHeight/2 + supportHeight / 2), bridgeLength / 2) );
        triangles.addAll( createRectangleWorld(supportWidth, supportHeight, supportThick, lowerSupportWidth / 5, -(lowerSupportHeight/2 + supportHeight / 2), -bridgeLength / 2) );
        triangles.addAll( createRectangleWorld(supportWidth, supportHeight, supportThick, -lowerSupportWidth / 5, -(lowerSupportHeight/2 + supportHeight / 2), -bridgeLength / 2) );
        triangles.addAll( createRectangleWorld(bridgeWidth, bridgeHeight, bridgeLength + 50 , 0, -(lowerSupportHeight/2 + bridgeLevel), 0) );
        createMetal();
        createHole();
        createInclineSupport();
    }
    
    private void createInclineSupport(){
        double cat1 = (bridgeLevel / 2) / 1.3;
        double cat2 = (lowerSupportWidth / 2.5 - supportWidth);// + supportWidth);
        double rot = Math.sqrt(cat1 * cat1 + cat2 * cat2);
        
        triangles.addAll( getRotate(0, -rot / 2 - lowerSupportHeight / 2, -bridgeLength/2,
                angleX, angleY, -Math.tan(cat2 / cat1), 
                1, rot, supportThick) );
        triangles.addAll( getRotate(0, -rot / 2 - lowerSupportHeight / 2, -bridgeLength/2,
                angleX, angleY, Math.tan(cat2 / cat1), 
                1, rot, supportThick) );
        triangles.addAll( getRotate(0, -(lowerSupportHeight / 2 + holeBalkHeight / 4 +  bridgeLevel / 2 / 1.3), -bridgeLength/2,
                0, 0, 0, 
                cat2, holeBalkHeight / 2, supportThick) );
        
        triangles.addAll( getRotate(0, -(lowerSupportHeight / 2 + holeBalkHeight / 4 +  bridgeLevel / 2 / 1.3 + rot/2), -bridgeLength/2,
                angleX, angleY, -Math.tan(cat2 / cat1), 
                1, rot, supportThick) );
        triangles.addAll( getRotate(0, -(lowerSupportHeight / 2 + holeBalkHeight / 4 +  bridgeLevel / 2 / 1.3 + rot/2), -bridgeLength/2,
                angleX, angleY, Math.tan(cat2 / cat1), 
                1, rot, supportThick) );
        triangles.addAll( getRotate(0, -(lowerSupportHeight / 2 + holeBalkHeight / 4 +  bridgeLevel / 2 / 1.3 + cat1), -bridgeLength/2,
                0, 0, 0, 
                cat2, holeBalkHeight / 2, supportThick) );
        
        triangles.addAll( getRotate(0, -rot / 2 - lowerSupportHeight / 2, bridgeLength/2,
                angleX, angleY, -Math.tan(cat2 / cat1), 
                1, rot, supportThick) );
        triangles.addAll( getRotate(0, -rot / 2 - lowerSupportHeight / 2, bridgeLength/2,
                angleX, angleY, Math.tan(cat2 / cat1), 
                1, rot, supportThick) );
        triangles.addAll( getRotate(0, -(lowerSupportHeight / 2 + holeBalkHeight / 4 +  bridgeLevel / 2 / 1.3), bridgeLength/2,
                0, 0, 0, 
                cat2, holeBalkHeight / 2, supportThick) );
        
        triangles.addAll( getRotate(0, -(lowerSupportHeight / 2 + holeBalkHeight / 4 +  bridgeLevel / 2 / 1.3 + rot/2), bridgeLength/2,
                angleX, angleY, -Math.tan(cat2 / cat1), 
                1, rot, supportThick) );
        triangles.addAll( getRotate(0, -(lowerSupportHeight / 2 + holeBalkHeight / 4 +  bridgeLevel / 2 / 1.3 + rot/2), bridgeLength/2,
                angleX, angleY, Math.tan(cat2 / cat1), 
                1, rot, supportThick) );
        triangles.addAll( getRotate(0, -(lowerSupportHeight / 2 + holeBalkHeight / 4 +  bridgeLevel / 2 / 1.3 + cat1), bridgeLength/2,
                0, 0, 0, 
                cat2, holeBalkHeight / 2, supportThick) );
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
    private void createCircleMetal(){
        List<Triangle> trs = new ArrayList<Triangle>();
        for(int i = 0; i < 100; i++){
            trs = ( createRectangleWorld(1, 1, 50, 200 - (double)i / 10, (double)i / 10 , -bridgeLength / 2) );
           
        
         Matrix3 transform = Matrix3.getRotate(0, 0, (double)i / 100.0).multiply(Matrix3.getWorld(-(double)i/10.0 , (double)i/10.0 , -bridgeLength / 2));
         for(Triangle t : trs){
                Vertex v1 = transform.transform(t.v1);
                Vertex v2 = transform.transform(t.v2);
                Vertex v3 = transform.transform(t.v3);
                triangles.add( new Triangle(v1, v2, v3, Color.BLACK) );
            }
        }
    }
    
    private void createHole(){
        double height = -(lowerSupportHeight/2 + bridgeLevel + bridgeHeight + firstHoleHeight);
        triangles.addAll( createRectangleWorld(lowerSupportWidth / 5 + supportWidth / 2, holeBalkHeight, lowerSupportThick,
                0, height, -bridgeLength / 2) );
        triangles.addAll( createRectangleWorld(lowerSupportWidth / 5 + supportWidth / 2, holeBalkHeight, lowerSupportThick,
                0, height, bridgeLength / 2) );
        
        height -= (holeBalkHeight  + secondHoleHeight);        
        triangles.addAll( createRectangleWorld(lowerSupportWidth / 5 + supportWidth / 2, holeBalkHeight, lowerSupportThick,
                0, height, -bridgeLength / 2) );
        triangles.addAll( createRectangleWorld(lowerSupportWidth / 5 + supportWidth / 2, holeBalkHeight, lowerSupportThick,
                0, height, bridgeLength / 2) );
        
        double needHeight = height - holeBalkHeight * (countThirdHole - 1) - countThirdHole * thirdHoleHeight;
        int i = 0;
        
        System.out.println(height + " :h " + needHeight + " :nh");
        
        while(i < countThirdHole &&  needHeight > -supportHeight){
            height -= (holeBalkHeight + thirdHoleHeight);
                    System.out.println(height + " :h");
            triangles.addAll( createRectangleWorld(lowerSupportWidth / 5 + supportWidth / 2, holeBalkHeight, lowerSupportThick,
                0, height, -bridgeLength / 2) );
            triangles.addAll( createRectangleWorld(lowerSupportWidth / 5 + supportWidth / 2, holeBalkHeight, lowerSupportThick,
                0, height, bridgeLength / 2) );
            i++;
        }
    }
    
    private void createMetal(){
        double x = 0, y = 0, z = 0;
        int count = (int)(bridgeLength / metalThick) - 1;
        for(int i = 0; i < 2; i++){
            for(int j = 0; j < count; j++){
                x = bridgeWidth / 2 - i * (bridgeWidth - metalWidth);
                y = -(lowerSupportHeight/2 + bridgeLevel - bridgeHeight / 2);
                z =  -bridgeLength / 2 + supportThick / 2 + metalThick / 2 + j * metalThick;
                triangles.addAll( createRectangleWorld(metalWidth / 5, metalHeight / 5, metalThick, x, y, z) );
                triangles.addAll( createRectangleWorld(metalWidth / 5, metalHeight / 5, metalThick, x - metalThick, y, z) );
                triangles.addAll( createRectangleWorld(metalWidth / 5, metalHeight / 5, metalThick, x, y + metalHeight, z) );
                triangles.addAll( createRectangleWorld(metalWidth / 5, metalHeight / 5, metalThick, x - metalThick, y + metalHeight, z) );

                y = -(lowerSupportHeight/2 + bridgeLevel - bridgeHeight / 2 - metalHeight / 2.5);
                z = -bridgeLength / 2 + supportThick / 2 + metalThick / 10 + j * metalThick;
                triangles.addAll( createRectangleWorld(metalWidth / 5, metalHeight, metalThick / 5, 
                        x, y, z) );
                triangles.addAll( createRectangleWorld(metalWidth / 5, metalHeight, metalThick / 5, 
                        x, y, z + metalThick - metalThick / 5) );
                triangles.addAll( createRectangleWorld(metalWidth / 5, metalHeight, metalThick / 5, 
                        x - metalThick, y, z) );
                triangles.addAll( createRectangleWorld(metalWidth / 5, metalHeight, metalThick / 5, 
                        x - metalThick, y, z + metalThick - metalThick / 5) );

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
    
    private List<Triangle> createRectangleWorld(double width, double height, double thick,
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
    
    private List<Triangle> createRectangle(double width, double height, double thick){
      
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
                    System.out.println("ffff");
                }
            
            return triangles;
            
        }
}
