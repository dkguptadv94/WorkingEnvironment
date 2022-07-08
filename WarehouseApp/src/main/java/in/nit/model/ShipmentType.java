package in.nit.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "shipment_type_tab")
public class ShipmentType {
	@Id
	@GeneratedValue
	// @GeneratedValue(generator="my_ship_seq")
	// @SequenceGenerator(name="my_ship_seq",sequenceName="my_ship_seq")
	@Column(name = "ship_id_col")
	private Integer id;

	@Column(name = "ship_model_col",length=10,nullable = false)
	private String shipmentMode;

	@Column(name = "ship_code_col",length=15)
	private String shipmentCode;

	@Column(name = "enab_ship_col",length=4,nullable = false)
	private String enableShipment;

	@Column(name = "ship_grade_col",length=3,nullable = false)
	private String shipmentGrade;

	@Column(name = "desc_col",length=180)
	private String description;

}
