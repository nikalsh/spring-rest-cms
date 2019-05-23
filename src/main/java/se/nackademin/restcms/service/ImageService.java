package se.nackademin.restcms.service;

import se.nackademin.restcms.Entities.Image;

import java.util.Optional;

public interface ImageService {
    Optional<Image> findById (Long id);
}
