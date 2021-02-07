package com.gaepom.service;

import java.util.List;

import com.gaepom.domain.Comment;

public interface CommentService {
	
	List<Comment> getCommentList(Comment comment);
	
	void insertComment(Comment comment);
	
	Comment getComment(Comment comment);
	
	void updateComment(Comment comment);
	
	void deleteComment(Comment comment);
	
	void updateCmtLike(Comment comment);

}
