package test;

import java.awt.BorderLayout;  //각 영역 사이에 간격이 있는 BorderLayout을 생성한다.
import java.awt.Dimension;     //  이 Dimension 객체의 크기를 가져옵니다.
import java.awt.Toolkit;       //   기본 툴킷을 가져옵니다. 
import java.awt.event.ActionEvent;  //   객체는 이벤트가 발생할 때이 ActionEvent 가져옵니다
import java.awt.event.ActionListener;  //  액션이벤트 받기위한 리스너 클래스 
import java.awt.event.MouseEvent;    //   구성 요소에서 마우스 동작이 발생했음을 나타내는 이벤트입니다
import java.awt.event.MouseListener;   // 마우스 이동 및 마우스 드래그를 추적하려면 MouseMotionListener 사용하십시오
import java.util.Vector;        //    Vector 클래스는 확장 가능한 객체 배열을 구현합니다.

import javax.swing.JButton;      //  설정된 텍스트 나 아이콘이없는 버튼을 만듭니다
import javax.swing.JFrame;       //   메인 프레임
import javax.swing.JPanel;       //   보조 프레임
import javax.swing.JScrollPane;  //    접근 가능한 ScrollPaneConstants 구현
import javax.swing.JTable;       //    빈 셀로 테이블이 생성됩니다   
import javax.swing.table.DefaultTableModel;  //이것은 Vector of Vectors 를 사용하여 셀 값 객체를 저장하는 TableModel 의 구현입니다. 
 
 
public class Member_List extends JFrame implements MouseListener,ActionListener{
   
    Vector v;    // v라는 변수 이름
    Vector cols;  // cols 변수 이름
    DefaultTableModel model; // 객체를 저장하는 TableModel 의 구현입니다. 
    JTable jTable;   // 표형식의 데이터
    JScrollPane pane;   //  경량 구성 요소의 스크롤 가능보기를 제공합니다
    JPanel pbtn;    // J패널 생성
    JButton btnInsert;   //  J버튼 생성
       
   
    public Member_List(){
       // 회원 상세정보 조회 구현
        super("주민 회원가입");
        //v=getMemberList();
        //MemberDAO
        MemberDAO dao = new MemberDAO();
        // 간단한 회원 인증 (DB를 연동한)
        v = dao.getMemberList();
        // Member.DAO클래스에 있는 getMemberList 값을 변수 v에 저장
        System.out.println("v="+v);
        cols = getColumn();
        //getColumns 메서드에 의해 지정
       
      
        model = new DefaultTableModel(v, cols);
      
        jTable = new JTable(model);
        pane = new JScrollPane(jTable);
        add(pane);
       
        pbtn = new JPanel();   
        btnInsert = new JButton("회원가입");   //버튼에 회원가입 등록
        pbtn.add(btnInsert);
        add(pbtn,BorderLayout.NORTH);
       
       
        jTable.addMouseListener(this); //리스너 등록
        btnInsert.addActionListener(this); //회원가입버튼 리스너 등록
       
        setSize(600,200);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        
        //전체 창 1/2 나눈 곳에 위치
        int xPos = screenSize.width/2 - getSize().width/2 ;
        int yPos = screenSize.height/2 - getSize().height/2 ;
           
        setLocation(xPos,yPos);      
        setVisible(true);
        //setResizable(false); // 크기 조정 불가하도록 설정 
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }//end 생성자
   
   
    //JTable의 컬럼
    public Vector getColumn(){
        Vector col = new Vector(); 
        // 백터 col에 그 값들을 추가
        col.add("아이디"); 
        col.add("비밀번호"); 
        col.add("이름");
        col.add("전화");
        col.add("동");
        col.add("호수");
        /*col.add("직업");
        col.add("성별");
        col.add("이메일");
        col.add("자기소개");*/
       
        return col;
    }//getColumn
   
   
    //Jtable 내용 갱신 메서드
    public void jTableRefresh(){
       
        MemberDAO dao = new MemberDAO();
        DefaultTableModel model= new DefaultTableModel(dao.getMemberList(), getColumn());
        jTable.setModel(model);    
       
    }
   
    public static void main(String[] args) {
     
        Member_List ML = new Member_List();
        ML.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }//main
    @Override
    public void mouseClicked(MouseEvent e) {
        // mouseClicked 만 사용
        int r = jTable.getSelectedRow();
        String id = (String) jTable.getValueAt(r, 0);
        //System.out.println("id="+id);
        MemberProc mem = new MemberProc(id,this); //아이디를 인자로 수정창 생성
               
    }
    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
       
    }
    @Override
    public void mouseExited(MouseEvent e) {
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
    public void actionPerformed(ActionEvent e) {
        //버튼을 클릭하면
        if(e.getSource() == btnInsert ){
            new MemberProc(this);
           
            /*테스트*/
            //dao = new MemberDAO();           
            //dao.userSelectAll(model);
            //model.fireTableDataChanged();
            //jTable.updateUI();           
            //jTable.requestFocusInWindow();
           
        }
       
    }
}