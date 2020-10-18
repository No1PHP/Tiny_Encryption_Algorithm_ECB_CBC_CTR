package TeaEncryption;

public class TEA{

	protected static int delta = 0x9e3779b9;
	protected int sum, left, right;
	private int ciphertext[], plaintext[];
	protected String cipherblocks[][];
	protected int PADDING_MODE = 0;

	public int[] encrypt(int[] plaintext, int[] key){
		left = plaintext[0];
		right = plaintext[1];
		sum = 0;
		for(int i = 0; i < 32; i++){
			sum += delta;
			left += ((right << 4) + key[0]) ^ (right+sum) ^ ((right >> 5) + key[1]);
			right += ((left << 4) + key[2]) ^ (left+sum) ^ ((left >> 5) + key[3]);
		}
		ciphertext = new int[2];
		ciphertext[0] = left;
		ciphertext[1] = right;
		return ciphertext;
	}


	public String[][] ECBEncryption(String plaintext, int[] key){
		int ciphertext_oreder = 0, length = plaintext.length();
		cipherblocks = new String[length/4 + 1][4];

		char[] text_chars =plaintext.toCharArray();
		int extraNum = plaintext.length()%4; //
		for(int i=0;i<length;i=i+4){ //i stands for the character's order in the string

			if(length-i>=4){ //when there is more than 4 characters beyond the current anchor
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
				int transform3 = text_chars[i + 2]; //start from 2 (3rd); represnets the 3rd character's ASCII value
				String hex3 = Integer.toHexString(transform3);
				int transform4 = text_chars[i + 3]; //4th
				String hex4 = Integer.toHexString(transform4);
				String HEX2 = hex3.concat(hex4);
				text[1] = Integer.parseInt(HEX2,16);

				//System.out.println("R: "+HEX2);

					//for debugging
				//System.out.println("i: "+i);
				//System.out.println("length-i: "+(length-i));
				//System.out.println();

				int[] returned = encrypt(text,key);
				//System.out.println("returned text： "+returned[0]+returned[1]);
				String leftHexEncryptedString = Integer.toHexString(returned[0]);
				String rightHexEncryptedString = Integer.toHexString(returned[1]);
		//ciphr String is a two-dimensional array;  [0][1]for the left and right half blocks; another dimension for the whole sequential text
				cipherblocks[ciphertext_oreder][0] = leftHexEncryptedString.substring(0,4);  //left 1
				cipherblocks[ciphertext_oreder][1] = leftHexEncryptedString.substring(4);  //right 1
				cipherblocks[ciphertext_oreder][2] = rightHexEncryptedString.substring(0,4);  //left 2
				cipherblocks[ciphertext_oreder][3] = rightHexEncryptedString.substring(4);  //right 2
				ciphertext_oreder++;
			}else{
				int[] text = new int[2];
				String cipherString = "";

				//System.out.println("length-i in the padding part: "+(length-i));

				switch (extraNum){  //zero(blank space character) padding
					case (0): break;
					case (1): {
						int transform = text_chars[i];
						String hex1 = Integer.toHexString(transform);
						String hex2 = Integer.toHexString(20);
						String hex = hex1.concat(hex2);
						text[0] = Integer.parseInt(hex, 16);
						text[1] = Integer.parseInt("2020", 16);
						break;
					}
					case (2): {
						//System.out.println("i in loop now = "+i+" and 2 blank padded!!!!!!");
						String hex1 = Integer.toHexString(text_chars[i]);
						String hex2 = Integer.toHexString(text_chars[i+1]);
						String p1 = hex1.concat(hex2);
						text[0] = Integer.parseInt(p1, 16);
						text[1] = Integer.parseInt("2020", 16);
						break;
					}
					case (3): {
						String hex1 = Integer.toHexString(text_chars[i]);
						String hex2 = Integer.toHexString(text_chars[i + 1]);
						String hex3 = Integer.toHexString(text_chars[i + 2]);
						String p1 = hex1.concat(hex2);
						String p2 = hex3.concat("20");
						text[0] = Integer.parseInt(p1, 16);
						text[1] = Integer.parseInt(p2, 16);
						break;
					}

					}
					int[] returned = encrypt(text,key);

					String leftHexEncryptedString = Integer.toHexString(returned[0]);
					String rightHexEncryptedString = Integer.toHexString(returned[1]);
					//ciphr String is a two-dimensional array;  [0][1]for the left and right half blocks; another dimension for the whole sequential text
					cipherblocks[ciphertext_oreder][0] = leftHexEncryptedString.substring(0,4);  //left 1
					cipherblocks[ciphertext_oreder][1] = leftHexEncryptedString.substring(4);  //right 1
					cipherblocks[ciphertext_oreder][2] = rightHexEncryptedString.substring(0,4);  //left 2
					cipherblocks[ciphertext_oreder][3] = rightHexEncryptedString.substring(4);  //right 2
					ciphertext_oreder++;


			}
		}

		return cipherblocks;
	}


	//input cipher strings must be a multiply of 4
	//returns a decrypted hexadecimal string, [0]for left block, [1]for right block
	public String[][] Decryption(String[][] cipherStrings, int[] key){
		int plaintext_oreder = 0, length = cipherStrings.length;
		int []decrypted = new int[2];
		String[][] returned = new String[cipherStrings.length][2];

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
			decrypted = decrypt(text,key);
			returned[i][0] = Integer.toHexString(decrypted[0]);
			returned[i][1] = Integer.toHexString(decrypted[1]);
		}
		return returned;
	}

	public int[] decrypt(int[] cipher, int[] key){
		left = cipher[0];
		right = cipher[1];
		sum = delta << 5;
		for(int i = 0; i < 32; i++){
			right -= ((left << 4) + key[2]) ^ (left+sum) ^ ((left >> 5) + key[3]);
			left -= ((right << 4) + key[0]) ^ (right+sum) ^ ((right >> 5) + key[1]);
			sum -= delta;
		}
		plaintext = new int[2];
		plaintext[0] = left;
		plaintext[1] = right;
		return plaintext;
	}

}