package fixie.dictionary_service.entity;

import javax.persistence.Column;
import javax.persistence.Id;

import fixie.dictionary_service.dto.PartTypeDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
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
    private String codeType;

    @Column(name = "name_type")
    private String nameType;

    public String getCodeType() {
        return codeType;
    }

    public void setCodeType(String codeType) {
        this.codeType = codeType;
    }

    public String getNameType() {
        return nameType;
    }

    public void setNameType(String nameType) {
        this.nameType = nameType;
    }




}
