/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphic;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.List;

/**
 *
 * @author Алексей
 */
public interface IRender {
    public void setRender(Graphics2D g2);
    public BufferedImage getImage(List<Model> models, int height, int width, Camera camera);
}
