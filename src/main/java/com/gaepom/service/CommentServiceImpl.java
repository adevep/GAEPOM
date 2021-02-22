package com.gaepom.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gaepom.dao.CommentRepository;
import com.gaepom.dao.ProjectTrackingRepository;
import com.gaepom.domain.Comment;
import com.gaepom.exception.CommentException;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepository commentRepo;

	@Autowired
	private ProjectTrackingRepository trackingRepo;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public List<Comment> getCommentList(Long trackSeq) {
		return (List<Comment>) trackingRepo.findById(trackSeq).get().getTcomments();
	}

	public void insertComment(Comment comment) {
		if (!comment.getCmt().trim().isEmpty()) {
			logger.info(comment.getCmtSeq() + " 댓글 생성 완료");
			commentRepo.save(comment);
		} else {
			throw new CommentException("댓글의 내용이 없습니다");
		}
	}

	public Comment getComment(Long cmtSeq) {
		Optional<Comment> findComment = commentRepo.findById(cmtSeq);
		if (findComment.isPresent()) {
			return commentRepo.findById(cmtSeq).get();
		} else {
			throw new CommentException("존재하지 않는 댓글입니다");
		}
	}
	
	// 본인이 작성한 댓글이 아닙니다
	public void deleteComment(Comment comment) { 
		logger.info(comment.getCmtSeq() + " 댓글 삭제 완료");
		commentRepo.deleteById(comment.getCmtSeq());
	}
}
