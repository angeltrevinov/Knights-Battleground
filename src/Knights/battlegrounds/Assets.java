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
    public static BufferedImage imgFightBG[];
    public static BufferedImage imgFightBG2[];
    public static BufferedImage imgFightBG3[];
    
    public static BufferedImage DoradoEspadaDer[], DoradoEspadaIzq[],
            DoradoEspadaAtkDer[], DoradoEspadaAtkIzq[], DoradoEspadaDerAtc[], 
            DoradoEspadaIzqAtc[]; /* Arreglo con spritespara animaciones*/
    
    public static BufferedImage DoradoEspadaDerParado, DoradoEspadaIzqParado, 
            DoradoEspadaBrincoIzq, DoradoEspadaBrincoDer,
            DoradoEspadaDerParadoAtc, DoradoEspadaIzqParadoAtc, 
            DoradoEspadaDerBrincoAtc, DoradoEspadaIzqBrincoAtc; 
    
    public static BufferedImage CafeAchaDer[], CafeAchaIzq[], CafeAchaAtkDer[],
            CafeAchaAtkIzq[], CafeAchaDerAtc[], CafeAchaIzqAtc[]; //Arreglo con sprites para animaciones
    
    public static BufferedImage CafeAchaDerParado, CafeAchaIzqParado, 
            CafeAchaBrincoIzq, CafeAchaBrincoDer, 
            CafeAchaDerParadoAtc, CafeAchaIzqParadoAtc, CafeAchaDerBrincoAtc, 
            CafeAchaIzqBrincoAtc; 
    
     public static BufferedImage DoradoEspadaDer2[], DoradoEspadaIzq2[],
            DoradoEspadaAtkDer2[], DoradoEspadaAtkIzq2[], DoradoEspadaDerAtc2[], 
             DoradoEspadaIzqAtc2[]; /* Arreglo con spritespara animaciones*/
     
    public static BufferedImage DoradoEspadaDerParado2, DoradoEspadaIzqParado2, 
            DoradoEspadaBrincoIzq2, DoradoEspadaBrincoDer2,
            DoradoEspadaDerParadoAtc2, DoradoEspadaIzqParadoAtc2, 
            DoradoEspadaDerBrincoAtc2, DoradoEspadaIzqBrincoAtc2; 
    
    public static BufferedImage CafeAchaDer2[], CafeAchaIzq2[], CafeAchaAtkDer2[],
            CafeAchaAtkIzq2[], CafeAchaDerAtc2[], CafeAchaIzqAtc2[]; //Arreglo con sprites para animaciones
    
    public static BufferedImage CafeAchaDerParado2, CafeAchaIzqParado2, 
            CafeAchaBrincoIzq2, CafeAchaBrincoDer2, 
            CafeAchaDerParadoAtc2, CafeAchaIzqParadoAtc2, 
            CafeAchaDerBrincoAtc2, CafeAchaIzqBrincoAtc2; 
    
    public static SoundClip select;
    public static SoundClip selectBack;
    public static SoundClip start;
    public static SoundClip menumusic;
    public static SoundClip navigate;
    public static SoundClip battle1;
    
    /**
     * init
     * 
     * funcion que inicializa los assets
     */
    public static void init(){
        
        menumusic = new SoundClip("/Sounds/menu.wav");
        navigate = new SoundClip("/Sounds/navigate.wav");
        select = new SoundClip("/Sounds/select.wav");
        selectBack = new SoundClip("/Sounds/selectback.wav");
        start = new SoundClip("/Sounds/start.wav");
        battle1 = new SoundClip("/Sounds/battle1.wav");
        
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
        
        //Sonidos son cargados en su respectiva variable
        select = new SoundClip("/sounds/select.wav");
        selectBack = new SoundClip("/sounds/selectback.wav");
        start = new SoundClip("/sounds/start.wav");
        menumusic = new SoundClip("/sounds/menu.wav");
        navigate = new SoundClip("/sounds/navigate.wav");
        battle1 = new SoundClip("/sounds/battle1.wav");
        
        //Imagenes son cargadas en su respectiva variable
        imgBackground = new BufferedImage[8];
        imgLogo = ImageLoader.loadImage("/Images/logo.png");
        imgPointerDer = ImageLoader.loadImage("/Images/pointerder.png");
        imgPointerIzq = ImageLoader.loadImage("/Images/pointerizq.png");
        imgStart = ImageLoader.loadImage("/images/pressStart.png");
        imgHighScore = ImageLoader.loadImage("/images/HighScore.png");
        imgNewGame = ImageLoader.loadImage("/images/NEwGame.png");
        Settings = ImageLoader.loadImage("/images/settings.png");
        img1v1 = ImageLoader.loadImage("/images/1v1.png");
        imgFightBG = new BufferedImage[8];
        
        
        
        DoradoEspadaDerParado = ImageLoader.loadImage("/DoradoEspada/_IDLE_000.png");
        DoradoEspadaIzqParado = ImageLoader.loadImage("/DoradoEspada/_IDLEIZQ_000.png");
        DoradoEspadaBrincoIzq = ImageLoader.loadImage("/DoradoEspada/_JUMPIZQ_001.png");
        DoradoEspadaBrincoDer = ImageLoader.loadImage("/DoradoEspada/_JUMP_001.png");
        
        DoradoEspadaDerParadoAtc = ImageLoader.loadImage("/DoradoEspada/IDLEDER.png");
        DoradoEspadaIzqParadoAtc = ImageLoader.loadImage("/DoradoEspada/IDLEIZQ.png");
        DoradoEspadaDerBrincoAtc = ImageLoader.loadImage("/DoradoEspada/_JUMPDERATC_001.png");
        DoradoEspadaIzqBrincoAtc = ImageLoader.loadImage("/DoradoEspada/_JUMPIZQATC_001.png");
        
        
        CafeAchaDerParado = ImageLoader.loadImage("/CafeAcha/_IDLE_000.png");
        CafeAchaIzqParado = ImageLoader.loadImage("/CafeAcha/_IDLEIZQ_000.png");
        CafeAchaBrincoIzq = ImageLoader.loadImage("/CafeAcha/_JUMPIZQ_001.png");
        CafeAchaBrincoDer = ImageLoader.loadImage("/CafeAcha/_JUMP_001.png");
        
        CafeAchaDerParadoAtc = ImageLoader.loadImage("/CafeAcha/ParadoAtcDer.png");
        CafeAchaIzqParadoAtc = ImageLoader.loadImage("/CafeAcha/ParadoAtcIzq.png");
        CafeAchaDerBrincoAtc = ImageLoader.loadImage("/CafeAcha/BrincoAtcDer.png");
        CafeAchaIzqBrincoAtc = ImageLoader.loadImage("/CafeAcha/BrincoAtcIzq.png");
        
        
        DoradoEspadaDerParado2 = ImageLoader.loadImage("/DoradoEspada2/_IDLE_000.png");
        DoradoEspadaIzqParado2 = ImageLoader.loadImage("/DoradoEspada2/_IDLEIZQ_000.png");
        DoradoEspadaBrincoIzq2 = ImageLoader.loadImage("/DoradoEspada2/_JUMPIZQ_001.png");
        DoradoEspadaBrincoDer2 = ImageLoader.loadImage("/DoradoEspada2/_JUMP_001.png");
        
        DoradoEspadaDerParadoAtc2 = ImageLoader.loadImage("/DoradoEspada2/IDLEDER.png");
        DoradoEspadaIzqParadoAtc2 = ImageLoader.loadImage("/DoradoEspada2/IDLEIZQ.png");
        DoradoEspadaDerBrincoAtc2 = ImageLoader.loadImage("/DoradoEspada2/_JUMPDERATC_001.png");
        DoradoEspadaIzqBrincoAtc2 = ImageLoader.loadImage("/DoradoEspada2/_JUMPIZQATC_001.png");
        
        
        CafeAchaDerParado2 = ImageLoader.loadImage("/CafeAcha2/_IDLE_000.png");
        CafeAchaIzqParado2 = ImageLoader.loadImage("/CafeAcha2/_IDLEIZQ_000.png");
        CafeAchaBrincoIzq2 = ImageLoader.loadImage("/CafeAcha2/_JUMPIZQ_001.png");
        CafeAchaBrincoDer2 = ImageLoader.loadImage("/CafeAcha2/_JUMP_001.png");
        
        CafeAchaDerParadoAtc2 = ImageLoader.loadImage("/CafeAcha2/ParadoAtcDer.png");
        CafeAchaIzqParadoAtc2 = ImageLoader.loadImage("/CafeAcha2/ParadoAtcIzq.png");
        CafeAchaDerBrincoAtc2 = ImageLoader.loadImage("/CafeAcha2/BrincoAtcDer.png");
        CafeAchaIzqBrincoAtc2 = ImageLoader.loadImage("/CafeAcha2/BrincoAtcIzq.png");
        
        
        DoradoEspadaDer = new BufferedImage[7];
        DoradoEspadaDerAtc = new BufferedImage[7];
        DoradoEspadaIzq = new BufferedImage[7];
        DoradoEspadaIzqAtc = new BufferedImage[7];
        DoradoEspadaAtkDer = new BufferedImage[8];
        DoradoEspadaAtkIzq = new BufferedImage[8];
        
        CafeAchaDer = new BufferedImage[7];
        CafeAchaDerAtc = new BufferedImage[7];
        CafeAchaIzq = new BufferedImage[7];
        CafeAchaIzqAtc = new BufferedImage[7];
        CafeAchaAtkDer = new BufferedImage[8];
        CafeAchaAtkIzq = new BufferedImage[8];
        
        
        DoradoEspadaDer2 = new BufferedImage[7];
        DoradoEspadaDerAtc2 = new BufferedImage[7];
        DoradoEspadaIzq2 = new BufferedImage[7];
        DoradoEspadaIzqAtc2 = new BufferedImage[7];
        DoradoEspadaAtkDer2 = new BufferedImage[8];
        DoradoEspadaAtkIzq2 = new BufferedImage[8];
        
        CafeAchaDer2 = new BufferedImage[7];
        CafeAchaDerAtc2 = new BufferedImage[7];
        CafeAchaIzq2 = new BufferedImage[7];
        CafeAchaIzqAtc2 = new BufferedImage[7];
        CafeAchaAtkDer2 = new BufferedImage[8];
        CafeAchaAtkIzq2 = new BufferedImage[8];
        
        
        for(int i =0; i <= 7; i++){
            DoradoEspadaAtkDer[i] = ImageLoader.loadImage("/DoradoEspada/Atacar_000"
                    + i + ".png");
            
            DoradoEspadaAtkIzq[i] = ImageLoader.loadImage("/DoradoEspada/AtacarIzq_000"
                    + i + ".png");
            
            CafeAchaAtkDer[i] = ImageLoader.loadImage("/CafeAcha/AtacarDer_000"
                    + i + ".png");
            
            CafeAchaAtkIzq[i] = ImageLoader.loadImage("/CafeAcha/AtacarIzq_000"
                    + i + ".png");
                    
        }
        
        for(int i =0; i <= 7; i++){
            DoradoEspadaAtkDer2[i] = ImageLoader.loadImage("/DoradoEspada2/Atacar_000"
                    + i + ".png");
            
            DoradoEspadaAtkIzq2[i] = ImageLoader.loadImage("/DoradoEspada2/AtacarIzq_000"
                    + i + ".png");
            
            CafeAchaAtkDer2[i] = ImageLoader.loadImage("/CafeAcha2/AtacarDer_000"
                    + i + ".png");
            
            CafeAchaAtkIzq2[i] = ImageLoader.loadImage("/CafeAcha2/AtacarIzq_000"
                    + i + ".png");
                    
        }
        
        
        for(int i = 0; i <= 6; i++){
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
            
            CafeAchaDerAtc[i] = ImageLoader.loadImage("/CafeAcha/CorrerAtcDer_000" + i
                    + ".png");
            CafeAchaIzqAtc[i] = ImageLoader.loadImage("/CafeAcha/CorrerAtcIzq_000" 
                    + i + ".png");
        }
        
        
        for(int i = 0; i <= 6; i++){
            
            DoradoEspadaDer2[i] = ImageLoader.loadImage("/DoradoEspada2/_Run_00" 
                    + i + ".png");
            
            DoradoEspadaIzq2[i] = ImageLoader.loadImage("/DoradoEspada2/_RunIzq_00"
                    + i + ".png");
            
            DoradoEspadaDerAtc2[i] = ImageLoader.loadImage("/DoradoEspada2/Correr_000"
                    + i + ".png");
             
            DoradoEspadaIzqAtc2[i] = ImageLoader.loadImage("/DoradoEspada2/CorrerIzq_000"
                    + i + ".png");
            
            CafeAchaDer2[i] = ImageLoader.loadImage("/CafeAcha2/_Run_00" + i + ""
                    + ".png");
            CafeAchaIzq2[i] = ImageLoader.loadImage("/CafeAcha2/_RunIzq_00" + i + 
                    ".png");
            
            CafeAchaDerAtc2[i] = ImageLoader.loadImage("/CafeAcha2/CorrerAtcDer_000" + i
                    + ".png");
            CafeAchaIzqAtc2[i] = ImageLoader.loadImage("/CafeAcha2/CorrerAtcIzq_000" 
                    + i + ".png");
        }
        
        imgBackground[7] = ImageLoader.loadImage("/Images/7.png");
        imgFightBG[7] = ImageLoader.loadImage("/Images/arena8.png");
        
    }
    
    static BufferedImage ParadoDer(String name){
        if(name == "DoradoEspada"){
            return DoradoEspadaDerParado;
        } else if (name == "CafeAcha"){
            return CafeAchaDerParado;
        }else if(name == "DoradoEspada2"){
            return DoradoEspadaDerParado2;
        }else if(name == "CafeAcha2"){
            return CafeAchaDerParado2;
        }
        return null;
    }
    
    static BufferedImage ParadoDerAtc(String name){
        if(name == "DoradoEspada"){
            return DoradoEspadaDerParadoAtc;
        } else if (name == "CafeAcha"){
            return CafeAchaDerParadoAtc;
        }else if(name == "DoradoEspada2"){
            return DoradoEspadaDerParadoAtc2;
        }else if(name == "CafeAcha2"){
            return CafeAchaDerParadoAtc2;
        }
        return null;
    }
    
    static BufferedImage[] AtkDer(String name){
        if(name == "CafeAcha"){
            return CafeAchaAtkDer;
        }else if(name == "DoradoEspada"){
            return DoradoEspadaAtkDer;
        }else if(name == "cafeAcha2"){
            return CafeAchaAtkDer2;
        }else if(name == "DoradoEspada2"){
            return DoradoEspadaAtkDer2;
        }
        return null;
    }
    
    static BufferedImage[] AtkIzq(String name){
        if(name == "CafeAcha"){
            return CafeAchaAtkIzq;
        }else if(name == "DoradoEspada"){
            return DoradoEspadaAtkIzq;
        }else if(name == "CafeAcha2"){
            return CafeAchaAtkIzq2;
        }else if(name == "DoradoEspada2"){
            return DoradoEspadaAtkIzq2;
        }
        return null;
    }
    
    static BufferedImage ParadoIzq(String name){
        if(name == "DoradoEspada"){
            return DoradoEspadaIzqParado;
        }else if (name == "CafeAcha"){
            return CafeAchaIzqParado;
        }else if(name == "DoradoEspada2"){
            return DoradoEspadaIzqParado2;
        }else if(name =="CafeAcha2"){
            return CafeAchaIzqParado2;
        }
        return null;
    }
    
    static BufferedImage ParadoIzqAtc(String name){
        if(name == "DoradoEspada"){
            return DoradoEspadaIzqParadoAtc;
        }else if (name == "CafeAcha"){
            return CafeAchaIzqParadoAtc;
        }else if(name == "DoradoEspada2"){
            return DoradoEspadaIzqParadoAtc2;
        }else if(name == "CafeAcha2"){
            return CafeAchaIzqParadoAtc2;
        }
        return null;
    }
    
    static BufferedImage[] AnimationDer(String name){
        if(name == "DoradoEspada"){
            return DoradoEspadaDer;
        }else if (name == "CafeAcha"){
            return CafeAchaDer;
        }else if(name == "DoradoEspada2"){
            return DoradoEspadaDer2;
        }else if(name == "CafeAcha2"){
            return CafeAchaDer2;
        }
        return null;
    }
    static BufferedImage[] AnimationDerAtc(String name){
        if(name == "DoradoEspada"){
            return DoradoEspadaDerAtc;
        }else if (name == "CafeAcha"){
            return CafeAchaDerAtc;
        }else if(name == "DoradoEspada2"){
            return DoradoEspadaDerAtc2;
        }else if(name == "CafeAcha2"){
            return CafeAchaDerAtc2;
        }
        return null;
    }
    
    static BufferedImage[] AnimationIzq(String name){
        if(name == "DoradoEspada"){
            return DoradoEspadaIzq;
        }else if (name == "CafeAcha"){
            return CafeAchaIzq;
        }else if(name == "DoradoEspada2"){
            return DoradoEspadaIzq2;
        }else if(name == "CafeAcha2"){
            return CafeAchaIzq2;
        }
        return null;
    } 
    static BufferedImage[] AnimationIzqAtc(String name){
        if(name == "DoradoEspada"){
            return DoradoEspadaIzqAtc;
        }else if (name == "CafeAcha"){
            return CafeAchaIzqAtc;
        }else if(name == "DoradoEspada2"){
            return DoradoEspadaIzqAtc2;
        }else if(name == "CafeAcha2"){
            return CafeAchaIzqAtc2;
        }
        return null;
    }
        static BufferedImage AnimationJumpIzq(String name){
        if(name == "DoradoEspada"){
            return DoradoEspadaBrincoIzq;
        }else if (name == "CafeAcha"){
            return CafeAchaBrincoIzq;
        }else if(name == "DoradoEspada2"){
            return DoradoEspadaBrincoIzq2;
        }else if(name == "CafeAcha2"){
            return CafeAchaBrincoIzq2;
        }
        return null;
    }
    
    static BufferedImage AnimationJumpDer(String name){
        if(name == "DoradoEspada"){
            return DoradoEspadaBrincoDer;
        }else if (name == "CafeAcha"){
            return CafeAchaBrincoDer;
        }else if(name == "DoradoEspada2"){
            return DoradoEspadaBrincoDer2;
        }else if(name == "CafeAcha2"){
            return CafeAchaBrincoDer2;
        }
        return null;
    }
    
    static BufferedImage AnimationJumpAtcDer(String name){
        if(name == "DoradoEspada"){
            return DoradoEspadaDerBrincoAtc;
        }else if (name == "CafeAcha"){
            return CafeAchaDerBrincoAtc;
        }else if(name == "DoradoEspada2"){
            return DoradoEspadaDerBrincoAtc2;
        }else if(name == "CafeAcha2"){
            return CafeAchaDerBrincoAtc2;
        }
        return null;
    }
    
    static BufferedImage AnimationJumpAtcIzq(String name){
        if(name == "DoradoEspada"){
            return DoradoEspadaIzqBrincoAtc;
        }else if (name == "CafeAcha"){
            return CafeAchaIzqBrincoAtc;
        }else if(name == "DoradoEspada2"){
            return DoradoEspadaIzqBrincoAtc2;
        }else if(name == "CafeAcha2"){
            return CafeAchaIzqBrincoAtc2;
        }
        return null;
    }
}