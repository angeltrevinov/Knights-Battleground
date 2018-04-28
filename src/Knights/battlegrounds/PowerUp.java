/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Knights.battlegrounds;

import java.awt.Graphics;
import java.awt.Shape;
import java.util.concurrent.ThreadLocalRandom;

/**
 *PowerUp
 * 
 * clase para poder utilizar powerups
 * 
 * @author Angel Odiel Treviño Villanueva A01336559
 */
public class PowerUp extends Item{
    
    private int iNumPower;      //para saber que power up se le entregara
    
    /**
     * PowerUp 
     * 
     * constructor del PowerUp
     * 
     * @param iX posicion en x
     * @param iY posicion en y
     * @param iWidth ancho del objeto
     * @param iHeight altura del objeto 
     * @param gaGame 
     */
    public PowerUp(int iX, int iY, int iWidth, int iHeight, Game gaGame) {
        super(iX, iY, iWidth, iHeight, gaGame);
        iNumPower = ThreadLocalRandom.current().nextInt(0, 5 + 1);      //crea un numero aleatorio para los powerups
    }
    
    /**
     * getiNumPower 
     * @return int
     */
    public int getiNumPower() {
        return iNumPower;
    }
    
    /**
     * tick
     */
    @Override
    public void tick() {
    }
    
    /**
     * render
     * @param gGraphics 
     */
    @Override
    public void render(Graphics gGraphics) {
    }
    
}
