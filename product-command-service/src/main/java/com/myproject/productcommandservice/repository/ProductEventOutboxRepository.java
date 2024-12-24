package com.myproject.productcommandservice.repository;

import com.myproject.productcommandservice.entity.ProductEventOutbox;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author nguyenle
 * @since 4:45 PM Mon 12/23/2024
 */
@Repository
public interface ProductEventOutboxRepository extends JpaRepository<ProductEventOutbox, String> {

	List<ProductEventOutbox> findAllBySentIsFalse();

}
