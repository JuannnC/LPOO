package obtenerRecursos;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javax.sound.sampled.FloatControl;

public class GestorSonidos {
    private Map<String, Clip> sonidos;

    public GestorSonidos() {
        sonidos = new HashMap<>();
    }

    public void cargarSonido(String nombre, String ruta) {
        try {
            File soundFile = new File(ruta);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            Clip soundClip = AudioSystem.getClip();
            soundClip.open(audioInputStream);
            sonidos.put(nombre, soundClip);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void reproducirSonido(String nombre) {
        Clip soundClip = sonidos.get(nombre);
        if (soundClip != null) {
            soundClip.setFramePosition(0);
            soundClip.start();
        }
    }
    
    public void reproducirSonidoEnBucle(String nombre) {
        Clip soundClip = sonidos.get(nombre);
        if (soundClip != null) {
            soundClip.setFramePosition(0);
            soundClip.loop(Clip.LOOP_CONTINUOUSLY);
            soundClip.start();
        }
    }
    
    public void detenerSonido(String nombre) {
        Clip soundClip = sonidos.get(nombre);
        if (soundClip != null) {
            soundClip.stop();
        }
    }
    
    public void ajustarVolumen(String nombre, float volumen) {
    Clip soundClip = sonidos.get(nombre);
    if (soundClip != null) {
        FloatControl gainControl = (FloatControl) soundClip.getControl(FloatControl.Type.MASTER_GAIN);
        // El valor de 'volumen' es un valor en decibelios (dB), generalmente negativo
        // Puedes ajustar el valor de 'volumen' según tus necesidades
        gainControl.setValue(volumen);
    }
}
}