package Unity3D;


import Utils.Utils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;

/**
 * Created by ye1088 on 2017/5/2.
 */
public class ChaBuduoYingXiong {


    public static void main(String[] args) {

        String path = "D:\\project\\222222\\chabuduo差不多英雄\\Resource\\Data_out\\combine\\sharedassets0.assets";
        String en_path = "D:\\project\\222222\\chabuduo差不多英雄\\Resource\\Data_out\\combine\\txt\\1.txt";
        String jp_path = "D:\\project\\222222\\chabuduo差不多英雄\\Resource\\Data_out\\combine\\txt\\8.txt";
//        dump(path);
        mergeStr(jp_path,en_path);
    }

    /**
     * 将两个国家的语言合并了
     * @param jp_path 非英语 的文本路径
     * @param en_path 英语的文本路径
     */
    public static void mergeStr(String jp_path,String en_path){
        try {
            JSONObject jp_joP = new JSONObject(Utils.fileToString(jp_path));
            JSONObject en_joP = new JSONObject(Utils.fileToString(en_path));
            JSONArray jp_ja = jp_joP.getJSONArray("items");
            JSONArray en_ja = en_joP.getJSONArray("items");
            JSONObject out_jo = new JSONObject();
            JSONArray out_ja = new JSONArray();
            for (int i = 0; i < jp_ja.length(); i++) {
                JSONObject jp_jo = jp_ja.getJSONObject(i);
                JSONObject en_jo = en_ja.getJSONObject(i);
                jp_jo.put("ori_en",en_jo.getString("ori"));
                out_ja.put(jp_jo);
            }
            out_jo.put("items",out_ja);
            Utils.writeNewFile("01.txt",out_jo.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void dump(String path){
        long startPos = 0xd9f7160;
        int strNum = 0x2f5;
        long pos = startPos;


        try {
            RandomAccessFile raf = new RandomAccessFile(path, "r");
            raf.seek(startPos);

            for (int j = 0; j < 8; j++) {
                JSONObject jsonObject = new JSONObject();
                JSONArray jarray = new JSONArray();
                for (int i = 0; i < strNum; i++) {
                    int blen = Utils.yiwei(raf.readInt());
                    JSONObject jo = new JSONObject();
                    jo.put("blen",blen);
                    jo.put("clen",blen/3);

                    pos+=4;
                    jo.put("pos",pos);
                    byte[] buff = new byte[blen];
                    raf.read(buff,0,blen);
                    pos += blen + Utils.align(blen);
                    String str = new String(buff,"utf-8");
                    jo.put("ori",str);
                    raf.seek(pos);
                    jo.put("cz","");
                    jarray.put(jo);

                }
                jsonObject.put("items",jarray);
                Utils.writeNewFile(j+"1.txt",jsonObject.toString());
                pos = pos+4;
                raf.seek(pos);

            }
            raf.close();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
