package com.phincon.rest.ws.inquiryacc.model.dto.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@Setter
@Getter
public class CustomResponse <T>{

    private T data;

    public static <T> ResponseEntity<CustomResponse<T>> okCustom(T data) {
        CustomResponse<T> body = new CustomResponse<>();
        body.setData(data);
        return ResponseEntity.status(HttpStatus.OK).body(body);
    }
}
