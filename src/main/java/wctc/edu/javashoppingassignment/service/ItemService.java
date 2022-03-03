package wctc.edu.javashoppingassignment.service;

import wctc.edu.javashoppingassignment.entity.Item;

import java.util.List;

public interface ItemService {
    List<Item> getItemList();

    Item getItem(int id);

    Item getItem(String name);

    void savePurchase(int id, int amount);
}