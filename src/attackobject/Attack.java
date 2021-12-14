package attackobject;

import object.GameWorld;
import object.ParticularObject;

import java.awt.*;

public class Attack extends ParticularObject {

    ParticularObject particularObject;

    public Attack(float posX, float posY, float width, float height,ParticularObject particularObject, GameWorld gameWorld) {
        super(posX, posY, width, height, 1, 1, gameWorld);
        setDame(15);
        this.particularObject = particularObject;
    }

    @Override
    public void update() {
        setPosX(getPosX()+ particularObject.getSpeedX());

        if (!particularObject.getIsAttack()){
            getGameWorld().particularObjectManager.removeObject(this);
        }

        ParticularObject object = getGameWorld().particularObjectManager.collisionObject(this);
        if(object!=null && object.getState() == alive){
            object.behurt(getDame());
        }

    }

    @Override
    public Rectangle getBoundForCollisionWithEnemy() {
        return getBoundForCollisionWithMap();
    }

    public void draw(Graphics2D g2){
        super.draw(g2);
    }
}
