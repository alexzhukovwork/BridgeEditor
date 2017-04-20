/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphic;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Алексей
 */
public class Camera implements Coordinats{
    List<Triangle> triangles = new ArrayList<>();
    
    @Override
    public void setTriangle(List<Triangle> triangles) {
        this.triangles = triangles;
    }
    
}
