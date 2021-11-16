
module.exports = function(app){
    const protect = require('../controllers/protectController');
    const jwtMiddleware = require('../../../config/jwtMiddleware');


    //  activity_protect_upload.xml : 입양신청위한 동물 정보 업로드 API
    // app.post('/app/protect/postprotectAniamlInfo1', protect.postProtectAniamlInfo1);

    // ?????.centerIdxConnect API
    // app.post('/app/ ????',protect.postcenterIdxConnect);

    // adopt_list.xml 보호중, (임시보호), 보호완료 조회API
    app.get('/protect/list',protect.getProtectList);

    //  activity_protect_info.xml 사진눌렀을시 정보조회 API
    app.post('/protect/info',protect.getImgClick);
    //
    // activity_protect_request.xml : 보호신청위한 정보 업로드 API
    app.post('/protect/info',protect.protectUserInfo);

};