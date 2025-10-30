package com.example.EdufyPod.entities;


import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class PodcastEpisode {
    @Id
    @GeneratedValue
    private UUID episodeId;

    private String title;
    private String description;
    private String audioUrl;
    private Integer duration;
    private LocalDate publishedDate;
    private String host;
    private String language;
    private boolean explicit;
    private Long playCount;

//    private Long seriesId;
    @ElementCollection
    private List<String> guests = new ArrayList<>();
    @ElementCollection
    private List<String> tags = new ArrayList<>();

    public PodcastEpisode() {}

    public PodcastEpisode(String title, String description, String audioUrl, Integer duration,
                          String host, String language, boolean explicit) {
        this.title = title;
        this.description = description;
        this.audioUrl = audioUrl;
        this.duration = duration;
        this.host = host;
        this.language = language;
        this.explicit = explicit;
    }


    public PodcastEpisode(UUID episodeId, String title, String description, String audioUrl, Integer duration, LocalDate publishedDate, String host, String language, boolean explicit, Long playCount, List<String> guests, List<String> tags) {
        this.episodeId = episodeId;
        this.title = title;
        this.description = description;
        this.audioUrl = audioUrl;
        this.duration = duration;
        this.publishedDate = publishedDate;
        this.host = host;
        this.language = language;
        this.explicit = explicit;
        this.playCount = playCount;
        this.guests = guests;
        this.tags = tags;
    }

    public UUID getEpisodeId() {
        return episodeId;
    }

    public void setEpisodeId(UUID episodeId) {
        this.episodeId = episodeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAudioUrl() {
        return audioUrl;
    }

    public void setAudioUrl(String audioUrl) {
        this.audioUrl = audioUrl;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public LocalDate getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(LocalDate publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public boolean isExplicit() {
        return explicit;
    }

    public void setExplicit(boolean explicit) {
        this.explicit = explicit;
    }

    public Long getPlayCount() {
        return playCount;
    }

    public void setPlayCount(Long playCount) {
        this.playCount = playCount;
    }

    public List<String> getGuests() {
        return guests;
    }

    public void setGuests(List<String> guests) {
        this.guests = guests;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
