package mx.com.gm.capaservicios;

import java.util.List;

import mx.com.gm.capadatos.domain.Persona;

public interface PersonaService {
	
	List<Persona> listarPersonas();
	
	Persona recuperarPersona(Persona persona);
	
	void agregarPersona(Persona persona);
	
	void modificarPersona(Persona persona);

	void eliminarPersona(Persona persona);
	
}
