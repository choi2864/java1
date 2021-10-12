package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class UserDao {

	//  회원조회(개인)  getUser("admin")
	public UserVo getUser(String  userid) {
		UserVo   user = null;
		
		Connection         conn   = null;
		PreparedStatement  pstmt  = null;
		ResultSet          rs     = null;
		
		String  sql  = "SELECT  USERID, PASSWD, USERNAME, JOB, GENDER, INTRO, ";
		sql         += " TO_CHAR(INDATE, 'YYYY-MM-DD HH24:MI:SS DAY') INDATE  ";
		sql         += " fROM   TUSER  ";
		sql         += " WHERE  USERID = ?";
		
		try {
			conn      =  DBConn.getInstance();
			pstmt     =  conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			
			rs  =  pstmt.executeQuery();
			if( rs.next() ) {
				String  uid       =   rs.getString("userid"); 
				String  passwd    =   rs.getString("passwd"); 
				String  username  =   rs.getString("username"); 
				String  job       =   rs.getString("job"); 
				String  gender    =   rs.getString("gender"); 
				String  intro     =   rs.getString("intro"); 
				String  indate    =   rs.getString("indate"); 
				user =  new UserVo(uid, passwd, username, job, gender, intro, indate);			
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if( rs    != null )  rs.close();
				if( pstmt != null )  pstmt.close();
			} catch (SQLException e) {
			}
		} 
		return   user;
	}
	
	//  회원조회(목록)
	
	//  회원 추가
	public  void   insertUser( String userid, String passwd,
			String username, String job, String gender, String intro) {
		Connection         conn   =  null;
		PreparedStatement  pstmt  =  null;
		String             sql    =  "";
		
		conn   =  DBConn.getInstance();
		sql    =  "INSERT  INTO    TUSER ( USERID, PASSWD, USERNAME, JOB, GENDER, INTRO )";
		sql   +=  " VALUES (  ?, ?, ?, ?, ?, ? )";
		try {
			pstmt  =  conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			pstmt.setString(2, passwd );
			pstmt.setString(3, username );
			pstmt.setString(4, job );
			pstmt.setString(5, gender );
			pstmt.setString(6, intro );
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn  != null) conn.close();
			} catch (SQLException e) {
			}
		}
	}
	
	public  String   insertUser( UserVo user ) {
		String  result = "";
		
		Connection         conn   =  null;
		PreparedStatement  pstmt  =  null;
		String             sql    =  "";
		
		conn   =  DBConn.getInstance();
		sql    =  "INSERT  INTO    TUSER ( USERID, PASSWD, USERNAME, JOB, GENDER, INTRO )";
		sql   +=  " VALUES (  ?, ?, ?, ?, ?, ? )";
		try {
			pstmt  =  conn.prepareStatement(sql);
			pstmt.setString(1, user.getUserid() );
			pstmt.setString(2, user.getPasswd() );
			pstmt.setString(3, user.getUsername() );
			pstmt.setString(4, user.getJob() );
			pstmt.setString(5, user.getGender() );
			pstmt.setString(6, user.getIntro() );
			
			pstmt.executeUpdate();
			
			result  = "추가되었습니다";
						
		} catch (SQLException e) {
			switch(e.getErrorCode()) {
			case 1:
				result = "중복된 자료입니다";
				break;
			default:
				result  = e.getMessage();
			}
		} finally {
			try {
				if(pstmt != null) pstmt.close();
			} catch (SQLException e) {
			}
		}
		return result;
		
	}

	//  회원 수정
	public String updateUser(UserVo user) {
		String  result = "";
		
		Connection         conn   =  null;
		PreparedStatement  pstmt  =  null;
		String             sql    =  "";
		
		conn   =  DBConn.getInstance();
		sql    =  "UPDATE   TUSER ";
		sql   +=  " SET     PASSWD  = ?,";
		sql   +=  "         JOB     = ?,";
		sql   +=  "         INTRO   = ? ";
		sql   +=  " WHERE   USERID  = ? "; 
	//	sql   +=  "  AND    PASSWD  = ? ";
		try {
			pstmt  =  conn.prepareStatement(sql);
			pstmt.setString(1, user.getPasswd() );
			pstmt.setString(2, user.getJob() );
			pstmt.setString(3, user.getIntro() );
			pstmt.setString(4, user.getUserid() );
			// pstmt.setString(5, user.getPasswd() );
					
			pstmt.executeUpdate();
			
			result  = "수정 되었습니다";
						
		} catch (SQLException e) {
			switch(e.getErrorCode()) {
			default:
				result  = e.getMessage();
			}
		} finally {
			try {
				if(pstmt != null) pstmt.close();
			} catch (SQLException e) {
			}
		}
		return result;
		
	}
	//  회원 삭제

	public String deleteUser(UserVo user) {
		String  result = "";
		
		Connection         conn   =  null;
		PreparedStatement  pstmt  =  null;
		String             sql    =  "";
		
		conn   =  DBConn.getInstance();
		sql    =  "DELETE   FROM  TUSER ";
		sql   +=  " WHERE   USERID  = ? "; 
	//	sql   +=  "  AND    PASSWD  = ? ";
		try {
			pstmt  =  conn.prepareStatement(sql);
			pstmt.setString(1, user.getUserid() );
		//	pstmt.setString(2, user.getPasswd() );

			pstmt.executeUpdate();
			
			result  = "삭제 되었습니다";
						
		} catch (SQLException e) {
			switch(e.getErrorCode()) {		
			default:
				result  = e.getMessage();
			}
		} finally {
			try {
				if(pstmt != null) pstmt.close();
			} catch (SQLException e) {
			}
		}
		return result;
		
	}

	// ArrayList< UserVo >  : ArrayList< ArrayList<String> >
	//  Vector< Vector< String > >    : 구현 힘듬 -. DefaultTabelModel 에 저장 XX
	
	//  Vector v = new Vector();     // oracle 한줄 : record
	//  Vector outV = new Vecor();   // 배열
	//  outV.add(v);
	public Vector getUserList() {
		Vector  outV   = new Vector();
		
		Connection         conn   = null;
		PreparedStatement  pstmt  = null;
		ResultSet          rs     = null;
		
		String  sql  = "SELECT  USERID,  USERNAME, JOB, GENDER,  ";
		sql         += " TO_CHAR(INDATE, 'YYYY-MM-DD') INDATE  ";
		sql         += " FROM   TUSER  ";
		
		try {
			conn      =  DBConn.getInstance();
			pstmt     =  conn.prepareStatement(sql);
			
			rs  =  pstmt.executeQuery();
			while( rs.next() ) {
				Vector    v  =   new  Vector(); 
				v.add( rs.getString("userid") );
				v.add( rs.getString("username") ); 
				v.add( rs.getString("gender") ); 
				v.add( rs.getString("job") ); 
				v.add( rs.getString("indate") ); 
				outV.add( v );							
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if( rs    != null )  rs.close();
				if( pstmt != null )  pstmt.close();
			} catch (SQLException e) {
			}
		} 
		return   outV;
	}
	
}









