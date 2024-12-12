# 1. Node.js ile build işlemini yap
FROM node:18-alpine as build
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build

# 2. Nginx ile React uygulamasını serve et
FROM nginx:1.25
COPY --from=build /app/build /usr/share/nginx/html
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]


FROM openjdk:22
WORKDIR /app
ADD target/Laborant-0.0.1-SNAPSHOT.jar Laborant-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=docker","Laborant-0.0.1-SNAPSHOT.jar"]
