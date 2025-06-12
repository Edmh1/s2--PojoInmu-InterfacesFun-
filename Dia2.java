public class Dia2 {

    // Instrucción 1:
    // Crea un `record` llamado `Persona` con campos `String nombre` e `int edad`.
    // Esto será tu POJO inmutable para todos los ejercicios.

    public static void main(String[] args) {

        // Instrucción 2:
        // Declara un `Supplier<Persona>` que retorne una persona fija, por ejemplo: ("pepe", 30)

        // Instrucción 3:
        // Declara un `Consumer<Persona>` que imprima algo como: "Hola, soy pepe y tengo 30 años"

        // Instrucción 4:
        // Usa el `Supplier` para obtener una Persona y pásala al `Consumer` con `.accept(...)`

        // Instrucción 5:
        // Declara una `Function<Persona, String>` que devuelva una descripción como: "pepe tiene 30 años"

        // Instrucción 6:
        // Declara una `BiFunction<String, Integer, Persona>` para crear objetos Persona dinámicamente

        // Instrucción 7:
        // Usa la `BiFunction` para crear una nueva Persona (por ejemplo: "Ana", 22)

        // Instrucción 8:
        // Usa la `Function` para obtener la descripción de esa persona e imprímela


        // Instrucción 9:
        // Crea una `List<Persona>` con al menos 6 personas con edades variadas (mayores y menores de 18)

        // Instrucción 10:
        // Usa `Collectors.partitioningBy` para dividir la lista en dos grupos: mayores y menores de edad

        // Instrucción 11:
        // Imprime cada grupo con encabezado: "Mayores de edad:" y "Menores de edad:"
        // Recorre cada grupo con `forEach` y muestra solo los nombres


        // Instrucción 12:
        // Declara una función reutilizable (puede ser un método static) que reciba una lista de `Persona`
        // y retorne un `Map<Boolean, List<Persona>>` con la partición por edad usando `partitioningBy`

        // Instrucción 13:
        // Crea una lista de 3 personas nuevas usando `Supplier` o `BiFunction`

        // Instrucción 14:
        // Usa `Consumer` para saludarlas en mayúsculas (usa `toUpperCase()`)

        // Instrucción 15:
        // Usa la función del punto 12 para clasificarlas por edad

        // Instrucción 16:
        // Imprime el resultado final con encabezados: "Mayores:" y "Menores:" usando `forEach`

    }

}
