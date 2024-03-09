package test;

import java.awt.EventQueue;      //메인클래스에 신뢰할 수 있는 어플리케이션 클래스로부터의 이벤트를 큐에 넣는다.
import java.awt.Graphics;        //그래픽 이미지 설정
import java.awt.LayoutManager;   //레이아웃 방법을 인식하고 있는 클래스를 위한 인터페이스를 정의
import java.awt.Rectangle;       //좌표공간의 영역 지정
import java.awt.event.ActionEvent;    //자기 자신을 디스패치 할 수 있는 이벤트를 위한 인터페이스
import java.awt.event.ActionListener; //액션 이벤트를 받기 위한 리스너 인터페이스
import java.awt.event.MouseEvent;     //컴퍼넌트내에서 마우스 액션이 발생한 것을 나타내는 이벤트
import java.awt.event.MouseListener;  //마우스 이벤트 (프레스, 릴리스, 클릭, 컴퍼넌트에의 출입)를 받기 위한 리스너 인터페이스
import java.sql.Connection;           //데이타베이스와의 접속
import java.sql.DriverManager;        //JDBC 드라이버를 관리하기 위함
import java.sql.PreparedStatement;    //프리컴파일 된 SQL 문을 나타내는 객체
import java.sql.ResultSet;            //데이타베이스의 결과 세트를 나타내는 데이터의 테이블

import javax.swing.ImageIcon;         //아이콘 이미지 구현
import javax.swing.JButton;           //버튼 구현
import javax.swing.JFrame;            //프레임 구현
import javax.swing.JPanel;            //패널 구현
import javax.swing.JScrollPane;       //스크롤바 구현
import javax.swing.JTable;            //테이블 구현
import javax.swing.table.DefaultTableModel;

public class admintable extends JPanel implements MouseListener {

// DB에서 스윙 화면으로 테이블 값 가져오기(select) , 저장하기(insert), 수
	public static final long serialVersionUID = 1L;
	public JButton jBtnAddRow = null; // 테이블 한줄 추가 버튼
	public JButton jBtnSaveRow = null; // 테이블 한줄 저장 버튼
	public JButton jBtnEditRow = null; // 테이블 한줄 저장 버튼
	public JButton jBtnDelRow = null; // 테이블 한줄 삭제 벅튼
	public JButton jBtnwrite = null; // 테이블 한줄 삭제 벅튼
	public JButton jBtnList = null; // 테이블 한줄 삭제 벅튼
	public JButton bts = null; // 테이블 한줄 삭제 벅튼
	public JTable table;
	public JScrollPane scrollPane; // 테이블 스크롤바 자관리자으로 생성
	public String driver = "org.postgresql.Driver";
	public String url = "jdbc:postgresql://localhost:5432/postgres"; //DB
	public String user = "postgres";
	public String password = "1234";
	public String colNames[] = { "관리자", "공지사항" };
	public DefaultTableModel model = new DefaultTableModel(colNames, 0); //모델 객채로 추가,삭제
	public Connection con = null;  //커넥션 생성
	public PreparedStatement pstmt = null;
	public ResultSet rs = null; // 리턴받아 사용할 객체 생성 ( select에서
	public LayoutManager win;   //배치관리자

	ImageIcon icon;
	
	public void paintComponent(Graphics g) {  //그래픽 이미지 불러오기

		icon = new ImageIcon("images/공지게시판 창2.png");
		g.drawImage(icon.getImage(), 0, 0, null);
		
		setOpaque(false);
		super.paintComponent(g);
	}
	
	public admintable() {

		setLayout(null); // 레이아웃 배치관리자 삭제
		table = new JTable(model); // 테이블에 모델객체 삽입
		table.addMouseListener(this); // 리스너 등록
		scrollPane = new JScrollPane(table); // 테이블에 스크롤
		scrollPane.setSize(500, 200);   //크기 지정
		scrollPane.setBounds(28, 100, 500, 200);
		add(scrollPane);
		initialize();
		select();

	}

	public void select() { // 테이블에 보이기 위해 검색
		String query = "select 관리자, 공지사항 from admintable";

		try {                    //예외처리문 - try,catch,finally
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			pstmt = con.prepareStatement(query); //객체에 지정된 sql명렁어 실행 
			rs = pstmt.executeQuery(); // 리턴받아와서 데이터를 사용할 객체
			while (rs.next()) { // 각각 값을 가져와서 테이블값들을
				model.addRow(new Object[] { rs.getString("관리자"), rs.getString("공지사항") });
			}
		} catch (Exception e) {//예외일시 수행
			System.out.println(e.getMessage());
		} finally {//반드시 수행
			try {
				rs.close();
				pstmt.close();
				con.close(); // 객체 생성한 반대 순으로 사용한 객체는
			} catch (Exception e2) {
			}
		}
	}

	public void initialize() {// 액션이벤트와 버튼 컴포넌트 설정
		
		//이미지 삽입
		ImageIcon adadd = new ImageIcon("images/관리자글쓰기1.png");
		ImageIcon adsave = new ImageIcon("images/관리자글저장1.png");
		ImageIcon addel = new ImageIcon("images/관리자글삭제1.png");
		ImageIcon adlist = new ImageIcon("images/회원관리1.png");
		
		try {

			String row2 = "select u_id, u_pwd from users where u_id = 'admin'";

			Class.forName(driver);
			// DriverManager로부터 커넥션을 얻어오는데 mysql서버 . id, pw 등을 언어온다.
			con = (Connection) DriverManager.getConnection(url, user, password);
			// 커넥션으로부터 실제로 sql쿼리 실행시키기 위한 statement 객체를 얻어온다.

			pstmt = con.prepareStatement(row2);  //객체에 지정된 sql명렁어 실행
			rs = pstmt.executeQuery();     // 리턴받아와서 데이터를 사용할 객체

			if (rs.next()) {             //결과가 1개인경우

				if (123456 == rs.getInt("u_pwd")) {

				
// 테이블 새로 한줄 추가하는 부분
						jBtnAddRow = new JButton(adadd);
						jBtnAddRow.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								System.out.println(e.getActionCommand()); // 선택된구문
								DefaultTableModel model2 = (DefaultTableModel) table.getModel();
								model2.addRow(new String[] { "경비실", "" }); 
							}
						});
						jBtnAddRow.setBounds(54, 350, 130, 35);
						
						add(jBtnAddRow);
// 테이블 새로 저장하는 부분
						jBtnSaveRow = new JButton(adsave);
						jBtnSaveRow.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								System.out.println(e.getActionCommand()); // 선택된구문
								DefaultTableModel model2 = (DefaultTableModel) table.getModel();
								int row = table.getSelectedRow();

								String query = "insert into admintable(관리자, 공지사항)" + "values (?,?)";
								try {
									Class.forName(driver); // 드라이버 로딩
									con = DriverManager.getConnection(url, user, password);
									pstmt = con.prepareStatement(query);
// 물음표가 4개 이므로 4개 각각 입력해줘야한다.
									pstmt.setString(1, (String) model2.getValueAt(row, 0));
									pstmt.setString(2, (String) model2.getValueAt(row, 1));

									int cnt = pstmt.executeUpdate();

								} catch (Exception eeee) {
									System.out.println(eeee.getMessage());
								} finally {
									try {
										pstmt.close();
										con.close();
									} catch (Exception e2) {
									}
								}
								model2.setRowCount(0); // 전체 테이블 화면을 지워
								select(); // 저장 후 다시 전체 값들을 받아옴.
							}
						});
						jBtnSaveRow.setBounds(192, 350, 130, 35);
						
						add(jBtnSaveRow);

						// 선택된 테이블 한줄 삭제하는 부분
						jBtnDelRow = new JButton(addel);
						jBtnDelRow.addActionListener(new ActionListener() {
							public void actionPerformed(java.awt.event.ActionEvent e) {
								System.out.println(e.getActionCommand()); // 선택된
								DefaultTableModel model2 = (DefaultTableModel) table.getModel();
								int row = table.getSelectedRow();
								if (row < 0)
									return; // 선택이 안된 상태면 -1리턴
								String query = "delete from admintable where 관리자= ?";
								try {
									Class.forName(driver); // 드라이버 로딩
									con = DriverManager.getConnection(url, user, password);
									pstmt = con.prepareStatement(query);
									// 물음표가 1개 이므로 4개 각각 입력해줘야한다.
									pstmt.setString(1, (String) model2.getValueAt(row, 0));

									int cnt = pstmt.executeUpdate();
								
								} catch (Exception eeee) {
									System.out.println(eeee.getMessage());
								} finally {
									try {
										pstmt.close();
										con.close();
									} catch (Exception e2) {
									}
								}
								model2.removeRow(row); // 테이블 상의 한줄 삭제
							}
						});
						jBtnDelRow.setBounds(new Rectangle(330, 350, 130, 35));
						
						add(jBtnDelRow);
						//회원리스트
						jBtnList = new JButton(adlist);
						jBtnList.addActionListener(new ActionListener() {
							public void actionPerformed(java.awt.event.ActionEvent e) {
								System.out.println(e.getActionCommand()); // 선택된
								DefaultTableModel model2 = (DefaultTableModel) table.getModel();
								new Member_List();
							}
						});
						jBtnList.setBounds(new Rectangle(330, 450, 130, 35));
						add(jBtnList);

					
					
					
					// 저장
				}

			}
       //닫기
		} catch (Exception eeee4) {
			System.out.println(eeee4.getMessage());
		} finally {
			try {
				rs.close();
				pstmt.close();
				con.close();
			} catch (Exception e22) {
			}
		}
	}
    //게시판 설정
	public static void createAndShowUI() {
		admintable panel = new admintable();
		JFrame win = new JFrame("공지게시판");
		win.add(panel);
		win.setSize(565, 740);
		win.setVisible(true);
		win.setResizable(false);
		win.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // dipose는 현재 프레임만 종료, exit는 활성화 되어 있는 전체 프레임 종료

	}
    //실행
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new admintable().createAndShowUI();
			}
		});
	}
    //마우스 설정
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
