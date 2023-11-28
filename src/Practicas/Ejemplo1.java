package Practicas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author marco
 */
public class Ejemplo1 {
    public static void main(String args[]){
        
        JFrame miframe = new JFrame("Ejemplo1");
        JButton boton = new JButton("Boton");
        
        miframe.setSize(300, 200);
        miframe.add(boton);
        miframe.setVisible(true);
        //miframe.pack();
        
        MiListener l = new MiListener();
        
        boton.addActionListener(l);
                
    }
            
    
}

class MiListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Hola mundo");
    }
    
}

