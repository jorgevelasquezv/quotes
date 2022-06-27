    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../templates/header.jsp" />

<body>
    <main class="main">
        <section >
            <h1 class="main__title">Requests and stock management at hand</h1>  
        </section>
        <section class="container mt-5 ">
            <table class="table table-hover caption-top" title="Check Approved Offers">
                <caption>Check Approved Offers</caption>
                <thead>
                    <tr>
                    <th scope="col">#</th>
                    <th scope="col">Product</th>
                    <th scope="col">Category</th>
                    <th scope="col">Quantity</th>
                    <th scope="col">Price Unit</th>
                    <th scope="col">Price Total</th>
                    <th scope="col">Provider</th>
                    </tr>
                </thead>
                <tbody>
                    <%-- <% for (int i = 0; i < offers.size(); i++) { %> 
                        <tr>
                            <th scope="row"><%= i + 1  %></th>>
                            <td><%= offers.get(i).getProduct() %></td>
                            <td><%= offers.get(i).getCategory() %></td>
                            <td><%= offers.get(i).getQuantity() %></td>
                            <td><%= offers.get(i).getPrice() %></td>
                            <td><%= offers.get(i).getPrice() * offers.get(i).getQuantity() %></td>
                            <td><%= offers.get(i).getProvider() %></td>
                        </tr>                            
                    <% } %> --%>
                </tbody>
            </table>
        </section>
    </main>
</body>
<jsp:include page="../templates/footer.html" />