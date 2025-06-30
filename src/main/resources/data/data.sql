INSERT INTO marche (id, nom)
VALUES ('5f039b79-255d-4a28-aa86-3f91eb69bf65', 'Réserve Primaire'),
       ('405a965e-82b0-43b0-b711-458474ab82b8', 'Réserve Secondaire'),
       ('085840f7-3593-423e-885e-f67695f7fbd0', 'Réserve Rapide');

INSERT INTO offre (id, acteur, jour, id_marche)
VALUES ('57407a6d-f5f6-4e72-835a-4b1a1ecd5686', 'Autre Acteur', '2025-06-30', '5f039b79-255d-4a28-aa86-3f91eb69bf65');

INSERT INTO bloc (id, quantite_energie_mw, prix_plancher, position_journee, id_offre)
VALUES ('e422d961-e3a4-48d5-b24e-2fe35dae9486', 8, 200, 0, '57407a6d-f5f6-4e72-835a-4b1a1ecd5686'),
       ('ba6b0908-be42-4730-a80d-a7de72ec21a1', 10, 180, 1, '57407a6d-f5f6-4e72-835a-4b1a1ecd5686'),
       ('519aa934-aacf-466b-ba7b-f0c4abf33059', 10, 180, 2, '57407a6d-f5f6-4e72-835a-4b1a1ecd5686'),
       ('e6dc4e46-0a49-40b9-b7f5-687e15317caa', 9, 190, 3, '57407a6d-f5f6-4e72-835a-4b1a1ecd5686'),
       ('fd36abaf-0058-4741-884f-9434c427f019', 7, 210, 4, '57407a6d-f5f6-4e72-835a-4b1a1ecd5686');

INSERT INTO parc (id, nom, type, capacite_horaire_mw)
VALUES ('fdf74155-3311-4821-bae5-024f6f1be12b', 'Micro-Parc éolien du Jura', 'EOLIEN', 200),
       ('312986b3-30f8-45c1-8a4a-a1e7b4432998', 'Bac à sable avec une hélice', 'EOLIEN', 10),
       ('09e8e366-3f64-4d76-90e0-2366d37447f7', 'Parc des Autres Acteurs', 'HYDRAULIQUE', 16);

INSERT INTO allocation_parc (id, quantite, id_parc, id_bloc)
VALUES ('78f32451-0423-4e66-a116-fef71f23746e', 8, '09e8e366-3f64-4d76-90e0-2366d37447f7',
        'e422d961-e3a4-48d5-b24e-2fe35dae9486'),
       ('a74a33b2-a7ee-4dab-ad34-e5fdc03a3cd3', 10, '09e8e366-3f64-4d76-90e0-2366d37447f7',
        'ba6b0908-be42-4730-a80d-a7de72ec21a1'),
       ('076736f9-901e-44a9-9ca9-3dda1c9e0997', 10, '09e8e366-3f64-4d76-90e0-2366d37447f7',
        '519aa934-aacf-466b-ba7b-f0c4abf33059'),
       ('02323a72-761d-403e-9752-381eaba622b8', 9, '09e8e366-3f64-4d76-90e0-2366d37447f7',
        'e6dc4e46-0a49-40b9-b7f5-687e15317caa'),
       ('3ec10281-ebe3-423e-a6c1-d34bd4268483', 7, '09e8e366-3f64-4d76-90e0-2366d37447f7',
        'fd36abaf-0058-4741-884f-9434c427f019');