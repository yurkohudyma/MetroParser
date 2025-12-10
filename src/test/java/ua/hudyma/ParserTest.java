package ua.hudyma;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static ua.hudyma.ErrorCode.NO_ERROR;
import static ua.hudyma.ErrorCode.SEVERE;
import static ua.hudyma.Validator.validate;

public class ParserTest {
    private final String ACTIVITY_DATE = "activityDate";
    private final String PHONE_NUMBER = "reporterPhone";

    /**
     * Date: invalid date checkup (Feb 30)
     */
    @Test
    void testInvalidDate() {
        assertEquals(ErrorCode.RELEVANT,
                validate(ACTIVITY_DATE, "20230230"));
    }

    /**
     * Date: future date checkup (One day ahead of today's)
     */
    @Test
    void testFutureDateInvokesSEVERE() {
        String future = LocalDate.now(ZoneOffset.UTC).plusDays(1)
                .format(DateTimeFormatter.ofPattern("MMdduuuu"));
        assertEquals(SEVERE, validate(ACTIVITY_DATE, future));
    }

    /**
     * Phone: correct digits-only (12 digits)
     */
    @Test
    void testCorrectPhoneNumberReturnsNoError() {
        assertEquals(NO_ERROR,
                validate(PHONE_NUMBER, "380991234567"));
    }

    /** Phone: wrong format (non-digits) */
    @Test
    void testInvalidPhoneNumberDoesNotReturnNoError() {
        assertNotEquals(NO_ERROR,
                validate(PHONE_NUMBER, "123-456-789"));
    }
}
