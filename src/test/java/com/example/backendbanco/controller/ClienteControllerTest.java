package com.example.backendbanco.controller;

import com.example.backendbanco.entity.Cliente;
import com.example.backendbanco.repository.ClienteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;

@RunWith(MockitoJUnitRunner.class)
public class ClienteControllerTest {

    private MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectWriter = objectMapper.writer();

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteController clienteController;

    Cliente cliente1 = new Cliente(1L, "Pepe", "Calle 1", "123");
    Cliente cliente2 = new Cliente(2L, "Flor", "Calle 2", "234");

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(clienteController).build();
    }

    @Test
    public void indexOKTest() throws Exception{
        List<Cliente> clientes = new ArrayList<>(Arrays.asList(cliente1, cliente2));

        ResponseEntity respuesta = new ResponseEntity(clientes, HttpStatus.OK);

        Mockito.when(clienteController.index()).thenReturn(respuesta);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/cliente/list")
                .contentType(MediaType.ALL))
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)));
    }

    @Test
    public void showOKTest() throws Exception {
        Mockito.when(clienteRepository.findById(cliente1.getId())).thenReturn(Optional.of(cliente1));
    }




















    /*@Autowired
    private ClienteRepository repository;

    @Test
    void index() {
        ClienteController controller = new ClienteController();
        Assertions.assertEquals("", controller.index());
    }

    @Test
    public void indexTest(){
        List<Cliente> clientes = repository.findAll();
        Assertions.assertEquals(clientes.size(), 0);
    }

    @Test
    public void showTest(){
        Cliente cliente = repository.findById(1L).get();
        Assertions.assertEquals(cliente.getId(), 1L);
    }*/

    /*@Test
    public void getEmployeeByIdTest()
    {
        when(dao.getEmployeeById(1)).thenReturn(new EmployeeVO(1,"Lokesh","Gupta","user@email.com"));

        EmployeeVO emp = manager.getEmployeeById(1);

        assertEquals("Lokesh", emp.getFirstName());
        assertEquals("Gupta", emp.getLastName());
        assertEquals("user@email.com", emp.getEmail());
    }

    @Test
    public void updateTest(){
        Cliente cliente = repository.findById(1L).get();
        cliente.setTelefono("123");

        Cliente clienteUpdated =  repository.save(cliente);

        Assertions.assertEquals(clienteUpdated.getTelefono(),"123");
    }*/

    /*@Test
    @Order(5)
    @Rollback(value = false)
    public void deleteEmployeeTest(){

        Employee employee = employeeRepository.findById(1L).get();

        employeeRepository.delete(employee);

        //employeeRepository.deleteById(1L);

        Employee employee1 = null;

        Optional<Employee> optionalEmployee = employeeRepository.findByEmail("ram@gmail.com");

        if(optionalEmployee.isPresent()){
            employee1 = optionalEmployee.get();
        }

        Assertions.assertThat(employee1).isNull();
    }*/
}
