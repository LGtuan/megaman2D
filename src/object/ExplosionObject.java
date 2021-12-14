package object;

import effect.Animation;
import effect.LoadData;

import java.awt.*;

public class ExplosionObject extends GameObject{

    private Animation explosion1, explosion2, explosion3;
    private float posX1, posY1, posX2, posY2, posX3, posY3;

    private SkillObject skill;
    private float area;

    public ExplosionObject(float posX1, float posY1, float posX2, float posY2, float posX3, float posY3,float area, GameWorld gameWorld) {
        super(posX1, posY1, gameWorld);
        this.posX1 = posX1;
        this.posX2 = posX2;
        this.posX3 = posX3;
        this.posY1 = posY1;
        this.posY2 = posY2;
        this.posY3 = posY3;
        this.area = area;

        explosion1 = LoadData.getInstance().getAnimation("explosion1");
        explosion2 = LoadData.getInstance().getAnimation("explosion2");
        explosion3 = LoadData.getInstance().getAnimation("explosion3");
        skill = new SkillObject(1000000000);
        skill.setTimeStartSkill(System.nanoTime());
    }

    @Override
    public void update() {
        if(System.nanoTime() - skill.getTimeStartSkill()> skill.getTimeSkill()) getGameWorld().explosionManager.removeObject(this);
    }

    public void draw(Graphics2D g2) {

        if (area < 2000) drawAnimation(g2, explosion1,(int) posX1,(int) posY1);
        else if (area < 4000) drawAnimation(g2, explosion2,(int) posX2, (int)posY2);
        else {
            drawAnimation(g2, explosion2, (int)posX2,(int) posY2);
            drawAnimation(g2, explosion3, (int)posX3,(int) posY3);
        }
    }

    public void drawAnimation(Graphics2D g2, Animation animation, int posX, int posY) {
        animation.update(System.nanoTime());

        animation.drawImage(g2, (int) (posX - getGameWorld().camera.getPosX()),
                (int) (posY - getGameWorld().camera.getPosY()));
    }
}
