package com.pwl.api.v1.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * NoteAllOf
 */

public class NoteAllOf   {
  @JsonProperty("stats")
  @Valid
  private Map<String, Integer> stats = null;

  public NoteAllOf stats(Map<String, Integer> stats) {
    this.stats = stats;
    return this;
  }

  public NoteAllOf putStatsItem(String key, Integer statsItem) {
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
    NoteAllOf noteAllOf = (NoteAllOf) o;
    return Objects.equals(this.stats, noteAllOf.stats);
  }

  @Override
  public int hashCode() {
    return Objects.hash(stats);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NoteAllOf {\n");
    
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

