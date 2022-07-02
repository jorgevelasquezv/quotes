package co.com.jorge.quotes.controllers;

import co.com.jorge.quotes.models.Category;
import co.com.jorge.quotes.models.Product;
import co.com.jorge.quotes.services.CategoryService;
import co.com.jorge.quotes.services.ProductService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/admin/product")
public class CreateProductServlet extends HttpServlet {

    @Inject
    private ProductService productService;

    @Inject
    private CategoryService categoryService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Category> categories = categoryService.findAll();
        req.setAttribute("categories", categories );

        Product product = new Product();
        req.setAttribute("product", product);

        getServletContext().getRequestDispatcher("/WEB-INF/pages/formProducts.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        Long price;

        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");

        try {
            price = Long.valueOf(req.getParameter("price"));
        } catch (NumberFormatException e){
            price = 0L;
            System.out.println("***** Price: " +  price);
        }

        String dateString = req.getParameter("registryDate");
        Long IdCategory;
        try {
            IdCategory = Long.parseLong(req.getParameter("category"));
        } catch (NumberFormatException e){
            IdCategory = 0L;
        }

        Map<String, String> errors = new HashMap<>();
        if (name == null || name.isBlank()){
            errors.put("name", "Name is required!");
        }
        if (dateString == null || dateString.isBlank()){
            errors.put("registryDate", "The date is required!");
        }
        if (price.equals(0l)) {
            errors.put("price", "The price is required!");
            System.out.println("***** Price into equals 0 ");

        }
        if (IdCategory.equals(0L)){
            errors.put("category", "The category is required!");
        }

        Date date;

        try {
            date = formatDate.parse(dateString);
        } catch (ParseException e) {
            date = null;
        }
        long id;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e){
            id = 0L;
        }
        Product product = new Product();
        product.setIdProduct(id);
        product.setName(name);
        product.setPrice(price);
        product.setRegistryDate(date);

        Category category = new Category();
        category.setIdCategory(IdCategory);
        product.setCategory(category);

        if (errors.isEmpty()) {
            productService.save(product);
            getServletContext().getRequestDispatcher("/admin/stock").forward(req, resp);
        } else {
            req.setAttribute("errors", errors);
            req.setAttribute("categories",  categoryService.findAll());
            req.setAttribute("product", product);
            getServletContext().getRequestDispatcher("/WEB-INF/pages/formProducts.jsp").forward(req, resp);
        }
    }
}
