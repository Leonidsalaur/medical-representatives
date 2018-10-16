package com.leosal.medrepear.frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.prefs.Preferences;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import javax.swing.table.AbstractTableModel;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import net.miginfocom.swing.MigLayout;

import com.leosal.leotools.exceptions.UserNotAuthorisedException;
import com.leosal.leotools.widgets.MultiSelectPane;
import com.leosal.medrepear.dto.DistribInstitDTO;
import com.leosal.medrepear.dto.DistribProdDTO;
import com.leosal.medrepear.dto.InstitTypeDTO;
import com.leosal.medrepear.dto.InstitutionDTO;
import com.leosal.medrepear.dto.ProductDTO;
import com.leosal.medrepear.dto.RegionDTO;
import com.leosal.medrepear.dto.ReportDTO;
import com.leosal.medrepear.dto.SaleDTO;
import com.leosal.medrepear.service.RestManager;
import com.toedter.calendar.JDateChooser;

public class ReportLoader extends JInternalFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Preferences prefs = Preferences.userNodeForPackage(com.leosal.medrepear.frames.ReportLoader.class);
	private JTextField textReportFile;
	
	String[][] tableData = null, resultTableData = null;
	int rows = 0, columns = 0;
	private final ButtonGroup buttonGroupProdNameCodeInstNameCode = new ButtonGroup();
	private final ButtonGroup buttonGroupProdNameCodeInstNameCodeInstRowCol = new ButtonGroup();
	private final ButtonGroup buttonGroupProdNameCode = new ButtonGroup();
	private final ButtonGroup buttonGroupProdNameCodeProdRowCol = new ButtonGroup();
	private JTable table;
	private JTable tableResult;
	private JSpinner spinnerColumnFrom;
	private JSpinner spinnerColumnTo;
	private JSpinner spinnerFirstRow;
	private JRadioButton rdbtnInstitNames;
	private JRadioButton rdbtnInstitCodes;
	private JRadioButton rdbtnInstitColumn;
	private JRadioButton rdbtnInstitRow;
	private JSpinner spinnerInstitRowCol;
	private JRadioButton rdbtnProdColumn;
	private JRadioButton rdbtnProdRow;
	private JRadioButton rdbtnProdBarcodes;
	private JRadioButton rdbtnProdNames;
	private JList<String> listInstitutions;
	private JList<String> listProducts;
	private JSpinner spinnerPrductsRowCol;
	private JComboBox<InstitutionDTO> comboDistrib;
	
	@SuppressWarnings("unused")
	private List<String> institutions, products;
	private List<InstitutionDTO> distributors;
	//private List<SalesReportDataItem> sales = null;
	
	private static final boolean TEST_MODE = false;
	private JDateChooser dateFrom;
	private JDateChooser dateTo;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReportLoader frame = new ReportLoader();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws UserNotAuthorisedException 
	 */
	public ReportLoader() throws UserNotAuthorisedException {
		setTitle("Report Loader");
		setResizable(true);
		setMaximizable(true);
		setClosable(true);
		setBounds(100, 100, 733, 594);
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
				
				
			}
			
			@Override
			public void internalFrameClosed(InternalFrameEvent e) {
				
				
			}
			
			@Override
			public void internalFrameActivated(InternalFrameEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		//distributors = DatabaseHelper.getDBHelper().getDistributors();
		
		distributors = RestManager.getInstance().getDistributors();
		
		
		JPanel panelTop = new JPanel();
		getContentPane().add(panelTop, BorderLayout.NORTH);
		panelTop.setLayout(new MigLayout("", "[][221.00:224.00,grow]", "[25.00px][26.00px]"));
		
		JLabel lblDistributor_1 = new JLabel("Distributor:");
		panelTop.add(lblDistributor_1, "cell 0 0,alignx trailing");
		
		if(!TEST_MODE)comboDistrib = new JComboBox<InstitutionDTO>(distributors.toArray(new InstitutionDTO[0]));
		else comboDistrib = new JComboBox<InstitutionDTO>();
		comboDistrib.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				restoreReportSettings();
				
			}
		});
		comboDistrib.setPreferredSize(new Dimension(300, 22));
		panelTop.add(comboDistrib, "cell 1 0,alignx left,growy");
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null), "General settings", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelTop.add(panel_2, "cell 0 1 2 1,grow");
		panel_2.setLayout(new MigLayout("", "[61.00px][116.00][150][30.00][150.00,grow][]", "[25.00px][grow][][][][]"));
		
		JLabel lblReportFile = new JLabel("Report file: ");
		panel_2.add(lblReportFile, "cell 0 0,alignx right,aligny center");
		
		textReportFile = new JTextField();
		panel_2.add(textReportFile, "cell 1 0 4 1,grow");
		textReportFile.setColumns(10);
		
		JButton buttonGetFile = new JButton("....");
		panel_2.add(buttonGetFile, "cell 5 0");
		buttonGetFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getReportFile();
			}
		});
		buttonGetFile.setMargin(new Insets(2, 2, 2, 2));
		buttonGetFile.setToolTipText("Select file to load");
		
		JLabel lblFrom = new JLabel("Date range from ");
		panel_2.add(lblFrom, "cell 1 1,alignx left");
		
		dateFrom = new JDateChooser();
		dateFrom.setDateFormatString("dd/MM/yyyy");
		panel_2.add(dateFrom, "cell 2 1,grow");
		
		JLabel lblTo = new JLabel(" to ");
		panel_2.add(lblTo, "cell 3 1,alignx center");
		
		dateTo = new JDateChooser();
		dateTo.setDateFormatString("dd/MM/yyyy");
		dateTo.setPreferredSize(new Dimension(150, 20));
		panel_2.add(dateTo, "cell 4 1,alignx left,growy");
		
		JLabel lblColumnRangeFrom = new JLabel("Column range from ");
		panel_2.add(lblColumnRangeFrom, "cell 1 2,alignx left");
		
		spinnerColumnFrom = new JSpinner();
		
		spinnerColumnFrom.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		spinnerColumnFrom.setPreferredSize(new Dimension(50, 18));
		panel_2.add(spinnerColumnFrom, "cell 2 2,growx");
		
		JLabel lblTo_1 = new JLabel(" to ");
		panel_2.add(lblTo_1, "cell 3 2,alignx center");
		
		spinnerColumnTo = new JSpinner();
		
		spinnerColumnTo.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		spinnerColumnTo.setPreferredSize(new Dimension(150, 18));
		panel_2.add(spinnerColumnTo, "cell 4 2");
		
		JLabel lblFirstRowWith = new JLabel("First row with data ");
		panel_2.add(lblFirstRowWith, "cell 1 3,alignx left");
		
		spinnerFirstRow = new JSpinner();
		
		spinnerFirstRow.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		spinnerFirstRow.setPreferredSize(new Dimension(50, 18));
		panel_2.add(spinnerFirstRow, "cell 2 3,growx");
		
		JButton btnT = new JButton("T");
		btnT.setVisible(false);
		btnT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				test();
			}
		});
		btnT.setMargin(new Insets(0, 0, 0, 0));
		panel_2.add(btnT, "cell 5 3");
		
		JPanel panelLeft = new JPanel();
		getContentPane().add(panelLeft, BorderLayout.WEST);
		panelLeft.setLayout(new MigLayout("", "[222.00px:229.00px]", "[][158.00:177.00,grow][187.00:207.00,grow]"));
		
		JPanel panelInstitutionSettings = new JPanel();
		panelInstitutionSettings.setBorder(new TitledBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null), "Institutions settings", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelLeft.add(panelInstitutionSettings, "cell 0 1,grow");
		panelInstitutionSettings.setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPaneInstitutions = new JSplitPane();
		splitPaneInstitutions.setDividerSize(0);
		splitPaneInstitutions.setOrientation(JSplitPane.VERTICAL_SPLIT);
		panelInstitutionSettings.add(splitPaneInstitutions);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		splitPaneInstitutions.setLeftComponent(panel);
		panel.setLayout(new MigLayout("", "[][][51.00:43.00]", "[][]"));
		
		rdbtnInstitNames = new JRadioButton("Names");
		buttonGroupProdNameCodeInstNameCode.add(rdbtnInstitNames);
		rdbtnInstitNames.setSelected(true);
		panel.add(rdbtnInstitNames, "cell 0 0");
		
		rdbtnInstitCodes = new JRadioButton("Codes");
		buttonGroupProdNameCodeInstNameCode.add(rdbtnInstitCodes);
		panel.add(rdbtnInstitCodes, "cell 1 0");
		
		rdbtnInstitColumn = new JRadioButton("Column");
		
		buttonGroupProdNameCodeInstNameCodeInstRowCol.add(rdbtnInstitColumn);
		panel.add(rdbtnInstitColumn, "cell 0 1");
		
		rdbtnInstitRow = new JRadioButton("Row");
		
		rdbtnInstitRow.setSelected(true);
		buttonGroupProdNameCodeInstNameCodeInstRowCol.add(rdbtnInstitRow);
		panel.add(rdbtnInstitRow, "cell 1 1");
		
		spinnerInstitRowCol = new JSpinner();
		
		spinnerInstitRowCol.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		panel.add(spinnerInstitRowCol, "cell 2 1,growx");
		
		JScrollPane scrollPaneInstList = new JScrollPane();
		splitPaneInstitutions.setRightComponent(scrollPaneInstList);
		
		listInstitutions = new JList<String>();
		scrollPaneInstList.setViewportView(listInstitutions);
		
		JPanel panelProductSettings = new JPanel();
		panelProductSettings.setBorder(new TitledBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null), "Products settings", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelLeft.add(panelProductSettings, "cell 0 2,grow");
		panelProductSettings.setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPaneProducts = new JSplitPane();
		splitPaneProducts.setDividerSize(0);
		splitPaneProducts.setContinuousLayout(true);
		splitPaneProducts.setOrientation(JSplitPane.VERTICAL_SPLIT);
		panelProductSettings.add(splitPaneProducts, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		splitPaneProducts.setLeftComponent(panel_1);
		panel_1.setLayout(new MigLayout("", "[57px][][52.00:n]", "[23px][]"));
		
		rdbtnProdNames = new JRadioButton("Names");
		buttonGroupProdNameCode.add(rdbtnProdNames);
		rdbtnProdNames.setSelected(true);
		panel_1.add(rdbtnProdNames, "cell 0 0,alignx left,aligny top");
		
		rdbtnProdBarcodes = new JRadioButton("Codes");
		buttonGroupProdNameCode.add(rdbtnProdBarcodes);
		panel_1.add(rdbtnProdBarcodes, "cell 1 0");
		
		rdbtnProdColumn = new JRadioButton("Column");
		
		rdbtnProdColumn.setSelected(true);
		buttonGroupProdNameCodeProdRowCol.add(rdbtnProdColumn);
		panel_1.add(rdbtnProdColumn, "cell 0 1");
		
		rdbtnProdRow = new JRadioButton("Row");
		
		buttonGroupProdNameCodeProdRowCol.add(rdbtnProdRow);
		panel_1.add(rdbtnProdRow, "cell 1 1");
		
		spinnerPrductsRowCol = new JSpinner();
		
		spinnerPrductsRowCol.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		panel_1.add(spinnerPrductsRowCol, "cell 2 1,growx");
		
		JScrollPane scrollPaneProducts = new JScrollPane();
		splitPaneProducts.setRightComponent(scrollPaneProducts);
		
		listProducts = new JList<String>();
		scrollPaneProducts.setViewportView(listProducts);
		
		JPanel panelBottom = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelBottom.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		getContentPane().add(panelBottom, BorderLayout.SOUTH);
		
		JButton btnLoadReport = new JButton("Load report");
		btnLoadReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					loadReport();
				} catch (UserNotAuthorisedException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		JButton btnPreview = new JButton("Preview");
		btnPreview.setVisible(false);
		btnPreview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				preview();
			}
		});
		panelBottom.add(btnPreview);
		panelBottom.add(btnLoadReport);
		
		JPanel panelRight = new JPanel();
		getContentPane().add(panelRight, BorderLayout.EAST);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setOneTouchExpandable(true);
		splitPane.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		splitPane.setResizeWeight(0.5);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		getContentPane().add(splitPane, BorderLayout.CENTER);
		
		JScrollPane scrollPaneReportFile = new JScrollPane();
		scrollPaneReportFile.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Report fields", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		splitPane.setLeftComponent(scrollPaneReportFile);
		
		table = new JTable();
		scrollPaneReportFile.setViewportView(table);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setFillsViewportHeight(true);
		table.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
		
		JScrollPane scrollPaneReportResult = new JScrollPane();
		scrollPaneReportResult.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Resulting table preview", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		splitPane.setRightComponent(scrollPaneReportResult);
		
		tableResult = new JTable();
		scrollPaneReportResult.setViewportView(tableResult);
		tableResult.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tableResult.setFillsViewportHeight(true);
		tableResult.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
		
		//Listeners
		spinnerColumnFrom.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				tableSettingsChanged();
			}
		});
		
		spinnerColumnTo.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				tableSettingsChanged();
			}
		});
		
		spinnerFirstRow.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				tableSettingsChanged();
			}
		});
		
		rdbtnInstitColumn.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				tableSettingsChanged();
			}
		});
		
		rdbtnInstitRow.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				tableSettingsChanged();
			}
		});
		
		spinnerInstitRowCol.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				tableSettingsChanged();
			}
		});
		
		rdbtnProdColumn.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				tableSettingsChanged();
			}
		});
		
		rdbtnProdRow.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				tableSettingsChanged();
			}
		});
		
		spinnerPrductsRowCol.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				tableSettingsChanged();
			}
		});
		
		
		

	}
	
	private void saveSettings(){
		InstitutionDTO distr = (InstitutionDTO) comboDistrib.getSelectedItem();
		prefs.putInt("ColumnFrom"+distr.getId(), (Integer) spinnerColumnFrom.getValue());
		prefs.putInt("ColumnTo"+distr.getId(), (Integer) spinnerColumnTo.getValue());
		prefs.putInt("FirstRow"+distr.getId(), (Integer) spinnerFirstRow.getValue());
		prefs.putInt("InstitColRow"+distr.getId(), (Integer) spinnerInstitRowCol.getValue());
		prefs.putInt("ProductsRowCol"+distr.getId(), (Integer) spinnerPrductsRowCol.getValue());
		prefs.put("InstitNamesCodes"+distr.getId(), rdbtnInstitCodes.isSelected()?"codes":"names");
		prefs.put("InstitRowCol"+distr.getId(),rdbtnInstitRow.isSelected()?"row":"col");
		prefs.put("ProdNameBarcode"+distr.getId(), rdbtnProdNames.isSelected()?"names":"codes");
		prefs.put("ProdRowCol"+distr.getId(), rdbtnProdRow.isSelected()?"row":"col");
	}
	
	private void loadReport() throws UserNotAuthorisedException {
		saveSettings();
		Date dFrom = dateFrom.getDate();
		Date dTo = dateTo.getDate();
		if(dFrom==null || dTo==null|| dTo.before(dFrom)){
			JOptionPane.showMessageDialog(this, 
					"Select a valid date range please!", 
					"Error", 
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		if (resultTableData== null|| resultTableData.length==0)return;
		if(!checkInstitutions()) return;
		if(!checkProducts())return;
		InstitutionDTO distr = (InstitutionDTO) comboDistrib.getSelectedItem();
		/*
		 * if checking is passed normally: Product names(codes) are changed to product id's
		 * and institution names(codes) are changed to institution id's
		 */
		
		try{
			this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			/*
			Session ss = getSession();
			
			// verify if report for this dates range and this distributor already exists
			Query q = ss.createQuery("FROM Reports WHERE "
					+ " ((per_start<=:f1 AND per_end>=:f2)"
					+ " OR "
					+ "(per_start<=:t1 AND per_end>=:t2)) AND "
					+ "distr_id=:dId");
			q.setDate("f1", dFrom);
			q.setDate("f2", dFrom);
			q.setDate("t1", dTo);
			q.setDate("t2", dTo);
			q.setInteger("dId", distr.getId());
			
			
			@SuppressWarnings("unchecked")
			*/
			List<ReportDTO> list = RestManager.getInstance().getReports(dFrom,dTo);
			if(list!=null && list.size()>0){
				int r = JOptionPane.showConfirmDialog(this,
						""+list.size()+" report"+(list.size()>1?"s ":" ")+"from database intersects "
								+ "currently selected dates range!"
								+ "\nWould you like to delete "+(list.size()>1?"them ":"it ")+"from database?", 
						"Attention!!!", 
						JOptionPane.YES_NO_OPTION);
				if(r==JOptionPane.NO_OPTION) return;
				
				RestManager.getInstance().removeFromDatabase(list);
				
			}
			
		
			
			ReportDTO rep = new ReportDTO();
			rep.setDistributor(distr);
			rep.setPerStart(dFrom);
			rep.setPerEnd(dTo);
			for(int row = 1;row<resultTableData.length;row++){
				if(resultTableData[row][0]==null) continue;
				String prStr = resultTableData[row][0].replaceAll("[^\\d]", "");
				if(prStr.length()==0) continue;
				int prId = Integer.parseInt(prStr);
				ProductDTO product = RestManager.getInstance().getProduct(prId);
				for(int col = 1; col<resultTableData[row].length; col++){
					if(resultTableData[0][col]==null) continue;
					if(resultTableData[row][col]==null) continue;
					String inStr = resultTableData[0][col].replaceAll("[^\\d]", "");
					if(inStr.length()==0) continue;
					int inId = Integer.parseInt(inStr);
					InstitutionDTO instit = RestManager.getInstance().getInstitution(inId);
					String qStr = resultTableData[row][col].replaceAll(",",".").replaceAll("[^\\d.-]", "");
					if(qStr.length()==0) continue;
					float quan = Float.parseFloat(qStr);
					if(quan==0) continue;
					SaleDTO sal = new SaleDTO();
					//sal.setReport(rep);
					sal.setInstitution(instit);
					sal.setProduct(product);
					sal.setQuant(quan);
					rep.addSale(sal);
					
				}
			}
			
			rep = RestManager.getInstance().saveObject(rep);
	
			//sales = SalesReportDataItem.build(rep);
			JOptionPane.showMessageDialog(this, "Report loaded in database");
		}catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Loading failed.\nSee log file for details.");
		}finally{
			this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}
		
		
		
		
	}
	
	private boolean checkInstitutions() throws UserNotAuthorisedException{
		if (resultTableData== null|| resultTableData.length==0) return false;
		InstitutionDTO distr = (InstitutionDTO) comboDistrib.getSelectedItem();
		int counter = 0;
		int i;
		for(i = 1;i<resultTableData[0].length;i++){
			if(resultTableData[0][i]==null || resultTableData[0][i].trim().length()==0) continue;
			DistribInstitDTO dinst=null;
			if(rdbtnInstitNames.isSelected()){
				dinst = RestManager.getInstance().getDistributorInstitutionByName(distr,resultTableData[0][i].trim());
			}else{
				dinst = RestManager.getInstance().getDistributorInstitutionByCode(distr,resultTableData[0][i].trim());
			}
			if(dinst==null){
				System.out.println(resultTableData[0][i]+"\tnot found");
				counter++;
				String[] buttons = { "Select from DB", "Insert in DB", "Ignore", "Cancel" };

			    int rc = JOptionPane.showOptionDialog(null, 
			    		resultTableData[0][i]+" not found in Database !"
			    				+ "\nWhat would you like to do with it?", 
			    		"Confirmation",
			    		JOptionPane.WARNING_MESSAGE, 
			    		0, null, buttons, buttons[0]);
			    switch(rc){
			    case 0:
			    	int id;
			    	if(rdbtnInstitNames.isSelected()) id = selectInstitutionForName(resultTableData[0][i].trim());
			    	else id = selectInstitutionForCode(resultTableData[0][i].trim());
			    	if(id>0) resultTableData[0][i] = ""+id;
			    	else {
			    		JOptionPane.showMessageDialog(this, 
			    				"Database institution was not selected."
						    	+ "\nThis column will be ignored.", 
						    	"Message", 
						    	JOptionPane.INFORMATION_MESSAGE);
			    		resultTableData[0][i] = null;
			    	}
			    			
			    	break;
			    case 1:
			    	InstitutionDTO inst = new InstitutionDTO();
			    	
					if(rdbtnInstitCodes.isSelected())
			    		{
						String str = JOptionPane.showInputDialog("Input please institution name:");
						if(str==null || str.length()==0){
							JOptionPane.showMessageDialog(this, 
				    				"Institution name wasn't introduced."
							    	+ "\nThis column will be ignored.", 
							    	"Message", 
							    	JOptionPane.INFORMATION_MESSAGE);
				    		resultTableData[0][i] = null;
				    		break;
						}
						inst.setName(str);
						
						
			    	}
					else{
						String str = JOptionPane.showInputDialog("Input please institution name:", resultTableData[0][i]);
						if(str==null || str.length()==0){
							JOptionPane.showMessageDialog(this, 
				    				"Institution name wasn't introduced."
							    	+ "\nThis column will be ignored.", 
							    	"Message", 
							    	JOptionPane.INFORMATION_MESSAGE);
				    		resultTableData[0][i] = null;
				    		break;
						}
						inst.setName(str);
					}
					
					
					List<RegionDTO> regs = RestManager.getInstance().getRegions();
					
					MultiSelectPane<RegionDTO> msp = new MultiSelectPane<RegionDTO>(regs, true);
					msp.setPreferedViewportSize(300, 400);
					int resp = JOptionPane.showConfirmDialog(null, msp, "Select region", JOptionPane.OK_CANCEL_OPTION);
					if(resp==JOptionPane.OK_OPTION
							&&msp.getSelectedIndices().length>0) 
						inst.setRegion(regs.get(msp.getSelectedIndices()[0]));
					
					List<InstitTypeDTO> its = RestManager.getInstance().getInstitutionTypes();
					MultiSelectPane<InstitTypeDTO> msp2 = new MultiSelectPane<InstitTypeDTO>(its, true);
					msp2.setPreferedViewportSize(300, 400);
					resp = JOptionPane.showConfirmDialog(null, msp2, "Select Institution Type", JOptionPane.OK_CANCEL_OPTION);
					if(resp==JOptionPane.OK_OPTION
							&&msp2.getSelectedIndices().length>0) 
						inst.setInstitType(its.get(msp2.getSelectedIndices()[0]));
					
					inst.setActive(true);
					inst.setDistributor(false);
					//List<InstitutionDTO> list = new ArrayList<InstitutionDTO>();
					//list.add(inst);
					//list = ConnectionManager.getInstance().storeDTOs(list);
					//inst = list.get(0);
					//DatabaseHelper.getDBHelper().insertHibernateObject(inst,null);
					//ss.beginTransaction();
					DistribInstitDTO di = new DistribInstitDTO();
					if(rdbtnProdBarcodes.isSelected()){
						di.setDistribCod(resultTableData[0][i]);
						di.setInstitName(inst.getName());
					}else{
						di.setInstitName(resultTableData[0][i]);
					}
					di.setDistributor(distr);
					di.setInstitution(inst);
					
					List<DistribInstitDTO> list_di = new ArrayList<DistribInstitDTO>();
					list_di.add(di);
					list_di = RestManager.getInstance().saveToDatabase(list_di);
					
					resultTableData[0][i] = ""+inst.getId();
			    	break;
			    case 2:
			    	resultTableData[0][i] = null;
			    	break;
			    case 3:
			    	return false;
			    default:
			    	return false;
			    		
			    }
			}else{
				resultTableData[0][i] = ""+dinst.getInstitution().getId();
			}
		}
		System.out.println();
		System.out.println("========================="+counter +" of "+ i+ " institutions not found=======================");
		System.out.println();
		return true;
	}
	private boolean checkProducts() throws UserNotAuthorisedException{
		if (resultTableData== null|| resultTableData.length==0)return false;
		InstitutionDTO distr = (InstitutionDTO) comboDistrib.getSelectedItem();
		int counter = 0;
		int i;
		for(i = 1;i<resultTableData.length;i++){
			if(resultTableData[i][0]==null || resultTableData[i][0].trim().length()==0) continue;
			DistribProdDTO dp=null;
			if(rdbtnProdNames.isSelected()){
				dp = RestManager.getInstance().getDistributorProductByName(distr,resultTableData[i][0].trim());
			}
			else{
				dp = RestManager.getInstance().getDistributorProductByCode(distr,resultTableData[i][0].trim());
			}
			if(dp==null){
				System.out.println(resultTableData[i][0]+"\tnot found");
				counter++;
				String[] buttons = { "Select from DB", "Insert in DB", "Ignore", "Cancel" };

			    int rc = JOptionPane.showOptionDialog(null, 
			    		resultTableData[i][0]+" not found in Database !"
			    				+ "\nWhat would you like to do with it?", 
			    		"Confirmation",
			    		JOptionPane.WARNING_MESSAGE, 
			    		0, null, buttons, buttons[0]);
			    switch(rc){
			    case 0:
			    	int id;
			    	if(rdbtnProdNames.isSelected()) id = selectProductForName(resultTableData[i][0].trim());
			    	else id = selectProductForCode(resultTableData[i][0].trim());
			    	if(id>0) resultTableData[i][0] = ""+id;
			    	else {
			    		JOptionPane.showMessageDialog(this, 
			    				"Database product was not selected."
						    	+ "\nThis row will be ignored.", 
						    	"Message", 
						    	JOptionPane.INFORMATION_MESSAGE);
			    		resultTableData[i][0] = null;
			    	}
			    			
			    	break;
			    case 1:
			    	ProductDTO pr = new ProductDTO();
			    	
					if(rdbtnProdBarcodes.isSelected())
			    		{
						String str = JOptionPane.showInputDialog("Input please product name:");
						if(str==null || str.length()==0){
							JOptionPane.showMessageDialog(this, 
				    				"Database product was not selected."
							    	+ "\nThis row will be ignored.", 
							    	"Message", 
							    	JOptionPane.INFORMATION_MESSAGE);
				    		resultTableData[i][0] = null;
				    		break;
						}
						pr.setName(str);
						
						
			    	}
					else{
						pr.setName(resultTableData[i][0]);
					}
					
					DistribProdDTO di = new DistribProdDTO();
					di.setDistribCod(resultTableData[i][0]);
					di.setDistributor(distr);
					di.setProduct(pr);
					di.setProdName(pr.getName());
					
					List<DistribProdDTO> list = new ArrayList<DistribProdDTO>();
					list.add(di);
					list = RestManager.getInstance().saveToDatabase(list);
					
					resultTableData[i][0] = ""+list.get(0).getProduct().getId();
			    	break;
			    case 2:
			    	resultTableData[i][0] = null;
			    	break;
			    case 3:
			    	return false;
			    default:
			    	return false;
			    		
			    }
			}else{
				resultTableData[i][0] = ""+dp.getProduct().getId();
			}
		}
		System.out.println();
		System.out.println("========================="+counter +" of "+ i + " products not found=======================");
		System.out.println();
		return true;
	}
	
	private void preview(){
		/*
		//System.out.println(sales);
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				MedServerMainFrame.getMainFrame().setStatus("Generating report...");
				
				
				try {
					Reports rep = (Reports) getSession().get(Reports.class, 1);
					//System.out.println(rep);
					sales = SalesReportDataItem.build(rep);
							
					
					JRBeanCollectionDataSource beanCol = new JRBeanCollectionDataSource(sales);
					
					
					Map<String, Object> param = new HashMap<String, Object>();
					param.put("logoImage",Activities.class.getResourceAsStream("/Reports/ww.png") );
					param.put("IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS", Boolean.TRUE);
					String reportFile;
					reportFile = "/Reports/SalesReport1.jasper";
						
					
					JasperPrint jp =JasperFillManager.fillReport(
							Activities.class.getResourceAsStream(reportFile), param, beanCol);
					JasperViewer jv = new JasperViewer(jp, false); 
					jv.setVisible(true);
					
				} catch (JRException e) {
					
					e.printStackTrace();
				}finally{
					MedServerMainFrame.getMainFrame().clearStatus();
				}
				
				
			}
		}).start();
		*/
	}
		
	
	private int selectInstitutionForName(String newName) throws UserNotAuthorisedException{

		List<InstitutionDTO> list = RestManager.getInstance().getInstitutions();
		if(list==null) return -1;
		MultiSelectPane<InstitutionDTO> msp = new MultiSelectPane<InstitutionDTO>(list, true);
		msp.setPreferedViewportSize(600, 400);
		int resp = JOptionPane.showConfirmDialog(null, msp, "Select institution", JOptionPane.OK_CANCEL_OPTION);
		if(resp==JOptionPane.CANCEL_OPTION) return -1;
		if(msp.getSelectedIndices().length==0) return -1;
		InstitutionDTO in = list.get(msp.getSelectedIndices()[0]);
		DistribInstitDTO di = new DistribInstitDTO();
		di.setInstitution(in);
		di.setInstitName(newName);
		di.setDistributor(((InstitutionDTO)comboDistrib.getSelectedItem()));
		List<DistribInstitDTO> list_di = new ArrayList<DistribInstitDTO>();
		list_di.add(di);
		list_di = RestManager.getInstance().saveToDatabase(list_di);
		return in.getId();
	}
	
	private int selectInstitutionForCode(String cod) throws UserNotAuthorisedException{
		List<InstitutionDTO> list = RestManager.getInstance().getInstitutions();
		if(list==null) return -1;
		MultiSelectPane<InstitutionDTO> msp = new MultiSelectPane<InstitutionDTO>(list, true);
		msp.setPreferedViewportSize(600, 400);
		int resp = JOptionPane.showConfirmDialog(null, msp, "Select institution", JOptionPane.OK_CANCEL_OPTION);
		if(resp==JOptionPane.CANCEL_OPTION) return -1;
		if(msp.getSelectedIndices().length==0) return -1;
		InstitutionDTO in = list.get(msp.getSelectedIndices()[0]);
		DistribInstitDTO di = new DistribInstitDTO();
		di.setInstitution(in);
		di.setDistribCod(cod);
		di.setDistributor(((InstitutionDTO)comboDistrib.getSelectedItem()));
		List<DistribInstitDTO> list_di = new ArrayList<DistribInstitDTO>();
		list_di.add(di);
		list_di = RestManager.getInstance().saveToDatabase(list_di);
		return in.getId();
	}
	
	private int selectProductForName(String newName) throws UserNotAuthorisedException{
		List<ProductDTO> list = RestManager.getInstance().getProducts();
		if(list==null) return -1;
		MultiSelectPane<ProductDTO> msp = new MultiSelectPane<ProductDTO>(list, true);
		msp.setPreferedViewportSize(600, 400);
		int resp = JOptionPane.showConfirmDialog(null, msp, "Select contacts", JOptionPane.OK_CANCEL_OPTION);
		if(resp==JOptionPane.CANCEL_OPTION) return -1;
		if(msp.getSelectedIndices().length==0) return -1;
		ProductDTO pr = list.get(msp.getSelectedIndices()[0]);
		DistribProdDTO di = new DistribProdDTO();
		di.setProduct(pr);
		di.setProdName(newName);
		di.setDistributor(((InstitutionDTO)comboDistrib.getSelectedItem()));
		List<DistribProdDTO> list_di = new ArrayList<DistribProdDTO>();
		list_di.add(di);
		list_di = RestManager.getInstance().saveToDatabase(list_di);
		return pr.getId();
	}
	private int selectProductForCode(String code) throws UserNotAuthorisedException{
		List<ProductDTO> list = RestManager.getInstance().getProducts();
		if(list==null) return -1;
		MultiSelectPane<ProductDTO> msp = new MultiSelectPane<ProductDTO>(list, true);
		msp.setPreferedViewportSize(600, 400);
		int resp = JOptionPane.showConfirmDialog(null, msp, "Select contacts", JOptionPane.OK_CANCEL_OPTION);
		if(resp==JOptionPane.CANCEL_OPTION) return -1;
		if(msp.getSelectedIndices().length==0) return -1;
		ProductDTO pr = list.get(msp.getSelectedIndices()[0]);
		DistribProdDTO di = new DistribProdDTO();
		di.setProduct(pr);
		di.setDistribCod(code);
		di.setDistributor(((InstitutionDTO)comboDistrib.getSelectedItem()));
		List<DistribProdDTO> list_di = new ArrayList<DistribProdDTO>();
		list_di.add(di);
		list_di = RestManager.getInstance().saveToDatabase(list_di);
		return pr.getId();
	}
	
	private void restoreReportSettings(){
		InstitutionDTO distr = (InstitutionDTO) comboDistrib.getSelectedItem();
		spinnerColumnTo.setValue(prefs.getInt("ColumnTo"+distr.getId(), 1));
		spinnerColumnFrom.setValue(prefs.getInt("ColumnFrom"+distr.getId(),1));
		spinnerFirstRow.setValue(prefs.getInt("FirstRow"+distr.getId(), 1));
		spinnerPrductsRowCol.setValue(prefs.getInt("ProductsRowCol"+distr.getId(), 1));
		spinnerInstitRowCol.setValue(prefs.getInt("InstitColRow"+distr.getId(), 1));
		rdbtnInstitColumn.setSelected(prefs.get("InstitRowCol"+distr.getId(),"row").equals("column"));
		rdbtnInstitRow.setSelected(prefs.get("InstitRowCol"+distr.getId(),"row").equals("row"));
		rdbtnInstitCodes.setSelected(prefs.get("InstitNamesCodes"+distr.getId(),"names").equals("codes"));
		rdbtnInstitNames.setSelected(prefs.get("InstitNamesCodes"+distr.getId(),"names").equals("names"));
		rdbtnProdRow.setSelected(prefs.get("ProdRowCol"+distr.getId(), "col").equals("row"));
		rdbtnProdColumn.setSelected(prefs.get("ProdRowCol"+distr.getId(), "col").equals("col"));
		rdbtnProdBarcodes.setSelected(prefs.get("ProdNameBarcode"+distr.getId(),"names").equals("codes"));
		rdbtnProdNames.setSelected(prefs.get("ProdNameBarcode"+distr.getId(),"names").equals("names"));
	}

	private void getReportFile(){
		JFileChooser chooser = new JFileChooser(prefs.get("report_dir", System.getProperty("user.dir")));
		int returnVal = chooser.showOpenDialog(this);
		if(returnVal != JFileChooser.APPROVE_OPTION) return;
		File f = chooser.getSelectedFile();
		
		if(f.getName().endsWith(".xls"))
			processXlsFile(f);
		textReportFile.setText(f.getAbsolutePath());
		prefs.put("report_dir", f.getParent());
		if(tableData==null) return;
		
		table.setModel(new ReportTableModel());
		restoreReportSettings();
	}
	
	private void processXlsFile(File f){
		
		Workbook book = null;
		try {
			book = Workbook.getWorkbook(f);
			Sheet sheet = book.getSheet(0);
			rows = sheet.getRows();
			columns = sheet.getColumns();
			tableData = new String[rows][columns];
			//System.out.println(rows+" rows, "+columns + " columns");
			for(int rowIndex = 0;rowIndex<rows;rowIndex++)
				for(int colIndex = 0; colIndex<columns;colIndex++){
					Cell cell = sheet.getCell(colIndex,rowIndex);
					tableData[rowIndex][colIndex] = cell.getContents();
				}
		} catch (BiffException e) {
			e.printStackTrace();
			return;
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}finally{
			if(book!=null) book.close();
		}
		
		
	}
	
	
	private void tableSettingsChanged(){
		if(tableData==null) return;
		institutions = new ArrayList<String>();
		products = new ArrayList<String>();
		DefaultListModel<String> instListModel = new DefaultListModel<String>(), prodListModel = new DefaultListModel<String>();
		int colFrom = (Integer)spinnerColumnFrom.getValue()+1;
		int colTo = (Integer)spinnerColumnTo.getValue()+1;
		int firstRow = (Integer)spinnerFirstRow.getValue()-1;
		int institSpinner = (Integer)spinnerInstitRowCol.getValue();
		int prodSpinner = (Integer)spinnerPrductsRowCol.getValue();
		if(rdbtnInstitColumn.isSelected()){
			for(int row = firstRow; row<rows; row++){
				if(tableData[row][institSpinner-1]!=null&&tableData[row][institSpinner-1].length()>0)
					{
					instListModel.addElement(tableData[row][institSpinner-1]);
					}
				
			}
		}else if(rdbtnInstitRow.isSelected()){
			for(int col = colFrom;col<colTo; col++){
				if(tableData[institSpinner-1][col]!=null && tableData[institSpinner-1][col].length()>0){
					instListModel.addElement(tableData[institSpinner-1][col]);
				}
					
			}
		}
		
		if(rdbtnProdColumn.isSelected()){
			for(int row = firstRow; row<rows; row++){
				if(tableData[row][prodSpinner-1]!=null&&tableData[row][prodSpinner-1].length()>0)
					{
					prodListModel.addElement(tableData[row][prodSpinner-1]);
					}
				
			}
		}else if(rdbtnProdRow.isSelected()){
			for(int col = colFrom;col<colTo; col++){
				if(tableData[prodSpinner-1][col]!=null && tableData[prodSpinner-1][col].length()>0){
					prodListModel.addElement(tableData[prodSpinner-1][col]);
				}
					
			}
		}
		
		listInstitutions.setModel(instListModel);
		listProducts.setModel(prodListModel);
		
		generateResultTableData();
		
	}
	
	private void generateResultTableData(){
		
		if(listInstitutions.getModel().getSize()==0 || listProducts.getModel().getSize()==0){
			tableResult.setModel(new EmptyResultTableModel());
			resultTableData = null;
			return;
		}
			
		
		//resultTableData = new String[listInstitutions.getModel().getSize()+1][listProducts.getModel().getSize()+1];
		resultTableData = getNormalizedTable();
		if(resultTableData!=null)
			tableResult.setModel(new ResultTableModel());
		else 
			tableResult.setModel(new EmptyResultTableModel());
		
		/*
		for(int i = 1; i<=listProducts.getModel().getSize();i++)
			resultTableData[0][i] = (String)listProducts.getModel().getElementAt(i);
		
		for(int i = 1; i<=listInstitutions.getModel().getSize(); i++)
			resultTableData[i][0] = (String)listInstitutions.getModel().getElementAt(i);
		
		for(int row = 1; row<=listInstitutions.getModel().getSize(); row++)
			for(int col = 1; col<=listProducts.getModel().getSize();col++){
				String inst = resultTableData[row][0];
			}
		*/
		
	}
	
	/**Generates standard table data with Products in columns and Institutions in rows
	 * @return two dimensional array with nInstitutions rows and nProducts columns according to initial report settings
	 */
	private String[][] getNormalizedTable(){
		String[][] result = null;
		int colFrom = (Integer)spinnerColumnFrom.getValue()-1;
		int colTo = (Integer)spinnerColumnTo.getValue()-1;
		int firstRow = (Integer)spinnerFirstRow.getValue()-1;
		int institSpinner = (Integer)spinnerInstitRowCol.getValue();
		int prodSpinner = (Integer)spinnerPrductsRowCol.getValue();
		if(rdbtnInstitRow.isSelected()&&rdbtnProdColumn.isSelected()){
			result = new String[rows-firstRow+1][colTo-colFrom+1+1];
			for(int row = 0; row < rows-firstRow; row++){
				result[row+1][0] = tableData[firstRow+row][prodSpinner-1];
				for(int col = 0; col<=colTo-colFrom; col++){
					result[0][col+1] = tableData[institSpinner-1][colFrom+col];
					result[row+1][col+1] = tableData[firstRow+row][colFrom+col];
				}
			}	
			
		}else if(rdbtnInstitColumn.isSelected() && rdbtnProdRow.isSelected()){
			result = new String[colTo-colFrom+1+1][rows-firstRow+1];
			for(int row = 0; row < rows-firstRow; row++){
				
				result[0][row+1] = tableData[firstRow+row][institSpinner-1];
				//System.out.println(result[0][row+1]);
				for(int col = 0; col<=colTo-colFrom; col++){
					result[col+1][row+1] = tableData[firstRow+row][colFrom+col];
					
					result[col+1][0] = tableData[prodSpinner-1][colFrom+col];
				}
			}
				
		}
		return result;
	}
	private class EmptyResultTableModel extends AbstractTableModel{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public int getColumnCount() {
			return 0;
		}

		@Override
		public int getRowCount() {
			return 0;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			return "Data can not be generated";
		}
		
	}
	private class ResultTableModel extends AbstractTableModel{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public int getColumnCount() {
			
			return resultTableData[0].length;
		}

		@Override
		public int getRowCount() {
			return resultTableData.length;
		}

		@Override
		public Object getValueAt(int row, int col) {
			return resultTableData[row][col];
		}
		
	}
	
	private class ReportTableModel extends AbstractTableModel{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public int getColumnCount() {
			return columns+1;
		}

		public int getRowCount() {
			
			return rows;
		}

		public Object getValueAt(int row, int col) {
			if(col==0) return row+1;
			else return tableData[row][col-1];
		}
		
		public String getColumnName(int col){
			if(col==0) return "";
			else return ""+col;
		}
		
	}
	
	
	private void test(){
		File f = new File("G:\\Worwag\\Report2 - копия.xls");
		processXlsFile(f);
		table.setModel(new ReportTableModel());
		spinnerColumnTo.setValue(177);
		spinnerColumnFrom.setValue(23);
		spinnerFirstRow.setValue(7);
		spinnerPrductsRowCol.setValue(3);
		spinnerInstitRowCol.setValue(2);
		
	}

}
