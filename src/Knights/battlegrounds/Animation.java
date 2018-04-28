/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Knights.battlegrounds;

import java.awt.image.BufferedImage;

/**
 *
 * @author chris
 */
public class Animation {
    private int speed;
    private int index;
    private long lastTime;
    private long timer;
    private BufferedImage[] frames;
    
    /**
     * Animation
     * 
     * constructor de la animacion
     * 
     * @param frames
     * @param speed 
     */
    public Animation(BufferedImage[] frames, int speed){
        this.frames = frames;
        this.speed = speed;
        index = 0;
        timer = 0;
        lastTime = System.currentTimeMillis();
    }
    
    /**
     * getCurrentFrame
     * 
     * @return obtiene el frame actual
     */
    public BufferedImage getCurrentFrame(){
        return frames[index];
    }
    
    /**
     * getSpeed
     * 
     * @return obtiene la velocidad de la animacion
     */
    public int getSpeed() {
        return speed;
    }
    
    /**
     * setspeed 
     * 
     * modifica la velocidad de la animacion 
     * 
     * @param speed 
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    
    /**
     * tick
     */
    public void tick(){
        timer += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();
        if(timer > speed){
            index++;
            timer = 0;
            if(index >= frames.length){
                index = 0;
            }
        }
     
    }
}
