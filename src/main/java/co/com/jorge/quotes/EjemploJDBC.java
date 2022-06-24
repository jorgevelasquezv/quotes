package co.com.jorge.quotes;

import co.com.jorge.quotes.models.Admin;
import co.com.jorge.quotes.models.Product;
import co.com.jorge.quotes.models.Provider;
import co.com.jorge.quotes.services.CatalogService;
import co.com.jorge.quotes.services.Service;
import co.com.jorge.quotes.util.DataBaseConnectionDS;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.SQLException;

public class EjemploJDBC {
    public static void main(String[] args) throws SQLException {


        Service servicio = null;
        try(Connection conn = DataBaseConnectionDS.getConnection();) {
            servicio = new CatalogService(conn);
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }

        System.out.println("============== Listar ==============");
        Admin admin = servicio.adminFindByUsername("admin");
        System.out.println(admin);

        Provider provider = servicio.providerFindByUsername("provider");
        System.out.println(provider);

        System.out.println("============== Listar Productos ==============");
        servicio.productFindAll().forEach(System.out::println);

        System.out.println("============== Listar Solicitudes ==============");
        servicio.requestProductFindAll().forEach(System.out::println);

        System.out.println("============== Buscar Productoo Por Nombre ==============");
        Product product = servicio.productFindByName("Escritorio En L");
        System.out.println(product);;

        System.out.println("============== Listar Offers ==============");
        servicio.offertFindAll().forEach(System.out::println);


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
