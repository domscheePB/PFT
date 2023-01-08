package com.capa3Persistencia.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the HISTORICO_ALIMENTOS database table.
 * 
 */
@Entity
@Table(name="HISTORICO_ALIMENTOS")
public class HistoricoAlimentoPersistencia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column(name="ID_HISTORICO_ALIM", unique=true, nullable=false, precision=19)
	private Long idHistoricoAlim;

	@Column(name="COSTO_ALIM", nullable=false, precision=126)
	private float costoAlim;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_ALIM", nullable=false)
	private Date fechaAlim;

	//bi-directional many-to-one association to Alimento
	@ManyToOne
	@JoinColumn(name="ID_ALIMENTO", nullable=false)
	private AlimentoPersistencia alimento;

	//bi-directional many-to-one association to Ternera
	@ManyToOne
	@JoinColumn(name="ID_TERNERA", nullable=false)
	private TerneraPersistencia ternera;

	public HistoricoAlimentoPersistencia() {
	}

	public Long getIdHistoricoAlim() {
		return this.idHistoricoAlim;
	}

	public void setIdHistoricoAlim(Long idHistoricoAlim) {
		this.idHistoricoAlim = idHistoricoAlim;
	}

	public float getCostoAlim() {
		return this.costoAlim;
	}

	public void setCostoAlim(float costoAlim) {
		this.costoAlim = costoAlim;
	}

	public Date getFechaAlim() {
		return this.fechaAlim;
	}

	public void setFechaAlim(Date fechaAlim) {
		this.fechaAlim = fechaAlim;
	}

	public AlimentoPersistencia getAlimento() {
		return this.alimento;
	}

	public void setAlimento(AlimentoPersistencia alimento) {
		this.alimento = alimento;
	}

	public TerneraPersistencia getTernera() {
		return this.ternera;
	}

	public void setTernera(TerneraPersistencia ternera) {
		this.ternera = ternera;
	}

}