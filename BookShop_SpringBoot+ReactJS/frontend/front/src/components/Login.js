import React , {Component} from 'react';
import {Link} from "react-router-dom";
import {Form , Button , Col} from 'react-bootstrap';
import "../css/Login.css";

class Login extends Component {


    constructor(props) {
        super(props);
        this.state = {
            inputUsername: '',
            inputPassword: ''
        }

        this.handleUsernameChange = this.handleUsernameChange.bind(this);
        this.handlePasswordChange = this.handlePasswordChange.bind(this);
    }


    handleUsernameChange = (event) => {
        this.setState({
            inputUsername: event.target.value
        });
    }

    handlePasswordChange = (event) => {
        this.setState({
            inputPassword: event.target.value
        });
    }

    login = (event) => {
        let loginDTO = {
            username: this.state.inputUsername,
            password: this.state.inputPassword
        }

        fetch('/authentication', {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(loginDTO)
        })
        .then((response) => response.json())
        .then((data) => {
            console.log('data', data);
            //retrive token -> save to local storage
            //get role => localstorage set role => in navbar if ls has role = admin -> render adminpage : -> render userpage 
            localStorage.setItem('token', JSON.stringify(data.jwt));
            localStorage.setItem('role', JSON.stringify(data.role.substring(1, data.role.length - 1)));
            localStorage.setItem('name', JSON.stringify(data.name));
            localStorage.setItem('surname', JSON.stringify(data.surname));
            window.location.href = '/home';
        });
    }
   
    render() {
        return (
            <div className="loginForm">
            <Form>

            <Form.Group controlId="formBasicUsername">
                <Form.Label>Username</Form.Label>
                <Form.Control type="text" placeholder="Username" value = {this.state.inputUsername} onChange = {this.handleUsernameChange}/>
            </Form.Group>

            <Form.Group controlId="formBasicPassword">
                <Form.Label>Password</Form.Label>
                <Form.Control type="password" placeholder="Password" value = {this.state.inputPassword} onChange = {this.handlePasswordChange}/>
            </Form.Group>
            <Form.Row>
                <Col>
                    <Button variant="primary" type="button" className="mt-4" onClick = {this.login}>
                    Submit
                    </Button>
                </Col>
                <Col>
                    <Link to="/Registration">
                        <Button variant="primary" type="submit" className="haveAccount mt-4">
                            No account?
                        </Button>
                    </Link>
                </Col>
            </Form.Row>
            
        </Form>
        </div>
            );
        }
}

export default Login;