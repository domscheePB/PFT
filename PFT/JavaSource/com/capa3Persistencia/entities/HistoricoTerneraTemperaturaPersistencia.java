package com.capa3Persistencia.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the HISTORICO_TERNERA_TEMPERATURA database table.
 * 
 */
@Entity
@Table(name="HISTORICO_TERNERA_TEMPERATURA")
public class HistoricoTerneraTemperaturaPersistencia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column(name="ID_HTT", unique=true, nullable=false, precision=19)
	private Long idHtt;

	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	private Date fecha;

	@Column(nullable=false, precision=126)
	private float temperatura;

	//bi-directional many-to-one association to Ternera
	@ManyToOne
	@JoinColumn(name="ID_TERNERA", nullable=false)
	private TerneraPersistencia ternera;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="ID_USUARIO")
	private UsuarioPersistencia usuario;

	public HistoricoTerneraTemperaturaPersistencia() {
	}

	public Long getIdHtt() {
		return this.idHtt;
	}

	public void setIdHtt(Long idHtt) {
		this.idHtt = idHtt;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public float getTemperatura() {
		return this.temperatura;
	}

	public void setTemperatura(float temperatura) {
		this.temperatura = temperatura;
	}

	public TerneraPersistencia getTernera() {
		return this.ternera;
	}

	public void setTernera(TerneraPersistencia ternera) {
		this.ternera = ternera;
	}

	public UsuarioPersistencia getUsuario() {
		return this.usuario;
	}

	public void setUsuario(UsuarioPersistencia usuario) {
		this.usuario = usuario;
	}
}