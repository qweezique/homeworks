package Hibernate;

import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "LinkedPurchaseList")
public class LinkedPurchaseList implements Serializable {

    @EmbeddedId
    private LinkedPurchaseKey purchaseKey;


    @Column(name = "student_id", insertable = false, updatable = false)
    private Integer studentId;


    @Column(name = "course_id", insertable = false, updatable = false)
    private Integer courseId;

    @Column(name = "course_price")
    private Integer price;

    @Column (name = "purchase_date")
    private Date purchaseDate;


    public LinkedPurchaseList() {
    }

    public LinkedPurchaseList(Student student, Course course) {
        this.purchaseKey = new LinkedPurchaseKey(student.getId(), course.getId());
        this.price = course.getPrice();

    }

    public LinkedPurchaseKey getPurchaseKey() {
        return purchaseKey;
    }

    public void setPurchaseKey(LinkedPurchaseKey purchaseKey) {
        this.purchaseKey = purchaseKey;
    }

    public Integer getStudent_id() {
        return studentId;
    }

    public void setStudent_id(Integer student_id) {
        this.studentId = student_id;
    }

    public Integer getCourse_id() {
        return courseId;
    }

    public void setCourse_id(Integer course_id) {
        this.courseId = course_id;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "LinkedPurchaseList{" +
                "purchaseKey=" + purchaseKey +
                ", studentId=" +  studentId +
                ", courseId=" + courseId +
                ", price=" + price +
                ", purchaseDate=" + purchaseDate +
                '}';
    }
}
