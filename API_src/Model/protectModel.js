const { pool } = require("../../../config/database");
const {logger} = require('../../../config/winston');


// Protect_list.xml (보호중), 임시보호, (보호완료) 조회API
async function selectProtectList() {
    try{
        const connection = await pool.getConnection(async (conn) => conn);

        const selectProtectlistQuery = `
        select ProtectList.protectImage,
               Animal.animalIdx,
               Animal.animalSpecies, 
               Animal.animalAge, 
               ProtectList.protectDateStart, 
               ProtectList.protectDateEnd
        from ProtectList, Animal
        where Animal.animalIdx = ProtectList.animalIdx 
          and ProtectList.status = 'Y';
        `;

        // const selectProtectlistParams = [animalIdx, protectedImage, animalSpecies, animalAge,protectDateStart, protectDateEnd];
        const [selectProtectlistRows] = await connection.query(
            selectProtectlistQuery
        );
        connection.release();
        return selectProtectlistRows;
    } catch (err){
        logger.error(`selectProtectList DB Connection error\n: ${err.message}`);
        return res.status(500).send(`Error: ${err.message}`);
    }
}


// 사진눌렀을시 정보조회 API
async function selectProtectAnimal(animalIdx) {
    try{
        const connection = await pool.getConnection(async (conn) => conn);
        const selectProtectAnimalQuery = `
            SELECT
                Animal.animalIdx,
                Center.centerIdx,
                Animal.animalSpecies,
                Animal.animalName,
                Animal.animalAge,
                Animal.animalGender,
                Animal.animalWeight,
                Animal.animalNeutralization,
                Animal.animalVaccinated,
                Animal.animalDiseases,
                Animal.animalFind,
                Animal.animalIntro,
                Animal.createdAt,
                Animal.status,
                ProtectList.protectListIdx,
                ProtectList.updatedAt,
                ProtectList.protectDateStart,
                ProtectList.protectDateEnd,
                ProtectList.protectImage

            FROM ProtectList,
                 Animal,
                 Center

            WHERE Animal.animalIdx = ProtectList.animalIdx
              and Animal.centerIdx = Center.centerIdx and Animal.animalIdx = '?';
    `;
        const selectProtectAnimalParams = [animalIdx];
        const [selectProtectAnimalRows] = await connection.query(
            selectProtectAnimalQuery,
            selectProtectAnimalParams
        );

        connection.release();
        return selectProtectAnimalRows;
    } catch (err){
        logger.error(`selectProtectAnimal DB Connection error\n: ${err.message}`);
        return res.status(500).send(`Error: ${err.message}`);
    }
}

var protectListIdxRows = await protectModel.selectProtectListIdx(animalIdx);
// console.log(adoptListIdxRows[0].adoptListIdx);
var protectListIdx = protectListIdxRows[0].protectListIdx;
// const postAdoptListIdx = await adoptModel.insertAdoptListIdx(adoptListIdx);
const postProtectInfo = await protectModel.insertProtectRequestInfo(
    protectListIdx, userIdx, contactName, contactPhoneNum, protectComment
);


// 4. 임보신청 등록 protectListIdx 뽑기
async function selectProtectListIdx(animalIdx) {
    try{
        const connection = await pool.getConnection(async (conn) => conn);

        const selectProtectListIdxQuery = `
            select pl.protectListIdx
            from ProtectList pl left join Animal a on pl.animalIdx = a.animalIdx
            where a.animalIdx = ?;
        `;

        const selectProtectListIdxParams = [animalIdx];
        const [selectProtectListIdxRows] = await connection.query(
            selectProtectListIdxQuery,
            selectProtectListIdxParams
        );

        connection.release();
        return selectProtectListIdxRows;
    } catch (err){
        logger.error(`selectProtectListIdx DB Connection error\n: ${err.message}`);
        return res.status(500).send(`Error: ${err.message}`);
    }
}


// 4. 임보신청 등록 API 토큰필요
async function insertProtectRequestInfo(protectListIdx, userIdx, contactName, contactNum, protectComment) {
    try{
        const connection = await pool.getConnection(async (conn) => conn);

        const insertProtectRequestInfoQuery = `
            INSERT into ProtectRequest
            (protectListIdx, userIdx, contactName, contactNum, protectComment)
            value ("?", "?","?", "?", "?");
        `;

        const insertProtectRequestInfoParams = [protectListIdx, userIdx, contactName, contactNum, protectComment];
        const [insertProtectRequestInfoRows] = await connection.query(
            insertProtectRequestInfoQuery,
            insertProtectRequestInfoParams
        );
        connection.release();
        return insertProtectRequestInfoRows;
    } catch (err){
        logger.error(`insertProtectRequestInfo DB Connection error\n: ${err.message}`);
        return res.status(500).send(`Error: ${err.message}`);
    }
}


module.exports = {
    selectProtectList,
    selectProtectAnimal,
    selectProtectListIdx,
    insertProtectRequestInfo
}