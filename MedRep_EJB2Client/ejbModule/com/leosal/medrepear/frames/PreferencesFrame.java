package com.leosal.medrepear.frames;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.UIManager.LookAndFeelInfo;

import org.jdesktop.swingbinding.JComboBoxBinding;
import org.jdesktop.swingbinding.SwingBindings;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;

import com.leosal.medrepear.util.UserPreferences;

import javax.swing.JToolBar;
import javax.swing.JButton;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PreferencesFrame extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JComboBox<String> comboBox;
	private List<String> themeList;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PreferencesFrame frame = new PreferencesFrame();
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
	public PreferencesFrame() {
		setClosable(true);
		setTitle("Preferences");
		setBounds(100, 100, 450, 300);
		themeList = new ArrayList<String>();
		for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
	        themeList.add(info.getName());
	    }
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		getContentPane().add(panel, BorderLayout.SOUTH);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		panel.add(toolBar);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserPreferences.setAppTheme(comboBox.getSelectedItem().toString());
				PreferencesFrame.this.dispose();
			}
		});
		toolBar.add(btnOk);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PreferencesFrame.this.dispose();
			}
		});
		toolBar.add(btnCancel);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Common", null, panel_1, null);
		panel_1.setLayout(new MigLayout("", "[][grow]", "[]"));
		
		JLabel lblApplicationTheme = new JLabel("Application theme: ");
		panel_1.add(lblApplicationTheme, "cell 0 0,alignx trailing");
		
		comboBox = new JComboBox<String>();
		panel_1.add(comboBox, "cell 1 0,growx");
		initDataBindings();

	}

	@SuppressWarnings("rawtypes")
	protected void initDataBindings() {
		JComboBoxBinding<String, List<String>, JComboBox> jComboBinding = SwingBindings.createJComboBoxBinding(UpdateStrategy.READ, themeList, comboBox);
		jComboBinding.bind();
	}
}
