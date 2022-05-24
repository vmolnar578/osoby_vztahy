package school.dal.students;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "students")
public class StudentsEntity {
    @Id
    @GeneratedValue
    private long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "grade", nullable = false)
    private Integer grade;

    @Column(name = "date_birth", nullable = false)
    private Date dateOfBirth;

    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "lunch_id", nullable = false)
    private long lunchId;

    @Column(name = "parent1_id", nullable = false)
    private long parentOneId;

    @Column(name = "parent2_id", nullable = false)
    private long parentTwoId;

    @Column(name = "teacher_id", nullable = false)
    private long teacherId;

    @Column(columnDefinition="VARCHAR(512) DEFAULT 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQwbGozsS9QP10p16rZiCrQD0koXVkI4c7LwUHab9dkmFRcN0VqCkB37f2y0EnySItwykg&usqp=CAU'")
    private String image;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public long getLunchId() {
        return lunchId;
    }

    public void setLunchId(long lunchId) {
        this.lunchId = lunchId;
    }

    public long getParentOneId() {
        return parentOneId;
    }

    public void setParentOneId(long parentOneId) {
        this.parentOneId = parentOneId;
    }

    public long getParentTwoId() {
        return parentTwoId;
    }

    public void setParentTwoId(long parentTwoId) {
        this.parentTwoId = parentTwoId;
    }

    public long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(long teacherId) {
        this.teacherId = teacherId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}