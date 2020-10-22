package Model;

import android.widget.ImageView;
import android.widget.TextView;

public class Data {
    String trip, story, image;
    public Data(String trip, String story, String image) {
        this.trip = trip;
        this.story = story;
        this.image = image;
    }

    public Data(){

    }

    public String getTrip() {
        return trip;
    }

    public void setTrip(String trip) {
        this.trip = trip;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
