import { useState, useEffect } from 'react';
import axios from 'axios';

import './css/index.css';
import './css/buttons.css';

import { get_sessionid } from './get_cookies.js';
import { get_organization_data } from './give_objects.js';

export default function Header() {
    const [user, setUser] = useState({});
    const [title, setTitle] = useState("");
    const api_url = "http://127.0.0.1:8001";

    useEffect(() => {
        updateTitle();
        updateUser();
    }, []);

    function updateTitle() {
        let title = get_organization_data().filter(f => f["name"] == "title");
        if (title[0]) {
            setTitle(title[0]["value"]);
        } else {
            setTitle("");
        }
    }

    function updateUser() {
        let sessionid = get_sessionid();
        axios.get(`${api_url}/current/`, 
            {headers: {'sessionid': sessionid}}).then(res => {
            if (res.data.is_authenticated) {
                setUser(res.data);
            }
        });
    }

    function logout() {
        let sessionid = get_sessionid();
        axios.post(`${api_url}/logout/`, {}, {headers: {'sessionid': sessionid}});
        document.cookie = 'sessiondid="";max-age=1;path=/';
        window.location.reload(true);
    }

    return (
        <header>
            <div className="title">
                <h2>Reports</h2>
                <label>{title}</label>
            </div>
            <div className="menuBar">
                <p className="options">
                    { (user.is_superuser || user.is_staff) && 
                        <a className="option styleBtn" href="/configure/" 
                        title="Configure">Configure ⚙</a> 
                    }
                    <a className="option styleBtn styleBtn-outline-neutral"
                    href="/" title="Home">Home</a>
                    <a className="option styleBtn styleBtn-outline-detail-1"
                    href="/users/" title="Users list">Users</a>
                    { user.is_authenticated && 
                        <>
                            {user.is_staff && 
                            <a className="option styleBtn styleBtn-outline-detail-2" 
                                href="/reports/" 
                                title="Reports (yours, for you)">Reports</a>}
                            <a className="option styleBtn styleBtn-outline-dark"
                                href="/blackList/" 
                                title="Black list of users">Black List</a>
                        </>
                    }
                </p>
                { user.is_authenticated ?
                    <p className="login_or_logout">
                        <a href={`/users/${user.id}`}
                        className="user_label" title="Profile">{ user.username }</a>
                        <button className="user_btn styleBtn styleBtn-outline-warning"
                        onClick={logout} title="Logout">Logout</button>
                    </p>
                    :
                    <p className="login_or_logout">
                        <a className="user_btn styleBtn styleBtn-outline-ok"
                        href="/login/" title="Login">Login</a>
                        <a className="user_btn styleBtn styleBtn-outline-new"
                        href="/register/" title="Register">Registry</a>
                    </p>
                }
            </div>
        </header>
    );
}