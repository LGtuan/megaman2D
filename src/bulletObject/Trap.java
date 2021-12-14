package bulletObject;

import effect.Animation;
import effect.LoadData;
import object.GameWorld;
import object.ParticularObject;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Trap extends ParticularObject {

    private BulletTrap b1;
    private BulletTrap b2;
    private BulletTrap b3;
    private BulletTrap b4;
    private BulletTrap b5;
    private BulletTrap b6;
    private BulletTrap b7;

    private Animation trap;

    public Trap(float posX, float posY, GameWorld gameWorld) {
        super(posX, posY, 0, 0, 1, 1, gameWorld);

        trap = LoadData.getInstance().getAnimation("trap");

        setIsAttack(true);
        innit();

    }

    public void innit(){
        b1 = new BulletTrap(4550,465,30,40,trap,this, getGameWorld());
        b2 = new BulletTrap(4450,465,30,40,trap,this, getGameWorld());
        b3 = new BulletTrap(4350,465,30,40,trap,this, getGameWorld());
        b4 = new BulletTrap(4250,465,30,40,trap,this, getGameWorld());
        b5 = new BulletTrap(4150,465,30,40,trap,this, getGameWorld());
        b6 = new BulletTrap(4050,465,30,40,trap,this, getGameWorld());
        b7 = new BulletTrap(3950,465,30,40,trap,this, getGameWorld());

        List<BulletTrap> listBullet = new ArrayList<>();
        listBullet.add(b1);
        listBullet.add(b2);
        listBullet.add(b3);
        listBullet.add(b4);
        listBullet.add(b5);
        listBullet.add(b6);
        listBullet.add(b7);

        for (int i=0;i<listBullet.size();i++){
            listBullet.get(i).setType(Enemy_Team);
            getGameWorld().bulletObjectManager.addObject(listBullet.get(i));
        }

    }

    public void update(){
        if(getGameWorld().megaman.getPosX()>=3900){
            setSpeed();
        }
        if(!getGameWorld().bulletObjectManager.getManagerObject().contains(b1)){
            setIsAttack(false);
        }
    }

    public void setSpeed(){
        b1.setSpeedY(-1);
        b2.setSpeedY(-1.5f);
        b3.setSpeedY(-2f);
        b4.setSpeedY(-2.5f);
        b5.setSpeedY(-3f);
        b6.setSpeedY(-3.5f);
        b7.setSpeedY(-4);

    }

}
