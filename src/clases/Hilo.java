package clases;

import java.util.logging.Level;
import java.util.logging.Logger;


public class Hilo extends Thread{
    Juego lamina;
    public Hilo(Juego lamina){
        this.lamina = lamina;
    }
    
    @Override
    public void run(){
        while(true){
            try {
                Thread.sleep(6);
            } catch (InterruptedException ex) {
                Logger.getLogger(Hilo.class.getName()).log(Level.SEVERE, null, ex);
            }
           lamina.repaint();
        }
    }
}
