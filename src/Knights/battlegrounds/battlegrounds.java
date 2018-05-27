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
 *              CLASS BATTLEGROUNDS
 * 
 * The one in charge of wrapping everything and creates an object to start the Game.
 * It also ask for the number of players that should be between 2 and 4. 
 * 
 */
public class battlegrounds {
    
    /**
     *          VOID MAIN
     */
    public static void main(String[] args) {
        
        //                                                  //for the number of players and controlers that are gonna
        //                                                  //      play for this sessiong
        int iNumPlayers = 0;
        
        /*START-DO-WHILE*/
        do{
            String sWhatTheUserEntered = JOptionPane.showInputDialog( 
                    "How many knights are going to fight? (between 2 and 4 are permited)"
            );
            //                                              //if the user cancel the window 
            if ( sWhatTheUserEntered == null ) {
                System.out.println("The user canceled the game");
                System.exit(1);
            }else{
                //                                          //pases the string to integer
                iNumPlayers = Integer.parseInt( sWhatTheUserEntered );
            }
        }while( iNumPlayers <= 1 && iNumPlayers > 4);
        /*END-DO-WHILE*/
        
        //                                                  //method that creates the game, we send:
        //                                                  //the String with the name of the game,
        //                                                  //the dimensions of the window ( They are going to be fix )
        //                                                  //and the int with the number of players 
        Game gamGeometry = new Game("Geometry Battlegrounds",  900, 650, iNumPlayers);
        
        //                                                  //starts the game
        gamGeometry.start();
        
    }
    
}
