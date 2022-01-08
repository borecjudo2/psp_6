package by.bsuir.psp_6.repo;

import by.bsuir.psp_6.model.ClothesItem;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * DESCRIPTION
 *
 * @author Vladislav_Karpeka
 * @version 1.0.0
 */
public interface ClothesItemRepository {

  ClothesItem save(ClothesItem clothesItem);

  Map<UUID, ClothesItem> findAll();
}
