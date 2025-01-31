import { useState, useEffect } from "react";
import axios from "axios";

import Table from "./reusable/Table";
import { get_sessionid } from "./get_cookies";
import { getUsersText } from "./getText";
import { tables_api_url, get_users } from "./give_objects";
import './css/users_list.css';
import CancelButton from "./reusable/CancelButton";

export default function UsersList(props) {
    const [user, setUser] = useState({});
    const [users, setUsers] = useState([]);
    const [headers, setHeaders] = useState([]);
    const [filter, setFilter] = useState({});
    const users_api_url = 'http://localhost:8001';
    const user_categories_api_url = "http://127.0.0.1:8005";
    const text = getUsersText(props.lang);
    useEffect(() => {
        updateUser();
        _get_users();
    }, []);

    function updateUser() {
        let sessionid = get_sessionid();
        axios.get(`${users_api_url}/current/`, 
            {headers: {'sessionid': sessionid}}).then(res => {
            if (res.data.is_authenticated) {
                setUser(res.data);
            }
        });
    }

    // функция получения нового пользователя
    function _get_users() {
        axios.get(user_categories_api_url).then(res => {
            setHeaders(["Id", "Name", ...res.data.map(cat => cat.name), "Staff"]);
            axios.get(users_api_url).then(users => {
                        setUsers(users.data.map(user => 
                            [   
                                {isId: true, text: user.id, onclick: () => {}, onblur: () => {}},
                                {text: user.username, tag: 'link', href: user.id},
                                ...res.data.map(cat => ({id: `${cat.name}`, tag: "input", 
                                    text: JSON.parse(user.categories)[cat.name]})),
                                {text: user.is_staff ? "✓" : "×"},
                            ]
                        )
                    )
                }
            );
        });
    }

    // функция, которая отправляет изменения на сервер
    function apply() {
        for (let row of document.getElementById('table-body').children) {
            let categories = {};
            for (let cat of row.children) {
                if (cat.children[0] && headers.includes(cat.children[0].id)) {
                    categories[cat.children[0].id] = cat.children[0].value;
                }
            }
            categories = JSON.stringify(categories);
            axios.patch(`${users_api_url}/${row.children[0].children[0].value}/`, {categories: categories},
                    {headers: {
                        sessionid: get_sessionid()
                    }}
            )
        }
    }

    // функция открытия диалога филтров
    function show_dialog() {
        document.getElementById('filter-dialog').showModal();
    }
    
    // функция закрытия диалога
    function close_dialog() {
        document.getElementById('filter-dialog').close();
    }

    // функция обновления филтров
    function filter_() {
        let new_filter = {}
        for (let row of document.getElementById('filter-form').children) {
            new_filter[row.children[0].innerText] = row.children[1].value;
        }
        setFilter(new_filter);
        close_dialog();
    }

    // функция получения отфильтрованных пользователей
    function get_filter_users(users) {
        let filter_users = [];
        users: for (let user of users) {
            for (let id in user) {
                if (filter[headers[id]]) {
                    continue users;
                }
                if (filter[headers[id]] && !user[id].text.trim().includes(filter[headers[id]].trim())) {
                    continue users;
                }
            }
            filter_users.push(user);
        }
        return filter_users;
    }

    function s2ab(s) {
        var buf = new ArrayBuffer(s.length);
        var view = new Uint8Array(buf);
        for (var i = 0; i != s.length; i++) {
            view[i] = s.charCodeAt(i) & 0xFF;
        }
        return buf;
    }

    function getFile(fileName, type) {
        return function() {
            let users = get_users().map(user => {
            let cats = headers.slice(2, -1).map(cat => 
                JSON.parse(user.categories)[cat]
            );
            return [user.id, user.username, ...cats, user.is_staff].map(f => String(f));
            });
            let data = {
                fileName: fileName,
                headers_: headers,
                rows: users
            }
            let xhr = new XMLHttpRequest();
            xhr.open("POST", `${tables_api_url}/${type}`, false);
            xhr.setRequestHeader("Content-Type", "application/json")
            xhr.send(JSON.stringify(data));
            let rd = xhr.responseText;

            let file = new Blob([s2ab(atob(rd))], {type: ""});
            var link = document.createElement('a');
            link.href = window.URL.createObjectURL(file);
            link.download = fileName;
            document.body.appendChild(link);
            link.click();
            document.body.removeChild(link);
            URL.revokeObjectURL(fileName);
        };
    }

    return (
        <div className="users-list">
            <dialog id="filter-dialog" className="filter-dialog">
                <CancelButton func={close_dialog}/>
                <form method="dialog" id="filter-form" >
                    {headers.slice(1).map(head => 
                        <p id={`${head}_row`}>
                            <label htmlFor={head}>{head}</label>
                            <input id={head}/>
                        </p>)
                    }
                </form>
                <div className="dialog-buttons">
                    <button className="styleBtn-outline-normal" title="Apply changes" onClick={filter_}>Apply</button>
                    <button className="styleBtn-outline-normal" type="button" onClick={close_dialog} title="Cancel changes">Cancel</button>
                </div>
            </dialog>
            <button style={{marginBottom: '5px'}} onClick={show_dialog} className="styleBtn styleBtn-outline-use">
                {text.filterBtn}
            </button>
            <a onClick={getFile("users.xlsx", "excel")} href="" style={{marginRight: "10px", marginLeft: "10px"}}>Exel</a>
            <a onClick={getFile("users.pdf", "pdf")} href="">PDF</a>
            <Table rows={get_filter_users(users)} headers={headers}/>
            <button onClick={apply} style={{marginTop: '5px'}} className="styleBtn styleBtn-outline-ok">
                {text.applyBtn}
            </button>
        </div>
    );
}