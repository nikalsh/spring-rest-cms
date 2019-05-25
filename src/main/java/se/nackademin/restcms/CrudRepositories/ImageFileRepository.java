package se.nackademin.restcms.CrudRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import se.nackademin.restcms.Entities.ImageFile;

@RepositoryRestResource(collectionResourceRel = "image", path = "image")
public interface ImageFileRepository extends JpaRepository<ImageFile, String> {

}
