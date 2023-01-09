package com.capa2LogicaNegocio;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.capa2LogicaNegocioEntities.HistoricoTemperatura;
import com.capa3Persistencia.dao.HistoricoTemperaturaPersistenciaDAO;
import com.capa3Persistencia.entities.HistoricoTerneraTemperaturaPersistencia;
import com.capa3Persistencia.entities.TerneraPersistencia;
import com.capa3Persistencia.entities.UsuarioPersistencia;

@Stateless
@LocalBean
public class GestionHistoricoTemperaturaService implements Serializable
{	
	private static final long serialVersionUID = 1L;

	@EJB
	GestionTerneraService gestionTerneraService;
	
	@EJB
	GestionUsuarioService gestionUsuarioService;
	
	@EJB
	HistoricoTemperaturaPersistenciaDAO historicoTemperaturaDAO;
	
	public HistoricoTemperatura fromHistoricoTemperaturaPersistencia(HistoricoTerneraTemperaturaPersistencia ht)
	{
		HistoricoTemperatura historico = new HistoricoTemperatura();
		historico.setId(ht.getIdHtt().longValue());
		historico.setFecha(ht.getFecha());
		historico.setTemperatura(ht.getTemperatura());;
		historico.setTernera(gestionTerneraService.fromTerneraPersistencia(ht.getTernera()));
		historico.setUsuario(gestionUsuarioService.fromUsuarioPersistencia(ht.getUsuario()));
		
		return historico;
	}
	
	public HistoricoTerneraTemperaturaPersistencia toHistoricoTemperaturaPersistencia(HistoricoTemperatura ht)
	{
		HistoricoTerneraTemperaturaPersistencia historico = new HistoricoTerneraTemperaturaPersistencia();
		historico.setIdHtt(ht.getId()!=null?ht.getId().longValue():null);
		historico.setTemperatura(ht.getTemperatura());
		historico.setFecha(ht.getFecha());
		historico.setTernera(gestionTerneraService.toTerneraPersistencia(ht.getTernera()));
		historico.setUsuario(gestionUsuarioService.toUsuarioPersistencia(ht.getUsuario()));
		
		return historico;
	}
	
	public HistoricoTemperatura crearHistoricoTemperatura(HistoricoTemperatura historico) throws Exception
	{
		HistoricoTerneraTemperaturaPersistencia historicoT = new HistoricoTerneraTemperaturaPersistencia();
		TerneraPersistencia ternera = new TerneraPersistencia();
		ternera.setCaravanatambo(historico.getTernera().getCaravanatambo());
		historicoT.setTernera(ternera);
		UsuarioPersistencia usuario = new UsuarioPersistencia();
		usuario.setNombreUsuario(historico.getUsuario().getNombreUsuario());
		historicoT.setUsuario(usuario);
		historicoT.setFecha(historico.getFecha());
		historicoT.setTemperatura(historico.getTemperatura());
		
		historicoT = historicoTemperaturaDAO.Crear(historicoT);
		
		return fromHistoricoTemperaturaPersistencia(historicoT);
	}
}