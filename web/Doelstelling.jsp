<%-- 
    Document   : Doelstelling
    Created on : 8-mrt-2018, 11:05:14
    Author     : Ewout phlips
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/theme.css">
        <title>Doelstellingen</title>
    </head>
<body>
    <h2>Doelstellingen</h2>
    <table>
        <tr>
            <th>Select</th>
            <th>Doelstelling</th>
            <th>Beschrijving</th>
            <th>Kerndoelstelling</th>
        </tr>
        <tr>
            <td><input id="doelstelling1" type="checkbox"></td>
            <td>Naam van doelstelling 1</td>
            <td>Hier komt de beschrijving van doelstelling 1</td>
            <td>Ja/Nee</td>
        </tr>
        <tr>
            <td><input id="doelstelling2" type="checkbox"></td>
            <td>Naam van doelstelling 2</td>
            <td>Hier komt de beschrijving van doelstelling 2</td>
            <td>Ja/Nee</td>
        </tr>
    </table>

    <br>
    <input type="button" value="Doelstelling Aanmaken" name="aanmaken"/>
    <input type="button" value="Doelstelling Aanpassen" name="aanpassen"/>
    <input type="button" value="Doelstelling Verwijderen" name="verwijderen"/>

</body>
</html>
