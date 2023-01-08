package com.capa2LogicaNegocio;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.capa2LogicaNegocioEntities.Gravedad;
import com.capa2LogicaNegocioEntities.Padecimiento;
import com.capa2LogicaNegocioEntities.PadecimientoExcel;
import com.capa3Persistencia.dao.GravedadPersistenciaDAO;
import com.capa3Persistencia.dao.PadecimientoPersistenciaDAO;
import com.capa3Persistencia.entities.EnfermedadPersistencia;
import com.capa3Persistencia.entities.GravedadPersistencia;
import com.capa3Persistencia.entities.NombresEnfermedadPersistencia;
import com.capa3Persistencia.entities.PadecimientoPersistencia;
import com.capa3Persistencia.entities.TerneraPersistencia;
import com.capa3Persistencia.entities.VariantePersistencia;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@Stateless
@LocalBean
public class GestionPadecimientoService implements Serializable
{	
	private static final long serialVersionUID = 1L;
	
	@EJB
	GestionEnfermedadService gestionEnfermedadService;
	
	@EJB
	GestionTerneraService gestionTerneraService;
	
	@EJB
	PadecimientoPersistenciaDAO padecimientoPersistenciaDAO;
	
	@EJB
	GravedadPersistenciaDAO gravedadPersistenciaDAO;

	public Padecimiento fromPadecimientoPersistencia(PadecimientoPersistencia p)
	{
		Padecimiento padecimiento = new Padecimiento();
		padecimiento.setId(p.getIdPadecimiento().longValue());
		padecimiento.setFechaInicio(p.getFechaInicio());
		padecimiento.setFechaFinal(p.getFechaFinal());
		padecimiento.setEnfermedad(gestionEnfermedadService.fromEnfermedadPersistencia(p.getEnfermedade()));
		Gravedad gravedad = new Gravedad(p.getGravedad().getIdGravedad(), p.getGravedad().getTipoGravedad());
		padecimiento.setGravedad(gravedad);
		padecimiento.setTernera(gestionTerneraService.fromTerneraPersistencia(p.getTernera()));
		
		return padecimiento;
	}
	
	public PadecimientoPersistencia toPadecimientoPersistencia(Padecimiento p)
	{
		PadecimientoPersistencia padecimientoPersistencia = new PadecimientoPersistencia();
		padecimientoPersistencia.setIdPadecimiento(p.getId()!=null?p.getId().longValue():null);
		padecimientoPersistencia.setFechaInicio(p.getFechaInicio());
		padecimientoPersistencia.setFechaFinal(p.getFechaFinal());
		padecimientoPersistencia.setEnfermedade(gestionEnfermedadService.toEnfermedadPersistencia(p.getEnfermedad()));
		padecimientoPersistencia.setTernera(gestionTerneraService.toTerneraPersistencia(p.getTernera()));
		GravedadPersistencia gravedad = new GravedadPersistencia();
		gravedad.setIdGravedad(p.getGravedad().getId()!=null?p.getGravedad().getId().longValue():null);
		gravedad.setTipoGravedad(p.getGravedad().getTipoGravedad());
		padecimientoPersistencia.setGravedad(gravedad);
		
		return padecimientoPersistencia;
	}
	
	public List<Padecimiento> obtenerPadecimientos(String identificadorTernera,
			String nomEnfermedad,
			String variante,
			String gravedad,
			Date FechaRegistroDesde, Date FechaRegistroHasta)
	{
		List<PadecimientoPersistencia> listaAUX = padecimientoPersistenciaDAO.obtenerHistorico(identificadorTernera, nomEnfermedad, variante, gravedad, FechaRegistroDesde, FechaRegistroHasta);
		List<Padecimiento> listaPadecimientos = new ArrayList<Padecimiento>();
		for(int i = 0; i<listaAUX.size(); i++)
			listaPadecimientos.add(fromPadecimientoPersistencia(listaAUX.get(i)));
		
		return listaPadecimientos;
	}
	
	public List<String> obtenerGravedades()
	{
		List<GravedadPersistencia> listaAUX = gravedadPersistenciaDAO.obtenerTodos();
		List<String> listaGravedades = new ArrayList<String>();
		
		for(int i = 0; i<listaAUX.size(); i++)
			listaGravedades.add(listaAUX.get(i).getTipoGravedad());
		
		return listaGravedades;
	}
	
	@SuppressWarnings("deprecation")
	public List<PadecimientoExcel> crearPadecimiento(List<PadecimientoExcel> padecimientosCarga)
	{
		PadecimientoPersistencia padecimiento = new PadecimientoPersistencia();
		
		List<String> gravedades = obtenerGravedades();
		
		List<String> identificadoresTerneras = gestionTerneraService.obtenerIdentificadoresTerneras();
		
		int i = 0;
		while(i<padecimientosCarga.size())
		{
			if(!padecimientosCarga.get(i).isError() && gravedades.contains(padecimientosCarga.get(i).getGravedad()) && identificadoresTerneras.contains(padecimientosCarga.get(i).getIdentificadorTernera()))
			{
				Date fechaAUX = padecimientosCarga.get(i).getFechaRegistro();
				fechaAUX.setYear(fechaAUX.getYear()-2000);
				
				padecimiento.setFechaInicio(fechaAUX);
				
				EnfermedadPersistencia enfermedadAux = new EnfermedadPersistencia();
				NombresEnfermedadPersistencia nombreEnfAux = new NombresEnfermedadPersistencia();
				nombreEnfAux.setNombreEnfer(padecimientosCarga.get(i).getNomEnfermedad());
				VariantePersistencia varianteAux = new VariantePersistencia();
				varianteAux.setNombre(padecimientosCarga.get(i).getVariante());
				enfermedadAux.setNombresEnfermedade(nombreEnfAux);
				enfermedadAux.setVariante(varianteAux);
				padecimiento.setEnfermedade(enfermedadAux);
				
				TerneraPersistencia terneraAux = new TerneraPersistencia();
				terneraAux.setCaravanatambo(padecimientosCarga.get(i).getIdentificadorTernera());
				padecimiento.setTernera(terneraAux);
				
				GravedadPersistencia gravedadAux = new GravedadPersistencia();
				gravedadAux.setTipoGravedad(padecimientosCarga.get(i).getGravedad());
				padecimiento.setGravedad(gravedadAux);
				
				padecimientoPersistenciaDAO.Crear(padecimiento);
				padecimientosCarga.remove(i);
			}
			else
			{
				i++;
			}
		}
		
		return padecimientosCarga;
	}
	
	public List<String> obtenerErroresAlta(List<PadecimientoExcel> padecimientos)
	{
		List<String> errores = new ArrayList<String>();
		
		List<String> gravedades = obtenerGravedades();
		
		List<String> identificadoresTerneras = gestionTerneraService.obtenerIdentificadoresTerneras();
		
		for(int i = 0; i<padecimientos.size(); i++)
		{
			PadecimientoExcel padecimientoAux = padecimientos.get(i);
			String error = "El registro de la fila " + padecimientoAux.getNroRegistro() + " tiene un error en la/las columnas: \n";
			if(!identificadoresTerneras.contains(padecimientos.get(i).getIdentificadorTernera()))
			{
				error += (1 + ") La caravana tambo.\n");
			}
			
			if(padecimientoAux.getNomEnfermedad().contentEquals("") || padecimientoAux.getNomEnfermedad() == null)
			{
				error += (2 + ") El nombre de la enfermedad.\n");
			}
			
			if(padecimientoAux.getVariante().contentEquals("") || padecimientoAux.getVariante() == null)
			{
				error += (3 + ") La variante de la enfermedad.\n");
			}
			
			if(!gravedades.contains(padecimientoAux.getGravedad()))
			{
				error += (4 + ") La gravedad de la enfermedad.\n");
			}
			
			if(padecimientoAux.getFechaRegistro() == null)
			{
				error += (5 + ") La fecha de registro.\n");
			}
			
			errores.add(error);
		}
		
		return errores;
	}
	
	@SuppressWarnings("deprecation")
	public List<PadecimientoExcel> obtenerPadecimientosExcel(byte[] datos) throws IOException
	{
		try
		{
			//Convierte los datos para ser leidos.
			InputStream targetStream = new ByteArrayInputStream(datos);

			//Creación del libro desde el InputStream recibido.
			XSSFWorkbook wb = new XSSFWorkbook(targetStream);

			//Retorna la primera hoja, que es donde está la información.
			XSSFSheet primeraHoja = wb.getSheetAt(0);

			//Creamos el Iterator para las filas existentes.
			Iterator<Row> rowIterator = primeraHoja.iterator();

			//Finalizamos la lectura del libro.
			wb.close();
			
			List<PadecimientoExcel> padecimientosExcel = new ArrayList<PadecimientoExcel>();
			boolean salteadoEncabezado = false;
			
			int i = 1;
			while (rowIterator.hasNext())
			{
				DataFormatter formato = new DataFormatter();
				Row row = rowIterator.next();
				if(salteadoEncabezado)
				{
					boolean error = false;
					
					String identificadorTernera = "";
					try
					{
						identificadorTernera = row.getCell(0).getStringCellValue();
					} catch (Exception e)
					{
						error = true;
					}
					
					String nomEnfermedad = "";
					try
					{
						nomEnfermedad = row.getCell(1).getStringCellValue();
					} catch (Exception e)
					{
						error = true;
					}
					
					String variante = "";
					try
					{
						variante = row.getCell(2).getStringCellValue();
					} catch (Exception e)
					{
						error = true;
					}
					
					String gravedad = "";
					try
					{
						gravedad = row.getCell(3).getStringCellValue();
					} catch (Exception e)
					{
						error = true;
					}
					
					Date fechaRegistro = null;
					try
					{
						String text = formato.formatCellValue(row.getCell(4));
						
						fechaRegistro = new Date(text);
					} catch (Exception e)
					{
						error = true;
					}
					
					PadecimientoExcel padecimiento = new PadecimientoExcel(i, identificadorTernera, nomEnfermedad, variante, gravedad, fechaRegistro, error);
					
					padecimientosExcel.add(padecimiento);
				}
				else
				{
					salteadoEncabezado = true;
				}
				
				i++;
			}
			
			return padecimientosExcel;
			
		} catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Padecimiento> obtenerTodos()
	{
		List<PadecimientoPersistencia> listaPadecimientosPersistencia = padecimientoPersistenciaDAO.obtenerTodos();
				
		List<Padecimiento> listaPadecimiento = new ArrayList<Padecimiento>();
		
		for(int i = 0; i<listaPadecimientosPersistencia.size(); i++)
		{
			listaPadecimiento.add(fromPadecimientoPersistencia(listaPadecimientosPersistencia.get(i)));
		}
		
		return listaPadecimiento;
	}
}