package com.gaepom.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.gaepom.domain.Comment;

public interface CommentRepository extends CrudRepository<Comment, Long> {
	
//	@Query(value = "DELETE FROM t_comment WHERE user_id=?", nativeQuery = true)
//	void deleteCommentByUserId (String userid);

}
