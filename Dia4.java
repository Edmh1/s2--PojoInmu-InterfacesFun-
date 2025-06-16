import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Dia4 {
    // 🛠️ Tu tarea:
    // ✅ Crea aquí tu propio `record`, con al menos 4 campos (por ejemplo: nombre, edad, salario, departamento)
    // ✅ Agrega un método dentro del record que imprima toda su información formateada
    // Puedes llamarlo `detalle()` o como prefieras
    public record Empleado(String nombre, Integer edad, Float salario, String departamento){

        public String detalle(){
            return "empleado{nombre: "+nombre+
                    ",edad: "+edad+
                    ",salario: "+salario+
                    ",departamento: "+departamento;
        }
    }

    public static void main(String[] args) {

        // 📦 Lista de prueba para usar en varios ejercicios:
        // Puedes crearla cuando tengas tu `record` listo
        List<Empleado> empleadosList = List.of(
                new Empleado("juan", 20, 1000.3f, "Atlantico"),
                new Empleado("suarez", 18, 14300.3f, "Magdalena"),
                new Empleado("pepe", 42, 200.3f, "Bolivar"),
                new Empleado("escobar", 34, 10330.3f, "Atlantico"),
                new Empleado("juanito", 17, 12000.3f, "Huila"));


        // ==================== EJERCICIOS PARA PRACTICAR ====================

        // 🧠 Ejercicio 1: Evaluar salud laboral
        // Dada una lista de empleados, imprime:
        // - "✅ Juan está saludable" si su edad está entre 18 y 60
        // - "❌ Juan no está saludable" en otro caso
        // Recorre la lista con una función que genere ese mensaje y otra que lo imprima.
        Function<List<Empleado>, List<String>> e1 = empleados -> empleados.stream()
                .map(empleado -> empleado.edad() >= 18 && empleado.edad() <= 60
                        ?  empleado.nombre() + " está saludable"
                        : empleado.nombre() + " no está saludable")
                .collect(Collectors.toList());
        Consumer<List<String>> ej11 = empleados -> empleados
                .forEach(info -> System.out.println(info));

        ej11.accept(e1.apply(empleadosList));

        // 🧠 Ejercicio 2: Generador dinámico de empleados
        // Crea una función que reciba un número `n` y retorne una lista de `n` empleados con:
        // - nombre "Empleado1", "Empleado2", ..., "EmpleadoN"
        // - edad aleatoria entre 20 y 65
        // - salario aleatorio entre 1.000.000 y 10.000.000
        // - departamento aleatorio entre "Ventas", "IT", "RRHH"
        Function<Integer, List<Empleado>> ej2 = cantidad -> {
            List<Empleado> result = new ArrayList<>();
            Random r = new Random();
            List<String> departamentos = List.of("Ventas", "IT", "RRHH");

            for (int i = 1; i <= cantidad; i++) {
                String nombre = "Empleado" + i;
                int edad = r.nextInt(46) + 15;
                float salario = r.nextFloat() * (10000000.0f - 1000000.0f) + 1000000.0f;
                String departamento = departamentos.get(r.nextInt(departamentos.size()));

                result.add(new Empleado(nombre, edad, salario, departamento));
            }

            return result;
        };

        // 🧠 Ejercicio 3: Filtrado por edad y mapeo de nombres
        // A partir de una lista de empleados, obtén una nueva lista con los nombres
        // de quienes tengan entre 25 y 40 años (inclusive).
        List<String> ej3 = ej2.apply(5).stream()
                .filter(e -> e.edad() >= 25 && e.edad() <= 40)
                .map(e -> e.nombre())
                .collect(Collectors.toList());

        // 🧠 Ejercicio 4: Buscar extremos
        // Encuentra el empleado con mayor salario y el de menor edad.
        // Imprime su método detalle()
        Consumer<List<Empleado>> ej4 = lista -> {
            Optional<Empleado> a = lista.stream()
                    .max(Comparator
                            .comparingDouble(Empleado::salario)
                            .reversed()
                            .thenComparingInt(Empleado::edad)
                    );
            a.ifPresent(e -> System.out.println(e.detalle()));
        };
        ej4.accept(ej2.apply(5));



        // 🧠 Ejercicio 5: Validación personalizada
        // Tienes un empleado:
        // - Si es menor de 18 años, lanza una IllegalArgumentException
        // - Si es válido, imprime su nombre en mayúsculas con un saludo
        Consumer<List<Empleado>> ej5 = list -> list.forEach(
                empleado -> {
                    if(empleado.edad() < 18){
                        throw new IllegalArgumentException("Muy joven");
                    }
                    System.out.println(empleado.nombre().toUpperCase()+ "Bienvenido");
                }
        );

        ej5.accept(ej2.apply(5));
    }
}
