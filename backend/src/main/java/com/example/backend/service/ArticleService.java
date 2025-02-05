package com.example.backend.service;

import com.example.backend.domain.Article;
import com.example.backend.dto.ArticleRequestDto;
import com.example.backend.dto.ArticleResponseDto;
import com.example.backend.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleService {

	private final ArticleRepository articleRepository;

	@Transactional
	public ArticleResponseDto createArticle(ArticleRequestDto requestDto) {

		Article article = Article.builder()
				.title(requestDto.getTitle())
				.content(requestDto.getContent())
				.build();

		Article savedArticle = articleRepository.save(article);

		return toResponseDto(savedArticle);
	}

	@Transactional
	public List<ArticleResponseDto> getArticles() {

		return articleRepository.findAll()
				.stream()
				.map(this::toResponseDto)
				.collect(Collectors.toList());
	}

  @Transactional
	public ArticleResponseDto getArticleById(Long id)  {
		Article article = articleRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Article not found with id: " + id));


		return toResponseDto(article);
	}


	private ArticleResponseDto toResponseDto(Article article) {

		return ArticleResponseDto.builder()
				.id(article.getId())
				.title(article.getTitle())
				.content(article.getContent())
				.createdAt(article.getCreatedAt())
				.updatedAt(article.getUpdatedAt())
				.build();

	}
}