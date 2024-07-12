package com.example.basic.service;

import com.example.basic.entity.User;
import com.example.basic.dto.JoinDto;
import com.example.basic.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JoinService {

	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	public void joinProcess(JoinDto joinDto) {
		//중복 회원 확인 로직
		if (userRepository.existsByUsername(joinDto.getUsername())) {
			throw new IllegalArgumentException("이미 존재하는 회원입니다.");
		}
		User user = User.builder()
			.username(joinDto.getUsername())
			.password(bCryptPasswordEncoder.encode(joinDto.getPassword()))
			.role("ROLE_USER")
			.build();
		userRepository.save(user);
	}
}
