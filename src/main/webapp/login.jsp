<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%  String admin = request.getParameter("admin");
    String provider = request.getParameter("provider"); %>
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

        <title>Login</title>
    </head>
    <body class="min-vh-100">
        <section style="min-height: 78vh;">
            <div class="container-fluid h-custom">
                <div
                    class="row d-flex justify-content-center align-items-center h-100"
                >
                    <div class="col-md-9 col-lg-6 col-xl-5">
                        <img
                            src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-login-form/draw2.webp"
                            class="img-fluid"
                            alt="Sample image"
                        />
                    </div>
                    <div class="col-md-8 col-lg-6 col-xl-4 offset-xl-1">
                        <% if(admin != null){%> <form  action= "/quotes/login-admin" method = "post"> <%}else{%> <form action= "/quotes/login-provider" method = "post"><%}; %> 
                            
                            <div class="divider d-flex align-items-center my-4 justify-content-center">
                                <h3 class="text-center fw-bold mx-3 mb-0"><% if(admin != null){
                                    %><%= admin%><%}else{
                                        %><%=provider%><%};%></h3>
                            </div>

                            <!-- Username input -->
                            <div class="form-outline mb-4">
                                <input
                                    type="text"
                                    id="username"
                                    name="username"
                                    class="form-control form-control-lg"
                                    placeholder="Enter username"
                                    required
                                />
                                <label class="form-label" for="username"
                                    >Username</label
                                >
                            </div>

                            <!-- Password input -->
                            <div class="form-outline mb-3">
                                <input
                                    type="password"
                                    id="password"
                                    name="password"
                                    class="form-control form-control-lg"
                                    placeholder="Enter password"
                                    required
                                />
                                <label class="form-label" for="password"
                                    >Password</label
                                >
                            </div>

                            <div class="d-grid gap-2 text-center text-lg-start mt-4 pt-2">
                                <button
                                    type="submit"
                                    class="btn btn-primary btn-lg"
                                    style="
                                        padding-left: 2.5rem;
                                        padding-right: 2.5rem;
                                    "
                                >
                                    Login
                                </button>
                                <p class="small fw-bold mt-2 pt-1 mb-0">
                                    Don't have an account?
                                    <a href="#!" class="link-danger"
                                        >Register</a
                                    >
                                </p>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            

<%--             
            <div
                class="bottom-0 position-absolute w-100 py-4 px-4 px-xl-5"
            >
            </div> --%>

        </section>
            

                <jsp:include page="./WEB-INF/templates/footer.html" />
        <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            crossorigin="anonymous"
        ></script>
    </body>
</html>
