package com.aydsii.tp2.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class ApiResponse<T> {
    
    @Schema(description = "Código de estado HTTP como número", example = "200")
    private int status;

    @Schema(description = "Mensaje descriptivo del resultado", example = "Operacion realizada con exito")
    private String message;

    @Schema(description = "Contenido real de la respuesta")
    private T data;

    public ApiResponse() {
    }

    public ApiResponse(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public int getStatus() { 
        return status; 
    }

    public void setStatus(int status) { 
        this.status = status; 
    }

    public String getMessage() { 
        return message; 
    }

    public void setMessage(String message) { 
        this.message = message; 
    }

    public T getData() { 
        return data; 
    }

    public void setData(T data) { 
        this.data = data; 
    }
}
