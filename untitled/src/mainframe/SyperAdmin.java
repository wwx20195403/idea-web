package mainframe;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entity.ProductType;
import mainframe.frames.EquipmentManageFrame;
import mainframe.frames.EquipmentTypeManageFrame;
import mainframe.frames.FactoryManageFrame;
import mainframe.frames.ProductManageFrame;
import mainframe.frames.ProductTypeManageFrame;
import mainframe.frames.SuperAdminOrderFrame;
import mainframe.frames.UserManageFrame;

import javax.swing.JLabel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.net.URL;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

public class SyperAdmin extends JFrame {
	
	
	 private static SyperAdmin instance;
	 public static SyperAdmin getInstance() {  
   if (instance == null) {  
       instance = new SyperAdmin();  
   }  
   return instance;  
   } 
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SyperAdmin frame = new SyperAdmin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SyperAdmin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 579, 586);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("超级管理员");
		lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 41));
		lblNewLabel_2.setBounds(157, 0, 263, 89);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel = new JLabel("系统设置：");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 16));
		lblNewLabel.setBounds(25, 91, 91, 41);
		contentPane.add(lblNewLabel);
		JButton btnNewButton =createToolButton("用户管理","usercharge.png");
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 18));
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(new Color(0,130,228)); 
		btnNewButton.setBounds(128, 87, 193, 41);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("云工厂：");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(39, 177, 72, 28);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton_1 =createToolButton("云工厂信息", "factory.png");
		btnNewButton_1.setFont(new Font("宋体", Font.PLAIN, 18));
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setBackground(new Color(0,130,228));
		btnNewButton_1.setBounds(128, 170, 193, 41);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_3 = new JLabel("产品管理：");
		lblNewLabel_3.setFont(new Font("宋体", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(24, 241, 92, 41);
		contentPane.add(lblNewLabel_3);
		
		JButton btnNewButton_2 =createToolButton("产品类别管理","producttype.png");
		btnNewButton_2.setFont(new Font("宋体", Font.PLAIN, 18));
		btnNewButton_2.setForeground(Color.WHITE);
		btnNewButton_2.setBackground(new Color(0,130,228));
		btnNewButton_2.setBounds(128, 241, 193, 41);
		contentPane.add(btnNewButton_2);
		JButton btnNewButton_3 =createToolButton("产品信息管理","producttype.png");
		btnNewButton_3.setFont(new Font("宋体", Font.PLAIN, 18));
		btnNewButton_3.setForeground(Color.WHITE);
		btnNewButton_3.setBackground(new Color(0,130,228));
		btnNewButton_3.setBounds(338, 241, 193, 41);
		contentPane.add(btnNewButton_3);
		
		JLabel lblNewLabel_4 = new JLabel("产能中心:");
		lblNewLabel_4.setFont(new Font("宋体", Font.PLAIN, 16));
		lblNewLabel_4.setBounds(25, 333, 72, 28);
		contentPane.add(lblNewLabel_4);
		JButton btnNewButton_4 =createToolButton("设备类型管理","equipmenttype.png");
		btnNewButton_4.setFont(new Font("宋体", Font.PLAIN, 18));
		btnNewButton_4.setForeground(Color.WHITE);
		btnNewButton_4.setBackground(new Color(0,130,228));
		btnNewButton_4.setBounds(128, 329, 193, 41);
		contentPane.add(btnNewButton_4);
		
		JButton btnNewButton_5 =createToolButton("设备管理","equipment.png");
		btnNewButton_5.setFont(new Font("宋体", Font.PLAIN, 18));
		btnNewButton_5.setForeground(Color.WHITE);
		btnNewButton_5.setBackground(new Color(0,130,228));
		btnNewButton_5.setBounds(338, 329, 193, 41);
		contentPane.add(btnNewButton_5);
		
		JLabel lblNewLabel_5 = new JLabel("订单管理:");
		lblNewLabel_5.setFont(new Font("宋体", Font.PLAIN, 16));
		lblNewLabel_5.setBounds(25, 417, 91, 28);
		contentPane.add(lblNewLabel_5);
		
		JButton btnNewButton_6 =createToolButton("订单管理","ordercharge.png");
		btnNewButton_6.setFont(new Font("宋体", Font.PLAIN, 18));
		btnNewButton_6.setForeground(Color.WHITE);
		btnNewButton_6.setBackground(new Color(0,130,228));
		btnNewButton_6.setBounds(128, 410, 193, 41);
		contentPane.add(btnNewButton_6);
		
		JButton btnNewButton_7 =createToolButton("返回登录","back.png");
		btnNewButton_7.setFont(new Font("宋体", Font.PLAIN, 18));
		btnNewButton_7.setForeground(Color.WHITE);
		btnNewButton_7.setBackground(new Color(0,130,228));
		btnNewButton_7.setBounds(260, 480, 130, 41);
		contentPane.add(btnNewButton_7);
		
		JButton btnNewButton_8 =createToolButton("退出程序","out.png");
		btnNewButton_8.setFont(new Font("宋体", Font.PLAIN, 18));
		btnNewButton_8.setForeground(Color.white);
		btnNewButton_8.setBackground(new Color(0,130,228));
		btnNewButton_8.setBounds(410, 480, 130, 41);
		contentPane.add(btnNewButton_8);
		
		btnNewButton_7.addActionListener(e->{
			Login a=Login.getInstance();
			a.setVisible(true);
			dispose();
		});
		btnNewButton.addActionListener(e->{
			UserManageFrame f=UserManageFrame.getInstance();
			f.updateUserList();
			f.setVisible(true);
			dispose();
		});
		btnNewButton_1.addActionListener(e->{
			FactoryManageFrame aa=FactoryManageFrame.getInstance();
			aa.updateFactoryList();
			aa.setVisible(true);
			dispose();
		});
		
		btnNewButton_2.addActionListener(e->{
			ProductTypeManageFrame pt=ProductTypeManageFrame.getInstance();
			pt.updateProductTypeList();
			pt.setVisible(true);
			dispose();
		});
		btnNewButton_3.addActionListener(e->{
			ProductManageFrame pd=ProductManageFrame.getInstance();
			pd.updateproductList();
			pd.setVisible(true);
			dispose();
		});	
	
		btnNewButton_4.addActionListener(e->{
			EquipmentTypeManageFrame et=EquipmentTypeManageFrame.getInstance();
			et.updateEquipmentTypeList();
			et.setVisible(true);
			dispose();
		});	
		btnNewButton_5.addActionListener(e->{
			EquipmentManageFrame eq=EquipmentManageFrame.getInstance();
			eq.updateequipmentList();
			eq.setVisible(true);
			dispose();
		});	
		btnNewButton_6.addActionListener(e->{
			SuperAdminOrderFrame or=SuperAdminOrderFrame.getInstance();
			or.updateequipmentList();
			or.setVisible(true);
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

