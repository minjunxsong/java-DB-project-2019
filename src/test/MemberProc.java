package test;

import java.awt.BorderLayout;  //각 영역 사이에 간격이 있는 BorderLayout을 생성한다.
import java.awt.Dimension;   //  이 Dimension 객체의 크기를 가져옵니다.
import java.awt.FlowLayout;   //흐름 레이아웃은 단락의 텍스트 줄처럼 구성 요소를 방향 흐름으로 배열합니다
import java.awt.Font;       // Font 클래스는 텍스트를 시각적으로 렌더링하는 데 사용되는 글꼴을 나타냅니다
import java.awt.Graphics;   //Graphics 클래스는 모든 그래픽 컨텍스트에 대한 추상 기본 클래스로, 응용 프로그램이 다양한 장치에서 구현 된 구성 요소와 화면 외부 이미지에 그릴 수 있도록합니다. 
import java.awt.GridBagConstraints; //GridBagLayout 클래스를 사용하여 배치 된 구성 요소의 제한 조건을 지정합니다
import java.awt.GridBagLayout; //구성 요소의 크기가 같지 않아도 구성 요소를 세로, 가로 또는 기준선을 따라 정렬하는 유연한 레이아웃 관리자입니다.
import java.awt.Insets; // Insets 객체는 컨테이너의 경계를 나타냅니다
import java.awt.Toolkit; // 기본 툴킷을 가져옵니다.
import java.awt.event.ActionEvent; //객체는 이벤트가 발생할 때이 ActionEvent 가져옵니다
import java.awt.event.ActionListener; // 마우스 이동 및 마우스 드래그를 추적하려면 MouseMotionListener 사용하십시오

import javax.swing.ImageIcon;  // 이미지에서 아이콘을 그리는 Icon 인터페이스의 구현입니다
import javax.swing.JButton;     // 설정된 텍스트 나 아이콘이없는 버튼을 만듭니다
import javax.swing.JComponent;  // 최상위 컨테이너를 제외한 모든 Swing 구성 요소의 기본 클래스입니다.
import javax.swing.JFrame;     //  메인 프레임
import javax.swing.JLabel;    // 짧은 텍스트 문자열 또는 이미지 또는 둘 다의 표시 영역입니다.
import javax.swing.JOptionPane;  // 대화 상자 를 작성하는 기능이 있습니다.
import javax.swing.JPanel;   //  보조 프레임
import javax.swing.JPasswordField; // 문자는 표시하지 않는 한 줄의 텍스트를 편집 할 수있는 간단한 구성 요소입니다.
import javax.swing.JScrollPane; // 접근 가능한 ScrollPaneConstants 구현
import javax.swing.JTextField; // 한 줄의 텍스트를 편집 할 수있는 간단한 구성 요소입니다

public class MemberProc extends JFrame implements ActionListener {
   JScrollPane scrollPane;
   ImageIcon icon;

   JLabel id, pw, name, tel, dong, hosu, telq, telw;
   JPanel p;
   JTextField tfId, tfName, tfdong, tfhosu;
   JTextField tfTel1, tfTel2, tfTel3; // 전화
   JPasswordField pfPwd; // 비밀번호
   JButton btnInsert, btnCancel, btnUpdate, btnDelete; // 가입, 취소, 수정 , 탈퇴 버튼
   GridBagLayout gb;
   GridBagConstraints gbc;
   Member_List mList;
   Font f = new Font("돋음", Font.BOLD, 25);
   Font f1 = new Font("돋음", Font.BOLD, 17);
   Font f2 = new Font("돋음", Font.PLAIN, 13);
    excute ex;

   public MemberProc() { // 가입용 생성자

      createUI(); // UI작성해주는 메소드
      btnUpdate.setEnabled(false);// 파란 글씨 = 위에서 선언한 것 버튼,텍스트 필드, 패스워드, 아이디
      btnUpdate.setVisible(false);// 버튼 업로드의 사이즈조절을 false 로 지정
      btnDelete.setEnabled(false); 
      btnDelete.setVisible(false);

   }// 생성자

   public MemberProc(Member_List mList) { // 가입용 생성자

      createUI(); // UI작성해주는 메소드
      btnUpdate.setEnabled(false);
      btnUpdate.setVisible(false);
      btnDelete.setEnabled(false);
      btnDelete.setVisible(false);
      this.mList = mList;

   }// 생성자

   public MemberProc(String id, excute ex) { // 수정/삭제용 생성자
      createUI();
      btnInsert.setEnabled(false);
      btnInsert.setVisible(false);
      this.ex = ex;

      System.out.println("id=" + id);

      MemberDAO dao = new MemberDAO();
      MemberDTO vMem = dao.getMemberDTO(id);
      viewData(vMem);

   }// id를 가지고 생성
   public MemberProc(String id,  Member_List mList) { // 관리자전용/수정/삭제용 생성자
      createUI();
      btnInsert.setEnabled(false);
      btnInsert.setVisible(false);
      this.mList = mList;

      System.out.println("id=" + id);

      MemberDAO dao = new MemberDAO();
      MemberDTO vMem = dao.getMemberDTO(id);
      viewData(vMem);

   }

   // MemberDTO 의 회원 정보를 가지고 화면에 셋팅해주는 메소드
   private void viewData(MemberDTO vMem) {

      String id = vMem.getId(); //
      String pwd = vMem.getPwd();
      String name = vMem.getName();
      String tel = vMem.getTel();
      String dong = vMem.getdong();
      String hosu = vMem.gethosu();
    

      // 화면에 세팅
      tfId.setText(id);
      tfId.setEditable(false); // 편집 안되게
      pfPwd.setText(""); // 비밀번호는 안보여준다.
      tfName.setText(name);
      String[] tels = tel.split("-");
      tfTel1.setText(tels[0]);
      tfTel2.setText(tels[1]);
      tfTel3.setText(tels[2]);
      tfdong.setText(dong);
      tfhosu.setText(hosu);
    

   }// viewData

   private void createUI() {
      this.setTitle("회원정보");
      icon = new ImageIcon("images/회원가입 창.png");

      // 배경 Panel 생성후 컨텐츠페인으로 지정
      JPanel background = new JPanel() {
         public void paintComponent(Graphics g) {
         
            g.drawImage(icon.getImage(), 0, 0, null);
      
            setOpaque(false); // 그림을 표시하게 설정,투명하게 조절
            super.paintComponent(g);
         }
      };
      id = new JLabel("아이디 :");
      id.setBounds(20, 30, 150, 37);
      id.setFont(f);
      background.add(id);

      tfId = new JTextField("", 30);
      tfId.setBounds(150, 30, 270, 37);
      background.add(tfId);

      pw = new JLabel("비밀번호 :");
      pw.setBounds(20, 100, 150, 37);
      pw.setFont(f);
      background.add(pw);

      pfPwd = new JPasswordField("", 30);
      pfPwd.setBounds(150, 100, 270, 37);
      background.add(pfPwd);

      name = new JLabel("이름 :");
      name.setBounds(20, 170, 150, 37);
      name.setFont(f);
      background.add(name);

      tfName = new JTextField("", 10);
      tfName.setBounds(150, 170, 270, 37);
      background.add(tfName);

      tel = new JLabel("전화번호 :");
      tel.setBounds(20, 240, 150, 37);
      tel.setFont(f);
      background.add(tel);

      tfTel1 = new JTextField("", 20);
      background.add(tfTel1);

      tfTel1 = new JTextField(6); // tfTel1의 텍스트 필드를 만든다. 크기는 6
      tfTel2 = new JTextField(6); // tfTel2의 텍스트 필드를 만든다. 크기는 6
      tfTel3 = new JTextField(6);
      telq = new JLabel("/");
      telw = new JLabel("/");
      tfTel1.setBounds(150, 240, 70, 37);
      tfTel2.setBounds(250, 240, 70, 37);
      tfTel3.setBounds(350, 240, 70, 37);
      background.add(tfTel1);
      background.add(tfTel2);
      background.add(tfTel3);
      telq.setFont(f);
      telw.setFont(f);
      telq.setBounds(230, 240, 30, 37);
      telw.setBounds(330, 240, 30, 37);
      background.add(telq);
      background.add(telw);

      dong = new JLabel("동 :");
      dong.setBounds(20, 310, 150, 37);
      dong.setFont(f);
      background.add(dong);

      tfdong = new JTextField("", 10);
      tfdong.setBounds(150, 310, 270, 37);
      background.add(tfdong);

      hosu = new JLabel("호수 :");
      hosu.setBounds(20, 380, 150, 37);
      hosu.setFont(f);
      background.add(hosu);

      tfhosu = new JTextField("", 10);
      tfhosu.setBounds(150, 380, 270, 37);
      background.add(tfhosu);

      // 버튼
      JPanel pButton = new JPanel();
      btnInsert = new JButton("가입");
      btnUpdate = new JButton("수정");
      btnDelete = new JButton("탈퇴");
      btnCancel = new JButton("취소");
      pButton.add(btnInsert);
      pButton.add(btnUpdate);
      pButton.add(btnDelete);
      pButton.add(btnCancel);
      pButton.setLayout(new FlowLayout());
      setLayout(new BorderLayout());

      // 버튼에 감지기를 붙이자
      btnInsert.addActionListener(this);
      btnUpdate.addActionListener(this);
      btnCancel.addActionListener(this);
      btnDelete.addActionListener(this);
      pButton.setBounds(160, 475, 130, 35);
      background.add(pButton);
      background.setLayout(null);
      scrollPane = new JScrollPane(background);
      setContentPane(scrollPane);

      setSize(500, 594);
      setVisible(true);

      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

      // 전체 창 1/2 나눈 곳에 위치
      int xPos = screenSize.width / 2 - getSize().width / 2;
      int yPos = screenSize.height / 2 - getSize().height / 2;

      setLocation(xPos, yPos);
      setVisible(true);
      setResizable(false);
      // setResizable(false); // 크기 조정 불가하도록 설정

   }

   // 그리드백레이아웃에 붙이는 메소드
   private void gbAdd(JComponent c, int x, int y, int w, int h) {
      gbc.gridx = x;
      gbc.gridy = y;
      gbc.gridwidth = w;
      gbc.gridheight = h;
      gb.setConstraints(c, gbc);
      gbc.insets = new Insets(2, 2, 2, 2);
      add(c, gbc);
   }// gbAdd

   public static void main(String[] args) {

      new MemberProc();
   }

   @Override
   public void actionPerformed(ActionEvent ae) {
      if (ae.getSource() == btnInsert) {
         insertMember();
         System.out.println("insertMember() 호출 종료");
      } else if (ae.getSource() == btnCancel) {
         this.dispose(); // 창닫기 (현재창만 닫힘)
         // system.exit(0)=> 내가 띄운 모든 창이 다 닫힘
      } else if (ae.getSource() == btnUpdate) {
         UpdateMember();
      } else if (ae.getSource() == btnDelete) {
         // int x = JOptionPane.showConfirmDialog(this,"정말 삭제하시겠습니까?");
         int x = JOptionPane.showConfirmDialog(this, "정말 삭제하시겠습니까?", "삭제", JOptionPane.YES_NO_OPTION);

         if (x == JOptionPane.OK_OPTION) {
            deleteMember();
            
               System.exit(0); 
         } else {
            JOptionPane.showMessageDialog(this, "삭제를 취소하였습니다.");
         }
      }

      // jTable내용 갱신 메소드 호출
      mList.jTableRefresh();

   }// actionPerformed

   private void deleteMember() {
      String id = tfId.getText();
      String pwd = pfPwd.getText();
      if (pwd.length() == 0) { // 길이가 0이면

         JOptionPane.showMessageDialog(this, "비밀번호를 꼭 입력하세요!");
         return; // 메소드 끝
      }
      // System.out.println(mList);
      MemberDAO dao = new MemberDAO();
      boolean ok = dao.deleteMember(id, pwd);

      if (ok) {
         JOptionPane.showMessageDialog(this, "삭제완료");
         dispose();

      } else {
         JOptionPane.showMessageDialog(this, "삭제실패");

      }

   }// deleteMember

   private void UpdateMember() {

      // 1. 화면의 정보를 얻는다.
      MemberDTO dto = getViewData();
      // 2. 그정보로 DB를 수정
      MemberDAO dao = new MemberDAO();
      boolean ok = dao.updateMember(dto);

      if (ok) {
         JOptionPane.showMessageDialog(this, "수정되었습니다.");
         this.dispose();
      } else {
         JOptionPane.showMessageDialog(this, "수정실패: 값을 확인하세요");
      }
   }

   private void insertMember() {

      // 화면에서 사용자가 입력한 내용을 얻는다.
      MemberDTO dto = getViewData();
      MemberDAO dao = new MemberDAO();
      boolean ok = dao.insertMember(dto);

      if (ok) {

         JOptionPane.showMessageDialog(this, "가입이 완료되었습니다.");
         dispose();

      } else {

         JOptionPane.showMessageDialog(this, "가입이 정상적으로 처리되지 않았습니다.");
      }

   }

   public MemberDTO getViewData() {

      // 화면에서 사용자가 입력한 내용을 얻는다.
      MemberDTO dto = new MemberDTO();
      String id = tfId.getText();
      String pwd = pfPwd.getText();
      String name = tfName.getText();
      String tel1 = tfTel1.getText();
      String tel2 = tfTel2.getText();
      String tel3 = tfTel3.getText();
      String tel = tel1 + "-" + tel2 + "-" + tel3;
      String dong = tfdong.getText();
 
      String hosu = tfhosu.getText();
   
    
      dto.setId(id);
      dto.setPwd(pwd);
      dto.setName(name);
      dto.setTel(tel);
      dto.setdong(dong);
      dto.sethosu(hosu);

      return dto;
   }

}// end