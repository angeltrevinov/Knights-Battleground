/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Knights.battlegrounds;

import java.awt.Graphics;
import java.awt.Shape;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 *                  CLASS GAME
 * 
 * This class constructs and connects all the classes together so the game can work.
 * 
 */
public class Game implements Runnable{
    
    /*                          VARIABLES FOR DISPLAY                                                                 */
    //                                                      //To have differen buffers while displaying
    private BufferStrategy bsBuffer; 
    //                                                      //To paint the objects on the screen
    private Graphics gGraphics;
    //                                                      //To display the game
    private Display dispDisplay;                            
    /*END*/
    
    /*VARIABLES FOR THE WINDOW*/
    //                                                      //The title of the window
    String sTitle;
    //                                                      //The width of the window
    private int iWidth;                 
    //                                                      //The Height of the window
    private int iHeight;
    //                                                      //The number of players
    private int iNumPlayers;            
    /*END*/
    
    /*VARIABLES FOR RUNNING*/
    //                                                      //Creates the Thread for the game
    private Thread thrThread;           
    //                                                      //Boolean to see if the game is running
    private boolean bRunning;           
    /*                          END                                                                                   */
    
    /*                          ARRAYS OF FOR THE PLAYERS                                                             */
    //                                                      //The Array with the players
    private ArrayList<Player> arrPlayers;   
    //                                                      //The Array with the Controllers
    private ArrayList<GamePadController> GMPControllers;
    /*                          END                                                                                   */
    
    //                                                      //creates plataforms
    private Arena aPlataforms;         
    
    /*                          POINTERS FOR THE MENU                                                                 */
    //                                                      //initial value for the pointer in y of the menu
    private int iPointery = 435;
    //                                                      //initial value for the pointer in x of the menu
    private int iPointerx = 250;    
    
    //                                                      //initial value for the second pointer in y of the menu
    private int iPointery2 = 435;
    //                                                      //initial value for the second pointer in x of the menu
    private int iPointerx2 = 600;  
    /*                          END                                                                                   */
    
    /*                          ASSETS                                                                                */
    //                                                      //animation for the background of the menu
    private Animation animationBG;
    //                                                      //animation for the background of the first stage
    private Animation fightanimation; 
    //                                                      //animation for the background of the second stage
    private Animation fightanimation2;
    //                                                      //animation for the background of the third stage
    private Animation fightanimation3; 
    //                                                      //To store the Background sound of the menu
    private SoundClip menumusic;      
    //                                                      //To store the sound for the navigation in the menu
    private SoundClip navigate;       
    //                                                      //To store the BG for the battle face 
    private SoundClip battle1;
    //                                                      //The Current state of the game
    public STATE state;
    //                                                      //The previous state of the game
    public STATE LastState;  
    //                                                      //To store the sound of the selection in the menus
    private SoundClip select;
    //                                                      //To store the sound for the return in the menu
    private SoundClip selectBack;
    //                                                      //The sound for the start in the welcome display 
    private SoundClip start;
    /*                          END                                                                                   */
    
    //                                                      //A random number that tells in what stage the game is gonna 
    //                                                      //      take
    private Random r;
    //                                                      //Stores the random number
    private int random;
    //                                                      //The Array with the Plataforms
    private ArrayList<Arena> ArenaFloor;
    //                                                      //An Array that is in charge of seeing the lives for the 
    //                                                      //      teams
    private int[] LivesTeam;
    
    /**
     *          Constructor for the Game
     * 
     *Creats the game and initializes the window
     * 
     * @param sTitle                                        The title of the window
     * @param iWidth                                        The width of the window
     * @param iHeight                                       The height of the window
     * @param iNumPlayers                                   The number of players that are gonna play
     */
    public Game(String sTitle, int iWidth, int iHeight, int iNumPlayers){
        
        this.sTitle = sTitle;
        this.iWidth = iWidth;
        this.iHeight = iHeight;
        this.iNumPlayers = iNumPlayers;
        //                                                  //The game its not running yet
        bRunning = false; 
        //                                                  //To store the number of teams, 0 is when is Free for All
        //                                                  //
        LivesTeam = new int[3];
    }
    
    
  
 /**
  *             ENUM for the STATEs of the Game
  * 
  * Menu:               Menu of the game, where the different optione appear
  * Start:              The first screen the player sees when starting the game
  * GameFFA:            State where the game mode is Free For ALL
  * Game2v2:            State where the game mode is 2 vs 2 
  * Game1v1:            State where the game mode is 1 vs 1 
  * Settings:          State where it Shows the user the controllers and how to play the game
  * ModeSelection:      State where the user can select the game mode it wants 
  * Pause:              State for pause inse the gameplay modes
  * newGame1v1:         Creates New match for 1 vs 1 
  * newGame2v2:         Creates New match for 2 vs 2 
  * newGameFFA:         Creates New match for Free For ALL
  * Victory:            State where indicates if a player won
  */   
    public enum STATE{
        MENU,
        Start,
        GAME,
        GameFFA,
        Game2v2,
        Game1v1,
        Settings,
        ModeSelection,
        Pause,
        newGame1v1,
        newGame2v2,
        newGameFFA,
        Victory
        
    };
    
    /**
     *          SetStatePause
     * 
     * Sets the Game in Pause State 
     * 
     */
    public void setStatePause(){
        LastState = state;
        state = STATE.Pause;
        setPointerx(505);
        setPointery1(305);
        setPointerx2(295);
        setPointery2(305);
        
    }
    
    /**
     *          SetStateVictory
     * 
     * sets the Game in the Victory State
     */
    public void setStateVictory(){
        setPointerx(275);
        setPointery1(475);
        setPointerx2(500);
        setPointery2(475);
        state = STATE.Victory;
    }
    
    /**
     *          CheckVictory
     * 
     * Method that checks if a player(s) one
     */
    public void checkVictory(){
        //                                                  //if the state is 1 vs 1
        if(state == STATE.Game1v1){
            
            //                                              //checks the lives of all players and see who has no lifes 
            //                                              //      left
            Iterator itr = arrPlayers.iterator();
            while(itr.hasNext()){
                Player playeraux = (Player) itr.next();
                if(playeraux.getLives() < 1){
                    setStateVictory();
                }    
            }
        }else //                                            //if the state is 2 vs 2 
            if(state == STATE.Game2v2){
                
                //                                          //checks the lifes of all playes and see who has no lifes
                //                                          //      left
                Iterator itr = arrPlayers.iterator();
                while(itr.hasNext()){
                    Player playeraux = (Player) itr.next();
                    //                                      //if that player is dead, goes to the array with the lifes 
                    //                                      //      of teams, and assings his team one dead player
                    if(playeraux.getLives() < 1){
                        LivesTeam[playeraux.getTeam()]++;
                        itr.remove();
                        itr = arrPlayers.iterator();
                    }
                }
                
                //                                          //if that team has 2 players dead, then they lost
                for(int i = 1; i <= 2; i++){
                    if(LivesTeam[i] == 2){
                        setStateVictory();
                    }
                }
        
        }else //                                            //if the game mode is Free for ALL
            if(state == STATE.GameFFA){
                //                                          //Checks the lifes of all players until just one is left 
                //                                          //      alive
                int cont=0;
                Iterator itr = arrPlayers.iterator();
                while(itr.hasNext()){
                    cont++;
                    Player playeraux = (Player) itr.next();
                    if(playeraux.getLives() < 1){
                        itr.remove();
                        itr = arrPlayers.iterator();
                    }    
                }
                if(cont == 1){
                    setStateVictory();
                }
        }
    }

    /**
     *          SetStateStart
     * 
     * sets the game in the Start screen
     */
    public void setStateStart(){
        state = STATE.Start;
        Assets.menumusic.setLooping(true);
        Assets.menumusic.play();
    }
    
    /**
     *          QuitPause
     * 
     * method that quits from the pause menu
     */
    public void QuitPause(){
    state = LastState;
}

    /**
     *          GetIntRandom
     * 
     * Generates a random integer from a random obj between 0 and 3
     * 
     * @param r
     * @return random integer
     */    
    public int getIntRandom(Random r){
        int aux = r.nextInt(3-0) + 0;
        return aux;
    }
    
    /**
     *          SetStateNewGame1vs1
     * 
     * Creates a new game with the game mode 1 vs 1
     * 
     * @param r random object
     * @return random integer
     */    
    public int setStateNewGame1v1(Random r){
        //                                                  //creates a list with the players
        for(int i = 0; i < 2; i++){
            Player player = new Player((getiWidth() /2 ) - 100, (getiHeight() / 2) - 100 * i, 100, 100, this, i, 
                GMPControllers.get(i), 0,3);
        
            arrPlayers.add(player);  
        }
        
        //                                                  //sets the players I think it does what the constructor did, 
        //                                                  //      but arranges the positions
        Iterator itr;
        itr = arrPlayers.iterator();
        Player paux = (Player) itr.next();
        paux.setiX(200);
        paux.setiY(826);
        paux.setLives(3);
        paux = (Player) itr.next();
        paux.setiX(700);
        paux.setiY(100);
        paux.setLives(3);
        
        //                                                  //Creates the plataforms, this can be done in a single 
        //                                                  //      method in the future
        for(int i = 0; i < (getiWidth()/100); i++) {
           
            aPlataforms =  new Arena(90*i + 30, iHeight - 70, 120, 100, this); 
            ArenaFloor.add(aPlataforms); 
            
            if(i != 3 && i != 4 && i!= 5 ) {
                aPlataforms =  new Arena(90*i + 30, iHeight - 190, 120, 100, this); 
                ArenaFloor.add(aPlataforms); 
           }
            
           if(i != 1 && i != 7) {
                aPlataforms =  new Arena(90*i + 30, iHeight - 310, 120, 100, this); 
                ArenaFloor.add(aPlataforms); 
           }
           
           if(i != 3 && i != 4 && i!= 5 ) {
                aPlataforms =  new Arena(90*i + 30, iHeight - 430, 120, 100, this); 
                ArenaFloor.add(aPlataforms); 
           }
           
           if(i != 2 && i!= 3 && i!= 4 && i!= 5 && i!= 6  ) {
                aPlataforms =  new Arena(90*i + 30, iHeight - 430, 120, 100, this); 
                ArenaFloor.add(aPlataforms); 
           }
           
           if(i != 2 && i!= 3 && i!= 4 && i!= 5 && i!= 6  ) {
                aPlataforms =  new Arena(90*i + 30, iHeight - 550, 120, 100, this); 
                ArenaFloor.add(aPlataforms); 
           }

        }
        //                                                  //Sets the state to new Game 1 vs 1
        state = STATE.newGame1v1;
        //                                                  //Starts music
        battle1.play();
        battle1.setLooping(true);
        menumusic.stop();
        //                                                  //generates random number
        int aux = getIntRandom(r);
        System.out.print(aux);
        //                                                  //sets the game in 1 vs 1
        state = STATE.Game1v1;
        return aux;
    }
 
    /**
     *          SetStateNewGame2v2
     * 
     * Creates a new game with the game mode 2 vs 2
     * @param r random object
     * @return random integer
     */
    public int setStateNewGame2v2(Random r){
        
        //                                                  //I believe this can be done with one for
        //                                                  //Creates a List with the players with one team
        for(int i = 0; i < 2; i++){
            Player player = new Player((getiWidth() /2 ) - 100, 
            (getiHeight() / 2) - 100 * i, 100, 100, this, i, GMPControllers.get(i), 1,3);
                arrPlayers.add(player);  
        }
        
        //                                                  //Creates a List with the players of the other team
        for(int i = 2; i < 4; i++){
            Player player = new Player((getiWidth() /2 ) - 100, 
            (getiHeight() / 2) - 100 * i, 100, 100, this, i, GMPControllers.get(i)
                    ,2,3);
            arrPlayers.add(player);
        }
        
        //                                                  //sets the players I think it does what the constructor did, 
        //                                                  //      but arranges the positions
        Iterator itr;
        itr = arrPlayers.iterator();
        Player paux = (Player) itr.next();
        paux.setiX(200);
        paux.setiY(826);
        paux.setLives(3);
        paux = (Player) itr.next();
        paux.setiX(700);
        paux.setiY(826);
        paux.setLives(3);
        
        if(iNumPlayers >= 3){
            paux = (Player) itr.next();
            paux.setiX(700);
            paux.setiY(100);
            paux.setLives(3);
        }
        if(iNumPlayers == 4){
            paux = (Player) itr.next();
            paux.setiX(200);
            paux.setiY(100);
            paux.setLives(3);
        }
        
        //                                                  //Creates the plataforms, this can be done in a single 
        //                                                  //      method in the future
        for(int i = 0; i < (getiWidth()/100); i++) {
           
            aPlataforms =  new Arena(90*i + 30, iHeight - 70, 120, 100, this); 
            ArenaFloor.add(aPlataforms); 
            
            if(i != 3 && i != 4 && i!= 5 ) {
                aPlataforms =  new Arena(90*i + 30, iHeight - 190, 120, 100, this); 
                ArenaFloor.add(aPlataforms); 
           }
            
           if(i != 1 && i != 7) {
                aPlataforms =  new Arena(90*i + 30, iHeight - 310, 120, 100, this); 
                ArenaFloor.add(aPlataforms); 
           }
           
           if(i != 3 && i != 4 && i!= 5 ) {
                aPlataforms =  new Arena(90*i + 30, iHeight - 430, 120, 100, this); 
                ArenaFloor.add(aPlataforms); 
           }
           
           if(i != 2 && i!= 3 && i!= 4 && i!= 5 && i!= 6  ) {
                aPlataforms =  new Arena(90*i + 30, iHeight - 430, 120, 100, this); 
                ArenaFloor.add(aPlataforms); 
           }
           
           if(i != 2 && i!= 3 && i!= 4 && i!= 5 && i!= 6  ) {
                aPlataforms =  new Arena(90*i + 30, iHeight - 550, 120, 100, this); 
                ArenaFloor.add(aPlataforms); 
           }
           

        }
        
        //                                                  //sets the state to creating Game with game mode 2 vs 2 
        state = STATE.newGame2v2;
        //                                                  //Starts music
        battle1.play();
        battle1.setLooping(true);
        menumusic.stop();
        //                                                  //Generates random number
        int aux = getIntRandom(r);
        System.out.print(aux);
        //                                                  //sets the state to game mode 2 vs 2 
        state = STATE.Game2v2;
        //                                                  //sets the deaths of the teams to 0 
        int[] LivesTeam = {0, 0, 0};
        return aux;
    }

    /**
     *              SetStateNewGameFFA
     * 
     * Creates a new game with the game mode Free For All
     * @param r random object
     * @return random integer
     */    
    public int setStateNewGameFFA(Random r){
    
        //                                                  //Creates list with the players
        for(int i = 0; i < iNumPlayers; i++){ 
            Player player = new Player((getiWidth() /2 ) - 100, 
            (getiHeight() / 2) - 100 * i, 100, 100, this, i, GMPControllers.get(i), 0,3);
                arrPlayers.add(player);  
        }
        
        //                                                  //sets the players I think it does what the constructor did, 
        //                                                  //      but arranges the positions
        Iterator itr;
        itr = arrPlayers.iterator();
        Player paux = (Player) itr.next();
        paux.setiX(200);
        paux.setiY(826);
        paux.setLives(3);
        if(iNumPlayers >=2){
            paux = (Player) itr.next();
            paux.setiX(700);
            paux.setiY(826);
            paux.setLives(3);
        }
        
        if(iNumPlayers >= 3){
            paux = (Player) itr.next();
            paux.setiX(700);
            paux.setiY(100);
            paux.setLives(3);
        }
        
        if(iNumPlayers >= 4){
            paux = (Player) itr.next();
            paux.setiX(200);
            paux.setiY(100);
            paux.setLives(3);
        }
        
        //                                                  //Creates the plataforms, this can be done in a single 
        //                                                  //      method in the future
        for(int i = 0; i < (getiWidth()/100); i++) {
           
            aPlataforms =  new Arena(90*i + 30, iHeight - 70, 120, 100, this); 
            ArenaFloor.add(aPlataforms); 
            
            if(i != 3 && i != 4 && i!= 5 ) {
                aPlataforms =  new Arena(90*i + 30, iHeight - 190, 120, 100, this); 
                ArenaFloor.add(aPlataforms); 
           }
            
           if(i != 1 && i != 7) {
                aPlataforms =  new Arena(90*i + 30, iHeight - 310, 120, 100, this); 
                ArenaFloor.add(aPlataforms); 
           }
           
           if(i != 3 && i != 4 && i!= 5 ) {
                aPlataforms =  new Arena(90*i + 30, iHeight - 430, 120, 100, this); 
                ArenaFloor.add(aPlataforms); 
           }
           
           if(i != 2 && i!= 3 && i!= 4 && i!= 5 && i!= 6  ) {
                aPlataforms =  new Arena(90*i + 30, iHeight - 430, 120, 100, this); 
                ArenaFloor.add(aPlataforms); 
           }
           
           if(i != 2 && i!= 3 && i!= 4 && i!= 5 && i!= 6  ) {
                aPlataforms =  new Arena(90*i + 30, iHeight - 550, 120, 100, this); 
                ArenaFloor.add(aPlataforms); 
           }
           

        }
        
        //                                                  //Sets the state to new Game Free For ALL
        state = STATE.newGameFFA;
        //                                                  //Starts Music
        battle1.play();
        battle1.setLooping(true);
        menumusic.stop();
        //                                                  //generates random integer
        int aux = getIntRandom(r);
        System.out.print(aux);
        //                                                  //Sets the state to Game with game mode Free For ALL 
        state = STATE.GameFFA;
        return aux;
    }

    /**
     *          SetStateGame
     * 
     * sets the state of the game
     * 
     */    
    public void setStateGame(){
        state = STATE.GAME;
    }
    
    /**
     *          setStateMenu
     * 
     * sets the state of the game to menu
     *      
     */    
    public void setStateMenu(){
        state = STATE.MENU;
        setPointerx(250);
        setPointerx2(600);
        setPointery1(435);
        setPointery2(435);

    }

    /**
     *          SetStateModeSelection
     * 
     * sets the state to the mode selection state
     * 
     */    
    public void setStateModeSelection(){
        state = STATE.ModeSelection;
        setPointery1(400);
        setPointery2(400);
        setPointerx(290);
        setPointerx2(605);
    }

    /**
     *          renderBG
     * 
     * Renders the animation for the background depending on the random number
     * NOTE: the random variable should be received 
     * 
     */    
    public void renderBG(){
        if(random == 0){
            
            gGraphics.drawImage(fightanimation.getCurrentFrame(), 0, 0, getiWidth(), getiHeight(),null);
            
        }else if (random == 1){
            
            gGraphics.drawImage(fightanimation2.getCurrentFrame(), 0, 0, getiWidth(), getiHeight(), null);  
            
        }else if ( random == 2){
            
            gGraphics.drawImage(fightanimation3.getCurrentFrame(),0,0, getiWidth(), getiHeight(), null);
            
        }
    }
    
   /**
    *           renderPlayer
    * 
    * we render all the players
    * 
    */    
    public void renderPlayer(){
        Iterator itr = arrPlayers.iterator();
        while (itr.hasNext()) {
            Player playeraux = (Player) itr.next();
            playeraux.render(gGraphics);
        }   
    }

   /**
    *           SetStateSettings
    * 
    * sets the state of the game to settings
    * 
    */    
    public void setStateSettings(){
        state = STATE.Settings;
    }

   /**
    *           setPointerX
    * 
    * sets the position in x for the pointer used in the menu 
    * 
    * @param iaux 
    */    
    public void setPointerx(int iaux){
        iPointerx = iaux;
    }
    
    /**
     *          setPointerX2
     * 
     * sets the position in x for the second pointher in the menu
     * 
     * @param iaux 
     */    
    public void setPointerx2(int iaux){
        iPointerx2 = iaux;
    }
 
    /**
     *          getPointerx1
     * 
     * Getter for the position in x for the first pointer
     * 
     * @return <code>int<code> with the value of x for the first pointer 
     */
    public int getPointerx1(){
        return iPointerx;
    }
    
    /**
     * mover el apuntador 1 del menu hacia abajo
     * @param iaux 
     */
    public void setPointery1Down(int iaux){
        iPointery += iaux;
    }
    
    /**
     * mover el apuntador 2 del menu hacia abajo
     * @param iaux 
     */
    public void setPointery2Down(int iaux){
        iPointery2 += iaux;
    }
    
    /**
     * acomodar el apuntador 1
     * @param aux 
     */
    public void setPointery1(int aux){
        iPointery = aux;
    }
    
    /**
     * acomodar el apuntador 2
     * @param aux 
     */
    public void setPointery2(int aux){
        iPointery2 = aux;
    }
    
    /**
     * mover el apuntador 2 hacia arriba
     * @param iaux 
     */
    public void setPointery2Up(int iaux){
        iPointery2 -= iaux;
    }
    
    /**
     * mover el apuntador 1 hacia arriba
     * @param iaux 
     */
    public void setPointery1Up(int iaux){
        iPointery -= iaux;
    }
    
    /**
     * conseguir la poscion del apuntador en y
     * @return <code>int</code> con el valor de la posicion en y
     */
    public int getPointery(){
        return iPointery;
    }
    
    
    /**
     * getcontrollers 
     * 
     * @return obtener una lista con todos los GamePads
     */
    public ArrayList<GamePadController> getGMPControllers() {
        return GMPControllers;
    }
    
    /**
     * getiWidth
     * 
     * para obtener la anchura de la pantalla
     * 
     * @return <code>int</code> con el valor de la anchura
     */
    public int getiWidth() {
        return iWidth;
    }
    
    /**
     *getiHeight
     * 
     * para obtener la altura de la pantalla
     * 
     * @return <code>int</code> con el valor de la altura
     */
    public int getiHeight() {
        return iHeight;
    }
    
    /**
     * getiNumPlayers
     * 
     * @return para conseguir el numero de jugadores
     */
    public int getiNumPlayers() {
        return iNumPlayers;
    }
    
    /**
     * getPlayers
     * 
     * @return consigue los players
     */
    public ArrayList<Player> getarrPlayers() {
        return arrPlayers;
    }
    
    /**
     * getaArena
     * 
     * @return regresa la arena
     */
    public Arena getaPlataforms() {
        return aPlataforms;
    }
    
    /**
     * init
     * 
     * inicializa el display de la ventana del juego 
     */
    private void init(){
        
        dispDisplay = new Display(sTitle, iWidth, iHeight);     //se crea el display
        Assets.init();
        r = new Random();
        //inicializa los assets 
        GMPControllers = new ArrayList<GamePadController>();
        animationBG = new Animation(Assets.imgBackground, 120);
        fightanimation = new Animation(Assets.imgFightBG, 120);
        fightanimation2 = new Animation(Assets.imgFightBG2,60);
        fightanimation3 = new Animation(Assets.imgFightBG3,60);
        menumusic = Assets.menumusic;
        navigate = Assets.navigate;
        select = Assets.select;
        selectBack = Assets.selectBack;
        start = Assets.start;
        battle1 = Assets.battle1;
        for(int i = 0; i < iNumPlayers; i++){ //inserta todos los controles necesairos
            GamePadController Controller = new GamePadController(i);
            GMPControllers.add(Controller);
        }
        arrPlayers = new ArrayList<Player>();
        ArenaFloor = new ArrayList<Arena>(); 
        
        setStateStart();
    }
    
    /**
     * run
     * 
     * funcion que controla los frames por segundo del juego 
     */
    @Override
    public void run(){
        
        init();
        int fps = 60;                       //frames por segundo
        double TimeTick = 1000000000 / fps; //tiempo por cada tick en nanosegundos
        double delta = 0;                   //inicializando el ultimo tiempo 
        long Now;                           //definimos now para utilizarlo dentro del ciclo
        long LastTime = System.nanoTime();  //inicializando el ultimo tiempo de la computadora
        
        while(bRunning){
            
            Now = System.nanoTime();        //poner el tiempo de ahora en el tiempo actual
            delta += (Now - LastTime) / TimeTick; //acomulando en delta la diferencia de tiempo
            LastTime = Now;                 //actualizando el ultimo tiempo
            
            if(delta >= 1){                 //si delta es positivo, tickeamos el juego
                tick(state);
                render();
                delta--;
            }
        }
        stop();
    }
    
    /**
     * tick
     * 
     * funcion que tiene las instrucciones de como los objetos van a comportarse
     * en cada frame
     */
    private void tick(STATE state){
        // hace el poll de todos los controlles
        Iterator itr = GMPControllers.iterator();
        while(itr.hasNext()){
            GamePadController Controller = (GamePadController) itr.next();
            Controller.poll();
        }
        
        if(state == STATE.Game1v1){
            fightanimation.tick();
            fightanimation2.tick();
            fightanimation3.tick();
            //para checar cada jugador
            itr = GMPControllers.iterator();
            checkVictory();
            while(itr.hasNext()){
                GamePadController Controller = (GamePadController) itr.next();
                if(Controller.isButtonPressed(Controller.getButtonStart())){
                    setStatePause();
                    try{
                        Thread.sleep(200);
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
            itr = arrPlayers.iterator();
            while (itr.hasNext()) {
                Player playeraux = (Player) itr.next();
                playeraux.tick();
                //para checar si se estan atacando
                Iterator itr2 = arrPlayers.iterator();
                while(itr2.hasNext()){

                    Player player2 = (Player) itr2.next(); 
                    //interseccion entre objetos
                    if(playeraux != player2 && playeraux.intersects(player2) 
                            && playeraux.isAttack()){
                        player2.setDireccionEnemigo(playeraux.getDirection());
                        player2.setHit(true);
                        player2.setSalud(player2.getSalud() + 1);
                    }
                    //se muere
                    if(player2.getSalud() > 93){
                        player2.setHit(true);
                    }
                    
                }
            }
        }
        
        if(state == STATE.GameFFA){
            fightanimation.tick();
            fightanimation2.tick();
            fightanimation3.tick();
            checkVictory();
            itr = GMPControllers.iterator();
            while(itr.hasNext()){
                GamePadController Controller = (GamePadController) itr.next();
                if(Controller.isButtonPressed(Controller.getButtonStart())){
                    setStatePause();
                    try{
                        Thread.sleep(200);
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
            itr = arrPlayers.iterator();
            while (itr.hasNext()) {
                Player playeraux = (Player) itr.next();
                playeraux.tick();
                //para checar si se estan atacando
                Iterator itr2 = arrPlayers.iterator();
                while(itr2.hasNext()){

                    Player player2 = (Player) itr2.next(); 
                    //interseccion entre objetos
                    if(playeraux != player2 && playeraux.intersects(player2) 
                            && playeraux.isAttack()){
                        player2.setDireccionEnemigo(playeraux.getDirection());
                        player2.setHit(true);
                        player2.setSalud(player2.getSalud() + 1);
                    }
                    //se muere
                    if(player2.getSalud() > 93){
                        player2.setHit(true);
                    }
                }
            }
        }
        
        if(state == STATE.Game2v2){
            fightanimation.tick();
            fightanimation2.tick();
            fightanimation3.tick();
            checkVictory();
            itr = GMPControllers.iterator();
            while(itr.hasNext()){
                GamePadController Controller = (GamePadController) itr.next();
                if(Controller.isButtonPressed(Controller.getButtonStart())){
                    setStatePause();
                    try{
                        Thread.sleep(200);
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
            itr = arrPlayers.iterator();
            while (itr.hasNext()) {
                Player playeraux = (Player) itr.next();
                playeraux.tick();
                //para checar si se estan atacando
                Iterator itr2 = arrPlayers.iterator();
                while(itr2.hasNext()){

                    Player player2 = (Player) itr2.next(); 
                    //interseccion entre objetos
                    if(playeraux != player2 && playeraux.intersects(player2) 
                            && playeraux.isAttack() && playeraux.getTeam() 
                            != player2.getTeam()){
                        player2.setDireccionEnemigo(playeraux.getDirection());
                        player2.setHit(true);
                        player2.setSalud(player2.getSalud() + 1);
                    }
                    //se muere
                    if(player2.getSalud() > 93){
                        player2.setHit(true);
                    }

                }
            }
        }
        if(state == STATE.Pause){
            itr = GMPControllers.iterator();
            while(itr.hasNext()){
                GamePadController Controller = (GamePadController) itr.next();
                if(Controller.isButtonPressed(Controller.getButtonStart())){
                    QuitPause();
                    try{
                        Thread.sleep(200);
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }
                }
                if(Controller.isButtonPressed(Controller.getButtonA())){
                    if(getPointery() == 305){
                        QuitPause();
                    }
                    if(getPointery() == 365){
                        setStateMenu();
                        battle1.stop();
                        menumusic.play();
                        arrPlayers.clear();   
                    }
                    try{
                        Thread.sleep(200);
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }
                }
                if(Controller.getLXYDir() == Controller.getSOUTH()){
                    setPointery1Down(60);
                    setPointery2Down(60);
                    if(getPointery() > 365){
                       setPointery1(305);
                       setPointery2(305);
                    }
                    try{
                        Thread.sleep(200);
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }
                }
                if(Controller.getLXYDir() == Controller.getNORTH()){
                    setPointery1Up(60);
                    setPointery2Up(60);
                    if(getPointery() < 305){
                       setPointery1(365);
                       setPointery2(365);
                    }
                    try{
                        Thread.sleep(200);
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }  
        }
        if(state == STATE.Start){
            animationBG.tick();
            itr = GMPControllers.iterator();
            while(itr.hasNext()){
                GamePadController Controller = (GamePadController) itr.next();
                //para seleccionar
                if(Controller.isButtonPressed(Controller.getButtonStart())){
                    Assets.start.play();
                    setStateMenu();
                    try{
                        Thread.sleep(200);
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }
                    
                }
            }
        }
        if(state == STATE.Settings){
            itr = GMPControllers.iterator();
            animationBG.tick();
            while(itr.hasNext()){
            GamePadController Controller = (GamePadController) itr.next();
            if(Controller.isButtonPressed(Controller.getButtonB())){
                Assets.selectBack.play();
                setStateMenu();
           }
         }
            
        }
        if(state == STATE.ModeSelection){
            itr = GMPControllers.iterator();
            animationBG.tick();
            while(itr.hasNext()){
                GamePadController Controller = (GamePadController) itr.next();
                if(Controller.isButtonPressed(Controller.getButtonB())){
                    Assets.selectBack.play();
                    setStateMenu();
                    
                }
                if(Controller.getLXYDir() == Controller.getSOUTH()){
                    setPointery1Down(60);
                    setPointery2Down(60);
                    Assets.navigate.play();
                    if(getPointery() > 530){
                        setPointery1(400);
                        setPointery2(400);
                    }
                    try{
                        Thread.sleep(200);
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }
                }
                if(Controller.getLXYDir() == Controller.getNORTH()){
                    setPointery1Up(60);
                    setPointery2Up(60);
                    Assets.navigate.play();
                    if(getPointery() < 390){
                        setPointery1(520);
                        setPointery2(520);
                    }
                    try{
                        Thread.sleep(200);
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }
                }
                if(Controller.isButtonPressed(Controller.getButtonA())){
                    Assets.select.play();
                    if(getPointery() == 400){
                        random = setStateNewGameFFA(r);
                    }
                    if(getPointery() == 460){
                        random = setStateNewGame1v1(r);
                    }
                    if(getPointery() == 520){
                        random = setStateNewGame2v2(r);
                    }
                    try{
                        Thread.sleep(200);
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }
                }

          }
        }
        if(state == STATE.Victory){
            try{
                Thread.sleep(50);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            itr = GMPControllers.iterator();
            while(itr.hasNext()){
                GamePadController Controller = (GamePadController) itr.next();
                if(Controller.isButtonPressed(Controller.getButtonA())){
                    select.play();
                    if(getPointery() == 475){
                        setStateMenu();
                        arrPlayers.clear();
                    }
                    if(getPointery() == 555){
                        System.exit(0);
                    }
                }
                if(Controller.getLXYDir() == Controller.getNORTH()){
                    setPointery1Up(80);
                    setPointery2Up(80);
                    if(getPointery() < 400){
                        setPointery1(555);
                        setPointery2(555);
                        
                    }
                    try{
                        Thread.sleep(50);
                    } catch (InterruptedException e){}
                    
                }
                if(Controller.getLXYDir() == Controller.getSOUTH()){
                    setPointery1Down(80);
                    setPointery2Down(80);
                    
                    if(getPointery() > 550){
                        setPointery1(475);
                        setPointery2(475);
                    }
                    try{
                        Thread.sleep(50);
                    } catch (InterruptedException e){}
             
                }
            }
            
        }
        
        if(state == STATE.MENU){
            animationBG.tick();
            itr = GMPControllers.iterator();
            while(itr.hasNext()){
                GamePadController Controller = (GamePadController) itr.next();
                if(Controller.isButtonPressed(Controller.getButtonA())){
                    Assets.select.play();
                    if(getPointery() == 435){
                        setStateModeSelection();
                    }
                    if(getPointery() == 475){
                        setStateSettings();
                    }
                    if(getPointery() == 515){
                        System.exit(0);
                    }     
                    try{
                        Thread.sleep(200);
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }
                }
                if(Controller.getLXYDir() == Controller.getSOUTH()){
                    setPointery1Down(40);
                    setPointery2Down(40);
                    Assets.navigate.play();
                    if(getPointery() > 515){
                        setPointery1(435);
                        setPointery2(435);
                    }
                    try{
                        Thread.sleep(200);
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }
                }
                if(Controller.getLXYDir() == Controller.getNORTH()){
                    setPointery1Up(40);
                    setPointery2Up(40);
                    Assets.navigate.play();
                    if(getPointery() < 435){
                        setPointery1(515);
                        setPointery2(515);
                    }
                    try{
                        Thread.sleep(200);
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    
    /**
     * render
     * 
     * funcion que dibuja los objetos necesarios
     */
    private void render(){
       //Get the buffer strategy from the display 
        bsBuffer = dispDisplay.getCanvas().getBufferStrategy();

        /*
        if it is null, we define one with 3 buffers to display images of the game, 
        if not null, then we display every image of the game but
        after clearing the Rectangle, getting the graphic object from the
        buffer strategy element.
        Show the graphic and dispose it to the trash system
         */

        if (bsBuffer == null){
            dispDisplay.getCanvas().createBufferStrategy(3);
        }else{
            gGraphics = bsBuffer.getDrawGraphics();
            
            //render de la pantalla de start del juego
            if(state == STATE.Start){
                
                gGraphics.drawImage(animationBG.getCurrentFrame(), 0, 
                0, getiWidth(), getiHeight(),null);
                gGraphics.drawImage(Assets.imgLogo, 0, 0, 900, 300,null);
                gGraphics.drawImage(Assets.imgStart, 325, 300, 300,300,null);
                
            }
            
            //render de la pantalla de menu del juego
            if(state == STATE.MENU){
                gGraphics.drawImage(animationBG.getCurrentFrame(), 0, 
                0, getiWidth(), getiHeight(),null);
                gGraphics.drawImage(Assets.imgLogo, 0, 0, 900, 300,null);
                gGraphics.drawImage(Assets.imgNewGame, 200, 310, 500, 300, null);
                gGraphics.drawImage(Assets.Settings, 200, 340, 500, 320, null);
                gGraphics.drawImage(Assets.imgquit,260,415,400,250,null);
                gGraphics.drawImage(Assets.imgPointerIzq, iPointerx, iPointery, 
                        50,50,null);
                gGraphics.drawImage(Assets.imgPointerDer, iPointerx2,iPointery2, 
                        50,50,null);     
            }
            
            //render de la pantalla de seleccion de modo de juego
            if(state == STATE.ModeSelection){
                gGraphics.drawImage(animationBG.getCurrentFrame(), 0, 
                0, getiWidth(), getiHeight(),null);
                gGraphics.drawImage(Assets.imgLogo, 0, 0, 900, 300,null);
                gGraphics.drawImage(Assets.imgffa,275, 275, 400,300,null);
                gGraphics.drawImage(Assets.img1v1, 270, 335, 400, 300, null);
                gGraphics.drawImage(Assets.img2v2, 270, 395, 400,300,null);
                gGraphics.drawImage(Assets.imgPointerIzq, iPointerx, iPointery, 50, 50, null);
                gGraphics.drawImage(Assets.imgPointerDer, iPointerx2, iPointery2, 50, 50, null);
            }
            
            //render de la pantalla de settings del juego
            if(state == STATE.Settings){
                gGraphics.drawImage(Assets.imgSettings,0,0,iWidth,iHeight,null);
            }
            
            //render de la pantalla pausa dentro del juego
            if(state == STATE.Pause){
                gGraphics.drawImage(Assets.imgPause, -72, -35, iWidth + 100, 
                        iHeight + 100, null);
                gGraphics.drawImage(Assets.imgPointerDer, iPointerx, iPointery,
                        50,50,null);
                gGraphics.drawImage(Assets.imgPointerIzq,iPointerx2, iPointery,
                        50,50,null);  
            }
            
            //Renders del background dentro del juego y de los players
            if( state == STATE.Game1v1 || state == STATE.Game2v2 || 
                    state == STATE.GameFFA){
                renderBG();
                //dibujar las plataformas
                Iterator itr2 = ArenaFloor.iterator(); 
                while(itr2.hasNext()) {
                    Arena faux = (Arena) itr2.next(); 
                    faux.render(gGraphics); 
                }
                renderPlayer();
                

            }
            //renderiar las cabezas de los jugadores
            
            
            if(state == STATE.Victory){
                gGraphics.drawImage(Assets.imgVictoria, -72, -35, iWidth + 100, 
                        iHeight + 100, null);
                gGraphics.drawImage(Assets.imgwinnerPointerDer, iPointerx, iPointery,
                        50,50,null);
                gGraphics.drawImage(Assets.imgwinnerPointerIzq, iPointerx2,iPointery2,
                        50,50,null);

            }
            bsBuffer.show();
            gGraphics.dispose();
        }
    }
    
    /**
     * start
     * 
     * empezando el thread del juego
     */
    public synchronized void start(){
        
        if(!bRunning){
            bRunning = true;
            thrThread = new Thread(this);
            thrThread.start();
        }
    }
    
    /**
     * stop
     * 
     * funcion para dejar de correr el juego
     */
    public synchronized void stop(){
        
        if(bRunning){
            bRunning = false;
            try{
               thrThread.join();
            } catch (InterruptedException ie){
                ie.printStackTrace();
            }
        }
    }
}