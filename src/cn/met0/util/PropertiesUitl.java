package cn.met0.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 读取Properties配置文件
 * 
 * @author met0
 *
 */
public class PropertiesUitl {

	private PropertiesUitl() {
	}

	/**
	 * 读取配置文件
	 * 
	 * @param file
	 *            配置文件流
	 * @return 键值对Map
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map load(InputStream file) throws FileNotFoundException,
			IOException {

		Map map = new HashMap();
		Properties prop = new Properties();
		prop.load(file);
		Enumeration enums = prop.propertyNames();
		while (enums.hasMoreElements()) {
			String key = (String) enums.nextElement();
			String value = (String) prop.get(key);
			map.put(key, value);
		}
		if (file != null) {
			file.close();
		}
		return map;
	}

}
