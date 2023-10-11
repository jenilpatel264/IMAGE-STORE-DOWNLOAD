package com.IMAGE.SERVICE;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.IMAGE.CONFIG.twilioConfig;
import com.IMAGE.DAO.imageDAO;
import com.IMAGE.VO.ImageData;
import com.IMAGE.VO.OTPSTATUS;
import com.IMAGE.VO.passwordResetDTO;
import com.IMAGE.VO.passwordResponse;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.lookups.v1.PhoneNumber;

import reactor.core.publisher.Mono;

@Service
public class imageService {
	
	@Autowired twilioConfig twilioConfig;
	
	Map<String,String> otpMapping; 
	
	
	@Autowired
	private imageDAO imageDAO;
	
	public void uploadImage(MultipartFile file) throws IOException
	{
		
		ImageData imageData=imageDAO.save(ImageData.builder().name(file.getOriginalFilename()
				).type(file.getContentType())
				 .imageData(ImageUtils.compressImage(file.getBytes())).build());
		
//		if(imageData!=null)
//		{
//			return "FILE HAS BEEN UPLOADED "+file.getOriginalFilename();
//		}
//		return "NOT SUCCESSFULLY";
		
	}
	
	public byte[] downloadFile(String fileName)
	{
		Optional<ImageData> imageData=this.imageDAO.findByName(fileName);
		byte[] image=ImageUtils.decompressImage(imageData.get().getImageData());
		
		return image;
		
	}
	
	public Mono<passwordResponse> sendOTP(passwordResetDTO passwordResetDTO)
	{
		passwordResponse passwordResponse=null;
		try
		{
		String otpNew=otpGenerate();
		
//		String to=passwordResetDTO.getNumber();
//		String fromString=this.twilioConfig.getTrialNumber();
		
		com.twilio.type.PhoneNumber to=new com.twilio.type.PhoneNumber(passwordResetDTO.getNumber());
		com.twilio.type.PhoneNumber from=new com.twilio.type.PhoneNumber(this.twilioConfig.getTrialNumber());
		String messageOTP="Dear "+passwordResetDTO.getUserName()+", your OTP is ##"+otpNew+"";
		
		 Message message = Message.creator(
			     to,
			      from,
			      messageOTP)
			    .create();
		 otpMapping.put(passwordResetDTO.getUserName(), otpNew);
		 
		 passwordResponse=new passwordResponse(OTPSTATUS.DELIVIRED, messageOTP);
		}
		catch (Exception e) {
			// TODO: handle exception
			 passwordResponse=new passwordResponse(OTPSTATUS.FAILED, e.getMessage());
		}
		
		return Mono.just(passwordResponse);

	}
	
	public Mono<String> validateOtp(passwordResetDTO passwordResetDTO)
	{
		if(passwordResetDTO.getOTP().equals(otpMapping.get(passwordResetDTO.getUserName())))
		{
			return Mono.just("VARIFICATION SUCCESSFULL");
		}
		else
		{
			return Mono.just("VARIFICATION DENIED");
		}
	}
	
	private String otpGenerate()
	{
		return new DecimalFormat("000000").format(new Random().nextInt(999999));
	}

	

}
