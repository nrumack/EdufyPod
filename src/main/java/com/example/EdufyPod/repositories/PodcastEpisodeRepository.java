package com.example.EdufyPod.repositories;

import com.example.EdufyPod.entities.PodcastEpisode;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PodcastEpisodeRepository extends JpaRepository<PodcastEpisode, Long> {

}
