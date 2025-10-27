<%-- 
    Document   : courseinfojsp.jsp
    Created on : 27/10/2025, 07:41:52 AM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Course Information</title>
    </head>
    <body>
        <h1>Course Information</h1>
        <form action="./CourseServlet" method="POST">
            <table>
                <tr>
                    <td>Course Code</td>
                    <td><input type="text" name="courseCode" value="${course.courseCode}" /></td>
                </tr>
                <tr>
                    <td>Course Name</td>
                    <td><input type="text" name="courseName" value="${course.courseName}" /></td>
                </tr>
                <tr>
                    <td>Credits</td>
                    <td><input type="text" name="credits" value="${course.credits}" /></td>
                </tr>
                <tr>
                    <td>Semester</td>
                    <td><input type="text" name="semester" value="${course.semester}" /></td>
                </tr>
                <tr>
                    <td>Admitted Students</td>
                    <td><input type="text" name="admitted" value="${course.admittedStudents}" /></td>
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
                <th>Code</th>
                <th>Name</th>
                <th>Credits</th>
                <th>Semester</th>
                <th>Admitted Students</th>
            </tr>
            <c:forEach items="${allCourses}" var="c">
                <tr>
                    <td>${c.courseCode}</td>
                    <td>${c.courseName}</td>
                    <td>${c.credits}</td>
                    <td>${c.semester}</td>
                    <td>${c.admittedStudents}</td>
                </tr>
            </c:forEach>
        </table>

        <br><a href="studentinfojsp.jsp">Go to Student Management</a>
    </body>
</html>

