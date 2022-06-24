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
                        <form class="nav-link"  method="post" action="request.jsp">
                            <input class="text-white bg-transparent border-0" type="submit" value="Home" name="home" />
                        </form>
                    </li>
                    <% if(rol != null && rol.equals("admin")){ %>  
                    <li class="nav-item">
                        <form class="nav-link"  method="post" action="request.jsp">
                            <input class="text-white bg-transparent border-0" type="submit" value="Request Products" name="request" />
                        </form>
                    </li>
                    
                    <li class="nav-item">
                        <form class="nav-link"  method="post" action="request.jsp">
                            <input class="text-white bg-transparent border-0" type="submit" value="Check Offers" name="offers" />
                        </form>
                    </li>

                    <% }else{ %>
                    
                    <li class="nav-item">
                        <form class="nav-link"  method="post" action="request.jsp">
                            <input class="text-white bg-transparent border-0" type="submit" value="Check Requests" name="checkRequest" />
                        </form>
                    </li>

                    <% } %>

                    <% if(rol != null && rol.equals("admin")){ %>
                    <li class="nav-item">
                        <form class="nav-link"  method="post" action="request.jsp">
                            <input class="text-white bg-transparent border-0" type="submit" value="Stock Products" name="stock" />
                        </form>
                    </li>
                    <% }else{ %>
                    <li class="nav-item">
                        <form class="nav-link"  method="post" action="request.jsp">
                            <input class="text-white bg-transparent border-0" type="submit" value="Submit Offers" name="submitOffers" />
                        </form>
                    </li>
                    <% } %>
                </ul>
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <form class="nav-link" method="post" action="request.jsp">
                            <input class="text-white bg-transparent border-0" type="submit" value="Logout" name="logout" />
                        </form>
                    </li>
                </ul>
            </section>
        </article>
    </nav>
</header>
