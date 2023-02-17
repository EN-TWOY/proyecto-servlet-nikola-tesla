package beans;

public class ContratoDTO {

	private int codContrato, codAdministrador, codProfesor;
	private double sueContrato;
	private String fecContrato, nomAdministrador, nomProfesor;

	public int getCodContrato() {
		return codContrato;
	}

	public void setCodContrato(int codContrato) {
		this.codContrato = codContrato;
	}

	public int getCodAdministrador() {
		return codAdministrador;
	}

	public void setCodAdministrador(int codAdministrador) {
		this.codAdministrador = codAdministrador;
	}

	public int getCodProfesor() {
		return codProfesor;
	}

	public void setCodProfesor(int codProfesor) {
		this.codProfesor = codProfesor;
	}

	public double getSueContrato() {
		return sueContrato;
	}

	public void setSueContrato(double sueContrato) {
		this.sueContrato = sueContrato;
	}

	public String getFecContrato() {
		return fecContrato;
	}

	public void setFecContrato(String fecContrato) {
		this.fecContrato = fecContrato;
	}

	public String getNomAdministrador() {
		return nomAdministrador;
	}

	public void setNomAdministrador(String nomAdministrador) {
		this.nomAdministrador = nomAdministrador;
	}

	public String getNomProfesor() {
		return nomProfesor;
	}

	public void setNomProfesor(String nomProfesor) {
		this.nomProfesor = nomProfesor;
	}

}
