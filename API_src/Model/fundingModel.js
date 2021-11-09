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
            where f.status = 'S' or 'F';
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

// // 4. 펀딩완료 사진 조회 API
// async function selectFundingIngRows(status) {
//     try{
//         const connection = await pool.getConnection(async (conn) => conn);
//
//         const selectFundingListQuery = `
//             select f.fundingName,
//                    f.fundingImage,
//                    f.fundingPurpose,
//                    f.fundingAmount,
//                    f.fundingReceived,
//                    f.fundingPeriod
//             from Funding f
//             where f.fundingIdx = ?;
//         `;
//         selectFundingListParams = [status];
//         const [selectFundingListRows] = await connection.query(
//             selectFundingListQuery,
//             selectFundingListParams
//         );
//
//         connection.release();
//         return selectFundingListRows;
//     } catch (err){
//         logger.error(`selectFundingIng DB Connection error\n: ${err.message}`);
//         return res.status(500).send(`Error: ${err.message}`);
//     }
// }



module.exports = {
    selectFundingList,
    insertFundingInfo2,
    selectFundingIng
// selectFundingEnd
}