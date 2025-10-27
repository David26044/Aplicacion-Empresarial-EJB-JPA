package co.edu.unipiloto.arquitectura.servlet;

import co.edu.unipiloto.arquitectura.entity.Course;
import co.edu.unipiloto.arquitectura.entity.Student;
import co.edu.unipiloto.arquitectura.session.CourseFacadeLocal;
import co.edu.unipiloto.arquitectura.session.StudentFacadeLocal;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StudentServlet extends HttpServlet {

    @EJB
    private StudentFacadeLocal studentFacade;

    @EJB
    private CourseFacadeLocal courseFacade;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        String idStr = request.getParameter("studentId");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String yearLevelStr = request.getParameter("yearLevel");

        Integer id = (idStr == null || idStr.isEmpty()) ? null : Integer.valueOf(idStr);
        Integer yearLevel = (yearLevelStr == null || yearLevelStr.isEmpty()) ? null : Integer.valueOf(yearLevelStr);

        Student student = (id != null) ? new Student(id, firstName, lastName, yearLevel) : null;

        if ("Add".equals(action) && student != null) {
            studentFacade.create(student);
        } else if ("Edit".equals(action) && student != null) {
            studentFacade.edit(student);
        } else if ("Delete".equals(action) && id != null) {
            Student s = studentFacade.find(id);
            if (s != null) {
                studentFacade.remove(s);
            }
        } else if ("Search".equals(action) && id != null) {
            student = studentFacade.find(id);
        } else if ("Enroll".equals(action)) {
            String courseCodeStr = request.getParameter("courseCode");
            Integer courseCode = (courseCodeStr == null || courseCodeStr.isEmpty()) ? null : Integer.valueOf(courseCodeStr);

            if (id != null && courseCode != null) {
                Student s = studentFacade.find(id);
                Course  c = courseFacade.find(courseCode);

                if (s != null && c != null) {
                    Collection<Course> col = s.getCourseCollection();
                    if (col == null) {
                        col = new ArrayList<>();
                    }
                    if (!col.contains(c)) {
                        col.add(c);
                        s.setCourseCollection(col);
                        studentFacade.edit(s);
                    }
                    student = s; // para mostrarlo en la vista
                }
            }
        }

        List<Student> students = studentFacade.findAll();
        List<Course> allCourses = courseFacade.findAll();

        request.setAttribute("student", student);
        request.setAttribute("allStudents", students);
        request.setAttribute("allCourses", allCourses);
        request.getRequestDispatcher("studentinfojsp.jsp").forward(request, response);
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
        return "Servlet para manejar estudiantes y matrícula en cursos";
    }
}
