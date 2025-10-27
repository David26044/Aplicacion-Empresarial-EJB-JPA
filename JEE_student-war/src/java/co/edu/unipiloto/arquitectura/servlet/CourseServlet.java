package co.edu.unipiloto.arquitectura.servlet;

import co.edu.unipiloto.arquitectura.entity.Course;
import co.edu.unipiloto.arquitectura.session.CourseFacadeLocal;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CourseServlet extends HttpServlet {

    @EJB
    private CourseFacadeLocal courseFacade;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        String codeStr = request.getParameter("courseCode");
        String name = request.getParameter("courseName");
        String creditsStr = request.getParameter("credits");
        String semesterStr = request.getParameter("semester");
        String admittedStr = request.getParameter("admitted");

        Integer code = (codeStr == null || codeStr.isEmpty()) ? null : Integer.valueOf(codeStr);
        Integer credits = (creditsStr == null || creditsStr.isEmpty()) ? null : Integer.valueOf(creditsStr);
        Integer semester = (semesterStr == null || semesterStr.isEmpty()) ? null : Integer.valueOf(semesterStr);
        Integer admitted = (admittedStr == null || admittedStr.isEmpty()) ? null : Integer.valueOf(admittedStr);

        Course course = (code != null) ? new Course(code, name, credits, semester, admitted) : null;

        if ("Add".equals(action) && course != null) {
            courseFacade.create(course);
        } else if ("Edit".equals(action) && course != null) {
            courseFacade.edit(course);
        } else if ("Delete".equals(action) && code != null) {
            Course c = courseFacade.find(code);
            if (c != null) {
                courseFacade.remove(c);
            }
        } else if ("Search".equals(action) && code != null) {
            course = courseFacade.find(code);
        }

        List<Course> all = courseFacade.findAll();
        request.setAttribute("course", course);
        request.setAttribute("allCourses", all);
        request.getRequestDispatcher("courseinfojsp.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet para manejo CRUD de cursos";
    }
}
