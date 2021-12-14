package bulletObject;

import effect.Animation;
import effect.LoadData;
import object.GameWorld;
import object.ParticularObject;

import java.awt.*;

public class BulletMegaman extends Bullet{

    Animation bulletForWard,bulletBack;

    public BulletMegaman(float posX, float posY, GameWorld gameWorld) {
        super(posX, posY, 40, 35, 1, 1, gameWorld);
        setDame(10);

        bulletForWard = LoadData.getInstance().getAnimation("bulletmegaman");
        bulletBack = LoadData.getInstance().getAnimation(("bulletmegaman"));
        bulletBack.flipAllImage();
    }

    @Override
    public Rectangle getBoundForCollisionWithEnemy() {
        return super.getBoundForCollisionWithEnemy();
    }

    public void update(){
        super.update();
    }

    public void draw(Graphics2D g2){
        drawLeftOrRightAnimation(bulletForWard,bulletBack,g2);
    }
}
