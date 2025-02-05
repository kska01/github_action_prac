FROM node:23-alpine AS build
# VITE_API_URL 환경변수를 빌드 시점에 주입받기 위한 ARG 선언
ARG VITE_API_URL
# ARG로 받은 VITE_API_URL 값을 ENV로 설정하여 빌드 시점에 사용
ENV VITE_API_URL=$VITE_API_URL
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build

FROM nginx:mainline-alpine-slim
COPY --from=build /app/dist /usr/share/nginx/html
COPY nginx.conf /etc/nginx/nginx.conf
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]