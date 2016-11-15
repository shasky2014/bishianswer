package answer1;
/**Directory
 * Created by Administrator on 2016/11/15.
 * 用传递参数的方式拷贝目录或文件到指定路径.
 */
import java.io.File;
public class copyFileOrDirectory {
    public static void main(String[] args) {

        try {
            File afile = new File("C:\\zuidaima_com_a\\zuidaima_com.txt");
            if (afile.renameTo(new File("C:\\zuidaima_com_b\\" + afile.getName()))) {
                System.out.println("File is moved successful!");
            } else {
                System.out.println("File is failed to move!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
