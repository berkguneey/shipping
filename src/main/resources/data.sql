INSERT INTO "PUBLIC"."VEHICLE" VALUES 
(X'788597922c6445168a768faf80d053cc', TIMESTAMP '2022-06-03 18:02:42.323839', TIMESTAMP '2022-06-03 18:02:42.323839', '34AAA12', 'HONDA'),
(X'0beb9a83ac01465d8f3afe1ee6b74981', TIMESTAMP '2022-06-03 18:02:50.782351', TIMESTAMP '2022-06-03 18:02:50.782351', '34ABB13', 'SCANIA'),
(X'db9dc1e1ef734e45b27210d257fb28f6', TIMESTAMP '2022-06-03 18:02:53.363513', TIMESTAMP '2022-06-03 18:02:53.363513', '34ACC14', 'RENAULT'),
(X'13b70ff6145541549e59013409da50dc', TIMESTAMP '2022-06-03 18:02:57.242768', TIMESTAMP '2022-06-03 18:02:57.242768', '34ADD14', 'VOLVO');

INSERT INTO "PUBLIC"."DELIVERY_POINT" VALUES
((VALUES NEXT VALUE FOR "PUBLIC"."ID_SEQ"), TIMESTAMP '2022-06-03 18:08:14.150725', TIMESTAMP '2022-06-03 18:08:14.150725', 'Branch'),
((VALUES NEXT VALUE FOR "PUBLIC"."ID_SEQ"), TIMESTAMP '2022-06-03 18:08:30.906069', TIMESTAMP '2022-06-03 18:08:30.906069', 'Distribution Center'),
((VALUES NEXT VALUE FOR "PUBLIC"."ID_SEQ"), TIMESTAMP '2022-06-03 18:08:40.744809', TIMESTAMP '2022-06-03 18:08:40.744809', 'Transfer Center');

INSERT INTO "PUBLIC"."BAG" VALUES
(X'14b863be9cfe48fa8d95c0683d7c5a21', TIMESTAMP '2022-06-03 18:10:54.460516', TIMESTAMP '2022-06-03 18:10:54.460516', 'C725799', 1, 2),
(X'1bb4a44eab4b46f39988d1a4dffc629a', TIMESTAMP '2022-06-03 18:11:03.078605', TIMESTAMP '2022-06-03 18:11:03.078605', 'C725800', 1, 3);

INSERT INTO "PUBLIC"."PACKAGE" VALUES
(X'4da505ebbb7a4ba9a4ceb9b48e1e0a3d', TIMESTAMP '2022-06-03 18:11:24.367911', TIMESTAMP '2022-06-03 18:11:24.367911', 'P7988000121', 1, 5, NULL, 1),
(X'556fd9dc064a4d07ba7fb249e22a012b', TIMESTAMP '2022-06-03 18:11:30.260656', TIMESTAMP '2022-06-03 18:11:30.260656', 'P7988000122', 1, 5, NULL, 1),
(X'fa3c8c0c2cf24e24b72156812184b4ab', TIMESTAMP '2022-06-03 18:11:39.253559', TIMESTAMP '2022-06-03 18:11:39.253559', 'P7988000123', 1, 9, NULL, 1),
(X'fb2ef160a1e641fa94931ffa7cfc95ae', TIMESTAMP '2022-06-03 18:12:06.631888', TIMESTAMP '2022-06-03 18:12:06.631888', 'P8988000120', 1, 33, NULL, 2),
(X'4f67110c9791405890b4bc551e165246', TIMESTAMP '2022-06-03 18:12:16.632343', TIMESTAMP '2022-06-03 18:12:16.632343', 'P8988000121', 1, 17, NULL, 2),
(X'343aaff0baff4987afc9ebefa54c7d26', TIMESTAMP '2022-06-03 18:12:26.813384', TIMESTAMP '2022-06-03 18:17:06.504757', 'P8988000122', 2, 26, X'14b863be9cfe48fa8d95c0683d7c5a21', 2),
(X'ccc3e469cef7492683decc1978561ca4', TIMESTAMP '2022-06-03 18:12:37.950204', TIMESTAMP '2022-06-03 18:12:37.950204', 'P8988000123', 1, 35, NULL, 2),
(X'27579499eebf4bceaafeee8e3639dd2f', TIMESTAMP '2022-06-03 18:12:48.894467', TIMESTAMP '2022-06-03 18:12:48.894467', 'P8988000124', 1, 1, NULL, 2),
(X'fa9543adb17c4c85af548bfae6b7db05', TIMESTAMP '2022-06-03 18:13:02.533682', TIMESTAMP '2022-06-03 18:13:02.533682', 'P8988000125', 1, 200, NULL, 2),
(X'cf32c2666699411696e7b9f09c166b46', TIMESTAMP '2022-06-03 18:13:11.909763', TIMESTAMP '2022-06-03 18:17:27.761413', 'P8988000126', 2, 50, X'14b863be9cfe48fa8d95c0683d7c5a21', 2),
(X'7912b92759574fe4879905c92d0839ba', TIMESTAMP '2022-06-03 18:13:32.818535', TIMESTAMP '2022-06-03 18:13:32.818535', 'P9988000126', 1, 15, NULL, 3),
(X'c4f82e863bda4a2c86e2f70a7cac5932', TIMESTAMP '2022-06-03 18:13:39.861638', TIMESTAMP '2022-06-03 18:13:39.861638', 'P9988000127', 1, 16, NULL, 3),
(X'f219d584d7a743d3ae954268ef10b013', TIMESTAMP '2022-06-03 18:13:45.139107', TIMESTAMP '2022-06-03 18:17:44.827774', 'P9988000128', 2, 55, X'1bb4a44eab4b46f39988d1a4dffc629a', 3),
(X'2d26a041652c450b92aac60c03a7e1b8', TIMESTAMP '2022-06-03 18:13:51.282144', TIMESTAMP '2022-06-03 18:17:55.708202', 'P9988000129', 2, 28, X'1bb4a44eab4b46f39988d1a4dffc629a', 3),
(X'7fc44b863574496696e9d045129caa23', TIMESTAMP '2022-06-03 18:13:58.108302', TIMESTAMP '2022-06-03 18:13:58.108302', 'P9988000130', 1, 17, NULL, 3);