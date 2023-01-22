package br.gov.sead.pagrn.domain.concrets;

import br.gov.sead.pagrn.domain.generic.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity(name = "categorias_funcionais")
@SQLDelete(sql = "UPDATE categorias_funcionais SET deletedAt = CURRENT_DATE WHERE id=?")
@Getter
@Setter
@RequiredArgsConstructor
public class CategoriaFuncional extends AbstractEntity {

    @NotBlank
    private String denominacao;

    @NotBlank
    private String mneumonico;

    @NotNull
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate criacao;

    @NotNull
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate extincao;
}
