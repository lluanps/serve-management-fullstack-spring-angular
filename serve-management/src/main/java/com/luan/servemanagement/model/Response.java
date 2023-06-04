package com.luan.servemanagement.model;

import java.time.LocalDate;
import java.util.Map;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
//usado para incluir apenas valores que não são nulos
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {

	protected LocalDate timeStamp;
	protected int statusCode;
	protected HttpStatus status;
	protected String reason;
	protected String message;
	protected String developerMessage;
	//usado para mandar informação para o frontend quando for mandar um response
	protected Map<?, ?> data;
	
}
