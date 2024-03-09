package test;

import java.awt.BorderLayout;       //5개의 영역이 들어가도록 배치및설정
import java.awt.FlowLayout;         //한방향으로 배치및설정
import java.awt.Font;               //글꼴
import java.awt.Graphics;        //그래픽이미지
import java.awt.event.ActionEvent;    //자기 자신을 디스패치 할 수 있는 이벤트를 위한 인터페이스
import java.awt.event.ActionListener; //액션 이벤트를 받기 위한 리스너 인터페이스
import java.sql.Connection;           //데이타베이스와의 접속
import java.sql.DriverManager;        //JDBC 드라이버를 관리하기 위함
import java.sql.PreparedStatement;    //프리컴파일 된 SQL 문을 나타내는 객체
import java.sql.ResultSet;            //데이타베이스의 결과 세트를 나타내는 데이터의 테이블
import java.sql.SQLException;         //데이타베이스 액세스 에러 또는 그 외의 에러에 관한 정보를 제공
import java.sql.Statement;            //SQL문을 실행해 결과값을 돌리기위한 객체

import javax.swing.ImageIcon;         //아이콘을 이미지를 불러와사용
import javax.swing.JButton;           //버튼 구현
import javax.swing.JFrame;            //프레임 구현
import javax.swing.JLabel;            //라벨 구현
import javax.swing.JOptionPane;       //다이얼로그박스 구현
import javax.swing.JPanel;            //패널 구현
import javax.swing.JScrollPane;       //스크롤바 구현
import javax.swing.JTable;            //테이블 구현
import javax.swing.JTextArea;         //텍스트 입력창 구현
import javax.swing.JTextField;        //텍스트 필드 구현

import test.openjtextarea.Listener;   //액션 리스너 생성

public class helptextarea extends JFrame {
	public String driver = "org.postgresql.Driver";
	public String url = "jdbc:postgresql://localhost:5432/postgres"; //DB주소
	public String user = "postgres";
	public String password = "1234";
	public Statement stmt = null;

	public PreparedStatement ps = null; // 명령
	public Connection con = null;       //커넥션 널값 선언

	public ResultSet rs = null;         //ResultSet 널값 선언
	String tmember;
	String naeyoung;
	public String lmember;
	public String write;
	public String dong;
	public String go;
	public String u_dong;
	public String plustext;
	// 프레임과 텍스트 에어리어의 변수 선언
	private JFrame f;
	public JTable table;
	public JTextField textfield;
	JTextArea ta1 = null;;
	public JButton fix;
	public JButton del;
	public JButton in;
	public int r;
	 JScrollPane scrollPane;
	Font f1 = new Font("돋음", Font.BOLD, 17);

	// 호출할 함수 작성

	ImageIcon icon;

	// 호출할 함수 작성
	public helptextarea() {
		//패널 이미지 삽입
		icon = new ImageIcon("images/노트수정본2.jpg");
       
		JPanel p1 = new JPanel() {
			public void paintComponent(Graphics g) {

				g.drawImage(icon.getImage(), 0, 0, null);

				setOpaque(false);
				super.paintComponent(g);
			}
		};

		ImageIcon wsave = new ImageIcon("images/저장하기.png");
		// 프레임 생성
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // dipose는 현재 프레임만 종료, exit는 활성화 되어 있는 전체 프레임 종료
		f = new JFrame("TextArea Test");
		// 윈도우 종료
		fix = new JButton("수정");
		del = new JButton("삭제");
		in = new JButton("저장");
		// 텍스트 에어리어 생성
		p1.setLayout(null);

		JScrollPane pane = new JScrollPane(ta1);

		// 스크롤바가 없는 텍스트 에어리어를 생성

		// 패널 생성 및 레이블과 텍스트에어리어의 부착

		// p1.setLayout(new BorderLayout());
		JLabel name1 = new JLabel("제목");

		name1.setFont(f1);
		name1.setBounds(20, 30, 50, 27);
		p1.add(name1);

		textfield = new JTextField();
		textfield.setBounds(90, 30, 280, 27);
		p1.add(textfield);
		

		JLabel areaname = new JLabel("글내용");
		areaname.setFont(f1);
		areaname.setBounds(20, 60, 60, 27);
		p1.add(areaname);

		ta1 = new JTextArea(250, 300);
		ta1.setBounds(90, 60, 280, 300);
		p1.add(ta1);

		fix = new JButton("수정");
		fix.setBounds(90, 390, 70, 50);
		// p1.add(fix);

		del = new JButton("삭제");
		del.setBounds(190, 390, 70, 50);
		// p1.add(del);

		in = new JButton(wsave);
		in.setBounds(90, 390, 280, 50);
		p1.add(in);

	
		in.addActionListener(new Listener());
		
		p1.setLayout(null);
		p1.setLayout(new BorderLayout());
		// 텍스트 에어리어에 입려괸 텍스트를 화면에 표시
		System.out.println(" ta1 Text : " + ta1.getText());
		setLayout(null);
		setLayout(new BorderLayout());
		scrollPane = new JScrollPane(p1);
		setContentPane(scrollPane);
		f.setResizable(false);

		f.add(p1);
		
		f.setSize(410, 500);
		f.setResizable(false);
		f.setVisible(true);
		f.setLocationRelativeTo(null);
	}

	void write() {
        //글쓰기창 설정
		tmember = "SELECT * FROM tb_member,users,helptable,TO_CHAR(now(), 'YYYY년MM월DD일,HH시MI분SS초') WHERE(dong = u_dong and hosu = u_hosu)";

		String join = ta1.getText();
		
		try {

			Class.forName(driver);
			// DriverManager로부터 커넥션을 얻어오는데 mysql서버 . id, pw 등을 언어온다.
			con = (Connection) DriverManager.getConnection(url, user, password);
			// 커넥션으로부터 실제로 sql쿼리 실행시키기 위한 statement 객체를 얻어온다.
			stmt = (Statement) con.createStatement();
			ResultSet rs = stmt.executeQuery(tmember);

			if (rs.next()) {

				boolean same = rs.getString("dong").equals(rs.getString("u_dong"));

				if (same) {

					System.out.println("글저장성공");

					String sql = "insert into help_write(h_dong,h_글내용,h_제목,h_조회수,h_글쓴시간,h_hosu) values(?,?,?,?,?,?)";

					try {
						String jaemok = textfield.getText();
						rs = stmt.executeQuery(tmember);

						if (rs.next()) {
							ps = con.prepareStatement(sql);

							ps.setString(1, rs.getString("u_dong"));
							ps.setString(2, join);
							ps.setString(3, jaemok);
							ps.setInt(4, 0);
							ps.setString(5, rs.getString("to_char"));
							ps.setString(6, rs.getString("u_hosu"));

							int r = ps.executeUpdate(); // 저장
						}
					} catch (SQLException e1) {
						
						e1.printStackTrace();
					}

				}

			}
		} catch (Exception b) {
			b.printStackTrace();
		}
	}

	class Listener implements ActionListener {

		@Override

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == in) {

				System.out.println("버튼누르기성공");
				write();
				setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // dipose는 현재 프레임만 종료, exit는 활성화 되어 있는 전체 프레임 종료
				 JOptionPane.showMessageDialog(null, "글이 저장되었습니다.");
				  f.dispose();
			}
		}
	}

	public static void main(String[] args) {
		helptextarea hta = new helptextarea();
		hta.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // dipose는 현재 프레임만 종료, exit는 활성화 되어 있는 전체 프레임 종료

	}

}
