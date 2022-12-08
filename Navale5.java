/**  
 *@author Florian GREDY  Quentin RADLO 
 *<h1>Projet Bataille Navale S1 | Groupe I2 </h1>
*/
public class Navale5{
		
		public static void main(String args[]){
			AffichageMenuPrincipal();
			
	}
	/**
	*@param Entree : Aucune 
	*@return : Vrai si l'utilisateur souhaite tricher faux sinon 
	*/
	static boolean Triche(){
		boolean LaTriche;
		LaTriche =  false;
		int saisieCorrect;
		saisieCorrect = 0;
		
		char Saisie;
		Saisie = 'a';
		do{

			Ecran.afficher("Voulez vous tricher Y/N ?");
			Saisie = Clavier.saisirChar();
			
			if ((Saisie == 'y') || (Saisie == 'Y')){
				LaTriche = true;
				saisieCorrect = 1;
				Ecran.afficher("La position des bateaux ennemi sera affiche a chaque debut de tour");
			}  
			else if ((Saisie == 'n') || (Saisie == 'N')){
				LaTriche = false;
				saisieCorrect = 1;
				Ecran.afficher("La position des bateaux ennemi ne sera pas affiche a chaque debut de tour");
			}
	} while (saisieCorrect == 0);
 
		return LaTriche;

	}
	/**	
	* Sert a lancer le menu principale du jeu ou le joueur fait le choix du monde de jeu 
	* @param Aucune
	* @return Aucune
	*/		
	static void AffichageMenuPrincipal(){
		int SaisieUser;
		Ecran.afficher("*------------------------------------------------*\n");
		Ecran.afficher("*                   BATAILLE NAVALE                 *\n*------------------------------------------------*\n*                      Instructions :                     *\n*Selectionnez le mode de jeu au quel vous   *\n*voulez jouer en inserant le chiffre               *\n*correspondant dans le l'invite de commande*\n" );
		Ecran.afficher("*1. Regles*\n");
		Ecran.afficher("*2. Test du placement automatique des bateaux *\n");
		Ecran.afficher("*3. Joueur seul contre Ordinateur*\n");
		Ecran.afficher("*4. Joueur contre Ordinateur*\n");
		Ecran.afficher("*5. Joueur contre Joueur*\n");
		Ecran.afficher("\nVeuilez saisir un nombre correspondant au mode de jeu souhaite\n");
		SaisieUser = Clavier.saisirInt();
		//Ce switch va lancer le mode de jeu correspondant suivant les valeurs indiques a l'affichage precedent
		switch(SaisieUser){
			case 1:
				AfficherRegles();
				AffichageMenuPrincipal();
				break;
			case 2:
				E2();
				break;
			case 3:
				E3();
				break;
			case 4:
				JoueurContreOrdi();
				break;
			case 5:
				JoueurContreJoueur();
				break;
		}
	
	
	}		
	/**
	 * Affiche les regles du jeu
	 * @param Aucun
	 * @return Aucun
	 */
	static void AfficherRegles(){
		Ecran.afficher("\nVous avez le choix entre plusieurs modes de jeu, le mode 2 et 3 vous permet de tester le placement de bateaux et vous assurez qu'il marche dans les 2 cas.\nLe mode de jeu 4 vous permet de deviner l'emplacement des bateaux de l'ordinateur sans que celui ci puisse riposter.\nEt finalement le mode de jeu 5 et 6, vous permet de jouer une partie classique contre un ordinateur ou un autre joueur.\nDernierement vous avez dans les differents mode de jeu la possibilite de tricher, si vous faites le choix de tricher, la position des bateaux ennemi sera affiche a chaque tour.\n");
	}	
	/**	
	*Sert a lancer l'etape 3 du projet, soit le mode de jeu Joueur Contre Ordi sans riposte du dernier
	*@param Entree : Aucune
	*@return Sortie : Aucune
	*/		
	static void E3(){

		boolean triche;
		triche = Triche();


		Case CaseOrdi = new Case();
		CaseOrdi  = DeclarationDeLaGrille();
		int totalpointOrdi;
		totalpointOrdi = 2;

		CaseOrdi = PlacerPorteAv(CaseOrdi);
		CaseOrdi = PlacerPorteAv2(CaseOrdi);
		CaseOrdi = PlacerPorteAv3(CaseOrdi);
		CaseOrdi = PlacerTorpilleurOrdi(CaseOrdi);
		CaseOrdi = PlacerCroiseurOrdi(CaseOrdi);


		while (totalpointOrdi > 1){
			totalpointOrdi = ((CaseOrdi.croiseur + CaseOrdi.torpilleur + CaseOrdi.porte_avion + CaseOrdi.porte_avion2 + CaseOrdi.porte_avion3));
			//Ecran.afficher("\n La valeur de total point est : ", totalpointOrdi);
			if (triche == true){
				Ecran.afficher("\nLe grille de ennemi se trouve si dessous\n");
				AffichageGrille(CaseOrdi);
			}
			LancerMissileJoueur(CaseOrdi);
			Ecran.afficher("\n Vous avez lance un missile ! \n");
			AffichageGrilleEnnemi(CaseOrdi);
			/* DEBUG Pour voir le score en direct
			Ecran.afficher("\n La valeur de croiseur est :", CaseOrdi.croiseur);
			Ecran.afficher("\n La valeur de torpilleur est :", CaseOrdi.torpilleur);
			Ecran.afficher("\n La valeur de porteavion  est :", CaseOrdi.porte_avion);
			Ecran.afficher("\n La valeur du sous marin 1 est :", CaseOrdi.porte_avion2);
			Ecran.afficher("\n La valeur du sous marin 2 est :", CaseOrdi.porte_avion3);

			//totalpoint = (CaseJoueur.croiseur + CaseJoueur.torpilleur + CaseJoueur.porte_avion + CaseJoueur.porte_avion2 + CaseJoueur.porte_avion3);
			 */
		}
		
		Ecran.afficher("Vous avez gagne !");
			
	}
	static void JoueurContreOrdi(){

		boolean TricheOuNon;
		TricheOuNon = Triche();
		

		Case CaseJoueur  = new Case();
		Case CaseOrdi  = new Case();
		
		CaseJoueur = DeclarationDeLaGrille();
		CaseOrdi = DeclarationDeLaGrille();

		int totalPointsJoueur;
		int totalPointsOrdi;

		totalPointsJoueur = 2;
		totalPointsOrdi = 2;
		
		//Placement des bateaux de l'ordinateur
		
		CaseOrdi = PlacerTorpilleurOrdi(CaseOrdi);
		CaseOrdi = PlacerPorteAv2(CaseOrdi);
		CaseOrdi = PlacerPorteAv2(CaseOrdi);
		CaseOrdi = PlacerCroiseurOrdi(CaseOrdi);
		CaseOrdi = PlacerPorteAv(CaseOrdi);	

		Ecran.afficher("\nL'ordinateur a choisi la position de ses bateaux");
		Ecran.afficher("\nChoisissez la position de vos bateaux");

		CaseJoueur = PlacerTorpilleur(CaseJoueur);
		CaseJoueur = PlacerPorteAvion2(CaseJoueur);
		CaseJoueur = PlacerPorteAvion3(CaseJoueur);
		CaseJoueur = PlacerCroiseur(CaseJoueur);
		CaseJoueur = PlacerPorteAvion(CaseJoueur);
		
		while ((totalPointsOrdi > 1) && (totalPointsJoueur > 1)){
			totalPointsOrdi = ((CaseOrdi.croiseur + CaseOrdi.torpilleur + CaseOrdi.porte_avion + CaseOrdi.porte_avion2 + CaseOrdi.porte_avion3));
			totalPointsJoueur = ((CaseJoueur.croiseur + CaseJoueur.torpilleur + CaseJoueur.porte_avion + CaseJoueur.porte_avion2 + CaseJoueur.porte_avion3));
			if (TricheOuNon == true){
				Ecran.afficher("\nLa grille de ennemi se trouve si dessous \n");
				AffichageGrille(CaseOrdi);
			}
		LancerMissileJoueur(CaseOrdi);
			Ecran.afficher("\n==================================================== \n");
			Ecran.afficher("\nVous avez lance un missile ! \n Le resultat du lance se trouve ci dessous");
			AffichageGrilleEnnemi(CaseOrdi);
			Ecran.afficher("\n==================================================== \n");
			LancerMissileOrdi(CaseJoueur);
			Ecran.afficher("\nL'ordinateur a lance un missile \n Le resultat du lance ennemi se trouve si dessous \n");
			AffichageGrilleEnnemi(CaseJoueur);
			Ecran.afficher("\nTotal points joueurs = ", totalPointsJoueur, "Total points ordi = ", totalPointsOrdi, "\n");
		}

		if (totalPointsOrdi > 1){
			Ecran.afficher("\nVous avez perdu \n");
		}

		else{
			Ecran.afficher("\nVous avez gagne \n");
		}
			
		Ecran.afficherln("\n CaseOrdi.torpilleur = ", CaseOrdi.torpilleur, "\n CaseOrdi.sous_marin1 = ", CaseOrdi.sous_marin1, "\n CaseOrdi.sous_marin2 = ", CaseOrdi.sous_marin2, "\n CaseOrdi.croiseur = ", CaseOrdi.croiseur, "CaseOrdi.porte_avion = ", CaseOrdi.porte_avion);
	}

	/**
	 * Lance le mode de jeu joueur contre Joueur
	 * @param Entree : Aucune
	 * @return Sortie : Rien
	 */
static void JoueurContreJoueur(){

		boolean TricheOuNon;
		TricheOuNon = Triche();
		
		Case CaseJoueur1  = new Case();
		Case CaseJoueur2  = new Case();
		
		CaseJoueur1 = DeclarationDeLaGrille();
		CaseJoueur2 = DeclarationDeLaGrille();

		int compteur;
		int totalPointsJoueur1;
		int totalPointsJoueur2;

		compteur = 0;
		totalPointsJoueur1 = 2;
		totalPointsJoueur2 = 2;
		
		//Placement des bateaux de l'ordinateur
	
		Ecran.afficher("\n Joueur 1 place ses bateaux : \n");

		CaseJoueur1 = PlacerTorpilleur(CaseJoueur1);
		CaseJoueur1 = PlacerPorteAvion2(CaseJoueur1);
		CaseJoueur1 = PlacerPorteAvion3(CaseJoueur1);
		CaseJoueur1 = PlacerCroiseur(CaseJoueur1);
		CaseJoueur1 = PlacerPorteAvion(CaseJoueur1);

		Ecran.afficher("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		Ecran.afficher("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		Ecran.afficher("\n Joueur 2 place ses bateaux : \n");

		CaseJoueur2 = PlacerTorpilleur(CaseJoueur2);
		CaseJoueur2 = PlacerPorteAvion2(CaseJoueur2);
		CaseJoueur2 = PlacerPorteAvion3(CaseJoueur2);
		CaseJoueur2 = PlacerCroiseur(CaseJoueur2);
		CaseJoueur2 = PlacerPorteAvion(CaseJoueur2);
		
		Ecran.afficher("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		Ecran.afficher("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		Ecran.afficher("\nQue le jeu commence !\n");

		while ((totalPointsJoueur2 > 1) && (totalPointsJoueur1 > 1)){
			totalPointsJoueur2 = ((CaseJoueur2.croiseur + CaseJoueur2.torpilleur + CaseJoueur2.porte_avion + CaseJoueur2.porte_avion2 + CaseJoueur2.porte_avion3));
			totalPointsJoueur1 = ((CaseJoueur1.croiseur + CaseJoueur1.torpilleur + CaseJoueur1.porte_avion + CaseJoueur1.porte_avion2 + CaseJoueur1.porte_avion3));
		
			if (TricheOuNon == true){
				Ecran.afficher("\n La grille de Joueur 1 se trouve si dessous \n");
				AffichageGrille(CaseJoueur1);
				Ecran.afficher("\n La grille de Joueur 2 se trouve si dessous \n");
				AffichageGrille(CaseJoueur2);
			}	
			Ecran.afficher("\nAu tour de Joueur 1 ! \n");
			if (compteur > 0){
				Ecran.afficher("\n==================================================== \n");
				Ecran.afficher("\nVoici ce qu'avait donne votre dernier lance de missile :\n");
			AffichageGrilleEnnemi(CaseJoueur2);}	
			LancerMissileJoueur(CaseJoueur2);

			Ecran.afficher("\n==================================================== \n");
			Ecran.afficher("\n Vous avez lance un missile ! \n Le resultat du lance se trouve ci dessous");
			AffichageGrilleEnnemi(CaseJoueur2);
			Ecran.afficher("\n ==================================================== \n");	
			Ecran.afficher("\nAu tour du Joueur 2\n"); 
			
			if (compteur > 0){
				Ecran.afficher("\n==================================================== \n");
				Ecran.afficher("\nVoici ce qu'avait donne votre dernier lance de missile :\n");
				AffichageGrilleEnnemi(CaseJoueur1);
			}	

			LancerMissileJoueur(CaseJoueur1);
			compteur ++;

			Ecran.afficher("\n==================================================== \n");
			Ecran.afficher("\n Vous avez lance un missile ! \n Le resultat du lance se trouve ci dessous");
			AffichageGrilleEnnemi(CaseJoueur1);
			Ecran.afficher("\n ==================================================== \n");

			Ecran.afficher("\n Il reste  = ", (totalPointsJoueur1 - 1), "cases sur la grille de joueur 1 et ", totalPointsJoueur2, "cases sur la grille de joueur 2\n");
		}
		
		if (totalPointsJoueur2 > 1){
			Ecran.afficher("\nJoueur 1 a gagne la partie !\n");
		}

		else{
			Ecran.afficher("\n Joueur 2 a gagne la partie !\n");
		}
		Ecran.afficherln("\n CaseJoueur2.torpilleur = ", CaseJoueur2.torpilleur, "\n CaseJoueur2.sous_marin1 = ", CaseJoueur2.sous_marin1, "\n CaseJoueur2.sous_marin2 = ", CaseJoueur2.sous_marin2, "\n CaseJoueur2.croiseur = ", CaseJoueur2.croiseur, "CaseJoueur2.porte_avion = ", CaseJoueur2.porte_avion);
				
	}
	/**
	 * Calcule les valeurs des differents bateaux pour voir si ils sont tous coules
	 * @param Entree : La case de l'ordinateur
	 * @return Vrai si tous les bateaux sont coules faux sinon
	 */
	static boolean ConditionVictoireOrdi(Case CaseOrdi){
		boolean victoire;
		victoire = false;
		
		 if (((CaseOrdi.torpilleur != 0) &&  (CaseOrdi.sous_marin1 != 0)) && (((CaseOrdi.sous_marin2 != 0)) && (((CaseOrdi.croiseur != 0)) && (CaseOrdi.porte_avion != 0)))){
			 victoire = true;}
			
		return victoire;
		
		}
	/**
	 * L'utilisateur saisie des coordonnes et envoie un missile sur la case ennemi
	 * @param Entree : la case de l'ennemi ou sera tire le missile
	 * @return Sortie : la case de l'ennemi avec un missile tire dessus
	 */
	static Case SaisirCoo(Case CaseEnnemi){
		//Declaration des variables
		int entierSaisi;
		char charSaisie;
		int transtypage;
		Coo CooSaisie = new Coo();
		//Demande de saisie utilisateur	
		Ecran.afficher("Veuillez entre un caractere : ");
		charSaisie = Clavier.saisirChar();
		
		while(((int)charSaisie< 65) || ((int)charSaisie > 74)){
			Ecran.afficher("Erreur de saisie, veuillez entrer un char correct");
			charSaisie = Clavier.saisirChar();
			}
			
		Ecran.afficher("Veuillez entre un entier : ");
		entierSaisi = Clavier.saisirInt();
		
		while((entierSaisi < 1) || (entierSaisi > 10)){
			Ecran.afficher("Erreur de saisie, veuillez entrer un entier correct");
			entierSaisi = Clavier.saisirInt();
			}
		
		Ecran.afficher("Vous lancez un missile a la case :", charSaisie , entierSaisi);
		CooSaisie.x = charSaisie;
		CooSaisie.y = entierSaisi;
		LancerMissileCorrespondance(CaseEnnemi, CooSaisie);
			
		return CaseEnnemi;
		}
	static Case LancerMissileJoueur(Case LaCase){
		//Declaration des variables
		boolean DejaVise;
		char abscisse;
		int ordonnee;
		Coo LaCoo = new Coo();
		DejaVise = false;
		//Saisie utilisateur
		Ecran.afficher("\n Entrez un char pour l'absicsse \n");
		abscisse = Clavier.saisirChar();
		
		Ecran.afficher("\n Entrez un int pour l'ordonne \n");
		ordonnee = Clavier.saisirInt();

		LaCoo.x = abscisse;
		LaCoo.y = ordonnee;

		DejaVise = BateauEstIlVise(LaCase, LaCoo);
		while (DejaVise == true){
		
			Ecran.afficher("\n Erreur, vous avez deja visee la case ", abscisse, ordonnee);
			Ecran.afficher("\n Entrez un char pour l'absicsse \n");
			abscisse = Clavier.saisirChar();
		
			Ecran.afficher("\n Entrez un int pour l'ordonne \n");
			ordonnee = Clavier.saisirInt();
			LaCoo.x = abscisse;
			LaCoo.y = ordonnee;
			DejaVise = BateauEstIlVise(LaCase, LaCoo);
		}
		
		Ecran.afficher(" \n Vous avez lance un missile a la case :", abscisse,ordonnee);
		LancerMissileCorrespondance(LaCase, LaCoo);
		//Correspondance(LaCase, LaCoo, "");		
		//DEBUG A REMETTRE PEUT ETRE	LancerMissile(LaCase, LaCoo);
		
		return LaCase;
		}
	/**
	 * Cette fonction sert a lancer un missile automatiquement
	 * @param La case ou sera tiree le missile
	 * @return La case entree avec un missile maintenant tire
	 */	
	static Case LancerMissileOrdi(Case LaCase){
	
		boolean DejaVise;
		char abscisse;
		int ordonnee;
		DejaVise = true;
		Coo LaCoo = new Coo();
		
		do{
			abscisse = CharHasard();
			ordonnee = EntierHasard();		
			LaCoo.x = abscisse;
			LaCoo.y = ordonnee;

			DejaVise = BateauEstIlVise(LaCase, LaCoo);
			if (DejaVise == true){
				Ecran.afficher("DEBUG ORDINATEUR : Case deja visee");
			}
		
		}while(DejaVise == true);
		Ecran.afficher(" \n L'ordinateur a lance un missile a la case : ", abscisse,ordonnee);
		LancerMissileCorrespondance(LaCase, LaCoo);	
	//	LancerMissile(LaCase, LaCoo); // DEBUG Normal cette commande ci ?
		
		return LaCase;
		}	
	/**
	 * Cette fonction sert a lancer un missile automatiquement
	 * @param La case ou sera tiree le missile
	 * @return La case entree avec un missile maintenant tire
	 */	
	static Case LancerMissile(Case LaCase, Coo CooSaisie){

		int condition;
		condition = 0;

		CooSaisie.aimed = true;
		if (CooSaisie.boat == false){
			Ecran.afficher("\n A l'eau \n");
			
		}
		else if (CooSaisie.typeBateau == "torpilleur"){
			
			condition = 1;
				if (LaCase.torpilleur == 1){
					Ecran.afficher("\n touche, coule \n");
					LaCase.torpilleur = (LaCase.torpilleur - 1);
				}
				
				else{
					LaCase.torpilleur = (LaCase.torpilleur - 1);
					Ecran.afficher("\n Touche !\n");
				}
			}
				
		else if (CooSaisie.typeBateau == "porte_avion2"){
			
			condition = 1;
			
				if (LaCase.porte_avion2 == 1){
					Ecran.afficher("\n touche, coule \n");
					LaCase.porte_avion2 = (LaCase.porte_avion2 - 1);
				}
				
				else{
					LaCase.porte_avion2 = (LaCase.porte_avion2 - 1);
					Ecran.afficher("\n Touche !\n");
				}
		}
		
		else if (CooSaisie.typeBateau == "porte_avion3"){
			
			condition = 1;
			
				if (LaCase.porte_avion3 == 1){
					Ecran.afficher("\n touche, coule \n");
					LaCase.porte_avion3 = (LaCase.porte_avion3 - 1);
					}
				
				else{
					LaCase.porte_avion3 = (LaCase.porte_avion3 - 1);
					Ecran.afficher("\n Touche !\n");
				}
		}
		else if (CooSaisie.typeBateau == "croiseur"){
			
			condition = 1;
				if (LaCase.croiseur == 1){
					Ecran.afficher("\n touche, coule \n");
					LaCase.croiseur = (LaCase.croiseur - 1);
				}
				
				else{
					LaCase.croiseur = (LaCase.croiseur - 1);
					Ecran.afficher("\n Touche !\n");
				}
		}
		
		else if (CooSaisie.typeBateau == "porte_avion"){
			
			condition = 1;
				if (LaCase.porte_avion == 1){
					Ecran.afficher("\n touche, coule \n");
					LaCase.porte_avion = (LaCase.porte_avion - 1);
				}
				
				else{
					LaCase.porte_avion = (LaCase.porte_avion - 1);
					Ecran.afficher("\n Touche !\n");
				}
		}
		else if (condition == 0){
		//	Ecran.afficher("\nA l'eau !\n");}
		}
		
		return LaCase;
	}
	/**
	 * Cette fonction sert a lancer un missile
	 * @param La case ou sera tiree le missile
	 * @param La coordonne ou sera tiree le missile
	 * @return La case entree avec un missile maintenant tire
	 */	
	static Case LancerMissileCorrespondance(Case Entree, Coo Atester){

		if ((Atester.x == Entree.A1.x) && (Atester.y == Entree.A1.y)){ 
		 LancerMissile(Entree, Entree.A1);} 
		 
		else if ((Atester.x == Entree.A2.x) && (Atester.y == Entree.A2.y)){ 
		 LancerMissile(Entree, Entree.A2);} 
		 
		else if ((Atester.x == Entree.A3.x) && (Atester.y == Entree.A3.y)){ 
		 LancerMissile(Entree, Entree.A3);} 
		 
		else if ((Atester.x == Entree.A4.x) && (Atester.y == Entree.A4.y)){ 
		 LancerMissile(Entree, Entree.A4);} 
		 
		else if ((Atester.x == Entree.A5.x) && (Atester.y == Entree.A5.y)){ 
		 LancerMissile(Entree, Entree.A5);} 
		 
		else if ((Atester.x == Entree.A6.x) && (Atester.y == Entree.A6.y)){ 
		 LancerMissile(Entree, Entree.A6);} 
		 
		else if ((Atester.x == Entree.A7.x) && (Atester.y == Entree.A7.y)){ 
		 LancerMissile(Entree, Entree.A7);} 
		 
		else if ((Atester.x == Entree.A8.x) && (Atester.y == Entree.A8.y)){ 
		 LancerMissile(Entree, Entree.A8);} 
		 
		else if ((Atester.x == Entree.A9.x) && (Atester.y == Entree.A9.y)){ 
		 LancerMissile(Entree, Entree.A9);} 
		 
		else if ((Atester.x == Entree.A10.x) && (Atester.y == Entree.A10.y)){ 
		 LancerMissile(Entree, Entree.A10);} 
		 
		else if ((Atester.x == Entree.B1.x) && (Atester.y == Entree.B1.y)){ 
		 LancerMissile(Entree, Entree.B1);} 
		 
		else if ((Atester.x == Entree.B2.x) && (Atester.y == Entree.B2.y)){ 
		 LancerMissile(Entree, Entree.B2);} 
		 
		else if ((Atester.x == Entree.B3.x) && (Atester.y == Entree.B3.y)){ 
		 LancerMissile(Entree, Entree.B3);} 
		 
		else if ((Atester.x == Entree.B4.x) && (Atester.y == Entree.B4.y)){ 
		 LancerMissile(Entree, Entree.B4);} 
		 
		else if ((Atester.x == Entree.B5.x) && (Atester.y == Entree.B5.y)){ 
		 LancerMissile(Entree, Entree.B5);} 
		 
		else if ((Atester.x == Entree.B6.x) && (Atester.y == Entree.B6.y)){ 
		 LancerMissile(Entree, Entree.B6);} 
		 
		else if ((Atester.x == Entree.B7.x) && (Atester.y == Entree.B7.y)){ 
		 LancerMissile(Entree, Entree.B7);} 
		 
		else if ((Atester.x == Entree.B8.x) && (Atester.y == Entree.B8.y)){ 
		 LancerMissile(Entree, Entree.B8);} 
		 
		else if ((Atester.x == Entree.B9.x) && (Atester.y == Entree.B9.y)){ 
		 LancerMissile(Entree, Entree.B9);} 
		 
		else if ((Atester.x == Entree.B10.x) && (Atester.y == Entree.B10.y)){ 
		 LancerMissile(Entree, Entree.B10);} 
		 
		else if ((Atester.x == Entree.C1.x) && (Atester.y == Entree.C1.y)){ 
		 LancerMissile(Entree, Entree.C1);} 
		 
		else if ((Atester.x == Entree.C2.x) && (Atester.y == Entree.C2.y)){ 
		 LancerMissile(Entree, Entree.C2);} 
		 
		else if ((Atester.x == Entree.C3.x) && (Atester.y == Entree.C3.y)){ 
		 LancerMissile(Entree, Entree.C3);} 
		 
		else if ((Atester.x == Entree.C4.x) && (Atester.y == Entree.C4.y)){ 
		 LancerMissile(Entree, Entree.C4);} 
		 
		else if ((Atester.x == Entree.C5.x) && (Atester.y == Entree.C5.y)){ 
		 LancerMissile(Entree, Entree.C5);} 
		 
		else if ((Atester.x == Entree.C6.x) && (Atester.y == Entree.C6.y)){ 
		 LancerMissile(Entree, Entree.C6);} 
		 
		else if ((Atester.x == Entree.C7.x) && (Atester.y == Entree.C7.y)){ 
		 LancerMissile(Entree, Entree.C7);} 
		 
		else if ((Atester.x == Entree.C8.x) && (Atester.y == Entree.C8.y)){ 
		 LancerMissile(Entree, Entree.C8);} 
		 
		else if ((Atester.x == Entree.C9.x) && (Atester.y == Entree.C9.y)){ 
		 LancerMissile(Entree, Entree.C9);} 
		 
		else if ((Atester.x == Entree.C10.x) && (Atester.y == Entree.C10.y)){ 
		 LancerMissile(Entree, Entree.C10);} 
		 
		else if ((Atester.x == Entree.D1.x) && (Atester.y == Entree.D1.y)){ 
		 LancerMissile(Entree, Entree.D1);} 
		 
		else if ((Atester.x == Entree.D2.x) && (Atester.y == Entree.D2.y)){ 
		 LancerMissile(Entree, Entree.D2);} 
		 
		else if ((Atester.x == Entree.D3.x) && (Atester.y == Entree.D3.y)){ 
		 LancerMissile(Entree, Entree.D3);} 
		 
		else if ((Atester.x == Entree.D4.x) && (Atester.y == Entree.D4.y)){ 
		 LancerMissile(Entree, Entree.D4);} 
		 
		else if ((Atester.x == Entree.D5.x) && (Atester.y == Entree.D5.y)){ 
		 LancerMissile(Entree, Entree.D5);} 
		 
		else if ((Atester.x == Entree.D6.x) && (Atester.y == Entree.D6.y)){ 
		 LancerMissile(Entree, Entree.D6);} 
		 
		else if ((Atester.x == Entree.D7.x) && (Atester.y == Entree.D7.y)){ 
		 LancerMissile(Entree, Entree.D7);} 
		 
		else if ((Atester.x == Entree.D8.x) && (Atester.y == Entree.D8.y)){ 
		 LancerMissile(Entree, Entree.D8);} 
		 
		else if ((Atester.x == Entree.D9.x) && (Atester.y == Entree.D9.y)){ 
		 LancerMissile(Entree, Entree.D9);} 
		 
		else if ((Atester.x == Entree.D10.x) && (Atester.y == Entree.D10.y)){ 
		 LancerMissile(Entree, Entree.D10);} 
		 
		else if ((Atester.x == Entree.E1.x) && (Atester.y == Entree.E1.y)){ 
		 LancerMissile(Entree, Entree.E1);} 
		 
		else if ((Atester.x == Entree.E2.x) && (Atester.y == Entree.E2.y)){ 
		 LancerMissile(Entree, Entree.E2);} 
		 
		else if ((Atester.x == Entree.E3.x) && (Atester.y == Entree.E3.y)){ 
		 LancerMissile(Entree, Entree.E3);} 
		 
		else if ((Atester.x == Entree.E4.x) && (Atester.y == Entree.E4.y)){ 
		 LancerMissile(Entree, Entree.E4);} 
		 
		else if ((Atester.x == Entree.E5.x) && (Atester.y == Entree.E5.y)){ 
		 LancerMissile(Entree, Entree.E5);} 
		 
		else if ((Atester.x == Entree.E6.x) && (Atester.y == Entree.E6.y)){ 
		 LancerMissile(Entree, Entree.E6);} 
		 
		else if ((Atester.x == Entree.E7.x) && (Atester.y == Entree.E7.y)){ 
		 LancerMissile(Entree, Entree.E7);} 
		 
		else if ((Atester.x == Entree.E8.x) && (Atester.y == Entree.E8.y)){ 
		 LancerMissile(Entree, Entree.E8);} 
		 
		else if ((Atester.x == Entree.E9.x) && (Atester.y == Entree.E9.y)){ 
		 LancerMissile(Entree, Entree.E9);} 
		 
		else if ((Atester.x == Entree.E10.x) && (Atester.y == Entree.E10.y)){ 
		 LancerMissile(Entree, Entree.E10);} 
		 
		else if ((Atester.x == Entree.F1.x) && (Atester.y == Entree.F1.y)){ 
		 LancerMissile(Entree, Entree.F1);} 
		 
		else if ((Atester.x == Entree.F2.x) && (Atester.y == Entree.F2.y)){ 
		 LancerMissile(Entree, Entree.F2);} 
		 
		else if ((Atester.x == Entree.F3.x) && (Atester.y == Entree.F3.y)){ 
		 LancerMissile(Entree, Entree.F3);} 
		 
		else if ((Atester.x == Entree.F4.x) && (Atester.y == Entree.F4.y)){ 
		 LancerMissile(Entree, Entree.F4);} 
		 
		else if ((Atester.x == Entree.F5.x) && (Atester.y == Entree.F5.y)){ 
		 LancerMissile(Entree, Entree.F5);} 
		 
		else if ((Atester.x == Entree.F6.x) && (Atester.y == Entree.F6.y)){ 
		 LancerMissile(Entree, Entree.F6);} 
		 
		else if ((Atester.x == Entree.F7.x) && (Atester.y == Entree.F7.y)){ 
		 LancerMissile(Entree, Entree.F7);} 
		 
		else if ((Atester.x == Entree.F8.x) && (Atester.y == Entree.F8.y)){ 
		 LancerMissile(Entree, Entree.F8);} 
		 
		else if ((Atester.x == Entree.F9.x) && (Atester.y == Entree.F9.y)){ 
		 LancerMissile(Entree, Entree.F9);} 
		 
		else if ((Atester.x == Entree.F10.x) && (Atester.y == Entree.F10.y)){ 
		 LancerMissile(Entree, Entree.F10);} 
		 
		else if ((Atester.x == Entree.G1.x) && (Atester.y == Entree.G1.y)){ 
		 LancerMissile(Entree, Entree.G1);} 
		 
		else if ((Atester.x == Entree.G2.x) && (Atester.y == Entree.G2.y)){ 
		 LancerMissile(Entree, Entree.G2);} 
		 
		else if ((Atester.x == Entree.G3.x) && (Atester.y == Entree.G3.y)){ 
		 LancerMissile(Entree, Entree.G3);} 
		 
		else if ((Atester.x == Entree.G4.x) && (Atester.y == Entree.G4.y)){ 
		 LancerMissile(Entree, Entree.G4);} 
		 
		else if ((Atester.x == Entree.G5.x) && (Atester.y == Entree.G5.y)){ 
		 LancerMissile(Entree, Entree.G5);} 
		 
		else if ((Atester.x == Entree.G6.x) && (Atester.y == Entree.G6.y)){ 
		 LancerMissile(Entree, Entree.G6);} 
		 
		else if ((Atester.x == Entree.G7.x) && (Atester.y == Entree.G7.y)){ 
		 LancerMissile(Entree, Entree.G7);} 
		 
		else if ((Atester.x == Entree.G8.x) && (Atester.y == Entree.G8.y)){ 
		 LancerMissile(Entree, Entree.G8);} 
		 
		else if ((Atester.x == Entree.G9.x) && (Atester.y == Entree.G9.y)){ 
		 LancerMissile(Entree, Entree.G9);} 
		 
		else if ((Atester.x == Entree.G10.x) && (Atester.y == Entree.G10.y)){ 
		 LancerMissile(Entree, Entree.G10);} 
		 
		else if ((Atester.x == Entree.H1.x) && (Atester.y == Entree.H1.y)){ 
		 LancerMissile(Entree, Entree.H1);} 
		 
		else if ((Atester.x == Entree.H2.x) && (Atester.y == Entree.H2.y)){ 
		 LancerMissile(Entree, Entree.H2);} 
		 
		else if ((Atester.x == Entree.H3.x) && (Atester.y == Entree.H3.y)){ 
		 LancerMissile(Entree, Entree.H3);} 
		 
		else if ((Atester.x == Entree.H4.x) && (Atester.y == Entree.H4.y)){ 
		 LancerMissile(Entree, Entree.H4);} 
		 
		else if ((Atester.x == Entree.H5.x) && (Atester.y == Entree.H5.y)){ 
		 LancerMissile(Entree, Entree.H5);} 
		 
		else if ((Atester.x == Entree.H6.x) && (Atester.y == Entree.H6.y)){ 
		 LancerMissile(Entree, Entree.H6);} 
		 
		else if ((Atester.x == Entree.H7.x) && (Atester.y == Entree.H7.y)){ 
		 LancerMissile(Entree, Entree.H7);} 
		 
		else if ((Atester.x == Entree.H8.x) && (Atester.y == Entree.H8.y)){ 
		 LancerMissile(Entree, Entree.H8);} 
		 
		else if ((Atester.x == Entree.H9.x) && (Atester.y == Entree.H9.y)){ 
		 LancerMissile(Entree, Entree.H9);} 
		 
		else if ((Atester.x == Entree.H10.x) && (Atester.y == Entree.H10.y)){ 
		 LancerMissile(Entree, Entree.H10);} 
		 
		else if ((Atester.x == Entree.I1.x) && (Atester.y == Entree.I1.y)){ 
		 LancerMissile(Entree, Entree.I1);} 
		 
		else if ((Atester.x == Entree.I2.x) && (Atester.y == Entree.I2.y)){ 
		 LancerMissile(Entree, Entree.I2);} 
		 
		else if ((Atester.x == Entree.I3.x) && (Atester.y == Entree.I3.y)){ 
		 LancerMissile(Entree, Entree.I3);} 
		 
		else if ((Atester.x == Entree.I4.x) && (Atester.y == Entree.I4.y)){ 
		 LancerMissile(Entree, Entree.I4);} 
		 
		else if ((Atester.x == Entree.I5.x) && (Atester.y == Entree.I5.y)){ 
		 LancerMissile(Entree, Entree.I5);} 
		 
		else if ((Atester.x == Entree.I6.x) && (Atester.y == Entree.I6.y)){ 
		 LancerMissile(Entree, Entree.I6);} 
		 
		else if ((Atester.x == Entree.I7.x) && (Atester.y == Entree.I7.y)){ 
		 LancerMissile(Entree, Entree.I7);} 
		 
		else if ((Atester.x == Entree.I8.x) && (Atester.y == Entree.I8.y)){ 
		 LancerMissile(Entree, Entree.I8);} 
		 
		else if ((Atester.x == Entree.I9.x) && (Atester.y == Entree.I9.y)){ 
		 LancerMissile(Entree, Entree.I9);} 
		 
		else if ((Atester.x == Entree.I10.x) && (Atester.y == Entree.I10.y)){ 
		 LancerMissile(Entree, Entree.I10);} 
		 
		else if ((Atester.x == Entree.J1.x) && (Atester.y == Entree.J1.y)){ 
		 LancerMissile(Entree, Entree.J1);} 
		 
		else if ((Atester.x == Entree.J2.x) && (Atester.y == Entree.J2.y)){ 
		 LancerMissile(Entree, Entree.J2);} 
		 
		else if ((Atester.x == Entree.J3.x) && (Atester.y == Entree.J3.y)){ 
		 LancerMissile(Entree, Entree.J3);} 
		 
		else if ((Atester.x == Entree.J4.x) && (Atester.y == Entree.J4.y)){ 
		 LancerMissile(Entree, Entree.J4);} 
		 
		else if ((Atester.x == Entree.J5.x) && (Atester.y == Entree.J5.y)){ 
		 LancerMissile(Entree, Entree.J5);} 
		 
		else if ((Atester.x == Entree.J6.x) && (Atester.y == Entree.J6.y)){ 
		 LancerMissile(Entree, Entree.J6);} 
		 
		else if ((Atester.x == Entree.J7.x) && (Atester.y == Entree.J7.y)){ 
		 LancerMissile(Entree, Entree.J7);} 
		 
		else if ((Atester.x == Entree.J8.x) && (Atester.y == Entree.J8.y)){ 
		 LancerMissile(Entree, Entree.J8);} 
		 
		else if ((Atester.x == Entree.J9.x) && (Atester.y == Entree.J9.y)){ 
		 LancerMissile(Entree, Entree.J9);} 
		 
		else if ((Atester.x == Entree.J10.x) && (Atester.y == Entree.J10.y)){ 
		 LancerMissile(Entree, Entree.J10);} 
		 		
		return Entree;}	
	//Declaration de la structure Coo qui se chargera de contenir les informations contenues sur la grille
		static class Coo{
		int y;
		char x;
		boolean boat;
		boolean aimed;
		boolean destroyed;
		boolean aLeau;
		String typeBateau;
	}
	//Declaration de la structure Case qui contient tout les informations contenues dans les cases	
		static class Case{
		int torpilleur = 2;
		int sous_marin1= 3;
		int sous_marin2 = 3;
		int croiseur = 4;
		int porte_avion = 5;
		int porte_avion2 = 3;
		int porte_avion3 = 3;
		Coo A1; 
		Coo A2; 
		Coo A3; 
		Coo A4; 
		Coo A5; 
		Coo A6; 
		Coo A7; 
		Coo A8; 
		Coo A9; 
		Coo A10; 
		Coo B1; 
		Coo B2; 
		Coo B3; 
		Coo B4; 
		Coo B5; 
		Coo B6; 
		Coo B7; 
		Coo B8; 
		Coo B9; 
		Coo B10; 
		Coo C1; 
		Coo C2; 
		Coo C3; 
		Coo C4; 
		Coo C5; 
		Coo C6; 
		Coo C7; 
		Coo C8; 
		Coo C9; 
		Coo C10; 
		Coo D1; 
		Coo D2; 
		Coo D3; 
		Coo D4; 
		Coo D5; 
		Coo D6; 
		Coo D7; 
		Coo D8; 
		Coo D9; 
		Coo D10; 
		Coo E1; 
		Coo E2; 
		Coo E3; 
		Coo E4; 
		Coo E5; 
		Coo E6; 
		Coo E7; 
		Coo E8; 
		Coo E9; 
		Coo E10; 
		Coo F1; 
		Coo F2; 
		Coo F3; 
		Coo F4; 
		Coo F5; 
		Coo F6; 
		Coo F7; 
		Coo F8; 
		Coo F9; 
		Coo F10; 
		Coo G1; 
		Coo G2; 
		Coo G3; 
		Coo G4; 
		Coo G5; 
		Coo G6; 
		Coo G7; 
		Coo G8; 
		Coo G9; 
		Coo G10; 
		Coo H1; 
		Coo H2; 
		Coo H3; 
		Coo H4; 
		Coo H5; 
		Coo H6; 
		Coo H7; 
		Coo H8; 
		Coo H9; 
		Coo H10; 
		Coo I1; 
		Coo I2; 
		Coo I3; 
		Coo I4; 
		Coo I5; 
		Coo I6; 
		Coo I7; 
		Coo I8; 
		Coo I9; 
		Coo I10; 
		Coo J1; 
		Coo J2; 
		Coo J3; 
		Coo J4; 
		Coo J5; 
		Coo J6; 
		Coo J7; 
		Coo J8; 
		Coo J9; 
		Coo J10; 


	}
	//Sert a affecter les valeurs a une nouvelle grille de jeu
	static Case DeclarationDeLaGrille(){

	Coo A1= new Coo(); 
	A1.y = 1; 
	A1.x = 'A'; 
	A1.boat = false; 
	A1.aimed = false; 
	 
	Coo A2= new Coo(); 
	A2.y = 2; 
	A2.x = 'A'; 
	A2.boat = false; 
	A2.aimed = false; 
	 
	Coo A3= new Coo(); 
	A3.y = 3; 
	A3.x = 'A'; 
	A3.boat = false; 
	A3.aimed = false; 
	 
	Coo A4= new Coo(); 
	A4.y = 4; 
	A4.x = 'A'; 
	A4.boat = false; 
	A4.aimed = false; 
	 
	Coo A5= new Coo(); 
	A5.y = 5; 
	A5.x = 'A'; 
	A5.boat = false; 
	A5.aimed = false; 
	 
	Coo A6= new Coo(); 
	A6.y = 6; 
	A6.x = 'A'; 
	A6.boat = false; 
	A6.aimed = false; 
	 
	Coo A7= new Coo(); 
	A7.y = 7; 
	A7.x = 'A'; 
	A7.boat = false; 
	A7.aimed = false; 
	 
	Coo A8= new Coo(); 
	A8.y = 8; 
	A8.x = 'A'; 
	A8.boat = false; 
	A8.aimed = false; 
	 
	Coo A9= new Coo(); 
	A9.y = 9; 
	A9.x = 'A'; 
	A9.boat = false; 
	A9.aimed = false; 
	 
	Coo A10= new Coo(); 
	A10.y = 10; 
	A10.x = 'A'; 
	A10.boat = false; 
	A10.aimed = false; 
	 
	Coo B1= new Coo(); 
	B1.y = 1; 
	B1.x = 'B'; 
	B1.boat = false; 
	B1.aimed = false; 
	 
	Coo B2= new Coo(); 
	B2.y = 2; 
	B2.x = 'B'; 
	B2.boat = false; 
	B2.aimed = false; 
	 
	Coo B3= new Coo(); 
	B3.y = 3; 
	B3.x = 'B'; 
	B3.boat = false; 
	B3.aimed = false; 
	 
	Coo B4= new Coo(); 
	B4.y = 4; 
	B4.x = 'B'; 
	B4.boat = false; 
	B4.aimed = false; 
	 
	Coo B5= new Coo(); 
	B5.y = 5; 
	B5.x = 'B'; 
	B5.boat = false; 
	B5.aimed = false; 
	 
	Coo B6= new Coo(); 
	B6.y = 6; 
	B6.x = 'B'; 
	B6.boat = false; 
	B6.aimed = false; 
	 
	Coo B7= new Coo(); 
	B7.y = 7; 
	B7.x = 'B'; 
	B7.boat = false; 
	B7.aimed = false; 
	 
	Coo B8= new Coo(); 
	B8.y = 8; 
	B8.x = 'B'; 
	B8.boat = false; 
	B8.aimed = false; 
	 
	Coo B9= new Coo(); 
	B9.y = 9; 
	B9.x = 'B'; 
	B9.boat = false; 
	B9.aimed = false; 
	 
	Coo B10= new Coo(); 
	B10.y = 10; 
	B10.x = 'B'; 
	B10.boat = false; 
	B10.aimed = false; 
	 
	Coo C1= new Coo(); 
	C1.y = 1; 
	C1.x = 'C'; 
	C1.boat = false; 
	C1.aimed = false; 
	 
	Coo C2= new Coo(); 
	C2.y = 2; 
	C2.x = 'C'; 
	C2.boat = false; 
	C2.aimed = false; 
	 
	Coo C3= new Coo(); 
	C3.y = 3; 
	C3.x = 'C'; 
	C3.boat = false; 
	C3.aimed = false; 
	 
	Coo C4= new Coo(); 
	C4.y = 4; 
	C4.x = 'C'; 
	C4.boat = false; 
	C4.aimed = false; 
	 
	Coo C5= new Coo(); 
	C5.y = 5; 
	C5.x = 'C'; 
	C5.boat = false; 
	C5.aimed = false; 
	 
	Coo C6= new Coo(); 
	C6.y = 6; 
	C6.x = 'C'; 
	C6.boat = false; 
	C6.aimed = false; 
	 
	Coo C7= new Coo(); 
	C7.y = 7; 
	C7.x = 'C'; 
	C7.boat = false; 
	C7.aimed = false; 
	 
	Coo C8= new Coo(); 
	C8.y = 8; 
	C8.x = 'C'; 
	C8.boat = false; 
	C8.aimed = false; 
	 
	Coo C9= new Coo(); 
	C9.y = 9; 
	C9.x = 'C'; 
	C9.boat = false; 
	C9.aimed = false; 
	 
	Coo C10= new Coo(); 
	C10.y = 10; 
	C10.x = 'C'; 
	C10.boat = false; 
	C10.aimed = false; 
	 
	Coo D1= new Coo(); 
	D1.y = 1; 
	D1.x = 'D'; 
	D1.boat = false; 
	D1.aimed = false; 
	 
	Coo D2= new Coo(); 
	D2.y = 2; 
	D2.x = 'D'; 
	D2.boat = false; 
	D2.aimed = false; 
	 
	Coo D3= new Coo(); 
	D3.y = 3; 
	D3.x = 'D'; 
	D3.boat = false; 
	D3.aimed = false; 
	 
	Coo D4= new Coo(); 
	D4.y = 4; 
	D4.x = 'D'; 
	D4.boat = false; 
	D4.aimed = false; 
	 
	Coo D5= new Coo(); 
	D5.y = 5; 
	D5.x = 'D'; 
	D5.boat = false; 
	D5.aimed = false; 
	 
	Coo D6= new Coo(); 
	D6.y = 6; 
	D6.x = 'D'; 
	D6.boat = false; 
	D6.aimed = false; 
	 
	Coo D7= new Coo(); 
	D7.y = 7; 
	D7.x = 'D'; 
	D7.boat = false; 
	D7.aimed = false; 
	 
	Coo D8= new Coo(); 
	D8.y = 8; 
	D8.x = 'D'; 
	D8.boat = false; 
	D8.aimed = false; 
	 
	Coo D9= new Coo(); 
	D9.y = 9; 
	D9.x = 'D'; 
	D9.boat = false; 
	D9.aimed = false; 
	 
	Coo D10= new Coo(); 
	D10.y = 10; 
	D10.x = 'D'; 
	D10.boat = false; 
	D10.aimed = false; 
	 
	Coo E1= new Coo(); 
	E1.y = 1; 
	E1.x = 'E'; 
	E1.boat = false; 
	E1.aimed = false; 
	 
	Coo E2= new Coo(); 
	E2.y = 2; 
	E2.x = 'E'; 
	E2.boat = false; 
	E2.aimed = false; 
	 
	Coo E3= new Coo(); 
	E3.y = 3; 
	E3.x = 'E'; 
	E3.boat = false; 
	E3.aimed = false; 
	 
	Coo E4= new Coo(); 
	E4.y = 4; 
	E4.x = 'E'; 
	E4.boat = false; 
	E4.aimed = false; 
	 
	Coo E5= new Coo(); 
	E5.y = 5; 
	E5.x = 'E'; 
	E5.boat = false; 
	E5.aimed = false; 
	 
	Coo E6= new Coo(); 
	E6.y = 6; 
	E6.x = 'E'; 
	E6.boat = false; 
	E6.aimed = false; 
	 
	Coo E7= new Coo(); 
	E7.y = 7; 
	E7.x = 'E'; 
	E7.boat = false; 
	E7.aimed = false; 
	 
	Coo E8= new Coo(); 
	E8.y = 8; 
	E8.x = 'E'; 
	E8.boat = false; 
	E8.aimed = false; 
	 
	Coo E9= new Coo(); 
	E9.y = 9; 
	E9.x = 'E'; 
	E9.boat = false; 
	E9.aimed = false; 
	 
	Coo E10= new Coo(); 
	E10.y = 10; 
	E10.x = 'E'; 
	E10.boat = false; 
	E10.aimed = false; 
	 
	Coo F1= new Coo(); 
	F1.y = 1; 
	F1.x = 'F'; 
	F1.boat = false; 
	F1.aimed = false; 
	 
	Coo F2= new Coo(); 
	F2.y = 2; 
	F2.x = 'F'; 
	F2.boat = false; 
	F2.aimed = false; 
	 
	Coo F3= new Coo(); 
	F3.y = 3; 
	F3.x = 'F'; 
	F3.boat = false; 
	F3.aimed = false; 
	 
	Coo F4= new Coo(); 
	F4.y = 4; 
	F4.x = 'F'; 
	F4.boat = false; 
	F4.aimed = false; 
	 
	Coo F5= new Coo(); 
	F5.y = 5; 
	F5.x = 'F'; 
	F5.boat = false; 
	F5.aimed = false; 
	 
	Coo F6= new Coo(); 
	F6.y = 6; 
	F6.x = 'F'; 
	F6.boat = false; 
	F6.aimed = false; 
	 
	Coo F7= new Coo(); 
	F7.y = 7; 
	F7.x = 'F'; 
	F7.boat = false; 
	F7.aimed = false; 
	 
	Coo F8= new Coo(); 
	F8.y = 8; 
	F8.x = 'F'; 
	F8.boat = false; 
	F8.aimed = false; 
	 
	Coo F9= new Coo(); 
	F9.y = 9; 
	F9.x = 'F'; 
	F9.boat = false; 
	F9.aimed = false; 
	 
	Coo F10= new Coo(); 
	F10.y = 10; 
	F10.x = 'F'; 
	F10.boat = false; 
	F10.aimed = false; 
	 
	Coo G1= new Coo(); 
	G1.y = 1; 
	G1.x = 'G'; 
	G1.boat = false; 
	G1.aimed = false; 
	 
	Coo G2= new Coo(); 
	G2.y = 2; 
	G2.x = 'G'; 
	G2.boat = false; 
	G2.aimed = false; 
	 
	Coo G3= new Coo(); 
	G3.y = 3; 
	G3.x = 'G'; 
	G3.boat = false; 
	G3.aimed = false; 
	 
	Coo G4= new Coo(); 
	G4.y = 4; 
	G4.x = 'G'; 
	G4.boat = false; 
	G4.aimed = false; 
	 
	Coo G5= new Coo(); 
	G5.y = 5; 
	G5.x = 'G'; 
	G5.boat = false; 
	G5.aimed = false; 
	 
	Coo G6= new Coo(); 
	G6.y = 6; 
	G6.x = 'G'; 
	G6.boat = false; 
	G6.aimed = false; 
	 
	Coo G7= new Coo(); 
	G7.y = 7; 
	G7.x = 'G'; 
	G7.boat = false; 
	G7.aimed = false; 
	 
	Coo G8= new Coo(); 
	G8.y = 8; 
	G8.x = 'G'; 
	G8.boat = false; 
	G8.aimed = false; 
	 
	Coo G9= new Coo(); 
	G9.y = 9; 
	G9.x = 'G'; 
	G9.boat = false; 
	G9.aimed = false; 
	 
	Coo G10= new Coo(); 
	G10.y = 10; 
	G10.x = 'G'; 
	G10.boat = false; 
	G10.aimed = false; 
	 
	Coo H1= new Coo(); 
	H1.y = 1; 
	H1.x = 'H'; 
	H1.boat = false; 
	H1.aimed = false; 
	 
	Coo H2= new Coo(); 
	H2.y = 2; 
	H2.x = 'H'; 
	H2.boat = false; 
	H2.aimed = false; 
	 
	Coo H3= new Coo(); 
	H3.y = 3; 
	H3.x = 'H'; 
	H3.boat = false; 
	H3.aimed = false; 
	 
	Coo H4= new Coo(); 
	H4.y = 4; 
	H4.x = 'H'; 
	H4.boat = false; 
	H4.aimed = false; 
	 
	Coo H5= new Coo(); 
	H5.y = 5; 
	H5.x = 'H'; 
	H5.boat = false; 
	H5.aimed = false; 
	 
	Coo H6= new Coo(); 
	H6.y = 6; 
	H6.x = 'H'; 
	H6.boat = false; 
	H6.aimed = false; 
	 
	Coo H7= new Coo(); 
	H7.y = 7; 
	H7.x = 'H'; 
	H7.boat = false; 
	H7.aimed = false; 
	 
	Coo H8= new Coo(); 
	H8.y = 8; 
	H8.x = 'H'; 
	H8.boat = false; 
	H8.aimed = false; 
	 
	Coo H9= new Coo(); 
	H9.y = 9; 
	H9.x = 'H'; 
	H9.boat = false; 
	H9.aimed = false; 
	 
	Coo H10= new Coo(); 
	H10.y = 10; 
	H10.x = 'H'; 
	H10.boat = false; 
	H10.aimed = false; 
	 
	Coo I1= new Coo(); 
	I1.y = 1; 
	I1.x = 'I'; 
	I1.boat = false; 
	I1.aimed = false; 
	 
	Coo I2= new Coo(); 
	I2.y = 2; 
	I2.x = 'I'; 
	I2.boat = false; 
	I2.aimed = false; 
	 
	Coo I3= new Coo(); 
	I3.y = 3; 
	I3.x = 'I'; 
	I3.boat = false; 
	I3.aimed = false; 
	 
	Coo I4= new Coo(); 
	I4.y = 4; 
	I4.x = 'I'; 
	I4.boat = false; 
	I4.aimed = false; 
	 
	Coo I5= new Coo(); 
	I5.y = 5; 
	I5.x = 'I'; 
	I5.boat = false; 
	I5.aimed = false; 
	 
	Coo I6= new Coo(); 
	I6.y = 6; 
	I6.x = 'I'; 
	I6.boat = false; 
	I6.aimed = false; 
	 
	Coo I7= new Coo(); 
	I7.y = 7; 
	I7.x = 'I'; 
	I7.boat = false; 
	I7.aimed = false; 
	 
	Coo I8= new Coo(); 
	I8.y = 8; 
	I8.x = 'I'; 
	I8.boat = false; 
	I8.aimed = false; 
	 
	Coo I9= new Coo(); 
	I9.y = 9; 
	I9.x = 'I'; 
	I9.boat = false; 
	I9.aimed = false; 
	 
	Coo I10= new Coo(); 
	I10.y = 10; 
	I10.x = 'I'; 
	I10.boat = false; 
	I10.aimed = false; 
	 
	Coo J1= new Coo(); 
	J1.y = 1; 
	J1.x = 'J'; 
	J1.boat = false; 
	J1.aimed = false; 
	 
	Coo J2= new Coo(); 
	J2.y = 2; 
	J2.x = 'J'; 
	J2.boat = false; 
	J2.aimed = false; 
	 
	Coo J3= new Coo(); 
	J3.y = 3; 
	J3.x = 'J'; 
	J3.boat = false; 
	J3.aimed = false; 
	 
	Coo J4= new Coo(); 
	J4.y = 4; 
	J4.x = 'J'; 
	J4.boat = false; 
	J4.aimed = false; 
	 
	Coo J5= new Coo(); 
	J5.y = 5; 
	J5.x = 'J'; 
	J5.boat = false; 
	J5.aimed = false; 
	 
	Coo J6= new Coo(); 
	J6.y = 6; 
	J6.x = 'J'; 
	J6.boat = false; 
	J6.aimed = false; 
	 
	Coo J7= new Coo(); 
	J7.y = 7; 
	J7.x = 'J'; 
	J7.boat = false; 
	J7.aimed = false; 
	 
	Coo J8= new Coo(); 
	J8.y = 8; 
	J8.x = 'J'; 
	J8.boat = false; 
	J8.aimed = false; 
	 
	Coo J9= new Coo(); 
	J9.y = 9; 
	J9.x = 'J'; 
	J9.boat = false; 
	J9.aimed = false; 
	 
	Coo J10= new Coo(); 
	J10.y = 10; 
	J10.x = 'J'; 
	J10.boat = false; 
	J10.aimed = false; 
	Case NewCase = new Case();

	NewCase.A1 = A1; 
	NewCase.A2 = A2; 
	NewCase.A3 = A3; 
	NewCase.A4 = A4; 
	NewCase.A5 = A5; 
	NewCase.A6 = A6; 
	NewCase.A7 = A7; 
	NewCase.A8 = A8; 
	NewCase.A9 = A9; 
	NewCase.A10 = A10; 
	NewCase.B1 = B1; 
	NewCase.B2 = B2; 
	NewCase.B3 = B3; 
	NewCase.B4 = B4; 
	NewCase.B5 = B5; 
	NewCase.B6 = B6; 
	NewCase.B7 = B7; 
	NewCase.B8 = B8; 
	NewCase.B9 = B9; 
	NewCase.B10 = B10; 
	NewCase.C1 = C1; 
	NewCase.C2 = C2; 
	NewCase.C3 = C3; 
	NewCase.C4 = C4; 
	NewCase.C5 = C5; 
	NewCase.C6 = C6; 
	NewCase.C7 = C7; 
	NewCase.C8 = C8; 
	NewCase.C9 = C9; 
	NewCase.C10 = C10; 
	NewCase.D1 = D1; 
	NewCase.D2 = D2; 
	NewCase.D3 = D3; 
	NewCase.D4 = D4; 
	NewCase.D5 = D5; 
	NewCase.D6 = D6; 
	NewCase.D7 = D7; 
	NewCase.D8 = D8; 
	NewCase.D9 = D9; 
	NewCase.D10 = D10; 
	NewCase.E1 = E1; 
	NewCase.E2 = E2; 
	NewCase.E3 = E3; 
	NewCase.E4 = E4; 
	NewCase.E5 = E5; 
	NewCase.E6 = E6; 
	NewCase.E7 = E7; 
	NewCase.E8 = E8; 
	NewCase.E9 = E9; 
	NewCase.E10 = E10; 
	NewCase.F1 = F1; 
	NewCase.F2 = F2; 
	NewCase.F3 = F3; 
	NewCase.F4 = F4; 
	NewCase.F5 = F5; 
	NewCase.F6 = F6; 
	NewCase.F7 = F7; 
	NewCase.F8 = F8; 
	NewCase.F9 = F9; 
	NewCase.F10 = F10; 
	NewCase.G1 = G1; 
	NewCase.G2 = G2; 
	NewCase.G3 = G3; 
	NewCase.G4 = G4; 
	NewCase.G5 = G5; 
	NewCase.G6 = G6; 
	NewCase.G7 = G7; 
	NewCase.G8 = G8; 
	NewCase.G9 = G9; 
	NewCase.G10 = G10; 
	NewCase.H1 = H1; 
	NewCase.H2 = H2; 
	NewCase.H3 = H3; 
	NewCase.H4 = H4; 
	NewCase.H5 = H5; 
	NewCase.H6 = H6; 
	NewCase.H7 = H7; 
	NewCase.H8 = H8; 
	NewCase.H9 = H9; 
	NewCase.H10 = H10; 
	NewCase.I1 = I1; 
	NewCase.I2 = I2; 
	NewCase.I3 = I3; 
	NewCase.I4 = I4; 
	NewCase.I5 = I5; 
	NewCase.I6 = I6; 
	NewCase.I7 = I7; 
	NewCase.I8 = I8; 
	NewCase.I9 = I9; 
	NewCase.I10 = I10; 
	NewCase.J1 = J1; 
	NewCase.J2 = J2; 
	NewCase.J3 = J3; 
	NewCase.J4 = J4; 
	NewCase.J5 = J5; 
	NewCase.J6 = J6; 
	NewCase.J7 = J7; 
	NewCase.J8 = J8; 
	NewCase.J9 = J9; 
	NewCase.J10 = J10; 

	return NewCase;	
}
	/**
	 * Sert a verifier la saisie effectuee par l'utilisateur	
	 * @param un int, un char
	 * @param un char
	 * @return vrai si les deux valeurs entrees sont dans l'intervalle de la grille du jeu, faux sinon
	 */
	static boolean VerifSaisie(int entier, char caractere){
		
		boolean LaSaisie;
		int transtypage;
		final int BorneInfInt = 1 ;
		final int BorneInfChar = 65 ;
		final int BorneSupInt = 10 ;
		final int BorneSupChar = 74 ;

		LaSaisie = true;
		transtypage = (int) caractere;

		if (((entier < BorneInfInt) || (entier > BorneSupInt)) || ((transtypage < BorneInfChar) || (transtypage > BorneSupChar))) {
			Ecran.afficher("Transypage a la valeur : ", transtypage , "\n");
			LaSaisie = false;
			Ecran.afficher("Pas bon");
		}

		return LaSaisie;
	
	}	
	/**
	* La classe case ou sont stockees toutes les coordonnes
 	* Une grille de jeu de type Case ou est place le torpilleur
	*/
	static Case PlacerTorpilleur(Case LaCase){
		
		//Declaration des variables	
		boolean adjacent;	
		String bateau = "torpilleur";
		Ecran.afficher("\nPlacement du torpilleur ! \n");
		Coo Entree1 = new Coo();
		Coo Entree2 = new Coo();
		char case1x;
		int case1y;
		char case2x;
		int case2y;
		adjacent = false; 
		do{
			
			case1x = 'M';
			case1y = 11;
			case2x = 'N';
			case2y = 12;

		//On demande la saisie de l'utilisateur
		
		int entierSaisi;
		char charSaisie;
		int compteur;
		int transtypage;
		compteur = 1;
			
		//do {
			
			Coo CooSaisie = new Coo();
			
			Ecran.afficher("Veuillez entre un caractere : ");
			case1x = Clavier.saisirChar();
						
			while(((int)case1x< 65) || ((int)case1x > 74)){
				Ecran.afficher("Erreur de saisie, veuillez entrer un char correct");
				case1x = Clavier.saisirChar();
				}
				
			Ecran.afficher("Veuillez entre un entier : ");
			case1y = Clavier.saisirInt();
			
			while((case1y < 1) || (case1y > 10)){
				Ecran.afficher("Erreur de saisie, veuillez entrer un entier correct");
				case1y = Clavier.saisirInt();
				compteur ++;
				}
			
			Entree1.x = case1x;
			Entree1.y = case1y;	
		//} while((BateauOuPas(LaCase, Entree1) == false));
		//On demande une deuxieme saisie pour la seconde case
			
		Ecran.afficher("Veuillez entre un caractere : ");
		case2x = Clavier.saisirChar();
				
		while(((int)case2x< 65) || ((int)case2x > 74)){
			Ecran.afficher("Erreur de saisie, veuillez entrer un char correct \n");
			case2x = Clavier.saisirChar();
			}
			
		Ecran.afficher("Veuillez entre un entier : ");
		case2y = Clavier.saisirInt();
		
		
		while((case2y < 1) || (case2y > 10)){
			Ecran.afficher("Erreur de saisie, veuillez entrer un entier correct \n");
			case1y = Clavier.saisirInt();
			}

		Entree2.x = case2x;
		Entree2.y = case2y;
		//On regarde si cette case est adjacente a la premiere
		
		if (VerfAdj(Entree1, Entree2) == true){
			adjacent = true;
		}
		else{Ecran.afficher(" \n Erreur, les deux points entrees ne sont pas adjacent ! \n Veuillez ressaisir des coordonnes valides \n");}
	} while (adjacent == false);

		LaCase = Correspondance(LaCase, Entree1, bateau);
		LaCase = Correspondance(LaCase, Entree2, bateau);
		Ecran.afficher(" \n Le torpilleur a ete place en ", Entree1.x, Entree1.y, " ", Entree2.x, Entree2.y, "\n");
		AffichageGrille(LaCase);
	
		//On retourne la case qui a maintenant le torpilleur
	
		return LaCase;
	}
	/**	
	* @param La case de jeu ou l'on souhaite placer le bateau
	* @return Le type Case qui contient la case de jeu entree avec le bateau maintenant place
	*/
	static Case PlacerCroiseur(Case LaCase){	
		//Declaration des variables
		
		Coo Entree1 = new Coo();
		Coo Entree2 = new Coo();
		Coo Entree3 = new Coo();
		Coo Entree4 = new Coo();
		String bateau;
		
		Ecran.afficher("\n Placement du Croiseur \n");
		bateau = "croiseur";
		
		char case1x;
		int case1y;
		char case2x;
		int case2y;
		char case3x;
		int case3y;
		char case4x;
		int case4y;
		boolean adjacent;
		int compteur;
		boolean BateauEn1;
		boolean BateauEn2;
		boolean BateauEn3;
		boolean BateauEn4;
		boolean full_adjacent;
		BateauEn1 = false; 
		BateauEn2 = false; 
		BateauEn3 = false; 
		BateauEn4 = false; 

		adjacent = false; 		
		do{
		do{
			compteur = 0;
			full_adjacent = true;

			do{
				if (compteur > 0){
					Ecran.afficher("soit, il y a peut etre deja un bateau place sur cette case, veuillez resaisir des coordonees valides ! \n");
				}
					
				Ecran.afficher("\nPlacement de la premiere case\n Veuillez entre un caractere : ");
				case1x = Clavier.saisirChar();
					
				while(((int)case1x< 65) || ((int)case1x > 74)){
					Ecran.afficher("\nErreur de saisie, veuillez entrer un char correct");
					case1x = Clavier.saisirChar();
					}
					
				Ecran.afficher("\nVeuillez entre un entier : ");
				case1y = Clavier.saisirInt();
			
				while((case1y < 1) || (case1y > 10)){
					Ecran.afficher("\nErreur de saisie, veuillez entrer un entier correct");
					case1y = Clavier.saisirInt();
					}
				
				Entree1.x = case1x;
				Entree1.y = case1y;
				//On verifie qu'il n'y ait pas de bateau deja place a la case saisie
				BateauEn1 = BateauOuPas(LaCase, Entree1);
			}while(BateauEn1 == true);
		
		Ecran.afficher("\nPremiere case placee en ", Entree1.x,Entree1.y ,"\n Placement de la seconde case \n");

		compteur = 0;
		do{
			if (compteur > 0){
                Ecran.afficher("soit, il y a peut etre deja un bateau place sur cette case, veuillez resaisir des coordonees valides ! \n");
            }
			//On demande une deuxieme saisie pour la seconde case
			
			Ecran.afficher("Placement de la 2eme case\nVeuillez entre un caractere : ");
			case2x = Clavier.saisirChar();
				
			while(((int)case2x< 65) || ((int)case2x > 74)){
				Ecran.afficher("\nErreur de saisie, veuillez entrer un char correct");
				case2x = Clavier.saisirChar();
				}
				
			Ecran.afficher("\nVeuillez entre un entier : ");
			case2y = Clavier.saisirInt();
			
			while((case2y < 1) || (case2y > 10)){
				Ecran.afficher("\nErreur de saisie, veuillez entrer un entier correct");
				case1y = Clavier.saisirInt();
				}
			
			Entree2.x = case2x;
			Entree2.y = case2y;
			BateauEn2 = BateauOuPas(LaCase, Entree2);
			compteur ++;
		}while(BateauEn2 == true);
		
		//On regarde si cette case est adjacente a la premiere
		
		if (VerfAdj(Entree1, Entree2) == true){
			adjacent = true;
		}
		else{Ecran.afficher("\n Erreur, les deux points entrees ne sont pas adjacent !");}
	} while (adjacent == false);
	Ecran.afficher("\n Seconde case placee en ", Entree2.x,Entree2.y ,"\n Placement de la troisieme case \n");
	compteur = 0;
		adjacent = false;
		do {
			do{
				//On demande la saisie de la troisieme case 
				if (compteur > 0){
					Ecran.afficher("soit, il y a peut etre deja un bateau place sur cette case, veuillez resaisir des coordonees valides ! \n");
				}
				
				Ecran.afficher("\nPlacement de la 3eme case\nVeuillez entre un caractere : ");
				case3x = Clavier.saisirChar();
					
				while(((int)case3x< 65) || ((int)case3x > 74)){
					Ecran.afficher("\nErreur de saisie, veuillez entrer un char correct");
					case3x = Clavier.saisirChar();
					}
					
				Ecran.afficher("\nVeuillez entre un entier : ");
				case3y = Clavier.saisirInt();
							
				while((case3y < 1) || (case3y > 10)){
					Ecran.afficher("\nErreur de saisie, veuillez entrer un entier correct");
					case3y = Clavier.saisirInt();
					}
				
				Entree3.x = case3x;
				Entree3.y = case3y;
				BateauEn3 = BateauOuPas(LaCase, Entree3);
				compteur ++;

			}while(BateauEn3 == true);
	
			//On verifie quelle est bien adjacente a le deuxieme case saisie
	
			if (VerfAdj(Entree2, Entree3) == true){
				adjacent = true;
			}
			else{Ecran.afficher("\nErreur, les deux points entrees ne sont pas adjacent !");}
	} while (adjacent == false);
	Ecran.afficher("\nTroisieme case placee en ", Entree3.x,Entree3.y ,"\n Placement de la derniere case \n");
	compteur = 0;

	adjacent = false;
		do {
			do{
				if (compteur > 0){
					Ecran.afficher("soit, il y a peut etre deja un bateau place sur cette case, veuillez resaisir des coordonees valides ! \n");
				}
				//On demande la saisie de la troisieme case 
				
				Ecran.afficher("\nPlacement de la 4eme case \nVeuillez entre un caractere : ");
				case4x = Clavier.saisirChar();
					
				while(((int)case4x< 65) || ((int)case4x > 74)){
					Ecran.afficher(" \nErreur de saisie, veuillez entrer un char correct");
					case4x = Clavier.saisirChar();
					}
					
				Ecran.afficher(" \nVeuillez entre un entier : ");
				case4y = Clavier.saisirInt();
				
				
				while((case4y < 1) || (case4y > 10)){
					Ecran.afficher(" \nErreur de saisie, veuillez entrer un entier correct");
					case4y = Clavier.saisirInt();
					}
				
				BateauEn4 = BateauOuPas(LaCase, Entree4);
				compteur ++;
			}while(BateauEn4 == true);
		Entree4.x = case4x;
		Entree4.y = case4y;
		
		//On verifie quelle est bien adjacente a le deuxieme case saisie
	
		if (VerfAdj(Entree3, Entree4) == true){
			adjacent = true;
		}
		else{Ecran.afficher("\n Erreur, les deux points entrees ne sont pas adjacent ! \n");}
		if (!(((case1x == case2x) && (case2x == case3x) && (case3x == case4x)) || ((case1y == case2y) && (case2y == case3y) && (case3y == case4y)))){
			full_adjacent = false;
			Ecran.afficher("\nErreur, tous vos bateaux ne sont pas sur la meme ligne\n");}
	}while (adjacent == false);
	}while (full_adjacent == false);

	//On affecte les cases correspondants aux valeurs saisies	
	LaCase = Correspondance(LaCase, Entree1, bateau);
	LaCase = Correspondance(LaCase, Entree2, bateau);
	LaCase = Correspondance(LaCase, Entree3, bateau);
	LaCase = Correspondance(LaCase, Entree4, bateau);

	Ecran.afficher("\nLe croiseur a ete place en : ", Entree1.x, Entree1.y," ", Entree2.x, Entree2.y," ", Entree3.x, Entree3.y, " ", Entree4.x, Entree4.y, "\n");
	AffichageGrille(LaCase);
	
		return LaCase;
		}		
	/**	
	* @param La case de jeu ou l'on souhaite placer le bateau
	* @return Le type Case qui contient la case de jeu entree avec le bateau maintenant place
	*/
	static Case PlacerSousMarin(Case LaCase, String nbSousMarin){
		
		Coo Entree1 = new Coo();
		Coo Entree2 = new Coo();
		Coo Entree3 = new Coo();
		
		Ecran.afficher(" \nPlacement du sous_marin\n", nbSousMarin);
		
		String bateau;
		bateau = "torpilleur" + nbSousMarin;

		char case1x;
		int case1y;

		char case2x;
		int case2y;
		
		char case3x;
		int case3y;
		
		int compteur;
		compteur = 1;
		
		boolean adjacent;
		
		adjacent = false; //Rechanger a false apres ceci est un debug !!
			
		//On demande la saisie de l'utilisateur
		
		if (compteur > 1){
				Ecran.afficher("\n Il y a deja un bateau place sur cette case");
			}

		Coo CooSaisie = new Coo();
		
		Ecran.afficher("Veuillez entre un caractere : ");
		case1x = Clavier.saisirChar();

	
		Ecran.afficher("Veuillez entre un entier : ");
		case1y = Clavier.saisirInt();

		while (VerifSaisie(case1y, case1x) == false){
			Ecran.afficher("Erreur de saisie");
			Ecran.afficher("Veuillez entre un caractere : ");
			case1x = Clavier.saisirChar();
			Ecran.afficher("Veuillez entre un entier : ");
			case1y = Clavier.saisirInt();
		}
						
			Entree1.x = case1x;
			Entree1.y = case1y;

		//On appelle une sous fonction pour trouver la case qui correspond
		
		LaCase = Correspondance(LaCase, Entree1, bateau);

		//On demande une deuxieme saisie pour la seconde case
		
		do{

		//On demande la saisie de l'utilisateur
		//
		//On verifie la saisie
		//
		//Ici on les fixe manuellement pour ne pas avoir a tester chaque fois
		
		if (compteur > 1){
				Ecran.afficher("\n Il y a deja un bateau place sur cette case");
			}
			
		
		Ecran.afficher("\n Placement de la 2eme case \n Veuillez entre un caractere : ");
		case2x = Clavier.saisirChar();
		Ecran.afficher("Veuillez entre un entier : ");
		case2y = Clavier.saisirInt();

		while (VerifSaisie(case2y, case2x) == false){
			Ecran.afficher("Erreur de saisie");
			Ecran.afficher("Veuillez entre un caractere : ");
			case2x = Clavier.saisirChar();
			Ecran.afficher("Veuillez entre un entier : ");
			case2y = Clavier.saisirInt();
		}
			
			
	
		
		Entree2.x = case2x;
		Entree2.y = case2y;
		

		//On rappelle la fonction pour trouver la case qui correspond
		
		LaCase = Correspondance(LaCase, Entree2, bateau);

		//On regarde si la case a deja un bateau
		
		//On regarde si cette case est adjacente a la premiere
		
		if (VerfAdj(Entree1, Entree2) == true){
			adjacent = true;
		}
		else{Ecran.afficher("Erreur, les deux points entrees ne sont pas adjacent !");}
	} while (adjacent == false);
	
		adjacent = true; // DEBUG rechanger a false apres
	
		//On demande la saisie de la troisieme case 
		
		do{

		//On demande la saisie de l'utilisateur
		//
		//On verifie la saisie
		//
		//Ici on les fixe manuellement pour ne pas avoir a tester chaque fois
		
		if (compteur > 1){
				Ecran.afficher("\n Il y a deja un bateau place sur cette case");
			}
			
		
		Ecran.afficher("Veuillez entre un caractere : ");
		case3x = Clavier.saisirChar();
		Ecran.afficher("Veuillez entre un entier : ");
		case3y = Clavier.saisirInt();

		while (VerifSaisie(case3y, case3x) == false){
			Ecran.afficher("Erreur de saisie");
			Ecran.afficher("Veuillez entre un caractere : ");
			case3x = Clavier.saisirChar();
			Ecran.afficher("Veuillez entre un entier : ");
			case3y = Clavier.saisirInt();
		}
			
		
		Entree3.x = case3x;
		Entree3.y = case3y;
	
		//On appelle la fonction pour trouver la case qui correspond
	
		LaCase = Correspondance(LaCase, Entree3, bateau);
	
		//On verifie quelle est bien adjacente a le deuxieme case saisie
	
		if (VerfAdj(Entree2, Entree3) == true){
			adjacent = true;
		}
		else{Ecran.afficher("Erreur, les deux points entrees ne sont pas adjacent !");}
		} while (adjacent == false);
	
	return LaCase;
	
	}		
	/**	
	* @param La case de jeu ou l'on souhaite placer le bateau
	* @return Le type Case qui contient la case de jeu entree avec le bateau maintenant place
	*/
	static Case PlacerSousMarin1(Case LaCase){
		Ecran.afficher("DEBUGG");	
		Coo Entree1 = new Coo();
		Coo Entree2 = new Coo();
		Coo Entree3 = new Coo();
		
		
		String bateau;
		bateau = "sous_marin1";

		char case1x;
		int case1y;

		char case2x;
		int case2y;
		
		char case3x;
		int case3y;
			
		int compteur;
		compteur = 1;
		
		boolean adjacent;
		
		adjacent = true; //Rechanger a false apres ceci est un debug !!
		
		do{

		//On demande la saisie de l'utilisateur
		//
		//On verifie la saisie
		//
		//Ici on les fixe manuellement pour ne pas avoir a tester chaque fois
		
		if (compteur > 1){
				Ecran.afficher("\n Il y a deja un bateau place sur cette case");
			}
			
		Coo CooSaisie = new Coo();
		
		case1x = SaisieUserChar(); 
		case1y = SaisieUserInt();	
				
			Entree1.x = case1x;
			Entree1.y = case1y;

		//On appelle une sous fonction pour trouver la case qui correspond
		
		LaCase = Correspondance(LaCase, Entree1, bateau);

		//On demande une deuxieme saisie pour la seconde case
		
	

		//On demande la saisie de l'utilisateur
		//
		//On verifie la saisie
		//
		//Ici on les fixe manuellement pour ne pas avoir a tester chaque fois
		
		if (compteur > 1){
				Ecran.afficher("\n Il y a deja un bateau place sur cette case");
			}
			
		Ecran.afficher("\nPlacement de la seconde case\n");	
		case2x = SaisieUserChar(); 
		case2y = SaisieUserInt();	
		
		Entree2.x = case2x;
		Entree2.y = case2y;
		//On rappelle la fonction pour trouver la case qui correspond
		LaCase = Correspondance(LaCase, Entree2, bateau);	
		//On regarde si cette case est adjacente a la premiere
		
		if (VerfAdj(Entree1, Entree2) == true){
			adjacent = true;
		}
		else{Ecran.afficher("\nErreur, les deux points entrees ne sont pas adjacent !");}
	} while (adjacent == false);
	
		adjacent = false; // DEBUG rechanger a false apres
		do {
		//On demande la saisie de la troisieme case 
		

		//On demande la saisie de l'utilisateur
		
		if (compteur > 1){
				Ecran.afficher("\n Il y a deja un bateau place sur cette case");
			}
			
		Ecran.afficher("\nPlacement de la 3eme case\n");
		case3x = SaisieUserChar(); 
		case3y = SaisieUserInt();	
		
		Entree3.x = case3x;
		Entree3.y = case3y;
	
		//On appelle la fonction pour trouver la case qui correspond
	
		LaCase = Correspondance(LaCase, Entree3, bateau);
	
		//On verifie quelle est bien adjacente a le deuxieme case saisie
	
		if (VerfAdj(Entree2, Entree3) == true){
			adjacent = true;
		}
		else{Ecran.afficher("Erreur, les deux points entrees ne sont pas adjacent !");}
	} while (adjacent == false);
	
		return LaCase;
		}		
	/**	
	* @param La case de jeu ou l'on souhaite placer le bateau
	* @return Le type Case qui contient la case de jeu entree avec le bateau maintenant place
	*/
	static Case PlacerPorteAvion(Case LaCase){
		
		Coo Entree1 = new Coo();
		Coo Entree2 = new Coo();
		Coo Entree3 = new Coo();
		Coo Entree4 = new Coo();
		Coo Entree5 = new Coo();
		
		String bateau;
		bateau = "porte_avion";

		char case1x;
		int case1y;

		char case2x;
		int case2y;
		
		char case3x;
		int case3y;
		
		char case4x;
		int case4y;
		
		char case5x;
		int case5y;
		
		boolean BateauEn1;
		boolean BateauEn2;
		boolean BateauEn3;
		boolean BateauEn4;
		boolean BateauEn5;
		boolean adjacent;
		boolean full_adjacent;	
		BateauEn1 = false;
		BateauEn2 = false;
		BateauEn3 = false;
		BateauEn4 = false;
		BateauEn5 = false;
		
		int compteur;
		compteur = 0;
		
		adjacent = false; 
		do{	
			do{
				Ecran.afficher("\nPlacement du porte-avion\n");
				do{

				//On demande la saisie de l'utilisateur
			
				if (compteur > 0){
						Ecran.afficher("\nErreur, les bateaux ne sont pas adjacents, ou il y a deja un bateau place sur cette case\n");
					}
				
					full_adjacent = true;
					Coo CooSaisie = new Coo();
		
					Ecran.afficher("\nPlacement de la 1ere case\nVeuillez entre un caractere : ");

					case1x = SaisieUserChar(); 
					case1y = SaisieUserInt();	
			
					Entree1.x = case1x;
					Entree1.y = case1y;	
				
					BateauEn1 = BateauOuPas(LaCase, Entree1);
					
					compteur ++;
				}while(BateauEn1 == true);
	
				compteur = 0;	

				//On demande la saisie de l'utilisateur
		do{	
			if (compteur > 0){
					Ecran.afficher("\nErreur, les bateaux ne sont pas adjacents, ou il y a deja un bateau place sur cette case\n");
				}
			
			
		Ecran.afficher("\nPlacement de la seconde case\nVeuillez entre un caractere : ");
			case2x = SaisieUserChar(); 
			case2y = SaisieUserInt();	
		
		Entree2.x = case2x;
		Entree2.y = case2y;

		//On regarde si la case a deja un bateau
		BateauEn2 = BateauOuPas(LaCase, Entree2);
		compteur ++;
			}while(BateauEn2 == true);	
		//On regarde si cette case est adjacente a la premiere
		
		if (VerfAdj(Entree1, Entree2) == true){
			adjacent = true;
		}
	} while (adjacent == false);
	
	compteur = 0;

		adjacent = false;
		do {
			do{
			if (compteur > 0){
					Ecran.afficher("\nErreur, les bateaux ne sont pas adjacents, ou il y a deja un bateau place sur cette case\n");
				}
			
			
			Ecran.afficher("\nPlacement de la troisieme case\n");
			//On demande la saisie de l'utilisateur
			case3x = SaisieUserChar(); 
			case3y = SaisieUserInt();	
		
			Entree3.x = case3x;
			Entree3.y = case3y;
		
			//On regarde si la case a deja un bateau
			BateauEn3 = BateauOuPas(LaCase, Entree3);
			compteur ++;
			}while(BateauEn3 == true);
	
		//On verifie quelle est bien adjacente a le deuxieme case saisie
	
		if (VerfAdj(Entree2, Entree3) == true){
			adjacent = true;
		}
	} while (adjacent == false);
	compteur = 0;	
	adjacent = false;
		do {
			do{
		//On demande la saisie de la troisieme case 

			if (compteur > 0){
					Ecran.afficher("\nErreur, les bateaux ne sont pas adjacents, ou il y a deja un bateau place sur cette case\n");
				}
			
			
		Ecran.afficher("\nPlacement de la quatrieme case\n");
		//On demande la saisie de l'utilisateur	
			case4x = SaisieUserChar(); 
			case4y = SaisieUserInt();	
		
		Entree4.x = case4x;
		Entree4.y = case4y;
	
				//On regarde si la case a deja un bateau
				BateauEn4 = BateauOuPas(LaCase, Entree4);
				compteur ++;
			}while(BateauEn4 == true);
	
		//On verifie quelle est bien adjacente a le deuxieme case saisie
	
		if (VerfAdj(Entree3, Entree4) == true){
			adjacent = true;
		}
	} while (adjacent == false);
	compteur = 0;	
	adjacent = false; 
		do {
			do{
		//On demande la saisie de la troisieme case 

			if (compteur > 0){
					Ecran.afficher("\nErreur, les bateaux ne sont pas adjacents, ou il y a deja un bateau place sur cette case\n");
				}
			
			
				Ecran.afficher("\nPlacement de la cinquieme et derniere case\n");
				case5x = SaisieUserChar(); 
				case5y = SaisieUserInt();	
				Entree5.x = case5x;
				Entree5.y = case5y;
		
				//On regarde si la case a deja un bateau
				BateauEn5 = BateauOuPas(LaCase, Entree5);
				compteur ++;
			}while(BateauEn5 == true);
	
		//On verifie quelle est bien adjacente a le deuxieme case saisie
	
		if (VerfAdj(Entree4, Entree5) == true){
			adjacent = true;
		}

		if (!(((case1x == case2x) && (case2x == case3x) && (case3x == case4x) && (case4x == case5x)) || ((case1y == case2y) && (case2y == case3y) && (case3y == case4y) && (case4y == case5y)))){
			full_adjacent = false;
			Ecran.afficher("\nErreur ! Tous vos bateaux ne sont pas adjacents !\n");}
	} while (adjacent == false);
	}while (full_adjacent == false);	
		
		LaCase = Correspondance(LaCase, Entree1, bateau);
		LaCase = Correspondance(LaCase, Entree2, bateau);
		LaCase = Correspondance(LaCase, Entree3, bateau);	
		LaCase = Correspondance(LaCase, Entree4, bateau);
		LaCase = Correspondance(LaCase, Entree5, bateau);	
		Ecran.afficher("\nLe porte-avion a ete place en : ", Entree1.x, Entree1.y," ", Entree2.x, Entree2.y," ", Entree3.x, Entree3.y," " , Entree4.x, Entree4.y," ", Entree5.x, Entree5.y,"\n");
		AffichageGrille(LaCase);
	
		return LaCase;
		}
	/**	
	* @param La case de jeu ou l'on souhaite placer le bateau
	* @return Le type Case qui contient la case de jeu entree avec le bateau maintenant place
	*/
	static Case PlacerPorteAvion2(Case LaCase){
		
			Coo Entree1 = new Coo();
			Coo Entree2 = new Coo();
			Coo Entree3 = new Coo();
			
			String bateau;
			bateau = "porte_avion2";
	
			char case1x;
			int case1y;
	
			char case2x;
			int case2y;
			
			char case3x;
			int case3y;
				
			int compteur;
			compteur = 0;
			
			boolean adjacent;

			boolean BateauEn1;
			boolean BateauEn2;
			boolean BateauEn3;

			BateauEn1 = false;
			BateauEn2 = false;
			BateauEn3 = false;

			adjacent = false; 
			Ecran.afficher("\n Placement du 1er sous - marin ! \n");
			
			do{

				compteur = 0;

				do{
	
				Coo CooSaisie = new Coo();
			//On demande la saisie de l'utilisateur
			//
			//On verifie la saisie
			//
			//Ici on les fixe manuellement pour ne pas avoir a tester chaque fois
			
				if (compteur > 0){
						Ecran.afficher("\nErreur, il y a deja un bateau place sur cette case, veuillez resaisir des coordonees valides ! \n");
					}
						
				Ecran.afficher("\nPlacement de la 1ere case\n");
				case1x = SaisieUserChar();
				case1y = SaisieUserInt();
					
					Entree1.x = case1x;
					Entree1.y = case1y;
		
				//On appelle une sous fonction pour trouver la case qui correspond
				BateauEn1 = BateauOuPas(LaCase, Entree1);
				
				compteur ++;
				}while(BateauEn1 == true);
				
			//On demande une deuxieme saisie pour la seconde case
			
			
			compteur = 0;
			do{
				
				
				if (compteur > 0){
					Ecran.afficher("\nErreur, il y a deja un bateau place sur cette case, veuillez resaisir des coordonees valides ! \n");
					}
					
				Ecran.afficher("\nPlacement de la 2eme case\n");
				case2x = SaisieUserChar();
				case2y = SaisieUserInt();
				
				Entree2.x = case2x;
				Entree2.y = case2y;
		
				//On regarde si la case a deja un bateau
				BateauEn2 = BateauOuPas(LaCase, Entree2);
				compteur ++;
			}while(BateauEn2 == true);
			//On regarde si cette case est adjacente a la premiere
			
			if (VerfAdj(Entree1, Entree2) == true){
				adjacent = true;
			}
			else{Ecran.afficher("\nErreur, les deux points entrees ne sont pas adjacent ! \n");}
		} while (adjacent == false);
			compteur = 0;
			adjacent = false; 
			do {
			
			if (compteur > 0){
					Ecran.afficher("\nErreur, il y a deja un bateau place sur cette case, veuillez resaisir des coordonees valides ! \n");
				}	
			
			Ecran.afficher("\nPlacement de la 3eme case\n");
			case3x = SaisieUserChar();
			case3y = SaisieUserInt();

			Entree3.x = case3x;
			Entree3.y = case3y;
			
			//On verifie quelle est bien adjacente a le deuxieme case saisie
		
			if (VerfAdj(Entree2, Entree3) == true){
				adjacent = true;
			}
			else{Ecran.afficher("\n Erreur, les deux points entrees ne sont pas adjacent ! \n");}
			if(!(((case1x == case2x) && (case2x == case3x)) || ((case1y == case2y) && (case2y == case3y)))){
			Ecran.afficher("\n Erreur ! Tous vos bateaux ne sont pas adjacents\n");
			adjacent = false;
			} 
		} while (adjacent == false);
		
		LaCase = Correspondance(LaCase, Entree1, bateau);
		LaCase = Correspondance(LaCase, Entree2, bateau);
		LaCase = Correspondance(LaCase, Entree3, bateau);	
		Ecran.afficher("\nLe premier sous marin a ete place en : ", Entree1.x, Entree1.y," ", Entree2.x, Entree2.y," ", Entree3.x, Entree3.y, "\n");
		AffichageGrille(LaCase);
		
			return LaCase;
			}
	/**	
	* @param La case de jeu ou l'on souhaite placer le bateau
	* @return Le type Case qui contient la case de jeu entree avec le bateau maintenant place
	*/
	static Case PlacerPorteAvion3(Case LaCase){
		
				Coo Entree1 = new Coo();
				Coo Entree2 = new Coo();
				Coo Entree3 = new Coo();
				
				String bateau;
				bateau = "porte_avion3";
		
				char case1x;
				int case1y;
		
				char case2x;
				int case2y;
				
				char case3x;
				int case3y;
					
				int compteur;
				compteur = 0;
				
				boolean adjacent;
	
				boolean BateauEn1;
				boolean BateauEn2;
				boolean BateauEn3;
	
				BateauEn1 = false;
				BateauEn2 = false;
				BateauEn3 = false;
	
				adjacent = false;	
				Ecran.afficher("\n Placement du 2eme sous - marin ! \n");
				
				do{
	
					compteur = 0;
	
					do{
	
						Ecran.afficher("Placement de la premiere case du sous marin");
		
				//On demande la saisie de l'utilisateur
				
					if (compteur > 0){
							Ecran.afficher("\n Erreur, il y a deja un bateau place sur cette case, veuillez resaisir des coordonees valides ! \n");
						}
						
					Coo CooSaisie = new Coo();
					
					Ecran.afficher("\nPlacement de la 1ere case\n");
					case1x = SaisieUserChar();
					case1y = SaisieUserInt();
						
					Entree1.x = case1x;
					Entree1.y = case1y;
			
					//On appelle une sous fonction pour trouver la case qui correspond
					BateauEn1 = BateauOuPas(LaCase, Entree1);
					
					compteur ++;
					}while(BateauEn1 == true);
					
				//On demande une deuxieme saisie pour la seconde case	
				
				compteur = 0;
				do{
				
				//On demande la saisie de l'utilisateur
					if (compteur > 0){
						Ecran.afficher("\nErreur, il y a deja un bateau place sur cette case, veuillez resaisir des coordonees valides ! \n");
						}
							
					Ecran.afficher("\nPlacement de la 2eme case\nVeuillez entre un caractere : ");
					case2x = SaisieUserChar();
					case2y = SaisieUserInt();
					
					Entree2.x = case2x;
					Entree2.y = case2y;
			
					//On regarde si la case a deja un bateau
					BateauEn2 = BateauOuPas(LaCase, Entree2);
					compteur ++;
				}while(BateauEn2 == true);
				//On regarde si cette case est adjacente a la premiere
				
				if (VerfAdj(Entree1, Entree2) == true){
					adjacent = true;
				}
				else{Ecran.afficher("\n Erreur, les deux points entrees ne sont pas adjacent ! \n");}
			} while (adjacent == false);
				compteur = 0;
				adjacent = false;
				do {
				//On demande la saisie de la troisieme case 
				if (compteur > 0){
						Ecran.afficher("\n Erreur, il y a deja un bateau place sur cette case, veuillez resaisir des coordonees valides ! \n");
					}
					
				Ecran.afficher("\nPlacement de la 3 eme case\nVeuillez entre un caractere : ");
				case3x = SaisieUserChar();
				case3y = SaisieUserInt();
				
				Entree3.x = case3x;
				Entree3.y = case3y;

				//On verifie quelle est bien adjacente a le deuxieme case saisie
			
				if (VerfAdj(Entree2, Entree3) == true){
					adjacent = true;
				}
				else{Ecran.afficher("\n Erreur, les deux points entrees ne sont pas adjacent ! \n");}
			if(!(((case1x == case2x) && (case2x == case3x)) || ((case1y == case2y) && (case2y == case3y)))){
				Ecran.afficher("\n Erreur ! Tous vos bateaux ne sont pas adjacents\n");
				adjacent = false;
			} 
			} while (adjacent == false);
			
			LaCase = Correspondance(LaCase, Entree1, bateau);
			LaCase = Correspondance(LaCase, Entree2, bateau);
			LaCase = Correspondance(LaCase, Entree3, bateau);	
			Ecran.afficher("\nLe premier sous marin a ete place en : ", Entree1.x, Entree1.y," ", Entree2.x, Entree2.y," ", Entree3.x, Entree3.y, "\n");
			AffichageGrille(LaCase);
			
				return LaCase;
				}	
	/**
	* Sert a afficher la grille du jeu	
	* @param La case de jeu que l'on souhaite afficher
	* @return Le type Case qui contient la case de jeu entree avec le bateau maintenant place
	*/
	static void AffichageGrille(Case LaCaseAffich){
		
		int compteur = 0;
		
		Ecran.afficher("\n| 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 |10 |  \n");
		Ecran.afficher("| A |");
		
		if (LaCaseAffich.A1.boat == true){ 
		   Ecran.afficher(" o |"); 
		} 
		 else { 
		   Ecran.afficher(" * |");
		} 
		 
		if (LaCaseAffich.A2.boat == true){ 
		   Ecran.afficher(" o |"); 
		} 
		 else { 
		   Ecran.afficher(" * |");
		} 
		 
		if (LaCaseAffich.A3.boat == true){ 
		   Ecran.afficher(" o |"); 
		} 
		 else { 
		   Ecran.afficher(" * |");
		} 
		 
		if (LaCaseAffich.A4.boat == true){ 
		   Ecran.afficher(" o |"); 
		} 
		 else { 
		   Ecran.afficher(" * |");
		} 
		 
		if (LaCaseAffich.A5.boat == true){ 
		   Ecran.afficher(" o |"); 
		} 
		 else { 
		   Ecran.afficher(" * |");
		} 
		 
		if (LaCaseAffich.A6.boat == true){ 
		   Ecran.afficher(" o |"); 
		} 
		 else { 
		   Ecran.afficher(" * |");
		}
		 
		if (LaCaseAffich.A7.boat == true){ 
		   Ecran.afficher(" o |"); 
		} 
		 else { 
		   Ecran.afficher(" * |");
		} 
		 
		if (LaCaseAffich.A8.boat == true){ 
		   Ecran.afficher(" o |"); 
		} 
		 else { 
		   Ecran.afficher(" * |");
		} 
		 
		if (LaCaseAffich.A9.boat == true){ 
		   Ecran.afficher(" o |"); 
		} 
		 else { 
		   Ecran.afficher(" * |");
		} 
		 
		if (LaCaseAffich.A10.boat == true){ 
		   Ecran.afficher(" o |"); 
		} 
		 else { 
		   Ecran.afficher(" * |");
		} 
		
		Ecran.afficher("\n| B |");
		 
		if (LaCaseAffich.B1.boat == true){ 
		   Ecran.afficher(" o |"); 
		} 
		 else { 
		   Ecran.afficher(" * |");
		} 
		 
		if (LaCaseAffich.B2.boat == true){ 
		   Ecran.afficher(" o |"); 
		} 
		 else { 
		   Ecran.afficher(" * |");
		} 
		 
		if (LaCaseAffich.B3.boat == true){ 
		   Ecran.afficher(" o |"); 
		} 
		 else { 
		   Ecran.afficher(" * |");
		} 
		 
		if (LaCaseAffich.B4.boat == true){ 
		   Ecran.afficher(" o |"); 
		} 
		 else { 
		   Ecran.afficher(" * |");
		} 
		 
		if (LaCaseAffich.B5.boat == true){ 
		   Ecran.afficher(" o |"); 
		} 
		 else { 
		   Ecran.afficher(" * |");
		} 
		 
		if (LaCaseAffich.B6.boat == true){ 
		   Ecran.afficher(" o |"); 
		} 
		 else { 
		   Ecran.afficher(" * |");
		} 
		 
		if (LaCaseAffich.B7.boat == true){ 
		   Ecran.afficher(" o |"); 
		} 
		 else { 
		   Ecran.afficher(" * |");
		} 
		 
		if (LaCaseAffich.B8.boat == true){ 
		   Ecran.afficher(" o |"); 
		} 
		 else { 
		   Ecran.afficher(" * |");
		} 
		 
		if (LaCaseAffich.B9.boat == true){ 
		   Ecran.afficher(" o |"); 
		} 
		 else { 
		   Ecran.afficher(" * |");
		} 
		 
		if (LaCaseAffich.B10.boat == true){ 
		   Ecran.afficher(" o |"); 
		} 
		 else { 
		   Ecran.afficher(" * |");
		} 
		Ecran.afficher("\n| C |"); 
		
		if (LaCaseAffich.C1.boat == true){ 
		   Ecran.afficher(" o |"); 
		} 
		 else { 
		   Ecran.afficher(" * |");
		} 
		 
		if (LaCaseAffich.C2.boat == true){ 
		   Ecran.afficher(" o |"); 
		} 
		 else { 
		   Ecran.afficher(" * |");
		} 
		 
		if (LaCaseAffich.C3.boat == true){ 
		   Ecran.afficher(" o |"); 
		} 
		 else { 
		   Ecran.afficher(" * |");
		} 
		 
		if (LaCaseAffich.C4.boat == true){ 
		   Ecran.afficher(" o |"); 
		} 
		 else { 
		   Ecran.afficher(" * |");
		} 
		 
		if (LaCaseAffich.C5.boat == true){ 
		   Ecran.afficher(" o |"); 
		} 
		 else { 
		   Ecran.afficher(" * |");
		} 
		 
		if (LaCaseAffich.C6.boat == true){ 
		   Ecran.afficher(" o |"); 
		} 
		 else { 
		   Ecran.afficher(" * |");
		} 
		 
		if (LaCaseAffich.C7.boat == true){ 
		   Ecran.afficher(" o |"); 
		} 
		 else { 
		   Ecran.afficher(" * |");
		} 
		 
		if (LaCaseAffich.C8.boat == true){ 
		   Ecran.afficher(" o |"); 
		} 
		 else { 
		   Ecran.afficher(" * |");
		} 
		 
		if (LaCaseAffich.C9.boat == true){ 
		   Ecran.afficher(" o |"); 
		} 
		 else { 
		   Ecran.afficher(" * |");
		} 
		 
		if (LaCaseAffich.C10.boat == true){ 
		   Ecran.afficher(" o |"); 
		} 
		 else { 
		   Ecran.afficher(" * |");
		} 
		 
		Ecran.afficher("\n| D |");
		if (LaCaseAffich.D1.boat == true){ 
		   Ecran.afficher(" o |"); 
		} 
		 else { 
		   Ecran.afficher(" * |");
		} 
		 
		if (LaCaseAffich.D2.boat == true){ 
		   Ecran.afficher(" o |"); 
		} 
		 else { 
		   Ecran.afficher(" * |");
		} 
		 
		if (LaCaseAffich.D3.boat == true){ 
		   Ecran.afficher(" o |"); 
		} 
		 else { 
		   Ecran.afficher(" * |");
		} 
		 
		if (LaCaseAffich.D4.boat == true){ 
		   Ecran.afficher(" o |"); 
		} 
		 else { 
		   Ecran.afficher(" * |");
		} 
		 
		if (LaCaseAffich.D5.boat == true){ 
		   Ecran.afficher(" o |"); 
		} 
		 else { 
		   Ecran.afficher(" * |");
		} 
		 
		if (LaCaseAffich.D6.boat == true){ 
		   Ecran.afficher(" o |"); 
		} 
		 else { 
		   Ecran.afficher(" * |");
		} 
		 
		if (LaCaseAffich.D7.boat == true){ 
		   Ecran.afficher(" o |"); 
		} 
		 else { 
		   Ecran.afficher(" * |");
		} 
		 
		if (LaCaseAffich.D8.boat == true){ 
		   Ecran.afficher(" o |"); 
		} 
		 else { 
		   Ecran.afficher(" * |");
		} 
		 
		if (LaCaseAffich.D9.boat == true){ 
		   Ecran.afficher(" o |"); 
		} 
		 else { 
		   Ecran.afficher(" * |");
		} 
		 
		if (LaCaseAffich.D10.boat == true){ 
		   Ecran.afficher(" o |"); 
		} 
		 else { 
		   Ecran.afficher(" * |");
		} 
		 
		Ecran.afficher("\n| E |");
		
		if (LaCaseAffich.E1.boat == true){ 
		   Ecran.afficher(" o |"); 
		} 
		 else { 
		   Ecran.afficher(" * |");
		} 
		 
		if (LaCaseAffich.E2.boat == true){ 
		   Ecran.afficher(" o |"); 
		} 
		 else { 
		   Ecran.afficher(" * |");
		} 
		 
		if (LaCaseAffich.E3.boat == true){ 
		   Ecran.afficher(" o |"); 
		} 
		 else { 
		   Ecran.afficher(" * |");
		} 
		 
		if (LaCaseAffich.E4.boat == true){ 
		   Ecran.afficher(" o |"); 
		} 
		 else { 
		   Ecran.afficher(" * |");
		} 
		 
		if (LaCaseAffich.E5.boat == true){ 
		   Ecran.afficher(" o |"); 
		} 
		 else { 
		   Ecran.afficher(" * |");
		} 
		 
		if (LaCaseAffich.E6.boat == true){ 
		   Ecran.afficher(" o |"); 
		} 
		 else { 
		   Ecran.afficher(" * |");
		} 
		 
		if (LaCaseAffich.E7.boat == true){ 
		   Ecran.afficher(" o |"); 
		} 
		 else { 
		   Ecran.afficher(" * |");
		} 
		 
		if (LaCaseAffich.E8.boat == true){ 
		   Ecran.afficher(" o |"); 
		} 
		 else { 
		   Ecran.afficher(" * |");
		} 
		 
		if (LaCaseAffich.E9.boat == true){ 
		   Ecran.afficher(" o |"); 
		} 
		 else { 
		   Ecran.afficher(" * |");
		} 
		 
		if (LaCaseAffich.E10.boat == true){ 
		   Ecran.afficher(" o |"); 
		} 
		 else { 
		   Ecran.afficher(" * |");
		} 
		
		Ecran.afficher("\n| F |");		
		
		if (LaCaseAffich.F1.boat == true){ 
		   Ecran.afficher(" o |"); 
		} 
		 else { 
		   Ecran.afficher(" * |");
		} 
		 
		if (LaCaseAffich.F2.boat == true){ 
		   Ecran.afficher(" o |"); 
		} 
		 else { 
		   Ecran.afficher(" * |");
		} 
		 
		if (LaCaseAffich.F3.boat == true){ 
		   Ecran.afficher(" o |"); 
		} 
		 else { 
		   Ecran.afficher(" * |");
		} 
		 
		if (LaCaseAffich.F4.boat == true){ 
		   Ecran.afficher(" o |"); 
		} 
		 else { 
		   Ecran.afficher(" * |");
		} 
		 
		if (LaCaseAffich.F5.boat == true){ 
		   Ecran.afficher(" o |"); 
		} 
		 else { 
		   Ecran.afficher(" * |");
		} 
		 
		if (LaCaseAffich.F6.boat == true){ 
		   Ecran.afficher(" o |"); 
		} 
		 else { 
		   Ecran.afficher(" * |");
		} 
		 
		if (LaCaseAffich.F7.boat == true){ 
		   Ecran.afficher(" o |"); 
		} 
		 else { 
		   Ecran.afficher(" * |");
		} 
		 
		if (LaCaseAffich.F8.boat == true){ 
		   Ecran.afficher(" o |"); 
		} 
		 else { 
		   Ecran.afficher(" * |");
		} 
		 
		if (LaCaseAffich.F9.boat == true){ 
		   Ecran.afficher(" o |"); 
		} 
		 else { 
		   Ecran.afficher(" * |");
		} 
		 
		if (LaCaseAffich.F10.boat == true){ 
		   Ecran.afficher(" o |"); 
		} 
		 else { 
		   Ecran.afficher(" * |");
		} 
		Ecran.afficher("\n| G |");
		 
		if (LaCaseAffich.G1.boat == true){ 
		   Ecran.afficher(" o |"); 
		} 
		 else { 
		   Ecran.afficher(" * |");
		} 
		 
		if (LaCaseAffich.G2.boat == true){ 
		   Ecran.afficher(" o |"); 
		} 
		 else { 
		   Ecran.afficher(" * |");
		} 
		 
		if (LaCaseAffich.G3.boat == true){ 
		   Ecran.afficher(" o |"); 
		} 
		 else { 
		   Ecran.afficher(" * |");
		} 
		 
		if (LaCaseAffich.G4.boat == true){ 
		   Ecran.afficher(" o |"); 
		} 
		 else { 
		   Ecran.afficher(" * |");
		} 
		 
		if (LaCaseAffich.G5.boat == true){ 
		   Ecran.afficher(" o |"); 
		} 
		 else { 
		   Ecran.afficher(" * |");
		} 
		 
		if (LaCaseAffich.G6.boat == true){ 
		   Ecran.afficher(" o |"); 
		} 
		 else { 
		   Ecran.afficher(" * |");
		} 
		 
		if (LaCaseAffich.G7.boat == true){ 
		   Ecran.afficher(" o |"); 
		} 
		 else { 
		   Ecran.afficher(" * |");
		} 
		 
		if (LaCaseAffich.G8.boat == true){ 
		   Ecran.afficher(" o |"); 
		} 
		 else { 
		   Ecran.afficher(" * |");
		} 
		 
		if (LaCaseAffich.G9.boat == true){ 
		   Ecran.afficher(" o |"); 
		} 
		 else { 
		   Ecran.afficher(" * |");
		} 
		 
		if (LaCaseAffich.G10.boat == true){ 
		   Ecran.afficher(" o |"); 
		} 
		 else { 
		   Ecran.afficher(" * |");
		} 
		Ecran.afficher("\n| H |");
		 
		if (LaCaseAffich.H1.boat == true){ 
		   Ecran.afficher(" o |"); 
		} 
		 else { 
		   Ecran.afficher(" * |");
		} 
		 
		if (LaCaseAffich.H2.boat == true){ 
		   Ecran.afficher(" o |"); 
		} 
		 else { 
		   Ecran.afficher(" * |");
		} 
		 
		if (LaCaseAffich.H3.boat == true){ 
		   Ecran.afficher(" o |"); 
		} 
		 else { 
		   Ecran.afficher(" * |");
		} 
		 
		if (LaCaseAffich.H4.boat == true){ 
		   Ecran.afficher(" o |"); 
		} 
		 else { 
		   Ecran.afficher(" * |");
		} 
		 
		if (LaCaseAffich.H5.boat == true){ 
		   Ecran.afficher(" o |"); 
		} 
		 else { 
		   Ecran.afficher(" * |");
		} 
		 
		if (LaCaseAffich.H6.boat == true){ 
		   Ecran.afficher(" o |"); 
		} 
		 else { 
		   Ecran.afficher(" * |");
		} 
		 
		if (LaCaseAffich.H7.boat == true){ 
		   Ecran.afficher(" o |"); 
		} 
		 else { 
		   Ecran.afficher(" * |");
		} 
		 
		if (LaCaseAffich.H8.boat == true){ 
		   Ecran.afficher(" o |"); 
		} 
		 else { 
		   Ecran.afficher(" * |");
		} 
		 
		if (LaCaseAffich.H9.boat == true){ 
		   Ecran.afficher(" o |"); 
		} 
		 else { 
		   Ecran.afficher(" * |");
		} 
		 
		if (LaCaseAffich.H10.boat == true){ 
		   Ecran.afficher(" o |"); 
		} 
		 else { 
		   Ecran.afficher(" * |");
		} 
		 
		Ecran.afficher("\n| I |");
		
		if (LaCaseAffich.I1.boat == true){ 
		   Ecran.afficher(" o |"); 
		} 
		 else { 
		   Ecran.afficher(" * |");
		} 
		 
		if (LaCaseAffich.I2.boat == true){ 
		   Ecran.afficher(" o |"); 
		} 
		 else { 
		   Ecran.afficher(" * |");
		} 
		 
		if (LaCaseAffich.I3.boat == true){ 
		   Ecran.afficher(" o |"); 
		} 
		 else { 
		   Ecran.afficher(" * |");
		} 
		 
		if (LaCaseAffich.I4.boat == true){ 
		   Ecran.afficher(" o |"); 
		} 
		 else { 
		   Ecran.afficher(" * |");
		} 
		 
		if (LaCaseAffich.I5.boat == true){ 
		   Ecran.afficher(" o |"); 
		} 
		 else { 
		   Ecran.afficher(" * |");
		} 
		 
		if (LaCaseAffich.I6.boat == true){ 
		   Ecran.afficher(" o |"); 
		} 
		 else { 
		   Ecran.afficher(" * |");
		} 
		 
		if (LaCaseAffich.I7.boat == true){ 
		   Ecran.afficher(" o |"); 
		} 
		 else { 
		   Ecran.afficher(" * |");
		} 
		 
		if (LaCaseAffich.I8.boat == true){ 
		   Ecran.afficher(" o |"); 
		} 
		 else { 
		   Ecran.afficher(" * |");
		} 
		 
		if (LaCaseAffich.I9.boat == true){ 
		   Ecran.afficher(" o |"); 
		} 
		 else { 
		   Ecran.afficher(" * |");
		} 
		 
		if (LaCaseAffich.I10.boat == true){ 
		   Ecran.afficher(" o |"); 
		} 
		 else { 
		   Ecran.afficher(" * |");
		} 
		
		Ecran.afficher("\n| J |");
		 
		if (LaCaseAffich.J1.boat == true){ 
		   Ecran.afficher(" o |"); 
		} 
		 else { 
		   Ecran.afficher(" * |");
		} 
		 
		if (LaCaseAffich.J2.boat == true){ 
		   Ecran.afficher(" o |"); 
		} 
		 else { 
		   Ecran.afficher(" * |");
		} 
		 
		if (LaCaseAffich.J3.boat == true){ 
		   Ecran.afficher(" o |"); 
		} 
		 else { 
		   Ecran.afficher(" * |");
		} 
		 
		if (LaCaseAffich.J4.boat == true){ 
		   Ecran.afficher(" o |"); 
		} 
		 else { 
		   Ecran.afficher(" * |");
		} 
		 
		if (LaCaseAffich.J5.boat == true){ 
		   Ecran.afficher(" o |"); 
		} 
		 else { 
		   Ecran.afficher(" * |");
		} 
		 
		if (LaCaseAffich.J6.boat == true){ 
		   Ecran.afficher(" o |"); 
		} 
		 else { 
		   Ecran.afficher(" * |");
		} 
		 
		if (LaCaseAffich.J7.boat == true){ 
		   Ecran.afficher(" o |"); 
		} 
		 else { 
		   Ecran.afficher(" * |");
		} 
		 
		if (LaCaseAffich.J8.boat == true){ 
		   Ecran.afficher(" o |"); 
		} 
		 else { 
		   Ecran.afficher(" * |");
		} 
		 
		if (LaCaseAffich.J9.boat == true){ 
		   Ecran.afficher(" o |"); 
		} 
		 else {
		   Ecran.afficher(" * |");
		} 
		 
		if (LaCaseAffich.J10.boat == true){ 
		   Ecran.afficher(" o |"); 
		} 
		 else { 
		   Ecran.afficher(" * |");
		} 

		
		
	}
	/**
	* Lance la premiere etape du jeu en appelant different sous algorithmes necessaire
	* @param Rien
	* @return Rien
	*/
	static void E2(){
		Case CaseOrdinateur = new Case();
		CaseOrdinateur = DeclarationDeLaGrille();
		AffichageGrille(CaseOrdinateur);
		CaseOrdinateur = PlacerTorpilleurOrdi(CaseOrdinateur);
		Ecran.afficher("\n \n \n \n");
		CaseOrdinateur = PlacerSousMarinOrdi1(CaseOrdinateur);
		CaseOrdinateur = PlacerSousMarinOrdi1(CaseOrdinateur);
		CaseOrdinateur = PlacerCroiseurOrdi(CaseOrdinateur);
		CaseOrdinateur = PlacerPorteAv(CaseOrdinateur);

		AffichageGrille(CaseOrdinateur);
		}	
	/**
	 * @param Rien
	 * @return Un entier au hasard entre 1 et 10 inclus 
	 */
	static int EntierHasard(){
		
		double doubleHasard;
		int entierHasard;

		doubleHasard = Math.random();
		doubleHasard = doubleHasard * 10;
		entierHasard = (int) doubleHasard + 1;
		return entierHasard;
	}	
	/**
	 * @param Rien
	 * @return Un char entre A et J inclus
	 */
	static char CharHasard(){
	
		int nombre;
		double nbvirgule;
		char lettre;

		nbvirgule = Math.random();
		nbvirgule = nbvirgule * 10;
		nbvirgule = nbvirgule + 65;
		lettre = (char) nbvirgule;

		return lettre;
	}	
	/**
	 * @param Rien
	 * @return Vrai ou faux au hasard
	 */	
	static boolean ConditHasard(){
		boolean LunOuLautre;
		int entierHasard;
		double doubleHasard;
		
		LunOuLautre = false;
		doubleHasard = Math.random();
		doubleHasard = doubleHasard * 2;
		
		entierHasard = (int) doubleHasard; 
		if (entierHasard == 1){
			LunOuLautre = true;
			}
		
		return LunOuLautre;
	}
	/**
	* @param la grile de l'ordinateur
	* @return la grille de l'ordi avec un bateau place
	*/
	static Case PlacerTorpilleurOrdi(Case LaCase){

		//Declaration des variables
		String bateau;
		bateau = "torpilleur";
		
		boolean adjacent;
		boolean hasard1;
		boolean hasard2;
		
		int transtypage;
		
		hasard1 = ConditHasard();
		hasard2 = ConditHasard();
		
		Coo Entree1 = new Coo();
		Coo Entree2 = new Coo();

		char case1x;
		int case1y;
		
		case1x = CharHasard();
		case1y = EntierHasard();
		
		Entree1.x = case1x;
		Entree1.y = case1y;

		char case2x;
		int case2y;
		
		case2y = case1y;
		case2x = case1x;
		
		transtypage = (int) case2x;
		
		LaCase = Correspondance(LaCase, Entree1, bateau);

		//On demande une deuxieme saisie pour la seconde case
		
		if ((hasard1 == true) && (hasard2 == true)){
			
				case2y = case1y + 1;
		}
			
		else if ((hasard1 == true) && (hasard2 == false)){
				case2y = case1y - 1; 
		}
		else if((hasard1 == true) && (hasard2 == false)){
			
			transtypage = (int) case1x;
			transtypage ++;
			case2x = (char) transtypage;
		}
			
		else{
			transtypage = (int) case1x;
			transtypage = transtypage - 1 ;
			case2x = (char) transtypage;
			}
			
		if (case2y == 11){
			case2y = 9;
			}
		if (case2x == '@'){
			case2x = 'B';
			}
			
		if (case2x == 'K'){
			case2x = 'I';
			}
				
		transtypage = (int) case2x;
			
		Entree2.x = case2x;
		Entree2.y = case2y;
	
		LaCase = Correspondance(LaCase, Entree2, bateau);
		
		return LaCase;
	}	
	/**
	* @param la grile de l'ordinateur
	* @return la grille de l'ordi avec un bateau place
	*/
	static Case PlacerSousMarinOrdi1(Case LaCase){

		
		boolean adjacent;
		boolean hasard1;
		boolean hasard2;
		boolean bateauPlace;
		
		String bateau;
		bateau = "porte_avion2";

		int transtypage;

		int case1y;
		int case2y;
		int case3y;
		
		char case1x;
		char case2x;
		char case3x;
		
		Coo Entree1 = new Coo();
		Coo Entree2 = new Coo();
		Coo Entree3 = new Coo();

		bateauPlace = false;
		do{
			hasard1 = ConditHasard();
			hasard2 = ConditHasard();
			case1x = CharHasard();
			case1y = EntierHasard();
			case2x = case1x;
			case3x = case1x;
			case2y = case1y;
			case3y = case1y;

					
			if ((hasard1 == true) && (hasard2 == true)){
				
					case2y = case1y + 1;
					case3y = case1y + 2;
					Ecran.afficher("hasard1 est vrai et hasard2 vrai");
			}
			
			else if ((hasard1 == true) && (hasard2 == false)){
					case2y = case1y - 1;
					case3y = case1y - 2;
					Ecran.afficher("hasard1 est vrai et hasard2 faux");
			}
			else if((hasard1 == true) && (hasard2 == false)){
			
				transtypage = (int) case1x;
				transtypage ++;
				case2x = (char) transtypage;
				transtypage ++;
				case3x = (char) transtypage;
				Ecran.afficher("hasard1 est faux et hasard2 vrai");
			}
			
			else{
				transtypage = (int) case1x;
				transtypage = transtypage - 1 ;
				case2x = (char) transtypage;
				transtypage = transtypage - 1 ;
				case3x = (char) transtypage;
				}
			
			if (case2y == 11){
				case2y = 9;
				case3y = 8;
				}
			if (case2x == '@'){
				case2x = 'B';
				case3x = 'C';
				}
			
			if (case2x == 'K'){
				case2x = 'I';
				case3x = 'F';
				}

			Entree1.x = case1x;
			Entree1.y = case1y;
			Entree2.x = case2x;
			Entree2.y = case2y;
			Entree3.x = case3x;
			Entree3.y = case3y;
			Ecran.afficher("Sous marin : 1 = ", case1x, case1y, " \n Sous marin : 2 = ", case2x, case2y, " \n Sous marin : 3 = ", case3x, case3y ,"\n");
				
		if (((BateauOuPas(LaCase, Entree1) == false) && (BateauOuPas(LaCase, Entree2) == false)) && (BateauOuPas(LaCase, Entree3) == false)){	
			
			LaCase = Correspondance(LaCase, Entree1, bateau);
			LaCase = Correspondance(LaCase, Entree2, bateau);
			LaCase = Correspondance(LaCase, Entree3, bateau);
			bateauPlace = true;
		}

		} while(bateauPlace == false);

		return LaCase;
	}
	/**
	* @param la grile de l'ordinateur
	* @return la grille de l'ordi avec un bateau place
	*/
	static Case PlacerCroiseurOrdi(Case LaCase){
	
		boolean adjacent;
		boolean hasard1;
		boolean hasard2;
		boolean bateauPlace;
		
		String bateau;
		bateau = "croiseur";

		int transtypage;

		int case1y;
		int case2y;
		int case3y;
		int case4y;
		
		char case1x;
		char case2x;
		char case3x;
		char case4x;
		
		Coo Entree1 = new Coo();
		Coo Entree2 = new Coo();
		Coo Entree3 = new Coo();
		Coo Entree4 = new Coo();

		bateauPlace = false;
		do{
			hasard1 = ConditHasard();
			hasard2 = ConditHasard();
			case1x = CharHasard();
			case1y = EntierHasard();
			case2x = case1x;
			case3x = case1x;
			case4y = case1y;
			case4x = case1x;
			case2y = case1y;
			case3y = case1y;
					
			if ((hasard1 == true) && (hasard2 == true)){
				
					case2y = case1y + 1;
					case3y = case1y + 2;
					case4y = case1y + 3;
			}
			
			else if ((hasard1 == true) && (hasard2 == false)){
					case2y = case1y - 1;
					case3y = case1y - 2;
					case4y = case1y - 3;
			}
			else if((hasard1 == true) && (hasard2 == false)){
			
				transtypage = (int) case1x;
				transtypage ++;
				case2x = (char) transtypage;
				transtypage ++;
				case3x = (char) transtypage;
				transtypage ++;
				case4x = (char) transtypage;
			}
			
			else{
				transtypage = (int) case1x;
				transtypage = transtypage - 1 ;
				case2x = (char) transtypage;
				transtypage = transtypage - 1 ;
				case3x = (char) transtypage;
				transtypage = transtypage - 1 ;
				case4x = (char) transtypage;
				}
			
			if (case2y == 11){
				case2y = 9;
				case3y = 8;
				case4y = 7;
				}
			if (case2x == '@'){
				case2x = 'B';
				case3x = 'C';
				case4x = 'D';
				}
			
			if (case2x == 'K'){
				case2x = 'I';
				case3x = 'F';
				case4x = 'E';
				}

			Entree1.x = case1x;
			Entree1.y = case1y;
			Entree2.x = case2x;
			Entree2.y = case2y;
			Entree3.x = case3x;
			Entree3.y = case3y;
			Entree4.x = case4x;
			Entree4.y = case4y;

				
			if ((((BateauOuPas(LaCase, Entree1) == false) && (BateauOuPas(LaCase, Entree2) == false)) && (BateauOuPas(LaCase, Entree3) == false)) && ((BateauOuPas(LaCase, Entree4) == false))){	
			
				LaCase = Correspondance(LaCase, Entree1, bateau);
				LaCase = Correspondance(LaCase, Entree2, bateau);
				LaCase = Correspondance(LaCase, Entree3, bateau);
				LaCase = Correspondance(LaCase, Entree4, bateau);
				bateauPlace = true; 
		}

		} while(bateauPlace == false);

		return LaCase;
	
	}
	/**
	* @param la grile de l'ordinateur
	* @return la grille de l'ordi avec un bateau place
	*/
	static Case PlacerPorteAv(Case LaCase){
		
		boolean adjacent;
		boolean hasard1;
		boolean hasard2;
		boolean bateauPlace;
		
		String bateau;
		bateau = "porte_avion";

		int transtypage;

		int case1y;
		int case2y;
		int case3y;
		int case4y;
		int case5y;
		
		char case1x;
		char case2x;
		char case3x;
		char case4x;
		char case5x;

		Coo Entree1 = new Coo();
		Coo Entree2 = new Coo();
		Coo Entree3 = new Coo();
		Coo Entree4 = new Coo();
		Coo Entree5 = new Coo();

		bateauPlace = false;
		do{
			hasard1 = ConditHasard();
			hasard2 = ConditHasard();
			case1x = CharHasard();
			case1y = EntierHasard();
			case2x = case1x;
			case3x = case1x;
			case4y = case1y;
			case4x = case1x;
			case2y = case1y;
			case3y = case1y;
			case5y = case1y;
			case5x = case1x;
			

					
			if ((hasard1 == true) && (hasard2 == true)){
				
					case2y = case1y + 1;
					case3y = case1y + 2;
					case4y = case1y + 3;
					case5y = case1y + 4;
			}
			
			else if ((hasard1 == true) && (hasard2 == false)){
					case2y = case1y - 1;
					case3y = case1y - 2;
					case4y = case1y - 3;
					case5y = case1y - 4;
			}
			else if((hasard1 == true) && (hasard2 == false)){
			
				transtypage = (int) case1x;
				transtypage ++;
				case2x = (char) transtypage;
				transtypage ++;
				case3x = (char) transtypage;
				transtypage ++;
				case4x = (char) transtypage;
				transtypage ++;
				case5x = (char) transtypage;
			}
			
			else{
				transtypage = (int) case1x;
				transtypage = transtypage - 1 ;
				case2x = (char) transtypage;
				transtypage = transtypage - 1 ;
				case3x = (char) transtypage;
				transtypage = transtypage - 1 ;
				case4x = (char) transtypage;
				transtypage = transtypage - 1 ;
				case5x = (char) transtypage;
				}
			
			if (case2y == 11){
				case2y = 9;
				case3y = 8;
				case4y = 7;
				case5y = 8;
				}
			if (case2x == '@'){
				case2x = 'B';
				case3x = 'C';
				case4x = 'D';
				case5x = 'E';		
				}
			
			if (case2x == 'K'){
				case2x = 'I';
				case3x = 'F';
				case4x = 'E';
				case5x = 'D';
				}

			Entree1.x = case1x;
			Entree1.y = case1y;
			Entree2.x = case2x;
			Entree2.y = case2y;
			Entree3.x = case3x;
			Entree3.y = case3y;
			Entree4.x = case4x;
			Entree4.y = case4y;
			Entree5.x = case5x;
			Entree5.y = case5y;
	
			if (((((BateauOuPas(LaCase, Entree1) == false) && (BateauOuPas(LaCase, Entree2) == false)) && (BateauOuPas(LaCase, Entree3) == false)) && ((BateauOuPas(LaCase, Entree4) == false)))&&((BateauOuPas(LaCase, Entree5) == false))){	
			
				LaCase = Correspondance(LaCase, Entree1, bateau);
				LaCase = Correspondance(LaCase, Entree2, bateau);
				LaCase = Correspondance(LaCase, Entree3, bateau);
				LaCase = Correspondance(LaCase, Entree4, bateau);
				LaCase = Correspondance(LaCase, Entree5, bateau);
				bateauPlace = true; 
		}

		} while(bateauPlace == false);

		return LaCase;
	}
	/**
	* @param la grile de l'ordinateur
	* @return la grille de l'ordi avec un bateau place
	*/
	static Case PlacerPorteAv2(Case LaCase){
		
		boolean adjacent;
		boolean hasard1;
		boolean hasard2;
		boolean bateauPlace;
		
		String bateau;
		bateau = "porte_avion2";

		int transtypage;

		int case1y;
		int case2y;
		int case3y;
		int case4y;
		int case5y;
		
		char case1x;
		char case2x;
		char case3x;
		char case4x;
		char case5x;

		Coo Entree1 = new Coo();
		Coo Entree2 = new Coo();
		Coo Entree3 = new Coo();
		Coo Entree4 = new Coo();
		Coo Entree5 = new Coo();

		bateauPlace = false;
		do{
			hasard1 = ConditHasard();
			hasard2 = ConditHasard();
			case1x = CharHasard();
			case1y = EntierHasard();
			case2x = case1x;
			case3x = case1x;
			case4y = case1y;
			case4x = case1x;
			case2y = case1y;
			case3y = case1y;
			case5y = case1y;
			case5x = case1x;
							
			if ((hasard1 == true) && (hasard2 == true)){
				
					case2y = case1y + 1;
					case3y = case1y + 2;
					
			}
			
			else if ((hasard1 == true) && (hasard2 == false)){
					case2y = case1y - 1;
					case3y = case1y - 2;
					
			}
			else if((hasard1 == true) && (hasard2 == false)){
			
				transtypage = (int) case1x;
				transtypage ++;
				case2x = (char) transtypage;
				transtypage ++;
				case3x = (char) transtypage;
	
				
			}
			
			else{
				transtypage = (int) case1x;
				transtypage = transtypage - 1 ;
				case2x = (char) transtypage;
				transtypage = transtypage - 1 ;
				case3x = (char) transtypage;
				
				}
			
			if (case2y == 11){
				case2y = 9;
				case3y = 8;
				case4y = 7;
				case5y = 8;
				}
			if (case2x == '@'){
				case2x = 'B';
				case3x = 'C';
				case4x = 'D';
				case5x = 'E';		
				}
			
			if (case2x == 'K'){
				case2x = 'I';
				case3x = 'F';
				case4x = 'E';
				case5x = 'D';
				}

			Entree1.x = case1x;
			Entree1.y = case1y;
			Entree2.x = case2x;
			Entree2.y = case2y;
			Entree3.x = case3x;
			Entree3.y = case3y;	
	
			if ((((BateauOuPas(LaCase, Entree1) == false) && (BateauOuPas(LaCase, Entree2) == false)) && (BateauOuPas(LaCase, Entree3) == false))){	
			
				LaCase = Correspondance(LaCase, Entree1, bateau);
				LaCase = Correspondance(LaCase, Entree2, bateau);
				LaCase = Correspondance(LaCase, Entree3, bateau);
				
				bateauPlace = true; 
		}

		} while(bateauPlace == false);

		return LaCase;	
	}
	/**
	* @param la grile de l'ordinateur
	* @return la grille de l'ordi avec un bateau place
	*/
	static Case PlacerPorteAv3(Case LaCase){
		
		boolean adjacent;
		boolean hasard1;
		boolean hasard2;
		boolean bateauPlace;
		
		String bateau;
		bateau = "porte_avion3";

		int transtypage;

		int case1y;
		int case2y;
		int case3y;
		int case4y;
		int case5y;
		
		char case1x;
		char case2x;
		char case3x;
		char case4x;
		char case5x;

		Coo Entree1 = new Coo();
		Coo Entree2 = new Coo();
		Coo Entree3 = new Coo();
		Coo Entree4 = new Coo();
		Coo Entree5 = new Coo();

		bateauPlace = false;
		do{
			hasard1 = ConditHasard();
			hasard2 = ConditHasard();
			case1x = CharHasard();
			case1y = EntierHasard();
			case2x = case1x;
			case3x = case1x;
			case4y = case1y;
			case4x = case1x;
			case2y = case1y;
			case3y = case1y;
			case5y = case1y;
			case5x = case1x;
			
			if ((hasard1 == true) && (hasard2 == true)){
				
					case2y = case1y + 1;
					case3y = case1y + 2;		
					//debug Ecran.afficher("hasard1 est vrai et hasard2 vrai");
			}
			
			else if ((hasard1 == true) && (hasard2 == false)){
					case2y = case1y - 1;
					case3y = case1y - 2;	
					// debug Ecran.afficher("hasard1 est vrai et hasard2 faux");
			}
			else if((hasard1 == true) && (hasard2 == false)){
			
				transtypage = (int) case1x;
				transtypage ++;
				case2x = (char) transtypage;
				transtypage ++;
				case3x = (char) transtypage;
				// debug Ecran.afficher("hasard1 est faux et hasard2 vrai");
			}
			
			else{
				transtypage = (int) case1x;
				transtypage = transtypage - 1 ;
				case2x = (char) transtypage;
				transtypage = transtypage - 1 ;
				case3x = (char) transtypage;
				}
			
			if (case2y == 11){
				case2y = 9;
				case3y = 8;
				case4y = 7;
				case5y = 8;
				}
			if (case2x == '@'){
				case2x = 'B';
				case3x = 'C';
				case4x = 'D';
				case5x = 'E';		
				}
			
			if (case2x == 'K'){
				case2x = 'I';
				case3x = 'F';
				case4x = 'E';
				case5x = 'D';
				}

			Entree1.x = case1x;
			Entree1.y = case1y;
			Entree2.x = case2x;
			Entree2.y = case2y;
			Entree3.x = case3x;
			Entree3.y = case3y;
			

			//Ecran.afficher("PorteAv: 1 = ", case1x, case1y, " \n PorteAv: 2 = ", case2x, case2y, " \n PorteAv : 3 = ", case3x, case3y);
				
			if ((((BateauOuPas(LaCase, Entree1) == false) && (BateauOuPas(LaCase, Entree2) == false)) && (BateauOuPas(LaCase, Entree3) == false))){	
			
				LaCase = Correspondance(LaCase, Entree1, bateau);
				LaCase = Correspondance(LaCase, Entree2, bateau);
				LaCase = Correspondance(LaCase, Entree3, bateau);
				
				bateauPlace = true; 
		}

		} while(bateauPlace == false);

		return LaCase;
	}
	/**
	* Sert a verifier que une case soit adjacente a une autre
	* @param Une coordonne
	* @param Une autre coordonee
	* @return Vrai si les deux cases sont adjacentes, faux sinon.
	*/
	static boolean VerfAdj(Coo case1, Coo case2){
		
		//Declaration des variables

		boolean verifCondition;	
		int test1;
		int test2;
		int testa;
		int testb;
		int Condition1;
		int Condition2;

		Condition1 = 0;
		Condition2 = 0;
		
		verifCondition = false;
		test1 = case1.y;
		test2 = case2.y;
		
		testa = (int) case1.x;
		testb = (int) case2.x;

		if (((test1 - 1) == test2) || ((test1 + 1) == test2)){
			Condition1 = 1;
		}

		if (((testa - 1) == testb) || ((testa + 1) == testb)){
			Condition2 = 1;
		}

		verifCondition = ((Condition1 == 1) ^ (Condition2 == 1));
		return verifCondition;
	}	
	/**
	 * @param La case du joueur ennemi a afficher	
	 * @return Rien
	 */
	static void AffichageGrilleEnnemi(Case LaCaseAffiche){
		Ecran.afficher("\n| 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 |10 |  \n");
		Ecran.afficher("| A |");

		if ((LaCaseAffiche.A1.aimed == true) && (LaCaseAffiche.A1.boat == true)){ 
		 Ecran.afficher(" X |"); } 
		 else if (LaCaseAffiche.A1.aimed == true){ 
		 Ecran.afficher(" O |");} 
		 else 
		{Ecran.afficher(" * |");} 
		 
		if ((LaCaseAffiche.A2.aimed == true) && (LaCaseAffiche.A2.boat == true)){ 
		 Ecran.afficher(" X |"); } 
		 else if (LaCaseAffiche.A2.aimed == true){ 
		 Ecran.afficher(" O |");} 
		 else 
		{Ecran.afficher(" * |");} 
		 
		if ((LaCaseAffiche.A3.aimed == true) && (LaCaseAffiche.A3.boat == true)){ 
		 Ecran.afficher(" X |"); } 
		 else if (LaCaseAffiche.A3.aimed == true){ 
		 Ecran.afficher(" O |");} 
		 else 
		{Ecran.afficher(" * |");} 
		 
		if ((LaCaseAffiche.A4.aimed == true) && (LaCaseAffiche.A4.boat == true)){ 
		 Ecran.afficher(" X |"); } 
		 else if (LaCaseAffiche.A4.aimed == true){ 
		 Ecran.afficher(" O |");} 
		 else 
		{Ecran.afficher(" * |");} 
		 
		if ((LaCaseAffiche.A5.aimed == true) && (LaCaseAffiche.A5.boat == true)){ 
		 Ecran.afficher(" X |"); } 
		 else if (LaCaseAffiche.A5.aimed == true){ 
		 Ecran.afficher(" O |");} 
		 else 
		{Ecran.afficher(" * |");} 
		 
		if ((LaCaseAffiche.A6.aimed == true) && (LaCaseAffiche.A6.boat == true)){ 
		 Ecran.afficher(" X |"); } 
		 else if (LaCaseAffiche.A6.aimed == true){ 
		 Ecran.afficher(" O |");} 
		 else 
		{Ecran.afficher(" * |");} 
		 
		if ((LaCaseAffiche.A7.aimed == true) && (LaCaseAffiche.A7.boat == true)){ 
		 Ecran.afficher(" X |"); } 
		 else if (LaCaseAffiche.A7.aimed == true){ 
		 Ecran.afficher(" O |");} 
		 else 
		{Ecran.afficher(" * |");} 
		 
		if ((LaCaseAffiche.A8.aimed == true) && (LaCaseAffiche.A8.boat == true)){ 
		 Ecran.afficher(" X |"); } 
		 else if (LaCaseAffiche.A8.aimed == true){ 
		 Ecran.afficher(" O |");} 
		 else 
		{Ecran.afficher(" * |");} 
		 
		if ((LaCaseAffiche.A9.aimed == true) && (LaCaseAffiche.A9.boat == true)){ 
		 Ecran.afficher(" X |"); } 
		 else if (LaCaseAffiche.A9.aimed == true){ 
		 Ecran.afficher(" O |");} 
		 else 
		{Ecran.afficher(" * |");} 
		 
		if ((LaCaseAffiche.A10.aimed == true) && (LaCaseAffiche.A10.boat == true)){ 
		 Ecran.afficher(" X | \n"); } 
		 else if (LaCaseAffiche.A10.aimed == true){ 
		 Ecran.afficher(" O | \n");} 
		 else 
		{Ecran.afficher(" * | \n");}

		Ecran.afficher("| B |");
		 
		if ((LaCaseAffiche.B1.aimed == true) && (LaCaseAffiche.B1.boat == true)){ 
		 Ecran.afficher(" X |"); } 
		 else if (LaCaseAffiche.B1.aimed == true){ 
		 Ecran.afficher(" O |");} 
		 else 
		{Ecran.afficher(" * |");} 
		 
		if ((LaCaseAffiche.B2.aimed == true) && (LaCaseAffiche.B2.boat == true)){ 
		 Ecran.afficher(" X |"); } 
		 else if (LaCaseAffiche.B2.aimed == true){ 
		 Ecran.afficher(" O |");} 
		 else 
		{Ecran.afficher(" * |");} 
		 
		if ((LaCaseAffiche.B3.aimed == true) && (LaCaseAffiche.B3.boat == true)){ 
		 Ecran.afficher(" X |"); } 
		 else if (LaCaseAffiche.B3.aimed == true){ 
		 Ecran.afficher(" O |");} 
		 else 
		{Ecran.afficher(" * |");} 
		 
		if ((LaCaseAffiche.B4.aimed == true) && (LaCaseAffiche.B4.boat == true)){ 
		 Ecran.afficher(" X |"); } 
		 else if (LaCaseAffiche.B4.aimed == true){ 
		 Ecran.afficher(" O |");} 
		 else 
		{Ecran.afficher(" * |");} 
		 
		if ((LaCaseAffiche.B5.aimed == true) && (LaCaseAffiche.B5.boat == true)){ 
		 Ecran.afficher(" X |"); } 
		 else if (LaCaseAffiche.B5.aimed == true){ 
		 Ecran.afficher(" O |");} 
		 else 
		{Ecran.afficher(" * |");} 
		 
		if ((LaCaseAffiche.B6.aimed == true) && (LaCaseAffiche.B6.boat == true)){ 
		 Ecran.afficher(" X |"); } 
		 else if (LaCaseAffiche.B6.aimed == true){ 
		 Ecran.afficher(" O |");} 
		 else 
		{Ecran.afficher(" * |");} 
		 
		if ((LaCaseAffiche.B7.aimed == true) && (LaCaseAffiche.B7.boat == true)){ 
		 Ecran.afficher(" X |"); } 
		 else if (LaCaseAffiche.B7.aimed == true){ 
		 Ecran.afficher(" O |");} 
		 else 
		{Ecran.afficher(" * |");} 
		 
		if ((LaCaseAffiche.B8.aimed == true) && (LaCaseAffiche.B8.boat == true)){ 
		 Ecran.afficher(" X |"); } 
		 else if (LaCaseAffiche.B8.aimed == true){ 
		 Ecran.afficher(" O |");} 
		 else 
		{Ecran.afficher(" * |");} 
		 
		if ((LaCaseAffiche.B9.aimed == true) && (LaCaseAffiche.B9.boat == true)){ 
		 Ecran.afficher(" X |"); } 
		 else if (LaCaseAffiche.B9.aimed == true){ 
		 Ecran.afficher(" O |");} 
		 else 
		{Ecran.afficher(" * |");} 
		 
		if ((LaCaseAffiche.B10.aimed == true) && (LaCaseAffiche.B10.boat == true)){ 
		 Ecran.afficher(" X | \n"); } 
		 else if (LaCaseAffiche.B10.aimed == true){ 
		 Ecran.afficher(" O | \n");} 
		 else 
		{Ecran.afficher(" * | \n");}

		Ecran.afficher("| C |");
		 
		if ((LaCaseAffiche.C1.aimed == true) && (LaCaseAffiche.C1.boat == true)){ 
		 Ecran.afficher(" X |"); } 
		 else if (LaCaseAffiche.C1.aimed == true){ 
		 Ecran.afficher(" O |");} 
		 else 
		{Ecran.afficher(" * |");} 
		 
		if ((LaCaseAffiche.C2.aimed == true) && (LaCaseAffiche.C2.boat == true)){ 
		 Ecran.afficher(" X |"); } 
		 else if (LaCaseAffiche.C2.aimed == true){ 
		 Ecran.afficher(" O |");} 
		 else 
		{Ecran.afficher(" * |");} 
		 
		if ((LaCaseAffiche.C3.aimed == true) && (LaCaseAffiche.C3.boat == true)){ 
		 Ecran.afficher(" X |"); } 
		 else if (LaCaseAffiche.C3.aimed == true){ 
		 Ecran.afficher(" O |");} 
		 else 
		{Ecran.afficher(" * |");} 
		 
		if ((LaCaseAffiche.C4.aimed == true) && (LaCaseAffiche.C4.boat == true)){ 
		 Ecran.afficher(" X |"); } 
		 else if (LaCaseAffiche.C4.aimed == true){ 
		 Ecran.afficher(" O |");} 
		 else 
		{Ecran.afficher(" * |");} 
		 
		if ((LaCaseAffiche.C5.aimed == true) && (LaCaseAffiche.C5.boat == true)){ 
		 Ecran.afficher(" X |"); } 
		 else if (LaCaseAffiche.C5.aimed == true){ 
		 Ecran.afficher(" O |");} 
		 else 
		{Ecran.afficher(" * |");} 
		 
		if ((LaCaseAffiche.C6.aimed == true) && (LaCaseAffiche.C6.boat == true)){ 
		 Ecran.afficher(" X |"); } 
		 else if (LaCaseAffiche.C6.aimed == true){ 
		 Ecran.afficher(" O |");} 
		 else 
		{Ecran.afficher(" * |");} 
		 
		if ((LaCaseAffiche.C7.aimed == true) && (LaCaseAffiche.C7.boat == true)){ 
		 Ecran.afficher(" X |"); } 
		 else if (LaCaseAffiche.C7.aimed == true){ 
		 Ecran.afficher(" O |");} 
		 else 
		{Ecran.afficher(" * |");} 
		 
		if ((LaCaseAffiche.C8.aimed == true) && (LaCaseAffiche.C8.boat == true)){ 
		 Ecran.afficher(" X |"); } 
		 else if (LaCaseAffiche.C8.aimed == true){ 
		 Ecran.afficher(" O |");} 
		 else 
		{Ecran.afficher(" * |");} 
		 
		if ((LaCaseAffiche.C9.aimed == true) && (LaCaseAffiche.C9.boat == true)){ 
		 Ecran.afficher(" X |"); } 
		 else if (LaCaseAffiche.C9.aimed == true){ 
		 Ecran.afficher(" O |");} 
		 else 
		{Ecran.afficher(" * |");} 
		 
		if ((LaCaseAffiche.C10.aimed == true) && (LaCaseAffiche.C10.boat == true)){ 
		 Ecran.afficher(" X | \n"); } 
		 else if (LaCaseAffiche.C10.aimed == true){ 
		 Ecran.afficher(" O | \n");} 
		 else 
		{Ecran.afficher(" * | \n");} 

		Ecran.afficher("| D |");

		if ((LaCaseAffiche.D1.aimed == true) && (LaCaseAffiche.D1.boat == true)){ 
		 Ecran.afficher(" X |"); } 
		 else if (LaCaseAffiche.D1.aimed == true){ 
		 Ecran.afficher(" O |");} 
		 else 
		{Ecran.afficher(" * |");} 
		 
		if ((LaCaseAffiche.D2.aimed == true) && (LaCaseAffiche.D2.boat == true)){ 
		 Ecran.afficher(" X |"); } 
		 else if (LaCaseAffiche.D2.aimed == true){ 
		 Ecran.afficher(" O |");} 
		 else 
		{Ecran.afficher(" * |");} 
		 
		if ((LaCaseAffiche.D3.aimed == true) && (LaCaseAffiche.D3.boat == true)){ 
		 Ecran.afficher(" X |"); } 
		 else if (LaCaseAffiche.D3.aimed == true){ 
		 Ecran.afficher(" O |");} 
		 else 
		{Ecran.afficher(" * |");} 
		 
		if ((LaCaseAffiche.D4.aimed == true) && (LaCaseAffiche.D4.boat == true)){ 
		 Ecran.afficher(" X |"); } 
		 else if (LaCaseAffiche.D4.aimed == true){ 
		 Ecran.afficher(" O |");} 
		 else 
		{Ecran.afficher(" * |");} 
		 
		if ((LaCaseAffiche.D5.aimed == true) && (LaCaseAffiche.D5.boat == true)){ 
		 Ecran.afficher(" X |"); } 
		 else if (LaCaseAffiche.D5.aimed == true){ 
		 Ecran.afficher(" O |");} 
		 else 
		{Ecran.afficher(" * |");} 
		 
		if ((LaCaseAffiche.D6.aimed == true) && (LaCaseAffiche.D6.boat == true)){ 
		 Ecran.afficher(" X |"); } 
		 else if (LaCaseAffiche.D6.aimed == true){ 
		 Ecran.afficher(" O |");} 
		 else 
		{Ecran.afficher(" * |");} 
		 
		if ((LaCaseAffiche.D7.aimed == true) && (LaCaseAffiche.D7.boat == true)){ 
		 Ecran.afficher(" X |"); } 
		 else if (LaCaseAffiche.D7.aimed == true){ 
		 Ecran.afficher(" O |");} 
		 else 
		{Ecran.afficher(" * |");} 
		 
		if ((LaCaseAffiche.D8.aimed == true) && (LaCaseAffiche.D8.boat == true)){ 
		 Ecran.afficher(" X |"); } 
		 else if (LaCaseAffiche.D8.aimed == true){ 
		 Ecran.afficher(" O |");} 
		 else 
		{Ecran.afficher(" * |");} 
		 
		if ((LaCaseAffiche.D9.aimed == true) && (LaCaseAffiche.D9.boat == true)){ 
		 Ecran.afficher(" X |"); } 
		 else if (LaCaseAffiche.D9.aimed == true){ 
		 Ecran.afficher(" O |");} 
		 else 
		{Ecran.afficher(" * |");} 
		 
		if ((LaCaseAffiche.D10.aimed == true) && (LaCaseAffiche.D10.boat == true)){ 
		 Ecran.afficher(" X | \n"); } 
		 else if (LaCaseAffiche.D10.aimed == true){ 
		 Ecran.afficher(" O | \n");} 
		 else 
		{Ecran.afficher(" * | \n");} 

		Ecran.afficher("| E |");

		if ((LaCaseAffiche.E1.aimed == true) && (LaCaseAffiche.E1.boat == true)){ 
		 Ecran.afficher(" X |"); } 
		 else if (LaCaseAffiche.E1.aimed == true){ 
		 Ecran.afficher(" O |");} 
		 else 
		{Ecran.afficher(" * |");} 
		 
		if ((LaCaseAffiche.E2.aimed == true) && (LaCaseAffiche.E2.boat == true)){ 
		 Ecran.afficher(" X |"); } 
		 else if (LaCaseAffiche.E2.aimed == true){ 
		 Ecran.afficher(" O |");} 
		 else 
		{Ecran.afficher(" * |");} 
		 
		if ((LaCaseAffiche.E3.aimed == true) && (LaCaseAffiche.E3.boat == true)){ 
		 Ecran.afficher(" X |"); } 
		 else if (LaCaseAffiche.E3.aimed == true){ 
		 Ecran.afficher(" O |");} 
		 else 
		{Ecran.afficher(" * |");} 
		 
		if ((LaCaseAffiche.E4.aimed == true) && (LaCaseAffiche.E4.boat == true)){ 
		 Ecran.afficher(" X |"); } 
		 else if (LaCaseAffiche.E4.aimed == true){ 
		 Ecran.afficher(" O |");} 
		 else 
		{Ecran.afficher(" * |");} 
		 
		if ((LaCaseAffiche.E5.aimed == true) && (LaCaseAffiche.E5.boat == true)){ 
		 Ecran.afficher(" X |"); } 
		 else if (LaCaseAffiche.E5.aimed == true){ 
		 Ecran.afficher(" O |");} 
		 else 
		{Ecran.afficher(" * |");} 
		 
		if ((LaCaseAffiche.E6.aimed == true) && (LaCaseAffiche.E6.boat == true)){ 
		 Ecran.afficher(" X |"); } 
		 else if (LaCaseAffiche.E6.aimed == true){ 
		 Ecran.afficher(" O |");} 
		 else 
		{Ecran.afficher(" * |");} 
		 
		if ((LaCaseAffiche.E7.aimed == true) && (LaCaseAffiche.E7.boat == true)){ 
		 Ecran.afficher(" X |"); } 
		 else if (LaCaseAffiche.E7.aimed == true){ 
		 Ecran.afficher(" O |");} 
		 else 
		{Ecran.afficher(" * |");} 
		 
		if ((LaCaseAffiche.E8.aimed == true) && (LaCaseAffiche.E8.boat == true)){ 
		 Ecran.afficher(" X |"); } 
		 else if (LaCaseAffiche.E8.aimed == true){ 
		 Ecran.afficher(" O |");} 
		 else 
		{Ecran.afficher(" * |");} 
		 
		if ((LaCaseAffiche.E9.aimed == true) && (LaCaseAffiche.E9.boat == true)){ 
		 Ecran.afficher(" X |"); } 
		 else if (LaCaseAffiche.E9.aimed == true){ 
		 Ecran.afficher(" O |");} 
		 else 
		{Ecran.afficher(" * |");} 
		 
		if ((LaCaseAffiche.E10.aimed == true) && (LaCaseAffiche.E10.boat == true)){ 
		 Ecran.afficher(" X | \n"); } 
		 else if (LaCaseAffiche.E10.aimed == true){ 
		 Ecran.afficher(" O | \n");} 
		 else 
		{Ecran.afficher(" * | \n");} 

		Ecran.afficher("| F |");

		if ((LaCaseAffiche.F1.aimed == true) && (LaCaseAffiche.F1.boat == true)){ 
		 Ecran.afficher(" X |"); } 
		 else if (LaCaseAffiche.F1.aimed == true){ 
		 Ecran.afficher(" O |");} 
		 else 
		{Ecran.afficher(" * |");} 
		 
		if ((LaCaseAffiche.F2.aimed == true) && (LaCaseAffiche.F2.boat == true)){ 
		 Ecran.afficher(" X |"); } 
		 else if (LaCaseAffiche.F2.aimed == true){ 
		 Ecran.afficher(" O |");} 
		 else 
		{Ecran.afficher(" * |");} 
		 
		if ((LaCaseAffiche.F3.aimed == true) && (LaCaseAffiche.F3.boat == true)){ 
		 Ecran.afficher(" X |"); } 
		 else if (LaCaseAffiche.F3.aimed == true){ 
		 Ecran.afficher(" O |");} 
		 else 
		{Ecran.afficher(" * |");} 
		 
		if ((LaCaseAffiche.F4.aimed == true) && (LaCaseAffiche.F4.boat == true)){ 
		 Ecran.afficher(" X |"); } 
		 else if (LaCaseAffiche.F4.aimed == true){ 
		 Ecran.afficher(" O |");} 
		 else 
		{Ecran.afficher(" * |");} 
		 
		if ((LaCaseAffiche.F5.aimed == true) && (LaCaseAffiche.F5.boat == true)){ 
		 Ecran.afficher(" X |"); } 
		 else if (LaCaseAffiche.F5.aimed == true){ 
		 Ecran.afficher(" O |");} 
		 else 
		{Ecran.afficher(" * |");} 
		 
		if ((LaCaseAffiche.F6.aimed == true) && (LaCaseAffiche.F6.boat == true)){ 
		 Ecran.afficher(" X |"); } 
		 else if (LaCaseAffiche.F6.aimed == true){ 
		 Ecran.afficher(" O |");} 
		 else 
		{Ecran.afficher(" * |");} 
		 
		if ((LaCaseAffiche.F7.aimed == true) && (LaCaseAffiche.F7.boat == true)){ 
		 Ecran.afficher(" X |"); } 
		 else if (LaCaseAffiche.F7.aimed == true){ 
		 Ecran.afficher(" O |");} 
		 else 
		{Ecran.afficher(" * |");} 
		 
		if ((LaCaseAffiche.F8.aimed == true) && (LaCaseAffiche.F8.boat == true)){ 
		 Ecran.afficher(" X |"); } 
		 else if (LaCaseAffiche.F8.aimed == true){ 
		 Ecran.afficher(" O |");} 
		 else 
		{Ecran.afficher(" * |");} 
		 
		if ((LaCaseAffiche.F9.aimed == true) && (LaCaseAffiche.F9.boat == true)){ 
		 Ecran.afficher(" X |"); } 
		 else if (LaCaseAffiche.F9.aimed == true){ 
		 Ecran.afficher(" O |");} 
		 else 
		{Ecran.afficher(" * |");} 
		 
		if ((LaCaseAffiche.F10.aimed == true) && (LaCaseAffiche.F10.boat == true)){ 
		 Ecran.afficher(" X | \n"); } 
		 else if (LaCaseAffiche.F10.aimed == true){ 
		 Ecran.afficher(" O | \n");} 
		 else 
		{Ecran.afficher(" * | \n");} 

		Ecran.afficher("| G |");

		if ((LaCaseAffiche.G1.aimed == true) && (LaCaseAffiche.G1.boat == true)){ 
		 Ecran.afficher(" X |"); } 
		 else if (LaCaseAffiche.G1.aimed == true){ 
		 Ecran.afficher(" O |");} 
		 else 
		{Ecran.afficher(" * |");} 
		 
		if ((LaCaseAffiche.G2.aimed == true) && (LaCaseAffiche.G2.boat == true)){ 
		 Ecran.afficher(" X |"); } 
		 else if (LaCaseAffiche.G2.aimed == true){ 
		 Ecran.afficher(" O |");} 
		 else 
		{Ecran.afficher(" * |");} 
		 
		if ((LaCaseAffiche.G3.aimed == true) && (LaCaseAffiche.G3.boat == true)){ 
		 Ecran.afficher(" X |"); } 
		 else if (LaCaseAffiche.G3.aimed == true){ 
		 Ecran.afficher(" O |");} 
		 else 
		{Ecran.afficher(" * |");} 
		 
		if ((LaCaseAffiche.G4.aimed == true) && (LaCaseAffiche.G4.boat == true)){ 
		 Ecran.afficher(" X |"); } 
		 else if (LaCaseAffiche.G4.aimed == true){ 
		 Ecran.afficher(" O |");} 
		 else 
		{Ecran.afficher(" * |");} 
		 
		if ((LaCaseAffiche.G5.aimed == true) && (LaCaseAffiche.G5.boat == true)){ 
		 Ecran.afficher(" X |"); } 
		 else if (LaCaseAffiche.G5.aimed == true){ 
		 Ecran.afficher(" O |");} 
		 else 
		{Ecran.afficher(" * |");} 
		 
		if ((LaCaseAffiche.G6.aimed == true) && (LaCaseAffiche.G6.boat == true)){ 
		 Ecran.afficher(" X |"); } 
		 else if (LaCaseAffiche.G6.aimed == true){ 
		 Ecran.afficher(" O |");} 
		 else 
		{Ecran.afficher(" * |");} 
		 
		if ((LaCaseAffiche.G7.aimed == true) && (LaCaseAffiche.G7.boat == true)){ 
		 Ecran.afficher(" X |"); } 
		 else if (LaCaseAffiche.G7.aimed == true){ 
		 Ecran.afficher(" O |");} 
		 else 
		{Ecran.afficher(" * |");} 
		 
		if ((LaCaseAffiche.G8.aimed == true) && (LaCaseAffiche.G8.boat == true)){ 
		 Ecran.afficher(" X |"); } 
		 else if (LaCaseAffiche.G8.aimed == true){ 
		 Ecran.afficher(" O |");} 
		 else 
		{Ecran.afficher(" * |");} 
		 
		if ((LaCaseAffiche.G9.aimed == true) && (LaCaseAffiche.G9.boat == true)){ 
		 Ecran.afficher(" X |"); } 
		 else if (LaCaseAffiche.G9.aimed == true){ 
		 Ecran.afficher(" O |");} 
		 else 
		{Ecran.afficher(" * |");} 
		 
		if ((LaCaseAffiche.G10.aimed == true) && (LaCaseAffiche.G10.boat == true)){ 
		 Ecran.afficher(" X | \n"); } 
		 else if (LaCaseAffiche.G10.aimed == true){ 
		 Ecran.afficher(" O | \n");} 
		 else 
		{Ecran.afficher(" * | \n");} 

		Ecran.afficher("| H |");

		if ((LaCaseAffiche.H1.aimed == true) && (LaCaseAffiche.H1.boat == true)){ 
		 Ecran.afficher(" X |"); } 
		 else if (LaCaseAffiche.H1.aimed == true){ 
		 Ecran.afficher(" O |");} 
		 else 
		{Ecran.afficher(" * |");} 
		 
		if ((LaCaseAffiche.H2.aimed == true) && (LaCaseAffiche.H2.boat == true)){ 
		 Ecran.afficher(" X |"); } 
		 else if (LaCaseAffiche.H2.aimed == true){ 
		 Ecran.afficher(" O |");} 
		 else 
		{Ecran.afficher(" * |");} 
		 
		if ((LaCaseAffiche.H3.aimed == true) && (LaCaseAffiche.H3.boat == true)){ 
		 Ecran.afficher(" X |"); } 
		 else if (LaCaseAffiche.H3.aimed == true){ 
		 Ecran.afficher(" O |");} 
		 else 
		{Ecran.afficher(" * |");} 
		 
		if ((LaCaseAffiche.H4.aimed == true) && (LaCaseAffiche.H4.boat == true)){ 
		 Ecran.afficher(" X |"); } 
		 else if (LaCaseAffiche.H4.aimed == true){ 
		 Ecran.afficher(" O |");} 
		 else 
		{Ecran.afficher(" * |");} 
		 
		if ((LaCaseAffiche.H5.aimed == true) && (LaCaseAffiche.H5.boat == true)){ 
		 Ecran.afficher(" X |"); } 
		 else if (LaCaseAffiche.H5.aimed == true){ 
		 Ecran.afficher(" O |");} 
		 else 
		{Ecran.afficher(" * |");} 
		 
		if ((LaCaseAffiche.H6.aimed == true) && (LaCaseAffiche.H6.boat == true)){ 
		 Ecran.afficher(" X |"); } 
		 else if (LaCaseAffiche.H6.aimed == true){ 
		 Ecran.afficher(" O |");} 
		 else 
		{Ecran.afficher(" * |");} 
		 
		if ((LaCaseAffiche.H7.aimed == true) && (LaCaseAffiche.H7.boat == true)){ 
		 Ecran.afficher(" X |"); } 
		 else if (LaCaseAffiche.H7.aimed == true){ 
		 Ecran.afficher(" O |");} 
		 else 
		{Ecran.afficher(" * |");} 
		 
		if ((LaCaseAffiche.H8.aimed == true) && (LaCaseAffiche.H8.boat == true)){ 
		 Ecran.afficher(" X |"); } 
		 else if (LaCaseAffiche.H8.aimed == true){ 
		 Ecran.afficher(" O |");} 
		 else 
		{Ecran.afficher(" * |");} 
		 
		if ((LaCaseAffiche.H9.aimed == true) && (LaCaseAffiche.H9.boat == true)){ 
		 Ecran.afficher(" X |"); } 
		 else if (LaCaseAffiche.H9.aimed == true){ 
		 Ecran.afficher(" O |");} 
		 else 
		{Ecran.afficher(" * |");} 
		 
		if ((LaCaseAffiche.H10.aimed == true) && (LaCaseAffiche.H10.boat == true)){ 
		 Ecran.afficher(" X | \n"); } 
		 else if (LaCaseAffiche.H10.aimed == true){ 
		 Ecran.afficher(" O | \n");} 
		 else 
		{Ecran.afficher(" * | \n");} 

		Ecran.afficher("| I |");

		if ((LaCaseAffiche.I1.aimed == true) && (LaCaseAffiche.I1.boat == true)){ 
		 Ecran.afficher(" X |"); } 
		 else if (LaCaseAffiche.I1.aimed == true){ 
		 Ecran.afficher(" O |");} 
		 else 
		{Ecran.afficher(" * |");} 
		 
		if ((LaCaseAffiche.I2.aimed == true) && (LaCaseAffiche.I2.boat == true)){ 
		 Ecran.afficher(" X |"); } 
		 else if (LaCaseAffiche.I2.aimed == true){ 
		 Ecran.afficher(" O |");} 
		 else 
		{Ecran.afficher(" * |");} 
		 
		if ((LaCaseAffiche.I3.aimed == true) && (LaCaseAffiche.I3.boat == true)){ 
		 Ecran.afficher(" X |"); } 
		 else if (LaCaseAffiche.I3.aimed == true){ 
		 Ecran.afficher(" O |");} 
		 else 
		{Ecran.afficher(" * |");} 
		 
		if ((LaCaseAffiche.I4.aimed == true) && (LaCaseAffiche.I4.boat == true)){ 
		 Ecran.afficher(" X |"); } 
		 else if (LaCaseAffiche.I4.aimed == true){ 
		 Ecran.afficher(" O |");} 
		 else 
		{Ecran.afficher(" * |");} 
		 
		if ((LaCaseAffiche.I5.aimed == true) && (LaCaseAffiche.I5.boat == true)){ 
		 Ecran.afficher(" X |"); } 
		 else if (LaCaseAffiche.I5.aimed == true){ 
		 Ecran.afficher(" O |");} 
		 else 
		{Ecran.afficher(" * |");} 
		 
		if ((LaCaseAffiche.I6.aimed == true) && (LaCaseAffiche.I6.boat == true)){ 
		 Ecran.afficher(" X |"); } 
		 else if (LaCaseAffiche.I6.aimed == true){ 
		 Ecran.afficher(" O |");} 
		 else 
		{Ecran.afficher(" * |");} 
		 
		if ((LaCaseAffiche.I7.aimed == true) && (LaCaseAffiche.I7.boat == true)){ 
		 Ecran.afficher(" X |"); } 
		 else if (LaCaseAffiche.I7.aimed == true){ 
		 Ecran.afficher(" O |");} 
		 else 
		{Ecran.afficher(" * |");} 
		 
		if ((LaCaseAffiche.I8.aimed == true) && (LaCaseAffiche.I8.boat == true)){ 
		 Ecran.afficher(" X |"); } 
		 else if (LaCaseAffiche.I8.aimed == true){ 
		 Ecran.afficher(" O |");} 
		 else 
		{Ecran.afficher(" * |");} 
		 
		if ((LaCaseAffiche.I9.aimed == true) && (LaCaseAffiche.I9.boat == true)){ 
		 Ecran.afficher(" X |"); } 
		 else if (LaCaseAffiche.I9.aimed == true){ 
		 Ecran.afficher(" O |");} 
		 else 
		{Ecran.afficher(" * |");} 
		 
		if ((LaCaseAffiche.I10.aimed == true) && (LaCaseAffiche.I10.boat == true)){ 
		 Ecran.afficher(" X | \n"); } 
		 else if (LaCaseAffiche.I10.aimed == true){ 
		 Ecran.afficher(" O | \n");} 
		 else 
		{Ecran.afficher(" * | \n");} 

		Ecran.afficher("| J |"); 

		if ((LaCaseAffiche.J1.aimed == true) && (LaCaseAffiche.J1.boat == true)){ 
		 Ecran.afficher(" X |"); } 
		 else if (LaCaseAffiche.J1.aimed == true){ 
		 Ecran.afficher(" O |");} 
		 else 
		{Ecran.afficher(" * |");} 
		 
		if ((LaCaseAffiche.J2.aimed == true) && (LaCaseAffiche.J2.boat == true)){ 
		 Ecran.afficher(" X |"); } 
		 else if (LaCaseAffiche.J2.aimed == true){ 
		 Ecran.afficher(" O |");} 
		 else 
		{Ecran.afficher(" * |");} 
		 
		if ((LaCaseAffiche.J3.aimed == true) && (LaCaseAffiche.J3.boat == true)){ 
		 Ecran.afficher(" X |"); } 
		 else if (LaCaseAffiche.J3.aimed == true){ 
		 Ecran.afficher(" O |");} 
		 else 
		{Ecran.afficher(" * |");} 
		 
		if ((LaCaseAffiche.J4.aimed == true) && (LaCaseAffiche.J4.boat == true)){ 
		 Ecran.afficher(" X |"); } 
		 else if (LaCaseAffiche.J4.aimed == true){ 
		 Ecran.afficher(" O |");} 
		 else 
		{Ecran.afficher(" * |");} 
		 
		if ((LaCaseAffiche.J5.aimed == true) && (LaCaseAffiche.J5.boat == true)){ 
		 Ecran.afficher(" X |"); } 
		 else if (LaCaseAffiche.J5.aimed == true){ 
		 Ecran.afficher(" O |");} 
		 else 
		{Ecran.afficher(" * |");} 
		 
		if ((LaCaseAffiche.J6.aimed == true) && (LaCaseAffiche.J6.boat == true)){ 
		 Ecran.afficher(" X |"); } 
		 else if (LaCaseAffiche.J6.aimed == true){ 
		 Ecran.afficher(" O |");} 
		 else 
		{Ecran.afficher(" * |");} 
		 
		if ((LaCaseAffiche.J7.aimed == true) && (LaCaseAffiche.J7.boat == true)){ 
		 Ecran.afficher(" X |"); } 
		 else if (LaCaseAffiche.J7.aimed == true){ 
		 Ecran.afficher(" O |");} 
		 else 
		{Ecran.afficher(" * |");} 
		 
		if ((LaCaseAffiche.J8.aimed == true) && (LaCaseAffiche.J8.boat == true)){ 
		 Ecran.afficher(" X |"); } 
		 else if (LaCaseAffiche.J8.aimed == true){ 
		 Ecran.afficher(" O |");} 
		 else 
		{Ecran.afficher(" * |");} 
		 
		if ((LaCaseAffiche.J9.aimed == true) && (LaCaseAffiche.J9.boat == true)){ 
		 Ecran.afficher(" X |"); } 
		 else if (LaCaseAffiche.J9.aimed == true){ 
		 Ecran.afficher(" O |");} 
		 else 
		{Ecran.afficher(" * |");} 
		 
		if ((LaCaseAffiche.J10.aimed == true) && (LaCaseAffiche.J10.boat == true)){ 
		 Ecran.afficher(" X | \n"); } 
		 else if (LaCaseAffiche.J10.aimed == true){ 
		 Ecran.afficher(" O | \n");} 
		 else 
		{Ecran.afficher(" * | \n");} 
		 

				
		
		}	
	/**
	* Sert a verifier qu'il n'y ait aucun bateau sur la case demandee.
	* @param  La coordonee a tester
	* @return Faux si il n'y a aucun bateau sur la case (soit Coo.boat == false), true sinon
	*/
	static boolean VerifCoo(Coo TestBateau){
		boolean verifCondition;
		verifCondition = false;
		if (TestBateau.boat == true){
			verifCondition = true;
			Ecran.afficher("Sous algo VerifCoo : Il y a un bateau sur la case testee \n");
		}
		return verifCondition;
	}
	/**
	* Sert a trouver la coordonne correspondante et indique si il y a un bateau dessus
	* @param La grille de jeu voulu et la coordonne a tester
	* @return vrai si il y a un bateau, faux sinon
	*/
	static boolean BateauOuPas (Case Entree, Coo Atester){
		//Ne pas se fier au nom de la variable, ApasBateau prend la valeur faux si il ny a pas de bateau
		boolean ApasBateau;
		ApasBateau = false;
		
		if ((Atester.x == Entree.A1.x) && (Atester.y == Entree.A1.y)){ 
			if (Entree.A1.boat == true){ 
			   ApasBateau = true;}}  
		  
		 if ((Atester.x == Entree.A2.x) && (Atester.y == Entree.A2.y)){ 
			if (Entree.A2.boat == true){ 
			   ApasBateau = true;}}  
		  
		 if ((Atester.x == Entree.A3.x) && (Atester.y == Entree.A3.y)){ 
			if (Entree.A3.boat == true){ 
			   ApasBateau = true;}}  
		  
		 if ((Atester.x == Entree.A4.x) && (Atester.y == Entree.A4.y)){ 
			if (Entree.A4.boat == true){ 
			   ApasBateau = true;}}  
		  
		 if ((Atester.x == Entree.A5.x) && (Atester.y == Entree.A5.y)){ 
			if (Entree.A5.boat == true){ 
			   ApasBateau = true;}}  
		  
		 if ((Atester.x == Entree.A6.x) && (Atester.y == Entree.A6.y)){ 
			if (Entree.A6.boat == true){ 
			   ApasBateau = true;}}  
		  
		 if ((Atester.x == Entree.A7.x) && (Atester.y == Entree.A7.y)){ 
			if (Entree.A7.boat == true){ 
			   ApasBateau = true;}}  
		  
		 if ((Atester.x == Entree.A8.x) && (Atester.y == Entree.A8.y)){ 
			if (Entree.A8.boat == true){ 
			   ApasBateau = true;}}  
		  
		 if ((Atester.x == Entree.A9.x) && (Atester.y == Entree.A9.y)){ 
			if (Entree.A9.boat == true){ 
			   ApasBateau = true;}}  
		  
		 if ((Atester.x == Entree.A10.x) && (Atester.y == Entree.A10.y)){ 
			if (Entree.A10.boat == true){ 
			   ApasBateau = true;}}  
		  
		 if ((Atester.x == Entree.B1.x) && (Atester.y == Entree.B1.y)){ 
			if (Entree.B1.boat == true){ 
			   ApasBateau = true;}}  
		  
		 if ((Atester.x == Entree.B2.x) && (Atester.y == Entree.B2.y)){ 
			if (Entree.B2.boat == true){ 
			   ApasBateau = true;}}  
		  
		 if ((Atester.x == Entree.B3.x) && (Atester.y == Entree.B3.y)){ 
			if (Entree.B3.boat == true){ 
			   ApasBateau = true;}}  
		  
		 if ((Atester.x == Entree.B4.x) && (Atester.y == Entree.B4.y)){ 
			if (Entree.B4.boat == true){ 
			   ApasBateau = true;}}  
		  
		 if ((Atester.x == Entree.B5.x) && (Atester.y == Entree.B5.y)){ 
			if (Entree.B5.boat == true){ 
			   ApasBateau = true;}}  
		  
		 if ((Atester.x == Entree.B6.x) && (Atester.y == Entree.B6.y)){ 
			if (Entree.B6.boat == true){ 
			   ApasBateau = true;}}  
		  
		 if ((Atester.x == Entree.B7.x) && (Atester.y == Entree.B7.y)){ 
			if (Entree.B7.boat == true){ 
			   ApasBateau = true;}}  
		  
		 if ((Atester.x == Entree.B8.x) && (Atester.y == Entree.B8.y)){ 
			if (Entree.B8.boat == true){ 
			   ApasBateau = true;}}  
		  
		 if ((Atester.x == Entree.B9.x) && (Atester.y == Entree.B9.y)){ 
			if (Entree.B9.boat == true){ 
			   ApasBateau = true;}}  
		  
		 if ((Atester.x == Entree.B10.x) && (Atester.y == Entree.B10.y)){ 
			if (Entree.B10.boat == true){ 
			   ApasBateau = true;}}  
		  
		 if ((Atester.x == Entree.C1.x) && (Atester.y == Entree.C1.y)){ 
			if (Entree.C1.boat == true){ 
			   ApasBateau = true;}}  
		  
		 if ((Atester.x == Entree.C2.x) && (Atester.y == Entree.C2.y)){ 
			if (Entree.C2.boat == true){ 
			   ApasBateau = true;}}  
		  
		 if ((Atester.x == Entree.C3.x) && (Atester.y == Entree.C3.y)){ 
			if (Entree.C3.boat == true){ 
			   ApasBateau = true;}}  
		  
		 if ((Atester.x == Entree.C4.x) && (Atester.y == Entree.C4.y)){ 
			if (Entree.C4.boat == true){ 
			   ApasBateau = true;}}  
		  
		 if ((Atester.x == Entree.C5.x) && (Atester.y == Entree.C5.y)){ 
			if (Entree.C5.boat == true){ 
			   ApasBateau = true;}}  
		  
		 if ((Atester.x == Entree.C6.x) && (Atester.y == Entree.C6.y)){ 
			if (Entree.C6.boat == true){ 
			   ApasBateau = true;}}  
		  
		 if ((Atester.x == Entree.C7.x) && (Atester.y == Entree.C7.y)){ 
			if (Entree.C7.boat == true){ 
			   ApasBateau = true;}}  
		  
		 if ((Atester.x == Entree.C8.x) && (Atester.y == Entree.C8.y)){ 
			if (Entree.C8.boat == true){ 
			   ApasBateau = true;}}  
		  
		 if ((Atester.x == Entree.C9.x) && (Atester.y == Entree.C9.y)){ 
			if (Entree.C9.boat == true){ 
			   ApasBateau = true;}}  
		  
		 if ((Atester.x == Entree.C10.x) && (Atester.y == Entree.C10.y)){ 
			if (Entree.C10.boat == true){ 
			   ApasBateau = true;}}  
		  
		 if ((Atester.x == Entree.D1.x) && (Atester.y == Entree.D1.y)){ 
			if (Entree.D1.boat == true){ 
			   ApasBateau = true;}}  
		  
		 if ((Atester.x == Entree.D2.x) && (Atester.y == Entree.D2.y)){ 
			if (Entree.D2.boat == true){ 
			   ApasBateau = true;}}  
		  
		 if ((Atester.x == Entree.D3.x) && (Atester.y == Entree.D3.y)){ 
			if (Entree.D3.boat == true){ 
			   ApasBateau = true;}}  
		  
		 if ((Atester.x == Entree.D4.x) && (Atester.y == Entree.D4.y)){ 
			if (Entree.D4.boat == true){ 
			   ApasBateau = true;}}  
		  
		 if ((Atester.x == Entree.D5.x) && (Atester.y == Entree.D5.y)){ 
			if (Entree.D5.boat == true){ 
			   ApasBateau = true;}}  
		  
		 if ((Atester.x == Entree.D6.x) && (Atester.y == Entree.D6.y)){ 
			if (Entree.D6.boat == true){ 
			   ApasBateau = true;}}  
		  
		 if ((Atester.x == Entree.D7.x) && (Atester.y == Entree.D7.y)){ 
			if (Entree.D7.boat == true){ 
			   ApasBateau = true;}}  
		  
		 if ((Atester.x == Entree.D8.x) && (Atester.y == Entree.D8.y)){ 
			if (Entree.D8.boat == true){ 
			   ApasBateau = true;}}  
		  
		 if ((Atester.x == Entree.D9.x) && (Atester.y == Entree.D9.y)){ 
			if (Entree.D9.boat == true){ 
			   ApasBateau = true;}}  
		  
		 if ((Atester.x == Entree.D10.x) && (Atester.y == Entree.D10.y)){ 
			if (Entree.D10.boat == true){ 
			   ApasBateau = true;}}  
		  
		 if ((Atester.x == Entree.E1.x) && (Atester.y == Entree.E1.y)){ 
			if (Entree.E1.boat == true){ 
			   ApasBateau = true;}}  
		  
		 if ((Atester.x == Entree.E2.x) && (Atester.y == Entree.E2.y)){ 
			if (Entree.E2.boat == true){ 
			   ApasBateau = true;}}  
		  
		 if ((Atester.x == Entree.E3.x) && (Atester.y == Entree.E3.y)){ 
			if (Entree.E3.boat == true){ 
			   ApasBateau = true;}}  
		  
		 if ((Atester.x == Entree.E4.x) && (Atester.y == Entree.E4.y)){ 
			if (Entree.E4.boat == true){ 
			   ApasBateau = true;}}  
		  
		 if ((Atester.x == Entree.E5.x) && (Atester.y == Entree.E5.y)){ 
			if (Entree.E5.boat == true){ 
			   ApasBateau = true;}}  
		  
		 if ((Atester.x == Entree.E6.x) && (Atester.y == Entree.E6.y)){ 
			if (Entree.E6.boat == true){ 
			   ApasBateau = true;}}  
		  
		 if ((Atester.x == Entree.E7.x) && (Atester.y == Entree.E7.y)){ 
			if (Entree.E7.boat == true){ 
			   ApasBateau = true;}}  
		  
		 if ((Atester.x == Entree.E8.x) && (Atester.y == Entree.E8.y)){ 
			if (Entree.E8.boat == true){ 
			   ApasBateau = true;}}  
		  
		 if ((Atester.x == Entree.E9.x) && (Atester.y == Entree.E9.y)){ 
			if (Entree.E9.boat == true){ 
			   ApasBateau = true;}}  
		  
		 if ((Atester.x == Entree.E10.x) && (Atester.y == Entree.E10.y)){ 
			if (Entree.E10.boat == true){ 
			   ApasBateau = true;}}  
		  
		 if ((Atester.x == Entree.F1.x) && (Atester.y == Entree.F1.y)){ 
			if (Entree.F1.boat == true){ 
			   ApasBateau = true;}}  
		  
		 if ((Atester.x == Entree.F2.x) && (Atester.y == Entree.F2.y)){ 
			if (Entree.F2.boat == true){ 
			   ApasBateau = true;}}  
		  
		 if ((Atester.x == Entree.F3.x) && (Atester.y == Entree.F3.y)){ 
			if (Entree.F3.boat == true){ 
			   ApasBateau = true;}}  
		  
		 if ((Atester.x == Entree.F4.x) && (Atester.y == Entree.F4.y)){ 
			if (Entree.F4.boat == true){ 
			   ApasBateau = true;}}  
		  
		 if ((Atester.x == Entree.F5.x) && (Atester.y == Entree.F5.y)){ 
			if (Entree.F5.boat == true){ 
			   ApasBateau = true;}}  
		  
		 if ((Atester.x == Entree.F6.x) && (Atester.y == Entree.F6.y)){ 
			if (Entree.F6.boat == true){ 
			   ApasBateau = true;}}  
		  
		 if ((Atester.x == Entree.F7.x) && (Atester.y == Entree.F7.y)){ 
			if (Entree.F7.boat == true){ 
			   ApasBateau = true;}}  
		  
		 if ((Atester.x == Entree.F8.x) && (Atester.y == Entree.F8.y)){ 
			if (Entree.F8.boat == true){ 
			   ApasBateau = true;}}  
		  
		 if ((Atester.x == Entree.F9.x) && (Atester.y == Entree.F9.y)){ 
			if (Entree.F9.boat == true){ 
			   ApasBateau = true;}}  
		  
		 if ((Atester.x == Entree.F10.x) && (Atester.y == Entree.F10.y)){ 
			if (Entree.F10.boat == true){ 
			   ApasBateau = true;}}  
		  
		 if ((Atester.x == Entree.G1.x) && (Atester.y == Entree.G1.y)){ 
			if (Entree.G1.boat == true){ 
			   ApasBateau = true;}}  
		  
		 if ((Atester.x == Entree.G2.x) && (Atester.y == Entree.G2.y)){ 
			if (Entree.G2.boat == true){ 
			   ApasBateau = true;}}  
		  
		 if ((Atester.x == Entree.G3.x) && (Atester.y == Entree.G3.y)){ 
			if (Entree.G3.boat == true){ 
			   ApasBateau = true;}}  
		  
		 if ((Atester.x == Entree.G4.x) && (Atester.y == Entree.G4.y)){ 
			if (Entree.G4.boat == true){ 
			   ApasBateau = true;}}  
		  
		 if ((Atester.x == Entree.G5.x) && (Atester.y == Entree.G5.y)){ 
			if (Entree.G5.boat == true){ 
			   ApasBateau = true;}}  
		  
		 if ((Atester.x == Entree.G6.x) && (Atester.y == Entree.G6.y)){ 
			if (Entree.G6.boat == true){ 
			   ApasBateau = true;}}  
		  
		 if ((Atester.x == Entree.G7.x) && (Atester.y == Entree.G7.y)){ 
			if (Entree.G7.boat == true){ 
			   ApasBateau = true;}}  
		  
		 if ((Atester.x == Entree.G8.x) && (Atester.y == Entree.G8.y)){ 
			if (Entree.G8.boat == true){ 
			   ApasBateau = true;}}  
		  
		 if ((Atester.x == Entree.G9.x) && (Atester.y == Entree.G9.y)){ 
			if (Entree.G9.boat == true){ 
			   ApasBateau = true;}}  
		  
		 if ((Atester.x == Entree.G10.x) && (Atester.y == Entree.G10.y)){ 
			if (Entree.G10.boat == true){ 
			   ApasBateau = true;}}  
		  
		 if ((Atester.x == Entree.H1.x) && (Atester.y == Entree.H1.y)){ 
			if (Entree.H1.boat == true){ 
			   ApasBateau = true;}}  
		  
		 if ((Atester.x == Entree.H2.x) && (Atester.y == Entree.H2.y)){ 
			if (Entree.H2.boat == true){ 
			   ApasBateau = true;}}  
		  
		 if ((Atester.x == Entree.H3.x) && (Atester.y == Entree.H3.y)){ 
			if (Entree.H3.boat == true){ 
			   ApasBateau = true;}}  
		  
		 if ((Atester.x == Entree.H4.x) && (Atester.y == Entree.H4.y)){ 
			if (Entree.H4.boat == true){ 
			   ApasBateau = true;}}  
		  
		 if ((Atester.x == Entree.H5.x) && (Atester.y == Entree.H5.y)){ 
			if (Entree.H5.boat == true){ 
			   ApasBateau = true;}}  
		  
		 if ((Atester.x == Entree.H6.x) && (Atester.y == Entree.H6.y)){ 
			if (Entree.H6.boat == true){ 
			   ApasBateau = true;}}  
		  
		 if ((Atester.x == Entree.H7.x) && (Atester.y == Entree.H7.y)){ 
			if (Entree.H7.boat == true){ 
			   ApasBateau = true;}}  
		  
		 if ((Atester.x == Entree.H8.x) && (Atester.y == Entree.H8.y)){ 
			if (Entree.H8.boat == true){ 
			   ApasBateau = true;}}  
		  
		 if ((Atester.x == Entree.H9.x) && (Atester.y == Entree.H9.y)){ 
			if (Entree.H9.boat == true){ 
			   ApasBateau = true;}}  
		  
		 if ((Atester.x == Entree.H10.x) && (Atester.y == Entree.H10.y)){ 
			if (Entree.H10.boat == true){ 
			   ApasBateau = true;}}  
		  
		 if ((Atester.x == Entree.I1.x) && (Atester.y == Entree.I1.y)){ 
			if (Entree.I1.boat == true){ 
			   ApasBateau = true;}}  
		  
		 if ((Atester.x == Entree.I2.x) && (Atester.y == Entree.I2.y)){ 
			if (Entree.I2.boat == true){ 
			   ApasBateau = true;}}  
		  
		 if ((Atester.x == Entree.I3.x) && (Atester.y == Entree.I3.y)){ 
			if (Entree.I3.boat == true){ 
			   ApasBateau = true;}}  
		  
		 if ((Atester.x == Entree.I4.x) && (Atester.y == Entree.I4.y)){ 
			if (Entree.I4.boat == true){ 
			   ApasBateau = true;}}  
		  
		 if ((Atester.x == Entree.I5.x) && (Atester.y == Entree.I5.y)){ 
			if (Entree.I5.boat == true){ 
			   ApasBateau = true;}}  
		  
		 if ((Atester.x == Entree.I6.x) && (Atester.y == Entree.I6.y)){ 
			if (Entree.I6.boat == true){ 
			   ApasBateau = true;}}  
		  
		 if ((Atester.x == Entree.I7.x) && (Atester.y == Entree.I7.y)){ 
			if (Entree.I7.boat == true){ 
			   ApasBateau = true;}}  
		  
		 if ((Atester.x == Entree.I8.x) && (Atester.y == Entree.I8.y)){ 
			if (Entree.I8.boat == true){ 
			   ApasBateau = true;}}  
		  
		 if ((Atester.x == Entree.I9.x) && (Atester.y == Entree.I9.y)){ 
			if (Entree.I9.boat == true){ 
			   ApasBateau = true;}}  
		  
		 if ((Atester.x == Entree.I10.x) && (Atester.y == Entree.I10.y)){ 
			if (Entree.I10.boat == true){ 
			   ApasBateau = true;}}  
		  
		 if ((Atester.x == Entree.J1.x) && (Atester.y == Entree.J1.y)){ 
			if (Entree.J1.boat == true){ 
			   ApasBateau = true;}}  
		  
		 if ((Atester.x == Entree.J2.x) && (Atester.y == Entree.J2.y)){ 
			if (Entree.J2.boat == true){ 
			   ApasBateau = true;}}  
		  
		 if ((Atester.x == Entree.J3.x) && (Atester.y == Entree.J3.y)){ 
			if (Entree.J3.boat == true){ 
			   ApasBateau = true;}}  
		  
		 if ((Atester.x == Entree.J4.x) && (Atester.y == Entree.J4.y)){ 
			if (Entree.J4.boat == true){ 
			   ApasBateau = true;}}  
		  
		 if ((Atester.x == Entree.J5.x) && (Atester.y == Entree.J5.y)){ 
			if (Entree.J5.boat == true){ 
			   ApasBateau = true;}}  
		  
		 if ((Atester.x == Entree.J6.x) && (Atester.y == Entree.J6.y)){ 
			if (Entree.J6.boat == true){ 
			   ApasBateau = true;}}  
		  
		 if ((Atester.x == Entree.J7.x) && (Atester.y == Entree.J7.y)){ 
			if (Entree.J7.boat == true){ 
			   ApasBateau = true;}}  
		  
		 if ((Atester.x == Entree.J8.x) && (Atester.y == Entree.J8.y)){ 
			if (Entree.J8.boat == true){ 
			   ApasBateau = true;}}  
		  
		 if ((Atester.x == Entree.J9.x) && (Atester.y == Entree.J9.y)){ 
			if (Entree.J9.boat == true){ 
			   ApasBateau = true;}}  
		  
		 if ((Atester.x == Entree.J10.x) && (Atester.y == Entree.J10.y)){ 
			if (Entree.J10.boat == true){ 
			   ApasBateau = true;}}  
		     
		return ApasBateau;
	} 
	/**
	* @param La grille de jeu sur lequel est la coordonee,
	* @param La coordonee entree
	* @return Vrai si la case visee a deja ete visee, faux sinon
	*/
	static boolean BateauEstIlVise (Case Entree, Coo Atester){
		boolean CeBateauEstVise;
		CeBateauEstVise = false;

		if ((Atester.x == Entree.A1.x) && (Atester.y == Entree.A1.y)){ 
			if ((Entree.A1.aimed) == true){ 
			   CeBateauEstVise = true;}}  
		  
		 if ((Atester.x == Entree.A2.x) && (Atester.y == Entree.A2.y)){ 
			if ((Entree.A2.aimed) == true){ 
			   CeBateauEstVise = true;}}  
		  
		 if ((Atester.x == Entree.A3.x) && (Atester.y == Entree.A3.y)){ 
			if ((Entree.A3.aimed) == true){ 
			   CeBateauEstVise = true;}}  
		  
		 if ((Atester.x == Entree.A4.x) && (Atester.y == Entree.A4.y)){ 
			if ((Entree.A4.aimed) == true){ 
			   CeBateauEstVise = true;}}  
		  
		 if ((Atester.x == Entree.A5.x) && (Atester.y == Entree.A5.y)){ 
			if ((Entree.A5.aimed) == true){ 
			   CeBateauEstVise = true;}}  
		  
		 if ((Atester.x == Entree.A6.x) && (Atester.y == Entree.A6.y)){ 
			if ((Entree.A6.aimed) == true){ 
			   CeBateauEstVise = true;}}  
		  
		 if ((Atester.x == Entree.A7.x) && (Atester.y == Entree.A7.y)){ 
			if ((Entree.A7.aimed) == true){ 
			   CeBateauEstVise = true;}}  
		  
		 if ((Atester.x == Entree.A8.x) && (Atester.y == Entree.A8.y)){ 
			if ((Entree.A8.aimed) == true){ 
			   CeBateauEstVise = true;}}  
		  
		 if ((Atester.x == Entree.A9.x) && (Atester.y == Entree.A9.y)){ 
			if ((Entree.A9.aimed) == true){ 
			   CeBateauEstVise = true;}}  
		  
		 if ((Atester.x == Entree.A10.x) && (Atester.y == Entree.A10.y)){ 
			if ((Entree.A10.aimed) == true){ 
			   CeBateauEstVise = true;}}  
		  
		 if ((Atester.x == Entree.B1.x) && (Atester.y == Entree.B1.y)){ 
			if ((Entree.B1.aimed) == true){ 
			   CeBateauEstVise = true;}}  
		  
		 if ((Atester.x == Entree.B2.x) && (Atester.y == Entree.B2.y)){ 
			if ((Entree.B2.aimed) == true){ 
			   CeBateauEstVise = true;}}  
		  
		 if ((Atester.x == Entree.B3.x) && (Atester.y == Entree.B3.y)){ 
			if ((Entree.B3.aimed) == true){ 
			   CeBateauEstVise = true;}}  
		  
		 if ((Atester.x == Entree.B4.x) && (Atester.y == Entree.B4.y)){ 
			if ((Entree.B4.aimed) == true){ 
			   CeBateauEstVise = true;}}  
		  
		 if ((Atester.x == Entree.B5.x) && (Atester.y == Entree.B5.y)){ 
			if ((Entree.B5.aimed) == true){ 
			   CeBateauEstVise = true;}}  
		  
		 if ((Atester.x == Entree.B6.x) && (Atester.y == Entree.B6.y)){ 
			if ((Entree.B6.aimed) == true){ 
			   CeBateauEstVise = true;}}  
		  
		 if ((Atester.x == Entree.B7.x) && (Atester.y == Entree.B7.y)){ 
			if ((Entree.B7.aimed) == true){ 
			   CeBateauEstVise = true;}}  
		  
		 if ((Atester.x == Entree.B8.x) && (Atester.y == Entree.B8.y)){ 
			if ((Entree.B8.aimed) == true){ 
			   CeBateauEstVise = true;}}  
		  
		 if ((Atester.x == Entree.B9.x) && (Atester.y == Entree.B9.y)){ 
			if ((Entree.B9.aimed) == true){ 
			   CeBateauEstVise = true;}}  
		  
		 if ((Atester.x == Entree.B10.x) && (Atester.y == Entree.B10.y)){ 
			if ((Entree.B10.aimed) == true){ 
			   CeBateauEstVise = true;}}  
		  
		 if ((Atester.x == Entree.C1.x) && (Atester.y == Entree.C1.y)){ 
			if ((Entree.C1.aimed) == true){ 
			   CeBateauEstVise = true;}}  
		  
		 if ((Atester.x == Entree.C2.x) && (Atester.y == Entree.C2.y)){ 
			if ((Entree.C2.aimed) == true){ 
			   CeBateauEstVise = true;}}  
		  
		 if ((Atester.x == Entree.C3.x) && (Atester.y == Entree.C3.y)){ 
			if ((Entree.C3.aimed) == true){ 
			   CeBateauEstVise = true;}}  
		  
		 if ((Atester.x == Entree.C4.x) && (Atester.y == Entree.C4.y)){ 
			if ((Entree.C4.aimed) == true){ 
			   CeBateauEstVise = true;}}  
		  
		 if ((Atester.x == Entree.C5.x) && (Atester.y == Entree.C5.y)){ 
			if ((Entree.C5.aimed) == true){ 
			   CeBateauEstVise = true;}}  
		  
		 if ((Atester.x == Entree.C6.x) && (Atester.y == Entree.C6.y)){ 
			if ((Entree.C6.aimed) == true){ 
			   CeBateauEstVise = true;}}  
		  
		 if ((Atester.x == Entree.C7.x) && (Atester.y == Entree.C7.y)){ 
			if ((Entree.C7.aimed) == true){ 
			   CeBateauEstVise = true;}}  
		  
		 if ((Atester.x == Entree.C8.x) && (Atester.y == Entree.C8.y)){ 
			if ((Entree.C8.aimed) == true){ 
			   CeBateauEstVise = true;}}  
		  
		 if ((Atester.x == Entree.C9.x) && (Atester.y == Entree.C9.y)){ 
			if ((Entree.C9.aimed) == true){ 
			   CeBateauEstVise = true;}}  
		  
		 if ((Atester.x == Entree.C10.x) && (Atester.y == Entree.C10.y)){ 
			if ((Entree.C10.aimed) == true){ 
			   CeBateauEstVise = true;}}  
		  
		 if ((Atester.x == Entree.D1.x) && (Atester.y == Entree.D1.y)){ 
			if ((Entree.D1.aimed) == true){ 
			   CeBateauEstVise = true;}}  
		  
		 if ((Atester.x == Entree.D2.x) && (Atester.y == Entree.D2.y)){ 
			if ((Entree.D2.aimed) == true){ 
			   CeBateauEstVise = true;}}  
		  
		 if ((Atester.x == Entree.D3.x) && (Atester.y == Entree.D3.y)){ 
			if ((Entree.D3.aimed) == true){ 
			   CeBateauEstVise = true;}}  
		  
		 if ((Atester.x == Entree.D4.x) && (Atester.y == Entree.D4.y)){ 
			if ((Entree.D4.aimed) == true){ 
			   CeBateauEstVise = true;}}  
		  
		 if ((Atester.x == Entree.D5.x) && (Atester.y == Entree.D5.y)){ 
			if ((Entree.D5.aimed) == true){ 
			   CeBateauEstVise = true;}}  
		  
		 if ((Atester.x == Entree.D6.x) && (Atester.y == Entree.D6.y)){ 
			if ((Entree.D6.aimed) == true){ 
			   CeBateauEstVise = true;}}  
		  
		 if ((Atester.x == Entree.D7.x) && (Atester.y == Entree.D7.y)){ 
			if ((Entree.D7.aimed) == true){ 
			   CeBateauEstVise = true;}}  
		  
		 if ((Atester.x == Entree.D8.x) && (Atester.y == Entree.D8.y)){ 
			if ((Entree.D8.aimed) == true){ 
			   CeBateauEstVise = true;}}  
		  
		 if ((Atester.x == Entree.D9.x) && (Atester.y == Entree.D9.y)){ 
			if ((Entree.D9.aimed) == true){ 
			   CeBateauEstVise = true;}}  
		  
		 if ((Atester.x == Entree.D10.x) && (Atester.y == Entree.D10.y)){ 
			if ((Entree.D10.aimed) == true){ 
			   CeBateauEstVise = true;}}  
		  
		 if ((Atester.x == Entree.E1.x) && (Atester.y == Entree.E1.y)){ 
			if ((Entree.E1.aimed) == true){ 
			   CeBateauEstVise = true;}}  
		  
		 if ((Atester.x == Entree.E2.x) && (Atester.y == Entree.E2.y)){ 
			if ((Entree.E2.aimed) == true){ 
			   CeBateauEstVise = true;}}  
		  
		 if ((Atester.x == Entree.E3.x) && (Atester.y == Entree.E3.y)){ 
			if ((Entree.E3.aimed) == true){ 
			   CeBateauEstVise = true;}}  
		  
		 if ((Atester.x == Entree.E4.x) && (Atester.y == Entree.E4.y)){ 
			if ((Entree.E4.aimed) == true){ 
			   CeBateauEstVise = true;}}  
		  
		 if ((Atester.x == Entree.E5.x) && (Atester.y == Entree.E5.y)){ 
			if ((Entree.E5.aimed) == true){ 
			   CeBateauEstVise = true;}}  
		  
		 if ((Atester.x == Entree.E6.x) && (Atester.y == Entree.E6.y)){ 
			if ((Entree.E6.aimed) == true){ 
			   CeBateauEstVise = true;}}  
		  
		 if ((Atester.x == Entree.E7.x) && (Atester.y == Entree.E7.y)){ 
			if ((Entree.E7.aimed) == true){ 
			   CeBateauEstVise = true;}}  
		  
		 if ((Atester.x == Entree.E8.x) && (Atester.y == Entree.E8.y)){ 
			if ((Entree.E8.aimed) == true){ 
			   CeBateauEstVise = true;}}  
		  
		 if ((Atester.x == Entree.E9.x) && (Atester.y == Entree.E9.y)){ 
			if ((Entree.E9.aimed) == true){ 
			   CeBateauEstVise = true;}}  
		  
		 if ((Atester.x == Entree.E10.x) && (Atester.y == Entree.E10.y)){ 
			if ((Entree.E10.aimed) == true){ 
			   CeBateauEstVise = true;}}  
		  
		 if ((Atester.x == Entree.F1.x) && (Atester.y == Entree.F1.y)){ 
			if ((Entree.F1.aimed) == true){ 
			   CeBateauEstVise = true;}}  
		  
		 if ((Atester.x == Entree.F2.x) && (Atester.y == Entree.F2.y)){ 
			if ((Entree.F2.aimed) == true){ 
			   CeBateauEstVise = true;}}  
		  
		 if ((Atester.x == Entree.F3.x) && (Atester.y == Entree.F3.y)){ 
			if ((Entree.F3.aimed) == true){ 
			   CeBateauEstVise = true;}}  
		  
		 if ((Atester.x == Entree.F4.x) && (Atester.y == Entree.F4.y)){ 
			if ((Entree.F4.aimed) == true){ 
			   CeBateauEstVise = true;}}  
		  
		 if ((Atester.x == Entree.F5.x) && (Atester.y == Entree.F5.y)){ 
			if ((Entree.F5.aimed) == true){ 
			   CeBateauEstVise = true;}}  
		  
		 if ((Atester.x == Entree.F6.x) && (Atester.y == Entree.F6.y)){ 
			if ((Entree.F6.aimed) == true){ 
			   CeBateauEstVise = true;}}  
		  
		 if ((Atester.x == Entree.F7.x) && (Atester.y == Entree.F7.y)){ 
			if ((Entree.F7.aimed) == true){ 
			   CeBateauEstVise = true;}}  
		  
		 if ((Atester.x == Entree.F8.x) && (Atester.y == Entree.F8.y)){ 
			if ((Entree.F8.aimed) == true){ 
			   CeBateauEstVise = true;}}  
		  
		 if ((Atester.x == Entree.F9.x) && (Atester.y == Entree.F9.y)){ 
			if ((Entree.F9.aimed) == true){ 
			   CeBateauEstVise = true;}}  
		  
		 if ((Atester.x == Entree.F10.x) && (Atester.y == Entree.F10.y)){ 
			if ((Entree.F10.aimed) == true){ 
			   CeBateauEstVise = true;}}  
		  
		 if ((Atester.x == Entree.G1.x) && (Atester.y == Entree.G1.y)){ 
			if ((Entree.G1.aimed) == true){ 
			   CeBateauEstVise = true;}}  
		  
		 if ((Atester.x == Entree.G2.x) && (Atester.y == Entree.G2.y)){ 
			if ((Entree.G2.aimed) == true){ 
			   CeBateauEstVise = true;}}  
		  
		 if ((Atester.x == Entree.G3.x) && (Atester.y == Entree.G3.y)){ 
			if ((Entree.G3.aimed) == true){ 
			   CeBateauEstVise = true;}}  
		  
		 if ((Atester.x == Entree.G4.x) && (Atester.y == Entree.G4.y)){ 
			if ((Entree.G4.aimed) == true){ 
			   CeBateauEstVise = true;}}  
		  
		 if ((Atester.x == Entree.G5.x) && (Atester.y == Entree.G5.y)){ 
			if ((Entree.G5.aimed) == true){ 
			   CeBateauEstVise = true;}}  
		  
		 if ((Atester.x == Entree.G6.x) && (Atester.y == Entree.G6.y)){ 
			if ((Entree.G6.aimed) == true){ 
			   CeBateauEstVise = true;}}  
		  
		 if ((Atester.x == Entree.G7.x) && (Atester.y == Entree.G7.y)){ 
			if ((Entree.G7.aimed) == true){ 
			   CeBateauEstVise = true;}}  
		  
		 if ((Atester.x == Entree.G8.x) && (Atester.y == Entree.G8.y)){ 
			if ((Entree.G8.aimed) == true){ 
			   CeBateauEstVise = true;}}  
		  
		 if ((Atester.x == Entree.G9.x) && (Atester.y == Entree.G9.y)){ 
			if ((Entree.G9.aimed) == true){ 
			   CeBateauEstVise = true;}}  
		  
		 if ((Atester.x == Entree.G10.x) && (Atester.y == Entree.G10.y)){ 
			if ((Entree.G10.aimed) == true){ 
			   CeBateauEstVise = true;}}  
		  
		 if ((Atester.x == Entree.H1.x) && (Atester.y == Entree.H1.y)){ 
			if ((Entree.H1.aimed) == true){ 
			   CeBateauEstVise = true;}}  
		  
		 if ((Atester.x == Entree.H2.x) && (Atester.y == Entree.H2.y)){ 
			if ((Entree.H2.aimed) == true){ 
			   CeBateauEstVise = true;}}  
		  
		 if ((Atester.x == Entree.H3.x) && (Atester.y == Entree.H3.y)){ 
			if ((Entree.H3.aimed) == true){ 
			   CeBateauEstVise = true;}}  
		  
		 if ((Atester.x == Entree.H4.x) && (Atester.y == Entree.H4.y)){ 
			if ((Entree.H4.aimed) == true){ 
			   CeBateauEstVise = true;}}  
		  
		 if ((Atester.x == Entree.H5.x) && (Atester.y == Entree.H5.y)){ 
			if ((Entree.H5.aimed) == true){ 
			   CeBateauEstVise = true;}}  
		  
		 if ((Atester.x == Entree.H6.x) && (Atester.y == Entree.H6.y)){ 
			if ((Entree.H6.aimed) == true){ 
			   CeBateauEstVise = true;}}  
		  
		 if ((Atester.x == Entree.H7.x) && (Atester.y == Entree.H7.y)){ 
			if ((Entree.H7.aimed) == true){ 
			   CeBateauEstVise = true;}}  
		  
		 if ((Atester.x == Entree.H8.x) && (Atester.y == Entree.H8.y)){ 
			if ((Entree.H8.aimed) == true){ 
			   CeBateauEstVise = true;}}  
		  
		 if ((Atester.x == Entree.H9.x) && (Atester.y == Entree.H9.y)){ 
			if ((Entree.H9.aimed) == true){ 
			   CeBateauEstVise = true;}}  
		  
		 if ((Atester.x == Entree.H10.x) && (Atester.y == Entree.H10.y)){ 
			if ((Entree.H10.aimed) == true){ 
			   CeBateauEstVise = true;}}  
		  
		 if ((Atester.x == Entree.I1.x) && (Atester.y == Entree.I1.y)){ 
			if ((Entree.I1.aimed) == true){ 
			   CeBateauEstVise = true;}}  
		  
		 if ((Atester.x == Entree.I2.x) && (Atester.y == Entree.I2.y)){ 
			if ((Entree.I2.aimed) == true){ 
			   CeBateauEstVise = true;}}  
		  
		 if ((Atester.x == Entree.I3.x) && (Atester.y == Entree.I3.y)){ 
			if ((Entree.I3.aimed) == true){ 
			   CeBateauEstVise = true;}}  
		  
		 if ((Atester.x == Entree.I4.x) && (Atester.y == Entree.I4.y)){ 
			if ((Entree.I4.aimed) == true){ 
			   CeBateauEstVise = true;}}  
		  
		 if ((Atester.x == Entree.I5.x) && (Atester.y == Entree.I5.y)){ 
			if ((Entree.I5.aimed) == true){ 
			   CeBateauEstVise = true;}}  
		  
		 if ((Atester.x == Entree.I6.x) && (Atester.y == Entree.I6.y)){ 
			if ((Entree.I6.aimed) == true){ 
			   CeBateauEstVise = true;}}  
		  
		 if ((Atester.x == Entree.I7.x) && (Atester.y == Entree.I7.y)){ 
			if ((Entree.I7.aimed) == true){ 
			   CeBateauEstVise = true;}}  
		  
		 if ((Atester.x == Entree.I8.x) && (Atester.y == Entree.I8.y)){ 
			if ((Entree.I8.aimed) == true){ 
			   CeBateauEstVise = true;}}  
		  
		 if ((Atester.x == Entree.I9.x) && (Atester.y == Entree.I9.y)){ 
			if ((Entree.I9.aimed) == true){ 
			   CeBateauEstVise = true;}}  
		  
		 if ((Atester.x == Entree.I10.x) && (Atester.y == Entree.I10.y)){ 
			if ((Entree.I10.aimed) == true){ 
			   CeBateauEstVise = true;}}  
		  
		 if ((Atester.x == Entree.J1.x) && (Atester.y == Entree.J1.y)){ 
			if ((Entree.J1.aimed) == true){ 
			   CeBateauEstVise = true;}}  
		  
		 if ((Atester.x == Entree.J2.x) && (Atester.y == Entree.J2.y)){ 
			if ((Entree.J2.aimed) == true){ 
			   CeBateauEstVise = true;}}  
		  
		 if ((Atester.x == Entree.J3.x) && (Atester.y == Entree.J3.y)){ 
			if ((Entree.J3.aimed) == true){ 
			   CeBateauEstVise = true;}}  
		  
		 if ((Atester.x == Entree.J4.x) && (Atester.y == Entree.J4.y)){ 
			if ((Entree.J4.aimed) == true){ 
			   CeBateauEstVise = true;}}  
		  
		 if ((Atester.x == Entree.J5.x) && (Atester.y == Entree.J5.y)){ 
			if ((Entree.J5.aimed) == true){ 
			   CeBateauEstVise = true;}}  
		  
		 if ((Atester.x == Entree.J6.x) && (Atester.y == Entree.J6.y)){ 
			if ((Entree.J6.aimed) == true){ 
			   CeBateauEstVise = true;}}  
		  
		 if ((Atester.x == Entree.J7.x) && (Atester.y == Entree.J7.y)){ 
			if ((Entree.J7.aimed) == true){ 
			   CeBateauEstVise = true;}}  
		  
		 if ((Atester.x == Entree.J8.x) && (Atester.y == Entree.J8.y)){ 
			if ((Entree.J8.aimed) == true){ 
			   CeBateauEstVise = true;}}  
		  
		 if ((Atester.x == Entree.J9.x) && (Atester.y == Entree.J9.y)){ 
			if ((Entree.J9.aimed) == true){ 
			   CeBateauEstVise = true;}}  
		  
		 if ((Atester.x == Entree.J10.x) && (Atester.y == Entree.J10.y)){ 
			if ((Entree.J10.aimed) == true){ 
			   CeBateauEstVise = true;}}  
		return CeBateauEstVise;

	}
	/**
	* @param Une case 
	* @param une coordonee 
	* @param le nom du bateau
	* @return La case entree, avec un bateau du nom du string entre place a la coordonnee mise en entree
	*/
	static Case Correspondance(Case Entree, Coo Atester, String leBateau){
		
		boolean ApasBateau;
		ApasBateau = true;
		
		Case CaseEntree = new Case();
		CaseEntree = Entree;

		if ((Atester.x == Entree.A1.x) && (Atester.y == Entree.A1.y)){
		     if (VerifCoo(Entree.A1) == false) {
		 Entree.A1.boat = true; 
		    ApasBateau = false; 
		 Entree.A1.typeBateau = leBateau; }
		 } 
		 
		 if ((Atester.x == Entree.A2.x) && (Atester.y == Entree.A2.y)){
		     if (VerifCoo(Entree.A2) == false) {
		 Entree.A2.boat = true; 
		    ApasBateau = false; 
		 Entree.A2.typeBateau = leBateau; }
		 } 
		 
		 if ((Atester.x == Entree.A3.x) && (Atester.y == Entree.A3.y)){
		     if (VerifCoo(Entree.A3) == false) {
		 Entree.A3.boat = true; 
		    ApasBateau = false; 
		 Entree.A3.typeBateau = leBateau; }
		 } 
		 
		 if ((Atester.x == Entree.A4.x) && (Atester.y == Entree.A4.y)){
		     if (VerifCoo(Entree.A4) == false) {
		 Entree.A4.boat = true; 
		    ApasBateau = false; 
		 Entree.A4.typeBateau = leBateau; }
		 } 
		 
		 if ((Atester.x == Entree.A5.x) && (Atester.y == Entree.A5.y)){
		     if (VerifCoo(Entree.A5) == false) {
		 Entree.A5.boat = true; 
		    ApasBateau = false; 
		 Entree.A5.typeBateau = leBateau; }
		 } 
		 
		 if ((Atester.x == Entree.A6.x) && (Atester.y == Entree.A6.y)){
		     if (VerifCoo(Entree.A6) == false) {
		 Entree.A6.boat = true; 
		    ApasBateau = false; 
		 Entree.A6.typeBateau = leBateau; }
		 } 
		 
		 if ((Atester.x == Entree.A7.x) && (Atester.y == Entree.A7.y)){
		     if (VerifCoo(Entree.A7) == false) {
		 Entree.A7.boat = true; 
		    ApasBateau = false; 
		 Entree.A7.typeBateau = leBateau; }
		 } 
		 
		 if ((Atester.x == Entree.A8.x) && (Atester.y == Entree.A8.y)){
		     if (VerifCoo(Entree.A8) == false) {
		 Entree.A8.boat = true; 
		    ApasBateau = false; 
		 Entree.A8.typeBateau = leBateau; }
		 } 
		 
		 if ((Atester.x == Entree.A9.x) && (Atester.y == Entree.A9.y)){
		     if (VerifCoo(Entree.A9) == false) {
		 Entree.A9.boat = true; 
		    ApasBateau = false; 
		 Entree.A9.typeBateau = leBateau; }
		 } 
		 
		 if ((Atester.x == Entree.A10.x) && (Atester.y == Entree.A10.y)){
		     if (VerifCoo(Entree.A10) == false) {
		 Entree.A10.boat = true; 
		    ApasBateau = false; 
		 Entree.A10.typeBateau = leBateau; }
		 } 
		 
		 if ((Atester.x == Entree.B1.x) && (Atester.y == Entree.B1.y)){
		     if (VerifCoo(Entree.B1) == false) {
		 Entree.B1.boat = true; 
		    ApasBateau = false; 
		 Entree.B1.typeBateau = leBateau; }
		 } 
		 
		 if ((Atester.x == Entree.B2.x) && (Atester.y == Entree.B2.y)){
		     if (VerifCoo(Entree.B2) == false) {
		 Entree.B2.boat = true; 
		    ApasBateau = false; 
		 Entree.B2.typeBateau = leBateau; }
		 } 
		 
		 if ((Atester.x == Entree.B3.x) && (Atester.y == Entree.B3.y)){
		     if (VerifCoo(Entree.B3) == false) {
		 Entree.B3.boat = true; 
		    ApasBateau = false; 
		 Entree.B3.typeBateau = leBateau; }
		 } 
		 
		 if ((Atester.x == Entree.B4.x) && (Atester.y == Entree.B4.y)){
		     if (VerifCoo(Entree.B4) == false) {
		 Entree.B4.boat = true; 
		    ApasBateau = false; 
		 Entree.B4.typeBateau = leBateau; }
		 } 
		 
		 if ((Atester.x == Entree.B5.x) && (Atester.y == Entree.B5.y)){
		     if (VerifCoo(Entree.B5) == false) {
		 Entree.B5.boat = true; 
		    ApasBateau = false; 
		 Entree.B5.typeBateau = leBateau; }
		 } 
		 
		 if ((Atester.x == Entree.B6.x) && (Atester.y == Entree.B6.y)){
		     if (VerifCoo(Entree.B6) == false) {
		 Entree.B6.boat = true; 
		    ApasBateau = false; 
		 Entree.B6.typeBateau = leBateau; }
		 } 
		 
		 if ((Atester.x == Entree.B7.x) && (Atester.y == Entree.B7.y)){
		     if (VerifCoo(Entree.B7) == false) {
		 Entree.B7.boat = true; 
		    ApasBateau = false; 
		 Entree.B7.typeBateau = leBateau; }
		 } 
		 
		 if ((Atester.x == Entree.B8.x) && (Atester.y == Entree.B8.y)){
		     if (VerifCoo(Entree.B8) == false) {
		 Entree.B8.boat = true; 
		    ApasBateau = false; 
		 Entree.B8.typeBateau = leBateau; }
		 } 
		 
		 if ((Atester.x == Entree.B9.x) && (Atester.y == Entree.B9.y)){
		     if (VerifCoo(Entree.B9) == false) {
		 Entree.B9.boat = true; 
		    ApasBateau = false; 
		 Entree.B9.typeBateau = leBateau; }
		 } 
		 
		 if ((Atester.x == Entree.B10.x) && (Atester.y == Entree.B10.y)){
		     if (VerifCoo(Entree.B10) == false) {
		 Entree.B10.boat = true; 
		    ApasBateau = false; 
		 Entree.B10.typeBateau = leBateau; }
		 } 
		 
		 if ((Atester.x == Entree.C1.x) && (Atester.y == Entree.C1.y)){
		     if (VerifCoo(Entree.C1) == false) {
		 Entree.C1.boat = true; 
		    ApasBateau = false; 
		 Entree.C1.typeBateau = leBateau; }
		 } 
		 
		 if ((Atester.x == Entree.C2.x) && (Atester.y == Entree.C2.y)){
		     if (VerifCoo(Entree.C2) == false) {
		 Entree.C2.boat = true; 
		    ApasBateau = false; 
		 Entree.C2.typeBateau = leBateau; }
		 } 
		 
		 if ((Atester.x == Entree.C3.x) && (Atester.y == Entree.C3.y)){
		     if (VerifCoo(Entree.C3) == false) {
		 Entree.C3.boat = true; 
		    ApasBateau = false; 
		 Entree.C3.typeBateau = leBateau; }
		 } 
		 
		 if ((Atester.x == Entree.C4.x) && (Atester.y == Entree.C4.y)){
		     if (VerifCoo(Entree.C4) == false) {
		 Entree.C4.boat = true; 
		    ApasBateau = false; 
		 Entree.C4.typeBateau = leBateau; }
		 } 
		 
		 if ((Atester.x == Entree.C5.x) && (Atester.y == Entree.C5.y)){
		     if (VerifCoo(Entree.C5) == false) {
		 Entree.C5.boat = true; 
		    ApasBateau = false; 
		 Entree.C5.typeBateau = leBateau; }
		 } 
		 
		 if ((Atester.x == Entree.C6.x) && (Atester.y == Entree.C6.y)){
		     if (VerifCoo(Entree.C6) == false) {
		 Entree.C6.boat = true; 
		    ApasBateau = false; 
		 Entree.C6.typeBateau = leBateau; }
		 } 
		 
		 if ((Atester.x == Entree.C7.x) && (Atester.y == Entree.C7.y)){
		     if (VerifCoo(Entree.C7) == false) {
		 Entree.C7.boat = true; 
		    ApasBateau = false; 
		 Entree.C7.typeBateau = leBateau; }
		 } 
		 
		 if ((Atester.x == Entree.C8.x) && (Atester.y == Entree.C8.y)){
		     if (VerifCoo(Entree.C8) == false) {
		 Entree.C8.boat = true; 
		    ApasBateau = false; 
		 Entree.C8.typeBateau = leBateau; }
		 } 
		 
		 if ((Atester.x == Entree.C9.x) && (Atester.y == Entree.C9.y)){
		     if (VerifCoo(Entree.C9) == false) {
		 Entree.C9.boat = true; 
		    ApasBateau = false; 
		 Entree.C9.typeBateau = leBateau; }
		 } 
		 
		 if ((Atester.x == Entree.C10.x) && (Atester.y == Entree.C10.y)){
		     if (VerifCoo(Entree.C10) == false) {
		 Entree.C10.boat = true; 
		    ApasBateau = false; 
		 Entree.C10.typeBateau = leBateau; }
		 } 
		 
		 if ((Atester.x == Entree.D1.x) && (Atester.y == Entree.D1.y)){
		     if (VerifCoo(Entree.D1) == false) {
		 Entree.D1.boat = true; 
		    ApasBateau = false; 
		 Entree.D1.typeBateau = leBateau; }
		 } 
		 
		 if ((Atester.x == Entree.D2.x) && (Atester.y == Entree.D2.y)){
		     if (VerifCoo(Entree.D2) == false) {
		 Entree.D2.boat = true; 
		    ApasBateau = false; 
		 Entree.D2.typeBateau = leBateau; }
		 } 
		 
		 if ((Atester.x == Entree.D3.x) && (Atester.y == Entree.D3.y)){
		     if (VerifCoo(Entree.D3) == false) {
		 Entree.D3.boat = true; 
		    ApasBateau = false; 
		 Entree.D3.typeBateau = leBateau; }
		 } 
		 
		 if ((Atester.x == Entree.D4.x) && (Atester.y == Entree.D4.y)){
		     if (VerifCoo(Entree.D4) == false) {
		 Entree.D4.boat = true; 
		    ApasBateau = false; 
		 Entree.D4.typeBateau = leBateau; }
		 } 
		 
		 if ((Atester.x == Entree.D5.x) && (Atester.y == Entree.D5.y)){
		     if (VerifCoo(Entree.D5) == false) {
		 Entree.D5.boat = true; 
		    ApasBateau = false; 
		 Entree.D5.typeBateau = leBateau; }
		 } 
		 
		 if ((Atester.x == Entree.D6.x) && (Atester.y == Entree.D6.y)){
		     if (VerifCoo(Entree.D6) == false) {
		 Entree.D6.boat = true; 
		    ApasBateau = false; 
		 Entree.D6.typeBateau = leBateau; }
		 } 
		 
		 if ((Atester.x == Entree.D7.x) && (Atester.y == Entree.D7.y)){
		     if (VerifCoo(Entree.D7) == false) {
		 Entree.D7.boat = true; 
		    ApasBateau = false; 
		 Entree.D7.typeBateau = leBateau; }
		 } 
		 
		 if ((Atester.x == Entree.D8.x) && (Atester.y == Entree.D8.y)){
		     if (VerifCoo(Entree.D8) == false) {
		 Entree.D8.boat = true; 
		    ApasBateau = false; 
		 Entree.D8.typeBateau = leBateau; }
		 } 
		 
		 if ((Atester.x == Entree.D9.x) && (Atester.y == Entree.D9.y)){
		     if (VerifCoo(Entree.D9) == false) {
		 Entree.D9.boat = true; 
		    ApasBateau = false; 
		 Entree.D9.typeBateau = leBateau; }
		 } 
		 
		 if ((Atester.x == Entree.D10.x) && (Atester.y == Entree.D10.y)){
		     if (VerifCoo(Entree.D10) == false) {
		 Entree.D10.boat = true; 
		    ApasBateau = false; 
		 Entree.D10.typeBateau = leBateau; }
		 } 
		 
		 if ((Atester.x == Entree.E1.x) && (Atester.y == Entree.E1.y)){
		     if (VerifCoo(Entree.E1) == false) {
		 Entree.E1.boat = true; 
		    ApasBateau = false; 
		 Entree.E1.typeBateau = leBateau; }
		 } 
		 
		 if ((Atester.x == Entree.E2.x) && (Atester.y == Entree.E2.y)){
		     if (VerifCoo(Entree.E2) == false) {
		 Entree.E2.boat = true; 
		    ApasBateau = false; 
		 Entree.E2.typeBateau = leBateau; }
		 } 
		 
		 if ((Atester.x == Entree.E3.x) && (Atester.y == Entree.E3.y)){
		     if (VerifCoo(Entree.E3) == false) {
		 Entree.E3.boat = true; 
		    ApasBateau = false; 
		 Entree.E3.typeBateau = leBateau; }
		 } 
		 
		 if ((Atester.x == Entree.E4.x) && (Atester.y == Entree.E4.y)){
		     if (VerifCoo(Entree.E4) == false) {
		 Entree.E4.boat = true; 
		    ApasBateau = false; 
		 Entree.E4.typeBateau = leBateau; }
		 } 
		 
		 if ((Atester.x == Entree.E5.x) && (Atester.y == Entree.E5.y)){
		     if (VerifCoo(Entree.E5) == false) {
		 Entree.E5.boat = true; 
		    ApasBateau = false; 
		 Entree.E5.typeBateau = leBateau; }
		 } 
		 
		 if ((Atester.x == Entree.E6.x) && (Atester.y == Entree.E6.y)){
		     if (VerifCoo(Entree.E6) == false) {
		 Entree.E6.boat = true; 
		    ApasBateau = false; 
		 Entree.E6.typeBateau = leBateau; }
		 } 
		 
		 if ((Atester.x == Entree.E7.x) && (Atester.y == Entree.E7.y)){
		     if (VerifCoo(Entree.E7) == false) {
		 Entree.E7.boat = true; 
		    ApasBateau = false; 
		 Entree.E7.typeBateau = leBateau; }
		 } 
		 
		 if ((Atester.x == Entree.E8.x) && (Atester.y == Entree.E8.y)){
		     if (VerifCoo(Entree.E8) == false) {
		 Entree.E8.boat = true; 
		    ApasBateau = false; 
		 Entree.E8.typeBateau = leBateau; }
		 } 
		 
		 if ((Atester.x == Entree.E9.x) && (Atester.y == Entree.E9.y)){
		     if (VerifCoo(Entree.E9) == false) {
		 Entree.E9.boat = true; 
		    ApasBateau = false; 
		 Entree.E9.typeBateau = leBateau; }
		 } 
		 
		 if ((Atester.x == Entree.E10.x) && (Atester.y == Entree.E10.y)){
		     if (VerifCoo(Entree.E10) == false) {
		 Entree.E10.boat = true; 
		    ApasBateau = false; 
		 Entree.E10.typeBateau = leBateau; }
		 } 
		 
		 if ((Atester.x == Entree.F1.x) && (Atester.y == Entree.F1.y)){
		     if (VerifCoo(Entree.F1) == false) {
		 Entree.F1.boat = true; 
		    ApasBateau = false; 
		 Entree.F1.typeBateau = leBateau; }
		 } 
		 
		 if ((Atester.x == Entree.F2.x) && (Atester.y == Entree.F2.y)){
		     if (VerifCoo(Entree.F2) == false) {
		 Entree.F2.boat = true; 
		    ApasBateau = false; 
		 Entree.F2.typeBateau = leBateau; }
		 } 
		 
		 if ((Atester.x == Entree.F3.x) && (Atester.y == Entree.F3.y)){
		     if (VerifCoo(Entree.F3) == false) {
		 Entree.F3.boat = true; 
		    ApasBateau = false; 
		 Entree.F3.typeBateau = leBateau; }
		 } 
		 
		 if ((Atester.x == Entree.F4.x) && (Atester.y == Entree.F4.y)){
		     if (VerifCoo(Entree.F4) == false) {
		 Entree.F4.boat = true; 
		    ApasBateau = false; 
		 Entree.F4.typeBateau = leBateau; }
		 } 
		 
		 if ((Atester.x == Entree.F5.x) && (Atester.y == Entree.F5.y)){
		     if (VerifCoo(Entree.F5) == false) {
		 Entree.F5.boat = true; 
		    ApasBateau = false; 
		 Entree.F5.typeBateau = leBateau; }
		 } 
		 
		 if ((Atester.x == Entree.F6.x) && (Atester.y == Entree.F6.y)){
		     if (VerifCoo(Entree.F6) == false) {
		 Entree.F6.boat = true; 
		    ApasBateau = false; 
		 Entree.F6.typeBateau = leBateau; }
		 } 
		 
		 if ((Atester.x == Entree.F7.x) && (Atester.y == Entree.F7.y)){
		     if (VerifCoo(Entree.F7) == false) {
		 Entree.F7.boat = true; 
		    ApasBateau = false; 
		 Entree.F7.typeBateau = leBateau; }
		 } 
		 
		 if ((Atester.x == Entree.F8.x) && (Atester.y == Entree.F8.y)){
		     if (VerifCoo(Entree.F8) == false) {
		 Entree.F8.boat = true; 
		    ApasBateau = false; 
		 Entree.F8.typeBateau = leBateau; }
		 } 
		 
		 if ((Atester.x == Entree.F9.x) && (Atester.y == Entree.F9.y)){
		     if (VerifCoo(Entree.F9) == false) {
		 Entree.F9.boat = true; 
		    ApasBateau = false; 
		 Entree.F9.typeBateau = leBateau; }
		 } 
		 
		 if ((Atester.x == Entree.F10.x) && (Atester.y == Entree.F10.y)){
		     if (VerifCoo(Entree.F10) == false) {
		 Entree.F10.boat = true; 
		    ApasBateau = false; 
		 Entree.F10.typeBateau = leBateau; }
		 } 
		 
		 if ((Atester.x == Entree.G1.x) && (Atester.y == Entree.G1.y)){
		     if (VerifCoo(Entree.G1) == false) {
		 Entree.G1.boat = true; 
		    ApasBateau = false; 
		 Entree.G1.typeBateau = leBateau; }
		 } 
		 
		 if ((Atester.x == Entree.G2.x) && (Atester.y == Entree.G2.y)){
		     if (VerifCoo(Entree.G2) == false) {
		 Entree.G2.boat = true; 
		    ApasBateau = false; 
		 Entree.G2.typeBateau = leBateau; }
		 } 
		 
		 if ((Atester.x == Entree.G3.x) && (Atester.y == Entree.G3.y)){
		     if (VerifCoo(Entree.G3) == false) {
		 Entree.G3.boat = true; 
		    ApasBateau = false; 
		 Entree.G3.typeBateau = leBateau; }
		 } 
		 
		 if ((Atester.x == Entree.G4.x) && (Atester.y == Entree.G4.y)){
		     if (VerifCoo(Entree.G4) == false) {
		 Entree.G4.boat = true; 
		    ApasBateau = false; 
		 Entree.G4.typeBateau = leBateau; }
		 } 
		 
		 if ((Atester.x == Entree.G5.x) && (Atester.y == Entree.G5.y)){
		     if (VerifCoo(Entree.G5) == false) {
		 Entree.G5.boat = true; 
		    ApasBateau = false; 
		 Entree.G5.typeBateau = leBateau; }
		 } 
		 
		 if ((Atester.x == Entree.G6.x) && (Atester.y == Entree.G6.y)){
		     if (VerifCoo(Entree.G6) == false) {
		 Entree.G6.boat = true; 
		    ApasBateau = false; 
		 Entree.G6.typeBateau = leBateau; }
		 } 
		 
		 if ((Atester.x == Entree.G7.x) && (Atester.y == Entree.G7.y)){
		     if (VerifCoo(Entree.G7) == false) {
		 Entree.G7.boat = true; 
		    ApasBateau = false; 
		 Entree.G7.typeBateau = leBateau; }
		 } 
		 
		 if ((Atester.x == Entree.G8.x) && (Atester.y == Entree.G8.y)){
		     if (VerifCoo(Entree.G8) == false) {
		 Entree.G8.boat = true; 
		    ApasBateau = false; 
		 Entree.G8.typeBateau = leBateau; }
		 } 
		 
		 if ((Atester.x == Entree.G9.x) && (Atester.y == Entree.G9.y)){
		     if (VerifCoo(Entree.G9) == false) {
		 Entree.G9.boat = true; 
		    ApasBateau = false; 
		 Entree.G9.typeBateau = leBateau; }
		 } 
		 
		 if ((Atester.x == Entree.G10.x) && (Atester.y == Entree.G10.y)){
		     if (VerifCoo(Entree.G10) == false) {
		 Entree.G10.boat = true; 
		    ApasBateau = false; 
		 Entree.G10.typeBateau = leBateau; }
		 } 
		 
		 if ((Atester.x == Entree.H1.x) && (Atester.y == Entree.H1.y)){
		     if (VerifCoo(Entree.H1) == false) {
		 Entree.H1.boat = true; 
		    ApasBateau = false; 
		 Entree.H1.typeBateau = leBateau; }
		 } 
		 
		 if ((Atester.x == Entree.H2.x) && (Atester.y == Entree.H2.y)){
		     if (VerifCoo(Entree.H2) == false) {
		 Entree.H2.boat = true; 
		    ApasBateau = false; 
		 Entree.H2.typeBateau = leBateau; }
		 } 
		 
		 if ((Atester.x == Entree.H3.x) && (Atester.y == Entree.H3.y)){
		     if (VerifCoo(Entree.H3) == false) {
		 Entree.H3.boat = true; 
		    ApasBateau = false; 
		 Entree.H3.typeBateau = leBateau; }
		 } 
		 
		 if ((Atester.x == Entree.H4.x) && (Atester.y == Entree.H4.y)){
		     if (VerifCoo(Entree.H4) == false) {
		 Entree.H4.boat = true; 
		    ApasBateau = false; 
		 Entree.H4.typeBateau = leBateau; }
		 } 
		 
		 if ((Atester.x == Entree.H5.x) && (Atester.y == Entree.H5.y)){
		     if (VerifCoo(Entree.H5) == false) {
		 Entree.H5.boat = true; 
		    ApasBateau = false; 
		 Entree.H5.typeBateau = leBateau; }
		 } 
		 
		 if ((Atester.x == Entree.H6.x) && (Atester.y == Entree.H6.y)){
		     if (VerifCoo(Entree.H6) == false) {
		 Entree.H6.boat = true; 
		    ApasBateau = false; 
		 Entree.H6.typeBateau = leBateau; }
		 } 
		 
		 if ((Atester.x == Entree.H7.x) && (Atester.y == Entree.H7.y)){
		     if (VerifCoo(Entree.H7) == false) {
		 Entree.H7.boat = true; 
		    ApasBateau = false; 
		 Entree.H7.typeBateau = leBateau; }
		 } 
		 
		 if ((Atester.x == Entree.H8.x) && (Atester.y == Entree.H8.y)){
		     if (VerifCoo(Entree.H8) == false) {
		 Entree.H8.boat = true; 
		    ApasBateau = false; 
		 Entree.H8.typeBateau = leBateau; }
		 } 
		 
		 if ((Atester.x == Entree.H9.x) && (Atester.y == Entree.H9.y)){
		     if (VerifCoo(Entree.H9) == false) {
		 Entree.H9.boat = true; 
		    ApasBateau = false; 
		 Entree.H9.typeBateau = leBateau; }
		 } 
		 
		 if ((Atester.x == Entree.H10.x) && (Atester.y == Entree.H10.y)){
		     if (VerifCoo(Entree.H10) == false) {
		 Entree.H10.boat = true; 
		    ApasBateau = false; 
		 Entree.H10.typeBateau = leBateau; }
		 } 
		 
		 if ((Atester.x == Entree.I1.x) && (Atester.y == Entree.I1.y)){
		     if (VerifCoo(Entree.I1) == false) {
		 Entree.I1.boat = true; 
		    ApasBateau = false; 
		 Entree.I1.typeBateau = leBateau; }
		 } 
		 
		 if ((Atester.x == Entree.I2.x) && (Atester.y == Entree.I2.y)){
		     if (VerifCoo(Entree.I2) == false) {
		 Entree.I2.boat = true; 
		    ApasBateau = false; 
		 Entree.I2.typeBateau = leBateau; }
		 } 
		 
		 if ((Atester.x == Entree.I3.x) && (Atester.y == Entree.I3.y)){
		     if (VerifCoo(Entree.I3) == false) {
		 Entree.I3.boat = true; 
		    ApasBateau = false; 
		 Entree.I3.typeBateau = leBateau; }
		 } 
		 
		 if ((Atester.x == Entree.I4.x) && (Atester.y == Entree.I4.y)){
		     if (VerifCoo(Entree.I4) == false) {
		 Entree.I4.boat = true; 
		    ApasBateau = false; 
		 Entree.I4.typeBateau = leBateau; }
		 } 
		 
		 if ((Atester.x == Entree.I5.x) && (Atester.y == Entree.I5.y)){
		     if (VerifCoo(Entree.I5) == false) {
		 Entree.I5.boat = true; 
		    ApasBateau = false; 
		 Entree.I5.typeBateau = leBateau; }
		 } 
		 
		 if ((Atester.x == Entree.I6.x) && (Atester.y == Entree.I6.y)){
		     if (VerifCoo(Entree.I6) == false) {
		 Entree.I6.boat = true; 
		    ApasBateau = false; 
		 Entree.I6.typeBateau = leBateau; }
		 } 
		 
		 if ((Atester.x == Entree.I7.x) && (Atester.y == Entree.I7.y)){
		     if (VerifCoo(Entree.I7) == false) {
		 Entree.I7.boat = true; 
		    ApasBateau = false; 
		 Entree.I7.typeBateau = leBateau; }
		 } 
		 
		 if ((Atester.x == Entree.I8.x) && (Atester.y == Entree.I8.y)){
		     if (VerifCoo(Entree.I8) == false) {
		 Entree.I8.boat = true; 
		    ApasBateau = false; 
		 Entree.I8.typeBateau = leBateau; }
		 } 
		 
		 if ((Atester.x == Entree.I9.x) && (Atester.y == Entree.I9.y)){
		     if (VerifCoo(Entree.I9) == false) {
		 Entree.I9.boat = true; 
		    ApasBateau = false; 
		 Entree.I9.typeBateau = leBateau; }
		 } 
		 
		 if ((Atester.x == Entree.I10.x) && (Atester.y == Entree.I10.y)){
		     if (VerifCoo(Entree.I10) == false) {
		 Entree.I10.boat = true; 
		    ApasBateau = false; 
		 Entree.I10.typeBateau = leBateau; }
		 } 
		 
		 if ((Atester.x == Entree.J1.x) && (Atester.y == Entree.J1.y)){
		     if (VerifCoo(Entree.J1) == false) {
		 Entree.J1.boat = true; 
		    ApasBateau = false; 
		 Entree.J1.typeBateau = leBateau; }
		 } 
		 
		 if ((Atester.x == Entree.J2.x) && (Atester.y == Entree.J2.y)){
		     if (VerifCoo(Entree.J2) == false) {
		 Entree.J2.boat = true; 
		    ApasBateau = false; 
		 Entree.J2.typeBateau = leBateau; }
		 } 
		 
		 if ((Atester.x == Entree.J3.x) && (Atester.y == Entree.J3.y)){
		     if (VerifCoo(Entree.J3) == false) {
		 Entree.J3.boat = true; 
		    ApasBateau = false; 
		 Entree.J3.typeBateau = leBateau; }
		 } 
		 
		 if ((Atester.x == Entree.J4.x) && (Atester.y == Entree.J4.y)){
		     if (VerifCoo(Entree.J4) == false) {
		 Entree.J4.boat = true; 
		    ApasBateau = false; 
		 Entree.J4.typeBateau = leBateau; }
		 } 
		 
		 if ((Atester.x == Entree.J5.x) && (Atester.y == Entree.J5.y)){
		     if (VerifCoo(Entree.J5) == false) {
		 Entree.J5.boat = true; 
		    ApasBateau = false; 
		 Entree.J5.typeBateau = leBateau; }
		 } 
		 
		 if ((Atester.x == Entree.J6.x) && (Atester.y == Entree.J6.y)){
		     if (VerifCoo(Entree.J6) == false) {
		 Entree.J6.boat = true; 
		    ApasBateau = false; 
		 Entree.J6.typeBateau = leBateau; }
		 } 
		 
		 if ((Atester.x == Entree.J7.x) && (Atester.y == Entree.J7.y)){
		     if (VerifCoo(Entree.J7) == false) {
		 Entree.J7.boat = true; 
		    ApasBateau = false; 
		 Entree.J7.typeBateau = leBateau; }
		 } 
		 
		 if ((Atester.x == Entree.J8.x) && (Atester.y == Entree.J8.y)){
		     if (VerifCoo(Entree.J8) == false) {
		 Entree.J8.boat = true; 
		    ApasBateau = false; 
		 Entree.J8.typeBateau = leBateau; }
		 } 
		 
		 if ((Atester.x == Entree.J9.x) && (Atester.y == Entree.J9.y)){
		     if (VerifCoo(Entree.J9) == false) {
		 Entree.J9.boat = true; 
		    ApasBateau = false; 
		 Entree.J9.typeBateau = leBateau; }
		 } 
		 
		 if ((Atester.x == Entree.J10.x) && (Atester.y == Entree.J10.y)){
		     if (VerifCoo(Entree.J10) == false) {
		 Entree.J10.boat = true; 
		    ApasBateau = false; 
		 Entree.J10.typeBateau = leBateau; }
		 } 
		return Entree;	
		}
	/**
	 * @param Aucun
	 * @return Un entier saisie par l'utilisateur compris entre 1 et 10 inclus
	 */
	static int SaisieUserInt(){
		int entierSaisi;
		Ecran.afficher("\nVeuilez saisir un entier\n");
		entierSaisi = Clavier.saisirInt();
		while ((entierSaisi < 1) || (entierSaisi > 10)){
			Ecran.afficher("\nErreur, l'entier saisi n'est pas compris entre 1 et 10 ! Veuillez recommencez la saisie\n");
		entierSaisi = Clavier.saisirInt();}
		
		return entierSaisi;
		}

	/**
	 * @param Aucun
	 * @return Un char saisie par l'utilisateur qui correspond a la grille
	 */
	static char SaisieUserChar(){
		char charSaisi;
		Ecran.afficher("\nVeuilez saisir un char\n");
		charSaisi = Clavier.saisirChar();
		while (((int)charSaisi < 65) || ((int)charSaisi > 74)){
			Ecran.afficher("\nErreur, le char saisie n'est pas dans la grille ! Veuillez recommencez la saisie\n");
		charSaisi = Clavier.saisirChar();
		}		
		return charSaisi;
		}
}	
