package Knights.battlegrounds;

import java.awt.image.BufferedImage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *Assets
 * 
 * clase donde se definen y se cargan las imagenes, sonidos, etc. del juego
 * 
 * @author Angel Odiel Treviño Villanueva
 */
public class Assets {
        
    public static BufferedImage imgBackground[]; //declarando la variable donde guardar el background
    public static BufferedImage imgLogo; //declarando la variabe en donde se guarda el logo
    public static BufferedImage imgPointerDer, imgPointerIzq;
    public static BufferedImage imgStart;
    public static BufferedImage imgHighScore;
    public static BufferedImage imgNewGame;
    public static BufferedImage imgquit;
    public static BufferedImage imgPause;
    public static BufferedImage Settings;
    public static BufferedImage img1v1, img2v2;
    public static BufferedImage imgffa;
    public static BufferedImage imgwinnerPointerDer, imgwinnerPointerIzq;
    public static BufferedImage imgVictoria;
    public static BufferedImage imgFightBG[];
    public static BufferedImage imgFightBG2[];
    public static BufferedImage imgFightBG3[];
    
    public static SoundClip select;
    public static SoundClip selectBack;
    public static SoundClip start;
    public static SoundClip menumusic;
    public static SoundClip navigate;
    public static SoundClip battle1;

    
    public static BufferedImage DoradoEspadaDer[], DoradoEspadaIzq[],
            DoradoEspadaAtkDer[], DoradoEspadaAtkIzq[], DoradoEspadaDerAtc[], DoradoEspadaIzqAtc[]; /* Arreglo con sprites 
    para animaciones*/
    public static BufferedImage DoradoEspadaDerParado, DoradoEspadaIzqParado, DoradoEspadaBrincoIzq, DoradoEspadaBrincoDer,
            DoradoEspadaDerParadoAtc, DoradoEspadaIzqParadoAtc, DoradoEspadaDerBrincoAtc, DoradoEspadaIzqBrincoAtc; 
    
    public static BufferedImage CafeAchaDer[], CafeAchaIzq[], CafeAchaAtkDer[],
            CafeAchaAtkIzq[], CafeAchaDerAtc[], CafeAchaIzqAtc[]; //Arreglo con sprites para animaciones
    
    public static BufferedImage CafeAchaDerParado, CafeAchaIzqParado, CafeAchaBrincoIzq, CafeAchaBrincoDer, 
            CafeAchaDerParadoAtc, CafeAchaIzqParadoAtc; 
    
    public static BufferedImage GrisLanzaDer[], GrisLanzaIzq[]; //Arreglo con sprites para animaciones
    public static BufferedImage GrisLanzaDerParado, GrisLanzaIzqParado;
   
    
    /**
     * init
     * 
     * funcion que inicializa los assets
     */
    public static void init(){
        
        //Imagenes son cargadas en su respectiva variable
        imgLogo = ImageLoader.loadImage("/Images/logo.png");
        imgPointerDer = ImageLoader.loadImage("/Images/pointerder.png");
        imgPointerIzq = ImageLoader.loadImage("/Images/pointerizq.png");
        imgStart = ImageLoader.loadImage("/images/pressStart.png");
        imgHighScore = ImageLoader.loadImage("/images/HighScore.png");
        imgNewGame = ImageLoader.loadImage("/images/NEwGame.png");
        Settings = ImageLoader.loadImage("/images/settings.png");
        img1v1 = ImageLoader.loadImage("/images/1vs1.png");
        img2v2 = ImageLoader.loadImage("/Images/2vs2.png");
        imgffa = ImageLoader.loadImage("/Images/ffa.png");
        imgquit = ImageLoader.loadImage("/images/quitgame.png");
        imgPause = ImageLoader.loadImage("/Images/pausa.png");
        imgVictoria = ImageLoader.loadImage("/images/winnerscreen.png");
        imgwinnerPointerDer = ImageLoader.loadImage("/images/winnerpointer.png");
        imgwinnerPointerIzq = ImageLoader.loadImage("/images/winnerpointer.png");
        
        //Sonidos son cargados en su respectiva variable
        select = new SoundClip("/sounds/select.wav");
        selectBack = new SoundClip("/sounds/selectback.wav");
        start = new SoundClip("/sounds/start.wav");
        menumusic = new SoundClip("/sounds/menu.wav");
        navigate = new SoundClip("/sounds/navigate.wav");
        battle1 = new SoundClip("/sounds/battle1.wav");
        
        //Imagenes de personaje dorado en idle
        DoradoEspadaDerParado = ImageLoader.loadImage("/DoradoEspada/"
                + "_IDLE_000.png");
        DoradoEspadaIzqParado = ImageLoader.loadImage("/DoradoEspada/"
                + "_IDLEIZQ_000.png");
        DoradoEspadaDerParadoAtc = ImageLoader.loadImage("/DoradoEspada/"
                + "IDLEDER.png");
        DoradoEspadaIzqParadoAtc = ImageLoader.loadImage("/DoradoEspada/"
                + "IDLEIZQ.png");
        //Imagenes de personaje dorado en brinco
        DoradoEspadaBrincoIzq = ImageLoader.loadImage("/DoradoEspada/"
                + "_JUMPIZQ_001.png");
        DoradoEspadaBrincoDer = ImageLoader.loadImage("/DoradoEspada/"
                + "_JUMP_001.png");
        DoradoEspadaDerBrincoAtc = ImageLoader.loadImage("/DoradoEspada/"
                + "_JUMPDERATC_001.png");
        DoradoEspadaIzqBrincoAtc = ImageLoader.loadImage("/DoradoEspada/"
                + "_JUMPIZQATC_001.png");
        
        //Imagenes de personaje cafe acha
        CafeAchaDerParado = ImageLoader.loadImage("/CafeAcha/_IDLE_000.png");
        CafeAchaIzqParado = ImageLoader.loadImage("/CafeAcha/_IDLEIZQ_000.png");
        CafeAchaBrincoIzq = ImageLoader.loadImage("/CafeAcha/_JUMPIZQ_001.png");
        CafeAchaBrincoDer = ImageLoader.loadImage("/CafeAcha/_JUMP_001.png");
       
        GrisLanzaDerParado = ImageLoader.loadImage("/GrisLanza/_IDLE_000.png");
        GrisLanzaIzqParado = ImageLoader.loadImage("/GrisLanza/_IDLEIZQ_000.png");
        
        //incializar los arreglos para animaciones
        DoradoEspadaDer = new BufferedImage[7];
        DoradoEspadaDerAtc = new BufferedImage[7];
        DoradoEspadaIzq = new BufferedImage[7];
        DoradoEspadaIzqAtc = new BufferedImage[7];
        DoradoEspadaAtkDer = new BufferedImage[8];
        DoradoEspadaAtkIzq = new BufferedImage[8];
       
        CafeAchaDer = new BufferedImage[7];
        CafeAchaDerAtc = new BufferedImage[7];
        CafeAchaIzq = new BufferedImage[7];
        CafeAchaAtkDer = new BufferedImage[7];
        CafeAchaAtkIzq = new BufferedImage[7];
   
        GrisLanzaDer = new BufferedImage[7];
        GrisLanzaIzq = new BufferedImage[7];
        
        imgFightBG = new BufferedImage[8];
        imgFightBG2 = new BufferedImage[4];
        imgFightBG3 = new BufferedImage[32];
        imgBackground = new BufferedImage[8];
        
        //cargar los frames a sus respectivos arreglos
        for(int i =0; i <= 7; i++){
            DoradoEspadaAtkDer[i] = ImageLoader.loadImage("/DoradoEspada/Atacar_000"
                    + i + ".png");
            
            DoradoEspadaAtkIzq[i] = ImageLoader.loadImage("/DoradoEspada/AtacarIzq_000"
                    + i + ".png");          
        }
        
        //cargar los frames a sus respectivos arreglos
        for(int i = 0; i <= 6; i++){
            if( i < 4){
                imgFightBG2[i] = ImageLoader.loadImage("/Images/batalla2_" + i + ".png");
            }
            imgFightBG3[i] = ImageLoader.loadImage("/images/frame_" + i +".png");
            imgBackground[i] = ImageLoader.loadImage("/Images/" + i + ".png");
            imgFightBG[i] = ImageLoader.loadImage("/images/arena" + (i + 1) + ".png");
            DoradoEspadaDer[i] = ImageLoader.loadImage("/DoradoEspada/_Run_00" 
                    + i + ".png");
            
            DoradoEspadaIzq[i] = ImageLoader.loadImage("/DoradoEspada/_RunIzq_00"
                    + i + ".png");
            
            DoradoEspadaDerAtc[i] = ImageLoader.loadImage("/DoradoEspada/Correr_000"
                    + i + ".png");
             
            DoradoEspadaIzqAtc[i] = ImageLoader.loadImage("/DoradoEspada/CorrerIzq_000"
                    + i + ".png");
            
            CafeAchaDer[i] = ImageLoader.loadImage("/CafeAcha/_Run_00" + i + ""
                    + ".png");
            CafeAchaIzq[i] = ImageLoader.loadImage("/CafeAcha/_RunIzq_00" + i + 
                    ".png");
            CafeAchaAtkDer[i] = ImageLoader.loadImage("/CafeAcha/_ATTACK_00" + i
                    + ".png");
            CafeAchaAtkIzq[i] = ImageLoader.loadImage("/CafeAcha/_ATTACKIzq_00" 
                    + i + ".png");
            
            GrisLanzaDer[i] = ImageLoader.loadImage("/GrisLanza/_Run_00" + i + 
                    ".png");
            GrisLanzaIzq[i] = ImageLoader.loadImage("/GrisLanza/_RunIzq_00" + i 
                    + ".png");

        }
        
        //cargar los frames a sus respectivos arreglos
        for(int i = 7; i <= 31; i++){
          imgFightBG3[i] = ImageLoader.loadImage("/images/frame_" + i +".png");  
        }
        imgBackground[7] = ImageLoader.loadImage("/Images/7.png");
        imgFightBG[7] = ImageLoader.loadImage("/Images/arena8.png");
        
    }
    
static BufferedImage ParadoDer(String name){
        if(name == "DoradoEspada"){
            return DoradoEspadaDerParado;
        } else if (name == "CafeAcha"){
            return CafeAchaDerParado;
        }
        return null;
    }
    
    static BufferedImage ParadoDerAtc(String name){
        if(name == "DoradoEspada"){
            return DoradoEspadaDerParadoAtc;
        } else if (name == "CafeAcha"){
            return CafeAchaDerParadoAtc;
        }
        return null;
    }
    
    static BufferedImage[] AtkDer(String name){
        if(name == "CafeAcha"){
            return CafeAchaAtkDer;
        }else if(name == "DoradoEspada"){
            return DoradoEspadaAtkDer;
        }
        return null;
    }
    
    static BufferedImage[] AtkIzq(String name){
        if(name == "CafeAcha"){
            return CafeAchaAtkIzq;
        }else if(name == "DoradoEspada"){
            return DoradoEspadaAtkIzq;
        }
        return null;
    }
    
    static BufferedImage ParadoIzq(String name){
        if(name == "DoradoEspada"){
            return DoradoEspadaIzqParado;
        }else if (name == "CafeAcha"){
            return CafeAchaIzqParado;
        }
        return null;
    }
    
    static BufferedImage ParadoIzqAtc(String name){
        if(name == "DoradoEspada"){
            return DoradoEspadaIzqParadoAtc;
        }else if (name == "CafeAcha"){
            return CafeAchaIzqParadoAtc;
        }
        return null;
    }
    
    static BufferedImage[] AnimationDer(String name){
        if(name == "DoradoEspada"){
            return DoradoEspadaDer;
        }else if (name == "CafeAcha"){
            return CafeAchaDer;
        }
        return null;
    }
    static BufferedImage[] AnimationDerAtc(String name){
        if(name == "DoradoEspada"){
            return DoradoEspadaDerAtc;
        }else if (name == "CafeAcha"){
            return CafeAchaDerAtc;
        }
        return null;
    }
    
    static BufferedImage[] AnimationIzq(String name){
        if(name == "DoradoEspada"){
            return DoradoEspadaIzq;
        }else if (name == "CafeAcha"){
            return CafeAchaIzq;
        }
        return null;
    } 
    static BufferedImage[] AnimationIzqAtc(String name){
        if(name == "DoradoEspada"){
            return DoradoEspadaIzqAtc;
        }else if (name == "CafeAcha"){
            return CafeAchaIzqAtc;
        }
        return null;
    }
        static BufferedImage AnimationJumpIzq(String name){
        if(name == "DoradoEspada"){
            return DoradoEspadaBrincoIzq;
        }else if (name == "CafeAcha"){
            return CafeAchaBrincoIzq;
        }
        return null;
    }
    
    static BufferedImage AnimationJumpDer(String name){
        if(name == "DoradoEspada"){
            return DoradoEspadaBrincoDer;
        }else if (name == "CafeAcha"){
            return CafeAchaBrincoDer;
        }
        return null;
    }
    
    static BufferedImage AnimationJumpAtcDer(String name){
        if(name == "DoradoEspada"){
            return DoradoEspadaDerBrincoAtc;
        }else if (name == "CafeAcha"){
            
        }
        return null;
    }
    
    static BufferedImage AnimationJumpAtcIzq(String name){
        if(name == "DoradoEspada"){
            return DoradoEspadaIzqBrincoAtc;
        }else if (name == "CafeAcha"){
            
        }
        return null;
    }
}