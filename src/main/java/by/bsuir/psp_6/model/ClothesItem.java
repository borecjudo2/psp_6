package by.bsuir.psp_6.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * DESCRIPTION
 *
 * @author Vladislav_Karpeka
 * @version 1.0.0
 */
@Getter
@Setter
@AllArgsConstructor
@Builder
public class ClothesItem implements Serializable {

  private final UUID id = UUID.randomUUID();

  private String name;

  private String articular;

  private String model;

  private String creator;

  private String color;

  private int size;

  private Date creationDate;

  private int price;
}
