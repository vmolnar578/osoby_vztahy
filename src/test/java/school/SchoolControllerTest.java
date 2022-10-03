package school;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.hamcrest.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import school.config.WebSecurityConfig;
import school.controller.UsersController;
import school.service.users.UsersDto;
import school.service.users.UsersService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UsersController.class)
class SchoolControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UsersService usersService;

	@MockBean
	private WebSecurityConfig securityConfig;

	private static ObjectMapper mapper = new ObjectMapper();

	@Test
	@SneakyThrows
	public void saveUser() {
		when(usersService.createUser(any())).thenReturn(1L);

		UsersDto dto = new UsersDto().setUsername("Meno Jozef").setPasswordHash("ahsfbsahfbhasjfb");
		String json = mapper.writeValueAsString(dto);

		mockMvc.perform(
						post("/api/school")
								.content(json)
								.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$", Matchers.equalTo(1)));

		verify(usersService, times(1)).createUser(any());
	}
}
