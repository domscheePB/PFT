package com.bean;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Named;

import com.capa2LogicaNegocio.GestionPadecimientoService;
import com.capa2LogicaNegocioEntities.PadecimientoExcel;

import org.primefaces.model.file.UploadedFile;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named(value="altaHistoricoEnfermedad")
@SessionScoped
public class GestionHistoricoEnfermedadBean implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@EJB
	GestionPadecimientoService gestionPadecimientoService;
	
	private List<PadecimientoExcel> padecimientosExcel = new ArrayList<PadecimientoExcel>();
	
	private UploadedFile archivo;
	
	public GestionHistoricoEnfermedadBean()
	{
		super();
	}
	
	@PostConstruct
	public void init()
	{
		
	}
	
	public void upload() throws IOException
	{
        if (archivo.getFileName() != null)
        {
        	byte[] datos = archivo.getContent();
			
        	padecimientosExcel = gestionPadecimientoService.obtenerPadecimientosExcel(datos);
        	
        	for(int i = 0; i<padecimientosExcel.size(); i++)
        		System.out.println(padecimientosExcel.get(i).toString());
        }
        else
        {
        	FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debe cargar un archivo para continuar.", "");
			FacesContext.getCurrentInstance().addMessage(null, mensaje);
        }
    }
	
	@SuppressWarnings("deprecation")
	public String fechaFormateada(Date fecha)
	{
		if(fecha!=null)
		{
			String fechaFormateada = fecha.getDate() + "/" + (fecha.getMonth() + 1) + "/" + (fecha.getYear() + 1900);
			
			return fechaFormateada;
		}
		
		return "Fecha Incorrecta";
	}
	
	public void cargarHistorico()
	{
		int registrosCorrectos = padecimientosExcel.size();
		
		padecimientosExcel = gestionPadecimientoService.crearPadecimiento(padecimientosExcel);
		
		registrosCorrectos -= padecimientosExcel.size();
		
		if(registrosCorrectos > 0)
		{
			FacesMessage mensajeCorrectos = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se han agregado " + registrosCorrectos + " registros correctamente.", "");
			FacesContext.getCurrentInstance().addMessage(null, mensajeCorrectos);
		}
		
		if(!(padecimientosExcel.size() == 0))
		{
			List<String> errores = gestionPadecimientoService.obtenerErroresAlta(padecimientosExcel);
			
			String mensajeAux =  "No se ha podido agregar " + padecimientosExcel.size() + " registros.";
			FacesMessage mensajeIncorrectos = new FacesMessage(FacesMessage.SEVERITY_ERROR, mensajeAux, "");
			FacesContext.getCurrentInstance().addMessage(null, mensajeIncorrectos);
			
			for(int i = 0; i<errores.size(); i++)
			{
				FacesMessage mensajeErrores = new FacesMessage(FacesMessage.SEVERITY_ERROR, errores.get(i), "");
				FacesContext.getCurrentInstance().addMessage(null, mensajeErrores);
			}
			
		}
	}

	public UploadedFile getArchivo() {
		return archivo;
	}

	public void setArchivo(UploadedFile archivo) {
		this.archivo = archivo;
	}

	public List<PadecimientoExcel> getPadecimientosExcel() {
		return padecimientosExcel;
	}

	public void setPadecimientosExcel(List<PadecimientoExcel> padecimientosExcel) {
		this.padecimientosExcel = padecimientosExcel;
	}
}