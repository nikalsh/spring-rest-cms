package se.nackademin.restcms.Entities;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Table(name = "files")
public class ImageFile {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String fileName;

    private String fileType;

@OneToOne
@JoinColumn(name = "blogadmin_id", nullable = false)
private BlogAdmin blogAdmin;

    @Lob
    private byte[] data;
    
    public ImageFile (String fileName, String fileType, byte[] data) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.data = data;
    }
    
}
