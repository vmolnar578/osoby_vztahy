package school.dal.subjects;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "subjects")
public class SubjectsEntity {
    @Id
    @GeneratedValue
    private long id;

    @Column(name = "grade")
    private long grade;

    @Column(name = "title")
    private String title;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getGrade() {
        return grade;
    }

    public void setGrade(long grade) {
        this.grade = grade;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}