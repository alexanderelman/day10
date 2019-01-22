<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Address App</title>
        <style>
            table {border-collapse: collapse; border: 1px solid black;}
            td {border: 1px solid black; margin: 5px; padding: 5px;}
            .error {color: red;}
        </style>
    </head>
    <body>
        <h1>Address Collection Application</h1>
        <c:if test="${not empty flash}">
        <h2 class="error">${flash}</h2>    
        </c:if>
        <p>
            Address count is currently ${count}.
            Please enter your address details below.
        </p>
        <form method="POST" action="main">
            <input type="hidden" name="action" value="add"/>
            <table>
                <tr>
                    <td>First Name:</td>
                    <td><input type="text" name="firstName"/></td>
                </tr>
                <tr>
                    <td>Last Name:</td>
                    <td><input type="text" name="lastName"/></td>
                </tr>
                <tr>
                    <td>Street:</td>
                    <td><input type="text" name="street"/></td>
                </tr>
                <tr>
                    <td>City</td>
                    <td><input type="text" name="city"/></td>
                </tr>
                <tr>
                    <td>State</td>
                    <td><input type="text" name="state"/></td>
                </tr>
                <tr>
                    <td>Zip</td>
                    <td><input type="text" name="zip"/></td>
                </tr>
                <tr><td colspan="2"><input type="submit"/></td></tr>
            </table>
        </form>
        <p><a href="main?action=list">View my Contacts</a></p>
        <p><a href="main?action=logout">Log Out and Delete Contacts List</a></p>
    </body>
</html>
