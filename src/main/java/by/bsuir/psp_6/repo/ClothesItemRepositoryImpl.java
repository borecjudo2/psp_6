package by.bsuir.psp_6.repo;

import by.bsuir.psp_6.model.ClothesItem;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * DESCRIPTION
 *
 * @author Vladislav_Karpeka
 * @version 1.0.0
 */
@Component
public class ClothesItemRepositoryImpl implements ClothesItemRepository {

  private final Map<UUID, ClothesItem> data = new HashMap();

  @Override
  public ClothesItem save(ClothesItem clothesItem) {
    data.put(clothesItem.getId(), clothesItem);
    return clothesItem;
  }

  @Override
  public Map<UUID, ClothesItem> findAll() {
    return data;
  }
}
