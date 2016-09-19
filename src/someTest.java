/**
 * Created by Administrator on 2016/7/5.
 */
public class someTest {

    public static String getPasswordFromName(String username){

        String EncTable = "www.jmpoep.com";
        byte[] namebytes = username.getBytes();
        int nameLen = namebytes.length;
        System.out.println("nameLen:"+nameLen);
        byte[] passWord = new byte[nameLen];
        System.out.println("passWord:"+passWord.length);

        for (int i = 0;i<nameLen ;i++){
            System.out.println("i:"+i);
            byte v5 = namebytes[i];
            int v6 = EncTable.length();
            int v5_int = v5;
            int v5_quyu = v5_int % v6;
            char v5_char = EncTable.charAt(v5_quyu);
            byte v5_byte = (byte) v5_char;
            passWord[i] = v5_byte;
        }

        return new String(passWord);
    }

    public static void main(String[] args) {
        System.out.println(getPasswordFromName("F8LEFT"));
    }
}
