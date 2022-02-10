package com.pwl.api.v1.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.pwl.api.v1.model.Note;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * NoteList
 */

public class NoteList   {
  @JsonProperty("itemList")
  @Valid
  private List<Note> itemList = null;

  @JsonProperty("currentPage")
  private Integer currentPage;

  @JsonProperty("totalItems")
  private Long totalItems;

  @JsonProperty("totalPage")
  private Integer totalPage;

  public NoteList itemList(List<Note> itemList) {
    this.itemList = itemList;
    return this;
  }

  public NoteList addItemListItem(Note itemListItem) {
    if (this.itemList == null) {
      this.itemList = new ArrayList<>();
    }
    this.itemList.add(itemListItem);
    return this;
  }

  /**
   * Get itemList
   * @return itemList
  */
  @ApiModelProperty(value = "")

  @Valid

  public List<Note> getItemList() {
    return itemList;
  }

  public void setItemList(List<Note> itemList) {
    this.itemList = itemList;
  }

  public NoteList currentPage(Integer currentPage) {
    this.currentPage = currentPage;
    return this;
  }

  /**
   * Get currentPage
   * @return currentPage
  */
  @ApiModelProperty(value = "")


  public Integer getCurrentPage() {
    return currentPage;
  }

  public void setCurrentPage(Integer currentPage) {
    this.currentPage = currentPage;
  }

  public NoteList totalItems(Long totalItems) {
    this.totalItems = totalItems;
    return this;
  }

  /**
   * Get totalItems
   * @return totalItems
  */
  @ApiModelProperty(value = "")


  public Long getTotalItems() {
    return totalItems;
  }

  public void setTotalItems(Long totalItems) {
    this.totalItems = totalItems;
  }

  public NoteList totalPage(Integer totalPage) {
    this.totalPage = totalPage;
    return this;
  }

  /**
   * Get totalPage
   * @return totalPage
  */
  @ApiModelProperty(value = "")


  public Integer getTotalPage() {
    return totalPage;
  }

  public void setTotalPage(Integer totalPage) {
    this.totalPage = totalPage;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NoteList noteList = (NoteList) o;
    return Objects.equals(this.itemList, noteList.itemList) &&
        Objects.equals(this.currentPage, noteList.currentPage) &&
        Objects.equals(this.totalItems, noteList.totalItems) &&
        Objects.equals(this.totalPage, noteList.totalPage);
  }

  @Override
  public int hashCode() {
    return Objects.hash(itemList, currentPage, totalItems, totalPage);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NoteList {\n");
    
    sb.append("    itemList: ").append(toIndentedString(itemList)).append("\n");
    sb.append("    currentPage: ").append(toIndentedString(currentPage)).append("\n");
    sb.append("    totalItems: ").append(toIndentedString(totalItems)).append("\n");
    sb.append("    totalPage: ").append(toIndentedString(totalPage)).append("\n");
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

