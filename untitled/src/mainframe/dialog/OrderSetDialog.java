package mainframe.dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controllers.OrderController;
import controllers.Productcontrollers;
import entity.Order;
import entity.Product;
import utils.SmallTool;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.io.IOException;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JTextField;

public class OrderSetDialog extends JDialog {

	private String userID;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JComboBox<String> comboBox;
	private Productcontrollers productController=new Productcontrollers("ProductService");
	private OrderController orderController=new OrderController("OrderService");
	private Order order; 

	/**
	 * Create the dialog.
	 */
	public OrderSetDialog(JFrame frame,String user_ID,Order ord) {
		super(frame,null,true);
		setBounds(100, 100, 425, 442);
		userID=user_ID;
		order=ord;
		if(order==null) {
			setTitle(userID+"的创建订单");
			}else {
				setTitle(userID+"的修改订单");
			}
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel	;
			
			if(order==null) {
				lblNewLabel	= new JLabel("创建订单");
			}else {
				lblNewLabel	= new JLabel("修改订单");
			}
			
			lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 18));
			lblNewLabel.setBounds(165, 27, 114, 29);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("产品名称：");
			lblNewLabel_1.setBounds(44, 90, 66, 26);
			contentPanel.add(lblNewLabel_1);
		}
		
		comboBox = new JComboBox();
		comboBox.setBounds(120, 92, 221, 23);
		contentPanel.add(comboBox);
		
		try {
			List<Product> list=productController.showProduct();
			for(Product a: list) {
				if(a.getIsAvailable().equals("true")) {
					comboBox.addItem(a.getName());
				}
			}
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} 
		
		JLabel lblNewLabel_2 = new JLabel("采购数量：");
		lblNewLabel_2.setBounds(44, 157, 66, 26);
		contentPanel.add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setBounds(120, 160, 221, 23);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("交付日期：");
		lblNewLabel_3.setBounds(44, 212, 72, 29);
		contentPanel.add(lblNewLabel_3);
		
		textField_1 = new JTextField();
		textField_1.setBounds(120, 215, 221, 23);
		contentPanel.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(120, 274, 214, 23);
		contentPanel.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("投标截止日期：");
		lblNewLabel_4.setBounds(27, 272, 100, 26);
		contentPanel.add(lblNewLabel_4);
		
		JButton btnNewButton = new JButton("确定");
		btnNewButton.setBounds(288, 334, 93, 23);
		contentPanel.add(btnNewButton);
		if(order==null) {
			btnNewButton.addActionListener(e->{
				if(checkValid()) {
					try {
						if(orderController.addOrder(getValid())) {
							JOptionPane.showMessageDialog(this, "创建成功");
							dispose();
						}else {
							JOptionPane.showMessageDialog(this, "创建失败");
						}
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
		}else {
			btnNewButton.addActionListener(e->{
				if(checkValid()) {
					try {
						if(orderController.changeOrder(getValid())) {
							JOptionPane.showMessageDialog(this, "修改成功");
							dispose();
						}else {
							JOptionPane.showMessageDialog(this, "修改失败");
						}
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
		}
	
	}
	
	public boolean checkValid() {
		if(textField.getText().isEmpty())
		{
			JOptionPane.showMessageDialog(this, "数量不得为空!");
			return false;
		}else {
			try {
			int a=Integer.parseInt(textField.getText());
				if(a==0) {
					JOptionPane.showMessageDialog(this, "数量不得为0!");
					return false;
				}
				}catch(Exception e) {
					JOptionPane.showMessageDialog(this, "数量错误!");
					return false;
				}
		}
		if(textField_1.getText().isEmpty())
		{
			JOptionPane.showMessageDialog(this, "日期不得为空!");
			return false;
		}
		if(textField_2.getText().isEmpty())
		{
			JOptionPane.showMessageDialog(this, "日期不得为空!");
			return false;
		}
		
		if(!checkDate(textField_1.getText(), textField_2.getText()).isEmpty()) {
			JOptionPane.showMessageDialog(this, checkDate(textField_1.getText(), textField_2.getText()));
			return false;
		}
		return true;
	}
	
	public Order getValid() {
		Order a;
		if(order==null) {
			a=new Order();
		}else {
			a=order;
		}
		a.setDeliverydate(textField_1.getText());
		a.setTenderdeadline(textField_2.getText());
		a.setProductnumber(Integer.parseInt(textField.getText()));
		a.setProductname((String)comboBox.getSelectedItem());
		a.setTraderID(SmallTool.userID_userNumber(userID));
		return a;
	}
	
	public String checkDate(String str1,String str2) {
		Date a=null;Date b=null;
		try {
		SimpleDateFormat s=new SimpleDateFormat( "yyyy-MM-dd");
			a=s.parse(str1);
			b=s.parse(str2);
			Date c=new Date();
			int result=b.compareTo(a);
			if(result<=0) {
				result=b.compareTo(c);
				if(result>=0) {					
					return "";
				}else {
					return "时间不可以早于今天";
				}
				
			}else {
				return "交货时间不得早于投标截止时间";
			}
		} catch (ParseException e) {
			return "时间格式错误，正确的格式为\nyyyy-MM-dd";
		}
	}
	
	
}
