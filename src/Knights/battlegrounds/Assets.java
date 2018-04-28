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
    public static BufferedImage Settings;
    public static BufferedImage img1v1;
    public static BufferedImage imgFightBG[];
   

    
    public static BufferedImage DoradoEspadaDer[], DoradoEspadaIzq[],
            DoradoEspadaAtkDer[], DoradoEspadaAtkIzq[]; /* Arreglo con sprites 
    para animaciones*/
    public static BufferedImage DoradoEspadaDerParado, DoradoEspadaIzqParado, DoradoEspadaBrincoIzq, DoradoEspadaBrincoDer; 
    
    public static BufferedImage CafeAchaDer[], CafeAchaIzq[], CafeAchaAtkDer[],
            CafeAchaAtkIzq[]; //Arreglo con sprites para animaciones
    public static BufferedImage CafeAchaDerParado, CafeAchaIzqParado, CafeAchaBrincoIzq, CafeAchaBrincoDer; 
    
    public static BufferedImage GrisLanzaDer[], GrisLanzaIzq[]; //Arreglo con sprites para animaciones
    public static BufferedImage GrisLanzaDerParado, GrisLanzaIzqParado;
   
    
    /**
     * init
     * 
     * funcion que inicializa los assets
     */
    public static void init(){
        
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
        
        CafeAchaDerParado = ImageLoader.loadImage("/CafeAcha/_IDLE_000.png");
        CafeAchaIzqParado = ImageLoader.loadImage("/CafeAcha/_IDLEIZQ_000.png");
        CafeAchaBrincoIzq = ImageLoader.loadImage("/CafeAcha/_JUMPIZQ_001.png");
        CafeAchaBrincoDer = ImageLoader.loadImage("/CafeAcha/_JUMP_001.png");
        
        GrisLanzaDerParado = ImageLoader.loadImage("/GrisLanza/_IDLE_000.png");
        GrisLanzaIzqParado = ImageLoader.loadImage("/GrisLanza/_IDLEIZQ_000.png");
        
        DoradoEspadaDer = new BufferedImage[7];
        DoradoEspadaIzq = new BufferedImage[7];
        DoradoEspadaAtkDer = new BufferedImage[7];
        DoradoEspadaAtkIzq = new BufferedImage[7];
        
        CafeAchaDer = new BufferedImage[7];
        CafeAchaIzq = new BufferedImage[7];
        CafeAchaAtkDer = new BufferedImage[7];
        CafeAchaAtkIzq = new BufferedImage[7];
   
        GrisLanzaDer = new BufferedImage[7];
        GrisLanzaIzq = new BufferedImage[7];
        
        for(int i = 0; i <= 6; i++){
            imgBackground[i] = ImageLoader.loadImage("/Images/" + i + ".png");
            imgFightBG[i] = ImageLoader.loadImage("/images/arena" + (i + 1) + ".png");
            
            DoradoEspadaDer[i] = ImageLoader.loadImage("/DoradoEspada/_Run_00" 
                    + i + ".png");
            DoradoEspadaIzq[i] = ImageLoader.loadImage("/DoradoEspada/_RunIzq_00"
                    + i + ".png");
            DoradoEspadaAtkDer[i] = ImageLoader.loadImage("/DoradoEspada/_ATTACK_00"
                    + i + ".png");
            DoradoEspadaAtkIzq[i] = ImageLoader.loadImage("/DoradoEspada/_ATTACKIzq_00"
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
    
    static BufferedImage[] AnimationDer(String name){
        if(name == "DoradoEspada"){
            return DoradoEspadaDer;
        }else if (name == "CafeAcha"){
            return CafeAchaDer;
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
}