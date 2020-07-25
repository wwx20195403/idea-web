package mainframe;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controllers.UserController;
import entity.User;
import mainframe.frames.EquipmentManageFrame_Fa;
import mainframe.frames.TraderOrderFrame;
import mainframe.frames.UserManageFrame;
import utils.SmallTool;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JPasswordField;

public class Login extends JFrame {
	private UserController userController=new UserController("UserService");
	 private static Login instance;
	 public static Login getInstance() {  
    if (instance == null) {  
        instance = new Login();  
    }  
    return instance;  
    } 
	private JPanel contentPane;
	private JTextField textField_1;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = Login.getInstance();
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
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("确定");
		btnNewButton.setBounds(298, 208, 93, 23);
		contentPane.add(btnNewButton);
		
		textField_1 = new JTextField();
		textField_1.setBounds(146, 79, 200, 23);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("账号:");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 16));
		lblNewLabel.setBounds(100, 71, 49, 36);
		contentPane.add(lblNewLabel);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(146, 142, 200, 23);
		contentPane.add(passwordField);
		
		JLabel lblNewLabel_1 = new JLabel("密码:");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(105, 134, 69, 36);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("登录");
		lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 40));
		lblNewLabel_2.setBounds(175, 0, 204, 78);
		contentPane.add(lblNewLabel_2);
		
	
		btnNewButton.addActionListener(e->{
			if(check()) {
				String userID=textField_1.getText();
				String password=String.valueOf(passwordField.getPassword());
				if(userID.equals("admin")&password.equals("123456")) {
					SyperAdmin a=SyperAdmin.getInstance();
					a.setVisible(true);
					dispose();
				}else {
				
					if(userController.validate(userID, password)) {
						try {
							User u=userController.searchUser(userID);
							if(u.getType().equals("云工厂管理员")){	
								FactoryAdmin.setUserID(u.getId());
								FactoryAdmin a=FactoryAdmin.getInstance();
								a.setVisible(true);
								dispose();
								}else if(u.getType().equals("经销商")){
									TraderAdmin.setUserID(u.getId());
									TraderAdmin a=TraderAdmin.getInstance();
									a.setVisible(true);
									dispose();
								}
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					}
				
				}
				textField_1.setText("");
				passwordField.setText("");
			}
		});
		
		
		setVisible(true);
	}
	
	public boolean check() {
		if(textField_1.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "账户名不得为空!");
			return false;
		}
		if(passwordField.getPassword().length==0) {
			JOptionPane.showMessageDialog(this, "密码不得为空!");
			return false;
		}
		 return true;
		}
	}
