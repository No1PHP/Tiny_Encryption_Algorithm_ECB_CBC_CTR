package TeaEncryption;


public class CTRTest {

    public static void main(String[] args) {
        int random1 = utils.randomIVGenerator(8);
        int num1 = random1;
        int random2 = utils.randomIVGenerator(8);
        int num2 = random2;
        int []iv = new int[]{num1,num2};
        int []tempIV = new int[]{num1,num2};
        int[] key3 = new int[]{0x73367639,0x79244226,0x45294840,0x4D635166};

        CTR ctr = new CTR();
        System.out.println("\n---------------------------------------------\n");
        System.out.println("---------------------------------------------\n");
        System.out.println("Below is the CTR ecryption demonstration of the two text");
        System.out.println("---------------------------------------------\n");


        String CTRplaintext4_1 = "Four score and seven years ago our fathers brought forth on this continent, a new nation, conceived in Liberty, and dedicated to the proposition that all men are created equal.";
        String [][]CTRciphertext4_1 = ctr.CTREncryption(CTRplaintext4_1,key3,iv);
        System.out.println("The forth plaintext is:                 "+CTRplaintext4_1);
        System.out.print("The forth encrypted text using CTR is:              ");
        for(int i=0;i<CTRciphertext4_1.length;i++){
            for(int j=0;j<4;j++)
                System.out.print(CTRciphertext4_1[i][j]);
        }
        System.out.print("\nThe decrypted forth text in hexadecimal is:   ");
        String[][] CTRdecrypted4_1 = ctr.CTRDecryption(CTRciphertext4_1,key3,tempIV);
        for(int i=0;i<CTRdecrypted4_1.length;i++){
            for(int j=0;j<2;j++){
                System.out.print(CTRdecrypted4_1[i][j]);
            }
        }
        System.out.print("\nThe decrypted forth text is:            ");
        System.out.println(utils.parseHexToAscii(CTRdecrypted4_1));



        System.out.println("---------------------------------------------\n");
        String CTRplaintext4_2 = "Your time is limited, so don't waste it living someone else's life. Don't be trapped by dogma, which is living with the results of other people's thinking.";
        String [][]CTRciphertext4_2 = ctr.CTREncryption(CTRplaintext4_2,key3,iv);
        System.out.println("The fifth plaintext is:                 "+CTRplaintext4_2);
        System.out.print("The fifth encrypted text using CTR is:              ");
        for(int i=0;i<CTRciphertext4_2.length;i++){
            for(int j=0;j<4;j++)
                System.out.print(CTRciphertext4_2[i][j]);
        }
        System.out.print("\nThe decrypted fifth text in hexadecimal is:   ");
        String[][] CTRdecrypted4_2 = ctr.CTRDecryption(CTRciphertext4_2,key3,tempIV);
        for(int i=0;i<CTRdecrypted4_2.length;i++){
            for(int j=0;j<2;j++){
                System.out.print(CTRdecrypted4_2[i][j]);
            }
        }
        System.out.print("\nThe decrypted fifth text is:            ");
        System.out.println(utils.parseHexToAscii(CTRdecrypted4_2));
    }
}
