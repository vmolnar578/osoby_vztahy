package school.service.lunches;

import java.util.Date;

public class LunchesDto {
    private long id;
    private String mainMeal;
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

