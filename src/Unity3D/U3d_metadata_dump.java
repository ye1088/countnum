package Unity3D;

import Utils.JaUtils;
import Utils.Utils;
import Utils.whatLanguage;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;

/**
 * Created by appchina on 2016/12/3.
 */
public class U3d_metadata_dump {

    public static void main(String[] args) {

        String path = "G:\\tools\\11111\\guanjun冠军足球物语2\\res\\提取\\global-metadata.dat";
        metadata_dump(path);

    }

    public static void metadata_dump(String path){
        try {
            boolean flag = true;
            RandomAccessFile raf = new RandomAccessFile(new File(path),"r");
            JSONObject paJo = new JSONObject();
            JSONArray ja = new JSONArray();
            paJo.put("offset",0);
            long pos = 0x10;
            raf.seek(pos);
            long start = Utils.yiwei(raf.readInt());
            pos +=0x4;
            long end = start + Utils.yiwei(raf.readInt());
            System.out.println("start: "+start);
            System.out.println("end : "+end);
            pos += 0x4;
            pos += 0xd8;
            raf.seek(pos);
//            byte[] b = new byte[1024*1024];
            int string_len = 0;
            long string_offset = 0;
//            String string = "";
            StringBuffer sb = new StringBuffer();
//            int num =0;
//            int file_num = 0;
            while ((end-start-string_offset)>0x4){
                JSONObject sonJo = new JSONObject();
                string_len = Utils.yiwei(raf.readInt());
                // 这里是第一个字符串的前三个字节，这个是通用的，一般不用改--elementattributeTypeType
                if (string_len==1835363429) break;
                string_offset = Utils.yiwei(raf.readInt());
                // 这里是第一个日文开始的位置，可以删掉
//                if (string_offset==0x13714){
//                    flag = false;
//                }
//                if (flag) continue;
                pos += 0x8;
                raf.seek(start+string_offset);
                System.out.println(string_len);
                byte[] b = new byte[string_len];
                raf.read(b,0,string_len);
//                System.out.println(b);
//                System.out.println(new String(b));
                String string = new String(b);
                //过滤掉英文
                if (!whatLanguage.containJp(string)){
                    raf.seek(pos);
                    continue;
                }
//                new String()
//                num++;
//                sb.append(string);

//                string = String.valueOf(b);
                sonJo.put("pos",start+string_offset);
                sonJo.put("ori",string);
                sonJo.put("cz","");
                sonJo.put("clen",string_len/3);
                sonJo.put("blen",string_len);
                ja.put(sonJo);
                System.out.println(string);
                string = null;
                raf.seek(pos);
//                System.out.println("num : "+num);
//                if (num>20){
//                    num = 0;
//                    Utils.writeNewFile(path+"_"+file_num+".txt",sb.toString());
//                    file_num++;
//                    sb = new StringBuffer();
//                }
            }
            raf.close();
            paJo.put("items",ja);
            Utils.writeNewFile(path+"_.txt",paJo.toString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
