package demo.devsu;


import demo.devsu.dto.ClienteDto;
import demo.devsu.entities.Cliente;
import demo.devsu.entities.Persona;
import demo.devsu.repositories.ClienteRepo;
import demo.devsu.services.ClienteService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class ClienteServiceTest {


    @Mock
    private ClienteRepo clienteRepositoryMock;

    @Mock
    private EntityManager entityManagerMock;

    @InjectMocks
    private ClienteService clienteService;


    // Test para obtenerPersonaPorId
    @Test
    public void testObtenerPersonaPorId() {
        // Se configura el mock del entityManager para devolver un objeto Cliente
        when(entityManagerMock.find(Mockito.eq(Cliente.class), Mockito.anyInt()))
                .thenReturn(new Cliente());

        // Se ejecuta el método a probar
        Cliente result = clienteService.obtenerPersonaPorId(1);

        // Se verifica que el resultado no sea nulo
        Assertions.assertNotNull(result);

        // Se verifica que se llamó al método correspondiente del entityManager
        Mockito.verify(entityManagerMock).find(Mockito.eq(Cliente.class), Mockito.anyInt());
    }

    // Test para deleteCliente
    @Test
    public void testDeleteCliente() {
        // Se configura el mock del clienteRepository para devolver un objeto Cliente
        Cliente cliente = new Cliente();
        cliente.setId(1);
        when(clienteRepositoryMock.findById(Mockito.eq(1))).thenReturn(Optional.of(cliente));

        // Se ejecuta el método a probar
        clienteService.deleteCliente(1);

        // Se verifica que se llamó al método correspondiente del clienteRepository
        Mockito.verify(clienteRepositoryMock).findById(Mockito.eq(1));
        Mockito.verify(clienteRepositoryMock).save(Mockito.any(Cliente.class));
    }



}
