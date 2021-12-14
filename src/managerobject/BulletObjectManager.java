package managerobject;

import bulletObject.BulletTrap;

public class BulletObjectManager extends ParticularObjectManager{
    public void updateObject(){
        super.updateObject();
        synchronized (managerObject){

            for (int i=0;i<managerObject.size();i++) {
                if(managerObject.get(i).isOutCamera() && !(managerObject.get(i) instanceof BulletTrap)){
                    managerObject.remove(managerObject.get(i));
                }
            }

        }
    }
}
