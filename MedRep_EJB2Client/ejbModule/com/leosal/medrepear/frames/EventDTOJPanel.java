package com.leosal.medrepear.frames;

import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import net.miginfocom.swing.MigLayout;

import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.BindingGroup;
import org.jdesktop.beansbinding.Bindings;
import org.jdesktop.beansbinding.ELProperty;
import org.jdesktop.swingbinding.JListBinding;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;

import com.leosal.leotools.exceptions.UserNotAuthorisedException;
import com.leosal.leotools.widgets.MultiSelectPane;
import com.leosal.medrepear.dto.AdvertDTO;
import com.leosal.medrepear.dto.EventDTO;
import com.leosal.medrepear.dto.EventGiftDTO;
import com.leosal.medrepear.dto.EventPersonDTO;
import com.leosal.medrepear.dto.EventProductDTO;
import com.leosal.medrepear.dto.EventTypeDTO;
import com.leosal.medrepear.dto.PersonDTO;
import com.leosal.medrepear.dto.ProductDTO;
import com.leosal.medrepear.service.RestManager;
import com.leosal.medrepear.util.UserPreferences;
import com.toedter.calendar.JDateChooser;

public class EventDTOJPanel extends JPanel {

	/**
	 * 
	 */
	//===================================
	private static final long serialVersionUID = 1L;
	
	private BindingGroup m_bindingGroup;
	private com.leosal.medrepear.dto.EventDTO eventDTO = new com.leosal.medrepear.dto.EventDTO();
	private JTextField commentJTextField;
	private JTextField messageJTextField;
	private JDateChooser dateChooser;
	private JComboBox<EventTypeDTO> comboEventTypes;
	private JLabel lblAdverts;
	private JLabel lblSamples;
	private JScrollPane scrollPane;
	private JTable tableAdverts;
	private JScrollPane scrollPane_1;
	private JTable tableSamples;
	private JLabel lblParticipants;
	private JList<? extends Object> list;
	private JToolBar toolBar;
	private JButton btnAddAdvert;
	private JButton buttonRemoveAdvert;
	private JToolBar toolBar_1;
	private JButton button;
	private JToolBar toolBar_2;
	private JButton buttonAddParticipant;
	private JButton buttonRemoveParticipant;
	private JButton buttonAddSample;
	private JPanel panel;
	private JButton button_1;
	private JTextField txtInitiator;
	
	private List<ProductDTO> allProducts;
	private List<AdvertDTO> allAdvert;
	private List<EventTypeDTO> allEventTypes;
	private List<PersonDTO> allContacts;
	

	public EventDTOJPanel(com.leosal.medrepear.dto.EventDTO newEventDTO) throws UserNotAuthorisedException {
		this();
		if(newEventDTO.getEventType()==null && allEventTypes.size()>0)
			newEventDTO.setEventType(allEventTypes.get(UserPreferences.getDefaultEventType()));
		setEventDTO(newEventDTO);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public EventDTOJPanel() throws UserNotAuthorisedException {
		setLayout(new MigLayout("", "[54px][150.00px:150.00px:150.00px,grow][100px:n:100px][61px][150px:150px:150px][100px:n:100px]", "[20px][20px,grow][25px][143px][:25px:25][143px][20px]"));
		
		allProducts = RestManager.getInstance().getProducts();
		allAdvert = RestManager.getInstance().getAdverts();
		allEventTypes = RestManager.getInstance().getEventTypes();
		allContacts = RestManager.getInstance().getContacts();
		
		JLabel dateLabel = new JLabel("Date:");
		add(dateLabel, "cell 0 0,alignx center,aligny center");
		
		dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("dd/MM/yyyy");
		add(dateChooser, "cell 1 0,grow");
		
		JLabel eventTypeLabel = new JLabel("EventType:");
		add(eventTypeLabel, "cell 3 0,alignx right,aligny center");
		
		comboEventTypes = new JComboBox<EventTypeDTO>();
		
		for(EventTypeDTO et:allEventTypes){
			comboEventTypes.addItem(et);
			//System.out.println(et);
		}
		add(comboEventTypes, "cell 4 0,growx,aligny center");
		
				JLabel initiatorLabel = new JLabel("Initiator:");
				add(initiatorLabel, "cell 0 1,alignx center,aligny center");
				
				panel = new JPanel();
				add(panel, "cell 1 1 2 1,grow");
				panel.setLayout(new BorderLayout(0, 0));
				
				button_1 = new JButton("....");
				button_1.setVisible(false);
				button_1.setMargin(new Insets(0, 0, 0, 0));
				panel.add(button_1, BorderLayout.EAST);
				
				txtInitiator = new JTextField();
				txtInitiator.setEnabled(false);
				panel.add(txtInitiator, BorderLayout.CENTER);
				txtInitiator.setColumns(10);
		
				JLabel messageLabel = new JLabel("Message:");
				add(messageLabel, "cell 3 1,alignx center,aligny center");
						
								messageJTextField = new JTextField();
								add(messageJTextField, "cell 4 1 2 1,growx,aligny center");
						
						toolBar = new JToolBar();
						toolBar.setFloatable(false);
						add(toolBar, "cell 0 2,alignx center,aligny center");
						
						btnAddAdvert = new JButton("");
						btnAddAdvert.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								addAdverts();
							}
						});
						btnAddAdvert.setToolTipText("Add advert");
						btnAddAdvert.setIcon(new ImageIcon(EventDTOJPanel.class.getResource("/com/leosal/leotools/graphics/list-add.png")));
						toolBar.add(btnAddAdvert);
						
						buttonRemoveAdvert = new JButton("");
						buttonRemoveAdvert.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								removeAdvert();
							}
						});
						buttonRemoveAdvert.setToolTipText("Remove selected advert");
						buttonRemoveAdvert.setIcon(new ImageIcon(EventDTOJPanel.class.getResource("/com/leosal/leotools/graphics/edit-delete.png")));
						toolBar.add(buttonRemoveAdvert);
						
						lblAdverts = new JLabel("Adverts:");
						add(lblAdverts, "cell 1 2,alignx center,aligny center");
						
						toolBar_2 = new JToolBar();
						toolBar_2.setFloatable(false);
						add(toolBar_2, "cell 3 2,alignx center,aligny center");
						
						buttonAddParticipant = new JButton("");
						buttonAddParticipant.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								addContacts();
							}
						});
						buttonAddParticipant.setToolTipText("Add contact");
						buttonAddParticipant.setIcon(new ImageIcon(EventDTOJPanel.class.getResource("/com/leosal/leotools/graphics/list-add.png")));
						toolBar_2.add(buttonAddParticipant);
						
						buttonRemoveParticipant = new JButton("");
						buttonRemoveParticipant.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								removeContacts();
							}
						});
						buttonRemoveParticipant.setToolTipText("Remove selected participant");
						buttonRemoveParticipant.setIcon(new ImageIcon(EventDTOJPanel.class.getResource("/com/leosal/leotools/graphics/edit-delete.png")));
						toolBar_2.add(buttonRemoveParticipant);
						
						lblParticipants = new JLabel("Participants:");
						add(lblParticipants, "cell 4 2,alignx center,aligny center");
						
						scrollPane = new JScrollPane();
						add(scrollPane, "cell 0 3 3 1,grow");
						
						tableAdverts = new JTable();
						scrollPane.setViewportView(tableAdverts);
						
						list = new JList();
						add(list, "cell 3 3 3 3,grow");
						
						toolBar_1 = new JToolBar();
						toolBar_1.setFloatable(false);
						add(toolBar_1, "cell 0 4,alignx center,aligny center");
						
						buttonAddSample = new JButton("");
						buttonAddSample.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								addSamples();
							}
						});
						buttonAddSample.setToolTipText("Add sample");
						buttonAddSample.setIcon(new ImageIcon(EventDTOJPanel.class.getResource("/com/leosal/leotools/graphics/list-add.png")));
						toolBar_1.add(buttonAddSample);
						
						button = new JButton("");
						button.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								removeSamples();
							}
						});
						button.setToolTipText("Remove selected sample");
						button.setIcon(new ImageIcon(EventDTOJPanel.class.getResource("/com/leosal/leotools/graphics/edit-delete.png")));
						toolBar_1.add(button);
						
						lblSamples = new JLabel("Samples:");
						add(lblSamples, "cell 1 4,alignx center,aligny center");
						
						scrollPane_1 = new JScrollPane();
						add(scrollPane_1, "cell 0 5 3 1,grow");
						
						tableSamples = new JTable();
						scrollPane_1.setViewportView(tableSamples);
				
						JLabel commentLabel = new JLabel("Comment:");
						add(commentLabel, "cell 0 6,alignx center,aligny center");
		
				commentJTextField = new JTextField();
				add(commentJTextField, "cell 1 6 5 1,growx,aligny center");

		if (eventDTO != null) {
			m_bindingGroup = initDataBindings();
		}
	}

	protected void removeContacts() {
		// TODO Auto-generated method stub
		
	}

	protected void addContacts() {
		// TODO Auto-generated method stub
		MultiSelectPane<PersonDTO> panel = new MultiSelectPane<PersonDTO>(allContacts, false);
		panel.setPreferedViewportSize(300, 500);
		int resp = JOptionPane.showConfirmDialog(null, panel, "Select contacts", JOptionPane.OK_CANCEL_OPTION);
		if(resp==JOptionPane.OK_OPTION){
			for(int ids:panel.getSelectedIndices()){
				PersonDTO pr = allContacts.get(ids);
				if(!eventDTO.containsParticipant(pr)){
					EventPersonDTO ep = new EventPersonDTO();
					ep.setEvent(eventDTO.getId());
					ep.setPerson(pr);
					eventDTO.addParticipant(ep);
				}
			}
			refresh();
		}
	}

	protected void removeSamples() {
		//  Auto-generated method stub
		if(tableSamples.getSelectedRow()<0) return;
		ArrayList<Integer> arrayIndexes = new ArrayList<Integer>();
		for(int tInd:tableSamples.getSelectedRows()){
			arrayIndexes.add(tableSamples.convertRowIndexToModel(tInd));	
		}
		Collections.sort(arrayIndexes, new Comparator<Integer>() {

			@Override
			public int compare(Integer lhs, Integer rhs) {
				return -lhs.compareTo(rhs);
			}
			
		});
		for(Integer i:arrayIndexes){
			eventDTO.getProducts().remove(i);
		}
		refresh();
		
	}

	protected void addSamples() {
		//  Auto-generated method stub
		MultiSelectPane<ProductDTO> panel = new MultiSelectPane<ProductDTO>(allProducts, false);
		panel.setPreferedViewportSize(300, 500);
		int resp = JOptionPane.showConfirmDialog(null, panel, "Select products", JOptionPane.OK_CANCEL_OPTION);
		if(resp==JOptionPane.OK_OPTION){
			for(int ids:panel.getSelectedIndices()){
				ProductDTO pr = allProducts.get(ids);
				if(!eventDTO.containsProduct(pr)){
					EventProductDTO epr = new EventProductDTO();
					epr.setProduct(pr);
					epr.setQuantity(1);
					eventDTO.addProduct(epr);
				}
			}
			refresh();
		}
		
	}

	protected void removeAdvert() {
		// TODO Auto-generated method stub
		if(tableAdverts.getSelectedRow()<0) return;
		ArrayList<Integer> arrayIndexes = new ArrayList<Integer>();
		for(int tInd:tableAdverts.getSelectedRows()){
			arrayIndexes.add(tableAdverts.convertRowIndexToModel(tInd));	
		}
		Collections.sort(arrayIndexes, new Comparator<Integer>() {

			@Override
			public int compare(Integer lhs, Integer rhs) {
				return -lhs.compareTo(rhs);
			}
			
		});
		for(Integer i:arrayIndexes){
			eventDTO.getGifts().remove(i);
		}
		refresh();
		
	}

	protected void addAdverts() {
		//  Auto-generated method stub
		MultiSelectPane<AdvertDTO> panel = new MultiSelectPane<AdvertDTO>(allAdvert, false);
		panel.setPreferedViewportSize(100, 500);
		int resp = JOptionPane.showConfirmDialog(null, panel, "Select adverts", JOptionPane.OK_CANCEL_OPTION);
		if(resp==JOptionPane.OK_OPTION){
			for(int ids:panel.getSelectedIndices()){
				AdvertDTO pr = allAdvert.get(ids);
				if(!eventDTO.containsGift(pr)){
					EventGiftDTO epr = new EventGiftDTO();
					epr.setAdvert(pr);
					epr.setQuantity(1);
					eventDTO.addGift(epr);
				}
			}
			refresh();
		}
	}

	public com.leosal.medrepear.dto.EventDTO getEventDTO() {
		return eventDTO;
	}
	
	public int getEventTypeIndex(){
		return comboEventTypes.getSelectedIndex();
	}

	public void setEventDTO(com.leosal.medrepear.dto.EventDTO newEventDTO) {
		setEventDTO(newEventDTO, true);
	}

	public void setEventDTO(com.leosal.medrepear.dto.EventDTO newEventDTO,
			boolean update) {
		eventDTO = newEventDTO;
		if (update) {
			if (m_bindingGroup != null) {
				m_bindingGroup.unbind();
				m_bindingGroup = null;
			}
			if (eventDTO != null) {
				m_bindingGroup = initDataBindings();
			}
		}
	}
	private void refresh(){
		if (m_bindingGroup != null) {
			m_bindingGroup.unbind();
			m_bindingGroup = null;
		}
		if (eventDTO != null) {
			m_bindingGroup = initDataBindings();
		}
	}
	@SuppressWarnings("rawtypes")
	protected BindingGroup initDataBindings() {
		BeanProperty<EventDTO, Date> eventDTOBeanProperty = BeanProperty.create("date");
		BeanProperty<JDateChooser, Date> jDateChooserBeanProperty = BeanProperty.create("date");
		AutoBinding<EventDTO, Date, JDateChooser, Date> autoBinding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, eventDTO, eventDTOBeanProperty, dateChooser, jDateChooserBeanProperty);
		autoBinding.bind();
		//
		BeanProperty<EventDTO, PersonDTO> eventDTOBeanProperty_2 = BeanProperty.create("initiator");
		ELProperty<EventDTO, Object> eventDTOEvalutionProperty = ELProperty.create(eventDTOBeanProperty_2, "${firstname} ${lastname}");
		BeanProperty<JTextField, String> jTextFieldBeanProperty = BeanProperty.create("text");
		AutoBinding<EventDTO, Object, JTextField, String> autoBinding_2 = Bindings.createAutoBinding(UpdateStrategy.READ, eventDTO, eventDTOEvalutionProperty, txtInitiator, jTextFieldBeanProperty);
		autoBinding_2.bind();
		//
		BeanProperty<EventDTO, String> eventDTOBeanProperty_3 = BeanProperty.create("message");
		BeanProperty<JTextField, String> jTextFieldBeanProperty_1 = BeanProperty.create("text");
		AutoBinding<EventDTO, String, JTextField, String> autoBinding_3 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, eventDTO, eventDTOBeanProperty_3, messageJTextField, jTextFieldBeanProperty_1);
		autoBinding_3.bind();
		//
		BeanProperty<EventDTO, String> eventDTOBeanProperty_6 = BeanProperty.create("comment");
		BeanProperty<JTextField, String> jTextFieldBeanProperty_2 = BeanProperty.create("text");
		AutoBinding<EventDTO, String, JTextField, String> autoBinding_6 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, eventDTO, eventDTOBeanProperty_6, commentJTextField, jTextFieldBeanProperty_2);
		autoBinding_6.bind();
		//
		BeanProperty<EventDTO, List<EventGiftDTO>> eventDTOBeanProperty_4 = BeanProperty.create("gifts");
		JTableBinding<EventGiftDTO, EventDTO, JTable> jTableBinding = SwingBindings.createJTableBinding(UpdateStrategy.READ_WRITE, eventDTO, eventDTOBeanProperty_4, tableAdverts);
		//
		BeanProperty<EventGiftDTO, String> eventGiftDTOBeanProperty = BeanProperty.create("advert.name");
		jTableBinding.addColumnBinding(eventGiftDTOBeanProperty).setColumnName("Advert").setEditable(false);
		//
		BeanProperty<EventGiftDTO, Integer> eventGiftDTOBeanProperty_1 = BeanProperty.create("quantity");
		jTableBinding.addColumnBinding(eventGiftDTOBeanProperty_1).setColumnName("Quantity").setEditable(false);
		//
		jTableBinding.bind();
		//
		BeanProperty<EventDTO, List<EventProductDTO>> eventDTOBeanProperty_5 = BeanProperty.create("products");
		JTableBinding<EventProductDTO, EventDTO, JTable> jTableBinding_1 = SwingBindings.createJTableBinding(UpdateStrategy.READ_WRITE, eventDTO, eventDTOBeanProperty_5, tableSamples);
		//
		BeanProperty<EventProductDTO, String> eventProductDTOBeanProperty = BeanProperty.create("product.name");
		jTableBinding_1.addColumnBinding(eventProductDTOBeanProperty).setColumnName("Product").setEditable(false);
		//
		BeanProperty<EventProductDTO, Integer> eventProductDTOBeanProperty_1 = BeanProperty.create("quantity");
		jTableBinding_1.addColumnBinding(eventProductDTOBeanProperty_1).setColumnName("Quantity").setEditable(false);
		//
		jTableBinding_1.bind();
		//
		BeanProperty<EventDTO, List<EventPersonDTO>> eventDTOBeanProperty_7 = BeanProperty.create("participants");
		JListBinding<EventPersonDTO, EventDTO, JList> jListBinding = SwingBindings.createJListBinding(UpdateStrategy.READ, eventDTO, eventDTOBeanProperty_7, list);
		//
		ELProperty<EventPersonDTO, Object> eventPersonDTOEvalutionProperty = ELProperty.create("${person.firstname} ${person.lastname}");
		jListBinding.setDetailBinding(eventPersonDTOEvalutionProperty);
		//
		jListBinding.bind();
		//
		BeanProperty<EventDTO, EventTypeDTO> eventDTOBeanProperty_1 = BeanProperty.create("eventType");
		BeanProperty<JComboBox, Object> jComboBoxBeanProperty = BeanProperty.create("selectedItem");
		AutoBinding<EventDTO, EventTypeDTO, JComboBox, Object> autoBinding_1 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, eventDTO, eventDTOBeanProperty_1, comboEventTypes, jComboBoxBeanProperty);
		autoBinding_1.bind();
		//
		BindingGroup bindingGroup = new BindingGroup();
		//
		bindingGroup.addBinding(autoBinding);
		bindingGroup.addBinding(autoBinding_2);
		bindingGroup.addBinding(autoBinding_3);
		bindingGroup.addBinding(autoBinding_6);
		bindingGroup.addBinding(jTableBinding);
		bindingGroup.addBinding(jTableBinding_1);
		bindingGroup.addBinding(jListBinding);
		bindingGroup.addBinding(autoBinding_1);
		return bindingGroup;
	}
}
