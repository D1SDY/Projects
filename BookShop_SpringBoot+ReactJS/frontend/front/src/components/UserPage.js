import React , { Component } from 'react';
import {  Button ,Badge , Row , Col, InputGroup , FormControl, Tabs , Tab , Card} from "react-bootstrap";
import '../css/userPage.css';


/**
 * Admin Page . If user logged with admin role it will show him this page . 
 * It have few diferrences like Buttons that relocate to addBook and create Auction pages .
 * **/ 


class AdminPage extends Component {

    constructor(props) {
        super(props);
        this.state = {
            userId: '',
            name: '',
            surname: '',
            role: '' , 
            userBooks : [] , 
            auctions : [] ,
            isHere: false
        }

        this.username = React.createRef();
        this.email = React.createRef();
        this.name = React.createRef();
        this.surname = React.createRef();
        this.saveUserChanges = this.saveUserChanges.bind(this);
    }

    //Get list of books , which user bought . And auction in which user partisipated  
    componentDidMount() {
        let token = JSON.parse(localStorage.getItem('token'));
        let userIdNow = null;

        fetch('/users/current', {
            method: 'GET',
            headers: {
                'Authorization': `Bearer ${token}`
            }
        })
        .then((response) => response.json())
        .then((data) => {
            console.log('data', data);
            userIdNow = data.id;    
        });
        
        setTimeout(() => {
            this.setState({
                userId: userIdNow
            });
            fetch('/orders/current', {
                method: 'GET',
                headers: {
                    'Authorization': `Bearer ${token}`
                }
            })
            .then((response) => response.json())
            .then((data) => {
                console.log('data', data[0].book.name)
                this.setState({
                    userBooks: data
                });
            });
    
            let auctionsUser = null;
    
            fetch(`/users/all/auctions/${this.state.userId}`, {
                method: 'GET',
                headers: {
                    'Authorization': `Bearer ${token}`
                }
            })
            .then((response) => response.json())
            .then((data) => {
                console.log('data', data)
                auctionsUser = data;
            });

            setTimeout(() => {
                console.log(auctionsUser);
                this.setState({
                    auctions: auctionsUser , 
                    isHere: true
                });
            }, 600)
            
        }, 800);

        setTimeout(() => {
            this.setState({
                name: JSON.parse(localStorage.getItem('name')),
                surname: JSON.parse(localStorage.getItem('surname')),
                role: JSON.parse(localStorage.getItem('role'))    
            });
            console.log(this.state.userId);
        }, 1000);
    }

    //After user clicked save changes button it will change user name , surname , username and email in DB and render new name and surname on page 
    saveUserChanges = (event) => {
        let updateUserDTO = {
            username: this.username.current.value,
            email: this.email.current.value,
            name: this.name.current.value,
            surname: this.surname.current.value
        }

        const token = JSON.parse(localStorage.getItem('token'));

        let name = updateUserDTO.name;
        let surname = updateUserDTO.surname;

        localStorage.setItem('name', JSON.stringify(name));
        localStorage.setItem('surname', JSON.stringify(surname));
        console.log('dto', updateUserDTO)

        fetch('/users/update', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${token}`    
            },
            body: JSON.stringify(updateUserDTO)
        })
        .then((response) => response.json())
        .then((data) => {
            console.log('data', data);
        });
    }
    
    render() {
        return(
            <React.Fragment>
                <div className="head">
                    <Row>
                        <Col xs={12} md={8}>
                            <h1>
                                {this.state.name} , {this.state.surname} <span>&nbsp;   </span> <Badge variant="secondary">{this.state.role}</Badge>
                            </h1>
                        </Col>
                        <Col xs={6} md={4} className="mt-2">
                        </Col>
                    </Row>
                </div>
                <Row className="mb-4">
                    <Col>
                        <div className="information">
                            <h3>
                                Change Information
                            </h3>
                            <InputGroup className="mb-3">
                                <InputGroup.Prepend>
                                    <InputGroup.Text id="inputGroup-sizing-default">Username</InputGroup.Text>
                                </InputGroup.Prepend>
                                <FormControl
                                aria-label="Default"
                                aria-describedby="inputGroup-sizing-default"
                                ref = {this.username}
                                />
                            </InputGroup>
                            <InputGroup className="mb-3">
                                <InputGroup.Prepend>
                                    <InputGroup.Text id="inputGroup-sizing-default">Email</InputGroup.Text>
                                </InputGroup.Prepend>
                                <FormControl
                                aria-label="Default"
                                aria-describedby="inputGroup-sizing-default"
                                ref = {this.email}
                                />
                            </InputGroup>
                            <InputGroup className="mb-4">
                                <InputGroup.Prepend>
                                    <InputGroup.Text>First and last name</InputGroup.Text>
                                </InputGroup.Prepend>
                                <FormControl ref = {this.name}/>
                                <FormControl ref = {this.surname}/>
                            </InputGroup>
                            <Button variant="outline-dark" type = "button" onClick = {this.saveUserChanges}>Save Changes</Button>
                        </div>
                    </Col>
                    <Col>
                        <div className="orders">
                            <h3>
                                Orders History
                            </h3>
                            <Tabs defaultActiveKey="home" transition={false} id="noanim-tab-example">
                                <Tab eventKey="home" title="What you will read">
                                    <Row className="mt-2">
                                        {
                                            this.state.userBooks.map(book => (
                                                <Card key={book.id} style={{ width: '7rem', height: "10rem" , margin: '15px'}} >
                                                    <Card.Img variant="top" src={require("./images/06.jpg")} />
                                                    <Card.Title style={{color: "white"}}>{book.book.name}</Card.Title>
                                                </Card>
                                            ))
                                        }
                                    </Row>
                                </Tab>
                                <Tab eventKey="profile" title="Auctiond you participated">
                                    <Row className="mt-2">
                                        {
                                            this.state.auctions.map(auction => (
                                                    <Card key={auction.id} style={{ width: '7rem', height: "10rem" , margin: '15px'}} >
                                                        <Card.Title style={{color: "black"}}>{auction.name}</Card.Title>
                                                    </Card>
                                            ))
                                        }                   
                                    </Row>
                                </Tab>
                            </Tabs>
                        </div>
                    </Col>
                </Row>    
            </React.Fragment>
        );
    } 
}

export default AdminPage;

