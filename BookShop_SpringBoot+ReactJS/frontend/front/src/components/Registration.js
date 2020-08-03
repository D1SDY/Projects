import React , {Component} from 'react';
import {Link} from "react-router-dom";
import {Form , Button , Col} from 'react-bootstrap';
import '../css/registration.css'


class Registration extends Component {

    constructor(props) {
        super(props);
        this.state = {
            inputName: '',
            inputSurname: '',
            inputUsername: '',
            inputEmail: '',
            inputPassword: '',
            inputRepeatPassword: '',
        }

        this.handleNameChange = this.handleNameChange.bind(this);
        this.handleSurnameChange = this.handleSurnameChange.bind(this);
        this.handleUsernameChange = this.handleUsernameChange.bind(this);
        this.handleEmailChange = this.handleEmailChange.bind(this);
        this.handlePasswordChange = this.handlePasswordChange.bind(this);
        this.handleRepeatPasswordChange = this.handleRepeatPasswordChange.bind(this);
    }
 


    register = () => {

        let registerDTO = {
            username: this.state.inputUsername,
            password: this.state.inputPassword,
            email: this.state.inputEmail,
            name: this.state.inputName,
            surname: this.state.inputSurname
        }

        console.log(registerDTO);


        fetch('/register', {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(registerDTO)
        })
        .then((response) => response.json())
        .then((data) => {
            console.log('ok')
        });

        
    }


    handleNameChange = (event) => {
        this.setState({
            inputName: event.target.value
        })
    }

    handleSurnameChange = (event) => {
        this.setState({
            inputSurname: event.target.value
        })
    }

    handleUsernameChange = (event) => {
        this.setState({
            inputUsername: event.target.value
        });
    }

    handleEmailChange = (event) => {
        this.setState({
            inputEmail: event.target.value
        });
    }

    handlePasswordChange = (event) => {
        this.setState({
            inputPassword: event.target.value
        });
    }

    handleRepeatPasswordChange = (event) => {
        this.setState({
            inputRepeatPassword: event.target.value
        });
    }

    
    render() {
        return(
            <div className="registrationForm">
                <Form>
                    <Form.Group controlId="formBasicUsername">
                        <Form.Label>Name</Form.Label>
                        <Form.Control type="text" placeholder="Name" value = {this.state.inputName} onChange = {this.handleNameChange}/>
                    </Form.Group>

                    <Form.Group controlId="formBasicUsername">
                        <Form.Label>Surname</Form.Label>
                        <Form.Control type="text" placeholder="Surname" value = {this.state.inputSurname} onChange = {this.handleSurnameChange}/>
                    </Form.Group>

                    <Form.Group controlId="formBasicUsername">
                        <Form.Label>Username</Form.Label>
                        <Form.Control type="text" placeholder="Username" value = {this.state.inputUsername} onChange = {this.handleUsernameChange}/>
                    </Form.Group>
                        
                    <Form.Group controlId="formBasicEmail">
                        <Form.Label>Email address</Form.Label>
                        <Form.Control type="email" placeholder="Enter email" value = {this.state.inputEmail} onChange = {this.handleEmailChange}/>
                        {/* <Form.Text className="text-muted">
                        We'll never share your email with anyone else.
                        </Form.Text> */}
                    </Form.Group>

                    <Form.Group controlId="formBasicPassword">
                        <Form.Label>Password</Form.Label>
                        <Form.Control type="password" placeholder="Password" value = {this.state.inputPassword} onChange = {this.handlePasswordChange}/>
                    </Form.Group>

                    <Form.Group controlId="formBasicPassword">
                        <Form.Label>Repeat Password</Form.Label>
                        <Form.Control type="password" placeholder="Repeat Password" value = {this.state.inputRepeatPassword} onChange = {this.handleRepeatPasswordChange}/>
                    </Form.Group>

                    <Form.Row>
                        <Col>
                            <Button variant="primary" type="button" onClick = {this.register}>
                            Submit
                            </Button>
                        </Col>
                        <Col>
                        <Link to="/Login">
                            <Button variant="primary" type="submit" className="haveAccount">
                                Have account
                            </Button>
                        </Link>
                        </Col>
                    </Form.Row>
                </Form>
            </div>
        );
    }
}

export default Registration;