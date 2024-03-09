package test;

import java.awt.Button; //라벨 버튼 생성
import java.awt.Dialog; //프레임 입력
import java.awt.Frame;  //프레임 생성
import java.awt.Label;  //라벨 생성
import java.awt.event.ActionEvent;    //자기 자신을 디스패치 할 수 있는 이벤트를 위한 인터페이스
import java.awt.event.ActionListener; //액션 이벤트를 받기 위한 리스너 인터페이스
import java.sql.Connection;           //데이타베이스와의 접속
import java.sql.DriverManager;        //JDBC 드라이버를 관리하기 위함
import java.sql.PreparedStatement;    //프리컴파일 된 SQL 문을 나타내는 객체
import java.sql.ResultSet;            //데이타베이스의 결과 세트를 나타내는 데이터의 테이블
import java.sql.Statement;            //작성된 결과를 돌려주기 위해서 사용되는 객체

import javax.xml.crypto.Data;

public class excute extends Frame implements ActionListener {
	public class Data {
		// 데이터 삽입 수정 할때 필요한 클래스 이다.
		// 데이터 임시 저장을 담당한다.
		String id, pwd, name, dong, hosu;
	}

// jdbc 설정
	public String url = "jdbc:postgresql://localhost:5432/postgres"; //
	public String strUser = "postgres"; // 계정 id
	public String strPassword = "1234"; // 계정 패스워드
	public String strMySQLDriver = "org.postgresql.Driver";
	
	Connection con;
	PreparedStatement ps = null; // 명령
	Statement stmt;
	
	ResultSet rs;
	String sql;

//
	String id;
	String pass;
	String name;
	String dong;
	String hosu;
// 로그인과 맴버 클래스를 가져온다.

	Login log = new Login(); // true
	LoginSub logSub = new LoginSub(); // false
	Data d = new Data();
	String login = "select id, pw, name from tb_member";

// 다이얼로그 , 버튼, 라벨,
	Button ok;
	Label msg;
	Dialog info;

	excute() {

		try {
// 로그인 버튼이 클릭 되었을시에 jdbc드라이버를 등록한다.
			Class.forName(strMySQLDriver);
// DriverManager로부터 커넥션을 얻어오는데 mysql서버 . id, pw 등을 언어온다.
			con = (Connection) DriverManager.getConnection(url, strUser, strPassword);
// 커넥션으로부터 실제로 sql쿼리 실행시키기 위한 statement 객체를 얻어온다.
			stmt = (Statement) con.createStatement();

		} catch (Exception b) {
			System.out.println("db연결실패");
		}

// 회원가입 버튼
		log.button3.addActionListener(this);
// 로그인 버튼
		log.button1.addActionListener(this);
// LoginSub의 수정버튼
		logSub.button1.addActionListener(this);
// LoginSub의 탈퇴버튼
		logSub.button2.addActionListener(this);
// LoginSub의 로그아웃버튼
		logSub.button3.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
// TODO Auto-generated method stub
// 해당하는 버튼값 가져오기
		Object obj = e.getSource();
		if (obj.equals(log.button1)) {
// 로그인창의 로그인 버튼
			loginCheck();
// 해당 접속자 환영 메시지
			logSub.label1.setText(name + "님 로그인 하였습니다.");
// title 변경
			logSub.setTitle(name + "님 접속창");
// 로그인창은 잠시 안보이도록 한다.
			log.setVisible(false);      
//SQL 
		}else if((obj.equals(logSub.button1))){
			new Tabplus();
		}
		else if((obj.equals(log.button3))){
			new MemberProc();
		}
		else if (obj.equals(logSub.button2)) {
			
			String query = "select *from users";

			try {
				Class.forName(strMySQLDriver);
				con = DriverManager.getConnection(url, strUser, strPassword);
				ps = con.prepareStatement(query);

				rs = ps.executeQuery(); // 리턴받아와서 데이터를 사용할 객체
				while (rs.next()) { // 각각 값을 가져와서 테이블값들을
				
					String id = rs.getString("u_id");
					 MemberProc mem = new MemberProc(id,this); //아이디를 인자로 수정창 생성
					 
				}
			} catch (Exception ee) {
				System.out.println(ee.getMessage());
			} finally {
				try {
					rs.close();
					ps.close();
					con.close(); // 객체 생성한 반대 순으로 사용한 객체는
				} catch (Exception e2) {
				}
			}
			
			
		} else if (obj.equals(logSub.button3)) {
// logSub 화면을 감춘다.
			logSub.setVisible(false);
// 로그인창을 띄운다.
			log.setVisible(true);
			selectDelete();
// 클리어
			clearText();
// 로그인창의 텍스트를 초기화

		}

	}

	private void clearText() {

		log.tex1.setText("");
		log.tex2.setText("");

		logSub.tex1.setText("");
	}
// 삭제
	void selectDelete() {
// TODO Auto-generated method stub

		String name = logSub.tex1.getText();
		String sql = "DELETE FROM users";
		System.out.println(sql);
		try {

			int rss = stmt.executeUpdate(sql);

			System.out.println(rss + "삭제");

// 커넥션으로부터 실제로 sql쿼리 실행시키기 위한 statement 객체를 얻어온다.

			logSub.tex1.setText("삭제 완료 ^^");

		} catch (Exception e) {
			logSub.tex1.setText("삭제중 예외 발생 T.T?");
			System.out.println("삭제중 예외 발생 : " + e);
		}
	}// deleteDB

// 로그인 체 크

	void loginCheck() {
// 로그인창의 텍스트필드에 쓴값을 가져오기
		id = log.tex1.getText().trim();
		pass = log.tex2.getText().trim();

// SELECT 쿼리를 작성한다. 해당하는 아이디값의 패스워드를 검색한다.
		String query = "SELECT pwd,name,dong,hosu FROM tb_member where id='" + id + "'";

		System.out.println(query);
		try {
// executeQuery() 메서드로 SELECT문의 실행시키고 결과로 ResultSet 객체를 받는다.
			ResultSet rs = stmt.executeQuery(query);

// 레코드가 있는지 검사
			if (rs.next()) {
//
				name = rs.getString("name");
				dong = rs.getString("dong");
				hosu = rs.getString("hosu");

// 텍스트필드에 쓴값과 데이터베이스에 있는 패스워드 값을 비교한다.
				if (pass.equals(rs.getString("pwd"))) {

					String sql = "insert into users(" + "u_id,u_pwd,u_dong,u_hosu)" + "values(?,?,?,?)";

					ps = con.prepareStatement(sql);
					ps.setString(1, id);
					ps.setString(2, pass);
					ps.setString(3, dong);
					ps.setString(4, hosu);
					int r = ps.executeUpdate(); // 저장

					System.out.println("맞았어");
// 맞으면 로그인서브를 띄워준다.
					logSub.setVisible(true);

				}

			}

		} catch (Exception b) {
			b.printStackTrace();
		}
	}

// 실행
	public static void main(String[] args) {
		new excute();

	}
}
