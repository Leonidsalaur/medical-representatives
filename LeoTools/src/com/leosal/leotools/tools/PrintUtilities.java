package com.leosal.leotools.tools;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import javax.swing.RepaintManager;

public class PrintUtilities implements Printable {
	private Component componentToBePrinted;
	  
	  public static void printComponent(Component c) {
	    new PrintUtilities(c).print();
	  }
	   
	  public PrintUtilities(Component componentToBePrinted) {
	    this.componentToBePrinted = componentToBePrinted;
	  }
	   
	  public void print() {
	    PrinterJob printJob = PrinterJob.getPrinterJob();
	    printJob.setPrintable(this);
	    if (printJob.printDialog())
	      try {
	        printJob.print();
	      } catch(PrinterException pe) {
	        System.out.println("Error printing: " + pe);
	      }
	  }
	  
	  public int print(Graphics g, PageFormat pageFormat, int pageIndex) {
	 
	    if (pageIndex > 0) {
	    	return(NO_SUCH_PAGE);
	    } else {
	    	Graphics2D g2d = (Graphics2D)g;
	    	g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
	    	disableDoubleBuffering(componentToBePrinted);
			// scale to fill the page        
			double dw = pageFormat.getImageableWidth();
			double dh = pageFormat.getImageableHeight();
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
			g2d.translate(tx, ty);
			g2d.scale(scale, scale);
			 
			//componentToBePrinted.paint(g2d);
			componentToBePrinted.printAll(g2d);
			enableDoubleBuffering(componentToBePrinted);
			return Printable.PAGE_EXISTS;
	    }
	  }
	  
	  /** The speed and quality of printing suffers dramatically if
	   *  any of the containers have double buffering turned on.
	   *  So this turns if off globally.
	   *  @see enableDoubleBuffering
	   */
	  public static void disableDoubleBuffering(Component c) {
	    RepaintManager currentManager = RepaintManager.currentManager(c);
	    currentManager.setDoubleBufferingEnabled(false);
	  }
	  
	  /** Re-enables double buffering globally. */
	   
	  public static void enableDoubleBuffering(Component c) {
	    RepaintManager currentManager = RepaintManager.currentManager(c);
	    currentManager.setDoubleBufferingEnabled(true);
	  }

}
