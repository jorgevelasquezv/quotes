<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../templates/header.jsp" />
<body>
    <main class="main">
        <section >
            <h1 class="main__title">Requests and stock management at hand</h1>  
        </section>
        <section class="d-flex flex-column justify-content-center align-items-center mt-5">
            <h3 class="my-2">Request Products</h3>
            <form class="d-flex flex-column" action="${pageContext.request.contextPath}/admin/product" method="post">
                <div class="row mb-2">
                    <label for="name" class="col-form-label col-sm-2 me-3">Nombre</label>
                    <div class="col-sm-4 flex-fill">
                        <input type="text" name="name" id="name" value="${product.name}" class="form-control">
                    </div>
                    <c:if test="${errors != null && errors.containsKey('name')}">
                        <div style="color:red;">${errors.name}</div>
                    </c:if>
                </div>

                <div class="row mb-2">
                    <label for="price" class="col-form-label col-sm-2 me-3">Price</label>
                    <div class="col-sm-4 flex-fill">
                        <input type="number" name="price" id="price" value="${product.price > 0 ? product.price : '' }" class="form-control">
                    </div>
                    <c:if test="${errors != null && not empty errors.price}">
                        <div style="color:red;">${errors.price}</div>
                    </c:if> 
                </div>

                <div class="row mb-2">
                    <label for="category" class="col-form-label col-sm-2 me-3">Category</label>
                    <div class="col-sm-4 flex-fill">
                        <select name="category" id="category" class="form-select">
                            <option value="">--- seleccionar ---</option>
                            <c:forEach items="${categories}" var="c">
                            <option value="${c.idCategory}" ${c.idCategory.equals(product.category.idCategory)? "selected" : ""}>${c.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <c:if test="${errors != null && not empty errors.category}">
                        <div style="color:red;">${errors.category}</div>
                    </c:if>
                </div>
                
                <div class="row mb-2">
                    <label for="registryDate" class="col-form-label col-sm-2 me-3">Registry Date</label>
                    <div class="col-sm-4 flex-fill">
                        <input class="form-control" type="date" name="registryDate" id="registryDate" value="${product.registryDate != null? product.registryDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")): ""}">
                    </div>
                    <c:if test="${errors != null && not empty errors.registryDate}">
                        <div style="color:red;">${errors.registryDate}</div>
                    </c:if>
                </div>

                <div class="row mb-2">
                <div >
                    <input class="btn btn-primary" type="submit" value="Guardar">
                </div>
                </div>
                <input type="hidden" name="id" value="${product.idProduct}">
            </form>
        </section>
    </main>
</body>
<jsp:include page="../templates/footer.html" />
