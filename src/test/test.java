package test;


import java.awt.BorderLayout; //각 영역 사이에 간격이 있는 BorderLayout을 생성한다.
import java.awt.Container; //일반적인 AWT 컨테이너 개체는 다른 AWT 구성 요소를 포함 할 수있는 구성 요소입니다
import java.awt.Cursor; //지정된 클래스로 커서를 만듭니다. 
import java.awt.Dimension; //이 Dimension 객체의 크기를 가져옵니다.
import java.awt.Graphics; //Graphics 클래스는 모든 그래픽 컨텍스트에 대한 추상 기본 클래스로, 응용 프로그램이 다양한 장치에서 구현 된 구성 요소와 화면 외부 이미지에 그릴 수 있도록합니다. 
import java.awt.Toolkit; // 기본 툴킷을 가져옵니다.
import java.awt.event.ActionEvent; //객체는 이벤트가 발생할 때이 ActionEvent 가져옵니다
import java.awt.event.ActionListener; // 마우스 이동 및 마우스 드래그를 추적하려면 MouseMotionListener 사용하십시오
import java.awt.event.MouseAdapter; //마우스 이벤트를 받기위한 추상 어댑터 클래스  이 클래스의 메소드는 비어 있습니다
import java.awt.event.MouseEvent; //구성 요소에서 마우스 동작이 발생했음을 나타내는 이벤트입니다

import javax.swing.ImageIcon; // 이미지에서 아이콘을 그리는 Icon 인터페이스의 구현입니다
import javax.swing.JButton; // 설정된 텍스트 나 아이콘이없는 버튼을 만듭니다
import javax.swing.JFrame;  // 메인 프레임
import javax.swing.JOptionPane; // 대화 상자 를 작성하는 기능이 있습니다.
import javax.swing.JPanel; //  보조 프레임
import javax.swing.JScrollPane; // 접근 가능한 ScrollPaneConstants 구현

public class test extends JFrame {
   JButton btnlogin, btnBoard, btnend,btnjoin;
   Container c = getContentPane();
 
   JScrollPane scrollPane;
   ImageIcon icon;
   
   public test() {
      setTitle("아파트 이웃게시판");
      //Toolkit toolkit = Toolkit.getDefaultToolkit();
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //dipose는 현재 프레임만 종료, exit는 활성화 되어 있는 전체 프레임 종료
  
      icon = new ImageIcon("images/메인화면.png");

      JPanel background = new JPanel() {
         public void paintComponent(Graphics g) {

            g.drawImage(icon.getImage(), 0, 0, null);

            setOpaque(false);
            super.paintComponent(g);
         }
      };
      
      background.setLayout(null);
      
      
      ImageIcon login2 = new ImageIcon("images/로그인이미지.png");
      ImageIcon loginin = new ImageIcon("images/로그인이미지2.png");
      btnlogin = new JButton(login2);
      btnlogin.setBounds(65, 500, 330, 55);
      
      btnlogin.setBorderPainted(false); 
      btnlogin.setContentAreaFilled(false); 
      btnlogin.setFocusPainted(false); 
      
      btnlogin.addMouseListener(new MouseAdapter() { //마우스 이벤트
            @Override
            public void mouseEntered(MouseEvent e) { //마우스를 버튼위에 올렸을떄 액션
               btnlogin.setIcon(loginin);
               btnlogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) { //마우스를 올리지 않았을때 액션
               btnlogin.setIcon(login2);
               btnlogin.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            @Override
            public void mousePressed(MouseEvent e) {//마우스를 클릭했을떄 액션
               //new Login();
            }
         });
      
      
      
      background.add(btnlogin);

      setLayout(new BorderLayout());
      add(background);

      scrollPane = new JScrollPane(background);
      setContentPane(scrollPane);
       btnlogin.addActionListener(new MyButtonListener());
      
      
      
      
      
      
      
      
      setSize(465, 690);
      //pack(); //프레임과 이미지크기를 맞춤
      //setResizable(false); //프레임의 크기 조절을 방지
      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

      // 전체 창 1/2 나눈 곳에 위치
      int xPos = screenSize.width / 2 - getSize().width / 2;
      int yPos = screenSize.height / 2 - getSize().height / 2;

      setLocation(xPos, yPos);

      setVisible(true); // false는 프레임 창 비활성화
      //CenterScreen(); // 프레임이 화면 중앙에 뜨도록 정의
      
   }

   
   class MyButtonListener implements ActionListener {
      public void actionPerformed(ActionEvent e) {
         Object source = e.getSource();
         
         if (source == btnBoard) {
            
         } else if (source == btnjoin) {
            new MemberProc();
         }
         else if (source == btnlogin)
         {
            new excute().selectDelete();
         }
         else if (source == btnend) {
            
            int result = JOptionPane.showConfirmDialog(null, "종료하시겠습니까?", "종료", JOptionPane.YES_NO_OPTION);

            if (result == JOptionPane.CLOSED_OPTION)
               return;
            else if (result == JOptionPane.YES_OPTION)
               System.exit(0); // exit는 프레임 싹다 종료 Discope는 해당 프레임만 종료
            else
               return;
         }
      }
   }

   public static void main(String[] args) {
      // TODO Auto-generated method stub
      new test();
   }

}