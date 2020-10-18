package TeaEncryption;

/**
 * @author Zhining
 * @description
 * @create 2020-10-18-14-01
 **/
public class CBCTest {

    public static void main(String[] args) {

        int[] key3 = new int[]{0x73367639,0x79244226,0x45294840,0x4D635166};
        int []iv = new int[]{utils.randomIVGenerator(8),utils.randomIVGenerator(8)};
        CBC cbc = new CBC(iv);
        System.out.println("\n---------------------------------------------\n");
        System.out.println("---------------------------------------------\n");
        System.out.println("Below is the CBC ecryption demonstration of the two text");
        System.out.println("---------------------------------------------\n");

        String CBCplaintext4_1 = "Four score and seven years ago our fathers brought forth on this continent, a new nation, conceived in Liberty, and dedicated to the proposition that all men are created equal.";
        String [][] CBCciphertext4_1 = cbc.CBCEncryption(CBCplaintext4_1,key3);
        System.out.println("The forth plaintext is:              "+CBCplaintext4_1);
        System.out.print("The forth encrypted text using CBC is: ");
        for(int i=0;i<CBCciphertext4_1.length;i++){
            for(int j=0;j<4;j++)
                System.out.print(CBCciphertext4_1[i][j]);
        }
        System.out.print("\nThe decrypted forth text in hexadecimal is:   ");
        String[][] CBCdecrypted4_1 = cbc.CBCDecryption(CBCciphertext4_1,key3);
        for(int i=0;i<CBCdecrypted4_1.length;i++){
            for(int j=0;j<2;j++){
                System.out.print(CBCdecrypted4_1[i][j]);
            }
        }
        System.out.print("\nThe decrypted forth text is:            ");
        System.out.println(utils.parseHexToAscii(CBCdecrypted4_1));



        System.out.println("---------------------------------------------\n");
        String CBCplaintext4_2 = "Your time is limited, so don't waste it living someone else's life. Don't be trapped by dogma, which is living with the results of other people's thinking.";
        String [][] CBCciphertext4_2 = cbc.CBCEncryption(CBCplaintext4_2,key3);
        System.out.println("The fifth plaintext is:              "+CBCplaintext4_2);
        System.out.print("The fifth encrypted text using CBC is: ");
        for(int i=0;i<CBCciphertext4_2.length;i++){
            for(int j=0;j<4;j++)
                System.out.print(CBCciphertext4_2[i][j]);
        }
        System.out.print("\nThe decrypted fifth text in hexadecimal is:   ");
        String[][] CBCdecrypted4_2 = cbc.CBCDecryption(CBCciphertext4_2,key3);
        for(int i=0;i<CBCdecrypted4_2.length;i++){
            for(int j=0;j<2;j++){
                System.out.print(CBCdecrypted4_2[i][j]);
            }
        }
        System.out.print("\nThe decrypted fifth text is:            ");
        System.out.println(utils.parseHexToAscii(CBCdecrypted4_2));

    }


}
