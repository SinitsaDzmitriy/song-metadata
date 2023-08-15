package com.parus.major.song.metadata.repo;

import com.parus.major.song.metadata.domain.SongMetadata;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SongMetadataRepository extends CrudRepository<SongMetadata, Long> {
    @Query("Select sm.id From SongMetadata sc Where sm.id IN :ids")
    List<Long> findExistingIds(List<Long> ids);
}
