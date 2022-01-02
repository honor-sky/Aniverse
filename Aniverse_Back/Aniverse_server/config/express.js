const express = require('express');
const compression = require('compression');
const methodOverride = require('method-override');
var cors = require('cors');
module.exports = function () {
    const app = express();

    app.use(compression());

    app.use(express.json());

    app.use(express.urlencoded({extended: true}));

    app.use(methodOverride());

    app.use(cors());

    require('../src/app/routes/userRoute')(app);
    require('../src/app/routes/adoptRoute')(app);
    require('../src/app/routes/fundingRoute')(app);
    require('../src/app/routes/protectRoute')(app);
    require('../src/app/routes/marketRoute')(app);

    return app;
};