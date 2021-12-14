package managerobject;

import attackobject.Attack;
import object.ExplosionObject;
import object.ParticularObject;

import java.awt.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ParticularObjectManager {

    protected List<ParticularObject> managerObject;

    public ParticularObjectManager(){
        managerObject = Collections.synchronizedList(new LinkedList<>());
    }

    public void removeObject(ParticularObject obj){

        synchronized (managerObject){

            for (int i=0;i<managerObject.size();i++) {

                if(managerObject.get(i) == obj) managerObject.remove(obj);
            }
        }
    }

    public ParticularObject collisionObject(ParticularObject object){
        synchronized (managerObject){
            for (int i=0;i<managerObject.size();i++) {
                ParticularObject obj = managerObject.get(i);
                if(obj.isOutCamera() || obj instanceof Attack) continue;
                if (obj.getBoundForCollisionWithEnemy().intersects(object.getBoundForCollisionWithEnemy()) &&obj.getType()!=object.getType()) {
                    return obj;
                }
            }
        }
        return null;
    }

    public void addObject(ParticularObject object){
        synchronized (managerObject){
            managerObject.add(object);
        }
    }

    public void updateObject(){
        synchronized (managerObject){
            for (int i=0;i<managerObject.size();i++) {

                ParticularObject object = managerObject.get(i);

                if(!object.isOutCamera()) managerObject.get(i).update();
                if(object.getState()==ParticularObject.death){
                    ExplosionObject ex = new ExplosionObject(object.getPosX(),object.getPosY(),
                            object.getPosX(),object.getPosY(),object.getPosX(),
                            object.getPosY(),object.getArea(),object.getGameWorld());
                    object.getGameWorld().explosionManager.addObject(ex);
                    object.setIsAttack(false);
                    managerObject.remove(managerObject.get(i));
                }
            }
        }
    }

    public void drawObject(Graphics2D g2){
        synchronized (managerObject){
            for (ParticularObject object :managerObject) {
                if(!object.isOutCamera()) object.draw(g2);
            }
        }
    }

    public List<ParticularObject> getManagerObject() {
        return managerObject;
    }

    public void setManagerObject(List<ParticularObject> managerObject) {
        this.managerObject = managerObject;
    }
}
