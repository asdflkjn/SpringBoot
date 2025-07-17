package com.example.domain;

import lombok.Data;

@Data
public class BoardVO {

	private Integer seq;
	private String title;
	private String writer;
	private String content;
	private String regdate;
	private Integer cnt;
}
/*
	@Setter
	@Getter
	@ToString
	@NoArgsConstructor
	@AllArgsConstructor
*/