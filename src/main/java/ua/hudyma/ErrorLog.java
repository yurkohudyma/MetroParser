package ua.hudyma;

public record ErrorLog(
        String fieldName,
        ErrorCode errorCode,
        String linePosition,
        String rawData
) {
}
