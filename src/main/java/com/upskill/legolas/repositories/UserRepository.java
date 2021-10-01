package com.upskill.legolas.repositories;

import com.upskill.legolas.models.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository <User, Long> {
    @Query("SELECT u FROM User u WHERE u.email = :email")
	public User getUserByEmail(@Param("email") String email);
	
	@Query("SELECT u FROM User u WHERE u.email = ?1")
    public User findByEmail(String email);
	
	@Query("SELECT u FROM User u WHERE " +
			" CONCAT(u.id, u.country, u.email, u.last_name, u.first_name, u.user_status, u.password, u.roles, u.mobile,"
			+ "u.learningTrackUsers, u.moduleProgresses, u.topicUsers)" + " LIKE %?1%")
	public Page<User> findAll(String keyword, Pageable pageable);
}
