package fixie.dictionary_service.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "part_type")
@Builder(toBuilder = true)
public class PartType implements Serializable {

    @Id
    @Column(name = "code_type")
    @Size(max=7)
    private String codeType;

    @Column(name = "name_type")
    @Size(max=50)
    private String nameType;
}
