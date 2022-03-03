package wctc.edu.javashoppingassignment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wctc.edu.javashoppingassignment.entity.Item;
import wctc.edu.javashoppingassignment.repo.ItemRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BasicItemService implements ItemService{
    private ItemRepository itemRepository;

    @Autowired
    public BasicItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public List<Item> getItemList() {
        return itemRepository.findAllByOrderByName();
    }

    @Override
    public Item getItem(int id) {
        Optional<Item> i = itemRepository.findById(id);
        return i.orElse(null);
    }

    @Override
    public Item getItem(String name) {
        Item it = null;
        for (int i = 0; i < itemRepository.count(); i++) {
            if(itemRepository.findAllByOrderByName().get(i).getName().equalsIgnoreCase(name)){
                it = itemRepository.findAllByOrderByName().get(i);
            };
        }

        return it;
    }

    @Override
    public void savePurchase(int id, int amount) {
        Item item = getItem(id);
        if(item != null&& item.getInventory()>=amount) {
            item.setInventory(item.getInventory() - amount);
            itemRepository.save(item);
        }
    }
}
