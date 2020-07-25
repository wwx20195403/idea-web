
package mainframe.frames;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controllers.EquipmentController;
import controllers.Productcontrollers;
import controllers.ScheduleServiceController;
import entity.Order;
import entity.Product;
import entity.Schedule;
import mainframe.dialog.ScheduleDialog;
import utils.SmallTool;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import entity.Equipment;

public class ScheduleFrame extends JFrame {
	private static JFrame jframe;
	public static void setJframe(JFrame a) {
		jframe=a;
	}
	
	private static String userID;
	public static void setUserID(String a) {
			userID=a;
	}
	private JPanel contentPane;
	private JTextField textField;
	private ScheduleServiceController scheduleServiceController=new ScheduleServiceController("ScheduleService");
	   private String[] tableHead=new String[] {"设备编号","开始时间","结束时间"};
	   private DefaultTableModel schedulemodel=new DefaultTableModel();
		private JTable schedules=new JTable(schedulemodel){
			public boolean isCellEditable(int rowIndex, int ColIndex){
			     return false;
				}
	   }; 
	   private Order ord;
	   private List<Schedule> schedulelist=null;
	/**
	 * Create the frame.
	 */
	public ScheduleFrame(Order order) {
		ord=order;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 691, 469);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("订单编号");
		lblNewLabel.setBounds(37, 25, 54, 15);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(101, 22, 288, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		textField.setText(order.getId());
		textField.setEditable(false);
		schedules.setFillsViewportHeight(true);
		schedules.setRowSelectionAllowed(true);
		schedules.setRowHeight(30);
		schedules.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//
		
		
		
		JButton btnNewButton = new JButton("添加设备");
		btnNewButton.setBounds(24, 73, 99, 23);
		contentPane.add(btnNewButton);
		
		btnNewButton.addActionListener(e->{
			
				List<Equipment> s;
				try {
					s = new EquipmentController("EquipmentService").showEquipment();
					int size=0;
					for(Equipment ep  :s) {
						if(ep.getNowBelong().equals(userID)) {
							if(ep.getIsAvailable().equals("true")) {
								if(ep.getEquiomentState().equals("闲置中")) {
									size++;
								}
							}
						}
					}
					
				if(size==0) {
					JOptionPane.showMessageDialog(null, "无可用设备！");
				}else {
					ScheduleDialog sc=new ScheduleDialog(null,order);
					sc.setVisible(true);
					sc.addWindowListener(new WindowAdapter(){
						
							@Override
							public void windowClosed(WindowEvent e) {
								// TODO Auto-generated method stub
								updateSchedule();
		
							}
					});	
				}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}


			
		});
		JButton btnNewButton_1 = new JButton("删除设备");
		btnNewButton_1.setBounds(133, 73, 99, 21);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("确定");
		btnNewButton_2.setBounds(541, 397, 93, 23);
		contentPane.add(btnNewButton_2);
		
		JScrollPane scrollPane = new JScrollPane(schedules);
		scrollPane.setBounds(34, 112, 588, 275);
		contentPane.add(scrollPane);
		
		btnNewButton_2.addActionListener(e->{
			FactorySchedFrame.setUserID(userID);
			FactorySchedFrame a=FactorySchedFrame.getInstance();
			a.updateorderList();
			a.setVisible(true);
			dispose();
		});

	}
	
	
	public void updateSchedule() {
		schedules.setModel(getDefaultTableModel());
	}
	public DefaultTableModel getDefaultTableModel() {
		try {
			schedulemodel=new DefaultTableModel(null, tableHead);
			schedulelist=scheduleServiceController.showSchedule();
			int i=1;
			for(Schedule sc:  schedulelist) {
				if(sc.getOrderID().equals(ord.getId())) {
						// {"设备编号","开始时间","结束时间"};
						Vector<Object> rowData=new Vector<>();
						rowData.add(sc.getEqipmentID());
						rowData.add(sc.getStarttime());
						rowData.add(sc.getEndtime());
						i++;
						schedulemodel.addRow(rowData);
					
				}
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return schedulemodel;
	}
}
