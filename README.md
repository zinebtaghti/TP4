# Application de Gestion des Shows

## Description
*Gestion des Shows* est une application Android simple qui affiche une liste de spectacles(shows) dans un RecyclerView. Chaque spectacle contient un nom, une image, et une note. L'utilisateur peut également rechercher des spectacles en utilisant une barre de recherche dans le menu.
## Objectifs du projet :
- Affichage d'une liste de spectacles avec leurs informations (nom, image, et note).
- Recherche dynamique à travers les spectacles avec un `SearchView` intégré au menu.
- Partage d'un texte via l'option "Share" du menu.


## Fonctionnalités
- *Liste des spectacles* : Affiche une collection de spectacles sous forme de liste.
- *Recherche de spectacles* : Permet aux utilisateurs de rechercher un spectacle dans la liste.
- Partage d'un texte via l'option "Share" du menu.
## Structure du projet

### Activités principales
1. *SplashActivity* :
   - Une activité de démarrage avec une animation appliquée sur une image (logo de l'application).
   
2. *ListActivity* :
   - Gère l'affichage de la liste des spectacles sous forme de RecyclerView.
   - Chaque élément de la liste affiche le nom, une image, et une note).
   
3. *ShowService* :
   - Service chargé de la gestion et du traitement des données liées aux spectacles.

### XML pour l'interface graphique

- *activity_splash.xml* : Fichier de mise en page pour la SplashActivity pour afficher une animation de démarrage de l'application.
- *activity_list.xml* : Fichier de mise en page pour la ListActivity affichant la liste des spectacles.
  - Utilisation d'un RecyclerView pour afficher les spectacles.
  
- *show_item.xml* : Fichier de mise en page des éléments de la liste des spectacles, avec des TextView pour le nom, ImageView pour l'image et le rating bar.
- *menu.xml* : Menu de l'application avec les options de recherche et de partage.

## Logique Java
1. *SplashActivity.java* :
   - Afficher une activité animée pour une durée déterminée avant de rediriger l'utilisateur vers ListActivity.
   
2. *ListActivity.java* :
   - Charge la liste des spectacles et les affiche dans un RecyclerView.

3. *ShowService.java* :
   - Assure la logique d'accès et de gestion des données des spectacles.
4. *StarAdapter.java* : Adapter pour gérer l'affichage des spectacles dans le `RecyclerView`.

## Technologies utilisées
- *Langage* : Java (Android SDK)
- *Interface utilisateur* : XML (pour les mises en page)
- *Plateforme* : Android
## Instructions d'installation
1. Clonez le dépôt GitHub :
   ```bash
   git clone https://github.com/zinebtaghti/TP4
2. Ouvrez le projet dans Android Studio.
3. Lancez l'application sur un émulateur Android ou un appareil physique connecté.

## Vidéo Démonstration

https://github.com/user-attachments/assets/0a932f4a-2f2d-4fa8-8a4d-9759d7bdc713
