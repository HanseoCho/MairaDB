import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DBConnection {
	private static String url = "jdbc:mysql://192.168.1.233:3306/test";
	private	static String user = "root";
	private static String pwd = "1234";
	private static List<HashMap<String, Object>> list = null;
	
	public static Connection openDB() throws Exception {
		Class.forName("org.mariadb.jdbc.Driver");
		return DriverManager.getConnection(url, user, pwd);
	}
	
	public static List<HashMap<String, Object>> select(Connection con, String sql) throws Exception {
		list = new ArrayList<HashMap<String, Object>>();
		
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		ResultSetMetaData rm = rs.getMetaData();       // 메타데이터로 컬럼이 몇개인지 받고
		
		while(rs.next()) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			for(int i=1;i<=rm.getColumnCount();i++) {
				String column = rm.getColumnName(i);
				System.out.println(rm.getColumnClassName(i));  //classname은 결국 컬럼의 타입이다. String값을 리턴시킴
				Object value = null;
				if("java.lang.String".equals(rm.getColumnClassName(i))){
					value = rs.getString(column);
				}else if ("java.lang.Integer".equals(rm.getColumnClassName(i))){
					value = rs.getInt(column);
				}
				map.put(column, value);
			}
			list.add(map);
		}
		rs.close(); 							//작업이 끝나면 자원의 낭비를 줄이기위해 역순으로 close 해준다. 
		ps.close();
				
		
		return list;		
	}
	//원래 삽입 삭제 수정 을 각각 만들었으나 구현하는게 같아서 하나의 메소드로 통합시킴
	public static int edit(Connection con,String sql, List dataList) throws Exception {
		PreparedStatement ps = con.prepareStatement(sql);
		for(int i=1;i<=dataList.size();i++) { 			// DB에서 가져오는건 1부터 가져온다 0은 헤더
			ps.setString(i, dataList.get(i-1).toString());
		}

		int result = ps.executeUpdate();
		ps.close();
		return result;
	}
}
