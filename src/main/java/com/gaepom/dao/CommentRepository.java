package com.gaepom.dao;

import org.springframework.data.repository.CrudRepository;

import com.gaepom.domain.Comment;

public interface CommentRepository extends CrudRepository<Comment, Long> {

}
