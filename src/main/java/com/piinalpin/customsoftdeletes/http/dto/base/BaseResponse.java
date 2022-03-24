package com.piinalpin.customsoftdeletes.http.dto.base;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class BaseResponse implements Serializable {

    private static final long serialVersionUID = -395801934596215889L;

    private LocalDateTime timestamp;

    private String responseCode;

    private String message;

    private Object data;
    
}
