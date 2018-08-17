package com.openmatrix;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;

@RestController
public class ItemController {
	private final ReactiveRedisOperations<String, Item> itemOps;

	@Autowired
	private ItemLoader loader;
	
	ItemController(ReactiveRedisOperations<String, Item> coffeeOps) {
		this.itemOps = coffeeOps;
	}

	@GetMapping("/inventory")
	public Flux<Item> all() {
		return itemOps.keys("*").flatMap(itemOps.opsForValue()::get);
	}

	@GetMapping("/checkinventory/{itemName}")
	public Flux<Item> getItem(@PathVariable("itemName") String itemName) {
		loader.getInventory(itemName);
		return itemOps.keys("*").flatMap(itemOps.opsForValue()::get);
	}
}
