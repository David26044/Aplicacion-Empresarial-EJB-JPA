package co.edu.unipiloto.arquitectura.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "STUDENT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Student.findAll", query = "SELECT s FROM Student s"),
    @NamedQuery(name = "Student.findByStudentId", query = "SELECT s FROM Student s WHERE s.studentId = :studentId"),
    @NamedQuery(name = "Student.findByFirstName", query = "SELECT s FROM Student s WHERE s.firstName = :firstName"),
    @NamedQuery(name = "Student.findByLastName", query = "SELECT s FROM Student s WHERE s.lastName = :lastName"),
    @NamedQuery(name = "Student.findByYearLevel", query = "SELECT s FROM Student s WHERE s.yearLevel = :yearLevel")
})
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "STUDENT_ID")
    private Integer studentId;

    @Size(max = 50)
    @Column(name = "FIRST_NAME")
    private String firstName;

    @Size(max = 50)
    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "YEAR_LEVEL")
    private Integer yearLevel;

    // ====== M:N dueño ======
    @ManyToMany
    @JoinTable(
        name = "STUDENT_COURSE",
        joinColumns = @JoinColumn(name = "STUDENT_ID", referencedColumnName = "STUDENT_ID"),
        inverseJoinColumns = @JoinColumn(name = "COURSE_CODE", referencedColumnName = "COURSE_CODE")
    )
    private Collection<Course> courseCollection;

    public Student() { }

    public Student(Integer studentId) {
        this.studentId = studentId;
    }

    public Student(Integer studentId, String firstName, String lastName, Integer yearLevel) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.yearLevel = yearLevel;
    }

    public Integer getStudentId() { return studentId; }
    public void setStudentId(Integer studentId) { this.studentId = studentId; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public Integer getYearLevel() { return yearLevel; }
    public void setYearLevel(Integer yearLevel) { this.yearLevel = yearLevel; }

    public Collection<Course> getCourseCollection() { return courseCollection; }
    public void setCourseCollection(Collection<Course> courseCollection) { this.courseCollection = courseCollection; }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (studentId != null ? studentId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Student)) return false;
        Student other = (Student) object;
        if ((this.studentId == null && other.studentId != null) || 
            (this.studentId != null && !this.studentId.equals(other.studentId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.unipiloto.arquitectura.entity.Student[ studentId=" + studentId + " ]";
    }
}
