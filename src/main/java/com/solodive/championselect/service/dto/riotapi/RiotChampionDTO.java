package com.solodive.championselect.service.dto.riotapi;

import java.util.ArrayList;
import java.util.List;

public class RiotChampionDTO {

    private String version;
    private String id;
    private String key;
    private String name;
    private String title;
    private String blurb;
    private RiotChampionInfoDTO info;
    private RiotChampionImageDTO image;
    private List<String> tags = new ArrayList<>();
    private String partype;
    private RiotChampionStatsDTO stats;


    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBlurb() {
        return blurb;
    }

    public void setBlurb(String blurb) {
        this.blurb = blurb;
    }

    public RiotChampionInfoDTO getInfo() {
        return info;
    }

    public void setInfo(RiotChampionInfoDTO info) {
        this.info = info;
    }

    public RiotChampionImageDTO getImage() {
        return image;
    }

    public void setImage(RiotChampionImageDTO image) {
        this.image = image;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getPartype() {
        return partype;
    }

    public void setPartype(String partype) {
        this.partype = partype;
    }

    public RiotChampionStatsDTO getStats() {
        return stats;
    }

    public void setStats(RiotChampionStatsDTO stats) {
        this.stats = stats;
    }


    @Override
    public String toString() {
        return "ChampionDTO{" +
            "version='" + version + '\'' +
            ", id='" + id + '\'' +
            ", key='" + key + '\'' +
            ", name='" + name + '\'' +
            ", title='" + title + '\'' +
            ", blurb='" + blurb + '\'' +
            ", info=" + info +
            ", image=" + image +
            ", tags=" + tags +
            ", partype='" + partype + '\'' +
            ", stats=" + stats +
            '}';
    }
}
