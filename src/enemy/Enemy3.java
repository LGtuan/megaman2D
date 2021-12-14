package enemy;

import attackobject.Attack;
import effect.Animation;
import object.GameWorld;
import object.ParticularObject;

import java.awt.*;

public class Enemy3 extends ParticularObject {

    private Animation animationForWard,animationBack;
    float x;
    private Attack attack;

    public Enemy3(float posX, float posY,Animation animation, GameWorld gameWorld) {
        super(posX, posY, 80, 50, 1, 50, gameWorld);
        this.animationForWard = new Animation(animation);
        this.animationBack = new Animation(animation);
        animationBack.flipAllImage();

        setIsAttack(true);
        x = getPosX();
        setSpeedX(-1);
        innit();
    }

    public void innit(){
        attack = new Attack(getPosX(),getPosY()+7,getWidth()-20,getHeight()-15,this,getGameWorld());
        attack.setDame(10);
        attack.setType(Enemy_Team);
        getGameWorld().particularObjectManager.addObject(attack);
    }

    public void update(){
        super.update();
        move();
    }

    public void move(){

        if (x + 200 < getPosX()) setDirection(left_Dir);
        else if (x - 140 > getPosX()) setDirection(right_Dir);


        if (getDirection() == left_Dir) setSpeedX(-1.5f);
        else setSpeedX(1.5f);
        setPosX(getPosX()+getSpeedX());
    }

    @Override
    public void draw(Graphics2D g2) {
        hp.draw(g2);
        drawLeftOrRightAnimation(animationForWard,animationBack,g2);
    }
}
