/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Knights.battlegrounds;

import Knights.battlegrounds.Game.STATE;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Shape;

/**
 *Item class
 * 
 * clase que permite heredar valores como x, y, ancho y altura.
 * se utilizaran para las clases player y arena
 * 
 * @author Angel Odiel Treviño Villanueva
 */
public abstract class Item{
    protected int iX;       //guarda la posicion en x
    protected int iY;       //guarda la posicion en y
    private int iWidth;   //guarda el ancho
    private int iHeight;  //guarda la altura
    
    final Game gaGame;
    
    /**
     * inicializa los valores de ITem
     * 
     * @param iX <b>iX</b>  posicion en x
     * @param iY <b>iY</b>  posicion en y
     * @param iWidth <b>iWidth</b>  ancho del objeto
     * @param iHeight <b>iHeight</b> altura del objeto
     */
    public Item(int iX, int iY, int iWidth, int iHeight, Game gaGame){
        
        this.iX = iX;
        this.iY = iY;
        this.iWidth = iWidth;
        this.iHeight = iHeight; 
        this.gaGame = gaGame; 
        
    }
        /**
     * 
     * @return 
     */
    private Rectangle getBounds() {
        return new Rectangle(getiX(), getiY(), getiWidth(), getiHeight());
    }
    /**
     * 
     * @param obj
     * @return 
     */
    public boolean intersects(Object obj) {
        return (obj instanceof Object && 
                this.getBounds().intersects(((Item) obj).getBounds()));
    }
    
    /**
     * getiX
     * 
     * @return la posicion en x
     */
    public int getiX() {
        return iX;
    }
    
    /**
     * setiX
     * 
     * @param iX para modificar la posicion en x del objeto
     */
    public void setiX(int iX) {
        this.iX = iX;
    }
    
    /**
     *getiY
     * 
     * @return la posicion en y del objeto
     */
    public int getiY() {
        return iY;
    }
    
    /**
     * setiY
     * 
     * @param iY para modificar la posicion en y del objeto
     */
    public void setiY(int iY) {
        this.iY = iY;
    }
    
    /**
     * getiWidth
     * 
     * @return el ancho del objeto
     */
    public int getiWidth() {
        return iWidth;
    }

    /**
     * setiWidth
     * 
     * @param iWidth para modificar el ancho del objeto
     */
    public void setiWidth(int iWidth) {
        this.iWidth = iWidth;
    }

    /**
     * getiHeight
     * 
     * @return la altura del objeto 
     */
    public int getiHeight() {
        return iHeight;
    }

    /**
     * setiHeight
     * 
     * @param iHeight para modificar la altura del objeto
     */
    public void setiHeight(int iHeight) {
        this.iHeight = iHeight;
    }

    /**
     * getGaGame
     * 
     * @return el objeto del juego
     */
    public Game getGaGame() {
        return gaGame;
    }
    
    /**
     * tick 
     * 
     * para modificar los objetos en cada tick
     * @param state
     */
    public abstract void tick();
    
    /**
     * render
     * 
     * para dibujar el objeto
     * 
     * @param gGraphics <b>Graphics</b> para dibuar el objeto 
     */
    public abstract void render(Graphics gGraphics);

 
}
