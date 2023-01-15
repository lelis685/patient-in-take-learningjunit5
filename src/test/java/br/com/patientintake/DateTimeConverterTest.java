package br.com.patientintake;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DateTimeConverterTest {


    @Test
    void convertTodayStringCorrectly(){
        LocalDateTime result = DateTimeConverter.convertStringToDateTime("today 1:00 pm",
                LocalDate.of(2023, 1,15));

        assertEquals(result, LocalDateTime.of(2023, 1,15,13,0));
    }

    @Test
    void convertCorrectPatternDateTime(){
        LocalDateTime result = DateTimeConverter.convertStringToDateTime("1/15/2023 1:00 pm",
                LocalDate.of(2023, 1,15));

        assertEquals(result, LocalDateTime.of(2023, 1,15,13,0));
    }


    @Test
    void throwExceptionIfIncorrectPatternProvided(){

        String dateTimeStringInvalid = "1/99/2023 1:00 pm";
        RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> {
            DateTimeConverter.convertStringToDateTime(dateTimeStringInvalid,
                    LocalDate.of(2023, 1, 15));
        });

        assertEquals("Unable to create date time from: [" +
                dateTimeStringInvalid + "], please enter with format [M/d/yyyy h:mm a], Text '1/99/2023 1:00 PM' could not be parsed: Invalid value for DayOfMonth (valid values 1 - 28/31): 99",
                runtimeException.getMessage());

    }


}