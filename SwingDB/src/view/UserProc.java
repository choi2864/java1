package view;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import model.UserDao;
import model.UserVo;

public class UserProc  extends  JFrame
   implements ActionListener, KeyListener   {

   // component 
   JPanel           p;
   JTextField          tfId,   tfName,   tfIndate;
   JPasswordField      pfPwd;
   JComboBox           cbJob;
   JRadioButton        rbMale, rbFemale;
   JTextArea           taIntro;
   JButton             btnInsert, btnDelete, btnUpdate, btnCancel;
   
   GridBagLayout       gb;
   GridBagConstraints  gbc;   // option 
   
   public  UserProc() {
      initComponent();
   }
   
   // initComponent
   private void initComponent() {
      this.setTitle("회원정보(개인)");
      
      // component 배치
      // GridBagLayout : Layout
      gb      =  new GridBagLayout();
      this.setLayout( gb );
      
      gbc          =  new GridBagConstraints();
      gbc.fill     =  GridBagConstraints.BOTH;
      gbc.weightx  = 1.0;
      gbc.weighty  = 1.0;
      
      // 아이디
      JLabel   lblId   =  new JLabel("아이디"); 
      tfId             =  new JTextField(20);
      gbAdd( lblId, 0, 0, 1, 1);
      gbAdd( tfId,  1, 0, 3, 1);
      
      // 암호
      JLabel  lblPwd  =  new  JLabel("암호");
      pfPwd            =  new  JPasswordField( 20 );
      gbAdd( lblPwd, 0, 1, 1, 1);
      gbAdd( pfPwd,  1, 1, 3, 1);
                  
      // 이름
      JLabel  lblName  =  new  JLabel("이름");
      tfName           =  new  JTextField(20);
      gbAdd( lblName, 0, 2, 1, 1);
      gbAdd( tfName,  1, 2, 3, 1);
      
      // 직업
      JLabel  lblJob   =  new  JLabel("직업");
      String  [] jobs  = {" ", "회사원", "학생", "자영업", "무직", "군인"};
      cbJob            =  new  JComboBox(  jobs  );
      gbAdd( lblJob, 0, 3, 1, 1);
      gbAdd( cbJob,  1, 3, 3, 1);
      
      // 성별
      JLabel  lblGender   =  new  JLabel("성별");
      
      this.rbMale         =  new  JRadioButton("남자", false);   
      this.rbFemale       =  new  JRadioButton("여자", false);   
      
      /// 선택 그룹 지정 
      ButtonGroup   group  = new  ButtonGroup();
      group.add( rbMale );
      group.add( rbFemale );
      
      // 패널 사용
      JPanel      pGender  =  new JPanel( new FlowLayout(FlowLayout.LEFT) );
      pGender.add( rbMale );
      pGender.add( rbFemale );
      
      gbAdd( lblGender, 0, 5, 1, 1);
      gbAdd( pGender ,  1, 5, 3, 1);
      
      // 자기소개
      JLabel       lblIntro  =  new  JLabel("자기소개");
      taIntro                =  new  JTextArea(5, 20); // 행 열
      JScrollPane   pane     =  new JScrollPane( taIntro );
      gbAdd( lblIntro, 0, 6, 1, 1);
      gbAdd( pane   ,  1, 6, 3, 1);
      
      // 가입일
      JLabel       lblIndate  =  new  JLabel("가입일");
      this.tfIndate           =  new  JTextField(20);
         
      gbAdd( lblIndate,  0, 7, 1, 1);
      gbAdd( tfIndate,  1, 7, 3, 1);
      
      // 버튼들
      JPanel   pButton  =  new  JPanel();
      btnInsert         =  new  JButton("가입"); 
      btnUpdate         =  new  JButton("수정"); 
      btnDelete         =  new  JButton("삭제"); 
      btnCancel         =  new  JButton("취소");
      
      pButton.add( this.btnInsert );
      pButton.add( this.btnUpdate );
      pButton.add( this.btnDelete );
      pButton.add( this.btnCancel );
      gbAdd(pButton,0, 11, 4, 1);
      
      // 이벤트 연결 - 기능추가\
      this.btnInsert.addActionListener( this );
      this.btnUpdate.addActionListener( this );
      this.btnDelete.addActionListener( this );
      this.btnCancel.addActionListener( this );
      
      // Enter 키 연결
      this.tfId.addKeyListener(this);
      
            
      this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      this.setSize( 350, 700 );
      this.setVisible(true);
   }

   private void gbAdd(JComponent c, int x, int y, int w, int h) {
      gbc.gridx       =  x;  
      gbc.gridy       =  y;
      gbc.gridwidth   =  w; 
      gbc.gridheight  =  h;
      gb.setConstraints(c, gbc);
      gbc.insets      =  new Insets(2, 2, 2, 2);
      this.add(c, gbc);
      
   }
   
   // 버튼 OnOff
   public  void   btnOnOff(boolean sw) {
      
      this.btnInsert.setEnabled( sw );
       this.btnUpdate.setEnabled( !sw );
       this.btnDelete.setEnabled( !sw );
       this.btnCancel.setEnabled( true );
       
   }

   // --------------------------------------
   // main
   public static void main(String[] args) {
      new UserProc();
   }
   
   
   private UserVo getViewData() {
      
      String   userid    =  this.tfId.getText();
      String   passwd    =  this.pfPwd.getText();
      String   username  =  this.tfName.getText();
      String   job       =  (String) this.cbJob.getSelectedItem();
      
      String   gender        =  "";
      if(  rbMale.isSelected()     )   gender = "M";
      if(  rbFemale.isSelected()   )   gender = "F";
      
      String   intro     =  this.taIntro.getText();
      
      UserVo   user    = new  UserVo(userid, passwd, username, job, gender, intro);
      return   user;
   }

   // 추가
   private void insertUser() {
      
      UserDao  uDao      =  new  UserDao();
      UserVo   user      =  getViewData();
      String   result    =  uDao.insertUser( user );
      JOptionPane.showMessageDialog(null, 
            result,
            "추가",
            JOptionPane.OK_OPTION
            );      
   }
   
   // 수정
   private void updateUser() {

      UserDao  uDao   =  new  UserDao();
      UserVo   user   =  getViewData();
      String   result = uDao.updateUser( user );
      JOptionPane.showMessageDialog(null, 
            result,
            "수정",
            JOptionPane.OK_OPTION
            );      
   }


   // 삭제
   private void deleteUser() {
      // TODO Auto-generated method stub
      
   }
   
   // 조회된 UserVo  의 data를  JTextField 등에 출력
   private void setViewData(UserVo user) {
      String   userid    =  user.getUserid();
      String   passwd    =  user.getUserid();
      String   username  =  user.getUsername();            
      String   job       =  user.getJob();
      String   gender    =  user.getGender();
      String   intro     =  user.getIntro();
      String   indate    =  user.getIndate();
      
      this.tfId.setText( userid );
      this.pfPwd.setText( passwd );
      this.tfName.setText( username );
       
      this.cbJob.setSelectedItem(job);      
      
       if(  gender.equals("M")  )
         this.rbMale.setSelected(true);
      if(  gender.equals( "F")  )
         this.rbFemale.setSelected(true);
      
      this.taIntro.setText( intro );
      this.tfIndate.setText( indate );
      
   }
   
   // 조회실패시 화면 초기화 
   private void clearViewData() {
      this.tfId.setText("");
      this.pfPwd.setText("");
      this.tfName.setText("");
      this.cbJob.setSelectedIndex( 0 );
      this.rbMale.setSelected(false);
      this.rbFemale.setSelected(false);
      this.taIntro.setText("");
      this.tfIndate.setText("");
   }
   

   //----------------------------------------------------
   // 이벤트 처리
   @Override
   public void actionPerformed(ActionEvent e) {
      switch( e.getActionCommand()  ) {
      case  "가입":  
         insertUser();
         clearViewData();
         btnOnOff(true);         
         break;
      case  "수정":  
         updateUser();
         clearViewData();
         btnOnOff(true);      
         break;
      case  "삭제":  
         deleteUser();
         clearViewData();
         btnOnOff(true);
         break;
      case  "취소":    // 창닫기
         this.dispose();  // 현재 창닫기
         break;
         
      }
      
   }

   @Override
   public void keyTyped(KeyEvent e) {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void keyPressed(KeyEvent e) {
      // TODO Auto-generated method stub
      
   }

   // Enter 키를 눌렀다 땔때
   @Override
   public void keyReleased(KeyEvent e) {
      if ( e.getKeyCode() == KeyEvent.VK_ENTER )  {
         System.out.println("검색실행");
         UserDao uDao   = new UserDao(); 
          String  userid = this.tfId.getText();
          UserVo  user   = uDao.getUser(userid);
          if ( user != null ) {
             setViewData( user );
             
             btnOnOff( false );
             
             
          } else {
             JOptionPane.showMessageDialog(null, 
                  "조회된 자료가 없습니다",
                  "자료없습",
                  JOptionPane.OK_OPTION
                  );
             // 화면 초기화
             clearViewData();
          }
      }
      
   }


   

}







