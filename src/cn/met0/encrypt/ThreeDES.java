package cn.met0.encrypt;

import java.io.UnsupportedEncodingException;

/**
 * 
 * @author liyan
 * 
 *
 */

public class ThreeDES {

	/*****************************鍘嬬缉鏇挎崲S-Box锟� **************************************************/

	private static final int[][] s1 = {
			{ 14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7 },

			{ 0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8 },

			{ 4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0 },

			{ 15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13 } };

	private static final int[][] s2 = {
			{ 15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10 },

			{ 3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5 },

			{ 0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15 },

			{ 13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9 } };

	private static final int[][] s3 = {
			{ 10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8 },

			{ 13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1 },

			{ 13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7 },

			{ 1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12 } };

	private static final int[][] s4 = {
			{ 7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15 },

			{ 13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9 },// erorr

			{ 10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4 },

			{ 3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14 } };

	private static final int[][] s5 = {
			{ 2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9 },

			{ 14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6 },

			{ 4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14 },

			{ 11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3 } };

	private static final int[][] s6 = {
			{ 12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11 },

			{ 10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8 },

			{ 9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6 },

			{ 4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13 } };

	private static final int[][] s7 = {
			{ 4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1 },

			{ 13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6 },

			{ 1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2 },

			{ 6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12 } };

	private static final int[][] s8 = {
			{ 13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7 },

			{ 1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2 },

			{ 7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8 },

			{ 2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11 } };

	private static final int[] ip = { 58, 50, 42, 34, 26, 18, 10, 2,

	60, 52, 44, 36, 28, 20, 12, 4,

	62, 54, 46, 38, 30, 22, 14, 6,

	64, 56, 48, 40, 32, 24, 16, 8,

	57, 49, 41, 33, 25, 17, 9, 1,

	59, 51, 43, 35, 27, 19, 11, 3,

	61, 53, 45, 37, 29, 21, 13, 5,

	63, 55, 47, 39, 31, 23, 15, 7 };

	private static final int[] _ip = { 40, 8, 48, 16, 56, 24, 64, 32,

	39, 7, 47, 15, 55, 23, 63, 31,

	38, 6, 46, 14, 54, 22, 62, 30,

	37, 5, 45, 13, 53, 21, 61, 29,

	36, 4, 44, 12, 52, 20, 60, 28,

	35, 3, 43, 11, 51, 19, 59, 27,

	34, 2, 42, 10, 50, 18, 58, 26,

	33, 1, 41, 9, 49, 17, 57, 25 };

	// 姣忔瀵嗛挜寰幆宸︾Щ浣嶆暟

	private static final int[] LS = { 1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2,
			2, 1 };

	private static int[][] subKey = new int[16][48];

	private static int HEX = 0;

	private static int ASC = 1;

	/**
	 * 
	 * 灏嗗崄鍏繘鍒禔--F杞崲鎴愬搴旀暟锟�
	 * 
	 * @param ch
	 * 
	 * @return
	 * 
	 * @throws Exception
	 */

	public static int getIntByChar(char ch) throws Exception {

		char t = Character.toUpperCase(ch);

		int i = 0;

		switch (t) {

		case '0':

		case '1':

		case '2':

		case '3':

		case '4':

		case '5':

		case '6':

		case '7':

		case '8':

		case '9':

			i = Integer.parseInt(Character.toString(t));

			break;

		case 'A':

			i = 10;

			break;

		case 'B':

			i = 11;

			break;

		case 'C':

			i = 12;

			break;

		case 'D':

			i = 13;

			break;

		case 'E':

			i = 14;

			break;

		case 'F':

			i = 15;

			break;

		default:

			throw new Exception("getIntByChar was wrong");

		}

		return i;

	}

	/**
	 * 
	 * 灏嗗瓧绗︿覆杞崲鎴愪簩杩涘埗鏁扮粍
	 * 
	 * @param source
	 *            : 16瀛楄妭
	 * 
	 * @return
	 */

	public static int[] string2Binary(String source) {

		int len = source.length();

		int[] dest = new int[len * 4];

		char[] arr = source.toCharArray();

		for (int i = 0; i < len; i++) {

			int t = 0;

			try {

				t = getIntByChar(arr[i]);

				// System.out.println(arr[i]);

			} catch (Exception e) {

				e.printStackTrace();

			}

			String[] str = Integer.toBinaryString(t).split("");

			int k = i * 4 + 3;

			for (int j = str.length - 1; j > 0; j--) {

				dest[k] = Integer.parseInt(str[j]);

				k--;

			}

		}

		return dest;

	}

	/**
	 * 
	 * 杩斿洖x鐨剏娆℃柟
	 * 
	 * @param x
	 * 
	 * @param y
	 * 
	 * @return
	 */

	public static int getXY(int x, int y) {

		int temp = x;

		if (y == 0)
			x = 1;

		for (int i = 2; i <= y; i++) {

			x *= temp;

		}

		return x;

	}

	/**
	 * 
	 * s锟�浣嶉暱搴︾殑浜岃繘鍒跺瓧绗︿覆
	 * 
	 * @param s
	 * 
	 * @return
	 */

	public static String binary2Hex(String s) {

		int len = s.length();

		int result = 0;

		int k = 0;

		if (len > 4)
			return null;

		for (int i = len; i > 0; i--) {

			result += Integer.parseInt(s.substring(i - 1, i)) * getXY(2, k);

			k++;

		}

		switch (result) {

		case 0:

		case 1:

		case 2:

		case 3:

		case 4:

		case 5:

		case 6:

		case 7:

		case 8:

		case 9:

			return "" + result;

		case 10:

			return "A";

		case 11:

			return "B";

		case 12:

			return "C";

		case 13:

			return "D";

		case 14:

			return "E";

		case 15:

			return "F";

		default:

			return null;

		}

	}

	/**
	 * 
	 * 灏唅nt杞崲鎴怘ex
	 * 
	 * @param i
	 * 
	 * @return
	 * 
	 * @throws Exception
	 */

	public static String int2Hex(int i) {

		switch (i) {

		case 0:

		case 1:

		case 2:

		case 3:

		case 4:

		case 5:

		case 6:

		case 7:

		case 8:

		case 9:

			return "" + i;

		case 10:

			return "A";

		case 11:

			return "B";

		case 12:

			return "C";

		case 13:

			return "D";

		case 14:

			return "E";

		case 15:

			return "F";

		default:

			return null;

		}

	}

	/**
	 * 
	 * 灏嗕簩杩涘埗瀛楃涓茶浆鎹㈡垚鍗佸叚杩涘埗瀛楃锟�
	 * 
	 * @param s
	 * 
	 * @return
	 */

	public static String binary2ASC(String s) {

		String str = "";

		int ii = 0;

		int len = s.length();

		// 涓嶅4bit宸﹁ˉ0

		if (len % 4 != 0) {

			while (ii < 4 - len % 4) {

				s = "0" + s;

			}

		}

		for (int i = 0; i < len / 4; i++) {

			str += binary2Hex(s.substring(i * 4, i * 4 + 4));

		}

		return str;

	}

	/**
	 * 
	 * IP鍒濆缃崲
	 * 
	 * @param source
	 * 
	 * @return
	 */

	public static int[] changeIP(int[] source) {

		int[] dest = new int[64];

		for (int i = 0; i < 64; i++) {

			dest[i] = source[ip[i] - 1];

		}

		return dest;

	}

	/**
	 * 
	 * IP-1閫嗙疆锟�
	 * 
	 * @param source
	 * 
	 * @return
	 */

	public static int[] changeInverseIP(int[] source) {

		int[] dest = new int[64];

		for (int i = 0; i < 64; i++) {

			dest[i] = source[_ip[i] - 1];

		}

		return dest;

	}

	/**
	 * 
	 * 锟�2bit鎵╁睍锟�8bit
	 * 
	 * @param source
	 * 
	 * @return
	 */

	public static int[] expend(int[] source) {

		int[] ret = new int[48];

		int[] temp = { 32, 1, 2, 3, 4, 5,

		4, 5, 6, 7, 8, 9,

		8, 9, 10, 11, 12, 13,

		12, 13, 14, 15, 16, 17,

		16, 17, 18, 19, 20, 21,

		20, 21, 22, 23, 24, 25,

		24, 25, 26, 27, 28, 29,

		28, 29, 30, 31, 32, 1 };

		for (int i = 0; i < 48; i++) {

			ret[i] = source[temp[i] - 1];

		}

		return ret;

	}

	/**
	 * 
	 * 锟�8bit鍘嬬缉锟�2bit
	 * 
	 * @param source
	 *            (48bit)
	 * 
	 * @return R(32bit)
	 * 
	 *         B=E(R)鈯昁锛屽皢48 浣嶇殑B 鍒嗘垚8 涓垎缁勶紝B=B1B2B3B4B5B6B7B8
	 */

	public static int[] press(int[] source) {

		int[] ret = new int[32];

		int[][] temp = new int[8][6];

		int[][][] s = { s1, s2, s3, s4, s5, s6, s7, s8 };

		StringBuffer str = new StringBuffer();

		for (int i = 0; i < 8; i++) {

			for (int j = 0; j < 6; j++) {

				temp[i][j] = source[i * 6 + j];

			}

		}

		for (int i = 0; i < 8; i++) {

			// (16)

			int x = temp[i][0] * 2 + temp[i][5];

			// (2345)

			int y = temp[i][1] * 8 + temp[i][2] * 4 + temp[i][3] * 2
					+ temp[i][4];

			int val = s[i][x][y];

			String ch = int2Hex(val);

			// System.out.println("x=" + x + ",y=" + y + "-->" + ch);

			// String ch = Integer.toBinaryString(val);

			str.append(ch);

		}

		// System.out.println(str.toString());

		ret = string2Binary(str.toString());

		// printArr(ret);

		// 缃崲P

		ret = dataP(ret);

		return ret;

	}

	/**
	 * 
	 * 缃崲P(32bit)
	 * 
	 * @param source
	 * 
	 * @return
	 */

	public static int[] dataP(int[] source) {

		int[] dest = new int[32];

		int[] temp = { 16, 7, 20, 21,

		29, 12, 28, 17,

		1, 15, 23, 26,

		5, 18, 31, 10,

		2, 8, 24, 14,

		32, 27, 3, 9,

		19, 13, 30, 6,

		22, 11, 4, 25 };

		int len = source.length;

		for (int i = 0; i < len; i++) {

			dest[i] = source[temp[i] - 1];

		}

		return dest;

	}

	/**
	 * 
	 * @param R
	 *            (锟�2bit)
	 * 
	 * @param K
	 *            (48bit鐨勮疆瀛愬瘑锟�
	 * 
	 * @return 32bit
	 */

	public static int[] f(int[] R, int[] K) {

		int[] dest = new int[32];

		int[] temp = new int[48];

		// 鍏堝皢杈撳叆32bit鎵╁睍锟�8bit

		int[] expendR = expend(R);// 48bit

		// 涓庤疆瀛愬瘑閽ヨ繘琛屽紓鎴栬繍锟�

		temp = diffOr(expendR, K);

		// 鍘嬬缉锟�2bit

		dest = press(temp);

		// System.out.println("need press data----->");

		// printArr(temp);

		return dest;

	}

	/**
	 * 
	 * 涓や釜绛夐暱鐨勬暟缁勫仛寮傛垨
	 * 
	 * @param source1
	 * 
	 * @param source2
	 * 
	 * @return
	 */

	public static int[] diffOr(int[] source1, int[] source2) {

		int len = source1.length;

		int[] dest = new int[len];

		for (int i = 0; i < len; i++) {

			dest[i] = source1[i] ^ source2[i];

		}

		return dest;

	}

	/**
	 * 
	 * DES鍔犲瘑--->瀵圭О瀵嗛挜
	 * 
	 * D = Ln(32bit)+Rn(32bit)
	 * 
	 * 缁忚繃16杞疆锟�
	 * 
	 * @param D
	 *            (16byte)鏄庢枃
	 * 
	 * @param K
	 *            (16byte)杞瓙瀵嗛挜
	 * 
	 * @return (16byte)瀵嗘枃
	 */

	public static String encryption(String D, String K) {

		String str = "";

		int[] temp = new int[64];

		int[] data = string2Binary(D);

		// printArr(data);

		// 绗竴姝ュ垵濮嬬疆锟�

		data = changeIP(data);

		// printArr(data);

		int[][] left = new int[17][32];

		int[][] right = new int[17][32];

		for (int j = 0; j < 32; j++) {

			left[0][j] = data[j];

			right[0][j] = data[j + 32];

		}

		// printArr(left[0]);

		// printArr(right[0]);

		setKey(K);// sub key ok

		for (int i = 1; i < 17; i++) {

			// 鑾峰彇(48bit)鐨勮疆瀛愬瘑锟�

			int[] key = subKey[i - 1];

			// L1 = R0

			left[i] = right[i - 1];

			// R1 = L0 ^ f(R0,K1)

			int[] fTemp = f(right[i - 1], key);// 32bit

			right[i] = diffOr(left[i - 1], fTemp);

		}

		// 锟�锟斤拷缁勫悎鐨勬椂鍊欙紝宸﹀彸璋冩崲**************************************************

		for (int i = 0; i < 32; i++) {

			temp[i] = right[16][i];

			temp[32 + i] = left[16][i];

		}

		temp = changeInverseIP(temp);

		str = binary2ASC(intArr2Str(temp));

		return str;

	}

	/**
	 * 
	 * DES瑙ｅ瘑--->瀵圭О瀵嗛挜
	 * 
	 * 瑙ｅ瘑绠楁硶涓庡姞瀵嗙畻娉曞熀鏈浉鍚岋紝涓嶅悓涔嬪浠呭湪浜庤疆瀛愬瘑閽ョ殑浣跨敤椤哄簭閫嗗簭锛屽嵆瑙ｅ瘑鐨勭1
	 * 
	 * 杞瓙瀵嗛挜涓哄姞瀵嗙殑锟�6 杞瓙瀵嗛挜锛岃В瀵嗙殑锟� 杞瓙瀵嗛挜涓哄姞瀵嗙殑锟�5 杞瓙瀵嗛挜锛岋拷?鈥︼紝
	 * 
	 * 瑙ｅ瘑鐨勭16 杞瓙瀵嗛挜涓哄姞瀵嗙殑锟� 杞瓙瀵嗛挜锟�
	 * 
	 * @param source瀵嗘枃
	 * 
	 * @param key瀵嗛挜
	 * 
	 * @return
	 */

	public static String discryption(String source, String key) {

		String str = "";

		int[] data = string2Binary(source);// 64bit

		// 绗竴姝ュ垵濮嬬疆锟�

		data = changeIP(data);

		int[] left = new int[32];

		int[] right = new int[32];

		int[] tmp = new int[32];

		for (int j = 0; j < 32; j++) {

			left[j] = data[j];

			right[j] = data[j + 32];

		}

		setKey(key);// sub key ok

		for (int i = 16; i > 0; i--) {

			// 鑾峰彇(48bit)鐨勮疆瀛愬瘑锟�

			/********* 涓嶅悓涔嬪 **********/

			int[] sKey = subKey[i - 1];

			tmp = left;

			// R1 = L0

			left = right;

			// L1 = R0 ^ f(L0,K1)

			int[] fTemp = f(right, sKey);// 32bit

			right = diffOr(tmp, fTemp);

		}

		// 锟�锟斤拷缁勫悎鐨勬椂鍊欙紝宸﹀彸璋冩崲**************************************************

		for (int i = 0; i < 32; i++) {

			data[i] = right[i];

			data[32 + i] = left[i];

		}

		data = changeInverseIP(data);

		for (int i = 0; i < data.length; i++) {

			str += data[i];

		}

		str = binary2ASC(str);

		return str;

	}

	/**
	 * 
	 * 鍗曪拷?闀垮瘑閽ES(16byte)
	 * 
	 * @param source
	 * 
	 * @param key
	 * 
	 * @param type
	 *            0:encrypt 1:discrypt
	 * 
	 * @return
	 */

	public static String DES_1(String source, String key, int type) {

		if (source.length() != 16 || key.length() != 16)
			return null;

		if (type == 0) {

			return encryption(source, key);

		}

		if (type == 1) {

			return discryption(source, key);

		}

		return null;

	}

	/**
	 * 
	 * 
	 * 
	 * @param source
	 * 
	 * @param key
	 * 
	 * @param type
	 *            0:encrypt 1:discrypt
	 * 
	 * @return
	 */

	public static String DES_2(String source, String key, int type) {

		return null;

	}

	/**
	 * 
	 * 涓夐噸DES绠楁硶(鍙岋拷?闀垮瘑锟�32byte))
	 * 
	 * 瀵嗛挜K1鍜孠2
	 * 
	 * 1銆佸厛鐢↘1鍔犲瘑鏄庢枃
	 * 
	 * 2銆佹帴锟�锟斤拷K2瀵逛笂锟�锟斤拷鐨勭粨鏋滆繘琛岃В锟�
	 * 
	 * 3銆佺劧鍚庣敤K1瀵逛笂锟�锟斤拷鐨勭粨鏋滆繘琛屽姞锟�
	 * 
	 * @param source
	 * 
	 * @param key
	 * 
	 * @param type
	 *            0:encrypt 1:discrypt
	 * 
	 * @return
	 */

	public static String DES_3(String source, String key, int type) {

		if (key.length() != 32 || source.length() != 16)
			return null;

		String temp = null;

		String K1 = key.substring(0, key.length() / 2);

		String K2 = key.substring(key.length() / 2);

		// System.out.println("K1--->" + K1);

		// System.out.println("K2--->" + K2);

		if (type == 0) {

			temp = encryption(source, K1);

			// System.out.println("step1--->" + temp);

			temp = discryption(temp, K2);

			// System.out.println("step2--->" + temp);

			return encryption(temp, K1);

		}

		if (type == 1) {

			temp = discryption(source, K1);

			temp = encryption(temp, K2);

			return discryption(temp, K1);

		}

		return null;

	}

	/************************************ 48bit鐨勮疆瀛愬瘑閽ョ殑鐢熸垚 **********************************************************/

	/**
	 * 
	 * 锟�4bit鐨勫瘑閽ヨ浆鎹㈡垚56bit
	 * 
	 * @param source
	 * 
	 * @return
	 */

	public static int[] keyPC_1(int[] source) {

		int[] dest = new int[56];

		int[] temp = { 57, 49, 41, 33, 25, 17, 9,

		1, 58, 50, 42, 34, 26, 18,

		10, 2, 59, 51, 43, 35, 27,

		19, 11, 3, 60, 52, 44, 36,

		63, 55, 47, 39, 31, 23, 15,

		7, 62, 54, 46, 38, 30, 22,

		14, 6, 61, 53, 45, 37, 29,

		21, 13, 5, 28, 20, 12, 4 };

		for (int i = 0; i < 56; i++) {

			dest[i] = source[temp[i] - 1];

		}

		return dest;

	}

	/**
	 * 
	 * 灏嗗瘑閽ュ惊鐜乏绉籭锟�
	 * 
	 * @param source
	 *            浜岃繘鍒跺瘑閽ユ暟锟�
	 * 
	 * @param i
	 *            寰幆宸︾Щ浣嶆暟
	 * 
	 * @return
	 */

	public static int[] keyLeftMove(int[] source, int i) {

		int temp = 0;

		int len = source.length;

		int ls = LS[i];

		// System.out.println("len" + len + ",LS[" + i + "]=" + ls);

		for (int k = 0; k < ls; k++) {

			temp = source[0];

			for (int j = 0; j < len - 1; j++) {

				source[j] = source[j + 1];

			}

			source[len - 1] = temp;

		}

		return source;

	}

	/**
	 * 
	 * 锟�6bit鐨勫瘑閽ヨ浆鎹㈡垚48bit
	 * 
	 * @param source
	 * 
	 * @return
	 */

	public static int[] keyPC_2(int[] source) {

		int[] dest = new int[48];

		int[] temp = { 14, 17, 11, 24, 1, 5,

		3, 28, 15, 6, 21, 10,

		23, 19, 12, 4, 26, 8,

		16, 7, 27, 20, 13, 2,

		41, 52, 31, 37, 47, 55,

		30, 40, 51, 45, 33, 48,

		44, 49, 39, 56, 34, 53,

		46, 42, 50, 36, 29, 32 };

		for (int i = 0; i < 48; i++) {

			dest[i] = source[temp[i] - 1];

		}

		return dest;

	}

	/**
	 * 
	 * 鑾峰彇杞瓙瀵嗛挜(48bit)
	 * 
	 * @param source
	 * 
	 * @return
	 */

	public static void setKey(String source) {

		if (subKey.length > 0)
			subKey = new int[16][48];

		// 瑁呮崲锟�4bit

		int[] temp = string2Binary(source);

		// 锟�6bit鍧囧垎鎴愪袱閮ㄥ垎

		int[] left = new int[28];

		int[] right = new int[28];

		// 缁忚繃PC-1锟�4bit杞崲锟�6bit

		int[] temp1 = new int[56];

		temp1 = keyPC_1(temp);

		// printArr(temp1);

		// 灏嗙粡杩囪浆鎹㈢殑temp1鍧囧垎鎴愪袱閮ㄥ垎

		for (int i = 0; i < 28; i++) {

			left[i] = temp1[i];

			right[i] = temp1[i + 28];

		}

		// 缁忚繃16娆″惊鐜乏绉伙紝鐒跺悗PC-2缃崲

		for (int i = 0; i < 16; i++) {

			left = keyLeftMove(left, LS[i]);

			right = keyLeftMove(right, LS[i]);

			for (int j = 0; j < 28; j++) {

				temp1[j] = left[j];

				temp1[j + 28] = right[j];

			}

			// printArr(temp1);

			subKey[i] = keyPC_2(temp1);

		}

	}

	public static void printArr(int[] source) {

		int len = source.length;

		for (int i = 0; i < len; i++) {

			System.out.print(source[i]);

		}

		System.out.println();

	}

	/**
	 * 
	 * 灏咥SC瀛楃涓茶浆锟�6杩涘埗瀛楃锟�
	 * 
	 * @param asc
	 * 
	 * @return
	 */

	public static String ASC_2_HEX(String asc) {

		StringBuffer hex = new StringBuffer();

		try {

			byte[] bs = asc.toUpperCase().getBytes("UTF-8");

			for (byte b : bs) {

				hex.append(Integer.toHexString(new Byte(b).intValue()));

			}

		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();

		}

		return hex.toString();

	}

	/**
	 * 
	 * 锟�6杩涘埗鐨勫瓧绗︿覆杞崲鎴怉SC鐨勫瓧绗︿覆
	 * 
	 * 锟�6杩涘埗鐨勫瓧绗︿覆鍘嬬缉鎴怋CD锟�30313233343536373839414243444546)-->(0123456789ABCDEF)
	 * 
	 * @param hex
	 * 
	 * @return
	 */

	public static String HEX_2_ASC(String hex) {

		String asc = null;

		int len = hex.length();

		byte[] bs = new byte[len / 2];

		for (int i = 0; i < len / 2; i++) {

			bs[i] = Byte.parseByte(hex.substring(i * 2, i * 2 + 2), 16);

		}

		try {

			asc = new String(bs, "UTF-8");

		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();

		}

		return asc;

	}

	/**
	 * 
	 * 璁＄畻MAC(hex)
	 * 
	 * ANSI-X9.9-MAC(16鐨勬暣鏁帮拷?涓嶈ˉ)
	 * 
	 * PBOC-DES-MAC(16鐨勬暣鏁帮拷?锟�000000000000000)
	 * 
	 * 浣跨敤鍗曪拷?闀垮瘑閽ES绠楁硶
	 * 
	 * @param key瀵嗛挜
	 *            (16byte)
	 * 
	 * @param vector鍒濆鍚戦噺0000000000000000
	 * 
	 * @param data鏁版嵁
	 * 
	 * @return mac
	 */

	public static String MAC(String key, String vector, String data, int type) {

		if (type == ASC) {

			data = ASC_2_HEX(data);

		}

		int len = data.length();

		int arrLen = len / 16 + 1;

		String[] D = new String[arrLen];

		if (vector == null)
			vector = "0000000000000000";

		if (len % 16 == 0) {

			data += "8000000000000000";

		} else {

			data += "80";

			for (int i = 0; i < 15 - len % 16; i++) {

				data += "00";

			}

		}

		for (int i = 0; i < arrLen; i++) {

			D[i] = data.substring(i * 16, i * 16 + 16);

			System.out.println("D[" + i + "]=" + D[i]);

		}

		// D0 Xor Vector

		String I = xOr(D[0], vector);

		String O = null;

		for (int i = 1; i < arrLen; i++) {

			// System.out.println(i + "**************");

			// System.out.println("I=" + I);

			O = DES_1(I, key, 0);

			// System.out.println("O=" + O);

			I = xOr(D[i], O);

			// System.out.println("I=" + I);

		}

		I = DES_1(I, key, 0);

		return I;

	}

	/**
	 * 
	 * 灏唖1鍜宻2鍋氬紓鎴栵紝鐒跺悗杩斿洖
	 * 
	 * @param s1
	 * 
	 * @param s2
	 * 
	 * @return
	 */

	public static String xOr(String s1, String s2) {

		int[] iArr = diffOr(string2Binary(s1), string2Binary(s2));

		return binary2ASC(intArr2Str(iArr));

	}

	/**
	 * 
	 * 灏唅nt绫诲瀷鏁扮粍鎷兼帴鎴愬瓧绗︿覆
	 * 
	 * @param arr
	 * 
	 * @return
	 */

	public static String intArr2Str(int[] arr) {

		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < arr.length; i++) {

			sb.append(arr[i]);

		}

		return sb.toString();

	}

	/**
	 * 
	 * 灏哾ata鍒嗘暎
	 * 
	 * @param data
	 * 
	 * @param key
	 * 
	 * @param type
	 * 
	 * @return
	 */

	public static String divData(String data, String key, int type) {

		String left = null;

		String right = null;

		if (type == HEX) {

			left = key.substring(0, 16);

			right = key.substring(16, 32);

		}

		if (type == ASC) {

			left = ASC_2_HEX(key.substring(0, 8));

			right = ASC_2_HEX(key.substring(8, 16));

		}

		// 鍔犲瘑

		data = DES_1(data, left, 0);

		// 瑙ｅ瘑

		data = DES_1(data, right, 1);

		// 鍔犲瘑

		data = DES_1(data, left, 0);

		return data;

	}

	/**
	 * 
	 * 鍙栧弽(10001)--->(01110)
	 * 
	 * @param source
	 * 
	 * @return
	 */

	public static String reverse(String source) {

		int[] data = string2Binary(source);

		int j = 0;

		for (int i : data) {

			data[j++] = 1 - i;

		}

		return binary2ASC(intArr2Str(data));

	}

	/**
	 * 
	 * 涓诲瘑閽ラ渶瑕佺粡杩囦袱娆″垎鏁ｈ幏寰桰C鍗′腑鐨勫瓙瀵嗛挜
	 * 
	 * @param issuerFlag鍙戝崱鏂规爣璇嗙
	 * 
	 * @param appNo搴旂敤搴忓垪鍙峰嵆鍗″彿
	 * 
	 * @param mpk涓诲瘑閽�
	 * 
	 * @return
	 */

	public static String getDPK(String issuerFlag, String appNo, String mpk) {

		// 绗竴娆″垎鏁�

		StringBuffer issuerMPK = new StringBuffer();

		// 鑾峰彇Issuer MPK宸﹀崐杈�

		issuerMPK.append(divData(issuerFlag, mpk, 0));

		// 鑾峰彇Issuer MPK鍙冲崐杈�

		issuerMPK.append(divData(reverse(issuerFlag), mpk, 0));

		// 绗簩娆″垎鏁�

		StringBuffer dpk = new StringBuffer();

		// 鑾峰彇DPK宸﹀崐杈�

		dpk.append(divData(appNo, issuerMPK.toString(), 0));

		// 鑾峰彇DPK鍙冲崐杈�

		dpk.append(divData(reverse(appNo), issuerMPK.toString(), 0));

		return dpk.toString();

	}

	/**
	 * 3des鍔犲瘑
	 * @param hexdata 16杩涘埗鏁版嵁
	 * @param keydata 16杩涘埗key
	 * @return
	 */
	public static String TripeDes(String hexdata, String keydata) {
		return DES_3(hexdata, keydata, 0);
	}

}