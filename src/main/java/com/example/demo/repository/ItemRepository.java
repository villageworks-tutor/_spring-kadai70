package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {

	/**
	 * カテゴリー検索
	 * SELECT * FROM items WHERE category_id = ?
	 */
	List<Item> findByCategoryId(Integer categoryId);

	/**
	 * 商品名のキーワード検索
	 * SELECT * FROM items WHERE name LIKE ?
	 */
	List<Item> findByNameContaining(String keyword);

	/**
	 * 商品名のキーワード検索かつ上限金額以下の価格の商品検索
	 * SELECT * FROM items WHERE name LIKE ? AND price <= ? 
	 */
	List<Item> findByNameContainingAndPriceLessThanEqual(String keyword, Integer maxPrice);

	/**
	 * 上限金額以下の商品検索
	 * SELECT * FROM item WHERE price <= ?
	 */
	List<Item> findByPriceLessThanEqual(Integer maxPrice);

}
