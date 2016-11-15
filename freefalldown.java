package answer1;

/**
 * Created by Administrator on 2016/11/15.
 * 一个球从100米高度自由下落,每次落地后反跳回原高度的一半,再落下.
 * 求它在第10次落地时经过的距离,和第10次反弹的高度.
 */
public class freefalldown {
    public static void main(String[] args){
        double hight = 100;
        double hight_n = hight;
        double length = 100;
        double length_n = length;


        for (int i=1;i<=10;i++){
            hight_n=hight_n*0.5;
            System.out.println("------------\n当前落地次数:"+(i)+"\n当前距离:"+length_n+"\n下次挑起高度:"+hight_n);
            length_n=length_n+ 2*hight_n;
        }

    }
}
