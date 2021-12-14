package boss;

import attackobject.Attack;
import bulletObject.BulletBoss1;
import bulletObject.BulletKameha;
import effect.Animation;
import effect.LoadData;
import object.GameWorld;
import object.Human;
import object.SkillObject;

import java.awt.*;
import java.util.Hashtable;
import java.util.Random;

public class Boss1 extends Human {

    private Animation idleAttackForWard, idleAttackBack;
    private Animation jumpForWard, jumpBack;
    private Animation changeForWard, changeBack;
    private Animation carForWard, carBack;

    private Hashtable<String, Long> timeActive;
    private String[] active;

    private int currentActive;

    private SkillObject skill;

    long timeStartActive;
    private int speedAttack = 0;

    private SkillObject skillKameha;
    BulletKameha bulletKameha;

    public Boss1(float posX, float posY, GameWorld gameWorld) {
        super(posX, posY, 100, 110, 0.3f, 150, gameWorld);

        idleAttackForWard = LoadData.getInstance().getAnimation("boss1idleattack");
        idleAttackBack = LoadData.getInstance().getAnimation("boss1idleattack");
        idleAttackBack.flipAllImage();

        jumpForWard = LoadData.getInstance().getAnimation("boss1jump");
        jumpBack = LoadData.getInstance().getAnimation("boss1jump");
        jumpBack.flipAllImage();

        changeForWard = LoadData.getInstance().getAnimation("boss1change");
        changeBack = LoadData.getInstance().getAnimation("boss1change");
        changeBack.flipAllImage();

        carForWard = LoadData.getInstance().getAnimation("boss1car");
        carBack = LoadData.getInstance().getAnimation("boss1car");
        carBack.flipAllImage();

        active = new String[4];
        timeActive = new Hashtable<>();

        active[1] = "attack";
        active[2] = "change";
        active[3] = "car";

        timeActive.put("attack", 2500L);
        timeActive.put("car", 6000L);
        timeActive.put("change", 2000L);

        currentActive = 1;
        skill = new SkillObject(1000000000);
        timeStartActive = System.currentTimeMillis();

        skillKameha = new SkillObject(800000000);

    }

    @Override
    public Rectangle getBoundForCollisionWithEnemy() {
        Rectangle rect = getBoundForCollisionWithMap();
        if (currentActive == 1) rect.x += 15;
        else if (currentActive == 3) {
            rect.width += 20;
        } else if (currentActive == 2) {
            rect.y += 10;
            rect.height -= 20;
        }

        return rect;
    }

    @Override
    public Rectangle getBoundForCollisionWithMap() {

        if (currentActive == 3 || skillKameha.getIsSkill()) {
            Rectangle rect = super.getBoundForCollisionWithMap();
            rect.y += 34;
            rect.height -= 70;

            return rect;
        }
        return super.getBoundForCollisionWithMap();
    }

    @Override
    public void update() {
        super.update();
        if (getDirection() == right_Dir) setSpeedX(7);
        else setSpeedX(-7);

        if(currentActive!=4) {
            if (System.currentTimeMillis() - timeStartActive > timeActive.get(active[currentActive])) {
                currentActive++;
                timeStartActive = System.currentTimeMillis();
            }
        }

        if (currentActive == 3) {

            if (!skill.getIsSkill() && getIsLanding()) {
                Attack attack = new Attack(getPosX(), getPosY(), 100, 40, this, getGameWorld());
                attack.setType(getType());
                getGameWorld().particularObjectManager.addObject(attack);
                skill.setIsSkill(true);
                speedAttack = 0;
            }
            setIsAttack(true);
            run();
        } else {
            setIsAttack(false);
            skill.setIsSkill(false);

            if (getGameWorld().megaman.getPosX() > getPosX()) setDirection(right_Dir);
            else setDirection(left_Dir);

            if (currentActive == 4) {
                setSpeedX(0);
                if (!skillKameha.getIsSkill()) kameha();
                if (System.nanoTime() - skillKameha.getTimeStartSkill() > skillKameha.getTimeSkill()) {
                    skillKameha.setIsSkill(false);
                    currentActive = 1;
                }
            } else {
                if (speedAttack == 0) {
                    speedAttack = 3;
                    setPosY(1020);
                }

                if (getPosY() >= 850) {
                    jump();
                } else {
                    if (speedAttack % 2 == 0) attack();
                    speedAttack++;
                }

                if (currentActive == 2) {
                    if (changeForWard.isLastFrame()) {
                        changeForWard.reset();
                        currentActive++;
                        timeStartActive = System.currentTimeMillis();
                    } else {
                        setSpeedY(0);
                        setIsJumping(false);
                    }
                }
            }
        }
    }

    @Override
    public void draw(Graphics2D g2) {

        //super.draw(g2);
        hp.draw(g2);
        if (getState() == noBehurt && System.currentTimeMillis() % 2 == 0) return;


        if (currentActive == 3 || skillKameha.getIsSkill()) {
            drawLeftOrRightAnimation(carForWard, carBack, g2);
        } else if (getIsJumping()) {
            drawLeftOrRightAnimation(jumpForWard, jumpBack, g2);
        } else if (currentActive == 1) {
            drawLeftOrRightAnimation(idleAttackForWard, idleAttackBack, g2);
        } else if (currentActive == 2) {
            drawLeftOrRightAnimation(changeForWard, changeBack, g2);
        }
    }

    @Override
    public void jump() {
        setSpeedY(-6);
    }

    @Override
    public void attack() {

        BulletBoss1 bullet = new BulletBoss1(getPosX(), getPosY(), getGameWorld());
        bullet.setType(getType());
        getGameWorld().bulletObjectManager.addObject(bullet);

        if (getDirection() == left_Dir) bullet.setSpeedX(-randomSpeed());
        else bullet.setSpeedX(randomSpeed());

        if (random() < 0) {
            bullet.setSpeedY(-randomSpeed());
        } else if (random() > 0) {
            bullet.setSpeedY(randomSpeed());
        } else {
            bullet.setSpeedY(randomSpeed());
        }
        setPosY(850);
        getGameWorld().bulletObjectManager.addObject(bullet);
    }


    public void attack2() {

    }

    public int randomSpeed() {
        Random rand = new Random();
        return rand.nextInt(4) + 4;
    }

    @Override
    public int random() {
        Random rand = new Random();
        return rand.nextInt(3) - 1;
    }

    @Override
    public void run() {
        Rectangle rect = getBoundForCollisionWithMap();
        if (getDirection() == left_Dir) rect.x -= 1;
        else rect.x += 1;

        if (getGameWorld().physicalMap.haveCollisionWithLeft(rect) != null) {
            setSpeedX(5);
        } else if (getGameWorld().physicalMap.haveCollisionWithRight(rect) != null) {
            setSpeedX(-5);
        }

    }

    public void kameha() {
        skillKameha.setIsSkill(true);
        skillKameha.setTimeStartSkill(System.nanoTime());

        bulletKameha = new BulletKameha(getPosX(), getPosY(), getGameWorld());
        if (getDirection() == left_Dir) {
                bulletKameha.setPosX(getPosX() - 30);
                bulletKameha.setPosY(bulletKameha.getPosY() + 10);
        } else {
                bulletKameha.setPosX(getPosX() + 30);
                bulletKameha.setPosY(bulletKameha.getPosY() + 10);
        }
        bulletKameha.setType(getType());
        getGameWorld().bulletObjectManager.addObject(bulletKameha);
    }

    @Override
    public void stopRun() {

    }

    public SkillObject getSkillKameha() {
        return skillKameha;
    }
}
