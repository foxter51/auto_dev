import {request} from "../axios_helper"

class AuthService {

    getAuthToken() {
        return window.localStorage.getItem("auth_token")
    }

    setAuthToken(token) {
        window.localStorage.setItem("auth_token", token)
        window.location.reload()
    }

    login(username, password) {
        return request(
            "POST",
                "/login",
            {      username: username,
                        password: password}
        ).then((response) => {
            this.setAuthToken(response.data.token)})
    }

    register(firstname, lastname, username, email, password) {
        return request(
            "POST",
                "/register",
            {  firstname: firstname,
                    lastname: lastname,
                    username: username,
                    email: email,
                    password: password}
        ).then((response) => {
            this.setAuthToken(response.data.token)})
    }

    logout() {
        window.localStorage.removeItem("auth_token")
        window.location.reload()
    }

    isAuthenticated() {
        return this.getAuthToken() !== null
    }
}

const authService = new AuthService()
export default authService