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
 -  "localhost:8080/one" : pour l'interface de lancement d'une seule simulation
 -  "localhost:8080/multi" : pour l'interface de lancement de multiple simulations
 -  "localhost:8080/extract" : pour l'interface d'une extraction
 
 Les modules associés à l'interface doivent être lancé en amont.

## Liste des commandes et leurs arguments :

### Lancer une simulation :

Cela se fait par le biais d'une commande curl, en voici un exemple :
```
curl -H "Content-Type: application/json" -X POST localhost:9090/state -d "{\"nbrHousehold\": 50, \"nbrInvestor\": 50, \"nbrPromoter\": 50, \"num\":10, \"listOfEquipment\":[85,81], \"listOfTransport\":[176,794], \"fileHousehold\" : \"householdAgent.apl\", \"fileInvestor\" : \"investorAgent.apl\", \"filePromoter\" : \"promoterAgent.apl\"}"

```
La simulation utilise le port 9090

Détails des paramètres :
  -fileInvestor / Household / Promoter : c'est le nom du fichier .apl de l'agent concerné, il doit se trouver dans le dossier "docs" du simulateur
  -num : c'est le nombre de tours que a accomplir la simulation  
  -listOfEquipment : c'est la list d'équipement qui vont être pris en compte lors de la simulation (si ce paramètre n'est pas présent, la simulation se lance avec tous les equipements)  
  -listOfNetwork : même chose que pour equipements.  
  
 ### Extraire les données d'une simulation
 
 Cela se fait également par commande curl voici un exemple :
```   
curl -H "Content-Type: application/json" -X POST localhost:9091/extract -d "{\"entity\":\"household\", \"idSimulation\":\"0\"}"
```
L'extraction utilise le port 9091

Avec cette commande, un dossier Simulation45 sera créé dans le dossier results du programme, a l'intérieur de ce dossier, tout le contenu de la collection Mongo des household de la simulation 45 sera extrait au format .csv.
Les différents paramètres d'extraction sont : household, property, land, promoter, investor, configuration.
"# Stage-Simulation" 
