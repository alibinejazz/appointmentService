package appointment.peaceofmind;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import appointment.peaceofmind.Controller.AppointmentController;
import appointment.peaceofmind.Model.Appointment;
import appointment.peaceofmind.Repository.IAppointmentRepo;

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

	private JacksonTester<Appointment> jsonAppointment;
	private JacksonTester<List<Appointment>> jsonAppointments;

	@BeforeEach
	public void setUp() {
		JacksonTester.initFields(this, new ObjectMapper());
		mvc = MockMvcBuilders.standaloneSetup(appointmentController).build();
	}

	@Test
	public void bookingAnAppointment() throws Exception{
		Appointment appointment = new Appointment(1L,"2020-06-04", "2020-06-06", 1L, 1L, true,1);
		mvc.perform(MockMvcRequestBuilders
				.post("/appointments")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonAppointment.write(appointment).getJson()))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

}
