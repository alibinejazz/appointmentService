package appointment.peaceofmind;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import appointment.peaceofmind.Controller.AppointmentController;
import appointment.peaceofmind.Model.Appointment;
import appointment.peaceofmind.Repository.IAppointmentRepo;
import appointment.peaceofmind.Service.AppointmentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@AutoConfigureJsonTesters
@AutoConfigureMockMvc
@SpringBootTest
class PeaceofmindApplicationTests {

	@Autowired
	private MockMvc mvc;

	@Mock
	private IAppointmentRepo appointmentrepo;

	@InjectMocks
	private AppointmentController appointmentController;

	@Mock
	private AppointmentService appointmentService;

	private JacksonTester<Appointment> jsonAppointment;

	private JacksonTester<List<Appointment>> jsonAppointments;



	@BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        JacksonTester.initFields(this, new ObjectMapper().registerModule(new JavaTimeModule()));
        ;
        mvc = MockMvcBuilders.standaloneSetup(appointmentController).build();
    }


	// Book appointment
	@Test
	public void bookingAnAppointment() throws Exception{
		ZonedDateTime now = ZonedDateTime.now();
		Appointment appointment = new Appointment(1L, now, now, 1L, 1L, false, 0);

		when(appointmentrepo.createAppointment(appointment)).thenReturn(appointment);

			
		mvc.perform(MockMvcRequestBuilders
				.post("/appointments")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonAppointment.write(appointment).getJson()))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}

	// Delete appointment
	@Test
	public void deleteAnAppointment() throws Exception{
		Appointment appointment = new Appointment(1L, ZonedDateTime.now( ZoneOffset.UTC ), ZonedDateTime.now( ZoneOffset.UTC ), 12L, 5L, false, 0);

		when(appointmentService.deleteAppointment(appointment.getId())).thenReturn("Appointment deleted");


		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.delete("/appointments/delete/1");

		mvc.perform(request)
			.andExpect(status().isOk())
			.andExpect(content().string("Appointment deleted"));
	}

	// get appointment
	@Test
	public void getAnAppointment() throws Exception{
		Appointment appointment = new Appointment(1L, ZonedDateTime.now( ZoneOffset.UTC ), ZonedDateTime.now( ZoneOffset.UTC ), 12L, 5L, false, 0);
		
		when(appointmentService.getAppointment(1L)).thenReturn(appointment);

		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/appointments/get/1");

		mvc.perform(request)
			.andExpect(status().isOk())
			.andExpect(content().json(jsonAppointment.write(appointment).getJson()));

		verify(appointmentService).getAppointment(1L);
	}


	// get all appointment
	@Test
	public void testGetAllAppointments() throws Exception {
		ZonedDateTime now = ZonedDateTime.now();
		Appointment appointment1 = new Appointment(1L, now, now, 1L, 1L, false, 0);
		Appointment appointment2 = new Appointment(2L, now, now, 2L, 2L, false, 0);
		List<Appointment> appointments = Arrays.asList(appointment1, appointment2);

		when(appointmentService.getAllAppointments()).thenReturn(appointments);


		mvc.perform(MockMvcRequestBuilders.get("/appointments/getall")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().json(jsonAppointments.write(appointments).getJson()));
	}



	// update appointment
	@Test
	public void updateAppointment() throws Exception{
		ZonedDateTime now = ZonedDateTime.now();
		Appointment appointment = new Appointment(1L, now, now, 1L, 1L, false, 0);

		when(appointmentService.updateAppointment(appointment)).thenReturn("Appointment Updated !");
		mvc.perform(MockMvcRequestBuilders
				.post("/appointments/update")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonAppointment.write(appointment).getJson()))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
}
