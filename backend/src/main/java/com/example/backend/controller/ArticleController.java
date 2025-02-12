package com.example.backend.controller;

import com.example.backend.dto.ArticleRequestDto;
import com.example.backend.dto.ArticleResponseDto;
import com.example.backend.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/articles")
@RequiredArgsConstructor
public class ArticleController {
	private final ArticleService articleService;


	@GetMapping
	public List<ArticleResponseDto> getArticles() {
		return articleService.getArticles();
	}

	@PostMapping
	public ArticleResponseDto createArticle(
			@RequestParam("title") String title,
			@RequestParam("content") String content,
			@RequestParam("file")MultipartFile file
			) {

		ArticleRequestDto requestDto = ArticleRequestDto.builder()
				.title(title)
				.content(content)
				.file(file)
				.build();

		return articleService.createArticle(requestDto);
	}

  @GetMapping("/{id}")
	public ArticleResponseDto getArticleById(@PathVariable Long id) {
		return articleService.getArticleById(id);
	}

}
