/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Knights.battlegrounds;

import Knights.battlegrounds.Game.STATE;
import static java.awt.Color.red;
import java.awt.Graphics;
import java.awt.Shape;

/**
 *Arena
 * 
 * Clase que controla la arena del juego
 * 
 * @author angel
 */
public class Arena extends Item{
    
    /**
     * Arena
     * 
     * constructor
     * 
     * @param iX para inicializar la posicion en x
     * @param iY para inicializar la posicion en y 
     * @param iWidth para el ancho de la arena
     * @param iHeight para la altura de la arena
     * @param gaGame para el game
     */
    public Arena(int iX, int iY, int iWidth, int iHeight, Game gaGame){
        super(iX, iY, iWidth, iHeight, gaGame);
    }
    
    /**
     * tick
     * 
     * tick de la arena
     * 
     */
    @Override
    public void tick() {
    }
    
    /**
     * render
     * 
     * dibuja la arena
     * 
     * @param gGraphics 
     */
    @Override
    public void render(Graphics gGraphics) {
        gGraphics.drawImage(Assets.plataform, getiX(), getiY(), getiWidth(), 
                getiHeight(), null);
    }
    
}
