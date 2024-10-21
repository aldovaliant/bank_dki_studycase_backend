package bank.dki.studycase.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GlobalError {
    private String message;
    private String errorReason;
    private int errorCode;

    public GlobalError() {
    }

    public GlobalError(String message, String errorReason, int errorCode) {
        this.message = message;
        this.errorReason = errorReason;
        this.errorCode = errorCode;
    }
}
