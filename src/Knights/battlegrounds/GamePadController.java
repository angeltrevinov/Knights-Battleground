/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Knights.battlegrounds;

import java.awt.Component;
import static java.awt.GridBagConstraints.NONE;
import static jdk.nashorn.internal.codegen.CompilerConstants.className;
import net.java.games.input.Component.Identifier;
import static net.java.games.input.Component.Identifier.Button.A;
import static net.java.games.input.Component.Identifier.Button._0;
import net.java.games.input.Component.POV;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;

/**
 *GamePadController
 * 
 * para poder utilizar un control para el videojuego 
 * 
 * @author Angel Odiel Treviño Villaneuva A01336559
 */
public class GamePadController {
    
    private Controller GamePad;     //para optener el GamePad
    private Controller[] Controllers;       //obtener una lista con los controles
    private net.java.games.input.Component[] components;    //lista de los componentes
    private net.java.games.input.Component component;
    //indices para los analogos
    private int xAxisId; //left x
    private int yAxisId; //left y
    private int rxAxisId; //right x
    private int ryAxisId; //right y 
    //direccion 
    public static final int NW = 0;
    public static final int NORTH = 1;
    public static final int NE = 2;
    public static final int WEST = 3;
    public static final int NONE = 4;  // default value
    public static final int EAST = 5;
    public static final int SW = 6;
    public static final int SOUTH = 7;
    public static final int SE = 8;
    
    private int povIdx; //para conocer el index del POV
    
    //los botones
    public static final int NUM_BUTTONS = 15; //creo que son 14 botones
    private int buttonsIdx[];   //para guardar los indices del boton
    
    //bottones que necesito, su mapeo cambia dependiendo del sistema 
    private int ButtonA = 100;
    private int ButtonB = 100;
    private int ButtonX = 100;
    private int ButtonY = 100;
    private int ButtonStart = 100;
    private int ButtonBack = 100;
    
    private int iNumController;
    
    /**
     * GamePadController
     * 
     * constructor de la clase y el cual busControllers un control tipo GamePad
     * y sus componentes
     */
    public GamePadController(int iNumController){
        this.iNumController = iNumController;
        Controllers = ControllerEnvironment.getDefaultEnvironment().getControllers();
        GamePad = findGamePad(Controllers);
        System.out.println("Game controller: "+ GamePad.getName() + ", " + 
                GamePad.getType() + " Number of controller: "+ getiNumController());
        findComponents(GamePad);
        
    }//constructor

    /**
     * setButtonA
     * 
     * para definir cual es mi boton A
     * 
     * @param ButtonA int para indicar donde esta el boton A dentro de mi control
     */
    public void setButtonA(int ButtonA) {
        this.ButtonA = ButtonA;
    }
    
    /**
     * setButtonB
     * 
     * para definir cual es mi boton B
     * 
     * @param ButtonB int para indicar donde esta el boton B dentro de mi control
     */
    public void setButtonB(int ButtonB) {
        this.ButtonB = ButtonB;
    }
    
    /**
     * setButtonX
     * 
     * para definir cual es mi boton X
     * 
     * @param ButtonX int para indicar donde esta el boton X dentro de mi control
     */
    public void setButtonX(int ButtonX) {
        this.ButtonX = ButtonX;
    }
    
    /**
     * setButtonY
     * 
     * para definir cual es mi boton Y
     * 
     * @param ButtonY int para indicar donde esta el boton Y dentro de mi control
     */
    public void setButtonY(int ButtonY) {
        this.ButtonY = ButtonY;
    }
    
    /**
     * setButtonStart
     * 
     * para definir cual es mi boton Start
     * 
     * @param ButtonStart int para indicar donde esta el boton Start dentro de mi control
     */
    public void setButtonStart(int ButtonStart) {
        this.ButtonStart = ButtonStart;
    }
    
    /**
     * setButtonBack
     * 
     * para definir cual es mi boton Back
     * 
     * @param ButtonBack int para indicar donde esta el boton Back dentro de mi control
     */
    public void setButtonBack(int ButtonBack) {
        this.ButtonBack = ButtonBack;
    }
    
    /**
     * getButtonBack
     * 
     * @return la posicion del boton Back
     */
    public int getButtonBack() {
        return ButtonBack;
    }
    
    /**
     * getButtonA
     * 
     * @return la posicion del boton A
     */
    public int getButtonA() {
        return ButtonA;
    }
    
    /**
     * getButtonB
     * 
     * @return la posicion del boton B
     */
    public int getButtonB() {
        return ButtonB;
    }
    
    /**
     * getButtonX
     * 
     * @return la posicion del boton X
     */
    public int getButtonX() {
        return ButtonX;
    }
    
    /**
     * getButtonY
     * 
     * @return la posicion del boton Y 
     */
    public int getButtonY() {
        return ButtonY;
    }
    
    /**
     * getButtonStart
     * 
     * @return la posicion del boton Start
     */
    public int getButtonStart() {
        return ButtonStart;
    }
    
    /**
     * getNW
     * 
     * @return el valor de la direccion North West
     */
    public static int getNW() {
        return NW;
    }
    
    /**
     * getNORTH
     * 
     * @return el valor de la direccion North 
     */
    public static int getNORTH() {
        return NORTH;
    }
    
    /**
     * getNE
     * 
     * @return el valor de la direccion North East
     */
    public static int getNE() {
        return NE;
    }
    
    /**
     * getWEST
     * 
     * @return el valor de la direccion WEST 
     */
    public static int getWEST() {
        return WEST;
    }
    
    /**
     * getNONE
     * 
     * @return el valor cuando no se apunta a ninga direccion
     */
    public static int getNONE() {
        return NONE;
    }
    
    /**
     * getEAST
     * 
     * @return el valor de la direccion EAST
     */
    public static int getEAST() {
        return EAST;
    }
    
    /**
     * getSW
     * 
     * @return el valor de la direccion South East
     */
    public static int getSW() {
        return SW;
    }
    
    /**
     * getSOUTH
     * 
     * @return el valor de la direccion South
     */
    public static int getSOUTH() {
        return SOUTH;
    }
    
    /**
     * getSE
     * 
     * @return el valor de la direccion South East
     */
    public static int getSE() {
        return SE;
    }
    
    /**
     * getiNumController
     * 
     * @return el numero de control que es
     */
    public int getiNumController() {
        return iNumController;
    }
    
    
    
    /**
     *Poll 
     */
    public void poll(){
        GamePad.poll();
    }//poll
    
    /**
     * findGamePad 
     * 
     * encuentra un GamePad dentro de la lista de controles conectados
     * 
     * @param Controllers una lista con todos los controles conectados
     * 
     * @return un control de tipo GamePad
     */
    private Controller findGamePad(Controller[] Controllers){
        Controller GamePad = null; 
        int FoundControllers = 0; //numero de GamePads encontrados 
        for(int i =0;i<Controllers.length;i++){ 
            
            //para saber si es un gamepad
            if(Controllers[i].getType() == Controller.Type.GAMEPAD){
                if(FoundControllers == getiNumController()){//si ya lo utilizamos
                    GamePad = Controllers[i];
                }
                FoundControllers++;
            }
        }
        if(GamePad == null){ //si no hay, nos salimos
            System.out.println("no GamePad found");
            System.exit(0);
        }
        return GamePad;
    }//findGamePAd
    
    /**
     * findComponets
     * 
     * busca todos los componentes que tiene el GamePad
     * 
     * @param GamePad un control 
     */
    private void findComponents(Controller GamePad){
       components =  GamePad.getComponents();
       System.out.println("Component count: " + components.length);
       //para obtener el index de los axis
       xAxisId = findCompIndex(components, Identifier.Axis.X, "x-axis"); //left x
       yAxisId = findCompIndex(components, Identifier.Axis.Y, "y-axis"); //left y
       rxAxisId = findCompIndex(components, Identifier.Axis.RX, "rx-axis"); //right x
       ryAxisId = findCompIndex(components, Identifier.Axis.RY, "ry-axis"); //right y 
       //para obtener el indece del pov
       povIdx = findCompIndex(components, Identifier.Axis.POV, "POV-hat");
       findButtons(components);
    }//findComponents
    
    /**
     * findButtons
     * 
     * busca todos los botones del gamepad
     * 
     * @param components una lista con los componentes del gamepad
     */
    private void findButtons(net.java.games.input.Component[] components){
        buttonsIdx = new int[NUM_BUTTONS];
        int numButtons = 0;
        for(int i = 0; i < components.length; i++){
            if(!components[i].isAnalog() && !components[i].isRelative()){
                if(numButtons == NUM_BUTTONS){
                    System.out.println("found an extra button, gonna ignore it");
                }else{
                    
                    //si el sistema es mac
                    //el boton a
                    if(components[i].getName().equals("4")){
                        setButtonA(i);
                    }
                    //el boton b
                    if(components[i].getName() == "5"){
                        setButtonB(i);
                    }
                    
                    //el boton x
                    if(components[i].getName() == "10"){
                        setButtonX(i);
                    }
                    
                    //el boton y
                    if(components[i].getName() == "0"){
                        setButtonY(i);
                    }
                    
                    //el boton back
                    if(components[i].getName() == "13"){
                        setButtonBack(i);
                    }
                    
                    //el boton start
                    if(components[i].getName() == "12"){
                        setButtonStart(i);
                    }
                    
                    buttonsIdx[numButtons] = i;     //found a button
                    System.out.println("found: "+ components[i].getName() +
                            "; index: "+ i + " Identifier: " + components[i].getIdentifier());
                    numButtons++;
                }
            }
        }
        
        //si el sistema es windows
        if(getButtonA() == 100){
            setButtonA(1);
        }
        
        if(getButtonB() == 100){
            setButtonB(2);
        }
        
        if(getButtonX() == 100){
            setButtonX(3);
        }
        
        if(getButtonY() == 100){
            setButtonY(4);
        }
        
        if(getButtonBack() == 100){
            setButtonBack(7);
        }
        
        if(getButtonStart() == 100){
            setButtonStart(8);
        }
        
        //llena el arreglo por si hay botones vacios
        if(numButtons < NUM_BUTTONS){
            System.out.println("too few buttons");
            while(numButtons < NUM_BUTTONS){
                buttonsIdx[numButtons] = -1;
                numButtons++;
            }
        }
    }//findButtons
    
    /**
     * findCompIndex
     * 
     * para saber los indices de los componentes analogos
     * 
     * @param components una lista con los componentes
     * @param Id su identificador 
     * @param mn y un string que los identifique
     * @return 
     */
    private int findCompIndex(net.java.games.input.Component[] components, 
            net.java.games.input.Component.Identifier Id, String mn){
        
        for(int i = 0; i < components.length; i++){
            if(components[i].getIdentifier() == Id && !components[i].isRelative()){
                System.out.println("Found "+ components[i].getName()+ "; Index: "+i);
                return i;
            }
        }
        System.out.println("no "+ mn +" component found");
        return -1;
    }//findCompIndex    
    
    /**
     * getLXYDir
     * 
     * para obtener la direccion que el LeftStick tiene
     * 
     * @return un entero con la direccion 
     */
    public int getLXYDir(){
        if(xAxisId == -1 || yAxisId == -1){
            System.out.println("x, y data unavailable");
            return NONE;
        }
        return getCompasDir(xAxisId, yAxisId);
    }//getLXYDir
    
    /**
     * getRXYDir
     * 
     * para obtener la direccion que el RightStick tiene
     * 
     * @return un entero con la direccion
     */
    public int getRXYDir(){
        if(rxAxisId == -1 || ryAxisId == -1){
            System.out.println("x, y data unavailable");
            return NONE;
        }
        return getCompasDir(rxAxisId, ryAxisId);
    }//getRXYDir
    
    /**
     * getHatDir
     * 
     * para obtener la direccion que tiene el POV
     * 
     * @return un entero con la direccion 
     */
    private int getHatDir(){
        if(povIdx == -1){
            System.out.println("POV data unavailable");
            return NONE; 
        }
        float povDir = components[povIdx].getPollData();
        if(povDir == POV.CENTER){
            return NONE;
        }else if(povDir == POV.DOWN){
            return SOUTH;
        }else if(povDir == POV.DOWN_LEFT){
            return SW;
        }else if(povDir == POV.DOWN_RIGHT){
            return SE;
        }else if(povDir == POV.LEFT){
            return WEST;
        }else if(povDir == POV.RIGHT){
            return EAST;
        }else if(povDir == POV.UP){
            return NORTH;
        }else if(povDir == POV.UP_LEFT){
            return NW;
        }else if(povDir == POV.UP_RIGHT){
            return NE;
        }
        System.out.println("POV hat value out of range: " + povDir);
        return NONE;
    }//getHatDir
    
    /**
     * getCompasDir
     * 
     * obtener la direccion del compas
     * 
     * @param xA indice del eje x
     * @param yA indice del eje y
     * @return la direccion en que apunta el compas
     */
    private int getCompasDir(int xA, int yA){
        float xCoord = components[xA].getPollData();
        float yCoord = components[yA].getPollData(); 
        int xc = Math.round(xCoord);
        int yc = Math.round(yCoord);
        
        if(yc == -1 && xc == -1){
            return NW;
        }else if(yc == -1 && xc == 0){
            return NORTH;
        }else if(yc == -1 && xc == 1){
            return NE; 
        }else if(yc == 0 && xc == -1 ){
            return WEST;
        }else if(yc == 0 && xc == 0){
            return NONE; 
        }else if(yc == 0 && xc == 1){
            return EAST;
        }else if(yc == 1 && xc == -1){
            return SW; 
        }else if(yc == 1 && xc == 0){
            return SOUTH; 
        }else if(yc == 1 && xc == 1){
            return SE; 
        }else{
            System.out.println("unknown");
            return NONE;
        }
    }//getCompasDir
    
    /**
     * getButtons
     * 
     * @return una lista de booleanos que obtiene todos los valores
     */
    public boolean[] getButtons(){
        boolean[] buttons = new boolean[NUM_BUTTONS];
        float value;
        for(int i = 0; i < NUM_BUTTONS; i++){
            value = components[ buttonsIdx[i] ].getPollData();
            buttons[i] = ((value == 0.0f) ? false : true);
        }
        return buttons;
    }//getButtons
    
    /**
     * isButtonPressed 
     * 
     * @param pos posicion del boton dentro del arreglo 
     * 
     * @return bool si esta presionado o no
     */
    public boolean isButtonPressed(int pos){
        if(pos < 1 || pos > NUM_BUTTONS){
            System.out.println("no button found");
            return false;
        }
        
        float value = components[ buttonsIdx[pos-1] ].getPollData();
        return ((value == 0.0f) ? false : true);
    }//end of button is pressed
}
