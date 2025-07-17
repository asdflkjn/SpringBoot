package com.example.domain;

import lombok.Data;

@Data
public class FileVO {
	
	  private Integer fileid;
	  private Integer board_seq;
	  private String originFilename;
	  private String filename;
	  private String filepath;
}
