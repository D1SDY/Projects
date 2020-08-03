import React , {Component} from 'react';
import { Row ,Container, Card , Button } from 'react-bootstrap';
import "../css/home.css";

/**Here is all books , which you can buy 
 * You cann see book name author , price and amount 
 * If you don t have enough balance you cant buy book 
 * or there are no books in store**/ 
class Shop extends Component {

    constructor(props) {
        super(props);
        this.state = {
            books: [],
            counter: 0 , 
            userBalance : "" 
        }
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
            console.log('count', data.length)
            console.log('data', data)
            this.setState({
                books: data,
                counter: data.length
            });

        });

        fetch('/users/current', {
            method: 'GET',
            headers: {
                'Authorization': `Bearer ${token}`
            }
          })
          .then((response) => response.json())
          .then((data) => {
              console.log('data', data);
      
            this.setState({
                userBalance: data.balance,
            });
            
        });


    }

    buyBook = (bookId , bookPrice , bookAmount) => {
        let token = JSON.parse(localStorage.getItem('token'));
        let balance = this.state.userBalance;

        if(balance >= bookPrice && bookAmount > 0){
            fetch(`/orders/${bookId}`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${token}`    
                }
            })
            .then((response) => response.json())
            .then((data) => {
                console.log('data', data);
            });
        } else {
            console.log("Not enough balance");
        }
        
    }

    render() {
        return(
            <React.Fragment>
                <Container>
                    <Row>
                        {
                            this.state.books.map(book => (
                                <Card style={{ width: '21rem', marginTop: '5rem' }} className="bookCard" key={book.id}>
                                    <Card.Img variant="top" src={require("./images/06.jpg")} />
                                    <Card.Body>
                                    <Card.Title ref = {this.cardTitle}>{book.name} : {book.price} <br/> 
                                        Amount of books : {book.ammount}
                                    </Card.Title>
                                        <Button onClick={this.handleButtonClick} variant="primary" onClick={() => this.buyBook(book.id , book.price , book.ammount)}>Buy</Button>
                                    </Card.Body>
                                </Card>
                            ))
                        }
                    </Row>
                </Container>
            </React.Fragment>    
        )
    }
    
}

export default Shop;