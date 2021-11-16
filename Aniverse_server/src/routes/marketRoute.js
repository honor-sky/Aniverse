module.exports = function(app){
    const market = require('../controllers/marketController');
    const jwtMiddleware = require('../../../config/jwtMiddleware');

    // 1. 마켓 상품 전체 조회 API
    app.get('/market', market.getMarket);

    // 2. 마켓 카테고리별 상품 조회 API
    app.post('/market/category', market.getCategory);

    // 3. 상품 업로드 API
    app.post('/market/product', market.postProduct);

    // 4. 상품 구매 API
    app.post('/market/purchase', market.productPurchase);
};