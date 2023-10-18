package clases;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class Raqueta {
    private int x;
    private int y;
    static final int ANCHO = 60;
    static final int ALTO = 10;
    private static final int VELOCIDAD = 3;
    private Image sprite;

    
    public Raqueta(int x, int y){
        this.x = x;
        this.y = y;
        
        try {
        sprite = ImageIO.read(new File("src\\recursos\\Raqueta (1).png"));
    } catch (IOException e) {
        e.printStackTrace();
    }
    }
    
    public Image getsprite() {
        return sprite;
    }
    
    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }
    
    public Rectangle2D getRaqueta(){
        return new Rectangle2D.Double(x, y, ANCHO, ALTO);
    }
    
    public void moverR1(Rectangle limites){
    if (EventoTeclado.a && x > limites.getMinX() + 20) {
        x -= VELOCIDAD;
    }
    if (EventoTeclado.d && x < limites.getMaxX()-ANCHO - 20) {
        x += VELOCIDAD;
    }
    
}
    public void setX(int newX) {
        x = newX;
    }
}
