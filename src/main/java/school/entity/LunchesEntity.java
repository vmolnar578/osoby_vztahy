package school.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "lunches")
public class LunchesEntity {
    @Id
    @GeneratedValue
    private long id;

    @Column(name = "day", nullable = false)
    private long day;

    @Column(name = "main_meal", nullable = false)
    private String mainMeal;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getDay() {
        return day;
    }

    public void setDay(long day) {
        this.day = day;
    }

    public String getMainMeal() {
        return mainMeal;
    }

    public void setMainMeal(String mainMeal) {
        this.mainMeal = mainMeal;
    }
}