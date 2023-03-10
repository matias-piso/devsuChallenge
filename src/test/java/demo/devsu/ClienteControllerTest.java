package demo.devsu;

import com.fasterxml.jackson.databind.ObjectMapper;
import demo.devsu.dto.ClienteDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest //permite levantar el contexto de spring, para poder usar los servicios.
@AutoConfigureMockMvc //permite hacer peticiones http a los endpoints que se creen en el controlador
public class ClienteControllerTest {

        @Autowired
        private MockMvc mockMvc;
        //a continuacion el siguiente test se encarga de verificar que el cliente no exista
        @Test
        public void testGetClienteNotFound() throws Exception {
                mockMvc.perform(MockMvcRequestBuilders.get("/clientes/100"))
                        .andExpect(status().isNotFound());
        }

        @Test
        public void testGetAllClientes() throws Exception {
                mockMvc.perform(MockMvcRequestBuilders.get("/clientes"))
                        .andExpect(status().isOk())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                        .andExpect(jsonPath("$", hasSize(3)));
        }
        
}
