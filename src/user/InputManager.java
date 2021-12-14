package user;

import java.awt.event.KeyEvent;
import object.GameWorld;
import object.ParticularObject;

public class InputManager {

    GameWorld gameWorld;

    public InputManager(GameWorld gameWorld){
        this.gameWorld = gameWorld;
    }

    public void keyPressed(int keyCode){

        switch(keyCode){
            case KeyEvent.VK_DOWN:

                break;

            case KeyEvent.VK_LEFT:
                gameWorld.megaman.setDirection(ParticularObject.left_Dir);
                gameWorld.megaman.run();
                break;

            case KeyEvent.VK_RIGHT:
                gameWorld.megaman.setDirection(ParticularObject.right_Dir);
                gameWorld.megaman.run();
                break;

            case KeyEvent.VK_SPACE:
                gameWorld.megaman.jump();
                break;

            case KeyEvent.VK_ENTER:
                gameWorld.setStart(false);
                break;

            case KeyEvent.VK_UP:

                break;

            case KeyEvent.VK_A:
                gameWorld.megaman.attack();
                break;

            case KeyEvent.VK_S:
                gameWorld.megaman.cut();
                break;

            case KeyEvent.VK_D:
        }

    }

    public void keyRelease(int keyCode){
        switch(keyCode){
            case KeyEvent.VK_DOWN:

                break;

            case KeyEvent.VK_LEFT:
                if(gameWorld.megaman.getSpeedX()<0)
                    gameWorld.megaman.stopRun();
                break;

            case KeyEvent.VK_RIGHT:
                if(gameWorld.megaman.getSpeedX()>0)
                    gameWorld.megaman.stopRun();
                break;

            case KeyEvent.VK_SPACE:

                break;

            case KeyEvent.VK_ENTER:

                break;

            case KeyEvent.VK_UP:

                break;

            case KeyEvent.VK_A:

                break;

            case KeyEvent.VK_S:

                break;

            case KeyEvent.VK_D:
        }
    }

}
