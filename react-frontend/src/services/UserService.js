import {request} from "../utils/axios_helper"

class UserService {
    getUser(id) {
        return request(
            "GET",
            `/users/${id}`,
            {}
        )
    }

    updateUser(user) {
        return request(
            "PATCH",
            `/users/${user.id}`,
            user
        )
    }

    deleteUser(id) {
        return request(
            "DELETE",
            `/users/${id}`,
        )
    }
}

const userService = new UserService()
export default userService