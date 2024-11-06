package org.iesvdm.tienda;

import org.iesvdm.tienda.modelo.Fabricante;
import org.iesvdm.tienda.modelo.Producto;
import org.iesvdm.tienda.repository.FabricanteRepository;
import org.iesvdm.tienda.repository.ProductoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

import static java.util.Collections.min;
import static java.util.Comparator.comparing;
import static sun.jvm.hotspot.runtime.VMObjectFactory.newObject;


@SpringBootTest
class TiendaApplicationTests {

	@Autowired
	FabricanteRepository fabRepo;
	
	@Autowired
	ProductoRepository prodRepo;

	@Test
	void testAllFabricante() {
		var listFabs = fabRepo.findAll();
		
		listFabs.forEach(f -> {
			System.out.println(">>"+f+ ":");
			f.getProductos().forEach(System.out::println);
		});
	}
	
	@Test
	void testAllProducto() {
		var listProds = prodRepo.findAll();

		listProds.forEach( p -> {
			System.out.println(">>"+p+":"+"\nProductos mismo fabricante "+ p.getFabricante());
			p.getFabricante().getProductos().forEach(pF -> System.out.println(">>>>"+pF));
		});
				
	}

	
	/**
	 * 1. Lista los nombres y los precios de todos los productos de la tabla producto
	 */
	@Test
	void test1() {
		var listProds = prodRepo.findAll();
		//TODO
		record Tupla (String nombre, double precio){}
		var result = listProds.stream()
				.map(p -> new Tupla(p.getNombre(), p.getPrecio()))
				.toList();

		// Encabezado de la tabla
		System.out.printf("%-35s %-10s%n", "Nombre producto", "Precio");
		System.out.println("-----------------------------------------------");

		// Imprimir cada producto en formato de tabla
		result.forEach(tupla ->
				System.out.printf("%-35s %-10.2f%n", tupla.nombre(), tupla.precio())
		);
	}
	
	
	/**
	 * 2. Devuelve una lista de Producto completa con el precio de euros convertido a dólares .
	 */
	@Test
	void test2() {
		var listProds = prodRepo.findAll();
		//TODO
		record Tupla (String nombre, double precio){}
		var result = listProds.stream()
				.map(p -> new Tupla(p.getNombre(), convertirEurosDolares(p.getPrecio())))
				.toList();

		System.out.printf("%-35s %-10s%n", "Nombre producto", "Precio");
		System.out.println("-----------------------------------------------");

		// Imprimir cada producto en formato de tabla
		result.forEach(tupla ->
				System.out.printf("%-35s %-10.2f%n", tupla.nombre(), tupla.precio())
		);


	}
	public static double convertirEurosDolares(double euros){
		final Double TASA_DE_CAMBIO=1.08;
		return euros * TASA_DE_CAMBIO;
	}
	
	/**
	 * 3. Lista los nombres y los precios de todos los productos, convirtiendo los nombres a mayúscula.
	 */
	@Test
	void test3() {
		var listProds = prodRepo.findAll();
		//TODO
		record Tupla (String nombre, double precio){}
		var result = listProds.stream()
				.map(p -> new Tupla(p.getNombre().toUpperCase(), p.getPrecio()))
				.toList();

		System.out.printf("%-35s %-10s%n", "Nombre producto", "Precio");
		System.out.println("-----------------------------------------------");

		// Imprimir cada producto en formato de tabla
		result.forEach(tupla ->
				System.out.printf("%-35s %-10.2f%n", tupla.nombre(), tupla.precio())
		);
	}
	
	/**
	 * 4. Lista el nombre de todos los fabricantes y a continuación en mayúsculas los dos primeros caracteres del nombre del fabricante.
	 */
	@Test
	void test4() {
		var listFabs = fabRepo.findAll();
		//TODO
		record Tupla (String nombre){}
		var listaFabricantes = listFabs.stream()
				.map(p -> new Tupla(capitalizeFirstTwoLetters(p.getNombre())))
				.toList();

		System.out.printf("%-35s%n", "Nombre fabricante");
		System.out.println("------------------------");

		// Imprimir cada producto en formato de tabla
		listaFabricantes.forEach(tupla ->
				System.out.printf("%-35s%n", tupla.nombre())
		);

	}

	public static String capitalizeFirstTwoLetters(String input){

		if(input.length()==2) {
			input = input.toUpperCase();
		}else {
			input=input.substring(0, 2).toUpperCase() + input.substring(2);
		}

		return input;

	}
	/**
	 * 5. Lista el código de los fabricantes que tienen productos.
	 */
	@Test
	void test5() {
		var listFabs = fabRepo.findAll();
		//TODO
		record Tupla(Integer codigoFabricante){}
		var result = listFabs.stream()
				.filter(f -> !f.getProductos().isEmpty())
				.map(f -> new Tupla(f.getCodigo()))
				.toList();

		System.out.printf("%-35s%n", "Codigo");
		System.out.println("------------------------");

		result.forEach( tupla ->
				System.out.printf("%-35d%n", tupla.codigoFabricante())
		);
	}
	
	/**
	 * 6. Lista los nombres de los fabricantes ordenados de forma descendente.
	 */
	@Test
	void test6() {
		var listFabs = fabRepo.findAll();
		//TODO
	}
	
	/**
	 * 7. Lista los nombres de los productos ordenados en primer lugar por el nombre de forma ascendente y en segundo lugar por el precio de forma descendente.
	 */
	@Test
	void test7() {
		var listProds = prodRepo.findAll();
		//TODO
	}
	
	/**
	 * 8. Devuelve una lista con los 5 primeros fabricantes.
	 */
	@Test
	void test8() {
		var listFabs = fabRepo.findAll();
		//TODO
	}
	
	/**
	 * 9.Devuelve una lista con 2 fabricantes a partir del cuarto fabricante. El cuarto fabricante también se debe incluir en la respuesta.
	 */
	@Test
	void test9() {
		var listFabs = fabRepo.findAll();
		//TODO		
	}
	
	/**
	 * 10. Lista el nombre y el precio del producto más barato
	 */
	@Test
	void test10() {
		var listProds = prodRepo.findAll();
		//TODO
	}
	
	/**
	 * 11. Lista el nombre y el precio del producto más caro
	 */
	@Test
	void test11() {
		var listProds = prodRepo.findAll();
		//TODO
	}
	
	/**
	 * 12. Lista el nombre de todos los productos del fabricante cuyo código de fabricante es igual a 2.
	 * 
	 */
	@Test
	void test12() {
		var listProds = prodRepo.findAll();
		//TODO
	}
	
	/**
	 * 13. Lista el nombre de los productos que tienen un precio menor o igual a 120€.
	 */
	@Test
	void test13() {
		var listProds = prodRepo.findAll();
		//TODO
	}
	
	/**
	 * 14. Lista los productos que tienen un precio mayor o igual a 400€.
	 */
	@Test
	void test14() {
		var listProds = prodRepo.findAll();
		//TODO
	}
	
	/**
	 * 15. Lista todos los productos que tengan un precio entre 80€ y 300€. 
	 */
	@Test
	void test15() {
		var listProds = prodRepo.findAll();
		//TODO
	}
	
	/**
	 * 16. Lista todos los productos que tengan un precio mayor que 200€ y que el código de fabricante sea igual a 6.
	 */
	@Test
	void test16() {
		var listProds = prodRepo.findAll();
		//TODO
	}
	
	/**
	 * 17. Lista todos los productos donde el código de fabricante sea 1, 3 o 5 utilizando un Set de codigos de fabricantes para filtrar.
	 */
	@Test
	void test17() {
		var listProds = prodRepo.findAll();
		//TODO
	}
	
	/**
	 * 18. Lista el nombre y el precio de los productos en céntimos.
	 */
	@Test
	void test18() {
		var listProds = prodRepo.findAll();
		//TODO
	}
	
	
	/**
	 * 19. Lista los nombres de los fabricantes cuyo nombre empiece por la letra S
	 */
	@Test
	void test19() {
		var listFabs = fabRepo.findAll();
		//TODOS
	}
	
	/**
	 * 20. Devuelve una lista con los productos que contienen la cadena Portátil en el nombre.
	 */
	@Test
	void test20() {
		var listProds = prodRepo.findAll();
		//TODO
	}
	
	/**
	 * 21. Devuelve una lista con el nombre de todos los productos que contienen la cadena Monitor en el nombre y tienen un precio inferior a 215 €.
	 */
	@Test
	void test21() {
		var listProds = prodRepo.findAll();
		//TODO
	}
	
	/**
	 * 22. Lista el nombre y el precio de todos los productos que tengan un precio mayor o igual a 180€. 
	 * Ordene el resultado en primer lugar por el precio (en orden descendente) y en segundo lugar por el nombre (en orden ascendente).
	 */
	void test22() {
		var listProds = prodRepo.findAll();
		//TODO
	}
	
	/**
	 * 23. Devuelve una lista con el nombre del producto, precio y nombre de fabricante de todos los productos de la base de datos. 
	 * Ordene el resultado por el nombre del fabricante, por orden alfabético.
	 */
	@Test
	void test23() {
		var listProds = prodRepo.findAll();

		var listAyuda = listProds.stream()
				.sorted(comparing(producto -> producto.getFabricante().getNombre()))
				.map(producto -> producto.getNombre()
						+ producto.getPrecio()
						+ producto.getFabricante().getNombre())
				.toList();

		listAyuda.forEach(System.out::println);

		Assertions.assertEquals(listAyuda, listProds);
		//TODO
	}
	
	/**
	 * 24. Devuelve el nombre del producto, su precio y el nombre de su fabricante, del producto más caro.
	 */
	@Test
	void test24() {
		var listProds = prodRepo.findAll();
		//TODO
	}
	
	/**
	 * 25. Devuelve una lista de todos los productos del fabricante Crucial que tengan un precio mayor que 200€.
	 */
	@Test
	void test25() {
		var listProds = prodRepo.findAll();
		//TODO	
	}
	
	/**
	 * 26. Devuelve un listado con todos los productos de los fabricantes Asus, Hewlett-Packard y Seagate
	 */
	@Test
	void test26() {
		var listProds = prodRepo.findAll();
		//TODO
	}
	
	/**
	 * 27. Devuelve un listado con el nombre de producto, precio y nombre de fabricante, de todos los productos que tengan un precio mayor o igual a 180€. 
	 * Ordene el resultado en primer lugar por el precio (en orden descendente) y en segundo lugar por el nombre.
	 * El listado debe mostrarse en formato tabla. Para ello, procesa las longitudes máximas de los diferentes campos a presentar y compensa mediante la inclusión de espacios en blanco.
	 * La salida debe quedar tabulada como sigue:

Producto                Precio             Fabricante
-----------------------------------------------------
GeForce GTX 1080 Xtreme|611.5500000000001 |Crucial
Portátil Yoga 520      |452.79            |Lenovo
Portátil Ideapd 320    |359.64000000000004|Lenovo
Monitor 27 LED Full HD |199.25190000000003|Asus

	 */		
	@Test
	void test27() {
		var listProds = prodRepo.findAll();
		//TODO
	}
	
	/**
	 * 28. Devuelve un listado de los nombres fabricantes que existen en la base de datos, junto con los nombres productos que tiene cada uno de ellos. 
	 * El listado deberá mostrar también aquellos fabricantes que no tienen productos asociados. 
	 * SÓLO SE PUEDEN UTILIZAR STREAM, NO PUEDE HABER BUCLES
	 * La salida debe queda como sigue:
Fabricante: Asus

            	Productos:
            	Monitor 27 LED Full HD
            	Monitor 24 LED Full HD

Fabricante: Lenovo

            	Productos:
            	Portátil Ideapd 320
            	Portátil Yoga 520

Fabricante: Hewlett-Packard

            	Productos:
            	Impresora HP Deskjet 3720
            	Impresora HP Laserjet Pro M26nw

Fabricante: Samsung

            	Productos:
            	Disco SSD 1 TB

Fabricante: Seagate

            	Productos:
            	Disco duro SATA3 1TB

Fabricante: Crucial

            	Productos:
            	GeForce GTX 1080 Xtreme
            	Memoria RAM DDR4 8GB

Fabricante: Gigabyte

            	Productos:
            	GeForce GTX 1050Ti

Fabricante: Huawei

            	Productos:


Fabricante: Xiaomi

            	Productos:

	 */
	@Test
	void test28() {
		var listFabs = fabRepo.findAll();
		listFabs.stream()
				.map(fabricante -> "Fabricante: " + fabricante.getNombre()+"\n\n"
						+ "Productos: "+"\n"
						+ fabricante.getProductos()
								.stream().map(producto -> producto.getNombre()+"\n")
								.collect(Collectors.joining()))
								.forEach(System.out::println);
		//TODO
	}
	
	/**
	 * 29. Devuelve un listado donde sólo aparezcan aquellos fabricantes que no tienen ningún producto asociado.
	 */
	@Test
	void test29() {
		var listFabs = fabRepo.findAll();
		//TODO
	}

	/**
	 * 30. Calcula el número total de productos que hay en la tabla productos. Utiliza la api de stream.
	 */
	@Test
	void test30() {
		var listProds = prodRepo.findAll();
		//TODO
		var numProds = listProds.stream()
				.count();
		System.out.println(numProds);

		Assertions.assertEquals(11, listProds.size());
	}

	
	/**
	 * 31. Calcula el número de fabricantes con productos, utilizando un stream de Productos.
	 */
	@Test
	void test31() {
		var listProds = prodRepo.findAll();
		//TODO
		var result = listProds.stream()
				.map(Producto::getFabricante).distinct().count();

				Assertions.assertEquals(7, result);
	}
	
	/**
	 * 32. Calcula la media del precio de todos los productos
	 */
	@Test
	void test32() {
		var listProds = prodRepo.findAll();
		//TODO
		var result = listProds.stream().mapToDouble(p -> p.getPrecio())
				.average();
		Assertions.assertEquals(271.7236363636364, result.orElse(0.0));
	}
	
	/**
	 * 33. Calcula el precio más barato de todos los productos. No se puede utilizar ordenación de stream.
	 */
	@Test
	void test33() {
		var listProds = prodRepo.findAll();
		//TODO
		OptionalDouble preciomin = listProds.stream()
				.mapToDouble(p -> p.getPrecio())
				.min();
		Assertions.assertEquals(59.99, preciomin.orElse(0.0));
	}
	
	/**
	 * 34. Calcula la suma de los precios de todos los productos.
	 */
	@Test
	void test34() {
		var listProds = prodRepo.findAll();
		//TODO	
	}
	
	/**
	 * 35. Calcula el número de productos que tiene el fabricante Asus.
	 */
	@Test
	void test35() {
		var listProds = prodRepo.findAll();
		//TODO
		var numProds = listProds.stream()
				.filter(producto -> producto.getFabricante().getNombre().equals("Asus"))
				.count();
		Assertions.assertEquals(2, numProds);

	}
	
	/**
	 * 36. Calcula la media del precio de todos los productos del fabricante Asus.
	 */
	@Test
	void test36() {
		var listProds = prodRepo.findAll();
		//TODO
		var mediaProds = listProds.stream()
				.filter(producto -> producto.getFabricante().getNombre().equals("Asus"))
				.mapToDouble(Producto::getPrecio)
				.average()
				.orElse(0.0);

		Assertions.assertEquals(223.995, mediaProds);
	}
	
	
	/**
	 * 37. Muestra el precio máximo, precio mínimo, precio medio y el número total de productos que tiene el fabricante Crucial. 
	 *  Realízalo en 1 solo stream principal. Utiliza reduce con Double[] como "acumulador".
	 */
	@Test
	void test37() {
		var listProds = prodRepo.findAll();
		//TODO
		var result = listProds.stream()
				.filter(producto -> producto.getFabricante().getNombre().equalsIgnoreCase("Crucial"))
				.map(producto -> new Double[]{
						producto.getPrecio(), producto.getPrecio(), producto.getPrecio(), 0.0})
				.reduce((doubles, doubles2) -> new Double[]{
						Math.min(doubles[0], doubles2[0]),
						Math.max(doubles[1], doubles2[1]),
						doubles[2] + doubles2[2],
						doubles[3]++
				})
				.orElse(new Double[]{});

		Double media = result[3]>0 ? result[2]/result[3]: 0.0;
		System.out.println(result[0]);
		System.out.println(result[1]);
		System.out.println(media);

	}
	
	/**
	 * 38. Muestra el número total de productos que tiene cada uno de los fabricantes. 
	 * El listado también debe incluir los fabricantes que no tienen ningún producto. 
	 * El resultado mostrará dos columnas, una con el nombre del fabricante y otra con el número de productos que tiene. 
	 * Ordene el resultado descendentemente por el número de productos. Utiliza String.format para la alineación de los nombres y las cantidades.
	 * La salida debe queda como sigue:
	 
     Fabricante     #Productos
-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
           Asus              2
         Lenovo              2
Hewlett-Packard              2
        Samsung              1
        Seagate              1
        Crucial              2
       Gigabyte              1
         Huawei              0
         Xiaomi              0

	 */
	@Test
	void test38() {
		var listFabs = fabRepo.findAll();
		//TODO
		var numProd = listFabs
				.stream()
				.map(f -> f.getNombre() + " " + f.getProductos().size())
				.toList();

		System.out.printf("%36s | %36s | %n" , "Fabricante", "#productos");
		System.out.println("----------------------------------------------------");
		listFabs.forEach(f -> {
				System.out.printf("%36s | %36s | %n", f.getNombre(), f.getProductos().size());
		});
	}
	
	/**
	 * 39. Muestra el precio máximo, precio mínimo y precio medio de los productos de cada uno de los fabricantes. 
	 * El resultado mostrará el nombre del fabricante junto con los datos que se solicitan. Realízalo en 1 solo stream principal. Utiliza reduce con Double[] como "acumulador".
	 * Deben aparecer los fabricantes que no tienen productos.
	 */
	@Test
	void test39() {
		var listFabs = fabRepo.findAll();
		//TODO

		listFabs.stream()
				.flatMap(f -> f.getProductos().stream())
				.mapToDouble(p -> p.getPrecio())
				.min().ifPresentOrElse(resultado -> System.out.println(resultado), () -> System.out.println("Nada"));

		System.out.println(listFabs);
		var resultmin = listFabs.stream()
				.flatMap(f -> f.getProductos().stream())
				.mapToDouble(p -> p.getPrecio())
				.min()
				.orElse(0.0);
		System.out.println(resultmin);

		var resultmax = listFabs.stream()
				.flatMap(f -> f.getProductos().stream())
				.mapToDouble(p -> p.getPrecio())
				.max()
				.orElse(0.0);
		System.out.println(resultmax);

		var resultmedio = listFabs.stream()
				.flatMap(f -> f.getProductos().stream())
				.mapToDouble(p -> p.getPrecio())
				.average()
				.orElse(0.0);
		System.out.println(resultmedio);
	}
	
	/**
	 * 40. Muestra el precio máximo, precio mínimo, precio medio y el número total de productos de los fabricantes que tienen un precio medio superior a 200€. 
	 * No es necesario mostrar el nombre del fabricante, con el código del fabricante es suficiente.
	 */
	@Test
	void test40() {
		var listFabs = fabRepo.findAll();
		//TODO
		var result = listFabs.stream()
				.filter(f -> f.getProductos().stream()
						.mapToDouble(p -> p.getPrecio())
								.average()
								.orElse(0)>=200)
						.map(f -> new Object() {
							int codFab = f.getCodigo();
							double resultmin = f.getProductos().stream()
									.mapToDouble(p -> p.getPrecio())
									.min()
									.orElse(0.0);

							double resultmax = f.getProductos().stream()
									.mapToDouble(p -> p.getPrecio())
									.max()
									.orElse(0.0);

							double resultmedio = f.getProductos().stream()
									.mapToDouble(p -> p.getPrecio())
									.average()
									.orElse(0.0);
							long total = f.getProductos().size();
						}

	}
	
	/**
	 * 41. Devuelve un listado con los nombres de los fabricantes que tienen 2 o más productos.
	 */
	@Test
	void test41() {
		var listFabs = fabRepo.findAll();
		//TODO
	}
	
	/**
	 * 42. Devuelve un listado con los nombres de los fabricantes y el número de productos que tiene cada uno con un precio superior o igual a 220 €. 
	 * Ordenado de mayor a menor número de productos.
	 */
	@Test
	void test42() {
		var listFabs = fabRepo.findAll();
		//TODO
	}
	
	/**
	 * 43.Devuelve un listado con los nombres de los fabricantes donde la suma del precio de todos sus productos es superior a 1000 €
	 */
	@Test
	void test43() {
		var listFabs = fabRepo.findAll();
		//TODO
	}
	
	/**
	 * 44. Devuelve un listado con los nombres de los fabricantes donde la suma del precio de todos sus productos es superior a 1000 €
	 * Ordenado de menor a mayor por cuantía de precio de los productos.
	 */
	@Test
	void test44() {
		var listFabs = fabRepo.findAll();
		//TODO	
	}
	
	/**
	 * 45. Devuelve un listado con el nombre del producto más caro que tiene cada fabricante. 
	 * El resultado debe tener tres columnas: nombre del producto, precio y nombre del fabricante. 
	 * El resultado tiene que estar ordenado alfabéticamente de menor a mayor por el nombre del fabricante.
	 */
	@Test
	void test45() {
		var listFabs = fabRepo.findAll();
		//TODO
	}
	
	/**
	 * 46. Devuelve un listado de todos los productos que tienen un precio mayor o igual a la media de todos los productos de su mismo fabricante.
	 * Se ordenará por fabricante en orden alfabético ascendente y los productos de cada fabricante tendrán que estar ordenados por precio descendente.
	 */
	@Test
	void test46() {
		var listFabs = fabRepo.findAll();
		//TODO
	}

}
