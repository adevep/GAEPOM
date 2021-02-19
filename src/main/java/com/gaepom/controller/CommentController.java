package com.gaepom.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gaepom.domain.Comment;
import com.gaepom.service.CommentService;
//@SessionAttributes("guser")
@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class CommentController {
	@Autowired
	private CommentService commentService;
	
	// comment list
	@GetMapping("/getcommentlist")
	public ResponseEntity<List<Comment>> getCommentList(Long trackSeq) {
		try {
			List<Comment> commentList = commentService.getCommentList(trackSeq);
			System.out.println("list 반환 성공");
			
			return new ResponseEntity<>(commentList, HttpStatus.OK);
			
		} catch(Exception e) {
			e.printStackTrace();
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
			e.printStackTrace();
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
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}