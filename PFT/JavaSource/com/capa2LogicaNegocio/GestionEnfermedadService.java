package com.capa2LogicaNegocio;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.capa2LogicaNegocioEntities.Enfermedad;
import com.capa2LogicaNegocioEntities.NomEnfermedad;
import com.capa2LogicaNegocioEntities.Variante;
import com.capa3Persistencia.dao.RazaPersistenciaDAO;
import com.capa3Persistencia.entities.EnfermedadPersistencia;
import com.capa3Persistencia.entities.NombresEnfermedadPersistencia;
import com.capa3Persistencia.entities.VariantePersistencia;

@Stateless
@LocalBean
public class GestionEnfermedadService implements Serializable
{	
	private static final long serialVersionUID = 1L;
	
	@EJB
	RazaPersistenciaDAO razaPersistenciaDAO;

	public Enfermedad fromEnfermedadPersistencia(EnfermedadPersistencia e)
	{
		Enfermedad enfermedad = new Enfermedad();
		enfermedad.setId(e.getIdEnfermedad().longValue());
		enfermedad.setTratamiento(e.getTratamiento());
		NomEnfermedad nomEnfermedad = new NomEnfermedad(e.getNombresEnfermedade().getIdNomenfermedad(), e.getNombresEnfermedade().getNombreEnfer());
		Variante variante = new Variante(e.getVariante().getIdVariante(), e.getVariante().getNombre());
		enfermedad.setNombre(nomEnfermedad);
		enfermedad.setVariante(variante);
		
		return enfermedad;
	}
	
	public EnfermedadPersistencia toEnfermedadPersistencia(Enfermedad e)
	{
		EnfermedadPersistencia enfermedadPersistencia = new EnfermedadPersistencia();
		enfermedadPersistencia.setIdEnfermedad(e.getId()!=null?e.getId().longValue():null);
		enfermedadPersistencia.setTratamiento(e.getTratamiento());
		NombresEnfermedadPersistencia nomEnfermedad = new NombresEnfermedadPersistencia();
		nomEnfermedad.setIdNomenfermedad(e.getNombre().getId());
		nomEnfermedad.setNombreEnfer(e.getNombre().getNombreEnfer());
		VariantePersistencia variante = new VariantePersistencia();
		variante.setIdVariante(e.getVariante().getId());
		variante.setNombre(e.getVariante().getNombre());
		enfermedadPersistencia.setVariante(variante);
		enfermedadPersistencia.setNombresEnfermedade(nomEnfermedad);
		
		return enfermedadPersistencia;
	}
}