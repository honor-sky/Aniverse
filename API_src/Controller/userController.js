const {pool} = require('../../../config/database');
const {logger} = require('../../../config/winston');
const jwtMiddleware = require("../../../config/jwtMiddleware");
const jwt = require('jsonwebtoken');
const crypto = require('crypto');
const secret_config = require('../../../config/secret');

const userModel = require('../models/userModel');
const { constants } = require('buffer');
const { connect } = require("http2");

/**
 * API No. 0
 * API Name : 테스트 API
 * [GET] /app/test
 */
exports.getTest = async function (req, res) {
    return res.json({
        isSuccess : true
    });
}

/**
 * User 1. 회원가입 API
 * [POST] /signup
 */
exports.signUp = async function(req, res){

    var userId = req.body.userId;
    var userAmmo = req.body.userAmmo;
    var userPhoneNum = req.body.userPhoneNum;
    var userName = req.body.userName;

    console.log(userId, userAmmo, userPhoneNum, userName);

    try{
        // const hashedPassword = await crypto.createHash('sha512').update(userPassword).digest('hex');
        await userModel.insertUserInfo(userId, userAmmo, userPhoneNum, userName);
        // console.log(res);
        return res.json({
            isSuccess: true
        });

    } catch (err){
        logger.error(`signUp Query error\n : ${err.message}`);
        return res.status(500).send(`Error : ${err.message}`);
    }
};

/**
 * User 2. 아이디 중복확인 API
 * [GET] /user/checkid
 */
exports.checkId = async function (req, res) {
    // const userId = req.verifiedToken.userId;
    const { userId } = req.body;

    try {
        // 닉네임 중복 확인
        const idRows = await userModel.userIdCheck(userId);
        console.log(idRows[0].exist);

        if (idRows[0].exist === 1) {
            return res.json({
                isSuccess: false
            });
        }

        return res.json({
            isSuccess: true
        });

    } catch (err) {
        logger.error(`CheckId Query error\n: ${err.message}`);
        return res.status(500).send(`Error: ${err.message}`);
    }
}

/**
 * User 3. 로그인 API
 * [POST] /signin
 */
exports.signIn = async function (req, res) {
    const {
        userId,
        userAmmo
    } = req.body;

    try {
        const userInfoRows = await userModel.selectUserInfo(userId);
        // const idCheck = await userModel.userIdCheck(id);
        // const hashedPassword = await crypto.createHash('sha512').update(userPassword).digest('hex');

        if (userInfoRows[0].userAmmo !== userAmmo) {
            return res.json({
                isSuccess: false
            });
        }

        let token = await jwt.sign({
                userId: userInfoRows[0].userId,
            },
            secret_config.jwtsecret,
            {
                expiresIn: '365d',
                subject: 'UserId',
            }
        );

        res.json({
            isSuccess: true,
            jwt : token,
            userAuth : userInfoRows[0].userAuth,
            userIdx : userInfoRows[0].userIdx
        });
    } catch (err) {
        logger.error(`SignIn Query error\n: ${JSON.stringify(err)}`);
        return false;
    }
};

/**
 * JWT 검증 API
 **/
exports.check = async function (req, res) {
    res.json({
        isSuccess: true,
        code: 200,
        message: "검증 성공",
        info: req.verifiedToken
    })
};


/**
 * User 4. 앱 메인화면 조회 API
 * [GET] /main
 */
// 메인배너(보류), 입양, 펀딩, 마켓
exports.getMain = async function(req, res){
    // 나중에 이차원배열같은걸로 효율적으로 수정하기
    const adoptRows = await userModel.selectAdopt();
    const fundingRows = await userModel.selectFunding();
    const marketRows = await userModel.selectMarket();

    // res 전송값도 서버에서 확인하고 수정
    res.json({
        isSuccess: true,
        adopt1 : adoptRows[0].animalImage,
        adopt1Idx : adoptRows[0].animalIdx,
        adopt2 : adoptRows[1].animalImage,
        adopt2Idx : adoptRows[1].animalIdx,
        adopt3 : adoptRows[2].animalImage,
        adopt3Idx : adoptRows[3].animalIdx,
        funding1 : fundingRows[0].fundingImage,
        funding1Idx : fundingRows[0].fundingIdx,
        funding2 : fundingRows[1].fundingImage,
        funding2Idx : fundingRows[1].fundingIdx,
        funding3 : fundingRows[2].fundingImage,
        funding3Idx : fundingRows[2].fundingIdx,
        market1 : marketRows[0].productImage,
        market1Idx : marketRows[0].productIdx,
        market2 : marketRows[1].productImage,
        market2Idx : marketRows[1].productIdx,
        market3 : marketRows[2].productImage,
        market3Idx : marketRows[2].productIdx
    });
}



/**
 * User 5. 마이페이지 조회 API
 * [GET] /user/mypage
 */
exports.getMypage = async function(req, res){
    const { userIdx } = req.body;
    try{
        const selectMypageRows = await userModel.selectMypage(userIdx);

        if (!selectMypageRows){
            return res.json({
                isSuccess : false
            })
        }

        res.json({
            isSuccess : true,
            fundingName : selectMypageRows[0].fundingName,
            fundingImage : selectMypageRows[0].fundingImage
        });

    } catch (err){
        logger.error(`selectMyPage DB Connection error\n: ${err.message}`);
        return res.status(500).send(`Error: ${err.message}`);
    }
}


/**
 * User 6. 마이페이지 구매/판매상품 조회 API
 * [GET] /user/mypage/product
 */
// if문으로 일반 / 상인 사용자 구별해서 다른 쿼리문 수행
exports.getMypageProduct = async function(req, res){
    // const userId = req.verifiedToken.userId;  // 토큰화
    const {
        userIdx
    } = req.body;

    // userIdx로 유저권한 뽑아오기
    try{
        const selectUserInfooRows = await userModel.selectUserInfoo(userIdx);
        var auth = selectUserInfooRows[0].userAuth;
        console.log(selectUserInfooRows[0].userAuth);
        console.log(auth);
        let mypageProductRows;

        if (auth == 'U' || auth == 'C'){
            mypageProductRows = await userModel.selectMypageProductUC(userIdx);
        }
        else{
            mypageProductRows = await userModel.selectMypageProductM(userIdx);
        }

        if (!mypageProductRows){
            return res.json({
                isSuccess : false
            })
        }

        res.json({
            isSuccess: true,
            // mypageProductRows
            productName : mypageProductRows[0].productName,
            productPrice : mypageProductRows[0].productPrice,
            productImage : mypageProductRows[0].productImage,
            categoryName : mypageProductRows[0].categoryName,
            productAmount : mypageProductRows[0].productAmount
        });
    } catch (err){
        logger.error(`selectMyPageProduct DB Connection error\n: ${err.message}`);
        return res.status(500).send(`Error: ${err.message}`);
    }
}


/**
 * User 7. 마이페이지 입양 /입시보호 조회 API
 * [GET] /user/mypage/adopt
 */
exports.getMypageAdopt = async function(req, res){
    // const userId = req.verifiedToken.userId;  // 토큰화

    const {
        userIdx
    } = req.body;

    // userIdx로 유저권한 뽑아오기
    try{
        const selectUserInfooRows = await userModel.selectUserInfoo(userIdx);
        var auth = selectUserInfooRows[0].userAuth;
        let mypageAdoptRows, mypageProtectRows;

        if (auth === 'U' || auth === 'M'){
            mypageAdoptRows = await userModel.selectMypageAdoptUM(userIdx);
            mypageProtectRows = await userModel.selectMypageProtectUM(userIdx);
        }
        else{
            mypageAdoptRows = await userModel.selectMypageAdoptC(userIdx);
            mypageProtectRows = await userModel.selectMypageProtectC(userIdx);
        }

        let resultRows = [
            mypageAdoptRows,
            mypageProtectRows
        ];

        res.json({
            isSuccess: true,
            result : resultRows
        });

    } catch (err){
        logger.error(`selectMyPageAdopt DB Connection error\n: ${err.message}`);
        return res.status(500).send(`Error: ${err.message}`);
    }
}


/**
 * User 8. 마이페이지 펀딩 조회 API
 * [GET] /user/mypage/adopt
 */
exports.getMypageAdopt = async function(req, res){
    // const userId = req.verifiedToken.userId;  // 토큰화

    const {
        userIdx
    } = req.body;

    // userIdx로 유저권한 뽑아오기
    try{
        const selectUserInfooRows = await userModel.selectUserInfoo(userIdx);
        var auth = selectUserInfooRows[0].userAuth;
        let mypageFundingRows;

        if (auth === 'U' || auth === 'M'){
            mypageFundingRows = await userModel.selectMypageFundingUM(userIdx);
        }
        else{
            mypageFundingRows = await userModel.selectMypageAdoptC(userIdx);
        }

        let resultRows = [
            mypageFundingRows
        ];

        res.json({
            isSuccess: true,
            result : resultRows
        });

    } catch (err){
        logger.error(`selectMyPageAdopt DB Connection error\n: ${err.message}`);
        return res.status(500).send(`Error: ${err.message}`);
    }
}











