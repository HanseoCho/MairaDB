import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class test extends DBConnection {
	static Connection Con;
	static List<HashMap<String, Object>> list;
	static String sql = null;
	public static void main(String[] args) throws Exception {
		Con = openDB();
		// 1) 값을 입력한다.
		input();
		// 2) 입력된 값을 확인한다.
		select();
		// 3) 입력된 값을 수정한다.
		update();
		// 4) 수정된 값을 확인한다.
		select();
		// 5) 수정한 값을 삭제한다.
		delete();
		// 6) 삭제한 값을 확인한다.
		select();

	}
	
	public static void select() throws Exception {
		System.out.println("확인");
		sql = "select * from user";
		list = select(Con,sql);
		for(int i=0;i<list.size();i++) {
			System.out.println(list.get(i));
		}
	}
	public static void input()throws Exception {
		System.out.println("삽입");
		sql="insert into user (id,pw) values (?,?);";
		List datalist = new ArrayList();
		datalist.add("id");
		datalist.add("pw");
		int result = edit(Con, sql,datalist);    
		System.out.println(result);
	}
	public static void update()throws Exception {
		System.out.println("수정");
		sql = "update `user` set `pw` = ? where `id` = ?";    // ?는 ''으로 감싸면 실행이 안된다. 
		List datalist = new ArrayList();
		datalist.add("수정");
		datalist.add("id");
		int result = edit(Con,sql,datalist);
		System.out.println(result);
	}
	public static void delete()throws Exception {
		System.out.println("삭제");
		sql = "delete from user where id = ?";    
		List datalist = new ArrayList();
		datalist.add("id");
		int result = edit(Con,sql,datalist);
		System.out.println(result);
	}
	

}
