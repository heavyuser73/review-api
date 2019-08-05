package review;

import javax.persistence.*;

@Entity
public class Review {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private String title;
    private String review;
    private int starPoint;
    private String url;

    protected Review(){}
    public Review(String title, String review, int startPoint, String url) {
        this.title = title;
        this.review = review;
        this.starPoint = startPoint;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public int getStarPoint() {
        return starPoint;
    }

    public void setStarPoint(int starPoint) {
        this.starPoint = starPoint;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format(
                "Review[id=%d, title='%s', review='%s', startPoint='%d', url='%s']",
                id, title, review, starPoint, url);
    }
}
