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
   public record Empleado(String nombre, Integer edad, Float sueldo, String departamento){
       public String detalle(){
           return "nombre: "+ nombre+";\n"
                   +"edad: "+ edad+";\n"
                   +"sueldo: "+ sueldo+";\n"
                   +"departamento: "+ departamento;
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
        Function<List<Empleado>, List<String>> ej1 = empleados -> {
            List<String> lista = new ArrayList<>();
            empleados.forEach(empleado -> {
                boolean saludable = empleado.edad() >= 18 && empleado.edad() <= 60;
                String valor = empleado.nombre()+" ";
                valor+= (saludable)?"está saludable": "no está saludable";
                lista.add(valor);
            });
            return lista;
        };
        Consumer<List<String>> ej11 = lista -> lista.forEach(
                elemento -> System.out.println(elemento));

        ej11.accept(ej1.apply(empleadosList));

        // 🧠 Ejercicio 2: Generador dinámico de empleados
        // Crea una función que reciba un número `n` y retorne una lista de `n` empleados con:
        // - nombre "Empleado1", "Empleado2", ..., "EmpleadoN"
        // - edad aleatoria entre 20 y 65
        // - salario aleatorio entre 1.000.000 y 10.000.000
        // - departamento aleatorio entre "Ventas", "IT", "RRHH"
        Function<Integer, List<Empleado>> generador = n -> {
            List<Empleado> empleados = new ArrayList<>();
            Random r = new Random();
            List<String> dep = Arrays.asList("Ventas", "IT", "RRHH");
            for (int i = 1; i <= n; i++) {
                String nombre = "Empleado"+i;
                Integer edad = r.nextInt(66) + 20;
                Float salario = r.nextFloat()* (10000000.0f - 1000000.0f) + 1000000.0f;
                String departamento = dep.get(r.nextInt(3));
                empleados.add(new Empleado(nombre, edad, salario, departamento));
            }
            return empleados;
        };

        // 🧠 Ejercicio 3: Filtrado por edad y mapeo de nombres
        // A partir de una lista de empleados, obtén una nueva lista con los nombres
        // de quienes tengan entre 25 y 40 años (inclusive).
        Function<List<Empleado>, List<String>> ej3 = lista -> lista.stream()
                .filter(empleado -> empleado.edad() >= 25 && empleado.edad() <= 40)
                .map(empleado -> empleado.nombre())
                .collect(Collectors.toList());

        // 🧠 Ejercicio 4: Buscar extremos
        // Encuentra el empleado con mayor salario y el de menor edad.
        // Imprime su método detalle()

        Consumer<List<Empleado>> ej4 = lista -> {
           Optional<Empleado> e = lista.stream().max(Comparator.comparingInt(Empleado::edad)
                            .reversed()
                            .thenComparing(Comparator.comparingDouble(Empleado::sueldo)));

           e.ifPresentOrElse(em -> System.out.println(em.detalle()),
                   () -> System.out.println("no encontrado"));
        };
        ej4.accept(generador.apply(10));

        // 🧠 Ejercicio 5: Validación personalizada
        // Tienes un empleado:
        // - Si es menor de 18 años, lanza una IllegalArgumentException
        // - Si es válido, imprime su nombre en mayúsculas con un saludo
        Consumer<Empleado> ej5 = empleado -> {
            if(empleado.edad() < 18){
                throw new IllegalArgumentException("oigaaaaaaaaaaa abuso de menoress");
            }else{
                System.out.println(empleado.nombre()+" Bienvenido");
            }
        };
    }
}
