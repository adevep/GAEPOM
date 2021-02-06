package com.gaepom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.gaepom.domain.User;
import com.gaepom.service.UserServiceImp;

@SessionAttributes("guser")
@Controller
public class UserController {

	@Autowired
	private UserServiceImp Userserviceimp;

//	@ModelAttribute("member")
//	public Member setMember() {
//		return new Member();
//	}

//	@RequestMapping("/getBoardList")
//	public String getBoardList(@ModelAttribute("member") Member member, Model model, Board board) {
//		
//		if (member.getId() == null) {
//			return "redirect:login.html";
//		}
//
//		List<Board> boardList = GUserserviceimp.getBoardList(board);
//
//		System.out.println(boardList);
//		model.addAttribute("boardList", boardList);
//		return "getBoardList";
//	}



	//@RequestMapping(value = "/insertguser", method = RequestMethod.POST)
	@PostMapping("/insertuser")
	public String insertUser(@ModelAttribute User user) {
		System.out.println("실행");
		if (user.getUserId() == null) {
			return "redirect:login.html";
		}
		System.out.println(1);
		Userserviceimp.insertUser(user);
		System.out.println("-------insertuser----------------------");
		return "redirect:login.html";
	}

//	@GetMapping("/getBoard")
//	public String getBoard(@ModelAttribute("member") Member member, Board board, Model model) {
//		if (member.getId() == null) {
//			return "redirect:login";
//		}
//
//		model.addAttribute("board", GUserserviceimp.getBoard(board));
//		return "getBoard";
//	}
//
//	@PostMapping("/updateBoard")
//	public String updateBoard(@ModelAttribute("member") Member member, Board board) {
//		if (member.getId() == null) {
//			return "redirect:login";
//		}
//
//		GUserserviceimp.updateBoard(board);
//		return "forward:getBoardList";
//	}
//
//	@GetMapping("/deleteBoard")
//	public String deleteBoard(@ModelAttribute("member") Member member, Board board) {
//		if (member.getId() == null) {
//			return "redirect:login";
//		}
//
//		GUserserviceimp.deleteBoard(board);
//		return "forward:getBoardList";
//	}

}
