import './css/home.css';

export default function Home() {
    return (
        <div className='home-content'>
            <h1 className="home_title">Reports list</h1>
            <p className="description">
                Welcome to reports site, user. On this site, you, as well as members of your team
                (school, university, etc), are able to see reports on your activities,
                which are collected by its leaders: reports on your academic performance, reports
                on your behavior, reports on your attendance and many any. Among other things,
                ordinary members of the team can also provide reports, that is, if your behavior is
                inappropriate, you risk <span className="black_list_span">
                    <a style={{textDecoration: 'none', color: 'red'}} href="/blackList/">being blacklisted</a>
                </span>.
                You also have the opportunity to correspond with users. We sincerely rely on your discretion
                and responsibility to ensure that you and other users do not experience any inconvenience.
                All questions to the <a>administrators</a>.
            </p>
        </div>
    );
}