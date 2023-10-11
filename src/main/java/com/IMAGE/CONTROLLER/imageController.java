package com.IMAGE.CONTROLLER;

import java.io.IOException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.IMAGE.SERVICE.imageService;
import com.IMAGE.VO.detailsVO;
import com.IMAGE.VO.passwordResetDTO;

import reactor.core.publisher.Mono;





@RestController
public class imageController {
	@Autowired private imageService imageService;
	
	@PostMapping("/")
	public ResponseEntity<String> saveImages(@RequestParam("image") MultipartFile[] file) throws IOException
	{
		//System.out.println(detailsVO.id+" "+detailsVO.name+" "+detailsVO.address);
		Arrays.stream(file).forEach(multipartFile ->
		{
			try {
				this.imageService.uploadImage(multipartFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		return new ResponseEntity<String>("successfull uploaded",HttpStatus.OK);
	}
//	
//	@GetMapping("/{fileName}")
//	public ResponseEntity<?> downloadfile(@PathVariable String fileName)
//	{
//		byte[] data=this.imageService.downloadFile(fileName);
//		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.IMAGE_JPEG).body(data);
//	}
//	
//	
//	public Mono<ServerResponse> sendOtp(ServerRequest  serverRequest)
//	{
//		return serverRequest.bodyToMono(passwordResetDTO.class)
//				.flatMap(dto->this.imageService.sendOTP(dto))
//				.flatMap(dto->ServerResponse.status(HttpStatus.ACCEPTED)
//				.body(BodyInserters.fromValue(dto)));
//	}
//	
//	
//	public Mono<ServerResponse> validateOTP(ServerRequest  serverRequest)
//	{
//		return serverRequest.bodyToMono(passwordResetDTO.class)
//				.flatMap(dto->this.imageService.validateOtp(dto))
//				.flatMap(dto->ServerResponse.status(HttpStatus.ACCEPTED)
//				.bodyValue(dto));
//	}
//	

}
