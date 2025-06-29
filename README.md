# Kata AGS

### Domaine

Marche: Marché sur lequel est posée une offre

Offre: Offre de Vente placée sur les marchés et contenant des blocs horaires.
- Placée sur les marchés (1)
- Composée de blocs (1 -> n)

Bloc: Bloc horaire contenant la quantité d'énergie qui sera produite (en MW) et un prix plancher au-dessous duquel on ne vendra pas.
- Représente un "bloc horaire" (Soit par pas, soit libre via dates)

Parc: Parc de Production d'électricité capable de fournir un nombre de MégaWatt pendant la durée d'un bloc horaire.

### Besoin API

- Créer une offre: POST /offre
- Créer un parc: POST /parc
- Lister les offres proposées pour chaque marché: GET /offre/marche
- Lister les parcs qui vendent sur un marche: GET /parc/marche

### Questionnements
- "Sur chacun de ces marchés Agregio peut placer une offre composée de plusieurs "blocs" horaires (une journée de 24h pourrait contenir 8 blocs de 3 heures)." -> **Ces blocs sont successifs ?**
  - *Choix KISS par défaut*: Les Blocs sont successifs
- **Quelle est la durée d'une offre ?**
  - *Choix KISS par défaut*: Pour coller à l'exemple et rester simple, une offre va pouvoir durer n * 3h, chaque pas de 3H correspondant à un bloc.
- **A quoi correspond la durée d'un bloc horaire ? Est-ce un début et une fin avec une précision en secondes ?**
  - *Choix KISS par défaut*: D'après l'exemple, un bloc va correspondre à un pas de 3H.