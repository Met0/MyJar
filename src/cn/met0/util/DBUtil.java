package cn.met0.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

/**
 * 数据库工具类
 * 
 * @author Met0
 *
 */
public class DBUtil {

	private Connection conn;
	private Statement sm;
	private PreparedStatement ps;
	private ResultSet rs;

	/**
	 * 查询数据，把查询的数据转为map类型
	 * 
	 * @param sql
	 *            查询语句
	 * @param objs
	 *            查询参数
	 * @return 返回Map键值对
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public Map queryToMap(String sql, Object[] objs) throws SQLException,
			ClassNotFoundException {
		Map result = null;
		rs = query(sql, objs);
		if (rs.next()) {
			result = getMap(rs);
		}
		return result;

	}

	/**
	 * 数据查询
	 * 
	 * @param sql
	 *            sql语句
	 * @param param
	 *            参数
	 * @return ResultSet 结果对象
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public ResultSet query(String sql, Object[] param)
			throws ClassNotFoundException, SQLException {

		ps = getConn().prepareStatement(sql);
		int i = 0;
		if (param != null) {
			for (Object p : param) {
				ps.setObject(++i, p);
			}
		}

		rs = ps.executeQuery();

		return rs;
	}

	/**
	 * 数据更新&&数据删除&&数据添加
	 * 
	 * @param sql
	 *            更新语句
	 * @param param
	 *            更新参数
	 * @return 更新条数
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int update(String sql, Object[] param)
			throws ClassNotFoundException, SQLException {
		ps = getConn().prepareStatement(sql);
		for (int i = 1; i <= param.length; i++) {
			getPs().setObject(i, param[i - 1]);
		}
		return getPs().executeUpdate();
	}

	/**
	 * 把 rs 变成 map对象
	 * 
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	private Map getMap(ResultSet rs) throws SQLException {

		ResultSetMetaData rsmd = rs.getMetaData();
		int columnCount = rsmd.getColumnCount();
		Map result = new HashMap();

		for (int i = 1; i <= columnCount; i++) {
			String columnName = rsmd.getColumnName(i);
			String value = rs.getString(i);
			result.put(columnName, value);
		}

		return result;
	}

	/**
	 * 关闭所有数据库连接
	 * 
	 * @throws SQLException
	 */
	public void CloseAll() throws SQLException {

		if (ps != null) {
			ps.close();
		}

		if (rs != null) {
			rs.close();
		}

		if (sm != null && !sm.isClosed()) {
			sm.close();
		}

		if (conn != null && !conn.isClosed()) {
			conn.close();
		}

	}

	/**
	 * 获取数据库连接
	 * 
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public Connection getConn() throws SQLException, ClassNotFoundException {
		if (conn == null || conn.isClosed()) {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@192.168.0.99:1521:orcl", "bbpro",
					"bbpro");
		}
		return conn;
	}

	/**
	 * 获取数据库流
	 * 
	 * @return 数据库 Statement 对象
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public Statement getSm() throws ClassNotFoundException, SQLException {
		if (sm == null) {
			sm = getConn().createStatement();
		}
		return sm;
	}

	/**
	 * 获取数据库 PreparedStatement 对象
	 * 
	 * @return
	 */
	public PreparedStatement getPs() {
		return ps;
	}

}