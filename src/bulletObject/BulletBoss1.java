package bulletObject;

import effect.Animation;
import effect.LoadData;
import object.GameWorld;
import object.SkillObject;

import java.awt.*;

public class BulletBoss1 extends Bullet{

    private Animation bulletForWard,bulletBack;
    private Animation bullet1,bullet2,bullet3,bullet4,bullet5,bullet6;


    public BulletBoss1(float posX, float posY, GameWorld gameWorld) {
        super(posX, posY, 30, 30, 1, 1, gameWorld);

        bulletBack = LoadData.getInstance().getAnimation("bullet_enemy3");
        bulletForWard = LoadData.getInstance().getAnimation("bullet_enemy3");

        bullet1 = LoadData.getInstance().getAnimation("bullet1_boss1");
        bullet2 = LoadData.getInstance().getAnimation("bullet2_boss1");
        bullet3 = LoadData.getInstance().getAnimation("bullet3_boss1");
        bullet4 = LoadData.getInstance().getAnimation("bullet4_boss1");
        bullet5 = LoadData.getInstance().getAnimation("bullet5_boss1");
        bullet6 = LoadData.getInstance().getAnimation("bullet6_boss1");

        bulletForWard.flipAllImage();
    }

    @Override
    public Rectangle getBoundForCollisionWithEnemy() {
        return getBoundForCollisionWithMap();
    }

    public void update(){
        super.update();

        if(getGameWorld().physicalMap.haveCollisionWithLand(getBoundForCollisionWithMap())!=null ||
                getGameWorld().physicalMap.haveCollisionWithTop(getBoundForCollisionWithMap())!=null){
            setSpeedY(-getSpeedY());
        }

    }

    @Override
    public void draw(Graphics2D g2) {
        super.draw(g2);

        if(getSpeedY()==4 || getSpeedY()==-4)
            bullet3.drawImage(g2, (int) (getPosX()-getGameWorld().camera.getPosX()),
                    (int) (getPosY()-getGameWorld().camera.getPosY()));
        else if(getSpeedY()==5 || getSpeedY()==-5)
            bullet2.drawImage(g2, (int) (getPosX()-getGameWorld().camera.getPosX()),
                    (int) (getPosY()-getGameWorld().camera.getPosY()));
        else if(getSpeedY()==6)
            bullet1.drawImage(g2, (int) (getPosX()-getGameWorld().camera.getPosX()),
                    (int) (getPosY()-getGameWorld().camera.getPosY()));
        else if(getSpeedY()==-6)
            bullet4.drawImage(g2, (int) (getPosX()-getGameWorld().camera.getPosX()),
                    (int) (getPosY()-getGameWorld().camera.getPosY()));
        else if(getSpeedY()==7)
            bullet5.drawImage(g2, (int) (getPosX()-getGameWorld().camera.getPosX()),
                    (int) (getPosY()-getGameWorld().camera.getPosY()));
        else
            bullet6.drawImage(g2, (int) (getPosX()-getGameWorld().camera.getPosX()),
                    (int) (getPosY()-getGameWorld().camera.getPosY()));
    }
}
