package com.manager.quanlyquytrinh.repository;

import com.manager.quanlyquytrinh.domain.TienTrinhXuLy;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the TienTrinhXuLy entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TienTrinhXuLyRepository extends JpaRepository<TienTrinhXuLy, Long> {

}
