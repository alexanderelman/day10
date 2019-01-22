<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Address List</title>
        <style>
            table {border-collapse: collapse; border: 1px solid black;}
            td {margin: 5px; padding: 5px; border: 1px solid black;}
            li {padding: 10px; list-style-type: none;}
            .error {color:red;}
        </style>
    </head>
    <body>
        <c:choose>
            <c:when test="${not empty addrs}">
        <h1>Your Address List</h1>                
            </c:when>
            <c:otherwise>
        <h1>There are no addresses in your list. Yet.</h1>
            </c:otherwise>
        </c:choose>
            <c:if test="${not empty flash}">
        <h2 class="error">${flash}</h2>
            </c:if>
        <form method="POST" action="main">
            <input type="hidden" name="action" value="alter"/>
            <ul>
                <c:forEach var="addr" items="${addrs}" varStatus="status">
                    <li>
                        <input type="checkbox" name="alterme" value="${status.index}"/>
                        Select to save changes to this address
                        <table width="30%">
                            <tr>
                                <td>First Name:</td>
                                <td>
                                    <input type="text" name="firstName${status.index}"
                                           value="${addr.firstName}"/>
                                </td>
                            </tr>
                            <tr>
                                <td>Last Name:</td>
                                <td>
                                    <input type="text" name="lastName${status.index}"
                                           value="${addr.lastName}"/>
                                </td>
                            </tr>
                            <tr>
                                <td>Street:</td>
                                <td>
                                    <input type="text" name="street${status.index}"
                                           value="${addr.street}"/>
                                </td>
                            </tr>
                            <tr>
                                <td>City</td>
                                <td>
                                    <input type="text" name="city${status.index}"
                                           value="${addr.city}"/>
                                </td>
                            </tr>
                            <tr>
                                <td>State</td>
                                <td>
                                    <input type="text" name="state${status.index}"
                                           value="${addr.state}"/>
                                </td>
                            </tr>
                            <tr>
                                <td>Zip</td>
                                <td>
                                    <input type="text" name="zip${status.index}"
                                           value="${addr.zip}"/>
                                </td>
                            </tr>
                        </table>
                    </li>    
                </c:forEach>
            </ul>
            <input type="submit" name="add" value="Add a New Address"/>
            <c:if test="${not empty addrs}">
            | <input type="submit" name="delete" value="Delete Selected Addresses"/>
            | <input type="submit" name="update" value="Update Edited Addresses"/>
            | <input type="submit" name="logout" value="Log Out and Delete Contacts"/>
            </c:if>
        </form>
    </body>
</html>
