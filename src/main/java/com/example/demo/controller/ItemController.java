package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Category;
import com.example.demo.entity.Item;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ItemRepository;

@Controller
public class ItemController {
	@Autowired
	CategoryRepository categoryRepository;
	@Autowired
	ItemRepository itemRepository;
	
	// 商品一覧表示
	@GetMapping("/items")
	public String index(
			@RequestParam(name = "categoryId", defaultValue = "") Integer categoryId,
			@RequestParam(name = "keyword", defaultValue = "") String keyword,
			Model model) {
		// カテゴリ検索リンク用のカテゴリーリストを取得
		List<Category> categoryList = categoryRepository.findAll();
		// 取得したカテゴリーリストをスコープに登録
		model.addAttribute("categoryList", categoryList);
		
		// リクエストパラメータによって取得する商品リストを変更
		List<Item> itemList = null;
		if (categoryId != null) {
			// カテゴリーIDが送信された場合：カテゴリー検索で商品を取得
			itemList = itemRepository.findByCategoryId(categoryId);
		} else if (!keyword.isEmpty()) {
			// 	キーワードが入力された場合：商品名のキーワード検索で商品を取得
			itemList = itemRepository.findByNameContaining(keyword);
		} else {
			// カテゴリーIDが送信されない場合：すべての商品を取得
			itemList = itemRepository.findAll();
		}
		// 取得した商品リストをスコープに登録
		model.addAttribute("itemList", itemList);
		// 画面遷移
		return "items";
	}
}
