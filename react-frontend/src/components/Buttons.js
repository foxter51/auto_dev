import * as React from "react"
import AuthService from "../services/AuthService"
import {Link} from "react-router-dom"

export default class Buttons extends React.Component{
    render() {
        return (
            <div className="row">
                <div className="col-md-12 text-center">
                    { AuthService.isAuthenticated() === false && <Link to="/auth"><button className="btn btn-primary">Login</button></Link> }
                    { AuthService.isAuthenticated() === true && <button onClick={AuthService.logout} className="btn btn-light">Logout</button> }
                </div>
            </div>
        )
    }
}