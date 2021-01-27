package com.example.ProjectNova.Nova.Errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.ALREADY_REPORTED, reason = "The person already follows or is subscribed to the author specified")
public class CopyException extends Exception {

}
