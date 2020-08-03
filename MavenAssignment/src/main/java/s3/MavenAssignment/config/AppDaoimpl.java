package s3.MavenAssignment.config;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;



public class AppDaoimpl implements AppDao {

	private DataSource dataSource;
	User user = new User();

	public AppDaoimpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public int listUsers(String email,String password) {
		System.out.println("sql");
		String SQL = "select * from users where Email_ID=? and Password=?";
//		String SQL = "Select * from users";
		List<User> listUsers = new ArrayList<User>();
		Connection conn = null;
		int flag=0;
		try{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(SQL);
			ps.setString(1,email);
			ps.setString(2,password);
			ResultSet rs =  ps.executeQuery();
			System.out.println("rs"+rs);
			boolean check=rs.next();
			String temp3 =rs.getString("Email_ID");
			String temp4=rs.getString("Password");
			System.out.println("email"+temp3+"pass"+temp4);
//			 while(rs.next()){
//				 System.out.println("test");
//     			String temp1=rs.getString("Password");
//     			String temp2 =rs.getString("Email_ID");
//     			System.out.println("email"+temp2+"pass"+temp1);
//		        }
			if(temp3!=null && temp4!=null)
			{
				flag=1;
			}
	     rs.close();
	     ps.close();
	     return flag;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return flag;
		}finally{
			if (conn != null) {
				try {
				conn.close();
				} catch (SQLException e) {e.printStackTrace();}
			}
			
		}
			
	
	}

}



























