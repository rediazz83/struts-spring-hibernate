package mx.com.gm.capadatos.dao.impl;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import mx.com.gm.capadatos.dao.PersonaDao;
import mx.com.gm.capadatos.domain.Persona;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:datasource-test.xml", "classpath:spring-context.xml" })
public class PersonaDaoImplTest {

	private static Log logger = LogFactory.getLog(PersonaDaoImplTest.class);

	@Autowired
	private PersonaDao personaDao;

	@Test
	@Transactional
	public void deberiaMostrarPersonas() {
		System.out.println();
		try {
			logger.info("Inicio del test deberiaMostrarPersonas");

			List<Persona> personas = personaDao.findAllPersonas();

			int contadorPersonas = 0;
			for (Persona persona : personas) {
				logger.info("Persona: " + persona);
				contadorPersonas++;
			}

			Assert.assertEquals(contadorPersonas, personaDao.contadorPersonas());

			logger.info("Fin del test deberiaMostrarPersonas");
		} catch (Exception e) {
			logger.error("Error JBDC", e);
		}
	}

	@Test
	@Transactional
	public void deberiaEncontrarPersonaPorId() {
		System.out.println();
		try {
			logger.info("Inicio	del	test deberiaEncontrarPersonaPorId");

			int idPersona = 1;
			Persona persona = personaDao.findPersonaById(idPersona);

			Assert.assertEquals("Admin", persona.getNombre());

			logger.info("Persona recuperada (id=" + idPersona + "): " + persona);

			logger.info("Fin del test deberiaEncontrarPersonaPorId");
		} catch (Exception e) {
			logger.error("Error JBDC", e);
		}
	}

	@Test
	@Transactional
	public void deberiaInsertarPersona() {
		System.out.println();
		try {			
			logger.info("Inicio del test deberiaInsertarPersona");
			
			Assert.assertEquals(3, personaDao.contadorPersonas());
			
			Persona persona = new Persona();
			persona.setNombre("Carlos");
			persona.setApePaterno("Romero");
			persona.setApeMaterno("Esparza");
			persona.setEmail("carlos.romero@gmail.com");
			
			personaDao.createPersona(persona);
			
			persona = personaDao.getPersonaByEmail(persona);
			
			logger.info("Persona insertada: " + persona);
			
			Assert.assertEquals(4, personaDao.contadorPersonas());
			
			logger.info("Fin del test deberiaInsertarPersona");
		} catch (Exception e) {
			logger.error("Error JBDC", e);
		}
	}
	
	@Test
	@Transactional
	public void deberiaActualizarPersonaTest() {
		System.out.println();
		try {
			logger.info("Inicio test deberiaActualizarPersonaTest");

			int idPersona = 1;
			Persona persona = personaDao.findPersonaById(idPersona);

			logger.info("Persona a modificar (id='" + idPersona + "'): ".concat(persona.toString()));

			persona.setNombre("Administrador");
			persona.setApePaterno("Sistemas");
			personaDao.updatePersona(persona);
			
			persona = personaDao.findPersonaById(idPersona);
			
			logger.info("Persona a modificada (id='" + idPersona + "'): ".concat(persona.toString()));
			
			assertEquals("Nombre no coincide.", "Administrador", persona.getNombre());

			logger.info("Fin test deberiaActualizarPersonaTest");
		} catch (Exception e) {
			logger.error("Error JDBC", e);
		}
	}
	
	@Test
	@Transactional
	public void deberiaEliminarPersonaTest() {
		System.out.println();
		try {
			logger.info("Inicio test deberiaEliminarPersonaTest");

			int idPersona = 2;
			Persona persona = personaDao.findPersonaById(idPersona);

			logger.info("Persona a eliminar (id='" + idPersona + "'): ".concat(persona.toString()));

			persona.setIdPersona(idPersona);
			personaDao.deletePersona(persona);
			
			persona = personaDao.findPersonaById(idPersona);
			
			assertNull("No deben encontrarse coincidencias", persona);
			
			List<Persona> personas = personaDao.findAllPersonas();

			int contadorPersonas = 0;
			for (Persona alguien : personas) {
				logger.info(alguien);
				contadorPersonas++;
			}

			assertEquals("Cantidad de personas no coincide con el esperado.", contadorPersonas,
					personaDao.contadorPersonas());

			logger.info("Fin test deberiaEliminarPersonaTest");
		} catch (Exception e) {
			logger.error("Error JDBC", e);
		}
	}

}
