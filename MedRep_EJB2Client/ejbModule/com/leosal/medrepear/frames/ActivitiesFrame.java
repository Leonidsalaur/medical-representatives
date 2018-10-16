package com.leosal.medrepear.frames;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import net.miginfocom.swing.MigLayout;

import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.swingbinding.JListBinding;
import org.jdesktop.swingbinding.SwingBindings;

import com.leosal.leotools.tools.DateToString;
import com.leosal.medrepear.dto.EventDTO;

public class ActivitiesFrame extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ActivitiesFrame frame = new ActivitiesFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private List<EventDTO> events=new ArrayList<EventDTO>();
	@SuppressWarnings("unused")
	private EventDTO ev=new EventDTO();
	private JList<EventDTO> list;
	

	/**
	 * Create the frame.
	 */
	public ActivitiesFrame(){
		setMaximizable(true);
		
		//EventDTO e = new EventDTO();
		//e.setDate(new Date());
		//events.add(e);
		//events=ConnectionManager.getInstance().getEvents(new Date(0), new Date());
		//System.out.println(events.get(0).getInitiator().getLogin());
		setClosable(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new MigLayout("", "[][grow]", "[]"));
		
		JPanel panel_2 = new JPanel();
		getContentPane().add(panel_2, BorderLayout.SOUTH);
		
		JPanel panel_3 = new JPanel();
		getContentPane().add(panel_3, BorderLayout.EAST);
		panel_3.setLayout(new MigLayout("", "[]", "[]"));
		
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.WEST);
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new MigLayout("", "[160.00]", "[grow]"));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panel_1.add(scrollPane_1, "cell 0 0,grow");
		
		list = new JList<EventDTO>();
		scrollPane_1.setViewportView(list);
		list.setMinimumSize(new Dimension(200, 200));
		initDataBindings();

	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected void initDataBindings() {
		JListBinding<EventDTO, List<EventDTO>, JList> jListBinding = SwingBindings.createJListBinding(UpdateStrategy.READ, events, list);
		//
		BeanProperty<EventDTO, Date> eventDTOBeanProperty = BeanProperty.create("date");
		JListBinding<EventDTO, List<EventDTO>, JList>.DetailBinding 
		jListDetail = jListBinding.setDetailBinding(eventDTOBeanProperty);
		jListDetail.setConverter(new DateToString());
		//
		jListBinding.bind();
	}
}
