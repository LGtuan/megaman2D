package object;

import protagonist.Megaman;

import java.awt.*;

public class Hp extends GameObject {

    ParticularObject particularObject;

    private int beginBlood;

    private float percent;

    private float width;
    private float height;

    private float x = 0;

    public Hp(float posX, float posY, ParticularObject particularObject, GameWorld gameWorld) {
        super(posX, posY, gameWorld);
        this.particularObject = particularObject;

        width = 40;
        height = 10;

        beginBlood = particularObject.getBlood();
        x = width/beginBlood;
    }


    @Override
    public void update() {

        percent = particularObject.getBlood()*x;
        setPosX(particularObject.getPosX());
        setPosY(particularObject.getBoundForCollisionWithMap().y - 10);

    }

    public void draw(Graphics2D g2) {
        g2.setColor(Color.black);
        g2.fillRect((int) (getPosX() - width / 2-getGameWorld().camera.getPosX()), (int) (getPosY() - getGameWorld().camera.getPosY()), (int) width, (int) height);
        g2.setColor(Color.red);
        if(particularObject instanceof Megaman) g2.setColor(Color.green);
        g2.fillRect((int) (getPosX() - width / 2 -getGameWorld().camera.getPosX()), (int) (getPosY() - getGameWorld().camera.getPosY()), (int) percent, (int) height);
    }
}
