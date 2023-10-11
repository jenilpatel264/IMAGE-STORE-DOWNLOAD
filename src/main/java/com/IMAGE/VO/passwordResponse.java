package com.IMAGE.VO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class passwordResponse {
	
	private OTPSTATUS optStatus;
	private String message;

}
