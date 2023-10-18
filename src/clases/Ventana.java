package clases;


import java.awt.FontFormatException;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import obtenerRecursos.GestorSonidos;



public class Ventana extends JFrame{
    private final int ANCHO=670, ALTO=760;
    private final Juego lamina;
    final Estadisticas tabla;
    private final Hilo hilo;
    final Menu menuini;
    private GestorSonidos gestorSonidos;
    
    public Ventana() throws FontFormatException{
        setTitle("SUPER PONG 20XX");
        setSize(ANCHO,ALTO);
        setLocationRelativeTo(null); //ubicando la ventana en el centro de la pantalla
        setResizable(false);
        
        gestorSonidos = new GestorSonidos();
        gestorSonidos.cargarSonido("pierdeVida", "src\\recursos\\hurt_c_08-102842.wav");
        gestorSonidos.cargarSonido("rebote1", "src\\recursos\\rebote_pelota1.wav");
        gestorSonidos.cargarSonido("rebote2", "src\\recursos\\rebote_pelota2.wav");
        
        JLayeredPane layeredPane = getLayeredPane();
        
        int initialVidas = 3;
        int initialPuntos = 0;
        
        menuini = new Menu(this);
        add(menuini);
        menuini.setBounds(0, 0, ANCHO, ALTO);
        layeredPane.add(menuini, JLayeredPane.DEFAULT_LAYER);
        
        // Crear un panel contenedor
        tabla = new Estadisticas(initialVidas, initialPuntos);
        add(tabla);
        tabla.setBounds(0, 0, ANCHO, ALTO);
        layeredPane.add(tabla, JLayeredPane.MODAL_LAYER);
        tabla.setVisible(false);

        // Configurar el juego
        lamina = new Juego(tabla, gestorSonidos);
        lamina.setBounds(0, 0, 500, 721);
        tabla.add(lamina);
        
        addKeyListener(new EventoTeclado());
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        hilo = new Hilo(lamina);
        hilo.start();
    }
    public void botonJugar() {
        menuini.setVisible(false);
        tabla.setVisible(true);
        requestFocus();
    }
}
