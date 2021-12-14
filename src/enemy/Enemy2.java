package enemy;

import bulletObject.Bullet2;
import effect.Animation;
import effect.LoadData;
import object.GameWorld;
import object.ParticularObject;
import object.SkillObject;

import java.awt.*;

public class Enemy2 extends ParticularObject {

    private Animation enemyForWard,enemyBack;

    private float x1,x2,y1,y2;
    private SkillObject skill;

    public Enemy2(float posX, float posY, GameWorld gameWorld) {
        super(posX, posY, 130, 90, 1, 50, gameWorld);

        enemyForWard = LoadData.getInstance().getAnimation("enemy2");
        enemyForWard.flipAllImage();
        enemyBack = LoadData.getInstance().getAnimation("enemy2");

        skill = new SkillObject(2100000000);

        x1=getPosX()-100;
        y1=getPosY()-50;
        x2 = getPosX()+100;
        y2=getPosY()+50;
        setSpeedX(-1);
        setSpeedY(1);
    }

    public void attack(){
        skill.setTimeStartSkill(System.nanoTime());

        Bullet2 bullet2_1 = new Bullet2(getPosX(),getPosY(),getGameWorld());
        Bullet2 bullet2_2 = new Bullet2(getPosX(),getPosY(),getGameWorld());

        if(getDirection()==right_Dir) {
            bullet2_1.setSpeedX(random());
            bullet2_2.setSpeedX(random());
        }else{
            bullet2_1.setSpeedX(-random());
            bullet2_2.setSpeedX(-random());
        }

        bullet2_1.setSpeedY(random());
        bullet2_2.setSpeedY(random());

        bullet2_1.setType(getType());
        bullet2_2.setType(getType());
        getGameWorld().bulletObjectManager.addObject(bullet2_2);
        getGameWorld().bulletObjectManager.addObject(bullet2_1);
    }

    public void update(){
        super.update();

        if(System.nanoTime()-skill.getTimeStartSkill()>skill.getTimeSkill()) attack();

        if(getGameWorld().megaman.getPosX()>getPosX()) setDirection(right_Dir);
        else setDirection(left_Dir);
        if(getPosX()<x1) setSpeedX(random());
        else if(getPosX()>x2) setSpeedX(-random());
        setPosX(getPosX()+getSpeedX());

        if(getPosY()<y1) setSpeedY(random());
        else if(getPosY()>y2) setSpeedY(-random());
        setPosY(getPosY()+getSpeedY());
    }

    public void draw(Graphics2D g2){
        hp.draw(g2);
        drawLeftOrRightAnimation(enemyForWard,enemyBack,g2);
    }
}
