package Knights.battlegrounds;
import java.awt.Graphics;
import java.util.Iterator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *Player
 * 
 * Esta clase es para poner las reglas de como el jugador va a poder moverse y 
 * jugar dentro del juego. Esta clase deriva de Item
 * 
 * @author Angel Odiel Treviño Villanueva A01336559
 */
public class Player extends Item {
    
    private int iTypePlayer;        //para saber que jugador es
    private Animation animationLeft;
    private Animation animationRight;
    private Animation animationAtkDer;
    private Animation animationAtkIzq;
    private Animation animationDerAtc;
    private Animation animationIzqAtc;
    private boolean Moving;
    private boolean Attack;     //para saber si esta atacando
    private Game game;
    private int Direction;      //direccion del jugador
    private String mono; 
    private GamePadController Controller;
    private int VelocidadX;
    private int VelocidadY;
    private boolean brinco;
    private int ticks;
    private boolean hit;
    private int salud;  //su salud
    private boolean dead; 
    private int team;
    private int Lives;
    
    /**
     * Player
     * 
     * constructor de player
     * 
     * @param iX para inicializar la posicion en x
     * @param iY para inicializar la posicion en y 
     * @param iWidth para el ancho del jugador
     * @param iHeight para la altura del jugador 
     * @param gaGame para el game
     * @param iTypePlayer obtiene el numero de jugador
     * @param Controller obtiene el control que lo controla
     */
    public Player(int iX, int iY, int iWidth, int iHeight, Game gaGame, int 
            iTypePlayer, GamePadController Controller, int team, int Lives){
        super(iX, iY, iWidth, iHeight, gaGame);
        //dependiendo del tipo de jugador, este sera el sprite
        this.iTypePlayer = iTypePlayer;
        this.Controller = Controller;
        this.team = team;
        this.Lives = Lives;
        if(iTypePlayer == 0){
            mono = "DoradoEspada";
        }else if(iTypePlayer == 1){
            mono = "CafeAcha";
        }
        
        this.animationRight = new Animation(Assets.AnimationDer(mono), 50);
        this.animationLeft = new Animation(Assets.AnimationIzq(mono), 50);
        this.animationAtkDer = new Animation(Assets.AtkDer(mono),50);
        this.animationAtkIzq = new Animation(Assets.AtkIzq(mono), 50);
        this.animationDerAtc = new Animation(Assets.AnimationDerAtc(mono), 50);
        this.animationIzqAtc = new Animation(Assets.AnimationIzqAtc(mono), 50);
        
        Direction = 1;
        Moving = false;
        Attack = false; 
        VelocidadX = 0;
        VelocidadY = -6;
        brinco = false;
        ticks = 0;
        hit = false;
        salud = 0;
        dead = false;
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public int getSalud() {
        return salud;
    }

    public void setSalud(int salud) {
        this.salud = salud;
    }

    public boolean isHit() {
        return hit;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }

    public GamePadController getController() {
        return Controller;
    }
    
    public int getVelocidadX() {
        return VelocidadX;
    }

    public void setVelocidadX(int VelocidadX) {
        this.VelocidadX = VelocidadX;
    }

    public int getVelocidadY() {
        return VelocidadY;
    }

    public void setVelocidadY(int VelocidadY) {
        this.VelocidadY = VelocidadY;
    }

    public boolean isBrinco() {
        return brinco;
    }

    public void setBrinco(boolean brinco) {
        this.brinco = brinco;
    }

    public int getTicks() {
        return ticks;
    }

    public void setTicks(int ticks) {
        this.ticks = ticks;
    }

    public int getLives() {
        return Lives;
    }

    public void setLives(int Lives) {
        this.Lives = Lives;
    }
    
    public void quitarVida(){
        this.Lives--;
    }
    
    
    /**
     * isAttack
     * 
     * @return booleana con estado del ataque
     */
    public boolean isAttack() {
        return Attack;
    }
    
    /**
     * setAttack 
     * 
     * actualiza el estado del ataque
     * 
     * @param attack 
     */
    public void setAttack(boolean attack) {
        this.Attack = attack;
    }
    
    /**
     * isMoving 
     * 
     * @return booleana con el estado del movimiento
     */
    public boolean isMoving() {
        return Moving;
    }
    
    /**
     * setMoving
     * 
     * actualiza el estado del movimiento
     * 
     * @param moving 
     */
    public void setMoving(boolean moving) {
        this.Moving = moving;
    }
    
    /**
     * getDirection 
     * 
     * @return int con la direccion del jugador
     */
    public int getDirection() {
        return Direction;
    }
    
    /**
     * setDirection 
     * 
     * actualiza la direccion del jugador
     * 
     * @param Direction 
     */
    public void setDirection(int Direction) {
        this.Direction = Direction;
    }
    
    /**
     * getiTypePlayer
     * 
     * saber que tipo de jugador es
     * 
     * @return el tipo de jugador
     */
    public int getiTypePlayer() {
        return iTypePlayer;
    }
    
    /**
     * tick
     * 
     * indicara como el jugador se ccomportara en cada tick
     * 
     */
    @Override
    public void tick() {
        this.animationLeft.tick();
        this.animationRight.tick();
        
        setTicks(getTicks() + 1);
        //gravedad
        
        setiY(getiY() - getVelocidadY());
        setiX(getiX() - getVelocidadX());
        
        
        if(getiY() + getiHeight() + 60 >= getGaGame().getiHeight()){
            setBrinco(false);
        }else if(isBrinco() && getVelocidadY() >= -6 && (getTicks() % 5) == 1){
            setVelocidadY(getVelocidadY() - 4);
        }
 
        
        if(getiTypePlayer() == getController().getiNumController()){
                       
            //mover al jugador en y
            if(getController().getLXYDir() == getController().getNORTH()){ //hacia arriba
                if(!isBrinco()){
                    setBrinco(true);
                    setVelocidadY(getVelocidadY()+25);
                    setMoving(false);
                }
 
            }else{
                setMoving(true);
            }
            if(getController().getLXYDir() == getController().getSOUTH()){//hacia abajo

                setiY(getiY() + 10); 
                setMoving(true);

            }else if(getController().getLXYDir() == getController().getEAST()){//a la derecha
                
                setiX(getiX() + 5);
                setDirection(1);
                setMoving(true);

            }else if(getController().getLXYDir() == getController().getWEST()){ //a la izquierda
                
                setiX(getiX() - 5);
                setDirection(-1);
                setMoving(true);
            }else

            //mover al jugador en diagonal
            if(getController().getLXYDir() == getController().getNW()){ //arriba a la izquierda
                setiX(getiX() - 5);
                if(!isBrinco()){
                    setBrinco(true);
                    setVelocidadY(getVelocidadY() + 25);
                    setMoving(false);
                }
                setDirection(-1);
                setMoving(true); 

            }else if(getController().getLXYDir() == getController().getNE()){//arriba a la derecha
                setiX(getiX() + 5); 
                if(!isBrinco()){
                    setBrinco(true);
                    setVelocidadY(getVelocidadY() +25);
                    setMoving(false);
                }
                setDirection(1);
                setMoving(true);  

            }else if(getController().getLXYDir()== getController().getSW()){ //abajo a la izquierda

                //setiY(getiY() + 10);
                //setiX(getiX() - 5);
                setDirection(-1);
                setMoving(true); 

            }else if(getController().getLXYDir() == getController().getSE()){ //abajo a la derecha

                //setiY(getiY() + 10);
                //setiX(getiX() + 5);
                setDirection(1);
                setMoving(true); 

            }

            //para el ataque a la derecha
            if(Controller.isButtonPressed(Controller.getButtonA()) 
                    && (Direction == 1)){
                this.animationDerAtc.tick();
                this.animationAtkDer.tick();
                setAttack(true);

            }else if(Controller.isButtonPressed(Controller.getButtonA()) 
                    && (Direction == -1)){
                this.animationIzqAtc.tick();
                this.animationAtkIzq.tick();
                setAttack(true);

            }           

            //colision con los bordes de la pantalla en x
            if(getiX() >= getGaGame().getiWidth() || getiX() <= -98){

                setDead(true);

            }
            
            //colision con los borden en y 
            if(getiY() + getiHeight()+ 60 >= getGaGame().getiHeight()){ 

                setiY(getGaGame().getiHeight() - getiHeight()- 60);
                
            }else if(getiY() <= -130){

                setDead(true);

            }
             

            //por si no se esta moviendo
            if(Controller.getLXYDir() == Controller.getNONE()){

                setMoving(false);

            }

            //para desactivar el ataque
            if(!Controller.isButtonPressed(Controller.getButtonA())){
                setAttack(false);
            }
            
            //para que se muera
            if(isHit()){
                setVelocidadX( getDirection() * getSalud()*2 );
                setiY(getiY()  - getSalud()*4);
            }else if(!isHit() && (getTicks() % 7) == 1){
                setVelocidadX(0);
            }
            
            //para resetear al personaje para cuando muera
            if(isDead()){
                setiX(getGaGame().getiWidth()/2 - 100 * getiTypePlayer());
                setiY(10);
                setSalud(0);
                setDead(false);
                quitarVida();
            }

        }
    }
    
    /**
     * render
     * 
     * dibuja al jugador
     * 
     * @param gGraphics 
     */
    @Override
    public void render(Graphics gGraphics) {
        
        
        if(brinco == true && (getDirection() == -1) && !Attack){
            
            gGraphics.drawImage(Assets.AnimationJumpIzq(mono), getiX(), getiY(),
                        getiWidth(), getiHeight(), null);
            
        }else if(brinco == true && (getDirection() == 1) && !Attack){
            gGraphics.drawImage(Assets.AnimationJumpDer(mono), getiX(), getiY(),
                        getiWidth(), getiHeight(), null);
            
        }else if(brinco == true && (getDirection() == 1) && Attack){
            gGraphics.drawImage(animationAtkDer.getCurrentFrame(), getiX(), 
                getiY(), getiWidth(), getiHeight(), null);
            
            gGraphics.drawImage(Assets.AnimationJumpAtcDer(mono), getiX(), getiY(),
                        getiWidth(), getiHeight(), null);
            
        }else if(brinco == true && (getDirection() == -1) && Attack){
            gGraphics.drawImage(animationAtkIzq.getCurrentFrame(), getiX(), 
                getiY(), getiWidth(), getiHeight(),null);
            
            gGraphics.drawImage(Assets.AnimationJumpAtcIzq(mono), getiX(), getiY(),
                        getiWidth(), getiHeight(), null);
        }
        
        
        //animacion de ataque a la derecha 
        
        if(isAttack() && (getDirection() == 1) && isMoving() && !brinco){
            
            gGraphics.drawImage(animationAtkDer.getCurrentFrame(), getiX(), 
                getiY(), getiWidth(), getiHeight(), null);
            
            gGraphics.drawImage(animationDerAtc.getCurrentFrame(), getiX(), 
                getiY(), getiWidth(), getiHeight(),null);
            
            

        }else if(isAttack() && (getDirection() == -1) && isMoving() && !brinco){
            
            gGraphics.drawImage(animationAtkIzq.getCurrentFrame(), getiX(), 
                getiY(), getiWidth(), getiHeight(), null);
            
            gGraphics.drawImage(animationIzqAtc.getCurrentFrame(), getiX(), 
                getiY(), getiWidth(), getiHeight(),null);
            
            

        }
        
        if(isAttack() && (getDirection() == 1) && !isMoving() && !brinco){

            gGraphics.drawImage(animationAtkDer.getCurrentFrame(), getiX() - 12, 
                getiY(), getiWidth(), getiHeight(),null);
                
            gGraphics.drawImage(Assets.ParadoDerAtc(mono), getiX(), getiY(),
                        85, getiHeight(), null);
            
        }else if(isAttack() && (getDirection() == -1) && !isMoving() && !brinco){//ataque a la izquierda

            gGraphics.drawImage(animationAtkIzq.getCurrentFrame(), getiX() + 6, 
                getiY(), getiWidth(), getiHeight(),null);
            
            gGraphics.drawImage(Assets.ParadoIzqAtc(mono), getiX() + 8, getiY(),
                        85, getiHeight(), null);
            
        }else if(isMoving() && getDirection() == 1 && !brinco && !Attack){ //moviendo a la derecha

            gGraphics.drawImage(animationRight.getCurrentFrame(), getiX(), getiY(), 
                getiWidth(), getiHeight(), null);

        }else if(isMoving() && getDirection() == -1 && !brinco && !Attack){//moviendo a la izquierda

            gGraphics.drawImage(animationLeft.getCurrentFrame(), getiX(), getiY(), 
                getiWidth(), getiHeight(), null);

        }else if(!isMoving() && getDirection() == 1 && !brinco){  //parado a la derecha

            gGraphics.drawImage(Assets.ParadoDer(mono), getiX(), getiY(),
                        getiWidth(), getiHeight(), null);

        }else if(!isMoving() && getDirection() == -1 && !brinco){ //parado a la izquierda
            gGraphics.drawImage(Assets.ParadoIzq(mono), getiX(), getiY(), 
                        getiWidth(), getiHeight(), null);
        }
        
    }//para render
}//para toda la clase