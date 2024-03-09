package test;

import javax.imageio.ImageIO; //ImageReader 및 ImageWriter 를 찾고 간단한 인코딩 및 디코딩을 수행하기위한 정적 편의 메소드가 포함 된 클래스입니다. 

import java.io.BufferedReader;//문자 입력 스트림에서 텍스트를 읽고 문자를 버퍼링하여 문자, 배열 및 행을 효율적으로 읽을 수 있도록합니다. 
import java.io.File;//File 클래스는 파일 또는 디렉토리 경로 이름의 Java 표현입니다.
import java.io.FileNotFoundException;//지정된 경로 이름으로 표시된 파일을 열려는 시도가 실패했음을 나타냅니다. 
import java.io.FileReader;// InputStreamReader를 확장합니다 
import java.io.FileWriter;// OutputStreamWriter를 확장 
import java.io.IOException;
import javax.swing.*;// 자바에서 컴포넌트를 작성시 화면이 일관되게 나오는 GUI이다.
import javax.swing.table.AbstractTableModel;//이 추상 클래스는 TableModel 인터페이스의 대부분의 메소드에 대한 기본 구현을 제공합니다.
import javax.swing.table.DefaultTableModel;//이것은 Vector of Vectors 를 사용하여 셀 값 객체를 저장하는 TableModel 의 구현입니다. 
import javax.swing.table.TableModel;//TableModel 인터페이스는 JTable 이 테이블 형식 데이터 모델을 조사하는 데 사용할 메소드를 지정합니다. 
import java.awt.BorderLayout;//테두리 레이아웃은 컨테이너를 배치하여 북쪽, 남쪽, 동쪽, 서쪽 및 중심의 5 개 지역에 맞게 구성 요소를 배열하고 크기를 조정합니다.
import java.awt.Image;//헬퍼 클래스는 파일과 스트림에서 이미지를 읽고 쓰는 메소드를 제공합니다. 
import javax.swing.ImageIcon;// 이미지에서 아이콘을 그리는 Icon 인터페이스의 구현입니다
import javax.swing.JFrame;//메인 프레임
import javax.swing.JLabel;// 짧은 텍스트 문자열 또는 이미지 또는 둘 다의 표시 영역입니다.
import javax.swing.SwingConstants;
import java.awt.*;
import java.awt.event.MouseAdapter;//마우스 이벤트를 받기위한 추상 어댑터 클래스  이 클래스의 메소드는 비어 있습니다
import java.awt.event.MouseEvent;// 구성 요소에서 마우스 동작이 발생했음을 나타내는 이벤트입니다
import java.net.URL;// 포인터 인 Uniform Resource Locator를 나타냅니다.

import java.util.HashMap;//  Java의 Map 인터페이스의 기본 구현을 제공합니다

import java.util.Map;//  키를 값에 매핑하는 인터페이스입니다.  

public class Tabplus extends JFrame {
   JPanel freetable = new JPanel();
   JPanel helptable = new JPanel();
   JPanel admintable = new JPanel(); 

   public Tabplus() {

      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // dipose는 현재 프레임만 종료, exit는 활성화 되어 있는 전체 프레임 종료

      JTabbedPane newtab = new JTabbedPane();

      freetable();
      helptable();
      admintable();
      newtab.addTab("자유게시판", freetable);
      newtab.addTab("도움게시판", helptable);
      newtab.addTab("공지게시판", admintable);
      setTitle("게시판");

      setLayout(new BorderLayout());
      add(newtab, BorderLayout.NORTH);

      JFrame win = new JFrame();
      win.add(newtab);
      win.setSize(570, 765);
      win.setVisible(true);
      win.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // dipose는 현재 프레임만 종료, exit는 활성화 되어 있는 전체 프레임 종료
       win.setLocationRelativeTo(null);
       
   }

   public void freetable() {
      freetable = new freetable();
   }

   public void helptable() {
      helptable = new helptable();
   }

   public void admintable() {
      admintable = new admintable();
   }

   public static void main(String args[])

   {
      new Tabplus();
   }
}