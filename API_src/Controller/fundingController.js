const {pool} = require('../../../config/database');
const {logger} = require('../../../config/winston');
const jwtMiddleware = require("../../../config/jwtMiddleware");
const jwt = require('jsonwebtoken');
const crypto = require('crypto');
const secret_config = require('../../../config/secret');

const fundingModel = require('../models/fundingModel');
const { constants } = require('buffer');
const { connect } = require("http2");

/**
 * Funding 1. 펀딩리스트 조회 API (펀딩중 / 펀딩완료)
 * [GET] /funding/list
 */
exports.getFundingList = async function (req, res) {
    // const { status } = req.body;
    const fundingListRows = await fundingModel.selectFundingList();

    if (!fundingListRows){
        return res.json({
            isSuccess : false
        })
    }

    return res.json({
        isSuccess : true,
        fundingIdx : fundingListRows[0].fundingIdx,
        fundingName : fundingListRows[0].fundingName,
        fundingImage : fundingListRows[0].fundingImage
    });
};


/**
 * Funding 2. 펀딩 업로드 API
 * [POST] /funding
 */
exports.postFunding = async function (req, res) {
    const {
        userIdx,
        centerName,
        fundingName,
        fundingImage,
        fundingPurpose,
        fundingAmount,
        fundingPeriod
    } = req.body;

    try {
        const insertFundingInfoRows2 =
            await fundingModel.insertFundingInfo2(userIdx, centerName, fundingName, fundingImage, fundingPurpose, fundingAmount, fundingPeriod);

        res.json({
            isSuccess: true
        });

    } catch (err){
        logger.error(`postFunding DB Connection error\n: ${err.message}`);
        return res.status(500).send(`Error: ${err.message}`);
    }
};


/**
 * Funding 3. 펀딩중 사진 클릭 조회 API
 * [GET] /funding/ing
 */
exports.getFundingIng = async function (req, res) {
    const { fundingIdx } = req.body;
    try{
        const selectFundingIngRows =
            await fundingModel.selectFundingIng(fundingIdx);

        if (!selectFundingIngRows){
            return res.json({
                isSuccess : false
            })
        }

        res.json({
            isSuccess: true,
            fundingName : selectFundingIngRows[0].fundingName,
            fundingImage : selectFundingIngRows[0].fundingImage,
            fundingPurpose : selectFundingIngRows[0].fundingPurpose,
            fundingAmount : selectFundingIngRows[0].fundingAmount,
            fundingReceived : selectFundingIngRows[0].fundingReceived,
            fundingPeriod : selectFundingIngRows[0].fundingPeriod
        });

    } catch (err){
        logger.error(`selectFundingIng DB Connection error\n: ${err.message}`);
        return res.status(500).send(`Error: ${err.message}`);
    }
};

/**
 * Funding 4. 펀딩 모니터링 업로드 API -1
 * [POST] /funding/postreview
 */
// exports.postFundingReview = async function (req, res) {
//     const {
//         userIdx,
//         fundingIdx,
//         fundingReviewText
//     } = req.body;
//
//     try {
//         const insertFundingReviewRows =
//             await fundingModel.insertFundingReview(
//                 userIdx, fundingIdx, fundingReviewText);
//
//         res.json({
//             isSuccess: true
//         });
//
//     } catch (err){
//         logger.error(`postFundingReview DB Connection error\n: ${err.message}`);
//         return res.status(500).send(`Error: ${err.message}`);
//     }
// };

/**
 * Funding 4. 펀딩 모니터링 업로드 API -2
 * [POST] /funding/postreview
 */
// exports.postFundingReview = async function (req, res) {
//     const {
//         fundingName,
//         fundingReviewFile1,
//         fundingReviewFile2
//     } = req.body;
//
//     try {
//         const selectFundingReviewIdxRows =
//             await fundingModel.selectFundingReviewIdx(fundingName);
//
//         var fundingReviewIdx = selectFundingReviewIdxRows[0].fundingReviewIdx;
//
//         const insertFundingReviewFileRows =
//             await fundingModel.insertFundingReviewFile(
//                 fundingReviewIdx,
//                 fundingReviewFile1,
//                 fundingReviewFile2
//             );
//
//         res.json({
//             isSuccess: true
//         });
//
//     } catch (err){
//         logger.error(`insertFundingReviewFile DB Connection error\n: ${err.message}`);
//         return res.status(500).send(`Error: ${err.message}`);
//     }
// };

/**
 * Funding 5. 펀딩 모니터링 조회 API
 * [POST] /funding/getreview
 */
exports.getFundingReview = async function (req, res) {
    const { fundingName } = req.body;
    try{
        const selectFundingReviewRows =
            await fundingModel.selectFundingReview(fundingName);
        // console.log(selectFundingReviewRows[0].fundingName);
        const selectFundingReviewFileRows =
            await fundingModel.selectFundingReviewFile(fundingName);

        if (!selectFundingReviewRows){
            return res.json({
                isSuccess : false
            })
        }

        res.json({
            isSuccess: true,
            fundingName : selectFundingReviewRows[0].fundingName,
            fundingImage : selectFundingReviewRows[0].fundingImage,
            fundingReviewText : selectFundingReviewRows[0].fundingReviewText,
            fundingReviewFile1 : selectFundingReviewFileRows[0].fundingReviewFile,
            fundingReviewFile2 : selectFundingReviewFileRows[1].fundingReviewFile
        });

    } catch (err){
        logger.error(`selectFundingReview DB Connection error\n: ${err.message}`);
        return res.status(500).send(`Error: ${err.message}`);
    }
};







