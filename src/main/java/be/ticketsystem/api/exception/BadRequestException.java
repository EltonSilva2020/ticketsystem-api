package be.ticketsystem.api.exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BadRequestException extends TechnicalException {

  public BadRequestException(String message) {
    super(message);
  }
}
