package com.myproject.productcommandservice.event;

import com.myproject.productcommandservice.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author nguyenle
 * @since 4:30 PM Mon 12/23/2024
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductEvent {

	private String type;

	private Product product;

}
