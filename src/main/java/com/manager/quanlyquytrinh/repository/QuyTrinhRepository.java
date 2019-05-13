package com.manager.quanlyquytrinh.repository;

import com.manager.quanlyquytrinh.domain.QuyTrinh;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


/**
 * Spring Data  repository for the QuyTrinh entity.
 */
@SuppressWarnings("unused")
@Repository
public interface QuyTrinhRepository extends JpaRepository<QuyTrinh, Long> {
    Optional<QuyTrinh> findByQuyTrinhCode(String quyTrinhCode);
}
