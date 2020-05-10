package sumdu.team4_project.entity;

import com.github.javafaker.Faker;

public class LectorEntity {

    private String name;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    private String image;
    private int id;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public LectorEntity() {
        this.image = new Faker().internet().avatar();
        this.name = new Faker().name().fullName();
        this.id = new Faker().random().nextInt(10000);
    }

    public LectorEntity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
