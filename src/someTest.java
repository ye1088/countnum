import java.io.*;

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

    public static void readFile(String path) throws IOException {
        FileInputStream fis = new FileInputStream(new File(path));
        FileOutputStream fos = new FileOutputStream(new File(path + "_"));
        byte[] tmp = new byte[1024];
        int len=0;
        while ((len =fis.read(tmp))!=-1 ){
            fos.write(tmp,0,len);
        }
        fis.close();
        fos.flush();
        fos.close();


    }
    static byte[] a_c = new byte[]{-9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -5, -9, -9, -5, -9, -9, -9, -9, -9,
            -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9,
            -9, -9, -9, 62, -9, -9, -9, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -9, -9, -9,
            -1, -9, -9, -9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19,
            20, 21, 22, 23, 24, 25, -9, -9, -9, -9, -9, -9, 26, 27, 28, 29, 30, 31, 32, 33, 34,
            35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -9, -9, -9, -9,
            -9};
    public static void b(byte[] arg3, byte[] arg4) {
        int v0;
        for(v0 = 0; v0 < arg3.length; ++v0) {
            arg3[v0] = ((byte)(arg3[v0] ^ arg4[v0 % arg4.length]));
        }
    }

    public static byte[] a(byte[] arg2) {
        byte[] v0_1;
        try {
            v0_1 = a_a(arg2, 0, arg2.length,a_c);
        }
        catch(Exception v0) {
            v0_1 = null;
        }

        return v0_1;
    }

    private static int a_a_a(byte[] arg3, int arg4, byte[] arg5, int arg6, byte[] arg7) {
        int v0;
        int v1 = 61;
        if(arg3[arg4 + 2] == v1) {
            arg5[arg6] = ((byte)((arg7[arg3[arg4]] << 24 >>> 6 | arg7[arg3[arg4 + 1]] << 24 >>> 12) >>>
                    16));
            v0 = 1;
        }
        else if(arg3[arg4 + 3] == v1) {
            v0 = arg7[arg3[arg4]] << 24 >>> 6 | arg7[arg3[arg4 + 1]] << 24 >>> 12 | arg7[arg3[arg4 +
                    2]] << 24 >>> 18;
            arg5[arg6] = ((byte)(v0 >>> 16));
            arg5[arg6 + 1] = ((byte)(v0 >>> 8));
            v0 = 2;
        }
        else {
            v0 = arg7[arg3[arg4]] << 24 >>> 6 | arg7[arg3[arg4 + 1]] << 24 >>> 12 | arg7[arg3[arg4 +
                    2]] << 24 >>> 18 | arg7[arg3[arg4 + 3]] << 24 >>> 24;
            arg5[arg6] = ((byte)(v0 >> 16));
            arg5[arg6 + 1] = ((byte)(v0 >> 8));
            arg5[arg6 + 2] = ((byte)v0);
            v0 = 3;
        }

        return v0;
    }


    private static byte[] a_a(byte[] arg12, int arg13, int arg14, byte[] arg15) {
        byte v11 = 61;
        int v10 = 4;
        byte[] v5 = new byte[arg14 * 3 / 4 + 2];
        byte[] v6 = new byte[v10];
        int v4 = 0;
        int v2 = 0;
        int v3 = 0;
        while(true) {
            if(v4 < arg14) {
                byte v7 = ((byte)(arg12[v4 + arg13] & 127));
                int v0 = arg15[v7];
                if(v0 >= -5) {
                    if(v0 < -1) {
                        v0 = v2;
                        v2 = v3;
                    }
                    else if(v7 == v11) {
                        v0 = arg14 - v4;
                        v7 = ((byte)(arg12[arg14 - 1 + arg13] & 127));
                        if(v2 != 0 && v2 != 1) {
                            if(v2 == 3 && v0 > 2 || v2 == v10 && v0 > 1) {
//                                throw new Exception("padding byte \'=\' falsely signals end of encoded value at offset "
//                                        + v4);
                            }

                            if(v7 == v11) {
                                if(v2 != 0) {
                                    if(v2 == 1) {
                                    }
                                    else {
                                        v6[v2] = v11;
                                        v3 += a_a_a(v6, 0, v5, v3, arg15);
                                    }
                                }

                                byte[] v0_1 = new byte[v3];
                                System.arraycopy(v5, 0, v0_1, 0, v3);
                                return v0_1;
                            }

                            if(v7 == 10) {
                                if(v2 != 0) {
                                    if(v2 == 1) {
                                    }
                                    else {
                                        v6[v2] = v11;
                                        v3 += a_a_a(v6, 0, v5, v3, arg15);
                                    }
                                }

                                byte[] v0_1 = new byte[v3];
                                System.arraycopy(v5, 0, v0_1, 0, v3);
                                return v0_1;
                            }

                        }

                    }
                    else {
                        v0 = v2 + 1;
                        v6[v2] = v7;
                        if(v0 == v10) {
                            v2 = a_a_a(v6, 0, v5, v3, arg15) + v3;
                            v0 = 0;
                            if(v2 != 0) {
                                if(v2 == 1) {
                                }
                                else {
                                    v6[v2] = v11;
                                    v3 += a_a_a(v6, 0, v5, v3, arg15);
                                }
                            }

                            byte[] v0_1 = new byte[v3];
                            System.arraycopy(v5, 0, v0_1, 0, v3);
                            return v0_1;
                        }

                        v2 = v3;
                    }

                    label_69:
                    ++v4;
                    v3 = v2;
                    v2 = v0;
                    continue;
                }
                else {
                    break;
                }
            }

            if(v2 != 0) {
                if(v2 == 1) {
                }
                else {
                    v6[v2] = v11;
                    v3 += a_a_a(v6, 0, v5, v3, arg15);
                }
            }

            byte[] v0_1 = new byte[v3];
            System.arraycopy(v5, 0, v0_1, 0, v3);
            return v0_1;
        }

//        throw new Exception("Bad Base64 input character at " + v4 + ": " + arg12[v4 + arg13] + "(decimal)");
        if(v2 != 0) {
            if(v2 == 1) {
            }
            else {
                v6[v2] = v11;
                v3 += a_a_a(v6, 0, v5, v3, arg15);
            }
        }

        byte[] v0_1 = new byte[v3];
        System.arraycopy(v5, 0, v0_1, 0, v3);
        return v0_1;
    }


    private static String a(int[] arg4) {
        byte[] v1 = new byte[arg4.length];
        int v0;
        for(v0 = 0; v0 < arg4.length; ++v0) {
            v1[v0] = ((byte)arg4[v0]);
        }

        b(v1, new byte[]{38, 76, 49, 27});
        return new String(a(v1));
    }

    public static void main(String[] args) throws IOException {

//        String path = "C:\\Users\\appchina\\Documents\\Tencent Files\\1217713349\\FileRecv\\8fc9343fc73d648c29279a07e9dea3e7__TrailLocalization.(1).txt";

//        readFile(path);

        System.out.println("111111:"+a(new int[]{69, 33, 9, 110, 69, 126, 103, 113, 66, 20,
                123, 119}));
    }
}
