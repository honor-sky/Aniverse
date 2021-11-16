const { pool } = require("../../../config/database");
const {logger} = require('../../../config/winston');

// 1. 펀딩중 펀딩완료 리스트 조회 API
async function selectFundingList() {
    try{
        const connection = await pool.getConnection(async (conn) => conn);

        const selectFundingListQuery = `
            select f.fundingIdx,
                   f.fundingName,
                   f.fundingImage
            from Funding f
            where f.fundingIdx = 16;
        `;

        const [selectFundingListRows] = await connection.query(
            selectFundingListQuery
        );

        connection.release();
        return selectFundingListRows;
    } catch (err){
        logger.error(`selectFundingList DB Connection error\n: ${err.message}`);
        return res.status(500).send(`Error: ${err.message}`);
    }
}


// 2. 펀딩 업로드 API 1
// async function insertFundingInfo1(animalSpecies, animalNeutralization) {
//     try{
//         const connection = await pool.getConnection(async (conn) => conn);
//
//         const insertFundingInfo1Query =
//             `insert into Animal(animalSpecies, animalNeutralization)
//              value (?, ?);
//             `;
//         const  insertFundingInfo1Params = [animalSpecies, animalNeutralization]
//         const [insertFundingInfo1Rows] = await connection.query(
//             insertFundingInfo1Query,
//             insertFundingInfo1Params
//         );
//
//         connection.release();
//         return insertFundingInfo1Rows;
//     } catch (err){
//         logger.error(`insertFundingInfo1 DB Connection error\n: ${err.message}`);
//         return res.status(500).send(`Error: ${err.message}`);
//     }
// }
//
// 2. 펀딩 업로드 API 2
async function insertFundingInfo2(userIdx, centerName, fundingName, fundingImage, fundingPurpose, fundingAmount, fundingPeriod) {
    try{
        const connection = await pool.getConnection(async (conn) => conn);

        const insertFundingInfo2Query =
            `insert into Funding(userIdx, centerName, fundingName, fundingImage, fundingPurpose, fundingAmount, fundingPeriod)
             value (?, ?, ?, ?, ?, ?, ?);
            `;
        const  insertFundingInfo2Params = [userIdx, centerName, fundingName, fundingImage, fundingPurpose, fundingAmount, fundingPeriod]
        const [insertFundingInfo2Rows] = await connection.query(
            insertFundingInfo2Query,
            insertFundingInfo2Params
        );

        connection.release();
        return insertFundingInfo2Rows;
    } catch (err){
        logger.error(`insertFundingInfo2 DB Connection error\n: ${err.message}`);
        return res.status(500).send(`Error: ${err.message}`);
    }
}

// 3. 펀딩중 사진 클릭 조회 API
async function selectFundingIng(fundingIdx) {
    try{
        const connection = await pool.getConnection(async (conn) => conn);

        const selectFundingListQuery = `
            select f.fundingName,
                   f.fundingImage,
                   f.fundingPurpose,
                   f.fundingAmount,
                   f.fundingReceived,
                   f.fundingPeriod
            from Funding f
            where f.fundingIdx = ?;
        `;
        selectFundingListParams = [fundingIdx];
        const [selectFundingListRows] = await connection.query(
            selectFundingListQuery,
            selectFundingListParams
        );

        connection.release();
        return selectFundingListRows;
    } catch (err){
        logger.error(`selectFundingIng DB Connection error\n: ${err.message}`);
        return res.status(500).send(`Error: ${err.message}`);
    }
}


// // 4. 펀딩 모니터링 업로드 1
// async function insertFundingReview(userIdx, fundingIdx, fundingReviewText) {
//     try{
//         const connection = await pool.getConnection(async (conn) => conn);
//
//         const insertFundingReviewQuery =
//             `insert into FundingReview(userIdx, fundingIdx, fundingReviewText)
//              value (?, ?, ?);
//             `;
//         const  insertFundingReviewParams = [userIdx, fundingIdx, fundingReviewText]
//         const [insertFundingReviewRows] = await connection.query(
//             insertFundingReviewQuery,
//             insertFundingReviewParams
//         );
//
//         connection.release();
//         return insertFundingReviewRows;
//     } catch (err){
//         logger.error(`insertFundingReview DB Connection error\n: ${err.message}`);
//         return res.status(500).send(`Error: ${err.message}`);
//     }
// }


// // 4. 펀딩리뷰인덱스 뽑아오기
// async function selectFundingReviewIdx(fundingName) {
//     try{
//         const connection = await pool.getConnection(async (conn) => conn);
//
//         const selectFundingReviewIdx = `
//             select fr.fundingReviewIdx
//             from FundingReview fr left join funding f on f.fundingIdx = fr.fundingIdx
//             where f.fundingName = ?;
//         `;
//
//         const selectFundingReviewIdxParams = [fundingName];
//         const [selectFundingReviewIdxRows] = await connection.query(
//             selectFundingListQuery,
//             selectFundingReviewIdxParams
//         );
//
//         connection.release();
//         return selectFundingReviewIdxRows;
//     } catch (err){
//         logger.error(`selectFundingReviewIdx DB Connection error\n: ${err.message}`);
//         return res.status(500).send(`Error: ${err.message}`);
//     }
// }
//
// // 4. 펀딩 모니터링 업로드 2
// async function insertFundingReviewFile(fundingReviewIdx, fundingReviewFile1, fundingReviewFile2) {
//     try{
//         const connection = await pool.getConnection(async (conn) => conn);
//
//         const insertFundingReviewFile1Query =
//             `insert into FundingReviewFile(fundingReviewIdx, fundingReviewFile1)
//              value (?, ?);
//             `;
//         const insertFundingReviewFile2Query =
//             `insert into FundingReviewFile(fundingReviewIdx, fundingReviewFile2)
//              value (?, ?);
//             `;
//         const  insertFundingReviewFile1Params = [fundingName, fundingReviewFile1]
//         const [insertFundingReviewFile1Rows] = await connection.query(
//             insertFundingReviewFile1Query,
//             insertFundingReviewFile1Params
//         );
//         const  insertFundingReviewFile2Params = [fundingName, fundingReviewFile2]
//         const [insertFundingReviewFile2Rows] = await connection.query(
//             insertFundingReviewFile2Query,
//             insertFundingReviewFile2Params
//         );
//
//         connection.release();
//         return insertFundingReviewFile1Rows;
//     } catch (err){
//         logger.error(`insertFundingReviewFile DB Connection error\n: ${err.message}`);
//         return res.status(500).send(`Error: ${err.message}`);
//     }
// }
//

// 4. 펀딩완료 모니터링 조회 API
async function selectFundingReview(fundingName) {
    try{
        const connection = await pool.getConnection(async (conn) => conn);

        const selectFundingReviewQuery = `
            select f.fundingName,
                   f.fundingImage,
                   f.fundingName,
                   fr.fundingReviewText
            from Funding f left join FundingReview fr on f.fundingIdx = fr.fundingIdx
            where f.fundingName = ?;
        `;
        selectFundingReviewParams = [fundingName];
        const [selectFundingReviewRows] = await connection.query(
            selectFundingReviewQuery,
            selectFundingReviewParams
        );

        connection.release();
        return selectFundingReviewRows;
    } catch (err){
        logger.error(`selectFundingReview DB Connection error\n: ${err.message}`);
        return res.status(500).send(`Error: ${err.message}`);
    }
}

// 4. 펀딩완료 모니터링 파일 조회 API
async function selectFundingReviewFile(fundingName) {
    try{
        const connection = await pool.getConnection(async (conn) => conn);

        const selectFundingReviewFileQuery = `
            select frf.fundingReviewFileIdx,
                   frf.fundingReviewFile
            from Funding f left join FundingReview fr on f.fundingIdx = fr.fundingIdx
                           left join FundingReviewFile frf on frf.fundingReviewIdx = fr.fundingReviewIdx
            where f.fundingName = ?;
        `;
        selectFundingReviewFileParams = [fundingName];
        const [selectFundingReviewFileRows] = await connection.query(
            selectFundingReviewFileQuery,
            selectFundingReviewFileParams
        );

        connection.release();
        return selectFundingReviewFileRows;
    } catch (err){
        logger.error(`selectFundingReviewFile DB Connection error\n: ${err.message}`);
        return res.status(500).send(`Error: ${err.message}`);
    }
}


module.exports = {
    selectFundingList,
    insertFundingInfo2,
    selectFundingIng,
    // insertFundingReview,
    // insertFundingReviewFile,
    // selectFundingReviewIdx,
    selectFundingReview,
    selectFundingReviewFile
}