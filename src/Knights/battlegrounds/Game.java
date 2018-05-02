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
 * class Game
 * 
 * Esta clase es la que se encarga de construir y envuelve el juego 
 * esta clase llama a todas las demas clases necesarias
 * 
 * @author Angel Odiel Treviño Villanueva A01336559 
 * pongan sus nombres y matriculas
 */
public class Game implements Runnable{
    
    private BufferStrategy bsBuffer; //para tener diferentes buffers cuando desplegamos
    private Graphics gGraphics;         //para pintar los objetos
    private Display dispDisplay;        //para desplegar el juego
    private KeyManager KeyManager;    //para utilizar el teclado 
    
    String sTitle;                      //titulo de la ventana
    private int iWidth;                 //anchura de la ventana
    private int iHeight;                //altura de la ventana
    private int iNumPlayers;               //numero de jugadores
    
    private Thread thrThread;           //crear el thread para el juego
    private boolean bRunning;           //para saber si el juego esta corriendo
    
    private ArrayList<Player> Players;   //para poder utilizar mas de un jugador
    private ArrayList<GamePadController> Controllers; //varios controles
    private Arena aArena;               //para crear una arena
    
    private int iPointery = 435;    //valor incial en y del apuntador 1 del menu
    private int iPointerx = 250;    //valor incial en x del apuntador 1 del menu
    
    private int iPointery2 = 435;   //valor incial en y del apuntador 2 del menu
    private int iPointerx2 = 600;   //valor incial en x del apuntador 2 del menu
    
    private Animation animationBG;  //animacion del background del menu
    private Animation fightanimation; //animacion del mapa de pelea 1
    private Animation fightanimation2; //animacion del mapa de pelea 2
    private Animation fightanimation3; //animacion del mapa de pelea 3
    private SoundClip menumusic;      //para guardar la musica del menu
    private SoundClip navigate;  //para guardar el sonido de navegacion del menu
    private SoundClip battle1;   //para guardar la musica de batalla
    public STATE state, LastState; //estado actual y ultimo estado guardado
    private SoundClip select;    //para guardar sonido de seleccion
    private SoundClip selectBack; //para guardar el sonido de retroceso del menu
    private SoundClip start; //para el sonido de start en la pantalla de inicio
    private Random r; // numero aleatorio para escoger mapa de pelea
    private int random; // para guardar el valor aleatorio
    private ArrayList<Arena> ArenaFloor;
    
    /**
     * Constructor de Game
     * 
     * crea la ventana del juego e indica que el juego todavia no esta corriendo
     * 
     * @param sTitle indica el titulo de la ventana
     * @param iWidth indica la anchura de la ventana
     * @param iHeight indica la altura de la ventana
     * @param iNumPlayers indica el numero de jugadres que jugaran
     */
    public Game(String sTitle, int iWidth, int iHeight, int iNumPlayers){
        
        this.sTitle = sTitle;
        this.iWidth = iWidth;
        this.iHeight = iHeight;
        this.iNumPlayers = iNumPlayers;
        bRunning = false; 
        KeyManager = new KeyManager();
    }
    
    
  
 /**
  * Se enumeran los diferentes estados en los que puede estar el juego
  * Menu: menu del juego, donde aparecen las opciones new game, settings, quit game
  * Start: pantalla de inicio del juego
  * GameFFA: estado en donde se esta ejecutando el modo todos contra todos
  * Game2v2: estado en donde se esta ejecutando el modo 2 contra 2
  * Game1v1: estado en donde se esta ejecutando el modo 1 contra 1 
  * Settings: estado donde se muestran los controles del juego
  * ModeSelection: estado en donde se selecciona el modo de juego
  * Pause: estado de pausa dentro de los modos de juego
  * newGame1v1: crear un nuevo juego 1 contra 1
  * newGame2v2: crear un nuevo juego 2 contra 2
  * newGameFFA: crear un nuevo juego todos contra todos
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
     * Pone al juego en estado de pausa y guarda su ultimo estado
     */
    public void setStatePause(){
        LastState = state;
        state = STATE.Pause;
        setPointerx(505);
        setPointery1(305);
        setPointerx2(295);
        setPointery2(305);
        
    }
    
    public void setStateVictory(){
        setPointerx(275);
        setPointery1(475);
        setPointerx2(500);
        setPointery2(475);
        state = STATE.Victory;
    }
    
    public void checkVictory(){
        if(state == STATE.Game1v1){
            Iterator itr = Players.iterator();
            while(itr.hasNext()){
                Player playeraux = (Player) itr.next();
                if(playeraux.getLives() == 0){
                    setStateVictory();
                }    
            }
        }
        if(state == STATE.Game2v2){
            
        }
        if(state == STATE.GameFFA){
            
        }
    }

    public void setStateStart(){
        state = STATE.Start;
        Assets.menumusic.setLooping(true);
        Assets.menumusic.play();
    }
    
    /**
     * QuitarPausa
     */
    public void QuitPause(){
    state = LastState;
}

    /**
     * Genera un numero aleatorio entre 0 y 2 y lo regresa
     * @param r
     * @return 
     */    
    public int getIntRandom(Random r){
        int aux = r.nextInt(3-0) + 0;
        return aux;
    }
    
    /**
     * Crea un nuevo juego 1v1 y regresa un numero aleatorio
     * @param r
     * @return 
     */    
    public int setStateNewGame1v1(Random r){
        for(int i = 0; i < 2; i++){ //solo crea una lista con los juadores
        Player player = new Player((getiWidth() /2 ) - 100, 
        (getiHeight() / 2) - 100 * i, 100, 100, this, i, Controllers.get(i), 0,3);
            Players.add(player);  
        }
        Iterator itr;
        itr = Players.iterator();
        Player paux = (Player) itr.next();
        paux.setiX(200);
        paux.setiY(826);
        paux.setLives(3);
        paux = (Player) itr.next();
        paux.setiX(700);
        paux.setiY(100);
        paux.setLives(3);
         
        for(int i = 0; i < (getiWidth()/100); i++) {
           
            aArena =  new Arena(90*i + 30, iHeight - 70, 120, 100, this); 
            ArenaFloor.add(aArena); 
            
            if(i != 3 && i != 4 && i!= 5 ) {
                aArena =  new Arena(90*i + 30, iHeight - 190, 120, 100, this); 
                ArenaFloor.add(aArena); 
           }
            
           if(i != 1 && i != 7) {
                aArena =  new Arena(90*i + 30, iHeight - 310, 120, 100, this); 
                ArenaFloor.add(aArena); 
           }
           
           if(i != 3 && i != 4 && i!= 5 ) {
                aArena =  new Arena(90*i + 30, iHeight - 430, 120, 100, this); 
                ArenaFloor.add(aArena); 
           }
           
           if(i != 2 && i!= 3 && i!= 4 && i!= 5 && i!= 6  ) {
                aArena =  new Arena(90*i + 30, iHeight - 430, 120, 100, this); 
                ArenaFloor.add(aArena); 
           }
           
           if(i != 2 && i!= 3 && i!= 4 && i!= 5 && i!= 6  ) {
                aArena =  new Arena(90*i + 30, iHeight - 550, 120, 100, this); 
                ArenaFloor.add(aArena); 
           }
           
            if(i != 0 && i!= 1 && i!= 7 && i!= 8 && i != 2 && i != 6 ) {
                aArena =  new Arena(90*i + 30, iHeight - 600, 120, 100, this); 
                ArenaFloor.add(aArena); 
           }
        }
        state = STATE.newGame1v1;
        battle1.play();
        battle1.setLooping(true);
        menumusic.stop();
        int aux = getIntRandom(r);
        System.out.print(aux);
        state = STATE.Game1v1;
        return aux;
    }
 
    /**
     * Crea un nuevo juego 2v2 y regresa un numero aleatorio
     * @param r
     * @return 
     */
    public int setStateNewGame2v2(Random r){
         for(int i = 0; i < 2; i++){ //solo crea una lista con los juadores
        Player player = new Player((getiWidth() /2 ) - 100, 
        (getiHeight() / 2) - 100 * i, 100, 100, this, i, Controllers.get(i), 1,3);
            Players.add(player);  
        }
         
          for(int i = 2; i < 4; i++){ //solo crea una lista con los juadores
              Player player = new Player((getiWidth() /2 ) - 100, 
              (getiHeight() / 2) - 100 * i, 100, 100, this, i, Controllers.get(i)
                      ,2,3);
              Players.add(player);
        }

        Iterator itr;
        itr = Players.iterator();
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
        for(int i = 0; i < (getiWidth()/100); i++) {
           
            aArena =  new Arena(90*i + 30, iHeight - 70, 120, 100, this); 
            ArenaFloor.add(aArena); 
            
            if(i != 3 && i != 4 && i!= 5 ) {
                aArena =  new Arena(90*i + 30, iHeight - 190, 120, 100, this); 
                ArenaFloor.add(aArena); 
           }
            
           if(i != 1 && i != 7) {
                aArena =  new Arena(90*i + 30, iHeight - 310, 120, 100, this); 
                ArenaFloor.add(aArena); 
           }
           
           if(i != 3 && i != 4 && i!= 5 ) {
                aArena =  new Arena(90*i + 30, iHeight - 430, 120, 100, this); 
                ArenaFloor.add(aArena); 
           }
           
           if(i != 2 && i!= 3 && i!= 4 && i!= 5 && i!= 6  ) {
                aArena =  new Arena(90*i + 30, iHeight - 430, 120, 100, this); 
                ArenaFloor.add(aArena); 
           }
           
           if(i != 2 && i!= 3 && i!= 4 && i!= 5 && i!= 6  ) {
                aArena =  new Arena(90*i + 30, iHeight - 550, 120, 100, this); 
                ArenaFloor.add(aArena); 
           }
           
            if(i != 0 && i!= 1 && i!= 7 && i!= 8 && i != 2 && i != 6 ) {
                aArena =  new Arena(90*i + 30, iHeight - 600, 120, 100, this); 
                ArenaFloor.add(aArena); 
           }
        }
        state = STATE.newGame2v2;
        battle1.play();
        battle1.setLooping(true);
        menumusic.stop();
        int aux = getIntRandom(r);
        System.out.print(aux);
        state = STATE.Game2v2;
        return aux;
    }

    /**
     * Crea un nuevo juego todos contra todos y regresa un numero aleatorio
     * @param r
     * @return 
     */    
    public int setStateNewGameFFA(Random r){
    for(int i = 0; i < iNumPlayers; i++){ //solo crea una lista con los juadores
        Player player = new Player((getiWidth() /2 ) - 100, 
        (getiHeight() / 2) - 100 * i, 100, 100, this, i, Controllers.get(i), 0,3);
            Players.add(player);  
        }
        Iterator itr;
        itr = Players.iterator();
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
        if(iNumPlayers >= 4){
            paux = (Player) itr.next();
            paux.setiX(200);
            paux.setiY(100);
            paux.setLives(3);
        }
        for(int i = 0; i < (getiWidth()/100); i++) {
           
            aArena =  new Arena(90*i + 30, iHeight - 70, 120, 100, this); 
            ArenaFloor.add(aArena); 
            
            if(i != 3 && i != 4 && i!= 5 ) {
                aArena =  new Arena(90*i + 30, iHeight - 190, 120, 100, this); 
                ArenaFloor.add(aArena); 
           }
            
           if(i != 1 && i != 7) {
                aArena =  new Arena(90*i + 30, iHeight - 310, 120, 100, this); 
                ArenaFloor.add(aArena); 
           }
           
           if(i != 3 && i != 4 && i!= 5 ) {
                aArena =  new Arena(90*i + 30, iHeight - 430, 120, 100, this); 
                ArenaFloor.add(aArena); 
           }
           
           if(i != 2 && i!= 3 && i!= 4 && i!= 5 && i!= 6  ) {
                aArena =  new Arena(90*i + 30, iHeight - 430, 120, 100, this); 
                ArenaFloor.add(aArena); 
           }
           
           if(i != 2 && i!= 3 && i!= 4 && i!= 5 && i!= 6  ) {
                aArena =  new Arena(90*i + 30, iHeight - 550, 120, 100, this); 
                ArenaFloor.add(aArena); 
           }
           
            if(i != 0 && i!= 1 && i!= 7 && i!= 8 && i != 2 && i != 6 ) {
                aArena =  new Arena(90*i + 30, iHeight - 600, 120, 100, this); 
                ArenaFloor.add(aArena); 
           }
        }
        state = STATE.newGameFFA;
        battle1.play();
        battle1.setLooping(true);
        menumusic.stop();
        int aux = getIntRandom(r);
        System.out.print(aux);
        state = STATE.GameFFA;
        return aux;
    }

    /**
     * Pone el estado en juego
     */    
    public void setStateGame(){
        state = STATE.GAME;
    }
    
    /**
     * Pone al juego en estado de menu
     */    
    public void setStateMenu(){
        state = STATE.MENU;
        setPointerx(250);
        setPointerx2(600);
        setPointery1(435);
        setPointery2(435);

    }

    /**
     * pone al juego en estado de seleccion de modo de juego
     */    
    public void setStateModeSelection(){
        state = STATE.ModeSelection;
        setPointery1(400);
        setPointery2(400);
        setPointerx(290);
        setPointerx2(605);
    }

    /**
     * se hace render de la animacion de fondo
     */    
    public void renderBG(){
        if(random == 0){
        gGraphics.drawImage(fightanimation.getCurrentFrame(), 0, 
                 0, getiWidth(), getiHeight(),null);                    
        }else if (random == 1){
         gGraphics.drawImage(fightanimation2.getCurrentFrame(), 0, 0, 
                  getiWidth(), getiHeight(), null);    
        }else if ( random == 2){
         gGraphics.drawImage(fightanimation3.getCurrentFrame(),0,0,
                  getiWidth(), getiHeight(), null);
        }
    }
    
   /**
    * se hace render del player
    */    
    public void renderPlayer(){
         Iterator itr = Players.iterator();
         while (itr.hasNext()) {
         Player playeraux = (Player) itr.next();
         playeraux.render(gGraphics);
       }   
    }

   /**
    * se coloca al juego en estado de settings
    */    
    public void setStateSettings(){
        state = STATE.Settings;
    }

   /**
    * set de de la posicion x del apuntador del menu
    * @param iaux 
    */    
    public void setPointerx(int iaux){
        iPointerx = iaux;
    }
    
    /**
     * set de la posicion x del segundo apuntador del menu
     * @param iaux 
     */    
    public void setPointerx2(int iaux){
        iPointerx2 = iaux;
    }
 
    /**
     * para obtener la poscion del apuntador 1 en x
     * @return <code>int<code> con el valor de la posicion del apuntador 1 en x 
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
    public ArrayList<GamePadController> getControllers() {
        return Controllers;
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
     * getKeyManager
     *
     * @return the Object KeyManager
     */
    public KeyManager getKeyManager(){
        return KeyManager;
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
    public ArrayList<Player> getPlayers() {
        return Players;
    }
    
    /**
     * getaArena
     * 
     * @return regresa la arena
     */
    public Arena getaArena() {
        return aArena;
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
        Controllers = new ArrayList<GamePadController>();
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
            Controllers.add(Controller);
        }
        Players = new ArrayList<Player>();
        ArenaFloor = new ArrayList<Arena>(); 
        
        dispDisplay.getJframe().addKeyListener(KeyManager);
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
        getKeyManager().tick();        //el tick del keymanager
        // hace el poll de todos los controlles
        Iterator itr = Controllers.iterator();
        while(itr.hasNext()){
            GamePadController Controller = (GamePadController) itr.next();
            Controller.poll();
        }
        
        if(state == STATE.Game1v1){
            fightanimation.tick();
            fightanimation2.tick();
            fightanimation3.tick();
            //para checar cada jugador
            itr = Controllers.iterator();
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
            itr = Players.iterator();
            while (itr.hasNext()) {
                Player playeraux = (Player) itr.next();
                playeraux.tick();
                //para checar si se estan atacando
                Iterator itr2 = Players.iterator();
                while(itr2.hasNext()){

                    Player player2 = (Player) itr2.next(); 
                    //interseccion entre objetos
                    if(playeraux != player2 && playeraux.intersects(player2) 
                            && playeraux.isAttack()){
                        player2.setHit(true);
                        player2.setSalud(player2.getSalud() + 1);
                    }
                    //se muere
                    if(player2.getSalud() > 93){
                        player2.setHit(true);
                    }
                    
                    //checar con las plataformas
                    Iterator itr3 = ArenaFloor.iterator();
                    while(itr3.hasNext()) {
                    Arena arenaaux = (Arena)itr3.next(); 
                        if(playeraux.intersects(arenaaux) && playeraux.getiY() == arenaaux.getiY()-10) {
                        
                            playeraux.setiY(arenaaux.getiY()-10);
                            playeraux.setiX(getiWidth()/2); 
               
                        }
                    }   
                }
            }
        }
        
        if(state == STATE.GameFFA){
            fightanimation.tick();
            fightanimation2.tick();
            fightanimation3.tick();
            itr = Controllers.iterator();
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
            itr = Players.iterator();
            while (itr.hasNext()) {
                Player playeraux = (Player) itr.next();
                playeraux.tick();
                //para checar si se estan atacando
                Iterator itr2 = Players.iterator();
                while(itr2.hasNext()){

                    Player player2 = (Player) itr2.next(); 
                    //interseccion entre objetos
                    if(playeraux != player2 && playeraux.intersects(player2) 
                            && playeraux.isAttack()){
                        player2.setHit(true);
                        player2.setSalud(player2.getSalud() + 1);
                    }
                    //se muere
                    if(player2.getSalud() > 93){
                        player2.setHit(true);
                    }
                    
                    //checar con las plataformas
                    Iterator itr3 = ArenaFloor.iterator();
                    while(itr3.hasNext()) {
                    Arena arenaaux = (Arena)itr3.next(); 
                        if(playeraux.intersects(arenaaux) && playeraux.getiY() == arenaaux.getiY()-10) {
                        
                            playeraux.setiY(arenaaux.getiY()-10);
                            playeraux.setiX(getiWidth()/2); 
               
                        }
                    }   

                }
            }
        }
        
        if(state == STATE.Game2v2){
            fightanimation.tick();
            fightanimation2.tick();
            fightanimation3.tick();
            itr = Controllers.iterator();
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
            itr = Players.iterator();
            while (itr.hasNext()) {
                Player playeraux = (Player) itr.next();
                playeraux.tick();
                //para checar si se estan atacando
                Iterator itr2 = Players.iterator();
                while(itr2.hasNext()){

                    Player player2 = (Player) itr2.next(); 
                    //interseccion entre objetos
                    if(playeraux != player2 && playeraux.intersects(player2) 
                            && playeraux.isAttack() && playeraux.getTeam() 
                            != player2.getTeam()){
                        player2.setHit(true);
                        player2.setSalud(player2.getSalud() + 1);
                    }
                    //se muere
                    if(player2.getSalud() > 93){
                        player2.setHit(true);
                    }
                    
                    
                    //checar con las plataformas
                    Iterator itr3 = ArenaFloor.iterator();
                    while(itr3.hasNext()) {
                    Arena arenaaux = (Arena)itr3.next(); 
                        if(playeraux.intersects(arenaaux) && playeraux.getiY() == arenaaux.getiY()-10) {
                        
                            playeraux.setiY(arenaaux.getiY()-10);
                            playeraux.setiX(getiWidth()/2); 
               
                        }
                    }   

                }
            }
        }
        if(state == STATE.Pause){
            itr = Controllers.iterator();
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
                        Players.clear();   
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
            itr = Controllers.iterator();
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
            itr = Controllers.iterator();
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
            itr = Controllers.iterator();
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
            itr = Controllers.iterator();
            while(itr.hasNext()){
                GamePadController Controller = (GamePadController) itr.next();
                if(Controller.isButtonPressed(Controller.getButtonA())){
                    select.play();
                            if(getPointery() == 475){
                                setStateMenu();
                            }
                            if(getPointery() == 555){
                                System.exit(0);
                            }
                        try{
                           Thread.sleep(200);
                        }catch(InterruptedException e){
                           e.printStackTrace();
                    }
                }
                if(Controller.getLXYDir() == Controller.getNORTH()){
                    setPointery1Up(80);
                    setPointery2Up(80);
                    if(getPointery() < 400){
                        setPointery1(550);
                        setPointery2(550);
                        
                    }
                    
                        try{
                           Thread.sleep(200);
                        }catch(InterruptedException e){
                           e.printStackTrace();
                    }
                }
                if(Controller.getLXYDir() == Controller.getSOUTH()){
                    setPointery1Down(80);
                    setPointery2Down(80);
                    
                    if(getPointery() > 550){
                        setPointery1(470);
                        setPointery2(470);
                    }
                        try{
                           Thread.sleep(200);
                        }catch(InterruptedException e){
                           e.printStackTrace();
                    }
                }
            }
            
        }
        
        if(state == STATE.MENU){
            animationBG.tick();
            itr = Controllers.iterator();
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