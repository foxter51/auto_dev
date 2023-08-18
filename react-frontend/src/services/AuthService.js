import {request} from "../utils/axios_helper"

class AuthService {

    getAuthToken() {
        return window.localStorage.getItem("auth_token")
    }

    setAuthToken(token) {
        window.localStorage.setItem("auth_token", token)
        window.location.reload()
    }

    getAuthUserId() {
        return window.localStorage.getItem("user_id")
    }

    setAuthUserId(id) {
        window.localStorage.setItem("user_id", id)
    }

    login(username, password) {
        return request(
            "POST",
                "/login",
            {      username: username,
                        password: password}
        ).then((response) => {
            this.setAuthUserId(response.data.id)
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
            this.setAuthUserId(response.data.id)
            this.setAuthToken(response.data.token)})
    }

    logout() {
        window.localStorage.removeItem("auth_token")
        window.localStorage.removeItem("user_id")
        window.location.reload()
    }

    isAuthenticated() {
        return this.getAuthToken() !== null
    }
}

const authService = new AuthService()
export default authService