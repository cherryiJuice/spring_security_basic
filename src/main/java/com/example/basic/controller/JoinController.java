package com.example.basic.controller;

import com.example.basic.dto.JoinDto;
import com.example.basic.service.JoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class JoinController {

	private final JoinService joinService;

	@GetMapping("/join")
	public String join() {
		return "join";
	}

	@PostMapping("/joinProc")
	public String joinProcess(JoinDto joinDto) {
		joinService.joinProcess(joinDto);
		return "redirect:/login";
	}
}
