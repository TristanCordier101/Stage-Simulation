# Windows

## Prérequis :

1. Installer Postgreql
2. Installer Postgis
3. Créer une base de données appelée tomsa avec extention Postgis et un utilisateur appelé tomsa
4. Lancer le script db/tomsa.sql

5. Installer mongoDB

6. Installer la commande curl si nécessaire

## Execution :
Dans un terminal :
1. Lancer le server de la bd exemple :
 - pg_ctl -D "myPath\PostgreSQL\11\data" start
 
A la racine de chaque module :
1. mvn install

Dans les modules simulator et data-extractor :
2. mvn spring-boot:run

Dans le module interface :
2. mvn appengine:devserver
3. Ouvrir un navigateur et se connecter sur l'url :
 -  "localhost:8080/one" : pour l'interface de lancement d'une seule simulation (Interface pour "localhost:9090/state")
 -  "localhost:8080/multi" : pour l'interface de lancement de multiple simulations (Interface pour "localhost:9090/statetab")
 -  "localhost:8080/extract" : pour l'interface d'une extraction (Interface pour "localhost:9091/extract")
 
 Les modules associés à l'interface doivent être lancé en amont.

## Liste des commandes et leurs arguments :

### Lancer une simulation :

Cela se fait par le biais d'une commande curl, en voici un exemple :
```
curl -H "Content-Type: application/json" -X POST localhost:9090/state -d "{\"storageType\": 1, \"nbrHousehold\": 50, \"nbrInvestor\": 50, \"nbrPromoter\": 50, \"num\":10, \"listOfEquipment\":[85,81], \"listOfTransport\":[176,794], \"fileHousehold\" : \"householdAgent.apl\", \"fileInvestor\" : \"investorAgent.apl\", \"filePromoter\" : \"promoterAgent.apl\"}"

```
La simulation utilise le port 9090

Détails des paramètres :
  - fileInvestor / fileHousehold / filePromoter : c'est le nom du fichier .apl de l'agent concerné, il doit se trouver dans le dossier "docs" du simulateur
  - num : c'est le nombre de tours que a accomplir la simulation  
  - listOfEquipment : c'est la liste d'équipements qui vont être pris en compte lors de la simulation (si ce paramètre n'est pas présent, la simulation se lance avec tous les equipements)  
  - listOfNetwork : même chose que pour equipements.  
  - StorageType : 0 pour stoquer les résultats sur MongDB et autre pour stoquer sur Postgres
  
 ### Lancer plusieurs simulations :
 Pour lancer plusieurs simulation en définissant au cas par cas les paramètres. Avec l'URL "statetab".
```
 curl -H "Content-Type: application/json" -X POST localhost:9090/statetab -d "{\"simulationMessageList\":{\"storageType\": 1, \"nbrHousehold\": 10, \"nbrInvestor\": 10, \"nbrPromoter\": 10, \"num\":5, \"listOfEquipment\":[85,81], \"listOfTransport\":[176,794], \"fileHousehold\" : \"householdAgent.apl\", \"fileInvestor\" : \"investorAgent.apl\", \"filePromoter\" : \"promoterAgent.apl\"},{\"storageType\": 1, \"nbrHousehold\": 10, \"nbrInvestor\": 10, \"nbrPromoter\": 10, \"num\":5, \"listOfEquipment\":[85,81], \"listOfTransport\":[176,794], \"fileHousehold\" : \"householdAgent.apl\", \"fileInvestor\" : \"investorAgent.apl\", \"filePromoter\" : \"promoterAgent.apl\"}}"
```
Les paramètres de chaques simulations sont séparés par une virgule.

 ### Lancer un grand nombre de simulations :
 Il est possible de lancer des simulation en définissant un ensemble pour certain paramètres, toute les combinaisons possbiles de paramètre seront éxécutées. Avec l'URL "batch".
 ```
curl -H "Content-Type: application/json" -X POST localhost:9090/batch -d "{\"storageType\": 1, \"nbrHouseholdRange\": [50,60],\"nbrHouseholdStep\": 5, \"nbrInvestorRange\": [50,60],\"nbrInvestorStep\": 5, \"nbrPromoterRange\": [50,60],\"nbrPromoterStep\": 5, \"numRange\":[10], \"numStep\":1, \"listOfEquipment\":[85,81,90,105], \"listOfTransport\":[176,794,254], \"fileHousehold\" : \"householdAgent.apl\", \"fileInvestor\" : \"investorAgent.apl\", \"filePromoter\" : \"promoterAgent.apl\"}"

 ```
 Détails des paramètres :
   - fileInvestor / fileHousehold / filePromoter : c'est le nom du fichier .apl de l'agent concerné, il doit se trouver dans le dossier "docs" du simulateur
   - numRange, nbrHouseholdRange, nbrInvestorRange, nbrPromoterRange : correspond au domaine de variation de ces paramètres. Toujours [min, max] avec max inclu, il est aussi possible d'entrer une seule valeur qui ne variera pas (Défault 50). Exemple : \"nbrHouseholdRange\":[50,60] , le nombre sera compris entre 50 et 60  
   - numStep, nbrHouseholdStep, nbrInvestorStep, nbrPromoterStep : correspond au pas de variation dans la range (Défault 1). Exemple  \"nbrHouseholdRange\":[50,60], \"nbrHouseholdStep\": 5 , household prendras les valeurs 50,55 et 60.
   - listOfEquipment : c'est la liste d'équipements qui vont être pris en compte lors de la simulation (si ce paramètre n'est pas présent, la simulation se lance avec tous les equipements)  
   - listOfNetwork : même chose que pour equipements.  
   - StorageType : 0 pour stoquer les résultats sur MongDB et autre pour stoquer sur Postgres
  
  
 ### Extraire les données d'une simulation
 
 Cela se fait également par commande curl voici un exemple :
```   
curl -H "Content-Type: application/json" -X POST localhost:9091/extract -d "{\"entity\":[\"household\",\"investor\"], \"idSimulationRange\":[10,20], \"storageType\": 1}"
```
L'extraction utilise le port 9091

Avec cette commande, un dossier Simulation45 sera créé dans le dossier results du programme, a l'intérieur de ce dossier, tout le contenu de la collection Mongo (storageType=0) ou de la table Postgres (storageType !=0) des household et des investor des simulation 10 à 20 sera extrait au format .csv.
Les différents paramètres d'extraction sont : household, property, land, promoter, investor, configuration.

