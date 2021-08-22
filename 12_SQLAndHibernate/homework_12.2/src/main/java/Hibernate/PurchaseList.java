package Hibernate;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "PurchaseList")
@Data
public class PurchaseList {

    @EmbeddedId
    private PurchaseListKey id;

    @JoinColumn(name = "student_name", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Student student;

    @JoinColumn(name = "course_id", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Course course;

    @Column (name ="student_name")
    private String studentName;

    @Column (name = "course_name")
    private String courseName;

    private Integer price;

    @Column(name = "subscription_date")
    private Date subscriptionDate;


}
