import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Dia2 {

    // Instrucción 1:
    // Crea un `record` llamado `Persona` con campos `String nombre` e `int edad`.
    // Esto será tu POJO inmutable para todos los ejercicios.
    public record Persona(String nombre, Integer edad){

        public String all(){
            StringBuilder epa = new StringBuilder(nombre);
            epa.append(edad);
            return epa.toString();
        }
    }

    public static void main(String[] args) {
        // Instrucción 2:
        // Declara un `Supplier<Persona>` que retorne una persona fija, por ejemplo: ("pepe", 30)
        Supplier<Persona> crearPersona = () -> new Persona("pepe", 30);
        // segun veo la utilidad de este es más como para testing

        // Instrucción 3:
        // Declara un `Consumer<Persona>` que imprima algo como: "Hola, soy pepe y tengo 30 años"
        Consumer<Persona> imprimir = persona -> System.out.println("Hola, soy "+ persona.nombre+ " y tengo "+ persona.edad+ " años");
        imprimir.accept(new Persona("pepe", 30));

        // Instrucción 4:
        // Usa el `Supplier` para obtener una Persona y pásala al `Consumer` con `.accept(...)`
        Supplier<Persona> s4 = () -> new Persona("pepe", 20);

        Consumer<Persona> c4 = persona -> System.out.println(persona.all()) ;

        c4.accept(s4.get());

        // Instrucción 5:
        // Declara una `Function<Persona, String>` que devuelva una descripción como: "pepe tiene 30 años"
        Function<Persona, String> f5 = persona-> persona.nombre+" tiene "+ persona.edad+ " años";
        System.out.println(f5.apply(new Persona("pepe", 60)));

        // Instrucción 6:
        // Declara una `BiFunction<String, Integer, Persona>` para crear objetos Persona dinámicamente
        BiFunction<String, Integer, Persona> b6 = (nombre, edad) -> new Persona(nombre, edad);

        System.out.println(b6.apply("juanito", 30).all());

        // Instrucción 10:
        // Usa `Collectors.partitioningBy` para dividir la lista en dos grupos: mayores y menores de edad
        List<Persona> l10 = Arrays.asList(
                new Persona("juares", 51),
                new Persona("asass", 91),
                new Persona("j33", 21),
                new Persona("ju2res", 11),
                new Persona("res", 12)
        );
        Map<Boolean,List<Persona>> s10 = l10.stream()
                .collect(Collectors.partitioningBy(persona -> persona.edad > 18));

        // Instrucción 11:
        // Imprime cada grupo con encabezado: "Mayores de edad:" y "Menores de edad:"
        // Recorre cada grupo con `forEach` y muestra solo los nombres
        Consumer<Map<Boolean, List<Persona>>> c11 = map -> {
            map.forEach((esMayor, lista) -> {
                String encabezado = esMayor ? "Mayores de edad:" : "Menores de edad:";
                System.out.println(encabezado);
                lista.forEach(p -> System.out.println("- " + p.nombre()));
            });
        };
        c11.accept(s10);

        // Instrucción 13:
        // Crea una lista de 3 personas nuevas usando `Supplier` o `BiFunction`
        Supplier<List<Persona>> crear3 = () -> Arrays.asList(
                new Persona("pepe", 23),
                new Persona("pepito", 13),
                new Persona("junaito", 43)
        );

    }

}
