package mx.com.gm.capadatos.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mx.com.gm.capadatos.dao.PersonaDao;
import mx.com.gm.capadatos.domain.Persona;

@Repository
public class PersonaDaoImpl implements PersonaDao {
	
	private SessionFactory sessionFactory;

	@Autowired
	public PersonaDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	private Session currentSession() {
		return sessionFactory.getCurrentSession();
	}

	public void updatePersona(Persona persona) {
		currentSession().update(persona);
	}

	public void deletePersona(Persona persona) {
		currentSession().delete(persona);
	}

	public Persona findPersonaById(long idPersona) {
		return (Persona) currentSession().get(Persona.class, idPersona);
	}

	@SuppressWarnings("unchecked")
	public List<Persona> findAllPersonas() {
		return currentSession().createQuery("from Persona").list();
	}

	public long contadorPersonas() {
		Long contador = (Long) currentSession().createCriteria(Persona.class).setProjection(Projections.rowCount())
				.uniqueResult();
		return contador.longValue();
	}

	public Persona getPersonaByEmail(Persona persona) {
		Example personaEjemplo = Example.create(persona);
		return (Persona) currentSession().createCriteria(Persona.class).add(personaEjemplo).uniqueResult();
	}

	@Override
	public void createPersona(Persona persona) {
		currentSession().saveOrUpdate(persona);
	}

}
