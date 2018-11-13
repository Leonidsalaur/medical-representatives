package com.leosal.medrep.ui;

import java.util.prefs.Preferences;

import javax.annotation.PostConstruct;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.springframework.stereotype.Component;

import com.jgoodies.binding.adapter.Bindings;
import com.jgoodies.binding.adapter.PreferencesAdapter;
import com.jgoodies.binding.value.ValueModel;

import net.miginfocom.swing.MigLayout;

@Component
public class AuthorizationPanel extends JPanel {
	
	private static final String LOGIN_KEY = "medrep.login";
	private static final String PASSWORD_KEY = "medrep.password";
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5687320189847718380L;
	private JTextField textUser;
	private JPasswordField passwordField;

	/**
	 * Create the frame.
	 */
	public AuthorizationPanel() {
//		init();
	}
	
	@PostConstruct
	private void init() {
		Preferences prefs = Preferences.userRoot();
		
		ValueModel loginModel = new PreferencesAdapter(prefs, LOGIN_KEY, "");
		ValueModel passwordModel = new PreferencesAdapter(prefs, PASSWORD_KEY, "");
		
		setBounds(100, 100, 298, 112);
		setLayout(new MigLayout("", "[][227.00]", "[][][][]"));
		
		JLabel lblLogin = new JLabel("User name");
		add(lblLogin, "cell 0 1,alignx trailing");
		
		textUser = new JTextField();
		add(textUser, "cell 1 1,growx");
		textUser.setColumns(10);

		
		JLabel lblPassword = new JLabel("Password");
		add(lblPassword, "cell 0 2,alignx trailing");
		
		passwordField = new JPasswordField();
		add(passwordField, "cell 1 2,growx");
		
//		textUser.setText("");
//		passwordField.setText("");
		Bindings.bind(textUser, loginModel, true);
		Bindings.bind(passwordField, passwordModel, true);

	}
	
	public String getLogin() {
		return textUser.getText();
	}
	
	public String getPassword() {
		return new String(passwordField.getPassword());
	}

}
