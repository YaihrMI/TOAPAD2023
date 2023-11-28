package multihilos;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Ejemplo1 {
    
    public static void main(String[] args){
        
        Thread hiloConteo = new Thread(new HiloConteo1());
        hiloConteo.start();  
        
        Thread hiloFecha = new Thread(new HiloFecha1());
        hiloFecha.start();
        
        try {
            hiloConteo.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Ejemplo1.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("Termino main");
        
    }
}

class HiloConteo1 implements Runnable {

    @Override
    public void run() {
        for (int i=0; i<20; i++){
       
            System.out.println("Conteo: "+i);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Ejemplo1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        System.out.println("Soy HiloConteo y ya termine mi ejecucion");
        
    }
    
}

class HiloFecha1 implements Runnable {

    @Override
    public void run() {
        Date tiempo;
       
        
        for (int i=0; i<20; i++){
            tiempo = new Date();
            System.out.println(tiempo.toString());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Ejemplo1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        System.out.println("Soy HiloFecha y ya termine mi ejecucion");
    }
    
    
}

