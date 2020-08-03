import React , {Component} from 'react';
import CarouselHome from './CarouselHome';
import "../css/home.css";
import { Row ,Container,Card } from "react-bootstrap";


//All auctions are here and cool looking carousel 
class Home extends Component {

    constructor(props) {
        super(props);
        this.state = {
            auctions: [],
            userId: '', 
            isEnough: true 
        }

        this.triggerTryClick = this.triggerTryClick.bind(this);
    }


    componentDidMount() {
        let token = JSON.parse(localStorage.getItem('token'));

        fetch('/auctions/all', {
            method: 'GET',
            headers: {
                'Authorization': `Bearer ${token}` 
            }
        })
        .then((response) => response.json())
        .then((data) => {

            console.log(data);

            this.setState({
                auctions: data
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
                userId: data.id
            });
            
        });
    }


    triggerTryClick = (auction) => {

        console.log('auction', auction)
        localStorage.setItem('auction', JSON.stringify(auction));

        let token = JSON.parse(localStorage.getItem('token'));

        let amount = auction.ammoutOfParticipants;
        let participants = auction.participants.length;


        if(participants >= amount){
            this.setState({
                isEnough: false
            })
        }else {
            fetch(`/auctions/add/${this.state.userId}/${auction.name}`, {
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
        }

        
    }

    
    render() {
        return(
            <React.Fragment>
                <CarouselHome />
                <h1>All new auctions</h1>
                <Container>
                    <Row>
                        
                        {
                            this.state.auctions.map(auction => (
                                    <Card key = {auction.id} style={{ width: '33rem' ,margin: '30px 15px' }} className="auctionCard">
                                        <Card.Body>
                                        <Card.Title>{auction.name}</Card.Title>
                                        <Card.Subtitle className="mb-2 text-muted">Participants: {auction.participants.length}/{auction.ammoutOfParticipants}</Card.Subtitle>
                                        
                                        {/* when clicking on button save to local storage current auction -> retrive from ls in auction component */}
                                        <Card.Link  onClick = {() => this.triggerTryClick(auction)} href={auction.participants.length <= auction.ammoutOfParticipants ? "/Auction" : "#Sosi"}>Try to win</Card.Link>
                                        {/* <Card.Link href="#">Book</Card.Link> */}
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

export default Home;
