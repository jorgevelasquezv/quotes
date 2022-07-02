<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- 
<%@page import="java.util.*, java.util.stream.Collectors, co.com.jorge.quotes.models.*" %>
<% 
    List<Product> stock = (List<Product>) session.getAttribute("stock");
%> 
--%>
<jsp:include page="../templates/header.jsp" />
<body>
    <main class="main">
        <section >
            <h1 class="main__title">Requests and stock management at hand</h1>  
        </section>
        <section  class="d-flex flex-column justify-content-center align-items-center mt-5 ">
            <h3 class="mb-4">Request Products</h3>
            <form class="d-flex flex-column g-3 needs-validation" action="/quotes/admin/request" method="post">
                <div class="row mb-3">
                    <div class="col-md-6 position-relative">
                        <label for="name" class="form-label"
                            >Product</label
                        >
                        <select
                            type="text"
                            class="form-select"
                            id="name"
                            name="name"
                            value="${param.name}"
                            required
                        >
                            <c:forEach var="product" items="${stock}">
                                <option>${product.name}</option>
                            </c:forEach>
                        </select>
                        <div class="valid-tooltip">Looks good!</div>
                    </div>
                    <div class="col-md-6 position-relative">
                        <label for="request" class="form-label"
                            >Request</label
                        >
                        <input
                            type="number"
                            class="form-control"
                            id="request"
                            name="request"
                            value="${param.request}"
                            required
                        />
                        <div class="invalid-tooltip">
                            Please provide a valid city.
                        </div>
                    </div>
                </div>
                <div class="row mb-3">
                    <div class="col-md-6 position-relative">
                        <label for="initialDate" class="form-label"
                            >Initial Date</label
                        >
                        <input
                            type="date"
                            class="form-control"
                            id="initialDate"
                            name="initialDate"
                            value="${param.initialDate}"
                            required
                        />
                        <div class="invalid-tooltip">
                            Please select a valid state.
                        </div>
                    </div>
                    <div class="col-md-6 position-relative">
                        <label for="finalDate" class="form-label"
                            >Final Date</label
                        >
                        <input
                            type="date"
                            class="form-control"
                            id="finalDate"
                            name="finalDate"
                            value="${param.finalDate}"
                            required
                        />
                        <div class="invalid-tooltip">
                            Please select a valid state.
                        </div>
                    </div>
                </div>
                <div class="col-12 mt-4">
                    <button class="btn btn-primary" type="submit">
                        Send Request
                    </button>
                </div>
            </form>
        </section> 
    </main>
</body>
<jsp:include page="../templates/footer.html" />
