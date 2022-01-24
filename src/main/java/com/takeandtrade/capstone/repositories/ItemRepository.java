package com.takeandtrade.capstone.repositories;

import com.takeandtrade.capstone.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query("SELECT i FROM Item i WHERE CONCAT(i.itemName, i.itemDescription, i.itemCondition, i.category.name, i.user.username, i.user.zipcode) LIKE %?1%")
    public List<Item> search(String search);

}
