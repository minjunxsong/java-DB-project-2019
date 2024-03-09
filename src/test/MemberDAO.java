package test;

//이름 규칙 : 테이블명DAO , 테이블명DTO
//CRUD : Create;insert , Read;Select, Update, delete
import java.sql.Connection; //데이터베이스와 연결하는 객체입니다
import java.sql.DriverManager;  // 연결할 JDBC 드라이버를 DriverManager 지정하십시오
import java.sql.PreparedStatement;  //  사전 컴파일 된 SQL 문을 나타내는 오브젝트입니다.
import java.sql.ResultSet;   //테이블에서 SQL 문을 실행하려면 Statement 오브젝트를 설정하십시오.따라서이 가져 오기 행을 코드 맨 위에 추가하십시오. 
import java.sql.SQLException; // DB연동
import java.util.Vector;    // Vector 클래스는 확장 가능한 객체 배열을 구현합니다

import javax.swing.table.DefaultTableModel;
//DefaultTableModel을 사용하지 않고 테이블을 만들게되면 한 번만든 테이블의 데이터는 변경을 할 수 없다.

//DB 처리
public class MemberDAO {
   //데이터 베이스에 접속해서데이터 추가, 삭제, 수정 등의 작업을 하는 클래스 입니다

  private static final String DRIVER
      = "org.postgresql.Driver";
  private static final String URL
      = "jdbc:postgresql://localhost:5432/postgres";
 
  private static final String USER = "postgres"; //DB ID
  private static final String PASS = "1234"; //DB 패스워드
  Member_List mList;
 
  public MemberDAO() {
 
  }
 
  public MemberDAO(Member_List mList){   // MEM 리스트 값을 가져옴
      this.mList = mList;
      System.out.println("DAO=>"+mList);
  }
 
  /**DB연결 메소드*/
  public Connection getConn(){
      Connection con = null;
     
      try {
          Class.forName(DRIVER); //1. 드라이버 로딩
          con = DriverManager.getConnection(URL,USER,PASS); //2. 드라이버 연결
         
      } catch (Exception e) {
          e.printStackTrace();
      }
     
      return con;
  }
 
 
  /**한사람의 회원 정보를 얻는 메소드*/
  public MemberDTO getMemberDTO(String id){
     
      MemberDTO dto = new MemberDTO();
     
      Connection con = null;       //연결
      PreparedStatement ps = null; //명령
      ResultSet rs = null;         //결과
     
      try {
         
          con = getConn();
          String sql = "select * from tb_member where id=?";
          ps = con.prepareStatement(sql);
          ps.setString(1, id);
         
          rs = ps.executeQuery();
         
          if(rs.next()){                     //DB에 저장되어 있는 각자의 컬럼을 불러온다.
              dto.setId(rs.getString("id"));
              dto.setPwd(rs.getString("Pwd"));
              dto.setName(rs.getString("Name"));
              dto.setTel(rs.getString("tel"));
              dto.setdong(rs.getString("dong"));
              dto.sethosu(rs.getString("hosu"));
            
             
          }
      } catch (Exception e) {
          e.printStackTrace();
      }      
     
      return dto;    
  }
 
  /**멤버리스트 출력*/
  public Vector getMemberList(){
     
      Vector data = new Vector();  //Jtable에 값을 쉽게 넣는 방법 1. 2차원배열   2. Vector 에 vector추가
     
         
      Connection con = null;       //연결
      PreparedStatement ps = null; //명령
      ResultSet rs = null;         //결과
     
      try{
         
          con = getConn();
          String sql = "select * from tb_member order by name asc";
          ps = con.prepareStatement(sql);
          rs = ps.executeQuery();
         
          while(rs.next()){
              String id = rs.getString("id");
              String pwd = rs.getString("pwd");
              String name = rs.getString("name");
              String tel = rs.getString("tel");
              String dong = rs.getString("dong");
       
              String hosu = rs.getString("hosu");
             
              Vector row = new Vector();
              row.add(id);
              row.add(pwd);
              row.add(name);
              row.add(tel);
              row.add(dong);
              //row.add(birth);
              /*row.add(job);
              row.add(gender);*/
              row.add(hosu);
             //row.add(intro);
             
              data.add(row);             
          }//while
      }catch(Exception e){
          e.printStackTrace();
      }
      return data;
  }//getMemberList()
 


  /**회원 등록*/
  public boolean insertMember(MemberDTO dto){
     
      boolean ok = false;
     
      Connection con = null;       //연결
      PreparedStatement ps = null; //명령
     
      try{
         
          con = getConn();
          String sql = "insert into tb_member(" + "id,pwd,name,tel,dong,hosu)" + "values(?,?,?,?,?,?)";
                     
                 
         
          ps = con.prepareStatement(sql);
          ps.setString(1, dto.getId());
          ps.setString(2, dto.getPwd());
          ps.setString(3, dto.getName());
          ps.setString(4, dto.getTel());
          ps.setString(5, dto.getdong());
          ps.setString(6, dto.gethosu());
      
          int r = ps.executeUpdate(); //실행 -> 저장
         
         
          if(r>0){
              System.out.println("가입 성공");   
              ok=true;
          }else{
              System.out.println("가입 실패");
          }
         
             
         
      }catch(Exception e){
          e.printStackTrace();
      }
     
      return ok;
  }//insertMmeber
 
 
  /**회원정보 수정*/
  public boolean updateMember(MemberDTO vMem){
      System.out.println("dto="+vMem.toString());
      boolean ok = false;
      Connection con = null;
      PreparedStatement ps = null;
      try{
         
          con = getConn();           
          String sql = "update tb_member set name=?, tel=?, dong=?, hosu=?" +"where id=? and pwd=?";

         
          ps = con.prepareStatement(sql);
         
          ps.setString(1, vMem.getName());
          ps.setString(2, vMem.getTel());
          ps.setString(3, vMem.getdong());
          ps.setString(4, vMem.gethosu());
          ps.setString(5, vMem.getId());
          ps.setString(6, vMem.getPwd());
         
          int r = ps.executeUpdate(); //실행 -> 수정
          // 1~n: 성공 , 0 : 실패
         
          if(r>0) ok = true; //수정이 성공되면 ok값을 true로 변경
         
      }catch(Exception e){
          e.printStackTrace();
      }
     
      return ok;
  }
 
  /**회원정보 삭제 :
   *tip: 실무에서는 회원정보를 Delete 하지 않고 탈퇴여부만 체크한다.*/
  public boolean deleteMember(String id, String pwd){
     
      boolean ok =false ;
      Connection con =null;
      PreparedStatement ps =null;
     
      try {
          con = getConn();
          String sql = "delete from tb_member where id=? and pwd=?";
         
          ps = con.prepareStatement(sql);
          ps.setString(1, id);
          ps.setString(2, pwd);
          int r = ps.executeUpdate(); // 실행 -> 삭제
         
          if (r>0) ok=true; //삭제됨;
         
      } catch (Exception e) {
          System.out.println(e + "-> 오류발생");
      }      
      return ok;
  }
 
 
  /**DB데이터 다시 불러오기*/   
  public void userSelectAll(DefaultTableModel model) {
     
     
      Connection con = null;
      PreparedStatement ps = null;
      ResultSet rs = null;
     
      try {
          con = getConn();
          String sql = "select * from tb_member order by name asc";
          ps = con.prepareStatement(sql);
          rs = ps.executeQuery();
         
          // DefaultTableModel에 있는 데이터 지우기
          for (int i = 0; i < model.getRowCount();) {
              model.removeRow(0);
          }

          while (rs.next()) {
              Object data[] = { rs.getString(1), rs.getString(2),
                      rs.getString(3), rs.getString(4),
                 
                      rs.getString(5),
                      rs.getString(6)};

              model.addRow(data);                
          }

      } catch (SQLException e) {
          System.out.println(e + "=> userSelectAll fail");
      } finally{
         
          if(rs!=null)
              try {
                  rs.close();
              } catch (SQLException e2) {
                  // TODO Auto-generated catch block
                  e2.printStackTrace();
              }
          if(ps!=null)
              try {
                  ps.close();
              } catch (SQLException e1) {
                  // TODO Auto-generated catch block
                  e1.printStackTrace();
              }
          if(con!=null)
              try {
                  con.close();
              } catch (SQLException e) {
                  // TODO Auto-generated catch block
                  e.printStackTrace();
              }
      }
  }
//로그인 소스 시작

  public int login(String id, String pwd) {
     boolean ok =false ;
     Connection con = null;       //연결
      PreparedStatement ps = null; //명령
       ResultSet rs; //정보를 담을 수 있는 객체
      String SQL = "Select id tb_member where id=? and pwd=?";

      //실제 SQL에서 작동하게 할 명령문 입력

      try {

          ps = con.prepareStatement(SQL);

          ps.setString(1, id);

          //인젝션해킹등을 방지하기 위한 기법 ?에 ID값을 받은 후 사용.

          rs = ps.executeQuery();

          if(rs.next()) {

              if(rs.getString(1).equals(pwd)) {

                  return 1; // 로그인 성공

              } else 

                  return 0; // 비밀번호 불일치

          }

          return -1; //아이디가 없음

      }catch(Exception e) {

          e.printStackTrace();    // 예외처리

      }

      return -2; // 데이터베이스 오류

  }

  

}