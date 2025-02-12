package com.example.backend.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArticleRequestDto {
	private String title;
	private String content;
	private MultipartFile file;

}