package com.leosal.leotools.widgets;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JCheckBox;

public class TriStateCheckBox extends JCheckBox {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Boolean extendedState = null;
    private boolean halfState=true;
    private static Icon selected = new javax.swing.ImageIcon(TriStateCheckBox.class.
    		getResource("/com/leosal/leotools/graphics/selected.png"));
    private static Icon unselected = new javax.swing.ImageIcon(TriStateCheckBox.class.
    		getResource("/com/leosal/leotools/graphics/unselected.png"));
    private static Icon halfselected = new javax.swing.ImageIcon(TriStateCheckBox.class.
    		getResource("/com/leosal/leotools/graphics/halfselected.png"));
    private PropertyChangeSupport pcs;
    
    

    public TriStateCheckBox() {
		super();
		setActionListener();
	}

	public TriStateCheckBox(Action a) {
		super(a);
		setActionListener();
	}

	public TriStateCheckBox(Icon icon, boolean selected) {
		super(icon, selected);
		extendedState=selected;
		setExtendedState(extendedState);
		setActionListener();
	}

	public TriStateCheckBox(Icon icon) {
		super(icon);
		setActionListener();
	}

	public TriStateCheckBox(String text, boolean selected) {
		super(text, selected);
		extendedState=selected;
		setExtendedState(extendedState);
		setActionListener();
	}

	public TriStateCheckBox(String text, Icon icon, boolean selected) {
		super(text, icon, selected);
		extendedState=selected;
		setExtendedState(extendedState);
		setActionListener();
	}

	public TriStateCheckBox(String text, Icon icon) {
		super(text, icon);
		setActionListener();
	}

	public TriStateCheckBox(String text) {
		super(text);
		setActionListener();
	}
	
	private void setActionListener(){
		addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setNextState();
				
			}
		});
	}
	
	private void setNextState(){
		Boolean newState = extendedState;
		if(extendedState==null) newState=true;
		else if(extendedState) newState=false;
		else newState=null;
		
		setExtendedState(newState);
	}

	@Override
    public void paint(Graphics g) {
        if (isSelected()) {
            halfState = false;
        }
        setIcon(halfState ? halfselected : isSelected() ? selected : unselected);
        super.paint(g);
    }

    public boolean isHalfSelected() {
        return halfState;
    }

    public void setHalfSelected(boolean halfState) {
        this.halfState = halfState;
        if (halfState) {
            setSelected(false);
            repaint();
        }
    }

	public Boolean getExtendedState() {
		return extendedState;
	}
	public Boolean isExtendedState() {
		return extendedState;
	}

	public void setExtendedState(Boolean state) {
		Boolean oldState =extendedState;
		this.extendedState = state;
		if(state==null){
			setHalfSelected(true);
		}else{
			setSelected(state);
			setHalfSelected(false);
			
		}
		//System.out.println("Next state: "+extendedState);
		getPropertyChangeSupport().firePropertyChange("extendedState", oldState, extendedState);
		//System.out.println(state);
	}
	
	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener) {
        getPropertyChangeSupport().addPropertyChangeListener(listener);
    }
	
	@Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
		 getPropertyChangeSupport().removePropertyChangeListener(listener);
    }
	
	private PropertyChangeSupport getPropertyChangeSupport(){
		if(pcs==null) pcs=new PropertyChangeSupport(this);
		return pcs;
	}

	

}
