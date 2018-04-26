import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main extends DBConnection {
	static Connection con;
	static List<HashMap<String, Object>> list;
	static String sql = null;
	public static void main(String[] args) throws Exception {
		System.out.println("연결 시작");
		con =openDB();
		System.out.println("연결 중");
		// select 데이터 가져오기
/*		String sql = "select * from user";
		list = select(con,sql);
		for(int i=0;i<list.size();i++) {
			System.out.println(list.get(i));
		}*/

		//insert 데이터 입력
/*		sql="insert into user (id,pw) values (?,?);";
		List datalist = new ArrayList();
		datalist.add("id");
		datalist.add("pw");
		int result = edit(con, sql,datalist);    
		System.out.println(result);
		*/
		
		//update 데이터 수정
/*		sql = "update `user` set `pw` = ? where `id` = ?";    // ?는 ''으로 감싸면 실행이 안된다. 
		List datalist = new ArrayList();
		datalist.add("수정");
		datalist.add("추가");
		int result = edit(con,sql,datalist);
		System.out.println(result);*/
		
		//데이터 삭제 - 실제회사에서 삭제는 업데이트로 함~~
		sql = "delete from user where id = ?";    // 
		List datalist = new ArrayList();
		datalist.add("id");
		int result = edit(con,sql,datalist);
		System.out.println(result);
		con.close();
		System.out.println("연결 종료");
	}

}
