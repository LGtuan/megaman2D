package user;

import object.GameWorld;
import object.ParticularObject;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GamePanel extends JPanel implements Runnable, KeyListener {

    private BufferedImage buff;
    private BufferedImage bgMap;
    private BufferedImage bgStart;
    private Graphics2D g2;
    private Thread thread;
    private boolean isRunning;
    private InputManager inputManager;
    private GameWorld gameWorld;
    private BufferedImage victoryGame;


    public GamePanel(){

        gameWorld = new GameWorld();
        inputManager = new InputManager(gameWorld);
        buff = new BufferedImage(GameFrame.Screen_Width,
                GameFrame.Screen_Height,BufferedImage.TYPE_INT_ARGB);
        g2 = (Graphics2D) buff.getGraphics();
        try {
            bgMap = ImageIO.read(new File("data/unnamed.png"));
            victoryGame = ImageIO.read(new File("data/vtrr.png"));
            bgStart = ImageIO.read(new File("data/startGame.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startGame(){
        thread = new Thread(this);
        isRunning = true;
        thread.start();

    }

    public void paint(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(buff,0,0,this);

    }

    public void update(){
        gameWorld.update();
    }

    public void render(){
        if(buff!=null)
            buff = new BufferedImage(GameFrame.Screen_Width,
                    GameFrame.Screen_Height,BufferedImage.TYPE_INT_ARGB);

        if(buff!=null) {
            g2 = (Graphics2D) buff.getGraphics();
        }
        if(g2!=null) {
            if(gameWorld.isStart()) g2.drawImage(bgStart,0,0,this);
            else if(gameWorld.isEndGame()){
                g2.drawImage(victoryGame,340,150,this);
            }
            else{
                g2.drawImage(bgMap, 0, 0, this);
                gameWorld.draw(g2);
            }
        }
    }

    @Override
    public void run() {

        int PTS = 60;
        long period = 1000*1000000/PTS;
        long sleepTime;
        long beginTime=System.nanoTime();
        long currentTime;

        while(isRunning){

            update();
            render();
            repaint();

            currentTime = System.nanoTime()-beginTime;
            sleepTime = period - currentTime;

            try {
                if(sleepTime>0)
                    Thread.sleep(sleepTime/1000000);
            } catch (InterruptedException e) {e.printStackTrace();}

            beginTime = System.nanoTime();

        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(gameWorld.megaman.getState() != ParticularObject.behurt)
            inputManager.keyPressed(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {

        inputManager.keyRelease(e.getKeyCode());
    }

}
