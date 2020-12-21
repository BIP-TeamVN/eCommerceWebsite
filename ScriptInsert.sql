USE `eCommerceWebsiteDb`;

INSERT INTO `USER`(`USER_ID`,`FIRST_NAME`, `LAST_NAME`, `GENDER`, `DATE_OF_BIRTH`, `SSN`, `PHONE_NUMBER`, `EMAIL`, `USER_NAME`, `PASSWORD`, `USER_TYPE`, `STATUS`)
VALUES (1, 'Vy', 'Huỳnh', 'Nữ', '20000520', '072355650000', '0936659599', 'vyhuynh@gmail.com', 'admin', '7e7175c2e20d590551e9fb500bc38c8c', 'ADMIN', 1);						-- UID = 1

-- INSERT INTO `USER_ADDRESS`(`USER_ID`, `ADDRESS_ID`) VALUES (1, 1), (1, 2), (1, 3);


INSERT INTO `USER`(`FIRST_NAME`, `LAST_NAME`, `GENDER`, `DATE_OF_BIRTH`, `SSN`, `PHONE_NUMBER`, `EMAIL`, `USER_NAME`, `PASSWORD`, `USER_TYPE`, `STATUS`)
VALUES
    ('Lý', 'Nguyễn Ngọc Thiên', 'Nữ', '20000331', '072356850000', '0965639521', NULL, 'empl', '7e7175c2e20d590551e9fb500bc38c8c', 'EMPLOYEE', 1),							-- UID = 2
	('Giang',  'Lê Trường', 'Nam', '19951203', '596522653964', '0965632521', 'giangle1995@gmail.com', '0965632521', 'e73adf9842e38aab89b6a8b9c8824051', 'EMPLOYEE', 0),		-- UID = 3
	('Minh',  'Mai Sỹ', 'Khác', '19940228', '496229526', '0339566263', 'msm1994@yahoo.com', '0339566263', 'e73adf9842e38aab89b6a8b9c8824051', 'EMPLOYEE', 1),				-- UID = 4
	('Thu',  'Lê Thị', 'Khác', '19980214', '261626546455', '0368465655', 'thult@gmail.com', '0368465655', 'e73adf9842e38aab89b6a8b9c8824051', 'EMPLOYEE', 1),				-- UID = 5
	('Toàn',  'Cao Văn', 'Nam', '19950831', '344643356', '0945641535', 'toancv@outlook.com', '0945641535', 'e73adf9842e38aab89b6a8b9c8824051', 'EMPLOYEE', 0),				-- UID = 6
	('Cúc',  'Nguyễn Thị Thu', 'Nữ', '19941002', '463786434', '0914846315', 'cuntt@hotmail.com', '0914846315', 'e73adf9842e38aab89b6a8b9c8824051', 'EMPLOYEE', 0),			-- UID = 7
	('Việt',  'Trần Quốc', 'Nam', '19970209', '243624766483', '0901316265', 'viettqq@yahoo.com', '0901316265', 'e73adf9842e38aab89b6a8b9c8824051', 'EMPLOYEE', 1),			-- UID = 8
	('Sơn',  'Huỳnh Văn', 'Nam', '19930402', '796289529', '0943325968', 'sonhv@gmail.com', '0943325968', 'e73adf9842e38aab89b6a8b9c8824051', 'EMPLOYEE', 1);				-- UID = 9
    
INSERT INTO `ADDRESS`(`USER_ID`, `STREET`, `COMMUNE_ID`, `DISTRICT_ID`, `PROVINCE_ID`, `FULL_NAME`, `PHONE_NUMBER`, `ADDRESS_NAME`)
VALUES 
	(1, '1 Võ Văn Ngân', '00001', '001', '79', 'Trần Quốc 1', '1111111111', 'Nhà'),
    (1, '2 Đường ABC', '26764', '761', '79', 'Trần Quốc 2', '2222222222', 'Khách Sạn'),
    (1, '3 Đường ABC', '26803', '762', '79', 'Trần Quốc 3', '3333333333', 'Trường'),
    (2, '4 Đường ABC', '26833', '763', '79', 'Trần Quốc 4', '444444444', 'Trọ'),
    (2, '5 Đường ABC', '26767', '761', '79', 'Trần Quốc 1', '1111111111', 'nhà'),
    (3, '6 Đường ABC', '27052', '768', '79', 'Trần Quốc 1', '1111111111', 'Nhà'),
    (3, '7 Đường ABC', '27130', '770', '79', 'Trần Quốc 6', '6666666666', 'Nhà'),
    (3, '8 Đường ABC', '26734', '760', '79', 'Trần Quốc 1', '1111111111', 'Chung cư'),
    (4, '9 Đường ABC', '26923', '765', '79', 'Trần Quốc 1', '1111111111', 'Nhà'),
    (5, '10 Đường XYZ', '26882', '764', '79', 'Trần Quốc 7', '7777777777', 'Nhà'),
    (6, '11 Đường XYZ', '26971', '766', '79', 'Trần Quốc 1', '1111111111', 'Hầm'),
    (7, '12 Đường XYZ', '27058', '768', '79', 'Trần Quốc 7', '7777777777', 'Nhà'),
    (8, '13 Đường XYZ', '27226', '772', '79', 'Trần Quốc 1', '1111111111', 'Nhà'),
    (9, '14 Đường XYZ', '26809', '762', '79', 'Trần Quốc 9', '9999999999', 'Nhà'),
    (9, '15 Đường XYZ', '26929', '765', '79', 'Trần Quốc 1', '1111111111', 'Nhà');


INSERT INTO `EMPLOYEE` (`USER_ID`, `START_DATE`, `SALARY`)
VALUES
	(2, '20181025', 8000000),
	(3, '20181102', 6500000),
	(4, '20190103', 5500000),
	(5, '20190605', 3500000),
	(6, '20190924', 4500000),
	(7, '20191031', 7500000),
	(8, '20191104', 8500000),
	(9, '20200301', 6800000);


INSERT INTO `USER`(`FIRST_NAME`, `LAST_NAME`, `GENDER`, `DATE_OF_BIRTH`, `SSN`, `PHONE_NUMBER`, `EMAIL`, `USER_NAME`, `PASSWORD`, `USER_TYPE`, `STATUS`)
VALUES ('Vy', 'Khánh', 'Nữ', '20000520', '124312341324', '0936659599', 'vyhuynh@gmail.com', 'seller1', '7e7175c2e20d590551e9fb500bc38c8c', 'SELLER', 1);	

INSERT INTO `USER`(`FIRST_NAME`, `LAST_NAME`, `GENDER`, `DATE_OF_BIRTH`, `SSN`, `PHONE_NUMBER`, `EMAIL`, `USER_NAME`, `PASSWORD`, `USER_TYPE`, `STATUS`)
VALUES ('Vy', 'Hoàng', 'Nữ', '20000520', '124312341324', '0936659599', 'vyhuynh@gmail.com', 'seller2', '7e7175c2e20d590551e9fb500bc38c8c', 'SELLER', 1);	

INSERT INTO `ADDRESS`(`USER_ID`, `STREET`, `COMMUNE_ID`, `DISTRICT_ID`, `PROVINCE_ID`, `FULL_NAME`, `PHONE_NUMBER`, `ADDRESS_NAME`)
VALUE (1, '1000 Võ Văn Ngân', '00001', '001', '79', 'Trần Quốc 1', '324234234', 'Khách sạn')





