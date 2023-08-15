package com.parus.major.song.metadata.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.Year;

@Entity
@Table(name = "song_metadata")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SongMetadata {
    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    private Long songId;
    private String name;
    private String artist;
    private String album;
    @NotNull
    private Duration length;
    private Year year;
}
