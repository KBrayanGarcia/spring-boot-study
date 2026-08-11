package kedev.study.exception;

import java.util.List;

public record ValidationErrorResponse(int status, String message, List<ValidationErrorDetail> errors) {
}
