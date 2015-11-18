package cn.met0.command.jsc;

import cn.met0.util.HexBin;
import cn.met0.util.NumberUtil;

/**
 * 
 * @author 吉胜读卡器命令创建
 *
 */
public class Command {

	public static void main(String[] args) {

	}

	/**
	 * 创建卡片命令
	 * 
	 * @param cmd
	 *            指令码
	 * @param date
	 *            数据
	 * @return 完整的操作指令
	 */
	public static String getCommand(String cmd, String date) {

		// cmd 和 date
		byte[] xordate = HexBin.decode(cmd + date);
		// 长度
		String lenHex = Complement(Integer.toHexString(xordate.length * 2 + 2),
				4);
		// xor值
		byte[] xor = { (byte) (NumberUtil.xor(xordate) & 0xFF) };
		// xor 16 进制
		String xorHex = HexBin.encode(xor);
		return "JSC" + lenHex + cmd + date + xorHex;
	}

	public static void exceAPDUIsSuccess(String r, String message)
			throws Exception {
		int rlen = r.length();
		if (!"9000".equals(r.substring(rlen - 6, rlen - 2))) {
			throw new Exception(message);
		}
	}

	/**
	 * 执行命令是否成功
	 * 
	 * @param r
	 * @param message
	 * @throws Exception
	 *             命令执行失败
	 */
	public static void exceIsSuccess(String r, String message) throws Exception {
		if (!"00".equals(r.substring(7, 9))) {
			throw new Exception(message);
		}
	}

	/**
	 * 字符串16进制补码
	 * 
	 * @param hex
	 *            需要补码的16进制的字符串
	 * @param x
	 *            需要补码的个数
	 * @return
	 */
	public static String Complement(String hex, int x) {
		String count = "";
		int num = x - hex.length();
		while (num > 0) {
			count += 0;
			num--;
		}
		return count + hex;
	}

}
