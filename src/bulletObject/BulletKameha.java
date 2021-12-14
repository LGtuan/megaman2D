package bulletObject;

import effect.Animation;
import effect.LoadData;
import object.GameWorld;
import object.ParticularObject;

import java.awt.*;

public class BulletKameha extends Bullet{
    private Animation kamehaForWard,kamehaBack;

    public BulletKameha(float posX, float posY, GameWorld gameWorld) {
        super(posX, posY, 520, 45, 1.0f, 10, gameWorld);

        kamehaForWard = LoadData.getInstance().getAnimation("bullet_kameha_right");
        kamehaBack = LoadData.getInstance().getAnimation("bullet_kameha_left");


    }

    @Override
    public void update(){

        if (getGameWorld().megaman.getPosX() > getPosX()) setDirection(right_Dir);
        else setDirection(left_Dir);

        if(System.nanoTime() - getGameWorld().boss1.getSkillKameha().getTimeStartSkill() >getGameWorld().boss1.getSkillKameha().getTimeSkill())
            getGameWorld().bulletObjectManager.removeObject(this);

        ParticularObject obj = getGameWorld().particularObjectManager.collisionObject(this);
        if(obj!=null && obj.getState()==alive){
            obj.setState(behurt);
            obj.setBlood(obj.getBlood()-getDame());
        }
    }

    @Override
    public Rectangle getBoundForCollisionWithEnemy() {
        Rectangle rect = getBoundForCollisionWithMap();
        if(getGameWorld().boss1.getDirection()==left_Dir)rect.x-=getWidth()/2;
        else rect.x+=getWidth()/2;
        return rect;
    }



    @Override
    public void draw(Graphics2D g2){
        //super.draw(g2);
        if(getGameWorld().boss1.getDirection()==right_Dir)
            drawAnimation(kamehaForWard, g2);
        else drawAnimation(kamehaBack, g2);

    }
}
