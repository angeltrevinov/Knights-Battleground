/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Knights.battlegrounds;

import static java.awt.Color.pink;
import static java.awt.Color.red;
import java.awt.Graphics;
import java.awt.Shape;

/**
 *
 * @author Angel Odiel Treviño Villanueva A01336559
 */
public class Enemy extends Item{
    
     /**
     * Enemy
     * 
     * constructor de player
     * 
     * @param iX para inicializar la posicion en x
     * @param iY para inicializar la posicion en y 
     * @param iWidth para el ancho del enemigo
     * @param iHeight para la altura del enemigo
     * @param gaGame para el game
     */
    public Enemy(int iX, int iY, int iWidth, int iHeight, Game gaGame){
        super(iX, iY, iWidth, iHeight, gaGame);
    }
    
    /**
     * tick
     * 
     * tick del enemigo
     * 
     */
    @Override
    public void tick() {
        /*setiX(gaGame.getpPlayer().getiX() - 10);
        setiY(gaGame.getpPlayer().getiY() - 10);*/
    }
    
    /**
     * render
     * 
     * dibuja al enemigo 
     * 
     * @param gGraphics 
     */
    @Override
    public void render(Graphics gGraphics) {
        gGraphics.setColor(pink);
        gGraphics.fillOval(getiX(), getiY(), getiWidth(), getiHeight());
    }
    
}
