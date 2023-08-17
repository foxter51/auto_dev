import * as React from "react"
import classNames from "classnames"

export default class LoginForm extends React.Component {

    constructor(props) {
        super(props)
        this.state = {
            active: "login",
            firstName: "",
            lastName: "",
            username: "",
            email: "",
            password: "",
            onLogin: props.onLogin,
            onRegister: props.onRegister
        }
    }

    onChangeHandler = (event) => {
        let name = event.target.name
        let value = event.target.value;
        this.setState({[name]: value})
    }

    onSubmitLogin = (e) => {
        this.state.onLogin(e, this.state.username, this.state.password)
    }

    onSubmitRegister = (e) => {
        this.state.onRegister(e, this.state.firstName, this.state.lastName, this.state.username, this.state.email,
            this.state.password)
    }

    render() {
        return (
            <div className="card col-4">
                <ul className="nav nav-pills nav-justified">
                    <li className="nav-item">
                        <button className={classNames("nav-link", this.state.active === "login" ? "active" : "")} id="tab-login"
                                onClick={() => this.setState({active: "login"})}>Login</button>
                    </li>
                    <li className="nav-item">
                        <button className={classNames("nav-link", this.state.active === "register" ? "active" : "")} id="tab-register"
                                onClick={() => this.setState({active: "register"})}>Register</button>
                    </li>
                </ul>
                <div className="card-body tab-content">
                    <div className={classNames("tab-pane", "fade", this.state.active === "login" ? "show active" : "")}>
                        <form onSubmit={this.onSubmitLogin}>
                            <div className="form-outline">
                                <label className="form-label" htmlFor="loginName">Username</label>
                                <input type="text" id="loginName" name="username" className="form-control" maxLength="32" onChange={this.onChangeHandler}/>
                            </div>
                            <div className="form-outline">
                                <label className="form-label" htmlFor="loginPassword">Password</label>
                                <input type="password" id="loginPassword" name="password" className="form-control" maxLength="64" onChange={this.onChangeHandler}/>
                            </div>
                            <button type="submit" className="btn btn-primary">Sign in</button>
                        </form>
                    </div>
                    <div className={classNames("tab-pane", "fade", this.state.active === "register" ? "show active" : "")}>
                        <form onSubmit={this.onSubmitRegister}>
                            <div className="form-outline">
                                <label className="form-label" htmlFor="firstName">Firstname</label>
                                <input type="text" id="firstName" name="firstName" className="form-control" maxLength="32" onChange={this.onChangeHandler}/>
                            </div>
                            <div className="form-outline">
                                <label className="form-label" htmlFor="lastName">Lastname</label>
                                <input type="text" id="lastName" name="lastName" className="form-control" maxLength="32" onChange={this.onChangeHandler}/>
                            </div>
                            <div className="form-outline">
                                <label className="form-label" htmlFor="registerLogin">Username</label>
                                <input type="text" id="registerLogin" name="username" className="form-control" maxLength="32" onChange={this.onChangeHandler}/>
                            </div>
                            <div className="form-outline">
                                <label className="form-label" htmlFor="registerEmail">Email</label>
                                <input type="email" id="registerEmail" name="email" className="form-control" maxLength="32" onChange={this.onChangeHandler}/>
                            </div>
                            <div className="form-outline">
                                <label className="form-label" htmlFor="registerPassword">Password</label>
                                <input type="password" id="registerPassword" name="password" className="form-control" maxLength="64" onChange={this.onChangeHandler}/>
                            </div>
                            <button type="submit" className="btn btn-primary">Sign up</button>
                        </form>
                    </div>
                </div>
            </div>
        )
    }
}