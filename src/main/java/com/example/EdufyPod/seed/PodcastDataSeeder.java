package com.example.EdufyPod.seed;

import com.example.EdufyPod.entities.PodcastEpisode;
import com.example.EdufyPod.repositories.PodcastEpisodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Component
public class PodcastDataSeeder implements CommandLineRunner {

    private final PodcastEpisodeRepository podcastEpisodeRepository;

    @Autowired
    public PodcastDataSeeder(PodcastEpisodeRepository podcastEpisodeRepository) {
        this.podcastEpisodeRepository = podcastEpisodeRepository;
    }

    @Override
    public void run(String... args) {
        if (podcastEpisodeRepository.count() == 0) {
            List<PodcastEpisode> episodes = List.of(
                    new PodcastEpisode("The Launch", "Our very first episode!", "https://example.com/audio/launch.mp3", 1800, "Alex", "en", false),
                    new PodcastEpisode("Deep Dive: Java", "Exploring Java's evolution.", "https://example.com/audio/java.mp3", 2400, "Jordan", "en", false),
                    new PodcastEpisode("Tech & Ethics", "Where do we draw the line?", "https://example.com/audio/ethics.mp3", 2100, "Taylor", "en", true),
                    new PodcastEpisode("AI in Education", "How AI is reshaping learning.", "https://example.com/audio/ai-edu.mp3", 1950, "Alex", "en", false),
                    new PodcastEpisode("Behind the Mic", "Meet the team behind EdufyPod.", "https://example.com/audio/behind.mp3", 1600, "Jordan", "en", false)
            );

            episodes.forEach(e -> {
                e.setPublishedDate(LocalDate.now().minusDays(new Random().nextInt(30)));
                e.setPlayCount(0L);
                podcastEpisodeRepository.save(e);
            });
        }
    }
}
