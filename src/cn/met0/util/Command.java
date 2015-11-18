package cn.met0.util;

/**
 * 
 * @author 卡片命令创建
 *
 */
public class Command {

	/**
	 * 创建卡片命令
	 * @param stx 协议头
	 * @param cmd 指令码
	 * @param date 数据
	 * @return 完整的操作指令
	 */
	public static String getCommand(String stx, String cmd, String date) {

		// 协议头
		byte[] stxdate = HexBin.decode(stx);

		// cmd 和 date
		byte[] xordate = HexBin.decode(cmd + date);

		// 长度
		byte[] len = { (byte) (xordate.length & 0xFF) };

		// xor值
		byte[] xor = { (byte) (NumberUtil.xor(xordate) & 0xFF) };

		String lenHex = HexBin.encode(len);
		String xorHex = HexBin.encode(xor);

		return stx + lenHex + cmd + date + xorHex;
	}

}
