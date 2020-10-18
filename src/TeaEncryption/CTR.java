package TeaEncryption;

/**
 * @author Zhining
 * @description
 * @create 2020-10-17-16-05
 **/
public class CTR extends TEA{


    public CTR(){

    }

    public String[][] CTREncryption(String plaintext, int[] key, int[] IV){
        int [] counter;
        counter = IV; //initialisation of the first block
        int ciphertext_oreder = 0, length = plaintext.length();
        cipherblocks = new String[length/4 + 1][4];
        int extraNum = plaintext.length()%4;
        switch (extraNum){  //zero padding
            case (0):break;
            case (1):{
                plaintext = plaintext.concat("   ");
                break;
            }
            case (2):{
                plaintext = plaintext.concat("  ");
                break;
            }
            case (3):{
                plaintext = plaintext.concat(" ");
                break;
            }
        }

        char[] text_chars =plaintext.toCharArray();

        for(int i=0;i<length;i=i+4){ //i stands for the character's order in the string
            int[] text = new int[]{0,0};  //initialization of the input plaintext for the encryption function
            //for the first two characters in a 64bit whole block
            int transform1 = text_chars[i]; //ASCII value of the first character in the plaintext
            String hex1 = Integer.toHexString(transform1); //1st
            int transform2 = text_chars[i + 1];  //2nd
            String hex2 = Integer.toHexString(transform2);
            String HEX1 = hex1.concat(hex2);
            //System.out.println("L: "+HEX1);
            text[0] = Integer.parseInt(HEX1,16);

            //for the 3rd and 4th characters in the block
            int transform3 = text_chars[i + 2]; //3rd; represnets the 3rd character's ASCII value
            String hex3 = Integer.toHexString(transform3);
            int transform4 = text_chars[i + 3]; //4th
            String hex4 = Integer.toHexString(transform4);
            String HEX2 = hex3.concat(hex4);
            text[1] = Integer.parseInt(HEX2,16);

            int [] encryption = encrypt(counter,key);
            int [] C = new int[2];

            C[0] = text[0]^encryption[0];
            C[1] = text[1]^encryption[1];

            String leftHexEncryptedString = Integer.toHexString(C[0]);
            String rightHexEncryptedString = Integer.toHexString(C[1]);

            //xor encryption of counter

            //System.out.println("R: "+HEX2);

            //for debugging
            //System.out.println("i: "+i);
            //System.out.println("length-i: "+(length-i));
            //System.out.println();
            cipherblocks[ciphertext_oreder][0] = leftHexEncryptedString.substring(0,4);  //left 1
            cipherblocks[ciphertext_oreder][1] = leftHexEncryptedString.substring(4);  //right 1
            cipherblocks[ciphertext_oreder][2] = rightHexEncryptedString.substring(0,4);  //left 2
            cipherblocks[ciphertext_oreder][3] = rightHexEncryptedString.substring(4);  //right 2
            ciphertext_oreder++;

            counter[1]++;
        }

        return cipherblocks;
    }






    public String[][] CTRDecryption(String[][] cipherStrings,int[] key, int[] IV){
        int plaintext_oreder = 0, length = cipherStrings.length;
        int [] counter;
        counter = IV; //initialisation of the first block

        int []decrypted;
        String[][] returned = new String[cipherStrings.length][2];
        int[] plainText = new int[2];

        for(int i=0;i<cipherStrings.length;i++){
            int []text = new int[2];
            if(cipherStrings[i][0]==null) break;
            if(cipherStrings[i][1]==null) break;
            String left1 = cipherStrings[i][0];
            String right1 = cipherStrings[i][1];
            String left2 = cipherStrings[i][2];
            String right2 = cipherStrings[i][3];

            String left1_first = left1.substring(0,2);  //the first character
            String left1_second = left1.substring(2);

            String right1_first = right1.substring(0,2);
            String right1_second = right1.substring(2);

            String left2_first = left2.substring(0,2);
            String left2_second = left2.substring(2);

            String right2_first = right2.substring(0,2);
            String right2_second = right2.substring(2);

            text[0]= Integer.parseUnsignedInt(left1_first.concat(left1_second).concat(right1_first).concat(right1_second),16);
            text[1]= Integer.parseUnsignedInt(left2_first.concat(left2_second).concat(right2_first).concat(right2_second),16);
            decrypted = encrypt(counter,key);
            plainText[0] = text[0]^decrypted[0];
            plainText[1] = text[1]^decrypted[1];

            returned[i][0] = Integer.toHexString(plainText[0]);
            returned[i][1] = Integer.toHexString(plainText[1]);

            counter[1]++;
        }
        return returned;

    }


}
