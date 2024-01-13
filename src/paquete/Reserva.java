package paquete;

public class Reserva {
	
	private String nombre = null;
	private String telefono = null;
	private String fechaEvento = null;
	private String tipo = null;
	private String asistentes = null;
	private String tipoCocina = null;
	
	private String tipoMesa = null;
	private String comensalesMesa = null;
	
	private String numeroJornadas = null;
	private String requiereHabitaciones = null;
	
	public Reserva(String nombre, String telefono, String fechaEvento, String tipo, String asistentes,
			String tipoCocina, String tipoMesa, String comensalesMesa, String numeroJornadas,
			String requiereHabitaciones) {
		
		this.nombre = nombre;
		this.telefono = telefono;
		this.fechaEvento = fechaEvento;
		this.tipo = tipo;
		this.asistentes = asistentes;
		this.tipoCocina = tipoCocina;
		this.tipoMesa = tipoMesa;
		this.comensalesMesa = comensalesMesa;
		this.numeroJornadas = numeroJornadas;
		this.requiereHabitaciones = requiereHabitaciones;
		
		if (tipo != null) {
			filtrarReserva();
		}
	}
	
	private void filtrarReserva() {
		if (tipo.equals("Congreso")) {
			tipoMesa = null;
			comensalesMesa = null;
		} else if (tipo.equals("Banquete")) {
			numeroJornadas = null;
			requiereHabitaciones = null;
		} else {
			tipoMesa = null;
			comensalesMesa = null;
			numeroJornadas = null;
			requiereHabitaciones = null;
		}
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getFechaEvento() {
		return fechaEvento;
	}

	public void setFechaEvento(String fechaEvento) {
		this.fechaEvento = fechaEvento;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getAsistentes() {
		return asistentes;
	}

	public void setAsistentes(String asistentes) {
		this.asistentes = asistentes;
	}

	public String getTipoCocina() {
		return tipoCocina;
	}

	public void setTipoCocina(String tipoCocina) {
		this.tipoCocina = tipoCocina;
	}

	public String getTipoMesa() {
		return tipoMesa;
	}

	public void setTipoMesa(String tipoMesa) {
		this.tipoMesa = tipoMesa;
	}

	public String getComensalesMesa() {
		return comensalesMesa;
	}

	public void setComensalesMesa(String comensalesMesa) {
		this.comensalesMesa = comensalesMesa;
	}

	public String getNumeroJornadas() {
		return numeroJornadas;
	}

	public void setNumeroJornadas(String numeroJornadas) {
		this.numeroJornadas = numeroJornadas;
	}

	public String getRequiereHabitaciones() {
		return requiereHabitaciones;
	}

	public void setRequiereHabitaciones(String requiereHabitaciones) {
		this.requiereHabitaciones = requiereHabitaciones;
	}
}
