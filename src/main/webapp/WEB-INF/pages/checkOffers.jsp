    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../templates/header.jsp" />
<body>
    <main class="main">
        <section>
            <h1 class="main__title">Requests and stock management at hand</h1>
        </section>
        <section class="container mt-5 ">
            <table class="table table-hover caption-top" title="Check Offers">
                <caption>Check Offers</caption>
                <thead>
                    <tr>
                    <th scope="col">Product</th>
                    <th scope="col">Category</th>
                    <th scope="col">Quantity</th>
                    <th scope="col">Price Unit</th>
                    <th scope="col">Price Total</th>
                    <th scope="col">Proveedor</th>
                    <th scope="col">Approve</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="item" items="${offers}">
                        <tr>
                            <td>${item.product} </td>
                            <td>${item.category} </td>
                            <td>${item.quantity} </td>
                            <td>${item.price} </td>
                            <td>${item.price * item.quantity} </td>
                            <td>${item.provider}</td>
                            <td>
                                <form method = "post" action="/quotes/admin/approve">
                                    <button type="submit" name="${item.idOffer}" class="mb-1 btn btn-outline-success">Aprove</button>
                                </form>
                                <form method = "post" action="/quotes/admin/decline">
                                    <button type="submit" name="${item.idOffer}" class = "btn btn-outline-danger">Decline</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </section>
    </main>
</body>
<jsp:include page="../templates/footer.html" />