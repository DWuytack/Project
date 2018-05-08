<%-- 
    Document   : OverzichtStudiegebieden
    Created on : 30-mrt-2018, 11:10:04
    Author     : CURSIST
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>

    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <script src="js/jquery.js"></script>
        <script src="js/nav.js"></script>
        <script src="js/table.js"></script>
        <script src="js/studiegebieden.js"></script>
        <link rel="stylesheet" href="css/theme.css">
        <link rel="stylesheet" href="css/studiegebieden.css">
        <title>StudieGebieden</title>
    </head>
    <body>
        <%@include file="Bovenbalk.jsp" %>
        <!-- styling verplaatst naar theme.css "SUBNAV" -->
        <section id="pagename">
            <h2> OverzichtStudiegebieden </h2>
            <!-- <h1 align="center" class="kleur" style="display: inherit;"> OverzichtStudiegebieden </h1> -->

        </section>

        <section>
            <form action="StudiegebiedenServlet">
                <script>
                    var data = {
                        opleiding: {
                            gebied1: [
                                {
                                    value: "opleiding1a",
                                    view: "opleiding1"
                                },
                                {
                                    value: "opleiding2a",
                                    view: "opleiding2"
                                },
                                {
                                    value: "opleiding3a",
                                    view: "opleiding3"
                                },
                                {
                                    value: "opleiding4a",
                                    view: "opleiding4"
                                }
                            ],
                            gebied2: [{
                                    value: "opleiding1b",
                                    view: "opleiding1b"
                                },
                                {
                                    value: "opleiding2b",
                                    view: "opleiding2b"
                                }
                            ]

                        },
                        module: {
                            opleiding1a: [
                                {
                                    value: "module1a",
                                    view: "module1"
                                },
                                {
                                    value: "module2a",
                                    view: "module2"
                                },
                                {
                                    value: "module3a",
                                    view: "module3"
                                },
                                {
                                    value: "module4a",
                                    view: "module4"
                                }
                            ],
                            opleiding2a: [
                                {
                                    value: "module1b",
                                    view: "module1b"
                                },
                                {
                                    value: "module2b",
                                    view: "module2b"
                                }
                            ],
                            opleiding1b: [
                                {
                                    value: "module1a",
                                    view: "module1"
                                },
                                {
                                    value: "module2a",
                                    view: "module2"
                                },
                                {
                                    value: "module3a",
                                    view: "module3"
                                },
                                {
                                    value: "module4a",
                                    view: "module4"
                                }
                            ],
                            opleiding2b: [
                                {
                                    value: "module1b",
                                    view2: "module1b"
                                },
                                {
                                    value: "module2b",
                                    view: "module2b"
                                }
                            ]
                        },
                        doelstelling: {
                            module1a: [
                                {
                                    value: "doelstelling1a",
                                    view: "doelstelling1"
                                },
                                {
                                    value: "doelstelling2a",
                                    view: "doelstelling2"
                                },
                                {
                                    value: "doelstelling3a",
                                    view: "doelstelling3"
                                },
                                {
                                    value: "doelstelling4a",
                                    view: "doelstelling4"
                                }
                            ],
                            module2a: [
                                {
                                    value: "doelstelling1b",
                                    view: "doelstelling1b"
                                },
                                {
                                    value: "doelstelling2b",
                                    view: "doelstelling2b"
                                }
                            ],
                            module1b: [
                                {
                                    value: "doelstelling1a",
                                    view: "doelstelling1"
                                },
                                {
                                    value: "doelstelling2a",
                                    view: "doelstelling2"
                                },
                                {
                                    value: "doelstelling3a",
                                    view: "doelstelling3"
                                },
                                {
                                    value: "doelstelling4a",
                                    view: "doelstelling4"
                                }
                            ],
                            module2b: [
                                {
                                    value: "doelstelling1b",
                                    view2: "doelstelling1b"
                                },
                                {
                                    value: "doelstelling2b",
                                    view: "doelstelling2b"
                                }
                            ]
                        }

                    };
                    console.log(data);
                </script>
                </head>

                <body>

                    <section>
                        <div id="menu">
                            <div role="menu-header">
                                <select name="studiegebieden">
                                    <option value="0">Kies Uw StudieGebied</option>
                                    <option value="gebied1">Gebied1</option>
                                    <option value="gebied2">Gebied2</option>
                                </select>
                            </div>
                            <div role="menu-content">
                                <div role="menu-header">
                                    <select name="opleidingen"></select>
                                </div>
                                <div role="menu-content">
                                    <div role="menu-header">
                                        <!--<a class="toggle">Kies Uw Module </a>-->
                                    </div>
                                    <div role="menu-content">
                                        <div role="menu-header">
                                            <!-- <a class="toggle">Doelstelling</a>-->
                                        </div>
                                        <div role="menu-content">
                                            <div role="menu-header">
                                                <a class="toggle">Taken</a>
                                                <option value="taak1">taak</option>
                                                <option value="taak2">taak2</option>
                                            </div>
                                            <div role="menu-content">
                                                <div role="menu-header">
                                                    <a class="toggle">Score</a>
                                                    <option value="Score">Score</option>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </section>
                </body>
            </form>
        </section>
    </body>
</html>
