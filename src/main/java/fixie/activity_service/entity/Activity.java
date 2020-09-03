package fixie.activity_service.entity;

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
@Table(name = "activity")
@Builder(toBuilder = true)
public class Activity implements Serializable {

    @Id
    @Column(name = "id_activity")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "status")
    @Size(max = 3)
    private String status;

    @Column(name = "worker_id")
    private Long workerId;

    @Column(name = "part_id")
    private Long partId;

    @Column(name = "activity_type")
    @Size(max = 7)
    private String activityType;
}
