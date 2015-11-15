<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Devices</title>
    <link rel="stylesheet" href="../style/styles.css">
</head>
<body class="secbody">

    <div>
        <table align="center">
            <tr>
                <th rowspan="3">ID</th>
                <th rowspan="3">Name</th>
                <th rowspan="3">Origin</th>
                <th rowspan="3">Price</th>
                <th colspan="6">Type</th>
                <th rowspan="3">Critical</th>
            </tr>
            <tr>
                <th rowspan="2">Peripheral</th>
                <th rowspan="2">Power</th>
                <th rowspan="2">Ports</th>
                <th colspan="3">Hardware</th>
            </tr>
            <tr>
                <th>Keyboard</th>
                <th>Mouse</th>
                <th>Speakers</th>
            </tr>
            <c:forEach var="computer" items="${devices}" >
                <tr>
                    <td>${computer.id}</td>
                    <td>${computer.name}</td>
                    <td>${computer.origin}</td>
                    <td>${computer.price}</td>
                    <td>${computer.type.peripheral}</td>
                    <td>${computer.type.power}</td>
                    <td>
                        <c:forEach var="port" items="${computer.type.ports}">
                            <c:out value="${port.portName}"></c:out>
                        </c:forEach>
                    </td>
                    <td>${computer.type.hardware.hasKeyboard}</td>
                    <td>${computer.type.hardware.hasMouse}</td>
                    <td>${computer.type.hardware.hasSpeakers}</td>
                    <td>${computer.critical}</td>
                </tr>
            </c:forEach>
        </table>

        <br/>

        <table align="center" border="1" cellpadding="5" cellspacing="5">
            <tr>
                <c:forEach begin="1" end="${noOfPages}" var="i">
                    <c:choose>
                        <c:when test="${currentPage eq i}">
                            <td>${i}</td>
                        </c:when>
                        <c:otherwise>
                            <td>
                                <form action="controller" enctype="multipart/form-data" method="post">
                                    <input type="hidden" name="command" value="load_info_command"/>
                                    <input type="hidden" name="recordsPerPage" value="5"/>
                                    <input type="hidden" name="page" value="${i}"/>
                                    <input class="btnpage" type="submit" value="${i}"/>
                                </form>
                            </td>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </tr>
        </table>

        <a class="btnback" href="../index.jsp">home</a>

    </div>

</body>
</html>
