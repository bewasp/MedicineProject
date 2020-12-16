package pl.edu.pwsztar.domain.dto;

import java.io.Serializable;

public class ResponseDto<T> implements Serializable {
    private T dto;
    private Integer code;

    public ResponseDto() {
    }

    public ResponseDto(T dto, Integer code) {
        this.code = code;
        this.dto = dto;
    }

    public Integer getCode() {
        return code;
    }

    public T getDto() {
        return dto;
    }
}
