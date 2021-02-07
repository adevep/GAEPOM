package com.gaepom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.gaepom.domain.Comment;
import com.gaepom.domain.User;
import com.gaepom.service.CommentService;

@SessionAttributes("guser")
@Controller
public class CommentController {
	@Autowired
	private CommentService commentService;
	
	@ModelAttribute("guser")
	public User setUser() {
		return new User();
	}
	
	@RequestMapping("/getCommentList")
	public String getCommentList(@ModelAttribute("guser") User user, Model model,
			Comment comment) {
		if (user.getUserId() == null) {
			return "redirect:login.html";
		}
		List<Comment> commentList = commentService.getCommentList(comment);
		System.out.println(commentList);
		model.addAttribute("commentList", commentList);
		return "getCommentList";
	}
	
	@GetMapping("/insertComment")
	public String insertCommentView(@ModelAttribute("guser") User user) {
		if (user.getUserId() == null) {
			return "redirect:login";
		}
		
		return "insertComment";
	}
	
	@PostMapping("/insertComment")
	public String insertComment(@ModelAttribute("guser") User user, Comment comment) {
		if (user.getUserId() == null) {
			return "redirect:login";
		}
		
		commentService.insertComment(comment);
		return "redirect:getCommentList";
	}
	
	@GetMapping("/getComment")
	public String getComment(@ModelAttribute("guser") User user, Comment comment, Model model) {
		if (user.getUserId() == null) {
			return "redirect:login";
		}
		model.addAttribute("comment", commentService.getComment(comment));
		return "getComment";
	}
	
	@PostMapping("/updateComment")
	public String updateComment(@ModelAttribute("guser") User user, Comment comment) {
		if (user.getUserId() == null) {
			return "redirect:login";
		}
		commentService.updateComment(comment);
		return "forward:getCommentList";
	}
	
	@GetMapping("/deleteComment")
	public String deleteComment(@ModelAttribute("guser") User user, Comment comment) {
		if (user.getUserId() == null) {
			return "redirect:login";
		}
		commentService.deleteComment(comment);
		return "forward:getCommentList";
	}
}
