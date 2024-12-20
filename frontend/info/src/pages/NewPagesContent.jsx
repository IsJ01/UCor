import axios from "axios";
import NewButton from "./reusable/NewButton.jsx";

import {pages_api_url, texts_api_url, elements_api_url} from "../give_objects.js";

function add(id) {
    let new_row = document.createElement("div");
    new_row.style.display = "flex";
    new_row.style.alignItems = "center";

    let new_row_label = document.createElement("label");
    new_row_label.innerText = "Tag: ";

    let new_row_input = document.createElement("input");
    new_row_input.style.height = "25px";

    let new_row_button = document.createElement("button");
    new_row_button.style.border = "1px solid black";
    new_row_button.style.background = "white";
    new_row_button.style.borderRadius = "5px";
    new_row_button.style.width = "25px";
    new_row_button.style.height = "25px";
    new_row_button.style.lineHeight = "20px";
    new_row_button.style.fontSize = "25px";
    new_row_button.style.textAlign = "center";
    new_row_button.style.padding = "0px 0px 3px 1px";
    new_row_button.innerText = '×';

    new_row_button.onclick = () => new_row.remove();

    new_row.appendChild(new_row_label);
    new_row.appendChild(new_row_input);
    new_row.appendChild(new_row_button);

    let row_name_label = document.createElement("label");

    row_name_label.innerText = "Text: ";
    row_name_label.style.marginLeft = "20px";

    let row_name_field = document.createElement("input");
    row_name_field.style.height = "25px";

    
    new_row.appendChild(row_name_label);
    new_row.appendChild(row_name_field);
    
    document.getElementById(id).appendChild(new_row);
    return new_row;
}

function apply() {
    let page_name = document.getElementById("new-pages-name").value;

    let elements = [];

    for (let el of document.getElementById("elements-form-list").children) {
        elements.push([el.children[1].value, el.children[4].value]);
    }

    axios.post(`${pages_api_url}/`, {
        uri: page_name
    })
    .then(res => {
        for (let el of elements) {

            axios.post(`${elements_api_url}/`, {
                parentId: -1,
                pageId: res.data.id,
                tag: el[0]
            })
            .then(res2 => {
                console.log(res2, res, el)
                axios.post(`${texts_api_url}/`, {
                    parentId: res2.data.id,
                    pageId: res.data.id,
                    value: el[1]
                });
            });
        }
    });
}

export default function NewPagesContent() {
    return (
        <div className="pages-content">
            <div className="new-pages-form">
                <label htmlFor="new-pages-name">Name: </label>
                <input className="pages-form-entry" id="new-pages-name" name="pages-name"/>
            </div>
            <div className="elements-form">
                <label style={{display: "flex"}}>Elements:&nbsp;&nbsp;<NewButton onClick={() => add("elements-form-list")}/></label>
                <div className="elements-form-list" id="elements-form-list">

                </div>
            </div>
            <button onClick={apply} className="styleBtn styleBtn-outline-ok">Add</button>
        </div>
    );
}