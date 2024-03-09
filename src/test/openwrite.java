package test;

import java.awt.BorderLayout; //각 영역 사이에 간격이 있는 BorderLayout을 생성한다.
import java.awt.Dimension;//  이 Dimension 객체의 크기를 가져옵니다.
import java.awt.FlowLayout; //흐름 레이아웃은 단락의 텍스트 줄처럼 구성 요소를 방향 흐름으로 배열합니다
import java.awt.Font;// Font 클래스는 텍스트를 시각적으로 렌더링하는 데 사용되는 글꼴을 나타냅니다
import java.awt.Graphics; //Graphics 클래스는 모든 그래픽 컨텍스트에 대한 추상 기본 클래스로, 응용 프로그램이 다양한 장치에서 구현 된 구성 요소와 화면 외부 이미지에 그릴 수 있도록합니다. 
import java.awt.event.ActionEvent; //객체는 이벤트가 발생할 때이 ActionEvent 가져옵니다
import java.awt.event.ActionListener; // 마우스 이동 및 마우스 드래그를 추적하려면 MouseMotionListener 사용하십시오
import java.awt.event.MouseEvent; //구성 요소에서 마우스 동작이 발생했음을 나타내는 이벤트입니다
import java.awt.event.MouseListener; // 마우스 이동 및 마우스 드래그를 추적하려면 MouseMotionListener 사용하십시오
import java.sql.Connection; //데이터베이스와 연결하는 객체입니다
import java.sql.DriverManager; // 연결할 JDBC 드라이버를 DriverManager 지정하십시오
import java.sql.PreparedStatement; //  사전 컴파일 된 SQL 문을 나타내는 오브젝트입니다.
import java.sql.ResultSet; //테이블에서 SQL 문을 실행하려면 Statement 오브젝트를 설정하십시오
import java.sql.SQLException; // DB연동
import java.sql.Statement; //데이터 베이스에 테이블 연결
import java.util.Calendar; //일정 필드 세트간에 날짜를 변환하는 메소드를 제공하는 추상 클래스입니다. 

import javax.swing.ImageIcon;  // 이미지에서 아이콘을 그리는 Icon 인터페이스의 구현입니다
import javax.swing.JButton; // 설정된 텍스트 나 아이콘이없는 버튼을 만듭니다
import javax.swing.JFrame; // 메인 프레임
import javax.swing.JLabel; // 짧은 텍스트 문자열 또는 이미지 또는 둘 다의 표시 영역입니다.
import javax.swing.JOptionPane; // 대화 상자 를 작성하는 기능이 있습니다.
import javax.swing.JPanel; //  보조 프레임
import javax.swing.JScrollPane; // 접근 가능한 ScrollPaneConstants 구현
import javax.swing.JTable; //빈 셀로 테이블이 생성됩니다
import javax.swing.JTextArea; //일반 텍스트를 표시하는 여러 줄 영역입니다
import javax.swing.JTextField; //한 줄의 텍스트를 편집 할 수있는 간단한 구성 요소입니다.
import javax.swing.table.DefaultTableModel; //이것은 Vector of Vectors 를 사용하여 셀 값 객체를 저장하는 TableModel 의 구현입니다.

public class openwrite extends JFrame implements MouseListener {
   public String driver = "org.postgresql.Driver";
   public String url = "jdbc:postgresql://localhost:5432/postgres"; //
   public String user = "postgres";
   public String password = "1234";
   public Statement stmt = null;

   public PreparedStatement pstmt = null; // 명령
   public Connection con = null;

   public ResultSet rs = null;
   public static int trow;
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
   public static JLabel label244;
   JLabel johwesu;
   // public int row;
   Font f = new Font("돋음", Font.BOLD, 25);
   Font f1 = new Font("돋음", Font.BOLD, 17);
   Font f2 = new Font("돋음", Font.PLAIN, 13);
   public String colNames[] = { "동", "호수", "댓글" };
   int row;
   public DefaultTableModel model = new DefaultTableModel(colNames, 0);

   // 호출할 함수 작성
   public openwrite() {

      ImageIcon icon = new ImageIcon("images/글보기창1.png");
      ImageIcon icon1 = new ImageIcon("images/덮밥.png");
      setLayout(null); // 레이아웃 배치관리자 삭제
      table1 = new JTable(model); // 테이블에 모델객체 삽입
      table1.addMouseListener(this); // 리스너 등록
      scrollPane = new JScrollPane(table1); // 테이블에 스크롤

      scrollPane.setSize(530, 290);

      String johesu = Integer.toString(josu);
      
      ImageIcon Addedit = new ImageIcon("images/글수정.png");
      ImageIcon Addsave = new ImageIcon("images/글저장.png");
      
      
      // 프레임 생성
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // dipose는 현재 프레임만 종료, exit는 활성화 되어 있는 전체 프레임 종료
      frame = new JFrame("자유게시판");
      fix = new JButton(Addedit);
      fix.setPreferredSize(new Dimension(80,35));
      in = new JButton(Addsave);
      in.setPreferredSize(new Dimension(80,35));
      ta1 = new JTextArea(20, 40);
      textfield = new JTextField(30);
      time = new JLabel("");
      JScrollPane pane = new JScrollPane(ta1);
      label244 = new JLabel("제목: ");
      JLabel label23 = new JLabel("글내용:");
      JLabel TimeLabel = new JLabel("글쓴시간:");
      johwesu = new JLabel("");
      // 스크롤바가 없는 텍스트 에어리어를 생성

      // 패널 생성 및 레이블과 텍스트에어리어의 부착
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
      test.setLayout(new BorderLayout());
      p1.setLayout(new BorderLayout());
      test.add(label244, "West");
      test.add(textfield, "Center");
      test.add(TimeLabel, "South");
      test.add(johwesu, "North");
      p1.add(label23, "West");
      // p1.add(johwesu,"East");
      p1.add(test, "North");
      p1.add(time, "South");
      p1.add(new JLabel("글을 써주세요", JLabel.CENTER), "Center");
      p1.add(pane, "Center");
      initialize();

      JPanel background = new JPanel() {
         public void paintComponent(Graphics g) {

            g.drawImage(icon.getImage(), 0, 0, null);

            setOpaque(false);
            super.paintComponent(g);
         }
      };
      background.setLayout(new FlowLayout());
      background.add(p1, BorderLayout.NORTH);

      background.add(fix);
      background.add(in);
      background.add(scrollPane, BorderLayout.SOUTH);
      background.add(addatguel);
      background.add(fixdatguel);
      background.add(deldatguel);
      // 텍스트 에어리어에 입려괸 텍스트를 화면에 표시

      frame.add(background, "Center");
      // f.add(p2, "South");
      frame.setSize(510, 1020);
      frame.setVisible(true);
      // in.addActionListener(new Listener());
      see();
      select();
      TimerThread1 th = new TimerThread1(TimeLabel);
      th.start();
      // Thread1 th1 = new Thread1();
   }

   public void see() {

     
      try {
         String row2 = "SELECT*FROM(SELECT *,row_number() over()AS rownum FROM(SELECT *FROM tb_writespace ORDER BY t_글쓴시간)x)x WHERE rownum = ?";
         Class.forName(driver);
         // DriverManager로부터 커넥션을 얻어오는데 mysql서버 . id, pw 등을 언어온다.
         con = (Connection) DriverManager.getConnection(url, user, password);
         // 커넥션으로부터 실제로 sql쿼리 실행시키기 위한 statement 객체를 얻어온다.

         pstmt = con.prepareStatement(row2);

         pstmt.setInt(1, trow);
         rs = pstmt.executeQuery();
         // int cnt = pstmt.executeUpdate();

         if (rs.next()) {
            System.out.println(rs.getInt("rownum"));
            if (trow == rs.getInt("rownum")) {
               ta1.setText(rs.getString("t_글내용"));
               textfield.setText(rs.getString("t_제목"));
               time.setText("글쓴시간:" + rs.getString("t_글쓴시간"));
               String josu = Integer.toString(rs.getInt("t_조회수"));
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
      String query = "select c_dong, c_hosu,c_comment,c_글내용 from comment where c_글내용 = ?";

      try {

         Class.forName(driver);
         // DriverManager로부터 커넥션을 얻어오는데 mysql서버 . id, pw 등을 언어온다.
         con = (Connection) DriverManager.getConnection(url, user, password);
         // 커넥션으로부터 실제로 sql쿼리 실행시키기 위한 statement 객체를 얻어온다.
         pstmt = con.prepareStatement(query);
         pstmt.setString(1, ta1.getText());
         // pstmt.setString(1, ta1.getText());
         rs = pstmt.executeQuery();

         while (rs.next()) {
            // 각각 값을 가져와서 테이블값들을
            model.addRow(
                  new Object[] { rs.getString("c_dong"), rs.getString("c_hosu"), rs.getString("c_comment") });
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
      ImageIcon AddRow = new ImageIcon("images/글추가.png");
      ImageIcon AddWi = new ImageIcon("images/글쓰기.png");
      ImageIcon Showwi = new ImageIcon("images/글보기.png");
      ImageIcon edit1 = new ImageIcon("images/수정2.png");
      ImageIcon delete = new ImageIcon("images/삭제.png");
      ImageIcon coadd = new ImageIcon("images/댓글추가.png");
      ImageIcon codrop = new ImageIcon("images/댓글삭제.png");
      ImageIcon cosave = new ImageIcon("images/댓글저장.png");
      
      

      addatguel = new JButton(coadd);
      addatguel.setPreferredSize(new Dimension(80,35));
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

      add(addatguel);

//테이블 새로 저장하는 부분
      fixdatguel = new JButton(cosave);
      fixdatguel.setPreferredSize(new Dimension(80,35));
      fixdatguel.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            System.out.println(e.getActionCommand()); // 선택된
            DefaultTableModel model2 = (DefaultTableModel) table1.getModel();
            int row2 = table1.getSelectedRow();
            String query = "insert into comment (c_dong, c_hosu, c_comment,c_글내용) values(?,?,?,?)";
            try {
               Class.forName(driver); // 드라이버 로딩
               con = DriverManager.getConnection(url, user, password);
               pstmt = con.prepareStatement(query);
//물음표가 4개 이므로 4개 각각 입력해줘야한다.
               pstmt.setString(1, (String) model2.getValueAt(row2, 0));
               pstmt.setString(2, (String) model2.getValueAt(row2, 1));  
               pstmt.setString(3, (String) model2.getValueAt(row2, 2));
               pstmt.setString(4, ta1.getText());

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

      add(fixdatguel);

      deldatguel = new JButton(codrop);
      deldatguel.setPreferredSize(new Dimension(80,35));
      deldatguel.addActionListener(new ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent e) {
            System.out.println(e.getActionCommand()); // 선택된w
            DefaultTableModel model2 = (DefaultTableModel) table1.getModel();
            // 선택된
            int row = table1.getSelectedRow();
            try {
               String row2 = "SELECT *FROM comment,tb_writespace,users WHERE u_dong = ? AND c_dong = ? AND c_hosu = ? AND u_hosu = ?";
               Class.forName(driver);
               // DriverManager로부터 커넥션을 얻어오는데 mysql서버 . id, pw 등을 언어온다.
               con = (Connection) DriverManager.getConnection(url, user, password);
               // 커넥션으로부터 실제로 sql쿼리 실행시키기 위한 statement 객체를 얻어온다.
               pstmt = con.prepareStatement(row2);
              pstmt.setString(1, (String) model2.getValueAt(row, 0));
         pstmt.setString(2, (String) model2.getValueAt(row, 0));
         pstmt.setString(3, (String) model2.getValueAt(row, 1));
         pstmt.setString(4, (String) model2.getValueAt(row, 1));
               rs = pstmt.executeQuery();

               if (rs.next()) {
                
                  if (rs.getString("u_dong").equals(rs.getString("c_dong"))) {
                     JOptionPane.showMessageDialog(null, "댓글 삭제완료!");

                     String query = "DELETE FROM comment WHERE c_comment= ?";
                  
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

      add(deldatguel);
   }

   public static void main(String[] args) {

      openwrite tat = new openwrite();

      tat.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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