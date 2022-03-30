package com.womenempowerment.dao;


import com.womenempowerment.entity.FeedBack;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IFeedBackDao extends JpaRepository<FeedBack, Integer>{
	@Query(value = "SELECT * FROM FeedBack fb WHERE fb.schemeName like %?1%", 
			  nativeQuery = true)
	public List<FeedBack> viewbySchemeName(String schemeName);
	
	@Query(value = "SELECT * FROM FeedBack fb WHERE fb.trainingCourseName like %?1%", 
			  nativeQuery = true)
	public List<FeedBack> viewbytrainingCourseName(String trainingCourseName);
}
