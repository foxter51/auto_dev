import * as React from "react"
import WelcomeContent from "./WelcomeContent"
import AuthContent from "./AuthContent"
import AuthService from "../services/AuthService"
import Buttons from "./Buttons";

export default class AppContent extends React.Component {

    render() {
        return (
            <div className="container">
                <Buttons/>
                {AuthService.isAuthenticated() === false && <WelcomeContent/>}
                {AuthService.isAuthenticated() === true && <AuthContent/>}
            </div>
        )
    }
}