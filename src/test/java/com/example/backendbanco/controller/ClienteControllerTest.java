package com.example.backendbanco.controller;

import com.example.backendbanco.entity.Cliente;
import com.example.backendbanco.repository.ClienteRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ClienteControllerTest {
    @Autowired
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
    }

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
