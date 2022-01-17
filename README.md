#**Projet 7Wonders**

##Démarrage

1. Avec Docker, créer la base de donnée et ajouter les collections dans modeles/dao/collections.
2. Saisir dans le terminal docker run -it --link mongoserver:mongo --rm -v /tmp:/data mongo bash
3. Pour ajouter les collections, saisir dans le terminal mongoimport --db wonders --collection collection --host mongo --port 27017 --file /data/collection.json --jsonArray


