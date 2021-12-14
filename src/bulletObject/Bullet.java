package bulletObject;

import effect.Animation;
import object.GameWorld;
import object.ParticularObject;

import java.awt.*;

public abstract class Bullet extends ParticularObject {
    public Bullet(float posX, float posY, float width, float height, float mass, int blood, GameWorld gameWorld) {
        super(posX, posY, width, height, mass, blood, gameWorld);
    }

    public void update(){
        //super.update();

        ParticularObject object = getGameWorld().particularObjectManager.collisionObject(this);
        ParticularObject object2 = getGameWorld().bulletObjectManager.collisionObject(this);
        if(object2!=null) getGameWorld().bulletObjectManager.removeObject(this);
        if(object!=null && object.getState()==alive){
            getGameWorld().bulletObjectManager.removeObject(this);
            object.behurt(this.getDame());
        }
        if(getSpeedX()>0) setDirection(right_Dir);
        else setDirection(left_Dir);
        setPosX(getPosX()+getSpeedX());
        setPosY(getPosY()+getSpeedY());
    }

    public void drawAnimation(Animation animation, Graphics2D g2) {

        animation.update(System.nanoTime());
        if (animation.getCurrentFrame() == 3 && !animation.getIgnoreFrame(0)) {
            setIgnore(animation);
        }

        animation.draw((int) (getPosX() - getGameWorld().camera.getPosX()),
                (int) (getPosY() - getGameWorld().camera.getPosY()),g2,getGameWorld().boss1.getDirection());
    }

    public void setIgnore(Animation animation) {
        animation.setIgnoreFrame(0);
        animation.setIgnoreFrame(1);
        animation.setIgnoreFrame(2);
    }
}
