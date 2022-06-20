<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.util.*" %>
<% String rol = (String) session.getAttribute("rol"); 
    String requestProduct = request.getParameter("request"); %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
            crossorigin="anonymous"
        />
        <link rel="stylesheet" href="./assets/css/admin.css" />
        <script
            src="https://kit.fontawesome.com/9092a5f080.js"
            crossorigin="anonymous"
        ></script>
        <title>Quotes</title>
    </head>
    <body class="vh-100">
        <%-- <header class="header">
            <nav
                class="navbar navbar-expand-lg navbar-dark"
                style="background-color: var(--background)"
            >
                <article class="container-fluid">
                    <a class="navbar-brand" href="#">Quotes</a>
                    <button
                        class="navbar-toggler"
                        type="button"
                        data-bs-toggle="collapse"
                        data-bs-target="#navbarNav"
                        aria-controls="navbarNav"
                        aria-expanded="false"
                        aria-label="Toggle navigation"
                    >
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <section class="collapse navbar-collapse" id="navbarNav">
                        <ul class="navbar-nav">
                            <li class="nav-item">
                                <a
                                    class="nav-link active"
                                    aria-current="page"
                                    href="#"
                                    >Home</a
                                >
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#"
                                    >Request Products</a
                                >
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#">Check Offers</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link">Stock Products</a>
                            </li>
                        </ul>
                    </section>
                </article>
            </nav>
        </header> --%>
        <jsp:include page="./WEB-INF/templates/header-admin.jsp" />
            


        <main class="main">
            <h1 class="main__title">Requests and stock management at hand</h1>

            <% if(rol.equals("admin") && requestProduct != null) { %>
                <section class="container mt-5">
                    <form class="row g-3 needs-validation" novalidate>
                        <div class="col-md-4 position-relative">
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
                            ><option selected value="">Choose...</option>
                                <option>...</option></select>
                            <div class="valid-tooltip">Looks good!</div>
                        </div>
                        <div class="col-md-4 position-relative">
                            <label for="category" class="form-label"
                                >Category</label
                            >
                            <select
                                type="text"
                                class="form-select"
                                id="category"
                                name="category"
                                value="${param.category}"
                                required
                            ><option selected value="">Choose...</option>
                                <option>...</option></select>
                            <div class="valid-tooltip">Looks good!</div>
                        </div>
                        <div class="col-md-4 position-relative">
                            <label for="stock" class="form-label"
                                >Stock</label
                            >
                            <input
                                type="number"
                                class="form-control"
                                id="stock"
                                name="stock"
                                value="${param.stock}"
                                required
                            />
                                <div class="invalid-tooltip">
                                    Please choose a unique and valid username.
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4 position-relative">
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
                        <div class="col-md-4 position-relative">
                            <label for="initialDate" class="form-label"
                                >Initial Date</label
                            >
                            <input
                                type="datetime-local"
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
                        <div class="col-md-4 position-relative">
                            <label for="finalDate" class="form-label"
                                >Final Date</label
                            >
                            <input
                                type="datetime-local"
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
                        <div class="col-12">
                            <button class="btn btn-primary" type="submit">
                                Submit form
                            </button>
                        </div>
                    </form>
                </section> <% } %>
            

        </main>

        <%-- <footer
            class="text-center text-white bottom-0 position-absolute w-100"
            style="background-color: var(--background-ligth)"
        >
            <!-- Grid container -->
            <div class="container pt-4">
                <!-- Section: Social media -->
                <section class="mb-4">
                    <!-- Facebook -->
                    <a
                        class="btn btn-link btn-floating btn-lg text-white m-1"
                        href="#!"
                        role="button"
                        data-mdb-ripple-color="dark"
                        ><i class="fab fa-facebook-f"></i
                    ></a>

                    <!-- Twitter -->
                    <a
                        class="btn btn-link btn-floating btn-lg text-white m-1"
                        href="#!"
                        role="button"
                        data-mdb-ripple-color="dark"
                        ><i class="fab fa-twitter"></i
                    ></a>

                    <!-- Google -->
                    <a
                        class="btn btn-link btn-floating btn-lg text-white m-1"
                        href="#!"
                        role="button"
                        data-mdb-ripple-color="dark"
                        ><i class="fab fa-google"></i
                    ></a>

                    <!-- Instagram -->
                    <a
                        class="btn btn-link btn-floating btn-lg text-white m-1"
                        href="#!"
                        role="button"
                        data-mdb-ripple-color="dark"
                        ><i class="fab fa-instagram"></i
                    ></a>

                    <!-- Linkedin -->
                    <a
                        class="btn btn-link btn-floating btn-lg text-white m-1"
                        href="#!"
                        role="button"
                        data-mdb-ripple-color="dark"
                        ><i class="fab fa-linkedin"></i
                    ></a>
                    <!-- Github -->
                    <a
                        class="btn btn-link btn-floating btn-lg text-white m-1"
                        href="#!"
                        role="button"
                        data-mdb-ripple-color="dark"
                        ><i class="fab fa-github"></i
                    ></a>
                </section>
                <!-- Section: Social media -->
            </div>
            <!-- Grid container -->

            <!-- Copyright -->
            <div
                class="text-center text-white p-3"
                style="background-color: var(--background)"
            >
                © 2020 Copyright:
                <a class="text-white" href="https://github.com/jorgevelasquezv/quotes"
                    >Jorge Velasquez github.com</a
                >
            </div>
            <!-- Copyright -->
        </footer> --%>
        <jsp:include page="./WEB-INF/templates/footer.html" />

        <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            crossorigin="anonymous"
        ></script>
    </body>
</html>
