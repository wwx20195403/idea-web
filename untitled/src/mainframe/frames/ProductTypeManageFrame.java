package mainframe.frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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

import controllers.ProductTypeController;
import entity.ProductType;
import entity.User;
import mainframe.SyperAdmin;
import mainframe.dialog.EditProductTypeDialog;


public class ProductTypeManageFrame extends JFrame {
	 private static ProductTypeManageFrame instance;
	 public static ProductTypeManageFrame getInstance() {  
 if (instance == null) {  
     instance = new ProductTypeManageFrame();  
 }  
 return instance;  
 } 
	private JPanel contentPane;


	private String[] tableHead=new String[] {"序号","类别名称","序列号","被引用次数"};
	private DefaultTableModel productTypeModel=new DefaultTableModel();
	private JTable productTypes=new JTable(productTypeModel){
		public boolean isCellEditable(int rowIndex, int ColIndex){
		     return false;
			}
   }; 
	private JScrollPane productTypeScroll;
	private List<ProductType> productTypeList=null;
	private ProductTypeController productTypeController=new ProductTypeController("ProductTypeService");
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JToolBar toolBar ;
	private JButton btnNewButton_2;
	private JButton btnNewButton_3;
	private JTextField textField;
	private JButton btnNewButton_4;
	/** 
	 * Create the frame.
	 */
	public ProductTypeManageFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 611, 302);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		
		//表格设置
		productTypeScroll=new JScrollPane(productTypes);
		productTypes.setFillsViewportHeight(true);
		productTypes.setRowSelectionAllowed(true);
		contentPane.add(productTypeScroll,BorderLayout.CENTER);		
		productTypes.setRowHeight(30);
		productTypes.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//
		toolBar = new JToolBar();
		contentPane.add(toolBar,BorderLayout.PAGE_START);
		toolBar.setFloatable(false);
		btnNewButton = new JButton("新建");		
		btnNewButton_1 = new JButton("删除");
		btnNewButton_2=new JButton("修改");
		btnNewButton_3=new JButton("检索");
		textField=new JTextField();
		textField.setToolTipText("请输入类别");
		toolBar.add(btnNewButton);
		toolBar.add(btnNewButton_1);
		toolBar.add(btnNewButton_2);
		toolBar.add(textField);
		toolBar.add(btnNewButton_3);
		
		btnNewButton_4 =createToolButton("返回超级管理员界面", "back.png");
		btnNewButton_4.setForeground(Color.white);
		btnNewButton_4.setBackground(new Color(0,130,228));
		contentPane.add(btnNewButton_4, BorderLayout.SOUTH);
		updateProductTypeList();
		btnNewButton_2.addActionListener((e)->{
			ProductType aa=getChange();
			if(aa==null) {JOptionPane.showMessageDialog(null, "无选择值！");}
			else {
				EditProductTypeDialog a=new EditProductTypeDialog(ProductTypeManageFrame.getInstance(), productTypeController,aa);
			a.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosed(WindowEvent e) {
					// TODO Auto-generated method stub
					updateProductTypeList();
				}
			});}
		});
		btnNewButton_1.addActionListener((e)->{
			onDelete();
			updateProductTypeList();
		});

		btnNewButton.addActionListener((e)->{
			EditProductTypeDialog a=new EditProductTypeDialog(ProductTypeManageFrame.getInstance(), productTypeController,null);
			a.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosed(WindowEvent e) {
					// TODO Auto-generated method stub
					updateProductTypeList();

				}
			});
		});
		btnNewButton_3.addActionListener(e->{
			String name=textField.getText();
			int m=0;
			for( int i=0;i<productTypeList.size();i++) {
				if(productTypeList.get(i).getIsAvailable().equals("true")) {
					if(productTypeList.get(i).getName().equals(name)) {
						productTypes.setRowSelectionInterval(m, m);
						productTypes.scrollRectToVisible(productTypes.getCellRect(m, 0, true));
						productTypes.setSelectionBackground(Color.LIGHT_GRAY);//选中行设置背景色								

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
		
		
	}
	public void updateProductTypeList() {
		productTypes.setModel(getDefaultTableModel());
	}
	
	public DefaultTableModel getDefaultTableModel() {
		try {
			productTypeList=productTypeController.showProductType();
			    productTypeModel=new DefaultTableModel(null,tableHead);
			int i=1;
			for(ProductType pro : productTypeList) {
				if(pro.getIsAvailable().equals("true")){
				Vector<Object> rowData=new Vector<>();
				rowData.add(i);
				rowData.add(pro.getName());
				rowData.add(pro.getSerialNumber());
				rowData.add(pro.getIsQuote());
				productTypeModel.addRow(rowData);
				i++; }
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return productTypeModel;
	}
	private ProductType getChange(){
		int[] rows = productTypes.getSelectedRows();
		for(int i= rows.length-1; i>=0; i--)
		{
			String s=(String)productTypes.getValueAt(rows[i], 1);
			try {
				ProductType u=productTypeController.searchProductType(s);
				return u;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		}
	return null;	
	}
	private void onDelete()
	{
		// 获取选中的行的索引
		int[] rows=null;
		rows = productTypes.getSelectedRows();
		System.out.println(rows.length);
		if(rows.length == 0)return;
				
		// 弹出对话框确认
		int select = JOptionPane.showConfirmDialog(this, "是否确认删除?", "确认", JOptionPane.YES_NO_OPTION);
		if(select != 0) return; // 0号按钮是'确定'按钮

		// 技巧：从后往前删除
		for(int i= rows.length-1; i>=0; i--)
		{
			String s=(String)productTypes.getValueAt(rows[i], 1);
			try {
				if(productTypeController.deleteProductType(s)){
					JOptionPane.showMessageDialog(this, "删除成功！");
					}else{
					JOptionPane.showMessageDialog(this, "删除失败！");
					}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
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


}
