package Hibernate;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class LinkedPurchaseKey implements Serializable {
    @Column(name = "student_id")
    private Integer studentID;
    @Column(name = "course_id")
    private Integer courseID;

    public Integer getStudentID() {
        return studentID;
    }

    public void setStudentID(Integer studentID) {
        this.studentID = studentID;
    }

    public Integer getCourseID() {
        return courseID;
    }

    public void setCourseID(Integer courseID) {
        this.courseID = courseID;
    }
    public LinkedPurchaseKey(){

    }

    public LinkedPurchaseKey(Integer studentID, Integer courseID) {
        this.studentID = studentID;
        this.courseID = courseID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LinkedPurchaseKey that = (LinkedPurchaseKey) o;

        if (!studentID.equals(that.studentID)) return false;
        return courseID.equals(that.courseID);
    }

    @Override
    public int hashCode() {
        int result = studentID.hashCode();
        result = 31 * result + courseID.hashCode();
        return result;
    }
}