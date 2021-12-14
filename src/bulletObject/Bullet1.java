package bulletObject;

import effect.Animation;
import object.GameWorld;
import object.ParticularObject;

import java.awt.*;

public class Bullet1 extends Bullet{

    Animation bullet;
    ParticularObject obj;
    public Bullet1(float posX, float posY, float width, float height, Animation animation, ParticularObject obj, GameWorld gameWorld) {
        super(posX, posY, width, height, 1.0f, 5, gameWorld);
        setDame(10);
        bullet = new Animation(animation);
        this.obj = obj;
    }

    @Override
    public Rectangle getBoundForCollisionWithEnemy() {
        return getBoundForCollisionWithMap();
    }

    @Override
    public void update(){
        super.update();
        if(!obj.getIsAttack()) getGameWorld().bulletObjectManager.removeObject(this);
        setPosX(getPosX()+getSpeedX());
        setPosY(getPosY()+getSpeedY());
    }

    @Override
    public void draw(Graphics2D g2) {
        super.draw(g2);
        if (bullet != null) {
            bullet.update(System.nanoTime());
            bullet.drawImage(g2,(int) (getPosX() - getGameWorld().camera.getPosX()),
                    (int) (getPosY() - getGameWorld().camera.getPosY()));
        }
    }

}
