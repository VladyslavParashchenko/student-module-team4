package sumdu.team4_project.entity;

import com.github.javafaker.Faker;

import java.util.ArrayList;

public class SubjectEntity {

    private String title;
    private String description;
    private String image;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public ArrayList<LectorEntity> getLectors() {
        return lectors;
    }

    public void setLectors(ArrayList<LectorEntity> lectors) {
        this.lectors = lectors;
    }

    private ArrayList<LectorEntity> lectors;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public SubjectEntity() {
        Faker faker = new Faker();
        this.id = faker.random().nextInt(10000);
        this.title = faker.funnyName().name();
        this.description = faker.lorem().paragraph();
        this.image = "https://img.tsn.ua/cached/1586176881/tsn-39e40e7436a3d6b560ed018f4e4f6552/thumbs/1340x530/67/c2/004846b39bd8a9c74d0bddf6b2f0c267.jpeg";
        this.lectors = new ArrayList<LectorEntity>();
        this.lectors.add(new LectorEntity());
        this.lectors.add(new LectorEntity());
        this.lectors.add(new LectorEntity());
    }


}
