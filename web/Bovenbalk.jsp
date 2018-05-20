<%-- 
    Document   : Bovenbalk
    Created on : 15-mrt-2018, 20:37:51
    Author     : Dirk
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<nav class="top-navigatie">
    <div id="titel">
        <h1>Score on Web</h1>
    </div>
    <div id="nav-left">
        <!-- toon menu altijd behalve bij het menu -->
        <div id="menu">
            <a id="bt-menu" href="BeginMenu.jsp" title="menu"></a>
        </div>
        <img id="logo" src="images/LogoPCVO.png" width="165" height="50">
    </div>
    <div id="profiel" tabindex="1">
        <a id="bt-profiel">
            <i class="material-icons">person</i>
        </a>
        <ul class="dropdown">
            <li>
                <a id="bt-naam">
                    <i class="material-icons">account_box</i><span><c:out value = "${sessionScope.currentSessionUser.voorNaam}" /> </span>
                </a>
            </li>
            <li>
                <a href="Profiel.jsp" title="profiel">
                    <i class="material-icons">settings</i><span>Profiel</span>
                </a>
            </li>
            <li>
                <a href="LoginPage.jsp">
                    <i class="material-icons">exit_to_app</i><span>Afmelden</span>
                </a>
            </li>
        </ul>
    </div>
</nav>
<header>
    
</header>
