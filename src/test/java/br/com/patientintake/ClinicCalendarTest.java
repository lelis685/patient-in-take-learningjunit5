package br.com.patientintake;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class ClinicCalendarTest {

    private ClinicCalendar clinicCalendar;

    @BeforeEach
    void init(){
        clinicCalendar = new ClinicCalendar(LocalDate.of(2023,1,15));
    }


    @Test
    void allowEntryOfAnAppointment(){
        clinicCalendar.addAppointment("Jim", "Weaver", "avery",
                "9/1/2023 02:00 PM");
        List<PatientAppointment> appointments = clinicCalendar.getAppointments();

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
        clinicCalendar.addAppointment("Jim", "Weaver", "avery",
                "09/01/2018 2:00 pm");
        assertTrue(clinicCalendar.hasAppointment(LocalDate.of(2018, 9, 1)));
    }

    @Test
    void returnFalseForHasAppointmentsIfThereAreNoAppointments() {
        assertFalse(clinicCalendar.hasAppointment(LocalDate.of(2018, 9, 1)));
    }

    @Test
    void returnCurrentDaysAppointments() {

        clinicCalendar.addAppointment("Jim", "Weaver", "avery",
                "1/15/2023 2:00 pm");
        clinicCalendar.addAppointment("Jim", "Weaver", "avery",
                "1/15/2023 3:00 pm");
        clinicCalendar.addAppointment("Jim", "Weaver", "avery",
                "09/01/2018 2:00 pm");
        assertEquals(2, clinicCalendar.getTodayAppointments().size());
    }









}