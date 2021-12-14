package object;

public class Camera extends GameObject{
    private boolean move;
    public Camera(float posX, float posY,GameWorld gameWorld) {
        super(posX, posY, gameWorld);
        move = true;
    }

    @Override
    public void update() {

        if(getGameWorld().megaman.getPosX()>=5090 && getGameWorld().megaman.getPosY()>600){
            setPosX(getPosX()+2);
            setPosY(560);
        }else {
            if (getGameWorld().megaman.getPosX() - getPosX() > 400) setPosX(getGameWorld().megaman.getPosX() - 400);
            else if (getGameWorld().megaman.getPosX() - getPosX() < 400)
                setPosX(getGameWorld().megaman.getPosX() - 400);

            if (getGameWorld().megaman.getPosY() - getPosY() > 350) setPosY(getGameWorld().megaman.getPosY() - 350);
            else if (getGameWorld().megaman.getPosY() - getPosY() < 250)
                setPosY(getGameWorld().megaman.getPosY() - 250);

        }
        if(getPosX()>5040) setPosX(5040);
        if(getPosX()<0) setPosX(0);
        if(getPosY()>570) setPosY(570);
        if(getPosY()<0) setPosY(0);
    }
}
