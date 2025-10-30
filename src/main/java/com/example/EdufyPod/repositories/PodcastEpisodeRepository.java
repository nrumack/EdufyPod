package com.example.EdufyPod.repositories;

import com.example.EdufyPod.entities.PodcastEpisode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PodcastEpisodeRepository extends JpaRepository<PodcastEpisode, UUID> {

}
