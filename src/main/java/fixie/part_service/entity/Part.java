package fixie.part_service.entity;

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
@Table(name = "part")
@Builder(toBuilder = true)
public class Part implements Serializable {

    @Id
    @Column(name = "id_part")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "part_code_type")
    @Size(max=7)
    private String partCodeType;

    @Column(name = "name")
    @Size(max=250)
    private String name;
}
