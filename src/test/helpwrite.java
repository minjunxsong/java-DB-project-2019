package test;

import java.awt.BorderLayout;         //레이아웃 영역 배치
import java.awt.Dimension; //컴퍼넌트의 폭과 높이를 정수 정밀도로 수정
import java.awt.FlowLayout;           //한방향으로 배치
import java.awt.Font;                 //글꼴
import java.awt.Graphics;        //그래픽이미지
import java.awt.event.ActionEvent;    //자기 자신을 디스패치 할 수 있는 이벤트를 위한 인터페이스
import java.awt.event.ActionListener; //액션 이벤트를 받기 위한 리스너 인터페이스
import java.awt.event.MouseEvent;     //컴퍼넌트내에서 마우스 액션이 발생한 것을 나타내는 이벤트
import java.awt.event.MouseListener;  //마우스 이벤트 (프레스, 릴리스, 클릭, 컴퍼넌트에의 출입)를 받기 위한 리스너 인터페이스
import java.awt.event.WindowAdapter;  //윈도우 이벤트를 받기 위한 추상 어댑터 클래스
import java.awt.event.WindowEvent;    //윈도우 상태가 바뀐 것을 나타내는 이벤트
import java.sql.Connection;           //데이타베이스와의 접속
import java.sql.DriverManager;        //JDBC 드라이버를 관리하기 위함
import java.sql.PreparedStatement;    //프리컴파일 된 SQL 문을 나타내는 객체
import java.sql.ResultSet;            //데이타베이스의 결과 세트를 나타내는 데이터의 테이블
import java.sql.SQLException;         //데이타베이스 액세스 에러 또는 그 외의 에러에 관한 정보를 제공
import java.sql.Statement;            // 작성된 결과를 돌려주기 위해서 사용되는 객체
import java.util.Calendar;            //달려 필드 조작을 위한 클래스

import javax.swing.ImageIcon;         //아이콘을 이미지로 불러오기
import javax.swing.JButton;           //버튼 구현
import javax.swing.JFrame;            //프레임 구현
import javax.swing.JLabel;            //라벨 구현
import javax.swing.JOptionPane;       //다이얼로그박스 구현
import javax.swing.JPanel;            //패널 구현
import javax.swing.JScrollPane;       //스크롤바 구현
import javax.swing.JTable;            //테이블 구현
import javax.swing.JTextArea;         //TEXT를 표시하는 영역
import javax.swing.JTextField;        //TEXT를 입력하는 영역
import javax.swing.SwingConstants;    //배치 및 방향 지정을 위한 객체
import javax.swing.table.DefaultTableCellRenderer; //테이블 각 셀의 렌더링 표시
import javax.swing.table.DefaultTableModel;        //각 셀에 객체를 포함하는것 구현
import javax.swing.table.TableColumnModel;         //테이블 컬럼 모델 구현

public class helpwrite extends JFrame implements MouseListener {
	public String driver = "org.postgresql.Driver";
	public String url = "jdbc:postgresql://localhost:5432/postgres"; //DB주소
	public String user = "postgres";
	public String password = "1234";
	public Statement stmt = null;

	public PreparedStatement pstmt = null; // PreparedStatement 널값선언
	public Connection con = null;          //커넥션 널값 선언
	public static String text;             //static 선언
	public static String text2;
	public ResultSet rs = null;            //ResultSet 널갑 선언

	public static int josu;
	String tmember;
	String naeyoung;
	public String lmember;
	public String write;
	public String dong;
	public String go;
	public String u_dong;
	public String plustext;
	// 프레임과 텍스트 에어리어의 변수 선언
	private JFrame frame;
	JTable table1;
	JScrollPane scrollPane;
	JTextField textfield = null;
	JLabel time = null;
	JTextArea ta1 = null;;
	public JButton fix;
	public JButton del;
	public JButton in;
	public JButton addatguel;
	public JButton fixdatguel;
	public JButton deldatguel;
	public JButton check;

	JLabel johwesu;
	//폰트(글꼴) 설정
	Font f = new Font("돋음", Font.BOLD, 25);
	Font f1 = new Font("돋음", Font.BOLD, 17);
	Font f2 = new Font("돋음", Font.PLAIN, 13);
	public String colNames[] = { "채택여부", "동", "호수", "댓글" };
	int row;
	public DefaultTableModel model = new DefaultTableModel(colNames, 0);

	// 호출할 함수 작성
	public helpwrite() {
        //이미지 삽입
		ImageIcon icon = new ImageIcon("images/글보기창1.png");
		ImageIcon icon1 = new ImageIcon("images/덮밥.png");
		ImageIcon icon2 = new ImageIcon("images/글보기창1.png");

		ImageIcon Addedit = new ImageIcon("images/글수정.png");
		ImageIcon Addsave = new ImageIcon("images/글저장.png");

		setLayout(null); // 레이아웃 배치관리자 삭제
		table1 = new JTable(model); // 테이블에 모델객체 삽입
		table1.addMouseListener(this); // 리스너 등록
		scrollPane = new JScrollPane(table1); // 테이블에 스크롤
		scrollPane.setSize(522, 200);
		String johesu = Integer.toString(josu);

		TableColumnModel ta = table1.getColumnModel(); // 정렬할 테이블의 컬럼모델을 가져옴
		
		// 테이블 컬럼의 이동을 방지한다. 이거 안쓰면 마우스로 드로그 앤 드롭으로 엉망진창이 될수 있다.
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);

		ta.getColumn(0).setCellRenderer(dtcr);
		ta.getColumn(1).setCellRenderer(dtcr);
		ta.getColumn(0).setPreferredWidth(18);
		ta.getColumn(1).setPreferredWidth(18);
		ta.getColumn(0).setResizable(false);
		ta.getColumn(2).setPreferredWidth(18);
		ta.getColumn(3).setPreferredWidth(170);

		// 프레임 생성
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // dipose는 현재 프레임만 종료, exit는 활성화 되어 있는 전체 프레임 종료
		frame = new JFrame("도움게시판");
		fix = new JButton(Addedit);
		fix.setPreferredSize(new Dimension(80, 35));
		in = new JButton(Addsave);
		in.setPreferredSize(new Dimension(80, 35));

		ta1 = new JTextArea(20, 40);
		textfield = new JTextField(30);
		time = new JLabel();
		JScrollPane pane = new JScrollPane(ta1);
		JLabel label244 = new JLabel("제목");
		JLabel label23 = new JLabel("글내용");
		JLabel TimeLabel = new JLabel();
        //패널 설정
		JPanel p1 = new JPanel() {
			public void paintComponent(Graphics g) {

				g.drawImage(icon1.getImage(), 0, 0, null);

				setOpaque(false);
				super.paintComponent(g);
			}
		};

		JPanel test = new JPanel() {
			public void paintComponent(Graphics g) {

				g.drawImage(icon1.getImage(), 0, 0, null);

				setOpaque(false);
				super.paintComponent(g);
			}
		};

		JPanel p3 = new JPanel() {
			public void paintComponent(Graphics g) {

				g.drawImage(icon2.getImage(), 0, 0, null);

				setOpaque(false);
				super.paintComponent(g);
			}
		};

		JPanel p4 = new JPanel();
		p4.setLayout(new BorderLayout());

		johwesu = new JLabel("");
		// 스크롤바가 없는 텍스트 에어리어를 생성
		// 패널 생성 및 레이블과 텍스트에어리어의 부착

		p1.setLayout(new BorderLayout());
		test.setLayout(new BorderLayout());

		test.add(label244, "West");
		test.add(textfield, "Center");
		test.add(TimeLabel, "South");
		test.add(johwesu, "North");

		p1.add(label23, "West");

		p1.add(test, "North");
		p1.add(time, "South");
		p1.add(new JLabel("글을 써주세요", JLabel.CENTER), "Center");

		p1.add(pane, "Center");
		initialize();

		p3.setLayout(new FlowLayout());
		p3.add(p1, BorderLayout.NORTH);
		p3.add(fix);
		p3.add(in);
		p3.add(scrollPane, BorderLayout.SOUTH);
		p3.add(addatguel);
		p3.add(fixdatguel);
		p3.add(deldatguel);
		p3.add(check);
		// 텍스트 에어리어에 입려괸 텍스트를 화면에 표시

		frame.add(p3, "Center");		
		frame.setSize(510, 1020);
		frame.setResizable(false);
		frame.setVisible(true);
		
		see();
		select();
		TimerThread1 th = new TimerThread1(TimeLabel);
		th.start();

	}

	public void see() {
        
		try {
			String row2 = "SELECT*FROM(SELECT *,row_number() over()AS rownum FROM(SELECT *FROM help_write ORDER BY h_글쓴시간)x)x WHERE rownum = ?";
			Class.forName(driver);
			// DriverManager로부터 커넥션을 얻어오는데 mysql서버 . id, pw 등을 언어온다.
			con = (Connection) DriverManager.getConnection(url, user, password);
			// 커넥션으로부터 실제로 sql쿼리 실행시키기 위한 statement 객체를 얻어온다.

			pstmt = con.prepareStatement(row2);

			pstmt.setInt(1, josu);
			rs = pstmt.executeQuery();
			if (rs.next()) {                
				System.out.println(rs.getInt("rownum"));
				if (josu == rs.getInt("rownum")) {
					ta1.setText(rs.getString("h_글내용"));

					textfield.setText(rs.getString("h_제목"));
					time.setText("글쓴시간:" + rs.getString("h_글쓴시간"));
					String josu = Integer.toString(rs.getInt("h_조회수"));
					johwesu.setText("조회수:" + josu);
					// ta1은 openwrite클래스에 있는 텍스트에리어, textfield은 openwrite클래스에 있는 텍스트 필드
				}
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

	public void select() { // 테이블에 보이기 위해 검색
		String query = "select hc_채택,hc_dong, hc_hosu,hc_comment,hc_글내용 from helpcomment where hc_글내용 = ?";

		try {

			Class.forName(driver);
			// DriverManager로부터 커넥션을 얻어오는데 mysql서버 . id, pw 등을 언어온다.
			con = (Connection) DriverManager.getConnection(url, user, password);
			// 커넥션으로부터 실제로 sql쿼리 실행시키기 위한 statement 객체를 얻어온다.
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, ta1.getText());
			System.out.println(ta1.getText());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				// 각각 값을 가져와서 테이블값들을
				model.addRow(new Object[] { rs.getString("hc_채택"), rs.getString("hc_dong"), rs.getString("hc_hosu"),
						rs.getString("hc_comment") });
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
//테이블 새로 한줄 추가하는 부분

		// 글추가
		ImageIcon coadd = new ImageIcon("images/댓글추가.png");
		ImageIcon codrop = new ImageIcon("images/댓글삭제.png");
		ImageIcon cosave = new ImageIcon("images/댓글저장.png");
		ImageIcon cocheck = new ImageIcon("images/채택하기.png");

		addatguel = new JButton(coadd);
		addatguel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {

					Class.forName(driver);
					con = DriverManager.getConnection(url, user, password);
					pstmt = con.prepareStatement(login2);
					rs = pstmt.executeQuery(); // 리턴받아와서 데이터를 사용할 객체

					System.out.println(e.getActionCommand()); // 선택된
					DefaultTableModel model2 = (DefaultTableModel) table1.getModel();
					while (rs.next()) {
						model2.addRow(new Object[] { "X", rs.getString("u_dong"), rs.getString("u_hosu"),
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
		addatguel.setPreferredSize(new Dimension(80, 35));
		add(addatguel);

//테이블 새로 저장하는 부분
		fixdatguel = new JButton(cosave);
		fixdatguel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(e.getActionCommand()); // 선택된
				DefaultTableModel model2 = (DefaultTableModel) table1.getModel();
				int row2 = table1.getSelectedRow();

				String query = "insert into helpcomment (hc_채택, hc_dong, hc_hosu, hc_comment,hc_글내용) values(?,?,?,?,?)";
				try {
					Class.forName(driver); // 드라이버 로딩
					con = DriverManager.getConnection(url, user, password);
					pstmt = con.prepareStatement(query);
//물음표가 4개 이므로 4개 각각 입력해줘야한다.
					pstmt.setString(1, (String) model2.getValueAt(row2, 0));
					pstmt.setString(2, (String) model2.getValueAt(row2, 1));
					pstmt.setString(3, (String) model2.getValueAt(row2, 2));
					pstmt.setString(4, (String) model2.getValueAt(row2, 3));
					pstmt.setString(5, ta1.getText());
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
				model2.setRowCount(0); // 전체 테이블 화면을 지워
				select(); // 저장 후 다시 전체 값들을 받아옴.
			}
		});
		fixdatguel.setPreferredSize(new Dimension(80, 35));
		add(fixdatguel);

		deldatguel = new JButton(codrop);
		deldatguel.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				System.out.println(e.getActionCommand()); // 선택된w
				DefaultTableModel model2 = (DefaultTableModel) table1.getModel();
				// 선택된
				int row3 = table1.getSelectedRow() + 1;
				int row = table1.getSelectedRow();
				try {
					String row2 = "SELECT *FROM helpcomment,help_write,users WHERE u_dong = ? AND hc_dong = ? AND hc_hosu = ? AND u_hosu = ?";

					Class.forName(driver);
					// DriverManager로부터 커넥션을 얻어오는데 mysql서버 . id, pw 등을 언어온다.
					con = (Connection) DriverManager.getConnection(url, user, password);
					// 커넥션으로부터 실제로 sql쿼리 실행시키기 위한 statement 객체를 얻어온다.

					pstmt = con.prepareStatement(row2);
					pstmt.setString(1, (String) model2.getValueAt(row, 1));
					pstmt.setString(2, (String) model2.getValueAt(row, 1));
					pstmt.setString(3, (String) model2.getValueAt(row, 2));
					pstmt.setString(4, (String) model2.getValueAt(row, 2));
					rs = pstmt.executeQuery();

					if (rs.next()) {
					
						if (rs.getString("u_dong").equals(rs.getString("hc_dong"))) {
							JOptionPane.showMessageDialog(null, "댓글 삭제완료!");

							String query = "DELETE FROM helpcomment WHERE hc_comment= ?";
						
							try {

								pstmt = con.prepareStatement(query);
								// 물음표가 1개 이므로 4개 각각 입력해줘야한다.
								pstmt.setString(1, (String) model2.getValueAt(row, 3));

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
						JOptionPane.showMessageDialog(null, "본인 소유의 댓글이 아닙니다!");
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
		deldatguel.setPreferredSize(new Dimension(80, 35));
		add(deldatguel);

		check = new JButton(cocheck);
		check.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				// DefaultTableModel model2 = (DefaultTableModel) table1.getModel();

				RunnableTest hp = new RunnableTest();
				Thread R = new Thread(hp);
				R.start();
				
				DefaultTableModel model2 = (DefaultTableModel) table1.getModel();
				int row = table1.getSelectedRow();
				if (row < 0)
					return; // 선택이 안된 상태면 -1리턴

				String check = "SELECT *FROM helpcomment,help_write,users WHERE h_dong = hc_dong AND hc_dong = u_dong AND h_hosu = u_hosu AND h_hosu = hc_hosu";

				try {

					Class.forName(driver);
					// DriverManager로부터 커넥션을 얻어오는데 mysql서버 . id, pw 등을 언어온다.
					con = (Connection) DriverManager.getConnection(url, user, password);
					// 커넥션으로부터 실제로 sql쿼리 실행시키기 위한 statement 객체를 얻어온다.

					pstmt = con.prepareStatement(check);
					rs = pstmt.executeQuery();
					// int cnt = pstmt.executeUpdate();
					if (rs.next()) {
					
						if (rs.getInt("u_dong") == rs.getInt("h_dong")) {
							JOptionPane.showMessageDialog(null, "채택되었습니다.");
							String query = "UPDATE helpcomment SET hc_채택=? where hc_dong=? AND hc_글내용=?";
							try {
								Class.forName(driver); // 드라이버 로딩
								con = DriverManager.getConnection(url, user, password);
								pstmt = con.prepareStatement(query);
								// 물음표가 4개 이므로 4개 각각 입력해줘야한다.
								pstmt.setString(1, "O");
								pstmt.setString(2, (String) model2.getValueAt(row, 1));
								pstmt.setString(3, ta1.getText());

								String g = ta1.getText();
								ta1.setText(g + "\n\n\n채택된 댓글: " + (String) model2.getValueAt(row, 1) + "동"
										+ (String) model2.getValueAt(row, 2) + "호: "
										+ (String) model2.getValueAt(row, 3));
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
						}
					}else{
						JOptionPane.showMessageDialog(null, "채택실패!(글 소유자가 아닙니다!)");
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

				String row2 = "SELECT * FROM(SELECT *,row_number() over()AS rownum FROM helptable)x WHERE rownum = ?";

				try {

					Class.forName(driver);
					// DriverManager로부터 커넥션을 얻어오는데 mysql서버 . id, pw 등을 언어온다.
					con = (Connection) DriverManager.getConnection(url, user, password);
					// 커넥션으로부터 실제로 sql쿼리 실행시키기 위한 statement 객체를 얻어온다.

					pstmt = con.prepareStatement(row2);

					pstmt.setInt(1, josu);
					rs = pstmt.executeQuery();
					// int cnt = pstmt.executeUpdate();
					if (rs.next()) {
						System.out.println(rs.getInt("rownum"));
						if (josu == rs.getInt("rownum")) {
							String change = "UPDATE help_write SET h_글내용=?" + "where h_제목=?";
							try {
								Class.forName(driver); // 드라이버 로딩
								con = DriverManager.getConnection(url, user, password);
								pstmt = con.prepareStatement(change);
								// 물음표가 4개 이므로 4개 각각 입력해줘야한다.
								pstmt.setString(1, ta1.getText());
								pstmt.setString(2, rs.getString("제목"));

								int rnt = pstmt.executeUpdate();
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
				// table1.setModel(model2);
				model2.setRowCount(0); // 전체 테이블 화면을 지워
				select(); // 저장 후 다시 전체 값들을 받아옴.
			}
		});
		check.setPreferredSize(new Dimension(80, 35));
		add(check);

	}
//실행 
	public static void main(String[] args) {

		helpwrite tat = new helpwrite();

		tat.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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

	public class RunnableTest implements Runnable {
		// Runnable 인터페이스의 run()오버라이딩
		public void run() {
			int row = table1.getSelectedRow();

			table1.setValueAt("O", row, 0);
		}
	}

}
//타이머 쓰레드 구문
class TimerThread1 extends Thread {
	JLabel timerLabel;

	public TimerThread1(JLabel Label) {
		timerLabel = Label;
	}
    //시간 설정
	public void run() {
		Calendar time = Calendar.getInstance();
		int hour = time.get(Calendar.HOUR_OF_DAY); //시,날짜
		int minute = time.get(Calendar.MINUTE);    //분
		int sec = time.get(Calendar.SECOND);       //초

		while (true) {
			timerLabel.setText("현재시각: " + (Integer.toString(hour)) + "시" + (Integer.toString(minute)) + "분"
					+ (Integer.toString(sec) + "초"));
			sec++;
			if (sec == 60) {
				sec = 0;
				minute++;
			}
			if (minute == 60) {
				minute = 0;
				hour++;
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				return;
			}
		}
	}
}
