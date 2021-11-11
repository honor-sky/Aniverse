module.exports = function(app){
    const user = require('../controllers/userController');
    const jwtMiddleware = require('../../../config/jwtMiddleware');

    app.get('/app/test', user.getTest);

    // 1. 유저 생성 (회원가입) API
    app.post('/signup', user.signUp);

    // 2. 아이디 중복체크 API
    app.post('/user/checkid', user.checkId);

    // 3-1. 일반 로그인 API
    app.post('/signin', user.signIn);

    // 3-2. 센터 로그인 API
    // app.post('/signin/center', user.signInCenter);

    // // 3-3. 마켓 로그인 API
    // app.post('/signin/market', user.signInMarket);

    // 4. 메인화면 조회 API
    app.get('/main', user.getMain);

    // 5. 마이페이지 메인화면 조회 API
    app.post('/user/mypage', user.getMypage);

    // 6. 마이페이지 일반/상인 -> 구매/판매 상품 조회 API
    app.post('/user/mypage/product', user.getMypageProduct);

    // 7. 마이페이지 일반/보호소 -> 입양/임시보호 조회 API
    app.post('/user/mypage/adopt', user.getMypageAdopt);

    // 8. 마이페이지 일반/보호소 -> 펀딩중 조회 API
    // app.post('/user/mypage/funding', user.getMypageFunding);
};