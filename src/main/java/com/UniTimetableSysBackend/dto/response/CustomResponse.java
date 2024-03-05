package com.UniTimetableSysBackend.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomResponse<T> {
    private Object data;
    private int statusCode;
    private String message;
    private boolean successful;
}
