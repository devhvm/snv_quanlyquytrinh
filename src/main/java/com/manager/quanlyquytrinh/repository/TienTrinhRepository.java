package com.manager.quanlyquytrinh.repository;

import com.manager.quanlyquytrinh.domain.TienTrinh;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the TienTrinh entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TienTrinhRepository extends JpaRepository<TienTrinh, Long> {

}
