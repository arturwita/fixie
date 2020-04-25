package fixie.dictionary_service.entity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "activity_dictionary")
@Builder(toBuilder = true)
public class ActivityDictionary implements Serializable {

    @Id
    @Column(name = "act_type")
    private String actType;

    @Column(name = "act_name")
    private String actName;





}
