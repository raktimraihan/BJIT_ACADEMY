package com.bjit.training.finalproject.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class CommonResponse {
    private String message;
    private String error;


}
