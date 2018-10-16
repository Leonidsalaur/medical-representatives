package com.leosal.medrepear.frames;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.BindingGroup;
import org.jdesktop.beansbinding.Bindings;

public class AdvertDTOJPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BindingGroup m_bindingGroup;
	private com.leosal.medrepear.dto.AdvertDTO advertDTO = new com.leosal.medrepear.dto.AdvertDTO();
	private JTextField nameJTextField;
	private JSpinner priceJSpinner;
	private JTextField barCodeJTextField;

	public AdvertDTOJPanel(com.leosal.medrepear.dto.AdvertDTO newAdvertDTO) {
		this();
		setAdvertDTO(newAdvertDTO);
	}

	public AdvertDTOJPanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 95, 67, 182, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, 1.0E-4 };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 1.0E-4 };
		setLayout(gridBagLayout);

		JLabel nameLabel = new JLabel("Name:");
		GridBagConstraints labelGbc_0 = new GridBagConstraints();
		labelGbc_0.insets = new Insets(5, 5, 5, 5);
		labelGbc_0.gridx = 0;
		labelGbc_0.gridy = 0;
		add(nameLabel, labelGbc_0);

		nameJTextField = new JTextField();
		GridBagConstraints componentGbc_0 = new GridBagConstraints();
		componentGbc_0.gridwidth = 4;
		componentGbc_0.insets = new Insets(5, 0, 5, 0);
		componentGbc_0.fill = GridBagConstraints.HORIZONTAL;
		componentGbc_0.gridx = 1;
		componentGbc_0.gridy = 0;
		add(nameJTextField, componentGbc_0);

		JLabel priceLabel = new JLabel("Price:");
		GridBagConstraints labelGbc_1 = new GridBagConstraints();
		labelGbc_1.insets = new Insets(5, 5, 0, 5);
		labelGbc_1.gridx = 0;
		labelGbc_1.gridy = 1;
		add(priceLabel, labelGbc_1);

		priceJSpinner = new JSpinner();
		priceJSpinner.setModel(new SpinnerNumberModel(new Float(0), new Float(0), null, new Float(0.1)));
		GridBagConstraints componentGbc_1 = new GridBagConstraints();
		componentGbc_1.insets = new Insets(5, 0, 0, 5);
		componentGbc_1.fill = GridBagConstraints.HORIZONTAL;
		componentGbc_1.gridx = 1;
		componentGbc_1.gridy = 1;
		add(priceJSpinner, componentGbc_1);
		
				JLabel barCodeLabel = new JLabel("BarCode:");
				GridBagConstraints labelGbc_2 = new GridBagConstraints();
				labelGbc_2.anchor = GridBagConstraints.EAST;
				labelGbc_2.insets = new Insets(5, 5, 0, 5);
				labelGbc_2.gridx = 2;
				labelGbc_2.gridy = 1;
				add(barCodeLabel, labelGbc_2);
		
				barCodeJTextField = new JTextField();
				GridBagConstraints componentGbc_2 = new GridBagConstraints();
				componentGbc_2.insets = new Insets(5, 0, 0, 5);
				componentGbc_2.fill = GridBagConstraints.HORIZONTAL;
				componentGbc_2.gridx = 3;
				componentGbc_2.gridy = 1;
				add(barCodeJTextField, componentGbc_2);

		if (advertDTO != null) {
			m_bindingGroup = initDataBindings();
		}
	}

	protected BindingGroup initDataBindings() {
		BeanProperty<com.leosal.medrepear.dto.AdvertDTO, java.lang.String> nameProperty = BeanProperty
				.create("name");
		BeanProperty<javax.swing.JTextField, java.lang.String> textProperty = BeanProperty
				.create("text");
		AutoBinding<com.leosal.medrepear.dto.AdvertDTO, java.lang.String, javax.swing.JTextField, java.lang.String> autoBinding = Bindings
				.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE,
						advertDTO, nameProperty, nameJTextField, textProperty);
		autoBinding.bind();
		//
		BeanProperty<com.leosal.medrepear.dto.AdvertDTO, java.lang.Float> priceProperty = BeanProperty
				.create("price");
		BeanProperty<javax.swing.JSpinner, java.lang.Object> valueProperty = BeanProperty
				.create("value");
		AutoBinding<com.leosal.medrepear.dto.AdvertDTO, java.lang.Float, javax.swing.JSpinner, java.lang.Object> autoBinding_1 = Bindings
				.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE,
						advertDTO, priceProperty, priceJSpinner, valueProperty);
		autoBinding_1.bind();
		//
		BeanProperty<com.leosal.medrepear.dto.AdvertDTO, java.math.BigInteger> barCodeProperty = BeanProperty
				.create("barCode");
		BeanProperty<javax.swing.JTextField, java.lang.String> textProperty_1 = BeanProperty
				.create("text");
		AutoBinding<com.leosal.medrepear.dto.AdvertDTO, java.math.BigInteger, javax.swing.JTextField, java.lang.String> autoBinding_2 = Bindings
				.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE,
						advertDTO, barCodeProperty, barCodeJTextField,
						textProperty_1);
		autoBinding_2.bind();
		//
		BindingGroup bindingGroup = new BindingGroup();
		bindingGroup.addBinding(autoBinding);
		bindingGroup.addBinding(autoBinding_1);
		bindingGroup.addBinding(autoBinding_2);
		//
		return bindingGroup;
	}

	public com.leosal.medrepear.dto.AdvertDTO getAdvertDTO() {
		return advertDTO;
	}

	public void setAdvertDTO(com.leosal.medrepear.dto.AdvertDTO newAdvertDTO) {
		setAdvertDTO(newAdvertDTO, true);
	}

	public void setAdvertDTO(com.leosal.medrepear.dto.AdvertDTO newAdvertDTO,
			boolean update) {
		advertDTO = newAdvertDTO;
		if (update) {
			if (m_bindingGroup != null) {
				m_bindingGroup.unbind();
				m_bindingGroup = null;
			}
			if (advertDTO != null) {
				m_bindingGroup = initDataBindings();
			}
		}
	}

}
