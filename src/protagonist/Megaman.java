package protagonist;

import attackobject.Attack;
import bulletObject.Bullet;
import bulletObject.BulletMegaman;
import effect.Animation;
import effect.LoadData;
import object.Human;
import object.GameWorld;
import object.SkillObject;

import javax.sound.sampled.AudioFileFormat;
import java.applet.AudioClip;
import java.awt.*;

public class Megaman extends Human {

    private Animation jumpForWard, jumpBack, jumpShootForWard, jumpShootBack;
    private Animation cutForWard, cutBack, jumpCutForWard, jumpCutBack;
    private Animation runForWard, runBack, runShootForWard, runShootBack;
    private Animation flyForWard, flyBack, flyShootingForWard, flyShootBack;
    private Animation idleForWard, idleBack, idleShootForWard, idleShootBack;

    private SkillObject skillShoot;
    private SkillObject skillCut;

    //private AudioClip hurting;
    //private AudioClip shooting;

    public Megaman(float posX, float posY, GameWorld gameWorld) {
        super(posX, posY, 70, 90, 0.065f, 300, gameWorld);

        //hurting = LoadData.getInstance().getSound("megamanhurt");
        //shooting = LoadData.getInstance().getSound("bluefireshooting");

        jumpForWard = LoadData.getInstance().getAnimation("jump");
        jumpForWard.setIsRepeat(false);

        jumpBack = LoadData.getInstance().getAnimation("jump");
        jumpBack.flipAllImage();
        jumpBack.setIsRepeat(false);

        jumpShootForWard = LoadData.getInstance().getAnimation("jumpshoot");
        jumpShootForWard.setIsRepeat(false);

        jumpShootBack = LoadData.getInstance().getAnimation("jumpshoot");
        jumpShootBack.flipAllImage();
        jumpShootForWard.setIsRepeat(false);

        cutForWard = LoadData.getInstance().getAnimation("idlechem");

        cutBack = LoadData.getInstance().getAnimation("idlechem");
        cutBack.flipAllImage();

        jumpCutForWard = LoadData.getInstance().getAnimation("jumpchem");

        jumpCutBack = LoadData.getInstance().getAnimation("jumpchem");
        jumpCutBack.flipAllImage();

        runForWard = LoadData.getInstance().getAnimation("run");

        runBack = LoadData.getInstance().getAnimation("run");
        runBack.flipAllImage();

        runShootForWard = LoadData.getInstance().getAnimation("runshoot");

        runShootBack = LoadData.getInstance().getAnimation("runshoot");
        runShootBack.flipAllImage();

        flyForWard = LoadData.getInstance().getAnimation("bird");

        flyBack = LoadData.getInstance().getAnimation("bird");
        flyBack.flipAllImage();

        flyShootingForWard = LoadData.getInstance().getAnimation("birdshoot");

        flyShootBack = LoadData.getInstance().getAnimation("birdshoot");
        flyShootBack.flipAllImage();

        idleForWard = LoadData.getInstance().getAnimation("idle");

        idleBack = LoadData.getInstance().getAnimation("idle");
        idleBack.flipAllImage();

        idleShootForWard = LoadData.getInstance().getAnimation("idleshoot");

        idleShootBack = LoadData.getInstance().getAnimation("idleshoot");
        idleShootBack.flipAllImage();

        behurtForWard = LoadData.getInstance().getAnimation("hurting");
        behurtBack = LoadData.getInstance().getAnimation("hurting");
        behurtBack.flipAllImage();

        skillShoot = new SkillObject(500000000);
        skillCut = new SkillObject(350000000);

        setDirection(right_Dir);
    }

    @Override
    public void draw(Graphics2D g2) {
        hp.draw(g2);
        g2.setColor(Color.black);
        //super.draw(g2);
        if (getState() == alive || getState() == noBehurt) {
            if (getState() == noBehurt && System.currentTimeMillis() % 2 == 0) ;
            else {
                if (getIsFly()) {
                    if (!skillShoot.getIsSkill()) drawLeftOrRightAnimation(flyForWard, flyBack, g2);
                    else drawLeftOrRightAnimation(flyShootingForWard, flyShootBack, g2);
                } else if (getIsJumping())
                    drawAnimationForActive(jumpShootForWard, jumpShootBack, jumpCutForWard, jumpCutBack, jumpForWard, jumpBack, g2);
                else if (getSpeedX() != 0)
                    drawAnimationForActive(runShootForWard, runShootBack, cutForWard, cutBack, runForWard, runBack, g2);
                else
                    drawAnimationForActive(idleShootForWard, idleShootBack, cutForWard, cutBack, idleForWard, idleBack, g2);
            }
        }
        if (getState() == behurt) drawLeftOrRightAnimation(behurtForWard, behurtBack, g2);
    }

    @Override
    public Rectangle getBoundForCollisionWithEnemy() {
        Rectangle rect = getBoundForCollisionWithMap();

        if (getIsFly()) return rect;
        else if (getIsJumping()) {
            rect.height += 10;
            rect.x += 5;
        } else {
            if (getSpeedX() > 0) rect.x += 25;
            else if (getSpeedX() < 0) rect.x -= 5;
            else rect.x += 10;
        }
        rect.y += 15;
        rect.width -= 20;
        rect.height -= 20;
        return rect;
    }

    @Override
    public Rectangle getBoundForCollisionWithMap() {
        Rectangle rect = super.getBoundForCollisionWithMap();
        if (getIsFly()) {
            if (getSpeedX() > 0) rect.x += -10;
            else if (getSpeedX() < 0) rect.x -= 10;
            rect.y += 25;
            rect.height -= 35;
            rect.width += 30;
        }
        return rect;
    }

    public void drawAnimationForActive(Animation animation1, Animation animation2, Animation animation3,
                                       Animation animation4, Animation animation5, Animation animation6, Graphics2D g2) {

        if (skillShoot.getIsSkill()) drawLeftOrRightAnimation(animation1, animation2, g2);
        else if (skillCut.getIsSkill()) drawLeftOrRightAnimation(animation3, animation4, g2);
        else drawLeftOrRightAnimation(animation5, animation6, g2);
    }

    public void drawLeftOrRightAnimation(Animation animation1, Animation animation2, Graphics2D g2) {
        animation1.update(System.nanoTime());
        int x = 0, y = 0;
        if (skillCut.getIsSkill()) {
            y += 23;
            x += 10;
        }
        ;
        if (getDirection() == left_Dir) {
            animation2.setCurrentFrame(animation1.getCurrentFrame());
            animation2.drawImage(g2, (int) (getPosX() - getGameWorld().camera.getPosX()) - x,
                    (int) (getPosY() - getGameWorld().camera.getPosY()) - y);
        } else {
            animation1.update(System.nanoTime());
            animation1.drawImage(g2, (int) (getPosX() - getGameWorld().camera.getPosX()) - x,
                    (int) (getPosY() - getGameWorld().camera.getPosY()) - y);
        }
    }

    @Override
    public void update() {
        super.update();
        if (runForWard.getCurrentFrame() > 0) {
            runForWard.setIgnoreFrame(0);
            runBack.setIgnoreFrame(0);
        }

        if (getIsLanding()) {
            jumpForWard.reset();
            jumpBack.reset();
        }

        if (getIsJumping() && getSpeedX() != 0) {
            setIsFly(true);
            if (collisionWithLeftOrRight()) {
                setIsFly(false);
                setIsJumping(true);
            }
        }
        if (skillCut.getIsSkill() && System.nanoTime() - skillCut.getTimeStartSkill() > skillCut.getTimeSkill()) {
            setIsAttack(false);
            skillCut.setIsSkill(false);
            cutForWard.reset();
            jumpCutForWard.reset();
        }
        if (skillShoot.getIsSkill() && System.nanoTime() - skillShoot.getTimeStartSkill() > skillShoot.getTimeSkill()) {
            skillShoot.setIsFirstSkill(true);
            skillShoot.setIsSkill(false);

        }
        if (skillShoot.getIsFirstSkill() && skillShoot.getIsSkill()) {
            skillShoot.setIsFirstSkill(false);
            runShootForWard.setCurrentFrame(runForWard.getCurrentFrame() + 1);
            jumpShootForWard.setCurrentFrame(jumpForWard.getCurrentFrame() - 1);
        }
    }

    @Override
    public void jump() {
        if (!getIsJumping())
            setSpeedY(-5);
    }

    @Override
    public void attack() {
        if (!skillShoot.getIsSkill()) {
            skillShoot.setTimeStartSkill(System.nanoTime());
            skillShoot.setIsSkill(true);

            Bullet bullet = new BulletMegaman(getPosX(), getPosY(), getGameWorld());

            if (getDirection() == left_Dir) {
                bullet.setSpeedX(-5);
                bullet.setPosX(getPosX() - 20);
            } else {
                bullet.setSpeedX(5);
                bullet.setPosX(getPosX() + 20);
            }

            bullet.setType(getType());
            getGameWorld().bulletObjectManager.addObject(bullet);
        }
    }

    public void cut() {
        if (!skillCut.getIsSkill() && !getIsFly()) {
            setIsAttack(true);
            skillCut.setTimeStartSkill(System.nanoTime());
            skillCut.setIsSkill(true);

            Attack attack = new Attack(getPosX(), getPosY(), 50, 100, this, getGameWorld());

            attack.setType(getType());
            if (getDirection() == left_Dir) attack.setPosX(attack.getPosX() - 50);
            else attack.setPosX(attack.getPosX() + 40);

            if (getSpeedY() > 0) attack.setPosY(attack.getPosY() + 25);
            else if (getSpeedY() < 0) {
                attack.setPosY(attack.getPosY() - 40);
            }

            attack.setPosY(attack.getPosY() - 15);
            getGameWorld().particularObjectManager.addObject(attack);

        }
    }

    @Override
    public void run() {
        if (getDirection() == left_Dir) setSpeedX(-3.5f);
        else setSpeedX(3.5f);
    }

    @Override
    public void stopRun() {
        setSpeedX(0);
        setIsFly(false);
        runForWard.reset();
        runForWard.unIgnoreFrame(0);
        runShootForWard.reset();
    }
}
