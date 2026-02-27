# ChâTop: Rental Backend API

[Lire ce fichier en français](#châtop-api-backend-de-location)

## Description

ChâTop is a rental property management backend API built with Spring Boot. This project provides a comprehensive REST API for managing rental properties in tourist areas, including user authentication, property listings, and messaging system. The backend serves as the foundation for the existing Angular frontend application, replacing the previously used mock data from Mockoon and Postman.

## Technologies Used

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.5-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![Java](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-8.0-4479A1?style=for-the-badge&logo=mysql&logoColor=white)
![Spring Security](https://img.shields.io/badge/Spring%20Security-6.5.2-6DB33F?style=for-the-badge&logo=spring-security&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-000000?style=for-the-badge&logo=jsonwebtokens&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-3.9-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)
![OpenAPI](https://img.shields.io/badge/OpenAPI-3.0-6BA539?style=for-the-badge&logo=openapi-initiative&logoColor=white)

## Features

- **User Authentication & Authorization**: JWT-based authentication system
- **Rental Property Management**: CRUD operations for rental properties
- **User Management**: User registration, login, and profile management
- **Messaging System**: Communication between users and property owners
- **RESTful API**: Well-structured REST endpoints
- **Database Integration**: MySQL database with JPA/Hibernate
- **API Documentation**: OpenAPI/Swagger documentation
- **Security**: Spring Security with OAuth2 resource server

## Prerequisites

Before running this project, make sure you have the following installed:

- **Java 21** or higher
- **Maven 3.9** or higher
- **MySQL 8.0** or higher
- **Git** (for cloning the repository)

## Installation

### 1. Clone the Repository

```bash
git clone https://github.com/IbrahimAlsabr/ChaTop.git
cd rentals-backend
```

### 2. Database Setup

1. Create a MySQL database named `rentals_database`
2. Update the database credentials in `src/main/resources/application.properties`:
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/rentals_database
    spring.datasource.username=your_username
    spring.datasource.password=your_password
    ```

### 3. Build and Run

#### Using Maven Wrapper (Recommended)

```bash
# On Windows
./mvnw.cmd spring-boot:run

# On Unix/Linux/macOS
./mvnw spring-boot:run
```

#### Using Maven (if installed globally)

```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

## API Documentation

Once the application is running, you can access the interactive API documentation at:

- **Swagger UI**: `http://localhost:8080/swagger-ui.html`
- **OpenAPI JSON**: `http://localhost:8080/v3/api-docs`

## Architecture

The project follows a layered architecture pattern with clear separation of concerns:

- **Controller Layer**: REST controllers handle HTTP requests and responses, implementing the API endpoints
- **Service Layer**: Business logic implementation, containing the core application functionality
- **Repository Layer**: Data access abstraction using Spring Data JPA repositories
- **Entity Layer**: JPA entities representing the database schema
- **DTO Layer**: Data Transfer Objects for API communication and data validation
- **Configuration Layer**: Security configuration, JWT setup, and application configuration

This architecture ensures maintainability, testability, and follows Spring Boot best practices for enterprise applications.

## API Endpoints

### Authentication

- `POST /api/auth/login` - User login
- `POST /api/auth/register` - User registration
- `GET /api/auth/me` - Get current user profile

### Rentals

- `GET /api/rentals` - Get all rental properties
- `GET /api/rentals/{id}` - Get rental by ID
- `POST /api/rentals` - Create new rental (authenticated)
- `PUT /api/rentals/{id}` - Update rental (authenticated)

### Messages

- `POST /api/messages` - Send message (authenticated)

## Building for Production

To build the application for production:

```bash
./mvnw clean package
```

The JAR file will be created in the `target/` directory.

## Frontend Integration

This backend is designed to work with the existing Angular frontend. The frontend repository can be found at:
[OpenClassrooms Frontend Repository](https://github.com/OpenClassrooms-Student-Center/Developpez-le-back-end-en-utilisant-Java-et-Spring)

## Author

### Ibrahim Alsabr

[![GitHub](https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white)](https://github.com/IbrahimAlsabr) [![LinkedIn](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/ibrahim-alsabr-188939231/) [![Twitter](https://img.shields.io/badge/Twitter-1DA1F2?style=for-the-badge&logo=twitter&logoColor=white)](https://twitter.com/home?lang=fr)

---

---

---

---

<br>

# ChâTop : API Backend de Location

[Read this file in English](#châtop-rental-backend-api)

## Description

ChâTop est une API backend de gestion de locations immobilières construite avec Spring Boot. Ce projet fournit une API REST complète pour gérer les propriétés locatives dans les zones touristiques, incluant l'authentification des utilisateurs, les annonces de propriétés et le système de messagerie. Le backend sert de fondation pour l'application frontend Angular existante, remplaçant les données mockées précédemment utilisées via Mockoon et Postman.

## Technologies Utilisées

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.5-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![Java](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-8.0-4479A1?style=for-the-badge&logo=mysql&logoColor=white)
![Spring Security](https://img.shields.io/badge/Spring%20Security-6.5.2-6DB33F?style=for-the-badge&logo=spring-security&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-000000?style=for-the-badge&logo=jsonwebtokens&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-3.9-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)
![OpenAPI](https://img.shields.io/badge/OpenAPI-3.0-6BA539?style=for-the-badge&logo=openapi-initiative&logoColor=white)

## Fonctionnalités

- **Authentification et Autorisation des Utilisateurs** : Système d'authentification basé sur JWT
- **Gestion des Propriétés Locatives** : Opérations CRUD pour les propriétés locatives
- **Gestion des Utilisateurs** : Inscription, connexion et gestion des profils utilisateurs
- **Système de Messagerie** : Communication entre utilisateurs et propriétaires
- **API RESTful** : Points de terminaison REST bien structurés
- **Intégration Base de Données** : Base de données MySQL avec JPA/Hibernate
- **Documentation API** : Documentation OpenAPI/Swagger
- **Sécurité** : Spring Security avec serveur de ressources OAuth2

## Prérequis

Avant d'exécuter ce projet, assurez-vous d'avoir installé :

- **Java 21** ou supérieur
- **Maven 3.9** ou supérieur
- **MySQL 8.0** ou supérieur
- **Git** (pour cloner le dépôt)

## Installation

### 1. Cloner le Dépôt

```bash
git clone https://github.com/IbrahimAlsabr/ChaTop.git
cd rentals-backend
```

### 2. Configuration de la Base de Données

1. Créez une base de données MySQL nommée `rentals_database`
2. Mettez à jour les identifiants de la base de données dans `src/main/resources/application.properties` :
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/rentals_database
    spring.datasource.username=votre_nom_utilisateur
    spring.datasource.password=votre_mot_de_passe
    ```

### 3. Construction et Exécution

#### Utilisation du Maven Wrapper (Recommandé)

```bash
# Sur Windows
./mvnw.cmd spring-boot:run

# Sur Unix/Linux/macOS
./mvnw spring-boot:run
```

#### Utilisation de Maven (si installé globalement)

```bash
mvn spring-boot:run
```

L'application démarrera sur `http://localhost:8080`

## Documentation API

Une fois l'application en cours d'exécution, vous pouvez accéder à la documentation interactive de l'API à :

- **Swagger UI** : `http://localhost:8080/swagger-ui.html`
- **OpenAPI JSON** : `http://localhost:8080/v3/api-docs`

## Architecture

Le projet suit un modèle d'architecture en couches avec une séparation claire des responsabilités :

- **Couche Contrôleur** : Les contrôleurs REST gèrent les requêtes et réponses HTTP, implémentant les points de terminaison de l'API
- **Couche Service** : Implémentation de la logique métier, contenant les fonctionnalités principales de l'application
- **Couche Repository** : Abstraction d'accès aux données utilisant les repositories Spring Data JPA
- **Couche Entité** : Entités JPA représentant le schéma de base de données
- **Couche DTO** : Objets de Transfert de Données pour la communication API et la validation des données
- **Couche Configuration** : Configuration de sécurité, configuration JWT et configuration de l'application

Cette architecture assure la maintenabilité, la testabilité et suit les meilleures pratiques Spring Boot pour les applications d'entreprise.

## Points de Terminaison API

### Authentification

- `POST /api/auth/login` - Connexion utilisateur
- `POST /api/auth/register` - Inscription utilisateur
- `GET /api/auth/me` - Obtenir le profil de l'utilisateur actuel

### Locations

- `GET /api/rentals` - Obtenir toutes les propriétés locatives
- `GET /api/rentals/{id}` - Obtenir une location par ID
- `POST /api/rentals` - Créer une nouvelle location (authentifié)
- `PUT /api/rentals/{id}` - Mettre à jour une location (authentifié)

### Messages

- `POST /api/messages` - Envoyer un message (authentifié)

## Construction pour la Production

Pour construire l'application pour la production :

```bash
./mvnw clean package
```

Le fichier JAR sera créé dans le répertoire `target/`.

## Intégration Frontend

Ce backend est conçu pour fonctionner avec le frontend Angular existant. Le dépôt frontend peut être trouvé à :
[Dépôt Frontend OpenClassrooms](https://github.com/OpenClassrooms-Student-Center/Developpez-le-back-end-en-utilisant-Java-et-Spring)

## Auteur

### Ibrahim Alsabr

[![GitHub](https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white)](https://github.com/IbrahimAlsabr) [![LinkedIn](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/ibrahim-alsabr-188939231/) [![Twitter](https://img.shields.io/badge/Twitter-1DA1F2?style=for-the-badge&logo=twitter&logoColor=white)](https://twitter.com/home?lang=fr)
