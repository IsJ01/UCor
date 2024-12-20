import "./css/page_section.css";

import {get_pages} from "../give_objects.js";

import NewPagesContent from "./NewPagesContent.jsx";

function setPagesContent(func) {
    func(<NewPagesContent/>);
}

export default function PagesSection(props) {
    return (
        <div className="pages-section">
            <div className="pages-section-title">
                <label>Counts: {get_pages().length}</label>
                <div onClick={() => setPagesContent(props.setContent)} 
                    title="new page" className="new-page-btn">+</div>
            </div>
            <div className="pages-list">
                {get_pages().map(page => {
                    return <div>URI: {page.uri} Chilrens: {page.childrens.length}</div>;
                })}
            </div>
        </div>
    );
}