package tank;

import java.io.IOException;
import java.util.Properties;

/**
 * @Description TODO
 * @Author Fedeline
 * @Date 2020/12/4 下午8:33
 * 配置文件管理类
 */
public class PropertyMgr {
    static Properties props = new Properties();


    static {
        try {
            props.load(PropertyMgr.class.getClassLoader().getResourceAsStream("config"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static String get(String key){
        if (props == null){
            return null;
        }
        return (String) props.get(key);

    }


}
