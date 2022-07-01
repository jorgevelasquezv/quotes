    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../templates/header.jsp" />
<body>
    <main class="main">
        <section >
            <h1 class="main__title">Requests and stock management at hand</h1>  
        </section>
        <section class="container mt-5 mb-5 ">
            <table class="table table-hover caption-top" title="List Request Products">
                <caption>List Request Producst</caption>
                <thead>
                    <tr>
                        <th scope="col">Product</th>
                        <th scope="col">Category</th>
                        <th scope="col">Quantity</th>
                        <th scope="col">Date Initial</th>
                        <th scope="col">Date Final</th>
                        <c:if test="${rol == 'provider'}">
                            <th scope="col">Price Unit</th>
                            <th scope="col">Send Offer</th>
                        </c:if>
                    </tr>
                </thead>
                <tbody>

                    <c:forEach var="item" items="${requests}">
                        <tr>
                            <td>${item.product.name}</td>
                            <td>${item.category.name}</td>
                            <td>${item.quantity}</td>
                            <td>${item.initialDate}</td>
                            <td>${item.finalDate}</td>
                            <c:if test="${rol == 'provider'}">
                                <td>
                                    <form method="post" action="/quotes/provider/send-offer">
                                        <input type="number" name="price" class="form-control" placeholder="Unit Price Of Offer" aria-label="Recipient's username" aria-describedby="button-addon2"/>
                                        <input type="hidden" name="idRequest" value="${item.idRequest}"/>
                                        <td>
                                            <input type="submit" class="btn btn-outline-info" id="button-addon2" value="Send" />
                                        </td>
                                    </form>
                                </td>
                            </c:if>
                        </tr>     
                    </c:forEach>                    
                </tbody>
            </table>
        </section>
    </main>
</body>
<jsp:include page="../templates/footer.html" />
