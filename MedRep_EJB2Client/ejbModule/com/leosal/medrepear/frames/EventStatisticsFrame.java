package com.leosal.medrepear.frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PrinterException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTable.PrintMode;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;

import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.Bindings;
import org.jdesktop.beansbinding.ObjectProperty;
import org.jdesktop.swingbinding.JComboBoxBinding;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;

import com.leosal.leotools.exceptions.UserNotAuthorisedException;
import com.leosal.leotools.tools.DateToString;
import com.leosal.leotools.widgets.MakeChangesToolbar;
import com.leosal.medrepear.dto.EventDTO;
import com.leosal.medrepear.dto.EventPersonDTO;
import com.leosal.medrepear.dto.InstitutionDTO;
import com.leosal.medrepear.dto.MedrepFilter;
import com.leosal.medrepear.dto.MedrepStatistics;
import com.leosal.medrepear.dto.PersonDTO;
import com.leosal.medrepear.dto.PersonPrivDTO;
import com.leosal.medrepear.dto.SpecialtyDTO;
import com.leosal.medrepear.service.RestManager;
import com.leosal.medrepear.util.UserPreferences;
import com.toedter.calendar.JDateChooser;

public class EventStatisticsFrame extends JInternalFrame {
	
	private static final int YEAR_LEVEL = 1;
	private static final int MONTH_LEVEL = 2;
	private static final int DAY_LEVEL = 3;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<MedrepStatistics> statistics = new ArrayList<MedrepStatistics>();;
	private JTable tableYears;
	private JTable tableMonths;
	private JTable tableDays;
	private JTable tableActivities;
	
	private List<EventDTO> events=new ArrayList<EventDTO>();
	private List<EventDTO> modifiedEvents = new ArrayList<EventDTO>();
	private List<EventDTO> deletedEvents = new ArrayList<EventDTO>();
	
	private List<PersonDTO> repsList;
	private List<SpecialtyDTO> specialtiesList;
	private List<String> categoriesList;
	
	private MedrepStatistics selectedYear, selectedMonth, selectedDay;
	private int selectedLevel=0;
	AutoBinding<JTable, MedrepStatistics, MedrepStatistics, MedrepStatistics> monthBinding;
	JTableBinding<MedrepStatistics, JTable, JTable> jTableBinding_1;
	private JDateChooser dateFrom;
	private JDateChooser dateTo;
	private JTextField txtOrganisation;
	private JTextField txtContact;
	private JTextField txtMessage;
	private JComboBox<String> comboCategory;
	private JComboBox<SpecialtyDTO> comboSpecialties;
	private JComboBox<PersonDTO> comboRep;
	private MedrepStatistics totals;
	private JLabel txtTotalignore;
	private JLabel txtTotalMonitor;
	private JLabel txtTotalSafe;
	private JLabel txtTotalGrow;
	private JLabel txtTotalOrganizations;
	private JLabel txtTotalVisits;
	private JLabel txtTotalContacts;
	private JLabel txtTotalDays;
	private JLabel txtTotalReps;
	private JLabel txtAveragePerDay;
	private JLabel lblRepresentative;
	private JLabel lblTotalRepresentatives;
	private MakeChangesToolbar toolbarActivities;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EventStatisticsFrame frame = new EventStatisticsFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public EventStatisticsFrame() {
		try {
			getLists();
		} catch (UserNotAuthorisedException e1) {
			e1.printStackTrace();
		}
		setTitle("General report");
		setClosable(true);
		setResizable(true);
		setMaximizable(true);
		
		setBounds(100, 100, 907, 715);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new MigLayout("", "[][170.00][][170.00][37.00]", "[grow,fill]"));
		
		JLabel lblFrom = new JLabel("From:");
		panel.add(lblFrom, "cell 0 0");
		
		dateFrom = new JDateChooser();
		dateFrom.setDateFormatString("dd/MM/yyyy");
		dateFrom.setDate(new Date());
		panel.add(dateFrom, "cell 1 0,growx");
		
		JLabel lblTo = new JLabel(" to ");
		panel.add(lblTo, "cell 2 0");
		
		dateTo = new JDateChooser();
		dateTo.setDateFormatString("dd/MM/yyyy");
		dateTo.setDate(new Date());
		panel.add(dateTo, "cell 3 0,grow");
		
		JButton btnProceed = new JButton("");
		btnProceed.setMargin(new Insets(0, 0, 0, 0));
		btnProceed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					proceed();
				} catch (UserNotAuthorisedException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnProceed.setIcon(new ImageIcon(EventStatisticsFrame.class.getResource("/com/leosal/leotools/graphics/proceed.png")));
		panel.add(btnProceed, "cell 4 0,alignx left");
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.WEST);
		
		JPanel panel_2 = new JPanel();
		getContentPane().add(panel_2, BorderLayout.SOUTH);
		
		JPanel panel_3 = new JPanel();
		getContentPane().add(panel_3, BorderLayout.EAST);
		
		JPanel panel_4 = new JPanel();
		getContentPane().add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(new MigLayout("", "[300.00:313.00,grow]", "[][170.00:n:194.00][225.00,grow]"));
		
		JLabel lblDoubleClickOn = new JLabel("Double click on any statistics to view visit's information");
		lblDoubleClickOn.setHorizontalAlignment(SwingConstants.CENTER);
		lblDoubleClickOn.setOpaque(true);
		lblDoubleClickOn.setBackground(new Color(255, 255, 102));
		panel_4.add(lblDoubleClickOn, "cell 0 0,grow");
		
		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new TitledBorder(new LineBorder(new Color(128, 128, 128), 1, true), "General Statistics", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_4.add(panel_7, "cell 0 1,grow");
		panel_7.setLayout(new MigLayout("", "[300.00:313.00,grow][300.00:305.00,grow][5.00:300.00,grow]", "[170.00:n:194.00]"));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_7.add(scrollPane, "cell 0 0,grow");
		
		tableYears = new JTable();
		tableYears.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==2){
					refreshEvents(YEAR_LEVEL);
				}
			}
		});
		scrollPane.setViewportView(tableYears);
		tableYears.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tableYears.setFillsViewportHeight(true);
		tableYears.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableYears.setFont(tableYears.getFont().deriveFont(tableYears.getFont().getStyle() | Font.ITALIC, tableYears.getFont().getSize() - 1f));
		tableYears.getTableHeader().setFont(tableYears.getFont().deriveFont(tableYears.getTableHeader().getFont().getStyle() | Font.ITALIC, tableYears.getFont().getSize() - 1f));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panel_7.add(scrollPane_1, "cell 1 0,grow");
		
		tableMonths = new JTable();
		tableMonths.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==2){
					refreshEvents(MONTH_LEVEL);
				}
			}
		});
		scrollPane_1.setViewportView(tableMonths);
		tableMonths.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tableMonths.setFillsViewportHeight(true);
		tableMonths.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableMonths.setFont(tableYears.getFont().deriveFont(tableYears.getFont().getStyle() | Font.ITALIC, tableYears.getFont().getSize() - 1f));
		tableMonths.getTableHeader().setFont(tableYears.getFont().deriveFont(tableYears.getTableHeader().getFont().getStyle() | Font.ITALIC, tableYears.getFont().getSize() - 1f));
		
		
		JScrollPane scrollPane_2 = new JScrollPane();
		panel_7.add(scrollPane_2, "cell 2 0,grow");
		
		tableDays = new JTable();
		tableDays.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==2){
					refreshEvents(DAY_LEVEL);
				}
			}
		});
		scrollPane_2.setViewportView(tableDays);
		tableDays.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tableDays.setFillsViewportHeight(true);
		tableDays.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableDays.setFont(tableYears.getFont().deriveFont(tableYears.getFont().getStyle() | Font.ITALIC, tableYears.getFont().getSize() - 1f));
		tableDays.getTableHeader().setFont(tableYears.getFont().
				deriveFont(tableYears.getTableHeader().getFont().getStyle() | Font.ITALIC, tableYears.getFont().getSize() - 1f));
		
		
		JPanel panel_6 = new JPanel();
		panel_4.add(panel_6, "cell 0 2,grow");
		panel_6.setLayout(new MigLayout("", "[grow][305.00px:n]", "[][][grow][][]"));
		
		toolbarActivities = new MakeChangesToolbar() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			@Override
			public void saveChanges() {
				EventStatisticsFrame.this.saveChanges();
				
			}
			
			@Override
			public void removeItem() {
				EventStatisticsFrame.this.removeItem();
				
			}
			
			@Override
			public void refresh() {
				EventStatisticsFrame.this.refresh();
				
			}
			
			@Override
			public void modifyItem() {
				EventStatisticsFrame.this.modifyItem();
				
			}
			
			@Override
			public void addItem() {
				EventStatisticsFrame.this.addItem();
				
			}
			@Override
			public void print() {
				EventStatisticsFrame.this.printActivities();
			}
		};
		toolbarActivities.setFloatable(false);
		toolbarActivities.setPrintButton(true);
		panel_6.add(toolbarActivities, "cell 0 1,grow");
		
		JScrollPane scrollPane_3 = new JScrollPane();
		panel_6.add(scrollPane_3, "cell 0 2,grow");
		
		tableActivities = new JTable();
		scrollPane_3.setViewportView(tableActivities);
		tableActivities.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tableActivities.setFillsViewportHeight(true);
		tableActivities.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		panel_6.add(scrollPane_4, "cell 1 2,grow");
		
		JPanel panel_5 = new JPanel();
		scrollPane_4.setViewportView(panel_5);
		panel_5.setLayout(new MigLayout("", "[][grow]", "[][][][][][][][][][][][][][][][][][][]"));
		
		JLabel lblFilters = new JLabel("Filters");
		lblFilters.setHorizontalAlignment(SwingConstants.CENTER);
		lblFilters.setFont(lblFilters.getFont().deriveFont(lblFilters.getFont().getStyle() | Font.BOLD));
		panel_5.add(lblFilters, "cell 0 0 2 1,grow");
		
		lblRepresentative = new JLabel("Representative:");
		panel_5.add(lblRepresentative, "cell 0 1,alignx left");
		
		comboRep = new JComboBox<PersonDTO>();
		panel_5.add(comboRep, "cell 1 1,growx");
		
		JLabel lblOrganisation = new JLabel("Organisation:");
		panel_5.add(lblOrganisation, "cell 0 2,alignx left");
		
		txtOrganisation = new JTextField();
		panel_5.add(txtOrganisation, "cell 1 2,growx");
		txtOrganisation.setColumns(10);
		
		JLabel lblContact = new JLabel("Contact:");
		panel_5.add(lblContact, "cell 0 3,alignx left");
		
		txtContact = new JTextField();
		panel_5.add(txtContact, "cell 1 3,growx");
		txtContact.setColumns(10);
		
		JLabel lblMessage = new JLabel("Message:");
		panel_5.add(lblMessage, "cell 0 4,alignx left");
		
		txtMessage = new JTextField();
		panel_5.add(txtMessage, "cell 1 4,growx");
		txtMessage.setColumns(10);
		
		JLabel lblCategory = new JLabel("Category:");
		panel_5.add(lblCategory, "cell 0 5,alignx left");
		
		comboCategory = new JComboBox<String>();
		panel_5.add(comboCategory, "cell 1 5,growx");
		
		JLabel lblSpecialty = new JLabel("Specialty:");
		panel_5.add(lblSpecialty, "cell 0 6,alignx left");
		
		comboSpecialties = new JComboBox<SpecialtyDTO>();
		panel_5.add(comboSpecialties, "cell 1 6,growx");
		
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		panel_5.add(toolBar, "cell 1 7,alignx right");
		
		JButton btnFilter = new JButton("");
		btnFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setFilter();
			}
		});
		btnFilter.setIcon(new ImageIcon(EventStatisticsFrame.class.getResource("/com/leosal/leotools/graphics/view-filter.png")));
		toolBar.add(btnFilter);
		
		JButton btnClearfilter = new JButton("");
		btnClearfilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeFilter();
			}
		});
		btnClearfilter.setIcon(new ImageIcon(EventStatisticsFrame.class.getResource("/com/leosal/leotools/graphics/view-filter_cancel.png")));
		toolBar.add(btnClearfilter);
		
		JLabel lblTotalStatistics = new JLabel("Resulting statistics:");
		lblTotalStatistics.setFont(lblTotalStatistics.getFont().deriveFont(lblTotalStatistics.getFont().getStyle() | Font.BOLD));
		panel_5.add(lblTotalStatistics, "cell 0 8 2 1,alignx center");
		
		JLabel lblAverageVisitsPer = new JLabel("Average visits per day:");
		lblAverageVisitsPer.setFont(lblAverageVisitsPer.getFont().deriveFont(lblAverageVisitsPer.getFont().getStyle() | Font.ITALIC, lblAverageVisitsPer.getFont().getSize() - 1f));
		panel_5.add(lblAverageVisitsPer, "cell 0 9,alignx left");
		
		txtAveragePerDay = new JLabel();
		txtAveragePerDay.setFont(txtAveragePerDay.getFont().deriveFont(txtAveragePerDay.getFont().getStyle() | Font.ITALIC, txtAveragePerDay.getFont().getSize() - 1f));
		txtAveragePerDay.setBackground(UIManager.getColor("Panel.background"));
		txtAveragePerDay.setBorder(null);
		panel_5.add(txtAveragePerDay, "cell 1 9,grow");
		
		lblTotalRepresentatives = new JLabel("Total Representatives:");
		lblTotalRepresentatives.setFont(lblTotalRepresentatives.getFont().deriveFont(lblTotalRepresentatives.getFont().getStyle() | Font.ITALIC, lblTotalRepresentatives.getFont().getSize() - 1f));
		panel_5.add(lblTotalRepresentatives, "cell 0 10,alignx left");
		
		txtTotalReps = new JLabel();
		txtTotalReps.setFont(txtTotalReps.getFont().deriveFont(txtTotalReps.getFont().getStyle() | Font.ITALIC, txtTotalReps.getFont().getSize() - 1f));
		txtTotalReps.setOpaque(false);
		txtTotalReps.setBorder(null);
		panel_5.add(txtTotalReps, "cell 1 10,growx,aligny top");
		
		JLabel lblTotalDays = new JLabel("Total Days:");
		lblTotalDays.setFont(lblTotalDays.getFont().deriveFont(lblTotalDays.getFont().getStyle() | Font.ITALIC, lblTotalDays.getFont().getSize() - 1f));
		panel_5.add(lblTotalDays, "cell 0 11,alignx left");
		
		txtTotalDays = new JLabel();
		txtTotalDays.setFont(txtTotalDays.getFont().deriveFont(txtTotalDays.getFont().getStyle() | Font.ITALIC, txtTotalDays.getFont().getSize() - 1f));
		txtTotalDays.setOpaque(false);
		txtTotalDays.setBorder(null);
		panel_5.add(txtTotalDays, "cell 1 11,growx");
		
		JLabel lblTotalContacts = new JLabel("Total Contacts:");
		lblTotalContacts.setFont(lblTotalContacts.getFont().deriveFont(lblTotalContacts.getFont().getStyle() | Font.ITALIC, lblTotalContacts.getFont().getSize() - 1f));
		panel_5.add(lblTotalContacts, "cell 0 12,alignx left");
		
		txtTotalContacts = new JLabel();
		txtTotalContacts.setFont(txtTotalContacts.getFont().deriveFont(txtTotalContacts.getFont().getStyle() | Font.ITALIC, txtTotalContacts.getFont().getSize() - 1f));
		txtTotalContacts.setOpaque(false);
		txtTotalContacts.setBorder(null);
		panel_5.add(txtTotalContacts, "cell 1 12,growx");
		
		JLabel lblTotalVisits = new JLabel("Total Visits:");
		lblTotalVisits.setFont(lblTotalVisits.getFont().deriveFont(lblTotalVisits.getFont().getStyle() | Font.ITALIC, lblTotalVisits.getFont().getSize() - 1f));
		panel_5.add(lblTotalVisits, "cell 0 13,alignx left");
		
		txtTotalVisits = new JLabel();
		txtTotalVisits.setFont(txtTotalVisits.getFont().deriveFont(txtTotalVisits.getFont().getStyle() | Font.ITALIC, txtTotalVisits.getFont().getSize() - 1f));
		txtTotalVisits.setOpaque(false);
		txtTotalVisits.setBorder(null);
		panel_5.add(txtTotalVisits, "cell 1 13,growx");
		
		JLabel lblTotalOrganizations = new JLabel("Total Organizations:");
		lblTotalOrganizations.setFont(lblTotalOrganizations.getFont().deriveFont(lblTotalOrganizations.getFont().getStyle() | Font.ITALIC, lblTotalOrganizations.getFont().getSize() - 1f));
		panel_5.add(lblTotalOrganizations, "cell 0 14,alignx left");
		
		txtTotalOrganizations = new JLabel();
		txtTotalOrganizations.setFont(txtTotalOrganizations.getFont().deriveFont(txtTotalOrganizations.getFont().getStyle() | Font.ITALIC, txtTotalOrganizations.getFont().getSize() - 1f));
		txtTotalOrganizations.setOpaque(false);
		txtTotalOrganizations.setBorder(null);
		panel_5.add(txtTotalOrganizations, "cell 1 14,growx");
		
		JLabel lblTotalGrow = new JLabel("Total Grow:");
		lblTotalGrow.setFont(lblTotalGrow.getFont().deriveFont(lblTotalGrow.getFont().getStyle() | Font.ITALIC, lblTotalGrow.getFont().getSize() - 1f));
		panel_5.add(lblTotalGrow, "cell 0 15,alignx left");
		
		txtTotalGrow = new JLabel();
		txtTotalGrow.setFont(txtTotalGrow.getFont().deriveFont(txtTotalGrow.getFont().getStyle() | Font.ITALIC, txtTotalGrow.getFont().getSize() - 1f));
		txtTotalGrow.setOpaque(false);
		txtTotalGrow.setBorder(null);
		panel_5.add(txtTotalGrow, "cell 1 15,growx");
		
		JLabel lblTotalSafe = new JLabel("Total Safe:");
		lblTotalSafe.setFont(lblTotalSafe.getFont().deriveFont(lblTotalSafe.getFont().getStyle() | Font.ITALIC, lblTotalSafe.getFont().getSize() - 1f));
		panel_5.add(lblTotalSafe, "cell 0 16,alignx left");
		
		txtTotalSafe = new JLabel();
		txtTotalSafe.setFont(txtTotalSafe.getFont().deriveFont(txtTotalSafe.getFont().getStyle() | Font.ITALIC, txtTotalSafe.getFont().getSize() - 1f));
		txtTotalSafe.setOpaque(false);
		txtTotalSafe.setBorder(null);
		panel_5.add(txtTotalSafe, "cell 1 16,growx");
		
		JLabel lblTotalMonitor = new JLabel("Total Monitor:");
		lblTotalMonitor.setFont(lblTotalMonitor.getFont().deriveFont(lblTotalMonitor.getFont().getStyle() | Font.ITALIC, lblTotalMonitor.getFont().getSize() - 1f));
		panel_5.add(lblTotalMonitor, "cell 0 17,alignx left");
		
		txtTotalMonitor = new JLabel();
		txtTotalMonitor.setFont(txtTotalMonitor.getFont().deriveFont(txtTotalMonitor.getFont().getStyle() | Font.ITALIC, txtTotalMonitor.getFont().getSize() - 1f));
		txtTotalMonitor.setOpaque(false);
		txtTotalMonitor.setBorder(null);
		panel_5.add(txtTotalMonitor, "cell 1 17,growx");
		
		JLabel lblTotalignore = new JLabel("Total Ignore:");
		lblTotalignore.setFont(lblTotalignore.getFont().deriveFont(lblTotalignore.getFont().getStyle() | Font.ITALIC, lblTotalignore.getFont().getSize() - 1f));
		panel_5.add(lblTotalignore, "cell 0 18,alignx left");
		
		txtTotalignore = new JLabel();
		txtTotalignore.setFont(txtTotalignore.getFont().deriveFont(txtTotalignore.getFont().getStyle() | Font.ITALIC, txtTotalignore.getFont().getSize() - 1f));
		txtTotalignore.setBorder(null);
		txtTotalignore.setOpaque(false);
		panel_5.add(txtTotalignore, "cell 1 18,growx");
		
		//refresh();
		totals= new MedrepStatistics();
		for(MedrepStatistics st:statistics){
			try {
				totals.addStatistics(st);
				st.setParent(totals);
			} catch (UnsupportedOperationException e) {
				e.printStackTrace();
			}
		}
		
		applyUserSettings();
		

	}

	protected void printActivities() {
		MessageFormat header = new MessageFormat("Activities list");
		MessageFormat footer = new MessageFormat("- {0} -");
		
		try {
			tableActivities.print(PrintMode.FIT_WIDTH, header, footer);
		} catch (PrinterException e) {
			e.printStackTrace();
		}
		
	}

	protected void addItem() {
		//  Auto-generated method stub
		try{
			EventDTO event = new EventDTO();
			event.setInitiator(RestManager.getInstance().currentUser());
			EventDTOJPanel panel = new EventDTOJPanel(event);
			int option = JOptionPane.showConfirmDialog(null, panel, "New event", JOptionPane.OK_CANCEL_OPTION);
			if(option==JOptionPane.OK_OPTION&&event.getParticipants()!=null){
				UserPreferences.setDefaultEventType(panel.getEventTypeIndex());
				List<EventDTO> list = totals.getEvents();
				if(list==null) list=new ArrayList<EventDTO>();
				if(!event.getEventType().isGroupEvent()){
					for(EventPersonDTO pp:event.getParticipants()){
						EventDTO ee = new EventDTO();
						ee.setComment(event.getComment());
						ee.setDate(event.getDate());
						ee.setEventType(event.getEventType());
						ee.setGifts(event.getGifts());
						System.out.println("Individual event: "+ee.getGifts().get(0).getAdvert().getName());
						ee.setId(event.getId());
						ee.setInitiator(event.getInitiator());
						ee.setMessage(event.getMessage());
						ee.setProducts(event.getProducts());
						
						List<EventPersonDTO> l = new ArrayList<EventPersonDTO>();
						l.add(pp);
						ee.setParticipants(l);
						list.add(ee);
						modifiedEvents.add(ee);
					}
				}else{
					//System.out.println("Group event: "+event.getGifts().get(0).getId());
					list.add(event);
					modifiedEvents.add(event);
				}
				//ConnectionManager.getInstance().storeDTOs(list);
				statistics = MedrepStatistics.generate(list);
				totals = new MedrepStatistics();
				for(MedrepStatistics st:statistics){
					try {
						totals.addStatistics(st);
						st.setParent(totals);
					} catch (UnsupportedOperationException e) {
						e.printStackTrace();
					}
				}
				
				setFilter();
				
				
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	

	protected void modifyItem() {
		if(tableActivities.getSelectedRow()<0) return;
		int index = tableActivities.convertRowIndexToModel(tableActivities.getSelectedRow());
		try{
			EventDTO event = events.get(index);
			JPanel panel = new EventDTOJPanel(event);
			int option = JOptionPane.showConfirmDialog(null, panel, "New event", JOptionPane.OK_CANCEL_OPTION);
			if(option==JOptionPane.OK_OPTION&&event.getParticipants()!=null){
				if(!modifiedEvents.contains(event)){
					modifiedEvents.add(event);
					refresh();
					
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	protected void removeItem() {
		if(tableActivities.getSelectedRow()<0) return;
		int index = tableActivities.convertRowIndexToModel(tableActivities.getSelectedRow());
		EventDTO event = events.get(index);
		List<EventDTO> list = totals.getEvents();
		list.remove(event);
		modifiedEvents.remove(event);
		if(event.getId()!=null){
			deletedEvents.add(event);
		}
		statistics = MedrepStatistics.generate(list);
		totals = new MedrepStatistics();
		for(MedrepStatistics st:statistics){
			try {
				totals.addStatistics(st);
				st.setParent(totals);
			} catch (UnsupportedOperationException e) {
				e.printStackTrace();
			}
		}
		setFilter();
	}

	protected void saveChanges() {
		List<EventDTO> oldList = totals.getEvents();
		int modified, saved, deleted, toRemove;
		saved = deleted = 0;
		modified = modifiedEvents.size();
		toRemove = deletedEvents.size();
		if(modifiedEvents.size()>0){
			try {
				List<EventDTO> savedList = RestManager.getInstance().saveToDatabase(modifiedEvents);
				for(EventDTO ev:modifiedEvents){
					oldList.remove(ev);
				}
				oldList.addAll(savedList);
				setFilter();
				saved = savedList.size();
				modifiedEvents = new ArrayList<EventDTO>();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(deletedEvents.size()>0){
			deleted = RestManager.getInstance().removeFromDatabase(deletedEvents);
			
			deletedEvents = new ArrayList<EventDTO>();
		}
		if(modified>0 || saved>0 || deleted>0){
			if(modified>0 || saved>0){
				statistics = MedrepStatistics.generate(oldList);
				totals = new MedrepStatistics();
				for(MedrepStatistics st:statistics){
					try {
						totals.addStatistics(st);
						st.setParent(totals);
					} catch (UnsupportedOperationException e) {
						e.printStackTrace();
					}
				}
				setFilter();
			}
			
			JOptionPane.showMessageDialog(null,
					""+saved+" of "+modified+" events saved!"
							+ "\n"
							+deleted+" of "+toRemove+" events deleted!", 
					"Database answer", 
					JOptionPane.INFORMATION_MESSAGE);
			
			refreshSavedStatus();
		}
	}
	private void refreshSavedStatus(){
		//System.out.println("Modified events = " + modifiedEvents.size());
		//System.out.println("Deleted events = " + deletedEvents.size());
		if(modifiedEvents.size()==0 && deletedEvents.size()==0){
			toolbarActivities.setSaved(true);
			String title = this.getTitle();
			if(title.endsWith("*")) 
				title = title.substring(0, title.length()-2);
			this.setTitle(title);
		}else{
			toolbarActivities.setSaved(false);
			String title = this.getTitle();
			if(!title.endsWith("*")) 
				title = title+"*";
			this.setTitle(title);
		}
	}

	private void applyUserSettings(){
		PersonDTO user = RestManager.getInstance().currentUser();
		this.lblRepresentative.setVisible(user.hasPermission(PersonPrivDTO.READ_ALL_EVENTS));
		this.comboRep.setVisible(user.hasPermission(PersonPrivDTO.READ_ALL_EVENTS));
		this.lblTotalRepresentatives.setVisible(user.hasPermission(PersonPrivDTO.READ_ALL_EVENTS));
	}
	private void getLists() throws UserNotAuthorisedException {
		
		repsList = RestManager.getInstance().getRepresentatives();
		PersonDTO emptyPerson = new PersonDTO();
		emptyPerson.setFirstname("All representatives");
		repsList.add(0,emptyPerson);
		specialtiesList = RestManager.getInstance().getSpecialties();
		SpecialtyDTO emptySpec = new SpecialtyDTO();
		emptySpec.setName("All specialties");
		specialtiesList.add(0,emptySpec);
		categoriesList = new ArrayList<String>();
		categoriesList.add("All categories");
		categoriesList.add("G");
		categoriesList.add("S");
		categoriesList.add("M");
		categoriesList.add("I");
		
	}

	protected void proceed() throws UserNotAuthorisedException {
		statistics=MedrepStatistics.
				generate(RestManager.getInstance().getEvents(dateFrom.getDate(), dateTo.getDate()));
		totals= new MedrepStatistics();
		for(MedrepStatistics st:statistics){
			try {
				totals.addStatistics(st);
				st.setParent(totals);
			} catch (UnsupportedOperationException e) {
				e.printStackTrace();
			}
		}
		modifiedEvents = new ArrayList<EventDTO>();
		deletedEvents = new ArrayList<EventDTO>();
		refresh();
		
	}
	
	private void setFilter(){
		MedrepFilter filter = new MedrepFilter();
		if(comboRep.getSelectedIndex()>0)
			filter.setRep((PersonDTO)comboRep.getSelectedItem());
		if(txtOrganisation.getText().trim().length()>0)
			filter.setInstitution(txtOrganisation.getText().trim());
		if(txtContact.getText().trim().length()>0)
			filter.setContact(txtContact.getText().trim());
		if(txtMessage.getText().trim().length()>0)
			filter.setMessage(txtMessage.getText().trim());
		if(comboCategory.getSelectedIndex()>0)
			filter.setCategory((String)comboCategory.getSelectedItem());
		if(comboSpecialties.getSelectedIndex()>0)
			filter.setSpecialty((SpecialtyDTO)comboSpecialties.getSelectedItem());
		
		selectedDay = null;
		selectedMonth = null;
		selectedYear = null;
		
		events = null;
		
		for(MedrepStatistics st:statistics){
			st.setFilter(filter);
		}
		if(this.selectedLevel>0)
			refreshEvents(selectedLevel);
		refresh();
	}
	private void removeFilter(){
		comboRep.setSelectedIndex(0);
		txtOrganisation.setText("");
		txtContact.setText("");
		txtMessage.setText("");
		comboCategory.setSelectedIndex(0);
		comboSpecialties.setSelectedIndex(0);
		totals.setFilter(null);
		
		selectedDay = null;
		selectedMonth = null;
		selectedYear = null;
		
		events = null;
		
		refresh();
	}

	private void refreshEvents(int level){
		this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		int selYear, selMonth;
		switch(level){
		case YEAR_LEVEL:
			
			selectedYear = statistics.get(tableYears.convertRowIndexToModel(tableYears.getSelectedRow()));
			if(selectedYear!=null){
				events=selectedYear.getEvents();
				refresh();
			}
			
			break;
		case MONTH_LEVEL:
			selYear = tableYears.getSelectedRow();
			MedrepStatistics y = statistics.get(tableYears.convertRowIndexToModel(selYear));
			selectedMonth=y.getNextLevel().get(tableMonths.convertRowIndexToModel(tableMonths.getSelectedRow()));
			if(selectedMonth!=null){
				events=selectedMonth.getEvents();
				refresh();
			}else
				System.out.println("No selected month");
			break;
		case DAY_LEVEL:
			selYear = tableYears.getSelectedRow();
			MedrepStatistics y2 = statistics.get(tableYears.convertRowIndexToModel(selYear));
			selMonth=tableMonths.getSelectedRow();
			MedrepStatistics m = y2.getNextLevel().get(tableMonths.convertRowIndexToModel(selMonth));
			selectedDay = m.getNextLevel().get(tableDays.convertRowIndexToModel(tableDays.getSelectedRow()));
			if(selectedDay!=null){
				events=selectedDay.getEvents();
				refresh();
			}
			break;
		}
		this.setCursor(Cursor.getDefaultCursor());
		this.selectedLevel = level;
		
	}
	private void refresh(){
		int selYear = Math.max(0, tableYears.getSelectedRow()), 
				selMonth=Math.max(0, tableMonths.getSelectedRow()), 
				selDay=Math.max(0, tableDays.getSelectedRow());
		int selCategory = comboCategory.getSelectedIndex();
		int selRep = comboRep.getSelectedIndex();
		int selSpec = comboSpecialties.getSelectedIndex();
		
		initDataBindings();
		if(tableYears.getRowCount()>0)
			tableYears.setRowSelectionInterval(selYear, selYear);
		if(tableMonths.getRowCount()>0)
			tableMonths.setRowSelectionInterval(selMonth, selMonth);
		if(tableDays.getRowCount()>0)
			tableDays.setRowSelectionInterval(selDay, selDay);
		if(tableActivities.getRowCount()>0)
			tableYears.setRowSelectionInterval(0, 0);
		
		comboCategory.setSelectedIndex(selCategory);
		comboRep.setSelectedIndex(selRep);
		comboSpecialties.setSelectedIndex(selSpec);
		
		tableYears.getColumnModel().getColumn(0).setPreferredWidth(60);
		for(int i=1;i<tableYears.getColumnCount();i++){
			tableYears.getColumnModel().getColumn(i).setMinWidth(15);
			tableYears.getColumnModel().getColumn(i).setPreferredWidth(30);
		}
		tableMonths.getColumnModel().getColumn(0).setPreferredWidth(60);
		for(int i=1;i<tableMonths.getColumnCount();i++){
			tableMonths.getColumnModel().getColumn(i).setMinWidth(15);
			tableMonths.getColumnModel().getColumn(i).setPreferredWidth(30);
			
		}
		tableDays.getColumnModel().getColumn(0).setPreferredWidth(60);
		for(int i=1;i<tableDays.getColumnCount();i++){
			tableDays.getColumnModel().getColumn(i).setMinWidth(15);
			tableDays.getColumnModel().getColumn(i).setPreferredWidth(30);
		}
		try{
			tableActivities.getColumnModel().getColumn(1).setPreferredWidth(100);
			tableActivities.getColumnModel().getColumn(2).setPreferredWidth(200);
			tableActivities.getColumnModel().getColumn(3).setPreferredWidth(200);
			tableActivities.getColumnModel().getColumn(4).setPreferredWidth(40);
			tableActivities.getColumnModel().getColumn(5).setPreferredWidth(400);
		}catch(Exception e){
			
		}
		
		refreshSavedStatus();
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected void initDataBindings() {
		JTableBinding<MedrepStatistics, List<MedrepStatistics>, JTable> jTableBinding = SwingBindings.createJTableBinding(UpdateStrategy.READ, statistics, tableYears);
		//
		BeanProperty<MedrepStatistics, String> medrepStatisticsBeanProperty = BeanProperty.create("name");
		jTableBinding.addColumnBinding(medrepStatisticsBeanProperty).setColumnName("Year").setEditable(false);
		//
		BeanProperty<MedrepStatistics, Integer> medrepStatisticsBeanProperty_1 = BeanProperty.create("totalReps");
		jTableBinding.addColumnBinding(medrepStatisticsBeanProperty_1).setColumnName("Reps");
		//
		BeanProperty<MedrepStatistics, Integer> medrepStatisticsBeanProperty_2 = BeanProperty.create("averagePerDay");
		jTableBinding.addColumnBinding(medrepStatisticsBeanProperty_2).setColumnName("Av");
		//
		BeanProperty<MedrepStatistics, Integer> medrepStatisticsBeanProperty_3 = BeanProperty.create("totalDays");
		jTableBinding.addColumnBinding(medrepStatisticsBeanProperty_3).setColumnName("Days");
		//
		BeanProperty<MedrepStatistics, Integer> medrepStatisticsBeanProperty_4 = BeanProperty.create("totalContacts");
		jTableBinding.addColumnBinding(medrepStatisticsBeanProperty_4).setColumnName("Contacts");
		//
		BeanProperty<MedrepStatistics, Integer> medrepStatisticsBeanProperty_5 = BeanProperty.create("totalVisits");
		jTableBinding.addColumnBinding(medrepStatisticsBeanProperty_5).setColumnName("Vis");
		//
		BeanProperty<MedrepStatistics, Integer> medrepStatisticsBeanProperty_6 = BeanProperty.create("totalOrganisations");
		jTableBinding.addColumnBinding(medrepStatisticsBeanProperty_6).setColumnName("Orgs");
		//
		BeanProperty<MedrepStatistics, Integer> medrepStatisticsBeanProperty_7 = BeanProperty.create("totalGrowth");
		jTableBinding.addColumnBinding(medrepStatisticsBeanProperty_7).setColumnName("G");
		//
		BeanProperty<MedrepStatistics, Integer> medrepStatisticsBeanProperty_8 = BeanProperty.create("totalSafe");
		jTableBinding.addColumnBinding(medrepStatisticsBeanProperty_8).setColumnName("S");
		//
		BeanProperty<MedrepStatistics, Integer> medrepStatisticsBeanProperty_9 = BeanProperty.create("totalMonitor");
		jTableBinding.addColumnBinding(medrepStatisticsBeanProperty_9).setColumnName("M");
		//
		BeanProperty<MedrepStatistics, Integer> medrepStatisticsBeanProperty_29 = BeanProperty.create("totalIgnore");
		jTableBinding.addColumnBinding(medrepStatisticsBeanProperty_29).setColumnName("I");
		//
		jTableBinding.bind();
		//
		BeanProperty<JTable, List<MedrepStatistics>> jTableBeanProperty = BeanProperty.create("selectedElement.nextLevel");
		jTableBinding_1 = SwingBindings.createJTableBinding(UpdateStrategy.READ, tableYears, jTableBeanProperty, tableMonths);
		//
		BeanProperty<MedrepStatistics, String> medrepStatisticsBeanProperty_10 = BeanProperty.create("name");
		jTableBinding_1.addColumnBinding(medrepStatisticsBeanProperty_10).setColumnName("Month");
		//
		BeanProperty<MedrepStatistics, Integer> medrepStatisticsBeanProperty_11 = BeanProperty.create("totalReps");
		jTableBinding_1.addColumnBinding(medrepStatisticsBeanProperty_11).setColumnName("Reps");
		//
		BeanProperty<MedrepStatistics, Integer> medrepStatisticsBeanProperty_12 = BeanProperty.create("averagePerDay");
		jTableBinding_1.addColumnBinding(medrepStatisticsBeanProperty_12).setColumnName("Av");
		//
		BeanProperty<MedrepStatistics, Integer> medrepStatisticsBeanProperty_13 = BeanProperty.create("totalDays");
		jTableBinding_1.addColumnBinding(medrepStatisticsBeanProperty_13).setColumnName("Days");
		//
		BeanProperty<MedrepStatistics, Integer> medrepStatisticsBeanProperty_14 = BeanProperty.create("totalContacts");
		jTableBinding_1.addColumnBinding(medrepStatisticsBeanProperty_14).setColumnName("Contacts");
		//
		BeanProperty<MedrepStatistics, Integer> medrepStatisticsBeanProperty_15 = BeanProperty.create("totalVisits");
		jTableBinding_1.addColumnBinding(medrepStatisticsBeanProperty_15).setColumnName("Vis");
		//
		BeanProperty<MedrepStatistics, Integer> medrepStatisticsBeanProperty_16 = BeanProperty.create("totalOrganisations");
		jTableBinding_1.addColumnBinding(medrepStatisticsBeanProperty_16).setColumnName("Orgs");
		//
		BeanProperty<MedrepStatistics, Integer> medrepStatisticsBeanProperty_17 = BeanProperty.create("totalGrowth");
		jTableBinding_1.addColumnBinding(medrepStatisticsBeanProperty_17).setColumnName("G");
		//
		BeanProperty<MedrepStatistics, Integer> medrepStatisticsBeanProperty_18 = BeanProperty.create("totalSafe");
		jTableBinding_1.addColumnBinding(medrepStatisticsBeanProperty_18).setColumnName("S");
		//
		BeanProperty<MedrepStatistics, Integer> medrepStatisticsBeanProperty_19 = BeanProperty.create("totalMonitor");
		jTableBinding_1.addColumnBinding(medrepStatisticsBeanProperty_19).setColumnName("M");
		//
		BeanProperty<MedrepStatistics, Integer> medrepStatisticsBeanProperty_30 = BeanProperty.create("totalIgnore");
		jTableBinding_1.addColumnBinding(medrepStatisticsBeanProperty_30).setColumnName("I");
		//
		jTableBinding_1.setEditable(false);
		jTableBinding_1.bind();
		//
		JTableBinding<MedrepStatistics, JTable, JTable> jTableBinding_2 = SwingBindings.createJTableBinding(UpdateStrategy.READ, tableMonths, jTableBeanProperty, tableDays);
		//
		BeanProperty<MedrepStatistics, String> medrepStatisticsBeanProperty_20 = BeanProperty.create("name");
		jTableBinding_2.addColumnBinding(medrepStatisticsBeanProperty_20).setColumnName("Day");
		//
		BeanProperty<MedrepStatistics, Integer> medrepStatisticsBeanProperty_21 = BeanProperty.create("totalReps");
		jTableBinding_2.addColumnBinding(medrepStatisticsBeanProperty_21).setColumnName("Reps");
		//
		BeanProperty<MedrepStatistics, Integer> medrepStatisticsBeanProperty_22 = BeanProperty.create("averagePerDay");
		jTableBinding_2.addColumnBinding(medrepStatisticsBeanProperty_22).setColumnName("Av");
		//
		BeanProperty<MedrepStatistics, Integer> medrepStatisticsBeanProperty_23 = BeanProperty.create("totalContacts");
		jTableBinding_2.addColumnBinding(medrepStatisticsBeanProperty_23).setColumnName("Contacts");
		//
		BeanProperty<MedrepStatistics, Integer> medrepStatisticsBeanProperty_24 = BeanProperty.create("totalVisits");
		jTableBinding_2.addColumnBinding(medrepStatisticsBeanProperty_24).setColumnName("Vis");
		//
		BeanProperty<MedrepStatistics, Integer> medrepStatisticsBeanProperty_25 = BeanProperty.create("totalOrganisations");
		jTableBinding_2.addColumnBinding(medrepStatisticsBeanProperty_25).setColumnName("Orgs");
		//
		BeanProperty<MedrepStatistics, Integer> medrepStatisticsBeanProperty_26 = BeanProperty.create("totalGrowth");
		jTableBinding_2.addColumnBinding(medrepStatisticsBeanProperty_26).setColumnName("G");
		//
		BeanProperty<MedrepStatistics, Integer> medrepStatisticsBeanProperty_27 = BeanProperty.create("totalSafe");
		jTableBinding_2.addColumnBinding(medrepStatisticsBeanProperty_27).setColumnName("S");
		//
		BeanProperty<MedrepStatistics, Integer> medrepStatisticsBeanProperty_28 = BeanProperty.create("totalMonitor");
		jTableBinding_2.addColumnBinding(medrepStatisticsBeanProperty_28).setColumnName("M");
		//
		BeanProperty<MedrepStatistics, Integer> medrepStatisticsBeanProperty_31 = BeanProperty.create("totalIgnore");
		jTableBinding_2.addColumnBinding(medrepStatisticsBeanProperty_31).setColumnName("I");
		//
		jTableBinding_2.setEditable(false);
		jTableBinding_2.bind();
		//
		JTableBinding<EventDTO, List<EventDTO>, JTable> jTableBinding_3 = SwingBindings.createJTableBinding(UpdateStrategy.READ, events, tableActivities);
		//
		BeanProperty<EventDTO, Date> eventDTOBeanProperty = BeanProperty.create("date");
		JTableBinding<EventDTO, List<EventDTO>, JTable>.ColumnBinding 
		columnBinding = jTableBinding_3.addColumnBinding(eventDTOBeanProperty);
		columnBinding.setColumnName("Date");
		columnBinding.setConverter(new DateToString());
		//
		BeanProperty<EventDTO, PersonDTO> eventDTOBeanProperty_1 = BeanProperty.create("initiator");
		jTableBinding_3.addColumnBinding(eventDTOBeanProperty_1).setColumnName("Representative");
		//
		BeanProperty<EventDTO, String> eventDTOBeanProperty_2 = BeanProperty.create("message");
		jTableBinding_3.addColumnBinding(eventDTOBeanProperty_2).setColumnName("Message");
		//
		BeanProperty<EventDTO, PersonDTO> eventDTOBeanProperty_3 = BeanProperty.create("firstContact");
		jTableBinding_3.addColumnBinding(eventDTOBeanProperty_3).setColumnName("Contact");
		//
		BeanProperty<EventDTO, String> eventDTOBeanProperty_4 = BeanProperty.create("firstContact.category");
		jTableBinding_3.addColumnBinding(eventDTOBeanProperty_4).setColumnName("Category");
		//
		BeanProperty<EventDTO, Set<InstitutionDTO>> eventDTOBeanProperty_5 = BeanProperty.create("firstContact.institutions");
		jTableBinding_3.addColumnBinding(eventDTOBeanProperty_5).setColumnName("Institutions");
		//
		BeanProperty<EventDTO, String> eventDTOBeanProperty_6 = BeanProperty.create("firstContact.specialty1.name");
		jTableBinding_3.addColumnBinding(eventDTOBeanProperty_6).setColumnName("Specialty");
		//
		jTableBinding_3.setEditable(false);
		jTableBinding_3.bind();
		//
		BeanProperty<JTable, MedrepStatistics> jTableBeanProperty_1 = BeanProperty.create("selectedElement");
		ObjectProperty<MedrepStatistics> medrepStatisticsObjectProperty = ObjectProperty.create();
		monthBinding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, tableMonths, jTableBeanProperty_1, selectedMonth, medrepStatisticsObjectProperty);
		monthBinding.bind();
		//
		JComboBoxBinding<PersonDTO, List<PersonDTO>, JComboBox> jComboBinding = SwingBindings.createJComboBoxBinding(UpdateStrategy.READ, repsList, comboRep);
		jComboBinding.bind();
		//
		JComboBoxBinding<String, List<String>, JComboBox> jComboBinding_1 = SwingBindings.createJComboBoxBinding(UpdateStrategy.READ, categoriesList, comboCategory);
		jComboBinding_1.bind();
		//
		JComboBoxBinding<SpecialtyDTO, List<SpecialtyDTO>, JComboBox> jComboBinding_2 = SwingBindings.createJComboBoxBinding(UpdateStrategy.READ, specialtiesList, comboSpecialties);
		jComboBinding_2.bind();
		//
		BeanProperty<MedrepStatistics, Integer> medrepStatisticsBeanProperty_32 = BeanProperty.create("averagePerDay");
		BeanProperty<JLabel, String> jLabelBeanProperty = BeanProperty.create("text");
		AutoBinding<MedrepStatistics, Integer, JLabel, String> autoBinding = Bindings.createAutoBinding(UpdateStrategy.READ, totals, medrepStatisticsBeanProperty_32, txtAveragePerDay, jLabelBeanProperty);
		autoBinding.bind();
		//
		BeanProperty<MedrepStatistics, Integer> medrepStatisticsBeanProperty_33 = BeanProperty.create("totalReps");
		AutoBinding<MedrepStatistics, Integer, JLabel, String> autoBinding_1 = Bindings.createAutoBinding(UpdateStrategy.READ, totals, medrepStatisticsBeanProperty_33, txtTotalReps, jLabelBeanProperty);
		autoBinding_1.bind();
		//
		BeanProperty<MedrepStatistics, Integer> medrepStatisticsBeanProperty_34 = BeanProperty.create("totalDays");
		AutoBinding<MedrepStatistics, Integer, JLabel, String> autoBinding_2 = Bindings.createAutoBinding(UpdateStrategy.READ, totals, medrepStatisticsBeanProperty_34, txtTotalDays, jLabelBeanProperty);
		autoBinding_2.bind();
		//
		BeanProperty<MedrepStatistics, Integer> medrepStatisticsBeanProperty_35 = BeanProperty.create("totalContacts");
		AutoBinding<MedrepStatistics, Integer, JLabel, String> autoBinding_3 = Bindings.createAutoBinding(UpdateStrategy.READ, totals, medrepStatisticsBeanProperty_35, txtTotalContacts, jLabelBeanProperty);
		autoBinding_3.bind();
		//
		BeanProperty<MedrepStatistics, Integer> medrepStatisticsBeanProperty_36 = BeanProperty.create("totalVisits");
		AutoBinding<MedrepStatistics, Integer, JLabel, String> autoBinding_4 = Bindings.createAutoBinding(UpdateStrategy.READ, totals, medrepStatisticsBeanProperty_36, txtTotalVisits, jLabelBeanProperty);
		autoBinding_4.bind();
		//
		BeanProperty<MedrepStatistics, Integer> medrepStatisticsBeanProperty_37 = BeanProperty.create("totalOrganisations");
		AutoBinding<MedrepStatistics, Integer, JLabel, String> autoBinding_5 = Bindings.createAutoBinding(UpdateStrategy.READ, totals, medrepStatisticsBeanProperty_37, txtTotalOrganizations, jLabelBeanProperty);
		autoBinding_5.bind();
		//
		BeanProperty<MedrepStatistics, Integer> medrepStatisticsBeanProperty_38 = BeanProperty.create("totalGrowth");
		AutoBinding<MedrepStatistics, Integer, JLabel, String> autoBinding_6 = Bindings.createAutoBinding(UpdateStrategy.READ, totals, medrepStatisticsBeanProperty_38, txtTotalGrow, jLabelBeanProperty);
		autoBinding_6.bind();
		//
		BeanProperty<MedrepStatistics, Integer> medrepStatisticsBeanProperty_39 = BeanProperty.create("totalSafe");
		AutoBinding<MedrepStatistics, Integer, JLabel, String> autoBinding_7 = Bindings.createAutoBinding(UpdateStrategy.READ, totals, medrepStatisticsBeanProperty_39, txtTotalSafe, jLabelBeanProperty);
		autoBinding_7.bind();
		//
		BeanProperty<MedrepStatistics, Integer> medrepStatisticsBeanProperty_41 = BeanProperty.create("totalIgnore");
		AutoBinding<MedrepStatistics, Integer, JLabel, String> autoBinding_9 = Bindings.createAutoBinding(UpdateStrategy.READ, totals, medrepStatisticsBeanProperty_41, txtTotalignore, jLabelBeanProperty);
		autoBinding_9.bind();
		//
		BeanProperty<MedrepStatistics, Integer> medrepStatisticsBeanProperty_40 = BeanProperty.create("totalMonitor");
		AutoBinding<MedrepStatistics, Integer, JLabel, String> autoBinding_8 = Bindings.createAutoBinding(UpdateStrategy.READ, totals, medrepStatisticsBeanProperty_40, txtTotalMonitor, jLabelBeanProperty);
		autoBinding_8.bind();
	}
}
