<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../templates/header.jsp" />

<body>
    <main class="main">
        <section>
            <h1 class="main__title">Requests and stock management at hand</h1>  
        </section>
        <section class="container mt-5">
            <form method="get" action="${pageContext.request.contextPath}/admin/product">
                <button type="submit" class="btn btn-primary"/>Add Product</button>
            </form>
        </section>
        <section class="container mt-5 mb-5 ">
            <table class="table table-hover caption-top" title="Stock Products">
                <caption>Stock Producst</caption>
                <thead>
                    <tr>
                        <th scope="col">Name</th>
                        <th scope="col">Category</th>
                        <th scope="col">Stock</th>
                        <th scope="col">Price</th>
                        <th scope="col">Update</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="product" items="${stock}"> 
                        <tr>
                            <td>${product.name}</td>
                            <td>${product.category.name}</td>
                            <form method="post" action="${pageContext.request.contextPath}/admin/stock/update">
                                <td>
                                    <input type="number" name="stock" value="${product.stock}"/>
                                </td>
                                <td>
                                    <input type="number" name="price" value="${product.price}"/>
                                </td>
                                <td>
                                    <button type="submit" class="btn btn-outline-success"/>Update
                                        <input class="invisible"  style="width : 1px; heigth : 1px" type="hidden" name="id" value="${product.idProduct}"/>
                                    </button>
                                </td>
                            </form>
                        </tr>                            
                    </c:forEach>
                    
                </tbody>
            </table>
        </section>
    </main>
</body>
<jsp:include page="../templates/footer.html" />
