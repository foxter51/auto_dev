import React, { useState, useEffect } from 'react'
import userService from '../services/UserService'
import {useParams} from "react-router"
import LoadingEffect from "../components/LoadingEffect"
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome"
import {faCheck, faPen, faTrash} from '@fortawesome/free-solid-svg-icons'
import authService from "../services/AuthService"

let equal = require('fast-deep-equal')

export default function UserPage() {
    const [originalUser, setOriginalUser] = useState(null)
    const [user, setUser] = useState(null)
    const [loading, setLoading] = useState(true)
    const [error, setError] = useState(null)

    const {id} = useParams()

    const [editing, setEditing] = useState({
        firstname: false,
        lastname: false,
        username: false,
        email: false,
        password: false
    })

    useEffect(() => {
        userService.getUser(id)
            .then(response => {
                setOriginalUser(response.data)
                setUser(response.data)
                setLoading(false)
            }).catch(error => {
                setError(error.response.data.message)})
    }, [id])

    const handleEdit = (field) => {
        setError(null)
        setEditing((prevState) => ({
            ...prevState,
            [field]: !prevState[field],
        }))
    }

    const submitUpdate = () => {
        if (!equal(originalUser, user)){
            userService.updateUser(user)
                .then((response) => {
                    setUser(response.data)
                    setError("OK")
                }).catch((error) => {
                setError(error.response.data.message)
                setUser(originalUser)
            })
        } else setError("Everything is up-to-date")
    }

    const submitDelete = () => {
        if(window.confirm('Are you sure you want to delete this user?')){
            userService.deleteUser(user.id)
                .catch((error) => {
                    setError(error.response.data.message)
                })
                .then(() => authService.logout())
        } else setError("Deletion canceled")
    }

    if(loading) {
        return <LoadingEffect/>
    }

    return (
        <div className="container d-flex justify-content-center">
            <div style={{marginRight: "auto"}}>
                <div className="h1">Profile</div>
                <div>{error}</div>
            </div>
            <div className="card col-8">
                <div className="card-body">
                    <div className="row">
                        <div className="col">Name</div>
                        <div className="col"><strong>{user.firstname} {user.lastname}</strong></div>
                        {user.id === +authService.getAuthUserId() &&
                            <>
                                {editing.firstname && editing.lastname ?
                                    <>
                                        <div className="col-2">
                                            <input className="form-control"
                                                   type="text"
                                                   value={user.firstname}
                                                   maxLength="32"
                                                   onChange={(e) => setUser({ ...user, firstname: e.target.value })}
                                            />
                                        </div>

                                        <div className="col-2">
                                            <input className="form-control"
                                                   type="text"
                                                   value={user.lastname}
                                                   maxLength="32"
                                                   onChange={(e) => setUser({ ...user, lastname: e.target.value })}
                                            />
                                        </div>

                                        <div className="col-1">
                                            <FontAwesomeIcon icon={faCheck} className="float-end" onClick={() => {
                                                handleEdit('firstname')
                                                handleEdit('lastname')
                                                submitUpdate()
                                            }}/>
                                        </div>
                                    </>
                                    :
                                    <div className="col-1">
                                        <FontAwesomeIcon icon={faPen} className="float-end" onClick={() => {
                                            handleEdit('firstname')
                                            handleEdit('lastname')
                                        }}/>
                                    </div>
                                }
                            </>
                        }
                    </div>

                    <div className="row">
                        <div className="col">Username</div>
                        <div className="col">{user.username}</div>
                        {user.id === +authService.getAuthUserId() &&
                            <>
                                {editing.username ?
                                    <>
                                        <div className="col-2">
                                            <input className="form-control"
                                                   type="text"
                                                   value={user.username}
                                                   maxLength="32"
                                                   onChange={(e) => setUser({ ...user, username: e.target.value })}
                                            />
                                        </div>

                                        <div className="col-1">
                                            <FontAwesomeIcon icon={faCheck} className="float-end" onClick={() => {
                                                handleEdit('username')
                                                submitUpdate()
                                            }}/>
                                        </div>
                                    </>
                                    :
                                    <div className="col-1">
                                        <FontAwesomeIcon icon={faPen} className="float-end" onClick={() => handleEdit('username')}/>
                                    </div>
                                }
                            </>
                        }
                    </div>

                    <div className="row">
                        <div className="col">E-mail</div>
                        <div className="col">{user.email}</div>
                        {user.id === +authService.getAuthUserId() &&
                            <>
                                {editing.email ?
                                    <>
                                        <div className="col-2">
                                            <input className="form-control"
                                                   type="email"
                                                   value={user.email}
                                                   maxLength="32"
                                                   onChange={(e) => setUser({ ...user, email: e.target.value })}
                                            />
                                        </div>

                                        <div className="col-1">
                                            <FontAwesomeIcon icon={faCheck} className="float-end" onClick={() => {
                                                handleEdit('email')
                                                submitUpdate()
                                            }}/>
                                        </div>
                                    </>
                                    :
                                    <div className="col-1">
                                        <FontAwesomeIcon icon={faPen} className="float-end" onClick={() => handleEdit('email')}/>
                                    </div>
                                }
                            </>
                        }
                    </div>

                    <div className="row">
                        <div className="col-2">Password</div>
                        {user.id === +authService.getAuthUserId() &&
                            <>
                                {editing.password ?
                                    <>
                                        <div className="col-2">
                                            <input className="form-control"
                                                   type="password"
                                                   value={user.password}
                                                   maxLength="64"
                                                   onChange={(e) => setUser({ ...user, password: e.target.value })}
                                            />
                                        </div>

                                        <div className="col-1">
                                            <FontAwesomeIcon icon={faCheck} className="float-end" onClick={() => {
                                                handleEdit('password')
                                                submitUpdate()
                                            }}/>
                                        </div>
                                    </>
                                    :
                                    <div className="col-1">
                                        <FontAwesomeIcon icon={faPen} className="float-end" onClick={() => handleEdit('password')}/>
                                    </div>
                                }
                            </>
                        }
                    </div>

                    <div className="row">
                        <div className="col">Teams</div>
                        <div className="col">
                            {user.teams ?
                                user.teams.map(team => <div key={team.id}>{team.name}</div>)
                                :
                                <div>no teams</div>
                            }
                        </div>
                        {user.id === +authService.getAuthUserId() &&<div className="col-1"/>}
                    </div>

                    {user.id === +authService.getAuthUserId() &&
                        <a href="#" className="text-danger text-decoration-none" onClick={() => {
                            submitDelete()
                        }}>
                           Delete account <FontAwesomeIcon icon={faTrash}/>
                        </a>
                    }
                </div>
            </div>
        </div>
    )
}