package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.UserDao;

public class UserList  extends  JFrame
   implements ActionListener, MouseListener {

   // Component  목록
   JButton             btnInsert,  btnRefresh,  btnToExcel;
   JPanel              topPane;
   JTable              jTable;    // 데이터 표시
   JScrollPane         pane;
   
   DefaultTableModel   dtm;
   
   // Vector : ArrayList  thread safe 버전
   Vector              v;      // data list     
   Vector              cols;   // list 의 제목들
   
   // data  출력
   //  oracle resultset -> vector -> defaultmodel -> jtable
   
   UserProc  uProc = null;
   
   // 생성자 : class 를 new 할때 실행되는 명령들
   public UserList() {
      initComponent();
   }
   
   // initComponent()
   private void initComponent() {
      this.setTitle("회원관리 프로그램 v1.0");
      
      jTable  = new JTable();
      // jTable  에 option 
      jTable.setModel(
         new DefaultTableModel( getDataList(), getColumns() ) {
            public boolean  isCellEditable(int row, int column) {
               // 각 cell 마다 편집가능  : false - 편집기능 해제
               return false;
            }
         }
      );
      pane    =   new JScrollPane( jTable );
      this.add(pane);
      
      topPane     =   new JPanel();
      btnInsert   =   new JButton("회원가입");
      topPane.add( btnInsert );
      btnRefresh  =   new JButton("새로고침");
      topPane.add(btnRefresh);
      btnToExcel  =   new JButton("엑셀로 저장");
      topPane.add(btnToExcel);
      
      this.add( topPane, BorderLayout.NORTH);
      
      // 버튼 이벤트 연결
      this.btnInsert.addActionListener( this );
      this.btnRefresh.addActionListener( this );
      this.btnToExcel.addActionListener( this );
             
      //  jtable 이벤트 연결 - 마우스 
      // jtable 안에 있는 리스트를 클릭하면
      this.jTable.addMouseListener(this);
      
      
      this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      this.setSize(600, 500);
      this.setVisible(true);      
   }

   // 테이블에 출력할 자료검색
   private Vector getDataList() {
      
      UserDao   uDao = new UserDao();
      Vector    v    = uDao.getUserList();
      System.out.println(v);
      
      return  v;
   }

   // header 지정 - 제목
   private Vector getColumns() {
      Vector  cols = new Vector();
      cols.add("아이디");
      cols.add("이름");
      cols.add("직업");
      cols.add("성별");
      cols.add("가입일");
      return  cols;
   }

   // main ()
   public static void main(String[] args) {
      new UserList();
   }   
   
   
   // 이벤트 목록들
   //  버튼 클릭
   @Override
   public void actionPerformed(ActionEvent e) {
      switch( e.getActionCommand() ) {
      case  "회원가입":
         new  UserProc();
         break;
      case  "새로고침":
         jTableRefresh();
         break;
      case  "엑셀로 저장": 
         break;
      }
      
   }

   // jTableRefresh - 데이터 새로고침
   private void jTableRefresh() {
      
      DefaultTableModel  dataModel = initTable();
      jTable.setModel( dataModel );
      jTable.repaint();    // swing component : ui 요소를 새로그린다.
      
   }

   // initTable()
   private DefaultTableModel initTable() {
      // 제목줄 처리 : cols
      cols  =  getColumns();
      // 데이터 처리 : ㅍ
      v     =  getDataList();
      DefaultTableModel  dtm = new DefaultTableModel( v, cols );
      return  dtm;
   }

   
   
   @Override
   public void mouseClicked(MouseEvent e) {
      int       r      =  jTable.getSelectedRow();
      int       c      =  jTable.getSelectedColumn();
      String    id     =  (String) jTable.getValueAt(r,  0);  // 클릭한 userid
      if( uProc == null )
         uProc  = new UserProc();
      
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