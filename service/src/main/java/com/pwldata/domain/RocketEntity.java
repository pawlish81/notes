package com.pwldata.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.pwl.rocket_sim.api.v1.model.RocketType;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Document(collection = "rockets")
@CompoundIndexes({
        @CompoundIndex(name = "company_idx", def = "{'company': 1, 'name': -1}"),
})
@NoArgsConstructor
public class RocketEntity extends AuditableEntity {

    private RocketType rocketType;

    private String name;

    private String company;

    private String base;

    private String desc;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime buildDate;

    private Map<String, Integer> stats;


    public RocketType getRocketType() {
        return rocketType;
    }

    public RocketEntity setRocketType(RocketType rocketType) {
        this.rocketType = rocketType;
        return this;
    }

    public String getName() {
        return name;
    }

    public RocketEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getCompany() {
        return company;
    }

    public RocketEntity setCompany(String company) {
        this.company = company;
        return this;
    }

    public String getBase() {
        return base;
    }

    public RocketEntity setBase(String base) {
        this.base = base;
        return this;
    }

    public String getDesc() {
        return desc;
    }

    public RocketEntity setDesc(String desc) {
        this.desc = desc;
        return this;
    }

    public Map<String, Integer> getStats() {
        return stats;
    }

    public RocketEntity setStats(Map<String, Integer> stats) {
        this.stats = stats;
        return this;
    }

    public LocalDateTime getBuildDate() {
        return buildDate;
    }

    public RocketEntity setBuildDate(LocalDateTime buildDate) {
        this.buildDate = buildDate;
        return this;
    }

    @Override
    public String toString() {

        return "RocketEntity{" +
                "rocketType=" + rocketType +
                ", name='" + name + '\'' +
                ", company='" + company + '\'' +
                ", base='" + base + '\'' +
                ", desc='" + desc + '\'' +
                ", buildDate=" + buildDate +
                ", stats=" + stats +
                '}' +super.toString();
    }
}
