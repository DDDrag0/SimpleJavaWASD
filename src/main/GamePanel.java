package main;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    //questo jpanel lavorera' come se fosse uno schermo di gioco
    //screen settings
    final int originalTileSize = 32; // 32x32 sara' la dimensione dei tile di gioco, quindi texture ecc saranno di 32 pixels
    final int scale = 2;

    final int tileSize = originalTileSize * scale; //con questo c'e' un resize dei pixel, adesso che abbiamo il resize di 2, i nostri pixel saranno reinderizzati da 32 a 64 pixel
    final int maxScreenCol = 20; //questi sono i tile che vengono reinderizzati orizzontalmente
    final int maxScreenRow = 13; //questi sono i tile che vengono reinderizzati verticalmente
    final int screenWidth = tileSize * maxScreenCol; //questa sara' la risoluzione della finestra orizzontalmente in pixel
    final int screenHeight = tileSize * maxScreenRow; //questa sara' la risoluzione della finestra verticalmente in pixel

    int fps = 60;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread; //dichiarare il thread permette al gioco di aver un clock di esecuzione per far si che il tutto funzioni e sia vivo

    //variabili per le posizioni del player
    int ppositionX=100;
    int ppositionY=100;
    int pspeed=4;


    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); //in questo modo abbiamo settato la dimenzione della classe JPanel
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); //serve per migliorare le performance di rendering
        this.addKeyListener(keyH);  //cosi' che il gamepanel possa riconoscere gli input delle key
        this.setFocusable(true);    //con questo permettiamo al gamepanel di essere focusato per poter prendere gli input delle key
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() { //classe che viene richiamata dal thread
        double drawInterval= 1000000000/fps;
        double delta=0;
        long lastTime = System.nanoTime();
        long currentTime;

        while(gameThread != null){
            currentTime=System.nanoTime();
            delta+=(currentTime-lastTime)/drawInterval;
            lastTime=currentTime;
            if(delta>=1){
                update();
                repaint();
                delta--;
            }
        }

    }

    public void update(){   //la classe che si occupera' dell'update di quello che non vediamo
        if(keyH.upPressed){
            ppositionY-=pspeed;
        }
        if(keyH.downPressed){
            ppositionY+=pspeed;
        }
        if(keyH.leftPressed){
            ppositionX+=pspeed;
        }
        if(keyH.rightPressed){
            ppositionX-=pspeed;
        }

    }

    public void paintComponent(Graphics g){ //la classe che si occupera' dell'update grafico
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g; //la graphics 2d estende le funzionalita' della classe graphics per avere un controllo migliore su alcune funzionalita'
        g2.setColor(Color.white);
        g2.drawRect(ppositionX,ppositionY, tileSize,tileSize);    //disegnamo una figura rettangolare
        g2.dispose();   //con questo rilasciamo e disegnamo la nostra risorsa

    }
}
