package Practicas;

/**
 *
 * @author marco
 */
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author macpro2
 */
public class EjemploLayout extends JFrame implements ActionListener, ChangeListener {
    
    JLabel lblInferior;
    JLabel lblSuperior;
    JLabel lblNumeroGenerado;
    
    JSpinner spInferior;
    JSpinner spSuperior;
    
    JTextField tfNumeroGenerado;
    
    JButton btnGenerar;
    
    GridLayout lm;
    
    public EjemploLayout() {
        this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        
        this.lm = new GridLayout(4,2);
        this.setLayout(lm);
        
        this.lblInferior = new JLabel("Límite inferior");
        this.spInferior = new JSpinner();
        
        this.lblSuperior = new JLabel("Límite superior");
         this.spSuperior = new JSpinner();
         
        this.lblNumeroGenerado = new JLabel("Numero generado"); 
        this.tfNumeroGenerado = new JTextField("0");
        
        this.btnGenerar = new JButton("Generar");
        
        this.tfNumeroGenerado.setEditable(false);
        this.tfNumeroGenerado.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        this.tfNumeroGenerado.setText("0");
        this.tfNumeroGenerado.setFocusable(false);
         
        this.add(this.lblInferior);
        this.add(this.spInferior);
                
        this.add(this.lblSuperior);
        this.add(this.spSuperior);
        
        this.add(this.lblNumeroGenerado);
        this.add(this.tfNumeroGenerado);
        
        this.add(this.btnGenerar);
        
        this.btnGenerar.addActionListener(this);
        this.spInferior.addChangeListener(this);
        this.spSuperior.addChangeListener(this);
        
    }
    
    public static void main(String args[]){
        EjemploLayout ej = new EjemploLayout();
        ej.setSize(640,480);
        ej.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Random r = new Random();
        int low = (int) this.spInferior.getValue();
        int high = (int) this.spSuperior.getValue();
        int result = r.nextInt(high-low) + low;
        this.tfNumeroGenerado.setText(Integer.toString(result));
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        // TODO add your handling code here:
        int low = (int) this.spInferior.getValue();
        int high = (int) this.spSuperior.getValue();   
        
        if (low>=high){
            JOptionPane.showMessageDialog(this, "El valor inferior es mayor que el superior", "Advertencia", JOptionPane.WARNING_MESSAGE);
            this.spInferior.setValue(high-1);
        }
        
        if (high<=low){
            JOptionPane.showMessageDialog(this, "El valor superior debe ser mayor que el inferior", "Advertencia", JOptionPane.WARNING_MESSAGE);
            this.spSuperior.setValue(low+1);
        }
        
    }
    
}

