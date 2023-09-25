package com.phincon.rest.ws.inquiryacc.model.dto.response;


import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Setter
@Getter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ResponseTemplate<T>{
    private Integer statusCode;
    private String message;
    private T data;

    public static <T> ResponseEntity<ResponseTemplate<T>> ok(T data) {
        ResponseTemplate<T> body = new ResponseTemplate<>();
        body.statusCode = HttpStatus.OK.value();
        body.message = HttpStatus.OK.name();
        body.data = data;
        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

}
