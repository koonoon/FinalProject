package com.icia.musicwired.dto;



import org.apache.ibatis.type.Alias;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor	
@NoArgsConstructor
@Alias("chatRoom")
public class CRDTO {
	int crNum;
	String crId;
	String crRead;
	
}