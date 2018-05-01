/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Knights.battlegrounds;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JOptionPane;

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
        int NumPlayers = 2;
        while(NumPlayers > 1 && NumPlayers <=4){
            String whatTheUserEntered = JOptionPane.showInputDialog("Number of players?");
            if (whatTheUserEntered == null) {
                System.out.println("The user canceled");
            }else{
                NumPlayers = Integer.parseInt(whatTheUserEntered);
            }
        }
        //metodos que empiezan el juego
        Game gamGeometry = new Game("Geometry Battlegrounds",  900, 
                650, NumPlayers); 
        gamGeometry.start();
        
    }
    
}
