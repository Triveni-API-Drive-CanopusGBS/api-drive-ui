package com.ttl.ITOapidrive.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ttl.ITOapidrive.entities.Region;

public interface RegionRepository extends JpaRepository<Region, Integer> {

	@Query(value = "SELECT region_name FROM regions", nativeQuery = true)
	List<String> findAllRegionNames();
}
