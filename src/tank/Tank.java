package tank;

import cn.xysycx.tank.strategy.FireStrategy;
import cn.xysycx.tank.strategy.FourDirFireStrategy;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

import static tank.ResourceMgr.*;

/**
 * @Description TODO
 * @Author Fedeline
 * @Date 2020/12/2 下午12:04
 */
public class Tank extends GameObject {
    TankFrame tankFrame;
    public int x,y;
    public Dir dir = Dir.DOWN;
    private static final int SPEED = Integer.valueOf(PropertyMgr.get("tankSpeed"));
    private boolean moving = true;
    private boolean living = true;
    private Random random = new Random();
    public Group group = Group.BAD;
    public GameModel gameModel;

    FireStrategy fs = new FourDirFireStrategy();
    public Rectangle rect = new Rectangle();



    public static int WIDTH = ResourceMgr.goodtankU.getWidth();
    public static int HEIGHT = ResourceMgr.goodtankU.getHeight();



    @Override
    public void paint(Graphics g) {


        if (!living){
            gameModel.remove(this);
        }

        switch (dir) {
            case LEFT:
                g.drawImage(this.group == Group.GOOD?ResourceMgr.goodtankL:badtankL, x, y, null);
                break;
            case RIGHT:
                g.drawImage(this.group == Group.GOOD?ResourceMgr.goodtankR:badtankR, x, y, null);
                break;
            case UP:
                g.drawImage(this.group == Group.GOOD?ResourceMgr.goodtankU:badtankU, x, y, null);
                break;
            case DOWN:
                g.drawImage(this.group == Group.GOOD?ResourceMgr.goodtankD:badtankD, x, y, null);
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
        boundsCheck();
        //update rect
        rect.x = this.x;
        rect.y = this.y;

    }

    private void boundsCheck() {
        if (this.x < 2){
            x=2;
        }
        if (this.y < 28){
            y = 28;
        }
        if (this.x > TankFrame.GAME_WIDTH - Tank.WIDTH){
            x = TankFrame.GAME_WIDTH - Tank.WIDTH -2;
        }
        if (this.y >TankFrame.GAME_HEIGHT - Tank.HEIGHT){
            y = TankFrame.GAME_HEIGHT - Tank.HEIGHT -2;
        }
    }


    public Tank(int x, int y, Dir dir,Group group,GameModel gameModel) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.gameModel = gameModel;
        rect.x = this.x;
        rect.y = this.y;
        rect.width = WIDTH;
        rect.height = HEIGHT;
        if (group == Group.GOOD){
            String  goodFSName =(String) PropertyMgr.get("goodFS");
            try {
               fs =(FireStrategy)  Class.forName(goodFSName).getDeclaredConstructor().newInstance();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }else {
            String  badFSName =(String) PropertyMgr.get("badFS");
            try {
                fs =(FireStrategy)  Class.forName(badFSName).getDeclaredConstructor().newInstance();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }

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
        fs.fire(this);

    }
     void die() {
        this.living = false;
    }

    private void randomDir(){


        this.dir = Dir.values()[random.nextInt(4)];
    }

    public void stop(){
        moving = false;
    }
}
