package mainframe.frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controllers.OrderController;
import entity.Order;
import mainframe.SyperAdmin;
import utils.SmallTool;

public class SuperAdminOrderFrame extends JFrame {
	 private static SuperAdminOrderFrame instance;
	 public static SuperAdminOrderFrame getInstance() {  
  if (instance == null) {  
      instance = new SuperAdminOrderFrame();  
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
		private JButton btnNewButton_4;


	/**
	 * Create the frame.
	 */
	public SuperAdminOrderFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 735, 390);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	
		btnNewButton_4 =createToolButton("返回超级管理员界面", "back.png");
		btnNewButton_4.setForeground(Color.white);
		btnNewButton_4.setBackground(new Color(0,130,228));
		contentPane.add(btnNewButton_4, BorderLayout.SOUTH);
	
		orderScroll=new JScrollPane(orders);
		orders.setFillsViewportHeight(true);
		orders.setRowSelectionAllowed(true);
		contentPane.add(orderScroll,BorderLayout.CENTER);
		orders.setRowHeight(30);
		orders.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//
		updateequipmentList();
		btnNewButton_4.addActionListener(e->{
			
			// TODO Auto-generated method stub
			SyperAdmin a=SyperAdmin.getInstance();
			a.setVisible(true);
			dispose();
		
		});
	}
	
	public void updateequipmentList() {
		orders.setModel(getDefaultTableModel());
	}
	public DefaultTableModel getDefaultTableModel() {
		try {
			ordermodel=new DefaultTableModel(null, tableHead);
			orderList=orderController.showOrder();
			int i=1;
			for(Order ord:  orderList) {
				if(!ord.getOrdetstate().equals("已删除")) {
				if(!ord.getOrdetstate().equals("已保存")) {
					//  {"序号","订单编号","产品名称","订购数量","交付日期","投标截止日期","订单状态"};
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
}
