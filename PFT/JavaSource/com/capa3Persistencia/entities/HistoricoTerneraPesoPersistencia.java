package com.capa3Persistencia.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the HISTORICO_TERNERA_PESO database table.
 * 
 */
@Entity
@Table(name="HISTORICO_TERNERA_PESO")
public class HistoricoTerneraPesoPersistencia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column(name="ID_HTP", unique=true, nullable=false, precision=19)
	private Long idHtp;

	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	private Date fecha;

	@Column(nullable=false, precision=126)
	private float peso;

	//bi-directional many-to-one association to Ternera
	@ManyToOne
	@JoinColumn(name="ID_TERNERA", nullable=false)
	private TerneraPersistencia ternera;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="ID_USUARIO")
	private UsuarioPersistencia usuario;

	public HistoricoTerneraPesoPersistencia() {
	}

	public Long getIdHtp() {
		return this.idHtp;
	}

	public void setIdHtp(Long idHtp) {
		this.idHtp = idHtp;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public float getPeso() {
		return this.peso;
	}

	public void setPeso(float peso) {
		this.peso = peso;
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