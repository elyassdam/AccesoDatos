import java.util.ArrayList;
import java.util.List;

public class EmpleadosFactory {
	    
	    public static List<Empleado> load(){
	        List<Empleado> empleados = new ArrayList<>();
	        
	        empleados.add(new Empleado("Aitor Tilla","tamal@mail.com"));
	        empleados.add(new Empleado("Soytu burla","bobo@mail.com"));
	        empleados.add(new Empleado("Burger King","@mail.com"));
	        empleados.add(new Empleado("Lola Mento","mentoladp@mail.com"));
	        empleados.add(new Empleado("Helen Chufe","cortapico@mail.com"));
	        empleados.add(new Empleado("Estela Gartija","kaiman@mail.com"));
	        
	        return empleados;
	    }
	    
	}

