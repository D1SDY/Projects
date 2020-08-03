import React, { Component } from 'react';
import { Carousel } from "react-bootstrap";
import "../css/carousel.css";


class CarouselHome extends Component {
    render() {
        return(
<Carousel id="carousel">
  <Carousel.Item>
    <img
      className="d-block w-100"
      src={require("./images/Home.jpg")}
      alt="First slide"
    />
    <Carousel.Caption>
      <h3 style={{textShadow: '2px 2px black'}}>Best Book Store</h3>
    </Carousel.Caption>
  </Carousel.Item>
  <Carousel.Item>
    <img
      className="d-block w-100"
      src={require("./images/Irelia.jpg")}
      alt="Second slide"
    />

    <Carousel.Caption>
      <h3 style={{textShadow: '2px 2px black'}}>Best books</h3>
    </Carousel.Caption>
  </Carousel.Item>
  <Carousel.Item>
    <img
      className="d-block w-100"
      src={require("./images/science-fiction.jpg")}
      alt="Third slide"
    />

    <Carousel.Caption>
      <h3 style={{textShadow: '2px 2px black'}}>Best experience</h3>
    </Carousel.Caption>
  </Carousel.Item>
</Carousel>
        )
    }

}

export default CarouselHome;