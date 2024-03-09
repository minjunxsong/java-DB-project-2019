package test;

import java.awt.BorderLayout;  //각 영역 사이에 간격이 있는 BorderLayout을 생성한다.
import java.awt.Dimension; //  이 Dimension 객체의 크기를 가져옵니다.
import java.awt.FlowLayout;  //흐름 레이아웃은 단락의 텍스트 줄처럼 구성 요소를 방향 흐름으로 배열합니다
import java.awt.Font;   // Font 클래스는 텍스트를 시각적으로 렌더링하는 데 사용되는 글꼴을 나타냅니다
import java.awt.Graphics;  //Graphics 클래스는 모든 그래픽 컨텍스트에 대한 추상 기본 클래스로, 응용 프로그램이 다양한 장치에서 구현 된 구성 요소와 화면 외부 이미지에 그릴 수 있도록합니다. 
import java.awt.Toolkit; // 기본 툴킷을 가져옵니다.
import java.awt.event.ActionEvent; //객체는 이벤트가 발생할 때이 ActionEvent 가져옵니다
import java.awt.event.ActionListener; // 마우스 이동 및 마우스 드래그를 추적하려면 MouseMotionListener 사용하십시오

import java.sql.Connection; //데이터베이스와 연결하는 객체입니다
import java.sql.DriverManager; // 연결할 JDBC 드라이버를 DriverManager 지정하십시오
import java.sql.PreparedStatement; //  사전 컴파일 된 SQL 문을 나타내는 오브젝트입니다.
import java.sql.ResultSet;  //테이블에서 SQL 문을 실행하려면 Statement 오브젝트를 설정하십시오.따라서이 가져 오기 행을 코드 맨 위에 추가하십시오. 
import java.sql.SQLException; // DB연동
import java.sql.Statement; //데이터 베이스에 테이블 연결

import javax.swing.ImageIcon; // 이미지에서 아이콘을 그리는 Icon 인터페이스의 구현입니다
import javax.swing.JButton;   // 설정된 텍스트 나 아이콘이없는 버튼을 만듭니다
import javax.swing.JFrame; //  메인 프레임
import javax.swing.JLabel; // 짧은 텍스트 문자열 또는 이미지 또는 둘 다의 표시 영역입니다.
import javax.swing.JOptionPane; // 대화 상자 를 작성하는 기능이 있습니다.
import javax.swing.JPanel;   //  보조 프레임
import javax.swing.JScrollPane; // 접근 가능한 ScrollPaneConstants 구현
import javax.swing.JTable; //빈 셀로 테이블이 생성됩니다
import javax.swing.JTextArea; //일반 텍스트를 표시하는 여러 줄 영역입니다
import javax.swing.JTextField; //한 줄의 텍스트를 편집 할 수있는 간단한 구성 요소입니다.

public class openjtextarea extends JFrame {
   public String driver = "org.postgresql.Driver";
   public String url = "jdbc:postgresql://localhost:5432/postgres"; //
   public String user = "postgres";
   public String password = "1234";
   public Statement stmt = null;

   public PreparedStatement ps = null; // 명령
   public Connection con = null;

   public ResultSet rs = null;
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
      ImageIcon icon;
      
   

   Font f1 = new Font("돋음", Font.BOLD, 17);

   // 호출할 함수 작성
   public openjtextarea() {
      
      
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
      // p1.add(textfield, "North");

      JLabel areaname = new JLabel("글내용");
      areaname.setFont(f1);
      areaname.setBounds(20, 60, 60, 27);
      p1.add(areaname);

      ta1 = new JTextArea(250, 300);
      ta1.setBounds(90, 60, 280, 300);
      p1.add(ta1);

      fix = new JButton("수정");
      fix.setBounds(90, 390, 70, 50);
      //p1.add(fix);

      del = new JButton("삭제");
      del.setBounds(190, 390, 70, 50);
      //p1.add(del);

      in = new JButton(wsave);
      in.setBounds(90, 390, 280, 50);
      p1.add(in);

      // p1.add(new JLabel("글을 써주세요", JLabel.CENTER), "Center");
      // p1.add(ta1, "Center");
      in.addActionListener(new Listener());
      // JPanel p3 = new JPanel();
      // p3.setLayout(new FlowLayout());
      
      
      
      

      // p1.add(p3);
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
      // f.add(p3, "Center");
      f.setSize(410,500);
      f.setResizable(false);
      f.setVisible(true);
      f.setLocationRelativeTo(null);

   }

   void write() {

      tmember = "SELECT * FROM tb_member,users,freetable,TO_CHAR(now(), 'YYYY년MM월DD일,HH시MI분SS초') WHERE(dong = u_dong and hosu = u_hosu)";

      String join = ta1.getText();
      //new openwrite().ta1.setText("ㅎㅇ").select();
      try {

         Class.forName(driver);
         // DriverManager로부터 커넥션을 얻어오는데 sql서버 . id, pw 등을 언어온다.
         con = (Connection) DriverManager.getConnection(url, user, password);
         // 커넥션으로부터 실제로 sql쿼리 실행시키기 위한 statement 객체를 얻어온다.
         stmt = (Statement) con.createStatement();
         ResultSet rs = stmt.executeQuery(tmember);
         
         if (rs.next()) {

            boolean same = rs.getString("dong").equals(rs.getString("u_dong"));

            if (same) {

               System.out.println("글저장성공");

               String sql = "insert into tb_writespace(t_dong,t_글내용,t_제목,t_조회수,t_글쓴시간) values(?,?,?,?,?)";

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
                     

                     int r = ps.executeUpdate(); // 저장
                  }
               } catch (SQLException e1) {
                  // TODO Auto-generated catch block
                  e1.printStackTrace();
               }

            }

         }
      } catch (Exception b) {
         b.printStackTrace();
      }
      f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
   }

   class Listener implements ActionListener {

      @Override

      public void actionPerformed(ActionEvent e) {
         if (e.getSource() == in) {

            System.out.println("버튼누르기성공");
            write();
              JOptionPane.showMessageDialog(null, "글이 저장되었습니다.");
              f.dispose();
         }
      }
   }

   public static void main(String[] args) {
      openjtextarea tat = new openjtextarea();
      tat.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // dipose는 현재 프레임만 종료, exit는 활성화 되어 있는 전체 프레임 종료
      
   }

}