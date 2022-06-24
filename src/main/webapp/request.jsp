<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.util.*, java.util.stream.Collectors, co.com.jorge.quotes.models.*" %>
<% String rol = (String) session.getAttribute("rol"); 
    List<Product> stock = (List<Product>) session.getAttribute("stock");
    List<Offer> offers = (List<Offer>) session.getAttribute("offers"); 
    List<RequestProduct> requests = (List<RequestProduct>) session.getAttribute("requests"); 
    String home = request.getParameter("home");
    String requestProduct = request.getParameter("request"); 
    String offersProduct = request.getParameter("offers");    
    String stockProduct = request.getParameter("stock");    
    String checkRequest = request.getParameter("checkRequest"); 
    String submitOffers = request.getParameter("submitOffers"); 
    String logout = request.getParameter("logout"); 
%>

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
        <link rel="stylesheet" href="./assets/css/request.css" />
        <script
            src="https://kit.fontawesome.com/9092a5f080.js"
            crossorigin="anonymous"
        ></script>
        <title>Quotes</title>
    </head>
    <body >

        <jsp:include page="./WEB-INF/templates/header-admin.jsp" />
            


        <main class="main">
            <h1 class="main__title">Requests and stock management at hand</h1>

            <%-- Home --%>
            <% if(home != null){ 
                requestProduct = null;
                offersProduct = null; %>
                <section ></section>
            <% } %> 

            <%-- Request Products --%>
            <% if(rol !=null && rol.equals("admin") && requestProduct != null) { 
                offersProduct = null; 
                stockProduct = null; %>
                <section class="container my-5 ">
                    <form class="row g-3 needs-validation" action="/quotes/request" method="post">
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
                            >
                            
                                <% for (Product product : stock) { %> 
                                    <option><%= product.getName() %></option>
                                <% } %>                          
                                                        
                            </select>
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
                            >

                                <% List<String> categoryList = new ArrayList<String>(); 
                                for (Product product : stock) { 
                                    categoryList.add(product.getCategory().getName()); }
                                    categoryList = categoryList.stream().distinct().collect(Collectors.toList());
                                    for (String category : categoryList) {
                                    %> 
                                    <option><%= category %></option>
                                <% } %>                          

                            </select>
                            <div class="valid-tooltip">Looks good!</div>
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
                        <div class="col-md-4 position-relative">
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
                        <div class="col-12">
                            <button class="btn btn-primary" type="submit">
                                Send Request
                            </button>
                        </div>
                    </form>
                </section> <% } %>

            <%-- Check Offers --%>
            <% if(rol != null && offersProduct != null){ 
                requestProduct = null; 
                stockProduct = null; %>
                                
                <section class="container mt-5 ">
                    <table class="table table-hover caption-top" title="Check Offers">
                        <caption>Check Offers</caption>
                        <thead>
                            <tr>
                            <th scope="col">#</th>
                            <th scope="col">Product</th>
                            <th scope="col">Category</th>
                            <th scope="col">Quantity</th>
                            <th scope="col">Price Unit</th>
                            <th scope="col">Price Total</th>
                            <th scope="col">Proveedor</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% for (int i = 0; i < offers.size(); i++) { %> 
                                <tr>
                                    <th scope="row"><%= i + 1  %></th>>
                                    <td><%= offers.get(i).getProduct() %></td>
                                    <td><%= offers.get(i).getCategory() %></td>
                                    <td><%= offers.get(i).getQuantity() %></td>
                                    <td><%= offers.get(i).getPrice() %></td>
                                    <td><%= offers.get(i).getPrice() * offers.get(i).getQuantity() %></td>
                                    <td><%= offers.get(i).getProvider() %></td>
                                </tr>                            
                            <% } %>
                        </tbody>
                    </table>
                </section>
            <% } %>
            
            <%-- Stock Products --%>
            <% if(rol != null && rol.equals("admin") && stockProduct != null){ 
                requestProduct = null;
                offersProduct = null; %>
                                
                <section class="container mt-5 mb-5 ">
                    <table class="table table-hover caption-top" title="Stock Products">
                        <caption>Stock Producst</caption>
                        <thead>
                            <tr>
                                <th scope="col">#</th>
                                <th scope="col">Name</th>
                                <th scope="col">Category</th>
                                <th scope="col">Stock</th>
                                <th scope="col">Price</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% for (int i = 0; i < stock.size(); i++) { %> 
                                <tr>
                                    <th scope="row"><%= i + 1  %></th>>
                                    <td><%= stock.get(i).getName() %></td>
                                    <td><%= stock.get(i).getCategory().getName() %></td>
                                    <td><%= stock.get(i).getStock() %></td>
                                    <td><%= stock.get(i).getPrice() %></td>
                                </tr>                            
                            <% } %>
                            
                        </tbody>
                    </table>
                </section>
            <% } %>

            <%-- Check Requests --%>
            <% if(rol != null && rol.equals("provider") && checkRequest != null) { 
                home = null; 
                submitOffers = null; %>
                
                <section class="container mt-5 mb-5 ">
                    <table class="table table-hover caption-top" title="Stock Products">
                        <caption>Stock Producst</caption>
                        <thead>
                            <tr>
                                <th scope="col">#</th>
                                <th scope="col">Product</th>
                                <th scope="col">Category</th>
                                <th scope="col">Quantity</th>
                                <th scope="col">Date Initial</th>
                                <th scope="col">Date Final</th>
                                <th scope="col">Price Unit</th>
                                <th scope="col">Send Offer</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% for (int i = 0; i < requests.size(); i++) { %> 
                                <tr>
                                    <th scope="row"><%= i + 1  %></th>>
                                    <td><%= requests.get(i).getProduct().getName() %></td>
                                    <td><%= requests.get(i).getCategory().getName() %></td>
                                    <td><%= requests.get(i).getQuantity() %></td>
                                    <td><%= requests.get(i).getInitialDate() %></td>
                                    <td><%= requests.get(i).getFinalDate() %></td>
                                    <td>
                                        <form method="post" action="/pruebas.html">
                                            <input type="number" class="form-control" placeholder="Unit Price Of Offer" aria-label="Recipient's username" aria-describedby="button-addon2">
                                            
                                            <td>
                                                <input type="submit" class="btn btn-outline-info" id="button-addon2" value="Send" />
                                            </td>
                                        </form>
                                    </td>
                                </tr>                            
                            <% } %>
                            
                        </tbody>
                    </table>
                </section>
            <% } %>

            <%-- Logout or Session finish --%>

            <% if(rol == null || logout != null){ 
                requestProduct = null; 
                offersProduct = null;    
                stockProduct = null;    
                checkRequest = null; 
                submitOffers = null; 
                stock = null; 
                offers = null; 
                requests = null;
                session.invalidate(); 
                response.sendRedirect(request.getContextPath());                
                } %>



        </main>

        <jsp:include page="./WEB-INF/templates/footer.html" />

        <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            crossorigin="anonymous"
        ></script>
    </body>
</html>
