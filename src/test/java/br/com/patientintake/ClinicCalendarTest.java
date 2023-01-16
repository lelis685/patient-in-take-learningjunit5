package br.com.patientintake;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class ClinicCalendarTest {

    private ClinicCalendar calendar;
    @BeforeEach
    void init(){
        calendar = new ClinicCalendar(LocalDate.of(2018,8,26));
    }


    @Test
    void allowEntryOfAnAppointment(){
        calendar.addAppointment("Jim", "Weaver", "avery",
                "9/1/2023 02:00 PM");
        List<PatientAppointment> appointments = calendar.getAppointments();

        assertNotNull(appointments);
        assertEquals(1, appointments.size());
        PatientAppointment enteredAppt = appointments.get(0);

        assertAll(
                () -> assertEquals("Jim", enteredAppt.getPatientFirstName()),
                () -> assertEquals("Weaver", enteredAppt.getPatientLastName()),
                () -> assertEquals(Doctor.avery, enteredAppt.getDoctor()),
                () -> assertEquals("9/1/2023 02:00 PM", enteredAppt.getAppointmentDateTime().format(DateTimeFormatter
                        .ofPattern("M/d/yyyy hh:mm a",new Locale("pt", "BR"))))
        );

    }


    @Test
    void returnTrueForHasAppointmentsIfThereAreAppointments() {
        calendar.addAppointment("Jim", "Weaver", "avery",
                "09/01/2018 2:00 pm");
        assertTrue(calendar.hasAppointment(LocalDate.of(2018, 9, 1)));
    }

    @Test
    void returnFalseForHasAppointmentsIfThereAreNoAppointments() {
        assertFalse(calendar.hasAppointment(LocalDate.of(2018, 9, 1)));
    }

    @Test
    void returnCurrentDaysAppointments() {

        calendar.addAppointment("Jim", "Weaver", "avery",
                "8/26/2018 2:00 pm");
        calendar.addAppointment("Jim", "Weaver", "avery",
                "1/15/2023 3:00 pm");
        calendar.addAppointment("Jim", "Weaver", "avery",
                "09/01/2018 2:00 pm");
        assertEquals(1, calendar.getTodayAppointments().size());
    }



    @Nested
    @DisplayName("return appointments for a given day correctly")
    class AppointmentsForDay {

        @Test
        @DisplayName("for today")
        void returnCurrentDaysAppointments() {
            calendar.addAppointment("Jim", "Weaver", "avery",
                    "08/26/2018 2:00 pm");
            calendar.addAppointment("Jim", "Weaver", "avery",
                    "08/26/2018 3:00 pm");
            calendar.addAppointment("Jim", "Weaver", "avery",
                    "09/01/2018 2:00 pm");
            assertEquals(2, calendar.getTodayAppointments().size());
        }

        @Test
        @DisplayName("for tomorrow")
        void returnTommorowsAppointments() {
            calendar.addAppointment("Jim", "Weaver", "avery",
                    "08/27/2018 2:00 pm");
            calendar.addAppointment("Jim", "Weaver", "avery",
                    "08/27/2018 2:00 pm");
            calendar.addAppointment("Jim", "Weaver", "avery",
                    "08/26/2018 3:00 pm");
            assertEquals(2, calendar.getTomorrowAppointments().size());
        }
    }






}