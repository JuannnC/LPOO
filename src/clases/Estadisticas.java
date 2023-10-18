package clases;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class Estadisticas extends JPanel {
    private int vidas;
    private int puntos;
    private Font customFont;
    private BufferedImage fondo;

    public Estadisticas(int vidas, int puntos) {
        setLayout(null);
        setBackground(Color.BLACK);
        this.vidas = vidas;
        this.puntos = puntos;
        
        JLabel vidaIMG = new JLabel();
        vidaIMG.setIcon(new ImageIcon("src\\recursos\\vida (2).png"));
        vidaIMG.setBounds(520, 640, 50, 50);
        add(vidaIMG);

        try {
            fondo = ImageIO.read(new File("src\\recursos\\fondo.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            InputStream fontStream = Estadisticas.class.getResourceAsStream("/recursos/8-BIT WONDER.ttf");
            customFont = Font.createFont(Font.TRUETYPE_FONT, fontStream);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }

    public void setVidas(int vidas) {
        this.vidas = vidas;
        repaint();
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.red);
        Graphics2D g2 = (Graphics2D) g;

        if (fondo != null) {
            g2.drawImage(fondo, 0, 0, this);
        }

        if (customFont != null) {
            g.setFont(customFont.deriveFont(25f));
        } else {
            g.setFont(new Font("Arial", Font.PLAIN, 40));
        }

        g.drawString("Puntos", getWidth() - 166, getHeight() - 300);
        g.drawString("TOP ", getWidth() - 135, getHeight() - 600);
        g.setColor(Color.WHITE);
        g.drawString("" + puntos, getWidth() - 150, getHeight() - 250);
        g.drawString("X" + vidas, getWidth() - 100, getHeight() - 80);
        g.drawString("", getWidth() - 110, getHeight() - 550);
    }
    
}
