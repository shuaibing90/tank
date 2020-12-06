package tank;

/**
 * @Description TODO
 * @Author Fedeline
 * @Date 2020/12/6 下午7:26
 */
public class FourDirFireStrategy implements FireStrategy {
    @Override
    public void fire(Tank t) {
        int bx = t.x + Tank.WIDTH/2 - Bullet.WIDTH/2;
        int by = t.y + Tank.HEIGHT/2 - Bullet.HEIGHT/2;
        Dir[] dirs = Dir.values();
        for (int i = 0; i < dirs.length; i++) {

            new Bullet(bx,by,dirs[i],t.getGroup(),t.tankFrame);
        }
    }
}
