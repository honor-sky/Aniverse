const {pool} = require('../../../config/database');
const {logger} = require('../../../config/winston');
const jwtMiddleware = require("../../../config/jwtMiddleware");
const jwt = require('jsonwebtoken');
const crypto = require('crypto');
const secret_config = require('../../../config/secret');

const protectModel = require('../models/protectModel');
const { constants } = require('buffer');
const { connect } = require("http2");


/**
 * Adopt 2. 입양중/임보중/입양완료 동물 조회
 * [GET] /protect/list
 */
exports.getProtectList = async function (req, res) {
    // const { status } = req.body;
    // 동물 전체 조회
    try{
        // 동물 전체 조회
        const getProtectlistResult = await protectModel.selectProtectList();
        // return res.send(response(baseResponse.SUCCESS, getProtectlistResult));

        if (!getProtectlistResult){
            return res.json({
                isSuccess : false
            })
        }

        res.json({
            isSuccess: true,
            animalIdx : getProtectlistResult[0].animalIdx,
            animalSpecies : getProtectlistResult[0].animalSpecies,
            animalAge : getProtectlistResult[0].animalAge,
            protectDateStart : getProtectlistResult[0].protectDateStart,
            protectDateEnd : getProtectlistResult[0].protectDateEnd

        });
    } catch (err){
        logger.error(`postProtectlist DB Connection error\n: ${err.message}`);
        return res.status(500).send(`Error: ${err.message}`);
    }
};


/**
 * API No. 5 임시보호 세부화면 조회 API
 * [POST] /protect/info
 */
exports.getImgClick = async function (req, res) {
    try{
        const {animalIdx} = req.body;

        // const CommentListResult = await adoptModel.selectAdoptAnimal(
        //     animalIdx, adoptListFile, animalSpecies, animalAge
        // );
        const getImgClickRows = await protectModel.selectProtectAnimal(animalIdx);

        if (!getImgClickRows){
            return res.json({
                isSuccess : false
            })
        }

        res.json({
            isSuccess: true,
            animalIdx : getImgClickRows[0].animalIdx,
            centerIdx : getImgClickRows[0].centerIdx,
            animalSpecies : getImgClickRows[0].animalSpecies,
            animalName : getImgClickRows[0].animalName,
            animalAge : getImgClickRows[0].animalAge,
            animalGender : getImgClickRows[0].animalGender,
            animalWeight : getImgClickRows[0].animalWeight,
            animalNeutralization : getImgClickRows[0].animalNeutralization,
            animalVaccinated : getImgClickRows[0].animalVaccinated,
            animalDiseases : getImgClickRows[0].animalDiseases,
            animalFind : getImgClickRows[0].animalFind,
            animalIntro : getImgClickRows[0].animalIntro,
            createdAt : getImgClickRows[0].createdAt,
            status : getImgClickRows[0].status,
            protectListIdx : getImgClickRows[0].protectListIdx,
            updatedAt : getImgClickRows[0].updatedAt,
            protectDateStart : getImgClickRows[0].protectDateStart,
            protectDateEnd : getImgClickRows[0].protectDateEnd,
            protectImage : getImgClickRows[0].protectImage

        });
    } catch (err){
        logger.error(`getImgClick DB Connection error\n: ${err.message}`);
        return res.status(500).send(`Error: ${err.message}`);
    }
};


/**
 * API No. 6
 * API Name :  activity_protect_request.xml : 보호신청위한 정보 업로드 API
 * [POST] /app/protect/postUserinfo
 */
exports.protectUserInfo = async function (req, res) {
    const {animalIdx, userIdx, contactName, contactPhoneNum, protectComment} = req.body;
    try{
        var protectListIdxRows = await protectModel.selectProtectListIdx(animalIdx);
        // console.log(adoptListIdxRows[0].adoptListIdx);
        var adoptListIdx = adoptListIdxRows[0].adoptListIdx;
        // const postAdoptListIdx = await adoptModel.insertAdoptListIdx(adoptListIdx);
        const postAdoptInfo = await adoptModel.insertAdoptRequestInfo(
            adoptListIdx, userIdx, contactName, contactPhoneNum, adoptComment
        );

        res.json({
            isSuccess: true
        });

    } catch (err){
        logger.error(`postAdoptUserInfo DB Connection error\n: ${err.message}`);
        return res.status(500).send(`Error: ${err.message}`);
    }
};