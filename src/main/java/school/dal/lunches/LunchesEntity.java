package school.dal.lunches;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "lunches")
public class LunchesEntity {
    @Id
    @GeneratedValue
    private long id;

    @Column(name = "main_meal", nullable = false)
    private String mainMeal;

    @Column(columnDefinition="VARCHAR(512) DEFAULT 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQwbGozsS9QP10p16rZiCrQD0koXVkI4c7LwUHab9dkmFRcN0VqCkB37f2y0EnySItwykg&usqp=CAU'")
    private String image;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMainMeal() {
        return mainMeal;
    }

    public void setMainMeal(String mainMeal) {
        this.mainMeal = mainMeal;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}