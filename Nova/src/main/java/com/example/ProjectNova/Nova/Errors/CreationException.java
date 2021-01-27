package com.example.ProjectNova.Nova.Errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "The object in creation could not be stored")
public class CreationException extends Exception {
}
