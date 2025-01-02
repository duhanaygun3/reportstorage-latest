import React from 'react';
import { useHistory } from 'react-router-dom';
import './Header.css';

const Header = () => {
    const history = useHistory();

    return (
        <header className="header-bar">
            <h1 className="header-title">Report Storage</h1>
            <nav className="header-nav">
                <button onClick={() => history.push('/')} className="nav-btn">Home</button>
                <button onClick={() => history.push('/laborants')} className="nav-btn">Laborants</button>
                <button onClick={() => history.push('/patients')} className="nav-btn">Patients</button>

            </nav>
        </header>
    );
};

export default Header;
