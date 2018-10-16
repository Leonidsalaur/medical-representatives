package com.leosal.leotools.tools;

import java.io.File;
import java.util.prefs.Preferences;
import java.util.prefs.PreferencesFactory;

public class FilePreferencesFactory implements PreferencesFactory {
	
	Preferences rootPrefes;
	public static final String SYSTEM_PROPERTY_FILE = "com.leosal.leotool.FilePreferencesFactory.file";
	
	public FilePreferencesFactory() {
	}

	@Override
	public Preferences systemRoot() {
		return userRoot();
	}

	@Override
	public Preferences userRoot() {
		if(rootPrefes==null){
			rootPrefes = new FilePreferences(null, "");
		}
		return rootPrefes;
	}
	
	private static File preferencesFile;

	public static File getPreferencesFile() {
		if(preferencesFile==null){
			String prefsFile = System.getProperty(SYSTEM_PROPERTY_FILE);
			if(prefsFile==null || prefsFile.length()==0){
				prefsFile=System.getProperty("user.home")+File.separator+".fileprefs";
			}
			preferencesFile = new File(prefsFile).getAbsoluteFile();
		}
		return preferencesFile;
	}

}
