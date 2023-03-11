package demo.devsu;

import demo.devsu.entities.*;
import demo.devsu.entities.enums.Generos;
import demo.devsu.entities.enums.TipoCuenta;
import demo.devsu.repositories.ClienteRepo;
import demo.devsu.repositories.CuentaRepo;
import demo.devsu.repositories.MovimientosRepo;
import demo.devsu.repositories.PersonaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class DevsuApplication {

	@Autowired private ClienteRepo clienteRepo;
	@Autowired private CuentaRepo cuentaRepo;

	@Autowired private MovimientosRepo movimientoRepo;

	@Autowired private PersonaRepo personaRepo;




	public static void main(String[] args) {
		SpringApplication.run(DevsuApplication.class, args);
	}
	@Bean
	public CommandLineRunner init() {
		return (args) -> {
			if (args.length > 0) {
				System.out.println(args[0]);
			}



			Persona persona1 = new Persona("Esteban gomez", Generos.MASCULINO,20,"1234","Calle 1","1234567");
			Persona persona2 = new Persona("Maria fernande",Generos.FEMENINO,20,"4321","Calle 2","7654321");
			Persona persona3 = new Persona("Nicolas lopez",Generos.MASCULINO,20,"1234","Calle 3","1234567");
			personaRepo.save(persona1);
			personaRepo.save(persona2);
			personaRepo.save(persona3);

			Cliente clienteFinal = new Cliente("Esteban gomez","Rivadavia 1111", "321321", "1234", true,persona1);
			Cliente clienteFinal2 = new Cliente("Maria fernandez" ,"Rivadavia 2222", "123123", "4321", true,persona2);
			Cliente clienteFinal3 = new Cliente("Nicolas lopez" ,"Rivadavia 3333", "456456", "1234", true,persona3);
			clienteRepo.save(clienteFinal);
			clienteRepo.save(clienteFinal2);
			clienteRepo.save(clienteFinal3);

			Cuenta cuenta1 = cuentaRepo.save(new Cuenta("1111", TipoCuenta.AHORRO,true,5000,clienteFinal));
			Cuenta cuenta4 = cuentaRepo.save(new Cuenta("4444",TipoCuenta.CORRIENTE,true,10000,clienteFinal));
			Cuenta cuenta2 = cuentaRepo.save(new Cuenta("2222",TipoCuenta.CORRIENTE,true,10000,clienteFinal2));
			Cuenta cuenta3 = cuentaRepo.save(new Cuenta("3333",TipoCuenta.AHORRO,true,5000,clienteFinal3));

			cuentaRepo.save(cuenta1);
			cuentaRepo.save(cuenta2);
			cuentaRepo.save(cuenta3);
			

		};
	}
}


