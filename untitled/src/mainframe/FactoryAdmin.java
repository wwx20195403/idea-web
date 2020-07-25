package mainframe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import mainframe.frames.EquipmentManageFrame_Fa;
import mainframe.frames.FactoryOrderFrame;
import mainframe.frames.FactorySchedFrame;
import utils.SmallTool;

import javax.swing.JLabel;
import java.awt.Font;
import java.net.URL;

public class FactoryAdmin extends JFrame {
	public static String userID;
	public static void setUserID(String a) {
			userID=a;
	}
	 private static FactoryAdmin instance;
	 public static FactoryAdmin getInstance() {  
		 if (instance == null) {  
			 instance = new FactoryAdmin();  
		 }  
		 return instance;  
   } 
	private JPanel contentPane;
	private  JLabel lblNewLabel;

	/**
	 * Create the frame.
	 */
	public FactoryAdmin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 557, 363);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNewLabel = new JLabel("工厂管理");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 16));
		lblNewLabel.setBounds(247, 10, 80, 39);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("工厂管理：");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(30, 69, 117, 45);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("订单管理：");
		lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(28, 170, 86, 27);
		contentPane.add(lblNewLabel_2);
		
		JButton btnNewButton_5 =createToolButton("设备管理","equipment.png");
		btnNewButton_5.setFont(new Font("宋体", Font.PLAIN, 18));
		btnNewButton_5.setForeground(Color.WHITE);
		btnNewButton_5.setBackground(new Color(0,130,228));
		btnNewButton_5.setBounds(104, 74, 193, 41);
		contentPane.add(btnNewButton_5);

		JButton btnNewButton_6 =createToolButton("订单接单","ordercharge.png");
		btnNewButton_6.setFont(new Font("宋体", Font.PLAIN, 18));
		btnNewButton_6.setForeground(Color.WHITE);
		btnNewButton_6.setBackground(new Color(0,130,228));
		btnNewButton_6.setBounds(101, 164, 193, 41);
		contentPane.add(btnNewButton_6);
		
		JButton btnNewButton_9 =createToolButton("订单排产","ordercharge.png");
		btnNewButton_9.setFont(new Font("宋体", Font.PLAIN, 18));
		btnNewButton_9.setForeground(Color.WHITE);
		btnNewButton_9.setBackground(new Color(0,130,228));
		btnNewButton_9.setBounds(326, 164, 193, 41);
		contentPane.add(btnNewButton_9);
		
		
		JButton btnNewButton_7 =createToolButton("返回登录","back.png");
		btnNewButton_7.setFont(new Font("宋体", Font.PLAIN, 18));
		btnNewButton_7.setForeground(Color.WHITE);
		btnNewButton_7.setBackground(new Color(0,130,228));
		btnNewButton_7.setBounds(252, 275, 130, 41);
		contentPane.add(btnNewButton_7);
		
		JButton btnNewButton_8 =createToolButton("退出程序","out.png");
		btnNewButton_8.setFont(new Font("宋体", Font.PLAIN, 18));
		btnNewButton_8.setForeground(Color.white);
		btnNewButton_8.setBackground(new Color(0,130,228));
		btnNewButton_8.setBounds(400, 275, 130, 41);
		contentPane.add(btnNewButton_8);
		
		btnNewButton_5.addActionListener(e->{
					EquipmentManageFrame_Fa.setUserID(userID);
					EquipmentManageFrame_Fa a=EquipmentManageFrame_Fa.getInstance();
					a.updateequipmentList();
					a.setVisible(true);
					dispose();			
		});
		
		btnNewButton_9.addActionListener(e->{
			FactorySchedFrame.setUserID(userID);
			FactorySchedFrame a=FactorySchedFrame.getInstance();
			a.setVisible(true);
			a.updateorderList();
			dispose();
		});
		
		 btnNewButton_6.addActionListener(e->{
			 FactoryOrderFrame.setUserID(userID);
			 FactoryOrderFrame a=FactoryOrderFrame.getInstance();
			 a.updateorderList();
			 a.setVisible(true);
			 dispose();
		 });
		btnNewButton_7.addActionListener(e->{
			Login a=Login.getInstance();
			a.setVisible(true);
			dispose();
		});
		
		
		
		
		
		btnNewButton_8.addActionListener(e->{
			System.exit(0);
		});
		
	}
	
	private JButton createToolButton(String text, String icon)
	{
		// 图标
		String imagePath = "/images/" + icon;
		URL imageURL = getClass().getResource(imagePath);
		// 创建按钮
		JButton button = new JButton(text);
		//button.setActionCommand(action);
		button.setToolTipText(text);
		button.setIcon(new ImageIcon(imageURL));
		button.setFocusPainted(false);
		return button;
	
	}
}
