package com.example.EdufyPod.services;

import com.example.EdufyPod.dto.PodcastEpisodeDTO;
import com.example.EdufyPod.entities.PodcastEpisode;
import com.example.EdufyPod.exceptions.ResourceNotFoundException;
import com.example.EdufyPod.mappers.PodCastEpisodeMapper;
import com.example.EdufyPod.repositories.PodcastEpisodeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PodcastEpisodeService {

    private final PodcastEpisodeRepository podcastEpisodeRepository;
    private final PodCastEpisodeMapper podcastEpisodeMapper;

    public PodcastEpisodeService(PodcastEpisodeRepository podcastEpisodeRepository, PodCastEpisodeMapper podcastEpisodeMapper) {
        this.podcastEpisodeRepository = podcastEpisodeRepository;
        this.podcastEpisodeMapper = podcastEpisodeMapper;
    }

    public PodcastEpisodeDTO getEpisodeById(Long id){
        PodcastEpisode episode = podcastEpisodeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Episode not found"));
        return podcastEpisodeMapper.toDTO(episode);
    }

    public List<PodcastEpisodeDTO> getAllEpisodes(){
        return podcastEpisodeRepository.findAll().stream()
                .map(podcastEpisodeMapper::toDTO)
                .collect(Collectors.toList());
    }

    public void addEpisode(PodcastEpisodeDTO dto){
        PodcastEpisode episode = podcastEpisodeMapper.toEntity(dto);
        episode.setPlayCount(0L);
        podcastEpisodeRepository.save(episode);
    }

    public void updateEpisode(PodcastEpisodeDTO dto){
        PodcastEpisode existing = podcastEpisodeRepository.findById(dto.getEpisodeId())
                .orElseThrow(() -> new ResourceNotFoundException("Episode not found"));
        existing.setEpisodeId(dto.getEpisodeId());
        existing.setTitle(dto.getTitle());
        existing.setDescription(dto.getDescription());
        existing.setAudioUrl(dto.getAudioUrl());
        existing.setDuration(dto.getDuration());
        existing.setPublishedDate(dto.getPublishedDate());
        existing.setHost(dto.getHost());
        existing.setLanguage(dto.getLanguage());
        existing.setExplicit(dto.isExplicit());

        podcastEpisodeRepository.save(existing);
    }

    public void deleteEpisode(Long id){
        if(!podcastEpisodeRepository.existsById(id)){
            throw new ResourceNotFoundException("Episode with id: " + id + "not found");
        }
        podcastEpisodeRepository.deleteById(id);
    }

    public void increasePlayCount(Long id){
        PodcastEpisode episode = podcastEpisodeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Episode with id: " + id + "not found"));
        episode.setPlayCount(episode.getPlayCount() + 1);
        podcastEpisodeRepository.save(episode);
    }

    public PodcastEpisodeDTO getRandomEpisode(){
        List<PodcastEpisode> episodes = podcastEpisodeRepository.findAll();
        if (episodes.isEmpty()) {
            throw new ResourceNotFoundException("Episode not found");
        }
        PodcastEpisode random = episodes.get(new Random().nextInt(episodes.size()));
         return podcastEpisodeMapper.toDTO(random);
    }

}
