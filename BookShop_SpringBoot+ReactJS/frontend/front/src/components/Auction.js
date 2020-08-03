import React , {Component} from 'react';
import { Button ,Row , Col, InputGroup , FormControl} from "react-bootstrap";
import '../css/userPage.css';

/**Auction page 
 * In right part there ara all usets that particapating and trying to win 
 * In left part there are book name and price 
 * You can raise price if your money will be last for donation you won bet and book
 * You cant donate if your bet is smaller than last donate**/
class Auction extends Component {

    constructor(props) {
        super(props);
        this.state = {
            auction: {},
            book: {},
            inputPrice: '',
            participants: [] , 
            userId: '', 
            isFinished: false , 
            bookPrice: '' ,
            lastBetId: ''
        }

        this.handlePriceChange = this.handlePriceChange.bind(this);
        this.triggerDonateclick = this.triggerDonateclick.bind(this);
    }


    handlePriceChange = (event) => {
        this.setState({
            inputPrice: event.target.value
        });
    }

    parseDeadLine = (event) => {
        let finish = this.state.auction.finish;
        let currDate = new Date();
        var date = currDate.getFullYear()+'-'+(currDate.getMonth())+'-'+currDate.getDate();
        var dateTime = date;
        // var temp= formatDate(Date.now(),"medium","en-Us");
        let normFinish = `${finish.substring(0,10).substring(0,5)}${finish.substring(0,10).substring(6,10)}`;
        normFinish === dateTime ? this.setState({isFinished: true}) :this.setState({isFinished: false});
    } 
    

    componentDidMount() {
        let token = JSON.parse(localStorage.getItem('token'));
        let auction = JSON.parse(localStorage.getItem('auction'));
        setInterval(() => {
            this.parseDeadLine();
            if(this.state.isFinished === true){
                
                fetch(`/orders/${auction.book.id}`, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': `Bearer ${token}`    
                    }
                })
                .then((response) => response.json())
                .then((data) => {
                    console.log('data', data);
                    window.location.href = "/Home";
                });
            }
        },10000)
        
        

        fetch(`/auctions/participants/${auction.name}`, {
            method: 'GET',
            headers: {
                'Authorization': `Bearer ${token}`
            }
        })
        .then((response) => response.json())
        .then((data) => {
            console.log('data', data);
            this.setState({
                participants: data
            })
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


        this.setState({
            auction: JSON.parse(localStorage.getItem('auction')),
            book: JSON.parse(localStorage.getItem('auction')).book ,
            bookPrice: JSON.parse(localStorage.getItem('auction')).book.price
        });

        setTimeout(() => {
            console.log(this.state)
        }, 200);
    }


    triggerDonateclick = (event) => {
        let token = JSON.parse(localStorage.getItem('token'));

        let price = this.state.inputPrice;
        let bookPrice = this.state.bookPrice;

        localStorage.setItem('inputPrice', JSON.stringify(price));
        if(price >= bookPrice){
            fetch(`/auctions/${this.state.userId}/${this.state.auction.name}/${this.state.inputPrice}`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${token}`    
                },
                body: JSON.stringify(this.state.inputPrice)
            })
            .then((response) => response.json())
            .then((data) => {
                console.log('data', data);
            });

            let newPrice = Number(price) + Number(this.state.bookPrice);
            this.setState({
                bookPrice: newPrice
            })
        }else {
            console.log("Not Enough Money");
        }
        

        
    }

    render() {
        return(
            <React.Fragment>
                <div className="head" >
                    
                        <h1 style={{margin : '10px 0px 10px 90px'}}>
                            {this.state.book.name}
                        </h1>
                
                </div>
                <Row className="mb-4">
                    <Col>
                        <div className="information">
                            <h3 style={{margin : '10px 0px 10px 90px' }}>
                                Price : {this.state.bookPrice}
                            </h3>
                            <img src={require("./images/06.jpg")} alt="" style={{margin : '10px 0px 10px 90px' }}/>
                            <br/>
                            <InputGroup className="mb-3" style={{width: "330px" ,margin : '0px 0px 0px 90px'}}>
                                <InputGroup.Prepend>
                                <InputGroup.Text id="inputGroup-sizing-default" >Price: </InputGroup.Text>
                                </InputGroup.Prepend>
                                <FormControl
                                aria-label="Default"
                                aria-describedby="inputGroup-sizing-default"
                                value = {this.state.inputPrice}
                                onChange = {this.handlePriceChange}
                                />
                            </InputGroup>
                            <Button style={{margin : '0px 390px 0px 0px' }} variant="primary" type="button" className="haveAccount mt-4" onClick = {this.triggerDonateclick}>
                                DONATE
                            </Button>

                        </div>
                    </Col>
                    <Col>
                        <div className="orders">
                            <h3>
                                Participants {this.state.participants.length}/{this.state.auction.ammoutOfParticipants}
                                
                            </h3>
                            {this.state.participants.map(participant => (
                                    <h4 style={{color: 'white'}} key={participant.id}>{participant.name}</h4>
                                ))
                                }
                            
                        </div>
                    </Col>
                </Row>
                
                
            </React.Fragment>
        );
    } 
}

export default Auction;