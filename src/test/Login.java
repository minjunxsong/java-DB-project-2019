package test;

import java.awt.BorderLayout;         //레이아웃 영역 배치
import java.awt.Cursor;             //마우스의 커서비트맵 설정
import java.awt.Dimension; //컴퍼넌트의 폭과 높이를 정수 정밀도로 수정
import java.awt.FlowLayout;         //한방향으로 배치및설정
import java.awt.Font;            //글꼴      
import java.awt.Graphics;        //그래픽
import java.awt.Image;           //이미지
import java.awt.Toolkit;         //윈도우 툴킷으로 모든 구현 클래스
import java.awt.event.MouseAdapter;    //마우스 이벤트를 받는 어댑터
import java.awt.event.MouseEvent;      //마우스 액션이 발생할것을 나타내는 이벤트
import java.awt.event.WindowAdapter;   //윈도우 이벤트를 받는 어댑터
import java.awt.event.WindowEvent;     //윈도우 상태가 바뀌는것을 나타내는 이벤트

import javax.swing.ImageIcon;          //아이콘을 이미지를 불러와사용
import javax.swing.JButton;           //버튼 구현
import javax.swing.JFrame;            //프레임 구현
import javax.swing.JLabel;            //라벨 구현
import javax.swing.JPanel;            //패널 구현
import javax.swing.JPasswordField;    //패스워드필드 구현
import javax.swing.JScrollPane;       //스크롤바 구현
import javax.swing.JTextField;        //텍스트 필드 구현

public class Login extends JFrame {   //로그인창 디자인
	JScrollPane scrollPane;
	ImageIcon icon;

	static JLabel label1, label2, label3, EX; //라벨설정
	JTextField tex1;                           //로그인id
	JPasswordField tex2;                       //로그인pw
	JButton button1, button2, button3;         //버튼 설정 
	public String id1, pw1;                    
	//글꼴 설정
	Font f = new Font("돋음", Font.BOLD, 25);
	Font f1 = new Font("돋음", Font.BOLD, 17);
	Font f2 = new Font("돋음", Font.PLAIN, 13);

	public Login() {
    //이미지 삽입
		icon = new ImageIcon("images/로그인2.png");

		JPanel background = new JPanel() {
			public void paintComponent(Graphics g) {

				g.drawImage(icon.getImage(), 0, 0, null);

				setOpaque(false);
				super.paintComponent(g);
			}
		};

        
		ImageIcon register = new ImageIcon("images/회원가입.png");
		ImageIcon cel = new ImageIcon("images/종료.png");
		ImageIcon login = new ImageIcon("images/로그인아이콘.png");
		ImageIcon loginin = new ImageIcon("images/로그인아이콘2.png");

		button1 = new JButton(login);
		button1.setBounds(364, 164, 104, 80); //버튼 배치

		button1.setBorderPainted(false);     //버튼테두리 사용안함
		button1.setContentAreaFilled(false); //버튼 내용영역 채움 사용안함
		button1.setFocusPainted(false);      //버튼이 선택(focus)되었을때 생기는 테두리 사용안함
		

		button1.addMouseListener(new MouseAdapter() { // 마우스 이벤트
			@Override
			public void mouseEntered(MouseEvent e) { // 마우스를 버튼위에 올렸을떄 액션
				button1.setIcon(loginin);
				button1.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) { // 마우스를 올리지 않았을때 액션
				button1.setIcon(login);
				button1.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});

		button2 = new JButton(cel);
		button2.setBounds(370, 250, 90, 28);

		button2.setBorderPainted(false);
		button2.setContentAreaFilled(false);
		button2.setFocusPainted(false);

		button3 = new JButton(register);
		button3.setBounds(260, 250, 90, 28);

		button3.setBorderPainted(false);
		button3.setContentAreaFilled(false);
		button3.setFocusPainted(false);

		background.add(button1);
		
		background.add(button3);

		background.setLayout(null);

		label1 = new JLabel("아이디");
		label1.setBounds(50, 165, 70, 27);
		label1.setFont(f1);

		background.add(label1);

		tex1 = new JTextField();

		tex1.setBounds(123, 162, 230, 37);

		background.add(tex1);

		label2 = new JLabel("비밀번호");

		label2.setBounds(30, 210, 75, 27);
		label2.setFont(f1);

		background.add(label2);

		tex2 = new JPasswordField();

		tex2.setBounds(123, 210, 230, 37);

		background.add(tex2);

		JPanel p3 = new JPanel();
		p3.setLayout(new FlowLayout());
		label3 = new JLabel("안녕");
		p3.add(label3);

		setTitle("로그인");
		setSize(500, 350);
		setVisible(true);
		setLayout(new BorderLayout());

		add(background);
		add(p3, BorderLayout.NORTH);
		scrollPane = new JScrollPane(background);
		setContentPane(scrollPane);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //스크린 사이즈 저장

		// 전체 창 1/2 나눈 곳에 위치
		int xPos = screenSize.width / 2 - getSize().width / 2;
		int yPos = screenSize.height / 2 - getSize().height / 2;

		setLocation(xPos, yPos);

		addWindowListener(new WindowHandler());
	}

}

class WindowHandler extends WindowAdapter {
	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}
}
