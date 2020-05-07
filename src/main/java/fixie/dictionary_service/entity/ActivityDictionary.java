package fixie.dictionary_service.entity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.Size;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "activity_dictionary")
@Builder(toBuilder = true)
public class ActivityDictionary implements Serializable {

    @Id
    @Column(name = "act_type")
    @Size(max=7)
    private String actType;

    @Column(name = "act_name")
    @Size(max=50)
    private String actName;
}
