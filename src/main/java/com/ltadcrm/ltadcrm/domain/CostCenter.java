package com.ltadcrm.ltadcrm.domain;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.UpdateTimestamp;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "CostCenter")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_costCenter")
public class CostCenter implements Serializable {

  @Column(name = "id_cost_center")
  @JsonProperty("id")
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "name_costCenter")
  @JsonProperty("nome")
  private String name;
  @Column(name = "identification_costCenter")
  @JsonProperty("identificação")
  private String identification;
  @Column(name = "initial_date_costCenter")
  @JsonProperty("data de inicio")
  private Date initialDate;
  @Column(name = "end_date_costCenter")
  @JsonProperty("data de fim")
  private Date endDate;
  @Column(name = "last_modify")
  private String lastModification;
  @Column(name = "update_in")
  @UpdateTimestamp
  private LocalDateTime updateIn;

  @OneToMany(mappedBy = "costCenter")
  @Fetch(FetchMode.SUBSELECT)
  private List<Items> items;
}
