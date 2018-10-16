package com.leosal.medrepear.frames;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import com.toedter.calendar.JDateChooser;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;

public class DailyStatistics extends JInternalFrame {

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
					DailyStatistics frame = new DailyStatistics();
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
	public DailyStatistics() {
		setTitle("Daily report");
		setBounds(100, 100, 450, 300);
		
		JPanel panelTop = new JPanel();
		getContentPane().add(panelTop, BorderLayout.NORTH);
		panelTop.setLayout(new MigLayout("", "[][122.00][][122.00]", "[grow]"));
		
		JLabel lblFrom = new JLabel("From:");
		panelTop.add(lblFrom, "cell 0 0");
		
		JDateChooser dateChooser = new JDateChooser();
		panelTop.add(dateChooser, "cell 1 0,growx");
		
		JLabel lblTo = new JLabel("to:");
		panelTop.add(lblTo, "cell 2 0");
		
		JDateChooser dateChooser_1 = new JDateChooser();
		panelTop.add(dateChooser_1, "cell 3 0,grow");
		
		JPanel panelLeft = new JPanel();
		getContentPane().add(panelLeft, BorderLayout.WEST);
		
		JPanel panelBottom = new JPanel();
		getContentPane().add(panelBottom, BorderLayout.SOUTH);
		
		JPanel panelRight = new JPanel();
		getContentPane().add(panelRight, BorderLayout.EAST);
		panelRight.setLayout(new MigLayout("", "[]", "[]"));
		
		JPanel panelCenter = new JPanel();
		getContentPane().add(panelCenter, BorderLayout.CENTER);
		panelCenter.setLayout(new MigLayout("", "[grow]", "[][grow]"));
		
		JToolBar toolBar = new JToolBar();
		panelCenter.add(toolBar, "cell 0 0");
		
		JScrollPane scrollPane = new JScrollPane();
		panelCenter.add(scrollPane, "cell 0 1,grow");

	}

}
