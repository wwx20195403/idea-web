package mainframe.frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controllers.EquipmentController;
import controllers.UserController;
import entity.Equipment;
import entity.Factory;
import entity.User;
import mainframe.SyperAdmin;
import mainframe.dialog.EditUserDialog;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class UserManageFrame extends JFrame {
	 private static UserManageFrame instance;
	 public static UserManageFrame getInstance() {  
    if (instance == null) {  
        instance = new UserManageFrame();  
    }  
    return instance;  
    } 
	private String[] tableHead=new String[] {"序号","登录账号","姓名","联系方式","类型"};
	private DefaultTableModel usermodel;
	private JTable users=new JTable(usermodel){
    public boolean isCellEditable(int rowIndex, int ColIndex){
		     return false;
			}
    } ; 
	private JPanel contentPane;
	private JScrollPane userScroll;
	private List<User> userList=null;
	private UserController userController=new UserController("UserService");
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JToolBar toolBar ;
	private JButton btnNewButton_2;
	private JButton btnNewButton_3;
	private JTextField textField;
	private JButton btnNewButton_4;
	private EquipmentController equipmentController=new EquipmentController("EquipmentService");
	private JButton  btnNewButton_5;

	/** 
	 * Create the frame.
	 */
	private UserManageFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		//表格设置
		userScroll=new JScrollPane(users);
		users.setFillsViewportHeight(true);
		users.setRowSelectionAllowed(true);
		contentPane.add(userScroll,BorderLayout.CENTER);
		users.setRowHeight(30);
		users.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//
		updateUserList();
		toolBar = new JToolBar();
		contentPane.add(toolBar,BorderLayout.PAGE_START);
		toolBar.setFloatable(false);
		btnNewButton = new JButton("新建");
		btnNewButton_1 =new JButton("删除");	
		btnNewButton_2=new JButton("修改");
		btnNewButton_3=new JButton("检索");

		btnNewButton_5=new JButton("刷新");
		
		
		
		textField=new JTextField();
		textField.setToolTipText("请输入账号");
		toolBar.add(btnNewButton);
		toolBar.add(btnNewButton_1);
		toolBar.add(btnNewButton_2);
		toolBar.add(btnNewButton_5);
		toolBar.add(textField);
		toolBar.add(btnNewButton_3);
		
		btnNewButton_4 =createToolButton("返回超级管理员界面", "back.png");
		btnNewButton_4.setForeground(Color.white);
		btnNewButton_4.setBackground(new Color(0,130,228));
		
		
		contentPane.add(btnNewButton_4, BorderLayout.SOUTH);
		btnNewButton.addActionListener( (e)->{
			EditUserDialog a=new EditUserDialog(UserManageFrame.getInstance(),userController,null);
			a.addWindowListener(new WindowAdapter(){
				@Override
				public void windowClosed(WindowEvent e) {
					// TODO Auto-generated method stub
					updateUserList();
				}
			});
		}); 
		btnNewButton_1.addActionListener( (e)->{

			User u=getChange();
			if(u==null) {
				
			}else {
			if(u.getType().equals("经销商")) {
				onDelete();
				updateUserList();
			}else {
				if(isProducting(u.getId())) {
					if(isRenting(u.getId())) {
						onDelete();
						updateUserList();
					}else {
						JOptionPane.showMessageDialog(null, "正在租借设备,无法删除");
					}
				}else {
					JOptionPane.showMessageDialog(null, "生产中,无法删除");
				}
			}
			}
			
		}); 
		btnNewButton_2.addActionListener( (e)->{
			User u=getChange();
			if(u==null)JOptionPane.showMessageDialog(null, "无选择值！");
			else{
			EditUserDialog a=new EditUserDialog(UserManageFrame.getInstance(),userController,u);
			a.addWindowListener(new WindowAdapter(){
				@Override
				public void windowClosed(WindowEvent e) {
					// TODO Auto-generated method stub
					updateUserList();
				}
			});}
		}); 
		btnNewButton_3.addActionListener(e->{
			String User_Id=textField.getText();
			int m=0;
			for( int i=0;i<userList.size();i++) {
				if(userList.get(i).getIsAvailable().equals("true")) {
					if(userList.get(i).getId().equals(User_Id)) {
							 users.setRowSelectionInterval(m, m);
							users.scrollRectToVisible(users.getCellRect(m, 0, true));
							users.setSelectionBackground(Color.LIGHT_GRAY);//选中行设置背景色								

					}
					m++;
				}
			}
		});
		btnNewButton_5.addActionListener(e->{
			updateUserList();
		});
		btnNewButton_4.addActionListener(e->{
	
					// TODO Auto-generated method stub
					SyperAdmin a=SyperAdmin.getInstance();
					a.setVisible(true);
					dispose();
				
		});

		
	}

	public void updateUserList() {
		users.setModel(getDefaultTableModel());
	}
	public DefaultTableModel getDefaultTableModel() {
		try {
			userList=userController.showUser();
			usermodel  =new DefaultTableModel(null,tableHead);

			int i=1;
			for(User user1 : userList) {
				if(user1.getIsAvailable().equals("true")){
				Vector<Object> rowData=new Vector<>();
				rowData.add(i);
				rowData.add(user1.getId());
				rowData.add(user1.getName());
				rowData.add(user1.getPhone());
				rowData.add(user1.getType());
				usermodel.addRow(rowData);
				i++; }
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return usermodel;
	}
	// 点 '删除' 按钮
	private void onDelete()
	{
		// 获取选中的行的索引
		int[] rows = users.getSelectedRows();
		if(rows.length == 0)return;
				
		// 弹出对话框确认
		int select = JOptionPane.showConfirmDialog(this, "是否确认删除?", "确认", JOptionPane.YES_NO_OPTION);
		if(select != 0) return; // 0号按钮是'确定'按钮

		// 技巧：从后往前删除
		for(int i= rows.length-1; i>=0; i--)
		{
			String s=(String)usermodel.getValueAt(rows[i], 1);
			try {
				if(userController.deleteUser(s)){
					JOptionPane.showMessageDialog(this, "删除成功！");
					}else{
					JOptionPane.showMessageDialog(this, "删除失败！");
					}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	private User getChange(){
		int[] rows = users.getSelectedRows();
		for(int i= rows.length-1; i>=0; i--)
		{
			String s=(String)usermodel.getValueAt(rows[i], 1);
			try {
				User u=userController.searchUser(s);
				return u;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		}
	return null;	
	}
	
	private JButton createToolButton(String text, String icon)
	{
		// 图标
		String imagePath = "/images/" + icon;
		URL imageURL = getClass().getResource(imagePath);
		// 创建按钮
		JButton button = new JButton(text);
		button.setToolTipText(text);
		button.setIcon(new ImageIcon(imageURL));
		button.setFocusPainted(false);
		return button;
	}
	public boolean  isProducting(String userID) {
		try {
			List<Equipment> list=equipmentController.showEquipment();
			for(Equipment eq:list) {
				if(eq.getIsAvailable().equals("true")) {
					if(eq.getNowBelong().equals(userID)) {
						if(eq.getEquiomentState().equals("生产中")) {
							return false;
						}
					}
				}
			}
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}
	public boolean  isRenting(String userID){
		try {
			List<Equipment> list=equipmentController.showEquipment();
			for(Equipment eq:list) {
				if(eq.getIsAvailable().equals("true")) {
					if(eq.getNowBelong().equals(userID)) {
						if(eq.getBelong().equals("0")) {
							return false;
						}
					}
				}
			}
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	

}
