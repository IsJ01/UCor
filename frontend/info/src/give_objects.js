// модуль для получения объектов из api

const users_api_url = "http://127.0.0.1:8001";
const reports_api_url = "http://127.0.0.1:8002";
const black_list_api_url = "http://127.0.0.1:8003";
const data_api_url = "http://127.0.0.1:8004";
const user_categories_api_url = "http://127.0.0.1:8005";
const report_categories_api_url = "http://127.0.0.1:8006";
const tasks_api_url = "http://127.0.0.1:8007/api/v1";

// данная функция получает api и возвращает функцию, которая возвращает объекты данного api

function get_objects(api) {
    return function() {
        const xhr = new XMLHttpRequest();
        xhr.open("GET", `${api}/`, false);
        xhr.send();
        return JSON.parse(xhr.responseText);
    } 
}

let get_users = get_objects(users_api_url);
let get_reports = get_objects(reports_api_url);
let get_black_list = get_objects(black_list_api_url);
let get_user_categories = get_objects(user_categories_api_url);
let get_report_categories = get_objects(report_categories_api_url);
let get_organization_data = get_objects(data_api_url);
let get_tasks = get_objects(tasks_api_url);


function get_user(id) {
    let users = get_users();
    return users.filter(user => user.id == id)[0];
}

function get_user_from_email(email) {
    let users = get_users();
    return users.filter(user => user.email == email)[0];
}

function get_report(id) {
    let reports = get_reports();
    return reports.filter(report => report.id == id)[0];
}

function get_black_user(id) {
    let black_list = get_black_list();
    return black_list.filter(bu => bu.id == id)[0];
}

function get_user_category(id) {
    let user_categories = get_user_categories();
    return user_categories.filter(cat => cat.id == id)[0];
}

function get_report_category(id) {
    let report_categories = get_report_categories();
    return report_categories.filter(cat => cat.id == id)[0];
}

function get_data_field(id) {
    let data = get_organization_data();
    return data.filter(field => field.id == id)[0];
}

export {
    users_api_url, reports_api_url, black_list_api_url, user_categories_api_url, report_categories_api_url, data_api_url, tasks_api_url,
    get_users, get_reports, get_black_list, get_user_categories, get_report_categories, get_organization_data,
    get_user, get_user_from_email, get_report, get_black_user, get_user_category, get_report_category, get_data_field, get_tasks
}

