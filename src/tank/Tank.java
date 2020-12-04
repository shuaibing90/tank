package tank;

import java.awt.*;
import java.util.Random;

/**
 * @Description TODO
 * @Author Fedeline
 * @Date 2020/12/2 下午12:04
 */
public class Tank {
    TankFrame tankFrame;
    private int x,y;
    private Dir dir = Dir.DOWN;
    private static final int SPEED = 1;
    private boolean moving = true;
    private boolean living = true;
    private Random random = new Random();
    private Group group = Group.BAD;

    public static int WIDTH = ResourceMgr.tankD.getWidth();
    public static int HEIGHT = ResourceMgr.tankD.getHeight();


    public void paint(Graphics g) {
        if (!living){
            tankFrame.tankList.remove(this);
        }
        switch (dir) {
            case LEFT:
                g.drawImage(ResourceMgr.tankL, x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.tankR, x, y, null);
                break;
            case UP:
                g.drawImage(ResourceMgr.tankU, x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.tankD, x, y, null);
                break;
            default:
                break;
        }
        move();
    }

    private void move() {
        if (!moving){
            return;
        }
        switch (dir){
            case LEFT:
                x -= SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
            default:
                break;

        }

        if (this.group == Group.BAD && random.nextInt(100)>95) {
            this.fire();


        }
        if (this.group == Group.BAD && random.nextInt(100)>95){
            randomDir();
        }


    }

    public Tank(int x, int y, Dir dir,Group group,TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tankFrame = tankFrame;
    }
    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public static int getSPEED() {
        return SPEED;
    }
    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void fire() {
        int bx = this.x + WIDTH/2 - Bullet.WIDTH/2;
        int by = this.y + HEIGHT/2 - Bullet.HEIGHT/2;
       tankFrame.bulletList.add(new Bullet(bx,by,this.dir,this.group,tankFrame));
    }
     void die() {
        this.living = false;
    }

    private void randomDir(){


        this.dir = Dir.values()[random.nextInt(4)];
    }
}
