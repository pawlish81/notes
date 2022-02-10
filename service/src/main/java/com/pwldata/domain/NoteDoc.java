package com.pwldata.domain;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.pwl.api.v1.model.Note;

import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "notes")
@Data
@NoArgsConstructor
public class NoteDoc {

    @Id
    private String id;

    private Note.TagEnum tag;

    private String title;

    private String text;

    private LocalDateTime createDate;

    public String getId() {
        return id;
    }

    public NoteDoc setId(String id) {
        this.id = id;
        return this;
    }

    public Note.TagEnum getTag() {
        return tag;
    }

    public NoteDoc setTag(Note.TagEnum tag) {
        this.tag = tag;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public NoteDoc setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getText() {
        return text;
    }

    public NoteDoc setText(String text) {
        this.text = text;
        return this;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public NoteDoc setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
        return this;
    }
}
