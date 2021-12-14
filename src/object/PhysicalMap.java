package object;

import effect.LoadData;

import java.awt.*;

public class PhysicalMap extends GameObject{
    private int[][] physMap;
    private int tilesize;

    public PhysicalMap(float posX,float posY,GameWorld gameWorld){
        super(posX, posY, gameWorld);

        physMap = LoadData.getInstance().getPhysMap();
        tilesize = 30;
    }

    public Rectangle haveCollisionWithLand(Rectangle rect){

        int x = rect.x/tilesize;
        int y = (rect.y+rect.height)/tilesize;

        for (int i = y; i <= y+1; i++) {

            for (int j = x; j <= x+2; j++) {

                if(physMap[i][j] !=0 ){
                    Rectangle r = new Rectangle((int)(getPosX()+j*tilesize),
                            (int)(getPosX()+i*tilesize),tilesize,tilesize);

                    if(r.intersects(rect)){
                        return r;
                    }

                }
            }
        }
        return null;
    }

    public Rectangle haveCollisionWithLeft(Rectangle rect){
        int x = rect.x/tilesize;
        int y = rect.y/tilesize;



        for (int i = y; i <= y+2; i++) {

            for (int j = x; j >= x-1; j--) {

                if(physMap[i][j] !=0){
                    Rectangle r = new Rectangle((int)(getPosX()+j*tilesize),
                            (int)(getPosX()+i*tilesize),tilesize,tilesize);

                    if(r.intersects(rect)){
                        return r;
                    }

                }
            }
        }
        return null;
    }

    public Rectangle haveCollisionWithTop(Rectangle rect){
        int x = rect.x/tilesize;
        int y = rect.y/tilesize;

        for (int i = y+1; i >= y-1; i--) {

            for (int j = x; j <= x+2; j++) {

                if(physMap[i][j] !=0){
                    Rectangle r = new Rectangle((int)(getPosX()+j*tilesize),
                            (int)(getPosX()+i*tilesize),tilesize,tilesize);

                    if(r.intersects(rect)){
                        return r;
                    }

                }
            }
        }
        return null;

    }

    public Rectangle haveCollisionWithRight(Rectangle rect){
        int x = (rect.x+rect.width)/tilesize;
        int y = rect.y/tilesize;

        for (int i = y; i <= y+3; i++) {

            for (int j = x+1; j >= x-1; j--) {

                if(physMap[i][j] != 0){
                    Rectangle r = new Rectangle((int)(getPosX()+j*tilesize),
                            (int)(getPosX()+i*tilesize),tilesize,tilesize);

                    if(r.intersects(rect)){
                        return r;
                    }

                }
            }
        }
        return null;
    }


    @Override
    public void update() {
        if(getGameWorld().megaman.getPosX()>5120 && getGameWorld().megaman.getPosY()>600){
            physMap[32][167] =1;
            physMap[33][167] =1;
            physMap[34][167] =1;
            physMap[35][167] =1;
            physMap[32][168] =1;
            physMap[33][168] =1;
            physMap[34][168] =1;
            physMap[35][168] =1;
        }
    }

    public void draw(Graphics2D g2) {
        g2.setColor(Color.blue);
        for (int i = 0; i < physMap.length; i++) {
            for (int j = 0; j < physMap[0].length; j++) {

                if (physMap[i][j] == 1)
                    g2.fillRect((int) (j * tilesize + getPosX() - getGameWorld().camera.getPosX())
                            , (int) (i * tilesize + getPosY() - getGameWorld().camera.getPosY()),
                            tilesize, tilesize);

            }
        }
    }
}
