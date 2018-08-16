package com.openmatrix;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;

@RestController
public class ItemController {
	private final ReactiveRedisOperations<String, Item> coffeeOps;

	@Autowired
	private ItemLoader loader;
	
	ItemController(ReactiveRedisOperations<String, Item> coffeeOps) {
		this.coffeeOps = coffeeOps;
	}

	@GetMapping("/inventory")
	public Flux<Item> all() {
		return coffeeOps.keys("*").flatMap(coffeeOps.opsForValue()::get);
	}

	@GetMapping("/item/{itemName}")
	public void getItem(@PathVariable("itemName") String itemName) {
		loader.getInventory(itemName);
	}
}
