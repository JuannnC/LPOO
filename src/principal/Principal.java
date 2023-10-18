package principal;

import clases.Ventana;
import java.awt.FontFormatException;
import javax.swing.JFrame;


public class Principal {
    public static void main(String[] args) throws FontFormatException {
        Ventana ventana = new Ventana();
        ventana.setVisible(true);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
