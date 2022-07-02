package co.com.jorge.quotes.models;

public class Category{

    private Long idCategory;

    private String name;

    private String product;

    private String category;

    private Integer quantity;

    private String initialDate;

    private String finalDate;

    public Category() {
    }

    public Category(Long idCategory, String name, String product, String category, Integer quantity, String initialDate, String finalDate) {
        this.idCategory = idCategory;
        this.name = name;
        this.product = product;
        this.category = category;
        this.quantity = quantity;
        this.initialDate = initialDate;
        this.finalDate = finalDate;
    }

    public Long getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Long idCategory) {
        this.idCategory = idCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getInitialDate() {
        return initialDate;
    }

    public void setInitialDate(String initialDate) {
        this.initialDate = initialDate;
    }

    public String getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(String finalDate) {
        this.finalDate = finalDate;
    }

    @Override
    public String toString() {
        return "Category{" +
                "idCategory=" + idCategory +
                ", name='" + name + '\'' +
                ", product='" + product + '\'' +
                ", category='" + category + '\'' +
                ", quantity=" + quantity +
                ", initialDate='" + initialDate + '\'' +
                ", finalDate='" + finalDate + '\'' +
                '}';
    }
}
