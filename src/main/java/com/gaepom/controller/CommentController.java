package com.gaepom.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gaepom.domain.Comment;
import com.gaepom.service.CommentService;

@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping(value = "comment")
@RestController
public class CommentController {
	@Autowired
	private CommentService commentService;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	// comment list
	@GetMapping("/getcommentlist")
	public ResponseEntity<List<Comment>> getCommentList(Long trackSeq) {
		try {
			List<Comment> commentList = commentService.getCommentList(trackSeq);

			logger.info("comment list 반환 성공");
			return new ResponseEntity<>(commentList, HttpStatus.OK);

		} catch (Exception e) {
			logger.debug(e + "list 반환 실패");
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// 댓글 생성
	@PostMapping("/insertcomment")
	public ResponseEntity<Comment> insertComment(@RequestBody Comment comment) {
		try {
			commentService.insertComment(comment);
			return new ResponseEntity<>(comment, HttpStatus.OK);

		} catch (Exception e) {
			logger.debug(e + "comment 생성 실패");
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/deletecomment")
	public ResponseEntity<HttpStatus> deleteComment(Long cmtSeq) {
		try {

			Comment comment = commentService.getComment(cmtSeq);
			commentService.deleteComment(comment);

			return new ResponseEntity<>(HttpStatus.NO_CONTENT);

		} catch (Exception e) {
			logger.debug(e + "comment 삭제 실패");
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}