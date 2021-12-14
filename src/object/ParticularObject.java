package object;

import effect.Animation;
import user.GameFrame;

import java.awt.*;
import java.util.Random;

public class ParticularObject extends GameObject {

    public static final int League_Team = 0;
    public static final int Enemy_Team = 1;

    private int type;

    public static final int alive = 0;
    public static final int behurt = 1;
    public static final int noBehurt = 2;
    public static final int death = 3;

    public static final int right_Dir = 0;
    public static final int left_Dir = 1;

    private int state = alive;


    private boolean isAttack;
    private float width;
    private float height;
    private float mass;
    private float speedX;
    private float speedY;

    private int blood;
    private int direction;

    private int dame;

    private long startTimeNobehurt;
    private long timeNobehurt;

    protected Animation behurtForWard,behurtBack;
    protected Hp hp;

    public ParticularObject(float posX, float posY, float width, float height, float mass, int blood, GameWorld gameWorld) {
        super(posX, posY, gameWorld);
        this.width = width;
        this.height = height;
        this.mass = mass;
        this.blood = blood;
        direction = right_Dir;

        timeNobehurt = 1500000000;
        hp = new Hp(getPosX(),getPosY(),this,this.getGameWorld());
    }

    @Override
    public void update() {
        switch (getState()) {
            case alive:

                break;
            case noBehurt:
                if(System.nanoTime() -startTimeNobehurt > timeNobehurt){
                    setState(alive);
                }
                break;
            case behurt:
                if(blood<=0) {
                    setState(death) ;
                    break;
                }
                if(behurtForWard!=null){
                    behurtForWard.update(System.nanoTime());
                    if(behurtForWard.isLastFrame()){
                        setState(noBehurt);
                        behurtForWard.reset();
                        behurtBack.reset();
                        startTimeNobehurt = System.nanoTime();
                    }
                }
                else if(behurtForWard==null){
                    setState(noBehurt);
                    startTimeNobehurt = System.nanoTime();
                }

                break;
            case death:

        }
        if(getSpeedX()>0) setDirection(right_Dir);
        else if(getSpeedX()<0) setDirection(left_Dir);
        hp.update();
    }

    public void drawLeftOrRightAnimation(Animation animation1, Animation animation2, Graphics2D g2) {
        if(getState()==noBehurt && System.currentTimeMillis()%2==0);
        else {
            animation1.update(System.nanoTime());

            if (getDirection() == left_Dir) {
                animation2.setCurrentFrame(animation1.getCurrentFrame());
                animation2.drawImage(g2, (int) (getPosX() - getGameWorld().camera.getPosX()), (int) (getPosY() - getGameWorld().camera.getPosY()));
            } else {
                animation1.update(System.nanoTime());
                animation1.drawImage(g2, (int) (getPosX() - getGameWorld().camera.getPosX()), (int) (getPosY() - getGameWorld().camera.getPosY()));
            }
        }
    }

    public void behurt(int dam){
        setBlood(getBlood()-dam);
        setState(behurt);
    }

    public void draw(Graphics2D g2) {
        //drawBoundForCollisionWithEnemy(g2);
        //drawBoundForCollisionWithMap(g2);
    }

    public Rectangle getBoundForCollisionWithMap() {
        Rectangle rect = new Rectangle();
        rect.x = (int) (getPosX() - getWidth() / 2);
        rect.y = (int) (getPosY() - getHeight() / 2);
        rect.width = (int) (getWidth());
        rect.height = (int) (getHeight());

        return rect;
    }

    public Rectangle getBoundForCollisionWithEnemy() {
        Rectangle rect = getBoundForCollisionWithMap();

        rect.x += 10;
        rect.y += 10;
        rect.width -= 20;
        rect.height -= 20;
        return rect;
    }
//
//    public void drawBoundForCollisionWithMap(Graphics2D g2) {
//        g2.drawRect((int) (getBoundForCollisionWithMap().x - getGameWorld().camera.getPosX()),
//                (int) (getBoundForCollisionWithMap().y - getGameWorld().camera.getPosY()),
//                getBoundForCollisionWithMap().width, getBoundForCollisionWithMap().height);
//
//    }
//
//    public void drawBoundForCollisionWithEnemy(Graphics2D g2) {
//        g2.drawRect((int) (getBoundForCollisionWithEnemy().x - getGameWorld().camera.getPosX()),
//                (int) (getBoundForCollisionWithEnemy().y - getGameWorld().camera.getPosY()),
//                getBoundForCollisionWithEnemy().width, getBoundForCollisionWithEnemy().height);
//    }

    public boolean isOutCamera() {
        return (getPosX() - getGameWorld().camera.getPosX() < -50 ||
                getPosX() - getGameWorld().camera.getPosX() > GameFrame.Screen_Width + 50 ||
                getPosY() - getGameWorld().camera.getPosY() < -50 ||
                getPosY() - getGameWorld().camera.getPosY() > GameFrame.Screen_Height + 50);
    }

    public int random(){
        Random rand = new Random();
        return rand.nextInt(2)+1;
    }

    public void attack(){
        isAttack = true;
    }

    public boolean getIsAttack() {
        return isAttack;
    }

    public void setIsAttack(boolean attack) {
        isAttack = attack;
    }

    public float getArea(){
        return getWidth()*getHeight();
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public float getMass() {
        return mass;
    }


    public float getSpeedX() {
        return speedX;
    }

    public void setSpeedX(float speedX) {
        this.speedX = speedX;
    }

    public float getSpeedY() {
        return speedY;
    }

    public void setSpeedY(float speedY) {
        this.speedY = speedY;
    }

    public int getBlood() {
        return blood;
    }

    public void setBlood(int blood) {
        this.blood = blood;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getDame() {
        return dame;
    }

    public void setDame(int dame) {
        this.dame = dame;
    }

}
