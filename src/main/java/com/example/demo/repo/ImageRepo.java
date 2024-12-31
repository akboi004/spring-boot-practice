package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.ImageEntity;

@Repository
public interface ImageRepo extends JpaRepository<ImageEntity, Long> {

}
