package object;

import boss.Boss1;
import bulletObject.Trap;
import effect.Animation;
import effect.LoadData;
import enemy.*;
import enemy.Robot;
import managerobject.BulletObjectManager;
import managerobject.ExplosionManager;
import managerobject.ParticularObjectManager;
import protagonist.Megaman;

import java.awt.*;

public class GameWorld {

    public Megaman megaman;
    public Camera camera;
    public PhysicalMap physicalMap;
    public ParticularObjectManager particularObjectManager;
    public BulletObjectManager bulletObjectManager;
    public ExplosionManager explosionManager;
    public BackGroundMap backGroundMap;
    public Boss1 boss1;
    private Animation animationEnemy3, animationEnemy4;
    private boolean endGame;
    private boolean isStart;

    public GameWorld() {
        megaman = new Megaman(250, 200, this);
        megaman.setType(ParticularObject.League_Team);
        camera = new Camera(0, 0, this);
        physicalMap = new PhysicalMap(0, 0, this);

        particularObjectManager = new ParticularObjectManager();
        particularObjectManager.addObject(megaman);
        bulletObjectManager = new BulletObjectManager();
        explosionManager = new ExplosionManager();
        backGroundMap = new BackGroundMap(0, 0, this);
        endGame = false;
        isStart = true;

        innit();
    }

    public void innit() {
        Enemy1 enemy1_1 = new Enemy1(400,800,this);
        enemy1_1.setType(ParticularObject.Enemy_Team);
        particularObjectManager.addObject(enemy1_1);

        Enemy2 enemy2_1 = new Enemy2(3400,350,this);
        enemy2_1.setType(ParticularObject.Enemy_Team);
        particularObjectManager.addObject(enemy2_1);

        animationEnemy3 = LoadData.getInstance().getAnimation("enemy3");
        animationEnemy4 = LoadData.getInstance().getAnimation("enemy4");

        Enemy3 enemy3_1 = new Enemy3(1700,200,animationEnemy3,this);
        enemy3_1.setType(ParticularObject.Enemy_Team);
        particularObjectManager.addObject(enemy3_1);

        Enemy3 enemy4_1 = new Enemy3(1300,510,animationEnemy4,this);
        enemy4_1.setType(ParticularObject.Enemy_Team);
        particularObjectManager.addObject(enemy4_1);

        Enemy3 enemy3_2 = new Enemy3(5500,420,animationEnemy3,this);
        enemy3_2.setType(ParticularObject.Enemy_Team);
        particularObjectManager.addObject(enemy3_2);

        Enemy3 enemy4_2 = new Enemy3(5000,320,animationEnemy4,this);
        enemy4_2.setType(ParticularObject.Enemy_Team);
        particularObjectManager.addObject(enemy4_2);

        Enemy3 enemy3_3 = new Enemy3(3000,650,animationEnemy3,this);
        enemy3_3.setType(ParticularObject.Enemy_Team);
        particularObjectManager.addObject(enemy3_3);

        Enemy3 enemy4_3 = new Enemy3(3000,210,animationEnemy4,this);
        enemy4_3.setType(ParticularObject.Enemy_Team);
        particularObjectManager.addObject(enemy4_3);

        EnemyLaze enemylaze1 = new EnemyLaze(700,80,this);
        enemylaze1.setType(ParticularObject.Enemy_Team);
        particularObjectManager.addObject(enemylaze1);

        EnemyLaze enemylaze2 = new EnemyLaze(5000,80,this);
        enemylaze2.setType(ParticularObject.Enemy_Team);
        particularObjectManager.addObject(enemylaze2);

        Enemy4 enemy6_1 = new Enemy4(630,355,this);
        enemy6_1.setType(ParticularObject.Enemy_Team);
        particularObjectManager.addObject(enemy6_1);

        Enemy1 enemy1_2 = new Enemy1(710,860,this);
        enemy1_2.setType(ParticularObject.Enemy_Team);
        particularObjectManager.addObject(enemy1_2);

        Enemy2 enemy2_2 = new Enemy2(510,890,this);
        enemy2_2.setType(ParticularObject.Enemy_Team);
        particularObjectManager.addObject(enemy2_2);

        Enemy1 enemy1_3 = new Enemy1(5000,420,this);
        enemy1_3.setType(ParticularObject.Enemy_Team);
        particularObjectManager.addObject(enemy1_3);

        Enemy2 enemy2_3 = new Enemy2(4550,890,this);
        enemy2_3.setType(ParticularObject.Enemy_Team);
        particularObjectManager.addObject(enemy2_3);

        Enemy1 enemy1_4 = new Enemy1(3300,670,this);
        enemy1_4.setType(ParticularObject.Enemy_Team);
        particularObjectManager.addObject(enemy1_4);

        Enemy2 enemy2_4 = new Enemy2(4250,890,this);
        enemy2_4.setType(ParticularObject.Enemy_Team);
        particularObjectManager.addObject(enemy2_4);

        Enemy4 enemy6_2 = new Enemy4(4400,1045,this);
        enemy6_2.setType(ParticularObject.Enemy_Team);
        particularObjectManager.addObject(enemy6_2);

        Enemy4 enemy6_3 = new Enemy4(3500,505,this);
        enemy6_3.setType(ParticularObject.Enemy_Team);
        particularObjectManager.addObject(enemy6_3);

//        Enemy4 enemy6_4 = new Enemy4(5500,628,this);
//        enemy6_4.setType(ParticularObject.Enemy_Team);
//        particularObjectManager.addObject(enemy6_4);

        Enemy5 enemy5_1 = new Enemy5(2000,400,this);
        enemy5_1.setType(ParticularObject.Enemy_Team);
        particularObjectManager.addObject(enemy5_1);

        Enemy5 enemy5_2 = new Enemy5(3500,100,this);
        enemy5_2.setType(ParticularObject.Enemy_Team);
        particularObjectManager.addObject(enemy5_2);

        Enemy5 enemy5_3 = new Enemy5(300,300,this);
        enemy5_3.setType(ParticularObject.Enemy_Team);
        particularObjectManager.addObject(enemy5_3);

        Trap trap = new Trap(3900,100,this);
        trap.setType(ParticularObject.Enemy_Team);
        particularObjectManager.addObject(trap);

        Robot robot1 = new Robot(1645,800,this);
        robot1.setType(ParticularObject.Enemy_Team);
        particularObjectManager.addObject(robot1);

        boss1 = new Boss1(5700, 1000, this);
        boss1.setType(ParticularObject.Enemy_Team);
        particularObjectManager.addObject(boss1);

        Robot robot2 = new Robot(5700, 500, this);
        robot2.setType(ParticularObject.Enemy_Team);
        particularObjectManager.addObject(robot2);

        Robot robot3 = new Robot(1745, 1040, this);
        robot3.setType(ParticularObject.Enemy_Team);
        particularObjectManager.addObject(robot3);

    }

    public void draw(Graphics2D g2) {
        backGroundMap.draw(g2);
        megaman.draw(g2);
        bulletObjectManager.drawObject(g2);
        explosionManager.drawObject(g2);
        particularObjectManager.drawObject(g2);

    }

    public void update() {
        physicalMap.update();
        bulletObjectManager.updateObject();
        particularObjectManager.updateObject();
        explosionManager.updateObject();
        megaman.update();
        camera.update();
        if(!particularObjectManager.getManagerObject().contains(boss1)){
            if(explosionManager.getManagerObject().isEmpty())
                endGame = true;
        }

    }

    public boolean isEndGame() {
        return endGame;
    }

    public boolean isStart() {
        return isStart;
    }

    public void setStart(boolean start) {
        isStart = start;
    }
}
