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

import controllers.Productcontrollers;
import controllers.ProductTypeController;
import entity.Product;
import entity.ProductType;
import entity.User;
import mainframe.SyperAdmin;
import mainframe.dialog.EditProductDialog;


public class ProductManageFrame extends JFrame {
	 private static ProductManageFrame instance;
	 public static ProductManageFrame getInstance() {  
  if (instance == null) {  
      instance = new ProductManageFrame();  
  }  
  return instance; 
  }  
	private DefaultTableModel productmodel;
		private JTable products=new JTable(productmodel){
		public boolean isCellEditable(int rowIndex, int ColIndex){
			     return false;
				}
	   }; 
	   private String[] tableHead=new String[] {"序号","产品编号","产品名称","产品类型","产品规格","产品描述"};
		private JPanel contentPane;
		private JScrollPane productScroll;
		private List<Product> productList=null;
		private Productcontrollers productController=new Productcontrollers("ProductService");
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
	public ProductManageFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1004, 457);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	
		productScroll=new JScrollPane(products);
		products.setFillsViewportHeight(true);
		products.setRowSelectionAllowed(true);
		contentPane.add(productScroll,BorderLayout.CENTER);

		products.setRowHeight(30);
		products.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//
		toolBar = new JToolBar();
		contentPane.add(toolBar,BorderLayout.PAGE_START);
		toolBar.setFloatable(false);
		btnNewButton = new JButton("新建");		
		btnNewButton_1 = new JButton("删除");
		btnNewButton_2=new JButton("修改");
		btnNewButton_3=new JButton("检索");
		textField=new JTextField();
		textField.setToolTipText("请输入账号");
		toolBar.add(btnNewButton);
		toolBar.add(btnNewButton_1);
		toolBar.add(btnNewButton_2);
		toolBar.add(textField);
		toolBar.add(btnNewButton_3);
		
		btnNewButton_3.addActionListener(e->{
			String name=textField.getText();
			int m=0;
			for( int i=0;i<productList.size();i++) {
				if(productList.get(i).getIsAvailable().equals("true")) {
					if(productList.get(i).getName().equals(name)) {
						products.setRowSelectionInterval(m, m);
						products.scrollRectToVisible(products.getCellRect(m, 0, true));
						products.setSelectionBackground(Color.LIGHT_GRAY);//选中行设置背景色								

					}
					m++;
				}
			}
		});
		btnNewButton_4 =createToolButton("返回超级管理员界面", "back.png");
		btnNewButton_4.setForeground(Color.white);
		btnNewButton_4.setBackground(new Color(0,130,228));
		contentPane.add(btnNewButton_4, BorderLayout.SOUTH);
		updateproductList();
		btnNewButton.addActionListener(e->{
			try {
				List<ProductType> s = new ProductTypeController("ProductTypeService").showProductType();
				int size=0;
				for(ProductType p:s) {
					if(p.getIsAvailable().equals("true"))size++;
					}
				if(size==0){
					JOptionPane.showMessageDialog(null, "无可创建的产品类型！");
				}else {
					EditProductDialog a=new EditProductDialog(ProductManageFrame.getInstance(), productController, null);
					a.addWindowListener(new WindowAdapter() {
						@Override
						public void windowClosed(WindowEvent e) {
							// TODO Auto-generated method stub
							updateproductList();
						}
					});
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
	
		});
		btnNewButton_1.addActionListener((e)->{
			int a=onDelete();
			updateproductList();
			if(a!=-1)products.setRowSelectionInterval(a,a);
		});
		btnNewButton_2.addActionListener(e->{
			Product u=getChange();
			if(u==null)JOptionPane.showMessageDialog(null, "无选择值！");
			else {
				EditProductDialog a=new EditProductDialog(ProductManageFrame.getInstance(), productController, u);
				a.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosed(WindowEvent e) {
						// TODO Auto-generated method stub
						updateproductList();
					}
				});
			}
			
		});
		btnNewButton_4.addActionListener(e->{
			
			// TODO Auto-generated method stub
			SyperAdmin a=SyperAdmin.getInstance();
			a.setVisible(true);
			dispose();
		
		});
	
	
	}
	public void updateproductList() {
		products.setModel(getDefaultTableModel());
	}
	public DefaultTableModel getDefaultTableModel() {
		try {
			productList=productController.showProduct();
		   productmodel =new DefaultTableModel(null,tableHead);
			int i=1;
			for(Product pro:  productList) {
				if(pro.getIsAvailable().equals("true")){
				Vector<Object> rowData=new Vector<>();
				rowData.add(i);
				rowData.add(pro.getId());
				rowData.add(pro.getName());
				rowData.add(pro.getType());
				rowData.add(pro.getSpecifications());
				rowData.add(pro.getDescription());
				productmodel.addRow(rowData);
				i++; }
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return productmodel;
	}
	
	private int onDelete()
	{
		// 获取选中的行的索引
		int[] rows = products.getSelectedRows();
		if(rows.length == 0)return -1;
				
		// 弹出对话框确认
		int select = JOptionPane.showConfirmDialog(this, "是否确认删除?", "确认", JOptionPane.YES_NO_OPTION);
		if(select != 0)	return rows[0]; // 0号按钮是'确定'按钮

		// 技巧：从后往前删除
		for(int i= rows.length-1; i>=0; i--)
		{
			String id=(String)products.getValueAt(rows[i], 1);
			try {
				if(productController.deleteProduct(id)){
					JOptionPane.showMessageDialog(this, "删除成功！");
					}else{
					JOptionPane.showMessageDialog(this, "删除失败！");
					}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return 0;
	}
	private Product getChange(){
		int[] rows = products.getSelectedRows();
		for(int i= rows.length-1; i>=0; i--)
		{
			String s=(String)productmodel.getValueAt(rows[i], 1);
			try {
				Product u=productController.searchProduct(s);;
				return u;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		}
	return null;	
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
