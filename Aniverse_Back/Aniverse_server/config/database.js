const mysql = require('mysql2/promise');
const {logger} = require('./winston');

const pool = mysql.createPool({
    host: 'aniversee.ct87bq5mjo.ap-northeast-2.rds.amazonaws.com',
    user: 'aniversee',
    port: '3306',
    password: 'aniversee',
    database: 'aniverse'
});

module.exports = {
    pool: pool
};