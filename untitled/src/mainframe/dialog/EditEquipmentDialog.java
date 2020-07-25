package mainframe.dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.io.IOException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controllers.EquipmentController;
import controllers.EquipmentTypeController;
import controllers.UserController;
import entity.Equipment;
import entity.EquipmentType;

import javax.swing.JLabel;
import javax.swing.JOptionPane;


public class EditEquipmentDialog extends JDialog {
 
	private JTextField E_name;
	private JTextField E_sp;
	private JTextField textField_3;
	private JComboBox<String> comboBox;
	private EquipmentTypeController et=new EquipmentTypeController("EquipmentTypeService");
	private Equipment eq;
	/**
	 * Create the dialog.
	 */
	public EditEquipmentDialog(JFrame frame,EquipmentController EquipmentController,Equipment equip) {
		super(frame,null,true);
		setBounds(100, 100, 347, 403);
		getContentPane().setLayout(null);
		JLabel lblNewLabel = new JLabel("设备名称:");
		lblNewLabel.setBounds(34, 80, 56, 29);
		getContentPane().add(lblNewLabel);
		eq=equip;
		E_name = new JTextField();
		E_name.setBounds(94, 82, 191, 25);
		getContentPane().add(E_name);
		E_name.setColumns(10);
		if(eq!=null)E_name.setText(eq.getName());
		JLabel lblNewLabel_2 = new JLabel("设备规格:");
		lblNewLabel_2.setBounds(34, 188, 56, 29);
		getContentPane().add(lblNewLabel_2);
		
		E_sp = new JTextField();
		E_sp.setBounds(94, 190, 191, 25);
		getContentPane().add(E_sp);
		E_sp.setColumns(10);
		if(eq!=null)E_sp.setText(eq.getSpecifications());
		textField_3 = new JTextField();
		textField_3.setBounds(94, 244, 191, 25);
		getContentPane().add(textField_3);
		textField_3.setColumns(10);
		if(eq!=null)textField_3.setText(eq.getDescription());
		JButton btnNewButton = new JButton("确定");
		btnNewButton.setBounds(213, 314, 93, 23);
		getContentPane().add(btnNewButton);
		
		JLabel E_de = new JLabel("设备描述:");
		E_de.setBounds(34, 242, 56, 29);
		getContentPane().add(E_de);
		
		JLabel lblNewLabel_4 = new JLabel("设备类型:");
		lblNewLabel_4.setBounds(34, 134, 56, 29);
		getContentPane().add(lblNewLabel_4);
		JLabel lblNewLabel_5 ;
		if(eq!=null) {
			lblNewLabel_5 = new JLabel("修改设备");
		}else {
			lblNewLabel_5=new JLabel("创建新设备");
		}
		lblNewLabel_5.setFont(new Font("宋体", Font.BOLD, 15));
		lblNewLabel_5.setBounds(128, 24, 87, 29);
		getContentPane().add(lblNewLabel_5);
		
		comboBox = new JComboBox();
		comboBox.setBounds(94, 136, 191, 25);
		try {
			List<EquipmentType> list=et.showEquipmentType();
			for(EquipmentType a: list) {
				if(a.getIsAvailable().equals("true")) {
					comboBox.addItem(a.getName());
				}
			}
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} 
		

	
		getContentPane().add(comboBox);
		if(eq!=null) {
			btnNewButton.addActionListener((e)->{
				if(checkValid()) {
					try {
						boolean a=EquipmentController.changeEquipment(getValue());
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
		}else {
		btnNewButton.addActionListener((e)->{
			if(checkValid()) {
				try {
					boolean a=EquipmentController.addEquipment(getValue());
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
		});}
		setVisible(true);
	}
	
	public Equipment getValue()
	{
		Equipment equips=null;
		if(eq!=null) {
			 equips = eq;
			 equips.setName(E_name.getText());
			 equips.setSpecifications(E_sp.getText());
			 equips.setType((String)comboBox.getSelectedItem());
			 equips.setDescription(textField_3.getText());
		}else {
			equips = new Equipment();
			equips.setName(E_name.getText());
			equips.setSpecifications(E_sp.getText());
			equips.setType((String)comboBox.getSelectedItem());
			equips.setDescription(textField_3.getText());
			equips.setBelong("0");
			equips.setNowBelong("0");
			equips.setIsRent("未被租借");
		
		}
		

		
		return equips;
	}
	public boolean checkValid()
	{
		if(E_name.getText().isEmpty())
		{
			JOptionPane.showMessageDialog(this, "名称不得为空!");
			return false;
		}
		if(E_sp.getText().isEmpty())
		{
			JOptionPane.showMessageDialog(this, "规格不得为空!");
			return false;
		}

		if(textField_3.getText().isEmpty())
		{
			JOptionPane.showMessageDialog(this, "描述不得为空!");
			return false;
		}				
		return true;
	}
	
	
}
