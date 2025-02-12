package com.example.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class S3Service {
    // 파일 저장 경로(폴더)
    private static final String FILE_PATH_PREFIX = "articles/";
    private final S3Client s3Client;
    @Value("${BUCKET_NAME}")
    private String bucketName;
    @Value("${REGION}")
    private String region;

    // S3 파일 업로드 처리 메서드
    // 파일을 articleService에서 받은 후
    // s3 업로드 후 imageUrl 과 s3Key를 반환받는 메서드
    public Map<String, String> uploadFile(MultipartFile file) {

        //s3key 생성
        String s3Key = FILE_PATH_PREFIX + UUID.randomUUID() + "_" + file.getOriginalFilename();

        // s3 버킷에 파일을 업로드
        uploadFileToS3(s3Key, file);

        String IMAGE_URL_FORMAT = "https://%s.s3.%s.amazonaws.com/%s";
        String imageUrl = String.format(IMAGE_URL_FORMAT, bucketName, region, s3Key);

        return Map.of(
                "imageUrl", imageUrl,
                "s3key", s3Key
        );
    }

    private void uploadFileToS3(String s3key, MultipartFile file) {
        try {
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(s3key)
                    .contentType(file.getContentType())
                    .contentLength(file.getSize())
                    .build();

            s3Client.putObject(putObjectRequest, RequestBody.fromInputStream(file.getInputStream(), file.getSize()));
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
