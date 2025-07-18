INSERT INTO role (id,name)
VALUES (1,"USER"),
 (2,"ADMINISTRATOR");

INSERT INTO user (id,psoeudo,password,is_admin,role_id)
VALUES (1,"user1","$2y$10$rnyU8u79GBfJ05YpmTSNt.K/cPiAaU91Kd3AQc2/unHA1SxOjI3QK",true,1),
 (2,"user2","$2y$10$rnyU8u79GBfJ05YpmTSNt.K/cPiAaU91Kd3AQc2/unHA1SxOjI3QK",false,1),
 (3,"user3","$2y$10$rnyU8u79GBfJ05YpmTSNt.K/cPiAaU91Kd3AQc2/unHA1SxOjI3QK",true,1),
 (4,"user4","$2y$10$rnyU8u79GBfJ05YpmTSNt.K/cPiAaU91Kd3AQc2/unHA1SxOjI3QK",false,1);

INSERT INTO ticket (id,title,description,is_solved,submitter_id)
VALUES (1,"Bug #1","Description bug #1",false,1),
 (2,"Bug #2","Description bug #2",false,2),
 (3,"Bug #3","Description bug #3",true,2),
 (4,"Change Request #1","Description change request  #1",false,4);

INSERT INTO category (id,name)
VALUES (1,"Bug"),
(2,"Change Request"),
(3,"New Feature");

INSERT INTO priority (id,name)
VALUES (1,"Minor"),
(2,"Medium"),
(3,"Major"),
(4,"Bloquant");