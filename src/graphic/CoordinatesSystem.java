/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphic;

/**
 *
 * @author Алексей
 */
public class CoordinatesSystem extends Model{
    
    public CoordinatesSystem(double worldX, double worldY, double worldZ) {
        super(worldX, worldY, worldZ);
        createModel();
    }
    
    private void createModel(){
        triangles.addAll( createRectangle(10, 30, 10) );
        triangles.addAll( createRectangle(10, 10, 30) );
        triangles.addAll( createRectangle(30, 10, 10) );
    }
    
}
