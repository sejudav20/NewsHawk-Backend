package com.example.ProjectNova.Nova.Errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "The specified object cannot be found")
public class ObjectDoesNotExistException extends Exception {
}
