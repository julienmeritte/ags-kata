# Kata AGS

### Domaine

Marche: Marché sur lequel est posée une offre

Offre: Offre de Vente placée sur les marchés et contenant des blocs horaires.

- Placée sur les marchés (1)
- Composée de blocs (1 -> n)

Bloc: Bloc horaire contenant la quantité d'énergie qui sera produite (en MW) et un prix plancher au-dessous duquel on ne
vendra pas.

- Représente un "bloc horaire" (Soit par pas, soit libre via dates)

Parc: Parc de Production d'électricité capable de fournir un nombre de MégaWatt pendant la durée d'un bloc horaire.

### Besoin API

- Créer une offre: POST /offre
- Créer un parc: POST /parc
- Lister les offres proposées par Agregio pour chaque marché: GET /offre/marche/agregio
- Lister les parcs qui vendent sur un marche: GET /parc/marche/{marcheId}

### Questionnements

- "Sur chacun de ces marchés Agregio peut placer une offre composée de plusieurs "blocs" horaires (une journée de 24h
  pourrait contenir 8 blocs de 3 heures)." -> **Ces blocs sont successifs ?**
    - *Choix KISS par défaut*: Les Blocs sont successifs
- **Quelle est la durée d'une offre ?**
    - *Choix KISS par défaut*: Pour coller à l'exemple et rester simple, une offre va pouvoir durer n * 3h, chaque pas
      de 3H correspondant à un bloc. Pour une durée maximum de 24h, soit 8 blocs de 3h.
- **A quoi correspond la durée d'un bloc horaire ? Est-ce un début et une fin avec une précision en secondes ?**
    - *Choix KISS par défaut*: D'après l'exemple, un bloc va correspondre à un pas de 3H.
- **Comment gérer le passage d'une journée à l'autre ?**
    - *Choix KISS par défaut**: Un bloc de 3H correspond à une position sur 24H se position sur une heure complète
      multiple de 3 ou égale à 0 (0H, 3H, 6H, ...21H).
- **A quoi exactement correpond une Offre de Vente ? Peuvent-elles se chevaucher ?**
    - *Choix Cohérence*: On part du principe qu'il peut y avoir plusieurs offres sur les même créneaux des même jour,
      tant que la capacité de production est respectée. Car il semble plus logique de ne pas laisser dormir de la
      production en permettant plusieurs offres simultanées plutôt que de sélectionner le choix KISS.
- **Comment se comporte la relation Bloc et Parc ? Un Bloc de 100MW qui aurait assigné un Parc qui produit 60MW
  nécessiterait 40MW d'un autre parc. Inversement, un parc qui produit 110MW pourrait produire 10MW pour un autre bloc.
  **
    - *Choix Cohérence par défaut*: Pour permettre ce découpage, création d'une notion intermédiaire AllocationParc
      reliant une quantité produite d'un parc sur un bloc et ainsi affiner l'assignation Bloc-Parc.

## Améliorations futures

#### Technique

- Tests d'intégration
- Exclusions des getters/setters via outil OU ajout tests pour garantir un domaine pur
- Finir d'affiner les tests (Tests copies défensives etc...)
- Paginer les API de consultation
- Monitoring/métriques Production
- Mise en cache (Si le besoin évolue et devient plus conséquent)
- Configuration par environnement
- Dockerfile prodution
- Authentification via Spring Security (si nécessaire)

### Clean code

- Ajout d'annotations clarifiant le domaine

#### Fonctionnel

- Utilisation de créneaux plus précis.
- Utilisation de blocs non successifs
- Faire le tour des edges cases

### REX

- Sequences complexes au sein du DDD -> Direction vers UUID.