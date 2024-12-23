package com.myproject.productcommandservice.repository;

import com.myproject.productcommandservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author nguyenle
 * @since 4:31 PM Mon 12/23/2024
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

}
