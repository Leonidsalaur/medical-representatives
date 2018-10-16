package com.leosal.medrepear.util;

import java.util.prefs.Preferences;

import com.leosal.medrepear.frames.MainFrame;

public class UserPreferences {
	public static final Preferences prefs = Preferences.userNodeForPackage(com.leosal.medrepear.util.UserPreferences.class);
	public static final String APP_THEME = "APP_THEME";
	public static final String DEFAULT_EVENT_TYPE = "DEFAULT_EVENT_TYPE";
	
	public static String getAppTheme(){
		return prefs.get(APP_THEME, "Windows");
	}
	
	public static void setAppTheme(String appTheme){
		prefs.put(APP_THEME, appTheme);
		MainFrame.getMainFrame().setLookAndFeel(appTheme);
	}
	
	/**Gets default event type
	 * @return Last selected list index (from the Event Types List) 
	 */
	public static int getDefaultEventType(){
		return prefs.getInt(DEFAULT_EVENT_TYPE, 0);
	}
	
	public static void setDefaultEventType(int index){
		prefs.putInt(DEFAULT_EVENT_TYPE, index);
	}

}
