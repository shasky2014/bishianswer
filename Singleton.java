package answer1;

/**
 * Created by Administrator on 2016/11/15.
 * http://blog.csdn.net/haoel/article/details/4028232
 * @author :
 */
public class Singleton {
    private volatile static Singleton singleton = null;
    private Singleton() {  }
    public static Singleton getInstance() {
        if (singleton== null)  {
            synchronized (Singleton.class) {
                if (singleton== null)  {
                    singleton= new Singleton();
                }
            }
        }
        return singleton;
    }
}
