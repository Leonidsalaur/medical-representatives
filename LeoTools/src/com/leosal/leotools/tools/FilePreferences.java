package com.leosal.leotools.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;
import java.util.prefs.AbstractPreferences;
import java.util.prefs.BackingStoreException;


public class FilePreferences extends AbstractPreferences {
	
	private Map<String, String> root;
	private Map<String,FilePreferences> children;
	private boolean isRemoved;
	
	public FilePreferences(AbstractPreferences parent, String name) {
		super(parent, name);
		root = new TreeMap<String, String>();
		children = new TreeMap<String, FilePreferences>();
		try {
			sync();
		} catch (BackingStoreException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void putSpi(String key, String value) {
		root.put(key, value);
		try {
			flush();
		} catch (BackingStoreException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected String getSpi(String key) {
		return root.get(key);
	}

	@Override
	protected void removeSpi(String key) {
		root.remove(key);
		try {
			flush();
		} catch (BackingStoreException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void removeNodeSpi() throws BackingStoreException {
		isRemoved=true;
		flush();
	}

	@Override
	protected String[] keysSpi() throws BackingStoreException {
		return root.keySet().toArray(new String[root.keySet().size()]);
	}

	@Override
	protected String[] childrenNamesSpi() throws BackingStoreException {
		return children.keySet().toArray(new String[children.keySet().size()]);
	}

	@Override
	protected AbstractPreferences childSpi(String name) {
		FilePreferences child = children.get(name);
		if(child==null||child.isRemoved()){
			child = new FilePreferences(this, name);
			children.put(name, child);
		}
		return child;
	}

	@Override
	protected void syncSpi() throws BackingStoreException {
		if(isRemoved()) return;
		final File file = FilePreferencesFactory.getPreferencesFile();
		if(!file.exists()) return;
		synchronized (file){
			Properties p = new Properties();
			try{
				p.load(new FileInputStream(file));
				StringBuilder sb = new StringBuilder();
				getPath(sb);
				String path = sb.toString();
				
				final Enumeration<?> pnen = p.propertyNames();
				while(pnen.hasMoreElements()){
					String propKey = (String)pnen.nextElement();
					if(propKey.startsWith(path)){
						String subKey = propKey.substring(path.length());
						if(subKey.indexOf('.')==-1){
							root.put(subKey, p.getProperty(propKey));
						}
					}
				}
			}catch(IOException e){
				throw new BackingStoreException(e);
			}
		}
	}

	private void getPath(StringBuilder sb) {
		final FilePreferences parent = (FilePreferences)parent();
		if(parent==null) return;
		parent.getPath(sb);
		sb.append(name()).append('.');
	}

	@Override
	protected void flushSpi() throws BackingStoreException {
		final File file = FilePreferencesFactory.getPreferencesFile();
		synchronized (file) {
			Properties p = new Properties();
			try{
				StringBuilder sb = new StringBuilder();
				getPath(sb);
				String path = sb.toString();
				if(file.exists()){
					p.load(new FileInputStream(file));
					List<String> toRemove = new ArrayList<String>();
					
					final Enumeration<?> pnen = p.propertyNames();
					while(pnen.hasMoreElements()){
						String propKey = (String)pnen.nextElement();
						if(propKey.startsWith(path)){
							String subKey = propKey.substring(path.length());
							if(subKey.indexOf('.')==-1){
								toRemove.add(propKey);
							}
						}
					}
					for(String propKey:toRemove){
						p.remove(propKey);
					}
				}
				if(!isRemoved){
					for(String s:root.keySet()){
						p.setProperty(path+s, root.get(s));
					}
				}
				p.store(new FileOutputStream(file), "FilePreferences");
				
			}catch(IOException e){
				throw new BackingStoreException(e);
			}
		}
	}

}
