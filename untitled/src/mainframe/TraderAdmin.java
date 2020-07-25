package mainframe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import mainframe.frames.TraderOrderFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.net.URL;

import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class TraderAdmin extends JFrame {

	private static String userID;
	public static void setUserID(String a) {
			userID=a;
	}
	
	 private static TraderAdmin instance;
	 public static TraderAdmin getInstance() {  
		 if (instance == null) {  
			 instance = new TraderAdmin();  
		 }  
		 return instance;  
  } 
	private JPanel contentPane;



	/**
	 * Create the frame.
	 */
	public TraderAdmin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("经销商管理");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 21));
		lblNewLabel.setBounds(150, 26, 174, 62);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("订单管理：");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 23));
		lblNewLabel_1.setBounds(21, 98, 115, 62);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton_1 =createToolButton("我的订单", "ordercharge.png");
		btnNewButton_1.setFont(new Font("宋体", Font.PLAIN, 18));
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setBackground(new Color(0,130,228));
		btnNewButton_1.setBounds(133, 111, 193, 41);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_7 =createToolButton("返回登录","back.png");
		btnNewButton_7.setFont(new Font("宋体", Font.PLAIN, 18));
		btnNewButton_7.setForeground(Color.WHITE);
		btnNewButton_7.setBackground(new Color(0,130,228));
		btnNewButton_7.setBounds(140, 208, 130, 41);
		contentPane.add(btnNewButton_7);
		
		JButton btnNewButton_8 =createToolButton("退出程序","out.png");
		btnNewButton_8.setFont(new Font("宋体", Font.PLAIN, 18));
		btnNewButton_8.setForeground(Color.white);
		btnNewButton_8.setBackground(new Color(0,130,228));
		btnNewButton_8.setBounds(280, 208, 130, 41);
		contentPane.add(btnNewButton_8);
		
		btnNewButton_1.addActionListener(e->{
			TraderOrderFrame.setUserID(userID);
			TraderOrderFrame a=TraderOrderFrame.getInstance();	
			a.updatorderList();
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
