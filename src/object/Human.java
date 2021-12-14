package object;

import java.awt.*;

public abstract class Human extends ParticularObject{

    private boolean isLanding;
    private boolean isJumping;
    private boolean isFly;

    public Human(float posX, float posY, float width, float height, float mass, int blood, GameWorld gameWorld) {
        super(posX, posY, width, height, mass, blood, gameWorld);
    }

    public boolean collisionWithLeftOrRight(){
        Rectangle rect = getBoundForCollisionWithMap();
        rect.x-=1;
        return getGameWorld().physicalMap.haveCollisionWithLeft(rect)!=null ||
                getGameWorld().physicalMap.haveCollisionWithRight(getBoundForCollisionWithMap())!=null;
    }

    public void update(){
        super.update();

        if(getState()==alive || getState()==noBehurt){

            setPosX(getPosX()+getSpeedX());

            if(getGameWorld().physicalMap.haveCollisionWithLeft(getBoundForCollisionWithMap())!=null && getDirection() == left_Dir) {
                setPosX(getGameWorld().physicalMap.haveCollisionWithLeft(getBoundForCollisionWithMap()).x
                        + getGameWorld().physicalMap.haveCollisionWithLeft(getBoundForCollisionWithMap()).width+ getBoundForCollisionWithMap().width / 2);
                setIsFly(false);
            }
            if(getGameWorld().physicalMap.haveCollisionWithRight(getBoundForCollisionWithMap())!=null && getDirection() == right_Dir) {
                setPosX(getGameWorld().physicalMap.haveCollisionWithRight(getBoundForCollisionWithMap()).x - getBoundForCollisionWithMap().width / 2);
                setIsFly(false);
            }

            Rectangle rectFuture = getBoundForCollisionWithMap();
            rectFuture.y += (getSpeedY()!=0) ? getSpeedY() : 2;

            if(getGameWorld().physicalMap.haveCollisionWithLand(rectFuture)!=null){
                setIsLanding(true);
                setIsJumping(false);
                setPosY(getGameWorld().physicalMap.haveCollisionWithLand(rectFuture).y-getBoundForCollisionWithMap().height/2);
                setSpeedY(0);
            }
            else if(getGameWorld().physicalMap.haveCollisionWithTop(rectFuture)!=null){
                setSpeedY(0);
                setPosY(getGameWorld().physicalMap.haveCollisionWithTop(rectFuture).y+
                        getGameWorld().physicalMap.haveCollisionWithTop(rectFuture).height+getHeight()/2);
            }
            else if(getIsFly()){
                setSpeedY(0);
            }
            else{
                setIsJumping(true);
                setIsLanding(false);
                setSpeedY(getSpeedY()+getMass());
                setPosY(getPosY()+getSpeedY());
            }
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        super.draw(g2);
    }

    public abstract void jump();
    public abstract void attack();
    public abstract void run();
    public abstract void stopRun();

    public boolean getIsLanding() {
        return isLanding;
    }

    public void setIsLanding(boolean landing) {
        isLanding = landing;
    }

    public boolean getIsJumping() {
        return isJumping;
    }

    public void setIsJumping(boolean jumping) {
        isJumping = jumping;
    }

    public boolean getIsFly() {
        return isFly;
    }

    public void setIsFly(boolean fly) {
        isFly = fly;
    }
}
