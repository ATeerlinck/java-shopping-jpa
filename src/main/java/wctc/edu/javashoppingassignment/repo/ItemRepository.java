package wctc.edu.javashoppingassignment.repo;

import wctc.edu.javashoppingassignment.entity.Item;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface ItemRepository extends CrudRepository<Item, Integer>{
    List<Item> findAllByOrderByName();
}
