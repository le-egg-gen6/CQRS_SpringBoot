package com.myproject.productqueryservice.repository;

import com.myproject.productqueryservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author nguyenle
 * @since 4:18 PM Mon 12/23/2024
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
}
