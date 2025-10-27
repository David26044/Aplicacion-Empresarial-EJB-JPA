package co.edu.unipiloto.arquitectura.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "COURSE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Course.findAll", query = "SELECT c FROM Course c"),
    @NamedQuery(name = "Course.findByCourseCode", query = "SELECT c FROM Course c WHERE c.courseCode = :courseCode"),
    @NamedQuery(name = "Course.findByCourseName", query = "SELECT c FROM Course c WHERE c.courseName = :courseName")
})
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "COURSE_CODE")
    private Integer courseCode;

    @Size(max = 100)
    @Column(name = "COURSE_NAME")
    private String courseName;

    @Column(name = "CREDITS")
    private Integer credits;

    @Column(name = "SEMESTER")
    private Integer semester;

    @Column(name = "ADMITTED_STUDENTS")
    private Integer admittedStudents;

    // ====== M:N inverso ======
    @ManyToMany(mappedBy = "courseCollection")
    private Collection<Student> studentCollection;

    public Course() { }

    public Course(Integer courseCode, String courseName, Integer credits, Integer semester, Integer admittedStudents) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.credits = credits;
        this.semester = semester;
        this.admittedStudents = admittedStudents;
    }

    public Integer getCourseCode() { return courseCode; }
    public void setCourseCode(Integer courseCode) { this.courseCode = courseCode; }

    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }

    public Integer getCredits() { return credits; }
    public void setCredits(Integer credits) { this.credits = credits; }

    public Integer getSemester() { return semester; }
    public void setSemester(Integer semester) { this.semester = semester; }

    public Integer getAdmittedStudents() { return admittedStudents; }
    public void setAdmittedStudents(Integer admittedStudents) { this.admittedStudents = admittedStudents; }

    public Collection<Student> getStudentCollection() { return studentCollection; }
    public void setStudentCollection(Collection<Student> studentCollection) { this.studentCollection = studentCollection; }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (courseCode != null ? courseCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Course)) return false;
        Course other = (Course) object;
        if ((this.courseCode == null && other.courseCode != null) ||
            (this.courseCode != null && !this.courseCode.equals(other.courseCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.unipiloto.arquitectura.entity.Course[ courseCode=" + courseCode + " ]";
    }
}
