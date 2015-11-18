package cn.met0.util;

/**
 * 
 * @author ��Ƭ�����
 *
 */
public class Command {

	/**
	 * ������Ƭ����
	 * @param stx Э��ͷ
	 * @param cmd ָ����
	 * @param date ����
	 * @return �����Ĳ���ָ��
	 */
	public static String getCommand(String stx, String cmd, String date) {

		// Э��ͷ
		byte[] stxdate = HexBin.decode(stx);

		// cmd �� date
		byte[] xordate = HexBin.decode(cmd + date);

		// ����
		byte[] len = { (byte) (xordate.length & 0xFF) };

		// xorֵ
		byte[] xor = { (byte) (NumberUtil.xor(xordate) & 0xFF) };

		String lenHex = HexBin.encode(len);
		String xorHex = HexBin.encode(xor);

		return stx + lenHex + cmd + date + xorHex;
	}

}
