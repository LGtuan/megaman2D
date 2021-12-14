package enemy;

import bulletObject.Bullet1;
import effect.Animation;
import effect.LoadData;
import object.GameWorld;
import object.ParticularObject;
import object.SkillObject;

import java.awt.*;

public class Enemy5 extends ParticularObject {
    private Animation enemyForWard;
    private Animation enemyBack;
    private Animation bulletEnemy;
    private SkillObject skill;
    float x;

    public Enemy5(float posX, float posY, GameWorld gameWorld) {

        super(posX, posY, 50, 40, 1, 30, gameWorld);
        enemyForWard = LoadData.getInstance().getAnimation("darkraise");
        enemyForWard.flipAllImage();
        enemyBack = LoadData.getInstance().getAnimation("darkraise");

        bulletEnemy = LoadData.getInstance().getAnimation("bullet_enemy2");

        x = getPosX();
        skill = new SkillObject(1800000000);
        setSpeedX(-1);
        setIsAttack(true);
    }

    public void move() {

        if (x + 100 < getPosX()) setDirection(left_Dir);
        else if (x - 140 > getPosX()) setDirection(right_Dir);


        if (getDirection() == left_Dir) setSpeedX(-1.5f);
        else setSpeedX(1.5f);
        setPosX(getPosX()+getSpeedX());
    }

    public void attack(){
        skill.setTimeStartSkill(System.nanoTime());
        float megaManX = getGameWorld().megaman.getPosX();
        float megaManY = getGameWorld().megaman.getPosY();

        float deltaX = megaManX - getPosX();
        float deltaY = megaManY - getPosY();

        float speed = 3.5f;
        float a = Math.abs(deltaX/deltaY);

        float speedX = (float) Math.sqrt(speed*speed*a*a/(a*a +1));
        float speedY = (float) Math.sqrt(speed*speed/(a*a +1));

        Bullet1 bullet1_1 = new Bullet1(getPosX(), getPosY(),30,30,bulletEnemy,this, getGameWorld());

        if(deltaX < 0)bullet1_1.setSpeedX(-speedX);
        else bullet1_1.setSpeedX(speedX);

        bullet1_1.setSpeedY(speedY);

        bullet1_1.setType(getType());
        getGameWorld().bulletObjectManager.addObject(bullet1_1);
    }

    public void update(){
        super.update();
        if(System.nanoTime() - skill.getTimeStartSkill() > skill.getTimeSkill()){
            attack();
        }
        move();
    }

    public void draw(Graphics2D g2){
        hp.draw(g2);
        if(getState()==noBehurt && System.currentTimeMillis()%2==0);
        else{
            drawLeftOrRightAnimation(enemyForWard,enemyBack,g2);
        }
    }
}
