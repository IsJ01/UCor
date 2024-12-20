import { Route } from "react-router-dom";
import { get_pages, get_texts } from "./give_objects";
import JsxParser from "react-jsx-parser";

export default function PagesList() {
    let routes = [];
    for (let page of get_pages()) {
        let pageContent;
        let elements = [];
        for (let el of page.childrens) {
            let element = `<${el.tag}></${el.tag}>`;
            for (let text of get_texts()) {
                if (text.parentId == el.id) {
                    element = `<${el.tag}>${text.value}</${el.tag}>`;
                }
            }
            elements.push(<JsxParser jsx={element}/>)
        }
        pageContent = (
            <div style={{marginTop: "45px"}} id={page.uri}>
                {elements}
            </div>
        );
        routes.push(<Route path={page.uri} element={pageContent}/>)
    }
    return routes;
}