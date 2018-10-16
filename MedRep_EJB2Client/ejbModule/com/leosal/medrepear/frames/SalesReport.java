package com.leosal.medrepear.frames;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.ListModel;
import javax.swing.SwingConstants;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import javax.swing.event.ListDataListener;

import jxl.Workbook;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.NumberFormat;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import net.miginfocom.swing.MigLayout;

import com.leosal.leotools.exceptions.UserNotAuthorisedException;
import com.leosal.leotools.widgets.MultiSelectPane;
import com.leosal.medrepear.dto.InstitutionDTO;
import com.leosal.medrepear.dto.PersonDTO;
import com.leosal.medrepear.dto.ProductDTO;
import com.leosal.medrepear.dto.RegionDTO;
import com.leosal.medrepear.dto.ReportDTO;
import com.leosal.medrepear.service.RestManager;
import com.leosal.medrepear.util.SalesReportDataItem;
import com.toedter.calendar.JDateChooser;

public class SalesReport extends JInternalFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final int EXCEL_FIRST_ROW=3, EXCEL_FIRST_COL=1;
	
	private final JButton btnGenerate = new JButton("Generate");
	private JDateChooser dateFrom;
	private JDateChooser dateTo;
	@SuppressWarnings("rawtypes")
	private JList listDistrib;
	
	private List<PersonDTO> repsFilter=null, repsList=null;;
	private List<RegionDTO> regFilter=null, regList=null;
	private List<InstitutionDTO> institFilter = null, institList=null,
			distribFilter=null, distribList=null;
	private List<String> colGroups=null, rowGroups=null;
	private List<ProductDTO> prodFilter=null, prodList=null;
	@SuppressWarnings("rawtypes")
	private JList listReps;
	@SuppressWarnings("rawtypes")
	private JList listRegions;
	@SuppressWarnings("rawtypes")
	private JList listInstits;
	@SuppressWarnings("rawtypes")
	private JList listProds;
	private JList<String> listRowGrouping;
	private JList<String> listColGrouping;
	private JCheckBox chckbxQuantity;
	private JCheckBox chckbxEuro;
	
	private boolean reportRunning=false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SalesReport frame = new SalesReport();
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
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public SalesReport() {
		setResizable(true);
		setTitle("Report Settings");
		setClosable(true);
		setDefaultCloseOperation(JInternalFrame.DO_NOTHING_ON_CLOSE);
		addInternalFrameListener(new InternalFrameListener() {
			
			@Override
			public void internalFrameOpened(InternalFrameEvent e) {
				
				
			}
			
			@Override
			public void internalFrameIconified(InternalFrameEvent e) {
				
				
			}
			
			@Override
			public void internalFrameDeiconified(InternalFrameEvent e) {
				
				
			}
			
			@Override
			public void internalFrameDeactivated(InternalFrameEvent e) {
				
				
			}
			
			@Override
			public void internalFrameClosing(InternalFrameEvent e) {
				if(reportRunning){
					JOptionPane.showMessageDialog(SalesReport.this, "Please wait until report generation will finish!");
					return;
				}else{
					SalesReport.this.dispose();
				}
				
			}
			
			@Override
			public void internalFrameClosed(InternalFrameEvent e) {
				
			}
			
			@Override
			public void internalFrameActivated(InternalFrameEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		setBounds(100, 100, 441, 508);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panelTop = new JPanel();
		getContentPane().add(panelTop, BorderLayout.NORTH);
		
		JPanel panelLeft = new JPanel();
		getContentPane().add(panelLeft, BorderLayout.WEST);
		
		JPanel panelBottom = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelBottom.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		getContentPane().add(panelBottom, BorderLayout.SOUTH);
		btnGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				generateReport();
			}
		});
		panelBottom.add(btnGenerate);
		
		JPanel panelRight = new JPanel();
		getContentPane().add(panelRight, BorderLayout.EAST);
		
		JTabbedPane tabPanel = new JTabbedPane();
		getContentPane().add(tabPanel, BorderLayout.CENTER);
		JPanel panelMain = new JPanel();
		tabPanel.addTab("General", panelMain);;
		panelMain.setLayout(new MigLayout("", "[][157.00][][157.00][]", "[][][][grow][][grow]"));
		
		JLabel lblDateFrom = new JLabel("Date from");
		panelMain.add(lblDateFrom, "cell 0 0");
		
		dateFrom = new JDateChooser();
		dateFrom.setDateFormatString("dd/MM/yyyy");
		panelMain.add(dateFrom, "cell 1 0,grow");
		
		JLabel lblTo = new JLabel("to");
		panelMain.add(lblTo, "cell 2 0");
		
		dateTo = new JDateChooser();
		dateTo.setDateFormatString("dd/MM/yyyy");
		panelMain.add(dateTo, "cell 3 0,grow");
		
		chckbxEuro = new JCheckBox("Euro");
		panelMain.add(chckbxEuro, "cell 0 1");
		
		chckbxQuantity = new JCheckBox("Quantity");
		chckbxQuantity.setSelected(true);
		panelMain.add(chckbxQuantity, "cell 1 1");
		
		JLabel lblRowGrouping = new JLabel("Row grouping:");
		panelMain.add(lblRowGrouping, "cell 0 2 4 1");
		
		JScrollPane scrollPane_5 = new JScrollPane();
		panelMain.add(scrollPane_5, "cell 0 3 4 1,grow");
		
		listRowGrouping = new JList();
		scrollPane_5.setViewportView(listRowGrouping);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		toolBar.setOrientation(SwingConstants.VERTICAL);
		panelMain.add(toolBar, "flowx,cell 4 3,aligny top");
		
		JButton buttonAddRowGrouping = new JButton("");
		buttonAddRowGrouping.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addRowGroups();
			}
		});
		toolBar.add(buttonAddRowGrouping);
		buttonAddRowGrouping.setIcon(new ImageIcon(SalesReport.class.getResource("/com/leosal/medrepear/frames/Graphics/list-add.png")));
		buttonAddRowGrouping.setMargin(new Insets(0, 0, 0, 0));
		
		JButton btnUp = new JButton("");
		btnUp.setMargin(new Insets(0, 0, 0, 0));
		btnUp.setIcon(new ImageIcon(SalesReport.class.getResource("/com/leosal/leotools/graphics/go-up.png")));
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				moveRowGroup(-1);
			}
		});
		toolBar.add(btnUp);
		
		JButton btnDown = new JButton("");
		btnDown.setMargin(new Insets(0, 0, 0, 0));
		btnDown.setIcon(new ImageIcon(SalesReport.class.getResource("/com/leosal/leotools/graphics/go-down.png")));
		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				moveRowGroup(1);
			}
		});
		toolBar.add(btnDown);
		
		JLabel lblColumnGrouping = new JLabel("Column grouping:");
		lblColumnGrouping.setVisible(false);
		panelMain.add(lblColumnGrouping, "cell 0 4 4 1");
		
		JScrollPane scrollPane_6 = new JScrollPane();
		scrollPane_6.setVisible(false);
		panelMain.add(scrollPane_6, "cell 0 5 4 1,grow");
		
		listColGrouping = new JList();
		scrollPane_6.setViewportView(listColGrouping);
		
		JToolBar toolBar_1 = new JToolBar();
		toolBar_1.setFloatable(false);
		toolBar_1.setOrientation(SwingConstants.VERTICAL);
		panelMain.add(toolBar_1, "flowx,cell 4 5,aligny top");
		
		JButton button = new JButton("");
		toolBar_1.add(button);
		button.setVisible(false);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addColGroups();
			}
		});
		button.setIcon(new ImageIcon(SalesReport.class.getResource("/com/leosal/medrepear/frames/Graphics/list-add.png")));
		button.setMargin(new Insets(0, 0, 0, 0));
		
		JPanel panel = new JPanel();
		tabPanel.addTab("Filters", null, panel, null);
		panel.setLayout(new MigLayout("", "[][grow][]", "[grow][grow][grow][grow][grow]"));
		
		JLabel lblDistributors = new JLabel("Distributors");
		panel.add(lblDistributors, "cell 0 0,aligny top");
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, "cell 1 0,grow");
		
		listDistrib = new JList();
		scrollPane.setViewportView(listDistrib);
		
		JButton buttonAddDistrib = new JButton("");
		buttonAddDistrib.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					addDistributors();
				} catch (UserNotAuthorisedException e1) {
					e1.printStackTrace();
				}
			}
		});
		buttonAddDistrib.setMargin(new Insets(0, 0, 0, 0));
		buttonAddDistrib.setIcon(new ImageIcon(SalesReport.class.getResource("/com/leosal/medrepear/frames/Graphics/list-add.png")));
		panel.add(buttonAddDistrib, "cell 2 0,aligny top");
		
		JLabel lblRepresentatives = new JLabel("Representatives");
		panel.add(lblRepresentatives, "cell 0 1,aligny top");
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panel.add(scrollPane_1, "cell 1 1,grow");
		
		listReps = new JList();
		scrollPane_1.setViewportView(listReps);
		
		JButton buttonAddReps = new JButton("");
		buttonAddReps.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					addReps();
				} catch (UserNotAuthorisedException e1) {
					e1.printStackTrace();
				}
			}
		});
		buttonAddReps.setIcon(new ImageIcon(SalesReport.class.getResource("/com/leosal/medrepear/frames/Graphics/list-add.png")));
		buttonAddReps.setMargin(new Insets(0, 0, 0, 0));
		panel.add(buttonAddReps, "cell 2 1,aligny top");
		
		JLabel lblRegions = new JLabel("Regions");
		panel.add(lblRegions, "cell 0 2,aligny top");
		
		JScrollPane scrollPane_2 = new JScrollPane();
		panel.add(scrollPane_2, "cell 1 2,grow");
		
		listRegions = new JList();
		scrollPane_2.setViewportView(listRegions);
		
		JButton buttonAddRegions = new JButton("");
		buttonAddRegions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					addRegions();
				} catch (UserNotAuthorisedException e1) {
					e1.printStackTrace();
				}
			}
		});
		buttonAddRegions.setIcon(new ImageIcon(SalesReport.class.getResource("/com/leosal/medrepear/frames/Graphics/list-add.png")));
		buttonAddRegions.setMargin(new Insets(0, 0, 0, 0));
		panel.add(buttonAddRegions, "cell 2 2,aligny top");
		
		JLabel lblInstitutions = new JLabel("Institutions");
		panel.add(lblInstitutions, "cell 0 3,aligny top");
		
		JScrollPane scrollPane_3 = new JScrollPane();
		panel.add(scrollPane_3, "cell 1 3,grow");
		
		listInstits = new JList();
		scrollPane_3.setViewportView(listInstits);
		
		JButton buttonAddInstits = new JButton("");
		buttonAddInstits.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					addInstits();
				} catch (UserNotAuthorisedException e1) {
					e1.printStackTrace();
				}
			}
		});
		buttonAddInstits.setIcon(new ImageIcon(SalesReport.class.getResource("/com/leosal/medrepear/frames/Graphics/list-add.png")));
		buttonAddInstits.setMargin(new Insets(0, 0, 0, 0));
		panel.add(buttonAddInstits, "cell 2 3,aligny top");
		
		JLabel lblProducts = new JLabel("Products");
		panel.add(lblProducts, "cell 0 4,aligny top");
		
		JScrollPane scrollPane_4 = new JScrollPane();
		panel.add(scrollPane_4, "cell 1 4,grow");
		
		listProds = new JList();
		scrollPane_4.setViewportView(listProds);
		
		JButton buttonAddProds = new JButton("");
		buttonAddProds.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					addProducts();
				} catch (UserNotAuthorisedException e1) {
					e1.printStackTrace();
				}
			}
		});
		buttonAddProds.setIcon(new ImageIcon(SalesReport.class.getResource("/com/leosal/medrepear/frames/Graphics/list-add.png")));
		buttonAddProds.setMargin(new Insets(0, 0, 0, 0));
		panel.add(buttonAddProds, "cell 2 4,aligny top");

	}

	protected void generateReport() {
		final Date dFrom = dateFrom.getDate();
		final Date dTo = dateTo.getDate();
		if(dFrom==null || dTo==null|| dTo.before(dFrom)){
			JOptionPane.showMessageDialog(this, 
					"Select a valid date range please!", 
					"Error", 
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				MainFrame.getMainFrame().setStatus("Generating report...");
				SalesReport.this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				reportRunning = true;
				try {
					
					exportExcel(getReportList());
					JOptionPane.showMessageDialog(SalesReport.this, "Export completed");
					
				} catch (Exception e) {
					JOptionPane.showMessageDialog(SalesReport.this, "Export failed");
					e.printStackTrace();
				}finally{
					MainFrame.getMainFrame().clearStatus();
					SalesReport.this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					reportRunning=false;
				}
				
				
			}
		}).start();	
		
	}
	
	@SuppressWarnings("unchecked")
	private void addReps() throws UserNotAuthorisedException{
		if(repsList==null){
			
			repsList = RestManager.getInstance().getRepresentatives();
			
		}
		MultiSelectPane<PersonDTO> msp = new MultiSelectPane<PersonDTO>(repsList, false);
		msp.setPreferedViewportSize(300, 600);
		int resp = JOptionPane.showConfirmDialog(this, msp, "Select representatives", JOptionPane.OK_CANCEL_OPTION);
		if(resp==JOptionPane.CANCEL_OPTION) return;
		if(msp.getSelectedIndices().length==0) return;
		int[] ids = msp.getSelectedIndices();
		repsFilter = new ArrayList<PersonDTO>();
		for(int id:ids) repsFilter.add(repsList.get(id));
		
		listReps.setModel(new ListModel<PersonDTO>() {

			@Override
			public int getSize() {
				
				return repsFilter.size();
			}

			@Override
			public PersonDTO getElementAt(int index) {
				
				return repsFilter.get(index);
			}

			@Override
			public void addListDataListener(ListDataListener l) {
				
				
			}

			@Override
			public void removeListDataListener(ListDataListener l) {
				
				
			}
		});
		
		
	}
	@SuppressWarnings("unchecked")
	private void addRegions() throws UserNotAuthorisedException{
		if(regList==null){
			regList = RestManager.getInstance().getRegions();
		}
		MultiSelectPane<RegionDTO> msp = new MultiSelectPane<RegionDTO>(regList, false);
		msp.setPreferedViewportSize(200, 400);
		int resp = JOptionPane.showConfirmDialog(this, msp, "Select regions", JOptionPane.OK_CANCEL_OPTION);
		if(resp==JOptionPane.CANCEL_OPTION) return;
		if(msp.getSelectedIndices().length==0) return;
		int[] ids = msp.getSelectedIndices();
		regFilter = new ArrayList<RegionDTO>();
		for(int id:ids) regFilter.add(regList.get(id));
		
		listRegions.setModel(new ListModel<RegionDTO>() {

			@Override
			public int getSize() {
				
				return regFilter.size();
			}

			@Override
			public RegionDTO getElementAt(int index) {
				
				return regFilter.get(index);
			}

			@Override
			public void addListDataListener(ListDataListener l) {
				
				
			}

			@Override
			public void removeListDataListener(ListDataListener l) {
				
				
			}
		});
	}
	@SuppressWarnings("unchecked")
	private void addInstits() throws UserNotAuthorisedException{
		if(institList==null){
			institList = RestManager.getInstance().getInstitutions();
		}
		MultiSelectPane<InstitutionDTO> msp = new MultiSelectPane<InstitutionDTO>(institList, false);
		msp.setPreferedViewportSize(400, 600);
		int resp = JOptionPane.showConfirmDialog(this, msp, "Select institutions", JOptionPane.OK_CANCEL_OPTION);
		if(resp==JOptionPane.CANCEL_OPTION) return;
		if(msp.getSelectedIndices().length==0) return;
		int[] ids = msp.getSelectedIndices();
		institFilter = new ArrayList<InstitutionDTO>();
		for(int id:ids) institFilter.add(institList.get(id));
		listInstits.setModel(new ListModel<InstitutionDTO>() {

			@Override
			public int getSize() {
				
				return institFilter.size();
			}

			@Override
			public InstitutionDTO getElementAt(int index) {
				
				return institFilter.get(index);
			}

			@Override
			public void addListDataListener(ListDataListener l) {
				
			}

			@Override
			public void removeListDataListener(ListDataListener l) {
				
			}
		});
	}
	@SuppressWarnings("unchecked")
	private void addProducts() throws UserNotAuthorisedException{
		if(prodList==null){
			prodList = RestManager.getInstance().getProducts();
		}
		MultiSelectPane<ProductDTO> msp = new MultiSelectPane<ProductDTO>(prodList, false);
		msp.setPreferedViewportSize(400, 600);
		int resp = JOptionPane.showConfirmDialog(this, msp, "Select products", JOptionPane.OK_CANCEL_OPTION);
		if(resp==JOptionPane.CANCEL_OPTION) return;
		if(msp.getSelectedIndices().length==0) return;
		int[] ids = msp.getSelectedIndices();
		prodFilter = new ArrayList<ProductDTO>();
		for(int id:ids) prodFilter.add(prodList.get(id));
		listProds.setModel(new ListModel<ProductDTO>() {

			@Override
			public int getSize() {
				
				return prodFilter.size();
			}

			@Override
			public ProductDTO getElementAt(int index) {
				
				return prodFilter.get(index);
			}

			@Override
			public void addListDataListener(ListDataListener l) {
				
			}

			@Override
			public void removeListDataListener(ListDataListener l) {
				
			}
		});
	}
	@SuppressWarnings("unchecked")
	private void addDistributors() throws UserNotAuthorisedException{
		if(distribList==null){
			distribList = RestManager.getInstance().getDistributors();
		}
		MultiSelectPane<InstitutionDTO> msp = new MultiSelectPane<InstitutionDTO>(distribList, false);
		int resp = JOptionPane.showConfirmDialog(this, msp, "Select distributors", JOptionPane.OK_CANCEL_OPTION);
		if(resp==JOptionPane.CANCEL_OPTION) return;
		if(msp.getSelectedIndices().length==0) return;
		int[] ids = msp.getSelectedIndices();
		distribFilter = new ArrayList<InstitutionDTO>();
		for(int id:ids) distribFilter.add(distribList.get(id));
		listDistrib.setModel(new ListModel<InstitutionDTO>() {

			@Override
			public int getSize() {
				
				return distribFilter.size();
			}

			@Override
			public InstitutionDTO getElementAt(int index) {
				
				return distribFilter.get(index);
			}

			@Override
			public void addListDataListener(ListDataListener l) {
				
			}

			@Override
			public void removeListDataListener(ListDataListener l) {
				
			}
		});
	}
	
	private void moveRowGroup(int move){
		if( move==0) return;
		int cur = listRowGrouping.getSelectedIndex();
		if(cur+move<0|| cur+move>=rowGroups.size()) return;
		Collections.swap(rowGroups, cur, cur+move);
		refreshGroupSettings();
		listRowGrouping.setSelectedIndex(cur+move);
		
	}
	
	private void addColGroups(){
		List<String> fields = Arrays.asList(SalesReportDataItem.getAvailableGroupFields());
		MultiSelectPane<String> msp = new MultiSelectPane<String>(fields, false);
		int resp = JOptionPane.showConfirmDialog(this, msp, "Select column grouping", JOptionPane.OK_CANCEL_OPTION);
		if(resp==JOptionPane.CANCEL_OPTION) return;
		if(msp.getSelectedIndices().length==0) return;
		int[] ids = msp.getSelectedIndices();
		colGroups = new ArrayList<String>();
		for(int id:ids){
			colGroups.add(fields.get(id));
			if(rowGroups!=null )rowGroups.remove(fields.get(id));
		}
		refreshGroupSettings();
	}
	private void addRowGroups(){
		List<String> fields = Arrays.asList(SalesReportDataItem.getAvailableGroupFields());
		MultiSelectPane<String> msp = new MultiSelectPane<String>(fields, false);
		int resp = JOptionPane.showConfirmDialog(this, msp, "Select column grouping", JOptionPane.OK_CANCEL_OPTION);
		if(resp==JOptionPane.CANCEL_OPTION) return;
		if(msp.getSelectedIndices().length==0) return;
		int[] ids = msp.getSelectedIndices();
		rowGroups = new ArrayList<String>();
		for(int id:ids){
			rowGroups.add(fields.get(id));
			if(colGroups!=null )colGroups.remove(fields.get(id));
		}
		refreshGroupSettings();
	}
	
	private void refreshGroupSettings(){
		if(rowGroups!=null)
			listRowGrouping.setModel(new ListModel<String>() {

				@Override
				public int getSize() {
					return rowGroups.size();
				}

				@Override
				public String getElementAt(int index) {
					return rowGroups.get(index);
				}

				@Override
				public void addListDataListener(ListDataListener l) {
					
				}

				@Override
				public void removeListDataListener(ListDataListener l) {
					
				}
			});
		if(colGroups!=null)
			listColGrouping.setModel(new ListModel<String>() {

			@Override
			public int getSize() {
				return colGroups.size();
			}

			@Override
			public String getElementAt(int index) {
				return colGroups.get(index);
			}

			@Override
			public void addListDataListener(ListDataListener l) {
				
			}

			@Override
			public void removeListDataListener(ListDataListener l) {
				
			}
		});
		
	}
	
	private synchronized List<SalesReportDataItem> getReportList() throws UserNotAuthorisedException{
		Date dFrom = dateFrom.getDate();
		Date dTo = dateTo.getDate();
		Calendar cal = new GregorianCalendar();
		cal.setTime(dFrom);
		cal.set(cal.get(Calendar.YEAR), 
				cal.get(Calendar.MONTH), 
				cal.get(Calendar.DAY_OF_MONTH),
				0, 0, 0);
		
		List<ReportDTO> reports = RestManager.getInstance().getReports(dFrom, dTo);
		dFrom = cal.getTime();
		System.out.println(""+reports.size()+" reports found");
		List<SalesReportDataItem> sales = SalesReportDataItem.union(reports, dFrom,dTo);
		System.out.println(""+sales.size()+" records to report");
		List<SalesReportDataItem> filteredSales = new ArrayList<SalesReportDataItem>();
		for(SalesReportDataItem srd:sales){
			
			if(srd.getDate().after(dTo)||srd.getDate().before(dFrom)) {
				//sales.remove(srd);
				//System.out.println("" +srd.getDate()+ " is before "+dFrom+" :: "+srd.getDate().before(dFrom));
				continue;
			}
			boolean fits = false;
			if(distribFilter!=null){
				for(InstitutionDTO distr:distribFilter)
					if(srd.getDistributor().equals(distr.getName())){
						fits=true; break;
					}
			}else fits=true;
			if(!fits){
				//sales.remove(srd);
				//System.out.println(""+ srd.getDistributor()+ " not fits");
				continue;
			}
			fits=false;
			if(repsFilter!=null){
				for(PersonDTO rep:repsFilter)
					if(srd.getRep().equals(rep.toString())){
						fits=true; break;
					}
			}else fits=true;
			if(!fits){
				//sales.remove(srd);
				//System.out.println(""+ srd.getRep()+ " not fits");
				continue;
			}
			fits=false;
			if(regFilter!=null){
				for(RegionDTO reg:regFilter)
					if(srd.getRegion().equals(reg.getName())){
						fits=true; break;
					}
			}else fits=true;
			if(!fits){
				//sales.remove(srd);
				//System.out.println(""+ srd.getRegion()+ " not fits");
				continue;
			}
			fits=false;
			if(institFilter!=null){
				for(InstitutionDTO instit:institFilter)
					if(srd.getInstitution().equals(instit.getName())){
						fits=true;break;
					}
			}else fits=true;
			if(!fits){
				//sales.remove(srd);
				//System.out.println(""+ srd.getInstitution()+ " not fits");
				continue;
			}
			fits=false;
			if(prodFilter!=null){
				for(ProductDTO prod:prodFilter)
					if(srd.getProduct().equals(prod.getName())){
						fits=true; break;
					}
			}else fits=true;
			if(!fits){
				//sales.remove(srd);
				//System.out.println(""+ srd.getProduct()+ " not fits");
				continue;
			}
			filteredSales.add(srd);
		}
		
		return filteredSales;
	}
	
	private synchronized void exportExcel(List<SalesReportDataItem> sales) throws Exception{
		//define column months
		if(sales==null || sales.size()==0){
			JOptionPane.showMessageDialog(this, "No data to show!");
			return;
		}
		if(rowGroups==null) {
			rowGroups=new ArrayList<String>();
			rowGroups.add("");
		}
		Set<String> cols = new LinkedHashSet<String>();
		for(int i=0;i<sales.size();i++)
			cols.add(new SimpleDateFormat("MMMM yyyy", Locale.ENGLISH).format(sales.get(i).getDate()));
		Object[] colNames= cols.toArray();
		Collections.sort(sales, new Comparator<SalesReportDataItem>() {

			@Override
			public int compare(SalesReportDataItem o1, SalesReportDataItem o2) {
				String s1="", s2="";
				for(String s:rowGroups)s1+=o1.getField(s)!=null?o1.getField(s):" NULL";
				//for(String s:colGroups)s1+=o1.getField(s)!=null?o1.getField(s):" NULL";
				
				for(String s:rowGroups)s2+=o2.getField(s)!=null?o2.getField(s):" NULL";
				//for(String s:colGroups)s2+=o2.getField(s)!=null?o2.getField(s):" NULL";
				
				return s1.compareTo(s2);
			}
		});
		Map<String, Float> rowValues = new HashMap<String, Float>();
		@SuppressWarnings("unused")
		Map<String, Float> colValues = new HashMap<String, Float>();
		String exc = "salesReport";
		File excelFile;
		WritableWorkbook book;
		int num=0;
		while(true){
			excelFile = new File(exc+(num==0?"":"("+num+")")+".xls");
			try{
				excelFile.delete();
				book = Workbook.createWorkbook(excelFile);
				break;
			}catch(Exception e){
				num++;
			}
		}
		
		
		try{
			WritableSheet sheet = book.createSheet("report", 0);
			WritableFont font = new WritableFont(WritableFont.ARIAL);
			WritableFont fontBold = new WritableFont(WritableFont.ARIAL);
			fontBold.setBoldStyle(WritableFont.BOLD);
			//WritableCellFormat numberFormat = new WritableCellFormat(font, new NumberFormat("# ##0.00"));
			//numberFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
			WritableCellFormat mainFormatt = new WritableCellFormat(font, new NumberFormat("# ##0.00"));
			//mainFormat.setFont(font);
			mainFormatt.setBorder(Border.ALL, BorderLineStyle.THIN);
			
			//set backgrounds for group levels
			int rgb = 160;
			WritableCellFormat[] groupFormats = new WritableCellFormat[rowGroups.size()];
			for(int i=0;i<rowGroups.size();i++){
				rgb=Math.min(rgb+i*32,255);
				if(i==rowGroups.size()-1) rgb=255;
				WritableCellFormat wf = new WritableCellFormat(mainFormatt);
				if(i==rowGroups.size()-1)wf.setBackground(Colour.WHITE);
				else switch(i){
				case 0: wf.setBackground(Colour.GREY_50_PERCENT); break;
				case 1: wf.setBackground(Colour.GREY_40_PERCENT); break;
				case 2: wf.setBackground(Colour.GREY_25_PERCENT); break;
				default: wf.setBackground(Colour.WHITE); break;
				}
				groupFormats[i] = wf;
			}
			
			WritableCellFormat headerFormat = new WritableCellFormat(mainFormatt);
			headerFormat.setFont(fontBold);
			headerFormat.setWrap(true);
			
			//creating sheet header
			sheet.setColumnView(0, 2);
			//sheet.setRowView(1, 1000,false);
			sheet.addCell(new Label(1, 1, 
					new SimpleDateFormat("dd.MM.yyyy").format(dateFrom.getDate())
					+" - "
					+new SimpleDateFormat("dd.MM.yyyy").format(dateTo.getDate())
					+" sales report."));
			sheet.mergeCells(1, 1, 4, 1);
			//Creating table header
			
			if(rowGroups==null||rowGroups.size()==0)
				sheet.addCell(new Label(EXCEL_FIRST_COL, EXCEL_FIRST_ROW+1, "TOTALS", headerFormat));
			else
				for(int col = 0;col<rowGroups.size();col++){
					sheet.addCell(new Label(EXCEL_FIRST_COL+col, EXCEL_FIRST_ROW, rowGroups.get(col),headerFormat));
					sheet.mergeCells(EXCEL_FIRST_COL+col, EXCEL_FIRST_ROW, 
							EXCEL_FIRST_COL+col, EXCEL_FIRST_ROW+1);
				}
			//Set last column for groups wider
			sheet.setColumnView(EXCEL_FIRST_COL+rowGroups.size()-1, 20);
			
			//add months to headers
			int curCol=EXCEL_FIRST_COL+rowGroups.size();
			for(int i = 0;i<colNames.length;i++){
				sheet.addCell(new Label(curCol, EXCEL_FIRST_ROW, colNames[i].toString(),headerFormat));
				if(chckbxQuantity.isSelected() && chckbxEuro.isSelected()){
					sheet.mergeCells(curCol, EXCEL_FIRST_ROW, curCol+1, EXCEL_FIRST_ROW);
				}
				if(chckbxQuantity.isSelected())
					{
					sheet.addCell(new Label(curCol, EXCEL_FIRST_ROW+1, "Packs",headerFormat));
					curCol++;
					}
				if(chckbxEuro.isSelected())
				{
				sheet.addCell(new Label(curCol, EXCEL_FIRST_ROW+1, "Euro",headerFormat));
				curCol++;
				}
			}
			//Add column header for TOTALS
			sheet.addCell(new Label(curCol, EXCEL_FIRST_ROW, "TOTALS",headerFormat));
			if(chckbxQuantity.isSelected() && chckbxEuro.isSelected()){
				sheet.mergeCells(curCol, EXCEL_FIRST_ROW, curCol+1, EXCEL_FIRST_ROW);
			}
			if(chckbxQuantity.isSelected())
				{
				sheet.addCell(new Label(curCol, EXCEL_FIRST_ROW+1, "Packs",headerFormat));
				curCol++;
				}
			if(chckbxEuro.isSelected())
			{
			sheet.addCell(new Label(curCol, EXCEL_FIRST_ROW+1, "Euro",headerFormat));
			curCol++;
			}
			
			int curRow=EXCEL_FIRST_ROW+1;
			String[] rowG = new String[rowGroups.size()];
			int[] rowGG = new int[rowGroups.size()];
			for(int i=0;i<rowGroups.size();i++){
				
				rowG[i]=sales.get(0).getField(rowGroups.get(i))==null?
						"Undefined":sales.get(0).getField(rowGroups.get(i)).toString();
				rowGG[i] = curRow+1;
			}
				
			for(int saleIndex=0;saleIndex<sales.size();saleIndex++){
				SalesReportDataItem srd = sales.get(saleIndex);
				
				for(int i=0;i<rowGroups.size();i++){
					if(!rowG[i].equals(srd.getField(rowGroups.get(i))==null?
							"Undefined":srd.getField(rowGroups.get(i)).toString())){ // group changed
						
						
						
						
						
						//new rows from the last to be inserted
						for(int insertRec=rowGroups.size()-1;insertRec>=i;insertRec--){
							int iniRow = rowGG[insertRec];
							int endRow = curRow;
							
							if(iniRow<=endRow && insertRec<rowGroups.size()-1){
								sheet.setRowGroup(iniRow, endRow, true);
								//System.out.println("\tGrouping from "+iniRow+" to "+endRow);
								rowGG[insertRec] = endRow+(insertRec-i+2);
							}
							curRow++;
							sheet.addCell(new Label(EXCEL_FIRST_COL+insertRec, 
									curRow, 
									rowG[insertRec],
									groupFormats[insertRec]));
							//merge cels to the right
							sheet.mergeCells(EXCEL_FIRST_COL+insertRec, curRow, 
									EXCEL_FIRST_COL+rowGroups.size()-1, curRow);
							curCol=EXCEL_FIRST_COL+rowGroups.size();
							float totPacks=0f, totEuro=0f;
							for(int ii = 0;ii<colNames.length;ii++){
								//sheet.addCell(new Label(curCol, EXCEL_FIRST_ROW, colNames[i].toString()));
								
								if(chckbxQuantity.isSelected())
									{
									//sheet.addCell(new Label(curCol, EXCEL_FIRST_ROW+1, "Quantity"));
									String key = rowGroups.get(insertRec)+colNames[ii].toString()+"quant";
									sheet.addCell(new Number(curCol, 
											curRow, 
											rowValues.get(key)==null?0:rowValues.get(key),
											groupFormats[insertRec]));
									totPacks+=rowValues.get(key)==null?0:rowValues.get(key);
									curCol++;
									}
								if(chckbxEuro.isSelected())
								{
									String key = rowGroups.get(insertRec)+colNames[ii].toString()+"euro";
									sheet.addCell(new Number(curCol, 
											curRow,
											rowValues.get(key)==null?0:rowValues.get(key),
											groupFormats[insertRec]));
									totEuro+=rowValues.get(key)==null?0:rowValues.get(key);
									curCol++;
								}
							}
							//add tot euro and totPacks in last column

							if(chckbxQuantity.isSelected())
								{
								//sheet.addCell(new Label(curCol, EXCEL_FIRST_ROW+1, "Quantity"));
								//String key = rowGroups.get(insertRec)+colNames[ii].toString()+"quant";
								sheet.addCell(new Number(curCol, 
										curRow, 
										totPacks,
										groupFormats[insertRec]));
								curCol++;
								}
							if(chckbxEuro.isSelected())
							{
								//String key = rowGroups.get(insertRec)+colNames[ii].toString()+"euro";
								sheet.addCell(new Number(curCol, 
										curRow,
										totEuro,
										groupFormats[insertRec]));
								
							}
						}
						
						
						//all subsequent group values becomes 0, rowG valuse become current
						for(int ii = i;ii<rowGroups.size();ii++){
							rowG[ii]=srd.getField(rowGroups.get(ii))==null?
									"":srd.getField(rowGroups.get(ii)).toString();
							//System.out.println("\tNew group: "+rowG[ii]);
							for(int iii = 0;iii<colNames.length;iii++){
								if(chckbxQuantity.isSelected()){
								//sheet.addCell(new Label(curCol, EXCEL_FIRST_ROW+1, "Quantity"));
									String key = rowGroups.get(ii)+colNames[iii].toString()+"quant";
									rowValues.put(key, 0f);
								}
								if(chckbxEuro.isSelected()){
									String key = rowGroups.get(ii)+colNames[iii].toString()+"euro";
									rowValues.put(key, 0f);
								}
							}
						}
						break;
					}
					
					
					
					
				}
				//add to all group values current record values
				for(int iii = 0;iii<rowGroups.size();iii++){
					if(chckbxQuantity.isSelected()){
					//sheet.addCell(new Label(curCol, EXCEL_FIRST_ROW+1, "Quantity"));
						String key = rowGroups.get(iii)
								+new SimpleDateFormat("MMMM yyyy", Locale.ENGLISH).format(srd.getDate())
								+"quant";
						
						rowValues.put(key, rowValues.get(key)==null?
								srd.getQuantity():
									rowValues.get(key)+srd.getQuantity());
					}
					if(chckbxEuro.isSelected()){
						String key = rowGroups.get(iii)
								+new SimpleDateFormat("MMMM yyyy", Locale.ENGLISH).format(srd.getDate())
								+"euro";
						rowValues.put(key, rowValues.get(key)==null?
								srd.getQuantity():
									rowValues.get(key)+srd.getCost());
					}
				}
				if(chckbxQuantity.isSelected()){
					//sheet.addCell(new Label(curCol, EXCEL_FIRST_ROW+1, "Quantity"));
						String key = "TOTALS"
								+new SimpleDateFormat("MMMM yyyy", Locale.ENGLISH).format(srd.getDate())
								+"quant";
						
						rowValues.put(key, rowValues.get(key)==null?
								srd.getQuantity():
									rowValues.get(key)+srd.getQuantity());
				}
				if(chckbxEuro.isSelected()){
						String key = "TOTALS"
								+new SimpleDateFormat("MMMM yyyy", Locale.ENGLISH).format(srd.getDate())
								+"euro";
						rowValues.put(key, rowValues.get(key)==null?
								srd.getQuantity():
									rowValues.get(key)+srd.getCost());
				}
				
			}
			//Group last group
			
			//add last row
			for(int insertRec=rowGroups.size()-1;insertRec>=0;insertRec--){
				int iniRow = rowGG[insertRec];
				int endRow = curRow;
				
				if(iniRow<=endRow && insertRec<rowGroups.size()-1){
					sheet.setRowGroup(iniRow, endRow, true);
					//System.out.println("\tGrouping from "+iniRow+" to "+endRow);
					rowGG[insertRec] = endRow+(insertRec+2);
				}
				curRow++;
				sheet.addCell(new Label(EXCEL_FIRST_COL+insertRec, curRow, rowG[insertRec],groupFormats[insertRec]));
				if(rowGroups.size()>1)
					sheet.mergeCells(EXCEL_FIRST_COL+insertRec, curRow, 
							EXCEL_FIRST_COL+rowGroups.size()-1, curRow);
				curCol=EXCEL_FIRST_COL+rowGroups.size();
				float totPacks=0f, totEuro=0f;
				for(int ii = 0;ii<colNames.length;ii++){
					//sheet.addCell(new Label(curCol, EXCEL_FIRST_ROW, colNames[i].toString()));
					
					if(chckbxQuantity.isSelected())
						{
						//sheet.addCell(new Label(curCol, EXCEL_FIRST_ROW+1, "Quantity"));
						String key = rowGroups.get(insertRec)+colNames[ii].toString()+"quant";
						sheet.addCell(new Number(curCol, 
								curRow, 
								rowValues.get(key)==null?0:rowValues.get(key),
										groupFormats[insertRec]));
						totPacks+=rowValues.get(key)==null?0:rowValues.get(key);
						curCol++;
						}
					if(chckbxEuro.isSelected())
					{
						String key = rowGroups.get(insertRec)+colNames[ii].toString()+"euro";
						sheet.addCell(new Number(curCol, 
								curRow,
								rowValues.get(key)==null?0:rowValues.get(key),
										groupFormats[insertRec]));
						totEuro+=rowValues.get(key)==null?0:rowValues.get(key);
						curCol++;
					}
				}
				if(chckbxQuantity.isSelected())
				{
				//sheet.addCell(new Label(curCol, EXCEL_FIRST_ROW+1, "Quantity"));
				sheet.addCell(new Number(curCol, 
						curRow, 
						totPacks,
						groupFormats[insertRec]));
				curCol++;
				}
				if(chckbxEuro.isSelected())
				{
					sheet.addCell(new Number(curCol, 
							curRow,
							totEuro,
							groupFormats[insertRec]));
					curCol++;
				}
			}
			WritableCellFormat totRow = new WritableCellFormat(fontBold, new NumberFormat("# ##0.00"));
			totRow.setBorder(Border.ALL, BorderLineStyle.THIN);
			//add totals
			curRow++;
			sheet.addCell(new Label(EXCEL_FIRST_COL, curRow, "TOTALS:",headerFormat));
			if(rowGroups.size()>1)
				sheet.mergeCells(EXCEL_FIRST_COL, curRow, EXCEL_FIRST_COL+rowGroups.size()-1, curRow);
			curCol=EXCEL_FIRST_COL+rowGroups.size();
			float totPacks=0f, totEuro = 0f;
			for(int ii = 0;ii<colNames.length;ii++){
				//sheet.addCell(new Label(curCol, EXCEL_FIRST_ROW, colNames[i].toString()));
				
				if(chckbxQuantity.isSelected())
					{
					//sheet.addCell(new Label(curCol, EXCEL_FIRST_ROW+1, "Quantity"));
					String key ="TOTALS"+colNames[ii].toString()+"quant";
					sheet.addCell(new Number(curCol, 
							curRow, 
							rowValues.get(key)==null?0:rowValues.get(key),
							totRow));
					totPacks+=rowValues.get(key)==null?0:rowValues.get(key);
					curCol++;
					}
				if(chckbxEuro.isSelected())
				{
					String key = "TOTALS"+colNames[ii].toString()+"euro";
					sheet.addCell(new Number(curCol, 
							curRow,
							rowValues.get(key)==null?0:rowValues.get(key),
							totRow));
					totEuro+=rowValues.get(key)==null?0:rowValues.get(key);
					curCol++;
				}
			}
			if(chckbxQuantity.isSelected())
			{
			//sheet.addCell(new Label(curCol, EXCEL_FIRST_ROW+1, "Quantity"));
			sheet.addCell(new Number(curCol, 
					curRow, 
					totPacks,
					totRow));
			curCol++;
			}
			if(chckbxEuro.isSelected())
			{
				sheet.addCell(new Number(curCol, 
						curRow,
						totEuro,
						totRow));
			}
			
			//set row grouping for TOTALS
			sheet.setRowGroup(EXCEL_FIRST_ROW+2, curRow-1, false);
			sheet.getSettings().setVerticalFreeze(EXCEL_FIRST_ROW+2);
			sheet.getSettings().setHorizontalFreeze(EXCEL_FIRST_COL+rowGroups.size());
			
			//surrond table with BOLD border
			WritableCellFormat left = new WritableCellFormat();
			left.setBorder(Border.RIGHT, BorderLineStyle.MEDIUM);
			
			WritableCellFormat right = new WritableCellFormat();
			right.setBorder(Border.LEFT, BorderLineStyle.MEDIUM);
			
			WritableCellFormat top = new WritableCellFormat();
			top.setBorder(Border.BOTTOM, BorderLineStyle.MEDIUM);
			
			WritableCellFormat bottom = new WritableCellFormat();
			bottom.setBorder(Border.TOP, BorderLineStyle.MEDIUM);
			int rowCount = sheet.getRows();
			int colCount = sheet.getColumns();
			for(int i=EXCEL_FIRST_ROW;i<rowCount;i++){
				sheet.addCell(new Label(EXCEL_FIRST_COL-1, i, "", left));
				sheet.addCell(new Label(colCount, i, "", right));
			}
			for(int i=EXCEL_FIRST_COL;i<colCount;i++){
				sheet.addCell(new Label(i,EXCEL_FIRST_ROW-1,  "", top));
				sheet.addCell(new Label(i,rowCount,  "", bottom));
			}
			
			
		}catch(Exception e){
			throw e;
		}finally{
			book.write(); 
			book.close();
		}
		
		Desktop desk = Desktop.getDesktop();
		try {
			desk.open(excelFile);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		
	}
	
	
	
	private static int colId=10000;
	@SuppressWarnings("unused")
	private class CustomColour extends Colour{
		

		protected CustomColour( int r, int g, int b) {
			super(++colId, ""+colId, r, g, b);
			System.out.println("["+r+","+g+","+b+"] created");
		}
		
	}

}
