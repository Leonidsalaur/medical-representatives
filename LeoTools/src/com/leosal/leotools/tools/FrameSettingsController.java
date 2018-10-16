package com.leosal.leotools.tools;

import java.util.prefs.Preferences;

import javax.swing.JInternalFrame;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;


public class FrameSettingsController {
	private JInternalFrame frame;
	private Preferences prefs = Preferences.userNodeForPackage(FrameSettingsController.class);
	
	private static final String FRAME_WIDTH = "frame_width";
	private static final String FRAME_HEIGHT = "frame_height";
	private static final String FRAME_TOP = "frame_top";
	private static final String FRAME_LEFT = "frame_left";
	
	private int x,y,width,height;
	
	public FrameSettingsController(final JInternalFrame frame){
		if(frame==null) return;
		this.frame = frame;	
		
		//System.out.println(frame.getClass());
		frame.addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameClosed(InternalFrameEvent e) {
				prefs.putInt(FRAME_LEFT+frame.getClass(), frame.getX());
				prefs.putInt(FRAME_TOP+frame.getClass(), frame.getY());
				prefs.putInt(FRAME_WIDTH+frame.getClass(), frame.getWidth());
				prefs.putInt(FRAME_HEIGHT+frame.getClass(), frame.getHeight());
			}
		});
		getSettings();
	}

	private void getSettings() {
		if(frame==null)
			return;
		x = Math.max(0,prefs.getInt(FRAME_LEFT+frame.getClass(), frame.getX()));
		//x = Math.min(x, frame.getParent().getWidth()-20);
		y = Math.max(0,prefs.getInt(FRAME_TOP+frame.getClass(), frame.getY()));
		//y = Math.min(y, frame.getParent().getHeight()-20);
		width = prefs.getInt(FRAME_WIDTH+frame.getClass(), frame.getWidth());
		height = prefs.getInt(FRAME_HEIGHT+frame.getClass(), frame.getHeight());
		frame.setBounds(x, y, width, height);
	}
	

}
