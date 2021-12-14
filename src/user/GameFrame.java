package user;

import effect.LoadData;

import javax.swing.*;
import java.io.IOException;

public class GameFrame extends JFrame {

    public static final int Screen_Width = 1000;
    public static final int Screen_Height = 600;
    private GamePanel gamePanel;

    public GameFrame(){

        this.setSize(Screen_Width,Screen_Height);
        this.setLocationRelativeTo(null);
        this.setTitle("GAME HAY VÃ ÒNG");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try {
            LoadData.getInstance().loadData();
        } catch (IOException e) {
            e.printStackTrace();
        }

        gamePanel = new GamePanel();
        this.add(gamePanel);
        this.addKeyListener(gamePanel);
    }

    public void startGame(){
        gamePanel.startGame();
        this.setVisible(true);
    }

    public static void main(String[] args) {
        GameFrame gameFrame = new GameFrame();
        gameFrame.startGame();
    }

}
