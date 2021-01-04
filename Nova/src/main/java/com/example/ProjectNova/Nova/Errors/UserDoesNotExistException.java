package com.example.ProjectNova.Nova.Errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "The username cannot be found")
public class UserDoesNotExistException extends Exception {
}
