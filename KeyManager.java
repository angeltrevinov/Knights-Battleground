/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Knights.battlegrounds;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *Keymanager
 * 
 * Calse para jugar con teclas, 
 * se tiene planeado eliminarla despues de implementar jugabilidad con control
 * 
 * @author Angel Odiel Treviño Villanueva A01336559
 */
public class KeyManager implements KeyListener{
    private static boolean bUp;      //Flag to move Up the player 1
    private static boolean bDown;    //Flag to move Down the player 1
    private static boolean bLeft;    //Flag to move Left the player 1
    private static boolean bRight;   //Flag to move Right the player 1
    private static boolean bW;      //flag para mover hacia riba el player 2
    private static boolean bS;      //flag para mover hacia abajo el player 2
    private static boolean bA;      //flag para mover hacia la izquierda el player 2
    private static boolean bD;      //flag para mover hacia la derecha el player 2
    private static boolean Space;   //flag para atacar player 2
    private static boolean v;       //flag para atacar player 1

    private boolean arrKeys[];       //To store all the flags for every key

    /**
     * KeyManager
     *
     * Constructor that initializes the array of 256 booleans
     */
    public KeyManager(){
        arrKeys = new boolean[256];
    }

    /**
     * isbUp
     *
     * @return the value of the bUp flag
     */
    public static boolean isbUp() {
        return bUp;
    }
    /**
     * 
     * @return the value of the v flag 
     */
    public static boolean isV(){
        return v;
    }
    
    /**
     * 
     * @return the value of the Space flag
     */
    public static boolean isSpace(){
        return Space;
    }

    /**
     * isbDown
     *
     * @return the value of the bDown flag
     */
    public static boolean isbDown() {
        return bDown;
    }

    /**
     * isbLeft
     *
     * @return the value of the bLeft flag
     */
    public static boolean isbLeft()
    {
        return bLeft;
    }

    /**
     * isbRight
     *
     * @return the value of the bRight flag
     */
    public static boolean isbRight()
    {
        return bRight;
    }

     /**
     * setbUp
     *
     * @param bUp to modify the bUp flag
     */
    public static void setbUp(boolean bUp) {
        KeyManager.bUp = bUp;
    }
    /**
     * 
     * @param Space to modify the flag 
     */
    public static void setSpace(boolean Space){
        KeyManager.Space = Space;
    }
    /**
     * 
     * @param v  to modify the flag
     */
    public static void setV(boolean v){
        KeyManager.v = v;
    }
    
     /**
     * setbDown
     *
     * @param bDown to modify the bDown flag
     */
    public static void setbDown(boolean bDown) {
        KeyManager.bDown = bDown;
    }

    /**
     * setbLeft
     *
     * @param bLeft to modify the bLeft flag
     */
    public static void setbLeft(boolean bLeft)
    {
        KeyManager.bLeft = bLeft;
    }

    /**
     * setbRight
     *
     * @param bRight to modify the bRight flag
     */
    public static void setbRight(boolean bRight)
    {
        KeyManager.bRight = bRight;
    }
    
    /**
     * isbW
     * 
     * @return para saber el valor de bW
     */
    public static boolean isbW() {
        return bW;
    }
    
    /**
     * setbW
     * 
     * @param bW para modificar el valor de bW
     */
    public static void setbW(boolean bW) {
        KeyManager.bW = bW;
    }
    
    /**
     * isbS
     * 
     * @return para saber el valor de bS
     */
    public static boolean isbS() {
        return bS;
    }
    
    /**
     * setbS
     * 
     * @param bS modificar el valor de bS
     */
    public static void setbS(boolean bS) {
        KeyManager.bS = bS;
    }
    
    /**
     * isbA
     * 
     * @return saber el valor de bA
     */
    public static boolean isbA() {
        return bA;
    }
    
    /**
     * setbA
     * 
     * @param bA modificar el valor de bA
     */
    public static void setbA(boolean bA) {
        KeyManager.bA = bA;
    }
    
    /**
     * isbD
     * 
     * @return saber el valor de bD
     */
    public static boolean isbD() {
        return bD;
    }
    
    /**
     * setbD
     * 
     * @param bD modificar el valor de bD
     */
    public static void setbD(boolean bD) {
        KeyManager.bD = bD;
    }
    
    

    @Override
    public void keyTyped(KeyEvent e){
    }

    /**
     * keyPressed
     *
     * Function that are called when a key is being pressed
     *
     * @param e to know what KeyEvent happened
     */
    @Override
    public void keyPressed(KeyEvent e){
        arrKeys[e.getKeyCode()] = true;
        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            arrKeys[e.getKeyCode()] = false;
        }

    }

    /**
     * keyReleased
     *
     * This function is called when a key is realesed
     *
     * @param e to know what KeyEvent happened
     */
    @Override
    public void keyReleased(KeyEvent e){
        arrKeys[e.getKeyCode()] = false;
     
    }

    /**
     * tick
     *
     * Function is called every tick of the object Game
     */
    public void tick() {
        //Equals the private variables wit their flag of this class    
        setbUp(arrKeys[KeyEvent.VK_UP]);
        setbDown(arrKeys[KeyEvent.VK_DOWN]);
        setbLeft(arrKeys[KeyEvent.VK_LEFT]);
        setbRight(arrKeys[KeyEvent.VK_RIGHT]);
        
        setSpace(arrKeys[KeyEvent.VK_SPACE]);
        setV(arrKeys[KeyEvent.VK_V]);
        
        setbW(arrKeys[KeyEvent.VK_W]);
        setbA(arrKeys[KeyEvent.VK_A]);
        setbS(arrKeys[KeyEvent.VK_S]);
        setbD(arrKeys[KeyEvent.VK_D]);

    }
}

