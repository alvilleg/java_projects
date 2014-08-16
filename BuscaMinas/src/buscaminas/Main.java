/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package buscaminas;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

/**
 *
 * @author Aldemar
 */
public class Main extends JFrame{

    /**
     * @param args the command line arguments
     */
    public Main(){
        PanelMinas panelMinas = new PanelMinas();        
        this.getContentPane().add(panelMinas);
        setSize(new Dimension(750,750));
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }            
        });
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        Main main = new Main();
        main.setVisible(true);        
    }
    
}
