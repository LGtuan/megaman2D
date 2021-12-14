package enemy;

import attackobject.Attack;
import effect.Animation;
import effect.LoadData;
import object.GameWorld;
import object.ParticularObject;
import object.SkillObject;

import java.awt.*;

public class Enemy4 extends ParticularObject {

    private Animation idleForWard, idleBack;
    private Animation runForWard, runBack;
    private Animation attackForWard, attackBack;


    private boolean isRun;
    private boolean isIdle;

    private float x1, x2;

    private SkillObject skill;

    public Enemy4(float posX, float posY, GameWorld gameWorld) {
        super(posX, posY, 85, 75, 1, 50, gameWorld);

        attackForWard = LoadData.getInstance().getAnimation("enemy6_attack");
        attackForWard.flipAllImage();
        attackBack = LoadData.getInstance().getAnimation("enemy6_attack");

        idleForWard = LoadData.getInstance().getAnimation("enemy6_idle");
        idleForWard.flipAllImage();
        idleBack = LoadData.getInstance().getAnimation("enemy6_idle");

        runForWard = LoadData.getInstance().getAnimation("enemy6_run");
        runForWard.flipAllImage();
        runBack = LoadData.getInstance().getAnimation("enemy6_run");

        behurtForWard = LoadData.getInstance().getAnimation("enemy6_behurt");
        behurtForWard.flipAllImage();
        behurtBack = LoadData.getInstance().getAnimation("enemy6_behurt");

        x1 = getPosX() - 350;
        x2 = getPosX() + 350;
        setDame(10);
        skill = new SkillObject(1200000000);
    }

    public boolean within() {
        return getGameWorld().megaman.getPosY() - getPosY() < 60 && getGameWorld().megaman.getPosY() - getPosY() > -60
                && getGameWorld().megaman.getPosX() > x1 && getGameWorld().megaman.getPosX() < x2;
    }

    @Override
    public void update() {
        super.update();
        if (within()) al();
        else move();

        if (getDirection() == left_Dir) setSpeedX(-1.5f);
        else setSpeedX(1.5f);
        if (isRun) setPosX(getPosX() + getSpeedX());

        if (skill.getIsSkill() && getIsAttack()) {
            skill.setIsSkill(false);
            attack();
        }
        if (!getIsAttack() && !isRun && getState() != behurt) isIdle = true;
        else isIdle = false;

        if (attackForWard.isLastFrame()) {
            setIsAttack(false);
            attackForWard.reset();
        }
        if(getIsAttack()) setSpeedX(0);
    }

    public void setAttack() {
        if (!getIsAttack() && System.nanoTime() - skill.getTimeStartSkill() > skill.getTimeSkill()) {
            skill.setIsSkill(true);
            setIsAttack(true);
        }
    }

    public void al() {
        if (getGameWorld().megaman.getPosX() > getPosX()) {
            setDirection(right_Dir);
            isRun = true;
            if (getGameWorld().megaman.getPosX() - 60 <= getPosX()) {
                isRun = false;
                setAttack();
            }
        } else if (getGameWorld().megaman.getPosX() < getPosX()) {
            setDirection(left_Dir);
            isRun = true;
            if (getGameWorld().megaman.getPosX() + 60 >= getPosX()) {
                isRun = false;
                setAttack();
            }
        }
    }

    public void move() {
        isRun = true;
        if (getPosX()-50 <= x1) setDirection(right_Dir);
        else if (getPosX()+50 >= x2) setDirection(left_Dir);
    }

    public void attack() {
        super.attack();
        skill.setTimeStartSkill(System.nanoTime());
        Attack attack = new Attack(getPosX(), getPosY(), 20, 60, this, getGameWorld());

        attack.getType();
        if (getDirection() == left_Dir) attack.setPosX(attack.getPosX() - 35);
        else attack.setPosX(attack.getPosX() + 40);

        attack.setType(getType());
        getGameWorld().particularObjectManager.addObject(attack);

    }

    @Override
    public void draw(Graphics2D g2) {
        hp.draw(g2);
        if(getState()==behurt){
            drawLeftOrRightAnimation(behurtForWard,behurtBack,g2);
            return;
        }
        if (getIsAttack()) {
            drawLeftOrRightAnimation(attackForWard, attackBack, g2);
        } else if (isIdle) {
            drawLeftOrRightAnimation(idleForWard, idleBack, g2);
        } else if (isRun) {
            drawLeftOrRightAnimation(runForWard, runBack, g2);
        }
    }
}
