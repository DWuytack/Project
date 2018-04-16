<%-- 
    Document   : OverzichtStudiegebieden
    Created on : 30-mrt-2018, 11:10:04
    Author     : CURSIST
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/TreeMenu.css">
        <script src="js/TreeMenu.js" type="text/javascript"></script>
        <title>JSP Page</title>
    </head>
    <body>
        <input type="submit" name="Studiegebieden" value="Studiegebieden">
        <input type="submit" name="Opleidingen" value="Opleidingen">
        <input type="submit" name="Modules" value="Modules">
        <input type="submit" name="Doelstellingen" value="Doelstellingen">
        <input type="submit" name="Taken" value="Taken">
            <div class="menu">
                <ul id="TreeMenu">
                    <li>Studiegebied 1
                        <ul>
                            <li>Opleiding 1
                                <ul>
                                    <li>Module 1
                                        <ul>
                                            <li>Doelstelling 1
                                                <ul>
                                                    <li>Taak 1</li>
                                                    <li>Taak 2</li>
                                                </ul>
                                            </li>
                                        </ul>
                                    </li>
                                </ul>
                            </li>
                            <li>Opleiding 2</li>
                            <li>Opleiding 3</a></li>
                        </ul>
                    </li>
                    <li>Studiegebied 2
                        <ul>
                            <li>Opleiding 1</li>
                            <li>Opleiding 2
                                <ul>
                                    <li>Module 1</li>
                                    <li>Module 2</li>
                                    <li>Module 3</li>
                                </ul>
                            </li>
                        </ul>
                    </li>
                </ul>
                <script type="text/javascript">make_tree_menu('TreeMenu');</script>
            </div>

        
    </body>
</html>
