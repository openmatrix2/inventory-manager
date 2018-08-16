package com.openmatrix;

import javax.annotation.PostConstruct;

import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class ItemLoader {

	private static final int FIVE_HUNDRED = 500;

	private final ReactiveRedisConnectionFactory factory;
	private final ReactiveRedisOperations<String, Item> itemOps;

	public ItemLoader(ReactiveRedisConnectionFactory factory, ReactiveRedisOperations<String, Item> coffeeOps) {
		this.factory = factory;
		this.itemOps = coffeeOps;
	}

	@PostConstruct
	public void loadData() {
		factory.getReactiveConnection().serverCommands().flushAll()
				.thenMany(Flux.just("Water", "Coffee", "Coke").map(name -> new Item(name, FIVE_HUNDRED))
						.flatMap(item -> itemOps.opsForValue().set(item.getName(), item)))
				.thenMany(itemOps.keys("*").flatMap(itemOps.opsForValue()::get)).subscribe(System.out::println);

	}
	
	public void getInventory(String name1) {
		Mono.just(1)
				.thenMany(itemOps.opsForValue().get(name1).map(item -> new Item(item.getName(), item.reduceInventory()))
						.flatMap(item -> itemOps.opsForValue().set(item.getName(), item)))
				.thenMany(itemOps.keys("*").flatMap(itemOps.opsForValue()::get)).subscribe(System.out::println);

	}
}
