 package mainframe.frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
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
import controllers.FactoryControllers;
import controllers.UserController;
import entity.Equipment;
import entity.Factory;
import mainframe.SyperAdmin;



public class FactoryManageFrame extends JFrame {

	 private static FactoryManageFrame instance;
	 public static FactoryManageFrame getInstance() {  
  if (instance == null) {  
      instance = new FactoryManageFrame();  
  }  
  return instance; 
  } 
	 
	private String[] tableHead=new String[] {"序号","工厂名称","工厂简介","负责人","联系方式","登录账号","工厂状态"};
	private DefaultTableModel factorymodel;
	private JTable factorys=new JTable(factorymodel){
		public boolean isCellEditable(int rowIndex, int ColIndex){
		     return false;
			}
  }; 
	
   
   	private JPanel contentPane;
	private JScrollPane factoryScroll;
	private List<Factory> factoryList=null;
	private EquipmentController equipmentController=new EquipmentController("EquipmentService");
	private FactoryControllers factoryController=new FactoryControllers("FactoryService");
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JButton btnNewButton_3;
	private JToolBar toolBar ;
	private JTextField textField;
	private JButton btnNewButton_4;

	 

	/**
	 * Create the frame.
	 */
	public FactoryManageFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 784, 386);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		//表格设置
		factoryScroll=new JScrollPane(factorys);
		factorys.setFillsViewportHeight(true);
		factorys.setRowSelectionAllowed(true);
		contentPane.add(factoryScroll,BorderLayout.CENTER);
		factorys.setRowHeight(30);
		factorys.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//
		toolBar = new JToolBar();
		contentPane.add(toolBar,BorderLayout.PAGE_START);
		toolBar.setFloatable(false);
		btnNewButton = new JButton("检索");		
		btnNewButton_1 = new JButton("开启");
		btnNewButton_2=new JButton("关停");
		btnNewButton_3=new JButton("刷新");
		textField=new JTextField();
		textField.setToolTipText("请输入账号");
	
		toolBar.add(textField);
		toolBar.add(btnNewButton);
		toolBar.add(btnNewButton_1);
		toolBar.add(btnNewButton_2);
		toolBar.add(btnNewButton_3);

		btnNewButton_4 =createToolButton("返回超级管理员界面", "back.png");
		btnNewButton_4.setForeground(Color.white);
		btnNewButton_4.setBackground(new Color(0,130,228));
		contentPane.add(btnNewButton_4, BorderLayout.SOUTH);
		updateFactoryList();
		btnNewButton_3.addActionListener(e->{
			updateFactoryList();
		});
		btnNewButton_1.addActionListener(e->{
			int[] rows = factorys.getSelectedRows();
			String s=(String)factorymodel.getValueAt(rows[0], 6);
			String s1=(String)factorymodel.getValueAt(rows[0], 5);
			if(s.equals("正常")) {
				JOptionPane.showMessageDialog(null,"工厂已经处于开启状态！");
			}else {
				try {
					if(factoryController.changeFactorystate(s1, "true")) {
						JOptionPane.showMessageDialog(null,"开启成功");
						updateFactoryList();
					}else {
						JOptionPane.showMessageDialog(null,"开启失败");
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_2.addActionListener(e->{
			int[] rows = factorys.getSelectedRows();
			String s=(String)factorymodel.getValueAt(rows[0], 6);
			String s1=(String)factorymodel.getValueAt(rows[0], 5);
			if(s.equals("关停")) {
				JOptionPane.showMessageDialog(null,"工厂已经处于关停状态！");
			}else {
				if(isProducting(s1)) {
					try {
						if(factoryController.changeFactorystate(s1, "false")) {
							JOptionPane.showMessageDialog(null,"关停成功");
							updateFactoryList();
						}else {
							JOptionPane.showMessageDialog(null,"关停失败");
						}
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else {
					JOptionPane.showMessageDialog(null,"设备生产中,无法关停");
				
				}

			}
		});
		
		
		btnNewButton.addActionListener(e->{
			String User_Id=textField.getText();
			int m=0;
			for( int i=0;i<factoryList.size();i++) {
				if(factoryList.get(i).getIsAvailable().equals("true")) {
					if(factoryList.get(i).getUserId().equals(User_Id)) {
						factorys.setRowSelectionInterval(m, m);
						factorys.scrollRectToVisible(factorys.getCellRect(m, 0, true));
						factorys.setSelectionBackground(Color.LIGHT_GRAY);//选中行设置背景色								

					}
					m++;
				}
			}
		});
		
		btnNewButton_4.addActionListener(e->{
			
			// TODO Auto-generated method stub
			SyperAdmin a=SyperAdmin.getInstance();
			a.setVisible(true);
			dispose();
		
		});
		setVisible(true);
	}
	
	
	public void updateFactoryList() {
		// TODO Auto-generated method stub
		factorys.setModel(getDefaultTableModel());
	}
	
	public DefaultTableModel getDefaultTableModel() {
		try {
			factorymodel=new DefaultTableModel(null, tableHead);
			factoryList=factoryController.showFactory();
			int i=1;
			for(Factory fa : factoryList) {
				if(fa.getIsAvailable().equals("true")){
				Vector<Object> rowData=new Vector<>();
				rowData.add(i);
				rowData.add(fa.getName());
				rowData.add(fa.getIntroduction());
				rowData.add(fa.getUsername());
				rowData.add((new UserController("UserService")).searchUser(fa.getUserId()).getPhone());
				rowData.add(fa.getUserId());
				rowData.add(fa.getFctorystate());
				factorymodel.addRow(rowData);
				i++; }
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return factorymodel;
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
	

	}
