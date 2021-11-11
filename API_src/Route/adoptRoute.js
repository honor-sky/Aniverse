module.exports = function(app){
    const adopt = require('../controllers/adoptController');
    const jwtMiddleware = require('../../../config/jwtMiddleware');

    // 1.  activity_adopt_upload.xml : 입양신청위한 동물 정보 업로드 API
    // app.post('/app/animalinfo', adopt.postAdoptAniamlInfo);

    // 2. adopt_list.xml 입양중, (임시보호), 입양완료 조회 API
    app.post('/adopt/list', adopt.getAdoptList);

    // 3.  activity_adopt_info.xml 사진눌렀을시 정보조회 API
    app.post('/adopt/imgclick', adopt.getImgClick);
    //
    // 4. activity_adopt_request.xml : 입양신청위한 정보 업로드 API
    app.post('/adopt/userinfo', adopt.postAdoptUserInfo);

    //5. activity_adopt_monitoring_upload.xml : 후기(모니터링)글 업로드 API
    // app.post('/adopt/review', adopt.postReview);

    //6. 후기사진 업로드 API


    // 7.  activity_adopt_monitor.xml 후기(모니터링)글 : 검진결과, 소식 조회
    // app.get('/adopt/review', adopt.getReview);

};