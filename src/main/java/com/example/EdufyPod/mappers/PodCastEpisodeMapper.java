package com.example.EdufyPod.mappers;

import com.example.EdufyPod.dto.PodcastEpisodeDTO;
import com.example.EdufyPod.entities.PodcastEpisode;
import org.springframework.stereotype.Component;

@Component
public class PodCastEpisodeMapper {

    public PodcastEpisodeDTO toDTO(PodcastEpisode episode) {
        PodcastEpisodeDTO dto = new PodcastEpisodeDTO();
        dto.setEpisodeId(episode.getEpisodeId());
        dto.setTitle(episode.getTitle());
        dto.setDescription(episode.getDescription());
        dto.setAudioUrl(episode.getAudioUrl());
        dto.setDuration(episode.getDuration());
        dto.setPublishedDate(episode.getPublishedDate());
        dto.setHost(episode.getHost());
        dto.setLanguage(episode.getLanguage());
        dto.setExplicit(episode.isExplicit());
        return dto;
    }

    public PodcastEpisode toEntity(PodcastEpisodeDTO dto) {
        PodcastEpisode episode = new PodcastEpisode();
        episode.setEpisodeId(dto.getEpisodeId());
        episode.setTitle(dto.getTitle());
        episode.setDescription(dto.getDescription());
        episode.setAudioUrl(dto.getAudioUrl());
        episode.setDuration(dto.getDuration());
        episode.setPublishedDate(dto.getPublishedDate());
        episode.setHost(dto.getHost());
        episode.setLanguage(dto.getLanguage());
        episode.setExplicit(dto.isExplicit());
        return episode;
    }
}
