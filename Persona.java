public record Persona(String nombre, Integer edad) {

    public String todo(){
        return nombre+edad;
    }
}
