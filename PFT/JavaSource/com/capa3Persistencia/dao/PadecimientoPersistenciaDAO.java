package com.capa3Persistencia.dao;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.capa3Persistencia.entities.PadecimientoPersistencia;
/**
 * Session Bean implementation class UsuariosEJBBean
 */
@Stateless
@LocalBean
public class PadecimientoPersistenciaDAO
{
	@PersistenceContext
	private EntityManager em;
	
	@EJB
	GravedadPersistenciaDAO gravedadPersistenciaDAO;
	
	@EJB
	TerneraPersistenciaDAO terneraPersistenciaDAO;
	
	@EJB
	EnfermedadPersistenciaDAO enfermedadPersistenciaDAO;
	
	public PadecimientoPersistenciaDAO()
	{
		super();
	}
	
	@SuppressWarnings("deprecation")
	public void Crear(PadecimientoPersistencia padecimiento)
	{
		try
		{
			PadecimientoPersistencia padecimientoAUX = new PadecimientoPersistencia();
						
			padecimientoAUX.setFechaInicio(padecimiento.getFechaInicio());
			
			Date fecha = padecimientoAUX.getFechaInicio();
			fecha.setYear(fecha.getYear() + 2000);
			
			padecimientoAUX.setFechaInicio(fecha);
			
			padecimientoAUX.setGravedad(gravedadPersistenciaDAO.obtener(padecimiento.getGravedad().getTipoGravedad()));
			
			padecimientoAUX.setTernera(terneraPersistenciaDAO.obtenerTernera(padecimiento.getTernera().getCaravanatambo()));
			
			padecimientoAUX.setEnfermedade(enfermedadPersistenciaDAO.obtener(padecimiento.getEnfermedade().getNombresEnfermedade().getNombreEnfer(), padecimiento.getEnfermedade().getVariante().getNombre()));
			
			em.persist(padecimientoAUX);
			
			em.flush();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public List<PadecimientoPersistencia> obtenerTodos()
	{
		TypedQuery<PadecimientoPersistencia> query = em.createQuery("SELECT p FROM PadecimientoPersistencia p", PadecimientoPersistencia.class);
		List<PadecimientoPersistencia> padecimientos = query.getResultList();
		
		return padecimientos;
	}
	
	public List<PadecimientoPersistencia> obtenerHistorico(String identificadorTernera,
			String nomEnfermedad,
			String variante,
			String gravedad,
			Date FechaRegistroDesde, Date FechaRegistroHasta)
	{
		List<PadecimientoPersistencia> listaPadecimiento = obtenerTodos();
		
		int i = 0;
		
		while(i<listaPadecimiento.size())
		{
			Date fechaAUX = listaPadecimiento.get(i).getFechaInicio();
			if((identificadorTernera!=null && !identificadorTernera.contentEquals("")) && !listaPadecimiento.get(i).getTernera().getCaravanatambo().toLowerCase().contains(identificadorTernera.toLowerCase()))
			{
				listaPadecimiento.remove(i);
			}
			else if((nomEnfermedad!=null && !nomEnfermedad.contentEquals("")) && !listaPadecimiento.get(i).getEnfermedade().getNombresEnfermedade().getNombreEnfer().toLowerCase().contains(nomEnfermedad.toLowerCase()))
			{
				listaPadecimiento.remove(i);
			}
			else if((variante!=null && !variante.contentEquals("")) && !listaPadecimiento.get(i).getEnfermedade().getVariante().getNombre().toLowerCase().contains(variante.toLowerCase()))
			{
				listaPadecimiento.remove(i);
			}
			else if((gravedad!=null && !gravedad.contentEquals("")) && !gravedad.equals(listaPadecimiento.get(i).getGravedad().getTipoGravedad()))
			{
				listaPadecimiento.remove(i);
			}
			else if(FechaRegistroDesde != null && fechaAUX.before(FechaRegistroDesde))
			{
				listaPadecimiento.remove(i);
			}
			else if(FechaRegistroHasta != null && fechaAUX.after(FechaRegistroHasta))
			{
				listaPadecimiento.remove(i);
			}
			else
			{
				i++;
			}
		}
		
		return listaPadecimiento;
	}
}
