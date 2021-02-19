package com.gaepom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gaepom.dao.CommentRepository;
import com.gaepom.dao.ProjectTrackingRepository;
import com.gaepom.domain.Comment;

@Service
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	private CommentRepository commentRepo;
	
	@Autowired
	private ProjectTrackingRepository trackingRepo;
	
	public List<Comment> getCommentList(Long trackSeq) {
		return (List<Comment>) trackingRepo.findById(trackSeq).get().getTcomments();
	}
	
	public void insertComment(Comment comment) {
		commentRepo.save(comment);
	}
	
	public Comment getComment(Long cmtSeq) {
		return commentRepo.findById(cmtSeq).get();
	}
	
	public void updateComment(Comment comment) {
		Comment findComment = commentRepo.findById(comment.getCmtSeq()).get();
		findComment.setCmt(comment.getCmt());
		
		commentRepo.save(findComment);
	}
	
	//TODO 좋아요 활성/비활성 구분 로직 구현하기
	public void updateCmtLike(Comment comment) {
		Comment likeStatus = commentRepo.findById(comment.getCmtSeq()).get();
		likeStatus.setCmtLike(comment.getCmtLike());
	}
	
	public void deleteComment(Comment comment) {
		commentRepo.deleteById(comment.getCmtSeq());
	}
}
