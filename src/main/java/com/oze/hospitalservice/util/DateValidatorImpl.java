package com.oze.hospitalservice.util;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateValidatorImpl implements DateValidator {
    private DateTimeFormatter dateFormatter;

    public DateValidatorImpl(DateTimeFormatter dateFormatter) {
        this.dateFormatter = dateFormatter;
    }

    @Override
    public boolean isValid(String dateStr) {
        this.dateFormatter.parse(dateStr);
        return true;
    }
}
