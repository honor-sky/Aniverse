const { pool } = require("../../../config/database");
const {logger} = require('../../../config/winston');

// 1. 회원가입 API
async function insertUserInfo(userId, userAmmo, userPhoneNum, userName) {
    try {
        const connection = await pool.getConnection(async (conn) => conn);
        try {
            const insertUserInfoQuery = `
                insert into User (userId, userAmmo, userPhoneNum, userName)
                values (?, ?, ?, ?);
            `;
            const insertUserInfoParams = [userId, userAmmo, userPhoneNum, userName];
            const insertUserInfoRow = await connection.query(
                insertUserInfoQuery,
                // userId
                insertUserInfoParams
            );
            connection.release();
            return insertUserInfoRow;
        } catch (err){
            connection.release();
            // logger.error(`InsertUserInfo Transaction error\n: ${err.message}`);
            return res.status(500).send(`Error: ${err.message}`);
        }
    } catch (err) {
        logger.error(`InsertUserInfo DB Connection error\n: ${err.message}`);
        return res.status(500).send(`Error: ${err.message}`);
    }
}

// 2. 아이디 중복확인 API
async function userIdCheck(userId) {
    try {
        const connection = await pool.getConnection(async (conn) => conn);
        const selectIdQuery = `
            select exists(
                           select userId
                           from User
                           where userId = ? and status= 'Y') as exist;
        `;
        const selectIdParams = [userId];
        const [userIdRows] = await connection.query(
            selectIdQuery,
            selectIdParams
        );
        connection.release();

        return userIdRows;
    } catch (err) {
        logger.error(`CheckId DB Connection error\n: ${err.message}`);
        return res.status(500).send(`Error: ${err.message}`);
    }
}

// 로그인 daeun
async function selectUserInfo(userId) {
    try {
        const connection = await pool.getConnection(async (conn) => conn);
        const selectUserInfoQuery = `
            select userId, userAmmo, userAuth, userIdx
            from User
            where userId = ? and status = 'Y';
        `;

        const selectUserInfoParams = [userId];
        const [userInfoRows] = await connection.query(
            selectUserInfoQuery,
            selectUserInfoParams
        );

        return userInfoRows;
        connection.release();
    } catch (err) {
        logger.error(`SignIn DB Connection error\n: ${err.message}`);
        return res.status(500).send(`Error: ${err.message}`);
    }
}

// 인덱스로 권한 뽑아오기 daeun
async function selectUserInfoo(userIdx) {
    try {
        const connection = await pool.getConnection(async (conn) => conn);
        const selectUserInfooQuery = `
            select userIdx, userAuth
            from User
            where userIdx = ? and status = 'Y';
        `;

        const selectUserInfooParams = [userIdx];
        const [userInfooRows] = await connection.query(
            selectUserInfooQuery,
            selectUserInfooParams
        );

        return userInfooRows;
        connection.release();
    } catch (err) {
        logger.error(`SignIn DB Connection error\n: ${err.message}`);
        return res.status(500).send(`Error: ${err.message}`);
    }
}

// 4. main화면
async function selectAdopt(){
    try{
        const connection = await pool.getConnection(async (conn) => conn);
        const selectAdoptQuery = `
          select a.animalImage, a.animalIdx
          from Animal a;
      `;
        // const selectAdoptParams = [];
        const [adoptRows] = await connection.query(selectAdoptQuery);
        return adoptRows;
        connection.release();
    } catch (err){
        logger.error(`selectAdopt DB Connection error\n: ${err.message}`);
        return res.status(500).send(`Error: ${err.message}`);
    }
}

// 4. main화면
async function selectFunding(){
    try{
        const connection = await pool.getConnection(async (conn) => conn);
        const selectFundingQuery = `
            select f.fundingImage, f.fundingIdx
            from Funding f
            order by f.createdAt;
      `;

        const [fundingRows] = await connection.query(selectFundingQuery);

        return fundingRows;
        connection.release();
    } catch (err) {
        logger.error(`selectFunding DB Connection error\n: ${err.message}`);
        return res.status(500).send(`Error: ${err.message}`);
    }
}

// 4. main화면
async function selectMarket(){
    try{
        const connection = await pool.getConnection(async (conn) => conn);
        const selectMarketQuery = `
          select p.productImage, p.productIdx
          from Product p
          order by p.createdAt;
      `;

        const [marketRows] = await connection.query(selectMarketQuery);

        return marketRows;
        connection.release();
    } catch (err){
        logger.error(`selectMarket DB Connection error\n: ${err.message}`);
        return res.status(500).send(`Error: ${err.message}`);
    }
}

// mypage
async function selectMypage(userIdx){
    try{
        const connection = await pool.getConnection(async (conn) => conn);

        const selectMypageQuery = `
            select f.fundingName,
                   f.fundingImage
            from Funding f left join DonateJelly dj on f.fundingIdx = dj.fundingIdx
            where dj.userIdx = ?;
        `;

        const selectMypageParams = [userIdx];
        const [selectMypageRows] = await connection.query(
            selectMypageQuery,
            selectMypageParams
        );

        return selectMypageRows;
        connection.release();
    } catch (err){
        logger.error(`selectMypage DB Connection error\n: ${err.message}`);
        return res.status(500).send(`Error: ${err.message}`);
    }
}

// mypage product - 일반유저, 센터 -> 구매한 상품 조회
async function selectMypageProductUC(userIdx){
    try{
        const connection = await pool.getConnection(async (conn) => conn);

        const selectMypageProductUCQuery = `
            select p.productName,
                   p.productPrice,
                   p.productImage,
                   c.categoryName,
                   p.productIntro,
                   pp.productAmount,
                   p.productIdx
            from Product p left join PurchaseProduct pp on pp.productIdx = p.productIdx
                           left join Category c on p.categoryIdx = c.categoryIdx
            where pp.userIdx = ?;
        `;

        const selectMypageProductUCParams = [userIdx];
        const [mypageProductRows] = await connection.query(
            selectMypageProductUCQuery,
            selectMypageProductUCParams
        );

        return mypageProductRows;
        connection.release();
    } catch (err){
        logger.error(`selectMypageProduct DB Connection error\n: ${err.message}`);
        return res.status(500).send(`Error: ${err.message}`);
    }
}

// mypage product - 마켓관리자 -> 판매상품 주문 조회
async function selectMypageProductM(userIdx){
    try{
        const connection = await pool.getConnection(async (conn) => conn);

        const selectMypageProductMQuery = `
          select p.productName,
                 p.productPrice,
                 p.productImage,
                 p.productIdx
          from Product p left join Market m on m.marketIdx = p.marketIdx
          where m.userIdx = ?;
        `;

        const selectMypageProductMParams = [userIdx];
        const [mypageProductRows] = await connection.query(
            selectMypageProductMQuery,
            selectMypageProductMParams
        );

        return mypageProductRows;
        connection.release();
    } catch (err){
        logger.error(`selectMypageProduct DB Connection error\n: ${err.message}`);
        return res.status(500).send(`Error: ${err.message}`);
    }
}

// mypage adopt - 일반,마켓유저 -> 입양동물 조회
async function selectMypageAdoptUM(userIdx){
    try{
        const connection = await pool.getConnection(async (conn) => conn);

        const selectMypageAdoptUMQuery = `
            select a.animalName,
                   a.animalAge,
                   a.animalSpecies,
                   aaf.adoptListFile,
                   ar.adoptDate,
                   ar.adoptRequestIdx
            from Animal a left join AdoptList al on a.animalIdx = al.animalIdx
                          left join AdoptRequest ar on ar.adoptListIdx = al.adoptListIdx
                          left join AdoptAnimalFile aaf on aaf.adoptListIdx = al.adoptListIdx
            where ar.userIdx = ? and ar.status = 'S';
        `;

        const selectMypageAdoptUMParams = [userIdx];
        const [mypageAdoptRows] = await connection.query(
            selectMypageAdoptUMQuery,
            selectMypageAdoptUMParams
        );

        return mypageAdoptRows;
        connection.release();
    } catch (err){
        logger.error(`selectMypageAdopt DB Connection error\n: ${err.message}`);
        return res.status(500).send(`Error: ${err.message}`);
    }
}

// mypage adopt - 일반,마켓유저 -> 임보동물 조회
async function selectMypageProtectUM(userIdx){
    try{
        const connection = await pool.getConnection(async (conn) => conn);

        const selectMypageProtectUMQuery = `
            select a.animalName,
                   a.animalAge,
                   a.animalSpecies,
                   pl.protectImage,
                   pl.protectDateStart,
                   pl.protectDateEnd,
                   pl.protectListIdx
            from Animal a left join ProtectList pl on a.animalIdx = pl.animalIdx
                          left join ProtectRequest pr on pl.protectListIdx = pr.protectListIdx
            where pr.userIdx = ? and pr.status = 'S';
        `;

        const selectMypageProtectUMParams = [userIdx];
        const [mypageProtectRows] = await connection.query(
            selectMypageProtectUMQuery,
            selectMypageProtectUMParams
        );

        return mypageProtectRows;
        connection.release();
    } catch (err){
        logger.error(`selectMypageProtect DB Connection error\n: ${err.message}`);
        return res.status(500).send(`Error: ${err.message}`);
    }
}

// mypage adopt - 센터입장 -> 입양 업로드 조회
async function selectMypageAdoptC(userIdx){
    try{
        const connection = await pool.getConnection(async (conn) => conn);

        const selectMypageProtectUMQuery = `
            select a.animalName,
                   a.animalAge,
                   a.animalSpecies,
                   (select aaf.adoptListFile
                    from AdoptAnimalFile aaf limit 1),
                   al.status,
                   al.animalIdx
            from AdoptList al left join Animal a on al.animalIdx = a.animalIdx
                              left join AdoptAnimalFile aaf on aaf.adoptListIdx = al.adoptListIdx
            where al.userIdx = ?;
        `;

        const selectMypageProtectUMParams = [userIdx];
        const [mypageProtectRows] = await connection.query(
            selectMypageProtectUMQuery,
            selectMypageProtectUMParams
        );

        return mypageProtectRows;
        connection.release();
    } catch (err){
        logger.error(`selectMypageProtect DB Connection error\n: ${err.message}`);
        return res.status(500).send(`Error: ${err.message}`);
    }
}


// mypage adopt - 센터입장 -> 임보 업로드 조회
async function selectMypageProtectC(userIdx){
    try{
        const connection = await pool.getConnection(async (conn) => conn);

        const selectMypageProtectUMQuery = `
            select a.animalName,
                   a.animalAge,
                   a.animalSpecies,
                   pl.protectImage,
                   pl.protectDateStart,
                   pl.protectDateEnd,
                   pl.status,
                   pl.protectListIdx
            from ProtectList pl left join Animal a on pl.animalIdx = a.animalIdx
            where pl.userIdx = ?;
        `;

        const selectMypageProtectUMParams = [userIdx];
        const [mypageProtectRows] = await connection.query(
            selectMypageProtectUMQuery,
            selectMypageProtectUMParams
        );

        return mypageProtectRows;
        connection.release();
    } catch (err){
        logger.error(`selectMypageProtect DB Connection error\n: ${err.message}`);
        return res.status(500).send(`Error: ${err.message}`);
    }
}

// mypage fundint -> 일반유저입장 펀딩중
async function selectMypageFundingIngUM(userIdx){
    try{
        const connection = await pool.getConnection(async (conn) => conn);

        const selectMypageFundingIngUMQuery = `
            select f.fundingName,
                   f.fundingImage,
                   f.fundingPurpose,
                   f.fundingIdx
            from Funding f
            where f.fundingIdx = ?;
        `;

        const selectMypageProtectUMParams = [userIdx];
        const [mypageFundingIngRows] = await connection.query(
            selectMypageFundingIngUMQuery,
            selectMypageFundingIngUMParams
        );

        return mypageFundingIngRows;
        connection.release();
    } catch (err){
        logger.error(`selectMypageFunding DB Connection error\n: ${err.message}`);
        return res.status(500).send(`Error: ${err.message}`);
    }
}

// mypage fundint -> 일반유저입장 펀딩완료
async function selectMypageFundingEndUM(userIdx){
    try{
        const connection = await pool.getConnection(async (conn) => conn);

        const selectFundingEndUMQuery = `
            select f.fundingName,
                   f.fundingImage,
                   f.fundingPurpose,
                   f.fundingIdx
            from Funding f
            where f.fundingIdx = ?;
        `;

        const selectMypageFundingEndUMParams = [userIdx];
        const [mypageFundingEndRows] = await connection.query(
            selectMypageFundingEndUMQuery,
            selectMypageFundingEndUMParams
        );

        return mypageFundingEndRows;
        connection.release();
    } catch (err){
        logger.error(`selectMypageFunding DB Connection error\n: ${err.message}`);
        return res.status(500).send(`Error: ${err.message}`);
    }
}



module.exports = {
    insertUserInfo,
    userIdCheck,
    selectUserInfo,
    selectUserInfoo,
    selectAdopt,
    selectFunding,
    selectMarket,
    selectMypage,
    selectMypageProductUC,
    selectMypageProductM,
    selectMypageAdoptUM,
    selectMypageProtectUM,
    selectMypageAdoptC,
    selectMypageProtectC
