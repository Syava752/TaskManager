import java.io.Serializable;

public class Task implements Serializable {
    private String title;
    private String description;
    private boolean isCompleted;

    public Task (String title,String description) {
        this.title = title;
        this.description = description;
        this.isCompleted = false;
    }

    //Геттеры и сеттеры для полей
    public String getTitle (){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public void markAsCompleted(){
        this.isCompleted = true;
    }

}
