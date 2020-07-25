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

import controllers.EquipmentController;
import controllers.EquipmentTypeController;
import entity.Equipment;
import entity.EquipmentType;
import mainframe.SyperAdmin;
import mainframe.dialog.EditEquipmentDialog;
import utils.SmallTool;

public class RentEquipment extends JFrame {

	private static String userID;
	public static void setUserID(String a) {
			userID=a;
		
	}
	
	 private static RentEquipment instance;
	 public static RentEquipment getInstance() {  
   if (instance == null) {  
       instance = new RentEquipment();  
   }  
   return instance;  
   } 
	 private String[] tableHead=new String[] {"设备序号","设备编号","设备名称","设备类型","设备规格","设备描述","设备状态"};
		private DefaultTableModel equipmentmodel=new DefaultTableModel();
		private JTable equipments=new JTable(equipmentmodel){
			public boolean isCellEditable(int rowIndex, int ColIndex){
			     return false;
				}
	   }; 
	   private JPanel contentPane;
		private JScrollPane equipmentScroll;
		private List<Equipment> equipmentList=null;
		private EquipmentController equipmentController=new EquipmentController("EquipmentService");
		private JButton btnNewButton;
		private JTextField textField; 
		private JButton btnNewButton_3;
		private JToolBar toolBar ;


		private JButton btnNewButton_4;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public RentEquipment() {
		setTitle(userID+"工厂—租借设备");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1004, 457);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		//表格设置
		equipmentScroll=new JScrollPane(equipments);
		equipments.setFillsViewportHeight(true);
		equipments.setRowSelectionAllowed(true);
		contentPane.add(equipmentScroll,BorderLayout.CENTER);
		equipments.setRowHeight(30);
		equipments.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//
		toolBar = new JToolBar();
		contentPane.add(toolBar,BorderLayout.PAGE_START);
		toolBar.setFloatable(false);
		btnNewButton = new JButton("租借");		
		textField=new JTextField();
		btnNewButton_3=new JButton("检索");
	
		toolBar.add(btnNewButton);
		toolBar.add(textField);
		toolBar.add(btnNewButton_3);
		
		
		btnNewButton_3.addActionListener(e->{
			String name=textField.getText();
			int m=0;
			for( int i=0;i<equipmentList.size();i++) {
				if(equipmentList.get(i).getIsAvailable().equals("true")) {
					if(equipmentList.get(i).getNowBelong().equals("0")) {
						if(equipmentList.get(i).getName().equals(name)) {
							equipments.setRowSelectionInterval(m, m);
							equipments.scrollRectToVisible(equipments.getCellRect(m, 0, true));
							equipments.setSelectionBackground(Color.LIGHT_GRAY);//选中行设置背景色								

						}
					m++;
					}
					
				}
			}
		});
		

		btnNewButton_4 =createToolButton("返回设备管理界面", "back.png");
		btnNewButton_4.setForeground(Color.white);
		btnNewButton_4.setBackground(new Color(0,130,228));
		contentPane.add(btnNewButton_4, BorderLayout.SOUTH);
		updateequipmentList();
		btnNewButton.addActionListener((e)->{
			int[] rows = equipments.getSelectedRows();
			if(rows.length == 0) {
				
			}else {
				for(int i= rows.length-1; i>=0; i--)
				{
					String id=(String)equipments.getValueAt(rows[i], 1);
					try {
						Equipment u=equipmentController.searchEquipment(id);
						if(u.getNowBelong().equals("0")){
							u.setNowBelong(userID);	
							u.setIsRent("已被租借");
							if(equipmentController.changeEquipment(u)) {
								JOptionPane.showMessageDialog(this, "租借成功！");					
							}
						}
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
					
				}
			}
			updateequipmentList();
		});

		btnNewButton_4.addActionListener(e->{
			
			// TODO Auto-generated method stub
			EquipmentManageFrame_Fa.setUserID(userID);
			EquipmentManageFrame_Fa a=EquipmentManageFrame_Fa.getInstance();
			a.updateequipmentList();
			a.setVisible(true);
			dispose();
		
		});
		
		
		setVisible(true);

	}
	
	public void updateequipmentList() {
		equipments.setModel(getDefaultTableModel());
	}
	public DefaultTableModel getDefaultTableModel() {
		try {
			equipmentmodel=new DefaultTableModel(null, tableHead);
			equipmentList=equipmentController.showEquipment();
			int i=1;
			for(Equipment equip:  equipmentList) {
				if(equip.getIsAvailable().equals("true")){
					if(equip.getNowBelong().equals("0")){
						Vector<Object> rowData=new Vector<>();
						rowData.add(i);
						rowData.add(equip.getId());
						rowData.add(equip.getName());
						rowData.add(equip.getType());
						rowData.add(equip.getSpecifications());
						rowData.add(equip.getDescription());
						rowData.add(equip.getEquiomentState());
						equipmentmodel.addRow(rowData);
						i++;
					}
				}
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return equipmentmodel;
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
