/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Knights.battlegrounds;

import java.awt.Dimension;
import java.awt.Toolkit;

/**
 * @author Angel Odiel Treviño Villanueva A01336559
 * pongan sus nombres y matriculas
 */
public class battlegrounds {
    
    /**
     * Clase principal
     * 
     * Esta es la clase que corre el juego, llama a la clase Game 
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //obtiene el tamaño de la pantalla del sistema 
        Toolkit t = Toolkit.getDefaultToolkit();
        Dimension ScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
        
        //metodos que empiezan el juego
        Game gamGeometry = new Game("Geometry Battlegrounds",  900, 
                650, 2); 
        gamGeometry.start();
        
    }
    
}
