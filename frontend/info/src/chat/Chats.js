import { useState } from "react";
import Chat from "./Chat";
import Row from "./Row";
import "./css/chats.css";
import "../css/buttons.css"
import axios from "axios";
import { chats_api_url } from "../give_objects";

function current_chat(chat, user) {

    let chatPanel = chat ? <Chat chat={chat} user={user}/> : <></>;

    return (
        <>
            {chatPanel}
        </>
    );
}

function delete_chat(id) {
    axios.delete(`${chats_api_url}/${id}/`);
    window.location.reload(true);
}

export default function Chats(props) {

    const [chat, setChat] = useState();
    const [user, setUser] = useState(props.user);
    let chats = props.chats.map(chat => {
        if (chat.user1.id == props.user.id) {
            return (
                <div style={{display: "flex"}}>
                    <Row onClick={() => {setChat(chat)}} user={chat.user2}/>
                    <button style={{height: "32px", marginTop: "10px", marginLeft: "20px", borderRadius: "15px"}} 
                        className="styleBtn styleBtn-outline-danger" 
                        onClick={() => delete_chat(chat.id)}>Delete</button>
                </div>
            );
        } else {
            return (
                <div style={{display: "flex"}}>
                    <Row onClick={() => {setChat(chat)}} user={chat.user1}/>
                    <button style={{height: "32px", marginTop: "10px", marginLeft: "20px", borderRadius: "15px"}} 
                        className="styleBtn styleBtn-outline-danger" 
                        onClick={() => delete_chat(chat.id)}>Delete</button>
                </div>
            );
        }
    });
    return (
        <div style={{display: "flex"}}>
            <div className="chats">
                {chats}
            </div>
            <div id="chat">
                {current_chat(chat, user)}
            </div>
        </div>
    );
}