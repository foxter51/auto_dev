import * as React from "react"

export default function Buttons(props) {

    return (
        <div className="row">
            <div className="col-md-12 text-center">
                { !props.isAuthenticated && <button className="btn btn-primary">Login</button> }
                { props.isAuthenticated && <button onClick={props.logout} className="btn btn-light">Logout</button> }
            </div>
        </div>
    )

}