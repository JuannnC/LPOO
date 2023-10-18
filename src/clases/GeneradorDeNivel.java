package clases;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;


public class GeneradorDeNivel {
    public int nivel[][];
    public int ANCHO, ALTO;
    
    public GeneradorDeNivel(int fila, int col) {
        
        nivel= new int[fila][col];
        
        for(int i=0;i<fila;i++) {
            for (int j=0;j<col;j++) {
                nivel[i][j] = 1;
            }
        }
        
        ANCHO = 400 / col;
        ALTO = 200/ fila;
    }
    
    public void setLadrillo(int valor,int f, int c) {
        nivel[f][c]=valor;
    }
    
    public void draw(Graphics2D g) {
        
        for (int i=0;i<nivel.length;i++) {
            for (int j=0;j<nivel[0].length;j++) {
                if (nivel[i][j]>0) {
                    g.setColor(Color.white);
                    g.fillRect(j*ANCHO + 50, i*ALTO + 80, ANCHO, ALTO);
                    
                    g.setColor(Color.black);
                    g.setStroke(new BasicStroke(3));
                    g.drawRect(j*ANCHO + 50, i*ALTO + 80, ANCHO, ALTO);
                    
                }
            }
        }
        
    }
    
}
