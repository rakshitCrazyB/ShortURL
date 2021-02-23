package com.rakshit.shorturl.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Entity not found")
public class InvalidInputException extends RuntimeException{
}
