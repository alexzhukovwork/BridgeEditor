package graphic;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Алексей
 */
public class Matrix3 {
    double[] values;
   
    Matrix3(double[] values) {
        this.values = values;
    }
    
    Matrix3 multiply(Matrix3 other) {
        double[] result = new double[16];
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                for (int i = 0; i < 4; i++) {
                    result[row * 4 + col] +=
                        this.values[row * 4 + i] * other.values[i * 4 + col];
                }
            }
        }
        return new Matrix3(result);
    }
    Vertex transform(Vertex in) {

        return new Vertex(
            (in.x * values[0] + in.y * values[4] + in.z * values[8] + in.w * values[12]) / in.w,
            (in.x * values[1] + in.y * values[5] + in.z * values[9] + in.w * values[13]) / in.w,
            (in.x * values[2] + in.y * values[6] + in.z * values[10] + in.w * values[14]) / in.w,
            in.x * values[3] + in.y * values[7] + in.z * values[11] + in.w * values[15]    
        );
    }
    
    public static Matrix3 getRotateСam(double angleX, double angleY, double angleZ){
        return(new Matrix3( new double[] {
            Math.cos(-angleY), 0, -Math.sin(-angleY), 0,
            0, 1, 0, 0,
            Math.sin(-angleY), 0, Math.cos(-angleY), 0,
            0, 0, 0, 1
        })).multiply(new Matrix3(new double[] {
            1, 0, 0, 0,
            0, Math.cos(-angleX), Math.sin(-angleX), 0,
            0, -Math.sin(-angleX), Math.cos(-angleX), 0,
            0, 0, 0, 1
        })).multiply( new Matrix3(new double[]{
            Math.cos(-angleZ), Math.sin(-angleZ), 0, 0,
            -Math.sin(-angleZ), Math.cos(-angleZ), 0, 0,
            0, 0, 1, 0,
            0, 0, 0, 1
        }));
    }
    
    public static Matrix3 getRotate(double angleX, double angleY, double angleZ){
        return (new Matrix3( new double[] {
            Math.cos(angleY), 0, -Math.sin(angleY), 0,
            0, 1, 0, 0,
            Math.sin(angleY), 0, Math.cos(angleY), 0,
            0, 0, 0, 1
   
        })).multiply(new Matrix3(new double[] {
            1, 0, 0, 0,
            0, Math.cos(angleX), Math.sin(angleX), 0,
            0, -Math.sin(angleX), Math.cos(angleX), 0,
            0, 0, 0, 1
        })).multiply(new Matrix3(new double[]{
            Math.cos(angleZ), Math.sin(angleZ), 0, 0,
            -Math.sin(angleZ), Math.cos(angleZ), 0, 0,
            0, 0, 1, 0,
            0, 0, 0, 1
        }));
    }
    
     public static Matrix3 GetPerspectiveMatrix()
        {
            double Far = 1000;
            double Near = 10;
            double fov = 70;
            double ar = 640 / 500;
            double tan = Math.tan( Math.toRadians(fov / 2) );
            
            
            return new Matrix3(new double[]{
                (1 / tan) / ar, 0,          0,                              0,
                0,              1 / tan,    0,                              0,
                0,              0,          (Far + Near)/Far - Near,        1,
                0,              0,          2 * Far * Near / (Far - Near), 0
            });
            
        }
    
    public static Matrix3 getWorld(double x, double y, double z){
        return new Matrix3(new double[]{
            1, 0, 0, 0,
            0, 1, 0, 0,
            0, 0, 1, 0,
            x, y, z, 1
        });
    }
    
    public static Matrix3 getOne(){
        return new Matrix3(new double[]{
            1, 0, 0, 0,
            0, 1, 0, 0,
            0, 0, 1, 0,
            0, 0, 0, 1
        });
    }
    
    public static Matrix3 getCam(double x, double y, double z, 
            double angleX, double angleY, double angleZ, double d){
        return (getCamCoordinat(x, y, z)).multiply((getRotateСam(angleX, angleY, angleZ)) );//.multiply(GetPerspectiveMatrix());
             //   .multiply(getCamPerspective(d) );
    }
    
    public static Matrix3 getCamCoordinat(double x, double y, double z){
        return new Matrix3(new double[]{
            1, 0, 0, 0,
            0, 1, 0, 0,
            0, 0, 1, 0,
            -x, -y, -z, 1
        });
    }
    
    public static Matrix3 getCamPerspective(double d){
        return new Matrix3(new double[]{
            1, 0, 0, 0,
            0, 1, 0, 0,
            0, 0, 1, 1/d,
            0, 0, 0, 0
        });
    }
    
    public static Matrix3 getZoom(double x, double y, double z){
        return new Matrix3(new double[]{
            x, 0, 0, 0,
            0, y, 0, 0,
            0, 0, z, 0,
            0, 0, 0, 1
        });
    }
}
