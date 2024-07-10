package com.ttl.ITOapidrive.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ttl.ITOapidrive.entities.Region;
import com.ttl.ITOapidrive.repositories.RegionRepository;

@Service
public class RegionService {

    @Autowired
    private RegionRepository regionRepository;

    public Region createRegion(Region region) {
        region.setCreatedDt(LocalDateTime.now());
        region.setModifiedDt(LocalDateTime.now());
        return regionRepository.save(region);
    }

    public List<Region> getAllRegions() {
        return regionRepository.findAll();
    }
    public List<String> getAllRegionsByName() {
		return regionRepository.findAllRegionNames();
	}
    
    public Optional<Region> getRegionById(int regionId) {
        return regionRepository.findById(regionId);
    }

	
}
