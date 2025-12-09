package ua.hudyma;
public enum Header {

    RECORD_DESCRIPTOR("recordDescriptor", 4, 1, 4),
    RECORD_IDENTIFIER("recordIdentifier", 6, 5, 10),
    CYCLE_NUMBER("cycleNumber", 2, 11, 12),
    CCA_IDENTIFIER("ccaIdentifier", 10, 13, 22),
    EQUIFAX_IDENTIFIER("equifaxIdentifier", 10, 23, 32),
    EXPERIAN_IDENTIFIER("experianIdentifier", 5, 33, 37),
    TRANS_UNION_IDENTIFIER("transUnionIdentifier", 10, 38, 47),
    ACTIVITY_DATE("activityDate", 8, 48, 55),
    DATE_CREATED("dateCreated", 8, 56, 63),
    PROGRAM_DATE("programDate", 8, 64, 71),
    PROGRAM_REVISION_DATE("programRevisionDate", 8, 72, 79),
    REPORTER_NAME("reporterName", 40, 80, 119),
    REPORTED_ADDRESS("reportedAddress", 94, 120, 215),
    REPORTER_PHONE("reporterPhone", 10, 214, 225),
    RESERVED("reserved", 204, 224, 426);

    private final String fieldName;
    private final int length;
    private final int startIndex;
    private final int endIndex;


    @Override
    public String toString() {
        return fieldName + " = " +
                "[length=" + length + "] " +
                "[startIndex=" + startIndex + "] " +
                "[endIndex=" + endIndex + "]" + "\n";
    }

    Header(String fieldName, int length, int startIndex, int endIndex) {
        this.fieldName = fieldName;
        this.length = length;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    public String getFieldName() {
        return fieldName;
    }

    public int getLength() {
        return length;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }
}



