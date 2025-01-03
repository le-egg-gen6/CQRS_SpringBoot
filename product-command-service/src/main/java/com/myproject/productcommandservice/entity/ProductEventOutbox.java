package com.myproject.productcommandservice.entity;

import com.myproject.productcommandservice.utils.JsonUtils;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

/**
 * @author nguyenle
 * @since 4:39 PM Mon 12/23/2024
 */
@Entity
@Table(
	name = "t_product_event_oubox"
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductEventOutbox {

	@Id
	@UuidGenerator
	private String id;

	private boolean sent = false;

	private String type;

	private String productJson;

	public ProductEventOutbox(String type, Product product) {
		this.type = type;
		this.productJson = JsonUtils.toJsonString(product);
	}

}
