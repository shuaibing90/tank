package cn.xysycx.tank.cor;

import tank.GameObject;

/**
 * 碰撞器
 */
public interface Collider {
    Boolean collider(GameObject o1,GameObject o2);
}
