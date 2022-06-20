<%@page import="java.util.*" %>
<% String rol = (String) session.getAttribute("rol"); %>

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
                        <a class="nav-link active" aria-current="page" href="#"
                            >Home</a
                        >
                    </li>
                    <% if(rol.equals("admin")){ %>  
                    <li class="nav-item">
                        <form class="nav-link"  method="post" action="admin.jsp">
                            <input class="text-white bg-transparent border-0" type="submit" value="Request Products" name="request" />
                        </form>
                    </li>
                    <% } %>
                    <li class="nav-item">
                        <form class="nav-link"  method="post" action="/quotes/offers">
                            <input class="text-white bg-transparent border-0" type="submit" value="Check Offers" name="offers" />
                        </form>
                    </li>
                    <% if(rol.equals("admin")){ %>
                    <li class="nav-item">
                        <form class="nav-link"  method="post" action="/quotes/stock">
                            <input class="text-white bg-transparent border-0" type="submit" value="Stock Products" name="stock" />
                        </form>
                    </li>
                    <% }else{ %>
                    <li class="nav-item">
                        <form class="nav-link"  method="post" action="/quotes/submit-offers">
                            <input class="text-white bg-transparent border-0" type="submit" value="Submit Offers" name="submitOffers" />
                        </form>
                    </li>
                    <% } %>
                </ul>
            </section>
        </article>
    </nav>
</header>
