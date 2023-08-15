package com.parus.major.song.metadata.controller;

import com.parus.major.song.metadata.domain.SongMetadata;
import com.parus.major.song.metadata.service.SongMetadataService;
import com.parus.major.song.metadata.transfer.DeleteDTO;
import com.parus.major.song.metadata.transfer.UploadDTO;
import jakarta.validation.constraints.Size;
import org.springframework.beans.SimpleTypeConverter;
import org.springframework.beans.TypeConverterSupport;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Validated
@RestController
@RequestMapping(path = "/metadata")
public class SongMetadataRestController {
    private final SongMetadataService songMetadataService;
    private final TypeConverterSupport typeConverterSupport;

    public SongMetadataRestController(SongMetadataService songMetadataService, @Qualifier("defaultConversionService") ConversionService conversionService) {
        this.songMetadataService = songMetadataService;
        this.typeConverterSupport = new SimpleTypeConverter();
        this.typeConverterSupport.setConversionService(conversionService);;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UploadDTO upload(@RequestBody SongMetadata metadata) {
        long id = songMetadataService.save(metadata);
        return new UploadDTO(id);
    }

    @GetMapping(path = "/{id}")
    public SongMetadata stream(@PathVariable long id) {
        return songMetadataService.find(id);
    }

    @DeleteMapping
    @SuppressWarnings("unchecked")
    public DeleteDTO delete(@RequestParam(name = "ids") @Size(max = 200) String idsStr) {
        List<Long> ids = typeConverterSupport.convertIfNecessary(idsStr, List.class,
                TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(Long.class)));
        return new DeleteDTO(songMetadataService.delete(ids));
    }
}
