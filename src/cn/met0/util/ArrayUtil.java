package cn.met0.util;


/**
 * 数组工具类
 * @author Met0
 *
 * @param <T>
 */
public class ArrayUtil {
	
	/**
	 * 反转数组
	 * @param arr 反转的数据
	 * @return 反转后的数组
	 */
	public static byte[] reverse(byte[] arr){
		byte[] tmp = new byte[arr.length];
		int j = 0;
		for(int i = arr.length - 1; i >= 0; i--){
			tmp[j++] = arr[i];
		}
		return tmp;
	}
	
	
	/**
	 * 数组添加数据
	 * @param arr 需要添加的数组
	 * @param arr2 添加的数据
	 * @return
	 */
	public static Object[] arrayAdd(Object[] arr,Object...arr2){
		
		int len=arr.length+arr2.length;
		
		Object[] newArr=new Object[len];
		
		for(int i=0;i<arr.length;i++){
			newArr[i]=arr[i];
		}
		int j=0;
		for(int i=arr.length;i<len;i++){
			newArr[i]=arr2[j++];
		}
		
		return newArr;
	}
	
	
	/**
	 * 数组变字符串
	 * @param objects
	 * @return
	 */
	public static String toString(Object...objects){
		StringBuilder data=new StringBuilder();
		
		for(Object str:objects){
			data.append(str);
		}
		return new String(data);
	}
	
}
