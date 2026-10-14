public class Alumno extends Persona {
    private static final long serialVersionUID = 1L;
    private Fecha fechaMatricula; // Objeto compuesto serializable

    public Alumno(String dni, String nombre, int edad, Fecha fechaMatricula) {
        super(dni, nombre, edad);
        this.fechaMatricula = fechaMatricula;
    }

    @Override
    public String toString() {
        return super.toString() + " | Matricula: " + fechaMatricula;
    }
}
