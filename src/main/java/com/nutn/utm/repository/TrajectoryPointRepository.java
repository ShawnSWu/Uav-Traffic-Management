package com.nutn.utm.repository;

import com.nutn.utm.model.entity.TrajectoryPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */

@Repository
public interface TrajectoryPointRepository extends JpaRepository<TrajectoryPoint, Long> {
}
