package com.capa2LogicaNegocio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.capa2LogicaNegocioEntities.Alimento;
import com.capa2LogicaNegocioEntities.HistoricoAlimento;
import com.capa3Persistencia.dao.HistoricoAlimentoPersistenciaDAO;
import com.capa3Persistencia.entities.AlimentoPersistencia;
import com.capa3Persistencia.entities.HistoricoAlimentoPersistencia;

@Stateless
@LocalBean
public class GestionHistoricoAlimentoService implements Serializable
{	
	private static final long serialVersionUID = 1L;
	
	@EJB
	GestionTerneraService gestionTerneraService;
	
	@EJB
	GestionAlojamientoService gestionAlojamientoService;
	
	@EJB
	HistoricoAlimentoPersistenciaDAO historicoAlimentoDAO;

	public HistoricoAlimento fromHistoricoAlimentoPersistencia(HistoricoAlimentoPersistencia ha)
	{
		HistoricoAlimento historico = new HistoricoAlimento();
		historico.setId(ha.getIdHistoricoAlim());
		Alimento alimento = new Alimento(ha.getAlimento().getIdAlimento(), ha.getAlimento().getCantidad(), ha.getAlimento().getCosto());
		historico.setAlimento(alimento);
		historico.setCostoAlim(ha.getCostoAlim());
		historico.setFechaAlim(ha.getFechaAlim());
		historico.setTernera(gestionTerneraService.fromTerneraPersistencia(ha.getTernera()));
		historico.setGuachera(gestionAlojamientoService.obtenerIdentificadorGuachera(ha.getTernera().getCaravanatambo()));
		
		return historico;
	}
	
	public HistoricoAlimentoPersistencia toHistoricoAlimentoPersistencia(HistoricoAlimento ha)
	{
		HistoricoAlimentoPersistencia historico = new HistoricoAlimentoPersistencia();
		historico.setIdHistoricoAlim(ha.getId()!=null?ha.getId().longValue():null);
		AlimentoPersistencia alimento = new AlimentoPersistencia();
		alimento.setIdAlimento(ha.getAlimento().getId());
		alimento.setCosto(ha.getAlimento().getCosto());
		alimento.setCantidad(ha.getAlimento().getCantidad());
		historico.setAlimento(alimento);
		historico.setCostoAlim(ha.getCostoAlim());
		historico.setFechaAlim(ha.getFechaAlim());
		historico.setTernera(gestionTerneraService.toTerneraPersistencia(ha.getTernera()));
		
		return historico;
	}
	
	@SuppressWarnings("deprecation")
	public List<HistoricoAlimento> obtenerCostosAlimentoTernera(Date fechaInicio, Date fechaFin)
	{
		List<HistoricoAlimentoPersistencia> listaAUX = historicoAlimentoDAO.obtenerTodos();
		
		int i = 0;
		while(i<listaAUX.size())
		{
			Date fechaAUX = listaAUX.get(i).getFechaAlim();
			fechaAUX = new Date(fechaAUX.getYear(), fechaAUX.getMonth(), fechaAUX.getDate());
			
			if(fechaAUX.compareTo(fechaInicio) == -1)
			{
				listaAUX.remove(i);
			}
			else if(fechaFin!=null && fechaAUX.compareTo(fechaFin) == 1)
			{
				listaAUX.remove(i);
			}
			else
			{
				i++;
			}
		}
		
		List<HistoricoAlimento> listaHA = new ArrayList<HistoricoAlimento>();
		for(int j = 0; j<listaAUX.size(); j++)
			listaHA.add(fromHistoricoAlimentoPersistencia(listaAUX.get(j)));
		
		return listaHA;
	}
}