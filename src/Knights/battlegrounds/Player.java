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
    private boolean Moving;
    private boolean Attack;     //para saber si esta atacando
    private Game game;
    private int Direction;      //direccion del jugador
    private String mono; 
    private GamePadController Controller;
    
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
            iTypePlayer, GamePadController Controller){
        super(iX, iY, iWidth, iHeight, gaGame);
        //dependiendo del tipo de jugador, este sera el sprite
        this.iTypePlayer = iTypePlayer;
        this.Controller = Controller;
        if(iTypePlayer == 0){
            mono = "DoradoEspada";
        }else if(iTypePlayer == 1){
            mono = "CafeAcha";
        }
        
        this.animationRight = new Animation(Assets.AnimationDer(mono), 50);
        this.animationLeft = new Animation(Assets.AnimationIzq(mono), 50);
        this.animationAtkDer = new Animation(Assets.AtkDer(mono),50);
        this.animationAtkIzq = new Animation(Assets.AtkIzq(mono), 50);
        Direction = 1;
        Moving = false;
        Attack = false; 
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
        
        //si el numero del jugador corresponde al numero del control
        if(getiTypePlayer() == Controller.getiNumController()){

            //mover al jugador en y
            if(Controller.getLXYDir() == Controller.getNORTH()){ //hacia arriba

                setiY(getiY() - 10);
                setMoving(true);

            }else if(Controller.getLXYDir() == Controller.getSOUTH()){//hacia abajo

                setiY(getiY() + 10); 
                setMoving(true);

            }

            //mover al jugador en x
            if(Controller.getLXYDir() == Controller.getEAST()){//a la derecha

                setiX(getiX() + 5);
                setDirection(1);
                setMoving(true);

            }else if(Controller.getLXYDir() == Controller.getWEST()){ //a la izquierda

                setiX(getiX() - 5);
                setDirection(-1);
                setMoving(true);
            }

            //mover al jugador en diagonal
            if(Controller.getLXYDir() == Controller.getNW()){ //arriba a la izquierda

                setiY(getiY() - 10);
                setiX(getiX() - 5);
                setDirection(-1);
                setMoving(true); 

            }else if(Controller.getLXYDir() == Controller.getNE()){//arriba a la derecha

                setiY(getiY() - 10);
                setiX(getiX() + 5);
                setDirection(1);
                setMoving(true);  

            }else if(Controller.getLXYDir()== Controller.getSW()){ //abajo a la izquierda

                setiY(getiY() + 10);
                setiX(getiX() - 5);
                setDirection(-1);
                setMoving(true); 

            }else if(Controller.getLXYDir() == Controller.getSE()){ //abajo a la derecha

                setiY(getiY() + 10);
                setiX(getiX() + 5);
                setDirection(1);
                setMoving(true); 

            }

            //para el ataque a la derecha
            if(Controller.isButtonPressed(Controller.getButtonA()) 
                    && (Direction == 1)){

                this.animationAtkDer.tick();
                setAttack(true);

            }else if(Controller.isButtonPressed(Controller.getButtonA()) 
                    && (Direction == -1)){

                this.animationAtkIzq.tick();
                setAttack(true);

            }           

            //colision con los bordes de la pantalla en x
            if(getiX() + getiWidth() >= getGaGame().getiWidth()){

                setiX(getGaGame().getiWidth() - getiWidth());

            }else if(getiX() <= 0){

                setiX(0);

            }
            //colision con los borden en y 
            if(getiY() + getiHeight()+120 >= getGaGame().getiHeight()){ 

                setiY(getGaGame().getiHeight() - getiHeight()-120);

            }else if(getiY() <= 0){

                setiY(0);

            }

            //por si no se esta moviendo
            if(Controller.getLXYDir() == Controller.getNONE()){

                setMoving(false);

            }

            //para desactivar el ataque
            if(!Controller.isButtonPressed(Controller.getButtonA())){
                setAttack(false);
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
            
        //animacion de ataque a la derecha 
        if(isAttack() && (getDirection() == 1)){

            gGraphics.drawImage(animationAtkDer.getCurrentFrame(), getiX(), 
                getiY(), getiWidth(), getiHeight(),null);

        }else if(isAttack() && (getDirection() == -1)){//ataque a la izquierda

            gGraphics.drawImage(animationAtkIzq.getCurrentFrame(), getiX(), 
                getiY(), getiWidth(), getiHeight(),null);

        }else if(isMoving() && getDirection() == 1){ //moviendo a la derecha

            gGraphics.drawImage(animationRight.getCurrentFrame(), getiX(), getiY(), 
                getiWidth(), getiHeight(), null);

        }else if(isMoving() && getDirection() == -1){//moviendo a la izquierda

            gGraphics.drawImage(animationLeft.getCurrentFrame(), getiX(), getiY(), 
                getiWidth(), getiHeight(), null);

        }else if(!isMoving() && getDirection() == 1){  //parado a la derecha

            gGraphics.drawImage(Assets.ParadoDer(mono), getiX(), getiY(),
                        getiWidth(), getiHeight(), null);

        }else if(!isMoving() && getDirection() == -1){ //parado a la izquierda
            gGraphics.drawImage(Assets.ParadoIzq(mono), getiX(), getiY(), 
                        getiWidth(), getiHeight(), null);
        }
        
    }//para el render
}//para toda la clase
