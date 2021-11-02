drop database if exists aniverse;
CREATE DATABASE IF NOT EXISTS aniverse;
USE aniverse;

-- Center Table Create SQL
CREATE TABLE Center
(
    `centerIdx`       INT            NOT NULL    AUTO_INCREMENT COMMENT '보호소 인덱스',
    `centerName`      VARCHAR(45)    NOT NULL    COMMENT '보호소 이름',
    `centerAddress`   VARCHAR(45)    NULL        COMMENT '보호소 주소',
    `centerPhoneNum`  VARCHAR(45)    NULL        COMMENT '보호소 연락처',
    `createdAt`       TIMESTAMP      NULL        DEFAULT CURRENT_TIMESTAMP COMMENT '보호소 생성',
    `updatedAt`       TIMESTAMP      NULL        DEFAULT CURRENT_TIMESTAMP COMMENT '보호소 업데이트',
    `status`          CHAR(1)        NULL        DEFAULT 'Y' COMMENT 'Y : 유지 N : 삭제',
    CONSTRAINT PK_Center PRIMARY KEY (centerIdx)
);

ALTER TABLE Center COMMENT '보호소 테이블';


-- User Table Create SQL
CREATE TABLE User
(
    `userIdx`       INT             NOT NULL    AUTO_INCREMENT COMMENT '사용자 인덱스',
    `userId`        VARCHAR(45)     NOT NULL    COMMENT '아이디',
    `userPassword`  VARCHAR(100)    NOT NULL    COMMENT '비밀번호',
    `userName`      VARCHAR(45)     NOT NULL    COMMENT '닉네임',
    `userAddress`   VARCHAR(100)    NULL        COMMENT '주소',
    `userPhoneNum`  VARCHAR(45)     NULL        COMMENT '전화번호',
    `userAuth`      CHAR(1)         NOT NULL    COMMENT 'U : 일반유저, C : 센터 관리자, M : 마켓 관리자',
    `userJellyNum`  INT             NULL        DEFAULT 0 COMMENT '젤리 개수',
    `createdAt`     TIMESTAMP       NULL        DEFAULT CURRENT_TIMESTAMP COMMENT '사용자 생성',
    `updatedAt`     TIMESTAMP       NULL        DEFAULT CURRENT_TIMESTAMP COMMENT '사용자 업데이트',
    `status`        CHAR(1)         NULL        DEFAULT 'Y' COMMENT 'Y : 유지, N : 탈퇴',
    CONSTRAINT PK_User PRIMARY KEY (userIdx)
);

ALTER TABLE User COMMENT '사용자 테이블';


-- Animal Table Create SQL
CREATE TABLE Animal
(
    `animalIdx`             INT              NOT NULL    AUTO_INCREMENT COMMENT '동물 인덱스',
    `centerIdx`             INT              NOT NULL    COMMENT '보호소 인덱스',
    `animalSpecies`         VARCHAR(45)      NULL        COMMENT '동물 종',
    `animalName`            VARCHAR(45)      NULL        COMMENT '동물 이름',
    `animalAge`             VARCHAR(45)      NULL        COMMENT '동물 나이',
    `animalGender`          CHAR(1)          NULL        COMMENT 'F : 여, M : 남',
    `animalWeight`          INT              NULL        COMMENT '동물 무게',
    `animalNeutralization`  CHAR(1)          NULL        DEFAULT 'Y' COMMENT 'Y : 완료, N : 미완',
    `animalVaccinated`      VARCHAR(1000)    NULL        COMMENT '접종 여부',
    `animalDiseases`        VARCHAR(1000)    NULL        COMMENT '병력 여부',
    `animalFind`            VARCHAR(1000)    NULL        COMMENT '발견 장소',
    `animalIntro`           VARCHAR(1000)    NULL        COMMENT '특성, 성격',
    `createdAt`             TIMESTAMP        NULL        DEFAULT CURRENT_TIMESTAMP COMMENT '동물 생성',
    `updatedAt`             TIMESTAMP        NULL        DEFAULT CURRENT_TIMESTAMP COMMENT '동물 업데이트',
    `status`                CHAR(1)          NULL        DEFAULT 'N' COMMENT 'A : 입양중, P : 보호중, F : 펀딩중, N : None',
     PRIMARY KEY (animalIdx)
);

ALTER TABLE Animal COMMENT '동물 테이블';

ALTER TABLE Animal
    ADD CONSTRAINT FK_Animal_centerIdx_Center_centerIdx FOREIGN KEY (centerIdx)
        REFERENCES Center (centerIdx) ON DELETE RESTRICT ON UPDATE RESTRICT;


-- AdoptList Table Create SQL
CREATE TABLE AdoptList
(
    `adoptListIdx`    INT              NOT NULL    AUTO_INCREMENT COMMENT '입양글 등록 인덱스',
    `animalIdx`       INT              NOT NULL    COMMENT '동물 인덱스',
    `userIdx`         INT              NOT NULL    COMMENT '등록자 인덱스',
    `adoptCondition`  VARCHAR(1000)    NULL        COMMENT '입양 조건',
    `adoptEtc`        VARCHAR(1000)    NULL        COMMENT '기타 사항',
    `createdAt`       TIMESTAMP        NULL        DEFAULT CURRENT_TIMESTAMP COMMENT '등록 생성',
    `updatedAt`       TIMESTAMP        NULL        DEFAULT CURRENT_TIMESTAMP COMMENT '등록 업데이트',
    `status`          CHAR(1)          NULL        DEFAULT 'Y' COMMENT 'Y : 유지,  S : 입양완료,  N : 삭제',
     PRIMARY KEY (adoptListIdx)
);

ALTER TABLE AdoptList COMMENT '입양글 등록 테이블';

ALTER TABLE AdoptList
    ADD CONSTRAINT FK_AdoptList_animalIdx_Animal_animalIdx FOREIGN KEY (animalIdx)
        REFERENCES Animal (animalIdx) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE AdoptList
    ADD CONSTRAINT FK_AdoptList_userIdx_User_userIdx FOREIGN KEY (userIdx)
        REFERENCES User (userIdx) ON DELETE RESTRICT ON UPDATE RESTRICT;


-- AdoptRequest Table Create SQL
CREATE TABLE AdoptRequest
(
    `adoptRequestIdx`  INT              NOT NULL    AUTO_INCREMENT COMMENT '입양 신청 인덱스',
    `adoptListIdx`     INT              NOT NULL    COMMENT '입양글 인덱스',
    `userIdx`          INT              NOT NULL    COMMENT '신청자 인덱스',
    `adoptComment`     VARCHAR(1000)    NULL        COMMENT '남기는말',
    `adoptDate`        TIMESTAMP        NULL        DEFAULT CURRENT_TIMESTAMP COMMENT '입양 완료 날짜',
    `createdAt`        TIMESTAMP        NULL        DEFAULT CURRENT_TIMESTAMP COMMENT '신청 생성',
    `updatedAt`        TIMESTAMP        NULL        DEFAULT CURRENT_TIMESTAMP COMMENT '신청 업데이트',
    `status`           CHAR(1)          NULL        DEFAULT 'Y' COMMENT 'Y : 유지, S : 입양 완료, F : 입양 실패, N : 삭제',
     PRIMARY KEY (adoptRequestIdx)
);

ALTER TABLE AdoptRequest COMMENT '입양 신청 테이블';

ALTER TABLE AdoptRequest
    ADD CONSTRAINT FK_AdoptRequest_adoptListIdx_AdoptList_adoptListIdx FOREIGN KEY (adoptListIdx)
        REFERENCES AdoptList (adoptListIdx) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE AdoptRequest
    ADD CONSTRAINT FK_AdoptRequest_userIdx_User_userIdx FOREIGN KEY (userIdx)
        REFERENCES User (userIdx) ON DELETE RESTRICT ON UPDATE RESTRICT;


-- Market Table Create SQL
CREATE TABLE Market
(
    `marketIdx`       INT             NOT NULL    AUTO_INCREMENT COMMENT '마켓 인덱스',
    `userIdx`         INT             NOT NULL    COMMENT '사용자 인덱스',
    `marketName`      VARCHAR(45)     NOT NULL    COMMENT '마켓 이름',
    `marketIntro`     VARCHAR(100)    NULL        COMMENT '마켓 소개',
    `marketAddress`   VARCHAR(100)    NULL        COMMENT '마켓 주소',
    `marketPhoneNum`  VARCHAR(45)     NULL        COMMENT '마켓 문의번호',
    `createdAt`       TIMESTAMP       NULL        DEFAULT CURRENT_TIMESTAMP COMMENT '마켓 생성',
    `updatedAt`       TIMESTAMP       NULL        DEFAULT CURRENT_TIMESTAMP COMMENT '마켓 업데이트',
    `status`          CHAR(1)         NULL        DEFAULT 'Y' COMMENT 'Y : 유지, N  : 삭제',
     PRIMARY KEY (marketIdx)
);

ALTER TABLE Market COMMENT '마켓 테이블';

ALTER TABLE Market
    ADD CONSTRAINT FK_Market_userIdx_User_userIdx FOREIGN KEY (userIdx)
        REFERENCES User (userIdx) ON DELETE RESTRICT ON UPDATE RESTRICT;


-- Category Table Create SQL
CREATE TABLE Category
(
    `categoryIdx`   INT            NOT NULL    AUTO_INCREMENT COMMENT '카테고리 인덱스',
    `categoryName`  VARCHAR(45)    NOT NULL    COMMENT '카테고리 이름',
    `createdAt`     TIMESTAMP      NULL        DEFAULT CURRENT_TIMESTAMP COMMENT '카테고리 생성',
    `updatedAt`     TIMESTAMP      NULL        DEFAULT CURRENT_TIMESTAMP COMMENT '카테고리 업데이트',
    `status`        CHAR(1)        NULL        DEFAULT 'Y' COMMENT 'Y : 유지, N : 삭제',
     PRIMARY KEY (categoryIdx)
);

ALTER TABLE Category COMMENT '카테고리 테이블';


-- Funding Table Create SQL
CREATE TABLE Funding
(
    `fundingIdx`      INT             NOT NULL    AUTO_INCREMENT COMMENT '펀딩 업로드 인덱스',
    `userIdx`         INT             NOT NULL    COMMENT '사용자 인덱스',
    `animalIdx`       INT             NOT NULL    COMMENT '동물 인덱스',
    `fundingPurpose`  VARCHAR(100)    NULL        COMMENT '펀딩 목적',
    `fundingAmount`   INT             NOT NULL    COMMENT '펀딩 금액',
    `fundingPeriod`   DATETIME        NULL        COMMENT 'DATETIME - CURRENT_TIME 계산',
    `createdAt`       TIMESTAMP       NULL        DEFAULT CURRENT_TIMESTAMP COMMENT '펀딩 생성',
    `updatedAt`       TIMESTAMP       NULL        DEFAULT CURRENT_TIMESTAMP COMMENT '펀딩 업데이트',
    `status`          CHAR(1)         NULL        DEFAULT 'Y' COMMENT 'Y : 유지, S : 마감-성공,  F : 마감-실패, N : 삭제',
     PRIMARY KEY (fundingIdx)
);

ALTER TABLE Funding COMMENT '모금 업로드 테이블';

ALTER TABLE Funding
    ADD CONSTRAINT FK_Funding_userIdx_User_userIdx FOREIGN KEY (userIdx)
        REFERENCES User (userIdx) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE Funding
    ADD CONSTRAINT FK_Funding_animalIdx_Animal_animalIdx FOREIGN KEY (animalIdx)
        REFERENCES Animal (animalIdx) ON DELETE RESTRICT ON UPDATE RESTRICT;


-- FundingReview Table Create SQL
CREATE TABLE FundingReview
(
    `fundingReviewIdx`   INT              NOT NULL    AUTO_INCREMENT COMMENT '펀딩 후기 인덱스',
    `fundingIdx`         INT              NOT NULL    COMMENT '펀딩 인덱스',
    `userIdx`            INT              NOT NULL    COMMENT '유저 인덱스',
    `fundingReviewText`  VARCHAR(1000)    NULL        COMMENT '후기 내용',
    `createdAt`          TIMESTAMP        NULL        DEFAULT CURRENT_TIMESTAMP COMMENT '후기 생성',
    `updatedAt`          TIMESTAMP        NULL        DEFAULT CURRENT_TIMESTAMP COMMENT '후기 업데이트',
    `status`             CHAR(1)          NULL        DEFAULT 'Y' COMMENT 'Y : 유지, N : 삭제',
     PRIMARY KEY (fundingReviewIdx)
);

ALTER TABLE FundingReview COMMENT '펀딩 후기 테이블';

ALTER TABLE FundingReview
    ADD CONSTRAINT FK_FundingReview_fundingIdx_Funding_fundingIdx FOREIGN KEY (fundingIdx)
        REFERENCES Funding (fundingIdx) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE FundingReview
    ADD CONSTRAINT FK_FundingReview_userIdx_User_userIdx FOREIGN KEY (userIdx)
        REFERENCES User (userIdx) ON DELETE RESTRICT ON UPDATE RESTRICT;


-- ProtectList Table Create SQL
CREATE TABLE ProtectList
(
    `protectListIdx`    INT              NOT NULL    AUTO_INCREMENT COMMENT '임시보호글 인덱스',
    `animalIdx`         INT              NOT NULL    COMMENT '동물 인덱스',
    `userIdx`           INT              NOT NULL    COMMENT '등록자 인덱스',
    `protectDateStart`  DATE             NULL        COMMENT '시작날짜',
    `protectDateEnd`    DATE             NULL        COMMENT '끝날짜',
    `protectCondition`  VARCHAR(1000)    NULL        COMMENT '보호조건',
    `createdAt`         TIMESTAMP        NULL        DEFAULT CURRENT_TIMESTAMP COMMENT '등록 생성',
    `updatedAt`         TIMESTAMP        NULL        DEFAULT CURRENT_TIMESTAMP COMMENT '등록 업데이트',
    `status`            CHAR(1)          NULL        DEFAULT 'Y' COMMENT 'Y : 유지,  S : 보호완료,  N : 삭제',
     PRIMARY KEY (protectListIdx)
);

ALTER TABLE ProtectList COMMENT '임시보호글 등록 테이블';

ALTER TABLE ProtectList
    ADD CONSTRAINT FK_ProtectList_animalIdx_Animal_animalIdx FOREIGN KEY (animalIdx)
        REFERENCES Animal (animalIdx) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE ProtectList
    ADD CONSTRAINT FK_ProtectList_userIdx_User_userIdx FOREIGN KEY (userIdx)
        REFERENCES User (userIdx) ON DELETE RESTRICT ON UPDATE RESTRICT;


-- AdoptReview Table Create SQL
CREATE TABLE AdoptReview
(
    `adoptReviewIdx`   INT              NOT NULL    AUTO_INCREMENT COMMENT '입양 후기 인덱스',
    `adoptRequestIdx`  INT              NOT NULL    COMMENT '입양 신청 인덱스',
    `adoptReviewText`  VARCHAR(1000)    NOT NULL    COMMENT '후기 내용',
    `createdAt`        TIMESTAMP        NULL        DEFAULT CURRENT_TIMESTAMP COMMENT '후기 생성',
    `updatedAt`        TIMESTAMP        NULL        DEFAULT CURRENT_TIMESTAMP COMMENT '후기 업데이트',
    `status`           CHAR(1)          NULL        DEFAULT 'Y' COMMENT 'Y : 유지, N : 삭제',
     PRIMARY KEY (adoptReviewIdx)
);

ALTER TABLE AdoptReview COMMENT '입양 후기 테이블';

ALTER TABLE AdoptReview
    ADD CONSTRAINT FK_AdoptReview_adoptRequestIdx_AdoptRequest_adoptRequestIdx FOREIGN KEY (adoptRequestIdx)
        REFERENCES AdoptRequest (adoptRequestIdx) ON DELETE RESTRICT ON UPDATE RESTRICT;


-- Product Table Create SQL
CREATE TABLE Product
(
    `productIdx`    INT              NOT NULL    AUTO_INCREMENT COMMENT '상품 인덱스',
    `marketIdx`     INT              NOT NULL    COMMENT '마켓 인덱스',
    `categoryIdx`   INT              NOT NULL    COMMENT '카테고리 인덱스',
    `productName`   VARCHAR(45)      NOT NULL    COMMENT '상품 이름',
    `productPrice`  INT              NOT NULL    COMMENT '상품 가격',
    `productIntro`  VARCHAR(1000)    NULL        COMMENT '상품 소개',
    `productImage`  VARCHAR(100)     NULL        COMMENT '상품 사진',
    `createdAt`     TIMESTAMP        NULL        DEFAULT CURRENT_TIMESTAMP COMMENT '상품 생성',
    `updatedAt`     TIMESTAMP        NULL        DEFAULT CURRENT_TIMESTAMP COMMENT '상품 업데이트',
    `status`        CHAR(1)          NULL        DEFAULT 'Y' COMMENT 'Y : 유지, S : 품절, N : 삭제',
     PRIMARY KEY (productIdx)
);

ALTER TABLE Product COMMENT '마켓 상품 테이블';

ALTER TABLE Product
    ADD CONSTRAINT FK_Product_marketIdx_Market_marketIdx FOREIGN KEY (marketIdx)
        REFERENCES Market (marketIdx) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE Product
    ADD CONSTRAINT FK_Product_categoryIdx_Category_categoryIdx FOREIGN KEY (categoryIdx)
        REFERENCES Category (categoryIdx) ON DELETE RESTRICT ON UPDATE RESTRICT;


-- adoptReviewFile Table Create SQL
CREATE TABLE adoptReviewFile
(
    `adoptReviewFileIdx`   INT             NOT NULL    AUTO_INCREMENT COMMENT '후기 파일 인덱스',
    `adoptReviewIdx`       INT             NOT NULL    COMMENT '후기글 인덱스',
    `adoptReviewFileSort`  VARCHAR(45)     NULL        DEFAULT 'P' COMMENT 'P : 사진, D : 문서',
    `adoptReviewFile`      VARCHAR(100)    NOT NULL    COMMENT '파일 링크',
    `createdAt`            TIMESTAMP       NULL        DEFAULT CURRENT_TIMESTAMP COMMENT '파일 생성',
    `updatedAt`            TIMESTAMP       NULL        DEFAULT CURRENT_TIMESTAMP COMMENT '파일 업데이트',
    `status`               CHAR(1)         NULL        DEFAULT 'Y' COMMENT 'Y : 유지, N : 삭제',
     PRIMARY KEY (adoptReviewFileIdx)
);

ALTER TABLE adoptReviewFile COMMENT '입양 후기 파일 테이블 (사진, 문서 저장)';

ALTER TABLE adoptReviewFile
    ADD CONSTRAINT FK_adoptReviewFile_adoptReviewIdx_AdoptReview_adoptReviewIdx FOREIGN KEY (adoptReviewIdx)
        REFERENCES AdoptReview (adoptReviewIdx) ON DELETE RESTRICT ON UPDATE RESTRICT;


-- ProtectRequest Table Create SQL
CREATE TABLE ProtectRequest
(
    `protectRequestIdx`  INT              NOT NULL    AUTO_INCREMENT COMMENT '보호 신청 인덱스',
    `protectListIdx`     INT              NOT NULL    COMMENT '임시보호글 인덱스',
    `userIdx`            INT              NOT NULL    COMMENT '신청자 인덱스',
    `protectComment`     VARCHAR(1000)    NULL        COMMENT '남기는말',
    `createdAt`          TIMESTAMP        NULL        DEFAULT CURRENT_TIMESTAMP COMMENT '신청 생성',
    `updatedAt`          TIMESTAMP        NULL        DEFAULT CURRENT_TIMESTAMP COMMENT '신청 업데이트',
    `status`             CHAR(1)          NULL        DEFAULT 'Y' COMMENT 'Y : 유지, S : 보호 완료, F : 입양 실패, N : 삭제',
     PRIMARY KEY (protectRequestIdx)
);

ALTER TABLE ProtectRequest COMMENT '임시보호 신청 테이블';

ALTER TABLE ProtectRequest
    ADD CONSTRAINT FK_ProtectRequest_protectListIdx_ProtectList_protectListIdx FOREIGN KEY (protectListIdx)
        REFERENCES ProtectList (protectListIdx) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE ProtectRequest
    ADD CONSTRAINT FK_ProtectRequest_userIdx_User_userIdx FOREIGN KEY (userIdx)
        REFERENCES User (userIdx) ON DELETE RESTRICT ON UPDATE RESTRICT;


-- DonateJelly Table Create SQL
CREATE TABLE DonateJelly
(
    `donateJellyIdx`  INT          NOT NULL    AUTO_INCREMENT COMMENT '젤리 기부 인덱스',
    `fundingIdx`      INT          NOT NULL    COMMENT '펀딩 인덱스',
    `userIdx`         INT          NOT NULL    COMMENT '사용자 인덱스',
    `donateJellyNum`  INT          NOT NULL    COMMENT '기부 젤리 개수',
    `createdAt`       TIMESTAMP    NULL        DEFAULT CURRENT_TIMESTAMP COMMENT '기부 생성',
    `updatedAt`       TIMESTAMP    NULL        DEFAULT CURRENT_TIMESTAMP COMMENT '기부 업데이트',
    `status`          CHAR(1)      NULL        COMMENT 'Y : 유지, N : 삭제',
     PRIMARY KEY (donateJellyIdx)
);

ALTER TABLE DonateJelly COMMENT '젤리 기부 테이블';

ALTER TABLE DonateJelly
    ADD CONSTRAINT FK_DonateJelly_fundingIdx_Funding_fundingIdx FOREIGN KEY (fundingIdx)
        REFERENCES Funding (fundingIdx) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE DonateJelly
    ADD CONSTRAINT FK_DonateJelly_userIdx_User_userIdx FOREIGN KEY (userIdx)
        REFERENCES User (userIdx) ON DELETE RESTRICT ON UPDATE RESTRICT;


-- FundingReviewFile Table Create SQL
CREATE TABLE FundingReviewFile
(
    `fundingReviewFileIdxg`  INT             NOT NULL    AUTO_INCREMENT COMMENT '후기 파일 인덱스',
    `fundingReviewIdx`       INT             NOT NULL    COMMENT '입양 후기 인덱스',
    `fundingReviewFileSort`  CHAR(1)         NULL        DEFAULT 'P' COMMENT 'P : 사진, D : 문서',
    `fundingReviewFile`      VARCHAR(100)    NOT NULL    COMMENT '파일 링크',
    `createdAt`              TIMESTAMP       NULL        DEFAULT CURRENT_TIMESTAMP COMMENT '파일 생성',
    `updatedAt`              TIMESTAMP       NULL        DEFAULT CURRENT_TIMESTAMP COMMENT '파일 업데이트',
    `status`                 CHAR(1)         NULL        DEFAULT 'Y' COMMENT 'Y : 유지, N : 삭제',
     PRIMARY KEY (fundingReviewFileIdxg)
);

ALTER TABLE FundingReviewFile COMMENT '펀딩 후기 파일(사진, 문서)';

ALTER TABLE FundingReviewFile
    ADD CONSTRAINT FK_FundingReviewFile_fundingReviewIdx_FundingReview_fundingRevie FOREIGN KEY (fundingReviewIdx)
        REFERENCES FundingReview (fundingReviewIdx) ON DELETE RESTRICT ON UPDATE RESTRICT;


-- PurchaseJelly Table Create SQL
CREATE TABLE PurchaseJelly
(
    `purchaseJellyIdx`  INT            NOT NULL    AUTO_INCREMENT COMMENT '젤리 구매 인덱스',
    `userIdx`           INT            NOT NULL    COMMENT '사용자 인덱스',
    `purchaseJellyNum`  INT            NOT NULL    COMMENT '젤리 구매 개수',
    `purchaseJellyWay`  VARCHAR(45)    NULL        COMMENT '수정필요',
    `createdAt`         TIMESTAMP      NULL        DEFAULT CURRENT_TIMESTAMP COMMENT '구매 생성',
    `updatedAt`         TIMESTAMP      NULL        DEFAULT CURRENT_TIMESTAMP COMMENT '구매 업데이트',
    `status`            CHAR(1)        NULL        COMMENT 'Y : 유지, N : 삭제',
     PRIMARY KEY (purchaseJellyIdx)
);

ALTER TABLE PurchaseJelly COMMENT '젤리 구매 테이블 (미완성)';

ALTER TABLE PurchaseJelly
    ADD CONSTRAINT FK_PurchaseJelly_userIdx_User_userIdx FOREIGN KEY (userIdx)
        REFERENCES User (userIdx) ON DELETE RESTRICT ON UPDATE RESTRICT;


-- PurchaseProduct Table Create SQL
CREATE TABLE PurchaseProduct
(
    `purchaseProductIdx`   INT            NOT NULL    AUTO_INCREMENT COMMENT '상품 구매 인덱스',
    `productIdx`           VARCHAR(45)    NOT NULL    COMMENT '상품 인덱스',
    `userIdx`              VARCHAR(45)    NOT NULL    COMMENT '구매자 인덱스',
    `purchasjeProductWay`  VARCHAR(45)    NULL        COMMENT '상품 구매 방법',
    `getJellyNum`          INT            NULL        COMMENT '수정필요',
    `createdAt`            TIMESTAMP      NULL        DEFAULT CURRENT_TIMESTAMP COMMENT '구매 생성',
    `updatedAt`            TIMESTAMP      NULL        DEFAULT CURRENT_TIMESTAMP COMMENT '구매 업데이트',
    `status`               CHAR(1)        NULL        DEFAULT 'Y' COMMENT 'Y : 유지, N : 취소',
     PRIMARY KEY (purchaseProductIdx)
);

ALTER TABLE PurchaseProduct COMMENT '상품 구매 테이블 (미완성 + 배송지 관련)';

# ALTER TABLE PurchaseProduct
#     ADD CONSTRAINT FK_PurchaseProduct_productIdx_Product_productIdx FOREIGN KEY (productIdx)
#         REFERENCES Product (productIdx) ON DELETE RESTRICT ON UPDATE RESTRICT;

# ALTER TABLE PurchaseProduct
#     ADD CONSTRAINT FK_PurchaseProduct_userIdx_User_userIdx FOREIGN KEY (userIdx)
#         REFERENCES User (userIdx) ON DELETE RESTRICT ON UPDATE RESTRICT;


-- adoptAnimalFile Table Create SQL
CREATE TABLE adoptAnimalFile
(
    `adoptAnimalFileIdx`  INT              NOT NULL    AUTO_INCREMENT COMMENT '동물 입양 파일 인덱스',
    `adoptListIdx`        INT              NULL        COMMENT '입양 등록글 인덱스',
    `adoptLisfFileSort`   CHAR(1)          NULL        DEFAULT 'P' COMMENT 'P : 사진, D : 문서',
    `adoptListFile`       VARCHAR(1000)    NULL        COMMENT '파일 링크',
    `createdAt`           TIMESTAMP        NULL        DEFAULT CURRENT_TIMESTAMP COMMENT '파일 생성',
    `updatedAt`           TIMESTAMP        NULL        DEFAULT CURRENT_TIMESTAMP COMMENT '파일 업데이트',
    `status`              CHAR(1)          NULL        DEFAULT 'Y' COMMENT 'Y : 유지, N : 삭제',
     PRIMARY KEY (adoptAnimalFileIdx)
);

ALTER TABLE adoptAnimalFile COMMENT '입양 등록 동물 파일 테이블';

ALTER TABLE adoptAnimalFile
    ADD CONSTRAINT FK_adoptAnimalFile_adoptListIdx_AdoptList_adoptListIdx FOREIGN KEY (adoptListIdx)
        REFERENCES AdoptList (adoptListIdx) ON DELETE RESTRICT ON UPDATE RESTRICT;


# ALTER TABLE PurchaseProduct
#     ADD CONSTRAINT FK_PurchaseProduct_productIdx_Product_productIdx FOREIGN KEY (productIdx)
#         REFERENCES Product (productIdx) ON DELETE RESTRICT ON UPDATE RESTRICT;
#
# ALTER TABLE PurchaseProduct
#     ADD CONSTRAINT FK_PurchaseProduct_userIdx_User_userIdx FOREIGN KEY (userIdx)
#         REFERENCES User (userIdx) ON DELETE RESTRICT ON UPDATE RESTRICT;
