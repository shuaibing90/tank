package tank;

/**
 * @Description TODO
 * @Author Fedeline
 * @Date 2020/12/6 下午7:13
 */
public class DefaultFireStrategy implements FireStrategy {
    @Override
    public void fire(Tank t) {

            int bx = t.x + Tank.WIDTH/2 - Bullet.WIDTH/2;
            int by = t.y + Tank.HEIGHT/2 - Bullet.HEIGHT/2;
            new Bullet(bx,by,t.getDir(),t.getGroup(),t.tankFrame);



    }
}
