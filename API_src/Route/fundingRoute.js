module.exports = function(app){
    const funding = require('../controllers/fundingController');
    const jwtMiddleware = require('../../../config/jwtMiddleware');

    // 1. 펀딩리스트 조회 API
    app.get('/funding/list', funding.getFundingList);

    // 2. 펀딩 업로드 API
    app.post('/funding', funding.postFunding);
    //
    // 3. 펀딩사진 클릭 조회 API
    app.post('/funding/ing', funding.getFundingIng);

    // 4. 펀딩 모니터링 업로드 API
    // app.post('/funding/review', funding.postFundingReview);

    // 5. 펀딩 모니터링 조회 API
    app.post('/funding/review', funding.getFundingReview);

    // 6. 젤리 기부 API

};