import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './components/App';
import 'bootstrap/dist/css/bootstrap.min.css'
import {BrowserRouter, Route, Routes} from "react-router-dom"
import LoginForm from "./components/LoginForm"

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
    <BrowserRouter>
        <Routes>
            <Route index element={<App/>}/>
            <Route path="/auth" element={<LoginForm/>}/>
        </Routes>
    </BrowserRouter>
);