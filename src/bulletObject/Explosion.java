package bulletObject;

import effect.Animation;
import effect.LoadData;
import object.GameWorld;
import object.ParticularObject;
import object.SkillObject;

import java.awt.*;

public class Explosion extends Bullet{

    private Animation animation;
    private SkillObject skill;

    public Explosion(float posX, float posY, GameWorld gameWorld) {
        super(posX, posY, 40, 50, 1, 10, gameWorld);

        animation = LoadData.getInstance().getAnimation("explosion");
        skill = new SkillObject(2000000000);
        skill.setTimeStartSkill(System.nanoTime());
    }

    @Override
    public void update(){
        ParticularObject obj = getGameWorld().particularObjectManager.collisionObject(this);
        if (obj != null && obj.getState() == alive) {
            obj.behurt(getDame());
        }
        if(System.nanoTime()-skill.getTimeStartSkill()>skill.getTimeSkill()*1.2){
            getGameWorld().bulletObjectManager.removeObject(this);
        }
    }

    public void draw(Graphics2D g2) {
        super.draw(g2);
        animation.update(System.nanoTime());
        animation.drawImage(g2,(int)(getPosX()-getGameWorld().camera.getPosX()),
                (int)(getPosY()-getGameWorld().camera.getPosY()));
    }

}

