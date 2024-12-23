package com.myproject.productqueryservice.event;

import com.myproject.productqueryservice.entity.Product;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

/**
 * @author nguyenle
 * @since 4:21 PM Mon 12/23/2024
 */
@Getter
@Setter
public class ProductEvent extends ApplicationEvent {

	private String type;

	public ProductEvent(Product product, String type) {
		super(product);
		this.type = type;
	}
}
