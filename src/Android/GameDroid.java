import Utils.Utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/9/12.
 */
public class GameDroid {

    public static void main(String[] args) throws IOException {
        String file_path = "F:\\汉化\\sicheng死城：僵尸生存\\game.droid";
        tiqu(file_path);
    }


    public static void tiqu(String file_path) throws IOException {
        RandomAccessFile raf = new RandomAccessFile(new File(file_path), "r");
        long pos = 0;
        raf.readInt();
        pos += 4;
        long end_pos = Utils.yiwei(raf.readInt())+8;
        pos += 4;
        ArrayList<String> key_list = new ArrayList<String>();

        while (true){
            byte[] key_value = new byte[4];
            raf.read(key_value);
            pos += 4;
            String key_str = new String(key_value,"utf-8");
            key_list.add(key_str+",");
            long seg_size = Utils.yiwei(raf.readInt());
            pos += 4;
            pos += seg_size;
//            try{
                raf.seek(pos);

//            }catch (Exception e){
//                break;
//            }
            if (pos == end_pos){
                break;
            }
//            if (key_str.equals("TXTR")||key_str.equals("FONT")||key_str.equals("STRG")){
//
//            }
        }
        raf.close();
        FileWriter fw = new FileWriter(file_path + "_");
        fw.write(key_list.toString());
        fw.flush();
        fw.close();
    }



    //

}
