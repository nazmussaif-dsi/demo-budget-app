package com.saif.helper.exception;

import com.saif.helper.dtos.ErrorResponseDTO;
import lombok.Data;
import lombok.NonNull;
import org.springframework.http.HttpStatus;

@Data
public class ServiceException extends Exception {
  @NonNull
  private final ErrorResponseDTO responseDTO;

  @NonNull
  private final HttpStatus status;

  @NonNull
  private final String message;

  public ServiceException(@NonNull String message, HttpStatus status) {
    this.status = status;
    this.message = message;
    responseDTO = new ErrorResponseDTO(status.getReasonPhrase(), message, "API-" + status.value());
  }
}
