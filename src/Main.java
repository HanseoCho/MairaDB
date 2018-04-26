import java.sql.Connection;
import java.util.HashMap;
import java.util.List;

public class Main extends DBConnection {
	static Connection con;
	static List<HashMap<String, Object>> list;
	public static void main(String[] args) throws Exception {
		System.out.println("연결 시작");
		con =openDB();
		System.out.println("연결 중");
		String sql = "select * from user";
		list = select(con,sql);
		for(int i=0;i<list.size();i++) {
			System.out.println(list.get(i).get("id"));
		}
		
		con.close();
		System.out.println("연결 종료");
	}

}
