package com.example.backend.dto;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArticleRequestDto {
	private String title;
	private String content;

}