package com.upskill.legolas.repositories;

import java.util.List;

import com.upskill.legolas.models.Topic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long>{

    @Query(value="select * \n"
			+ "from topics \n"
			+ "where fk_module_id = :moduleId", nativeQuery = true)
	List<Topic> getTopicsUnderModule(@Param("moduleId")Long moduleId);

}
