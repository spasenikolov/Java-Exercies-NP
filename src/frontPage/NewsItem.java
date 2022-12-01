package frontPage;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public abstract class NewsItem {
    String title;
    Date dateOfPublishing;
    Category category;

    public NewsItem(String title, Date dateOfPublishing, Category category) {
        this.title = title;
        this.dateOfPublishing = dateOfPublishing;
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public Category getCategory() {
        return category;
    }


    public abstract String getTeaser();

    protected int getMinutesFromPublication() {
        Date nowDate = new Date(System.currentTimeMillis());


        return (int)nowDate.getTime()/60000 - (int)this.dateOfPublishing.getTime()/60000;

    }
}
