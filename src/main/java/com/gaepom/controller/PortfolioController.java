package com.gaepom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.gaepom.domain.Portfolio;
import com.gaepom.domain.User;
import com.gaepom.service.PortfolioServiceImpl;
import com.gaepom.service.UserServiceImpl_bak;

@SessionAttributes("pf")
@Controller
public class PortfolioController {

	@Autowired
	private PortfolioServiceImpl portfolioServiceImp;

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

//	@RequestMapping(value = "/insertpf", method = RequestMethod.POST)
	@PostMapping("/insertpf")
	public String insertPortfolio(@ModelAttribute Portfolio pf) {
		System.out.println("=== # insertPortfolio() 조회 중 ===");
		if (pf.getPfSeq() == null) {
			return "redirect:index.html";
		}
		
		portfolioServiceImp.insertPortfolio(pf);
		System.out.println(pf.getPfSeq() + "번 님의 새로운 포트폴리오가 등록되었습니다.");
		return "redirect:index.html";
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
