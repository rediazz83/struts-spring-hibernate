package mx.com.gm.capaservicios.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import mx.com.gm.capadatos.dao.PersonaDao;
import mx.com.gm.capadatos.domain.Persona;
import mx.com.gm.capaservicios.PersonaService;

@Service("personaService")
@Transactional
public class PersonaServiceImpl implements PersonaService {

	@Autowired
	private PersonaDao personaDao;

	@Override
	public List<Persona> listarPersonas() {
		return this.personaDao.findAllPersonas();
	}

	@Override
	public Persona recuperarPersona(Persona persona) {
		return this.personaDao.findPersonaById(persona.getIdPersona());
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void agregarPersona(Persona persona) {
		personaDao.createPersona(persona);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void modificarPersona(Persona persona) {
		personaDao.updatePersona(persona);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void eliminarPersona(Persona persona) {
		personaDao.deletePersona(persona);
	}

}
