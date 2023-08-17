import * as React from "react"
import WelcomeContent from "./WelcomeContent"
import AuthContent from "./AuthContent"
import LoginForm from "./LoginForm"
import { request, setAuthToken} from "../axios_helper";
import Buttons from "./Buttons";

export default class AppContent extends React.Component {

    constructor(props) {
        super(props)
        this.state = {
            isAuthenticated: false,
            error: null
        }
    }

    logout = () => {
        this.setState({isAuthenticated: false})
    }

    onLogin = (e, username, password) => {
        e.preventDefault();
        request("POST", "/login", {username: username, password: password}
        ).then((response) => {
            setAuthToken(response.data.token)
            this.setState({isAuthenticated: true})}
        ).catch((error) => {
            this.setState({isAuthenticated: false, error: error.response.data.message})
        })
    }

    onRegister = (e, firstname, lastname, username, email, password) => {
        e.preventDefault();
        request("POST", "/register", {firstname: firstname, lastname: lastname,
            username: username, email: email, password: password}
        ).then((response) => {
            setAuthToken(response.data.token)
            this.setState({isAuthenticated: true})}
        ).catch((error) => {
            this.setState({isAuthenticated: false, error: error.response.data.message})
        })
    }

    clearError = () =>{
        this.setState({error: null})
    }

    render() {
        return (
            <div className="container">
                <Buttons isAuthenticated={this.state.isAuthenticated} logout={this.logout}/>
                {this.state.isAuthenticated === false && <WelcomeContent/>}
                {this.state.isAuthenticated === true && <AuthContent/>}
                {this.state.isAuthenticated === false && <LoginForm onLogin={this.onLogin} onRegister={this.onRegister}
                                                            error={this.state.error} clearError={this.clearError}/>}
            </div>
        )
    }
}