package mainframe.dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controllers.EquipmentController;
import controllers.ScheduleServiceController;
import entity.Equipment;
import entity.EquipmentType;
import entity.Order;
import entity.Schedule;
import utils.SmallTool;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ScheduleDialog extends JDialog {
	
	private EquipmentController equipmentController=new EquipmentController("EquipmentService");
	private ScheduleServiceController scheduleServiceController=new ScheduleServiceController("ScheduleService");
	private JTextField textField;
	private JTextField textField_1;
	private Order order;
	private JComboBox<String> comboBox;
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		try {
//			OrderAddEquipment dialog = new OrderAddEquipment();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public ScheduleDialog(JFrame jframe,Order ord) {
		super(jframe,null,true);
		setBounds(100, 100, 473, 339);
		getContentPane().setLayout(null);
		
		order=ord;
		
		JLabel lblNewLabel = new JLabel("设备选择");
		lblNewLabel.setBounds(56, 80, 71, 29);
		getContentPane().add(lblNewLabel);
		
		comboBox = new JComboBox();
		comboBox.setBounds(137, 80, 241, 29);
		getContentPane().add(comboBox);
		
		JLabel lblNewLabel_1 = new JLabel("设备名称");
		lblNewLabel_1.setBounds(56, 37, 56, 20);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel();
		lblNewLabel_2.setBounds(137, 36, 241, 23);
		getContentPane().add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("开始时间");
		lblNewLabel_3.setBounds(56, 139, 54, 15);
		getContentPane().add(lblNewLabel_3);
		
		textField = new JTextField();
		textField.setBounds(137, 132, 241, 29);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("结束时间");
		lblNewLabel_4.setBounds(56, 193, 54, 15);
		getContentPane().add(lblNewLabel_4);
		
		textField_1 = new JTextField();
		textField_1.setBounds(137, 190, 241, 29);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("确定");
		btnNewButton.setBounds(337, 267, 93, 23);
		getContentPane().add(btnNewButton);
		btnNewButton.addActionListener(e->{
			
		});
		String userID=SmallTool.userNumber_userID(ord.getFactoryID());
		try {
			List<Equipment> list=equipmentController.showEquipment();
			for(Equipment a: list) {
				if(a.getIsAvailable().equals("true")) {
					if(a.getNowBelong().equals(userID)) {
						if(a.getEquiomentState().equals("闲置中")) {
							comboBox.addItem(a.getId());
						}
					}
				}
			}
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		String aa=(String)comboBox.getSelectedItem();
		try {
			Equipment eq=equipmentController.searchEquipment(aa);
			lblNewLabel_2.setText(eq.getName());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		comboBox.addActionListener(e->{
			String a=(String)comboBox.getSelectedItem();
			try {
				Equipment eq=equipmentController.searchEquipment(a);
				lblNewLabel_2.setText(eq.getName());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
	btnNewButton.addActionListener(e->{
		if(checkValid()) {
			try {
				if(scheduleServiceController.addEquipment(getValue())) {
					JOptionPane.showMessageDialog(this, "添加成功!");
					dispose();
				}else {
					JOptionPane.showMessageDialog(this, "添加失败!");
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	});
	}
	
	public Schedule getValue()
	{
		Schedule a=new Schedule();
		a.setOrderID(order.getId());
		a.setEqipmentID((String)comboBox.getSelectedItem());
		a.setStarttime(textField.getText());
		a.setEndtime(textField_1.getText());
		return a;
	}
	
	
	public boolean checkValid()
	{
		if(textField.getText().isEmpty())
		{
			JOptionPane.showMessageDialog(this, "开始时间不得为空!");
			return false;
		}
		if(textField_1.getText().isEmpty())
		{
			JOptionPane.showMessageDialog(this, "结束时间不得为空!");
			return false;
		}
		if(!checkDate(textField.getText(), textField_1.getText()).isEmpty()) {
			JOptionPane.showMessageDialog(this, checkDate(textField.getText(), textField_1.getText()));
			return false;
		}
					
		return true;
	}
	
	public String checkDate(String str1,String str2) {
		Date a=null;Date b=null;
		try {
		SimpleDateFormat s=new SimpleDateFormat( "yyyy-MM-dd HH:mm");
			a=s.parse(str1);
			b=s.parse(str2);
			Date c=new Date();
			int result=b.compareTo(a);
			if(result>=0) {
				result=a.compareTo(c);
				if(result>=0) {					
					return "";
				}else {
					return "时间不可以早于今天";
				}
				
			}else {
				return "结束不得早于开始时间";
			}
		} catch (ParseException e) {
			return "时间格式错误，正确的格式为\nyyyy-MM-dd HH:mm";
		}
	}

}
