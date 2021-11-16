const {pool} = require('../../../config/database');
const {logger} = require('../../../config/winston');
const jwtMiddleware = require("../../../config/jwtMiddleware");
const jwt = require('jsonwebtoken');
const crypto = require('crypto');
const secret_config = require('../../../config/secret');

const marketModel = require('../models/marketModel');
const { constants } = require('buffer');
const { connect } = require("http2");

/**
 * Market 1. 마켓 메인화면 조회 API
 * [GET] /market
 */
exports.getMarket = async function (req, res) {
    const marketRows = await marketModel.selectMarket();

    if (!marketRows){
        return res.json({
            isSuccess : false
        })
    }

    return res.json({
        isSuccess : true,
        productIdx : marketRows[0].productIdx,
        productImage : marketRows[0].productImage,
        productName : marketRows[0].productName
    });
};


/**
 * Market 2. 카테고리별 상품 조회
 * [POST] /market/category
 */
exports.getCategory = async function (req, res) {
    const { categoryIdx } = req.body;
    try{
        const selectCategoryRows =
            await marketModel.selectCategory(categoryIdx);

        if (!selectCategoryRows){
            return res.json({
                isSuccess : false
            })
        }

        res.json({
            isSuccess : true,
            productIdx : selectCategoryRows[0].productIdx,
            productImage : selectCategoryRows[0].productImage,
            productName : selectCategoryRows[0].productName
        });

    } catch (err){
        logger.error(`selectCategory DB Connection error\n: ${err.message}`);
        return res.status(500).send(`Error: ${err.message}`);
    }
};


/**
 * Market 3. 상품 업로드 API
 * [POST] /market/product
 */
exports.postProduct = async function (req, res) {
    const {
        userIdx,
        productName,
        productIntro,
        productPrice,
        productImage,
        categoryIdx
        // marketName,
        // marketAddress,
        // marketPhoneNum
    } = req.body;

    try{
        const postProductRows = await marketModel.postProduct1(
            userIdx, productName, productIntro, productPrice, productImage, categoryIdx
        );

        const postProuct2 = await marketModel.postProduct2(
            marketName, marketAddress, marketPhoneNum
        )

        res.json({
            isSuccess: true
        });

    } catch (err){
        logger.error(`postProduct DB Connection error\n: ${err.message}`);
        return res.status(500).send(`Error: ${err.message}`);
    }
};










