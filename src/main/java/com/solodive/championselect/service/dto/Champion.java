package com.solodive.championselect.service.dto;

import java.util.List;

public class Champion {

    private String version;
    private String id;
    private String key;
    private String name;
    private String title;
    private String blurb;
    private ChampionInfo info;
    private ChampionImage image;
    private List<String> tags = null;
    private String partype;
    private ChampionStats stats;


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

    public ChampionInfo getInfo() {
        return info;
    }

    public void setInfo(ChampionInfo info) {
        this.info = info;
    }

    public ChampionImage getImage() {
        return image;
    }

    public void setImage(ChampionImage image) {
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

    public ChampionStats getStats() {
        return stats;
    }

    public void setStats(ChampionStats stats) {
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
