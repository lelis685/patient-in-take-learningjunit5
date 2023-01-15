package br.com.learning.junit5.utils;

import org.junit.jupiter.api.Test;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class ClinicCalendarTest {


    @Test
    public void allowEntryOfAnAppointment(){

        ClinicCalendar clinicCalendar = new ClinicCalendar();
        clinicCalendar.addAppointment("Jim", "Weaver", "avery",
                "9/1/2023 02:00 PM");
        List<PatientAppointment> appointments = clinicCalendar.getAppointments();

        assertNotNull(appointments);
        assertEquals(1, appointments.size());
        PatientAppointment enteredAppt = appointments.get(0);
        assertEquals("Jim", enteredAppt.getPatientFirstName());
        assertEquals("Weaver", enteredAppt.getPatientLastName());
        assertEquals(Doctor.avery, enteredAppt.getDoctor());
        assertEquals("9/1/2023 02:00 PM",
                enteredAppt.getAppointmentDateTime().format(DateTimeFormatter
                        .ofPattern("M/d/yyyy hh:mm a",new Locale("pt", "BR"))));
    }






}