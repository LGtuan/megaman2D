package enemy;

import bulletObject.Bullet1;
import effect.Animation;
import effect.LoadData;
import object.GameWorld;
import object.ParticularObject;
import object.SkillObject;

import java.awt.*;

public class Robot extends ParticularObject {

    Animation robotForWard,robotBack;
    Animation bulletForWard,bulletBack;
    SkillObject skill;

    int x = 0;

    public Robot(float posX, float posY, GameWorld gameWorld) {
        super(posX, posY, 90, 80, 1, 30, gameWorld);

        robotForWard = LoadData.getInstance().getAnimation("robot");
        robotBack = LoadData.getInstance().getAnimation("robot");

        bulletForWard = LoadData.getInstance().getAnimation("bulletrobot");
        bulletForWard.flipAllImage();
        bulletBack = LoadData.getInstance().getAnimation("bulletrobot");

        robotBack.flipAllImage();
        skill = new SkillObject(800000000);
        skill.setTimeStartSkill(System.nanoTime());
        setIsAttack(true);
    }

    public void update(){
        super.update();
        if(System.nanoTime()-skill.getTimeStartSkill() > skill.getTimeSkill() ||
                System.nanoTime()-skill.getTimeStartSkill()>skill.getTimeSkill()-300000000){
            attack();
        }
        if(getPosX()<getGameWorld().megaman.getPosX()) setDirection(right_Dir);
        else setDirection(left_Dir);
    }

    public void attack(){
        if(x%2==0) {
            skill.setTimeStartSkill(System.nanoTime());
        }
        Bullet1 bullet;
        if(getDirection()==left_Dir){
            bullet = new Bullet1(getPosX(),getPosY(),35,10,bulletForWard,this,getGameWorld());
            bullet.setSpeedX(-3);
        }
        else{
            bullet = new Bullet1(getPosX(),getPosY(),35,10,bulletBack,this,getGameWorld());
            bullet.setSpeedX(3);
        }

        bullet.setType(getType());
        getGameWorld().bulletObjectManager.addObject(bullet);
    }

    public void draw(Graphics2D g2){
        hp.draw(g2);
        if(getState()==noBehurt && System.currentTimeMillis()%2==0);
        else drawLeftOrRightAnimation(robotForWard, robotBack, g2);
    }
}
