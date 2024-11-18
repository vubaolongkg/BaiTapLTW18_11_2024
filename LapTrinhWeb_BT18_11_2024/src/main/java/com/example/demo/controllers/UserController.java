package com.example.demo.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.UserInfo;
import com.example.demo.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController

@RequestMapping("/user")

@RequiredArgsConstructor
public class UserController {
	private final UserService userService;

	@PostMapping("/new")

	public String addUser(@RequestBody UserInfo userInfo) { return userService.addUser(userInfo);} 
}
