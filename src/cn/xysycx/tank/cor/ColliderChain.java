package cn.xysycx.tank.cor;

import tank.GameObject;

import java.util.LinkedList;
import java.util.List;

public class ColliderChain implements Collider {
    public List<Collider> colliderList =  new LinkedList<>();

    public ColliderChain() {
        add(new BulletTankCollider());
        add(new TankTankCollider());
    }

    public void add(Collider c){
        colliderList.add(c);
    }


    @Override
    public Boolean collider(GameObject o1, GameObject o2) {
        for (int i = 0; i < colliderList.size(); i++) {
           if(!colliderList.get(i).collider(o1,o2)){
               return false;
           }
        }

        return false;
    }


}
