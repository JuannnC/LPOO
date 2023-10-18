package clases;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import obtenerRecursos.GestorSonidos;

public class Juego extends JPanel {
    final int ancho = 500, alto = 760;
    private Pelota pelota;
    private Raqueta r1 = new Raqueta(300, 680);
    int vidas = 3;
    int puntos;
    boolean gameover = false;
    private final Estadisticas tabla;
    private BufferedImage fondo;
    private Font customFont;
    private JLabel tuto;
    private GestorSonidos gestorSonidos;
    private GeneradorDeNivel nivel;

    public Juego(Estadisticas tabla, GestorSonidos gestorSonidos) {
        this.tabla = tabla;
        this.gestorSonidos = gestorSonidos;
        
        
        try {
            // Carga la imagen de fondo
            fondo = ImageIO.read(new File("src\\recursos\\fondo.png")); // Cambia "background.jpg" al nombre de tu imagen
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        try {
            // Carga la fuente personalizada desde el classpath
            InputStream fontStream = Estadisticas.class.getResourceAsStream("/recursos/2 Lines.ttf");
            customFont = Font.createFont(Font.TRUETYPE_FONT, fontStream);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        
        JLabel tuto = new JLabel();
        tuto.setIcon(new ImageIcon("src\\recursos\\tutorial.png"));
        tuto.setBounds(520, 600, 50, 50);
        add(tuto);
        tuto.setVisible(true);
        this.tuto = tuto;
        
        this.pelota = new Pelota(r1.getX() + 17, 660, this, gestorSonidos);
        setOpaque(false);
        setBackground(Color.black);
        setPreferredSize(new Dimension(ancho, alto));
        
        int pelotaX = (ancho - 15) / 2;
        this.pelota = new Pelota(pelotaX, 660, this, gestorSonidos);
        
        int raquetaX = (ancho - Raqueta.ANCHO) / 2;
        r1 = new Raqueta(raquetaX, 680);
        
        nivel = new GeneradorDeNivel(8, 9);
    }
    
    public void sacarTuto() {
        tuto.setVisible(false);
    }
    
     private boolean colision(Rectangle2D pelota, Rectangle2D raqueta) {
        return pelota.intersects(raqueta);
    }
     
     public Raqueta getRaqueta() {
        return r1;
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Dibuja la imagen de fondo
        if (fondo != null) {
            g2.drawImage(fondo, 0, 0, this);
        }

        // Dibuja la imagen de la pelota
        if (pelota.getsprite() != null) {
            g2.drawImage(pelota.getsprite(), pelota.getX(), pelota.getY(), this);
        }

        // Dibuja la imagen de la raqueta
        if (r1.getsprite() != null) {
            g2.drawImage(r1.getsprite(), r1.getX(), r1.getY(), this);
        }

        g2.setFont(new Font("Arial", Font.PLAIN, 50));

        actualizar();

        g.setColor(Color.white);

        if (gameover) {
            if (customFont != null) {
                g2.setFont(customFont.deriveFont(70f));
            }
        
            g2.setColor(Color.red);
            g2.drawString("GAME OVER", 30, 200);
        }
        
        nivel.draw((Graphics2D) g);
    }


    
    public void dibujar(Graphics2D g){
        g.fill(pelota.getPelota());
        g.fill(r1.getRaqueta());
        g.setFont(new Font("Arial", Font.PLAIN, 50));
    }
    
    public void actualizar() {
        boolean colisionR1 = colision(pelota.getPelota(), r1.getRaqueta());

        pelota.mover(getBounds(), colisionR1);
        r1.moverR1(getBounds());

        if (pelota.getPelota().getMaxY() > getBounds().getMaxY()) {
            
            if (vidas <= 0) {
                gameover = true;
            } else {
                // Reiniciar la posición de la pelota y la raqueta al centro de la pantalla
                pelota = new Pelota(350, 420, this, gestorSonidos);
                int centroXRaqueta = (int) (getBounds().getMinX() + getBounds().getMaxX()) / 2 - Raqueta.ANCHO / 2;
                r1.setX(centroXRaqueta);
            }
        }
    }
    
    public void perderVida() {
    vidas--;
    tabla.setVidas(vidas);
    }
    
    public void sumarPunto() {
        puntos++;
        tabla.setPuntos(puntos);
    }
    
    public int getVidas() {
        return this.vidas;
    }
    
    public void gameover() {
        gameover = true;
    }
}
