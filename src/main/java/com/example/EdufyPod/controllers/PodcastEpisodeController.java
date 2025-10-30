package com.example.EdufyPod.controllers;

import com.example.EdufyPod.dto.PodcastEpisodeDTO;
import com.example.EdufyPod.services.PodcastEpisodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/episodes")
public class PodcastEpisodeController {

    private final PodcastEpisodeService podcastEpisodeService;

    @Autowired
    public PodcastEpisodeController(PodcastEpisodeService podcastEpisodeService){
        this.podcastEpisodeService = podcastEpisodeService;
    }

    @GetMapping("/{id}/stream")
    public ResponseEntity<PodcastEpisodeDTO> getEpisode(@PathVariable UUID id){
        PodcastEpisodeDTO dto = podcastEpisodeService.getEpisodeById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<PodcastEpisodeDTO>> getEpisodes(){
        return ResponseEntity.ok(podcastEpisodeService.getAllEpisodes());
    }

    @PostMapping("/{id}/play")
    public ResponseEntity<Void> playEpisode(@PathVariable UUID id){
        podcastEpisodeService.increasePlayCount(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<Void> addEpisode(@RequestBody PodcastEpisodeDTO dto){
        podcastEpisodeService.addEpisode(dto);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> updateEpisode(@RequestBody PodcastEpisodeDTO dto){
        podcastEpisodeService.updateEpisode(dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEpisode(@PathVariable UUID id){
        podcastEpisodeService.deleteEpisode(id);
        return ResponseEntity.ok("episode deleted successfully");
    }

    @GetMapping("/random")
    public ResponseEntity<PodcastEpisodeDTO> getRandomEpisodes(){
        PodcastEpisodeDTO dto = podcastEpisodeService.getRandomEpisode();
        return ResponseEntity.ok(dto);
    }
}
