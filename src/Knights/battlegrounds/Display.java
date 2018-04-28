/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Knights.battlegrounds;

import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 * Display
 * 
 * clase que crea un JFrame y un canvas para poder desplegar el juego
 *
 * @author Angel Odiel Treviño Villanueva A01336559
 */
class Display {
    private JFrame jframe;      //clases de las apps
    private Canvas Canvas;   //se usa para desplegar los objetos
    
    private String sTitle;      //titulo de la ventana
    private int iWidth;         //anchura de la ventana
    private int iHeight;        //altura de la ventana
    
    /**
     * Display
     * 
     * inicializa los valores de la ventana de la aplicacion
     * 
     * @param sTitle para desplegar el titulo de la ventana
     * @param iWidth para ajustar la anchura
     * @param iHeight para ajustar la altura
     */
    public Display(String sTitle, int iWidth, int iHeight){
        this.sTitle = sTitle;
        this.iWidth = iWidth; 
        this.iHeight = iHeight;
        createDisplay();
    }

    /**
     * get Jframe
     * @return jframe del juego
     */
    public JFrame getJframe() {
        return jframe;
    }

    /**
     * get Canvas
     * @return canvas del juego
     */
    public Canvas getCanvas() {
        return Canvas;
    }
    
    /**
     **
     * createDisplay
     *
     * crea el canvas para dibujar los objetos
     */
    public void createDisplay(){

        //Create the app window
        jframe = new JFrame(sTitle);

        //Set the size of the window
        jframe.setSize(iWidth, iHeight);

        //Setting not resizable, visible and possible to close
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setResizable(true);
        jframe.setLocationRelativeTo(null);
        jframe.setVisible(true);

        //Creating the canvas to paint and settings size
        Canvas = new Canvas();
        Canvas.setPreferredSize(new Dimension(iWidth, iHeight));
        Canvas.setMaximumSize(new Dimension(iWidth, iHeight));
        Canvas.setMinimumSize(new Dimension(iWidth, iHeight));
        Canvas.setFocusable(false);
        jframe.add(Canvas);
        jframe.pack();
    }
}