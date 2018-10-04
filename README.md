# RMDechiffreur

## Objectif

Ce projet a pour but d'offrir une interface agréable pour l'interpréation d'instructions RPG Maker 2003.

Un jeu RPG Maker est constitué de fichiers qui décrivent, entre autres, des suites d'instructions. Toutes les
instructions sont codées avec le code de l'instruction, une chaîne et un tableau de nombres. Ce format force à
connaître la signification de chaque nombre et de chaque code.

L'objectif est ici de proposer une interface qui permet de transformer des instructions avec des paramètres abstraits :

Par exemple
* [10110, "Chaton", pas de nombre] deviendrait afficherMessage("Chaton");
* [11550, "miaou.wav", {70, 100, 50}] deviendrait jouerEffetSonore(fichier : "miaou.wav", volume: 70%, tempo: 100%, balance: 50 ) en documentant que la balance signifie l'endroit d'où on entend le son (50 = milieu, 0 = seulement à gauche, 100 = seulement à droite).

## Utilisation

La classe Projet regroupe toutes les méthodes permettant d'interpréter les fichiers de manière générique. Par exemple
lire tous les objets de la base.

Certaines méthodes exigent d'implémenter un objet de type "ExecuterInstrutions". Cette interface englobe la totalité
des instructions pouvant être appelées hors d'un combat. L'objectif est d'implémenter seulement les instructions
interessantes.

Certains paramètres sont des interfaces que l'on peut soit visiter, soit exploiter à travers une méthode prenant un
comportement par type. Dans certains cas (les conditions et le changement de variables / d'interrupteurs), il est
possible d'implémenter les interface ExtChangeVariable et ExtCondition pour implémenter seulement les comportements
des possibilités qui nous interessent.

Des exemples d'implémentations sont disponibles dans ![RMEventReader](https://github.com/BruJu/RMEventMonsterReader).


## Dépendances

Ce projet est dépendant de ![JavaLCFReader](https://github.com/BruJu/JavaLCFReader) pour la lecture des fichiers LCF.


## Crédits

* **Développement du projet par Julian Bruyat** : Ce projet a été réalisé lors du second semestre de l'année 2018.
