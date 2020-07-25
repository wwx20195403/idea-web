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

import controllers.EquipmentTypeController;
import controllers.OrderController;
import controllers.Productcontrollers;
import entity.EquipmentType;
import entity.Order;
import entity.Product;
import mainframe.TraderAdmin;
import mainframe.dialog.EditEquipmentDialog_Fa;
import mainframe.dialog.OrderSetDialog;
import utils.SmallTool;

public class TraderOrderFrame extends JFrame {

	private static String userID;
	public static void setUserID(String a) {
			userID=a;
	}
	
	 private static TraderOrderFrame instance;
	 public static TraderOrderFrame getInstance() {  
		 if (instance == null) {  
			 instance = new TraderOrderFrame();  
		 }  
		 return instance;  
  } 
	private JPanel contentPane;


	 private String[] tableHead=new String[] {"序号","订单编号","产品名称","订购数量","交付日期","投标截止日期","订单状态"};
		private DefaultTableModel ordermodel=new DefaultTableModel();
		private JTable orders=new JTable(ordermodel){
			public boolean isCellEditable(int rowIndex, int ColIndex){
			     return false;
				}
	   }; 
		private JScrollPane orderScroll;
		private List<Order> orderList=null;
		private OrderController orderController=new OrderController("OrderService");
		private JButton btnNewButton;
		private JToolBar toolBar ;
		private JButton btnNewButton_3;
		private JButton btnNewButton_2;
		private JButton btnNewButton_5;
		private JButton btnNewButton_6;
		private JTextField textField; 

		private JButton btnNewButton_4;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public TraderOrderFrame() {
		setTitle("经销商 订单管理");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1004, 457);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		//表格设置
		orderScroll=new JScrollPane(orders);
		orders.setFillsViewportHeight(true);
		orders.setRowSelectionAllowed(true);
		contentPane.add(orderScroll,BorderLayout.CENTER);
		orders.setRowHeight(30);
		orders.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//
		toolBar = new JToolBar();
		contentPane.add(toolBar,BorderLayout.PAGE_START);
		toolBar.setFloatable(false);
		btnNewButton = new JButton("新建");			
		btnNewButton_2=new JButton("发布");
		btnNewButton_3=new JButton("修改");
		btnNewButton_6=new JButton("删除");
		
		btnNewButton_5=new JButton("收货");
		
		toolBar.add(btnNewButton);
		toolBar.add(btnNewButton_3);
		toolBar.add(btnNewButton_2);
		toolBar.add(btnNewButton_6);
		toolBar.add(btnNewButton_5);
		btnNewButton_4 =createToolButton("返回经销商管理界面", "back.png");
		btnNewButton_4.setForeground(Color.white);
		btnNewButton_4.setBackground(new Color(0,130,228));
		contentPane.add(btnNewButton_4, BorderLayout.SOUTH);
		updatorderList();

		btnNewButton.addActionListener(e->{
			try {
				List<Product> s=new Productcontrollers("ProductService").showProduct();
				int size=0;
			for(Product ep:s) {
				if(ep.getIsAvailable().equals("true"))size++;
			}	
			if(size==0) {
				JOptionPane.showMessageDialog(null, "无可创建的产品类型！");
			}else {
				OrderSetDialog a=new OrderSetDialog(TraderOrderFrame.getInstance(),userID,null);
				a.setVisible(true);
				a.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosed(WindowEvent e) {
						// TODO Auto-generated method stub
						updatorderList();
					}
				});
			}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		});
		
		btnNewButton_5.addActionListener(e->{
			Order a=getOrder();
			if(a==null) {
				
			}else {
				a.setOrdetstate("已收货");
				try {
					if(orderController.changeOrder(a)) {
						JOptionPane.showMessageDialog(this, "收货成功！");
					}else {
						JOptionPane.showMessageDialog(this, "收货失败！");
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			updatorderList();
		});
		
		btnNewButton_2.addActionListener(e->{
			Order a=getChange();
			if(a==null) {
				
			}else {
				a.setOrdetstate("已发布");
				try {
					if(orderController.changeOrder(a)) {
						JOptionPane.showMessageDialog(this, "发布成功！");
					}else {
						JOptionPane.showMessageDialog(this, "发布失败！");
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			updatorderList();
		});
		
		btnNewButton_3.addActionListener(e->{
			Order a=getChange();
			if(a==null) {
				
			}else {
				OrderSetDialog aa=new OrderSetDialog(TraderOrderFrame.getInstance(),userID,a);
				aa.setVisible(true);
				aa.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosed(WindowEvent e) {
						// TODO Auto-generated method stub
						updatorderList();
					}
				});
			}
		});
		btnNewButton_6.addActionListener(e->{
			Order a=getChange();
			if(a==null) {
				
			}else {
				a.setOrdetstate("已删除");
				try {
					if(orderController.changeOrder(a)){
						JOptionPane.showMessageDialog(this, "删除成功");
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				updatorderList();
			}
		});
		
		btnNewButton_4.addActionListener(e->{
			
			// TODO Auto-generated method stub
			TraderAdmin.setUserID(userID);
			TraderAdmin a=TraderAdmin.getInstance();
			a.setVisible(true);
			dispose();
		
		});
		
		
		setVisible(true);

	}
	
	public void updatorderList() {
		orders.setModel(getDefaultTableModel());
	}
	public DefaultTableModel getDefaultTableModel() {
		try {
			String usernumber=SmallTool.userID_userNumber(userID);
			ordermodel=new DefaultTableModel(null, tableHead);
			orderList=orderController.showOrder();
			int i=1;
			for(Order ord:  orderList) {
				//  {"序号","订单编号","产品名称","订购数量","交付日期","投标截止日期","订单状态"};
				if(!ord.getOrdetstate().equals("已删除")) {
					if(ord.getTraderID().equals(usernumber)) {
						Vector<Object> rowData=new Vector<>();
						rowData.add(i);
						rowData.add(ord.getId());
						rowData.add(ord.getProductname());
						rowData.add(ord.getProductnumber());
						rowData.add(ord.getDeliverydate());
						rowData.add(ord.getTenderdeadline());
						rowData.add(ord.getOrdetstate());
						i++;
						ordermodel.addRow(rowData);
				}
				}
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ordermodel;
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

	private Order getChange(){
		int[] rows = orders.getSelectedRows();
		for(int i= rows.length-1; i>=0; i--)
		{
			String s=(String)ordermodel.getValueAt(rows[i], 1);
			try {
				Order u=orderController.searchOrder(s);
				if(u.getOrdetstate().equals("已保存")) {
					return u;
				}else {
					JOptionPane.showMessageDialog(this, "不可进行此操作！");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		}
	return null;	
	}
	
	private Order getOrder(){
		int[] rows = orders.getSelectedRows();
		for(int i= rows.length-1; i>=0; i--)
		{
			String s=(String)ordermodel.getValueAt(rows[i], 1);
			try {
				Order u=orderController.searchOrder(s);
				if(u.getOrdetstate().equals("已发货")) {
					return u;
				}else {
					JOptionPane.showMessageDialog(this, "不可进行此操作！");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		}
	return null;	
	}
}
