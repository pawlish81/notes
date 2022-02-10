package com.pwl.api.v1.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.pwl.api.v1.model.NoteAllOf;
import com.pwl.api.v1.model.UpdatedNote;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Note
 */

public class Note   {
  @JsonProperty("id")
  private String id;

  /**
   * Gets or Sets tag
   */
  public enum TagEnum {
    BUSINESS("BUSINESS"),
    
    PERSONAL("PERSONAL"),
    
    IMPORTANT("IMPORTANT");

    private String value;

    TagEnum(String value) {
      this.value = value;
    }

    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static TagEnum fromValue(String value) {
      for (TagEnum b : TagEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  @JsonProperty("tag")
  private TagEnum tag;

  @JsonProperty("title")
  private String title;

  @JsonProperty("text")
  private String text;

  @JsonProperty("createDate")
  @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime createDate;

  @JsonProperty("stats")
  @Valid
  private Map<String, Integer> stats = null;

  public Note id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  @ApiModelProperty(value = "")


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Note tag(TagEnum tag) {
    this.tag = tag;
    return this;
  }

  /**
   * Get tag
   * @return tag
  */
  @ApiModelProperty(value = "")


  public TagEnum getTag() {
    return tag;
  }

  public void setTag(TagEnum tag) {
    this.tag = tag;
  }

  public Note title(String title) {
    this.title = title;
    return this;
  }

  /**
   * Get title
   * @return title
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Note text(String text) {
    this.text = text;
    return this;
  }

  /**
   * Get text
   * @return text
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public Note createDate(OffsetDateTime createDate) {
    this.createDate = createDate;
    return this;
  }

  /**
   * Get createDate
   * @return createDate
  */
  @ApiModelProperty(value = "")

  @Valid

  public OffsetDateTime getCreateDate() {
    return createDate;
  }

  public void setCreateDate(OffsetDateTime createDate) {
    this.createDate = createDate;
  }

  public Note stats(Map<String, Integer> stats) {
    this.stats = stats;
    return this;
  }

  public Note putStatsItem(String key, Integer statsItem) {
    if (this.stats == null) {
      this.stats = new HashMap<>();
    }
    this.stats.put(key, statsItem);
    return this;
  }

  /**
   * Get stats
   * @return stats
  */
  @ApiModelProperty(value = "")


  public Map<String, Integer> getStats() {
    return stats;
  }

  public void setStats(Map<String, Integer> stats) {
    this.stats = stats;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Note note = (Note) o;
    return Objects.equals(this.id, note.id) &&
        Objects.equals(this.tag, note.tag) &&
        Objects.equals(this.title, note.title) &&
        Objects.equals(this.text, note.text) &&
        Objects.equals(this.createDate, note.createDate) &&
        Objects.equals(this.stats, note.stats);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, tag, title, text, createDate, stats);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Note {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    tag: ").append(toIndentedString(tag)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    text: ").append(toIndentedString(text)).append("\n");
    sb.append("    createDate: ").append(toIndentedString(createDate)).append("\n");
    sb.append("    stats: ").append(toIndentedString(stats)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

