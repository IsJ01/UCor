import "./css/left_bar.css";
import PagesSection from "./PagesSection.jsx";
import Section from "./reusable/Section.jsx";
import Services from "./Services.jsx";

export default function LeftBar(props) {
    return (
        <div className="pages-leftBar">
            <Section text="Pages" content={<PagesSection setContent={props.setContent}/>}/>
            <Section text="Services" content={<Services setContent={props.setContent}/>}/>
        </div>
    );
}