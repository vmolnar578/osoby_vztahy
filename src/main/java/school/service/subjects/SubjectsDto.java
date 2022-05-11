package school.service.subjects;

import java.util.Date;

public class SubjectsDto {
    private long id;
    private long grade;
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

