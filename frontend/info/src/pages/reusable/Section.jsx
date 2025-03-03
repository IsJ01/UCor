import "./css/section.css";


// функция отвечает за открытие и закрытие секции
function selectSection(event) {
    let text = event.target.innerText.split(" ");
    if (text[1] === "⯈") {
        event.target.innerText = text[0] + " ⯆"
        event.target.parentNode.children[1].style.display = "block";
    } else {
        event.target.innerText = text[0] + " ⯈"
        event.target.parentNode.children[1].style.display = "none";
    }
    
}

// в компонент секции передаются заголовок и содержимое
export default function Section(props) {
    return (
        <div className="bar-section">
            <div className="section-title" onClick={selectSection}>
                {props.text} ⯆
            </div>
            <div className="bar-section-content">
                {props.content}
            </div>
        </div>
    );
}