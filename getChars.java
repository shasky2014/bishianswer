package answer1;

/**
 * Created by Administrator on 2016/11/15.
 * 中英文混合截取如下字符串"Java创新教程"的
 * 前5个字符,结果为"Java创",前6个字符结果也是同样.
 * - 字符编码 >255 的为汉字编码,这个方法没有用, 关于字符编码参考百度百科
 * 不同字符类型的识别,用 Character.getType() 识别
 */
public class getChars {
    public static void main(String[] args){
        String a ="Javaà创新教SAFsssS程の0ε0?>,,-=_+[]{ };:'";
        char [] aa =a.toCharArray();

        System.out.println(a.length()+"..."+aa.length);
        for (int i=0;i<aa.length;i++){
            System.out.print(aa[i]+":");
            System.out.println(Character.getType(aa[i]));
        }

        System.out.println(getChar(a,6));
    }
    private static String getChar(String a,int topn){
        char [] aa =a.toCharArray();
        String b="";
        for (int i=0,j=0;( i<topn && j<topn );i++){
            if(Character.getType(aa[i])==5){
                j+=2;
            }else {
                j++;
            }
            b=b+aa[i];
//        System.out.println(aa[i]);
        }
        return b;
    }
}
