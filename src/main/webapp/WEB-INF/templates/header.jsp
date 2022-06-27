<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/request.css" />
        <script
            src="https://kit.fontawesome.com/9092a5f080.js"
            crossorigin="anonymous"
        ></script>
        <title>Quotes</title>
    </head>

<header class="header">
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
                        <form class="nav-link"  method="post" action="/quotes/${rol}/home">
                            <input class="text-white bg-transparent border-0" type="submit" value="Home" name="home" />
                        </form>
                    </li>

                    <c:if test="${rol == 'admin'}">
                        <li class="nav-item">
                            <form class="nav-link"  method="post" action="/quotes/admin/request-form">
                                <input class="text-white bg-transparent border-0" type="submit" value="Request Products" name="request" />
                            </form>
                        </li>
                        
                        <li class="nav-item">
                            <form class="nav-link"  method="post" action="/quotes/admin/offers">
                                <input class="text-white bg-transparent border-0" type="submit" value="Check Offers" name="offers" />
                            </form>
                        </li>

                        <li class="nav-item">
                            <form class="nav-link"  method="post" action="/quotes/admin/check-request">
                                <input class="text-white bg-transparent border-0" type="submit" value="Check Requests" name="checkRequest" />
                            </form>
                        </li>

                    </c:if>
                    
                    <c:if test="${rol == 'provider'}">
                        <li class="nav-item">
                            <form class="nav-link"  method="post" action="/quotes/provider/request">
                                <input class="text-white bg-transparent border-0" type="submit" value="Check Requests" name="checkRequest" />
                            </form>
                        </li>
                    </c:if>

                    <c:if test="${rol == 'admin'}">
                        <li class="nav-item">
                            <form class="nav-link"  method="post" action="/quotes/admin/stock">
                                <input class="text-white bg-transparent border-0" type="submit" value="Stock Products" name="stock" />
                            </form>
                        </li>
                    </c:if>
                    
                    <c:if test="${rol == 'provider'}">
                        <li class="nav-item">
                            <form class="nav-link"  method="post" action="/quotes/provider/approved">
                                <input class="text-white bg-transparent border-0" type="submit" value="Approved Orders" name="approvedOffers" />
                            </form>
                        </li>
                    </c:if>

                </ul>
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <form class="nav-link" method="post" action="${pageContext.request.contextPath}/logout">
                            <input class="text-white bg-transparent border-0" type="submit" value="Logout" name="logout" />
                        </form>
                    </li>
                </ul>
            </section>
        </article>
    </nav>
</header>
