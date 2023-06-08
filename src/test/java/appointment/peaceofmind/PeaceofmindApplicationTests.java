package appointment.peaceofmind;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

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

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import appointment.peaceofmind.Controller.AppointmentController;
import appointment.peaceofmind.Model.Appointment;
import appointment.peaceofmind.Repository.AppointmentRepository;
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

	@Autowired
	private JacksonTester<List<Appointment>> jsonAppointments;



	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		JacksonTester.initFields(this, new ObjectMapper());
		mvc = MockMvcBuilders.standaloneSetup(appointmentController).build();
	}


	// Book appointment
	// @Test
	// public void bookingAnAppointment() throws Exception{
	// 	Appointment appointment = new Appointment(1L, ZonedDateTime.now( ZoneOffset.UTC ), ZonedDateTime.now( ZoneOffset.UTC ), 12L, 5L, false, 0);

	// 	when(appointmentrepo.createAppointment(appointment)).thenReturn(appointment);

	// 	MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/appointments");

	// 	mvc.perform(request)
	// 		.andExpect(status().isOk());
			// .andExpect(content().string("Appointment deleted"));

		// mvc.perform(MockMvcRequestBuilders
		// 		.post("/appointments")
		// 		.contentType(MediaType.APPLICATION_JSON)
		// 		.content(jsonAppointment.write(appointment).getJson()))
		// 		.andExpect(MockMvcResultMatchers.status().isOk());


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
	// @Test
	// public void getAnAppointment() throws Exception{
	// 	Appointment appointment = new Appointment(1L, ZonedDateTime.now( ZoneOffset.UTC ), ZonedDateTime.now( ZoneOffset.UTC ), 12L, 5L, false, 0);
		
	// 	when(appointmentService.getAppointment(1L)).thenReturn(appointment);

	// 	MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/appointments/get/1");

	// 	mvc.perform(request)
	// 		.andExpect(status().isOk())
	// 		.andExpect(content().json(jsonAppointment.write(appointment).getJson()));

	// 	verify(appointmentService).getAppointment(1L);
	// }


	// get all appointment
	// @Test
	// public void getAllAppointment() throws Exception{
	// 	Appointment appointment = new Appointment(1L, ZonedDateTime.now( ZoneOffset.UTC ), ZonedDateTime.now( ZoneOffset.UTC ), 12L, 5L, false, 0);
	// 	Appointment appointment2 = new Appointment(2L, ZonedDateTime.now( ZoneOffset.UTC ), ZonedDateTime.now( ZoneOffset.UTC ), 12L, 5L, false, 0);

	// 	ArrayList<Appointment> arr = new ArrayList<>();

	// 	arr.add(appointment);
	// 	arr.add(appointment2);

		
	// 	when(appointmentService.getAllAppointments()).thenReturn(arr);


	// 	MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/appointments/getall");

	// 	mvc.perform(request)
	// 		.andExpect(status().isOk())
	// 		.andExpect(content().json(jsonAppointments.write(arr).getJson()));

	// 	verify(appointmentService).getAllAppointments();
	// }


	// update appointment
	// @Test
	// public void updateAppointment() throws Exception{
	// 	Appointment appointment = new Appointment(1L, ZonedDateTime.now( ZoneOffset.UTC ), ZonedDateTime.now( ZoneOffset.UTC ), 12L, 5L, false, 0);
	// 	//Appointment appointment2 = new Appointment(2L, Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now()), 12L, 5L, false, 0);
		
	// 	when(appointmentService.getAppointment(1L)).thenReturn(appointment);

	// 	appointment.setAvailability_id(5L);
	// 	appointment.setPatient_id(4L);
	// 	appointment.setConfirmed(false);

	// 	when(appointmentService.updateAppointment(appointment)).thenReturn("Appointment Updated !");


	// 	MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/appointments/update")
	// 				.contentType(org.springframework.http.MediaType.APPLICATION_JSON)
	// 				.content(jsonAppointment.write(appointment).getJson());

	// 	mvc.perform(request)
	// 		.andExpect(status().isOk());
	// 		// .andExpect(content().string("Appointment Updated !"));

	// 	// verify(appointmentService).updateAppointment(appointment);
	// }

}
