Lancement du programme : 

1 - Dans une console :
	- se positionner dans le répertoire où se trouve le jeu
	- entrer la ligne de commande : java -jar EscapeGame.jar
				   ou : java -jar EscapeGame.jar modedev (lancement du jeu en mode developpeur)

OU
2 - double-click sur le fichier EscapeGame.bat

OU
3 - Lancer le programme dans IntelliJ : attention, le nettoyage de la console ne fonctionnera pas dans ce cas là.

-- # Principes de jeu # --

Mode “challenger” :

L'intelligence artificielle de l’ordinateur joue le rôle de défenseur. Elle définit une combinaison de X chiffres aléatoirement.
Le joueur a le rôle d’attaquant et doit faire une proposition d’une combinaison de X chiffres.
L'intelligence artificielle de l’ordinateur indique pour chaque chiffre de la combinaison proposée si le chiffre de sa combinaison est plus grand (+), plus petit (-) ou si c’est le bon (=).
Il y a un nombre limité d’essais.

Exemple :
(Combinaison secrète : 1234)
Proposition : 4278 -> Réponse : -=--
Proposition : 2214 -> Réponse : -=+=

Mode “défenseur” :

Le joueur (cette fois dans le rôle de défenseur) définit une combinaison de X chiffres aléatoirement.
L'intelligence artificielle de l’ordinateur doit faire une proposition d’une combinaison de X chiffres (c’est le rôle attaquant).
Le joueur indique pour chaque chiffre de la combinaison proposée si le chiffre de sa combinaison est plus grand (+), plus petit (-) ou si c’est le bon (=).
L’intelligence artificielle fait une autre proposition en se basant sur la réponse fournit par le joueur.
Il y a un nombre limité d’essais.
 
Mode “duel” :

Le joueur et l’intelligence artificielle de l’ordinateur jouent tour à tour. Le premier à trouver la combinaison secrète de l'autre a gagné !.
 

Au démarrage, l’utilisateur choisit un mode (challenger, défenseur, duel). L'application lance le mode sélectionné.
L'utilisateur joue. S'il perd, l'application affiche la solution.
À la fin de la partie, l'utilisateur peut choisir :
de rejouer au même mode ;
de lancer un autre mode (retour à l'écran de choix du début) ;
de quitter l'application.
