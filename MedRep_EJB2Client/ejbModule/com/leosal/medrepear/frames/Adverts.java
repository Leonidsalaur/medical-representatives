package com.leosal.medrepear.frames;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

import com.leosal.leotools.exceptions.UserNotAuthorisedException;

public class Adverts extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private AdvertsList panel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Adverts frame = new Adverts();
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
	public Adverts() throws UserNotAuthorisedException{
		setTitle("Adverts");
		setMaximizable(true);
		setResizable(true);
		setClosable(true);
		setBounds(100, 100, 610, 439);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
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
				if(!panel.isSaved()){
					int opt = JOptionPane.showConfirmDialog(null, 
							"You have unsaved changes.\nDo you want to save them now?", 
							"Attention", 
							JOptionPane.YES_NO_CANCEL_OPTION);
					if(opt==JOptionPane.YES_OPTION) panel.saveChanges();
					if(opt!=JOptionPane.CANCEL_OPTION) Adverts.this.dispose();
				}else{
					Adverts.this.dispose();
				}
			}
			
			@Override
			public void internalFrameClosed(InternalFrameEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void internalFrameActivated(InternalFrameEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		panel = new AdvertsList();
		
		getContentPane().add(panel, BorderLayout.CENTER);

	}

}
