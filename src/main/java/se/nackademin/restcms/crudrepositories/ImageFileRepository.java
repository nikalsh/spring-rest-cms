package se.nackademin.restcms.crudRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import se.nackademin.restcms.entities.ImageFile;

//@RepositoryRestResource(collectionResourceRel = "image", path = "image")
public interface ImageFileRepository extends JpaRepository<ImageFile, String> {

}
