package frontPage;

import java.util.Date;

public class MediaNewsItem extends NewsItem{
    String url;
    Integer viewsOfNewsItem;

    public MediaNewsItem(String title, Date dateOfPublishing, Category category, String url, Integer viewsOfNewsItem) {
        super(title, dateOfPublishing, category);
        this.url = url;
        this.viewsOfNewsItem = viewsOfNewsItem;
    }

    @Override
    public String getTeaser() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.title).append("\n");
        stringBuilder.append(getMinutesFromPublication()).append("\n");
        stringBuilder.append(this.url).append("\n");
        stringBuilder.append(this.viewsOfNewsItem);


        return stringBuilder.toString();
    }
}
