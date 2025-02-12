package com.example.backend.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Table(name = "articles")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Article {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String title;

	@Column(nullable = false, length = 1000)
	private String content;

	// S3 객체의 접근 URL
	@Column(nullable = true)
	private String imageUrl;

	// S3 버킷의 객체를 식별하는 키
	// uuid_filename.jpg 같은 형태
	@Column(nullable = true)
	private String s3Key;

	// 업로드 파일의 원본 파일명
	// 화면 출력용
	@Column(nullable = true)
	private String originalFileName;

	@CreatedDate
	@Column(nullable = true, updatable = false)
	private LocalDateTime createdAt;

	@LastModifiedDate
	@Column(nullable = true)
	private LocalDateTime updatedAt;
}

