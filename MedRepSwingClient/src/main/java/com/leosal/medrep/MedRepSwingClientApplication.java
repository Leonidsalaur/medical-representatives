package com.leosal.medrep;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.oauth2.client.resource.OAuth2AccessDeniedException;
import org.springframework.web.client.ResourceAccessException;

import com.leosal.medrep.services.UserService;
import com.leosal.medrep.ui.AuthorizationPanel;
import com.leosal.medrep.ui.Contacts;
import com.leosal.medrep.ui.PersonDTOJPanel;
import com.leosal.medrepear.dto.ContactDTO;


@SpringBootApplication
@ComponentScan(basePackages= {"com.leosal.medrep.configs", "com.leosal.medrep.services", "com.leosal.medrep.rest", "com.leosal.medrep.ui",
		"com.leosal.medrep.models", "com.leosal.medrep.configs", "com.leosal.medrep.services"})
public class MedRepSwingClientApplication extends JFrame implements CommandLineRunner {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Value("${application.theme}")
	private String appTheme;
	
	@Autowired
	private ApplicationContext context;
	
	@Autowired
	private UserService restService;
	
	private JPanel panelFrames;
	private JPanel panelStatus;
	private JLabel lblStatus;
	private JDesktopPane desktop;
	private JMenuBar menuBar;
	
	private List<FrameButton> frames=new ArrayList<FrameButton>();
	private JMenuItem mntmRepresentatives;

	public static void main(String[] args) {
		new SpringApplicationBuilder(MedRepSwingClientApplication.class).headless(false).run(args);
	}

	@Override
	public void run(String... args) throws Exception {
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setVisible(true);
	}
	
	@PostConstruct
	private void initialize() {
		this.setFont(null);
		this.setTitle("Representatives Activitie Database");
		this.setBounds(100, 100, 450, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
//		String appTheme = UserPreferences.getAppTheme(); FIXME
//		setLookAndFeel(appTheme);
		
		menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);
		
		JMenu mnNew = new JMenu("New");
		menuBar.add(mnNew);
		
		JMenuItem mntmEvent = new JMenuItem("Event");
		mntmEvent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addEvent();
			}
		});
		mnNew.add(mntmEvent);
		
		JMenuItem mntmContact = new JMenuItem("Contact");
		mntmContact.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addContact();
			}
		});
		mnNew.add(mntmContact);
		
		
		JMenu mnCatalogs = new JMenu("Catalogs");
		menuBar.add(mnCatalogs);
		
		JMenuItem mntmAdvert = new JMenuItem("Advert");
		mntmAdvert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showAdverts();
			}
		});
		mnCatalogs.add(mntmAdvert);
		
		JMenuItem mntmContacts = new JMenuItem("Contacts");
		mntmContacts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showContacts();
			}
		});
		mnCatalogs.add(mntmContacts);
		
		JMenuItem mntmProducts = new JMenuItem("Products");
		mntmProducts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showProducts();
			}
		});
		mnCatalogs.add(mntmProducts);
		
		JMenuItem mntmRegions = new JMenuItem("Regions");
		mntmRegions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showRegions();
			}
		});
		mnCatalogs.add(mntmRegions);
		
		JMenuItem mntmSpecialties = new JMenuItem("Specialties");
		mntmSpecialties.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showSpecialties();
			}
		});
		mnCatalogs.add(mntmSpecialties);
		
		mntmRepresentatives = new JMenuItem("Representatives");
		mntmRepresentatives.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showRepresentatives();
			}
		});
		mnCatalogs.add(mntmRepresentatives);
		
		JMenu mnReports = new JMenu("Reports");
		menuBar.add(mnReports);
		
		JMenu mnActivitiesReports = new JMenu("Activities reports");
		mnReports.add(mnActivitiesReports);
		
		JMenuItem mntmActivities = new JMenuItem("General report");
		mnActivitiesReports.add(mntmActivities);
		
		JMenuItem mntmDailyReport = new JMenuItem("Daily report");
		mntmDailyReport.setVisible(false);
		mnActivitiesReports.add(mntmDailyReport);
		mntmActivities.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showEventStatistics();
			}
		});
		
		JMenuItem mntmSales = new JMenuItem("Sales report");
		mntmSales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showSalesReport();
			}
		});
		mnReports.add(mntmSales);
		
		JMenuItem mntmExpenses = new JMenuItem("Expenses");
		mntmExpenses.setVisible(false);
		mntmExpenses.setEnabled(false);
		mnReports.add(mntmExpenses);
		
		JMenu mnService = new JMenu("Service");
		menuBar.add(mnService);
		
		JMenuItem mntmSettings = new JMenuItem("Settings");
		mntmSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showSettings();
			}
		});
		mnService.add(mntmSettings);
		
		JMenuItem mntmLoadReports = new JMenuItem("Load Report Files");
		mntmLoadReports.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showReportLoader();
			}
		});
		mnService.add(mntmLoadReports);
		
		JMenuItem mntmCheckForUpdates = new JMenuItem("Check for updates");
		mntmCheckForUpdates.setEnabled(false);
		mnService.add(mntmCheckForUpdates);
		
		this.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panelTop = new JPanel();
		this.getContentPane().add(panelTop, BorderLayout.NORTH);
		
		JPanel panelLeft = new JPanel();
		this.getContentPane().add(panelLeft, BorderLayout.WEST);
		
		JPanel panelBottom = new JPanel();
		this.getContentPane().add(panelBottom, BorderLayout.SOUTH);
		panelBottom.setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		panelBottom.add(splitPane);
		
		panelFrames = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelFrames.getLayout();
		flowLayout.setHgap(0);
		flowLayout.setVgap(0);
		flowLayout.setAlignment(FlowLayout.LEFT);
		splitPane.setLeftComponent(panelFrames);
		
		panelStatus = new JPanel();
		splitPane.setRightComponent(panelStatus);
		panelStatus.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
		
		lblStatus = new JLabel("Status:");
		lblStatus.setFont(lblStatus.getFont().deriveFont(lblStatus.getFont().getStyle() & ~Font.BOLD));
		lblStatus.setHorizontalAlignment(SwingConstants.LEFT);
		panelStatus.add(lblStatus);
		
		JPanel panelRight = new JPanel();
		this.getContentPane().add(panelRight, BorderLayout.EAST);
		
		desktop = new JDesktopPane();
		desktop.setBackground(Color.WHITE);
		desktop.addContainerListener(new ContainerListener() {
			//private int ocSpace = 0;
			
			public void componentRemoved(ContainerEvent e) {
				
				for(int i=0;i<frames.size();i++){
					FrameButton fb = frames.get(i);
					if(fb.getFrame()==e.getChild()){
						frames.remove(i);
						fb.setVisible(false);
						panelFrames.remove(fb);
						reArrangeFramesPanel();
						break;
					}
				}
				
			}
			
			
			public void componentAdded(ContainerEvent e) {
				
				if(JInternalFrame.class.isAssignableFrom(e.getChild().getClass())){
					FrameButton fb = new FrameButton((JInternalFrame)e.getChild());
					panelFrames.add(fb);
					reArrangeFramesPanel();
				}
			}
			
		});
		this.getContentPane().add(desktop, BorderLayout.CENTER);
		
		
		this.setLookAndFeel();
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setVisible(true);
		
		authorize();
	}
	
	private void setLookAndFeel(){
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if (appTheme.equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            SwingUtilities.updateComponentTreeUI(this);
		            break;
		        }
		    }
		} catch (Exception e) {
			try {
				e.printStackTrace();
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	protected void showReportLoader() {
		// FIXME
//		new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				setStatus("Opening Report Loader...");
//				desktop.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
//				try{
//					JInternalFrame frame = new ReportLoader();
//					
//					desktop.add(frame);
//					try {
//						frame.setMaximum(true);
//					} catch (PropertyVetoException e) {
//						e.printStackTrace();
//					}
//					frame.setVisible(true);
//				}catch(UserNotAuthorisedException e){
//					JOptionPane.showMessageDialog(null, "Authorisation failed");
//				}
//				desktop.setCursor(Cursor.getDefaultCursor());
//				clearStatus();
//				
//			}
//		}).start(); 
		
	}

	protected void addContact() {
		ContactDTO contact = new ContactDTO();
		PersonDTOJPanel panel = context.getBean(PersonDTOJPanel.class);
		panel.setContact(contact);
		int resp = JOptionPane.showConfirmDialog(desktop, panel, "New Contact",JOptionPane.OK_CANCEL_OPTION);
		
		if(resp==JOptionPane.OK_OPTION){
			if(contact.getFirstname()==null || contact.getLastname()==null){
				JOptionPane.showMessageDialog(null, 
						"Please define at least Firstname or Lastname!", 
						"Undefined person", 
						JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			ArrayList<ContactDTO> list = new ArrayList<ContactDTO>();
			list.add(contact);
			try {
//				RestManager.getInstance().saveToDatabase(list);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, 
						"Failed to store contact.\n"
						+ "See LOG file for details.", "Error", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}
		
	}

	protected void addEvent() {
		// FIXME
//		new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				setStatus("Creating new event...");
//				desktop.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
//				EventDTO ev = EventDTO.getDefaultEvent();
//				
//				EventDTOJPanel panel;
//				try {
//					panel = new EventDTOJPanel(ev);
//					clearStatus();
//					int resp = JOptionPane.showConfirmDialog(desktop, panel, "New Event",JOptionPane.OK_CANCEL_OPTION);
//					if(resp==JOptionPane.OK_OPTION){
//						
//						ArrayList<EventDTO> list = new ArrayList<EventDTO>();
//						list.add(ev);
//						if(!ev.save()) {
//							JOptionPane.showMessageDialog(null, 
//									"Failed to store contact.\n"
//									+ "See LOG file for details.", "Error", JOptionPane.ERROR_MESSAGE);
//						}
//					}
//				} catch (UserNotAuthorisedException e) {
//					JOptionPane.showMessageDialog(null, 
//							"Failed to store contact.\n"
//							+ "See LOG file for details.", "Error", JOptionPane.ERROR_MESSAGE);
//					e.printStackTrace();
//				}
//				
//				desktop.setCursor(Cursor.getDefaultCursor());
//				
//				
//			}
//		}).start(); 
		
	}
		
	

	protected void showSettings() {
		// FIXME
//		JInternalFrame frame = new PreferencesFrame();
//		
//		desktop.add(frame);
//		frame.setVisible(true);
		
	}

	protected void showAdverts() {
		// FIXME
//		new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				setStatus("Extracting adverts");
//				desktop.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
//				try{
//					JInternalFrame frame = new Adverts();
//					
//					desktop.add(frame);
//					frame.setBounds(0, 0,frame.getWidth(), desktop.getHeight());
//					frame.setVisible(true);
//				}catch(UserNotAuthorisedException e){
//					JOptionPane.showMessageDialog(null, "Authorisation failed");
//				}
//				desktop.setCursor(Cursor.getDefaultCursor());
//				clearStatus();
//				
//			}
//		}).start(); 
		
	}
	protected void showContacts() {
		// FIXME
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				setStatus("Extracting contacts");
				desktop.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				try{
					Contacts frame = context.getBean(Contacts.class);
					frame.extract();
					
					desktop.add(frame);
					frame.setBounds(0, 0,frame.getWidth(), desktop.getHeight());
					frame.setVisible(true);
				}catch(ResourceAccessException e){
					JOptionPane.showMessageDialog(null, "Resource server not available!\nPlease try again later!", "Request failed", JOptionPane.ERROR_MESSAGE);
				}
				desktop.setCursor(Cursor.getDefaultCursor());
				clearStatus();
				
			}
		}).start(); 
		
	}
	
	protected void showRepresentatives() {
		// FIXME
//		new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				setStatus("Extracting representatives");
//				desktop.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
//				try{
//					JInternalFrame frame = new RepsFrame();
//					desktop.add(frame);
//					frame.setVisible(true);
//				}catch(UserNotAuthorisedException e){
//					JOptionPane.showMessageDialog(null, "Authorisation failed");
//				}
//				desktop.setCursor(Cursor.getDefaultCursor());
//				clearStatus();
//				
//			}
//		}).start(); 
		
	}
	
	protected void showProducts() {
		// FIXME
//		new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				setStatus("Extracting products");
//				desktop.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
//				try{
//					JInternalFrame frame = new ProductsFrame();
//					
//					desktop.add(frame);
//					frame.setBounds(0, 0,frame.getWidth(), desktop.getHeight());
//					frame.setVisible(true);
//				}catch(UserNotAuthorisedException e){
//					JOptionPane.showMessageDialog(null, "Authorisation failed");
//				}
//				desktop.setCursor(Cursor.getDefaultCursor());
//				clearStatus();
//				
//			}
//		}).start(); 
		
	}
	protected void showSalesReport() {
		// FIXME
//		new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				//setStatus("Extracting products");
//				desktop.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
//				
//					JInternalFrame frame = new SalesReport();
//					
//					desktop.add(frame);
//					//frame.setBounds(0, 0,frame.getWidth(), desktop.getHeight());
//					frame.setVisible(true);
//				
//				desktop.setCursor(Cursor.getDefaultCursor());
//				clearStatus();
//				
//			}
//		}).start(); 
		
	}
	protected void showRegions() {
		// FIXME
//		new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				setStatus("Extracting regions...");
//				desktop.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
//				try{
//					JInternalFrame frame = new RegionsFrame();
//					
//					desktop.add(frame);
//					frame.setBounds(0, 0,frame.getWidth(), desktop.getHeight());
//					frame.setVisible(true);
//				}catch(UserNotAuthorisedException e){
//					JOptionPane.showMessageDialog(null, "Authorisation failed");
//				}
//				desktop.setCursor(Cursor.getDefaultCursor());
//				clearStatus();
//				
//			}
//		}).start(); 
		
	}
	protected void showSpecialties() {
		// FIXME
//		new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				setStatus("Extracting specialties...");
//				desktop.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
//				try{
//					JInternalFrame frame = new SpecialtiesFrame();
//					desktop.add(frame);
//					frame.setBounds(0, 0,frame.getWidth(), desktop.getHeight());
//					
//					frame.setVisible(true);
//				}catch(UserNotAuthorisedException e){
//					JOptionPane.showMessageDialog(null, "Authorisation failed");
//				}
//				desktop.setCursor(Cursor.getDefaultCursor());
//				clearStatus();
//				
//			}
//		}).start(); 
		
	}
	protected void showEvents() {
		// FIXME
//		new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				setStatus("Extracting activities...");
//				desktop.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
//				try{
//					JInternalFrame frame = new ActivitiesFrame();
//					desktop.add(frame);
//					frame.setMaximum(true);
//					frame.setVisible(true);
//				}catch (PropertyVetoException e) {
//					e.printStackTrace();
//				}
//				desktop.setCursor(Cursor.getDefaultCursor());
//				clearStatus();
//				
//			}
//		}).start(); 
		
	}
	protected void showEventStatistics() {
		// FIXME
//		new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				setStatus("Extracting activities statistics...");
//				desktop.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
//				try{
//					JInternalFrame frame = new EventStatisticsFrame();
//					desktop.add(frame);
//					frame.setMaximum(true);
//					frame.setVisible(true);
//				}catch (PropertyVetoException e) {
//					e.printStackTrace();
//				}
//				desktop.setCursor(Cursor.getDefaultCursor());
//				clearStatus();
//				
//			}
//		}).start(); 
//		
	}
	
	private void authorize(){
		menuBar.setVisible(false);
		AuthorizationPanel panel = context.getBean(AuthorizationPanel.class);
		int resp = JOptionPane.showConfirmDialog(desktop, panel, "Authorization",JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
		
		if(resp == JOptionPane.CANCEL_OPTION) {
			System.exit(0);
		} else {
			try {
				restService.login(panel.getLogin(), panel.getPassword());
				menuBar.setVisible(true);
			} catch(OAuth2AccessDeniedException e) {
				JOptionPane.showMessageDialog(null, "Invalid credentials!", "Login failed", JOptionPane.ERROR_MESSAGE);
				authorize();
			} catch(ResourceAccessException e) {
				JOptionPane.showMessageDialog(null, "Resource server not available!\nPlease try again later!", "Login failed", JOptionPane.ERROR_MESSAGE);
				authorize();
			} catch(Exception e) {
				JOptionPane.showMessageDialog(null, "Unexpected error!\nPlease see log file for detailed information.", "Login failed", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
				authorize();
			}
		}
	}
	
	public void setStatus(String status){
		lblStatus.setText("Status: "+status);
	}
	public void clearStatus(){
		lblStatus.setText("Status:");
	}
	
	public void activate(){
		// FIXME
//		menuBar.setVisible(true);
//		if(!RestManager.getInstance().hasPermission(PersonPrivDTO.READ_ALL_CONTACTS))
//			mntmRepresentatives.setVisible(false);
	}
	
	private void reArrangeFramesPanel(){
		int ocSpace = 0;
		for(int i=0;i<frames.size();i++){
			FrameButton fb = frames.get(i);
			ocSpace+=fb.getInitialWidth();
		}
		if(ocSpace<=panelFrames.getWidth()){
			for(int i=0;i<frames.size();i++){
				FrameButton fb = frames.get(i);
				fb.setPreferredSize(new Dimension(fb.getInitialWidth(), fb.getPreferredSize().height));
			}
		}else{
			int width = panelFrames.getWidth()/frames.size();
			ocSpace = frames.size()*width;
			for(int i=0;i<frames.size();i++){
				FrameButton fb = frames.get(i);
				fb.setPreferredSize(new Dimension(width, fb.getPreferredSize().height));
			}
		}
	}
	
	private class FrameButton extends JButton{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private JInternalFrame frame;
		private int initialWidth;
		public FrameButton(final JInternalFrame frame){
			super();
			//System.out.println("Frame added");
			
			if(frame.getTitle().length()>0)
				this.setText(frame.getTitle());
			else
				this.setText(frame.getClass().getSimpleName());
			
			
			this.frame = frame;
			this.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					frame.moveToFront();
				}
				
			});
			this.setToolTipText(this.getText());
			this.initialWidth = this.getPreferredSize().width;
			//System.out.println(initialWidth);
			frames.add(this);
			this.setVisible(true);
		}
		public JInternalFrame getFrame(){
			return frame;
		}
		public int getInitialWidth(){
			return initialWidth;
		}
	}
	
}