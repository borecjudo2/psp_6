package by.bsuir.psp_6.service.impl;

import by.bsuir.psp_6.model.ClothesItem;
import by.bsuir.psp_6.repo.ClothesItemRepository;
import by.bsuir.psp_6.service.ClothesItemService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * DESCRIPTION
 *
 * @author Vladislav_Karpeka
 * @version 1.0.0
 */
@Service
@AllArgsConstructor
public class ClothesItemServiceImpl implements ClothesItemService {

  private final ClothesItemRepository repository;

  @Override
  public ClothesItem save(ClothesItem clothesItem) {
    return repository.save(clothesItem);
  }

  @Override
  public Map<UUID, ClothesItem> findAll() {
    return repository.findAll();
  }
}
