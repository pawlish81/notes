package com.pwldata.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import javax.validation.constraints.NotEmpty;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.pwl.api.v1.model.Note;
import com.pwldata.exceptions.NoteValidationException;

import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Document(collection = "notes")
@NoArgsConstructor
public class NoteDoc {

    @Id
    private String id;

    private Note.TagEnum tag;

    @NotEmpty
    private String title;

    @NotEmpty
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String stringDate = formatter.format(createDate);
        this.createDate = LocalDateTime.parse(stringDate, formatter);
        return this;
    }

    public NoteDoc setTagFromString(String stringTag) {
        try {
            setTag(stringTag == null ? null : Note.TagEnum.fromValue(stringTag));
        } catch (IllegalArgumentException e) {
            throw new NoteValidationException("tag has incorrect value :" + tag + ", correct one is : " + Arrays.toString(Note.TagEnum.values()));
        }
        return this;
    }
}
