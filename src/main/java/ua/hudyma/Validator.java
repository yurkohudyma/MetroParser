package ua.hudyma;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static ua.hudyma.ErrorCode.*;

public class Validator {
    private static final String PHONE_REGEX = "^\\d{10,15}$";
    private static final String DATE_REGEX = "^\\d{8}$";
    private static final int METRO2_FILE_FORMAT_LENGTH = 426;

    protected static ErrorCode validate(String fieldName, String rawData) {
        if (isNullOrEmpty(rawData)) return CRITICAL;
        return switch (fieldName) {
            case "activityDate", "dateCreated", "programDate", "programRevisionDate"
                    -> validateDate(rawData);
            case "reporterPhone" -> isPhoneValid(rawData);
            default -> NO_ERROR;
        };
    }

    private static ErrorCode validateDate(String rawData) {
        if (!rawData.matches(DATE_REGEX)) return RELEVANT;
        var formatter = DateTimeFormatter.ofPattern("MMddyyyy");
        LocalDateTime data;
        try {
            data = LocalDate.parse(rawData, formatter).atStartOfDay();
        } catch (DateTimeParseException e) {
            return RELEVANT;
        }
        return data.isBefore(LocalDateTime.now()) ? NO_ERROR : SEVERE;
}

    protected static ErrorCode isPhoneValid(String phoneNumber) {
        return phoneNumber.matches(PHONE_REGEX) ? NO_ERROR : RELEVANT;
    }

    private static boolean isNullOrEmpty(String rawData) {
        return rawData == null || rawData.isBlank();
    }

    public static ErrorCode validateRawData(String rawData) {
        return rawData.length() == METRO2_FILE_FORMAT_LENGTH ? NO_ERROR : SEVERE;
    }
}
