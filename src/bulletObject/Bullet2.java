package bulletObject;

import effect.Animation;
import effect.LoadData;
import object.GameWorld;
import object.ParticularObject;

import java.awt.*;

public class Bullet2 extends Bullet{
    private Animation bullet;

    public Bullet2(float posX, float posY, GameWorld gameWorld) {
        super(posX, posY, 25, 23, 1.0f, 5, gameWorld);
        bullet = LoadData.getInstance().getAnimation("bullet_enemy3");
        setDame(10);
    }

    @Override
    public void update() {

        setPosX(getPosX() + getSpeedX());
        setPosY(getPosY() + getSpeedY());
        if (getGameWorld().physicalMap.haveCollisionWithLand(getBoundForCollisionWithEnemy()) != null) {
            Rectangle rect = getGameWorld().physicalMap.haveCollisionWithLand(getBoundForCollisionWithEnemy());
            innitExplosion(rect,-10,20);
        }
        ParticularObject obj = getGameWorld().particularObjectManager.collisionObject(this);

        if (obj != null && obj.getState() == alive) {
            obj.behurt(getDame());
            innitExplosion(obj.getBoundForCollisionWithEnemy(),-10,-40);
        }

    }

    public void innitExplosion(Rectangle rect,int x,int y) {
        Explosion explosion = new Explosion(rect.x-x, rect.y-y, getGameWorld());
        explosion.setType(getType());
        getGameWorld().bulletObjectManager.addObject(explosion);
        setState(death);
    }

    @Override
    public void draw(Graphics2D g2) {
        super.draw(g2);
        bullet.update(System.nanoTime());
        bullet.drawImage(g2,(int) (getPosX() - getGameWorld().camera.getPosX()),
                (int) (getPosY() - getGameWorld().camera.getPosY()));
    }

}
