package com.leosal.medrep.models;

import javax.swing.ListModel;
import javax.swing.table.TableModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.jgoodies.binding.PresentationModel;
import com.jgoodies.binding.adapter.AbstractTableAdapter;
import com.jgoodies.binding.list.SelectionInList;
import com.jgoodies.binding.value.ValueHolder;
import com.leosal.medrepear.dto.InstitutionDTO;

@Component
@Scope("prototype")
public class ContactPresentationModel extends PresentationModel<ContactModel> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final SelectionInList<InstitutionDTO> selectionInList;
	
	public ContactPresentationModel(@Autowired ContactModel model) {
		super(model);
		selectionInList = new SelectionInList<>(new ValueHolder(model.getInstituionsListModel(), true), getModel(ContactModel.INSTITUTION_SELECTION_FIELD));
	}

	public SelectionInList<InstitutionDTO> getSelectionInList() {
		return selectionInList;
	}
	
	public TableModel getTableModel() {
		
		String[] columnNames = new String[] {"Institution", "Code"};
		return new InstitutionsInContactsTableAdapter(getBean().getInstituionsListModel(), columnNames);
	}
	
	private class InstitutionsInContactsTableAdapter extends AbstractTableAdapter<InstitutionDTO>{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public InstitutionsInContactsTableAdapter(ListModel<InstitutionDTO> model, String[] columnNames) {
			super(model, columnNames);
		}

		@Override
		public Object getValueAt(int rowIndex, int colIndex) {
			InstitutionDTO inst = getRow(rowIndex);
			
			switch (colIndex) {
			case 0:
				return inst.getName();
			case 1:
				return inst.getCode();
			}
			return null;
		}
	}

}
