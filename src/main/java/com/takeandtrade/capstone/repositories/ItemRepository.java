package com.takeandtrade.capstone.repositories;

import com.takeandtrade.capstone.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    Item findItemByItemName (String search);
    List<Item> findItemByItemNameContains (String search);
}
