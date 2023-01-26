<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>ELibrary</title>
    <link rel="stylesheet" href="css/nav.css">
</head>
<body>
<nav id="navUl">
    <ul>
        <li><a class="active" href="login.jsp">Sign In</a></li>
        <li><a href="front?command=books">Catalog</a></li>
        <c:choose>
            <c:when test="${sessionScope.role == 'user'}">
                <li><a href="front?command=my_books">Ordered books</a> </li>
            </c:when>
            <c:when test="${sessionScope.role == 'librarian'}">
                <li><a href="front?command=show_orders">Readers' orders</a></li>
            </c:when>
        </c:choose>
        <li><a href="">Contact</a></li>
        <li style="float:right"><a href="front?command=logout">Log Out</a></li>
        <li style="float:right" ><a href="">About</a></li>
    </ul>
</nav>
<div class="main-div">
    <div class="welcome-box">
        <c:choose>
            <c:when test="${empty sessionScope.role}">
                <h2>Welcome to ELibrary!</h2>
            </c:when>
            <c:otherwise>
                <h2>Welcome back, ${sessionScope.role} ${sessionScope.name}!</h2>
            </c:otherwise>
        </c:choose>
    </div>
</div>

</body>
</html>

<style>
    .main-div {
        background-image: url(https://img.freepik.com/premium-photo/book-stack-library-room-blurred-bookshelf-background_42691-514.jpg?w=2000);
        /* filter: blur(2px); */
        /* -webkit-filter: blur(2px); */
        height: 100%;

        background-position: center;
        background-repeat: no-repeat;
        background-size: cover;
    }

    .welcome-box {
        font-weight: bold;
        font-size: x-large;
        position: absolute;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
        z-index: 2;
        width: 80%;
        padding: 20px;
        text-align: center;

    }
</style>