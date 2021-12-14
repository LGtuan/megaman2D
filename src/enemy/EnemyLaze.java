package enemy;

import bulletObject.Bullet;
import bulletObject.Bullet1;
import effect.Animation;
import effect.LoadData;
import object.GameWorld;
import object.ParticularObject;
import object.SkillObject;

import java.awt.*;

public class EnemyLaze extends ParticularObject {

    private SkillObject skill;
    private Animation enemyLaze;
    private Animation bullet;
    private boolean isActive;
    private Bullet bulletLaze;
    public EnemyLaze(float posX, float posY, GameWorld gameWorld) {
        super(posX, posY, 30, 40, 1, 15, gameWorld);

        enemyLaze = LoadData.getInstance().getAnimation("laze");
        enemyLaze.setIsRepeat(false);
        skill = new SkillObject(2000000000);

        bullet = LoadData.getInstance().getAnimation("tialaze");
        skill.setIsSkill(true);
        isActive = true;
    }

    @Override
    public void update() {
        super.update();
        if(enemyLaze.isLastFrame() && isActive){
            attack();
            isActive = false;
        }
        if(System.nanoTime() - skill.getTimeStartSkill()>skill.getTimeSkill()*2 &&!isActive){
            getGameWorld().bulletObjectManager.removeObject(bulletLaze);
            skill.setIsSkill(true);
            skill.setTimeStartSkill(System.nanoTime());
        }
        if(System.nanoTime() - skill.getTimeStartSkill()>skill.getTimeSkill()
                && skill.getIsSkill()){
            isActive=true;
            skill.setIsSkill(false);
            setIsAttack(false);
        }
    }

    @Override
    public void attack() {
        setIsAttack(true);
        skill.setTimeStartSkill(System.nanoTime());
        bulletLaze = new Bullet1(getPosX(),
                getPosY()+165, 20, 300, bullet,this, getGameWorld());
        bulletLaze.setType(getType());
        getGameWorld().bulletObjectManager.addObject(bulletLaze);
    }

    @Override
    public void draw(Graphics2D g2) {

        enemyLaze.update(System.nanoTime());
        enemyLaze.drawImage(g2,(int) (getPosX()-getGameWorld().camera.getPosX()),
                (int) (getPosY()-getGameWorld().camera.getPosY()));
    }

}
