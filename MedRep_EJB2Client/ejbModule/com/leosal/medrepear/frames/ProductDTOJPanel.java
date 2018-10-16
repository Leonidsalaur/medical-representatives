package com.leosal.medrepear.frames;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.BindingGroup;
import org.jdesktop.beansbinding.Bindings;

public class ProductDTOJPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BindingGroup m_bindingGroup;
	private com.leosal.medrepear.dto.ProductDTO productDTO = new com.leosal.medrepear.dto.ProductDTO();
	private JTextField barCodeJTextField;
	private JTextField nameJTextField;
	private JSpinner priceJSpinner;

	public ProductDTOJPanel(com.leosal.medrepear.dto.ProductDTO newProductDTO) {
		this();
		setProductDTO(newProductDTO);
	}

	public ProductDTOJPanel() {
		setLayout(new MigLayout("", "[61.00px:71.00px][73.00px:71.00px][53.00px][389px]", "[][20px][20px]"));
		
				JLabel nameLabel = new JLabel("Name:");
				add(nameLabel, "cell 0 0,alignx left,aligny center");
		
				nameJTextField = new JTextField();
				add(nameJTextField, "cell 1 0 3 1,growx,aligny center");
				
						JLabel priceLabel = new JLabel("Price:");
						add(priceLabel, "cell 0 1,alignx left,aligny center");
				
						priceJSpinner = new JSpinner();
						add(priceJSpinner, "cell 1 1,growx,aligny center");
		
				JLabel barCodeLabel = new JLabel("  BarCode:   ");
				add(barCodeLabel, "cell 2 1,alignx left,aligny center");

		barCodeJTextField = new JTextField();
		add(barCodeJTextField, "cell 3 1,growx,aligny center");

		if (productDTO != null) {
			m_bindingGroup = initDataBindings();
		}
	}

	protected BindingGroup initDataBindings() {
		BeanProperty<com.leosal.medrepear.dto.ProductDTO, java.math.BigInteger> barCodeProperty = BeanProperty
				.create("barCode");
		BeanProperty<javax.swing.JTextField, java.lang.String> textProperty = BeanProperty
				.create("text");
		AutoBinding<com.leosal.medrepear.dto.ProductDTO, java.math.BigInteger, javax.swing.JTextField, java.lang.String> autoBinding = Bindings
				.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE,
						productDTO, barCodeProperty, barCodeJTextField,
						textProperty);
		autoBinding.bind();
		//
		BeanProperty<com.leosal.medrepear.dto.ProductDTO, java.lang.String> nameProperty = BeanProperty
				.create("name");
		BeanProperty<javax.swing.JTextField, java.lang.String> textProperty_1 = BeanProperty
				.create("text");
		AutoBinding<com.leosal.medrepear.dto.ProductDTO, java.lang.String, javax.swing.JTextField, java.lang.String> autoBinding_1 = Bindings
				.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE,
						productDTO, nameProperty, nameJTextField,
						textProperty_1);
		autoBinding_1.bind();
		//
		BeanProperty<com.leosal.medrepear.dto.ProductDTO, java.lang.Float> priceProperty = BeanProperty
				.create("price");
		BeanProperty<javax.swing.JSpinner, java.lang.Object> valueProperty = BeanProperty
				.create("value");
		AutoBinding<com.leosal.medrepear.dto.ProductDTO, java.lang.Float, javax.swing.JSpinner, java.lang.Object> autoBinding_2 = Bindings
				.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE,
						productDTO, priceProperty, priceJSpinner, valueProperty);
		autoBinding_2.bind();
		//
		BindingGroup bindingGroup = new BindingGroup();
		bindingGroup.addBinding(autoBinding);
		bindingGroup.addBinding(autoBinding_1);
		bindingGroup.addBinding(autoBinding_2);
		//
		return bindingGroup;
	}

	public com.leosal.medrepear.dto.ProductDTO getProductDTO() {
		return productDTO;
	}

	public void setProductDTO(com.leosal.medrepear.dto.ProductDTO newProductDTO) {
		setProductDTO(newProductDTO, true);
	}

	public void setProductDTO(
			com.leosal.medrepear.dto.ProductDTO newProductDTO, boolean update) {
		productDTO = newProductDTO;
		if (update) {
			if (m_bindingGroup != null) {
				m_bindingGroup.unbind();
				m_bindingGroup = null;
			}
			if (productDTO != null) {
				m_bindingGroup = initDataBindings();
			}
		}
	}

}
