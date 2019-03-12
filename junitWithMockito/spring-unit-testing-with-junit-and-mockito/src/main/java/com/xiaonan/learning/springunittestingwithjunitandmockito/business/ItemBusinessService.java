package com.xiaonan.learning.springunittestingwithjunitandmockito.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaonan.learning.springunittestingwithjunitandmockito.data.ItemRepository;
import com.xiaonan.learning.springunittestingwithjunitandmockito.model.Item;

@Service
public class ItemBusinessService {

	
	@Autowired
	private ItemRepository repository;
	
	public Item retrieveHardcodedItem() {
		return new Item(2, "Ball2", 10, 100);
	}

	public List<Item> retrieveAllItems() {
		List<Item> items = repository.findAll();
		items.forEach(item -> 
			item.setValue(item.getPrice() * item.getQuantity())
		);
		/*for(Item item:items) {
			item.setValue(item.getPrice() * item.getQuantity());
		}*/
		return repository.findAll();
	}
	
}

