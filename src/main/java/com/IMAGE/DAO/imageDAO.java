package com.IMAGE.DAO;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.IMAGE.VO.ImageData;

@Repository
public interface imageDAO extends JpaRepository<ImageData, Long>{
	
	  Optional<ImageData> findByName(String fileName);
}