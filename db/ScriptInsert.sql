USE `eCommerceWebsiteDb`;

INSERT INTO `USER`(`USER_ID`,`FIRST_NAME`, `LAST_NAME`, `GENDER`, `DATE_OF_BIRTH`, `SSN`, `PHONE_NUMBER`, `EMAIL`, `USER_NAME`, `PASSWORD`, `USER_TYPE`, `STATUS`)
VALUES (1, 'Vy', 'Huỳnh', 'Nữ', '20000520', '072355650000', '0936659599', 'vyhuynh@gmail.com', 'admin', '7e7175c2e20d590551e9fb500bc38c8c', 'ADMIN', 1);						-- UID = 1

-- INSERT INTO `USER_ADDRESS`(`USER_ID`, `ADDRESS_ID`) VALUES (1, 1), (1, 2), (1, 3);


INSERT INTO `USER`(`FIRST_NAME`, `LAST_NAME`, `GENDER`, `DATE_OF_BIRTH`, `SSN`, `PHONE_NUMBER`, `EMAIL`, `USER_NAME`, `PASSWORD`, `USER_TYPE`, `STATUS`)
VALUES
    ('Lý', 'Nguyễn Ngọc Thiên', 'Nữ', '20000331', '072356850000', '0965639521', NULL, 'empl', '7e7175c2e20d590551e9fb500bc38c8c', 'EMPLOYEE', 1),							-- UID = 10000
	('Giang',  'Lê Trường', 'Nam', '19951203', '596522653964', '0965632521', 'giangle1995@gmail.com', '0965632521', 'e73adf9842e38aab89b6a8b9c8824051', 'EMPLOYEE', 0),		-- UID = 10001
	('Minh',  'Mai Sỹ', 'Khác', '19940228', '496229526', '0339566263', 'msm1994@yahoo.com', 'seller', '7e7175c2e20d590551e9fb500bc38c8c', 'SELLER', 1),					-- UID = 10002
	('Thu',  'Lê Thị', 'Khác', '19980214', '261626546455', '0368465655', 'thult@gmail.com', '0368465655', 'e73adf9842e38aab89b6a8b9c8824051', 'SELLER', 1),					-- UID = 10003
	('Toàn',  'Cao Văn', 'Nam', '19950831', '344643356', '0945641535', 'toancv@outlook.com', '0945641535', 'e73adf9842e38aab89b6a8b9c8824051', 'EMPLOYEE', 0),				-- UID = 10004
	('Cúc',  'Nguyễn Thị Thu', 'Nữ', '19941002', '463786434', '0914846315', 'cuntt@hotmail.com', '0914846315', 'e73adf9842e38aab89b6a8b9c8824051', 'EMPLOYEE', 0),			-- UID = 10005
	('Việt',  'Trần Quốc', 'Nam', '19970209', '243624766483', '0901316265', 'viettqq@yahoo.com', '0901316265', 'e73adf9842e38aab89b6a8b9c8824051', 'EMPLOYEE', 1),			-- UID = 10006
	('Sơn',  'Huỳnh Văn', 'Nam', '19930402', '796289529', '0943325968', 'sonhv@gmail.com', '0943325968', 'e73adf9842e38aab89b6a8b9c8824051', 'EMPLOYEE', 1);				-- UID = 10007
    
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
	(10000, '20181025', 8000000),
	(10001, '20181102', 6500000),
	(10002, '20190103', 5500000),
	(10003, '20190605', 3500000),
	(10004, '20190924', 4500000),
	(10005, '20191031', 7500000),
	(10006, '20191104', 8500000),
	(10007, '20200301', 6800000);
  
/* INSERT INTO `BRAND` (`BRAND_NAME`, `BRAND_ORIGIN`)
VALUES 
	('MSI', 'Trung Quốc'),
    ('Apple', 'Mỹ'),
    ('Kim biên', 'Việt Nam'),
    ('No brand', 'no origin'); 

INSERT INTO `SELLER` (`USER_ID`,`STORE_NAME`,`STORE_LINK`,`BUSINESS_LICENSE_ID`,`BANK_ACCOUNT_ID`)
VALUES 
	(10002, 'Store Nam', '/namshop', '456456456456', 123123123),
    (10003, 'Store Nữ', '/nushop', '52345345234243', 3455321231);

INSERT INTO `PRODUCT` (`BRAND_ID`,`SELLER_ID`,`PRODUCT_NAME`,`PRODUCT_RATE`,`PRODUCT_ORIGIN`,`PRODUCT_DESC`,`PRICE_ORIGIN`,`PRICE_ORDER`)
VALUES
	(1, 10002, 'Nokia 1080', 4.5, 'Việt Nam', 'Nghe, Gọi, Chọi', 500000, 499999),
    (1, 10002, 'Ốp lưng A5', 4.7, 'Việt Nam', 'Bảo vệ điện thoại', 40000, 39999),
    (2, 10003, 'Airpro', 1.1, 'Việt Nam', 'Nghe', 500000, 3999);

INSERT INTO `PRODUCT_TYPE` (`PRODUCT_TYPE_NAME`, `PRODUCT_ID`, `QUANTITY`)
VALUES 
	('Xanh', 1, 90),
    ('Đỏ', 1, 34),
    ('Tim', 1, 23);

INSERT INTO `RATE_COMMENT` (`RATE_START`, `COMMENT`, `NO_OF_LIKE`, `NO_OF_DISLIKE`, `USER_ID`, `PRODUCT_ID`)
VALUES 
	(4, 'Tạm được', 4, 1, 1, 1),
    (5, 'Cũng được', 2, 7, 2, 1),
    (4, 'Cố nhìn thì cũng được', 3, 6, 1, 3);
    
    
INSERT INTO `REPLY_COMMENT` (`RATE_COMMENT_ID`, `COMMENT`, `NO_OF_LIKE`, `NO_OF_DISLIKE`, `USER_ID`)
VALUES 
	(1, 'thiệt không bạn', 1, 2, 3),
    (1, 'Vậy hả', 1, 2, 4),
    (2, 'Haha', 2, 4, 5); */
    
INSERT INTO `DISCOUNT` (`DISCOUNT_CODE`, `DISCOUNT_VALUE`, `DISCOUNT_MAX_VALUE`)
VALUES 
	('VANCHUYEN', 0, 15000),
    ('HOANTIEN', 10000, 20000);

INSERT INTO `DISCOUNT` (`DISCOUNT_CODE`, `DISCOUNT_VALUE`, `DISCOUNT_MAX_VALUE`)
VALUES 
	('VANCHUYEN', 0, 15000),
    ('HOANTIEN', 10000, 20000);

INSERT INTO `USER`(`FIRST_NAME`, `LAST_NAME`, `GENDER`, `DATE_OF_BIRTH`, `SSN`, `PHONE_NUMBER`, `EMAIL`, `USER_NAME`, `PASSWORD`, `USER_TYPE`, `STATUS`)
VALUES
    ('Lý', 'Nguyễn Khách', 'Nữ', '20000331', '072356850000', '0965639521', NULL, 'empl', '7e7175c2e20d590551e9fb500bc38c8c', 'CUSTOMER', 1),		-- 10008		
	('Giang',  'Lê Hàng', 'Nam', '19951203', '596522653964', '0965632521', 'giangle1995@gmail.com', '0965632521', 'e73adf9842e38aab89b6a8b9c8824051', 'CUSTOMER', 1);-- 10009
    
INSERT INTO `CUSTOMER`(`USER_ID`, `REGISTER_DATE`)
VALUES
    (10008, '20201023'),							
	(10009, '20191214');
    
INSERT INTO `ADDRESS`(`USER_ID`, `STREET`, `COMMUNE_ID`, `DISTRICT_ID`, `PROVINCE_ID`, `FULL_NAME`, `PHONE_NUMBER`, `ADDRESS_NAME`)
VALUES 
	(10001, '1 Võ Văn Ngân', '00001', '001', '79', 'Trần Quốc 1', '1111111111', 'Nhà'),
    (10000, '34 Đường ABC', '26764', '761', '79', 'Trần Quốc 2', '2222222222', 'Khách Sạn'),
    (10008, '54 Đường ABC', '26803', '762', '79', 'Trần Quốc 3', '3333333333', 'Trường'),
    (10008, '12/23 Đường ABC', '26833', '763', '79', 'Trần Quốc 4', '444444444', 'Trọ'),
    (10009, '12/3 Đường ABC', '26767', '761', '79', 'Trần Quốc 1', '1111111111', 'nhà');
    
INSERT INTO `BILL`(`CUSTOMER_ID`, `ADDRESS_ID`, `DISCOUNT_ID`)
VALUES
    (10008, 18, 1),							
	(10008, 17, 2),
    (10009, 20, 1);
    
INSERT INTO `BILL_DETAIL` (`BILL_ID`, `PRODUCT_ID`, `QUANTITY`)
VALUES
    (1, 1, 2),							
	(2, 2, 3),
    (2, 1, 4);

INSERT INTO `BILL`(`CUSTOMER_ID`, `ADDRESS_ID`, `DISCOUNT_ID`)
VALUES
    (10008, 18, 1),							
	(10008, 19, 2),
    (10009, 20, 1);
    
INSERT INTO `BILL_DETAIL` (`BILL_ID`, `PRODUCT_TYPE_ID`, `QUANTITY`)
VALUES
    (23, 3, 5),							
	(24, 2, 1),
    (25, 1, 5),
    (26, 3, 5);
    

INSERT INTO `CATEGORIES_FOR_PRODUCTS` (`PRODUCT_ID`, `PRODUCT_CATEGORY_ID`)
VALUES
    (1, 1),							
	(1, 2),
    (1, 3),
    (2, 1),							
	(3, 2),
    (3, 1);
 
 
INSERT INTO `USER`(`FIRST_NAME`, `LAST_NAME`, `GENDER`, `DATE_OF_BIRTH`, `SSN`, `PHONE_NUMBER`, `EMAIL`, `USER_NAME`, `PASSWORD`, `USER_TYPE`, `STATUS`)
VALUES
    ('Giao', 'Nguyễn Khách', 'Nữ', '20000331', '072356850000', '0965639521', NULL, 'giaohang1', '7e7175c2e20d590551e9fb500bc38c8c', 'DELIVERY', 1),	-- 10010					
	('Hàng',  'Lê Hàng', 'Nam', '19951203', '596522653964', '0965632521', 'hang5@gmail.com', 'giaohang2', 'e73adf9842e38aab89b6a8b9c8824051', 'DELIVERY', 1); -- 10011

INSERT INTO `DELIVERY` (`USER_ID`, `START_DATE`, `SALARY`)
VALUES
	(10010, '20181025', 8000000),
	(10011, '20181102', 6500000);



