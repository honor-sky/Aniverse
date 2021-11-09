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










