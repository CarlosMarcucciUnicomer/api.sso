package com.unicomer.api.sso.models.dto.otps;

public class ValidateResponseDto {
    private Boolean ok;
    private String title;
    private String message;
    
    public ValidateResponseDto(Boolean ok, String title, String message) {
        this.ok = ok;
        this.title = title;
        this.message = message;
    }

    public Boolean isOk() {
        return this.ok;
    }

    public Boolean getOk() {
        return this.ok;
    }

    public void setOk(Boolean ok) {
        this.ok = ok;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
