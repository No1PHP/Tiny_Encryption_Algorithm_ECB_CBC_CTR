package TeaEncryption;

import java.util.Random;

/**
 * @author Zhining
 * @description utils class
 * @create 2020-10-17-13-52
 **/
public class utils {

    /*
    input:a 2D hexadecimal-composed String array; [0] for one block's left 32bit, [1]for right half block
    output: a hexadecimal ASCII code translated characters-constructed String
     */
    public static String parseHexToAscii(String [][]cipherStrings){
        StringBuffer buffer = new StringBuffer();

        //parsing the 2D array to a consecutive string
        for(int i=0;i<cipherStrings.length;i++){
            buffer.append(cipherStrings[i][0]);
            buffer.append(cipherStrings[i][1]);
        }
        String flat = buffer.toString();
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < flat.length(); i = i + 2) {
            int n;
            String s = flat.substring(i, i + 2);
            if(s.charAt(0)!='n') {  //in case the end is "null"
                n = Integer.valueOf(s, 16);
            }else break;
            builder.append((char)n);
        }
        return builder.toString();
    }


    public static int randomIVGenerator(int length) {
        Random r = new Random();
        StringBuffer buffer = new StringBuffer();
        while(buffer.length() < length){
            buffer.append(Integer.toHexString(r.nextInt()));
        }
        int returned = (int)Long.parseLong(buffer.toString().substring(0, length),16);
        return returned;

    }
}
