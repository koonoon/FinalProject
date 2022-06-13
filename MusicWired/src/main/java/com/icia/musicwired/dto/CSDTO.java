package com.icia.musicwired.dto;


import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor	
@NoArgsConstructor
@Alias("chatSave")
public class CSDTO {
	private int csNum;
	private String csContent;
	private Date csDate;
	private String csId;

}
