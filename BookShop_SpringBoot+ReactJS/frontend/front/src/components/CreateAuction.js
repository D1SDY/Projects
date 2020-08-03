import React , {Component} from 'react';
import {Form , Button } from 'react-bootstrap';
import '../css/registration.css'


class CreateAuction extends Component {

    constructor(props) {
        super(props);
        this.state = {
            books: [],
            inputName: '',
            inputNumberOfParticipants: '',
            inputStartDate: '',
            inputEndDate: ''
        }

        this.selectedValue = React.createRef();
        this.handleNameChange = this.handleNameChange.bind(this);
        this.handleNumberChange = this.handleNumberChange.bind(this);
        this.triggerAddAuction = this.triggerAddAuction.bind(this);
    }


    handleNameChange = (event) => {
        this.setState({
            inputName: event.target.value
        });
    }

    handleNumberChange = (event) => {
        this.setState({
            inputNumberOfParticipants: event.target.value
        });
    }

    handleStartDateChange = (event) => {
        this.setState({
            inputStartDate: event.target.value
        });
    }

    handleEndDateChange = (event) => {
        this.setState({
            inputEndDate: event.target.value
        })
    }


    triggerAddAuction = (event) => {
        let token = JSON.parse(localStorage.getItem('token'));

        let auctionDTO = {
            name: this.state.inputName,
            ammoutOfParticipants: this.state.inputNumberOfParticipants,
            start: this.state.inputStartDate,
            end: this.state.inputEndDate,
            book: {
                name: this.selectedValue.current.value
            }
        }

        console.log(this.selectedValue.current.value)



        fetch('/auctions', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${token}`    
            },
            body: JSON.stringify(auctionDTO)
        })
        .then((response) => response.json())
        .then((data) => {
            console.log('data', data);
        });
    }




    componentDidMount() {
        let token = JSON.parse(localStorage.getItem('token'));

        fetch('/books', {
            method: 'GET',
            headers: {
                'Authorization': `Bearer ${token}`
            }
        })
        .then((response) => response.json())
        .then((data) => {
            console.log('data', data)
            this.setState({
                books: data
            });

        });

    }

    render() {
        return(
            <div className="registrationForm">
                <Form>

                <Form.Group controlId="formBasicUsername">
                    <Form.Label>Name</Form.Label>
                    <Form.Control type="text" placeholder="Auction Name" value = {this.state.inputName} onChange = {this.handleNameChange}/>
                </Form.Group>
                
                <Form.Group controlId="formBasicUsername">
                    <Form.Label>Number of participants</Form.Label>
                    <Form.Control type="text" placeholder="Number of participants" value = {this.state.inputNumberOfParticipants} onChange = {this.handleNumberChange}/>
                </Form.Group>
                <Form.Group controlId="formBasicDate">
                    <Form.Label>Start Date</Form.Label>
                    <Form.Control type="date" placeholder="Start date" value = {this.state.inputStartDate} onChange = {this.handleStartDateChange}/>
                </Form.Group>
                <Form.Group controlId="formBasicEndDate">
                    <Form.Label>End date</Form.Label>
                    <Form.Control type="date" placeholder="End date" value = {this.state.inputEndDate} onChange = {this.handleEndDateChange}/>
                </Form.Group>

                <Form.Group controlId="exampleForm.ControlSelect2">
                    <Form.Label>Choose book for auction</Form.Label>
                    <Form.Control as="select" ref = {this.selectedValue}>
                        {
                            this.state.books.map(book => (
                                <option key = {book.name}>{book.name}</option>
                            ))
                        }
                    </Form.Control>
                </Form.Group>
                {/* <Link to="./AdminPage"> */}
                    <Button variant="primary" type="button" className="mt-4" onClick = {this.triggerAddAuction}>
                    Submit
                    </Button>
                {/* </Link> */}
                

                
            </Form>
            </div>
        );
    }
}

export default CreateAuction;