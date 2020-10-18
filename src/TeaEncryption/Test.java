package TeaEncryption;

import java.math.BigInteger;

public class Test {

    public static void main(String[] args) {


        TEA tea = new TEA();


        //frist text
        int []plaintext1 = new int[]{0x01234567,0x89ABCDEF};
        int []key1 = new int[]{0xA56BABCD,0x00000000,0xFFFFFFFF,0xABCDEF01};
        int [] encrypted1 = tea.encrypt(plaintext1,key1);
        System.out.println("The first plaintext is: 0x0123456789ABCDEF");
        System.out.print("The first encrypted text is: ");
        for(int item:encrypted1) System.out.print(Integer.toHexString(item));
        int [] decrypted1 = tea.decrypt(encrypted1,key1);
        System.out.print("\nThe first decrypted text is: ");
        for(int item:decrypted1) System.out.print(Integer.toHexString(item));

        System.out.println("\n---------------------------------------------\n");

        String p2_decimal = "106859140693170989536793383318618451778";
        int[] key2 = new int[]{0x50645267,0x556B5870,0x32733576,0x38792F42};
        BigInteger toHex=new BigInteger(p2_decimal,10);
        String p2_string=toHex.toString(16);
        //128bits plaintext -- 2 blocks encryption
        int [] p2_1 = new int[]{Integer.parseUnsignedInt(p2_string.substring(0,p2_string.length()/4),16),Integer.parseUnsignedInt(p2_string.substring(p2_string.length()/4,p2_string.length()/2),16)};
        int [] p2_2 = new int[]{Integer.parseUnsignedInt(p2_string.substring(p2_string.length()/2,3*p2_string.length()/4),16),Integer.parseUnsignedInt(p2_string.substring(3*p2_string.length()/4),16)};
        System.out.print("The second plaintext value in Hex is: "+ p2_string);
        int[] ciph2_1 = tea.encrypt(p2_1,key2);
        int[] ciph2_2 = tea.encrypt(p2_2,key2);
        System.out.print("\nThe second encrypted text is:         ");
        for(int item:ciph2_1) System.out.print(Integer.toHexString(item));
        for(int item:ciph2_2) System.out.print(Integer.toHexString(item));
        int[] decrypted2_1 = tea.decrypt(ciph2_1,key2);
        int[] decrypted2_2 = tea.decrypt(ciph2_2,key2);
        System.out.print("\nThe second decrypted text is:         ");
        for(int item:decrypted2_1) System.out.print(Integer.toHexString(item));
        for(int item:decrypted2_2) System.out.print(Integer.toHexString(item));

        System.out.println("\n---------------------------------------------\n");

        String plaintext3 = "Passwords are like underwear: don't let people see it, change it very often, and you shouldn't share it with strangers";
        int[] key3 = new int[]{0x73367639,0x79244226,0x45294840,0x4D635166};
        String [][]ciphertext3 = tea.ECBEncryption(plaintext3,key3);
        System.out.println("The third plaintext is:                 "+plaintext3);
        System.out.print("The third encrypted text is:              ");
        //for(String s:ciphertext3) System.out.println(s);
        for(int i=0;i<ciphertext3.length;i++){
            for(int j=0;j<4;j++)
            System.out.print(ciphertext3[i][j]);
        }
        System.out.print("\nThe decrypted third text in hexadecimal is:   ");
        String[][] decrypted3 = tea.Decryption(ciphertext3,key3);
        for(int i=0;i<decrypted3.length;i++){
            for(int j=0;j<2;j++){
                System.out.print(decrypted3[i][j]);
            }
        }
        System.out.print("\nThe decrypted third text is:            ");
        System.out.println(utils.parseHexToAscii(decrypted3));




        System.out.println("\n---------------------------------------------\n");
        System.out.println("---------------------------------------------\n");
        System.out.println("Below is the ECB ecryption demonstration of the two next text");
        System.out.println("---------------------------------------------\n");
        String ECBplaintext4_1 = "Four score and seven years ago our fathers brought forth on this continent, a new nation, conceived in Liberty, and dedicated to the proposition that all men are created equal.";
        String [][]ECBciphertext4_1 = tea.ECBEncryption(ECBplaintext4_1,key3);
        System.out.println("The forth plaintext is:                 "+ECBplaintext4_1);
        System.out.print("The forth encrypted text using ECB is:              ");
        for(int i=0;i<ECBciphertext4_1.length;i++){
            for(int j=0;j<4;j++)
                System.out.print(ECBciphertext4_1[i][j]);
        }
        System.out.print("\nThe decrypted forth text in hexadecimal is:   ");
        String[][] ECBdecrypted4_1 = tea.Decryption(ECBciphertext4_1,key3);
        for(int i=0;i<ECBdecrypted4_1.length;i++){
            for(int j=0;j<2;j++){
                System.out.print(ECBdecrypted4_1[i][j]);
            }
        }
        System.out.print("\nThe decrypted forth text is:            ");
        System.out.println(utils.parseHexToAscii(ECBdecrypted4_1));


        System.out.println("\n---------------------------------------------\n");
        String plaintext4_2 = "Your time is limited, so don't waste it living someone else's life. Don't be trapped by dogma, which is living with the results of other people's thinking.";
        String [][]ECBciphertext4_2 = tea.ECBEncryption(plaintext4_2,key3);
        System.out.println("The fifth plaintext is:                 "+plaintext4_2);
        System.out.print("The fifth encrypted text using ECB is:              ");
        for(int i=0;i<ECBciphertext4_2.length;i++){
            for(int j=0;j<4;j++)
                System.out.print(ECBciphertext4_2[i][j]);
        }
        System.out.print("\nThe decrypted fifth text in hexadecimal is:   ");
        String[][] ECBdecrypted4_2 = tea.Decryption(ECBciphertext4_2,key3);
        for(int i=0;i<ECBdecrypted4_2.length;i++){
            for(int j=0;j<2;j++){
                System.out.print(ECBdecrypted4_2[i][j]);
            }
        }
        System.out.print("\nThe decrypted fifth text is:            ");
        System.out.println(utils.parseHexToAscii(ECBdecrypted4_2));



    }


}
