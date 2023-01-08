package com.capa3Persistencia.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the TERNERAS database table.
 * 
 */
@Entity
@Table(name="TERNERAS")
public class TerneraPersistencia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column(name="ID_TERNERA", unique=true, nullable=false, precision=19)
	private Long idTernera;

	@Column(length=50)
	private String caravanasnis;

	@Column(nullable=false, length=50)
	private String caravanatambo;

	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	private Date fechanacimiento;

	@Column(name="ID_MADRE", nullable=false, precision=10)
	private int idMadre;

	@Column(name="ID_PADRE", nullable=false, precision=10)
	private int idPadre;

	@Column(precision=126)
	private float pesoalnacer;

	@Column(nullable=false, length=50)
	private String tipodeparto;

	//bi-directional many-to-one association to Raza
	@ManyToOne
	@JoinColumn(name="ID_RAZA", nullable=false)
	private RazaPersistencia raza;

	public TerneraPersistencia() {
	}

	public Long getIdTernera() {
		return this.idTernera;
	}

	public void setIdTernera(Long idTernera) {
		this.idTernera = idTernera;
	}

	public String getCaravanasnis() {
		return this.caravanasnis;
	}

	public void setCaravanasnis(String caravanasnis) {
		this.caravanasnis = caravanasnis;
	}

	public String getCaravanatambo() {
		return this.caravanatambo;
	}

	public void setCaravanatambo(String caravanatambo) {
		this.caravanatambo = caravanatambo;
	}

	public Date getFechanacimiento() {
		return this.fechanacimiento;
	}

	public void setFechanacimiento(Date fechanacimiento) {
		this.fechanacimiento = fechanacimiento;
	}

	public int getIdMadre() {
		return this.idMadre;
	}

	public void setIdMadre(int idMadre) {
		this.idMadre = idMadre;
	}

	public int getIdPadre() {
		return this.idPadre;
	}

	public void setIdPadre(int idPadre) {
		this.idPadre = idPadre;
	}

	public float getPesoalnacer() {
		return this.pesoalnacer;
	}

	public void setPesoalnacer(float pesoalnacer) {
		this.pesoalnacer = pesoalnacer;
	}

	public String getTipodeparto() {
		return this.tipodeparto;
	}

	public void setTipodeparto(String tipodeparto) {
		this.tipodeparto = tipodeparto;
	}

	public RazaPersistencia getRaza() {
		return this.raza;
	}

	public void setRaza(RazaPersistencia raza) {
		this.raza = raza;
	}

}