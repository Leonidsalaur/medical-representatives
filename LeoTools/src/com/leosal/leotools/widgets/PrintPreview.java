package com.leosal.leotools.widgets;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;

import javax.swing.JInternalFrame;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;


import javax.swing.RepaintManager;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

public class PrintPreview extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panelPrint;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrintPreview frame = new PrintPreview();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public PrintPreview(){
		this(new JPanel());
	}
	/**
	 * Create the frame.
	 */
	public PrintPreview(JPanel panelPrint) {
		setTitle("Print Preview");
		setResizable(true);
		//setClosable(true);
		//setMaximizable(true);
		//setIconifiable(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		panel.add(toolBar);
		
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendToPrinter();
			}
		});
		button.setIcon(new ImageIcon(PrintPreview.class.getResource("/com/leosal/leotools/graphics/document-print.png")));
		toolBar.add(button);
		
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		this.panelPrint = panelPrint;
		scrollPane.setViewportView(panelPrint);

	}

	protected void sendToPrinter() {
		PrinterJob pj = PrinterJob.getPrinterJob();
		pj.setJobName("Print Results");
		
		pj.setPrintable(new Printable() {
			
			@Override
			public int print(Graphics graphics, PageFormat pf, int pageIndex)
					throws PrinterException {
				if(pageIndex>0){
					return Printable.NO_SUCH_PAGE;
				}
				Graphics2D g2 = (Graphics2D) graphics;
				g2.translate(860, 1720);
			   disableDoubleBuffering(panelPrint);
			   // scale to fill the page        
			   double dw = pf.getImageableWidth();
			   double dh = pf.getImageableHeight();
			   Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			    
			   double xScale = dw / screenSize.width;
			   double yScale = dh / screenSize.height;
			   double scale = Math.min(xScale,yScale);
			     
			   // center the chart on the page
			   double tx = 0.0;
			   double ty = 0.0;
			   if (xScale > scale)
			   {
			       tx = 0.5*(xScale-scale)*screenSize.width;
			   }
			   else
			          {
			       ty = 0.5*(yScale-scale)*screenSize.height;
			   }
			   g2.translate(tx, ty);
			   g2.scale(scale, scale);
			     
			   panelPrint.paint(g2);
			   enableDoubleBuffering(panelPrint);
			   return Printable.PAGE_EXISTS;
				
			}
		});
		
		if(pj.printDialog()==false)
			return;
		try {
			pj.print();
		} catch (PrinterException e) {
			e.printStackTrace();
		}
	}
	
	/** The speed and quality of printing suffers dramatically if
	   *  any of the containers have double buffering turned on.
	   *  So this turns if off globally.
	   *  @see enableDoubleBuffering
	   */
	  protected static void disableDoubleBuffering(Component c) {
	    RepaintManager currentManager = RepaintManager.currentManager(c);
	    currentManager.setDoubleBufferingEnabled(false);
	  }
	  
	  /** Re-enables double buffering globally. */
	   
	  protected static void enableDoubleBuffering(Component c) {
	    RepaintManager currentManager = RepaintManager.currentManager(c);
	    currentManager.setDoubleBufferingEnabled(true);
	  }

}
