import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "linked_purchaselist")
public class LinkedPurchaseList {

    @EmbeddedId
    private KeyLinkedPurchaseList id;

    @Column(name = "student_id", insertable = false, updatable = false)
    private Integer studentId;

    @Column(name = "course_id", insertable = false, updatable = false)
    private Integer courseId;

    @Column(name = "price")
    private Integer price;

    @Column(name = "subscription_date")
    private Date subscriptionDate;


    public KeyLinkedPurchaseList getId() {
        return id;
    }

    public void setId(KeyLinkedPurchaseList id) {
        this.id = id;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Date getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(Date subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }
}
