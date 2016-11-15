package answer1;


/**
 * Created by Administrator on 2016/11/15.
 * 某个公司采用公用电话传递数据,数据是四位的整数,在传递过程中是加密的,
 * 加密规则如下:每位数字都加上5,然后用和除以10的余数代替该数字,再将第一位和第四位交换,第二位和第三位交换.
 */
public class phoneNumberEncryption {

    public static void main(String[] args){
        String phoneNumber="13051321230";
        System.out.println(phoneNumber.substring(10));
        System.out.println(encyption_phone(phoneNumber));
    }

    private static String encyption_phone(String phoneNumber){
        if( checkPhoneNumber(phoneNumber)) return null;

        String beg_phone_num = phoneNumber.substring(0,7);

        int ge = Integer.parseInt(phoneNumber.substring(10));
        int shi = Integer.parseInt(phoneNumber.substring(9,10));
        int bai = Integer.parseInt(phoneNumber.substring(8,9));
        int qian = Integer.parseInt(phoneNumber.substring(7,8));

        System.out.println(beg_phone_num+" ge:"+ge+" shi:"+shi+" bai:"+bai+" qian:"+qian);

        return beg_phone_num+"-"+encryption(ge)+ encryption(shi)+ encryption(bai)+ encryption(qian);
    }

    private static String encryption(int n){
        return ((n+5)%10)+"";
    }

    private static boolean checkPhoneNumber(String phoneNumber){
        /**
         * 13051321230
         * 130-5132-1230
         * 130 5132 1230
         * 1305-1321-230
         * 1305 1321 230
         **/
        if (phoneNumber == null) return false;
        phoneNumber=phoneNumber.replace(" ","").replace("-","");
        if (phoneNumber.trim().length()!=10)
            return false;
        return true;
    }

}
