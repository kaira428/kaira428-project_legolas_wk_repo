package com.upskill.legolas.repositories;

import com.upskill.legolas.models.LearningTrack;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LearningTrackRepository extends JpaRepository<LearningTrack, Long>{
    
}
