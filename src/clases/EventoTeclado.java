package clases;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class EventoTeclado extends KeyAdapter{
    
    static boolean a, d, espacio;
    
    @Override
    public void keyPressed(KeyEvent e) {
        
        int id = e.getKeyCode();
        
        if (id == KeyEvent.VK_A) {
            a = true;
        }
        if (id == KeyEvent.VK_D) {
            d = true;
        }
        if (id == KeyEvent.VK_SPACE){
            espacio = true;
        }
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        
        int id = e.getKeyCode();
        
        if (id == KeyEvent.VK_A) {
            a = false;
        }
        if (id == KeyEvent.VK_D) {
            d = false;
        }
    }
}
