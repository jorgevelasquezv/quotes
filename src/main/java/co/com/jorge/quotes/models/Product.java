package co.com.jorge.quotes.models;

import java.io.Serializable;
import java.util.Date;

public class Product implements Serializable {

    private Long idProduct;

    private String name;

    private Long price;

    private int stock;

    private Long idCategory;

    private Date registryDate;

    public Product() {
    }

    public Product(Long idProduct, String name, Long price, int stock, Long idCategory, Date registryDate) {
        this.idProduct = idProduct;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.idCategory = idCategory;
        this.registryDate = registryDate;
    }

    public Long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Long getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Long idCategory) {
        this.idCategory = idCategory;
    }

    public Date getRegistryDate() {
        return registryDate;
    }

    public void setRegistryDate(Date registryDate) {
        this.registryDate = registryDate;
    }


    @Override
    public String toString() {
        return "Product{" +
                "idProduct=" + idProduct +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", idCategory=" + idCategory +
                '}';
    }
}
