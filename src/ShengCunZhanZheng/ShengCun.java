package ShengCunZhanZheng;

import Utils.Utils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.util.HashMap;

/**
 * Created by appchina on 2016/12/13.
 */
public class ShengCun {

    private static final String[] TXT_FILE = {"Engine.Graphics.Shader","System.Xml.Linq.XElement","System.String"};

    /**
     * 使用方法:
     *  解析时:
     *      path为 要解析的文件,
     *      out_parent_path 为输出文件夹
     *   打包时:
     *      out_parent_path : 为翻译好的文本所在的文件夹,事先将文件路径都按原来的文件路径放好,比如原来是XXX/aa/bb/cc.txt,我们放的时候得是 thisIsSplit/aa/bb/cc.txt,
     *      path  : 为之前解析的那个文件,打好包之后会生成一个 path+"_out"文件
     * @param args
     */
    public static void main(String[] args) {
        String path = "F:\\汉化\\shengcun生存战争2\\res\\提取\\Content.pak";
        String out_parent_path = "F:\\汉化\\shengcun生存战争2\\res\\修改\\测试1修改好\\生存战争\\thisIsSplit";
//        dump(path,out_parent_path);
        pack(out_parent_path,path);

    }


    private static int Write7BitEncodedInt(int value ,RandomAccessFile raf) throws IOException {
        int num = value;
        System.out.println("Write7BitEncodedInt:--"+value);
        int i = 0;
        while (num >= 0x80)
        {
            raf.write( (byte)((num | 0x80)));
            System.out.println("Write7BitEncodedInt:--"+((num | 0x80)&0xff));
            num = num >> 7;
            i++;
        }
        raf.write( (byte)num);
        i ++;
        System.out.println("Write7BitEncodedInt:--"+num );
        return i;

    }




    public static void pack(String pack_dir,String src_file){


        try {

            Utils.copySrcFile2Dst(src_file,src_file+"_out");
            RandomAccessFile raf = new RandomAccessFile(new File(src_file+"_out"), "rw");
            long old_pos = 0;
            //文件头
            raf.readInt();
            old_pos += 4;
            //文件大小
            raf.readInt();
            old_pos += 4;
            //第一个文件的位置
            long file_start = Utils.yiwei(raf.readInt());
            old_pos += 4;
            //文件总数目
            int file_num = Utils.yiwei(raf.readInt());
            old_pos += 4;
            // 创建 pak 文件信息文件
            HashMap<String, Long> file_msg_map = creat_file_list_msg(raf, old_pos, src_file, file_num);

            //真正开始打包的地方
            real_pack_res(raf,pack_dir,file_msg_map,file_start);

            raf.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void real_pack_res(RandomAccessFile raf, String pack_dir, HashMap<String, Long> file_msg_map, long file_start) throws IOException {


        File dir_pack = new File(pack_dir);

        File[] files = dir_pack.listFiles();

//        File file_src_file = new File(src_file);

//        old_pos = raf.length();

        // pak 文件的大小
//        long start_append_pos = file_src_file.length();
        for (File file :
                files) {
            if (file.isDirectory()){
                real_pack_res(raf,file.getAbsolutePath(), file_msg_map, file_start);
            }else {
                String parent_dir = file.getParent();
                // 取出文件在 pak中 的父文件夹名字 这是打包前要做的工作
                String real_file_parent = parent_dir.split("thisIsSplit")[1].replace("\\","/");
                // 文件的大小
                long file_size = file.length();

                System.out.println("file_size:--" + file_size);


                FileInputStream fis = new FileInputStream(file);
//                System.out.println(real_file_parent);
                long file_off_pos = file_msg_map.get(real_file_parent);


                // 矫正 文件偏移 和文件大小
                raf.seek(file_off_pos);
                System.out.println("yi_wei :--" + Utils.yiwei((int) (raf.length()  - file_start)));
                raf.writeInt(Utils.yiwei((int) (raf.length()  - file_start)));


                raf.seek(raf.length());

                // 写文件
                byte[] buff = new byte[1024 * 1024];
                int len = -1;
                int zipIntLen = 0;
                if (isNeedZip(file)){
                    zipIntLen = Write7BitEncodedInt((int)file_size,raf);

                }

                //设置新文件的大小
                raf.seek(file_off_pos+4);
                raf.writeInt(Utils.yiwei((int) (file_size + zipIntLen)));

                raf.seek(raf.length());

                while ((len = fis.read(buff))!= -1){
                    raf.write(buff,0,len);
//                    old_pos += len;
                    raf.seek(raf.length());
                }


                fis.close();




            }
        }
        raf.seek(4);
        raf.writeInt(Utils.yiwei((int) raf.length()));

    }

    private static boolean isNeedZip(File file) {
        for (String name :
                TXT_FILE) {
            if (file.getName().equals(name)) {
                return true;
            }
            }

        return false;
    }


    private static HashMap<String, Long> creat_file_list_msg(RandomAccessFile raf, long old_pos, String src_file, int file_num) throws IOException {
        JSONArray file_list_msg = new JSONArray();
        int i = 0;
        HashMap<String,Long> file_msg_map = new HashMap<String,Long>();
        raf.seek(old_pos);
        while (i < file_num){
            JSONObject jo = new JSONObject();
            int string_len = raf.readByte();
            old_pos ++;
            if (string_len < 0){
                System.out.println("hehe");
            }
            byte[] buf = new byte[string_len];
            raf.read(buf);

            String file_dir_name = new String(buf,"utf-8");
            jo.put("file_dir_name",file_dir_name);
            old_pos += string_len;

            string_len = raf.readByte();
            old_pos ++;

            buf = new byte[string_len];
            raf.read(buf);

            jo.put("file_name",new String(buf,"utf-8"));
            old_pos += string_len;

            //保存 每个文件可以添加文件偏移的文职
//            System.out.println(File.separator+file_dir_name);
            file_msg_map.put("/"+file_dir_name,old_pos);
            jo.put("file_off_pos",old_pos);


            jo.put("file_off",Utils.yiwei(raf.readInt()));
            old_pos += 4;
            jo.put("file_size",Utils.yiwei(raf.readInt()));
            old_pos += 4;
            file_list_msg.put(jo);
            i++;
        }
        Utils.writeNewFile(src_file+"_fileList",file_list_msg.toString());
        return file_msg_map;
    }



    private static int  Read7BitEncodedInt(RandomAccessFile raf) throws IOException {


        byte num3;
        int num = 0;
        int num2 = 0;
        do
        {

            if (num2 == 0x23)
            {
                new Throwable("解压缩10进制数出问题了");
            }
            num3 = raf.readByte();
            num |= (num3 & 0x7f) << num2;
            num2 += 7;
        }
        while ((num3 & 0x80) != 0);

        return num;
    }



    public static void dump(String path,String out_parent_path){
        try {
            RandomAccessFile raf = new RandomAccessFile(new File(path), "r");
            long old_pos = 0;
            //文件头
            raf.readInt();
            old_pos += 4;
            //文件大小
            raf.readInt();
            old_pos += 4;
            //第一个文件的位置
            long file_start = Utils.yiwei(raf.readInt());
            old_pos += 4;
            //文件总数目
            int file_num = Utils.yiwei(raf.readInt());
            old_pos += 4;
            int i = 0;
            while (i<file_num){
                int file_dir_name_len = raf.read();
                old_pos++;
                old_pos+=file_dir_name_len;
                String file_dir_name = get_file_dir_name(file_dir_name_len,raf);
//                if (file_dir_name.contains("/")){
//                    String[] single_dir_names = file_dir_name.split("/");
//                    for (int j = 0; j < single_dir_names.length; j++) {
//                        new File(out_parent_path+File.separator+single_dir_names[i]).mkdirs()
//                    }
//                }
                File file_dir = new File(out_parent_path+File.separator+file_dir_name);
                file_dir.mkdirs();


                int file_name_len = raf.read();
                old_pos++;
                old_pos += file_name_len;
                String file_name = get_file_dir_name(file_name_len,raf);
                File file = new File(file_dir.getAbsolutePath()+File.separator+file_name);

                //文件相对第一个文件起始位置的偏移
                long file_off = Utils.yiwei(raf.readInt());
                old_pos+=4;
                //文件的大小 可能包括 十进制压缩数
                int file_size = Utils.yiwei(raf.readInt());
                old_pos += 4;


                FileOutputStream fos = new FileOutputStream(file);

                raf.seek(file_off+file_start);
                byte[] buff = new byte[file_size];
                if (isNeedZip(file)){
                    int real_file_size = Read7BitEncodedInt(raf);
                    buff = new byte[real_file_size];
                }

                raf.read(buff);
                fos.write(buff);
                fos.flush();
                fos.close();
                raf.seek(old_pos);

                i++;
            }
            raf.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //获取文件路径名
    private static String get_file_dir_name(int file_dir_name_len, RandomAccessFile raf) {

        String file_dir_name = "";
        byte[] b = new byte[file_dir_name_len];
        try {
            raf.read(b,0,file_dir_name_len);
            file_dir_name = new String(b,"utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }


        return file_dir_name;
    }
}
