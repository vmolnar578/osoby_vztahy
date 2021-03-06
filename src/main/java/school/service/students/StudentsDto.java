package school.service.students;

import javax.persistence.Column;
import java.util.Date;

public class StudentsDto {
    private long id;
    private String firstName;
    private String lastName;
    private Integer grade;
    private Date dateOfBirth;
    private String gender;
    private long lunchId;
    private long parentOneId;
    private long parentTwoId;
    private long teacherId;
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

