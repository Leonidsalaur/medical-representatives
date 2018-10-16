package com.leosal.medrepear.frames;

public interface DatabaseCatalog {

	public abstract void addItem();

	public abstract void modifySelectedItem();

	public abstract void removeSelectedItem();

	public abstract void refresh();

	public abstract void saveChanges();

	public abstract boolean isSaved();

}