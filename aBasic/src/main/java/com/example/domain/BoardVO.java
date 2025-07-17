package com.example.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class BoardVO {
	private Integer seq;
	private String 	title;
	private String 	writer;
	private String 	content;
	private String 	regdate;
	private Integer readcnt;
	
}
