<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../templates/header.jsp" />

<body>
    <main>
        <section >
            <h1 class="main__title">Requests and stock management at hand</h1>  
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
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="product" items="${stock}"> 
                        <tr>
                            <td>${product.name}</td>
                            <td>${product.category.name}</td>
                            <td>${product.stock}</td>
                            <td>${product.price}</td>
                        </tr>                            
                    </c:forEach>
                    
                </tbody>
            </table>
        </section>
    </main>
</body>
<jsp:include page="../templates/footer.html" />
