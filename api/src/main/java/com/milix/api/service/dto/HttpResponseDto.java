package com.milix.api.service.dto;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HttpResponseDto {

    private HttpStatus httpStatus;

    private String message;

    private Object responseData;
}
