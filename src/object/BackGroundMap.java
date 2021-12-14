package object;

import effect.LoadData;
import user.GameFrame;

import java.awt.*;

public class BackGroundMap extends GameObject{

    private int[][] bgMap;
    private int tileSize;
    public BackGroundMap(float posX, float posY, GameWorld gameWorld) {
        super(posX, posY, gameWorld);

        bgMap = LoadData.getInstance().getPhysMap();
        tileSize = 30;
    }

    @Override
    public void update() {

    }

    public void draw(Graphics2D g2){

        for (int i=0;i<bgMap.length;i++){
            for (int j=0;j<bgMap[0].length;j++){
                if(bgMap[i][j]==0 || j*30+30<getGameWorld().camera.getPosX() ||
                        j*30>getGameWorld().camera.getPosX()+ GameFrame.Screen_Width ||
                        i*30+30<getGameWorld().camera.getPosY() ||
                        i*30>getGameWorld().camera.getPosY()+GameFrame.Screen_Height ) continue;

                g2.drawImage(LoadData.getInstance().getFrameImage("tiled"+bgMap[i][j]).getImage(),
                        (int)(getPosX()+j*tileSize-getGameWorld().camera.getPosX()),
                        (int)(getPosY()+i*tileSize-getGameWorld().camera.getPosY()),null);
            }
        }
    }
}
