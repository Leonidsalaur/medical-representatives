package com.leosal.leotools.widgets;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.NumberFormat;

import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

public class NumberTextField extends JFormattedTextField {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Double value = null;
	private Double minValue, maxValue;
	private int digits;
	public NumberTextField(){
		this(2,null,null);
	}

	public NumberTextField(int digits, Double minValue, Double maxValue) {
		super();
		this.digits = digits;
		this.minValue = minValue;
		this.maxValue = maxValue;
		addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				if(!e.isTemporary())
					verifyValue();
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				
			}
		});
		
		this.setDigits(digits);

		
		
	}
	
	

	private void verifyValue(){
		//System.out.print("["+this.getText()+"]");
		try{
			if(this.getText().length()==0||this.getText()==null){
				this.value=null;
			}else{
				Double val = Double.parseDouble(this.getText());
				boolean minChecked=true;
				if(minValue==null || (minValue!=null && val>=minValue))
					minChecked=true;
				else{
					JOptionPane.showMessageDialog(null, 
							"Please input value not less than "+minValue, 
							"Error", 
							JOptionPane.ERROR_MESSAGE);
					minChecked=false;
				}
				if(minChecked)
					if(maxValue==null || (maxValue!=null && val<=maxValue))
						this.value=val;
					else
						JOptionPane.showMessageDialog(null, 
								"Please input value not greater than "+maxValue, 
								"Error", 
								JOptionPane.ERROR_MESSAGE);
			}
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Invalid value!", "Error", JOptionPane.ERROR_MESSAGE);
		}
		if(this.value==null){
			//System.out.print(" -> Setting null value");
			this.setValue(null);
		}else{
			//System.out.print(" -> Setting  value "+value);
			this.setValue(value);
		}
		
		//System.out.println(" -> Value = "+this.getText());
		
	}
	/*
	public Double getDoubleValue(){
		return value;
	}
	public void setDoubleValue(Double value){
		this.value = value;
		if(value==null) this.setText("");
		else this.setText(value.toString());
		verifyValue();
	}
	public Float getFloatValue(){
		if(value==null) return null;
		return value.floatValue();
	}
	public void setFloatValue(Float value){
		if(value==null) return;
		else this.setDoubleValue(new Double(value));
	}
	public Integer getIntValue(){
		if(value==null) return null;
		return value.intValue();
	}
	public void setIntValue(Integer value){
		if(value==null) return;
		else this.setDoubleValue(new Double(value));
	}
*/
	public Double getMinValue() {
		return minValue;
	}

	public void setMinValue(Double minValue) {
		this.minValue = minValue;
	}

	public Double getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(Double maxValue) {
		this.maxValue = maxValue;
	}

	public int getDigits() {
		return digits;
	}

	public void setDigits(int digits) {
		this.digits = digits;
		NumberFormat numberFormat = NumberFormat.getNumberInstance();
		numberFormat.setMinimumFractionDigits(digits);
		numberFormat.setGroupingUsed(false);
		
		NumberFormatter formater = new NumberFormatter(numberFormat);
		DefaultFormatterFactory factory = new DefaultFormatterFactory(formater);
		
		this.setFormatterFactory(factory);
	}
	/*
	public static void setMinMax(final NumberTextField min, final NumberTextField max){
		//max.setMinValue(min.getMaxValue());
		//min.setMaxValue(max.getMinValue());
		min.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(max.getDoubleValue()==null) return;
				Double maxValue = max.getMaxValue();
				if(maxValue!=null && min.getDoubleValue()<=maxValue){	
					max.setMinValue(min.getDoubleValue());
					if(max.getMinValue()!=null && max.getDoubleValue()<max.getMinValue())
						max.setText(""+max.getMinValue());
				}
			}
		});
		max.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(min.getDoubleValue()==null) return;
				Double minValue = min.getMinValue();
				if(minValue!=null && min.getDoubleValue()>=minValue){
					
					min.setMaxValue(max.getDoubleValue());
					if(min.getMaxValue()!=null && (min.getDoubleValue()>min.getMaxValue()))
						min.setText(""+min.getMaxValue());
				}
			}
		});
	}

	*/

}
