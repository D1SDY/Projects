import React , {Component} from 'react';
import {Link} from 'react-router-dom';
import {Form , Button } from 'react-bootstrap';
import '../css/registration.css'

//Component that allow us to create book and add it ti DB 
class AddBook extends Component {

    constructor(props) {
        super(props);
        this.state = {
            inputBookName: '',
            inputBookAuthor: '',
            inputBookPrice: '',
            inputBookAmount: ''
        }

        this.handleBookNameChange = this.handleBookNameChange.bind(this);
        this.handleBookAuthorChange = this.handleBookAuthorChange.bind(this);
        this.handleBookPriceChange = this.handleBookPriceChange.bind(this);
        this.triggerAddBook = this.triggerAddBook.bind(this);
        this.handleBookAmountChange = this.handleBookAmountChange.bind(this);
    }


    handleBookNameChange = (event) => {
        this.setState({
            inputBookName: event.target.value
        });
    }

    handleBookAuthorChange = (event) => {
        this.setState({
            inputBookAuthor: event.target.value
        });
    }

    handleBookPriceChange = (event) => {
        this.setState({
            inputBookPrice: event.target.value
        });
    }

    handleBookAmountChange = (event) => {
        this.setState({
            inputBookAmount: event.target.value
        });
    }

    //On click event for adding book in DB with fetch Post method 
    triggerAddBook = (event) => {
        let bookDTO = {
            name: this.state.inputBookName,
            price: this.state.inputBookPrice,
            author: this.state.inputBookAuthor,
            ammount: this.state.inputBookAmount
        }

        let token = JSON.parse(localStorage.getItem('token'));

        fetch('/books', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${token}`    
            },
            body: JSON.stringify(bookDTO)
        })
        .then((response) => response.json())
        .then((data) => {
            console.log('data', data);
        });
    }

    //Creating style with bootstrap form adding book
    render() {
        return(
            <div className="registrationForm">
                <Form>
                <Form.Group controlId="formBasicName">
                    <Form.Label>Name</Form.Label>
                    <Form.Control type="text" placeholder="Book Name" value = {this.state.inputBookName} onChange = {this.handleBookNameChange}/>
                </Form.Group>
                    
                <Form.Group controlId="formBasicEmail">
                    <Form.Label>Book Author</Form.Label>
                    <Form.Control type="email" placeholder="Book Author" value = {this.state.inputBookAuthor} onChange = {this.handleBookAuthorChange}/>
                </Form.Group>

                <Form.Group controlId="formBasicName">
                    <Form.Label>Price</Form.Label>
                    <Form.Control type="text" placeholder="Book Name" value = {this.state.inputBookPrice} onChange = {this.handleBookPriceChange}/>
                </Form.Group>

                <Form.Group controlId="formBasicName">
                    <Form.Label>Amount of books in store</Form.Label>
                    <Form.Control type="text" placeholder="Book Name" value = {this.state.inputBookAmount} onChange = {this.handleBookAmountChange}/>
                </Form.Group>

                

                <Link to="./AdminPage">
                    <Button variant="primary" type="button" className="mt-4" onClick = {this.triggerAddBook}>
                    Submit
                    </Button>
                </Link>
            </Form>
            </div>
        );
    }
}

export default AddBook;