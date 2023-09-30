package main;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        GamePanel gamepanel = new GamePanel();

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //questo fa chiudere per bene la finestra quando clicchiamo sul bottone x
        window.setResizable(false);
        window.setTitle("Prova gioco 2D"); //questo sara' il titolo che andra' sulla finestra
        window.add(gamepanel); //qui stiamo aggiungendo le informazioni per quanto riguarda il gamepanel
        window.pack();
        window.setLocationRelativeTo(null); //serve per settare dove la window sara' mostrata, in questo caso al centro dello schermo
        window.setVisible(true);

        gamepanel.startGameThread();
    }
}