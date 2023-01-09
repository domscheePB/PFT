package com.ws.rest.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.capa2LogicaNegocio.GestionPadecimientoService;
import com.capa2LogicaNegocioEntities.Padecimiento;
import com.ws.rest.entities.HEnfermedadRestEntidad;

@Stateless
@LocalBean
public class HEnfermedadBean implements Serializable
{	
	private static final long serialVersionUID = 1L;
	
	@EJB
	GestionPadecimientoService gestionPadecimientoService;
	
	public List<HEnfermedadRestEntidad> obtenerHistorico()
	{
		List<Padecimiento> listaAUX = gestionPadecimientoService.obtenerTodos();
		
		List<HEnfermedadRestEntidad> lista = convertirLista(listaAUX);
		
		return lista;
	}
	
	public List<HEnfermedadRestEntidad> convertirLista(List<Padecimiento> padecimientos)
	{
		List<HEnfermedadRestEntidad> lista = new ArrayList<HEnfermedadRestEntidad>();
		for(int i = 0; i<padecimientos.size(); i++)
		{
			lista.add(toPadecimientoRest(padecimientos.get(i)));
		}
		
		return lista;
	}
	
	public HEnfermedadRestEntidad toPadecimientoRest(Padecimiento padecimiento)
	{
		HEnfermedadRestEntidad historico = new HEnfermedadRestEntidad();
		historico.setId(padecimiento.getId());
		historico.setIdentificadorTernera(padecimiento.getTernera().getCaravanatambo());
		historico.setVariante(padecimiento.getEnfermedad().getVariante().getNombre());
		historico.setFechaRegistro(padecimiento.getFechaInicio());
		historico.setGravedad(padecimiento.getGravedad().getTipoGravedad());
		historico.setEnfermedad(padecimiento.getEnfermedad().getNombre().getNombreEnfer());
		
		return historico;
	}
}