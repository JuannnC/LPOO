package clases;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import obtenerRecursos.GestorSonidos;


public class Menu extends JPanel{
    private final Ventana ventana;
    private Font customFont;
    private BufferedImage fondo;
    
    public Menu(Ventana ventana) throws FontFormatException {
        this.ventana = ventana;
        
        GestorSonidos gestorSonidos = new GestorSonidos();
        gestorSonidos.cargarSonido("musica", "src\\recursos\\01-Regressive-Trip-Debug.wav");
        gestorSonidos.cargarSonido("boton", "src\\recursos\\confirm-38513.wav");
        gestorSonidos.cargarSonido("musicalv1", "src\\recursos\\05-Microwave-Starship-Phase00.wav");
        
        gestorSonidos.reproducirSonidoEnBucle("musica");
        gestorSonidos.ajustarVolumen("musica", -15.0f);
        
        
        setBackground(Color.BLACK);
        setLayout(null);
        
        try {
            // Carga la imagen de fondo
            fondo = ImageIO.read(new File("src\\recursos\\fondomenu (1).jpg")); // Cambia "background.jpg" al nombre de tu imagen
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        JButton Jugar = new JButton("JUGAR");
        Jugar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                    ventana.botonJugar();
                    gestorSonidos.reproducirSonido("boton");
                    gestorSonidos.detenerSonido("musica");
                    gestorSonidos.reproducirSonidoEnBucle("musicalv1");
                    gestorSonidos.ajustarVolumen("musicalv1", -15.0f);
        	}
        });
        Jugar.setForeground(Color.WHITE);
        Jugar.setFont(new Font("Tahoma", Font.PLAIN, 20));
        Jugar.setBounds(230, 335, 170, 40);
        add(Jugar);
        Jugar.setBackground(null);
        Jugar.setBorderPainted(false);
        Jugar.setFocusPainted(false);
        
        JButton Opciones = new JButton("OPCIONES");
        Opciones.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                    gestorSonidos.reproducirSonido("boton");
        	}
        });
        Opciones.setForeground(Color.WHITE);
        Opciones.setFont(new Font("Tahoma", Font.PLAIN, 20));
        Opciones.setBackground((Color) null);
        Opciones.setBounds(218, 405, 200, 40);
        add(Opciones);
        Opciones.setBorderPainted(false);
        Opciones.setFocusPainted(false);
        
        JButton Salir = new JButton("SALIR");
        Salir.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                    gestorSonidos.reproducirSonido("boton");
                    System.exit(0);
        	}
        });
        Salir.setForeground(Color.WHITE);
        Salir.setFont(new Font("Tahoma", Font.PLAIN, 20));
        Salir.setBackground((Color) null);
        Salir.setBounds(230, 475, 170, 40);
        add(Salir);
        Salir.setBorderPainted(false);
        Salir.setFocusPainted(false);
        
        JLabel lpoo = new JLabel("LPOO");
        lpoo.setFont(new Font("Tahoma", Font.PLAIN, 40));
        lpoo.setForeground(Color.RED);
        lpoo.setBounds(277, 620, 140, 40);
        add(lpoo);
        
        JLabel copy = new JLabel("COPYRIGHT 2023 LPOO CORP");
        copy.setFont(new Font("Tahoma", Font.PLAIN, 15));
        copy.setForeground(Color.BLACK);
        copy.setBounds(100, 660, 450, 40);
        add(copy);
        
        JLabel copy2 = new JLabel("ALL RIGHTS RESERVED");
        copy2.setForeground(Color.BLACK);
        copy2.setFont(new Font("Tahoma", Font.PLAIN, 15));
        copy2.setBounds(140, 680, 350, 40);
        add(copy2);
        
        try {
            InputStream fontStream = Estadisticas.class.getResourceAsStream("/recursos/8-BIT WONDER.ttf");
            customFont = Font.createFont(Font.TRUETYPE_FONT, fontStream);
            customFont = customFont.deriveFont(20f);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        
        if (customFont != null) {
            Jugar.setFont(customFont);
            Opciones.setFont(customFont);
            Salir.setFont(customFont);
            lpoo.setFont(customFont);
            copy.setFont(customFont);
            copy2.setFont(customFont);
        }
        
        JLabel logo = new JLabel();
        logo.setIcon(new ImageIcon("src\\recursos\\image (1).png"));
        logo.setBounds(90, 84, 450, 153);
        add(logo);
     }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Dibuja la imagen de fondo
        if (fondo != null) {
            g2.drawImage(fondo, 0, 0, this);
        }
    }
    
}
