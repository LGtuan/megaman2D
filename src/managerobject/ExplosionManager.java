package managerobject;

import object.ExplosionObject;
import object.ParticularObject;

import java.awt.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ExplosionManager{

    protected List<ExplosionObject> managerObject;

    public ExplosionManager(){
        managerObject = Collections.synchronizedList(new LinkedList<>());
    }

    public void removeObject(ExplosionObject obj){

        synchronized (managerObject){

            for (int i=0;i<managerObject.size();i++) {
                ExplosionObject object = managerObject.get(i);
                if(object == obj) managerObject.remove(obj);
            }
        }
    }

    public void addObject(ExplosionObject object){
        synchronized (managerObject){
            managerObject.add(object);
        }
    }

    public void updateObject(){
        synchronized (managerObject){
            for (int i=0;i<managerObject.size();i++) {
                ExplosionObject object = managerObject.get(i);
                object.update();
            }
        }
    }

    public void drawObject(Graphics2D g2){
        synchronized (managerObject){
            for (ExplosionObject object :
                    managerObject) {
                object.draw(g2);
            }
        }
    }

    public List<ExplosionObject> getManagerObject() {
        return managerObject;
    }

    public void setManagerObject(List<ExplosionObject> managerObject) {
        this.managerObject = managerObject;
    }
}
