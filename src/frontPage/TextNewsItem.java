package frontPage;

import java.util.Calendar;
import java.util.Date;

public class TextNewsItem extends NewsItem{
    String textOfNews;

    public TextNewsItem(String title, Date dateOfPublishing, Category category, String textOfNews) {
        super(title, dateOfPublishing, category);
        this.textOfNews = textOfNews;
    }

    @Override
    public String getTeaser() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.title).append("\n");
        stringBuilder.append(getMinutesFromPublication()).append("\n");

        stringBuilder.append(this.textOfNews.substring(0, Math.min(this.textOfNews.length(), 80)));
        return stringBuilder.toString();
    }
}

