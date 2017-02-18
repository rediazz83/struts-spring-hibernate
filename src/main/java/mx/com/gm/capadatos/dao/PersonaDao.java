package mx.com.gm.capadatos.dao;

import java.util.List;

import mx.com.gm.capadatos.domain.Persona;

public interface PersonaDao {
	
	void updatePersona(Persona persona);

	void deletePersona(Persona persona);

	Persona findPersonaById(long idPersona);
	
	List<Persona> findAllPersonas();

	long contadorPersonas();

	Persona getPersonaByEmail(Persona persona);
	
	void createPersona(Persona persona);
	
}
