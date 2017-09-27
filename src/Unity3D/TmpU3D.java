package Unity3D;

import Utils.Utils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Created by appchina on 2017/1/4.
 */
public class TmpU3D {

    private static void dump(String path,long start){
        RandomAccessFile raf = null;
        JSONObject paJo = new JSONObject();
        JSONArray ja = new JSONArray();
        try {
            raf = new RandomAccessFile(path, "r");

            paJo.put("offset",0);
            raf.seek(start);
            long pos = start;
            int size = Utils.yiwei(raf.readInt());//所有字符串的数目
            pos += 4;
            int read_num = 0;
            while (true){

                JSONObject sonJo = new JSONObject();
                int string_len = Utils.yiwei(raf.readInt());
                pos+= 4;
                sonJo.put("pos",pos);
                if(string_len>1000){
                    System.out.println("hehe");
                }
                if (string_len<0){
                    System.out.println("hehe");
                }
                byte[] string_bytes = new byte[string_len];
                raf.read(string_bytes);
                String string = new String(string_bytes,"utf-8");
                int clen = string_len/3;
                int blen = string_len;

                if ((string_len%4)!=0){
                    pos += string_len + (4-string_len%4);
                }else {
                    pos += string_len;
                }



                sonJo.put("ori",string);
                sonJo.put("cz","");
                sonJo.put("clen",clen);
                sonJo.put("blen",blen);
                ja.put(sonJo);

                raf.seek(pos);
                read_num++;
            }

//            paJo.put("items",ja);
//            Utils.writeNewFile(path+"_.txt",paJo.toString());

//            raf.close();
        } catch (Exception e) {
            e.printStackTrace();

            paJo.put("items",ja);
            try {
                Utils.writeNewFile(path+"_.txt",paJo.toString());
                raf.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }


        }


    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {

        String path = "F:\\汉化\\ling零骑士\\零骑士\\assets\\55ff9e086bee91a4b954482c076304c6";
        long start = 0x10D8;//总字符串数目开始的位置0x000010F0;

        dump(path,start);
    }
}
