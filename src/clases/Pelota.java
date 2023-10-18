package clases;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import obtenerRecursos.GestorSonidos;


public class Pelota {
    private float x;
    private float y;
    private float dx=2, dy=-2;
    final float ANCHO = 15;
    private final float ALTO = 15;
    private float velocidad = 1;
    private final Juego juego;
    private boolean resvel = true;
    private Image sprite;
    private GestorSonidos gestorSonidos;
    
    public Pelota(int x, int y, Juego juego, GestorSonidos gestorSonidos){
        this.x = x;
        this.y = y;
        this.juego = juego;
        this.gestorSonidos = gestorSonidos;
        
        try {
            // Carga la imagen de fondo de la pelota
            sprite = ImageIO.read(new File("src\\recursos\\Pelota (1).png")); // Ajusta la ruta y el nombre de la imagen
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public Image getsprite() {
        return sprite;
    }
    
    public int getX(){
        return (int) x;
    }
    
    public int getY(){
        return (int) y;
    }
    
    public Rectangle2D getPelota(){
        return new Rectangle2D.Double(x, y, ANCHO, ALTO);
    }
    
    public void mover(Rectangle limites, boolean colisionR1) {
        
    if (EventoTeclado.espacio) { // Mover la pelota solo si la tecla de espacio está presionada
        juego.sacarTuto();
            if (resvel){
                velocidad = (float) 1.2;
                resvel = false;
            }
            x += dx * velocidad;  // Aplicar la velocidad horizontal
            y += dy * velocidad;  // Aplicar la velocidad vertical
            }
    if(EventoTeclado.espacio == false) {
        
        velocidad = 3;
        if (EventoTeclado.a && x > limites.getMinX()) {
            x -= velocidad;
        }
        if (EventoTeclado.d && x < limites.getMaxX()-ANCHO) {
            x += velocidad;
        }
    }

    if (colisionR1) {
            dy = -dy;
            y = 660;

            // Ajusta la dirección horizontal (dx) en función de dónde impacta con la raqueta
            float centroRaqueta = juego.getRaqueta().getX() + Raqueta.ANCHO / 2;
            float centroPelota = (x + ANCHO / 2);
            float desplazamiento = centroPelota - centroRaqueta;
            
            if (desplazamiento == 0) {
                desplazamiento = 1;
            }

            // Ajusta dx en función del desplazamiento desde el centro de la raqueta
            dx = desplazamiento / 10; // Puedes ajustar este valor para controlar la dirección del rebote
            System.out.println(dx);
            System.out.println(desplazamiento);
        }

    
            
    if (x > limites.getMaxX()  - ANCHO - 20) {
        x = (int) (limites.getMaxX() - ANCHO - 20);
        dx = -dx;
        gestorSonidos.reproducirSonido("rebote2");
    } else if (x < limites.getMinX() + 20) {
        x = (int) limites.getMinX() + 20;
        dx = -dx;
        gestorSonidos.reproducirSonido("rebote2");
    }

    if (y < limites.getMinY() + 20) {
        y = (int) limites.getMinY() + 20;
        dy = -dy;
        gestorSonidos.reproducirSonido("rebote2");
    }
    int centroRaqueta = juego.getRaqueta().getX() + Raqueta.ANCHO / 2; // Obtener el centro de la raqueta
    if (y > limites.getMaxY() - ALTO) {
        if (juego.getVidas() <= 0) {
            gestorSonidos.detenerSonido("musicalv1");
            gestorSonidos.reproducirSonido("pierdeVida");
            juego.gameover();
        }
        else {
            //reiniciar posicion de pelota al centro de la raqueta
            x = centroRaqueta - 8;
            y = 660;
            dy = -dy;
            
            System.out.println("Vidas restantes: " + juego.getVidas());
            
            
            juego.perderVida();
            resvel = true;
            velocidad = 1;
            EventoTeclado.espacio = false;
            gestorSonidos.reproducirSonido("pierdeVida");
        }
        }
}
}
