package test;

import java.awt.Component;       //화면에 표시할 수 있어 사용자 라고 대화할 수 있는 그래피컬한 표현을 가지는 객체
import java.awt.Dimension; //컴퍼넌트의 폭과 높이를 정수 정밀도로 수정
import java.awt.EventQueue;      //메인클래스에 신뢰할 수 있는 어플리케이션 클래스로부터의 이벤트를 큐에 넣는다.
import java.awt.Font;            //글꼴
import java.awt.Graphics;        //그래픽이미지
import java.awt.LayoutManager;   //레이아웃 방법을 인식하고 있는 클래스를 위한 인터페이스를 정의
import java.awt.Rectangle;       //좌표 영역 지정
import java.awt.Toolkit;         //윈도우 툴킷으로 모든 구현 클래스
import java.awt.event.ActionEvent;    //자기 자신을 디스패치 할 수 있는 이벤트를 위한 인터페이스
import java.awt.event.ActionListener; //액션 이벤트를 받기 위한 리스너 인터페이스
import java.awt.event.MouseEvent;     //컴퍼넌트내에서 마우스 액션이 발생한 것을 나타내는 이벤트
import java.awt.event.MouseListener;  //마우스 이벤트 (프레스, 릴리스, 클릭, 컴퍼넌트에의 출입)를 받기 위한 리스너 인터페이스
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
import javax.swing.JTextField;        //텍스트 필드 구현
import javax.swing.SwingConstants;    //배치 및 방향 지정을 위한 객체
import javax.swing.table.DefaultTableCellRenderer; //테이블 각 셀의 렌더링 표시
import javax.swing.table.DefaultTableModel;        //각 셀에 객체를 포함하는것 구현
import javax.swing.table.TableColumnModel;         //테이블 컬럼 모델 구현

public class freetable extends JPanel implements MouseListener {

// DB에서 스윙 화면으로 테이블 값 가져오기(select) , 저장하기(insert)
	public static final long serialVersionUID = 1L;
	public JButton jBtnAddRow = null; // 테이블 한줄 추가 버튼
	public JButton jBtnSaveRow = null; // 테이블 한줄 저장 버튼
	public JButton jBtnEditRow = null; // 테이블 한줄 저장 버튼
	public JButton jBtnDelRow = null; // 테이블 한줄 삭제 벅튼
	public JButton jBtnwrite = null; // 테이블 한줄 삭제 벅튼
	public JButton bts = null; // 테이블 한줄 삭제 벅튼
	public JTable table;
	public JScrollPane scrollPane; // 테이블 스크롤바 자동으로 생성
	public String driver = "org.postgresql.Driver";
	public String url = "jdbc:postgresql://localhost:5432/postgres"; 
	public String user = "postgres";
	public String password = "1234";
	public String colNames[] = { "동", "호수", "제목" };
	public static int trow;  //스태틱함수 선언
	public static int josu;
	public DefaultTableModel model = new DefaultTableModel(colNames, 0); //DefaultTableModel선언
	public Connection con = null;  //커넥션 널값 선언
	public PreparedStatement pstmt = null; //PreparedStatement 널값 선언
	public Statement stmt = null;  //Statement 널값 선언
	public ResultSet rs = null; // 리턴받아 사용할 객체 생성 ( select에서
	public LayoutManager win;
	String login = "select id,pwd,name,tel,dong, hosu from tb_member"; 
	String login2 = "select u_id,u_pwd,u_name from users";

	Font f = new Font("돋음", Font.BOLD, 25);
	Font f1 = new Font("돋음", Font.BOLD, 17);
	Font f2 = new Font("돋음", Font.PLAIN, 13);
	ImageIcon icon;
	
	public void paintComponent(Graphics g) {
    //이미지 삽입 
		icon = new ImageIcon("images/자유게시판 창2.png");
		g.drawImage(icon.getImage(), 0, 0, null);

		setOpaque(false);
		super.paintComponent(g);
	}
	public freetable() {	
		setLayout(null); // 레이아웃 배치관리자 삭제
		table = new JTable(model); // 테이블에 모델객체 삽입
		table.addMouseListener(this); // 리스너 등록
		scrollPane = new JScrollPane(table); // 테이블에 스크롤

		scrollPane.setSize(522, 200);
		scrollPane.setBounds(28, 100, 500, 200);
		add(scrollPane);

		TableColumnModel ta = table.getColumnModel(); // 정렬할 테이블의 컬럼모델을 가져옴
		
		// 테이블 컬럼의 이동을 방지한다. 이거 안쓰면 마우스로 드로그 앤 드롭으로 엉망진창이 될수 있다.
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);

		ta.getColumn(0).setCellRenderer(dtcr);
		ta.getColumn(1).setCellRenderer(dtcr);
		ta.getColumn(0).setPreferredWidth(20);
		ta.getColumn(0).setResizable(false);
		ta.getColumn(1).setPreferredWidth(20);
		ta.getColumn(2).setPreferredWidth(162);

		// 정렬할 테이블의 컬럼모델을 가져옴
		initialize();
		select();

	}

	public void select() { // 테이블에 보이기 위해 검색
		String query = "select 동, 호수, 제목 from freetable";

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			pstmt = con.prepareStatement(query);

			rs = pstmt.executeQuery(); // 리턴받아와서 데이터를 사용할 객체
			while (rs.next()) { // 각각 값을 가져와서 테이블값들을
				model.addRow(new Object[] { rs.getString("동"), rs.getString("호수"), rs.getString("제목") });
				
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				rs.close();
				pstmt.close();
				con.close(); // 객체 생성한 반대 순으로 사용한 객체는
			} catch (Exception e2) {
			}
		}
	}

	public void initialize() {
		String login2 = "select *from users";// 액션이벤트와 버튼 컴포넌트 설정
// 테이블 새로 한줄 추가하는 부분

		ImageIcon AddRow = new ImageIcon("images/글추가.png");
		ImageIcon AddWi = new ImageIcon("images/글쓰기.png");
		ImageIcon Showwi = new ImageIcon("images/글보기.png");
		ImageIcon edit1 = new ImageIcon("images/글수정12.png");
		ImageIcon delete = new ImageIcon("images/글삭제.png");

		jBtnAddRow = new JButton(AddRow);
		jBtnAddRow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {

					Class.forName(driver);
					con = DriverManager.getConnection(url, user, password);
					pstmt = con.prepareStatement(login2);
					rs = pstmt.executeQuery(); // 리턴받아와서 데이터를 사용할 객체

					System.out.println(e.getActionCommand()); // 선택된
					DefaultTableModel model2 = (DefaultTableModel) table.getModel();
					while (rs.next()) {
						model2.addRow(new Object[] { rs.getString("u_dong"), rs.getString("u_hosu"),
								rs.getString("u_name") });
					}
				} catch (Exception e1) {
					System.out.println(e1.getMessage());
				} finally {
					try {
						rs.close();
						pstmt.close();
						con.close(); // 객체 생성한 반대 순으로 사용한 객체는
					} catch (Exception e2) {
					}
				}

			}
		});

		jBtnAddRow.setBounds(54, 350, 130, 35);
		// jBtnAddRow.setText("글추가");
		add(jBtnAddRow);

		JLabel ex1 = new JLabel("글추가를 한뒤 엔터키를 누른후 글쓰기를 해주세요.");
		ex1.setBounds(34, 302, 400, 45);
		ex1.setFont(f1);
		add(ex1);

// 테이블 새로 저장하는 부분
		jBtnSaveRow = new JButton(AddWi);
		jBtnSaveRow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(e.getActionCommand()); // 선택된
				DefaultTableModel model2 = (DefaultTableModel) table.getModel();
				int row = table.getSelectedRow();

				String query = "insert into freetable(동, 호수, 제목) values (?,?,?)";
				try {
					Class.forName(driver); // 드라이버 로딩
					con = DriverManager.getConnection(url, user, password);
					pstmt = con.prepareStatement(query);
//물음표가 4개 이므로 4개 각각 입력해줘야한다.
					pstmt.setString(1, (String) model2.getValueAt(row, 0));
					pstmt.setString(2, (String) model2.getValueAt(row, 1));
					pstmt.setString(3, (String) model2.getValueAt(row, 2));
					int cnt = pstmt.executeUpdate();
//pstmt.executeUpdate(); create insert update delete
//pstmt.executeQuery(); select
				} catch (Exception eeee) {
					System.out.println(eeee.getMessage());
				} finally {
					try {
						pstmt.close();
						con.close();
					} catch (Exception e2) {
					}
				}
				JTextField txtfield = new openjtextarea().textfield;
				txtfield.setText((String) model2.getValueAt(row, 2));
				model2.setRowCount(0); // 전체 테이블 화면을 지워
				select(); // 저장 후 다시 전체 값들을 받아옴.
			}
		});
		jBtnSaveRow.setBounds(192, 350, 130, 35);
		// jBtnSaveRow.setText("글쓰기");
		add(jBtnSaveRow);
		// 선택된 테이블 한줄 수정하는 부분
		jBtnEditRow = new JButton(edit1);
		jBtnEditRow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(e.getActionCommand()); // 선택된 버튼의
				DefaultTableModel model2 = (DefaultTableModel) table.getModel();
				int row = table.getSelectedRow();
				if (row < 0)
					return; // 선택이 안된 상태면 -1리턴
				String query = "UPDATE freetable SET 호수=?, 제목=?" + "where 동=?";
				try {
					Class.forName(driver); // 드라이버 로딩
					con = DriverManager.getConnection(url, user, password);
					pstmt = con.prepareStatement(query);
					// 물음표가 4개 이므로 4개 각각 입력해줘야한다.
					pstmt.setString(1, (String) model2.getValueAt(row, 1));
					pstmt.setString(2, (String) model2.getValueAt(row, 2));
					pstmt.setString(3, (String) model2.getValueAt(row, 0));

					int cnt = pstmt.executeUpdate();
					// pstmt.executeUpdate(); create insert update delete
					// pstmt.executeQuery(); select
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

		jBtnEditRow.setBounds(192, 450, 130, 35);
		// jBtnEditRow.setText("수정");
		add(jBtnEditRow);

		// 선택된 테이블 한줄 삭제하는 부분
		jBtnDelRow = new JButton(delete);
		jBtnDelRow.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				System.out.println(e.getActionCommand()); // 선택된
				DefaultTableModel model2 = (DefaultTableModel) table.getModel();
				// 선택된
				int row3 = table.getSelectedRow() + 1;
				try {
					String row2 = "select u_dong, 동,rownum from (SELECT *,row_number() over()AS rownum from users,freetable)x WHERE rownum = ? AND 동 = u_dong AND 호수 = u_hosu";

					Class.forName(driver);
					// DriverManager로부터 커넥션을 얻어오는데 mysql서버 . id, pw 등을 언어온다.
					con = (Connection) DriverManager.getConnection(url, user, password);
					// 커넥션으로부터 실제로 sql쿼리 실행시키기 위한 statement 객체를 얻어온다.

					pstmt = con.prepareStatement(row2);

					pstmt.setInt(1, row3);
					rs = pstmt.executeQuery();

					if (rs.next()) {
						System.out.println(rs.getInt("rownum"));
						if (row3 == rs.getInt("rownum")) {
							JOptionPane.showMessageDialog(null, "삭제완료!");

							String query = "DELETE FROM freetable WHERE 제목= ?";
							int row = table.getSelectedRow();
							try {

								pstmt = con.prepareStatement(query);
								// 물음표가 1개 이므로 4개 각각 입력해줘야한다.
								pstmt.setString(1, (String) model2.getValueAt(row, 2));

								int cnt = pstmt.executeUpdate();
								model2.removeRow(row); // 테이블 상의 한줄 삭제
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} finally {
								try {
									rs.close();
									pstmt.close();
									con.close();
								} catch (Exception e2) {
								}
							}
							// 저장
						}

					} else {
						JOptionPane.showMessageDialog(null, "본인 소유의 글이 아닙니다!");
					}

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
		});
		jBtnDelRow.setBounds(new Rectangle(330, 450, 130, 35));
		// jBtnDelRow.setText("삭제");
		add(jBtnDelRow);

		// 글보이게하느버튼

		jBtnwrite = new JButton(Showwi);
		jBtnwrite.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				System.out.println(e.getActionCommand());
				trow = table.getSelectedRow() + 1;
				openwrite.trow = trow;
				new openwrite();
				int row = table.getSelectedRow();
				DefaultTableModel model2 = (DefaultTableModel) table.getModel();
				try {
					String count = "UPDATE tb_writespace SET t_조회수 = t_조회수 + 1 " + "WHERE t_제목 = ?";
					Class.forName(driver);
					// DriverManager로부터 커넥션을 얻어오는데 mysql서버 . id, pw 등을 언어온다.
					con = (Connection) DriverManager.getConnection(url, user, password);
					// 커넥션으로부터 실제로 sql쿼리 실행시키기 위한 statement 객체를 얻어온다.
					pstmt = con.prepareStatement(count);
					pstmt.setString(1, (String) model2.getValueAt(row, 2));
					rs = pstmt.executeQuery();
					
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
			
		});
		
		jBtnwrite.setBounds(new Rectangle(330, 350, 130, 35));

		add(jBtnwrite);
	}

	public void createAndShowUI() {
		freetable panel = new freetable();
		JFrame win = new JFrame();
		win.add(panel);
		win.setSize(565, 740);
		win.setVisible(true);
		win.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // dipose는 현재 프레임만 종료, exit는 활성화 되어 있는 전체 프레임 종료
		win.setResizable(false);
		win.setTitle("자유게시판");
		 win.setLocationRelativeTo(null);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {

				new freetable().createAndShowUI();
			}
		});
	}

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