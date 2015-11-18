package cn.met0.util;


/**
 * 数字工具类
 * @author Met0
 *
 */
public class NumberUtil {

	/**
	 * 异或
	 * @param xordate 异或的数据
	 * @return
	 */
	public static int xor(byte[] xordate){
		int result = xordate[0];
		for(int i = 1; i < xordate.length; i++){
			result = result ^ xordate[i];
		}
		return result;	
	}
	
	
	public static byte[] xorFor(byte[] date){
		byte result[] = new byte[date.length];
		for(int i = 0; i < date.length; i++){
			result[i] = (byte) (date[i] ^ 0XFF);
		}
		return result;
	}
	
	
	public static void main(String[] args) {
		byte[] a =HexBin.decode("001111");
		byte[] b = xorFor(a);
		
		System.out.println(HexBin.encode(b));
	}
	
}
