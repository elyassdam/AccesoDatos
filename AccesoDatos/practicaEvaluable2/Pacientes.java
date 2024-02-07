package practicaEvaluable2;
import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;

import java.util.*;


public class Pacientes {

	    private int idPaciente;
	    private String nombre;
	    private String apellido;
	    private Date fechaNacimiento;
	    private String direccion;
	    private String telefono;
	    private Set<Consultas> consultas;
	    private List<Recetas> recetas;

	    public Pacientes(int idPaciente, String nombre, String apellido, Date fechaNacimiento, String direccion, String telefono) {
	        this.idPaciente = idPaciente;
	        this.nombre = nombre;
	        this.apellido = apellido;
	        this.fechaNacimiento = fechaNacimiento;
	        this.direccion = direccion;
	        this.telefono = telefono;
	        this.consultas = new HashSet<>();
	        this.recetas = new ArrayList<>();
	    }

	    // Métodos para agregar y obtener consultas
	    public void agregarConsulta(Consultas consulta) {
	        consultas.add(consulta);
	    }

	    public Set<Consultas> getConsultas() {
	        return consultas;
	    }

	    // Métodos para agregar y obtener recetas
	    public void agregarReceta(Recetas receta) {
	        recetas.add(receta);
	    }

	    public List<Recetas> getRecetas() {
	        return recetas;
	    }

		public int getIdPaciente() {
			return idPaciente;
		}

		public void setIdPaciente(int idPaciente) {
			this.idPaciente = idPaciente;
		}

		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}

		public String getApellido() {
			return apellido;
		}

		public void setApellido(String apellido) {
			this.apellido = apellido;
		}

		public Date getFechaNacimiento() {
			return fechaNacimiento;
		}

		public void setFechaNacimiento(Date fechaNacimiento) {
			this.fechaNacimiento = fechaNacimiento;
		}

		public String getDireccion() {
			return direccion;
		}

		public void setDireccion(String direccion) {
			this.direccion = direccion;
		}

		public String getTelefono() {
			return telefono;
		}

		public void setTelefono(String telefono) {
			this.telefono = telefono;
		}

		public void setConsultas(Set<Consultas> consultas) {
			this.consultas = consultas;
		}

		public void setRecetas(List<Recetas> recetas) {
			this.recetas = recetas;
		}

	  
	}



	
