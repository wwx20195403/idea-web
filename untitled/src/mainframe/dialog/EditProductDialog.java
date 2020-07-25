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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controllers.Productcontrollers;
import controllers.ProductTypeController;
import entity.Product;
import entity.ProductType;
/*------------------------------------------------------------------*/
public class EditProductDialog extends JDialog {

	private JTextField E_name;
	private JTextField E_sp;
	private JTextField textField_3;
	private JComboBox<String> comboBox;
	private ProductTypeController et=new ProductTypeController("ProductTypeService");
	private Product pro;
	/** 
	 * Create the dialog.
	 */
	public EditProductDialog(JFrame frame,Productcontrollers productController,Product pros) {
		super(frame,null,true);
		setBounds(100, 100, 338, 417);
		getContentPane().setLayout(null);
		JLabel lblNewLabel = new JLabel("产品名称:");
		lblNewLabel.setBounds(34, 80, 56, 29);
		getContentPane().add(lblNewLabel);
		pro=pros;
		E_name = new JTextField();
		E_name.setBounds(94, 82, 191, 25);
		getContentPane().add(E_name);
		E_name.setColumns(10);
		if(pro!=null)E_name.setText(pro.getName());
		JLabel lblNewLabel_2 = new JLabel("产品规格:");
		lblNewLabel_2.setBounds(34, 188, 56, 29);
		getContentPane().add(lblNewLabel_2);
		
		E_sp = new JTextField();
		E_sp.setBounds(94, 190, 191, 25);
		getContentPane().add(E_sp);
		E_sp.setColumns(10);
		if(pro!=null)E_sp.setText(pro.getSpecifications());
		textField_3 = new JTextField();
		textField_3.setBounds(94, 244, 191, 25);
		getContentPane().add(textField_3);
		textField_3.setColumns(10);
		if(pro!=null)textField_3.setText(pro.getDescription());
		JButton btnNewButton = new JButton("确定");
		btnNewButton.setBounds(189, 317, 93, 23);
		getContentPane().add(btnNewButton);
		
		JLabel E_de = new JLabel("产品描述:");
		E_de.setBounds(34, 242, 56, 29);
		getContentPane().add(E_de);
		
		JLabel lblNewLabel_4 = new JLabel("产品类型:");
		lblNewLabel_4.setBounds(34, 134, 56, 29);
		getContentPane().add(lblNewLabel_4);
		JLabel lblNewLabel_5 ;
		if(pro!=null) {
			lblNewLabel_5 = new JLabel("修改产品");
		}else {
			lblNewLabel_5=new JLabel("创建新产品");
		}
		lblNewLabel_5.setFont(new Font("宋体", Font.BOLD, 15));
		lblNewLabel_5.setBounds(128, 24, 87, 29);
		getContentPane().add(lblNewLabel_5);
		
		comboBox = new JComboBox();
		comboBox.setBounds(94, 136, 191, 25);
		try {
			List<ProductType> list=et.showProductType();
			for(ProductType a: list) {
				if(a.getIsAvailable().equals("true")) {
					comboBox.addItem(a.getName());
				}
			}
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} 
		

	
		getContentPane().add(comboBox);
		if(pro!=null) {
			btnNewButton.addActionListener((e)->{
				if(checkValid()) {
					try {
						boolean a=productController.changeProduct(getValue());
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
					boolean a=productController.addProduct(getValue());
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
	
	public Product getValue()
	{
		Product pross=null;
		if(pro!=null) {
			 pross = pro;
			 pross.setName(E_name.getText());
			 pross.setSpecifications(E_sp.getText());
			 pross.setType((String)comboBox.getSelectedItem());
			 pross.setDescription(textField_3.getText());
		}else {
			pross = new Product();
			pross.setName(E_name.getText());
			pross.setSpecifications(E_sp.getText());
			pross.setType((String)comboBox.getSelectedItem());
			pross.setDescription(textField_3.getText());

		}
		

		
		return pross;
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
