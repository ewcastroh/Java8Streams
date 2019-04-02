package stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamPrueba {
	
	private static List<User> users;
	
	private static void setUpUser() {
		users = new ArrayList<>();
		users.add(new User(1, "Eimer"));
		users.add(new User(2, "Alejandra"));
		users.add(new User(3, "Duvan"));
		users.add(new User(4, "Manuel"));
		users.add(new User(5, "Luisa"));
		users.add(new User(6, "Samantha"));
		users.add(new User(7, "Mona"));
		users.add(new User(8, "Mono"));
		users.add(new User(9, "Alejandra"));
	}
	
	private static String convertirAMayuscular(String nombre) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return nombre.toUpperCase();
	}
	
	// ForEach: Para recorrer una lista de objetos de un tipo
	private static void imprimirLista() {
		users.stream().forEach(e -> System.out.println(e.getId() + " - " + e.getNombre()));
	}
	
	public static void main(String[] args) {
		setUpUser();
		Stream stream = Stream.of(users);
		users.stream();
		
		/*
		 *  ForEach: Para recorrer una lista de objetos de un tipo
		 */
		System.out.println("----- ForEach -----");
		users.stream().forEach(e -> e.setNombre(e.getNombre() + " Castro."));
		imprimirLista();
		
		/*
		 *  Map: Permite realizar una transformación rápida de los datos del flujo original del Stream.
		 * Se tomará el Stream original que es ua lista de USers y el Map devolverá una lista de Stream.
		 * Se pasará de la clase Lista a Stream
		 */
		System.out.println("----- Map -----");
		List<String> listaString = users.stream().map(usuario -> usuario.getNombre()).collect(Collectors.toList());
		listaString.stream().forEach(user -> System.out.println(user));
		
		/*
		 *  Filter: Produce una nueva secuencia que contiene elementos del
		 *  stream original que han pasado por una determinada condición
		 */
		System.out.println("----- Filter -----");
		setUpUser();
		List<User> usersFilter = users.stream()
				.filter(user -> user.getNombre() != "Eimer")
				.filter(user -> user.getId() < 4)
				.collect(Collectors.toList());
		usersFilter.stream().forEach(user -> System.out.println(user.getId() + " - " + user.getNombre()));
		
		/*
		 * FindFirst: Encuentra el primer elemento del stream que cumple 
		 * con una condición dada
		 */
		System.out.println("----- FindFirst -----");
		setUpUser();
		User user = users.stream()
				.filter(usuario -> usuario.getNombre().equals("Alejandra"))
				.findFirst()
				.orElse(null);
		System.out.println(user.getId() + " - " + user.getNombre());
		
		/*
		 * FlatMap: Se obtiene los datos de diferentes Array
		 * y los concatena en uno solo
		 */
		System.out.println("----- FlatMap -----");
		List<List<String>> nombresVariasListas = new ArrayList<>(
				Arrays.asList(
						new ArrayList<String>(Arrays.asList("Eimer", "Alejandra", "Mono")),
						new ArrayList<String>(Arrays.asList("Samantha", "Mona"))
						)
				);
		List<String> nombresUnicaLista = nombresVariasListas.stream()
				.flatMap(nombre -> nombre.stream())
				.collect(Collectors.toList());
		nombresUnicaLista.stream().forEach(nombre -> System.out.println(nombre));
		
		/*
		 * Peek: Similar a ForEach, pero sin ser una acción final.
		 * Se puede ejecutar otros métodos posteriormente.
		 */
		System.out.println("----- Peek -----");
		setUpUser();
		List<User> users2 = users.stream()
				.peek(u -> u.setNombre(u.getNombre() + " Apellido"))
				.collect(Collectors.toList());
		users2.stream().forEach(u -> System.out.println(u.getNombre()));
		
		/*
		 * Count: Retorna la cantidad de elementos del Stream
		 * después de pasar todos los filtros
		 */
		System.out.println("----- Count -----");
		setUpUser();
		long numeroFiltrados = users.stream()
				.filter(usuario -> usuario.getId() < 4)
				.count();
		System.out.println(numeroFiltrados);
		
		/*
		 * Skip: Salta(omite) los primeros elementos que se le indiquen
		 * Limit: Limita el número de elementos que se desean obtener
		 */
		System.out.println("----- Skip And Limit -----");
		String[] abc = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "ñ", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
		List<String> abcFilter = Arrays.stream(abc)
				.skip(10)
				.limit(7)
				.collect(Collectors.toList());
		abcFilter.stream().forEach(letra -> System.out.println(letra));
		
		/*
		 * Sorted:
		 */
		System.out.println("----- Sorted -----");
		setUpUser();
		users = users.stream()
			.sorted(Comparator.comparing(User::getNombre))
			.collect(Collectors.toList());
		imprimirLista();
		
		/*
		 * Min: Obtiene objeto con el valor mínimo del atributo en una lista
		 * Max: Obtiene objeto con el valor máximo del atributo en una lista
		 */
		System.out.println("----- Min y Max -----");
		setUpUser();
		System.out.println("----- Min -----");
		User usuarioMinimo = users.stream()
				.min(Comparator.comparing(User::getId))
				.orElse(null);
		System.out.println(usuarioMinimo.getId() + " - " + usuarioMinimo.getNombre());
		
		System.out.println("----- Max -----");
		User usuarioMaximo = users.stream()
				.max(Comparator.comparing(User::getId))
				.orElse(null);
		System.out.println(usuarioMaximo.getId() + " - " + usuarioMinimo.getNombre());
		
		/*
		 * Distinct: 
		 */
		System.out.println("----- Distinct -----");
		String[] abcRepetidos = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "a", "b", "c", "d", "e", "f", "g", "h", "i", "a", "b", "c", "d", "e", "f", "g", "h", "i"};
		List<String> abcFilterDistinct = Arrays.stream(abcRepetidos)
				.distinct().collect(Collectors.toList());
		abcFilterDistinct.stream().forEach(letra -> System.out.println(letra));
		
		/*
		 * AllMatch: Verifica si todo el predicado es verdaderos
		 * AnyMatch: Verifica si al menos un valor del predicado es verdadero
		 * NoneMatch: Verifica si ningún elemento cumple con el predicado
		 */
		System.out.println("----- AllMatch - AnyMatch - NoneMatch -----");
		List<Integer> listaNumeros = Arrays.asList(100, 2000, 3400, 4500, 12, 340);
		
		System.out.println("----- AllMatch -----");
		boolean allMatch = listaNumeros.stream()
				.allMatch(number -> number < 500);
		System.out.println("allMatch: " + allMatch); 
		
		System.out.println("----- AnyMatch -----");
		boolean anyMatch = listaNumeros.stream()
				.anyMatch(number -> number < 500);
		System.out.println("anyMatch: " + anyMatch);
		
		System.out.println("----- NoneMatch -----");
		boolean noneMatch = listaNumeros.stream()
				.noneMatch(number -> number < 500);
		System.out.println("noneMatch: " + noneMatch);
		
		/*
		 * Sum: Suma
		 * Average: Obtener media
		 * Range: Iteración de n elementos
		 */
		System.out.println("----- Sum - Average - Range -----");
		setUpUser();
		
		double result = users.stream()
				.mapToInt(User::getId)
				.sum();
		System.out.println("Suma ids: " + result);
		
		System.out.println("----- Average -----");
		result = users.stream()
				.mapToInt(User::getId)
				.average()
				.orElse(0);
		System.out.println("Promedio ids: " + result);

		System.out.println("----- Range -----");
		System.out.println("Suma de 3 a 90: " +
				IntStream.range(3, 90)
				.sum());
		
		/*
		 * Reduce: Toma los elementos de una lista y los convierte 
		 * en un único resultado a traves de una operación dada
		 */
		System.out.println("----- Reduce -----");
		setUpUser();
		int sumaIds = users.stream()
				.map(User::getId)
				.reduce(0, Integer::sum);
		System.out.println("Suma de los ids con Reduce: " + sumaIds);
		
		/*
		 * Joining: Devuelve un recopilador que concatena la secuencia de
		 * char sequency y devuelve el resultado como una cadena.
		 */
		System.out.println("----- Joining -----");
		setUpUser();
		String names = users.stream()
				.map(User::getNombre)
				.collect(Collectors.joining(" - "))
				.toString();
		System.out.println("Names con joining: " + names);
		
		/*
		 * toSet: Devuelve un collector que acumula los elementos 
		 * de entrada en un nuevo set sin repetir elementos.
		 */
		System.out.println("----- toSet -----");
		setUpUser();
		Set<String> setNames = users.stream()
				.map(User::getNombre)
				.collect(Collectors.toSet());
		setNames.stream().forEach(setName -> System.out.println(setName));
		
		/*
		 * SummarizingDouble: Retorna estadísticas en una variable DoubleSummaryStatistic
		 */
		System.out.println("----- SummarizingDouble -----");
		setUpUser();
		DoubleSummaryStatistics statistics = users.stream()
				.collect(Collectors.summarizingDouble(User::getId));
		System.out.println("statistics.getAverage(): " + statistics.getAverage());
		System.out.println("statistics.getCount(): " + statistics.getCount());
		System.out.println("statistics.getMax(): " + statistics.getMax());
		System.out.println("statistics.getMin(): " + statistics.getMin());
		System.out.println("statistics.getSum(): " + statistics.getSum());
		
		DoubleSummaryStatistics statistics1 = users.stream()
				.mapToDouble(User::getId)
				.summaryStatistics();
		System.out.println("statistics1.getAverage(): " + statistics1.getAverage());
		System.out.println("statistics1.getCount(): " + statistics1.getCount());
		System.out.println("statistics1.getMax(): " + statistics1.getMax());
		System.out.println("statistics1.getMin(): " + statistics1.getMin());
		System.out.println("statistics1.getSum(): " + statistics1.getSum());
		
		/*
		 * PartitioningBy: Retorna dos listas de elementos, una en la que
		 * se cumple el predicado y otra en la que no se cumple.
		 */
		System.out.println("----- PartitioningBy -----");
		setUpUser();
		List<Integer> numeros = Arrays.asList(2, 34, 23, 56, 78, 43, 98, 1);
		Map<Boolean, List<Integer>> esMayor = numeros.stream()
				.collect(Collectors.partitioningBy(number -> number > 50));
		System.out.println("Mayores a 50");
		esMayor.get(true).stream().forEach(esMay -> System.out.println(esMay));
		System.out.println("No Mayores a 50");
		esMayor.get(false).stream().forEach(esMay -> System.out.println(esMay));
		
		/*
		 * GroupingBy: Se agrupan elementos según la propiedad que se indica.
		 */
		System.out.println("----- GroupingBy -----");
		setUpUser();
		Map<Character, List<User>> grupoAlfabetico = users.stream()
				.collect(Collectors.groupingBy(palabra -> new Character(palabra.getNombre().charAt(0))));
		grupoAlfabetico.get('A').stream().forEach(word -> System.out.println(word.getNombre()));
		grupoAlfabetico.get('M').stream().forEach(word -> System.out.println(word.getNombre()));
		grupoAlfabetico.get('L').stream().forEach(word -> System.out.println(word.getNombre()));
		
		/*
		 * Mapping: Convierte una lista de objetos en otra lista de objetos
		 */
		System.out.println("----- Mapping -----");
		setUpUser();
		List<String> personas = users.stream()
				.collect(Collectors.mapping(User::getNombre, Collectors.toList()));
		personas.stream().forEach(persona -> System.out.println(persona));
		
		/*
		 * Stream Paralelo: Sirve para ejecutar uns Stream por varios hilos para reducir tiempos
		 */
		System.out.println("----- Stream Paralelo -----");
		setUpUser();
		long tiempo1 = System.currentTimeMillis();
		listaString.stream().forEach(nombre -> System.out.println(convertirAMayuscular(nombre)));
		long tiempo2 = System.currentTimeMillis();
		System.out.println("Tiempo tomado sin paralelo: " + (tiempo2 - tiempo1));
		
		tiempo1 = System.currentTimeMillis();
		listaString.parallelStream().forEach(nombre -> System.out.println(convertirAMayuscular(nombre)));
		tiempo2 = System.currentTimeMillis();
		System.out.println("Tiempo tomado en paralelo: " + (tiempo2 - tiempo1));
	
	}

}
