package frontPage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class FrontPage {
    List<NewsItem> newsItems;
    Category [] categories;

    public FrontPage(Category[] categories) {
        this.categories = categories;
        this.newsItems = new ArrayList<>();
    }

    public void setCategories(Category[] categories) {
        this.categories = categories;
    }

    public void addNewsItem (NewsItem newsItem){
        this.newsItems.add(newsItem);
    }

    public List<NewsItem> listByCategory(Category category){
        return newsItems.stream()
                .filter(newsItem -> newsItem.category.equals(category))
                .collect(Collectors.toList());
    }
    public List<NewsItem> listByCategoryName(String category){
        Category categoryObject = new Category(category);
        Arrays.stream(this.categories)
                .filter(cat -> cat.equals(categoryObject))
                .findAny()
                .orElseThrow(()->new CategoryNotFoundException(category));

        return this.newsItems.stream()
                .filter(newsItem -> newsItem.getCategory().equals(categoryObject))
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        this.newsItems.forEach(newsItem -> stringBuilder.append(newsItem.getTeaser()).append("\n"));
        return stringBuilder.toString();
    }
}
