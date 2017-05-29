package graphic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Camera implements Coordinats, Serializable{
    public double x, y, z;
    public double d;
    public double angleX, angleY, angleZ;
    public int width, height;
    Vertex e;
    List<Triangle> triangles = new ArrayList<>();
    
    public Camera(){
        e = new Vertex(0, 0, 1);
        width = height = 0;
        angleX = angleY = angleZ = 0;
        x = y = z = 0;
        d = 0;
    }
    
     public void setCamToDot(){
        
        Vertex c = new Vertex(0, 0, -1);
        Vertex e = new Vertex(this.e.x - x, 0, this.e.z - z);
        double cameraLength = Vertex.getLen(c);
        double dotLength = Vertex.getLen(e);
        double vertexMul = Vertex.multiplyD(e, c);
        angleY = Math.toDegrees( Math.acos(vertexMul / (cameraLength * dotLength)) );
        angleY *= this.e.x - this.x < 0 ? 1 : -1;
               
        c = new Vertex(this.e.x - x, 0, this.e.z - z);
        e = new Vertex(this.e.x - x, this.e.y - y, this.e.z - z);
        cameraLength =  Vertex.getLen(c);
        dotLength = Vertex.getLen(e);
        vertexMul = Vertex.multiplyD(e, c);
        angleX =  Math.toDegrees( Math.acos(vertexMul / (cameraLength * dotLength)) ) ;
        angleX *= this.e.y - this.y <= 0 ? 1 : -1;   
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
