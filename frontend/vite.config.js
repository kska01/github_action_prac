import { defineConfig } from "vite";
import react from "@vitejs/plugin-react";
import compression from "vite-plugin-compression";

// https://vite.dev/config/
export default defineConfig({
  plugins: [
    react(),
    compression({
      algorithm: "gzip", // Gzip 알고리즘 사용
      ext: ".gz", // 생성되는 파일의 확장자
      threshold: 10240, // 압축 최소 크기 (10KB 이상만 압축)
    }),
  ],
});
