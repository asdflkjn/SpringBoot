package com.example.domain;

import lombok.Data;

@Data
public class MemberVO {

	private String id;
	private String password;
	private String name;

	private String originFilename;
	private String filename;
}
