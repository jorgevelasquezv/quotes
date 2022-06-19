package co.com.jorge.quotes;

import co.com.jorge.quotes.models.Admin;
import co.com.jorge.quotes.services.CatalogService;
import co.com.jorge.quotes.services.Service;

import java.sql.SQLException;
import java.util.Date;

public class EjemploJDBC {
    public static void main(String[] args) throws SQLException {

        Service servicio = new CatalogService();

        System.out.println("============== Listar ==============");
        Admin admin = servicio.adminFindByUsername("admin");
        System.out.println(admin);
//        System.out.println(admin.getUsername());
//        System.out.println(admin.getPassword());

//        servicio.listar().forEach(System.out::println);
//        Categoria categoria = new Categoria();
//        categoria.setNombre("Iluminacion");
//
//        System.out.println("============== Agregar producto con categoria ==============");
//        Producto producto = new Producto();
//        producto.setNombre("Lampara led escritorio");
//        producto.setPrecio(440);
//        producto.setFechaRegistro(new Date());
//        producto.setSku("Abcdeh126");
//        servicio.guardarProductoConCategoria(producto, categoria);
//
//        System.out.println("Producto guardado con exito: " + producto.getId());
//        System.out.println("============== Listar ==============");
//        servicio.listar().forEach(System.out::println);
    }
}
