package com.gaepom.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.gaepom.dao.CommentRepository;
import com.gaepom.domain.Comment;
import com.gaepom.domain.User;
import com.gaepom.service.CommentService;
import com.gaepom.service.ProjectTrackingService;

//@SessionAttributes("guser")
@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class CommentController {
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private CommentRepository repoComm;
	
	@Autowired
	private ProjectTrackingService projectTrackingService;
	
	@ModelAttribute("guser")
	public User setUser() {
		return new User();
	}
	
	// comment list
	@GetMapping("/getcommentlist")
	public List<Comment> getCommentList(User user, Long trackSeq) {
		try {
			List<Comment> commentList = commentService.getCommentList(trackSeq);
			System.out.println("list 반환 성공"); 
			
			return commentList;
		} catch(Exception e) { 
			e.printStackTrace();
			return null;
		}
	}
	
	@GetMapping("/insertComment")
	public String insertCommentView(User user) {
		if (user.getUserId() == null) { 
			return "redirect:login";
		} 
		return "insertComment";
	}
	
	// 댓글 생성
	@PostMapping("/insertcomment")
	public Comment insertComment(User user, @RequestBody Comment comment) {
		commentService.insertComment(comment);
//		projectTrackingService.updateProjComment(comment);
		
		return comment;
	}
	
	@GetMapping("/getComment")
	public String getComment(User user, Comment comment, Model model) {
		if (user.getUserId() == null) {
			return "redirect:login";
		}
		model.addAttribute("comment", commentService.getComment(comment));
		return "getComment";
	}
	
	// 댓글 수정기능?? 넣을지 모르겠음
	@PostMapping("/updateComment")
	public String updateComment(User user, Comment comment) {
		if (user.getUserId() == null) {
			return "redirect:login";
		}
		commentService.updateComment(comment);
		return "forward:getCommentList";
	}
	
	@DeleteMapping("/deletecomment")
	public void deleteComment(User user, Long cmtSeq) {
		Optional<Comment> comment = repoComm.findById(cmtSeq);
		commentService.deleteComment(comment.get());
	}
}
