package com.capa2LogicaNegocio;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.capa2LogicaNegocioEntities.HistoricoPeso;
import com.capa3Persistencia.dao.HistoricoPesoPersistenciaDAO;
import com.capa3Persistencia.entities.HistoricoTerneraPesoPersistencia;
import com.capa3Persistencia.entities.TerneraPersistencia;
import com.capa3Persistencia.entities.UsuarioPersistencia;

@Stateless
@LocalBean
public class GestionHistoricoPesoService implements Serializable
{	
	private static final long serialVersionUID = 1L;

	@EJB
	GestionTerneraService gestionTerneraService;
	
	@EJB
	GestionGuacheraService gestionGuacheraService;
	
	@EJB
	GestionAlojamientoService gestionAlojamientoService;
	
	@EJB
	GestionUsuarioService gestionUsuarioService;
	
	@EJB
	HistoricoPesoPersistenciaDAO historicoPesoDAO;
	
	public HistoricoPeso fromHistoricoPesoPersistencia(HistoricoTerneraPesoPersistencia hp)
	{
		HistoricoPeso historico = new HistoricoPeso();
		historico.setId(hp.getIdHtp().longValue());
		historico.setFecha(hp.getFecha());
		historico.setPeso(hp.getPeso());
		historico.setTernera(gestionTerneraService.fromTerneraPersistencia(hp.getTernera()));
		historico.setUsuario(gestionUsuarioService.fromUsuarioPersistencia(hp.getUsuario()));
		
		return historico;
	}
	
	public HistoricoTerneraPesoPersistencia toHistoricoPesoPersistencia(HistoricoPeso hp)
	{
		HistoricoTerneraPesoPersistencia historico = new HistoricoTerneraPesoPersistencia();
		historico.setIdHtp(hp.getId()!=null?hp.getId().longValue():null);
		historico.setPeso(hp.getPeso());
		historico.setFecha(hp.getFecha());
		historico.setTernera(gestionTerneraService.toTerneraPersistencia(hp.getTernera()));
		historico.setUsuario(gestionUsuarioService.toUsuarioPersistencia(hp.getUsuario()));
		
		return historico;
	}
	
	public HistoricoPeso crearHistoricoPeso(HistoricoPeso historico) throws Exception
	{
		HistoricoTerneraPesoPersistencia historicoP = new HistoricoTerneraPesoPersistencia();
		TerneraPersistencia ternera = new TerneraPersistencia();
		ternera.setCaravanatambo(historico.getTernera().getCaravanatambo());
		historicoP.setTernera(ternera);
		UsuarioPersistencia usuario = new UsuarioPersistencia();
		usuario.setNombreUsuario(historico.getUsuario().getNombreUsuario());
		historicoP.setUsuario(usuario);
		historicoP.setFecha(historico.getFecha());
		historicoP.setPeso(historico.getPeso());
		
		historicoP = historicoPesoDAO.Crear(historicoP);
		
		return fromHistoricoPesoPersistencia(historicoP);
	}
	
	@SuppressWarnings("deprecation")
	public List<HistoricoPeso> obtenerVariacionDePeso_T(Date fechaInicio, Date fechaFinal, String identificador)
	{
		List<HistoricoTerneraPesoPersistencia> historico = historicoPesoDAO.obtenerTodos();
		List<HistoricoPeso> listaHistorico = new ArrayList<HistoricoPeso>();
		
		List<String> listaTernerasDistintas = gestionTerneraService.obtenerIdentificadoresTerneras();
		
		int i = 0;
		
		while(i<listaTernerasDistintas.size())
		{
			String terneraAUX = listaTernerasDistintas.get(i);
			
			if(!terneraAUX.toLowerCase().contains(identificador.toLowerCase()))
			{
				listaTernerasDistintas.remove(i);
			}
			else
			{
				i++;
			}
		}
		
		i = 0;
		while(i<historico.size())
		{	
			Date fechaAUX = historico.get(i).getFecha();
			fechaAUX = new Date(fechaAUX.getYear(), fechaAUX.getMonth(), fechaAUX.getDate());
			
			if(fechaAUX.compareTo(fechaInicio) == -1)
			{
				historico.remove(i);
			}
			else if(fechaFinal!=null && fechaAUX.compareTo(fechaFinal) == 1)
			{
				historico.remove(i);
			}
			else
			{
				i++;
			}
		}
		
		for(int h = 0; h<listaTernerasDistintas.size(); h++)
		{
			String ternera = listaTernerasDistintas.get(h);
			
			List<HistoricoTerneraPesoPersistencia> historicoAUX = new ArrayList<HistoricoTerneraPesoPersistencia>();
			
			for(i = 0; i<historico.size(); i++)
			{
				if(historico.get(i).getTernera().getCaravanatambo().contentEquals(ternera))
				{
					historicoAUX.add(historico.get(i));
				}
			}
			
			if(historicoAUX.size() != 0)
			{
				float primerPeso = 0;
				float ultimoPeso = 0;
				
				if(fechaFinal==null)
				{
					fechaFinal = convertToDateViaSqlTimestamp(LocalDateTime.now());
				}
				
				Date fechaPrimerPeso = fechaFinal;
				Date fechaUltimoPeso = fechaInicio;
				
				for(int j = 0; j<historicoAUX.size(); j++)
				{
					Date fechaAUX = historicoAUX.get(j).getFecha();
					fechaAUX = new Date(fechaAUX.getYear(), fechaAUX.getMonth(), fechaAUX.getDate());
					
					if(fechaPrimerPeso.compareTo(fechaAUX) != -1)
					{
						fechaPrimerPeso = fechaAUX;
						primerPeso = historicoAUX.get(j).getPeso();
					}
					
					if(fechaUltimoPeso.compareTo(fechaAUX) != 1)
					{
						fechaUltimoPeso = fechaAUX;
						ultimoPeso = historicoAUX.get(j).getPeso();
					}
				}
				
				Date fechaAUX = historicoAUX.get(0).getTernera().getFechanacimiento();
				fechaAUX = new Date(fechaAUX.getYear(), fechaAUX.getMonth(), fechaAUX.getDate());
				
				if(fechaPrimerPeso.compareTo(fechaAUX) != -1 && fechaAUX.compareTo(fechaInicio) != -1)
				{
					primerPeso = historicoAUX.get(0).getTernera().getPesoalnacer();
				}
				
				HistoricoPeso historicoRegistro = new HistoricoPeso();
				historicoRegistro.setTernera(gestionTerneraService.fromTerneraPersistencia(historicoAUX.get(0).getTernera()));
				float variacion = Math.round((ultimoPeso - primerPeso) * 100) / 100f;
				historicoRegistro.setPeso(variacion);
				listaHistorico.add(historicoRegistro);
			}
			else
			{
				HistoricoPeso historicoRegistro = new HistoricoPeso();
				historicoRegistro.setTernera(gestionTerneraService.obtenerTerneraIdentificador(ternera));
				historicoRegistro.setPeso(0);
				listaHistorico.add(historicoRegistro);
			}
		}
		
		return listaHistorico;
	}
	
	@SuppressWarnings("deprecation")
	public List<HistoricoPeso> obtenerVariacionDePeso_G(Date fechaInicio, Date fechaFinal, String identificador_G)
	{
		List<HistoricoTerneraPesoPersistencia> historico = historicoPesoDAO.obtenerTodos();
		List<HistoricoPeso> listaHistorico = new ArrayList<HistoricoPeso>();
		
		List<String> listaTernerasDistintas = gestionTerneraService.obtenerIdentificadoresTerneras();
		
		List<String> listaGuacherasDistintas = gestionGuacheraService.obtenerListaIdentificadores();
		
		int i = 0;
		
		while(i<listaGuacherasDistintas.size())
		{
			String guacheraAUX = listaGuacherasDistintas.get(i);
			
			if(!guacheraAUX.toLowerCase().contains(identificador_G.toLowerCase()))
			{
				listaGuacherasDistintas.remove(i);
			}
			else
			{
				i++;
			}
		}
		
		i = 0;
		while(i<historico.size())
		{	
			Date fechaAUX = historico.get(i).getFecha();
			fechaAUX = new Date(fechaAUX.getYear(), fechaAUX.getMonth(), fechaAUX.getDate());
			
			if(fechaAUX.compareTo(fechaInicio) == -1)
			{
				historico.remove(i);
			}
			else if(fechaFinal!=null && fechaAUX.compareTo(fechaFinal) == 1)
			{
				historico.remove(i);
			}
			else
			{
				i++;
			}
		}
		
		for(int h = 0; h<listaTernerasDistintas.size(); h++)
		{
			String ternera = listaTernerasDistintas.get(h);
			
			String guachera = gestionAlojamientoService.obtenerIdentificadorGuachera(ternera);
			
			if(listaGuacherasDistintas.contains(guachera))
			{
				List<HistoricoTerneraPesoPersistencia> historicoAUX = new ArrayList<HistoricoTerneraPesoPersistencia>();
				
				for(i = 0; i<historico.size(); i++)
				{
					if(historico.get(i).getTernera().getCaravanatambo().contentEquals(ternera))
					{
						historicoAUX.add(historico.get(i));
					}
				}
				
				if(historicoAUX.size() != 0)
				{
					float primerPeso = 0;
					float ultimoPeso = 0;
					
					if(fechaFinal==null)
					{
						fechaFinal = convertToDateViaSqlTimestamp(LocalDateTime.now());
					}
					
					Date fechaPrimerPeso = fechaFinal;
					Date fechaUltimoPeso = fechaInicio;
					
					for(int j = 0; j<historicoAUX.size(); j++)
					{
						Date fechaAUX = historicoAUX.get(j).getFecha();
						fechaAUX = new Date(fechaAUX.getYear(), fechaAUX.getMonth(), fechaAUX.getDate());
						
						if(fechaPrimerPeso.compareTo(fechaAUX) != -1)
						{
							fechaPrimerPeso = fechaAUX;
							primerPeso = historicoAUX.get(j).getPeso();
						}
						
						if(fechaUltimoPeso.compareTo(fechaAUX) != 1)
						{
							fechaUltimoPeso = fechaAUX;
							ultimoPeso = historicoAUX.get(j).getPeso();
						}
					}
					
					Date fechaAUX = historicoAUX.get(0).getTernera().getFechanacimiento();
					fechaAUX = new Date(fechaAUX.getYear(), fechaAUX.getMonth(), fechaAUX.getDate());
					
					if(fechaPrimerPeso.compareTo(fechaAUX) != -1 && fechaAUX.compareTo(fechaInicio) != -1)
					{
						primerPeso = historicoAUX.get(0).getTernera().getPesoalnacer();
					}
					
					HistoricoPeso historicoRegistro = new HistoricoPeso();
					historicoRegistro.setTernera(gestionTerneraService.fromTerneraPersistencia(historicoAUX.get(0).getTernera()));
					float variacion = Math.round((ultimoPeso - primerPeso) * 100) / 100f;
					historicoRegistro.setPeso(variacion);
					historicoRegistro.setIdentificadorGuachera(guachera);
					listaHistorico.add(historicoRegistro);
				}
				else
				{
					HistoricoPeso historicoRegistro = new HistoricoPeso();
					historicoRegistro.setTernera(gestionTerneraService.obtenerTerneraIdentificador(ternera));
					historicoRegistro.setPeso(0);
					historicoRegistro.setIdentificadorGuachera(guachera);
					listaHistorico.add(historicoRegistro);
				}
			}
		}
		
		return listaHistorico;
	}
	
//	public List<HistoricoPeso> obtenerTodosConGuachera()
//	{
//		List<HistoricoTerneraPesoPersistencia> historicoAUX = historicoPesoDAO.obtenerTodos();
//		List<HistoricoPeso> historico = new ArrayList<HistoricoPeso>();
//		for(int i = 0; i<historicoAUX.size(); i++)
//		{
//			HistoricoPeso h = fromHistoricoPesoPersistencia(historicoAUX.get(i));
//			h.setIdentificadorGuachera(gestionAlojamientoService.obtenerIdentificadorGuachera(h.getTernera().getCaravanatambo()));
//			historico.add(h);
//		}
//		
//		return historico;
//	}
	
	public Date convertToDateViaSqlTimestamp(LocalDateTime dateToConvert)
    {
        return java.sql.Timestamp.valueOf(dateToConvert);
    }
}