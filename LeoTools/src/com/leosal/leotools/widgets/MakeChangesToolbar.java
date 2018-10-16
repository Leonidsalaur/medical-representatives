package com.leosal.leotools.widgets;

import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public abstract class MakeChangesToolbar extends JToolBar {
	public MakeChangesToolbar() {
		
		btnAdd = new JButton("");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addItem();
			}
		});
		btnAdd.setToolTipText("Add new item");
		btnAdd.setMargin(new Insets(0, 0, 0, 0));
		btnAdd.setIcon(new ImageIcon(MakeChangesToolbar.class.getResource("/com/leosal/leotools/graphics/list-add.png")));
		add(btnAdd);
		
		btnModify = new JButton("");
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modifyItem();
			}
		});
		btnModify.setIcon(new ImageIcon(MakeChangesToolbar.class.getResource("/com/leosal/leotools/graphics/document-edit.png")));
		btnModify.setToolTipText("Modify selcted item");
		btnModify.setMargin(new Insets(0, 0, 0, 0));
		add(btnModify);
		
		btnRemove = new JButton("");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeItem();
			}
		});
		btnRemove.setIcon(new ImageIcon(MakeChangesToolbar.class.getResource("/com/leosal/leotools/graphics/edit-delete.png")));
		btnRemove.setToolTipText("Remove selected item");
		btnRemove.setMargin(new Insets(0, 0, 0, 0));
		add(btnRemove);
		
		btnSave = new JButton("");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveChanges();
			}
		});
		
		btnRefresh = new JButton("");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MakeChangesToolbar.this.refresh();
			}
		});
		btnRefresh.setToolTipText("Refresh");
		btnRefresh.setMargin(new Insets(0, 0, 0, 0));
		btnRefresh.setIcon(new ImageIcon(MakeChangesToolbar.class.getResource("/com/leosal/leotools/graphics/view-refresh.png")));
		add(btnRefresh);
		
		btnPrint = new JButton("");
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MakeChangesToolbar.this.print();
			}
		});
		btnPrint.setVisible(false);
		btnPrint.setToolTipText("Print");
		btnPrint.setIcon(new ImageIcon(MakeChangesToolbar.class.getResource("/com/leosal/leotools/graphics/document-print.png")));
		btnPrint.setMargin(new Insets(0, 0, 0, 0));
		add(btnPrint);
		btnSave.setIcon(new ImageIcon(MakeChangesToolbar.class.getResource("/com/leosal/leotools/graphics/document-save.png")));
		btnSave.setToolTipText("Save changes");
		btnSave.setMargin(new Insets(0, 0, 0, 0));
		btnSave.setEnabled(false);
		add(btnSave);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btnSave;
	private JButton btnRemove;
	private JButton btnModify;
	private JButton btnAdd;
	private JButton btnRefresh;
	private JButton btnPrint;
	
	public void setAddButton(boolean visible){
		this.btnAdd.setVisible(visible);
	}
	public void setModifyButton(boolean visible){
		this.btnModify.setVisible(visible);
	}
	public void setRemoveButton(boolean visible){
		this.btnRemove.setVisible(visible);
	}
	public void setSaveButton(boolean visible){
		this.btnSave.setVisible(visible);
	}
	public void setRefreshButton(boolean visible){
		this.btnRefresh.setVisible(visible);
	}
	public void setPrintButton(boolean visible){
		this.btnPrint.setVisible(visible);
	}
	public void setSaved(boolean saved){
		this.btnSave.setEnabled(!saved);
	}
	
	
	public abstract void addItem();
	public abstract void modifyItem();
	public abstract void removeItem();
	public abstract void saveChanges();
	public abstract void refresh();
	public abstract void print();
	
	

}
