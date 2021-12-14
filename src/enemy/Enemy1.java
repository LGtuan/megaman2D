package enemy;

import bulletObject.Bullet1;
import effect.Animation;
import effect.LoadData;
import object.GameWorld;
import object.ParticularObject;
import object.SkillObject;

import java.awt.*;

public class Enemy1 extends ParticularObject {

    private Animation enemyForWard;
    private Animation bulletEnemy1;
    private SkillObject skill;
    float x;

    public Enemy1(float posX, float posY, GameWorld gameWorld) {

        super(posX, posY, 40, 40, 1, 20, gameWorld);
        enemyForWard = LoadData.getInstance().getAnimation("enemy1");
        bulletEnemy1 = LoadData.getInstance().getAnimation("bullet_enemy1");

        x = getPosX();
        skill = new SkillObject(1800000000);
        setSpeedX(-1);
    }

    public void move() {

        if (x + 100 < getPosX()) setDirection(left_Dir);
        else if (x - 140 > getPosX()) setDirection(right_Dir);


        if (getDirection() == left_Dir) setSpeedX(-1.5f);
        else setSpeedX(1.5f);
        setPosX(getPosX()+getSpeedX());
    }

    @Override
    public Rectangle getBoundForCollisionWithEnemy() {
        return getBoundForCollisionWithMap();
    }

    public void attack(){
        skill.setTimeStartSkill(System.nanoTime());
        setIsAttack(true);
        Bullet1 bullet1_1 = new Bullet1(getPosX(),getPosY(),30,30,bulletEnemy1,this,getGameWorld());
        Bullet1 bullet1_2 = new Bullet1(getPosX(),getPosY(),30,30,bulletEnemy1,this,getGameWorld());

        bullet1_1.setType(getType());
        bullet1_2.setType(getType());

        bullet1_1.setSpeedX(-random());
        bullet1_2.setSpeedX(random());
        bullet1_1.setSpeedY(2.5f);
        bullet1_2.setSpeedY(2.5f);

        getGameWorld().bulletObjectManager.addObject(bullet1_1);
        getGameWorld().bulletObjectManager.addObject(bullet1_2);
    }

    public void update(){

        super.update();
        if(System.nanoTime() - skill.getTimeStartSkill() > skill.getTimeSkill()) attack();
        move();
    }

    public void draw(Graphics2D g2){
        super.draw(g2);
        hp.draw(g2);
        if(getState()==noBehurt && System.currentTimeMillis()%2==0);
        else{
            enemyForWard.update(System.nanoTime());
            enemyForWard.drawImage(g2, (int) (getPosX() - getGameWorld().camera.getPosX()),
                    (int) (getPosY() - getGameWorld().camera.getPosY()));
        }
    }
}
