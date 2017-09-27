package Unity3D;

import Utils.Utils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;

/**
 * Created by appchina on 2016/12/14.
 */
public class cu_ilen {

    static boolean isDoubleLine = false;
    public static void main(String[] args) throws IOException {

        String path = "F:\\汉化\\huangye荒野求生\\res\\修改\\测试3\\il\\en\\Assembly-CSharp.dll.il";
        dump_ilen(path);
//        pack_ilen(path);
    }


    public static void pack_ilen(String path) throws IOException {
        String srcPath = path.split("_out")[0];
        long old_line = 0;
        if (!(new File(srcPath)).exists()){
            new Exception("Error：源文件不存在：\n"+srcPath);
        }
        BufferedWriter bw = new BufferedWriter(new FileWriter(srcPath + "ilout"));
        JSONArray ja = new JSONArray(Utils.fileToString(path));
        
        for (int i = 0; i < ja.length(); i++) {
            BufferedReader br = new BufferedReader(new FileReader(srcPath));
            JSONObject jo = ja.getJSONObject(i);
            if (!isDoubleLine){
                write_new_src(br,jo,bw,old_line+1);
            }else {
                isDoubleLine = false;
                write_new_src(br,jo,bw,old_line+2);
            }

            old_line = jo.getLong("line");
            br.close();
        }
        BufferedReader br = new BufferedReader(new FileReader(srcPath));
        finish_left(bw,isDoubleLine,br,old_line);
        br.close();
    }

    private static void finish_left(BufferedWriter bw, boolean isDoubleLine, BufferedReader br, long old_line) throws IOException {
        if (isDoubleLine){
            old_line+=2;
        }else {
            old_line += 1;
        }
        String line = "";
        br.skip(old_line-1);
        while ((line = br.readLine())!=null){
            bw.write(line+"\n");
        }
        bw.flush();
        bw.close();
    }

    private static void write_new_src(BufferedReader br, JSONObject jo, BufferedWriter bw, long old_line) throws IOException {
        long line = jo.getLong("line");
        br.skip(old_line-1);
        for (long i = old_line; i <=line; i++) {
            String read_line = br.readLine();
            if (i==line){
                read_line = br.readLine();
                if (!read_line.contains("    IL_")&&read_line.contains("+ ")){
                    isDoubleLine = true;
                }
                bw.write(jo.getString("zh")+"\n");

            }else {
                bw.write(read_line+"\n");
            }

        }
    }


    public static void dump_ilen(String path){
        try {
            JSONArray ja = new JSONArray();

            BufferedReader br = new BufferedReader(new FileReader(path));
            String line = "";
            long pos = 0;
            while ((line = br.readLine())!= null){
                pos++;
                if (line.contains("ldstr      \"")){
                    JSONObject jo = new JSONObject();
                    jo.put("line",pos);
                    line = getRealString(line);
                    jo.put("ori",line);
                    jo.put("zh","");
                    jo.put("ba",line);
                    ja.put(jo);

                }else {
                    continue;
                }
            }
            br.close();
            Utils.writeNewFile(path + "_out" , ja.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getRealString(String line) {
        String result = "";
        String[] tmp_sp = line.split("\"");
        int i = 1;
        while (i < tmp_sp.length){
            result += tmp_sp[i];
            i += 2;
        }
        return result;

    }
}
