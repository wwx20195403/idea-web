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
import controllers.UserController;
import entity.Equipment;
import entity.EquipmentType;
import entity.Factory;
import mainframe.SyperAdmin;
import mainframe.dialog.EditEquipmentDialog;
import mainframe.dialog.EditEquipmentTypeDialog;
import utils.SmallTool;


public class EquipmentManageFrame extends JFrame {

	 private static EquipmentManageFrame instance;
	 public static EquipmentManageFrame getInstance() {  
   if (instance == null) {  
       instance = new EquipmentManageFrame();  
   }  
   return instance; 
   } 
	 private String[] tableHead=new String[] {"设备序号","设备编号","设备名称","设备类型","设备规格","设备描述","设备状态","租借状态","所属工厂"};
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
	private JButton btnNewButton_1;
	private JToolBar toolBar ;
	private JButton btnNewButton_2;
	private JButton btnNewButton_3;
	private JTextField textField; 
	private JButton btnNewButton_5;		
	private JButton btnNewButton_6;	
	private JButton btnNewButton_4;

	/**
	 * Create the frame.
	 */
	public EquipmentManageFrame() {
		setTitle("设备管理—产能中心");
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
		btnNewButton = new JButton("新建");		
		btnNewButton_1 = new JButton("删除");
		btnNewButton_2=new JButton("修改");
		btnNewButton_3=new JButton("检索");
		btnNewButton_5=new JButton("远程开机");
		btnNewButton_6=new JButton("远程关机");
	
		textField=new JTextField();
		textField.setToolTipText("请输入账号");
		toolBar.add(btnNewButton);
		toolBar.add(btnNewButton_1);
		toolBar.add(btnNewButton_2);
		toolBar.add(btnNewButton_5);
		toolBar.add(btnNewButton_6);
		toolBar.add(textField);
		toolBar.add(btnNewButton_3);
		btnNewButton_4 =createToolButton("返回超级管理员界面", "back.png");
		btnNewButton_4.setForeground(Color.white);
		btnNewButton_4.setBackground(new Color(0,130,228));
		contentPane.add(btnNewButton_4, BorderLayout.SOUTH);
		updateequipmentList();
		btnNewButton.addActionListener((e)->{
			try {
				List<EquipmentType> s=new EquipmentTypeController("EquipmentTypeService").showEquipmentType();
				int size=0;
			for(EquipmentType ep:s) {
				if(ep.getIsAvailable().equals("true"))size++;
			}	
			if(size==0) {
				JOptionPane.showMessageDialog(null, "无可创建的设备类型！");
			}else {
				EditEquipmentDialog a=new EditEquipmentDialog(EquipmentManageFrame.getInstance(), equipmentController,null);
				a.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosed(WindowEvent e) {
						// TODO Auto-generated method stub
						updateequipmentList();
					}
				});
			}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		});
		btnNewButton_3.addActionListener(e->{
			String name=textField.getText();
			int m=0;
			for( int i=0;i<equipmentList.size();i++) {
				if(equipmentList.get(i).getIsAvailable().equals("true")) {
					if(equipmentList.get(i).getName().equals(name)) {
						equipments.setRowSelectionInterval(m, m);
						equipments.scrollRectToVisible(equipments.getCellRect(m, 0, true));
						equipments.setSelectionBackground(Color.LIGHT_GRAY);//选中行设置背景色								

					}
					m++;
				}
			}
		});

		btnNewButton_1.addActionListener((e)->{
			
			Equipment u=getChange();
			if(u==null) {}
			else {
				if(u.getEquiomentState().equals("生产中")) {
					JOptionPane.showMessageDialog(this, "生产中无法删除");
				}else {
					int a=onDelete();
					updateequipmentList();
					if(a!=-1)equipments.setRowSelectionInterval(a,a);
				}
			}
		});
		btnNewButton_2.addActionListener((e)->{
			Equipment u=getChange();
			if(u==null) {}
			else {
				if(u.getEquiomentState().equals("生产中")) {
					JOptionPane.showMessageDialog(this, "生产中无法修改");
				}else {
					EditEquipmentDialog a=new EditEquipmentDialog(EquipmentManageFrame.getInstance(), equipmentController, u);
					a.addWindowListener(new WindowAdapter() {
						@Override
						public void windowClosed(WindowEvent e) {
							// TODO Auto-generated method stub
							updateequipmentList();
						}
					});
				}
			}
			
		});
		btnNewButton_4.addActionListener(e->{
			
			// TODO Auto-generated method stub
			SyperAdmin a=SyperAdmin.getInstance();
			a.setVisible(true);
			dispose();
		
		});
		
		btnNewButton_5.addActionListener(e->{
			int[] rows = equipments.getSelectedRows();
			if(rows.length == 0) {
				
			}else {
				for(int i= rows.length-1; i>=0; i--)
				{
					String id=(String)equipments.getValueAt(rows[i], 1);
					try {
						Equipment u=equipmentController.searchEquipment(id);
						if(u.getEquiomentState().equals("关闭中")){
							u.setEquiomentState("闲置中");
							if(equipmentController.changeEquipment(u)) {
								JOptionPane.showMessageDialog(this, "开启成功！");
								
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
		btnNewButton_6.addActionListener(e->{
			int[] rows = equipments.getSelectedRows();
			if(rows.length == 0) {
				
			}else {
				for(int i= rows.length-1; i>=0; i--)
				{
					String id=(String)equipments.getValueAt(rows[i], 1);
					try {
						Equipment u=equipmentController.searchEquipment(id);
						if(u.getEquiomentState().equals("闲置中")){
							u.setEquiomentState("关闭中");
							if(equipmentController.changeEquipment(u)) {
								JOptionPane.showMessageDialog(this, "关闭成功！");
								
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
				Vector<Object> rowData=new Vector<>();
				rowData.add(i);
				rowData.add(equip.getId());
				rowData.add(equip.getName());
				rowData.add(equip.getType());
				rowData.add(equip.getSpecifications());
				rowData.add(equip.getDescription());
				rowData.add(equip.getEquiomentState());
				rowData.add(equip.getIsRent());
				rowData.add(SmallTool.userId_FactotyID(equip.getNowBelong()));
				equipmentmodel.addRow(rowData);
				i++; }
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return equipmentmodel;
	}

	private int onDelete()
	{
		// 获取选中的行的索引
		int[] rows = equipments.getSelectedRows();
		if(rows.length == 0)return -1;
				
		// 弹出对话框确认
		int select = JOptionPane.showConfirmDialog(this, "是否确认删除?", "确认", JOptionPane.YES_NO_OPTION);
		if(select != 0)	return rows[0]; // 0号按钮是'确定'按钮

		// 技巧：从后往前删除
		for(int i= rows.length-1; i>=0; i--)
		{
			String id=(String)equipments.getValueAt(rows[i], 1);
			try {
				Equipment a=equipmentController.searchEquipment(id);
				if(a.getBelong().equals("0")) {
					if(a.getNowBelong().equals("0")) {
						if(equipmentController.deleteEquipment(id)){
							JOptionPane.showMessageDialog(this, "删除成功！");
							}else{
							JOptionPane.showMessageDialog(this, "删除失败！");
							}
					}else {
						JOptionPane.showMessageDialog(this, "已借出设备不可删除！");
					}
				}else {
					JOptionPane.showMessageDialog(this, "不可删除工厂自有的设备！");
				}			
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return 0;
	}
	private Equipment getChange(){
		int[] rows = equipments.getSelectedRows();
		for(int i= rows.length-1; i>=0; i--)
		{
			String s=(String)equipmentmodel.getValueAt(rows[i], 1);
			try {
				Equipment u=equipmentController.searchEquipment(s);
					if(u.getBelong().equals("0")) {
						return u;
					}else {
						JOptionPane.showMessageDialog(this, "不可修改工厂自有的设备！");
					}
				
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
