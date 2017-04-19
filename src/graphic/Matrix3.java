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
            (in.x * values[0] + in.y * values[4] + in.z * values[8] + in.w * values[12])/in.w,
            (in.x * values[1] + in.y * values[5] + in.z * values[9] + in.w * values[13])/in.w,
            (in.x * values[2] + in.y * values[6] + in.z * values[10] + in.w * values[14])/in.w,
            in.x * values[3] + in.y * values[7] + in.z * values[11] + in.w * values[15]      
        );
    }
}
