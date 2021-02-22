package com.gaepom.service;

import java.util.List;

import com.gaepom.domain.Comment;

public interface CommentService {

	List<Comment> getCommentList(Long trackSeq);

	void insertComment(Comment comment);

	Comment getComment(Long cmtSeq);

	void deleteComment(Comment comment);

}
