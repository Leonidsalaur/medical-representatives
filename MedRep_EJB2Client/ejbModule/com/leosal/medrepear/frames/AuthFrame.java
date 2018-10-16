package com.leosal.medrepear.frames;

import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import net.miginfocom.swing.MigLayout;

import com.leosal.medrepear.service.RestManager;

public class AuthFrame extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5687320189847718380L;
	private JTextField textUser;
	private JPasswordField password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AuthFrame frame = new AuthFrame();
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
	public AuthFrame() {
		setTitle("Authorisation");
		setBounds(100, 100, 450, 166);
		getContentPane().setLayout(new MigLayout("", "[][][grow]", "[][][][]"));
		
		JLabel lblLogin = new JLabel("User name");
		getContentPane().add(lblLogin, "cell 1 1,alignx trailing");
		
		textUser = new JTextField();
		getContentPane().add(textUser, "cell 2 1,growx");
		textUser.setColumns(10);
		textUser.setText(RestManager.getInstance().getLastUser());
		
		JLabel lblPassword = new JLabel("Password");
		getContentPane().add(lblPassword, "cell 1 2,alignx trailing");
		
		password = new JPasswordField();
		getContentPane().add(password, "cell 2 2,growx");
		
		password.setText(RestManager.getInstance().getLastPassword());
		
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		getContentPane().add(toolBar, "cell 2 3,alignx right");
		
		JButton btnLogOn = new JButton("Log on");
		btnLogOn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connect();
			}
		});
		toolBar.add(btnLogOn);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		toolBar.add(btnCancel);

	}

	protected void connect() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				MainFrame.getMainFrame().setStatus("Connecting to server...");
				try{
					AuthFrame.this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
					String pass = new String(password.getPassword());
					RestManager.getInstance().login(textUser.getText(), pass);
					MainFrame.getMainFrame().activate();
					AuthFrame.this.dispose();
				}catch(NullPointerException e){
					JOptionPane.showMessageDialog(null, "Password field is empty");
				}catch(Exception e){
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Execution failed. See log file");
				}finally{
					AuthFrame.this.setCursor(Cursor.getDefaultCursor());
					MainFrame.getMainFrame().clearStatus();
				}
				
				
			}
		}).start();
		
		
	}

}
