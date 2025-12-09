package ua.hudyma;

public record DataHeader(
        String recordDescriptor,
        String recordIdentifier,
        String cycleNumber,
        String ccaIdentifier,
        String equifaxIdentifier,
        String experianIdentifier,
        String transUnionIdentifier,
        String activityDate,
        String dateCreated,
        String programDate,
        String programRevisionDate,
        String reporterName,
        String reportedAddress,
        String reporterPhone,
        String reserved
) {

}
