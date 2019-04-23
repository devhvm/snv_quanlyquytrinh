package com.manager.quanlyquytrinh.repository;

import com.manager.quanlyquytrinh.domain.LoaiQuyTrinh;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the LoaiQuyTrinh entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LoaiQuyTrinhRepository extends JpaRepository<LoaiQuyTrinh, Long> {

}
