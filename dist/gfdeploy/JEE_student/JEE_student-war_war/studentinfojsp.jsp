<%-- 
    Document   : studentinfojsp
    Created on : 27/10/2025, 12:37:44 AM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Student Information</title>
    </head>
    <body>
        <h1>Student Information</h1>
        <form action="./StudentServlet" method="POST">
            <table>
                <tr>
                    <td>Student Id</td>
                    <td><input type="text" name="studentId" value="${student.studentId}" /></td>
                </tr>
                <tr>
                    <td>First Name</td>
                    <td><input type="text" name="firstName" value="${student.firstName}" /></td>
                </tr>
                <tr>
                    <td>Last Name</td>
                    <td><input type="text" name="lastName" value="${student.lastName}" /></td>
                </tr>
                <tr>
                    <td>Year Level</td>
                    <td><input type="text" name="yearLevel" value="${student.yearLevel}" /></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit" name="action" value="Add" />
                        <input type="submit" name="action" value="Edit" />
                        <input type="submit" name="action" value="Delete" />
                        <input type="submit" name="action" value="Search" />
                    </td>
                </tr>
            </table>
        </form>

        <br>
        <table border="1">
            <tr>
                <th>ID</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Year Level</th>
            </tr>
            <c:forEach items="${allStudents}" var="stud">
                <tr>
                    <td>${stud.studentId}</td>
                    <td>${stud.firstName}</td>
                    <td>${stud.lastName}</td>
                    <td>${stud.yearLevel}</td>
                </tr>
            </c:forEach>
        </table>

        <br><hr><br>

        <h2>Enroll Student in Course</h2>
        <form action="./StudentServlet" method="POST">
            <table>
                <tr>
                    <td>Student Id</td>
                    <td><input type="text" name="studentId" value="${student.studentId}" /></td>
                </tr>
                <tr>
                    <td>Course Code</td>
                    <td>
                        <select name="courseCode">
                            <c:forEach items="${allCourses}" var="c">
                                <option value="${c.courseCode}">${c.courseCode} - ${c.courseName}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit" name="action" value="Enroll" />
                    </td>
                </tr>
            </table>
        </form>

        <br>
        <c:if test="${student != null}">
            <h3>Courses of Student ${student.studentId}</h3>
            <table border="1">
                <tr><th>Code</th><th>Name</th><th>Credits</th></tr>
                        <c:forEach items="${student.courseCollection}" var="c">
                    <tr>
                        <td>${c.courseCode}</td>
                        <td>${c.courseName}</td>
                        <td>${c.credits}</td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>


        <br><a href="courseinfojsp.jsp">Go to Course Management</a>
    </body>
</html>
