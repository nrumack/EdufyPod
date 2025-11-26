package com.example.EdufyPod.services;

import com.example.EdufyPod.dto.PodcastEpisodeDTO;
import com.example.EdufyPod.entities.PodcastEpisode;
import com.example.EdufyPod.exceptions.ResourceNotFoundException;
import com.example.EdufyPod.mappers.PodCastEpisodeMapper;
import com.example.EdufyPod.repositories.PodcastEpisodeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PodcastEpisodeServiceTest {
        @Mock
        private PodcastEpisodeRepository repository;

        @Mock
        private PodCastEpisodeMapper mapper;

        @InjectMocks
        private PodcastEpisodeService service;

        private Long episodeId;
        private PodcastEpisode episode;
        private PodcastEpisodeDTO dto;

        @BeforeEach
        void setUp() {
        episodeId = ThreadLocalRandom.current().nextLong();
        episode = new PodcastEpisode();
        episode.setEpisodeId(episodeId);
        episode.setTitle("Test Episode");
        episode.setPlayCount(5L);

        dto = new PodcastEpisodeDTO();
        dto.setEpisodeId(episodeId);
        dto.setTitle("Test Episode");
    }

        @Test
        void getEpisodeById_shouldReturnDTO_whenEpisodeExists() {
        when(repository.findById(episodeId)).thenReturn(Optional.of(episode));
        when(mapper.toDTO(episode)).thenReturn(dto);

        PodcastEpisodeDTO result = service.getEpisodeById(episodeId);

        assertEquals(dto, result);
        verify(repository).findById(episodeId);
        verify(mapper).toDTO(episode);
    }

        @Test
        void getEpisodeById_shouldThrow_whenNotFound() {
        when(repository.findById(episodeId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.getEpisodeById(episodeId));
        verify(repository).findById(episodeId);
        verifyNoInteractions(mapper);
    }

        @Test
        void addEpisode_shouldMapAndSave() {
        when(mapper.toEntity(dto)).thenReturn(episode);

        service.addEpisode(dto);

        assertEquals(0L, episode.getPlayCount());
        verify(mapper).toEntity(dto);
        verify(repository).save(episode);
    }

        @Test
        void increasePlayCount_shouldIncrementAndSave() {
        when(repository.findById(episodeId)).thenReturn(Optional.of(episode));

        service.increasePlayCount(episodeId);

        assertEquals(6L, episode.getPlayCount());
        verify(repository).save(episode);
    }

        @Test
        void deleteEpisode_shouldDelete_whenExists() {
        when(repository.existsById(episodeId)).thenReturn(true);

        service.deleteEpisode(episodeId);

        verify(repository).deleteById(episodeId);
    }

        @Test
        void deleteEpisode_shouldThrow_whenNotFound() {
            when(repository.existsById(episodeId)).thenReturn(false);

            assertThrows(ResourceNotFoundException.class, () -> service.deleteEpisode(episodeId));
            verify(repository, never()).deleteById(any());
    }


        @Test
            void getRandomEpisode_shouldReturnOne_whenListNotEmpty() {
                List<PodcastEpisode> episodes = List.of(episode);
                when(repository.findAll()).thenReturn(episodes);
                when(mapper.toDTO(episode)).thenReturn(dto);

                PodcastEpisodeDTO result = service.getRandomEpisode();

                assertEquals(dto, result);
                verify(repository).findAll();
                verify(mapper).toDTO(episode);
    }

        @Test
        void getRandomEpisode_shouldThrow_whenListEmpty() {
            when(repository.findAll()).thenReturn(Collections.emptyList());

            assertThrows(ResourceNotFoundException.class, () -> service.getRandomEpisode());
    }

}
