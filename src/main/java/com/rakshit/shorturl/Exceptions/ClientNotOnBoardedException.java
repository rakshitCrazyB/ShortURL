package com.rakshit.shorturl.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED, reason = "Entity not found")
public class ClientNotOnBoardedException extends RuntimeException{
}
