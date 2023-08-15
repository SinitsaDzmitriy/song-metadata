package com.parus.major.song.metadata.service;

import com.parus.major.song.metadata.domain.SongMetadata;
import com.parus.major.song.metadata.repo.SongMetadataRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SongMetadataService {
    private final SongMetadataRepository songMetadataRepository;

    public SongMetadata find(Long id) {
        return songMetadataRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("No song metadata with %d ID.", id)));
    }

    public Long save(SongMetadata metadata) {
        songMetadataRepository.save(metadata);
        return metadata.getId();
    }

    public List<Long> delete(List<Long> ids) {
        var existingIds = songMetadataRepository.findExistingIds(ids);
        songMetadataRepository.deleteAllById(existingIds);
        return existingIds;
    }
}
