package mainframe.dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controllers.EquipmentTypeController;
import entity.EquipmentType;

public class EditEquipmentTypeDialog extends JDialog {
	private JTextField textField;
	private EquipmentTypeController Controller;
	private EquipmentType equ;

	/** 
	 * Create the dialog.
	 * @wbp.parser.constructor
	 */
	public EditEquipmentTypeDialog(JFrame jframe,EquipmentTypeController equipmentTypeController) {
			super(jframe,"设备类别管理",true);
			setBounds(100, 100, 353, 235);
			getContentPane().setLayout(null);
			Controller=equipmentTypeController;
			
			JLabel lblNewLabel_5 = new JLabel("创建新类型");
			lblNewLabel_5.setFont(new Font("宋体", Font.BOLD, 15));
			lblNewLabel_5.setBounds(126, 28, 141, 29);
			getContentPane().add(lblNewLabel_5);
			
			JLabel lblNewLabel = new JLabel("设置类型:");
			lblNewLabel.setBounds(34, 80, 56, 29);
			getContentPane().add(lblNewLabel);
			
			textField = new JTextField();
			textField.setBounds(94, 82, 191, 25);
			getContentPane().add(textField);
			textField.setColumns(10);
			
			JButton btnNewButton = new JButton("确定");
			btnNewButton.setBounds(234, 149, 93, 23);
			getContentPane().add(btnNewButton);

			
		 btnNewButton.addActionListener((e)->{
			 if(checkValid()) {
				 try {
					boolean a=Controller.addEquipmentType(new EquipmentType(textField.getText()));
					if(!a) {
						JOptionPane.showMessageDialog(null,"添加失败!");
					}else {
						JOptionPane.showMessageDialog(null,"添加成功!");
						dispose();
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				 
			 }
			 
		 });
			
			setVisible(true);
			
		}
	public EditEquipmentTypeDialog(JFrame jframe,EquipmentTypeController equipmentTypeController,EquipmentType equipty) {
		super(jframe,"设备类别管理",true);
		setBounds(100, 100, 353, 235);
		getContentPane().setLayout(null);
		Controller=equipmentTypeController;
		equ=equipty;
		JLabel lblNewLabel_5 = new JLabel("修改类型");
		lblNewLabel_5.setFont(new Font("宋体", Font.BOLD, 15));
		lblNewLabel_5.setBounds(126, 28, 141, 29);
		getContentPane().add(lblNewLabel_5);
		
		JLabel lblNewLabel = new JLabel("修改类型为:");
		lblNewLabel.setBounds(34, 80, 56, 29);
		getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(94, 82, 191, 25);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("确定");
		btnNewButton.setBounds(234, 149, 93, 23);
		getContentPane().add(btnNewButton);

		
	 btnNewButton.addActionListener((e)->{
		 if(checkValid()) {
			 try {
				 equ.setName(textField.getText());
				boolean a=Controller.changeEquipmentType(equ);
				if(!a) {
					JOptionPane.showMessageDialog(null,"修改失败!");
				}else {
					JOptionPane.showMessageDialog(null,"修改成功!");
					dispose();
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			 
		 }
		 
	 });
		
		setVisible(true);
		
	}

	public boolean checkValid() {
		if(textField.getText().isEmpty())
		{
			JOptionPane.showMessageDialog(this, "账号不得为空!");
			return false;
		}
		return true;
	}
	
	}

