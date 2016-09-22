package Utils;

import java.io.*;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2016/9/20.
 */
public class whatLanguage {


    public static byte[] getBytesByInt(int value, boolean isSwap) {
        byte[] bytes = new byte[4];
        if (isSwap) {
            bytes[0] = (byte) (value & 0x000000ff);
            bytes[1] = (byte) (value >> 8 & 0x000000ff);
            bytes[2] = (byte) (value >> 16 & 0x000000ff);
            bytes[3] = (byte) (value >> 24 & 0x000000ff);
        } else {
            bytes[3] = (byte) (value & 0x000000ff);
            bytes[2] = (byte) (value >> 8 & 0x000000ff);
            bytes[1] = (byte) (value >> 16 & 0x000000ff);
            bytes[0] = (byte) (value >> 24 & 0x000000ff);
        }
        return bytes;
    }

    public static int swipInt(int value){
        int res = 0;
        byte[] bytes = getBytesByInt(value,true);
        res |=(((int)bytes[0])&0x000000ff);
        res = res<<8;
        res |=(((int)bytes[1])&0x000000ff);
        res = res<<8;
        res |=(((int)bytes[2])&0x000000ff);
        res = res<<8;
        res |=(((int)bytes[3])&0x000000ff);
        return res;
    }

    /**
     * 将from中的offset开始length长度的数据写入to
     *
     * @param from
     * @param to
     * @param offset
     * @param lenght
     */
    public static void writeFromTo(ByteBuffer from, ByteBuffer to, int offset, int lenght) {
        int pos = from.position();
        int limit = from.limit();

        from.limit(offset + lenght);
        from.position(offset);
        to.put(from);
        from.position(pos);
        from.limit(limit);

    }

    public static String getStringFromFile(String file) throws IOException {
        Scanner scanner = new Scanner(new File(file), "utf-8");
        StringBuffer sb = new StringBuffer();
        while (scanner.hasNextLine()) {
            sb.append(scanner.nextLine());
        }
        scanner.close();
        return sb.toString();
    }

    public static String getStringFromFile2(String file) throws IOException{
        BufferedReader breader = getTxtReader(file,"utf-8");
        StringBuffer sbuffer = new StringBuffer();
        String line ;
        while ((line = breader.readLine())!=null){
            sbuffer.append(line);
        }
        return sbuffer.toString();
    }

    public static String getObbMd5String(String infileName) {
        try {
            MessageDigest md = MessageDigest.getInstance("md5");
            FileInputStream fins = new FileInputStream(infileName);
            File file = new File(infileName);
            long fileLength = file.length();
            final long fileLengthConst = 0x10016;    //65558
            long minNum = Math.min(fileLength, fileLengthConst);
            fileLength = fileLength - minNum;
            fins.skip(fileLength);
            byte byteArray[] = new byte[0x400];
            int ret = 0;
            while (true) {
                if (ret == -1) break;
                md.update(byteArray, 0, ret);
                ret = fins.read(byteArray);
            }
            byte md5Res[] = md.digest();
            if (md5Res == null) {
                return "";
            }
            StringBuffer sb = new StringBuffer();
            int i = 0;
            while (true) {
                int md5ResLength = md5Res.length;
                if (i >= md5ResLength) {
                    break;
                }
                byte tmp_b = md5Res[i];
                int tmp_byte = tmp_b & 0xff;
                tmp_byte = tmp_byte + 0x100;
                String str_tmp = Integer.toString(tmp_byte, 0x10);
                str_tmp = str_tmp.substring(0x1);
                sb.append(str_tmp);
                i++;
            }
            return sb.toString();
        } catch (Exception e) {
            System.out.println("catch some exception;"+infileName);
            return "";
        }
    }

    public static String getStrByUnicode(String uni) {
        StringBuilder sb = new StringBuilder();
        int i = -1;
        int pos = 0;

        while ((i = uni.indexOf("\\u", pos)) != -1) {
            sb.append(uni.substring(pos, i));
            if (i + 5 < uni.length()) {
                pos = i + 6;
                sb.append((char) Integer.parseInt(uni.substring(i + 2, i + 6), 16));
            }
        }
        if (pos < uni.length()) {
            sb.append(uni.substring(pos, uni.length()));
        }
        System.out.println(sb.toString());
        return sb.toString();
    }


    public static void byteBufferSkip(ByteBuffer bb, int skip) {
        for (int i = 0; i < skip; i++) {
            bb.put((byte) 0);
        }
    }

//    public static void write2File(String file, String content) throws IOException {
//        FileWriter fw = new FileWriter(new File(file));
//        fw.write(content);
//        fw.flush();
//        fw.close();
//    }

    public static void write2File(String file, String content) throws IOException {
        File _file = new File(file);
        _file.getParentFile().mkdirs();
        BufferedWriter writer = getTxtWriter(file, "utf-8");
        writer.write(content);
        writer.flush();
        writer.close();
    }

    public static BufferedReader getTxtReader(String file, String charset) throws IOException {
        InputStreamReader isr = new InputStreamReader(new FileInputStream(file), charset);
        return new BufferedReader(new FileReader(file));
//        return new BufferedReader(new FileReader(file));
    }

    public static BufferedWriter getTxtWriter(String file, String charset) throws IOException {
        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(file), charset);
        return new BufferedWriter(osw);
//        return new BufferedWriter(new FileWriter(file));
    }


    public static String readString(ByteBuffer bb) throws IOException {
        List<Byte> bytes = new ArrayList<Byte>();
        byte b;
        while ((b = bb.get()) != 0x00 && bb.position() < bb.capacity()) {
            bytes.add(b);
        }

        byte[] bs = new byte[bytes.size()];
        for (int i = 0; i < bytes.size(); i++) {
            bs[i] = bytes.get(i);
        }
        return new String(bs, "utf-8");
    }

    // 读取一个字符串，到<|>结束，不包括<|>
    public static byte[] readUsefulBytes(ByteBuffer bb) throws IOException {
        List<Byte> bytes = new ArrayList<Byte>();
        byte[] bs = null;

        byte b, state = 0;

        while ((b = bb.get()) != 0x00 && bb.position() < bb.capacity()) {
            if (b == '<') {
                state = 1;
            } else if (b == '|' && state == 1) {
                state = 2;
            } else if (b == '>' && state == 2) {
                state = 0;
                bytes.remove(bytes.size() - 1);// 减去之前放入的"|"
                bytes.remove(bytes.size() - 1);// 减去之前放入的"<"
                break;
            } else {
                state = 0;
            }
            bytes.add(b);
        }

        bs = new byte[bytes.size()];
        for (int i = 0; i < bytes.size(); i++) {
            bs[i] = bytes.get(i);
        }
        return bs;
    }

    // 读取一行的数据
    public static String[] readLine(ByteBuffer bb) throws IOException {
        int len = 5;
        String[] sentences = new String[len];
        for (int i = 0; i < len; i++) {
            sentences[i] = new String(readUsefulBytes(bb), "utf-8");
        }
        bb.get();// 读取换行符"/n"
        return sentences;
    }

    /**
     * target是否包含于bb中,找到第一个
     *
     * @param bb
     * @param target
     * @return
     */
    public static int match(ByteBuffer bb, byte[] target) {
        int pos = bb.position();

        for (int p = bb.position(); bb.limit() - bb.position() >= target.length; bb.position(p++)) {
            for (int i = 0; i < target.length; i++) {
                if (bb.get() != target[i]) {
                    break;
                }
                if (i == target.length - 1) {
                    return p;
                }
            }
        }

        bb.position(pos);
        return -1;
    }


    public static boolean isMatch(ByteBuffer bbf, byte[] target) {
        for (byte b : target) {
            if (bbf.get() != b) {
                return false;
            }
        }
        return true;
    }

    private static Pattern pcz = Pattern.compile("[\u4e00-\u9fa5]");

    public static boolean containCz(String str) {
        return containP(pcz, str);
    }

    private static Pattern phr = Pattern.compile("[\u3040-\u309f]");
    private static Pattern pkt = Pattern.compile("[\u30a0-\u30ff]");
    private static Pattern pkj = Pattern.compile("[\u4e00-\u9fbf]");

    public static boolean containJp(String str) {
        boolean temp = false;
        temp = containP(phr, str) || containP(pkt, str) || containP(pkj, str);
        return temp;
    }

    public static boolean containEn(String str){
        boolean temp = false;
        int tag = 0;
        for(char ca:str.toCharArray()){
            if(tag>=(str.length()/2)){
                return true;
            }
            if((ca>='a'&&ca<='z')||(ca>='A'&&ca<='Z')){
                tag++;
            }
        }
        return temp;
    }

    private static boolean containP(Pattern p, String str) {
        boolean temp = false;
        Matcher m = p.matcher(str);
        if (m.find()) {
            temp = true;
        }

        return temp;
    }


    public static String convertChar2Ba(String cz) {
        StringBuffer sbuffer = new StringBuffer();

        for (char ca : cz.toCharArray()) {
            int[] carray = new int[4];
            carray[0] = ca & 0x000f;
            carray[1] = ca >> 4 & 0x000f;
            carray[2] = ca >> 8 & 0x000f;
            carray[3] = ca >> 12 & 0x000f;
            sbuffer.append(Integer.toString(carray[1], 16).toUpperCase())
                    .append(Integer.toString(carray[0], 16).toUpperCase()).append(" ")
                    .append(Integer.toString(carray[3], 16).toUpperCase())
                    .append(Integer.toString(carray[2], 16).toUpperCase()).append(" ");

        }

        return sbuffer.toString();
    }


    public static boolean strEquals(String st1, String st2){
        if(st1.length()>st2.length()){
            return st1.startsWith(st2);
        }
        return st2.startsWith(st1);
    }

    private static  String [] usefull={".apk",".xpk",".gpk",".exe",".obb",".inject","bpk"};
    public static void deleteUnsue(String path) throws IOException{
        File file = new File(path);
        for(File f:file.listFiles()) {
            if (f.isDirectory()) {
                deleteUnsue(f.getAbsolutePath());
            }else{
                if(!isUsefull(f.getAbsolutePath())){
                    System.out.println("delete file at path :"+f.getPath());
                    f.delete();
                }
            }
        }
        if(file.listFiles().length==0){
            file.delete();
        }

    }
    private static boolean isUsefull(String path){
        for(String sbext:usefull){
            if(path.endsWith(sbext)){
                return true;
            }
        }
        return false;
    }

    public static DataOutputStream getOutputStream(String file)throws IOException{
        File f = new File(file);
        f = f.getParentFile();
        f.mkdirs();
        return new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
    }

    public static DataInputStream getInputStream(String file) throws IOException{
        return new DataInputStream(new BufferedInputStream(new FileInputStream(file)));
    }

    public static RandomAccessFile getRandomAccessFile(String file) throws IOException{
        return new RandomAccessFile(file,"rw");
    }

}
