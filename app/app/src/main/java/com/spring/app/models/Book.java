package com.spring.app.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;

@Data
public class Book {

    @Id
    private String id;
    private String title;
    private String author;
    private Status status;
    private Rating rating;
    private LocalDateTime addedAt;
    private Date finishedAt;

    public Map<String, Object> toMap(){
        return Map.of("id", this.id,
                "title", this.title,
                "author", this.author,
                "status", this.status,
                "rating", this.rating,
                "addedAt", this.addedAt,
                "finishedAt", this.finishedAt);
    }

    public Book(String title, String author, Status status, Rating rating, LocalDateTime addedAt, Date finishedAt) {
        this.title = title;
        this.author = author;
        this.status = status;
        this.rating = rating;
        this.addedAt = addedAt;
        this.finishedAt = finishedAt;
    }

}
