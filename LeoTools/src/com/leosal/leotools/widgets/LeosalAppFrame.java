package com.leosal.leotools.widgets;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;

import com.leosal.leotools.tools.FilePreferencesFactory;


public class LeosalAppFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	
	protected JPanel panelFrames;
	protected JPanel panelStatus;
	protected JLabel lblStatus;
	protected JDesktopPane desktop;
	
	private List<FrameButton> frames=new ArrayList<FrameButton>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LeosalAppFrame frame = new LeosalAppFrame();
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
	public LeosalAppFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 414);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		initialize();
		
	}
	/**
	 * Initialize the contents of the frame.
	 */
	
	protected void initialize() {
		
		this.setFont(null);
		this.setTitle("Main Apllication Frame");
		this.setBounds(100, 100, 450, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

		setLookAndFeel();
		
		this.getContentPane().setLayout(new BorderLayout(0, 0));
		
		//JPanel panelTop = new JPanel();
		//this.getContentPane().add(panelTop, BorderLayout.NORTH);
		
		//JPanel panelLeft = new JPanel();
		//this.getContentPane().add(panelLeft, BorderLayout.WEST);
		
		//JPanel panelRight = new JPanel();
		//this.getContentPane().add(panelRight, BorderLayout.EAST);
		
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
		
		
		
	}

	public JDesktopPane getDesktop(){
		return desktop;
	}
	
	private void setLookAndFeel() {
		// TODO Auto-generated method stub
		
	}


	public void setStatus(String status){
		lblStatus.setText("Status: "+status);
	}
	public void clearStatus(){
		lblStatus.setText("Status:");
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
	
	public void setLookAndFeel(String appTheme){
		//System.out.println(appTheme);
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		    	//System.out.println("\t"+info.getName());
		        if (appTheme.equals(info.getClassName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            SwingUtilities.updateComponentTreeUI(this);
		            break;
		        }
		    }
		} catch (Exception e) {
			try {
				//e.printStackTrace();
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
	public void configureEnvironment(File logfile) {
		if(logfile==null)
			logfile = new File("application.log");
		System.setProperty("java.util.prefs.PreferencesFactory", FilePreferencesFactory.class.getName());
		System.setProperty(FilePreferencesFactory.SYSTEM_PROPERTY_FILE, "medrep_prefs.conf");
		try {
			PrintStream ps = new PrintStream(logfile);
			System.setErr(ps);
			System.setOut(ps);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private class FrameButton extends JButton{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private JInternalFrame frmMainApllicationFrame;
		private int initialWidth;
		public FrameButton(final JInternalFrame frame){
			super();
			//System.out.println("Frame added");
			
			if(frame.getTitle().length()>0)
				this.setText(frame.getTitle());
			else
				this.setText(frame.getClass().getSimpleName());
			
			
			this.frmMainApllicationFrame = frame;
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
			return frmMainApllicationFrame;
		}
		public int getInitialWidth(){
			return initialWidth;
		}
	}
}
