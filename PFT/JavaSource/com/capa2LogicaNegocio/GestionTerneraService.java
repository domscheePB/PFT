package com.capa2LogicaNegocio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.capa2LogicaNegocioEntities.Ternera;
import com.capa3Persistencia.dao.TerneraPersistenciaDAO;
import com.capa3Persistencia.entities.TerneraPersistencia;

@Stateless
@LocalBean
public class GestionTerneraService implements Serializable
{	
	private static final long serialVersionUID = 1L;
	
	@EJB
	GestionRazaService gestionRazaService;
	
	@EJB
	TerneraPersistenciaDAO terneraPersistenciaDAO;

	public Ternera fromTerneraPersistencia(TerneraPersistencia t)
	{
		Ternera ternera = new Ternera();
		ternera.setId(t.getIdTernera().longValue());
		ternera.setCaravanasnis(t.getCaravanasnis());
		ternera.setCaravanatambo(t.getCaravanatambo());
		ternera.setFechaNacimiento(t.getFechanacimiento());
		ternera.setIdMadre(t.getIdMadre());
		ternera.setIdPadre(t.getIdPadre());
		ternera.setPesoAlNacer(t.getPesoalnacer());
		ternera.setRaza(gestionRazaService.fromRazaPersistencia(t.getRaza()));
		ternera.setTipoDeParto(t.getTipodeparto());
		
		return ternera;
	}
	
	public TerneraPersistencia toTerneraPersistencia(Ternera t)
	{
		TerneraPersistencia terneraPersistencia = new TerneraPersistencia();
		terneraPersistencia.setIdTernera(t.getId()!=null?t.getId().longValue():null);
		terneraPersistencia.setCaravanasnis(t.getCaravanasnis());
		terneraPersistencia.setCaravanatambo(t.getCaravanatambo());
		terneraPersistencia.setFechanacimiento(t.getFechaNacimiento());
		terneraPersistencia.setIdMadre(t.getIdMadre());
		terneraPersistencia.setIdPadre(t.getIdPadre());
		terneraPersistencia.setPesoalnacer(t.getPesoAlNacer());
		terneraPersistencia.setRaza(gestionRazaService.toRazaPersistencia(t.getRaza()));
		terneraPersistencia.setTipodeparto(t.getTipoDeParto());
		
		return terneraPersistencia;
	}
	
	public Ternera crearTernera(Ternera ternera, String nomRaza, String identificadorGuachera)
	{
		TerneraPersistencia terneraP = new TerneraPersistencia();
		
		try
		{
			terneraP = terneraPersistenciaDAO.Crear(toTerneraPersistencia(ternera), nomRaza, identificadorGuachera);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return fromTerneraPersistencia(terneraP);
	}
	
	public List<Ternera> obtenerTodas()
	{
		List<Ternera> listaTerneras = new ArrayList<Ternera>();
		
		List<TerneraPersistencia> listaAux = terneraPersistenciaDAO.obtenerTodos();
		
		for(int i = 0; i < listaAux.size(); i++)
		{
			listaTerneras.add(fromTerneraPersistencia(listaAux.get(i)));
		}
		
		return listaTerneras;
	}
	
	public Ternera actualizarTernera(Ternera ternera, String nomRaza, String identificadorGuachera)
	{
		try
		{
			terneraPersistenciaDAO.Actualizar(toTerneraPersistencia(ternera), nomRaza, identificadorGuachera);
			ternera = fromTerneraPersistencia(terneraPersistenciaDAO.obtenerTerneraID(ternera.getId()));
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return ternera;
	}
	
	public void eliminarTernera(Ternera ternera)
	{
		terneraPersistenciaDAO.eliminarTernera(toTerneraPersistencia(ternera));
	}
	
	public List<Ternera> obtenerTerneras(String caravanaSnis,
			String caravanaTambo,
			String tipoParto,
			String guachera,
			String raza,
			int idMadre,
			int idPadre,
			Date nacimientoDesde,
			Date nacimientoHasta,
			float pesoDesde, 
			float pesoHasta)
	{
		List<TerneraPersistencia> tPersistencia = terneraPersistenciaDAO.obtenerTerneras(caravanaSnis, caravanaTambo, tipoParto, guachera, raza, idMadre, idPadre, nacimientoDesde, nacimientoHasta, pesoDesde, pesoHasta);
		
		List<Ternera> listaTerneras = new ArrayList<Ternera>();
		for(int i = 0; i<tPersistencia.size(); i++)
			listaTerneras.add(fromTerneraPersistencia(tPersistencia.get(i)));
		
		return listaTerneras;
	}
	
	public Ternera obtenerTerneraID(Long id)
	{
		TerneraPersistencia ternera = terneraPersistenciaDAO.obtenerTerneraID(id);
		
		return fromTerneraPersistencia(ternera);
	}
	
	public List<String> obtenerIdentificadoresTerneras()
	{
		List<Ternera> listaAUX = obtenerTodas();
		List<String> listaTerneras = new ArrayList<String>();
		
		for(int i = 0; i<listaAUX.size(); i++)
			listaTerneras.add(listaAUX.get(i).getCaravanatambo());
		
		return listaTerneras;
	}
	
	public Ternera obtenerTerneraIdentificador(String identificador)
	{
		return fromTerneraPersistencia(terneraPersistenciaDAO.obtenerTernera(identificador));
	}
	
	public boolean caravanaTamboRepetida(String caravana)
	{
		return terneraPersistenciaDAO.idRepetido(caravana);
	}
}