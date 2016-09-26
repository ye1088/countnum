package Android;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;

/**
 * Created by Administrator on 2016/9/20.
 */
public class Mu_Biao {

    public static void main(String[] args) throws Exception {
        String path = "D:\\工作目录\\lizai立在地下城的墓标\\res\\修改\\1\\1-1\\修改2（9-22）";
//        dumpMain(path);
        packMain(path);

    }

    /**
     * 文件批量打包
     * 传入的路径为文件夹
     * @param path
     */
    public static void packMain(String path) throws Exception {
        File dir = new File(path);
        File[] files = dir.listFiles();
        for (File file:files
             ) {
            if (file.isDirectory()){
                packMain(file.getAbsolutePath());
                continue;
            }
            if (!file.getName().contains("_.txt")){
                continue;
            }
            pack(file.getAbsolutePath());
        }
    }


    /**
     * 单个文件打包
     * @param path
     * @throws Exception
     */
    public static void pack(String path) throws Exception {
        File data_file = new File(path.split("_.txt")[0]);

        if (!data_file.exists()){
            throw new Exception("ERROR: data文件不存在："+path.split("_.txt")[0]);
        }
        System.out.println(path);
        RandomAccessFile fout = new RandomAccessFile(new File(path.split("_.txt")[0]+"_out"),"rw");

        RandomAccessFile finp = new RandomAccessFile(data_file,"r");
        byte[] b = new byte[4096];
        int len =-1;
        while ((len =(finp.read(b)))!=-1){
            fout.write(b,0,len);

        }
        finp.close();
        
        JSONObject parent_jo = new JSONObject(Utils.JaUtils.fileToString(path));
        JSONArray items = parent_jo.getJSONArray("items");
        for (int i = 0; i < items.length(); i++) {
            JSONObject jo = items.getJSONObject(i);
            String fanyi =jo.getString("fanyi");
            if (fanyi.equals("")){
                continue;
            }
            byte[] fanyi_byte = fanyi.getBytes();
            int enLen = jo.getInt("enLen");
            Long pos = jo.getLong("pos");
            if (fanyi_byte.length>enLen){
                fout.close();
                throw new Exception("ERROR：字符串太长在："+pos+"\n"+fanyi);
            }
            
            fout.seek(pos);
            fout.write(fanyi_byte);
            if (fanyi_byte.length<enLen){
                bu_kong_ge(fout,enLen-fanyi_byte.length);
            }



        }
        fout.close();
    }

    //字符长度不够 填补空格
    private static void bu_kong_ge(RandomAccessFile fout, int i) throws IOException {
        while (i>0){
            fout.write(0x20);
            i--;
        }
    }

    //提取资源 入口
    public static void dumpMain(String path) throws IOException {
        File dir = new File(path);
        if (dir.isDirectory()){
            File[] files = dir.listFiles();
            for (File file:files
                    ) {
                System.out.println(file.getAbsolutePath());
                if (file.isDirectory()){
                    dumpMain(file.getAbsolutePath());
                    continue;
                }

                if (file.getName().equals("message.bytes")){
                    dumpMessage(file.getAbsolutePath());
                }else {
                    dumpOthers(file.getAbsolutePath());
                }
            }
        }
    }

    /**
     * 这个是对主要文本 message.byte 进行提取
     * @param path
     * @throws IOException
     */
    public static void dumpMessage(String path) throws IOException {
        RandomAccessFile raf = new RandomAccessFile(new File(path),"r");

        BufferedWriter bw = new BufferedWriter(new FileWriter(new File(path + "_.txt")));

        JSONObject parent_jo = new JSONObject();
        JSONArray ja = new JSONArray();


        parent_jo.put("offset",4);


        raf.readInt();
        int pos = 4;
        while (true){
            int reByte = raf.readUnsignedByte();
            JSONObject sub_jo = new JSONObject();
//            System.out.println(reByte);
            if (reByte==0x00){
                parent_jo.put("items",ja);
                break;
            }
            if (reByte<0xd9){
                pos += 1;
                byte[] ch_byte = new byte[reByte - 0xa0];
                raf.read(ch_byte,0,reByte-0xa0);
                String ori = new String(ch_byte);
                sub_jo.put("pos",pos);
                sub_jo.put("ori",ori);
                sub_jo.put("fanyi","");
                sub_jo.put("zhLen",(reByte-0xa0)/3);
                sub_jo.put("enLen",reByte-0xa0);
                ja.put(sub_jo);
                pos+=(reByte-0xa0);
                System.out.println(ori);
            }else if (reByte==0xd9){
                pos+=2;
                reByte = raf.readUnsignedByte();
                byte[] ch_byte = new byte[reByte];
                raf.read(ch_byte,0,reByte);
                String ori = new String(ch_byte);
                sub_jo.put("pos",pos);
                sub_jo.put("ori",ori);
                sub_jo.put("fanyi","");
                sub_jo.put("zhLen",(reByte)/3);
                sub_jo.put("enLen",reByte);
                ja.put(sub_jo);
                pos+=(reByte);
                System.out.println(ori);

            }else if (reByte==0xda){
                pos += 3;
                reByte = raf.readUnsignedByte();
                reByte = reByte*16*16 + raf.readUnsignedByte();
                byte[] ch_byte = new byte[reByte];
                raf.read(ch_byte,0,reByte);
                String ori = new String(ch_byte);
                sub_jo.put("pos",pos);
                sub_jo.put("ori",ori);
                sub_jo.put("fanyi","");
                sub_jo.put("zhLen",(reByte)/3);
                sub_jo.put("enLen",reByte);
                ja.put(sub_jo);
                pos+=(reByte);
                System.out.println(ori);

            }else if (reByte==0x00){
                parent_jo.put("items",ja);
                break;
            }else {
                System.out.println("出错啦：错误izhi:"+pos+1);
                break;
            }
        }
        raf.close();
        bw.write(parent_jo.toString());
        bw.flush();
        bw.close();




    }

    //这个是防止 误提资源
    public static boolean isValid(RandomAccessFile raf) throws IOException {
        int tmp = raf.readUnsignedByte();
        if ((0xa0<tmp && tmp < 0xc0)){
            return false;
        }else {
            return true;
        }
    }


    public static void dumpOthers(String path) throws IOException {
        File srcfile = new File(path);
        RandomAccessFile raf = new RandomAccessFile(srcfile,"r");

        File out_file = new File(path + "_.txt");
        BufferedWriter bw = new BufferedWriter(new FileWriter(out_file));

        JSONObject parent_jo = new JSONObject();
        JSONArray ja = new JSONArray();


        parent_jo.put("offset",2);


        raf.readShort();
        int pos = 2;
        boolean empty_flag = true;
        while (true) {
            boolean flag = false;
            try {
                int reByte = raf.readUnsignedByte();
                JSONObject sub_jo = new JSONObject();
//                System.out.println(reByte);

                if ((0xa0<reByte && reByte < 0xc0)) {
                    pos += 1;

                    flag = false;
                    if (!isValid(raf)){
                        raf.seek(pos);
                        continue;

                    }
                    raf.seek(pos);

                    byte[] ch_byte = new byte[reByte - 0xa0];
                    int test_byte;
                    test_byte =  raf.readUnsignedByte();
//                    String test_str = new String(test_byte);
                    raf.seek(pos);
                    if (test_byte<0x20){

                        continue;
                    }

                    raf.read(ch_byte, 0, reByte - 0xa0);
                    String ori = new String(ch_byte);
//                    ori = test_str + ori;
                    //判断是否包含日文
                    if(!Utils.whatLanguage.containJp(ori)){pos += (reByte - 0xa0);continue;}
                    sub_jo.put("pos", pos);
                    sub_jo.put("ori", ori);
                    sub_jo.put("fanyi", "");
                    sub_jo.put("zhLen", (reByte - 0xa0) / 3);
                    sub_jo.put("enLen", reByte - 0xa0);
                    ja.put(sub_jo);
                    pos += (reByte - 0xa0);
                    System.out.println(ori);
                    empty_flag = false;
                } else if (reByte == 0xd9) {
                    flag = false;
                    pos += 2;
                    reByte = raf.readUnsignedByte();
                    if (reByte==0x99){
                        continue;
                    }
                    byte[] ch_byte = new byte[reByte];
                    raf.read(ch_byte, 0, reByte);
                    String ori = new String(ch_byte);
                    //判断是否包含日文
                    if(!Utils.whatLanguage.containJp(ori)){ pos += (reByte);continue;}
                    sub_jo.put("pos", pos);
                    sub_jo.put("ori", ori);
                    sub_jo.put("fanyi", "");
                    sub_jo.put("zhLen", (reByte) / 3);
                    sub_jo.put("enLen", reByte);
                    ja.put(sub_jo);
                    pos += (reByte);
                    System.out.println(ori);
                    empty_flag = false;

                } else if (reByte == 0xda) {
                    flag = false;
                    pos += 3;
                    reByte = raf.readUnsignedByte();
                    reByte = reByte * 16 * 16 + raf.readUnsignedByte();
                    byte[] ch_byte = new byte[reByte];
                    raf.read(ch_byte, 0, reByte);
                    String ori = new String(ch_byte);
                    //判断是否包含日文
                    if(!Utils.whatLanguage.containJp(ori)){ pos += (reByte);continue;}
                    sub_jo.put("pos", pos);
                    sub_jo.put("ori", ori);
                    sub_jo.put("fanyi", "");
                    sub_jo.put("zhLen", (reByte) / 3);
                    sub_jo.put("enLen", reByte);
                    ja.put(sub_jo);
                    pos += (reByte);
                    System.out.println(ori);
                    empty_flag = false;

                }else {

                    pos += 1;
                }

                }catch (Exception e){
                    parent_jo.put("items", ja);
//                    raf.close();
//                    bw.write(parent_jo.toString());
//                    bw.flush();
//                    bw.close();
                    break;
            }
            }
            raf.close();
            if (!empty_flag) {
                bw.write(parent_jo.toString());
                bw.flush();

                bw.close();
            }else {
                bw.close();
                out_file.delete();
//                srcfile.delete();
            }







    }
}
