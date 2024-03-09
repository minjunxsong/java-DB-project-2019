package test;

import java.awt.BorderLayout;    //레이아웃 영역 배치
import java.awt.Color;           //색상
import java.awt.Dimension;       //컴퍼넌트의 폭과 높이를 정수 정밀도로 수정
import java.awt.Font;            //글꼴      
import java.awt.Graphics;        //그래픽
import java.awt.Panel;           //패널
import java.awt.TextField;       //텍스트 필드 
import java.awt.Toolkit;         //윈도우 툴킷으로 모든 구현 클래스
import java.awt.event.WindowAdapter; //윈도우 이벤트를 받기 위한 어댑터
import java.awt.event.WindowEvent;   //윈도우 상태변화를 나타내는 클래스

import javax.swing.ImageIcon;          //아이콘을 이미지를 불러와사용
import javax.swing.JButton;           //버튼 구현
import javax.swing.JFrame;            //프레임 구현
import javax.swing.JLabel;            //라벨 구현
import javax.swing.JPanel;            //패널 구현
import javax.swing.JScrollPane;       //스크롤바 구현
import javax.swing.border.LineBorder; //직선 보더를 구현하는 클래스

public class LoginSub extends JFrame {
//라벨구문설정
   JLabel label1;
   TextField tex1;
   JButton button1, button2, button3;
   Panel p1, p2, p3;
   public String id1, pw1;
    JScrollPane scrollPane;
    Font f = new Font("돋음", Font.BOLD, 25);
       ImageIcon icon;
   public LoginSub() {
      
      //이미지 삽입
          
           icon = new ImageIcon("images/로그인서브.png");
          
         
           JPanel background = new JPanel() {
               public void paintComponent(Graphics g) {
                   
                   g.drawImage(icon.getImage(), 0, 0, null);
                  
                   setOpaque(false); 
                   super.paintComponent(g);
               }   
           };


           background.setLayout(null);
          
      p3 = new Panel();
      p3.setLayout(new BorderLayout());

      
      ImageIcon noticeboard = new ImageIcon("images/게시판11.png");
		ImageIcon dropout = new ImageIcon("images/탈퇴1.png");
		ImageIcon logout = new ImageIcon("images/로그아웃1.png");
      
      
      
      
      
      
      
//누구누구로그인창
      label1 = new JLabel();
      label1.setText("aa");
      label1.setFont(f);
      label1.setBounds(80, 70, 350, 47);

      background.add(label1);
//게시판
      button1 = new JButton(noticeboard);
      button1.setBounds(160, 219, 90, 40);
//탈퇴
      button2 = new JButton(dropout);
      button2.setBounds(260, 219, 90, 40);
//로그아웃
      button3 = new JButton(logout);
      button3.setBounds(360, 219, 90, 40);

//버튼추가
      background.add(button1);
      background.add(button2);
      background.add(button3);

      tex1 = new TextField();
      tex1.setText("");
      tex1.setBounds(40 ,20,400, 27);
      p3.add(tex1);

      setTitle("환영합니다. ");
      setSize(520, 390);
      setResizable(false);
      setVisible(false);
      setLayout(new BorderLayout());

      //스윙패널에 배경추가
      add(background);
      add(p3, BorderLayout.NORTH);
       scrollPane = new JScrollPane(background);
           setContentPane(scrollPane);
           //사이즈 정밀 설정
           Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

         
         int xPos = screenSize.width / 2 - getSize().width / 2;
         int yPos = screenSize.height / 2 - getSize().height / 2;

         setLocation(xPos, yPos);
      addWindowListener(new WindowHandler());
   }
class WindowHandler extends WindowAdapter {
      public void windowClosing(WindowEvent e) {
         System.exit(0);
      }
   }
   
   
}

   